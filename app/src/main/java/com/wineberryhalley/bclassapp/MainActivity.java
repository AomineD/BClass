package com.wineberryhalley.bclassapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.graphics.Movie;
import android.os.Bundle;
import android.util.Log;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.wineberryhalley.bclassapp.baseapi.BaseApi;
import com.wineberryhalley.bclassapp.baseapi.Interfaces;
import com.wineberryhalley.bclassapp.baseapi.ObjectType;
import com.wineberryhalley.bclassapp.notification.DownloadNotification;
import com.wineberryhalley.bclassapp.permission.PermissionBottom;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends BaseActivity {

    @Override
    public void Main() {
/*
DownloadNotification downloadNotification = new DownloadNotification("Downloading...", "Downloading video", R.drawable.ic_download);
downloadNotification.setMaxProgress(50);
downloadNotification.setOnFinished("Descargado", "Descarga completada");
downloadNotification.setOnClickNotification(this.getClass());
downloadNotification.show(203);
new Timer().schedule(new TimerTask() {
    @Override
    public void run() {
        int prg = downloadNotification.getProgressActual() + 10;
        downloadNotification.updateProgress(prg);
    }
}, 1500, 1500);*/


   /*     PermissionBottom b = new PermissionBottom(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA});
  b.setCustomDescriptionTo(0, "necesita permisos de escrituras para guardar fotos");
   b.showPermissionsRequest(new PermissionBottom.OnDismissPermission() {
       @Override
       public void OnDissmisResult(boolean okresult) {

       }
   });


     BaseApi<?> baseApi =   new BaseApi.ApiBuilder<Country>("https://restcountries.eu/rest/", "v2/all")
             .setListener(new Interfaces.MultipleObjectListener<Country>(){
                 @Override
                 public void OnLoadSuccess(ArrayList<Country> models) {
                     super.OnLoadSuccess(models);
                     for (Country c:
                          models) {
                         Log.e("MAIN", "OnLoadSuccess: "+c.getName() );
                     }
                 }

                 @Override
                 public void OnError(String erno) {
                     super.OnError(erno);
                     Log.e("MAIN", "OnError: "+erno );
                 }
             })
        .build(ObjectType.JsonArray, Country.class);

     baseApi.executeUrl()*/

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

}