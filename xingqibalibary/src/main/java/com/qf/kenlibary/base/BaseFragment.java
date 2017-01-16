package com.qf.kenlibary.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.qf.kenlibary.R;
import com.qf.kenlibary.util.ScreenUtil;

/**
 * Created by ken on 2016/12/26.
 */
public abstract class BaseFragment  extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getContentId(), container, false);

        //沉浸式状态栏
        Activity activity = getActivity();
        if(activity instanceof BaseActivity){
            BaseActivity baseActivity = (BaseActivity) activity;
            if(baseActivity.isOpenStatus()){
                View actionbar = view.findViewById(R.id.actionbar);
                if(actionbar != null){
                    int statusHeight = ScreenUtil.getStatusHeight(activity);
                    if(statusHeight != -1){
                        actionbar.setPadding(0, statusHeight, 0, 0);
                    }
                }
            }
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        init(view);
        loadDatas();
        Bundle bundle = getArguments();
        if(bundle!=null){
            getData(bundle);
        }
    }

    protected void getData(Bundle bundle) {
    }


    /**
     * 初始化方法
     * @param view
     */
    protected void init(View view) {
    }

    /**
     * 加载数据
     */
    protected void loadDatas() {
    }

    protected abstract int getContentId();

    protected <T> T findViewByIds(View view, int id){
        return (T)view.findViewById(id);
    }

}
