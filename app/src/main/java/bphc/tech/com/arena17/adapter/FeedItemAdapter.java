package bphc.tech.com.arena17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.database.DBHelper;
import bphc.tech.com.arena17.sets.FeedItem;

/**
 * Created by tejeshwar on 3/1/17.
 */

public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemAdapter.FeedsViewHolder>{

    ArrayList<FeedItem> feedItems;
    Context context;
    DBHelper helper;

    public FeedItemAdapter(Context context) {
        this.context = context;
        feedItems = new ArrayList<>();
        helper = new DBHelper(context);
        feedItems = helper.getAllFeedData();
    }

    @Override
    public FeedsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FeedsViewHolder(LayoutInflater.from(context).inflate(R.layout.feed_card,parent,false));
    }

    @Override
    public void onBindViewHolder(FeedsViewHolder holder, int position) {
        holder.message.setText(feedItems.get(position).getMessage());
        holder.time.setText(getDate(feedItems.get(position).getTime(), "dd/MM/yyyy hh:mm:ss.SSS"));
        holder.title.setText(feedItems.get(position).getTitle());
    }

    public static String getDate(long milliSeconds, String dateFormat)
    {
        // Create a DateFormatter object for displaying date in specified format.
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormat, Locale.UK);

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class FeedsViewHolder extends RecyclerView.ViewHolder{
        TextView title,time,message;
        public FeedsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.feed_card_title);
            time = (TextView) itemView.findViewById(R.id.feed_card_time);
            message = (TextView) itemView.findViewById(R.id.feed_card_message);
        }
    }
}
