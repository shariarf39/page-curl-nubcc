package com.fahimshariar.pagecurlnub.video;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.constants.ScaleTypes;
import com.denzcoskun.imageslider.models.SlideModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


import com.fahimshariar.pagecurlnub.MainActivity;
import com.fahimshariar.pagecurlnub.R;

public class Home extends AppCompatActivity {

    public static int CategoryClicked = -10;
    ImageSlider imageSlider;
    ExpandableHeightGridView mainGrid;
    RelativeLayout rLayRateUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        imageSlider = findViewById(R.id.image_slider);
        mainGrid = findViewById(R.id.mainGrid);
        rLayRateUs = findViewById(R.id.rLayRateUs);


        createSlider();
        MakeVideoList.createMyAlbums();
        rateUsOnGooglePlay();

        MyAdapter adapter = new MyAdapter();
        mainGrid.setExpanded(true);
        mainGrid.setAdapter(adapter);
        adapter.notifyDataSetChanged();


    } //------------------------------onCreate (bundle) ENDS here






    // ================================================================
    // ================================================================
    // ================================================================

    private void createSlider(){

        ArrayList<SlideModel> imageList = new ArrayList<>();
        imageList.add(new SlideModel(R.drawable.nubcc_magazine_01, null));

        imageSlider.setImageList(imageList, ScaleTypes.CENTER_CROP);

    }
    // ================================================================
    // ================================================================
    // ================================================================



    private class MyAdapter extends BaseAdapter {
        private LayoutInflater inflater;

        public  MyAdapter(){
            this.inflater = (LayoutInflater) Home.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @Override
        public int getCount() {
            return MakeVideoList.catArrayList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            convertView = inflater.inflate(R.layout.grid_item, parent, false);


            ImageView imgIcon = convertView.findViewById(R.id.imgIcon);
            TextView tvTitle = convertView.findViewById(R.id.tvTitle);
            LinearLayout layItem = convertView.findViewById(R.id.layItem);

            HashMap<String, String> mHashMap = MakeVideoList.catArrayList.get(position);
            String catName = mHashMap.get("category_name");
            String img = mHashMap.get("img");


            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Start the main activity after the splash screen duration
                    Video_MainActivity.arrayList = MakeVideoList.rootArrayList.get(0);
                    Intent intent = new Intent(Home.this, Video_MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }, 300);

            if (tvTitle!=null) tvTitle.setText(catName);
            if (imgIcon!=null && img!=null) {
                int drawable = Integer.parseInt(img);
                imgIcon.setImageResource( drawable );
            }

            Animation animation = AnimationUtils.loadAnimation(Home.this, R.anim.anim_grid);
            animation.setStartOffset(position*300);
            convertView.startAnimation(animation);



            if (layItem!=null){
                layItem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //We are tracking the category postion. So that we can call the intent after ad loads
                        CategoryClicked = position;


                            Video_MainActivity.arrayList = MakeVideoList.rootArrayList.get(0);
                            startActivity(new Intent(Home.this, Video_MainActivity.class));


                    }
                });
            }



            return convertView;
        }
    }




    //=============================================
    //=============================================
    //=============================================
















    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>




    // loadFullscreenAd method ENDS  here..... >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>





        //Init Admob Ads



    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    private void rateUsOnGooglePlay(){
        rLayRateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=" + appPackageName)));
                }

            }
        });
    }

    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    //>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>



    ///====================================================
    private static final int TIME_INTERVAL = 2000; // # milliseconds, desired
    private long mBackPressed;

    // When user click bakpress button this method is called
    @Override
    public void onBackPressed() {
        // When user press back button

        if (mBackPressed + TIME_INTERVAL > System.currentTimeMillis()) {
        /*    Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);*/
            startActivity(new Intent(Home.this, MainActivity.class));

        } else {

            startActivity(new Intent(Home.this, MainActivity.class));
        }

        mBackPressed = System.currentTimeMillis();



    }
}