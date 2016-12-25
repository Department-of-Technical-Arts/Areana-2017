package bphc.tech.com.arena17;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.List;

import bphc.tech.com.arena17.app.Constants;
import bphc.tech.com.arena17.database.DBHelper;
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
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_details);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Details"));
        tabLayout.addTab(tabLayout.newTab().setText("Schedule"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        tabLayout.setupWithViewPager(mViewPager);

        /*      setUpWithViewPager will take care of the below code: :-)
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });*/


        toolbar = (Toolbar) findViewById(R.id.events_toolbar);
        setSupportActionBar(toolbar);
        collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.events_collapsingToolbar);
        //Title is set using fillDetails method!

        // ActionBar back button:
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Obtain the Event.
        try{
            DBHelper helper = new DBHelper(this);
            int eventID = getIntent().getIntExtra(Constants.Arg_Event_ID,-1);
            if (eventID != -1){
                List<EventsSet> eventList = helper.getEventData(eventID);
                event = eventList.get(0);
                fillDetails(event);
            }
            else {
                Toast.makeText(this,"Sorry.. no such event found..!!",Toast.LENGTH_SHORT).show();}
        }catch (Exception e){
            Toast.makeText(this,"Error getting event",Toast.LENGTH_SHORT).show();
            collapsingToolbar.setTitle("Inside catch");
        }

        nestedScrollView = (NestedScrollView) findViewById (R.id.NestedScrollView);
        nestedScrollView.setFillViewport (true); // needed for the nestedScrollView to be displayed.
    }

    private void fillDetails(EventsSet event){
        if(event.getName().isEmpty()){
            collapsingToolbar.setTitle("Error");
        }
        else{
            collapsingToolbar.setTitle(event.getName());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_star,menu);
        MenuItem item = menu.findItem(R.id.menu_star);
        // TODO: 25-12-2016 add appropriate ic for star
        //if isFavorite(eventID){setIcon selected} **
        //item.setIcon(R.drawable.ic_star_selected);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

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
                case 1: return new EventScheduleFragment();
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
                    return "Fixtures";
            }
            return null;
        }
    }
}

