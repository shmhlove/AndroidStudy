package com.mongonight.thread;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MainHandler handler;
    Handler handler2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BackgroundThread thread = new BackgroundThread();
                thread.start();
            }
        });

        handler = new MainHandler();
        handler2 = new Handler();
    }

    class BackgroundThread extends Thread {
        int value = 0;

        public void run() {
            for (int i = 0; i < 100; ++i) {

                try {
                    Thread.sleep(1000);
                }
                catch (Exception e) {

                }

                ++value;
                Log.d("MyThread", "value : " + value);

                // 메인스레드에서 관리하는 view에 접근할 수 없음.
                // UI 스레드에서 접근할 수 있도록 핸들러 처리를 해보자
                // textView.setText("값 : " + value);

                Message message = handler.obtainMessage();
                Bundle bundle = new Bundle();
                bundle.putInt("value", value);
                message.setData(bundle);
                handler.sendMessage(message);

                // 복잡해지니깐 간단한 방법으로 가보자
                handler2.post(new Runnable() {
                    @Override
                    public void run() {
                        //textView.setText("값 : " + value);
                    }
                });

                // 더 간단한 방법으로
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("값 : " + value);
                    }
                });

                // handler2 방식으로 딜레이 줄수도 있음
                handler2.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("MyThread", "delayed thread : " + value);
                    }
                }, 5000);
            }
        }
    }

    class MainHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);

            //textView.setText("값 : " + msg.getData().getInt("value"));
        }
    }
}