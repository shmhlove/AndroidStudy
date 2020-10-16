package com.mangonight.provider;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);

        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPerson();
            }
        });

        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                queryPerson();
            }
        });

        Button button3 = findViewById(R.id.button3);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updatePerson();
            }
        });

        Button button4 = findViewById(R.id.button4);
        button4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePerson();
            }
        });
    }

    public void insertPerson() {
        println("insertPerson");

        String uriStr = "content://com.mangonight.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        String[] columns = cursor.getColumnNames();
        for (int i = 0; i < columns.length; ++i) {
            println("#" + i + " : " + columns[i]);
        }

        ContentValues values = new ContentValues();
        values.put("name", "SangHo");
        values.put("age", "39");
        values.put("mobile", "010-3622-2209");

        uri = getContentResolver().insert(uri, values);
        println("insert 결과 : " + uri.toString());
    }

    public void queryPerson()
    {
        String uriStr = "content://com.mangonight.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        String[] colums = new String[] {"name", "age", "mobile"};
        Cursor cursor = getContentResolver().query(uri, colums, null, null);
        println("query 결과 : " + cursor.getCount());

        int index = 0;
        while(cursor.moveToNext()) {
            String name = cursor.getString(cursor.getColumnIndex(colums[0]));
            int age = cursor.getInt(cursor.getColumnIndex(colums[1]));
            String mobile = cursor.getString(cursor.getColumnIndex(colums[2]));

            println("#" + index + " -> " + name + ", " + age + ", " + mobile);
            ++index;
        }
    }

    public void updatePerson() {
        String uriStr = "content://com.mangonight.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        String selection = "mobile = ?";
        String[] selectionArgs = new String[] {"010-3622-2209"};
        ContentValues updateValues = new ContentValues();
        updateValues.put("mobile", "010-3333-5555");
        int cnt = getContentResolver().update(uri, updateValues, selection, selectionArgs);
        println("updatePerson 결과 : " + cnt);
    }

    public void deletePerson() {
        String uriStr = "content://com.mangonight.provider/person";
        Uri uri = new Uri.Builder().build().parse(uriStr);

        String selection = "name = ?";
        String[] selectionArgs = new String[] {"SangHo"};

        int cnt = getContentResolver().delete(uri, selection, selectionArgs);
        println("deletePerson 결과 : " + cnt);
    }

    public void println(String data) {
        textView.append(data + "\n");
    }
}