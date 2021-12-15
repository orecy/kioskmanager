package com.orecy.myshop.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.util.Log;

import com.orecy.myshop.util.Config;
import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


public class DatabaseHelper extends SQLiteOpenHelper {


    // SELECT qb.Ex_id,Q_id FROM questionBank as qb inner join difficulty as d
    // on qb.Ex_id = d.ex_id where level_of_difficulty = '1'

//    private static String DB_PATH = "/data/data/com.orecy.myshop/databases/";
    private static String DB_PATH = "";

    private static String DB_NAME = "myxiosk.db";

    private SQLiteDatabase myDataBase;

    private final Context context;
    static int correctAnswerCount;

    /**
     * Constructor Takes and keeps a reference of the passed context in order to
     * access to the application assets and resources.
     *
     * @param context
     */
    public DatabaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.context = context;
        DB_PATH=context.getExternalFilesDir(null).getAbsolutePath() + "/" ;
        openDB();

    }

    /**
     * Creates a empty database on the system and rewrites it with your own
     * database.
     * */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // do nothing - database already exist
        } else {

            // By calling this method and empty database will be created into
            // the default system path
            // of your application so we are gonna be able to overwrite that
            // database with our database.
            // this.getReadableDatabase(); // changed to write
            this.getWritableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each
     * time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        File dbFile = new File(DB_PATH+DB_NAME );
        return dbFile.exists();

        // SQLiteDatabase checkDB = null;
        //
        // try {
        // String myPath = DB_PATH + DB_NAME;
        // checkDB = SQLiteDatabase.openDatabase(myPath, null,
        // SQLiteDatabase.OPEN_READWRITE); // changing to writable
        //
        // } catch (SQLiteException e) {
        //
        // // database does't exist yet.
        //
        // }
        //
        // if (checkDB != null) {
        //
        // checkDB.close();
        //
        // }
        //
        // return checkDB != null ? true : false;
    }

    /*
     * (non-Javadoc)
     *
     * @see android.database.sqlite.SQLiteOpenHelper#getReadableDatabase()
     */
    @Override
    public synchronized SQLiteDatabase getReadableDatabase() {
        return super.getReadableDatabase();
    }

    @Override
    public synchronized SQLiteDatabase getWritableDatabase() {
        // TODO Auto-generated method stub
        return super.getWritableDatabase();
    }

    /**
     * Copies your database from your local assets-folder to the just created
     * empty database in the system folder, from where it can be accessed and
     * handled. This is done by transfering bytestream.
     * */
    private void copyDataBase() throws IOException {

        // Open your local db as the input stream
        InputStream myInput = context.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        // Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        // transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        // Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        // Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE // this was readable only
                        | SQLiteDatabase.NO_LOCALIZED_COLLATORS);

    }

    public SQLiteDatabase getInstanceOfDB() {
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE // this was readable only
                        | SQLiteDatabase.NO_LOCALIZED_COLLATORS);
        return myDataBase;
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    // Add your public helper methods to access and get content from the
    // database.
    // You could return cursors by doing "return myDataBase.query(....)" so it'd
    // be easy
    // to you to create adapters for your views.

    /**
     * my own open method, makes a call to createDatabase, opendatabase,
     * getReadableDatabase methods present in DatabaseHelper.java
     *
     */
    public void openDB() {
        try {
            createDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            Log.e("DataBaseHelper", e.toString());
        }

        openDataBase();
        getWritableDatabase();

    }

    /**
     * closes db, makes a call to close() method in DatabaseHelper.java
     *
     */
    public void closeDB() {
        close();
    }
}
