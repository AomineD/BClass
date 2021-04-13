package com.wineberryhalley.bclassapp.notification;

import android.content.Intent;

import java.util.ArrayList;

public class DefaultBroadCast extends BaseBroadcast{

    private ArrayList<Intent> getInt = new ArrayList<>();
    private ArrayList<String> getact = new ArrayList<>();
    @Override
    public ArrayList<Intent> getIntent() {
        return getInt;
    }

    @Override
    public ArrayList<String> actions() {
        return getact;
    }

    protected DefaultBroadCast(){

    }

    public static DefaultBroadCast getDefault(){
        return new DefaultBroadCast();
    }

    protected DefaultBroadCast setAction(String action){
        getact.add(action);
        return this;
    }

    protected DefaultBroadCast setIntent(Intent intent){
        getInt.add(intent);
        return this;
    }

    protected DefaultBroadCast setActions(ArrayList<String> action){
        getact.addAll(action);
        return this;
    }

    protected DefaultBroadCast setIntents(ArrayList<Intent> intent){
        getInt.addAll(intent);
        return this;
    }
}
