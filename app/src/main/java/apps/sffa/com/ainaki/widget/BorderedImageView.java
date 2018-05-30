package apps.sffa.com.ainaki.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

import apps.sffa.com.ainaki.R;

/**
 * Created by mr-code on 12/12/2017.
 */

public class BorderedImageView extends AppCompatImageView {

    private Paint circlePaint;
    private Paint textPaint;

    private static final int STROKE_WIDTH = 2;

    private int percent = 100;
    private int startAngel = 0;


    public BorderedImageView(Context context) {
        super(context);
        initialize(context);
    }


    public BorderedImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initialize(context);
    }


    public BorderedImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initialize(context);
    }


    public void setProgress(int value) {
        percent = value;
        postInvalidate();
    }

    public void setStartAngel(int value) {
        startAngel = value;
        postInvalidate();
    }


    private void initialize(Context context) {
        circlePaint = new Paint();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            circlePaint.setColor(getResources().getColor(R.color.colorPrimary));
        } else {
            circlePaint.setColor(getResources().getColor(R.color.colorPrimary, null));
        }
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(STROKE_WIDTH);
        circlePaint.setStyle(Paint.Style.STROKE);

        textPaint = new Paint();
        textPaint.setColor(Color.parseColor("#212121"));
        textPaint.setAntiAlias(true);
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTextSize(16);
        textPaint.setStyle(Paint.Style.FILL_AND_STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rect = new RectF();
        rect.left = 0 + STROKE_WIDTH;
        rect.right = getWidth() - STROKE_WIDTH;
        rect.top = 0 + STROKE_WIDTH;
        rect.bottom = getHeight() - STROKE_WIDTH;

        //canvas.drawCircle(getWidth() / 2, getHeight() / 2, getWidth() / 2, circlePaint);
        int sweepAngle = percent * 360 / 100;



        canvas.drawArc(rect, startAngel, sweepAngle, false, circlePaint);
    }
}