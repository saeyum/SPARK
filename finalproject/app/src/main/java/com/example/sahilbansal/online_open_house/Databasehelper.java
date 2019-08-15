package com.example.sahilbansal.online_open_house;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Databasehelper extends SQLiteOpenHelper{

    public static final String DB_NAME = "ADMIN";
    public static final String TABLE_NAME = "Login";
    public static final String COLUMN_ID = "id";
    public static final String COLUMN_EMAIL = "email";
    public static final String COLUMN_PASS = "password";
    public static final String COLUMN_UID = "uid";
    public static final String COLUMN_FLAG ="flag" ;
    public static final int DB_VERSION = 1;

    public Databasehelper(Context context) {
        super(context , DB_NAME,null,DB_VERSION);
    }

    public Databasehelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " +TABLE_NAME
                +"(" +COLUMN_ID+
                " INTEGER PRIMARY KEY AUTOINCREMENT, " +COLUMN_EMAIL+
                " VARCHAR, " +COLUMN_PASS+
                " VARCHAR, " +COLUMN_UID+
                " VARCHAR, " +COLUMN_FLAG+
                " VARCHAR);";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS Login";
        db.execSQL(sql);
        onCreate(db);


    }

    public boolean addperson()
    {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();


        contentValues.put(COLUMN_EMAIL,"admin@gmail.com");

        contentValues.put(COLUMN_PASS,"admin@123");
        contentValues.put(COLUMN_UID,"E1234");
        contentValues.put(COLUMN_FLAG,"login");


        db.insert(TABLE_NAME, null, contentValues);

        db.close();

        return true;
    }
    public Cursor getPerson(int id)
    {

        SQLiteDatabase db = this.getReadableDatabase();

        String sql = "SELECT * FROM Login WHERE id="+"id"+";";

        Cursor c = db.rawQuery(sql, null);

        return c;
    }
}
