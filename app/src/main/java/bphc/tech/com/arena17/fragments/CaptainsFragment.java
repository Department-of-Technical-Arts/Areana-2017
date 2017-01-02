package bphc.tech.com.arena17.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import java.util.ArrayList;

import bphc.tech.com.arena17.adapter.OrganisersAdapter;
import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.sets.Contacts;

/**
 * Created by Sravani Jagini on 29-12-2016.
 */

public class CaptainsFragment extends Fragment implements AdapterView.OnItemClickListener{
    bphc.tech.com.arena17.adapter.OrganisersAdapter OrganisersAdapter;
    RecyclerView recyclerView;
    ArrayList<Contacts> data1;
    Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_captains, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data1 = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.contacts_recycler);
        OrganisersAdapter = new OrganisersAdapter(getActivity(),this,data1);
        recyclerView.setAdapter(OrganisersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        OrganisersAdapter.setArrayList(data1);
        feedData();
    }

    private void feedData() {
        Contacts temp1 = new Contacts("yyooooooo", "general secretary", "+91999999999", R.drawable.claire,"");
        Contacts temp2 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp3 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp4 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp5 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp6 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp7 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp8 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp9 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp10 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        data1.add(temp1);
        data1.add(temp2);
        data1.add(temp3);
        data1.add(temp4);
        data1.add(temp5);
        data1.add(temp6);
        data1.add(temp7);
        data1.add(temp8);
        data1.add(temp9);
        data1.add(temp10);
        OrganisersAdapter.notifyItemRangeInserted(0, data1.size() - 1);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {
        /*PopupMenu popup = new PopupMenu(getActivity(),view);
        //inflating menu from xml resource
        popup.inflate(R.menu.options_menu);
        //adding click listener
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.call:
                        //handle menu1 click
                        Uri number = Uri.parse("tel:" + data1.get(i).getMobile());
                        startActivity(new Intent(Intent.ACTION_DIAL, number));
                        break;
                    case R.id.mail:
                        //handle menu2 click
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/html").putExtra(Intent.EXTRA_EMAIL, new String[]{data1.get(i).getMail()});
                        startActivity(emailIntent);
                        break;
                }
                return false;
            }
        });
        //displaying the popup
        popup.show();*/
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.contact_card_toolbar);
        toolbar.inflateMenu(R.menu.options_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.call:
                        Uri number = Uri.parse("tel:" + data1.get(i).getMobile());
                        view.getContext().startActivity(new Intent(Intent.ACTION_DIAL, number));
                        break;
                    case R.id.mail:
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/html").putExtra(Intent.EXTRA_EMAIL, new String[]{data1.get(i).getMail()});
                        emailIntent.setType("message/rfc822");
                        view.getContext().startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
                        break;
                }
                return false;
            }
        });

    }
}

