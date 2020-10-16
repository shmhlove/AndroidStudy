package com.mangonight.database;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    EditText editText2;
    TextView textView;

    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editTextTextPersonName);
        editText2 = findViewById(R.id.editTextTextPersonName2);
        textView = findViewById(R.id.textView);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String databaseName = editText.getText().toString();
                createDatabase(databaseName);
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tableName = editText2.getText().toString();
                createTable(tableName);
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tableName = editText2.getText().toString();
                insertRecode(tableName);
            }
        });
    }

    public void createDatabase(String databaseName) {
        println("createDatabase 호출됨");

        try {
            database = openOrCreateDatabase(databaseName, MODE_PRIVATE, null);
            println("데이터베이스 생성됨 : " + databaseName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createTable(String tableName) {
        println("createTable 호출됨");

        if (null == database) {
            println("데이터베이스를 먼저 열어주세요.");
            return;
        }

        try {
            String sql = "create table if not exists " + tableName + "(_id integer PRIMARY KEY autoincrement, name text, age integer, mobile text)";
            database.execSQL(sql);
            println("테이블 생성됨 : " + tableName);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertRecode(String tableName) {
        println("insertRecode 호출됨");

        if (null == database) {
            println("데이터베이스를 먼저 열어주세요.");
            return;
        }

        if (null == tableName) {
            println("테이블 이름을 입력하세요.");
            return;
        }

        try {
            String sql = "insert into " + tableName + "(name, age, mobile) values ('SangHo', 39, '010-3622-2209')";
            database.execSQL(sql);
            println("레코드 추가함");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}