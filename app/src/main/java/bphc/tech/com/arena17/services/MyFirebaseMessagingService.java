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

import java.util.ArrayList;

import bphc.tech.com.arena17.HomeActivity;
import bphc.tech.com.arena17.R;
import bphc.tech.com.arena17.database.DBHelper;
import bphc.tech.com.arena17.sets.FeedItem;

/**
 * Created by tejeshwar on 18/12/16.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private final String TAG = "firebase notification";
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Intent intent = new Intent(this,HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_ONE_SHOT);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this);
        notificationBuilder.setContentTitle("Arena 2017");
        notificationBuilder.setContentText(remoteMessage.getNotification().getBody());
        notificationBuilder.setAutoCancel(false);
        notificationBuilder.setSmallIcon(R.mipmap.ic_launcher);
        notificationBuilder.setContentIntent(pendingIntent);
        notificationBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0,notificationBuilder.build());

        DBHelper helper = new DBHelper(this);
        long success = helper.addFeedRow(remoteMessage.getMessageId(),remoteMessage.getNotification().getTitle(),remoteMessage.getNotification().getBody(),remoteMessage.getSentTime());
        ArrayList<FeedItem> feedItems = helper.getAllFeedData();
        for (int i=0;i<feedItems.size();i++){
            Log.e(TAG,feedItems.get(i).getFeedid());
            Log.e(TAG,feedItems.get(i).getMessage());
            Log.e(TAG,feedItems.get(i).getTitle());
            Log.e(TAG,feedItems.get(i).getTime()+"");
        }
        Log.e(TAG,success+"");
    }

    @Override
    public String getSystemServiceName(Class<?> serviceClass) {
        return super.getSystemServiceName(serviceClass);
    }
}
