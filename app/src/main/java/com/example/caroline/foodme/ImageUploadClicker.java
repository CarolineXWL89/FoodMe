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
import android.support.design.widget.Snackbar;
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
        //todo make two options
        if(camera){
            checkCameraPermissions();
            if(!canUseCamera){
                Snackbar snackbar = Snackbar.make(v, "Camera Permissions are not enabled \n Please enable in order to take photos", Snackbar.LENGTH_LONG);
                snackbar.setAction("Allow Access", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        checkCameraPermissions();
                    }
                });
                snackbar.show();
            } else {
                Intent cameraIntent = new Intent(ACTION_IMAGE_CAPTURE);
                createFragment.startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
        } else {
            checkStoragePermissions();
            if(!canUseStorage){
                Snackbar snackbar = Snackbar.make(v, "Storage Permissions are not enabled \n Please enable in order to upload", Snackbar.LENGTH_LONG);
                snackbar.setAction("Allow Access", new View.OnClickListener() {
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
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            createFragment.requestPermissions(new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE}, MY_PERMISSIONS_REQUEST_EXTERNAL_STORAGE);
        } else{
            canUseStorage = true;
        }
    }

    public void setCanUseCamera(boolean canUseCamera) {
        this.canUseCamera = canUseCamera;
    }
}
