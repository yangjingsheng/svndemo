package com.qf.kenlibary.widget.slidinglabel;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by ken on 2016/12/27.
 * 中间显示选中字母的标签控件
 */
public class LabelView extends View {

    private Paint backgroundPaint, labelPaint;
    private int radiuo = 60;
    private String text;//需要绘制的文本

    public LabelView(Context context) {
        this(context, null);
    }

    public LabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        //绘制背景圆圈
        backgroundPaint = new Paint();
        backgroundPaint.setAntiAlias(true);
        backgroundPaint.setColor(Color.RED);

        //绘制中间的选中字母
        labelPaint = new Paint();
        labelPaint.setAntiAlias(true);
        labelPaint.setColor(Color.WHITE);
        labelPaint.setTextSize(80);
    }

    /**
     * 提供一个方法供外界调用，用于设置显示的文本内容
     * @param text
     */
    public void setText(String text){
        this.text = text;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(text != null) {
            canvas.drawCircle(getWidth()/2, getHeight()/2, radiuo, backgroundPaint);
            canvas.drawText(text,
                    getWidth()/2 - labelPaint.measureText(text)/2,
                    getHeight()/2 + (labelPaint.descent() - labelPaint.ascent())/2 - labelPaint.descent(),
                    labelPaint);
        }
    }
}
