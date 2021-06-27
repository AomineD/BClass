package com.wineberryhalley.bclassapp.captcha;

import android.animation.Animator;
import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.wineberryhalley.bclassapp.R;
import com.wineberryhalley.bclassapp.RevealAnimationSetting;

import static androidx.core.content.ContextCompat.getColor;
import static com.wineberryhalley.bclassapp.BaseFragment.registerCircularRevealAnimation;

public class CaptchaRevealer extends RelativeLayout {
    public CaptchaRevealer(Context context) {
        super(context);
        config();
    }

    public CaptchaRevealer(Context context, AttributeSet attrs) {
        super(context, attrs);
        config();
    }

    public CaptchaRevealer(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        config();
    }

    public CaptchaRevealer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    private View child;
    private void config(){

        if(getContext() == null){
            return;
        }


        child = LayoutInflater.from(getContext()).inflate(R.layout.zca_rv, null);

        addView(child);
        setVisibility(INVISIBLE);



    }

    private String TAG = "MAIN";
    private AppCompatActivity appCompatActivity;
    private WineCaptcha.CaptchaListener l;
    
    public void reveal(AppCompatActivity appCompatActivity, WineCaptcha.CaptchaListener listener){
        if(getParent() == null || !(getParent() instanceof ViewGroup)){
            return;
        }
        this.appCompatActivity = appCompatActivity;
        this.l = listener;
      //  Log.e(TAG, "reveal: va" );

   setLayoutParams(new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));



        View posReveal = (View) getParent();

        post(new Runnable() {
            @Override
            public void run() {
                int colorReveal = ContextCompat.getColor(getContext(), R.color.acctp);

                int x =  (int) (posReveal.getX() + (posReveal.getWidth() / 2));
                int y = (int) (posReveal.getY() + posReveal.getHeight());

              //  Log.e(TAG, "run: "+x+" "+y+" "+getWidth()+" "+getHeight() );

                RevealAnimationSetting revealAnimationSettings = RevealAnimationSetting.with(
                        x,
                        y,
                        getWidth(),
                        getHeight());
                circularReveal(getContext(), CaptchaRevealer.this, revealAnimationSettings, colorReveal, getColor(getContext(), R.color.white));

            }
        });
        

    }


    private void circularReveal(final Context context, final View view, final RevealAnimationSetting revealSettings, final int startColor, final int endColor){
        int cx = revealSettings.getCenterX();
        int cy = revealSettings.getCenterY();
        int width = revealSettings.getWidth();
        int height = revealSettings.getHeight();
        int duration = 1000;//context.getResources().getInteger(android.R.integer.config_mediumAnimTime);

    //    Log.e(TAG, "circularReveal: "+duration );
        //Simply use the diagonal of the view
        float finalRadius = (float) Math.sqrt(width * width + height * height);
        Animator anim = ViewAnimationUtils.createCircularReveal(view, cx, cy, 0, finalRadius).setDuration(duration);
        anim.setInterpolator(new FastOutSlowInInterpolator());
        anim.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
                view.setVisibility(VISIBLE);
                showCaptcha();
            }

            @Override
            public void onAnimationEnd(Animator animation) {
               // Log.e("MAIN", "onAnimationEnd: " );
                child.setFocusable(true);
                child.setClickable(true);

            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        anim.start();
        startColorAnimation(view, startColor, endColor, duration);
    }

    void startColorAnimation(final View view, final int startColor, final int endColor, int duration) {
        ValueAnimator anim = new ValueAnimator();
        anim.setIntValues(startColor, endColor);
        anim.setEvaluator(new ArgbEvaluator());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                view.setBackgroundColor((Integer) valueAnimator.getAnimatedValue());
            }
        });
        anim.setDuration(duration);
        anim.start();
    }

    private void showCaptcha(){
        LinearLayout captcha_content = child.findViewById(R.id.capt);

        WineCaptcha.getOn(appCompatActivity).config()
                .show(captcha_content, new WineCaptcha.CaptchaListener() {
                    @Override
                    public void onButtonClick() {
                        l.onButtonClick();
                    }

                    @Override
                    public void onSuccess(String s) {
                        Log.e(TAG, "onSuccess: "+s );
                        hide();

                    }

                    @Override
                    public void onClosed(int i) {
l.onClosed(i);
                    }

                    @Override
                    public void onFailed(String erno) {
l.onFailed(erno);
                    }
                });

    }

    private void hide() {
    postDelayed(new Runnable() {
        @Override
        public void run() {
            YoYo.with(Techniques.FadeOutDown)
            .duration(700)
                    .onEnd(new YoYo.AnimatorCallback() {
                        @Override
                        public void call(Animator animator) {
                            CaptchaRevealer.this.setVisibility(GONE);
                            l.onSuccess("success");
                        }
                    })
            .playOn(CaptchaRevealer.this);
        }
    }, 2000);
    }

}
