package apps.sffa.com.ainaki.widget;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import apps.sffa.com.ainaki.util.AndroidUtilities;

/**
 * Created by mr-code on 6/12/2018.
 */

public class PhotoCropView extends FrameLayout {

    Paint rectPaint = null;
    Paint circlePaint = null;
    Paint halfPaint = null;
    float rectSizeX = 600;
    float rectSizeY = 600;
    float rectX = -1, rectY = -1;
    int draggingState = 0;
    float oldX = 0, oldY = 0;
    int bitmapWidth, bitmapHeight, bitmapX, bitmapY;
    int viewWidth, viewHeight;
    boolean freeform;
    private Bitmap imageToCrop;
    private AndroidUtilities utilities;
    private BitmapDrawable drawable;

    public PhotoCropView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }


    public void setImageToCrop(Bitmap imageToCrop) {
        this.imageToCrop = imageToCrop;
    }

    public void setBitmapDrawable(BitmapDrawable drawable) {
        this.drawable = drawable;
    }

    public void init(Context context) {
        utilities = AndroidUtilities.getInstance(context);
        rectPaint = new Paint();
        rectPaint.setColor(0x3ffafafa);
        rectPaint.setStrokeWidth(utilities.dp(2));
        rectPaint.setStyle(Paint.Style.STROKE);
        circlePaint = new Paint();
        circlePaint.setColor(0xffffffff);
        halfPaint = new Paint();
        halfPaint.setColor(0x220000);

        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                float x = motionEvent.getX();
                float y = motionEvent.getY();

                int cornerSide = utilities.dp(14);

                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    if (rectX - cornerSide < x && rectX + cornerSide > x && rectY - cornerSide < y && rectY + cornerSide > y) {
                        draggingState = 1;
                    } else if (rectX - cornerSide + rectSizeX < x && rectX + cornerSide + rectSizeX > x && rectY - cornerSide < y && rectY + cornerSide > y) {
                        draggingState = 2;
                    } else if (rectX - cornerSide < x && rectX + cornerSide > x && rectY - cornerSide + rectSizeY < y && rectY + cornerSide + rectSizeY > y) {
                        draggingState = 3;
                    } else if (rectX - cornerSide + rectSizeX < x && rectX + cornerSide + rectSizeX > x && rectY - cornerSide + rectSizeY < y && rectY + cornerSide + rectSizeY > y) {
                        draggingState = 4;
                    } else if (rectX < x && rectX + rectSizeX > x && rectY < y && rectY + rectSizeY > y) {
                        draggingState = 5;
                    } else {
                        draggingState = 0;
                    }
                    if (draggingState != 0) {
                        PhotoCropView.this.requestDisallowInterceptTouchEvent(true);
                    }
                    oldX = x;
                    oldY = y;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    draggingState = 0;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE && draggingState != 0) {
                    float diffX = x - oldX;
                    float diffY = y - oldY;
                    if (draggingState == 5) {
                        rectX += diffX;
                        rectY += diffY;

                        if (rectX < bitmapX) {
                            rectX = bitmapX;
                        } else if (rectX + rectSizeX > bitmapX + bitmapWidth) {
                            rectX = bitmapX + bitmapWidth - rectSizeX;
                        }
                        if (rectY < bitmapY) {
                            rectY = bitmapY;
                        } else if (rectY + rectSizeY > bitmapY + bitmapHeight) {
                            rectY = bitmapY + bitmapHeight - rectSizeY;
                        }
                    } else {
                        if (draggingState == 1) {
                            if (rectSizeX - diffX < 160) {
                                diffX = rectSizeX - 160;
                            }
                            if (rectX + diffX < bitmapX) {
                                diffX = bitmapX - rectX;
                            }
                            if (!freeform) {
                                if (rectY + diffX < bitmapY) {
                                    diffX = bitmapY - rectY;
                                }
                                rectX += diffX;
                                rectY += diffX;
                                rectSizeX -= diffX;
                                rectSizeY -= diffX;
                            } else {
                                if (rectSizeY - diffY < 160) {
                                    diffY = rectSizeY - 160;
                                }
                                if (rectY + diffY < bitmapY) {
                                    diffY = bitmapY - rectY;
                                }
                                rectX += diffX;
                                rectY += diffY;
                                rectSizeX -= diffX;
                                rectSizeY -= diffY;
                            }
                        } else if (draggingState == 2) {
                            if (rectSizeX + diffX < 160) {
                                diffX = -(rectSizeX - 160);
                            }
                            if (rectX + rectSizeX + diffX > bitmapX + bitmapWidth) {
                                diffX = bitmapX + bitmapWidth - rectX - rectSizeX;
                            }
                            if (!freeform) {
                                if (rectY - diffX < bitmapY) {
                                    diffX = rectY - bitmapY;
                                }
                                rectY -= diffX;
                                rectSizeX += diffX;
                                rectSizeY += diffX;
                            } else {
                                if (rectSizeY - diffY < 160) {
                                    diffY = rectSizeY - 160;
                                }
                                if (rectY + diffY < bitmapY) {
                                    diffY = bitmapY - rectY;
                                }
                                rectY += diffY;
                                rectSizeX += diffX;
                                rectSizeY -= diffY;
                            }
                        } else if (draggingState == 3) {
                            if (rectSizeX - diffX < 160) {
                                diffX = rectSizeX - 160;
                            }
                            if (rectX + diffX < bitmapX) {
                                diffX = bitmapX - rectX;
                            }
                            if (!freeform) {
                                if (rectY + rectSizeX - diffX > bitmapY + bitmapHeight) {
                                    diffX = rectY + rectSizeX - bitmapY - bitmapHeight;
                                }
                                rectX += diffX;
                                rectSizeX -= diffX;
                                rectSizeY -= diffX;
                            } else {
                                if (rectY + rectSizeY + diffY > bitmapY + bitmapHeight) {
                                    diffY = bitmapY + bitmapHeight - rectY - rectSizeY;
                                }
                                rectX += diffX;
                                rectSizeX -= diffX;
                                rectSizeY += diffY;
                                if (rectSizeY < 160) {
                                    rectSizeY = 160;
                                }
                            }
                        } else if (draggingState == 4) {
                            if (rectX + rectSizeX + diffX > bitmapX + bitmapWidth) {
                                diffX = bitmapX + bitmapWidth - rectX - rectSizeX;
                            }
                            if (!freeform) {
                                if (rectY + rectSizeX + diffX > bitmapY + bitmapHeight) {
                                    diffX = bitmapY + bitmapHeight - rectY - rectSizeX;
                                }
                                rectSizeX += diffX;
                                rectSizeY += diffX;
                            } else {
                                if (rectY + rectSizeY + diffY > bitmapY + bitmapHeight) {
                                    diffY = bitmapY + bitmapHeight - rectY - rectSizeY;
                                }
                                rectSizeX += diffX;
                                rectSizeY += diffY;
                            }
                            if (rectSizeX < 160) {
                                rectSizeX = 160;
                            }
                            if (rectSizeY < 160) {
                                rectSizeY = 160;
                            }
                        }
                    }

                    oldX = x;
                    oldY = y;
                    invalidate();
                }
                return true;
            }
        });
    }

    private void updateBitmapSize() {
        if (viewWidth == 0 || viewHeight == 0 || imageToCrop == null) {
            return;
        }
        float percX = (rectX - bitmapX) / bitmapWidth;
        float percY = (rectY - bitmapY) / bitmapHeight;
        float percSizeX = rectSizeX / bitmapWidth;
        float percSizeY = rectSizeY / bitmapHeight;
        float w = imageToCrop.getWidth();
        float h = imageToCrop.getHeight();
        float scaleX = viewWidth / w;
        float scaleY = viewHeight / h;
        if (scaleX > scaleY) {
            bitmapHeight = viewHeight;
            bitmapWidth = (int) Math.ceil(w * scaleY);
        } else {
            bitmapWidth = viewWidth;
            bitmapHeight = (int) Math.ceil(h * scaleX);
        }
        bitmapX = (viewWidth - bitmapWidth) / 2 + utilities.dp(14);
        bitmapY = (viewHeight - bitmapHeight) / 2 + utilities.dp(14);

        if (rectX == -1 && rectY == -1) {
            if (freeform) {
                rectY = bitmapY;
                rectX = bitmapX;
                rectSizeX = bitmapWidth;
                rectSizeY = bitmapHeight;
            } else {
                if (bitmapWidth > bitmapHeight) {
                    rectY = bitmapY;
                    rectX = (viewWidth - bitmapHeight) / 2 + utilities.dp(14);
                    rectSizeX = bitmapHeight;
                    rectSizeY = bitmapHeight;
                } else {
                    rectX = bitmapX;
                    rectY = (viewHeight - bitmapWidth) / 2 + utilities.dp(14);
                    rectSizeX = bitmapWidth;
                    rectSizeY = bitmapWidth;
                }
            }
        } else {
            rectX = percX * bitmapWidth + bitmapX;
            rectY = percY * bitmapHeight + bitmapY;
            rectSizeX = percSizeX * bitmapWidth;
            rectSizeY = percSizeY * bitmapHeight;
        }
        invalidate();
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        viewWidth = right - left - utilities.dp(28);
        viewHeight = bottom - top - utilities.dp(28);
        updateBitmapSize();
    }

    public Bitmap getBitmap() {
        float percX = (rectX - bitmapX) / bitmapWidth;
        float percY = (rectY - bitmapY) / bitmapHeight;
        float percSizeX = rectSizeX / bitmapWidth;
        float percSizeY = rectSizeY / bitmapWidth;
        int x = (int) (percX * imageToCrop.getWidth());
        int y = (int) (percY * imageToCrop.getHeight());
        int sizeX = (int) (percSizeX * imageToCrop.getWidth());
        int sizeY = (int) (percSizeY * imageToCrop.getWidth());
        if (x < 0) {
            x = 0;
        }
        if (y < 0) {
            y = 0;
        }
        if (x + sizeX > imageToCrop.getWidth()) {
            sizeX = imageToCrop.getWidth() - x;
        }
        if (y + sizeY > imageToCrop.getHeight()) {
            sizeY = imageToCrop.getHeight() - y;
        }
        try {
            return Bitmap.createBitmap(imageToCrop, x, y, sizeX, sizeY);
        } catch (Throwable e) {
            System.gc();
            try {
                return Bitmap.createBitmap(imageToCrop, x, y, sizeX, sizeY);
            } catch (Throwable e2) {
            }
        }
        return null;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (drawable != null) {
            try {
                drawable.setBounds(bitmapX, bitmapY, bitmapX + bitmapWidth, bitmapY + bitmapHeight);
                drawable.draw(canvas);
                Toast.makeText(getContext(), "Amaha Kho", Toast.LENGTH_SHORT).show();

            } catch (Throwable e) {
            }
        }
        canvas.drawRect(bitmapX, bitmapY, bitmapX + bitmapWidth, rectY, halfPaint);
        canvas.drawRect(bitmapX, rectY, rectX, rectY + rectSizeY, halfPaint);
        canvas.drawRect(rectX + rectSizeX, rectY, bitmapX + bitmapWidth, rectY + rectSizeY, halfPaint);
        canvas.drawRect(bitmapX, rectY + rectSizeY, bitmapX + bitmapWidth, bitmapY + bitmapHeight, halfPaint);

        canvas.drawRect(rectX, rectY, rectX + rectSizeX, rectY + rectSizeY, rectPaint);

        int side = utilities.dp(1);
        canvas.drawRect(rectX + side, rectY + side, rectX + side + utilities.dp(20), rectY + side * 3, circlePaint);
        canvas.drawRect(rectX + side, rectY + side, rectX + side * 3, rectY + side + utilities.dp(20), circlePaint);

        canvas.drawRect(rectX + rectSizeX - side - utilities.dp(20), rectY + side, rectX + rectSizeX - side, rectY + side * 3, circlePaint);
        canvas.drawRect(rectX + rectSizeX - side * 3, rectY + side, rectX + rectSizeX - side, rectY + side + utilities.dp(20), circlePaint);

        canvas.drawRect(rectX + side, rectY + rectSizeY - side - utilities.dp(20), rectX + side * 3, rectY + rectSizeY - side, circlePaint);
        canvas.drawRect(rectX + side, rectY + rectSizeY - side * 3, rectX + side + utilities.dp(20), rectY + rectSizeY - side, circlePaint);

        canvas.drawRect(rectX + rectSizeX - side - utilities.dp(20), rectY + rectSizeY - side * 3, rectX + rectSizeX - side, rectY + rectSizeY - side, circlePaint);
        canvas.drawRect(rectX + rectSizeX - side * 3, rectY + rectSizeY - side - utilities.dp(20), rectX + rectSizeX - side, rectY + rectSizeY - side, circlePaint);

        for (int a = 1; a < 3; a++) {
            canvas.drawRect(rectX + rectSizeX / 3 * a, rectY + side, rectX + side + rectSizeX / 3 * a, rectY + rectSizeY - side, circlePaint);
            canvas.drawRect(rectX + side, rectY + rectSizeY / 3 * a, rectX - side + rectSizeX, rectY + rectSizeY / 3 * a + side, circlePaint);
        }
    }
}