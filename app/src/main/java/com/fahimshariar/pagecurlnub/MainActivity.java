package com.fahimshariar.pagecurlnub;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.fahimshariar.pagecurlnub.video.Home;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private CurlView mCurlView;
    private ImageView profile, video;

    private FloatingActionButton zoomInButton, zoomOutButton;
    private float scaleFactor = 1.0f;
    private final float SCALE_STEP = 0.1f;
    private final float MIN_SCALE = 0.5f;
    private final float MAX_SCALE = 3.0f;

    private FloatingActionButton panLeftButton, panRightButton;
    private float translateX = 0f;
    private final float PAN_STEP = 50f;  // Amount to pan with each button press

    private MediaPlayer pageFlipSound; // MediaPlayer for sound


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        profile = findViewById(R.id.profile);
        mCurlView = findViewById(R.id.curl);
        zoomInButton = findViewById(R.id.zoom_in_button);
        zoomOutButton = findViewById(R.id.zoom_out_button);
        panLeftButton = findViewById(R.id.pan_left_button);
        panRightButton = findViewById(R.id.pan_right_button);
        video = findViewById(R.id.video);
        mCurlView.setPageProvider(new PageProvider());
        mCurlView.setSizeChangedObserver(new SizeChangedObserver());
        mCurlView.setBackgroundColor(0xFF202830);

        pageFlipSound = MediaPlayer.create(this, R.raw.page_turn);

        // Set up Zoom In button
        zoomInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scaleFactor < MAX_SCALE) {
                    scaleFactor += SCALE_STEP;
                    mCurlView.setScaleX(scaleFactor);
                    mCurlView.setScaleY(scaleFactor);
                }
            }
        });
        //-------------------
        video.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Home.class));
            }
        });

        // Set up Zoom Out button
        zoomOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (scaleFactor > MIN_SCALE) {
                    scaleFactor -= SCALE_STEP;
                    mCurlView.setScaleX(scaleFactor);
                    mCurlView.setScaleY(scaleFactor);
                }
            }
        });

        // Pan Left button
        panLeftButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pan to the left
                float maxTranslateX = (mCurlView.getWidth() * (scaleFactor - 1)) / 2;
                translateX = Math.max(-maxTranslateX, translateX - PAN_STEP);
                mCurlView.setTranslationX(translateX);
            }
        });

        // Pan Right button
        panRightButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Pan to the right
                float maxTranslateX = (mCurlView.getWidth() * (scaleFactor - 1)) / 2;
                translateX = Math.min(maxTranslateX, translateX + PAN_STEP);
                mCurlView.setTranslationX(translateX);
            }
        });



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white, this.getTheme()));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.white));
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        }

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
        private int[] mBitmapIds = {R.drawable.nubcc_magazine_01, R.drawable.nubcc_magazine_02,
        R.drawable.nubcc_magazine_03, R.drawable.nubcc_magazine_04, R.drawable.nubcc_magazine_05, R.drawable.nubcc_magazine_06, R.drawable.nubcc_magazine_07,R.drawable.nubcc_magazine_08, R.drawable.nubcc_magazine_09, R.drawable.nubcc_magazine_10, R.drawable.nubcc_magazine_11, R.drawable.nubcc_magazine12, R.drawable.nubcc_magazine13, R.drawable.nubcc_magazine14, R.drawable.nubcc_magazine15, R.drawable.nubcc_magazine16, R.drawable.nubcc_magazine17, R.drawable.nubcc_magazine18, R.drawable.nubcc_magazine19, R.drawable.nubcc_magazine20, R.drawable.nubcc_magazine21, R.drawable.nubcc_magazine22, R.drawable.nubcc_magazine23, R.drawable.nubcc_magazine24, R.drawable.nubcc_magazine25, R.drawable.nubcc_magazine26, R.drawable.nubcc_magazine27, R.drawable.nubcc_magazine28, R.drawable.nubcc_magazine29, R.drawable.nubcc_magazine30, R.drawable.nubcc_magazine31, R.drawable.nubcc_magazine32, R.drawable.nubcc_magazine33, R.drawable.nubcc_magazine34, R.drawable.nubcc_magazine35, R.drawable.nubcc_magazine36, R.drawable.nubcc_magazine37, R.drawable.nubcc_magazine38, R.drawable.nubcc_magazine39, R.drawable.nubcc_magazine40, R.drawable.nubcc_magazine41, R.drawable.nubcc_magazine42, R.drawable.nubcc_magazine43, R.drawable.nubcc_magazine44, R.drawable.nubcc_magazine45, R.drawable.nubcc_magazine46, R.drawable.nubcc_magazine47, R.drawable.nubcc_magazine48, R.drawable.nubcc_magazine49, R.drawable.nubcc_magazine50, R.drawable.nubcc_magazine51
        };

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
            p.setColor(0xFFC0C0C0);
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
            Bitmap front;
            Bitmap back;

            // Set both front and back textures for each page
            switch (index) {
                case 0:
                    // First page, front side has an image, back is a solid color.
                    front = loadBitmap(width, height, 0);
                    back = loadBitmap(width, height, 1);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);


                    break;

                case 1:
                    // Second page, front is a solid color, back side has an image.
                    front = loadBitmap(width, height, 1);
                    back = loadBitmap(width, height, 2);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;

                case 2:
                    // Third page, both sides have images.
                    front = loadBitmap(width, height, 2);
                    back = loadBitmap(width, height, 3);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;

                case 3:
                    // Fourth page, both sides have images.
                    front = loadBitmap(width, height, 3);
                    back = loadBitmap(width, height, 4);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;

                case 4:
                    // Fifth page, both sides have images.
                    front = loadBitmap(width, height, 4);
                    back = loadBitmap(width, height, 5);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;

                case 5:
                    // Sixth page, image on the front and a solid color on the back.
                    front = loadBitmap(width, height, 5);
                    back = loadBitmap(width, height, 6);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;

                case 6:
                    // Seventh page, solid color on the front, image on the back.
                    front = loadBitmap(width, height, 6);
                    back = loadBitmap(width, height, 7);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;

                case 7:
                    front = loadBitmap(width, height, 7);
                    back = loadBitmap(width, height, 8);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 8:

                    front = loadBitmap(width, height, 8);
                    back = loadBitmap(width, height, 9);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 9:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 9);
                    back = loadBitmap(width, height, 10);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 10:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 10);
                    back = loadBitmap(width, height, 11);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 11:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 11);
                    back = loadBitmap(width, height, 12);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 12:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 12);
                    back = loadBitmap(width, height, 13);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 13:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 13);
                    back = loadBitmap(width, height, 14);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 14:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 14);
                    back = loadBitmap(width, height, 15);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 15:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 15);
                    back = loadBitmap(width, height, 16);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 16:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 16);
                    back = loadBitmap(width, height, 17);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 17:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 17);
                    back = loadBitmap(width, height, 18);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 18:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 18);
                    back = loadBitmap(width, height, 19);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 19:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 19);
                    back = loadBitmap(width, height, 20);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 20:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 20);
                    back = loadBitmap(width, height, 21);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 21:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 21);
                    back = loadBitmap(width, height, 22);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 22:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 22);
                    back = loadBitmap(width, height, 23);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 23:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 23);
                    back = loadBitmap(width, height, 24);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 24:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 24);
                    back = loadBitmap(width, height, 25);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    break;
                case 25:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 25);
                    back = loadBitmap(width, height, 26);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 26:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 26);
                    back = loadBitmap(width, height, 27);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 27:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 27);
                    back = loadBitmap(width, height, 28);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 28:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 28);
                    back = loadBitmap(width, height, 29);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 29:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 29);
                    back = loadBitmap(width, height, 30);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 30:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 30);
                    back = loadBitmap(width, height, 31);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 31:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 31);
                    back = loadBitmap(width, height, 32);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 32:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 32);
                    back = loadBitmap(width, height, 33);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 33:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 33);
                    back = loadBitmap(width, height, 34);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 34:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 34);
                    back = loadBitmap(width, height, 35);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 35:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 35);
                    back = loadBitmap(width, height, 36);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 36:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 36);
                    back = loadBitmap(width, height, 37);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 37:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 37);
                    back = loadBitmap(width, height, 38);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 38:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 38);
                    back = loadBitmap(width, height, 39);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 39:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 39);
                    back = loadBitmap(width, height, 40);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 40:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 40);
                    back = loadBitmap(width, height, 41);
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 41:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 41);
                    back = loadBitmap(width, height, 42);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 42:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 42);
                    back = loadBitmap(width, height, 43);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 43:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 43);
                    back = loadBitmap(width, height, 44);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 44:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 44);
                    back = loadBitmap(width, height, 45);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 45:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 45);
                    back = loadBitmap(width, height, 46);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 46:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 46);
                    back = loadBitmap(width, height, 47);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 47:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 47);
                    back = loadBitmap(width, height, 48);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 48:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 48);
                    back = loadBitmap(width, height, 49);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 49:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 49);
                    back = loadBitmap(width, height, 50);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
                case 50:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 50);
                    back = loadBitmap(width, height, 0);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    if (pageFlipSound != null) {
                        pageFlipSound.start(); // Play sound
                    }
                    break;
               /* case 51:
                    // Eighth page, image on the front and back.
                    front = loadBitmap(width, height, 51);
                    back = loadBitmap(width, height, 0);  // Example of cycling back to another image
                    page.setTexture(front, CurlPage.SIDE_FRONT);
                    page.setTexture(back, CurlPage.SIDE_BACK);
                    break;*/








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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (pageFlipSound != null) {
            pageFlipSound.release(); // Release MediaPlayer resources
            pageFlipSound = null;
        }
    }
}
