package org.bts.android.dummynotification;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

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
                Log.i(MainActivity.TAG, "Add Notification Button clicked");

                break;

            case R.id.button_to_custom_notify:
                Log.i(MainActivity.TAG, "Custom Add Notification Button clicked");

                break;
            default:
                Log.i(MainActivity.TAG,"Clicked Item not registered");
        }

    }
}
