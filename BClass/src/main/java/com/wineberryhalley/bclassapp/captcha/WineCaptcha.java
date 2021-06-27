package com.wineberryhalley.bclassapp.captcha;

import android.util.Log;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.RequiresApi;
import androidx.annotation.RequiresFeature;
import androidx.appcompat.app.AppCompatActivity;

import com.geetest.sdk.GT3ConfigBean;
import com.geetest.sdk.GT3ErrorBean;
import com.geetest.sdk.GT3GeetestUtils;
import com.geetest.sdk.GT3Listener;
import com.geetest.sdk.utils.GT3ServiceNode;
import com.geetest.sdk.views.GT3GeetestButton;
import com.wineberryhalley.bclassapp.R;
import com.wineberryhalley.bclassapp.captcha.net.CaptchaApi;

import org.json.JSONObject;

public class WineCaptcha {

    private GT3ConfigBean gt3ConfigBean;
    GT3GeetestUtils gt3GeetestUtils;
    private AppCompatActivity activity;

    public interface CaptchaListener{
        void onButtonClick();
        void onSuccess(String s);
        void onClosed(int i);
        void onFailed(String erno);
    }


    public static WineCaptcha getOn(AppCompatActivity activity){
        return new WineCaptcha(activity);
    }

    private CaptchaApi api;
    protected WineCaptcha(AppCompatActivity activity){
        this.activity = activity;
         api = new CaptchaApi(activity);
    }

    public WineCaptcha config(){
if(activity == null){
    return this;
}

         gt3GeetestUtils = new GT3GeetestUtils(activity);


           gt3ConfigBean = new GT3ConfigBean();
        gt3ConfigBean.setPattern(2);
        gt3ConfigBean.setCanceledOnTouchOutside(false);
// Set language. Use system default language if null
        gt3ConfigBean.setLang(null);
// Set the timeout for loading webview static files
        gt3ConfigBean.setTimeout(10000);
// Set the timeout for webview request after user finishing the CAPTCHA verification. The default is 10,000
        gt3ConfigBean.setWebviewTimeout(10000);
// Set callback listener
return this;
    }

    public WineCaptcha show(LinearLayout showOn, CaptchaListener listener){
        if(activity == null){
            return this;
        }
        GT3GeetestButton gt3GeetestButton = (GT3GeetestButton) LayoutInflater.from(activity).inflate(R.layout.zxxz, showOn,false);

gt3ConfigBean.setGt3ServiceNode(GT3ServiceNode.NODE_CHINA);

        showOn.addView(gt3GeetestButton);
        gt3ConfigBean.setListener(new GT3Listener() {
            @Override
            public void onReceiveCaptchaCode(int i) {

            }

            @Override
            public void onStatistics(String s) {

            }

            @Override
            public void onClosed(int i) {
listener.onClosed(i);
            }

            @Override
            public void onSuccess(String s) {
listener.onSuccess(s);
            }

            @Override
            public void onFailed(GT3ErrorBean gt3ErrorBean) {
            //    Log.e("MAIN", "onFailed: "+gt3ErrorBean.errorCode );
listener.onFailed(gt3ErrorBean.errorDesc);
            }

            @Override
            public void onButtonClick() {
listener.onButtonClick();
api.executeApiOne(new CaptchaApi.OnLoadOne() {
    @Override
    public void OnLoad(JSONObject params) {
     //   Log.e("MAIN", "OnLoad: "+params.toString() );
        gt3ConfigBean.setApi1Json(params);
        gt3GeetestUtils.getGeetest();
    }

    @Override
    public void OnFail(String erno) {
     //   Log.e("MAIN", "OnFail: "+erno );
    }
});
                //gt3GeetestUtils.getGeetest();
            }

            @Override
            public void onDialogReady(String s) {
                super.onDialogReady(s);

            }

            @Override
            public void onDialogResult(String s) {
                super.onDialogResult(s);
api.executeApiTwo(s, new CaptchaApi.OnLoadTwo() {
    @Override
    public void success() {
        gt3GeetestUtils.showSuccessDialog();
    }

    @Override
    public void fail() {
        gt3GeetestUtils.showFailedDialog();
    }
});
            }
        });
        gt3GeetestUtils.init(gt3ConfigBean);
        gt3GeetestButton.setGeetestUtils(gt3GeetestUtils);


        return this;
    }
}
