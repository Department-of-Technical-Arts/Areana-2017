package org.arenatest.bits.arena_test.fragments;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.arenatest.bits.arena_test.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AppCreditsFragment extends Fragment {


    public AppCreditsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_app_credits, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.tejeshwar).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.linkedin.com/in/tejeshwar-reddy-689b94b8")));
            }
        });
        view.findViewById(R.id.sarath).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://in.linkedin.com/in/tdsk-sarath-1bb146136")));
            }
        });
        view.findViewById(R.id.sravani).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://facebook.com/jagini.sravs")));
            }
        });
        view.findViewById(R.id.vaishnavi).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.linkedin.com/in/vaishnavi-k-58344575")));
            }
        });
        view.findViewById(R.id.adarsh).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://in.linkedin.com/in/adarsh-salagame-05036b105")));
            }
        });
        view.findViewById(R.id.gagan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.behance.net/gagan1805960ea")));
            }
        });
        view.findViewById(R.id.ksitij).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://www.linkedin.com/in/kshitij-grovor-0b4083122")));
            }
        });

    }
}
