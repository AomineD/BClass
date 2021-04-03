package com.wineberryhalley.bclassapp.baseapi;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.wineberryhalley.bclassapp.baseapi.RequestType.GET;

public class BaseApi<T> {
    private Context context;
    protected RequestQueue queue;

    private Class<T> tClass;
    private String url;
    private String end_point;
    private String finalUrl = "";
    private Map<String, String> map;
    private ObjectType objectType = ObjectType.JsonOBJECT;
    private Interfaces.SingleListener<T> listener;
    private Interfaces.MultipleObjectListener<T> multiple;


    private BaseApi(){
context = BaseProviderApp.context;
queue = Volley.newRequestQueue(context);
    }

    public Context getContext()
    {
return context;
    }


    protected String getStringFromRes(int res){
        return context.getString(res);
    }

    public void executeUrl(){
        finalUrl = url+end_point;
        switch (objectType){
            case JsonOBJECT:
                goJsonObject(type);
                break;
            case String:
                goString(type);
                break;
            case JsonArray:
                goJsonArray(type);
                break;
        }
    }


    private void goJsonObject(RequestType type) {
        switch (type){
            case GET:
                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, finalUrl, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        T ob = new Gson().fromJson(response.toString(), tClass);

                        if(listener != null){
                         listener.OnLoadSuccess(ob);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(listener != null){
                            listener.OnError(error.getMessage());
                        }

                        if(multiple != null){
                            multiple.OnError(error.getMessage());
                        }
                    }
                });
                queue.add(jsonObjectRequest);
                break;

            case POST:
                JSONObject jsonObject = new JSONObject(map);
                JsonObjectRequest jsonObjectRequest1 = new JsonObjectRequest(Request.Method.POST, finalUrl, jsonObject, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        T ob = new Gson().fromJson(response.toString(), tClass);

                        if(listener != null){
                            listener.OnLoadSuccess(ob);
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(listener != null){
                            listener.OnError(error.getMessage());
                        }

                        if(multiple != null){
                            multiple.OnError(error.getMessage());
                        }
                    }
                });
                queue.add(jsonObjectRequest1);
                break;
        }
    }


    private void goJsonArray(RequestType type) {
        switch (type){
            case GET:
                JsonArrayRequest jsonObjectRequest = new JsonArrayRequest(Request.Method.GET, finalUrl, null, new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<T> arrayList = new ArrayList<>();
                        try {
                        for (int i = 0; i < response.length(); i++) {

                                T ob = new Gson().fromJson(response.getJSONObject(i).toString(), tClass);
                          arrayList.add(ob);
                        }

                        if(multiple != null)
                        {
                            multiple.OnLoadSuccess(arrayList);
                        }

                        } catch (JSONException e) {
                            if(multiple != null) {
                                multiple.OnError(e.getMessage());
                            }
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(listener != null){
                            listener.OnError(error.getMessage());
                        }

                        if(multiple != null){
                            multiple.OnError(error.getMessage());
                        }
                    }
                });
                queue.add(jsonObjectRequest);
                break;

            case POST:
                StringRequest jsonObjectRequest1 = new StringRequest(Request.Method.POST, finalUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String responsea) {
                        ArrayList<T> arrayList = new ArrayList<>();
                        try {
                            JSONArray response = new JSONArray(responsea);
                            for (int i = 0; i < response.length(); i++) {

                                T ob = new Gson().fromJson(response.getJSONObject(i).toString(), tClass);
                                arrayList.add(ob);
                            }

                            if(multiple != null)
                            {
                                multiple.OnLoadSuccess(arrayList);
                            }

                        } catch (JSONException e) {
                            if(multiple != null) {
                                multiple.OnError(e.getMessage());
                            }
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(listener != null){
                            listener.OnError(error.getMessage());
                        }

                        if(multiple != null){
                            multiple.OnError(error.getMessage());
                        }
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        if (map == null) {
                            return super.getParams();
                        }else{
                            return map;
                        }
                    }
                };
                queue.add(jsonObjectRequest1);
                break;
        }
    }

    private void goString(RequestType type) {
        Log.e("MAIN", "goString: "+type.name()+" url "+finalUrl );
        switch (type){
            case GET:
                StringRequest jsonObjectRequest = new StringRequest(Request.Method.GET, finalUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            T ob = (T) response;
                            if(listener != null)
                            {
                                listener.OnLoadSuccess(ob);
                            }
                        }catch (Exception e){
                            if(listener != null){
                                listener.OnError(e.getMessage());
                            }
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(listener != null){
                            listener.OnError(error.getMessage());
                        }

                        if(multiple != null){
                            multiple.OnError(error.getMessage());
                        }
                    }
                });
                queue.add(jsonObjectRequest);
                break;

            case POST:
                StringRequest jsonObjectRequest1 = new StringRequest(Request.Method.POST, finalUrl, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                        T ob = (T) response;
                        if(listener != null)
                        {
                            listener.OnLoadSuccess(ob);
                        }
                        }catch (Exception e){
if(listener != null){
    listener.OnError(e.getMessage());
}
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if(listener != null){
                            listener.OnError(error.getMessage());
                        }

                        if(multiple != null){
                            multiple.OnError(error.getMessage());
                        }
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        if (map == null) {
                            return super.getParams();
                        }else{
                            return map;
                        }
                    }
                };
                queue.add(jsonObjectRequest1);
                break;
        }
    }

    private RequestType type;


    public static class ApiBuilder<T>{
        private String url;
        private String endpoint;
        private Map<String, String> hashMap = new HashMap<>();
        private BaseApi<T> baseApi;
        private RequestType requestType = GET;
        public ApiBuilder(String url, String endpoint){
        this.url = url;
        this.endpoint = endpoint;
        baseApi = new BaseApi<T>();
        }

        public ApiBuilder<T> requestType(RequestType requestType){
            this.requestType = requestType;
            return this;
        }

        public ApiBuilder<T> postParam(String key, String value)
        {
            hashMap.put(key, value);
       return this;
        }

        private Interfaces.SingleListener<T> real = null;
        private Interfaces.MultipleObjectListener<T> malt = null;

        public ApiBuilder<T> setListener(Interfaces.SingleListener<T> listener){

            real = listener;
            return this;
        }

        public ApiBuilder<T> setListener(Interfaces.MultipleObjectListener<T> listener){

            malt = listener;
            return this;
        }

        public BaseApi<T> build(ObjectType type, Class<T> tClass){
            baseApi.type = requestType;
            baseApi.url = url;
            baseApi.objectType = type;
            baseApi.end_point = endpoint;
            baseApi.map = hashMap;
            baseApi.listener = real;
            baseApi.tClass = tClass;
            baseApi.multiple = malt;
           return baseApi;
        }

    }


}
