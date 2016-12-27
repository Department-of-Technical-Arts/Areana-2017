package bphc.tech.com.arena17.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import bphc.tech.com.arena17.EventDetailsActivity;
import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.sets.EventItem;

/**
 * Created by tejeshwar on 26/12/16.
 */

public class SelectEventAdapter extends RecyclerView.Adapter<SelectEventAdapter.EventItemHolder> {

    private ArrayList<EventItem> items;
    private Context context;

    public SelectEventAdapter(Context context, ArrayList<EventItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public EventItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new EventItemHolder(LayoutInflater.from(context).inflate(R.layout.event_select_item,null));
    }

    @Override
    public void onBindViewHolder(EventItemHolder holder, int position) {
        holder.image.setImageResource(items.get(position).getImage());
        holder.event.setText(items.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class EventItemHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView event;
        ImageView image;
        public EventItemHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            event = (TextView) itemView.findViewById(R.id.event_name);
            image = (ImageView) itemView.findViewById(R.id.event_image);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(view.getContext(),getAdapterPosition()+"", Toast.LENGTH_SHORT).show();
            view.getContext().startActivity(new Intent(view.getContext().getApplicationContext(), EventDetailsActivity.class).putExtra("eventid",getAdapterPosition()));
        }
    }
}
