package com.wineberryhalley.bclassapp;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.Timer;
import java.util.TimerTask;

public abstract class BottomBaseShet extends BottomSheetDialogFragment {


    public abstract int layoutID();

    private String TAG = "MAIN";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
    View containeView = inflater.inflate(layoutID(), container, false);
      //  Log.e(TAG, "onCreateView: aja" );
    //  BottomSheetBehavior      mBottomSheetBehavior = BottomSheetBehavior.from(((View) containeView.getParent()));
        return containeView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.CustomBottomSheetDialogD);
    }

    public boolean Modal(){
        return false;
    }

    public int heightMax(){
        return 0;
    }

    private View mainView;
    private BottomSheetBehavior<View> bottomSheetBehavior;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainView = view;


            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    if(getActivity() != null)
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            bottomSheetBehavior = BottomSheetBehavior.from(((View) view.getParent()));
                            if(Modal()) {
                                bottomSheetBehavior.setDraggable(true);
                    if (BottomSheetBehavior.from(((View) view.getParent())) != null) {

                        bottomSheetBehavior.setPeekHeight(dip2px(heightMax()));
                        view.requestLayout();
                    }
                            }else{
                                if(heightMax() == 0){
                                    bottomSheetBehavior.setPeekHeight(mainView.getHeight());
                                }else {
                                    bottomSheetBehavior.setPeekHeight(dip2px(heightMax()));
                                }
                                Log.e(TAG, "run: "+mainView.getHeight() );
                                bottomSheetBehavior.setDraggable(false);
                                view.requestLayout();
                            }

                        }
                    });
                }
            }, 200);

        OnStart();
    }
    public abstract void OnStart();

    public <T> T find(int id){
       return (T) mainView.findViewById(id);
    }

    @Override
    public void show(@NonNull FragmentManager manager, @Nullable String tag) {
        super.show(manager, tag);
    }


    /**
     * dip to px
     */
    private int dip2px(float dpValue) {
        final float scale = getActivity().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px to dp
     */
    private int px2dip(float pxValue) {
        final float scale =  getActivity().getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

  public void expandModal(){
        if(Modal() && bottomSheetBehavior != null){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
        }
  }

    public void collapseModal(){
        if(Modal() && bottomSheetBehavior != null){
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }
    }

    public void setPeek(int pixels){
        if(Modal() && bottomSheetBehavior != null)
        bottomSheetBehavior.setPeekHeight(pixels, true);
    }

    public void setPeekDP(int dp){
        if(Modal() && bottomSheetBehavior != null)
        bottomSheetBehavior.setPeekHeight(dip2px(dp), true);
    }


    public void requestNewSize(){
        if(heightMax() == 0){
            bottomSheetBehavior.setPeekHeight(mainView.getHeight());
        }else {
            bottomSheetBehavior.setPeekHeight(dip2px(heightMax()));
        }
        Log.e(TAG, "requestNewSize: "+mainView.getHeight() +", m: "+mainView.getMeasuredHeight());
        mainView.requestLayout();
    }
}
