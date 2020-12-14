package com.example.mib_cambiomoneda.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.mib_cambiomoneda.Modelo.Paises;

public class PaisesDBOpenHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "paises.db";
    private static final int VERSION = 1;

    private static final String CREATE_TABLE_PAISES = "CREATE TABLE PAISES (" +
            "id INTEGER PRIMARY KEY autoincrement," +
            "Nombre TEXT," +
            "Siglas TEXT," +
            "Precio FLOAT)";
    private static final String INSERT_PAIS_Argentina="INSERT INTO PAISES (Nombre,Siglas,Precio) VALUES ('Argentina','ARS',0,11) ";
    private static final String INSERT_PAIS_Bolivia="INSERT INTO PAISES (Nombre,Siglas,Precio) VALUES ('Bolivia','BOB',0,01) ";
    private static final String INSERT_PAIS_Brasil="INSERT INTO PAISES (Nombre,Siglas,Precio) VALUES ('Brasil','BRL',0,01) ";
    private static final String INSERT_PAIS_Chile="INSERT INTO PAISES (Nombre,Siglas,Precio) VALUES ('Chile','CHL',1)";
    private static final String INSERT_PAIS_Colombia="INSERT INTO PAISES (Nombre,Siglas,Precio) VALUES ('Colombia','COP',5.00) ";
    private static final String INSERT_PAIS_Estados_Unidos="INSERT INTO PAISES (Nombre,Siglas,Precio) VALUES ('Estados Unidos','EEUU',0.0014)";
    private static final String INSERT_PAIS_Peru="INSERT INTO PAISES (Nombre,Siglas,Precio) VALUES ('Perú','PEN',0.0049) ";
    private static final String INSERT_PAIS_Uruguay="INSERT INTO PAISES (Nombre,Siglas,Precio) VALUES ('Uruguay','UYU',0.058) ";


    public PaisesDBOpenHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }


    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_PAISES);
        db.execSQL(INSERT_PAIS_Argentina);
        db.execSQL(INSERT_PAIS_Bolivia);
        db.execSQL(INSERT_PAIS_Brasil);
        db.execSQL(INSERT_PAIS_Chile);
        db.execSQL(INSERT_PAIS_Colombia);
        db.execSQL(INSERT_PAIS_Estados_Unidos);
        db.execSQL(INSERT_PAIS_Peru);
        db.execSQL(INSERT_PAIS_Uruguay);
    }


    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {

    }
}
