package com.na.dragtest.pit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nadav Amami on 25/11/2017.
 * A custom view that renders a pit point
 */

public class PitPointView extends View implements IPoint{
    private Paint mPaint;
    private int mColor = Color.RED;
    private int mX = 0;
    private int mY = 0;
    private static final int SIZE = 60;
    private static final int RAD = 30;

    public PitPointView(Context context) {
        super(context);
        init();
    }

    public PitPointView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public PitPointView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(mX,mY,RAD,mPaint);
    }

    /* initialize drawing objects*/
    private void init(){
       mPaint =  new Paint(Paint.ANTI_ALIAS_FLAG);
       mPaint.setStyle(Paint.Style.FILL);
       mPaint.setColor(mColor);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(SIZE,SIZE);
    }

    @Override
    public void setPosition(int x, int y) {
        mX = x;
        mY = y;
        Pit.LayoutParams layoutParams = new Pit.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        layoutParams.height = SIZE;
        layoutParams.width = SIZE;
        layoutParams.x = mX;
        layoutParams.y = mY;
        setLayoutParams(layoutParams);
//        setX(mX);
//        setY(mY);
        invalidate();
    }

}
