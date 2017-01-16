package com.example.svndemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.RadioGroup;

import com.qf.kenlibary.base.BaseActivity;

import butterknife.Bind;

/**
 * 这是第一界面
 */

public class MainActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {

    private static final String TAG ="print" ;

  private RadioGroup radioGroup;
    @Override
    protected int getContentId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
       radioGroup= (RadioGroup) findViewById(R.id.gr_group);
    }

    @Override
    protected void loadDatas() {
        radioGroup.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i){
            case R.id.rb_home:

                break;
            case R.id.rb_shop:
                break;

            case R.id.rb_shop_car:
                break;

            case R.id.rb_my:
                break;
        }
    }
}
