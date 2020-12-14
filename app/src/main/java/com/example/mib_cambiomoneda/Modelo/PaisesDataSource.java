package com.example.mib_cambiomoneda.Modelo;

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


    public List<Paises> obtenerContactos(String NombrePaisDes, String NombrePaisOri){

        List<Paises> paises = new ArrayList<>();
        String query="SELECT * FROM PAISES where NombrePaisOrigen=NombrePaisOri and NombrePaisDestino=NombrePaisDes";

        Cursor cursor= database.rawQuery(query,null);

        if(cursor.getCount() > 0 ){
            while (cursor.moveToNext()){
                Paises pais = new Paises();
                pais.setNombre(cursor.getString(cursor.getColumnIndex("Nombre")));
                pais.setSiglas(cursor.getString(cursor.getColumnIndex("Siglas")));
                pais.setPrecio(cursor.getFloat(cursor.getColumnIndex("Precio")));
                paises.add(pais);
            }
        }

        return paises;
    }
}
