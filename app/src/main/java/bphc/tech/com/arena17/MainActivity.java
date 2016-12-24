package bphc.tech.com.arena17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bphc.tech.com.arena17.services.EventsUpdateService;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, EventsUpdateService.class));
    }
}
