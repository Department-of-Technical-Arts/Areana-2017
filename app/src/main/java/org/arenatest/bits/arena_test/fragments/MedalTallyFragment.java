package org.arenatest.bits.arena_test.fragments;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.arenatest.bits.arena_test.R;
import org.arenatest.bits.arena_test.adapter.MedalTallyAdapter;

/**
 * A simple {@link Fragment} subclass.
 */
public class MedalTallyFragment extends Fragment {

    RecyclerView medalRecycler;

    public MedalTallyFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_medal_tally, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        medalRecycler = (RecyclerView) view.findViewById(R.id.medal_recycler);
        medalRecycler.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        medalRecycler.setAdapter(new MedalTallyAdapter(getActivity()));
    }
}
