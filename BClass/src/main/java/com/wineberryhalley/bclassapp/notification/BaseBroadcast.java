package com.wineberryhalley.bclassapp.notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import java.util.ArrayList;

public abstract class BaseBroadcast extends BroadcastReceiver {

    public abstract ArrayList<Intent> getIntent();

    public abstract ArrayList<String> actions();

    @Override
    public void onReceive(Context context, Intent intent) {

        if(hasAny())
        {
            for (int i = 0; i < actions().size(); i++) {
                String action = actions().get(i);
                if(intent.getAction().equals(action)){
                    context.startActivity(getIntent().get(i));
                    break;
                }
            }
        }

    }

    private boolean hasAny(){
        return getIntent() != null && getIntent().size() > 0 && actions() != null && actions().size() > 0;
    }
}
