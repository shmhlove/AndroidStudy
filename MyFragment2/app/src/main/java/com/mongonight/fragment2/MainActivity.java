package com.mongonight.fragment2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;

import static com.mongonight.fragment2.R.id.listFragment;
import static com.mongonight.fragment2.R.id.viewerFragment;

public class MainActivity extends AppCompatActivity implements ImageSelectionCallback {

    ListFragment listFragment;
    ViewerFragment viewerFragment;

    int[] images = {R.drawable.image_1, R.drawable.image_2, R.drawable.image_3};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager manager = (FragmentManager) getSupportFragmentManager();
        listFragment = (ListFragment) manager.findFragmentById(R.id.listFragment);
        viewerFragment = (ViewerFragment) manager.findFragmentById(R.id.viewerFragment);

        getSupportFragmentManager().beginTransaction().replace(R.id.listFragmentLayout, listFragment).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.viewerFragmentLayout, viewerFragment).commit();
    }

    @Override
    public void onImageSelected(int position) {
        viewerFragment.setImage(images[position]);
    }
}