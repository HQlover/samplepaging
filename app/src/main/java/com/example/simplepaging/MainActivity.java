package com.example.simplepaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simplepaging.ui.basic.BasicUsageActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBasicUsage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClickEvent();
    }

    private void initView() {
        mBasicUsage = findViewById(R.id.mBtnBasicUsage);
    }

    private void initClickEvent() {
        mBasicUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BasicUsageActivity.class));
            }
        });
    }
}