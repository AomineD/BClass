package com.wineberryhalley.bclassapp.captcha.net;

import android.content.Context;
import android.os.AsyncTask;
import android.text.TextUtils;
import android.util.Log;

import com.wineberryhalley.bclassapp.captcha.utils.AddressUtils;
import com.wineberryhalley.bclassapp.captcha.utils.NetRequestUtils;
import com.wineberryhalley.bclassapp.captcha.utils.RiskTypeEnum;

import org.json.JSONObject;

public class CaptchaApi {


    public Context getContext() {
        return context;
    }

    private final Context context;
    public CaptchaApi(Context context){
        this.context = context;
    }

    public void executeApiOne(OnLoadOne loadOne){
        new RequestAPI1(loadOne).execute();
    }

    public void executeApiTwo(String result,OnLoadTwo loadOne){
        new RequestAPI2(loadOne).execute(result);
    }

    public interface OnLoadOne{
        void OnLoad(JSONObject params);
        void OnFail(String erno);
    }

    private String TAG ="MAIN";

    class RequestAPI1 extends AsyncTask<Void, Void, JSONObject> {

        private OnLoadOne listener;
        public RequestAPI1(OnLoadOne loadOne) {
            this.listener = loadOne;
        }



        @Override
        protected JSONObject doInBackground(Void... params) {
            JSONObject jsonObject = null;
            try {
                String result = NetRequestUtils.requestGet(AddressUtils.getRegister(getContext(), RiskTypeEnum.SLIDE));
              //  Log.e(TAG, "doInBackground: "+result );
                jsonObject = new JSONObject(result);
            } catch (Exception e) {
                e.printStackTrace();
                listener.OnFail(e.getMessage());
            }
            return jsonObject;
        }

        @Override
        protected void onPostExecute(JSONObject params) {
            // SDK可识别格式为
            // {"success":1,"challenge":"06fbb267def3c3c9530d62aa2d56d018","gt":"019924a82c70bb123aae90d483087f94","new_captcha":true}
            // TODO 设置返回api1数据，即使为 null 也要设置，SDK内部已处理

     if(params != null){
      listener.OnLoad(params);
     }
        }
    }

    public interface OnLoadTwo{
        void success();
        void fail();
    }

    class RequestAPI2 extends AsyncTask<String, Void, String> {


        private OnLoadTwo loadTwo;
        public RequestAPI2(OnLoadTwo loadTwo){
            this.loadTwo = loadTwo;
        }

        @Override
        protected String doInBackground(String... params) {
            return NetRequestUtils.requestPostByForm(AddressUtils.getValidate(getContext(), RiskTypeEnum.SLIDE), params[0]);
        }

        @Override
        protected void onPostExecute(String result) {
            try {
                JSONObject jsonObject = new JSONObject(result);
                String status = jsonObject.optString("result");
                if (TextUtils.isEmpty(status)) {
                    status = jsonObject.optString("status");
                }
                if ("success".equals(status)) {
loadTwo.success();
                } else {
loadTwo.fail();
                }
            } catch (Exception e) {
                e.printStackTrace();
loadTwo.fail();
            }
        }
    }
}
