package bphc.tech.com.arena17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.app.Constants;
import bphc.tech.com.arena17.database.DBHelper;
import bphc.tech.com.arena17.sets.ScheduleSet;
import bphc.tech.com.arena17.adapter.ScheduleRecyclerAdapter;
/**
 * Created by sarath on 23-12-2016.
 */

public class EventScheduleFragment extends Fragment {
    public EventScheduleFragment() {
    }


    List<ScheduleSet> scheduleSets;
    int eventID;
    RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_schedule, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) view.findViewById(R.id.schedule_recycler_view);
        try {
            getEventSet();
            Log.e("Event name",scheduleSets.get(0).getSportName());
//            Log.e("Description",scheduleSets.get(0).getDescription());
//            Log.e("Round",scheduleSets.get(0).getRound());
//            Log.e("Time",scheduleSets.get(0).getTime()+"");

        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }
        recyclerView.setAdapter(new ScheduleRecyclerAdapter(scheduleSets, getActivity()));
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


    }
    private void getEventSet(){
        DBHelper helper = new DBHelper(getActivity());
        eventID = getActivity().getIntent().getIntExtra(Constants.Arg_Event_ID,-1);
        scheduleSets = helper.getScheduleData(eventID);
    }

    private String getTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM hh:mm a");
        return dateFormat.format(calendar.getTime());

    }
}


