package com.mongonight.audioplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    String audioUrl = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    MediaPlayer player;
    int pausePosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio();
            }
        });

        Button button1 = findViewById(R.id.button2);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });

        Button button2 = findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pauseAudio();
            }
        });

        Button button3 = findViewById(R.id.button4);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resumeAudio();
            }
        });
    }

    public void playAudio() {
        showToast("음악파일 재생 호출 됨");

        killPlayer();

        player = new MediaPlayer();

        try {
            if (!player.isPlaying()) {
                player.setDataSource(audioUrl);
                player.prepare();
                player.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopAudio() {
        showToast("음악파일 정지 호출 됨");

        if (null == player) {
            return;
        }

        try {
            if (player.isPlaying()) {
                player.stop();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pauseAudio() {
        showToast("음악파일 일시정지 호출 됨");

        if (null == player) {
            return;
        }

        try {
            if (player.isPlaying()) {
                pausePosition = player.getCurrentPosition();
                player.pause();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void resumeAudio() {
        showToast("음악파일 재시작 호출 됨");

        if (null == player) {
            return;
        }

        try {
            if (!player.isPlaying()) {
                player.start();
                player.seekTo(pausePosition);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    public void killPlayer() {
        if (null != player) {
            try {
                player.release();
                player = null;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}