package com.example.mib_cambiomoneda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

public class Registrar extends AppCompatActivity {

    private EditText textNombreRegistrar;
    private EditText editTextTextPassword;
    private EditText editTextTextEmailAddress;
    private Spinner spnPaisRegistrar;
    private String Nombre;
    private String Password;
    private String Email;
    private String Pais;
    private String Url="https://computacionmovilmib.000webhostapp.com/MIB/Insertar_Registro_Usuario.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        textNombreRegistrar = findViewById(R.id.textNombreRegistrar);
        editTextTextPassword = findViewById(R.id.editTextTextPasswordRegistrar);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddressRegistrar);
        spnPaisRegistrar = findViewById(R.id.spnPaisRegistrar);
    }

    public void AceptarRegistro(View view) {

        Nombre = textNombreRegistrar.getText().toString();
        Password = editTextTextPassword.getText().toString().trim();
        Email = editTextTextEmailAddress.getText().toString().trim();
        Pais = spnPaisRegistrar.getSelectedItem().toString().trim();

        if (Nombre.isEmpty()) {
            Toast.makeText(Registrar.this, "Ingresar Nombre: " + Nombre, Toast.LENGTH_LONG).show();
        } else if (Password.isEmpty()) {
            Toast.makeText(Registrar.this, "Ingresar Password", Toast.LENGTH_LONG).show();
        } else if (Email.isEmpty()) {
            Toast.makeText(Registrar.this, "Ingresar Email", Toast.LENGTH_LONG).show();
        } else if (Pais.equals("Seleccione...")) {
            Toast.makeText(Registrar.this, "Ingresar Pais", Toast.LENGTH_LONG).show();
        } else {
            DBVolley();

        }
    }

    public void DBVolley() {

        StringRequest request = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("datos insertados")) {
                    Toast.makeText(Registrar.this, "Usuario Registrado correctamente", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(Registrar.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    Log.e("Error Volley", response);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registrar.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Nombre", Nombre);
                params.put("Password", Password);
                params.put("Email", Email);
                params.put("Pais", Pais);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Registrar.this);
        requestQueue.add(request);

    }

}