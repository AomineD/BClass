package com.wineberryhalley.bclassapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.os.Bundle;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
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


        PermissionBottom b = new PermissionBottom(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA});
  b.setCustomDescriptionTo(0, "necesita permisos de escrituras para guardar fotos");
   b.showPermissionsRequest();

        
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