package com.example.svndemo;

import android.content.Intent;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.qf.kenlibary.base.BaseActivity;

import java.util.Timer;
import java.util.concurrent.Callable;

/**
 * Created by 星期八 on 2017/1/13.
 */
public class Welcome_Activity extends BaseActivity {

    @Override
    protected int getContentId() {
        return R.layout.welcome_layout;
    }

    @Override
    protected void loadDatas() {
        //使用动画效果
        ImageView imageView= (ImageView) findViewById(R.id.image_view);
        Animation animation=new ScaleAnimation(1.0f, 1.2f, 1.0f,1.2f,ScaleAnimation.RELATIVE_TO_SELF,
                0.5f,ScaleAnimation.RELATIVE_TO_SELF, 0.5f);

        animation.setDuration(2000);
        imageView.setAnimation(animation);

        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //跳转到第二个界面
                Intent intent=new Intent(Welcome_Activity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
