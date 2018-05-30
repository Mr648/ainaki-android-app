package layout;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;

import apps.sffa.com.ainaki.R;


public class CircularProductViewerFragment extends Fragment implements SurfaceHolder.Callback {

    public CircularProductViewerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private SurfaceView images;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        images = (SurfaceView) view.findViewById(R.id.surface);
        images.getHolder().addCallback(this);

//        images.onTouchEvent(new MotionEvent(){
//
//        })

    }

//
//    DisplayMetrics getDisplayMetrics() {
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//        return dm;
//    }
//

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_circular_product_viewer, container, false);


    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        tryDrawing(holder);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int frmt, int w, int h) {
        tryDrawing(holder);
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
    }

    private void tryDrawing(SurfaceHolder holder) {

        Canvas canvas = holder.lockCanvas();
        if (canvas != null) {
            drawMyStuff(canvas);
            holder.unlockCanvasAndPost(canvas);
        }
    }

    CountDownTimer timer;

    private void drawMyStuff(final Canvas canvas) {
        canvas.drawRGB(255, 255, 255);
        final Paint circlePaint;
        circlePaint = new Paint();
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            circlePaint.setColor(getResources().getColor(R.color.colorAccent));
        } else {
            circlePaint.setColor(getResources().getColor(R.color.colorAccent, null));
        }
        circlePaint.setAntiAlias(true);
        circlePaint.setStrokeWidth(5);
        circlePaint.setStyle(Paint.Style.FILL_AND_STROKE);

        RectF bounds = new RectF(canvas.getClipBounds());
        final float centerX = bounds.centerX();
        final float centerY = bounds.centerY();


        final Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.ic_sunglass);
//        Paint p = new Paint();
//        p.setAntiAlias(true);
//        p.setFilterBitmap(true);
        canvas.drawCircle(centerX, centerY, 64, circlePaint);
        canvas.drawBitmap(bmp, (centerX - bmp.getWidth() / 2), (centerY - bmp.getHeight() / 2), null);

//        bmp = getResizedBitmap(bmp, 48, 48);

        float radius = 32.0f;
        for (int i = 0; i <= 360; i += 45) {
            float angleDeg = i;
            float xPos = 150 * (float) Math.cos(Math.toRadians(angleDeg)) + centerX;
            float yPos = 150 * (float) Math.sin(Math.toRadians(angleDeg)) + centerY;
            circlePaint.setColor(Color.rgb(((int) (Math.random() * 255)), ((int) (Math.random() * 255)), ((int) (Math.random() * 255))));
            canvas.drawCircle(xPos, yPos, radius, circlePaint);
            canvas.drawBitmap(bmp, (xPos - bmp.getWidth() / 2), (yPos - bmp.getHeight() / 2), null);
        }
        timer = new CountDownTimer(5000, 20) {

            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                try {
                    drawCircular(circlePaint, canvas, bmp, centerX, centerY);
                } catch (Exception e) {
                }
            }
        }.start();


    }

    private void drawCircular(Paint circlePaint, Canvas canvas, Bitmap image, float x, float y) {

        timer.start();
    }

    public Bitmap getResizedBitmap(Bitmap bm, int newWidth, int newHeight) {
        int width = bm.getWidth();
        int height = bm.getHeight();
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // CREATE A MATRIX FOR THE MANIPULATION
        Matrix matrix = new Matrix();
        // RESIZE THE BIT MAP
        matrix.postScale(scaleWidth, scaleHeight);

        // "RECREATE" THE NEW BITMAP
        Bitmap resizedBitmap = Bitmap.createBitmap(
                bm, 0, 0, width, height, matrix, false);
        bm.recycle();
        return resizedBitmap;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }


}
