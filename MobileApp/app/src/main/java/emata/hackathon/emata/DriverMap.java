package emata.hackathon.emata;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.StrictMode;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Random;

public class DriverMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private double trashX;
    private double trashY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        setContentView(R.layout.activity_driver_map);
        showMap(false);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button btnNext = this.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //setActiveTrash( (int) (Math.random()*100), 21.412294 + Math.random()*0.01, 39.890611+ Math.random()*0.01);
                getTrashJob();
            }
        });
        Button btnNav = this.findViewById(R.id.btnNav);
        btnNav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr=" + trashX + "," + trashY));
                startActivity(intent);
            }
        });
        getTrashJob();
        loadDriverInfo("Mohammad Shehri" , 1);
    }

    private void showMap(boolean showMap)
    {
        findViewById(R.id.map).setVisibility(showMap ? View.VISIBLE : View.INVISIBLE);
        if (!showMap)
            ((TextView) findViewById(R.id.txtTrashId)).setText("Waiting for next job.");
    }

    private void getTrashJob()
    {
        //showMap(false);
        final DriverMap me = this;
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                getTrashJobThread(me);
            }
        });
        th.start();
    }

    private void getTrashJobThread(final DriverMap me)
    {
        URL url = null;
        try {
            url = new URL("http://192.168.42.99:50390/api/TrashJob/?DriverId=1");
        HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
        urlConnection.setRequestMethod("GET");
        int statusCode = urlConnection.getResponseCode();
        if (statusCode ==  200) {
            InputStream it = new BufferedInputStream(urlConnection.getInputStream());
            InputStreamReader read = new InputStreamReader(it);
            BufferedReader buff = new BufferedReader(read);
            StringBuilder dta = new StringBuilder();
            String chunks ;
            while((chunks = buff.readLine()) != null)
            {
                dta.append(chunks);
            }
            Handler mainHandler = new Handler(me.getMainLooper());
            final JSONArray reader = new JSONArray(dta.toString());

            Runnable myRunnable = new Runnable() {

                @Override
                public void run() {
                    try {
                        me.setActiveTrash(reader.getInt(0),reader.getDouble(1),reader.getDouble(2));
                    } catch (JSONException e) {

                    }
                }
            };
            mainHandler.post(myRunnable);
        }
        else
        {
        }
        } catch (Exception e) {
            e.printStackTrace();
            Log.i("msg" , "ERR " + e.getMessage());
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        //setActiveTrash(11, 21.412294 + Math.random()*0.001, 39.890611+ Math.random()*0.001);
    }

    public void setActiveTrash(int trashId, double x, double y)
    {
        trashX = x;
        trashY = y;
        mMap.clear();
        LatLng trashLocation = new LatLng(x, y);
        mMap.addMarker(new MarkerOptions().position(trashLocation).title("Trash #" + trashId));
        CameraUpdate cam = CameraUpdateFactory.newLatLngZoom(trashLocation, 17);
        mMap.moveCamera(cam);
        ((TextView) findViewById(R.id.txtTrashId)).setText("Move to trash # " + trashId);
        showMap(true);
    }

    public void loadDriverInfo(String name, int id)
    {
        ((TextView) findViewById(R.id.txtDriver)).setText("Driver - " + name);
        ((TextView) findViewById(R.id.txtDriverID)).setText("Driver ID # " + id);
    }
}
