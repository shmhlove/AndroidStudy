package com.mongonight.audiorecoder;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

import com.pedro.library.AutoPermissions;
import com.pedro.library.AutoPermissionsListener;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    MediaRecorder recoder;
    MediaPlayer player;

    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 첫번째 버튼 클릭시
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startRecoding();
            }
        });

        // 두번째 버튼 클릭시
        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopRecoding();
            }
        });

        // 세번째 버튼 클릭시
        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playAudio();
            }
        });

        // 네번째 버튼 클릭시
        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopAudio();
            }
        });

        String sdcard = Environment.getExternalStorageDirectory().getAbsolutePath();
        filename = sdcard + File.separator + "recorded.mp4";

        AutoPermissions.Companion.loadAllPermissions(this, 101);
    }

    public void startRecoding() {
        if (null == recoder) {
            recoder = new MediaRecorder();
        }

        recoder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recoder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recoder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);
        recoder.setOutputFile(filename);

        try {
            recoder.prepare();
            recoder.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopRecoding() {
        if (null == recoder) {
            return;
        }

        recoder.stop();
        recoder.release();
        recoder = null;
    }

    public void playAudio() {
        killPlayer();

        player = new MediaPlayer();
        try {
            player.setDataSource("file://" + filename);
            player.prepare();
            player.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopAudio() {
        killPlayer();
    }

    public void killPlayer() {
        if (null == player) {
            return;
        }

        player.stop();
        player.release();
        player = null;
    }
}