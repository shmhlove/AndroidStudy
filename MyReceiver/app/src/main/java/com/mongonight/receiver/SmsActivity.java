package com.mongonight.receiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SmsActivity extends AppCompatActivity {

    TextView textView_number;
    TextView textView_contents;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);

        textView_number = findViewById(R.id.textView_number);
        textView_contents = findViewById(R.id.textView_contents);

        Intent intent = getIntent();
        processIntent(intent);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);

        processIntent(intent);
    }

    private void processIntent(Intent intent) {
        if (null != intent) {
            String sender = intent.getStringExtra("sender");
            String contents = intent.getStringExtra("contents");

            textView_number.setText(sender);
            textView_contents.setText(contents);
        }
    }
}