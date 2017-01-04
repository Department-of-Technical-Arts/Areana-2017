package bphc.tech.com.arena17;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import bphc.tech.com.arena17.fragments.AppCreditsFragment;
import bphc.tech.com.arena17.fragments.FeedFragment;
import bphc.tech.com.arena17.fragments.MedalTallyFragment;

public class CommonActivity extends AppCompatActivity {

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_common);
        toolbar = (Toolbar) findViewById(R.id.container_toolbar);
        Fragment fragment;
        FragmentTransaction ft;
        switch (getIntent().getIntExtra("frag_flag",0)){
            case 1:
                fragment = new MedalTallyFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.common_container,fragment).commit();
                toolbar.setTitle("Medal Tally");
                break;
            case 2:
                fragment = new FeedFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.common_container,fragment).commit();
                toolbar.setTitle("News Feed");
                break;
            case 3:
                fragment = new AppCreditsFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.common_container,fragment).commit();
                toolbar.setTitle("App Credits");
                break;
            default:
                Toast.makeText(CommonActivity.this,"INVALID TOKEN RECEIVED",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
