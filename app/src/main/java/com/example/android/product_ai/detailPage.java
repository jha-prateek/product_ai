package com.example.android.product_ai;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class detailPage extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
//    private ImageClassifier classifier;
    private ArrayList<Bitmap> bitmapArrayList;
    private String tag = "details";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_page);

//        try {
//            classifier = new ImageClassifier(this);
//        } catch (IOException e) {
//            Log.e(tag, "Failed to initialize an image classifier.");
//        }

        bitmapArrayList = new ArrayList<>();
        imageView = findViewById(R.id.imageView2);
        textView = findViewById(R.id.text_view);
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
                    imageView.setImageBitmap(bitmapArr[0]);
                    textView.setText("\t" + labels.toString());
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
