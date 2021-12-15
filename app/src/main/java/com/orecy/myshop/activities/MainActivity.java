package com.orecy.myshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.orecy.myshop.R;
import com.orecy.myshop.database.DatabaseHelper;

public class MainActivity extends AppCompatActivity {
       LinearLayout addpro,delete,productlist,inventory;
       Activity activity;
       Context context;
    public  SQLiteDatabase database;
    static DatabaseHelper dbOpenHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_COARSE_LOCATION}, 1);

        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        activity =this;
        context =this;
        dbOpenHelper = new DatabaseHelper(this);
        database =dbOpenHelper.getInstanceOfDB();

        addpro=(LinearLayout)findViewById(R.id.addpro);
        delete=(LinearLayout)findViewById(R.id.delete);
        productlist=(LinearLayout)findViewById(R.id.productlist);
        inventory=(LinearLayout)findViewById(R.id.inventory);

        addpro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,ProductAdition.class));
               activity.finish();
            }
        });
        productlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,product_list.class));
               activity.finish();
            }
        });
        inventory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,product_list.class));
               activity.finish();
            }
        });


    }
}