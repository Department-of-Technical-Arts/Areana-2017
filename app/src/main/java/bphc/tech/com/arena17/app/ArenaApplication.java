package bphc.tech.com.arena17.app;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by tejeshwar on 6/1/17.
 */

public class ArenaApplication extends Application {

    Context context;

    public ArenaApplication() {
        super();
    }

    public ArenaApplication(Context context) {
        this.context = context;
    }

    public boolean isActivityVisible() {
        return activityVisible;
    }

    public void activityResumed() {
        activityVisible = true;
    }

    public void activityPaused() {
        activityVisible = false;
        setPrefs(activityVisible);
    }

    SharedPreferences prefs;
    public boolean getPrefs(){
        prefs = context.getSharedPreferences(Constants.PREFS_TAG,MODE_PRIVATE);
        return prefs.getBoolean("ad_start", true);
    }

    public void setPrefs(boolean check){
        SharedPreferences prefs = context.getSharedPreferences(Constants.PREFS_TAG,MODE_PRIVATE);
        if (!check) {
            prefs.edit().putBoolean("ad_start",true).apply();
        }else {
            prefs.edit().putBoolean("ad_start",false).apply();
        }
    }

    private boolean activityVisible;
}
