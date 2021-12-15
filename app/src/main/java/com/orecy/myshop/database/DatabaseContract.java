package com.orecy.myshop.database;

import android.net.Uri;
import android.provider.BaseColumns;

public class DatabaseContract {
    // So no one create an object from this class
    private DatabaseContract(){}

    // Content Provider
    public static final String CONTENT_AUTHORITY = "com.orecy.myshop";

    // Base URI's
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    // Possible Paths (just one in this case)
    public static final String PATH_PRODUCTS = "products";


    public static final class ProductEntry implements BaseColumns {

        // Product Table Uri
        public static final Uri CONTENT_URI_PRODUCT = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_PRODUCTS);

        // Product Table
        public static final String _ID = BaseColumns._ID;
        public static final String PRODUCT_TABLE_NAME = "products";
        public static final String COLUMN_PRODUCT_NAME = "productname";
        public static final String COLUMN_PRODUCT_PRICE = "price";
        public static final String COLUMN_PRODUCT_QUANTITY = "quantity";
        public static final String COLUMN_PRODUCT_IMAGE = "image";
        public static final String COLUMN_PRODUCT_SUPPLIER_NAME = "product_supplier_name";
        public static final String COLUMN_PRODUCT_SUPPLIER_EMAIL = "product_supplier_email";
        public static final String COLUMN_PRODUCT_SUPPLIER_PHONE_NUMBER = "product_supplier_phone_number";

    }
}
