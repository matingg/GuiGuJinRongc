package com.mashaoting.guigujinrong.ui;


import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.mashaoting.guigujinrong.R;

/**
 * Created by 麻少亭 on 2017/3/13.
 */

public class MyProgress extends View {



    private int measuredHeight;
    private int measuredWidth;
    private int roundWidth = 10;//圆环的宽度
    private Paint paint;
    private int roundColor; //圆环的颜色
    private int sweepArc = 60;
    private int sweepColor = Color.RED; //进度的颜色

    public MyProgress(Context context) {
        super(context);
        init();
    }

    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(); //在布局配置该控件的时候会调用该构造器
        //自定义属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.progress);
        //第一个参数获取attrs里面的配置属性名，第二个参数设置默认值
        roundColor = typedArray.getColor(R.styleable.progress_roundColor,Color.GRAY);
        sweepColor  = typedArray.getColor(R.styleable.progress_sweepColor,Color.RED);
        sweepArc = typedArray.getInteger(R.styleable.progress_sweepArc,0);
        typedArray.recycle();
    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);

    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        int cx = measuredWidth / 2 ;
        int cy = measuredHeight / 2 ;

        int radius = cx - roundWidth / 2 ;//控件的宽度 圆环宽度的一半

        paint.setColor(roundColor);//设置圆环的颜色

        paint.setStrokeWidth(roundWidth);//设置圆环的宽度

        //前两个参数是圆心坐标  第三个参数半径 第四个参数是画笔
        canvas.drawCircle(cx , cy , radius , paint);

        RectF rectF = new RectF(roundWidth / 2 ,roundWidth / 2 ,
                measuredWidth - roundWidth / 2 , measuredHeight - roundWidth /2 );

        paint.setColor(sweepColor);

        canvas.drawArc(rectF , 0 , sweepArc * 360 / 100 , false , paint);

        //画文字
        String text = sweepArc + "%";

        Rect rect = new Rect();

        paint.setColor(Color.BLUE);

        paint.setStrokeWidth(0);

        paint.setTextSize(30);

        paint.getTextBounds(text , 0 , text.length() , rect);

        float tx = measuredWidth / 2 - rect.width() / 2;
        float ty = measuredHeight / 2 + rect.height() / 2;
        canvas.drawText(text, tx, ty, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        measuredHeight = getMeasuredHeight(); //控件的高
        measuredWidth = getMeasuredWidth(); //控件的宽
    }

    public void setProgress(int progress) {
        sweepArc = progress;
        /*
        * invalidate 是在主线程强制刷新
        * postinvalidate 是在分线程强制刷新
        * */
        postInvalidate();
    }
}
