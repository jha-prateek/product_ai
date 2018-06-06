package com.example.android.product_ai;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListener;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends Activity{

    private static final int INPUT_SIZE = 224;

    private ImageView btnDetectObject;
    private CameraView cameraView;
    private ImageView passIntent;


    private ArrayList<String> classifiedLabel;
    private ArrayList<Bitmap> bitmaps;

    private ImageClassifier classifier;

    private Vibrator vibrator;

                private String tag = "Camera";
    private ImageView count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try {
            classifier = new ImageClassifier(this);
        } catch (IOException e) {
            Log.e(tag, "Failed to initialize an image classifier.");
        }

        classifiedLabel = new ArrayList<>();
        bitmaps = new ArrayList<>();

        cameraView = findViewById(R.id.cameraView);
        btnDetectObject = findViewById(R.id.btnDetectObject);
        passIntent = findViewById(R.id.intentPass);
        count = findViewById(R.id.btncount);
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
                btnDetectObject.setVisibility(View.VISIBLE);
                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);
                bitmaps.add(bitmap);
                String label = classifier.classifyFrame(bitmap);
                classifiedLabel.add(label);
                Log.d(tag,String.valueOf(bitmaps.size()));

                if (bitmaps.size()==1){
                    count.setImageResource(R.drawable.baseline_looks_one_white_24dp);
                    Toast.makeText(MainActivity.this, "Click to show details or take one more shot to compare!!", Toast.LENGTH_SHORT).show();
                }else if (bitmaps.size()==2){
                    count.setImageResource(R.drawable.baseline_looks_two_white_24dp);
                    Toast.makeText(MainActivity.this, "Ready to Compare!!", Toast.LENGTH_SHORT).show();
                }

                if (bitmaps.size()>=2){
                    btnDetectObject.setVisibility(View.INVISIBLE);
                }

                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(500);
                }
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });

        btnDetectObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vibrator.hasVibrator()) {
                    vibrator.vibrate(50);
                }
                cameraView.captureImage();
                btnDetectObject.setVisibility(View.INVISIBLE);
            }
        });

        passIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitmaps.size()>0 && bitmaps.size()<=2){
                    Intent intent = new Intent(getApplicationContext(), detailPage.class);
                    intent.putStringArrayListExtra("byteArr", classifiedLabel);
                    intent.putParcelableArrayListExtra("images", bitmaps);
                    startActivity(intent);
                    bitmaps.clear();
                    classifiedLabel.clear();
                }
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        btnDetectObject.setVisibility(View.VISIBLE);
        cameraView.start();
    }

    @Override
    protected void onPause() {
        cameraView.stop();
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        classifier.close();
    }
}

