package com.github.pro_arm;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();

//        Intent intent = new Intent();
//        intent.putExtra(AppConst.IP_EXTRAS,"192.168.1.9")
//                .putExtra(AppConst.PORT,8080);
//
//        TCPClientService.enqueueWork(this,intent);
    }
}