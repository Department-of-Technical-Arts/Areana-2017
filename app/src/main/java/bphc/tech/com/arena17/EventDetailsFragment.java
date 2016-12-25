package bphc.tech.com.arena17;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.List;

import bphc.tech.com.arena17.app.Constants;
import bphc.tech.com.arena17.database.DBHelper;
import bphc.tech.com.arena17.sets.EventsSet;

/**
 * Created by sarath on 23-12-2016.
 */

public class EventDetailsFragment extends Fragment implements View.OnClickListener{
    public EventDetailsFragment() {
    }

    TextView contact_name, contact_number, prize;
    EventsSet event;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_event_details, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        prize = (TextView) view.findViewById(R.id.prize_money);
        contact_name = (TextView) view.findViewById(R.id.contact_name_1);
        contact_number = (TextView)view.findViewById(R.id.contact_no_1);

        //To launch dialer activities.
        // Contact number
        contact_number.setClickable(true);
        contact_number.setOnClickListener(this);
        // Image View
        view.findViewById(R.id.ic_dailer_1).setOnClickListener(this);
        // PDF Download
        view.findViewById(R.id.pdf_textView).setOnClickListener(this);


        DBHelper helper = new DBHelper(getActivity());
        int eventID = getActivity().getIntent().getIntExtra(Constants.Arg_Event_ID,-1);

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
        if((view.getId()==R.id.contact_no_1)){
            try {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", (String)(((TextView)view).getText()), null)));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(), "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
            }
        }
        // when the individual ic_dialer images are clicked. (Bit messy But works!! )
        else if((view.getId()==R.id.ic_dailer_1)){
            try {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", (String)(((TextView)getView().findViewById(R.id.contact_no_1)).getText()), null)));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(), "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                    Toast.makeText(getActivity(), "Sorry.. There was some error", Toast.LENGTH_SHORT).show();
            }
        }
        //// TODO: 25-12-2016 get the pdf link from the events!
        else if(view.getId()==R.id.pdf_textView){
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(event.getPDFLink));
//            startActivity(browserIntent);
        }
    }

    private void fillDetailsData(EventsSet event){
        if(!event.getCaptainName().isEmpty()){
            contact_number.setText(event.getContact());
        }
        if(!event.getCaptainName().isEmpty()){
            contact_name.setText((event.getCaptainName()+", Captain"));
        }
        //need another if statement!!
        prize.setText("Get JSON response for this!");
    }
}
