package com.dupleit.vector.actvittyrecognisation.IntentService;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;

import com.dupleit.vector.actvittyrecognisation.Constant.AppConstant;
import com.google.android.gms.location.ActivityRecognitionResult;
import com.google.android.gms.location.DetectedActivity;

import java.util.ArrayList;

/**
 * Created by android on 6/11/17.
 */

public class DetectionIntent extends IntentService {
    protected static final String TAG = "detection_is";
    /**
     * Creates an IntentService.  Invoked by your subclass's constructor.
     *
     * @param name Used to name the worker thread, important only for debugging.
     */
    public DetectionIntent(String name) {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        ActivityRecognitionResult result = ActivityRecognitionResult.extractResult(intent);
        Intent localintent = new Intent(AppConstant.BroadCastIntent);

        ArrayList<DetectedActivity> detectedActivities = (ArrayList) result.getProbableActivities();
        localintent.putExtra(AppConstant.ActivityExtra,detectedActivities);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localintent);

    }
}
