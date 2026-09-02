package com.rena.renafilm;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rena.renafilm.modelo.Pelicula;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PeliculaAdapter extends RecyclerView.Adapter<PeliculaAdapter.MiViewHolder> {
    private List<Pelicula> peliculas;
    // CONSTRUCTOR: Por aquí recibe la lista de datos desde la Activity
    public PeliculaAdapter(List<Pelicula> peliculas) {
        this.peliculas = peliculas;
    }
    public void actualizarPeliculas(List<Pelicula> nuevasPeliculas) {
        peliculas = nuevasPeliculas;
        notifyDataSetChanged();
    }
    // PASO 1: INFLAR EL MOLDE (Crear la parte visual de una fila nueva)
    @NonNull
    @Override
    public MiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // EXAMEN: Cambiar "bloque4_item_fila" por el nombre de tu XML de la fila
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.bloque4_item_pelicula, parent, false);
        return new MiViewHolder(view);
    }
    // PASO 2: ENLAZAR LOS DATOS (Pintar la información en la fila)
    @Override
    public void onBindViewHolder(@NonNull MiViewHolder holder, int position) {
        Pelicula peliculaActual = peliculas.get(position);

        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500" + peliculaActual.getPosterPath())
                .into(holder.ivPoster); //Carga de imagen con Picasso
        holder.tvTituloPelicula.setText(peliculaActual.getTitle());
        holder.puntuacionPelicula.setRating((float) (peliculaActual.getVoteAverage()/2));
        // ⚠️ DETECTOR DE CLIC EN LA FILA
        holder.itemView.setOnClickListener(v -> {
            // Creamos el Intent hacia la Activity común que muestra cada pelicula
            Intent intent = new Intent(v.getContext(), PeliculaActivity.class);

            // Pasamos los atributos de la pelicula como "pasaporte"
            intent.putExtra("PELICULA_TITULO", peliculaActual.getTitle());
            intent.putExtra("PELICULA_IMAGEN", peliculaActual.getPosterPath());
            intent.putExtra("PELICULA_PUNTUACION", peliculaActual.getVoteAverage());
            intent.putExtra("PELICULA_DESCRIPCION", peliculaActual.getOverview());

            v.getContext().startActivity(intent);
        });

    }
    // PASO 3: TAMAÑO DE LA LISTA
    @Override
    public int getItemCount() {
        return peliculas.size();
    }
    // EL CONTENEDOR (ViewHolder): Busca y guarda los IDs visuales de la fila
    public static class MiViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPoster;
        TextView tvTituloPelicula;
        RatingBar puntuacionPelicula;
        public MiViewHolder(@NonNull View itemView) {
            super(itemView);
            // EXAMEN: ID del TextView dentro de tu archivo bloque4_item_fila.xml
            ivPoster = itemView.findViewById(R.id.ivPoster);
            tvTituloPelicula = itemView.findViewById(R.id.tvTituloPelicula);
            puntuacionPelicula = itemView.findViewById(R.id.puntuacionPelicula);
        }
    }
}