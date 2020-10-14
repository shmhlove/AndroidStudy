package com.mongonight.layout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Layout1 layout1;
    int curImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout1 = findViewById(R.id.layout1);
        layout1.setImage(R.drawable.super_mario);
        layout1.setName("이상호");
        layout1.setMobile("010-3622-2209");

        curImage = R.drawable.super_mario;

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (R.drawable.super_mario == curImage) {
                    layout1.setImage(R.drawable.super_mario2);
                    curImage = R.drawable.super_mario2;
                } else if (R.drawable.super_mario2 == curImage) {
                    layout1.setImage(R.drawable.super_mario);
                    curImage = R.drawable.super_mario;
                }

            }
        });
    }
}