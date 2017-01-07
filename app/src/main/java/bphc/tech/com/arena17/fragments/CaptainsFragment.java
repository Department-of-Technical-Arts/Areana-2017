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
        Contacts temp1 = new Contacts("Umamaheshwar Nuthulapati","Cricket","9652496568", R.drawable.uma,"umamaheshwarrocks@gmail.com");
        Contacts temp2 = new Contacts("Karthik Ramanathan",	"Football","9542981889", R.drawable.default_pic,"karthik227.kr@gmail.com");
        Contacts temp3 = new Contacts("Muskan Agrawal", "Volley ball (G)", "8499855297", R.drawable.muskan,"muskanag.2013@gmail.com");
        Contacts temp4 = new Contacts("Achyuth Reddy", "Volley ball (B)", "7660853350", R.drawable.achyuth,"achyuth.ravula@gmail.com");
        Contacts temp5 = new Contacts("Karthik", "Basketball (B)", "9985117233", R.drawable.default_pic,"karthikbballm@gmail.com");
        Contacts temp6 = new Contacts("Richa Mittal", "Basketball (G)", "9573583388", R.drawable.default_pic,"richadagr8@gmail.com");
        Contacts temp7 = new Contacts("Sushmitha Belede", "Badminton (G)", "8184999840", R.drawable.default_pic,"susmitha1157@gmail.com");
        Contacts temp8 = new Contacts("Aditya Singh", "Badminton (B)", "8501869602", R.drawable.adityasingh,"aditya0000001@gmail.com");
        Contacts temp9 = new Contacts("A Rajasekhar", "Kabaddi", "9542968482", R.drawable.rajasekhar,"rajashekharailuri@gmail.com");
        Contacts temp10 = new Contacts("Hanoosha Reddy", "Throwball ", "9705792625 ", R.drawable.hanoosha,"NAGIREDDYHANOOSHA@YAHOO.IN");
        Contacts temp11 = new Contacts("Anuroop G", "Chess", "9603490148", R.drawable.anuroop,"");
        Contacts temp12 = new Contacts("Anshul Mathur", "Tennis", "9553320852", R.drawable.anshul,"ace.anshul@gmail.com");
        Contacts temp13 = new Contacts("Manas Chandola", "Squash", "9912318132", R.drawable.manas,"chandolamanas1995@gmail.com");
        Contacts temp14 = new Contacts("Aastha Singh", "Table Tennis (G)", "9133235010", R.drawable.aastha,"aasthasingh999@gmail.com");
        Contacts temp15 = new Contacts("Sarath T", "Table Tennis (B)", "7729920853", R.drawable.sarath,"sarathisalwaysbusy@gmail.com");
        Contacts temp16 = new Contacts("Anvesh", "Carroms", "8099599556", R.drawable.anvesh,"alugupallyanveshreddy@gmail.com");
        Contacts temp17 = new Contacts("Hem Vats", "Hockey", "9912380179", R.drawable.hemvats,"96hemvats@gmail.com");
        Contacts temp18 = new Contacts("Abhilash K", "Billiards/Pool", "9603411910", R.drawable.default_pic,"abhilashf93@gmail.com");
        Contacts temp19 = new Contacts("Shrinil Thakkar", "Athletics", "9912259873", R.drawable.shrinil,"shrinil.thakkar@gmail.com");
        Contacts temp20 = new Contacts("Harsh Mundra", "Body Building ", "7658965045", R.drawable.mundra,"harshmundra@hotmail.com");
        Contacts temp21 = new Contacts("Prabhakar Chowdary", "Power lifting", "8501011169", R.drawable.prabhakar,"");
        Contacts temp22 = new Contacts("Yashwardhan Singh", "Duathlon", "9030024944", R.drawable.default_pic,"yashwardhan95@gmail.com");
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
        data1.add(temp11);
        data1.add(temp12);
        data1.add(temp13);
        data1.add(temp14);
        data1.add(temp15);
        data1.add(temp16);
        data1.add(temp17);
        data1.add(temp18);
        data1.add(temp19);
        data1.add(temp20);
        data1.add(temp21);
        data1.add(temp22);
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

