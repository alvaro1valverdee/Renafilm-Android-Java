package com.rena.renafilm;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
public class PlataformaAdapter extends RecyclerView.Adapter<PlataformaAdapter.MiViewHolder> {
    private List<Plataforma> plataformas;
    // CONSTRUCTOR: Por aquí recibe la lista de datos desde la Activity
    public PlataformaAdapter(List<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }
    // PASO 1: INFLAR EL MOLDE (Crear la parte visual de una fila nueva)
    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // EXAMEN: Cambiar "bloque4_item_fila" por el nombre de tu XML de la fila
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bloque4_item_plataforma, parent, false);
        return new MiViewHolder(view);
    }
    // PASO 2: ENLAZAR LOS DATOS (Pintar la información en la fila)
    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        Plataforma plataformaActual = plataformas.get(position);

        // EXAMEN: Usar el ID que le pusiste a tu TextView dentro del XML de la fila
        holder.imagenFila.setImageResource(plataformaActual.getImagenResId());
        holder.tvFilaTexto.setText(plataformaActual.getNombre());

        //Si pìde que al pinchar en una plataforma te lleve a otra activity con una lista de peliculas
        // ⚠️ DETECTOR DE CLIC EN LA FILA
        holder.itemView.setOnClickListener(v -> {
            // Creamos el Intent hacia la Activity común que muestra películas
            Intent intent = new Intent(v.getContext(), PeliculasActivity.class);

            // Pasamos el nombre de la plataforma como "pasaporte" ("Netflix", "HBO"...)
            intent.putExtra("PLATAFORMA_SELECCIONADA", plataformaActual.getNombre());

            v.getContext().startActivity(intent);
        });
    }
    // PASO 3: TAMAÑO DE LA LISTA
    @Override
    public int getItemCount() {
        return plataformas.size();
    }
    // EL CONTENEDOR (ViewHolder): Busca y guarda los IDs visuales de la fila
    public static class MiViewHolder extends RecyclerView.ViewHolder {
        ImageView imagenFila;
        TextView tvFilaTexto;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            // EXAMEN: ID del TextView dentro de tu archivo bloque4_item_fila.xml
            imagenFila = itemView.findViewById(R.id.imagenFila);
            tvFilaTexto = itemView.findViewById(R.id.tvFilaTexto);
        }
    }
}
