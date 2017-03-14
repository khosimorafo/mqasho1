package com.feerlaroc.widgets.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by root on 2016/09/11.
 */

public class PinItem extends View {

    private String mTopText = "M YYYY";
    private String mBottomText = "";

    @StringRes
    private int mLabelResource;

    private Drawable mDrawable;
    @DrawableRes
    private int mDrawableResource;

    private Rect mTextTopRectangle = new Rect();
    private Rect mIconRectangle = new Rect();
    private Rect mTextBottomRectangle = new Rect();

    private Canvas mCanvas;

    private Paint mIconPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Paint mTextPaint = new Paint(TextPaint.ANTI_ALIAS_FLAG);

    private float mDensity;

    private String mDBKey;

    public PinItem(Context context) {

        super(context);
        init();
    }

    public PinItem(Context context, AttributeSet attrs) {

        super(context, attrs);
        init();
    }

    public PinItem(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        init();
    }

    private void init(){

        mDensity = getResources().getDisplayMetrics().density;
    }

    @Override
    protected void onDraw(Canvas canvas) {

        super.onDraw(canvas);
        mCanvas = canvas;

        measureDrawAreas();

        drawTopText();
        drawIcon();
        drawBottomText();
    }


    private void drawTopText(){

        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(20);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        int xPosition = ((mTextTopRectangle.width())/2) + getPaddingRight();

        mCanvas.drawText(mTopText, xPosition, mTextTopRectangle.bottom, mTextPaint);
    }

    private void drawIcon(){

        Bitmap bmp = BitmapFactory.decodeResource(getResources(),
                mDrawableResource);

        int xPosition = ((mIconRectangle.width() - bmp.getWidth())/2) + getPaddingRight();
        int yPosition = ((mIconRectangle.height() - bmp.getHeight())/2) + getPaddingTop();
        mCanvas.drawBitmap(bmp, xPosition, yPosition, mIconPaint);
    }

    private void drawBottomText(){

        mTextPaint.setColor(Color.BLACK);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(20);
        mTextPaint.setTextAlign(Paint.Align.CENTER);

        int xPosition = ((mTextBottomRectangle.width())/2) + getPaddingRight();

        mCanvas.drawText(mBottomText, xPosition, mTextBottomRectangle.bottom, mTextPaint);
    }

    private void measureDrawAreas(){

        final int width = getWidth();
        final int height = getHeight();

        final int topHeight = (int) (height * 0.1);

        final int iconBottom = (int) (height * 0.65);
        //final int iconBottom = (int)iconHeight;

        mIconRectangle = new Rect(getPaddingLeft(), getPaddingTop()
                , width - getPaddingRight(), iconBottom);

        mTextTopRectangle = new Rect(getPaddingLeft(), mIconRectangle.bottom + 2
                , width - getPaddingRight(), mIconRectangle.bottom + topHeight);

        mTextBottomRectangle = new Rect(getPaddingLeft(), mTextTopRectangle.bottom + 2
                , width - getPaddingRight(), height - getPaddingBottom());


    }

    // Private Methods /////////////////////////////////////////////////////////////////

    //Set text size based on available pin width.
    private void calibrateTextSize(Paint paint, String text, float boxWidth) {
        paint.setTextSize(20);

        float textSize = paint.measureText(text);
        float estimatedFontSize = boxWidth * 8 / textSize / mDensity;

        paint.setTextSize(estimatedFontSize * mDensity);
    }

    public void setBottomText(@StringRes int labelRes) {

        this.mLabelResource = labelRes;
        this.mBottomText = "";
    }

    public void setTopText(String str) {

        this.mTopText = str;
    }

    public void setBottomText(String str) {

        this.mBottomText = str;
    }

    public void setDrawable(@DrawableRes int drawableRes) {

        this.mDrawableResource = drawableRes;
        this.mDrawable = null;
    }

    public void setDrawable(Drawable drawable) {

        this.mDrawable = drawable;
    }

    public void setDBKey(String key){

        mDBKey = key;
    }

    public String getDBKey(){

        return mDBKey;
    }
}
