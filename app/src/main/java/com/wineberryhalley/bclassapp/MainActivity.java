package com.wineberryhalley.bclassapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Movie;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.github.dhaval2404.imagepicker.ImagePicker;
import com.wineberryhalley.bclassapp.baseapi.BaseApi;
import com.wineberryhalley.bclassapp.baseapi.Interfaces;
import com.wineberryhalley.bclassapp.baseapi.ObjectType;
import com.wineberryhalley.bclassapp.notification.DownloadNotification;
import com.wineberryhalley.bclassapp.permission.PermissionBottom;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {

    @Override
    public void Main() {

     /*   ImagePicker.Companion.with(this)
                .crop()	    			//Crop image(Optional), Check Customization for more option
                .compress(1024)			//Final image size will be less than 1 MB(Optional)
                .maxResultSize(1080, 1080)	//Final image resolution will be less than 1080 x 1080(Optional)
                .start();
    */


        DownloadNotification downloadNotification = new DownloadNotification("Download", "ah");

        downloadNotification.setMaxProgress(100);
    }

    @Override
    public void statusChanged(int pixelesSizeBar) {

    }

    @Override
    public int resLayout() {
        return R.layout.activity_main;
    }

    @Override
    public ArrayList<String> keysNotification() {
        return null;
    }

    @Override
    public void onReceiveValues(ArrayList<String> values) {

    }

    private String profileUri;
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            //Image Uri will not be null for RESULT_OK
            Uri fileUri = data.getData();


            //You can get File object from intent
            //val file:File = ImagePicker.getFile(data)!!

            //You can also get File Path from intent
            profileUri = ImagePicker.Companion.getFilePath(data);
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), fileUri);
            } catch (IOException e) {
                Log.e("MAIN", "onActivityResult: "+e.getMessage() );
                e.printStackTrace();
            }
            Bitmap lastBitmap = null;
            lastBitmap = bitmap;
            //encoding image to string
            if(lastBitmap != null)
                profileUri = getStringImage(lastBitmap);
            //  Log.e("MAIN", "onActivityResult: "+profileUri);

            PicassoUtils.uploadImage(profileUri, "non", new PicassoUtils.UploadListener() {
                @Override
                public void OnSuccess(String url) {
                    Log.e(TAG, "OnSuccess: "+url );
                }

                @Override
                public void OnError(String erno) {
                    Log.e(TAG, "OnError: "+erno );
                }
            });
        } else if (resultCode == ImagePicker.RESULT_ERROR) {
            Log.e("MAIN", "onActivityResult: f" );
        } else {

        }
    }


    private String TAG ="MAIN";

    public String getStringImage(Bitmap bmp) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
        return encodedImage;

    }



}