package org.arenatest.bits.arena_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.arenatest.bits.arena_test.R;
import org.arenatest.bits.arena_test.database.DBHelper;
import org.arenatest.bits.arena_test.sets.FeedItem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by tejeshwar on 3/1/17.
 */

public class FeedItemAdapter extends RecyclerView.Adapter<FeedItemAdapter.FeedsViewHolder>{

    ArrayList<FeedItem> feedItems;
    Context context;
    DBHelper helper;

    public final String TAG = "FEED ITEM ADAPTER";
    public FeedItemAdapter(Context context) {
        this.context = context;
        feedItems = new ArrayList<>();
        helper = new DBHelper(context);
        feedItems = helper.getAllFeedData();
        for (int i=0;i<feedItems.size();i++){
            Log.e(TAG,feedItems.get(i).getMessage());
            Log.e(TAG,feedItems.get(i).getTitle());
        }
    }

    @Override
    public FeedsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FeedsViewHolder(LayoutInflater.from(context).inflate(R.layout.feed_card,parent,false));
    }

    @Override
    public void onBindViewHolder(FeedsViewHolder holder, int position) {
        Calendar c = Calendar.getInstance();
        long tim = feedItems.get(position).getUpdated_at();
        c.setTimeInMillis(tim);
        holder.message.setText(feedItems.get(position).getMessage());
        holder.time.setText(getTime(feedItems.get(position).getUpdated_at()));
        holder.title.setText(feedItems.get(position).getTitle());
    }

    private String getTime(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);
        SimpleDateFormat dateFormat = new SimpleDateFormat("d MMM hh:mm a");
        return dateFormat.format(calendar.getTime());

    }

    @Override
    public int getItemCount() {
        return feedItems.size();
    }

    public class FeedsViewHolder extends RecyclerView.ViewHolder{
        TextView title,date,time,month,am,message;
        public FeedsViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.feed_card_title);
            time = (TextView) itemView.findViewById(R.id.feed_card_time);
            message = (TextView) itemView.findViewById(R.id.feed_card_message);
        }
    }
}
