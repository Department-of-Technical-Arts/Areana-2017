package bphc.tech.com.arena17.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.sets.ScheduleSet;

/**
 * Created by sarath on 29-12-2016.
 */

public class ScheduleRecyclerAdapter extends RecyclerView.Adapter<ScheduleRecyclerAdapter.ScheduleViewHolder> {
    List<ScheduleSet> list;
    Context context;

    public ScheduleRecyclerAdapter(List<ScheduleSet> list, Context context) {
        this.list = list;
        this.context = context;
    }

    class ScheduleViewHolder extends RecyclerView.ViewHolder {
        TextView vertical, description, time, date,round;
        LinearLayout horizontal;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            vertical = (TextView) itemView.findViewById(R.id.schedule_vertical_text_view);
            description = (TextView) itemView.findViewById(R.id.schedule_description);
            time = (TextView) itemView.findViewById(R.id.schedule_time);
            date = (TextView) itemView.findViewById(R.id.schedule_date);
            round = (TextView) itemView.findViewById(R.id.schedule_round);
            horizontal=(LinearLayout) itemView.findViewById(R.id.schedule_horizontal_color_linear_layout);
        }
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_event_schedule_row,parent,false);
        return new ScheduleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        holder.round.setText(("Round "+list.get(position).getRound()));
        holder.description.setText((list.get(position).getDescription()));
        holder.time.setText(getTime(list.get(position).getTime()));
        holder.date.setText(getDate(list.get(position).getTime()));
        int cnum = position%4;
        if(cnum==1){
            holder.horizontal.setBackgroundColor(context.getResources().getColor(R.color.schedule_green_horizontal));
            holder.vertical.setBackgroundColor(context.getResources().getColor(R.color.schedule_green_vertical));
        }else if(cnum==2){
            holder.horizontal.setBackgroundColor(context.getResources().getColor(R.color.schedule_purple_horizontal));
            holder.vertical.setBackgroundColor(context.getResources().getColor(R.color.schedule_purple_vertical));
        } else if(cnum==3){
            holder.horizontal.setBackgroundColor(context.getResources().getColor(R.color.schedule_blue_horizontal));
            holder.vertical.setBackgroundColor(context.getResources().getColor(R.color.schedule_blue_vertical));
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    private String getTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
        return dateFormat.format(calendar.getTime());
    }

    private String getDate(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        String date = mDay+" Jan";
        return date;
    }

}