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

public class OrganisersFragment extends Fragment implements AdapterView.OnItemClickListener{
    bphc.tech.com.arena17.adapter.OrganisersAdapter OrganisersAdapter;
    RecyclerView recyclerView;
    ArrayList<Contacts> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_organisers, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<>();
        recyclerView = (RecyclerView) view.findViewById(R.id.contacts_recycler);

        OrganisersAdapter = new OrganisersAdapter(getActivity(),this,data);
        recyclerView.setAdapter(OrganisersAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        OrganisersAdapter.setArrayList(data);
        feedData();
    }

    private void feedData() {
        Contacts temp1 = new Contacts("sampath balineni", "general secretary", "+91999999999", R.drawable.claire,"");
        Contacts temp2 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp3 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp4 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp5 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp6 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp7 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp8 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp9 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        Contacts temp10 = new Contacts("Keshav Kedarnath", "President", "+917997030001", R.drawable.claire,"");
        data.add(temp1);
        data.add(temp2);
        data.add(temp3);
        data.add(temp4);
        data.add(temp5);
        data.add(temp6);
        data.add(temp7);
        data.add(temp8);
        data.add(temp9);
        data.add(temp10);
        OrganisersAdapter.notifyItemRangeInserted(0, data.size() - 1);
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, final View view, final int i, long l) {
        /*PopupMenu popup = new PopupMenu(getActivity(),view);
        popup.inflate(R.menu.options_menu);
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.call:
                        Uri number = Uri.parse("tel:" + data.get(i).getMobile());
                        startActivity(new Intent(Intent.ACTION_DIAL, number));
                        break;
                    case R.id.mail:
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/html").putExtra(Intent.EXTRA_EMAIL, new String[]{data.get(i).getMail()});
                        emailIntent.setType("message/rfc822");
                        startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
                        break;
                }
                return false;
            }
        });
        popup.show();*/
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.contact_card_toolbar);
        toolbar.inflateMenu(R.menu.options_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.call:
                        Uri number = Uri.parse("tel:" + data.get(i).getMobile());
                        view.getContext().startActivity(new Intent(Intent.ACTION_DIAL, number));
                        break;
                    case R.id.mail:
                        Intent emailIntent = new Intent(Intent.ACTION_SEND);
                        emailIntent.setType("text/html").putExtra(Intent.EXTRA_EMAIL, new String[]{data.get(i).getMail()});
                        emailIntent.setType("message/rfc822");
                        view.getContext().startActivity(Intent.createChooser(emailIntent, "Choose an Email client :"));
                        break;
                }
                return false;
            }
        });
    }
}
