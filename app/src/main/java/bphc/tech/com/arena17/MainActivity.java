package bphc.tech.com.arena17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import bphc.tech.com.arena17.services.EventsUpdateService;
import bphc.tech.com.arena17.views.CircularMenuLayout;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN ACTIVITY";
    private CircularMenuLayout mCircleMenuLayout;

    /*private String[] mItemTexts = new String[] {
            "Home",
            "App Credits",
            "Contact us",
            "Favourites",
            "Reach Campus" ,
            "Sponsors" };*/

    private String[] mItemTexts = new String[] {
            "",
            "",
            "",
            "",
            ""};

    private int[] mItemImgs = new int[] {
            R.drawable.contactus,
            R.drawable.map,
            R.drawable.sponsors,
            R.drawable.favorites,
            R.drawable.appcredits};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        startService(new Intent(this, EventsUpdateService.class));

/*        DBHelper helper = new DBHelper(this);
        ArrayList<EventsSet> eventsSets = helper.getEventData(1);
        ArrayList<ScheduleSet> scheduleSets = helper.getScheduleData(1);

        for (int i=0 ;i<eventsSets.size();i++){
            Log.e(TAG,eventsSets.get(i).getName() + "\n" +
                    eventsSets.get(i).getCaptainName()+ "\n"+
                    eventsSets.get(i).getGender() + "\n" +
                    eventsSets.get(i).getImageUrl() + "\n");
        }

        for (int i=0 ;i<scheduleSets.size();i++){
            Log.e(TAG,scheduleSets.get(i).getSportName() + "\n" +
                    scheduleSets.get(i).getTime()+ "\n"+
                    scheduleSets.get(i).getEventDate() + "\n" +
                    scheduleSets.get(i).getUpdatedAt() + "\n");
        }*/

        mCircleMenuLayout = (CircularMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        mCircleMenuLayout.setOnMenuItemClickListener(new CircularMenuLayout.OnMenuItemClickListener()
        {
            @Override
            public void itemClick(View view, int pos)
            {
                Toast.makeText(MainActivity.this, pos+"",
                        Toast.LENGTH_SHORT).show();
            }
            @Override
            public void itemCenterClick(View view)
            {
                startActivity(new Intent(MainActivity.this,EventSelectingActivity.class));
            }
        });
    }
}
