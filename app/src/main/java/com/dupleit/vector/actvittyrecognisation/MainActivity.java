package com.dupleit.vector.actvittyrecognisation;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.dupleit.vector.actvittyrecognisation.Constant.AppConstant;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.ConnectionCallbacks,GoogleApiClient.OnConnectionFailedListener{

    TextView OutputBox;
    Button request,cancel;
    GoogleApiClient googleApiClient;
    ActivityDetectionReciever activityDetectionReciever;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        OutputBox = findViewById(R.id.detectedActivities);
        request = findViewById(R.id.request_activity_updates_button);
        cancel = findViewById(R.id.remove_activity_updates_button);
        activityDetectionReciever = new ActivityDetectionReciever();
        googleApiClient = new GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .build();
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (googleApiClient.isConnected())
            googleApiClient.disconnect();
    }

    @Override
    protected void onPause() {
        super.onPause();
        LocalBroadcastManager.getInstance(this).unregisterReceiver(activityDetectionReciever);
    }

    @Override
    protected void onStart() {
        super.onStart();
        googleApiClient.connect();
    }

    @Override
    protected void onResume() {
        super.onResume();
        LocalBroadcastManager.getInstance(this).registerReceiver(activityDetectionReciever, new IntentFilter(AppConstant.BroadCastIntent));
    }

    public class ActivityDetectionReciever extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {

        }
    }
}
