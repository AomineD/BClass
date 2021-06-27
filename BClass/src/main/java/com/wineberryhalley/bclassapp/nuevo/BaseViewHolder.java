package com.wineberryhalley.bclassapp.nuevo;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public abstract class BaseViewHolder extends RecyclerView.ViewHolder {

    public<T> T findView(int id){
        if(view != null)
        return (T) view.findViewById(id);
    else
        return null;
    }


    private View view;
    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
        view = itemView;
        init();
    }

    public abstract void init();

}
