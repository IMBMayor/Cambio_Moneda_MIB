package com.example.mib_cambiomoneda.PerfilAdapter;

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
    public  com.example.mib_cambiomoneda.PerfilAdapter.ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = mInflater.inflate(R.layout.perfil_cardview,null);
        return new com.example.mib_cambiomoneda.PerfilAdapter.ListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final com.example.mib_cambiomoneda.PerfilAdapter.ListAdapter.ViewHolder holder, final int posicion){
        holder.binData(mdatos.get(posicion));
    }

    public void setItems(List<ListElement> items){
        mdatos = items;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iconImage;
        TextView txt_Nombre, txt_Email,txt_Pais;

     ViewHolder(View itemView){
         super(itemView);
         iconImage = itemView.findViewById(R.id.iconView);
         txt_Nombre = itemView.findViewById(R.id.txt_Nombre);
         txt_Email = itemView.findViewById(R.id.txt_Email);
         txt_Pais = itemView.findViewById(R.id.txt_Pais);
     }

     void binData(final ListElement item){
         iconImage.setColorFilter(Color.parseColor(item.getColor()), PorterDuff.Mode.SRC_IN);
         txt_Nombre.setText(item.getNombreUsuario());
         txt_Email.setText(item.getEmailUsuario());
         txt_Pais.setText(item.getPaisUsuario());
     }

    }
}
