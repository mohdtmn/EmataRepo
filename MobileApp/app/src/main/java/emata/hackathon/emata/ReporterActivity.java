package emata.hackathon.emata;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Locale;

public class ReporterActivity extends AppCompatActivity {

    double xLoc;
    double yLoc;

    void setView(boolean done)
    {
        LinearLayout ly = (LinearLayout) findViewById(R.id.pnlThanks);
        ly.setVisibility(done? View.VISIBLE : View.GONE);
        ly = (LinearLayout) findViewById(R.id.pnlVote);
        ly.setVisibility(!done? View.VISIBLE : View.GONE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporter);
        getSupportActionBar().hide();
        setView(false);
        // Acquire a reference to the system Location Manager
        LocationManager locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        final ReporterActivity me = this;
        // Define a listener that responds to location updates
        LocationListener locationListener = new LocationListener() {
            public void onLocationChanged(Location location) {
                Log.i("msg", "Location: " + location.getLatitude());
                xLoc = location.getLatitude();
                yLoc = location.getLongitude();
            }

            public void onStatusChanged(String provider, int status, Bundle extras) {
            }

            public void onProviderEnabled(String provider) {
            }

            public void onProviderDisabled(String provider) {
            }
        };

// Register the listener with the Location Manager to receive location updates
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.

        } else
        {
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
        }

        Button btnGood = (Button) this.findViewById(R.id.btnGood);
        Button btnBad = (Button) this.findViewById(R.id.btnBad);
        btnGood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendResponse(0);;
            }
        });
        btnBad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendResponse(1);
            }
        });
    }

    void sendResponse(int rType)
    {
        URL url = null;
        try {
            url = new URL("http://192.168.42.99:50390/api/CleanReport?x=" + xLoc + "&y=" + yLoc + "&type=" + rType);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();
            Log.i("msg" , "respnes: " + statusCode);
        } catch (Exception e) {
            Log.i("msg" , "Error: " + e.getMessage());
        }
        setView(true);
    }
}