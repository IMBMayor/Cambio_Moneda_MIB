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
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.mib_cambiomoneda.HistorialAdapter.ListAdapter;
import com.example.mib_cambiomoneda.HistorialAdapter.ListElement;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Historial extends AppCompatActivity {

    private RecyclerView rv;
    private ListAdapter la;
    private List<ListElement> elements;
    private String Url = "https://computacionmovilmib.000webhostapp.com/MIB/Mostrar_Historial.php";
    private String Usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SharedPreferences prefs = getBaseContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        Usuario=prefs.getString("Email","");
        rv = (RecyclerView) findViewById(R.id.recyclerviewListado);
        elements = new ArrayList<>();
        datos();

    }

    private void datos(){
        StringRequest request = new StringRequest(Request.Method.POST, Url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    String success = jsonObject.getString("success");
                    JSONArray jsonArray = jsonObject.getJSONArray("Tb_Historial_MIB");
                    if (success.equals("1")) {
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject object = jsonArray.getJSONObject(i);
                            String paisOrigen = object.getString("PaisOrigen");
                            String montoOrigen = object.getString("MontoOrigen");
                            String paisDestino = object.getString("PaisDestino");
                            String montoDestino = object.getString("MontoDestino");
                            elements.add(new ListElement("#00796B",paisOrigen,montoOrigen,paisDestino,montoDestino));
                            LinearLayoutManager llm = new LinearLayoutManager(Historial.this);
                            la= new ListAdapter(elements, Historial.this);
                            llm.setOrientation(LinearLayoutManager.VERTICAL);
                            rv.setLayoutManager(llm);
                            rv.setHasFixedSize(true);
                            rv.setAdapter(la);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Historial.this, error.getMessage(), Toast.LENGTH_LONG).show();
            }
        }){
                @Override
                protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("Email", Usuario);
                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(Historial.this);
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