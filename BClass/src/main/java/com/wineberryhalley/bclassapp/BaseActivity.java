package com.wineberryhalley.bclassapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public abstract class BaseActivity extends AppCompatActivity {
    public abstract void Main();
    public abstract void statusChanged(int pixelesSizeBar);
    public abstract int resLayout();
    public abstract ArrayList<String> keysNotification();
    public abstract void onReceiveValues(ArrayList<String> values);

    public void onNotificationReceived(RemoteMessage.Notification n, Bundle datos){

    }

    public void onReceiveJsonFromNotif(JSONObject object){

    }

    public static BaseActivity main;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = this;
        setContentView(resLayout());
        handleNotifIfExist();
        Main();




    }

    private Runnable runnable(int pxls) {
        return new Runnable() {
            @Override
            public void run() {
              statusChanged(pxls);

            }
        };
    }

    protected boolean receiveNotification = false;

    private void handleNotifIfExist() {
        if(getIntent() != null && getIntent().getExtras() != null && keysNotification() != null){
            Bundle b = getIntent().getExtras();
            ArrayList<String> vars = new ArrayList<>();
            receiveNotification = true;
            for(int i=0; i < keysNotification().size(); i++) {
                if (b.containsKey(keysNotification().get(i))) {
                    String var = b.getString(keysNotification().get(i));
                    vars.add(var);
                }
            }
            if(vars.size() > 0)
            onReceiveValues(vars);



        }
    }



    public static boolean canReceiveNotifications(){
        return main != null && main.keysNotification() != null && main.keysNotification().size() > 0;
    }

    public View root(){
        return this.getWindow().getDecorView().findViewById(android.R.id.content);
    }

}
