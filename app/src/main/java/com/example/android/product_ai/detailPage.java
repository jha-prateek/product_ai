package com.example.android.product_ai;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
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
    private ImageView thumb1, thumb2;
    private TextView title1, title2, detail1, detail2;
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
        thumb1 = findViewById(R.id.imageView2);
        title1 = findViewById(R.id.text_view);
        thumb2 = findViewById(R.id.imageView3);
        title2 = findViewById(R.id.text_view2);
        cardView = findViewById(R.id.card2);
        detail1 = findViewById(R.id.text_view1);
        detail2 = findViewById(R.id.text_view3);

        Thread thread = new Thread() {
            @Override
            public void run() {
                Intent intent = getIntent();
                ArrayList<String> labels = intent.getStringArrayListExtra("byteArr");
                bitmapArrayList = intent.getParcelableArrayListExtra("images");
                Log.d("bitmaparray",bitmapArrayList.toString());
//                Bitmap[] bitmapArr = bitmapArrayList.toArray(new Bitmap[bitmapArrayList.size()]);
//                Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
//                String labelIdentified = classifier.classifyFrame(bitmap);

                runOnUiThread(() -> {
                    if (bitmapArrayList.size()>=2){
                        cardView.setVisibility(View.VISIBLE);
                        thumb1.setImageBitmap(bitmapArrayList.get(0));
                        thumb2.setImageBitmap(bitmapArrayList.get(1));
                        title1.setText(labels.get(0));
                        title2.setText(labels.get(1));
                        detail1.setText(getDetails(labels.get(0)));
                        detail2.setText(getDetails(labels.get(1)));
                    }
                    else
                    {
                        cardView.setVisibility(View.INVISIBLE);
                        thumb1.setImageBitmap(bitmapArrayList.get(0));
                        title1.setText(labels.get(0));
                        detail1.setText(getDetails(labels.get(0)));
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

    public String getDetails(String label){
        switch (label){
            case "tide naturals": return getResources().getString(R.string.TideN);
            case "ariel complete": return getResources().getString(R.string.ArielC);
            case "tide plus jasmine and rose":return getResources().getString(R.string.jasmine);
            case "tide plus original":return getResources().getString(R.string.tideplus);
        }
        return "null";
    }
}
