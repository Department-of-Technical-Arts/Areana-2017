package bphc.tech.com.arena17;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.List;

import bphc.tech.com.arena17.api.ApiClient;
import bphc.tech.com.arena17.api.ApiInterface;
import bphc.tech.com.arena17.app.Constants;
import bphc.tech.com.arena17.sets.EventsData;
import bphc.tech.com.arena17.sets.EventsSet;
import bphc.tech.com.arena17.sets.ScheduleData;
import bphc.tech.com.arena17.sets.ScheduleSet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MAIN ACTIVITY";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<EventsData> events = apiService.getEvents(Constants.EVENTS_TAG);
        events.enqueue(new Callback<EventsData>() {
            @Override
            public void onResponse(Call<EventsData> call, Response<EventsData> response) {
                List<EventsSet> eventsSet = response.body().getData();
                Log.e(TAG,eventsSet.size()+"");
            }

            @Override
            public void onFailure(Call<EventsData> call, Throwable t) {
                Log.e(TAG,"Check your internet connection");
            }
        });

        Call<ScheduleData> schedules = apiService.getSchedule(Constants.SCHEDULE_TAG);
        schedules.enqueue(new Callback<ScheduleData>() {
            @Override
            public void onResponse(Call<ScheduleData> call, Response<ScheduleData> response) {
                List<ScheduleSet> scheduleSet = response.body().getData();
                Log.e(TAG,scheduleSet.size()+"");
            }

            @Override
            public void onFailure(Call<ScheduleData> call, Throwable t) {
                Log.e(TAG,"Check your internet connection");
            }
        });
    }
}
