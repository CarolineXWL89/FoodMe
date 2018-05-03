package com.example.caroline.foodme.CreateFragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.provider.MediaStore;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by maylisw on 4/23/18.
 */

public class ImageUploadClicker implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 1888;
    private static final int STORAGE_REQUEST = 1999;
    private Context context;
    private Activity activity;
    final private int MY_PERMISSIONS_REQUEST_CAMERA = 123;
    private static final int MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE = 456;
    private boolean canUseCamera = false;
    private boolean canUseStorage = false;
    private CreateFragment createFragment;

    public ImageUploadClicker(Context context, Activity activity, CreateFragment createFragment) {
        this.context = context;
        this.activity = activity;
        this.createFragment = createFragment;
    }

    @Override
    public void onClick(View v) {
        boolean camera = false;
        //todo make two buttons
        if(!camera){
            if(!canUseCamera){
                Snackbar snackbar = Snackbar.make(v, "Camera Permissions are not enabled \n Please enable in order to take photos", Snackbar.LENGTH_LONG);
                snackbar.setAction("ALLOW", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkCameraPermissions();
                    }
                });
                snackbar.show();
            } else {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                createFragment.startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        } else {
            if(!canUseStorage){
                Snackbar snackbar = Snackbar.make(v, "Storage Permissions are not enabled \n Please enable in order to upload", Snackbar.LENGTH_LONG);
                snackbar.setAction("ALLOW", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkStoragePermissions();
                    }
                });
                snackbar.show();
            } else {
                Intent intent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                createFragment.startActivityForResult(intent, STORAGE_REQUEST);
            }
        }
    }

    public void setCanUseStorage(boolean canUseStorage) {
        this.canUseStorage = canUseStorage;
    }

    private void checkCameraPermissions() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            createFragment.requestPermissions(new String[]{android.Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        } else{
            canUseCamera = true;
        }
    }

    private void checkStoragePermissions() {
        //checks permissions for storage
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            createFragment.requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
        } else{
            canUseStorage = true;
        }
    }

    public void setCanUseCamera(boolean canUseCamera) {
        this.canUseCamera = canUseCamera;
    }

    public static int getCameraRequest() {
        return CAMERA_REQUEST;
    }

    public static int getStorageRequest() {
        return STORAGE_REQUEST;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    public int getMY_PERMISSIONS_REQUEST_CAMERA() {
        return MY_PERMISSIONS_REQUEST_CAMERA;
    }

    public static int getMyPermissionsRequestExternalStorage() {
        return MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE;
    }

    public boolean isCanUseCamera() {
        return canUseCamera;
    }

    public boolean isCanUseStorage() {
        return canUseStorage;
    }

    public CreateFragment getCreateFragment() {
        return createFragment;
    }

    public void setCreateFragment(CreateFragment createFragment) {
        this.createFragment = createFragment;
    }

}
