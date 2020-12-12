package com.example.mib_cambiomoneda;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
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
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;
import java.util.Map;

public class GEO_Cambio extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mapa;
    private String NombrePaisOrigen;
    private String NombrePaisDestino;
    private Marker marcador;
    private double lat;
    private double lng;
    private Spinner spinnerOrigenGeo;
    private Spinner spinnerDestinoGeo;
    private EditText numberOrigenGeo;
    private EditText numberDestinoGeo;
    private String Url="https://computacionmovilmib.000webhostapp.com/MIB/Insertar_Datos_Historial.php";
    private String paisOrigen;
    private String montoOrigen;
    private String paisDestino;
    private String montoDestino;
    private String Usuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_geo__cambio);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_map);
        mapFragment.getMapAsync(this);
        spinnerOrigenGeo=findViewById(R.id.spinnerOrigenGeo);
        spinnerDestinoGeo=findViewById(R.id.spinnerDestinoGeo);
        numberOrigenGeo=findViewById(R.id.numberOrigenGeo);
        numberDestinoGeo=findViewById(R.id.numberDestinoGeo);
        SharedPreferences prefs = getBaseContext().getSharedPreferences("Usuario", Context.MODE_PRIVATE);
        NombrePaisOrigen=prefs.getString("Pais","");
        Usuario=prefs.getString("Email","");
    }

    public void onMapReady(GoogleMap map) {

        mapa = map;
        NombrePaisDestino="Chile";
        LatLng Pais = new LatLng(-31.7613365, -71.3187697);
        mapa.addMarker(new MarkerOptions().position(Pais).title(NombrePaisDestino).icon(bitmapDescriptorFromVector(this,R.drawable.ic_baseline_location_on_24)));
        mapa.moveCamera(CameraUpdateFactory.newLatLng(Pais));

        spinnerOrigenGeo.setSelection(obtenerPosicionItem(spinnerOrigenGeo,NombrePaisOrigen));
        spinnerDestinoGeo.setSelection(obtenerPosicionItem(spinnerDestinoGeo,NombrePaisDestino));
        spinnerOrigenGeo.setEnabled(false);
        spinnerDestinoGeo.setEnabled(false);
        numberDestinoGeo.setEnabled(false);
        //miUbicacion();

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

    private BitmapDescriptor bitmapDescriptorFromVector(Context context, int vectorResId) {
        Drawable vectorDrawable = ContextCompat.getDrawable(context, vectorResId);
        vectorDrawable.setBounds(0, 0, vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight());
        Bitmap bitmap = Bitmap.createBitmap(vectorDrawable.getIntrinsicWidth(), vectorDrawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        vectorDrawable.draw(canvas);
        return BitmapDescriptorFactory.fromBitmap(bitmap);
    }
    private void agregarMarcador(double lat, double lng) {
        LatLng Pais = new LatLng(lat, lng);
        CameraUpdate miUbicacion = CameraUpdateFactory.newLatLngZoom(Pais, 50);
        if (marcador != null) marcador.remove();
        marcador = mapa.addMarker(new MarkerOptions().position(Pais).title(NombrePaisDestino).icon(BitmapDescriptorFactory.fromResource(R.drawable.ic_baseline_location_on_24)));
        mapa.animateCamera(miUbicacion);
    }

    private void actualizarUbicacion(Location location) {
        if (location != null) {
            lat = location.getLatitude();
            lng = location.getLongitude();
            agregarMarcador(lat, lng);
        }
    }

    LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            actualizarUbicacion(location);
        }
    };

    private void miUbicacion() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        actualizarUbicacion(location);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,15000,0,locationListener);
    }

    public void Restablecer(View view) {
        numberOrigenGeo.setText("");
        numberDestinoGeo.setText("");
    }

    public void Calcular(View view) {
        //aca API de Cambio de moneda
        float cambioOrigenDestino = (float) (Float.parseFloat(numberOrigenGeo.getText().toString().trim())*1.28611);
        numberDestinoGeo.setText(Float.toString(cambioOrigenDestino));

        paisOrigen=NombrePaisOrigen;
        montoOrigen=numberOrigenGeo.getText().toString().trim();
        paisDestino=NombrePaisDestino;
        montoDestino=Float.toString(cambioOrigenDestino);
        DBVolley();

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
                Toast.makeText(GEO_Cambio.this, error.getMessage(), Toast.LENGTH_LONG).show();
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
        RequestQueue requestQueue = Volley.newRequestQueue(GEO_Cambio.this);
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