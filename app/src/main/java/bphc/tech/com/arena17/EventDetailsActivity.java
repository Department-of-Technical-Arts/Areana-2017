package bphc.tech.com.arena17;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.flaviofaria.kenburnsview.KenBurnsView;
import com.google.android.gms.maps.model.LatLng;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Field;
import java.util.List;

import bphc.tech.com.arena17.app.Constants;
import bphc.tech.com.arena17.database.DBHelper;
import bphc.tech.com.arena17.fragments.EventDetailsFragment;
import bphc.tech.com.arena17.fragments.EventScheduleFragment;
import bphc.tech.com.arena17.fragments.NoScheduleFragment;
import bphc.tech.com.arena17.sets.EventsSet;

/**
 * Created by sarath on 23-12-2016.
 */

public class EventDetailsActivity extends AppCompatActivity {

    SectionsPagerAdapter mSectionsPagerAdapter;
    ViewPager mViewPager;
    Toolbar toolbar;
    CollapsingToolbarLayout collapsingToolbar;
    NestedScrollView nestedScrollView;
    EventsSet event;
    int eventID;
    DBHelper helper;
    KenBurnsView eventImage;
    CoordinatorLayout baseLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_up, R.anim.stay);
        setContentView(R.layout.activity_event_details);

        //Instantiate all views
        eventImage = (KenBurnsView) findViewById(R.id.event_image);
        mViewPager = (ViewPager) findViewById(R.id.container);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        baseLayout = (CoordinatorLayout) findViewById(R.id.event_details_coordinator);
        // Setup Tab Layout
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //MyAdapter
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager.setAdapter(mSectionsPagerAdapter);
        tabLayout.setupWithViewPager(mViewPager);

        toolbar = (Toolbar) findViewById(R.id.events_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.events_collapsingToolbar);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.ExpandedAppBar);
        collapsingToolbar.setCollapsedTitleTextAppearance(R.style.CollapsedAppBar);
        //Title is set using fillDetails method!

        // ActionBar back button:
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        //Obtain the Event.
        getAndFillEventData();

        // needed for the nestedScrollView to be displayed.
        nestedScrollView = (NestedScrollView) findViewById(R.id.NestedScrollView);
        nestedScrollView.setFillViewport(true);

        //from here
       ImageView imageButton = (ImageView) findViewById(R.id.event_location);
        helper = new DBHelper(this);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LatLng latLng = helper.getLocation(getIntent().getIntExtra(Constants.Arg_Event_ID, -1));
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("http://maps.google.com/maps?daddr="+latLng.latitude+","+latLng.longitude)));
            }
        });
    }

//till here
    private void fillData(EventsSet event) {
        if (event.getName().isEmpty()) {
            collapsingToolbar.setTitle("Arena 17");
        } else {
            collapsingToolbar.setTitle(event.getName());
            Picasso.with(this)
                    .load(event.getImageUrl())
                    .placeholder(R.drawable.ic_home_small)   // optional
                    .error(R.drawable.ic_home_small)      // optional
                    .into(eventImage);
        }
    }

    private void getAndFillEventData() {
        try {
            helper = new DBHelper(this);
            eventID = getIntent().getIntExtra(Constants.Arg_Event_ID, -1);
            if (eventID != -1) {
                List<EventsSet> eventList = helper.getEventData(eventID);
                event = eventList.get(0);
                fillData(event);        // Filling Data
            } else {
                Toast.makeText(this, "Sorry.. no such event found..!!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Error getting event", Toast.LENGTH_SHORT).show();
            collapsingToolbar.setTitle("Error");
        }
    }

    private void makeCollapsingToolbarLayoutLooksGood(CollapsingToolbarLayout collapsingToolbarLayout) {
        try {
            final Field field = collapsingToolbarLayout.getClass().getDeclaredField("mCollapsingTextHelper");
            field.setAccessible(true);

            final Object object = field.get(collapsingToolbarLayout);
            final Field tpf = object.getClass().getDeclaredField("mTextPaint");
            tpf.setAccessible(true);

            ((TextPaint) tpf.get(object)).setTypeface(Typeface.createFromAsset(getAssets(), "Roboto-Bold.ttf"));
            ((TextPaint) tpf.get(object)).setColor(getResources().getColor(R.color.white));
        } catch (Exception ignored) {
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    // Pager Adapter
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            //return PlaceholderFragment.newInstance(position + 1);
            switch (position){
                case 0: return new EventDetailsFragment();
                case 1: {
                    try{
                        DBHelper helper1 = new DBHelper(EventDetailsActivity.this);
                        int eventID1 = getIntent().getIntExtra(Constants.Arg_Event_ID, -1);
                        if(helper1.getScheduleData(eventID1).size()!=0){
                            return new EventScheduleFragment();
                        }
                        else {return new NoScheduleFragment();}
                    }catch (Exception e){
                        return new NoScheduleFragment();
                    }


                }
            }
            return null;
        }

        @Override
        public int getCount() {
            // Show 2 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Details";
                case 1:
                    return "Schedule";
            }
            return null;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_down);
    }
}

