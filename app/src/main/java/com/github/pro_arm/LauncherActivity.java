package com.github.pro_arm;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LauncherActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setTheme(R.style.Theme_pro_arm);
        super.onCreate(savedInstanceState);

        startActivity(new Intent(this,MainActivity.class));
        finish();
    }
}
