package com.wineberryhalley.bclassapp.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import java.util.ArrayList;
import java.util.List;

public abstract class NotificationBase {
protected String title;
    protected String desc;
    protected int DrawableRes;
    protected PriorityNotification priorityNotification = PriorityNotification.DEFAULT;
    private boolean showed = false;
    private BaseBroadcast broadcast;

    private String chanId;
    protected void init(String title, String desc, int drawable, PriorityNotification prioti){
        this.title = title;
        this.desc = desc;
        this.DrawableRes = drawable;
        this.chanId = shuffle(title);
        this.priorityNotification = prioti;
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationUtil.initNotifications(chanId, getManagerPriority());
        }
        initialized = true;
    }

    private String shuffle(String input){
        List<Character> characters = new ArrayList<Character>();
        for(char c:input.toCharArray()){
            characters.add(c);
        }
        StringBuilder output = new StringBuilder(input.length());
        while(characters.size()!=0){
            int randPicker = (int)(Math.random()*characters.size());
            output.append(characters.remove(randPicker));
        }
       return output.toString();
    }

    private boolean initialized = false;
private Notification notification;

protected abstract boolean autoCancel();
protected abstract void onShow();
private boolean builded = false;
    protected void buildNotification(){
    if(initialized) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            notification = new Notification.Builder(NotifProvider.context, chanId)
                    .setContentTitle(title)
                    .setContentText(desc)
                    .setSmallIcon(DrawableRes)

                    .setAutoCancel(autoCancel())
                    .build();
        } else {
            notification = new Notification.Builder(NotifProvider.context)
                    .setContentTitle(title)
                    .setContentText(desc)
                    .setSmallIcon(DrawableRes)
                    .setPriority(getRealPriority())
                    .setAutoCancel(autoCancel())
                    .build();
        }
    }
}

    protected Notification.Builder getBuilder(){
        if(initialized) {

            return getBaseBuilder();
        }else{
            return null;
        }
    }


    protected Notification.Builder getBuilderMoreDesc(String expandable){
        if(initialized) {
            Notification.Builder b = getBaseBuilder();
            b.setStyle(new Notification.BigTextStyle()
                    .bigText(expandable));

            return b;
        }else{
            return null;
        }
    }

    private Notification.Builder getBaseBuilder(){


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

                return new Notification.Builder(NotifProvider.context, chanId)
                        .setContentTitle(title)
                        .setContentText(desc)

                        .setSmallIcon(DrawableRes)
                        .setAutoCancel(autoCancel());
            }else{
                return new Notification.Builder(NotifProvider.context)
                        .setContentTitle(title)
                        .setContentText(desc)
                        .setSmallIcon(DrawableRes)
                        .setPriority(getRealPriority())
                        .setAutoCancel(autoCancel());
            }
    }


public void showNotification(int id) {
    if (notification != null) {
        NotificationManager notificationManager = NotificationUtil.getNotificationManager();

        notificationManager.notify(id, notification);
        showed = true;
        onShow();
    }
}


    public void showNotificationBuild(int id, Notification.Builder builder) {
        if (builder != null) {
            NotificationManager notificationManager = NotificationUtil.getNotificationManager();

            notificationManager.notify(id, builder.build());
            showed = true;
            onShow();
        }
    }

    public void hideNotification(int id, Notification.Builder builder){
        if (builder != null) {
            NotificationManager notificationManager = NotificationUtil.getNotificationManager();
        showed = false;
            notificationManager.cancel(id);
        }
    }

    public void hideNotification(int id){
        if (notification != null) {
            NotificationManager notificationManager = NotificationUtil.getNotificationManager();
            showed = false;
            notificationManager.cancel(id);
        }
    }

protected void setNotification(Notification notification){
        this.notification = notification;
}

protected Notification getNotification(){
    return notification;
}

public boolean isBuilded(){
    return builded;
}

public boolean isShowed(){
    return showed;
}

private int getRealPriority(){
    switch (priorityNotification){
        case LOW:
            return Notification.PRIORITY_LOW;
        case HIGH:
            return Notification.PRIORITY_HIGH;
        case DEFAULT:
            return Notification.PRIORITY_DEFAULT;
        case MIN:
            return Notification.PRIORITY_MIN;
        case MAX:
            return Notification.PRIORITY_MAX;
        default:
            return Notification.PRIORITY_DEFAULT;
    }
}



    @RequiresApi(api = Build.VERSION_CODES.N)
    private int getManagerPriority(){
        switch (priorityNotification){
            case LOW:
                return NotificationManager.IMPORTANCE_LOW;
            case HIGH:
                return NotificationManager.IMPORTANCE_HIGH;
            case DEFAULT:
                return NotificationManager.IMPORTANCE_DEFAULT;
            case MIN:
                return NotificationManager.IMPORTANCE_MIN;
            case MAX:
                return NotificationManager.IMPORTANCE_MAX;
            default:
                return NotificationManager.IMPORTANCE_DEFAULT;
        }
    }

    protected void setBroadcast(BaseBroadcast b, int pos,Notification.Builder bk){
        Intent br = new Intent(NotifProvider.context, BaseBroadcast.class);
        br.setAction(b.actions().get(pos));
   PendingIntent pendingIntent =     PendingIntent.getBroadcast(NotifProvider.context, 443, br, PendingIntent.FLAG_UPDATE_CURRENT);
        bk.setContentIntent(pendingIntent);
    }

    protected void setBroadcast(BaseBroadcast b,Notification.Builder bk){
        Intent br = new Intent(NotifProvider.context, BaseBroadcast.class);
        br.setAction(b.actions().get(0));
        PendingIntent pendingIntent =     PendingIntent.getBroadcast(NotifProvider.context, 443, br, PendingIntent.FLAG_UPDATE_CURRENT);
        bk.setContentIntent(pendingIntent);
    }

    public<T> void setOnClickNotificationBase(Class<T> tClass, Notification.Builder builder){
        Intent cont = new Intent(NotifProvider.context, tClass);
        PendingIntent contentIntent = PendingIntent.getActivity(NotifProvider.context, 842,cont, 0);
        builder.setContentIntent(contentIntent);
    }


    public<T> void setOnClickNotificationBase(Class<T> tClass, Bundle args, Notification.Builder builder){
        Intent cont = new Intent(NotifProvider.context, tClass);
        cont.putExtras(args);
        PendingIntent contentIntent = PendingIntent.getActivity(NotifProvider.context, 842,cont, 0);
        builder.setContentIntent(contentIntent);
    }

}
