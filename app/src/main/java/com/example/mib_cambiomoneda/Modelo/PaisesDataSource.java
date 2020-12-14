package com.example.mib_cambiomoneda.Modelo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.mib_cambiomoneda.Database.PaisesDBOpenHelper;
import java.util.ArrayList;
import java.util.List;

public class PaisesDataSource{

    SQLiteOpenHelper dbhelper;
    SQLiteDatabase database;

    public PaisesDataSource (Context context){
        dbhelper = new PaisesDBOpenHelper(context);
    }
    public void openDB(){
        database = dbhelper.getWritableDatabase();
    }
    public void closeDB(){
        dbhelper.close();
    }


    /*public Paises obtenerPaisOrigen(String NombrePaisOri){

        Paises paisOrigen = new Paises();
        String query="SELECT * FROM PAISES where Nombre='"+NombrePaisOri+"'";

        Cursor cursor= database.rawQuery(query,null);

        if(cursor.getCount() > 0 ){
            while (cursor.moveToNext()){
                paisOrigen.setNombre(cursor.getString(cursor.getColumnIndex("Nombre")));
                paisOrigen.setSiglas(cursor.getString(cursor.getColumnIndex("Siglas")));
                paisOrigen.setPrecio(cursor.getFloat(cursor.getColumnIndex("Precio")));
            }
        }

        return paisOrigen;
    }*/

    public Paises obtenerPaisDestino(String NombrePaisDes){

        Paises paisDestino = new Paises();
        String query="SELECT * FROM PAISES where Nombre='"+NombrePaisDes+"'";

        Cursor cursor= database.rawQuery(query,null);

        if(cursor.getCount() > 0 ){
            while (cursor.moveToNext()){
                paisDestino.setNombre(cursor.getString(cursor.getColumnIndex("Nombre")));
                paisDestino.setSiglas(cursor.getString(cursor.getColumnIndex("Siglas")));
                paisDestino.setPrecio(cursor.getFloat(cursor.getColumnIndex("Precio")));
            }
        }

        return paisDestino;
    }

    public Paises insertPaises(Paises paises){
        ContentValues valores = new ContentValues();
        valores.put("Nombre",paises.getNombre());
        valores.put("Siglas",paises.getSiglas());
        valores.put("Precio",paises.getPrecio());

        long paisesid = database.insert("paises",null,valores);
        paises.setId(paisesid);
        return paises;
    }
}
