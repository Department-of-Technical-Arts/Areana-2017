package org.arenatest.bits.arena_test.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import java.util.ArrayList;

import org.arenatest.bits.arena_test.R;
import org.arenatest.bits.arena_test.adapter.SponsorsAdapter;
import org.arenatest.bits.arena_test.database.DBHelper;
import org.arenatest.bits.arena_test.sets.SponsorSet;

/**
 * A simple {@link Fragment} subclass.
 */
public class SponsorsFragment extends Fragment implements AdapterView.OnItemClickListener{

    RecyclerView recyclerView;
    private GridLayoutManager layoutManager;

    public SponsorsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_sponsors, container, false);
    }

    DBHelper helper;
    TextView textView;
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        mDemoSlider = (SliderLayout)view.findViewById(R.id.slider);
        recyclerView = (RecyclerView) view.findViewById(R.id.sponsor_recycler);
        textView = (TextView) view.findViewById(R.id.no_sponsors);
        helper = new DBHelper(getActivity());
        ArrayList<SponsorSet> sponsorSets = helper.getSponsorData();

        if (sponsorSets.size() == 0){
            textView.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }else {
            textView.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            layoutManager = new GridLayoutManager(getActivity(), 2);
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(new SponsorsAdapter(getActivity(), sponsorSets, this));
        }
    }


    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        DBHelper helper = new DBHelper(getActivity());
        ArrayList<SponsorSet> sponsorSets = helper.getSponsorData();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(sponsorSets.get(i).getSponsUrl()));
        startActivity(browserIntent);
    }
}
