package com.example.mib_cambiomoneda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mib_cambiomoneda.Modelo.Paises;
import com.example.mib_cambiomoneda.Modelo.PaisesDataSource;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private String Url = "https://computacionmovilmib.000webhostapp.com/MIB/Mostrar_Registro_Usuario.php";
    private EditText usernameEditText;
    private EditText passwordEditText;
    private PaisesDataSource dataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        usernameEditText = findViewById(R.id.username);
        passwordEditText = findViewById(R.id.password);
        dataSource = new PaisesDataSource(this);
        crearContacto();
    }

    public void Ingresar(View view) {
        final String inputEmail = usernameEditText.getText().toString();
        final String inputPassword = passwordEditText.getText().toString();

        StringRequest request = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String succes = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("Tb_Registro_Usuario_MIB");
                    if (succes.equals("1")) {
                        if(jsonArray.length()==0){
                            Toast.makeText(MainActivity.this, "Usuario no registrado", Toast.LENGTH_LONG).show();
                        }else{
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String Nombre = object.getString("Nombre");
                                String Password = object.getString("Password");
                                String Email = object.getString("Email");
                                String Pais = object.getString("Pais");
                                if (Password.equals(inputPassword)) {
                                    Intent intent = new Intent(MainActivity.this, Home.class);
                                    SharedPreferences prefs = getBaseContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor=prefs.edit();
                                    editor.putString("Nombre",Nombre).apply();
                                    editor.putString("Pais",Pais).apply();
                                    editor.putString("Email",Email).apply();
                                    editor.apply();
                                    startActivity(intent);
                                    Toast.makeText(MainActivity.this, "Bienvenido " + Nombre, Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(MainActivity.this, "Contraseña incorrecta", Toast.LENGTH_LONG).show();
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
                Toast.makeText(MainActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Email", usernameEditText.getText().toString());
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);
    }

    public void Registrar(View view) {
        Intent intent = new Intent(this, Registrar.class);
        startActivity(intent);
    }

    public void crearContacto(){

        Paises Argentina=new Paises();

        Argentina.setNombre("Argentina");
        Argentina.setSiglas("ARS");
        Argentina.setPrecio(0.11);

        Paises Bolivia=new Paises();

        Bolivia.setNombre("Bolivia");
        Bolivia.setSiglas("BOB");
        Bolivia.setPrecio(0.01);

        Paises Brasil=new Paises();

        Brasil.setNombre("Brasil");
        Brasil.setSiglas("BRL");
        Brasil.setPrecio(0.01);

        Paises Chile=new Paises();

        Chile.setNombre("Chile");
        Chile.setSiglas("CHL");
        Chile.setPrecio(1.00);

        Paises Colombia=new Paises();

        Colombia.setNombre("Colombia");
        Colombia.setSiglas("COP");
        Colombia.setPrecio(5.00);

        Paises Estados_Unidos=new Paises();

        Estados_Unidos.setNombre("Estados Unidos");
        Estados_Unidos.setSiglas("EEUU");
        Estados_Unidos.setPrecio(0.0014);

        Paises Peru=new Paises();

        Peru.setNombre("Perú");
        Peru.setSiglas("PEN");
        Peru.setPrecio(0.0049);

        Paises Uruguay=new Paises();

        Uruguay.setNombre("Uruguay");
        Uruguay.setSiglas("UYU");
        Uruguay.setPrecio(0.058);

        dataSource.openDB();
        Argentina = dataSource.insertPaises(Argentina);
        dataSource.closeDB();
        dataSource.openDB();
        Bolivia = dataSource.insertPaises(Bolivia);
        dataSource.closeDB();
        dataSource.openDB();
        Brasil = dataSource.insertPaises(Brasil);
        dataSource.closeDB();
        dataSource.openDB();
        Chile = dataSource.insertPaises(Chile);
        dataSource.closeDB();
        dataSource.openDB();
        Colombia = dataSource.insertPaises(Colombia);
        dataSource.closeDB();
        dataSource.openDB();
        Estados_Unidos = dataSource.insertPaises(Estados_Unidos);
        dataSource.closeDB();
        dataSource.openDB();
        Peru = dataSource.insertPaises(Peru);
        dataSource.closeDB();
        dataSource.openDB();
        Uruguay = dataSource.insertPaises(Uruguay);
        dataSource.closeDB();

    }
}