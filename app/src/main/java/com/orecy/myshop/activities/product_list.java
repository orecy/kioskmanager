package com.orecy.myshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.orecy.myshop.R;

public class product_list extends AppCompatActivity {
    Activity activity;
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        activity=this;
        context=this;
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(context,MainActivity.class));
        activity.finish();
    }
}