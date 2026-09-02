package com.rena.renafilm;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rena.renafilm.api.RetrofitClient;
import com.rena.renafilm.modelo.Pelicula;
import com.rena.renafilm.modelo.PeliResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculasActivity extends AppCompatActivity {
    private PeliculaAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas); // Tu XML contenedor (RecyclerView + ProgressBar)
        //TOOLBAR NATIVA
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Muestra la flecha de atrás
            getSupportActionBar().setDisplayShowHomeEnabled(true);  // Activa el comportamiento de botón
            // Al poner el listener directo en la navegación de la toolbar:
            toolbar.setNavigationOnClickListener(v -> {
                finish(); // Cierra la pantalla al pulsar la flecha
            });
        }//FIN TOOLBAR
        //PROGRESS BAR
        ProgressBar progressBar = findViewById(R.id.pbPeliculas);
        // 1. Recorremos el Intent para saber qué categoria ha pulsado el usuario
        String categoria = getIntent().getStringExtra("CATEGORIA_SELECCIONADA");
        String apiKey = "83a8fe0de40d5d82e94bbeb24301f2da"; // Sustituye esto por tu API Key real de TMDB
        String idioma = "es-ES"; // Para que nos traiga los títulos y sinopsis en español

        // Vinculamos el RecyclerView común
        RecyclerView rv = findViewById(R.id.rvPeliculas);
        rv.setLayoutManager(new GridLayoutManager(this, 3));//Indicamos que sea en grid y 3 por fila
        List<Pelicula> listaVacia = new ArrayList<>();
        adapter = new PeliculaAdapter(listaVacia);
        rv.setAdapter(adapter);
        // Preparamos la llamada usando la interfaz que creamos
        Call<PeliResponse> call;
        progressBar.setVisibility(View.VISIBLE);
        switch (categoria) {
            case "MOVIE_POPULAR":
                call = RetrofitClient.getApi().getPopularMovies(apiKey, idioma);
                break;
            case "TV_POPULAR":
                call = RetrofitClient.getApi().getPopularTvShows(apiKey, idioma);
                break;
            case "MOVIE_TOP_RATED":
                call = RetrofitClient.getApi().getTopRatedMovies(apiKey, idioma);
                break;
            default:
                // Por si acaso llega algo raro, cargamos las populares por defecto
                call = RetrofitClient.getApi().getPopularMovies(apiKey, idioma);
                break;
        }
        progressBar.setVisibility(View.VISIBLE);

        // ¡Ejecutamos la llamada final! (Sea la que sea que haya ganado en el switch)
        if (call != null) {
            call.enqueue(new Callback<PeliResponse>() {
                @Override
                public void onResponse(Call<PeliResponse> call, Response<PeliResponse> response) {
                    progressBar.setVisibility(View.GONE);
                    // Si la llamada ha ido bien (Código 200 OK)
                    if (response.isSuccessful() && response.body() != null) {
                        List<Pelicula> listaPeliculas = response.body().getResults();
                        // Vamos a imprimir el título de la primera película en el Logcat para comprobar
                        if (!listaPeliculas.isEmpty()) {
                            adapter.actualizarPeliculas(listaPeliculas);
                        }
                    } else {
                        // Si la API nos rechaza (ej. API Key mal escrita)
                        Log.e("TMDB_ERROR", "Error del servidor: " + response.code());
                    }
                }

                @Override
                public void onFailure(Call<PeliResponse> call, Throwable t) {
                    // Si no hay internet o la URL está mal
                    progressBar.setVisibility(View.GONE);
                    Log.e("TMDB_FALLO", "Fallo de conexión crítico: " + t.getMessage());
                }
            });
        }
    }
}