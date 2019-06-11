package com.hzp.zhbj.activity;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.RelativeLayout;

import com.hzp.zhbj.R;
import com.hzp.zhbj.activity.guide.GuideActivity;
import com.hzp.zhbj.activity.home.HomeActivity;
import com.hzp.zhbj.utils.Constants;
import com.hzp.zhbj.utils.SharedPreferencesTool;

public class SplashActivity extends Activity {

    private RelativeLayout mRoot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash );
        initView();
    }
    /**
     * 初始化控件
     *
     * 2016-11-1 上午10:26:37
     */
    private void initView() {
        mRoot = (RelativeLayout) findViewById(R.id.splash_rel_root);

        //执行动画的操作
        setAnimation();
    }

    /**
     * 执行界面动画
     *
     */
    private void setAnimation() {
        //旋转
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(1000);
        rotateAnimation.setFillAfter(true);//保持动画结束的状态
        //缩放
        //fromX : 起始控件的尺寸
        //toX : 结束控件的尺寸
        ScaleAnimation scaleAnimation = new ScaleAnimation(0, 1, 0, 1, Animation.RELATIVE_TO_SELF, 0.5f,Animation.RELATIVE_TO_SELF, 0.5f);
        scaleAnimation.setDuration(1000);
        scaleAnimation.setFillAfter(true);
        //渐变
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.0f, 1.0f);//透明到不透明的效果
        alphaAnimation.setDuration(2000);
        alphaAnimation.setFillAfter(true);
        //组合动画
        //shareInterpolator : 是否使用同一动画插补器，true使用;false:不使用，各自使用各自的
        AnimationSet animationSet = new AnimationSet(false);
        animationSet.addAnimation(rotateAnimation);//添加动画
        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);

        //执行动画
        mRoot.startAnimation(animationSet);

        //动画执行结束，跳转到引导界面/首页
        //监听动画的执行操作
        animationSet.setAnimationListener(animationListener);
    }

    /**动画的监听**/
    private AnimationListener animationListener = new AnimationListener() {

        //动画开始的调用
        @Override
        public void onAnimationStart(Animation animation) {
            // TODO Auto-generated method stub

        }

        //动画重复执行调用
        @Override
        public void onAnimationRepeat(Animation animation) {
            // TODO Auto-generated method stub

        }

        //动画结束调用的
        @Override
        public void onAnimationEnd(Animation animation) {
            enter();
        }
    };

    /**
     * 跳转界面的操作
     *
     */
    private void enter() {
        //跳转引导界面/首页
        //问题：如何知道跳转到首页还是引导界面
        //跟手机卫士中手机防盗的跳转操作思路，获取是否是第一次进入的操作，如果是第一次进入，跳转到引导页面，如果不是第一次进入，跳转到首页
        boolean b = SharedPreferencesTool.getBoolean(SplashActivity.this,Constants.ISFIRSTENTER, true);
        //根据是否是第一次进入的状态，实现跳转不同的界面
        if (b) {
            //第一次进入，跳转到引导界面
            Intent intent = new Intent(SplashActivity.this,GuideActivity.class);
            startActivity(intent);
        }else{
            //不是第一次进入，跳转到首页
            Intent intent = new Intent(SplashActivity.this,HomeActivity.class);
            startActivity(intent);
        }
        //跳转界面完成，移除Splash界面
        finish();
    }
}
