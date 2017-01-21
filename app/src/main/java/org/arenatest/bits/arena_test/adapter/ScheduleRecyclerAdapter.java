package org.arenatest.bits.arena_test.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.arenatest.bits.arena_test.R;
import org.arenatest.bits.arena_test.sets.ScheduleSet;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

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
        TextView description, time, date, round, vsTag;
        CardView vertical;
        LinearLayout horizontal;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            vertical = (CardView) itemView.findViewById(R.id.schedule_vertical_card_view);
            description = (TextView) itemView.findViewById(R.id.schedule_description);
            time = (TextView) itemView.findViewById(R.id.schedule_time);
            date = (TextView) itemView.findViewById(R.id.schedule_date);
            round = (TextView) itemView.findViewById(R.id.schedule_round);
            vsTag = (TextView) itemView.findViewById(R.id.schedule_vs_tag);
            horizontal = (LinearLayout) itemView.findViewById(R.id.schedule_horizontal_color_linear_layout);
            //title= (TextView) itemView.findViewById(R.id.schedule_title);
        }
    }

    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_inaug_card, parent, false);
            return new ScheduleViewHolder(view);
        } else if (viewType == 1) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_event_schedule_row, parent, false);
            return new ScheduleViewHolder(view);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_closing_ceremony_card, parent, false);
            return new ScheduleViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int Mposition) {
        int position = Mposition - 1;

        if (Mposition == 0 || Mposition == list.size() +1) {
            //do nothing
        } else {
            holder.round.setText(("Round: " + list.get(position).getRound()));
            //for description/ remarks
            String mdesc = list.get(position).getDescription();
            if (mdesc.isEmpty() || mdesc.equals(" ")) {
                //do nothing
            } else {
                holder.description.setText(mdesc);
                holder.description.setVisibility(View.VISIBLE);
            }

            //date and time
            holder.time.setText(getTime(list.get(position).getEventDate()));

            int date = myDate(list.get(position).getEventDate());
            if (date%2==1){
                holder.date.setText(getDate(list.get(position).getEventDate()));
                holder.horizontal.setBackgroundColor(context.getResources().getColor(R.color.schedule_green_horizontal));
                holder.vertical.setCardBackgroundColor(context.getResources().getColor(R.color.schedule_green_vertical));
            }
            else {
                holder.horizontal.setBackgroundColor(context.getResources().getColor(R.color.schedule_red_horizontal));
                holder.vertical.setCardBackgroundColor(context.getResources().getColor(R.color.schedule_red_vertical));
                holder.date.setText(getDate(list.get(position).getEventDate()));
            }




            //for vs tag:
            int mgender = list.get(position).getGender();
            if (mgender == 2) {
                holder.vsTag.setText(list.get(position).getVs());
            } else {
                if (mgender == 0) {
                    holder.vsTag.setText((list.get(position).getVs() + " (Girls)"));
                } else if (mgender == 1) {
                    holder.vsTag.setText((list.get(position).getVs() + " (Girls)"));
                }
            }

           /* int cnum = position % 2;
            if (cnum == 1) {
                holder.horizontal.setBackgroundColor(context.getResources().getColor(R.color.schedule_green_horizontal));
//            holder.vertical.setBackgroundColor(context.getResources().getColor(R.color.schedule_green_vertical));
            }else if(cnum==2){ // for other colors to be displayed
            holder.horizontal.setBackgroundColor(context.getResources().getColor(R.color.schedule_purple_horizontal));
            holder.vertical.setBackgroundColor(context.getResources().getColor(R.color.schedule_purple_vertical));
        } else if(cnum==3){
            holder.horizontal.setBackgroundColor(context.getResources().getColor(R.color.schedule_blue_horizontal));
            holder.vertical.setBackgroundColor(context.getResources().getColor(R.color.schedule_blue_vertical));
        }*/

        }

    }

    @Override
    public int getItemViewType(int Mposition) {
        if (Mposition == 0) {   // inaug card
            return 0;
        } else if (Mposition == list.size() + 1) { // closing card
            return 2;
        } else {
            return 1;
        } // normal card
    }

    @Override
    public int getItemCount() {
        return list.size() + 2;   //inaug and closing cards
    }

    private String getTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");

        return dateFormat.format(calendar.getTime());
    }

/*
    private String getDate(long time){
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        String date = mDay+" Jan";
        return date;
    }
*/

    String getDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
//        int mYear = calendar.get(Calendar.YEAR);
//        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);
        if (mDay == 21){
            return (Integer.toString(mDay) + "st Jan");
        }
        if(mDay == 22){
            return (Integer.toString(mDay) + "nd Jan");
        }
        if(mDay == 23){
            return (Integer.toString(mDay) + "rd Jan");
        }
        else {
            return (Integer.toString(mDay) + "th Jan");
        }


    }
    int myDate(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
//        int mYear = calendar.get(Calendar.YEAR);
//        int mMonth = calendar.get(Calendar.MONTH);
        int mDay = calendar.get(Calendar.DAY_OF_MONTH);

        return (mDay);
    }


}