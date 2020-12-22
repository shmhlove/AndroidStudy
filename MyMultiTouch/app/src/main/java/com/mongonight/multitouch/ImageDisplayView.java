package com.mongonight.multitouch;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

public class ImageDisplayView  extends View {

    float MIN_SCALE_RATIO = 0.1f;
    float MAX_SCALE_RATIO = 5.0f;

    Paint mPaint;
    Bitmap mBitmap;
    Canvas mCanvas;
    Matrix mMatrix;
    Bitmap sourceBitmap;

    int lastX, lastY;
    float displayWidth, displayHeight;
    float displayCenterX, displayCenterY;

    float startX, startY;
    float oldDistance;
    float oldPointerCount;
    float distanceThreshold;
    float scaleRatio;
    float totalScaleRatio;
    boolean isScrolling;

    float bitmapCenterX;
    float bitmapCenterY;

    public ImageDisplayView(Context context) {
        super(context);
        init(context);
    }

    public ImageDisplayView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        mPaint = new Paint();
        mMatrix = new Matrix();

        lastX = lastY = -1;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (0 < w && 0 < h) {
            newImage(w, h);
            redraw();
        }
    }

    public void newImage(int w, int h) {
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas();
        mCanvas.setBitmap(mBitmap);

        displayWidth = (float)w;
        displayHeight = (float)h;
        displayCenterX = (float)w/2;
        displayCenterY = (float)h/2;
    }

    public void setImageData(Bitmap image) {
        recycle();

        sourceBitmap = image;

        bitmapCenterX = sourceBitmap.getWidth()/2;
        bitmapCenterY = sourceBitmap.getHeight()/2;

        scaleRatio = 1.0F;
        totalScaleRatio = 1.0F;
    }

    public void recycle() {
        if (sourceBitmap != null) {
            sourceBitmap.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (null != mBitmap) {
            canvas.drawBitmap(mBitmap, 0,0,null);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        final int action = event.getAction();

        int pointerCount = event.getPointerCount();

        switch (action) {
            case MotionEvent.ACTION_DOWN:

                if (1 == pointerCount) {
                    startX = event.getX();
                    startY = event.getY();
                } else if (2 == pointerCount) {
                    oldDistance = 0.0f;
                    isScrolling = true;
                }

                return true;
            case MotionEvent.ACTION_MOVE:

                if (1 == pointerCount) {

                    if (isScrolling) {
                        return true;
                    }

                    float curX = event.getX();
                    float curY = event.getY();

                    if (startX == 0.0f) {
                        startX = curX;
                        startY = curY;

                        return true;
                    }

                    float offsetX = startX - curX;
                    float offsetY = startY - curY;

                    if (oldPointerCount == 2) {

                    } else {
                        if (totalScaleRatio > 1.0F) {
                            moveImage(-offsetX, -offsetY);
                        }

                        startX = curX;
                        startY = curY;
                    }

                } else if (pointerCount == 2) {

                    float x1 = event.getX(0);
                    float y1 = event.getY(0);
                    float x2 = event.getX(1);
                    float y2 = event.getY(1);

                    float dx = x1 - x2;
                    float dy = y1 - y2;
                    float distance = new Double(Math.sqrt(new Float(dx * dx + dy * dy).doubleValue())).floatValue();

                    float outScaleRatio = 0.0F;
                    if (oldDistance == 0.0F) {
                        oldDistance = distance;
                        break;
                    }

                    if (distance > oldDistance) {
                        if ((distance-oldDistance) < distanceThreshold) {
                            return true;
                        }

                        outScaleRatio = scaleRatio + (oldDistance / distance * 0.05F);
                    } else if (distance < oldDistance) {
                        if ((oldDistance-distance) < distanceThreshold) {
                            return true;
                        }

                        outScaleRatio = scaleRatio - (distance / oldDistance * 0.05F);
                    }

                    if (outScaleRatio < MIN_SCALE_RATIO || outScaleRatio > MAX_SCALE_RATIO) {

                    } else {
                        scaleImage(outScaleRatio);
                    }

                    oldDistance = distance;
                }

                oldPointerCount = pointerCount;

                break;

            case MotionEvent.ACTION_UP:

                if (pointerCount == 1) {

                    float curX = event.getX();
                    float curY = event.getY();

                    float offsetX = startX - curX;
                    float offsetY = startY - curY;

                    if (oldPointerCount == 2) {

                    } else {
                        moveImage(-offsetX, -offsetY);
                    }

                } else {
                    isScrolling = false;
                }

                return true;
        }

        return true;
    }

    public void moveImage(float fx, float fy)
    {
        mMatrix.postTranslate(fx, fy);

        redraw();
    }

    public void scaleImage(float inScaleRatio)
    {
        mMatrix.postScale(inScaleRatio, inScaleRatio, bitmapCenterX, bitmapCenterY);
        mMatrix.postRotate(0);

        totalScaleRatio = totalScaleRatio * inScaleRatio;

        redraw();
    }

    public void redraw() {
        if (sourceBitmap == null) {
            return;
        }

        drawBackground(mCanvas);

        float originX = (displayWidth - (float)sourceBitmap.getWidth()) / 2.0F;
        float originY = (displayHeight - (float)sourceBitmap.getHeight()) / 2.0F;

        mCanvas.translate(originX, originY);
        mCanvas.drawBitmap(sourceBitmap, mMatrix, mPaint);
        mCanvas.translate(-originX, -originY);

        invalidate();
    }

    public void drawBackground(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(Color.BLACK);
        }
    }
}
