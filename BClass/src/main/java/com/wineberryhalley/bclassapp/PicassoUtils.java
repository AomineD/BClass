package com.wineberryhalley.bclassapp;

import android.net.Uri;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;
import jp.wasabeef.picasso.transformations.RoundedCornersTransformation;

public class PicassoUtils {

public static void loadImageIn(String img, ImageView imgview){
    Picasso.get().load(Uri.parse(img)).fit().into(imgview);
}


    public static void loadCountryFlag(String code, ImageView imgview, LottieAnimationView lt){
        // imgview.getRootView();
        //  Log.e("MAIN", "loadImageWithLoading: "+imgview.getRootView().getClass().getSimpleName());
        //lt.setVisibility(View.VISIBLE);
        imgview.setVisibility(View.VISIBLE);
        String img = "https://flagcdn.com/w320/"+code.toLowerCase()+".jpg";
        //   Log.e("MAIN", "loadCountryFlag: "+img );
        Picasso.get().load(Uri.parse(img)).fit().into(imgview, new Callback() {
            @Override
            public void onSuccess() {
                imgview.setVisibility(View.VISIBLE);
                lt.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }


    public static void loadImageWithLoading(String img, ImageView imgview, LottieAnimationView lt){
   // imgview.getRootView();
      //  Log.e("MAIN", "loadImageWithLoading: "+imgview.getRootView().getClass().getSimpleName());
        //lt.setVisibility(View.VISIBLE);
        imgview.setVisibility(View.VISIBLE);
        Picasso.get().load(Uri.parse(img)).fit().into(imgview, new Callback() {
            @Override
            public void onSuccess() {
                imgview.setVisibility(View.VISIBLE);
                lt.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }


    public static void loadImageWithLoading(String img, int rounded, ImageView imgview, LottieAnimationView lt){
        // imgview.getRootView();
        //  Log.e("MAIN", "loadImageWithLoading: "+imgview.getRootView().getClass().getSimpleName());
        //lt.setVisibility(View.VISIBLE);
        imgview.setVisibility(View.VISIBLE);
        Picasso.get().load(Uri.parse(img)).fit().transform(new RoundedCornersTransformation(rounded, 0)).into(imgview, new Callback() {
            @Override
            public void onSuccess() {
                imgview.setVisibility(View.VISIBLE);
                lt.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }

    public static void loadCircleImgWithLoading(String img, ImageView imgview, LottieAnimationView lt){
        // imgview.getRootView();
        //  Log.e("MAIN", "loadImageWithLoading: "+imgview.getRootView().getClass().getSimpleName());
        //lt.setVisibility(View.VISIBLE);
        imgview.setVisibility(View.VISIBLE);
        Picasso.get().load(Uri.parse(img)).fit().transform(new CropCircleTransformation()).into(imgview, new Callback() {
            @Override
            public void onSuccess() {
                imgview.setVisibility(View.VISIBLE);
                lt.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }


    public static void loadImageWithLoading(int img, ImageView imgview, LottieAnimationView lt){
        // imgview.getRootView();
        //  Log.e("MAIN", "loadImageWithLoading: "+imgview.getRootView().getClass().getSimpleName());
        //lt.setVisibility(View.VISIBLE);
        imgview.setVisibility(View.VISIBLE);
        Picasso.get().load(img).fit().into(imgview, new Callback() {
            @Override
            public void onSuccess() {
                imgview.setVisibility(View.VISIBLE);
                lt.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }

    public static void loadCircleImgWithLoading(int img, ImageView imgview, LottieAnimationView lt){
        // imgview.getRootView();
        //  Log.e("MAIN", "loadImageWithLoading: "+imgview.getRootView().getClass().getSimpleName());
        //lt.setVisibility(View.VISIBLE);
        imgview.setVisibility(View.VISIBLE);
        Picasso.get().load(img).fit().transform(new CropCircleTransformation()).into(imgview, new Callback() {
            @Override
            public void onSuccess() {
                imgview.setVisibility(View.VISIBLE);
                lt.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }


    public static void loadCircleFromUri(Uri img, ImageLoadingView image){
        // imgview.getRootView();
        //  Log.e("MAIN", "loadImageWithLoading: "+imgview.getRootView().getClass().getSimpleName());
        //lt.setVisibility(View.VISIBLE);
        image.getImg().setVisibility(View.VISIBLE);
        Picasso.get().load(img).fit().transform(new CropCircleTransformation()).into(image.getImg(), new Callback() {
            @Override
            public void onSuccess() {
                image.getImg().setVisibility(View.VISIBLE);
                image.getLottie().setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                image.getLottie().setVisibility(View.GONE);
            }
        });
    }

    public static void loadImageWithLoading(int img, int rounded,ImageView imgview, LottieAnimationView lt){
        // imgview.getRootView();
        //  Log.e("MAIN", "loadImageWithLoading: "+imgview.getRootView().getClass().getSimpleName());
        //lt.setVisibility(View.VISIBLE);
        imgview.setVisibility(View.VISIBLE);
        Picasso.get().load(img).fit().transform(new RoundedCornersTransformation(rounded, 0)).into(imgview, new Callback() {
            @Override
            public void onSuccess() {
                imgview.setVisibility(View.VISIBLE);
                lt.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }

    public static void loadImageLoadingFade(String img, ImageView imgview, LottieAnimationView lt){
        Picasso.get().load(img).fetch(new Callback(){
            @Override
            public void onSuccess() {
                imgview.setAlpha(0f);
                Picasso.get().load(img).fit().into(imgview);
                lt.setVisibility(View.GONE);
                imgview.animate().setDuration(1000).alpha(1f).start();
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }

    public static void loadImageLoadingFade(int img, ImageView imgview, LottieAnimationView lt){
        Picasso.get().load(img).fetch(new Callback(){
            @Override
            public void onSuccess() {
                imgview.setAlpha(0f);
                Picasso.get().load(img).fit().into(imgview);
                lt.setVisibility(View.GONE);
                imgview.animate().setDuration(1000).alpha(1f).start();
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }

    public static void loadImageNoFitFade(String img, ImageView imgview, LottieAnimationView lt){
        Picasso.get().load(img).fetch(new Callback(){
            @Override
            public void onSuccess() {
                imgview.setAlpha(0f);
                Picasso.get().load(img).into(imgview);
                lt.setVisibility(View.GONE);
                imgview.animate().setDuration(1000).alpha(1f).start();
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }

    public static void loadImageWithLoading(String img, ImageView imgview, LottieAnimationView lt, boolean flag){
        // imgview.getRootView();
        //  Log.e("MAIN", "loadImageWithLoading: "+imgview.getRootView().getClass().getSimpleName());
     //   lt.setVisibility(View.VISIBLE);
        imgview.setVisibility(View.VISIBLE);
        Picasso.get().load(Uri.parse(img)).fit().into(imgview, new Callback() {
            @Override
            public void onSuccess() {
                if(flag)
                Log.e("MAIN", "onSuccess: ay wey" );
                imgview.setVisibility(View.VISIBLE);
                lt.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {
                lt.setVisibility(View.GONE);
            }
        });
    }

    public static void loadImageWithLoadingRounded(String img, int radius, ImageView imgview, LottieAnimationView lt){
        // imgview.getRootView();
        //  Log.e("MAIN", "loadImageWithLoading: "+imgview.getRootView().getClass().getSimpleName());
        imgview.setVisibility(View.VISIBLE);
        lt.setVisibility(View.VISIBLE);
       Picasso.get().load(Uri.parse(img)).transform(new RoundedCornersTransformation(radius, 0)).fit().into(imgview, new Callback() {
            @Override
            public void onSuccess() {
                imgview.setVisibility(View.VISIBLE);
                lt.setVisibility(View.GONE);
            }

            @Override
            public void onError(Exception e) {

            }
        });

}
}
