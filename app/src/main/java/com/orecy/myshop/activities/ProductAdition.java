package com.orecy.myshop.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.orecy.myshop.R;
import com.orecy.myshop.database.DatabaseContract;

public class ProductAdition extends AppCompatActivity {
        Activity activity;
        Context context;


    // Objects
    private static final String TAG = ProductAdition.class.getSimpleName();

    // Views
    private EditText mProductNameEditText;
    private EditText mProductPriceEditText;
    private EditText mProductQuantityEditText;
    private ImageButton mQuantityMinusButton;
    private ImageButton mQuantityPlusButton;
    private EditText mSupplierNameEditText;
    private EditText mSupplierPhoneEditText;
    private EditText mSupplierEmailEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_adition);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        activity=this;
        context=this;

        findViews();

    }
    private void findViews() {
        mProductNameEditText = (EditText) findViewById(R.id.product_name_edit);
        mProductPriceEditText = (EditText) findViewById(R.id.price_edit);
        mProductQuantityEditText = (EditText) findViewById(R.id.quantity_edit);
        mQuantityMinusButton = (ImageButton) findViewById(R.id.decrease_quantity);
        mQuantityPlusButton = (ImageButton) findViewById(R.id.increase_quantity);
        mSupplierNameEditText = (EditText) findViewById(R.id.supplier_name_edit);
        mSupplierPhoneEditText = (EditText) findViewById(R.id.supplier_phone_edit);
        mSupplierEmailEditText = (EditText) findViewById(R.id.supplier_email_edit);

        mQuantityMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeQuantity();
            }
        });
        mQuantityPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addQuantity();
            }
        });

    }

    private void removeQuantity() {
        if (TextUtils.isEmpty(mProductQuantityEditText.getText().toString())) {
            mProductQuantityEditText.setText("0");
        }
        int currentQuantity = Integer.parseInt(mProductQuantityEditText.getText().toString());

        if (currentQuantity > 0){
            currentQuantity--;
            mProductQuantityEditText.setText(String.valueOf(currentQuantity));
        } else {
            Toast.makeText(this, R.string.can_not_set_negative_quantity, Toast.LENGTH_SHORT).show();
        }
    }

    private void addQuantity() {
        if (TextUtils.isEmpty(mProductQuantityEditText.getText().toString())) {
            mProductQuantityEditText.setText("0");
        }
        int currentQuantity = Integer.parseInt(mProductQuantityEditText.getText().toString());
        currentQuantity++;
        mProductQuantityEditText.setText(String.valueOf(currentQuantity));
    }


    private void addProduct(){
        String productName = mProductNameEditText.getText().toString();
        String productPrice = mProductPriceEditText.getText().toString();
        String productQuantity = mProductQuantityEditText.getText().toString();
        String supplierName = mSupplierNameEditText.getText().toString();
        String supplierPhone = mSupplierPhoneEditText.getText().toString();
        String supplierEmail = mSupplierEmailEditText.getText().toString();

        // Checking values are not empty
        if (TextUtils.isEmpty(productName)) {
            Toast.makeText(this, R.string.please_fill_product_name, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(productPrice)) {
            Toast.makeText(this, R.string.please_fill_price, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(productQuantity)) {
            Toast.makeText(this, R.string.please_fill_quantity, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(supplierName)) {
            Toast.makeText(this, R.string.please_fill_supplier_name, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(supplierPhone)) {
            Toast.makeText(this, R.string.please_fill_supplier_phone, Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(supplierEmail)) {
            Toast.makeText(this, R.string.please_fill_supplier_email, Toast.LENGTH_SHORT).show();
            return;
        }

        // Preparing values to insert
        ContentValues values = new ContentValues();
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_NAME, productName);
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_PRICE, productPrice);
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_QUANTITY, productQuantity);
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_IMAGE, 0);
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_NAME, supplierName);
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_EMAIL, supplierEmail);
        values.put(DatabaseContract.ProductEntry.COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER, supplierPhone);
        // Inserting

        Uri insertUri = getContentResolver().insert(DatabaseContract.ProductEntry.CONTENT_URI_PRODUCT, values);
        // Checking Insert
        if (insertUri == null){
            Log.d(TAG, "addProduct: Insert Failed!");
            Toast.makeText(this, R.string.saved_failed, Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "addProduct: Insert Successful");
            Toast.makeText(this, R.string.saved_successful, Toast.LENGTH_SHORT).show();
        }


        startActivity(new Intent(context,MainActivity.class));
        activity.finish();
    }

    // --------------
    // Menu Section
    // --------------
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_activity_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_image_pro_menu_button:
                Toast.makeText(context, R.string.image_added, Toast.LENGTH_SHORT).show();
                break;
            case R.id.save_product_menu_button:
                addProduct();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
        @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(context,MainActivity.class));
        activity.finish();
    }
}