package pl.com.imralav.magisternative23.fling;

import android.graphics.Point;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class FlingGestureListener extends GestureDetector.SimpleOnGestureListener {
    private static final String TAG = FlingGestureListener.class.getCanonicalName();
    private static final float MIN_FLING_LENGTH_FACTOR = 0.5f;

    private final OnFlingAction onFlingAction;
    private Point size;

    public FlingGestureListener(Point currentScreenSize, OnFlingAction onFlingAction) {
        this.size = currentScreenSize;
        this.onFlingAction = onFlingAction;
    }

    public void updateScreenSize(Point size) {
        this.size = size;
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return handleFling(e1, e2);
    }

    private boolean handleFling(MotionEvent e1, MotionEvent e2) {
        float deltaX = e2.getX() - e1.getX();
        Log.i(TAG, "Fling delta: " + deltaX);
        float minimumFlingLength = MIN_FLING_LENGTH_FACTOR * (float) size.x;
        if (Math.abs(deltaX) < minimumFlingLength) {
            Log.i(TAG, "Delta was below minimumFlingLength of " + minimumFlingLength);
            return false;
        }
        if (deltaX > 0) {
            Log.i(TAG, "Fling into right direction, invoking action");
            onFlingAction.onFling(FlingDirection.RIGHT);
        } else {
            Log.i(TAG, "Fling into left direction, invoking action");
            onFlingAction.onFling(FlingDirection.LEFT);
        }
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return handleFling(e1, e2);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return true;
    }
}
