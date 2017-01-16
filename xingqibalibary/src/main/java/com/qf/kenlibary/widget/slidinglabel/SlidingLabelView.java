package com.qf.kenlibary.widget.slidinglabel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;


/**
 * Created by ken on 2016/12/27.
 * 侧边字母标签控件
 */
public class SlidingLabelView extends View{

    //需要绘制的内容
    private String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    /**
     * 未被选中的标签画笔
     */
    private Paint unCheckedLabel;
    private Paint checkedLabel;

    private int index = -1;//表示当前选中的字母下标

    private OnSelecterListener onSelecterListener;

    public OnSelecterListener getOnSelecterListener() {
        return onSelecterListener;
    }

    public void setOnSelecterListener(OnSelecterListener onSelecterListener) {
        this.onSelecterListener = onSelecterListener;
    }

    /**
     * 当需要new这个自定义控件时，调用该构造方法
     * @param context
     */
    public SlidingLabelView(Context context) {
        this(context, null);
    }

    /**
     * 当自定义控件以标签的形式写到布局上时，会调用这个构造方法
     * @param context
     * @param attrs
     */
    public SlidingLabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    /**
     * 当自定义控件以标签的形式写到布局上时，并且包含一个style属性时，调用该构造方法
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlidingLabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 初始化
     */
    private void init() {
        unCheckedLabel = new Paint();
        unCheckedLabel.setAntiAlias(true);//抗锯齿
        unCheckedLabel.setColor(Color.parseColor("#88888888"));
        unCheckedLabel.setTextSize(50);

        checkedLabel = new Paint();
        checkedLabel.setAntiAlias(true);//抗锯齿
        checkedLabel.setColor(Color.parseColor("#ff0000"));
        checkedLabel.setTextSize(50);
    }

    /**
     * 文本的宽度：绘制该文本的画笔.measureText(需要测量的文本)
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {
        int width = getWidth();//获得控件宽度

        for(int i = 0; i < labels.length; i++){

            if(i == index){
                //当前这个字母是被选中的
                canvas.drawText(labels[i],
                        width/2 - checkedLabel.measureText(labels[i])/2,
                        (checkedLabel.descent() - checkedLabel.ascent()) * (i + 1),
                        checkedLabel);
            } else {
                canvas.drawText(labels[i],
                        width/2 - unCheckedLabel.measureText(labels[i])/2,
                        (unCheckedLabel.descent() - unCheckedLabel.ascent()) * (i + 1),
                        unCheckedLabel);
            }
        }
    }

    /**
     * 测量方法
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = getMeasureSize(widthMeasureSpec, 0);
        int heigth = getMeasureSize(heightMeasureSpec, 1);
        //保存测量的宽高
        setMeasuredDimension(width, heigth);
    }

    /**
     * 对宽高进行测量的方法
     * @param spec 高度或者宽度的空间值
     * @param type 0 - 宽度 1 - 高度
     * @return
     */
    private int getMeasureSize(int spec, int type){
        int model = MeasureSpec.getMode(spec);
        int size = MeasureSpec.getSize(spec);

        switch (model){
            case MeasureSpec.EXACTLY:
                return size;
            case MeasureSpec.AT_MOST:
                switch (type){
                    case 0:
                        //测量宽度
                        return (int) unCheckedLabel.measureText(labels[0]) + getPaddingLeft() + getPaddingRight();//测量其中最宽的一个文本宽度作为控件的宽度
                    case 1:
                        //测量高度
                        return (int) ((unCheckedLabel.descent() - unCheckedLabel.ascent()) * labels.length) + getPaddingTop() + getPaddingBottom();
                }
                break;
            case MeasureSpec.UNSPECIFIED:
                break;
        }
        return -1;
    }

    /**
     * 事件处理方法
     * @param event
     * @return 返回true表示消费了该事件
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                touch(event);
                break;
            case MotionEvent.ACTION_UP:
                index = -1;
                invalidate();

                //抬起手指调用回调方法
                if(onSelecterListener != null){
                    onSelecterListener.eventup();
                }
                break;
        }
        return true;
    }

    private void touch(MotionEvent event){
        int clicky = (int) event.getY();
        //获得当前点击的字母下标
        int index = (int) (clicky / (unCheckedLabel.descent() - unCheckedLabel.ascent()));

        if(index < 0){
            index = 0;
        }

        if(index >= labels.length){
            index = labels.length - 1;
        }

        /**
         * 如果当前的选中的index是已经被选中的，则不做任何处理
         */
        if(index != this.index){
            this.index = index;

            //重绘
            invalidate();

            //调用选中的回调方法
            if(onSelecterListener != null){
                onSelecterListener.selector(labels[index], index);
            }
        }
    }

    public interface OnSelecterListener{
        void selector(String text, int position);
        void eventup();
    }
}
