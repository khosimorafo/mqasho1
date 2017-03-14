package com.feerlaroc.widgets.view;

import android.app.FragmentManager;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

import com.feerlaroc.widgets.R;

public abstract class DataBite extends View
        implements View.OnClickListener {

    private static final float DEFAULT_TEXT_SIZE = 28;
    private static final int DEFAULT_TEXT_COLOR = Color.DKGRAY;

    private static final float DEFAULT_LABEL_TEXT_SIZE = 12;
    private static final int DEFAULT_LABEL_TEXT_COLOR = Color.DKGRAY;

    private int mWidth = 200; //Default width of view
    private int mHeight = 60; //Default height of view

    private float mTextSize;
    private int mTextColor;
    private Rect mTextBounds = new Rect();
    private Point mTextDimens = new Point(0, 0);
    private TextPaint mTextPaint;

    private boolean mShowLabel = true;
    private int mLabelPosition = 0;
    private float mLabelTextSize;
    private int mLabelTextColor;
    private Rect mLabelBounds = new Rect();
    private Point mLabelDimens = new Point(0, 0);
    private TextPaint mLabelPaint;


    private int mInternalPadding = 8; //Padding between labels and text

    private int mTextHeight = 0;
    private int mTextWidth = 0;

    private int mLabelHeight = 0;
    private int mLabelWidth = 0;

    private String mValue = "-";
    private String mLabel = "Label";

    private FragmentManager mFragmentManager;
    protected Context mContext;

    public DataBite(Context context) {

        super(context);
        init(context, null, 0, 0);
    }

    public DataBite(Context context, AttributeSet attrs) {

        super(context, attrs);
        init(context, attrs, 0, 0);
    }

    public DataBite(Context context, AttributeSet attrs, int defStyleAttr) {

        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr, 0);
    }


    protected void init(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {

        final Resources res = context.getResources();

        // Set defaults
        mTextColor = DEFAULT_TEXT_COLOR;
        mTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_TEXT_SIZE,
                res.getDisplayMetrics());

        mLabelTextColor  = DEFAULT_LABEL_TEXT_COLOR;
        mLabelTextSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, DEFAULT_LABEL_TEXT_SIZE,
                res.getDisplayMetrics());

        // Set the view attributes from XML or from default values defined in this class
        final TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.DataBite,
                defStyleAttr, defStyleRes);

        mTextSize = attributes.getDimension(R.styleable.DataBite_bite_text_size, mTextSize);
        mTextColor = attributes.getColor(R.styleable.DataBite_bite_text_color, mTextColor);

        mLabelTextSize = attributes.getDimension(R.styleable.DataBite_bite_label_text_size, mLabelTextSize);
        mLabelTextColor = attributes.getColor(R.styleable.DataBite_bite_label_text_color, mLabelTextColor);

        mShowLabel = attributes.getBoolean(R.styleable.DataBite_bite_show_label, mShowLabel);



        attributes.recycle();

        mTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        mLabelPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG);
        mLabelPaint.setTextSize(mLabelTextSize);
        mLabelPaint.setColor(mLabelTextColor);
        mLabelPaint.setTextAlign(Paint.Align.LEFT);
        mLabelPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));

    }


    public void setValue(String value){

        mValue = value;
        invalidate();
    }

    public void setLabel(String label){

        mLabel = label;
        invalidate();
    }

    private void drawValue(Canvas canvas){

        mLabelPaint.getTextBounds(mLabel, 0, mLabel.length(), mLabelBounds);

        float y = getPaddingTop() + mLabelBounds.bottom + mLabelBounds.height() + mInternalPadding;
        float x = getPaddingLeft();

        canvas.drawText(mValue, x, mHeight, mTextPaint);
    }

    private void drawLabel(Canvas canvas){

        float y = getPaddingTop() + mLabelBounds.height();
        float x = getPaddingLeft();

        canvas.drawText(mLabel, x, y, mLabelPaint);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        if(mShowLabel)
            drawLabel(canvas);

        drawValue(canvas);
    }

    private void setBounds(){

        mTextPaint.getTextBounds(mValue, 0, mValue.length(), mTextBounds);
        mLabelPaint.getTextBounds(mLabel, 0, mLabel.length(), mLabelBounds);

        mHeight = getPaddingTop() + mTextBounds.height() + mLabelBounds.height() + getPaddingBottom();
    }

    /*
	 * Determining view size based on constraints from parent views
	 *
	 * (non-Javadoc)
	 * @see android.view.View#onMeasure(int, int)
	 */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        //Get size requested and size mode
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int width, height = 0;

        setBounds();
        //Determine Width
        switch(widthMode){
            case MeasureSpec.EXACTLY:
                width = widthSize;
                break;
            case MeasureSpec.AT_MOST:
                width = Math.min(mWidth, widthSize);
                break;
            case MeasureSpec.UNSPECIFIED:
            default:

                width = mWidth;
                break;
        }

        //Determine Height
        switch(heightMode){
            case MeasureSpec.EXACTLY:
                height = heightSize;
                break;
            case MeasureSpec.AT_MOST:
                height = Math.min(mHeight, heightSize);
                break;
            case MeasureSpec.UNSPECIFIED:
            default:
                //setBounds();
                height = mHeight;
                break;
        }

        setMeasuredDimension(width, height);
    }

    /*
     * Called after onMeasure, returning the actual size of the view before drawing.
     *
     * (non-Javadoc)
     * @see android.view.View#onSizeChanged(int, int, int, int)
     */
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        //Change height and width of draw rect to size of view
        //mDrawRect is using to draw the wheel to the view's canvas,
        //setting mDrawRect to the width and height of the view ensures
        //the wheel is drawn correctly to the view
        mWidth = w; mHeight = h;
    }

    public static int getApproxXToCenterText(String text, Typeface typeface, int fontSize, int widthToFitStringInto) {
        Paint p = new Paint();
        p.setTypeface(typeface);
        p.setTextSize(fontSize);
        float textWidth = p.measureText(text);
        int xOffset = (int)((widthToFitStringInto-textWidth)/2f) - (int)(fontSize/2f);
        return xOffset;
    }

    public void setFragmentManager(FragmentManager manager){

        mFragmentManager = manager;
    }

    public FragmentManager getFragmentManager(){

        return mFragmentManager;
    }
}
