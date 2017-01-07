package org.arenatest.bits.arena_test.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.List;

import org.arenatest.bits.arena_test.api.ApiClient;
import org.arenatest.bits.arena_test.api.ApiInterface;
import org.arenatest.bits.arena_test.app.Constants;
import org.arenatest.bits.arena_test.database.DBHelper;
import org.arenatest.bits.arena_test.sets.EventsData;
import org.arenatest.bits.arena_test.sets.EventsSet;
import org.arenatest.bits.arena_test.sets.ScheduleData;
import org.arenatest.bits.arena_test.sets.ScheduleSet;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by tejeshwar on 20/12/16.
 */

public class EventsUpdateService extends IntentService {

    public static final String TAG = "EVENTS UPDATE SERVICE";

    public EventsUpdateService() {
        super("EventsUpdateService");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(final Intent intent) {

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        final Call<EventsData> events = apiService.getEvents(Constants.EVENTS_TAG,0);
        events.enqueue(new Callback<EventsData>() {
            @Override
            public void onResponse(Call<EventsData> call, Response<EventsData> response) {
                DBHelper helper = new DBHelper(getApplicationContext());
                helper.deleteEventsTable();
                List<EventsSet> eventsSet = response.body().getData();
                Log.e(TAG,eventsSet.size()+"");
                for (int i=0;i<eventsSet.size();i++){
//                    Log.e(TAG, Integer.parseInt(eventsSet.get(i).getGender())+"");

                    helper.addEventIdRow(
                            eventsSet.get(i).getEventId(),
                            eventsSet.get(i).getName());

                    helper.addEventsRow(
                            eventsSet.get(i).getEventId(),
                            eventsSet.get(i).getName(),
                            eventsSet.get(i).getCaptainName(),
                            eventsSet.get(i).getContact(),
                            eventsSet.get(i).getImageUrl(),
                            eventsSet.get(i).getPdfUrl(),
                            eventsSet.get(i).getLongitude(),
                            eventsSet.get(i).getLatitude(),
                            eventsSet.get(i).getUpdatedAt(),
                            eventsSet.get(i).getPrize(),
                            eventsSet.get(i).getGender());

//                    Log.d(TAG,successID +"        "+ successEvent);
                }
            }

            @Override
            public void onFailure(Call<EventsData> call, Throwable t) {
//                Log.e(TAG,"Check your internet connection");
            }
        });

        final Call<ScheduleData> scheduleDataCall = apiService.getSchedule(Constants.SCHEDULE_TAG,0);
        scheduleDataCall.enqueue(new Callback<ScheduleData>() {
            @Override
            public void onResponse(Call<ScheduleData> call, Response<ScheduleData> response) {
                DBHelper helper = new DBHelper(getApplicationContext());
                helper.deleteScheduleTable();
                List<ScheduleSet> scheduleSets = response.body().getData();
                Log.e(TAG,scheduleSets.size()+"");
                for (int i=0;i<scheduleSets.size();i++){
//                    Log.e(TAG, Integer.parseInt(scheduleSets.get(i).getGender())+"");
                    helper.addScheduleRow(
                            scheduleSets.get(i).getEventId(),
                            scheduleSets.get(i).getTime(),
                            scheduleSets.get(i).getUpdatedAt(),
                            scheduleSets.get(i).getSportName(),
                            scheduleSets.get(i).getEventDate(),
                            scheduleSets.get(i).getGender(),
                            scheduleSets.get(i).getDescription(),
                            scheduleSets.get(i).getVenue(),
                            scheduleSets.get(i).getRound(),
                            scheduleSets.get(i).getVs());
//                    Log.e(TAG,success+"");
                }
            }
            @Override
            public void onFailure(Call<ScheduleData> call, Throwable t) {
                Log.e(TAG,"Check your internet connection");
            }
        });
    }
}
