package bphc.tech.com.arena17.services;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Map;

import bphc.tech.com.arena17.EventDetailsActivity;
import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.app.Constants;
import bphc.tech.com.arena17.database.DBHelper;
import bphc.tech.com.arena17.sets.FeedItem;

/**
 * Created by tejeshwar on 18/12/16.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final String TAG = "firebase notification";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Map<String, String> a=remoteMessage.getData();

        Log.e("Message",a.get("message"));

        String response = a.get("message");
        Gson gson = new Gson();
        FeedItem feedItem = gson.fromJson(response,FeedItem.class);

        Intent intent = new Intent(this,EventDetailsActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra(Constants.Arg_Event_ID,feedItem.getEvent_id());
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle(feedItem.getTitle());
        notificationBuilder.setContentText(feedItem.getMessage());
        notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSmallIcon(R.drawable.ic_noti);
        notificationBuilder.setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());

        DBHelper helper = new DBHelper(this);
        long success = -1;
        if (feedItem.getType()==1) {
             success = helper.addFeedRow(remoteMessage.getMessageId(), feedItem.getTitle(), feedItem.getMessage(),remoteMessage.getSentTime());
        }
        ArrayList<FeedItem> feedItems = helper.getAllFeedData();
        for (int i=0;i<feedItems.size();i++){
            Log.e(TAG,feedItems.get(i).getMessage());
            Log.e(TAG,feedItems.get(i).getTitle());
        }
        Log.e(TAG,success+"");
    }

    @Override
    public String getSystemServiceName(Class<?> serviceClass) {
        return super.getSystemServiceName(serviceClass);
    }
}
