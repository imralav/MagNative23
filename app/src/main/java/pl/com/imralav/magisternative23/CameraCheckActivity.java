package pl.com.imralav.magisternative23;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.View;

import pl.com.imralav.magisternative23.fling.FlingDirection;
import pl.com.imralav.magisternative23.fling.FlingGestureListener;

public class CameraCheckActivity extends MagNativeActivity {

    private GestureDetector gestureDetector;
    private FlingGestureListener flingGestureListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_check);
    }

    @Override
    protected void onFlingAction(FlingDirection flingDirection) {
        if(flingDirection == FlingDirection.RIGHT) {
            goToDataCheck(null);
        }
    }

    public void goToDataCheck(View view) {
        Intent intent = new Intent(this, DataAccessCheckActivity.class);
        startActivity(intent);
    }

    public void swapCameras(View view) {
    }
}
