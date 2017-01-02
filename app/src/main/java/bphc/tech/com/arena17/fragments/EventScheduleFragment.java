package bphc.tech.com.arena17.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
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

/**
 * Created by sarath on 23-12-2016.
 */

public class EventScheduleFragment extends Fragment {
    public EventScheduleFragment() {
    }


    ScheduleSet scheduleSet;
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

        try {
            getEventSet();
        } catch (Exception e) {
            Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
        }


    }
    private void getEventSet(){
        DBHelper helper = new DBHelper(getActivity());
        eventID = getActivity().getIntent().getIntExtra(Constants.Arg_Event_ID,-1);
        List<ScheduleSet> list = helper.getScheduleData(eventID);
        scheduleSet = list.get(0);

    }

    private String getTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM hh:mm a");
        return dateFormat.format(calendar.getTime());

    }



}
