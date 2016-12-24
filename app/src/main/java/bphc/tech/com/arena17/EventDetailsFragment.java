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

/**
 * Created by sarath on 23-12-2016.
 */

public class EventDetailsFragment extends Fragment implements View.OnClickListener{
    public EventDetailsFragment() {
    }

    TextView callme, pdf;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View v = inflater.inflate(R.layout.fragment_event_details, container, false);



        return v;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //To launch dialer activities.
        view.findViewById(R.id.contact_no_1).setOnClickListener(this);
        view.findViewById(R.id.contact_no_2).setOnClickListener(this);
        view.findViewById(R.id.ic_dailer_1).setOnClickListener(this);
        view.findViewById(R.id.ic_dailer_2).setOnClickListener(this);

        view.findViewById(R.id.pdf_textView).setOnClickListener(this);

    }

    // To launch the dialer Activity
    @Override
    public void onClick(View view) {
        //when the number textViews are clicked directly!
        if((view.getId()==R.id.contact_no_1)|(view.getId()==R.id.contact_no_2)){
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
        else if((view.getId()==R.id.ic_dailer_2)){
            try {
                startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", (String)(((TextView)getView().findViewById(R.id.contact_no_2)).getText()), null)));
            } catch (android.content.ActivityNotFoundException ex) {
                Toast.makeText(getActivity(), "Could not find an activity to place the call.", Toast.LENGTH_SHORT).show();
            } catch (Exception e){
                Toast.makeText(getActivity(), "Sorry.. There was some error", Toast.LENGTH_SHORT).show();
            }
        }

        else if(view.getId()==R.id.pdf_textView){

        }

    }
}
