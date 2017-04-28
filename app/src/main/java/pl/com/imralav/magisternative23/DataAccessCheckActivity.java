package pl.com.imralav.magisternative23;

import android.content.Intent;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

import pl.com.imralav.magisternative23.fling.FlingDirection;

public class DataAccessCheckActivity extends MagNativeActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_access_check);
    }

    @Override
    protected void onFlingAction(FlingDirection flingDirection) {
        if(flingDirection == FlingDirection.LEFT) {
            goToCameraCheck(null);
        }
    }

    public void goToCameraCheck(View view) {
        Intent intent = new Intent(this, CameraCheckActivity.class);
        startActivity(intent);
    }

    public void checkPhotos(View view) {
    }

    public void checkContacts(View view) {
    }

    public void checkMessages(View view) {
    }
}
