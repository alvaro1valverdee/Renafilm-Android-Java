package com.rena.renafilm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.example.practicas_aisladas_pmdm.bloque4_RecyclerView.Bloque4Adapter;

import java.util.ArrayList;
import java.util.List;

public class CategoriaListaActivityTema4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        //EMPIEZA EL RECYCLERVIEW
        setContentView(R.layout.activity_categoria_lista);
        //BINDING
        // 1. Enganchar el RecyclerView del XML
        // EXAMEN: ID de tu RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvMiListaPlataformas);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        // 3. Crear los datos falsos estáticos para probar la mecánica pura
        List<Categoria> categorias = new ArrayList<>();
        categorias.add(new Categoria("Películas Populares", R.drawable.ic_corona, "MOVIE_POPULAR",R.drawable.peliculas_cine_favoritas));
        categorias.add(new Categoria("Series Populares", R.drawable.ic_popular, "TV_POPULAR", R.drawable.series_tv));
        categorias.add(new Categoria("Películas Mejor Valoradas", R.drawable.ic_corona, "MOVIE_TOP_RATED", R.drawable.peliculas_mvp));
        categorias.add(new Categoria("Películas Favoritas", R.drawable.ic_cine, "", R.drawable.peliculas_cine_favoritas));
        categorias.add(new Categoria("Series Favoritas", R.drawable.ic_tv, "", R.drawable.series_tv_favoritas));
        // 4. Crear el operario (Adaptador) y entregarle los datos
        CategoriaAdapter miAdaptador = new CategoriaAdapter(categorias);//Importante que miAdaptador lo creemos del tipo de la clase en este caso PlataformaAdapter
        // 5. Unir el operario al RecyclerView para que empiece a pintar
        recyclerView.setAdapter(miAdaptador);

        //TERMINA EL RECYCLERVIEW

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

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
    // === 2. FUERA DEL onCreate(): INFLAMOS EL MENÚ DE LA DERECHA ===
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Buscamos el XML de tu menú y lo metemos en la Toolbar
        getMenuInflater().inflate(R.menu.menu_rv, menu);
        return true; // ⚠️ OBLIGATORIO: Si no devuelves true, el menú no se dibuja
    }
    // 3. DETECTAR CLICKS: Saber qué opción ha pulsado el usuario
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        // EXAMEN: Usamos if-else porque las últimas versiones de Android Studio dan problemas con switch-case en IDs
        if (id == R.id.menu_rv_ayuda) {
            Intent intent = new Intent(CategoriaListaActivityTema4.this, AyudaActivity.class);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.menu_rv_acerca_de) {
            Intent intent = new Intent(CategoriaListaActivityTema4.this, AcercaDeTema4.class);
            startActivity(intent);
            finish();
            return true;
        } else if (id == R.id.menu_rv_cerrar_sesion) {
            SharedPreferences prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();
            // ⚠️ LA CLAVE DEL CAMBIO: No borramos todo, solo apagamos el interruptor de entrada
            editor.putBoolean("sesion_activa", false);
            editor.apply(); // Guarda el cambio de forma segura
            // Volvemos al Login
            Intent intent = new Intent(CategoriaListaActivityTema4.this, LoginActivityTema4.class);
            startActivity(intent);
            finish();
            return true;
    }
        return super.onOptionsItemSelected(item);
    }
}