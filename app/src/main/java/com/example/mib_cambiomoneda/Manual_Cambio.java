package com.example.mib_cambiomoneda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class Manual_Cambio extends AppCompatActivity {

    private String NombrePaisOrigen;
    private String NombrePaisDestino;
    private Spinner spinnerOrigenManual;
    private Spinner spinnerDestinoManual;
    private EditText numberOrigenManual;
    private EditText numberDestinoManual;
    private String Url="https://computacionmovilmib.000webhostapp.com/MIB/Insertar_Datos_Historial.php";
    private String paisOrigen;
    private String montoOrigen;
    private String paisDestino;
    private String montoDestino;
    private String Usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setContentView(R.layout.activity_manual__cambio);
        spinnerOrigenManual=findViewById(R.id.spinnerOrigenManual);
        spinnerDestinoManual=findViewById(R.id.spinnerDestinoManual);
        numberOrigenManual=findViewById(R.id.numberOrigenManual);
        numberDestinoManual=findViewById(R.id.numberDestinoManual);
        SharedPreferences prefs = getBaseContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        NombrePaisOrigen=prefs.getString("Pais","");
        Usuario=prefs.getString("Email","");
        spinnerOrigenManual.setSelection(obtenerPosicionItem(spinnerOrigenManual,NombrePaisOrigen));
        spinnerOrigenManual.setEnabled(false);
    }

    public static int obtenerPosicionItem(Spinner spinner, String NombrePais) {
        int posicion = 0;
        for (int i = 0; i < spinner.getCount(); i++) {
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(NombrePais)) {
                posicion = i;
            }
        }
        return posicion;
    }

    public void Restablecer(View view) {
        numberOrigenManual.setText("");
        numberDestinoManual.setText("");
    }
    public void Calcular(View view) {
        NombrePaisDestino=spinnerDestinoManual.getSelectedItem().toString().trim();
        String numberOrigen=numberOrigenManual.getEditableText().toString();
        if(numberOrigen.isEmpty()){
            Toast.makeText(this,"Ingrese monto origen",Toast.LENGTH_LONG).show();
        }else if(NombrePaisDestino.equals("Seleccione...")){
            Toast.makeText(this,"Ingrese pais de destino",Toast.LENGTH_LONG).show();
        }else {
            //aca Api de cambio
            float cambioOrigenDestino = (float) (Float.parseFloat(numberOrigen)*1.28611);
            numberDestinoManual.setText(Float.toString(cambioOrigenDestino));

            paisOrigen=NombrePaisOrigen;
            montoOrigen=numberOrigen;
            paisDestino=NombrePaisDestino;
            montoDestino=Float.toString(cambioOrigenDestino);
            DBVolley();
        }
    }
    public void DBVolley() {

        StringRequest request = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("datos insertados")) {
                } else {
                    Log.e("Error Volley", response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Manual_Cambio.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Email",Usuario);
                params.put("PaisOrigen", paisOrigen);
                params.put("MontoOrigen", montoOrigen);
                params.put("PaisDestino", paisDestino);
                params.put("MontoDestino", montoDestino);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Manual_Cambio.this);
        requestQueue.add(request);

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