package com.example.android.product_ai;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
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

//    long startTime = 0;
//    long delay = 0;

    private String tag = "Camera";


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

        cameraView.addCameraKitListener(new CameraKitEventListener() {
            @Override
            public void onEvent(CameraKitEvent cameraKitEvent) {

            }

            @Override
            public void onError(CameraKitError cameraKitError) {

            }

            @Override
            public void onImage(CameraKitImage cameraKitImage) {
//                delay = SystemClock.uptimeMillis() - startTime;
//                Log.d(tag, Long.toString(delay));
//                startTime = SystemClock.uptimeMillis();
//                Log.d(tag,"Picture Taken");
                Bitmap bitmap = cameraKitImage.getBitmap();
                bitmap = Bitmap.createScaledBitmap(bitmap, INPUT_SIZE, INPUT_SIZE, false);
                bitmaps.add(bitmap);
//                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArrayOutputStream);
//                byte[] bytesArray = byteArrayOutputStream.toByteArray();
//                delay = SystemClock.uptimeMillis() - startTime;
//                Log.d(tag,Long.toString(delay));

//                startTime = SystemClock.uptimeMillis();
                String label = classifier.classifyFrame(bitmap);
//                imgList imgData = new imgList(bytesArray);
                classifiedLabel.add(label);
//                delay = SystemClock.uptimeMillis() - startTime;
//                Log.d(tag,Long.toString(delay));
                Log.d(tag,String.valueOf(bitmaps.size()));
                if (bitmaps.size()>=2){
                    btnDetectObject.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onVideo(CameraKitVideo cameraKitVideo) {

            }
        });

        btnDetectObject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startTime = SystemClock.uptimeMillis();
                cameraView.captureImage();
            }
        });

        passIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),detailPage.class);
                intent.putStringArrayListExtra("byteArr",classifiedLabel);
                intent.putParcelableArrayListExtra("images",bitmaps);
                startActivity(intent);
                bitmaps.clear();
                classifiedLabel.clear();
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
