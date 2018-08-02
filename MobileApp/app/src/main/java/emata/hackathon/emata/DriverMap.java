package emata.hackathon.emata;

import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
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

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class DriverMap extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_map);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        Button btnNext = this.findViewById(R.id.btnNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setActiveTrash( (int) (Math.random()*100), 21.412294 + Math.random()*0.01, 39.890611+ Math.random()*0.01);
            }
        });
    }

    private void getTrashJob() throws Exception
    {
        URL url = new URL("http://localhost:8080/web/get?key=value");
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
        }
        else
        {
            //Handle else
        }

    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        setActiveTrash(11, 21.412294 + Math.random()*0.001, 39.890611+ Math.random()*0.001);
    }

    public void setActiveTrash(int trashId, double x, double y)
    {
        mMap.clear();
        LatLng trashLocation = new LatLng(x, y);
        mMap.addMarker(new MarkerOptions().position(trashLocation).title("Trash #" + trashId));
        CameraUpdate cam = CameraUpdateFactory.newLatLngZoom(trashLocation, 17);
        mMap.moveCamera(cam);
        ((TextView) findViewById(R.id.txtTrashId)).setText("Move to trash # " + trashId);
    }

    public void loadDriverInfo()
    {

    }
}
