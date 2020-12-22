package com.mongonight.customviewimage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class CustomViewImage extends View {

    Paint paint;
    Bitmap cacheBitmap;
    Canvas cacheCanvas;

    public CustomViewImage(Context context) {
        super(context);
        init(context);
    }

    public CustomViewImage(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public void init(Context context) {
        paint = new Paint();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // 퍼포먼스 해결
        // 비트맵을 메모리에 먼저 그려서 그리는 작업의 부하를 줄여주자.(더블버퍼링)
        createCacheBitmap(w, h);
        testDrawing();
    }

    public void createCacheBitmap(int w, int h) {
        cacheBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        cacheCanvas = new Canvas();
        cacheCanvas.setBitmap(cacheBitmap);
    }

    public void testDrawing() {
        Bitmap srcImg = BitmapFactory.decodeResource(getResources(), R.drawable.face);
        cacheCanvas.drawBitmap(srcImg, 100, 100, paint);

        Matrix matrix = new Matrix();
        matrix.setScale(1, -1);
        Bitmap inverseBitmap = Bitmap.createBitmap(srcImg, 0,0, srcImg.getWidth(), srcImg.getHeight(), matrix, false);
        cacheCanvas.drawBitmap(inverseBitmap, 100, 800, paint);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // 화면에 바로 그리기 - 퍼포먼스가 많이 듬,
        //Bitmap srcImg = BitmapFactory.decodeResource(getResources(), R.drawable.face);
        //canvas.drawBitmap(srcImg, 100, 100, paint);

        if (null != cacheBitmap) {
            canvas.drawBitmap(cacheBitmap, 0,0,null);
        }
    }
}
