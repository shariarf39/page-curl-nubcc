package com.fahimshariar.pagecurlnub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ZoomableCurlView mCurlView;
    private ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = findViewById(R.id.profile);
        mCurlView = findViewById(R.id.curl);
        mCurlView.setPageProvider(new PageProvider());
        mCurlView.setSizeChangedObserver(new SizeChangedObserver());
        mCurlView.setBackgroundColor(0xFF202830);

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, profile.class));
            }
        });

        if (savedInstanceState != null) {
            int index = savedInstanceState.getInt("currentIndex", 0);
            mCurlView.setCurrentIndex(index);
        } else {
            mCurlView.setCurrentIndex(0);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("currentIndex", mCurlView.getCurrentIndex());
    }

    @Override
    public void onPause() {
        super.onPause();
        mCurlView.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mCurlView.onResume();
    }

    /**
     * PageProvider class that provides the content for each page.
     */
    private class PageProvider implements CurlView.PageProvider {

        // Bitmap resources.
        private int[] mBitmapIds = {R.drawable.page1, R.drawable.page2,
                R.drawable.page3, R.drawable.page4, R.drawable.page1, R.drawable.page2,
                R.drawable.page3, R.drawable.page4};

        @Override
        public int getPageCount() {
            return mBitmapIds.length;
        }

        private Bitmap loadBitmap(int width, int height, int index) {
            Bitmap b = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            b.eraseColor(0xFFFFFFFF);
            Canvas c = new Canvas(b);
            Drawable d = getResources().getDrawable(mBitmapIds[index]);

            int margin = 0;
            int border = 0;
            Rect r = new Rect(margin, margin, width - margin, height - margin);

            int imageWidth = r.width() - (border * 2);
            int imageHeight = imageWidth * d.getIntrinsicHeight()
                    / d.getIntrinsicWidth();
            if (imageHeight > r.height() - (border * 2)) {
                imageHeight = r.height() - (border * 2);
                imageWidth = imageHeight * d.getIntrinsicWidth()
                        / d.getIntrinsicHeight();
            }

            r.left += ((r.width() - imageWidth) / 2) - border;
            r.right = r.left + imageWidth + border + border;
            r.top += ((r.height() - imageHeight) / 2) - border;
            r.bottom = r.top + imageHeight + border + border;

            Paint p = new Paint();
        //    p.setColor(0xFFC0C0C0);
            c.drawRect(r, p);
            r.left += border;
            r.right -= border;
            r.top += border;
            r.bottom -= border;

            d.setBounds(r);
            d.draw(c);

            return b;
        }

        @Override
        public void updatePage(CurlPage page, int width, int height, int index) {
            switch (index) {
                // First case: image on front side, solid colored back.
                case 0: {
                    Bitmap front = loadBitmap(width, height, 0);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setColor(Color.rgb(180, 180, 180), CurlPage.SIDE_BACK);
                    break;
                }
                // Second case: image on back side, solid colored front.
                case 1: {
                    Bitmap back = loadBitmap(width, height, 2);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    page.setColor(Color.rgb(127, 140, 180), CurlPage.SIDE_FRONT);
                    break;
                }
                // Third case: images on both sides.
                case 2: {
                    Bitmap front = loadBitmap(width, height, 1);
                    Bitmap back = loadBitmap(width, height, 3);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    break;
                }
                // Fourth case: images on both sides with separate colors.
                case 3: {
                    Bitmap front = loadBitmap(width, height, 2);
                    Bitmap back = loadBitmap(width, height, 1);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    page.setColor(Color.argb(127, 170, 130, 255), CurlPage.SIDE_FRONT);
                    page.setColor(Color.rgb(255, 190, 150), CurlPage.SIDE_BACK);
                    break;
                }
                // Fifth case: same image on both sides.
                case 4:
                    Bitmap front = loadBitmap(width, height, 0);
                    page.setTexture(front, CurlPage.SIDE_BOTH);
                    page.setColor(Color.argb(127, 255, 255, 255), CurlPage.SIDE_BACK);
                    break;
            }
        }
    }

    /**
     * CurlView size changed observer.
     */
    private class SizeChangedObserver implements CurlView.SizeChangedObserver {
        @Override
        public void onSizeChanged(int w, int h) {
            if (w > h) {
                mCurlView.setViewMode(CurlView.SHOW_TWO_PAGES);
              mCurlView.setMargins(.05f, .05f, .05f, .05f);
            } else {
                mCurlView.setViewMode(CurlView.SHOW_ONE_PAGE);
              //  mCurlView.setMargins(.1f, .1f, .1f, .1f);
            }
        }
    }
}
