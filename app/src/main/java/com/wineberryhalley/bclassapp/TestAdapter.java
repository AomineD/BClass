package com.wineberryhalley.bclassapp;

import android.app.Activity;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.wineberryhalley.bclassapp.nuevo.BaseViewHolder;

import java.util.ArrayList;

public class TestAdapter extends BaseAdapter<TestAdapter.TestHolder, String> {

    public TestAdapter(Activity activity){
        ArrayList<String> a = new ArrayList<>();
        a.add("a");
        a.add("a");
        a.add("a");
        a.add("a");
        a.add("a");
        a.add("a");
        a.add("a");
     config(activity, a);
    }

    @Override
    protected int resLayout() {
        return R.layout.item_test_nativ;
    }

    @Override
    protected RecyclerView.ViewHolder viewHolderClass(View layout) {
        return new TestHolder(layout);
    }

    @Override
    protected void bindHolder(TestHolder holder, int position, String model) {

        if(canLoadNatives()){
holder.native_view.setVisibility(View.VISIBLE);
        }else{
            holder.native_view.setVisibility(View.GONE);
        }

    }

    public class TestHolder extends BaseViewHolder {


        private RelativeLayout native_view;
        public TestHolder(@NonNull View itemView) {
            super(itemView);
        }

        @Override
        public void init() {
            native_view = findView(R.id.native_ad);
        }
    }
}
