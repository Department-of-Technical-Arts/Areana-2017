package bphc.tech.com.arena17.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.media.TransportPerformer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.app.Constants;
import bphc.tech.com.arena17.database.DBHelper;
import bphc.tech.com.arena17.sets.ContactItem;
import bphc.tech.com.arena17.sets.EventsSet;
import bphc.tech.com.arena17.sets.PrizeItem;

/**
 * Created by sarath on 23-12-2016.
 */

public class EventDetailsFragment extends Fragment implements View.OnClickListener{
    public EventDetailsFragment() {
    }

    TextView contact_name1,contact_name2,contact_number1,contact_number2,prize1,prize2;
    LinearLayout contact_container1,contact_container2;
    EventsSet event;

    DBHelper helper;
    int eventID;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prize1 = (TextView) view.findViewById(R.id.prize_money_1);
        prize2 = (TextView) view.findViewById(R.id.prize_money_2);
        contact_name1 = (TextView) view.findViewById(R.id.contact_name_1);
        contact_name2 = (TextView) view.findViewById(R.id.contact_name_2);
        contact_number1 = (TextView)view.findViewById(R.id.contact_no_1);
        contact_number2 = (TextView)view.findViewById(R.id.contact_no_2);
        contact_container1 = (LinearLayout) view.findViewById(R.id.contact_container_1);
        contact_container2 = (LinearLayout) view.findViewById(R.id.contact_container_2);


        //To launch dialer activities.
        // Contact number
        contact_number1.setClickable(true);
        contact_number1.setOnClickListener(this);
        contact_number2.setClickable(true);
        contact_number2.setOnClickListener(this);
        // Image View
//        view.findViewById(R.id.ic_dailer_1).setOnClickListener(this); // activity not launching for image view
        // PDF Download
        view.findViewById(R.id.pdf_textView).setOnClickListener(this);


        helper = new DBHelper(getActivity());
        eventID = getActivity().getIntent().getIntExtra(Constants.Arg_Event_ID,-1);

        if (eventID != -1){
            try{
                List<EventsSet> eventList = helper.getEventData(eventID);
                event = eventList.get(0);
                // Fill the necessary details
                fillDetailsData(event);
            }
            catch (Exception e){
                Toast.makeText(getActivity(),"Could not fetch event",Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(getActivity(),"No event Found",Toast.LENGTH_SHORT).show();
        }
    }

    // To launch the dialer Activity
    @Override
    public void onClick(View view) {
        //when the number textView is clicked directly!
        if((view.getId()==R.id.contact_no_1|view.getId()==R.id.contact_no_2)){
            try {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", (String)(((TextView)view).getText()), null)));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(), "Cannot place call!", Toast.LENGTH_SHORT).show(); //cannot fetch the required activity.
            }
        }
        // when the individual ic_dialer images are clicked. (Bit messy But works!! )
        else if((view.getId()==R.id.ic_dailer_1)){
            try {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", (String)(contact_number1.getText()), null)));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(), "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                    Toast.makeText(getActivity(), "Sorry.. There was some error", Toast.LENGTH_SHORT).show();
            }
        }
        else if(view.getId()==R.id.pdf_textView){
          if(event.getPdfUrl().isEmpty()){
              Toast.makeText(getActivity(), "No PDF found!", Toast.LENGTH_SHORT).show();
            }
            else{
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(event.getPdfUrl()));
                startActivity(browserIntent);
            }
        }
    }

    private void fillDetailsData(EventsSet event){

        List<ContactItem> contactItems = helper.getEventContacts(eventID);
        // Contacts
        for (int i = 0; i < contactItems.size(); i++) {
            try {
                ContactItem contactItem = contactItems.get(i);
                if (i == 0) {
                    if (contactItem.getGender() == 0) {
                        contact_name1.setText((contactItem.getName() + ", Captain (Boy's)"));
                        contact_number1.setText(contactItem.getPhone());
                        contact_container1.setVisibility(View.VISIBLE);
                    } else if (contactItem.getGender() == 1) {
                        contact_name1.setText((contactItem.getName() + ", Captain (Girl's)"));
                        contact_number1.setText(contactItem.getPhone());
                        contact_container1.setVisibility(View.VISIBLE);
                    } else if (contactItem.getGender() == 2) {
                        contact_name1.setText((contactItem.getName() + ", Captain"));
                        contact_number1.setText(contactItem.getPhone());
                        contact_container1.setVisibility(View.VISIBLE);
                    }
                }
                if (i == 1) {
                    if (contactItem.getGender() == 0) {
                        contact_name2.setText((contactItem.getName() + ", Captain (Boy's)"));
                        contact_number2.setText(contactItem.getPhone());
                        contact_container2.setVisibility(View.VISIBLE);
                    } else if (contactItem.getGender() == 1) {
                        contact_name2.setText((contactItem.getName() + ", Captain (Girl's)"));
                        contact_number2.setText(contactItem.getPhone());
                        contact_container2.setVisibility(View.VISIBLE);
                    } else if (contactItem.getGender() == 2) {    // this if is not needed but meh..!!
                        contact_name2.setText((contactItem.getName() + ", Captain"));
                        contact_number2.setText(contactItem.getPhone());
                        contact_container2.setVisibility(View.VISIBLE);
                    }
                }
            }

            catch (Exception e){
                // Toast.makeText(getActivity(), "inside contacts' catch", Toast.LENGTH_LONG).show();
                break;// do nothing
            }
        }

        /*if(!event.getCaptainName().isEmpty()){
            contact_number1.setText(("+91 "+ event.getContact()));
        }
        if(!event.getCaptainName().isEmpty()){
            contact_name1.setText((event.getCaptainName()+", Captain"));
        }*/

        //For Prize money
        List<PrizeItem> prizeItems = helper.getEventPrizes(eventID);
        for(int i=0; i<prizeItems.size(); i++){
            try{
                PrizeItem prizeItem = prizeItems.get(i);
                if(i==0){
                    if(prizeItem.getGender()==0){
                        prize1.setText(("Total Prize Money (Boys'): " + prizeItem.getPrize()));
                        prize1.setVisibility(View.VISIBLE);
                    }
                    else if(prizeItem.getGender()==1){
                        prize1.setText(("Total Prize Money (Girls'): " + prizeItem.getPrize()));
                        prize1.setVisibility(View.VISIBLE);
                    }
                    else if(prizeItem.getGender()==2){
                        prize1.setText(("Total Prize Money: " + prizeItem.getPrize()));
                        prize1.setVisibility(View.VISIBLE);
                    }
                }

                if(i==1) {
                    if(prizeItem.getGender()==0){
                        prize2.setText(("Total Prize Money (Boys'): " + prizeItem.getPrize()));
                        prize2.setVisibility(View.VISIBLE);
                    }
                    else if(prizeItem.getGender()==1){
                        prize2.setText(("Total Prize Money (Girls'): " + prizeItem.getPrize()));
                        prize2.setVisibility(View.VISIBLE);
                    }
                    else if(prizeItem.getGender()==2){  //not needed.. but meh..!!
                        prize2.setText(("Total Prize Money: " + prizeItem.getPrize()));
                        prize2.setVisibility(View.VISIBLE);
                    }
                }
            }  catch (Exception e){
               // Toast.makeText(getActivity(), "inside catch", Toast.LENGTH_LONG).show();
                break;
            }
        }
    }
}
