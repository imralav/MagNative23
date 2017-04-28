package pl.com.imralav.magisternative23;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import pl.com.imralav.magisternative23.fling.FlingDirection;
import pl.com.imralav.magisternative23.utils.DrawablesHelper;

public class DataAccessCheckActivity extends MagNativeActivity {
    private static final String TAG = DataAccessCheckActivity.class.getCanonicalName();
    private static final int PHOTO_GALLERY_REQUEST_CODE = 0;
    private static final int CONTACTS_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_access_check);
    }

    @Override
    protected void onFlingAction(FlingDirection flingDirection) {
        if (flingDirection == FlingDirection.LEFT) {
            goToCameraCheck(null);
        }
    }

    public void goToCameraCheck(View view) {
        Intent intent = new Intent(this, CameraCheckActivity.class);
        startActivity(intent);
    }

    public void checkPhotos(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PHOTO_GALLERY_REQUEST_CODE);
    }

    public void checkContacts(View view) {
    }

    public void checkMessages(View view) {
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case PHOTO_GALLERY_REQUEST_CODE:
                handlePhotoGalleryResult(resultCode, data);
                break;
            case CONTACTS_REQUEST_CODE:
                break;
            default:
                break;
        }
    }

    private void handlePhotoGalleryResult(int resultCode, Intent data) {
        if (resultCode != RESULT_OK) {
            setPhotosStatusToInaccessible();
        } else {
            setPhotosStatusToAccessible();
            Uri photoUri = data.getData();
            Log.i(TAG, "Starting photo preview activity for URI " + photoUri.toString());
            openPopupForPhotoUri(photoUri);
        }
    }

    private void openPopupForPhotoUri(Uri photoUri) {
        View photoPreviewPopupLayout = getLayoutInflater().inflate(R.layout.photo_preview_dialog, null);
        ImageView photoContainer = (ImageView) photoPreviewPopupLayout.findViewById(R.id.photoPreviewContainer);
        photoContainer.setImageURI(photoUri);
        new AlertDialog.Builder(this)
                .setTitle(photoUri.toString())
                .setView(photoPreviewPopupLayout)
                .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .show();
    }

    private void setAccessibleStatusOnResource(int resourceId) {
        setAccessibleStatusOnResource(resourceId, getResources().getString(R.string.available));
    }

    private void setAccessibleStatusOnResource(int resourceId, String text) {
        TextView statusText = (TextView) findViewById(resourceId);
        statusText.setText(text);
        int iconId = DrawablesHelper.getDataAccessibleIconId(getResources(), getPackageName());
        statusText.setCompoundDrawablesRelativeWithIntrinsicBounds(iconId, 0, 0, 0);
    }

    private void setPhotosStatusToAccessible() {
        setAccessibleStatusOnResource(R.id.checkPhotosStatusText);
    }

    private void setPhotosStatusToInaccessible() {
        setInaccessibleStatusOnResource(R.id.checkPhotosStatusText);
    }

    private void setInaccessibleStatusOnResource(int resourceId) {
        TextView statusText = (TextView) findViewById(resourceId);
        statusText.setText(R.string.unavailable);
        int iconId = DrawablesHelper.getDataInaccessibleIconId(getResources(), getPackageName());
        statusText.setCompoundDrawablesRelativeWithIntrinsicBounds(iconId, 0, 0, 0);
    }
}
