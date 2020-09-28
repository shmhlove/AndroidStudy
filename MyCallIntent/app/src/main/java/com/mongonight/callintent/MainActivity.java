package com.mongonight.callintent;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:010-1000-1000"));
                startActivity(intent);

                // 명시적으로 액티비티 패키지명으로 액티비티 띄우기
//                Intent intent = new Intent();
//                ComponentName name = new ComponentName("org.mongonight.callintent", "org.mongonight.callintent.MenuActivity");
//                intent.setComponent(name);
//                startActivityForResult(intent, 101);
            }
        });
    }
}