package com.wineberryhalley.bclassapp.baseapi;

import java.util.ArrayList;

public class Interfaces {

    public static class SingleListener<T>{
        public void OnLoadSuccess(T models){

        }

        public void OnError(String erno){

        }
    }

    public static class MultipleObjectListener<T>{
        public void OnLoadSuccess(ArrayList<T> models){

        }

        public void OnError(String erno){

        }
    }
}
