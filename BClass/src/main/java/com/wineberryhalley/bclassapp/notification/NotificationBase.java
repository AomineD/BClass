package com.wineberryhalley.bclassapp.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.os.Build;

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
        }else{
            return null;
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
            default:
                return NotificationManager.IMPORTANCE_DEFAULT;
        }
    }

}
