package bphc.tech.com.arena17.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import bphc.tech.com.arena17.R;

/**
 * Created by sarath on 29-12-2016.
 */

class ScheduleRecyclerAdapter extends RecyclerView.Adapter<ScheduleRecyclerAdapter.ScheduleViewHolder> {
    // List<ScheduleSet> list;


    public ScheduleRecyclerAdapter() {
        //this.list = list;
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView desc, subDesc, time, date;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            desc = (TextView) itemView.findViewById(R.id.schedule_description);
            subDesc = (TextView) itemView.findViewById(R.id.schedule_sub_description);


        }
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_event_schedule_row,parent,false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        //holder.desc.setText();

    }

    @Override
    public int getItemCount() {
        return 0;
    }



}