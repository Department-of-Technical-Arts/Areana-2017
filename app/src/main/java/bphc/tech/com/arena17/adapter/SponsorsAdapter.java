package bphc.tech.com.arena17.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.ScaleAnimation;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.sets.SponsorSet;

/**
 * Created by tejeshwar on 6/1/17.
 */

public class SponsorsAdapter extends RecyclerView.Adapter<SponsorsAdapter.SponsorViewHolder> {

    AdapterView.OnItemClickListener itemClickListener;
    Context context;
    ArrayList<SponsorSet> sponsorSets;
    public SponsorsAdapter(Context context, ArrayList<SponsorSet> sponsorSets, AdapterView.OnItemClickListener itemClickListener) {
        this.context = context;
        this.sponsorSets = sponsorSets;
        this.itemClickListener = itemClickListener;
    }

    @Override
    public SponsorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SponsorViewHolder(LayoutInflater.from(context).inflate(R.layout.sponsor_card,null));
    }

    int offset =0;
    @Override
    public void onBindViewHolder(SponsorViewHolder holder, int position) {
        holder.textView.setText(sponsorSets.get(position).getTitle());
        Picasso.with(context).load(sponsorSets.get(position).getUrl()).into(holder.image);

        Animation animation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(300);
        animation.setInterpolator(new DecelerateInterpolator(1.5f));
        animation.setFillAfter(true);
        animation.setStartOffset(offset);
        holder.itemView.startAnimation(animation);
        offset = offset + 40;
    }

    @Override
    public int getItemCount() {
        return sponsorSets.size();
    }


    public class SponsorViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        ImageView image;
        TextView textView;
        public SponsorViewHolder(View itemView) {
            super(itemView);
            image = (ImageView) itemView.findViewById(R.id.sponsor_card_image);
            textView = (TextView) itemView.findViewById(R.id.sponsor_card_text);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onItemClick(null,view,getAdapterPosition(),view.getId());
        }
    }
}
