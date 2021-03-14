package com.wineberryhalley.bclassapp.notification;

import android.app.Notification;
import android.widget.Toast;

public class DownloadNotification extends NotificationBase {

    public DownloadNotification(String title, String desc, int finalDrawable){
        finDrawa = finalDrawable;
        init(title, desc, android.R.drawable.stat_sys_download, PriorityNotification.DEFAULT);
        builder = getBuilder();
    }

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

    private Notification.Builder builder;
    private int maxProgress;
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
            progressActual = progress;
            if (progress < maxProgress) {
                builder.setProgress(maxProgress, progress, false);
                showNotificationBuild(normalId, builder);
            }else{
                builder.setProgress(0, 0, false);
                builder.setContentText(descFinished);
                builder.setContentTitle(titleFinished);
                builder.setSmallIcon(finDrawa);
                showNotificationBuild(normalId, builder);
            }
        }
    }


}
