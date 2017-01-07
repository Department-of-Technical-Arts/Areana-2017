package bphc.tech.com.arena17;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import bphc.tech.com.arena17.app.ArenaApplication;
import bphc.tech.com.arena17.services.EventsUpdateService;
import bphc.tech.com.arena17.services.MedalsUpdateService;
import bphc.tech.com.arena17.services.SponsorsUpdateService;
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
    "",
    ""};

    private int[] mItemImgs = new int[] {
            R.drawable.contactus,
            R.drawable.map,
            R.drawable.sponsors,
            R.drawable.appcredits,
            R.drawable.register,
            R.drawable.newsfeed};

    TextView textView;
    SharedPreferences preferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        startService(new Intent(this, EventsUpdateService.class));
        startService(new Intent(this, MedalsUpdateService.class));
        startService(new Intent(this, SponsorsUpdateService.class));

        textView = (TextView) findViewById(R.id.esteregg_view);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder =
                        new AlertDialog.Builder(HomeActivity.this, R.style.AppCompatAlertDialogStyle);
                builder.setTitle("About Us\n(Easter Egg)");
                builder.setMessage(R.string.about_us);
                builder.setPositiveButton("OKAY, GOT IT", null);
                builder.show();
            }
        });
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
                        Toast.makeText(HomeActivity.this, "Contact Us", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this,ContactsActivity.class));
                        break;
                    case 1:
                        //campus map
                        Toast.makeText(HomeActivity.this, "Campus Map", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this,MapsActivity.class));
                        break;
                    case 2:
                        //sponsers
                        Toast.makeText(HomeActivity.this, "Our Sponsors", Toast.LENGTH_SHORT).show();
//                        Toast.makeText(getApplicationContext(),"No Sponsers Self Sponsored",Toast.LENGTH_LONG).show();
                        startActivity(new Intent(HomeActivity.this,CommonActivity.class).putExtra("frag_flag",2));
                        startService(new Intent(HomeActivity.this, SponsorsUpdateService.class));
                        break;
                    case 3:
                        //app credits
                        Toast.makeText(HomeActivity.this, "App Credits", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this,CommonActivity.class).putExtra("frag_flag",3));
                        break;
                    case 4:
                        //register
//                        Toast.makeText(getApplicationContext(),"Registration in Progress",Toast.LENGTH_LONG).show();
                        Toast.makeText(HomeActivity.this, "Register", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this,CommonActivity.class).putExtra("frag_flag",1));
                        break;
                    case 5:
                        //news feed
                        Toast.makeText(HomeActivity.this, "News Feed", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(HomeActivity.this,NewsFeedActivity.class));
                        startService(new Intent(HomeActivity.this, MedalsUpdateService.class));
                        break;
                    default:
                        //lite
                        break;
                }

            }
            @Override
            public void itemCenterClick(View view)
            {
                startActivity(new Intent(HomeActivity.this,EventSelectingActivity.class));
            }
        });
        app = new ArenaApplication(this);
    }
    ArenaApplication app;
    @Override
    protected void onResume() {
        super.onResume();
        app.activityResumed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        app.activityPaused();
    }
}
