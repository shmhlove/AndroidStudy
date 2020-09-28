package com.mongonight.intent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 다른 Activity에 데이터 전달
                Intent intent = new Intent();
                intent.putExtra("name", "mike");

                // 이 Activity를 시작시킨 곳으로 결과값 반환
                setResult(RESULT_OK, intent);

                // 현재 Activity 닫기
                finish();
            }
        });
    }
}