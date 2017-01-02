package bphc.tech.com.arena17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import bphc.tech.com.arena17.adapter.SelectEventAdapter;
import bphc.tech.com.arena17.sets.EventItem;

public class EventSelectingActivity extends AppCompatActivity {

    private RecyclerView event_select;
    private GridLayoutManager layoutManager;
    private SelectEventAdapter adapter;

    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_selecting);


        MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_ads_id));
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);

        ArrayList<EventItem> items = getAllItemList();
        event_select = (RecyclerView) findViewById(R.id.events_select_recycler);
        layoutManager = new GridLayoutManager(this,3);
        event_select.setHasFixedSize(true);
        event_select.setLayoutManager(layoutManager);

        adapter = new SelectEventAdapter(this,items);
        event_select.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        if (mAdView != null) {
            mAdView.pause();
        }
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mAdView != null) {
            mAdView.resume();
        }
    }

    @Override
    public void onDestroy() {
        if (mAdView != null) {
            mAdView.destroy();
        }
        super.onDestroy();
    }
    public ArrayList<EventItem> getAllItemList(){

        ArrayList<EventItem> items = new ArrayList<>();
        items.add(new EventItem("Duathlon",R.drawable.duathlon));
        items.add(new EventItem("Kabaddi",R.drawable.kabaddi));
        items.add(new EventItem("Hockey",R.drawable.hockey));
        items.add(new EventItem("Body Building",R.drawable.bodybuilding));
        items.add(new EventItem("Athletics",R.drawable.athletics));
        items.add(new EventItem("Carrom",R.drawable.carrom));
        items.add(new EventItem("Billiards",R.drawable.billiards));
        items.add(new EventItem("Squash",R.drawable.squash));
        items.add(new EventItem("Table Tennis",R.drawable.tabletennis));
        items.add(new EventItem("Chess",R.drawable.chess));
        items.add(new EventItem("Badminton",R.drawable.badminton));
        items.add(new EventItem("Cricket",R.drawable.cricket));
        items.add(new EventItem("Volleyball",R.drawable.volleyball));
        items.add(new EventItem("ThrowBall",R.drawable.throwball));
        items.add(new EventItem("Tennis",R.drawable.tennis));
        items.add(new EventItem("Basketball",R.drawable.basketball));
        items.add(new EventItem("Football",R.drawable.football));
        items.add(new EventItem("Weight Lifting",R.drawable.weightlifting));
        return items;
    }

}
