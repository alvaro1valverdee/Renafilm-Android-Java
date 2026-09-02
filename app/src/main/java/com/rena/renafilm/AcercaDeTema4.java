package com.rena.renafilm;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AcercaDeTema4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_acerca_de_tema4);
        //BINDING
        Button btnAcercaDe = findViewById(R.id.btnAcercaDe);
        //TOOLBAR NATIVA
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // ⚠️ LAS DOS LÍNEAS MÁGICAS:
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Muestra la flecha de atrás
            getSupportActionBar().setDisplayShowHomeEnabled(true);  // Activa el comportamiento de botón
            // Al poner el listener directo en la navegación de la toolbar:
            toolbar.setNavigationOnClickListener(v -> {
                Intent intent = new Intent(AcercaDeTema4.this, CategoriaListaActivityTema4.class);
                startActivity(intent);
                finish();
            });
        }//FIN TOOLBAR

        btnAcercaDe.setOnClickListener(v -> {
            Intent intent = new Intent(AcercaDeTema4.this, CategoriaListaActivityTema4.class);
            startActivity(intent);
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}