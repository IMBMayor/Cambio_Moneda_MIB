package com.example.mib_cambiomoneda.HistorialAdapter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.example.mib_cambiomoneda.R;


import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolder> {

    private List<ListElement> mdatos;
    private LayoutInflater mInflater;
    private Context context;


    public ListAdapter(List<ListElement> itemsLists, Context context){
      this.mInflater = LayoutInflater.from(context);
      this.context = context;
      this.mdatos = itemsLists;
    }

    @Override
    public int getItemCount(){return mdatos.size();}

    @Override
    public  com.example.mib_cambiomoneda.HistorialAdapter.ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.contenido_cardview,null);
        return new com.example.mib_cambiomoneda.HistorialAdapter.ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final com.example.mib_cambiomoneda.HistorialAdapter.ListAdapter.ViewHolder holder, final int posicion){
        holder.binData(mdatos.get(posicion));
    }

    public void setItems(List<ListElement> items){
        mdatos = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView paisOrigen, montoOrigen,paisDestino,montoDestino;

     ViewHolder(View itemView){
         super(itemView);
         iconImage = itemView.findViewById(R.id.iconView);
         paisOrigen = itemView.findViewById(R.id.txt_pais_origen_historial);
         montoOrigen = itemView.findViewById(R.id.txt_monto_origen_historial);
         paisDestino = itemView.findViewById(R.id.txt_pais_destino_historial);
         montoDestino = itemView.findViewById(R.id.txt_monto_destino_historial);
     }

     void binData(final ListElement item){
         iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
         paisOrigen.setText(item.getPaisOrigen());
         montoOrigen.setText(item.getMontoOrigen());
         paisDestino.setText(item.getPaisDestino());
         montoDestino.setText(item.getMontoDestino());
     }

    }
}
