package com.wineberryhalley.bclassapp.nuevo;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerNatives extends RecyclerView {

    public interface RecyclerListener{
        void putNativesNow();
        void hideNatives();
    }

    public void setRecyclerListener(RecyclerListener recyclerListener) {
        this.recyclerListener = recyclerListener;
    }

    private RecyclerListener recyclerListener;



    public RecyclerNatives(@NonNull Context context) {
        super(context);
    }

    public RecyclerNatives(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public RecyclerNatives(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onDraw(Canvas c) {
        super.onDraw(c);

        if(!scrollSetted) {
            addOnScrollListener(scrollListener);
            scrollSetted = true;
        }
    }

    private boolean scrollSetted = false;
    private boolean root = false;


    private OnScrollListener user;
    @Override
    public void addOnScrollListener(@NonNull OnScrollListener listener) {
   //     Log.e("MAIN", "addOnScrollListener: " + root);
        if (!root) {
            super.addOnScrollListener(listener);
            root = true;
        } else {
            user = listener;
        }
    }

    public OnScrollListener scrollListener = new OnScrollListener() {
        @Override
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            if(user != null){
                user.onScrollStateChanged(recyclerView, newState);

            }

            if(recyclerListener != null){
              //  Log.e("MAIN", "onScrollStateChanged: "+newState);
                if(newState == RecyclerView.SCROLL_STATE_IDLE){
                    recyclerListener.putNativesNow();
                }else{
                    recyclerListener.hideNatives();
                }
            }

        }

        @Override
        public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
          //  Log.e("MAIN", "onScrolled: "+(user != null) );
            if(user != null){
                user.onScrolled(recyclerView, dx, dy);
            }
        }
    };


}
