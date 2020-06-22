package com.example.simplepaging;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.simplepaging.ui.basic.BasicUsageActivity;
import com.example.simplepaging.ui.header_proxy.HeaderProxyActivity;
import com.example.simplepaging.ui.header_simple.HeaderSimpleActivity;

public class MainActivity extends AppCompatActivity {
    private Button mBasicUsage;
    private Button mHeaderSimple;
    private Button mHeaderProxy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClickEvent();
    }

    private void initView() {
        mBasicUsage = findViewById(R.id.mBtnBasicUsage);
        mHeaderSimple=findViewById(R.id.mBtnHeaderMultiType);
        mHeaderProxy=findViewById(R.id.mBtnHeaderProxy);
    }

    private void initClickEvent() {
        mBasicUsage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, BasicUsageActivity.class));
            }
        });

        mHeaderSimple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HeaderSimpleActivity.class));
            }
        });

        mHeaderProxy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, HeaderProxyActivity.class));
            }
        });
    }
}