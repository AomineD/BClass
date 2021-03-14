package com.wineberryhalley.bclassapp.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.annotation.RestrictTo;
import androidx.core.content.ContextCompat;

import static android.content.Context.NOTIFICATION_SERVICE;

public class NotificationUtil {
    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void initNotifications(String channelId, int importante){
        NotificationChannel channel = new NotificationChannel(channelId, "general", importante);
        channel.setDescription("Channel one desc");
        NotificationManager notificationManager = getSystemService(NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
        Log.e("MAIN", "initNotifications: listo "+channelId );
    }


    public static <T> T getSystemService(String tclass){

        return (T) NotifProvider.context.getSystemService(tclass);
    }

    public static NotificationManager getNotificationManager(){
        return getSystemService(NOTIFICATION_SERVICE);
    }




}
