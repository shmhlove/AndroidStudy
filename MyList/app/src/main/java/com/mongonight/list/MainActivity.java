package com.mongonight.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    PersonAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
//        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new PersonAdapter();

        adapter.addItem(new Person("이상호1", "010-3622-2209"));
        adapter.addItem(new Person("이상호2", "010-3622-2209"));
        adapter.addItem(new Person("이상호3", "010-3622-2209"));
        adapter.addItem(new Person("이상호4", "010-3622-2209"));
        adapter.addItem(new Person("이상호5", "010-3622-2209"));
        adapter.addItem(new Person("이상호6", "010-3622-2209"));
        adapter.addItem(new Person("이상호7", "010-3622-2209"));
        adapter.addItem(new Person("이상호8", "010-3622-2209"));
        adapter.addItem(new Person("이상호9", "010-3622-2209"));
        adapter.addItem(new Person("이상호10", "010-3622-2209"));
        adapter.addItem(new Person("이상호11", "010-3622-2209"));
        adapter.addItem(new Person("이상호12", "010-3622-2209"));
        adapter.addItem(new Person("이상호13", "010-3622-2209"));
        adapter.addItem(new Person("이상호14", "010-3622-2209"));
        adapter.addItem(new Person("이상호15", "010-3622-2209"));
        adapter.addItem(new Person("이상호16", "010-3622-2209"));
        adapter.addItem(new Person("이상호17", "010-3622-2209"));
        adapter.addItem(new Person("이상호18", "010-3622-2209"));

        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(new OnPersonItemClickListener() {
            @Override
            public void onItemClick(PersonAdapter.ViewHolder holder, View view, int position) {
                showToast("아이템 선택 됨 : " + adapter.getItem(position).getName());
            }
        });
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}