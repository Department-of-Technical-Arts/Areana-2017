package org.arenatest.bits.arena_test.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import org.arenatest.bits.arena_test.R;
import org.arenatest.bits.arena_test.database.DBHelper;
import org.arenatest.bits.arena_test.sets.MedalSet;

/**
 * Created by tejeshwar on 3/1/17.
 */

public class MedalTallyAdapter extends RecyclerView.Adapter<MedalTallyAdapter.TallyViewHolder> {

    ArrayList<MedalSet> medalSets;
    Context context;

    public MedalTallyAdapter(Context context) {
        this.context = context;
        medalSets = new ArrayList<>();
        DBHelper helper = new DBHelper(context);
        medalSets = helper.getMedalTally();

    }

    @Override
    public TallyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TallyViewHolder(LayoutInflater.from(context).inflate(R.layout.medal_card,parent,false));
    }

    @Override
    public void onBindViewHolder(TallyViewHolder holder, int position) {
        holder.team.setText(medalSets.get(position).getCollege());
        holder.gold.setText(medalSets.get(position).getGold()+"");
        holder.silver.setText(medalSets.get(position).getSilver()+"");
        holder.bronze.setText(medalSets.get(position).getBronze()+"");
    }

    @Override
    public int getItemCount() {
        return medalSets.size();
    }

    public class TallyViewHolder extends RecyclerView.ViewHolder{
        TextView team,gold,silver,bronze;
        public TallyViewHolder(View itemView) {
            super(itemView);
            team = (TextView) itemView.findViewById(R.id.medal_card_team);
            gold = (TextView) itemView.findViewById(R.id.medal_card_gold);
            silver = (TextView) itemView.findViewById(R.id.medal_card_silver);
            bronze = (TextView) itemView.findViewById(R.id.medal_card_bronze);
        }
    }
}
