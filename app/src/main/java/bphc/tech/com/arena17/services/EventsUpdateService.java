package bphc.tech.com.arena17.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import bphc.tech.com.arena17.database.DBHelper;
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

/**
 * Created by tejeshwar on 20/12/16.
 */

public class EventsUpdateService extends IntentService {

    public static final String TAG = "EVENTS UPDATE SERVICE";

    public EventsUpdateService() {
        super("EvnetsUpdateService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(final Intent intent) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<EventsData> events = apiService.getEvents(Constants.EVENTS_TAG);
        events.enqueue(new Callback<EventsData>() {
            @Override
            public void onResponse(Call<EventsData> call, Response<EventsData> response) {
                DBHelper helper = new DBHelper(getApplicationContext());
                List<EventsSet> eventsSet = response.body().getData();
                Log.e(TAG,eventsSet.size()+"");
                long successID,successEvent;
                for (int i=0;i<eventsSet.size();i++){
//                    Log.e(TAG, Integer.parseInt(eventsSet.get(i).getGender())+"");

                    successID = helper.addEventIdRow(
                            eventsSet.get(i).getEventId(),
                            eventsSet.get(i).getName());

                    successEvent = helper.addEventsRow(
                            eventsSet.get(i).getEventId(),
                            eventsSet.get(i).getName(),
                            eventsSet.get(i).getCaptainName(),
                            eventsSet.get(i).getContact(),
                            eventsSet.get(i).getImageUrl(),
                            eventsSet.get(i).getPdfUrl(),
                            eventsSet.get(i).getLongitude(),
                            eventsSet.get(i).getLatitude(),
                            eventsSet.get(i).getUpdatedAt(),
                            eventsSet.get(i).getGender());

                    Log.d(TAG,successID +"        "+ successEvent);
                }
            }

            @Override
            public void onFailure(Call<EventsData> call, Throwable t) {
                Log.e(TAG,"Check your internet connection");
            }
        });

        final Call<ScheduleData> scheduleDataCall = apiService.getSchedule(Constants.SCHEDULE_TAG,0);
        scheduleDataCall.enqueue(new Callback<ScheduleData>() {
            @Override
            public void onResponse(Call<ScheduleData> call, Response<ScheduleData> response) {
                DBHelper helper = new DBHelper(getApplicationContext());
                List<ScheduleSet> scheduleSets = response.body().getData();
                Log.e(TAG,scheduleSets.size()+"");
                long success;
                for (int i=0;i<scheduleSets.size();i++){
//                    Log.e(TAG, Integer.parseInt(scheduleSets.get(i).getGender())+"");
                    success = helper.addScheduleRow(
                            scheduleSets.get(i).getEventId(),
                            scheduleSets.get(i).getSportName(),
                            scheduleSets.get(i).getTime(),
                            scheduleSets.get(i).getUpdatedAt(),
                            scheduleSets.get(i).getEventDate(),
                            scheduleSets.get(i).getGender());
                    Log.d(TAG,success+"");
                }
            }
            @Override
            public void onFailure(Call<ScheduleData> call, Throwable t) {
                Log.e(TAG,"Check your internet connection");
            }
        });
    }
}
