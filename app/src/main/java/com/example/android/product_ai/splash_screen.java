package com.example.android.product_ai;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;

import static java.lang.Thread.sleep;

/**
 * Created by shivendra on 27/05/18.
 */

public class splash_screen extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        try {
            sleep(250);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        finish();
    }

}
