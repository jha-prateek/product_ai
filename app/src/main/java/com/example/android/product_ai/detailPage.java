package com.example.android.product_ai;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class detailPage extends AppCompatActivity {
    private ImageView imageView,imageView1;
    private TextView textView,textView1;
//    private ImageClassifier classifier;
    private ArrayList<Bitmap> bitmapArrayList;
    private CardView cardView;
    private String tag = "details";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.getSupportActionBar().hide();


        setContentView(R.layout.detail_page);


        RelativeLayout relativeLayout =  findViewById(R.id.details_id);
        final AnimationDrawable animationDrawable = (AnimationDrawable) relativeLayout.getBackground();
        animationDrawable.setEnterFadeDuration(2000);
        animationDrawable.setExitFadeDuration(4000);
        animationDrawable.start();

//        try {
//            classifier = new ImageClassifier(this);
//        } catch (IOException e) {
//            Log.e(tag, "Failed to initialize an image classifier.");
//        }

//        RelativeLayout relativeLayout1 =  findViewById(R.id.relative1);
//        final AnimationDrawable animationDrawable1 = (AnimationDrawable) relativeLayout1.getBackground();
//        animationDrawable1.setEnterFadeDuration(2000);
//        animationDrawable1.setExitFadeDuration(4000);
//        animationDrawable1.start();


        bitmapArrayList = new ArrayList<>();
        imageView = findViewById(R.id.imageView2);
        textView = findViewById(R.id.text_view);
        imageView1 = findViewById(R.id.imageView3);
        textView1 = findViewById(R.id.text_view2);
        cardView = findViewById(R.id.card2);
        Thread thread = new Thread() {
            @Override
            public void run() {
                Intent intent = getIntent();
                ArrayList<String> labels = intent.getStringArrayListExtra("byteArr");
                bitmapArrayList = intent.getParcelableArrayListExtra("images");
                Log.d("bitmaparray",bitmapArrayList.toString());
                Bitmap[] bitmapArr = bitmapArrayList.toArray(new Bitmap[bitmapArrayList.size()]);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                String labelIdentified = classifier.classifyFrame(bitmap);

                runOnUiThread(() -> {
                    if (bitmapArr.length>=2){
                        cardView.setVisibility(View.VISIBLE);
                        imageView.setImageBitmap(bitmapArr[0]);
                        imageView1.setImageBitmap(bitmapArr[1]);
                        textView.setText(labels.get(0));
                        textView1.setText(labels.get(1));
                    }
                    else
                    {
                        cardView.setVisibility(View.INVISIBLE);
                        imageView.setImageBitmap(bitmapArr[0]);
                        textView.setText(labels.get(0));
                    }
                });
            }
        };
        thread.start();
    }

//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        classifier.close();
//    }
}
