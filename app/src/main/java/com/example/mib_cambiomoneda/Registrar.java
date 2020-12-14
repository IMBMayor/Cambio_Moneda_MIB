package com.example.mib_cambiomoneda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private String UrlUserRegistrado="https://computacionmovilmib.000webhostapp.com/MIB/Mostrar_Registro_Usuario.php";
    private Boolean flagUser=false;


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
        //UsuarioRegistrado();
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
            /*if(flagUser){


            }else{
                Toast.makeText(this,"Usuario Registrado",Toast.LENGTH_LONG).show();
                Intent intent=new Intent(Registrar.this,MainActivity.class);
                startActivity(intent);
                finish();
            }*/

        }
    }
    public void DBVolley() {
        StringRequest request = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response.equalsIgnoreCase("datos insertados")) {
                    Toast.makeText(Registrar.this, "Usuario Registrado correctamente", Toast.LENGTH_LONG).show();
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
    private void UsuarioRegistrado(){
        StringRequest request = new StringRequest(Request.Method.POST, UrlUserRegistrado, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("Tb_Registro_Usuario_MIB");

                    if (succes.equals("1")) {
                        if(jsonArray.length()==0){
                            flagUser=true;
                        }else{
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String Email = object.getString("Email");
                                if(Email.equals(Registrar.this.Email)){
                                    flagUser=false;
                                    }
                                }
                            }
                        }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registrar.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Email", Registrar.this.Email);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Registrar.this);
        requestQueue.add(request);
    }
}