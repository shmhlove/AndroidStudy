package com.mongonight.framelayout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ImageView imageView2;

    int imageIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
    }

    public void onClickedButton(View v) {
        changeImage();
    }

    public void changeImage() {
        if (0 == imageIndex) {
            imageView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.INVISIBLE);

            imageIndex = 1;
        }
        else if (1 == imageIndex) {
            imageView.setVisibility(View.INVISIBLE);
            imageView.setVisibility(View.VISIBLE);

            imageIndex = 0;
        }
    }
}