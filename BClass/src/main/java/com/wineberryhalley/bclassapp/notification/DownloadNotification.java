package com.wineberryhalley.bclassapp.notification;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.wineberryhalley.bclassapp.R;

public class DownloadNotification extends NotificationBase {

    public DownloadNotification(String title, String desc, int finalDrawable){
        finDrawa = finalDrawable;
        this.title = title;
        this.desc = desc;
        init(title, desc, android.R.drawable.stat_sys_download, PriorityNotification.DEFAULT);
        builder = getBuilder();
    }

    public DownloadNotification(String title, String desc){
        finDrawa = R.drawable.ic_download;
        this.title = title;
        this.desc = desc;
        init(title, desc, android.R.drawable.stat_sys_download, PriorityNotification.DEFAULT);
        builder = getBuilder();
    }

    public DownloadNotification(String title, String desc, PriorityNotification priorityNotification){
        finDrawa = R.drawable.ic_download;
        this.title = title;
        this.desc = desc;
        init(title, desc, android.R.drawable.stat_sys_download, priorityNotification);
        builder = getBuilder();
    }


    public void changePriority(PriorityNotification priorityNotification){
        init(title, desc, android.R.drawable.stat_sys_download, priorityNotification);
        builder = getBuilder();
    }

    private String title;
    private String desc;


    private int finDrawa;
    @Override
    protected boolean autoCancel() {
        return true;
    }

    @Override
    protected void onShow() {
//        Toast.makeText(NotifProvider.context, "YA SHOW", Toast.LENGTH_SHORT).show();
    }

    private String titleFinished;
    private String descFinished;

    public int getProgressActual() {
        return progressActual;
    }

    private int progressActual;



    public void setOnFinished(String title, String desc){
        titleFinished = title;
        descFinished = desc;
    }

    private String tterror;
    private String descError;
    public void setOnError(String title, String desc){
        tterror = title;
        descError = desc;
    }


    public<T> void setOnClickNotification(Class<T> tClass){
        Intent cont = new Intent(NotifProvider.context, tClass);
        PendingIntent contentIntent = PendingIntent.getActivity(NotifProvider.context, 842,cont, 0);
        builder.setContentIntent(contentIntent);
    }


    public<T> void setOnClickNotification(Class<T> tClass, Bundle args){
        Intent cont = new Intent(NotifProvider.context, tClass);
        cont.putExtras(args);
        PendingIntent contentIntent = PendingIntent.getActivity(NotifProvider.context, 842,cont, 0);
 builder.setContentIntent(contentIntent);
    }

    public<T> void setClickWhenFinish(Class<T> tClass, Bundle args){
        Intent cont = new Intent(NotifProvider.context, tClass);
        cont.putExtras(args);
        finalPend = PendingIntent.getActivity(NotifProvider.context, 842,cont, 0);
        //builder.setContentIntent(contentIntent);
    }

    private PendingIntent finalPend;


    private Notification.Builder builder;

    public int getMaxProgress() {
        return maxProgress;
    }

    private int maxProgress;

    private void goLow(){
        init(title, desc, android.R.drawable.stat_sys_download, PriorityNotification.LOW);
        builder = getBuilder();
    }

    public void setMaxProgress(int maxP){
         builder.setProgress(maxP, 0, true);
   maxProgress = maxP;
    }

    private int normalId;
    public void show(int id){
        normalId = id;
        showNotificationBuild(id, builder);
    }

    public void updateProgress(int progress) {
        if (normalId != 0) {
            goLow();
            progressActual = progress;
            if (progress < maxProgress) {
                builder.setProgress(maxProgress, progress, false);
                showNotificationBuild(normalId, builder);
            }else{
                builder.setProgress(0, 0, false);
                builder.setContentText(descFinished);
                builder.setContentTitle(titleFinished);
                builder.setSmallIcon(finDrawa);
                if(finalPend != null){
                    builder.setContentIntent(finalPend);
                }
                showNotificationBuild(normalId, builder);
            }
        }
    }

    public void errorProgress(){
        builder.setProgress(0, 0, false);
        builder.setContentText(descError);
        builder.setContentTitle(tterror);
        builder.setSmallIcon(finDrawa);
        showNotificationBuild(normalId, builder);
    }

    public void hide(){
        hideNotification(normalId, builder);
    }

}
