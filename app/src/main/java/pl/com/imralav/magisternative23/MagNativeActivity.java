package pl.com.imralav.magisternative23;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.Display;
import android.view.GestureDetector;
import android.widget.Toast;

import pl.com.imralav.magisternative23.fling.FlingDirection;
import pl.com.imralav.magisternative23.fling.FlingGestureListener;
import pl.com.imralav.magisternative23.fling.OnFlingAction;

public abstract class MagNativeActivity extends Activity {

    private GestureDetector gestureDetector;
    private FlingGestureListener flingGestureListener;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        flingGestureListener = new FlingGestureListener(getCurrentScreenSize(), prepareOnFlingActionConsumer());
        gestureDetector = new GestureDetector(getApplicationContext(), flingGestureListener);
    }

    @NonNull
    private OnFlingAction prepareOnFlingActionConsumer() {
        return new OnFlingAction() {
            @Override
            public void onFling(FlingDirection flingDirection) {
                onFlingAction(flingDirection);
            }
        };
    }

    @NonNull
    protected Point getCurrentScreenSize() {
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size;
    }

    protected abstract void onFlingAction(FlingDirection flingDirection);

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        Point size = getCurrentScreenSize();
        flingGestureListener.updateScreenSize(size);
        Toast.makeText(this, "Current dimensions: " + size.x + ":" + size.y, Toast.LENGTH_SHORT).show();
    }
}
