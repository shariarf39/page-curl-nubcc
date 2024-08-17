package com.fahimshariar.pagecurlnub;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.fahimshariar.pagecurlnub.CurlView;

public class ZoomableCurlView extends CurlView {

    private ScaleGestureDetector scaleDetector;
    private float scaleFactor = 1.0f;

    public ZoomableCurlView(Context ctx) {
        super(ctx);
        initialize(ctx);
    }

    public ZoomableCurlView(Context ctx, AttributeSet attrs) {
        super(ctx, attrs);
        initialize(ctx);
    }

    private void initialize(Context context) {
        scaleDetector = new ScaleGestureDetector(context, new ScaleListener());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.scale(scaleFactor, scaleFactor);
        super.onDraw(canvas);
        canvas.restore();
    }

    @Override
    public boolean onTouchEvent(android.view.MotionEvent event) {
        scaleDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    private class ScaleListener extends ScaleGestureDetector.SimpleOnScaleGestureListener {
        @Override
        public boolean onScale(ScaleGestureDetector detector) {
            scaleFactor *= detector.getScaleFactor();
            scaleFactor = Math.max(0.1f, Math.min(scaleFactor, 5.0f));  // Set zoom limits
            invalidate();
            return true;
        }
    }
}
