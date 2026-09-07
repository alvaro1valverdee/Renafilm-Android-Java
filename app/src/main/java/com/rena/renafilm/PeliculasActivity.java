package com.rena.renafilm;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
    private int paginaActual = 1;
    private boolean estaCargando = false;
    private String categoria;
    private List<Pelicula> listaPeliculas = new ArrayList<>();
    private PeliculaAdapter adapter;
    //PROGRESS BAR
    ProgressBar progressBar;
    String apiKey = "83a8fe0de40d5d82e94bbeb24301f2da"; // Sustituye esto por tu API Key real de TMDB
    String idioma = "es-ES"; // Para que nos traiga los títulos y sinopsis en español

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas); // Tu XML contenedor (RecyclerView + ProgressBar)
        progressBar = findViewById(R.id.pbPeliculas);
        // 1. Recorremos el Intent para saber qué categoria ha pulsado el usuario
        categoria = getIntent().getStringExtra("CATEGORIA_SELECCIONADA");
        // 2. Recogemos el nombre que nos manda el Adapter
        String tituloCategoria = getIntent().getStringExtra("TITULO_CATEGORIA");
        //TOOLBAR NATIVA
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null && tituloCategoria != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Muestra la flecha de atrás
            getSupportActionBar().setDisplayShowHomeEnabled(true);  // Activa el comportamiento de botón
            getSupportActionBar().setTitle(tituloCategoria);
            // Al poner el listener directo en la navegación de la toolbar:
            toolbar.setNavigationOnClickListener(v -> {
                finish(); // Cierra la pantalla al pulsar la flecha
            });
        }//FIN TOOLBAR
        // Vinculamos el RecyclerView común
        RecyclerView rv = findViewById(R.id.rvPeliculas);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        rv.setLayoutManager(layoutManager);
        rv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                // Si estamos bajando (dy > 0)
                if (dy > 0) {
                    int itemsTotales = layoutManager.getItemCount();
                    int itemsVisibles = layoutManager.getChildCount();
                    int itemsPasados = layoutManager.findFirstVisibleItemPosition();

                    // Si no está cargando ya, y hemos llegado al final de la lista...
                    if (!estaCargando && (itemsVisibles + itemsPasados) >= itemsTotales) {
                        paginaActual++; // Pasamos a la siguiente página
                        cargarPagina(paginaActual); // Disparamos la descarga
                    }
                }
            }
        });
        adapter = new PeliculaAdapter(listaPeliculas);
        rv.setAdapter(adapter);
        cargarPagina(paginaActual);
    }
    private void cargarPagina(int pagina) {
        estaCargando = true;
        progressBar.setVisibility(View.VISIBLE);

        Call<PeliResponse> call = null;

        switch (categoria) {
            case "MOVIE_POPULAR":
                call = RetrofitClient.getApi().getPopularMovies(apiKey, idioma, pagina);
                break;
            case "TV_POPULAR":
                call = RetrofitClient.getApi().getPopularTvShows(apiKey, idioma, pagina);
                break;
            case "MOVIE_TOP_RATED":
                call = RetrofitClient.getApi().getTopRatedMovies(apiKey, idioma, pagina);
                break;
        }

        if (call != null) {
            call.enqueue(new Callback<PeliResponse>() {
                @Override
                public void onResponse(Call<PeliResponse> call, Response<PeliResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        // 1. Añadimos las nuevas pelis a la lista que ya teníamos
                        listaPeliculas.addAll(response.body().getResults());

                        // 2. Avisamos al adaptador de que hay datos nuevos
                        adapter.notifyDataSetChanged();

                        // 3. Liberamos el cerrojo
                        estaCargando = false;
                    }
                    progressBar.setVisibility(View.GONE);
                }

                @Override
                public void onFailure(Call<PeliResponse> call, Throwable t) {
                    estaCargando = false;
                    progressBar.setVisibility(View.GONE);
                }
            });
        }
    }
}