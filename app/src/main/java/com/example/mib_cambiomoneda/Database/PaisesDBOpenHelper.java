package com.example.mib_cambiomoneda.Database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class PaisesDBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "paises.db";
    private static final int VERSION = 1;

    private static final String CREATE_TABLE_PAISES = "CREATE TABLE PAISES (" +
            "id INTEGER PRIMARY KEY autoincrement," +
            "Nombre TEXT," +
            "Siglas TEXT," +
            "Precio DOUBLE)";


    public PaisesDBOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PAISES);
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
