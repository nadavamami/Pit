package com.na.dragtest.pit;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Nadav Amami on 24/11/2017.
 * renders points and connects them  with lines according to their x coordinate
 *
 */

public class Pit extends ViewGroup{
    private Paint mPaint;
    private int mColor = Color.BLUE;
    private static  final String TAG = Pit.class.getSimpleName();

    public Pit(Context context) {
        super(context);
        init();
    }

    public Pit(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Pit(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int count = getChildCount();

        int maxHeight = 0;
        int maxWidth = 0;

        // Find out how big everyone wants to be
        measureChildren(widthMeasureSpec, heightMeasureSpec);

        // Find rightmost and bottom-most child
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            if (child.getVisibility() != GONE) {
                int childRight;
                int childBottom;

                Pit.LayoutParams lp
                        = (Pit.LayoutParams) child.getLayoutParams();

                childRight = lp.x + child.getMeasuredWidth();
                childBottom = lp.y + child.getMeasuredHeight();

                maxWidth = Math.max(maxWidth, childRight);
                maxHeight = Math.max(maxHeight, childBottom);
            }
        }

        // Account for padding too
        maxWidth += getPaddingLeft() + getPaddingRight();
        maxHeight += getPaddingTop() + getPaddingBottom();

        // Check against minimum height and width
        maxHeight = Math.max(maxHeight, getSuggestedMinimumHeight());
        maxWidth = Math.max(maxWidth, getSuggestedMinimumWidth());

        setMeasuredDimension(resolveSizeAndState(maxWidth, widthMeasureSpec, 0),
                resolveSizeAndState(maxHeight, heightMeasureSpec, 0));
    }

    @Override
    protected void onLayout(boolean b, int i, int i1, int i2, int i3) {
        Log.d(TAG,"onLayout");
        int count = getChildCount();

        for (int index = 0; index < count; index++) {
            View child = getChildAt(index);
            if (child.getVisibility() != GONE) {

                Pit.LayoutParams lp =
                        (Pit.LayoutParams) child.getLayoutParams();

                int childLeft = getPaddingLeft() + lp.x;
                int childTop = getPaddingBottom() + lp.y;
                child.layout(childLeft, childTop,
                        childLeft + child.getMeasuredWidth(),
                        childTop + child.getMeasuredHeight());

            }
        }
    }

    /*
    * initialize drawing objects
     */
    private void init(){
        setWillNotDraw(false);
        mPaint =  new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(mColor);
//        setMotionEventSplittingEnabled(true);
    }

    /*
    * draws the origin axis lines
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int hy = getMeasuredHeight() / 2;
        int vx = getMeasuredWidth() / 2;

        canvas.drawLine(0,hy,getMeasuredWidth(),hy,mPaint);
        canvas.drawLine(vx,0,vx,getMeasuredHeight(),mPaint);
    }

    /*
    * draw line between all the points children
     */
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        Log.d(TAG,"dispatch draw");
        int childCount = getChildCount();
        Path path = new Path();
        path.moveTo(getChildAt(0).getX(),getChildAt(0).getY());
        for (int i = 1; i < childCount; i++){
            View curChild  = getChildAt(i);
            path.lineTo(curChild.getX(),curChild.getY());

        }
        canvas.drawPath(path,mPaint);
    }



    public static class LayoutParams extends ViewGroup.LayoutParams{

        public int x;

        public int y;

        public LayoutParams(int width, int height, int x, int y) {
            super(width, height);
            this.x = x;
            this.y = y;
        }

        public LayoutParams(Context c, AttributeSet attrs) {
            super(c, attrs);
        }

        public LayoutParams(int width, int height) {
            super(width, height);
        }

        public LayoutParams(ViewGroup.LayoutParams source) {
            super(source);
        }
    }
}
