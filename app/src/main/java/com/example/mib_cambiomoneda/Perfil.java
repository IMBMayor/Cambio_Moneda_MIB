package com.example.mib_cambiomoneda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toolbar;

import com.example.mib_cambiomoneda.PerfilAdapter.ListAdapter;
import com.example.mib_cambiomoneda.PerfilAdapter.ListElement;

import java.util.ArrayList;
import java.util.List;

public class Perfil extends AppCompatActivity {

    private String NombreUsuario;
    private String EmailUsuario;
    private String PaisUsuario;
    private RecyclerView rv;
    private ListAdapter la;
    private List<ListElement> elements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        rv = (RecyclerView) findViewById(R.id.recyclerviewPerfil);
        SharedPreferences prefs = getBaseContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        NombreUsuario=prefs.getString("Nombre","");
        EmailUsuario=prefs.getString("Email","");
        PaisUsuario=prefs.getString("Pais","");

        elements = new ArrayList<>();
        elements.add(new ListElement("#00796B","Usuario: "+NombreUsuario,"Email: "+EmailUsuario,"Pais: "+PaisUsuario));
        LinearLayoutManager llm = new LinearLayoutManager(Perfil.this);
        la= new ListAdapter(elements, Perfil.this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(llm);
        rv.setHasFixedSize(true);
        rv.setAdapter(la);

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_app,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

            case R.id.Home:
                Intent intentHome=new Intent(this,Home.class);
                startActivity(intentHome);
                break;
            case R.id.GEO_Cambio:
                Intent intentGeo_Cambio=new Intent(this,GEO_Cambio.class);
                startActivity(intentGeo_Cambio);
                break;
            case  R.id.Manual_Cambio:
                Intent intentManual_Cambio=new Intent(this,Manual_Cambio.class);
                startActivity(intentManual_Cambio);
                break;
            case R.id.Historial:
                Intent intentHistorial=new Intent(this,Historial.class);
                startActivity(intentHistorial);
                break;
            case R.id.Perfil:
                Intent intentPerfil=new Intent(this,Perfil.class);
                startActivity(intentPerfil);
                break;
            case R.id.Salir:
                Intent intentLogin=new Intent(this,MainActivity.class);
                startActivity(intentLogin);
                finish();
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}