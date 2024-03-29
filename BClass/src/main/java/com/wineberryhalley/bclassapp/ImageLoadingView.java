package com.wineberryhalley.bclassapp;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.airbnb.lottie.LottieAnimationView;

public class ImageLoadingView extends RelativeLayout {

    public ImageLoadingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public ImageLoadingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    public ImageLoadingView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(attrs);
    }

    public void showLoading(){
        if(loading_root != null){
            loading_root.setVisibility(VISIBLE);
        }
        if(img != null){
            img.setVisibility(GONE);
        }
    }

    public ImageView getImg(){
        return img;
    }

    public LottieAnimationView getLottie(){
        return loading_root;
    }

    public void hideLoading(){
        if(loading_root != null){
            loading_root.setVisibility(GONE);
        }
        if(img != null){
            img.setVisibility(VISIBLE);
        }
    }

    private String assetName;
    private LottieAnimationView loading_root;
    private ImageView img;
    private void init(AttributeSet attributeSet) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attributeSet,
                R.styleable.ImageLoadingView, 0, 0);
        try {
            //get the text and colors specified using the names in attrs.xml
            assetName = a.getString(R.styleable.ImageLoadingView_assetName);
        } finally {
            a.recycle();
        }

        img = (ImageView) LayoutInflater.from(getContext()).inflate(R.layout.img_root, (ViewGroup) getRootView(), false);

        this.addView(img);

        loading_root = (LottieAnimationView) LayoutInflater.from(getContext()).inflate(R.layout.loading_root, (ViewGroup) getRootView(), false);
if(assetName != null && !assetName.isEmpty()){
    loading_root.setAnimation(assetName);
}
        this.addView(loading_root);


    }

    public void loadImg(String url){
        if(!url.isEmpty() && img != null && loading_root != null)
        PicassoUtils.loadImageWithLoading(url, img, loading_root);
    }

    public void loadImg(int rawFile){
        if(rawFile != 0 && img != null && loading_root != null)
            PicassoUtils.loadImageWithLoading(rawFile, img, loading_root);
    }

    public void loadImgPath(String path){
        if(!path.isEmpty() && img != null && loading_root != null) {
                PicassoUtils.loadImageLoadingPath(path, img, loading_root);
        }
    }

    public void loadImg(String url, boolean faded){
        if(!url.isEmpty() && img != null && loading_root != null) {
         if(!faded)
            PicassoUtils.loadImageWithLoading(url, img, loading_root);
         else
             PicassoUtils.loadImageLoadingFade(url, img, loading_root);
        }
    }

    public void loadImg(int rawFile, boolean faded){
        if(rawFile != 0 && img != null && loading_root != null) {
         if(!faded)
            PicassoUtils.loadImageWithLoading(rawFile, img, loading_root);
         else
             PicassoUtils.loadImageLoadingFade(rawFile, img, loading_root);
        }
    }


    public void loadImgRound(String url, int rounded){
        if(!url.isEmpty() && img != null && loading_root != null)
            PicassoUtils.loadImageWithLoading(url, rounded, img, loading_root);
    }


    public void loadImgRound(int rawFile, int rounded){
        if(rawFile != 0 && img != null && loading_root != null)
            PicassoUtils.loadImageWithLoading(rawFile, rounded, img, loading_root);
    }

    public void playAnim(){
        if(loading_root != null){
            loading_root.playAnimation();
        }
    }


    public void loadImgCircle(int rawFile){
        if(rawFile != 0 && img != null && loading_root != null)
            PicassoUtils.loadCircleImgWithLoading(rawFile, img, loading_root);
    }
    public void loadImgCircle(String url){
        if(!url.isEmpty() && img != null && loading_root != null)
            PicassoUtils.loadCircleImgWithLoading(url, img, loading_root);
    }


}
