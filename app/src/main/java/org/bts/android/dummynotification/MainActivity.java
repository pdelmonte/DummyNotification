package org.bts.android.dummynotification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Button btnStandardNotification = (Button) findViewById(R.id.button_to_notify);
        btnStandardNotification.setOnClickListener(this);
        final Button btnCustomNotification = (Button) findViewById(R.id.button_to_custom_notify);
        btnCustomNotification.setOnClickListener(this);
    }

    @Override
    public void onClick(View whichView) {

        switch(whichView.getId()) {
            case R.id.button_to_notify:
                triggerRegularNotification();
                Log.i(MainActivity.TAG, "Add Notification Button clicked");
                break;

            case R.id.button_to_custom_notify:
                triggerCustomNotification();
                Log.i(MainActivity.TAG, "Custom Add Notification Button clicked");
                break;

            default:
                Log.i(MainActivity.TAG,"Clicked Item not registered");
        }

    }

    private void triggerRegularNotification() {
       NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
               .setSmallIcon(R.mipmap.ic_launcher)
               .setContentTitle(getString(R.string.custom_notify_text_1))
               .setContentText(getString(R.string.notification_long_text))
               .setAutoCancel(true);

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, 1 ,notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        mBuilder.setContentIntent(mPendingIntent);

        NotificationManager mNotifManager = (NotificationManager) this.getSystemService(Service.NOTIFICATION_SERVICE);
            mNotifManager.notify(2, mBuilder.build());
    }

    private void triggerCustomNotification() {

        Intent customNotificationIntent = new Intent(this, MainActivity.class);
        PendingIntent mPendingIntent = PendingIntent.getActivity(this, 2 ,customNotificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder mCustomBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.messenger_icon);

        NotificationManager mNotifManager = (NotificationManager) this.getSystemService(Service.NOTIFICATION_SERVICE);
        Log.i(MainActivity.TAG, "----------------");

        RemoteViews mRemoteViews = new RemoteViews(this.getPackageName(),R.layout.layout_custom_notification);
        mRemoteViews.setImageViewResource(R.id.imageViewNotification, R.drawable.messenger_icon);
        mRemoteViews.setTextViewText(R.id.txtViewTitle, getString(R.string.custom_notify_text_1));
        mRemoteViews.setTextViewText(R.id.txtViewBody, getString(R.string.custom_notify_text_2));
        mRemoteViews.setTextViewText(R.id.txtViewFootnote, getString(R.string.custom_notify_text_3));

        mCustomBuilder.setContentIntent(mPendingIntent);
        mCustomBuilder.setContent(mRemoteViews);
        mNotifManager.notify(1, mCustomBuilder.build());

    }
}
