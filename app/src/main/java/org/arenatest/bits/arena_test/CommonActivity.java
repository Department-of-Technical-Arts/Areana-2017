package org.arenatest.bits.arena_test;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import org.arenatest.bits.arena_test.fragments.AppCreditsFragment;
import org.arenatest.bits.arena_test.fragments.RegistrationFragment;
import org.arenatest.bits.arena_test.fragments.SponsorsFragment;

public class CommonActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{

    Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.slide_up, R.anim.stay);
        setContentView(R.layout.activity_common);
//        startActivity(new Intent(this, MedalsUpdateService.class));
//        startActivity(new Intent(this, SponsorsUpdateService.class));
        toolbar = (Toolbar) findViewById(R.id.container_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }

        Fragment fragment;
        FragmentTransaction ft;
        switch (getIntent().getIntExtra("frag_flag",0)){
            case 1:
                fragment = new RegistrationFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.common_container,fragment).commit();
                getSupportActionBar().setTitle("Register");
                break;
            case 2:
                fragment = new SponsorsFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.common_container,fragment).commit();
                getSupportActionBar().setTitle("Our Sponsors");
                break;
            case 3:
                fragment = new AppCreditsFragment();
                ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.common_container,fragment).commit();
                getSupportActionBar().setTitle("App Credits");
                break;
            default:
                Toast.makeText(CommonActivity.this,"INVALID TOKEN RECEIVED",Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        overridePendingTransition(R.anim.stay, R.anim.slide_down);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
