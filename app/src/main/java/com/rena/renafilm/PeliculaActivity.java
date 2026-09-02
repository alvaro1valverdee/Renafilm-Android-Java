package com.rena.renafilm;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.rena.renafilm.R;
import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

public class PeliculaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_pelicula);
        //TOOLBAR NATIVA
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ⚠️ LAS DOS LÍNEAS MÁGICAS:
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Muestra la flecha de atrás
            getSupportActionBar().setDisplayShowHomeEnabled(true);  // Activa el comportamiento de botón
        }//FIN TOOLBAR
        //1. BINDING
        ImageView ivDetallePoster = findViewById(R.id.ivDetallePoster);
        TextView tvDetalleTitulo = findViewById(R.id.tvDetalleTitulo);
        RatingBar rbDetallePelicula = findViewById(R.id.rbDetallePelicula);
        TextView descripcionPelicula = findViewById(R.id.descripcionPelicula);

        // 2. Recuperamos los datos que viajan en el Intent
        String peliculaNombre = getIntent().getStringExtra("PELICULA_TITULO");
        // 🚨 OJO EXAMEN: Los tipos primitivos (int, float, boolean) requieren un valor por defecto
        // por si la clave no se encuentra o llega vacía.
        String peliculaImagen = getIntent().getStringExtra("PELICULA_IMAGEN");
        float peliculaPuntuacion = (float) getIntent().getDoubleExtra("PELICULA_PUNTUACION", 0.0f);
        String peliculaDescripcion = getIntent().getStringExtra("PELICULA_DESCRIPCION");
        // 3. Pintamos los datos en la pantalla si no son nulos
        if (peliculaNombre != null) {
            tvDetalleTitulo.setText(peliculaNombre);
            Picasso.get()
                    .load("https://image.tmdb.org/t/p/w500" + peliculaImagen)
                    .into(ivDetallePoster); //Carga de imagen con Picasso
            rbDetallePelicula.setRating(peliculaPuntuacion/2);
            descripcionPelicula.setText(peliculaDescripcion);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // 🌟 METODO DE EXAMEN: Gestiona de forma limpia la flecha de la Toolbar
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Comprobamos si el usuario ha pulsado la flecha de atrás nativa
        if (item.getItemId() == android.R.id.home) {
            finish(); // ⚠️ LA CLAVE: Destruye el detalle y "cae" automáticamente en la lista que ya estaba abierta abajo
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}