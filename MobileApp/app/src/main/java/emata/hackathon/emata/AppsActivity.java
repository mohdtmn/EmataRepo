package emata.hackathon.emata;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AppsActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apps);
        Button btnGuide = this.findViewById(R.id.btnDriverGuide);
        final AppsActivity me = this;
        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(me, DriverMap.class);
                me.startActivity(myIntent);
            }
        });

        Button btnReporter = this.findViewById(R.id.btnReporter);
        btnReporter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent myIntent = new Intent(me, ReporterActivity.class);
                me.startActivity(myIntent);
            }
        });
    }
}
