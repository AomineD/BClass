package com.wineberryhalley.bclassapp.permission;

import android.Manifest;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.baoyachi.stepview.HorizontalStepView;
import com.baoyachi.stepview.bean.StepBean;
import com.kofigyan.stateprogressbar.StateProgressBar;
import com.wineberryhalley.bclassapp.BottomBaseShet;
import com.wineberryhalley.bclassapp.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class PermissionBottom extends BottomBaseShet {

    public PermissionBottom(AppCompatActivity activity, String[] stPermiss){
        this.appCompatActivity = activity;
        this.stPermiss = stPermiss;
switch (stPermiss.length){
    case 1:
        stateNumber = StateProgressBar.StateNumber.ONE;
        break;
    case 2:
        stateNumber = StateProgressBar.StateNumber.TWO;
        break;
    case 3:
        stateNumber = StateProgressBar.StateNumber.THREE;
        break;
    case 4:
        stateNumber = StateProgressBar.StateNumber.FOUR;
    default:
        stateNumber = StateProgressBar.StateNumber.FIVE;
}
    }

    private StateProgressBar.StateNumber getActual(boolean next){
        if(next)
        p++;

        StateProgressBar.StateNumber am = StateProgressBar.StateNumber.ONE;
       int as =  p+1;
        switch (as){
            case 1:
                am = StateProgressBar.StateNumber.ONE;
                break;
            case 2:
                am = StateProgressBar.StateNumber.TWO;
                break;
            case 3:
                am = StateProgressBar.StateNumber.THREE;
                break;
            case 4:
                am = StateProgressBar.StateNumber.FOUR;
                break;
            case 5:
                am = StateProgressBar.StateNumber.FIVE;
                break;
        }

        return am;
    }

    private AppCompatActivity appCompatActivity;
    private void configurePermissions(){
        permissions.clear();
        for (int i = 0; i < stPermiss.length; i++) {
            PermissionModel m = new PermissionModel();
            m.setNamePermission(getName(i));
            m.setPermissionCode(stPermiss[i]);
            permissions.add(m);
        }
    }

    private String[] descrs = new String[0];
    public void setCustomDescriptionFull(String[] descriptions){
        if(descriptions.length == stPermiss.length){
            descrs = descriptions;
        }
    }

    public void setCustomDescriptionTo(int position, String descf){
        if(position < stPermiss.length){
            if(position > descrs.length || descrs.length == 0){
                descrs = new String[stPermiss.length];
            }
           // Log.e("MAIN", "setCustomDescriptionTo: "+stPermiss.length );
            descrs[position] = descf;
        }
    }


    public String getName(int pos){
        switch (stPermiss[pos]){
            case Manifest
                    .permission.WRITE_EXTERNAL_STORAGE:
                return getString(R.string.write_pe_mission);
            case Manifest
                    .permission.SEND_SMS:
                return getString(R.string.sendsms_pe_mission);
            case Manifest
                    .permission.CAMERA:
                return getString(R.string.camera_pe_mission);
        }
        return getString(R.string.defafperme);
    }

    public String getDefaultDescription(int pos){
        String abueno = "";
        switch (stPermiss[pos]){
            case Manifest
                    .permission.WRITE_EXTERNAL_STORAGE:
                abueno = getString(R.string.write_default_perm);
            break;
            case Manifest
                    .permission.SEND_SMS:
                abueno = getString(R.string.sendsms_default_perm);
            break;
            case Manifest
                    .permission.CAMERA:
                abueno = getString(R.string.camera_default_perm);
            break;
            default:
                abueno = getString(R.string._default_perm);
        }

       abueno = String.format(abueno, getContext().getString(R.string.app_name));
        return abueno;
    }

    private String[] stPermiss;
    private ArrayList<PermissionModel> permissions = new ArrayList<>();

    public void showPermissionsRequest(){
        show(appCompatActivity.getSupportFragmentManager(), "permshowa");

    }

    @Override
    public int layoutID() {
        return R.layout.bottom_permiss_p;
    }

    @Override
    public boolean Modal() {
        return false;
    }

    private String[] descriptionData;
    private StateProgressBar.StateNumber stateNumber;
    private TextView descr;
    private TextView titl;
    private CardView btn;
    StateProgressBar setpview;
    @Override
    public void OnStart() {
        configurePermissions();
        setpview = find(R.id.your_state_progress_bar_id);
        titl = find(R.id.tittl_perm);
        String g = getString(R.string.base_ttl_perms);
        g = String.format(g, getString(R.string.app_name));
        titl.setText(g);
        descr = find(R.id.desc_permission);
        btn = find(R.id.btn_giveper);
btn.setOnClickListener(clickTo());
        if(descrs.length < 1){
            String gt = getDefaultDescription(0);
            descr.setText(gt);
        }else{
            String gt = getString(R.string.app_name)+" "+descrs[0];
            descr.setText(gt);
        }

        if (permissions.size() > 0) {
            getBeans();
setpview.setStateNumberTextSize(13);

            setpview.setStateDescriptionData(descriptionData);

            setpview.setMaxStateNumber(stateNumber);

            checkPermissionIfSuccess();
        }else{
            Toast.makeText(appCompatActivity, "No permissions", Toast.LENGTH_SHORT).show();
            dismissAllowingStateLoss();
        }
    }

    private void checkPermissionIfSuccess(){

        if(ContextCompat.checkSelfPermission(getContext(), stPermiss[p]) == PackageManager.PERMISSION_GRANTED){
            nextPermission();
        }

    }

    private int p;
    private View.OnClickListener clickTo() {
    return new View.OnClickListener() {
        @Override
        public void onClick(View v) {
 p = setpview.getCurrentStateNumber() - 1;

requestPermissions(new String[]{stPermiss[p]}, 960);

        }
    };
    }

    private void nextPermission() {
        StateProgressBar.StateNumber ase = getActual(true);
        if (p < stPermiss.length) {
            setpview.setCurrentStateNumber(ase);
            // Log.e(TAG, "nextPermission: "+descrs[p]);
            if (descrs.length < 1) {
                String gt = getDefaultDescription(p);
                descr.setText(gt);
            } else if (descrs.length > p && descrs[p] != null && !descrs[p].isEmpty()) {
                String gt = getString(R.string.app_name) + " " + descrs[p];
                descr.setText(gt);
            } else {
                String gt = getDefaultDescription(p);
                descr.setText(gt);
            }

            checkPermissionIfSuccess();
        }else{
            successPermissions();
        }
    }


    private void successPermissions()
    {
        titl.setText(R.string.success_perm);
        descr.setText(R.string.success_perm_de);
        setpview.setAllStatesCompleted(true);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
               dismissAllowingStateLoss();
            }
        }, 2300);
    }
    private boolean a = false;
    private void getBeans() {
        a = false;
        descriptionData = new String[permissions.size()];
        for (int i = 0; i < permissions.size(); i++) {
            descriptionData[i] = permissions.get(i).getNamePermission();
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
            if(p == stPermiss.length - 1){
                successPermissions();
            }else{
                nextPermission();
            }
        }else{
            dismissAllowingStateLoss();
        }
    }
}

