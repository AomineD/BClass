package com.wineberryhalley.bclassapp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.google.firebase.messaging.RemoteMessage;
import com.wineberryhalley.bclassapp.push.PushInfo;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.zip.Inflater;

public abstract class BaseActivity extends AppCompatActivity {
    public abstract void Main();
    public void statusChanged(int pixelesSizeBar){

    }
    public abstract int resLayout();
    public ArrayList<String> keysNotification(){
        return null;
    }

    public void onReceiveValues(ArrayList<PushInfo> values){

    }

    public void onNotificationReceived(RemoteMessage.Notification n, Bundle datos){

    }

    public void onReceiveJsonFromNotif(JSONObject object){

    }

    public static BaseActivity main;

    protected void setAnimAsset(String asset){
        this.loading_anim = asset;
    }

    private String loading_anim = "loading_bs.json";

    public boolean isLoading(){
       return inflay.getVisibility() == View.VISIBLE;
    }

    private TextView loading_text;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        main = this;
        setContentView(resLayout());
        inflay = getLayoutInflater().inflate(R.layout.loading_lay, (ViewGroup) root(), false);
        l = inflay.findViewById(R.id.lottie_ld);
        loading_text = inflay.findViewById(R.id.txt_loading);
        ((ViewGroup) root()).addView(inflay);
        inflay.setVisibility(View.GONE);
        handleNotifIfExist();
        this.savedInstanceState = savedInstanceState;
        Main();




    }

    private Bundle savedInstanceState;

    public Bundle getSavedInstanceState(){
        return savedInstanceState;
    }

    private View inflay;

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
        if(getIntent() != null && getIntent().getExtras() != null){
            Bundle b = getIntent().getExtras();
            ArrayList<PushInfo> vars = new ArrayList<>();
            receiveNotification = true;

            for (String key:
                 b.keySet()) {
                    PushInfo info = new PushInfo();
                    info.setKey(key);
                    String var = b.getString(key);
                    info.setInfo(var);
                    vars.add(info);
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

    private LottieAnimationView l;

    public void showLoading(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                l.setAnimation(loading_anim);
                loading_text.setText(R.string.loading_base);
                l.playAnimation();
                inflay.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showLoadingWithText(String txt){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                l.setAnimation(loading_anim);
                l.playAnimation();
                loading_text.setText(txt);
                inflay.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showLoading(String assetName){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
        l.setAnimation(assetName);
                loading_text.setText(R.string.loading_base);
        l.playAnimation();
        inflay.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showLoading(String assetName, String txt){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                l.setAnimation(assetName);
                loading_text.setText(txt);
                l.playAnimation();
                inflay.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showLoadingNoLoop(String assetName){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
        l.setRepeatCount(1);
                loading_text.setText(R.string.loading_base);
        l.setAnimation(assetName);
        l.playAnimation();
        inflay.setVisibility(View.VISIBLE);
            }
        });
    }

    public void showLoadingNoLoop(String assetName, String txt){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                l.setRepeatCount(1);
                l.setAnimation(assetName);
                l.playAnimation();
                loading_text.setText(txt);
                inflay.setVisibility(View.VISIBLE);
            }
        });
    }

    public void hideLoading(){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
        inflay.setVisibility(View.GONE);
            }
        });
    }
}
