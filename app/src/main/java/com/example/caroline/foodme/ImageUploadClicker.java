package com.example.caroline.foodme;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import android.support.v4.app.Fragment;

import java.io.File;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;
import static com.example.caroline.foodme.CreateFragment.TAG;

/**
 * Created by maylisw on 4/23/18.
 */

public class ImageUploadClicker implements View.OnClickListener {
    private static final int CAMERA_REQUEST = 1888;
    private Context context;
    private Activity activity;
    final private int MY_PERMISSIONS_REQUEST_CAMERA = 123;
    private boolean canUseCamera = false;
    private CreateFragment createFragment;

    public ImageUploadClicker(Context context, Activity activity, CreateFragment createFragment) {
        this.context = context;
        this.activity = activity;
        this.createFragment = createFragment;
    }

    @Override
    public void onClick(View v) {
        checkPermissions();
        if(!canUseCamera){
            Toast.makeText(context, "Camera Permissions are not enabled \n Please enable in order to upload ", Toast.LENGTH_SHORT).show();
        } else {
            Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
            createFragment.startActivityForResult(cameraIntent, CAMERA_REQUEST);
            //todo option to upload or capture
        }

    }

    private void checkPermissions() {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            Log.d(TAG, "checkPermissions: permissions not granted");
            Toast.makeText(context, "permissions not granted", Toast.LENGTH_SHORT).show();
            createFragment.requestPermissions(new String[]{android.Manifest.permission.CAMERA}, MY_PERMISSIONS_REQUEST_CAMERA);
        } else{
            canUseCamera = true;
        }
    }

    public void setCanUseCamera(boolean canUseCamera) {
        this.canUseCamera = canUseCamera;
    }
}
