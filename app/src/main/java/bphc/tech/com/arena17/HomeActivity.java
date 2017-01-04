package bphc.tech.com.arena17;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import bphc.tech.com.arena17.services.EventsUpdateService;
import bphc.tech.com.arena17.views.CircularMenuLayout;

public class HomeActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_home);
        startService(new Intent(this, EventsUpdateService.class));

        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            Log.e(TAG, bundle.toString());
        }
        mCircleMenuLayout = (CircularMenuLayout) findViewById(R.id.id_menulayout);
        mCircleMenuLayout.setMenuItemIconsAndTexts(mItemImgs, mItemTexts);

        mCircleMenuLayout.setOnMenuItemClickListener(new CircularMenuLayout.OnMenuItemClickListener()
        {
            @Override
            public void itemClick(View view, int pos)
            {
                switch (pos){
                    case 0:
                        //contacts
                        startActivity(new Intent(HomeActivity.this,ContactsActivity.class));
                        break;
                    case 1:
                        //campus map
                        startActivity(new Intent(HomeActivity.this,MapsActivity.class));
                        break;
                    case 2:
                        //sponsers
                        startActivity(new Intent(HomeActivity.this,CommonActivity.class).putExtra("frag_flag",2));
                        break;
                    case 3:
                        //favourites
                        startActivity(new Intent(HomeActivity.this,CommonActivity.class).putExtra("frag_flag",1));
                        break;
                    case 4:
                        //app credits
                        startActivity(new Intent(HomeActivity.this,CommonActivity.class).putExtra("frag_flag",3));
                        break;
                    default:
                        //lite
                        break;
                }
                Toast.makeText(HomeActivity.this, pos+"", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void itemCenterClick(View view)
            {
                startActivity(new Intent(HomeActivity.this,EventSelectingActivity.class));
            }
        });
    }
}
