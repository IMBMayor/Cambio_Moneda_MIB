package com.example.mib_cambiomoneda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class Home extends AppCompatActivity {
    private TextView Usuario;
    private String NombreUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Usuario=findViewById(R.id.Usuario);
        SharedPreferences prefs = getBaseContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        NombreUser=prefs.getString("Nombre","");
        Usuario.setText("Bienvenido "+NombreUser);

    }

    public void Geo_Cambio(View view) {
        Intent intent=new Intent(this,GEO_Cambio.class);
        startActivity(intent);
    }

    public void Manual_Cambio(View view) {
        Intent intent=new Intent(this,Manual_Cambio.class);
        startActivity(intent);
    }

    public void Historial(View view) {
        Intent intent=new Intent(this,Historial.class);
        startActivity(intent);
    }

    public void Perfil(View view) {
        Intent intent=new Intent(this,Perfil.class);
        startActivity(intent);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){

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