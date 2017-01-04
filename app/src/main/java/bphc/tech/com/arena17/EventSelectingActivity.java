package bphc.tech.com.arena17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.android.gms.ads.AdView;

import java.util.ArrayList;

import bphc.tech.com.arena17.adapter.SelectEventAdapter;
import bphc.tech.com.arena17.database.DBHelper;
import bphc.tech.com.arena17.sets.EventItem;
import bphc.tech.com.arena17.sets.PrizeItem;

public class EventSelectingActivity extends AppCompatActivity {

    private RecyclerView event_select;
    private GridLayoutManager layoutManager;
    private SelectEventAdapter adapter;

    AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_selecting);

        /*MobileAds.initialize(getApplicationContext(), getResources().getString(R.string.banner_ads_id));
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest
                .Builder()
                .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
                .build();
        mAdView.loadAd(adRequest);*/

        ArrayList<EventItem> items = getAllItemList();
        event_select = (RecyclerView) findViewById(R.id.events_select_recycler);
        layoutManager = new GridLayoutManager(this,3);
        event_select.setHasFixedSize(true);
        event_select.setLayoutManager(layoutManager);

        adapter = new SelectEventAdapter(this,items);
        event_select.setAdapter(adapter);

        DBHelper helper = new DBHelper(this);
        ArrayList<PrizeItem> prizeItems = helper.getEventPrizes(4);
        for (int i=0;i<prizeItems.size();i++){
            Log.e("prize " , prizeItems.get(i).getGender()+"");
            Log.e("prize " , prizeItems.get(i).getPrize()+"");
        }
    }

    public ArrayList<EventItem> getAllItemList(){

        ArrayList<EventItem> items = new ArrayList<>();
        items.add(new EventItem("Duathlon", R.drawable.duathlon, 17));
        items.add(new EventItem("Kabaddi", R.drawable.kabaddi, 6));
        items.add(new EventItem("Hockey", R.drawable.hockey, 12));
        items.add(new EventItem("Body Building", R.drawable.bodybuilding, 16));
        items.add(new EventItem("Athletics", R.drawable.athletics, 15));
        items.add(new EventItem("Carrom", R.drawable.carrom, 13));
        items.add(new EventItem("Billiards", R.drawable.billiards, 14));
        items.add(new EventItem("Squash", R.drawable.squash, 11));
        items.add(new EventItem("Table Tennis", R.drawable.tabletennis, 10));
        items.add(new EventItem("Chess", R.drawable.chess, 8));
        items.add(new EventItem("Badminton", R.drawable.badminton, 4));
        items.add(new EventItem("Cricket", R.drawable.cricket, 1));
        items.add(new EventItem("Volleyball", R.drawable.volleyball, 3));
        items.add(new EventItem("ThrowBall", R.drawable.throwball, 7));
        items.add(new EventItem("Tennis", R.drawable.tennis, 9));
        items.add(new EventItem("Basketball", R.drawable.basketball, 5));
        items.add(new EventItem("Football", R.drawable.football, 2));
        items.add(new EventItem("Power Lifting",R.drawable.weightlifting,18));
        items.add(new EventItem("Snooker",R.drawable.billiards,19));
        return items;
    }

}
