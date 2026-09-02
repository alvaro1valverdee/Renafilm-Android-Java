package com.rena.renafilm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.appcompat.widget.Toolbar; // ⚠️ ESTA ES LA CLAVE


public class LoginActivityTema4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_tema4);
        //BINDING
        EditText etUsuario = findViewById(R.id.usuarioLogin);
        EditText etPassword = findViewById(R.id.passwordLogin);
        Button btnLogin = findViewById(R.id.btnLogin);
        TextView tvRegistroLogin = findViewById(R.id.registroLogin);
        //TOOLBAR NATIVA
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // ⚠️ LAS DOS LÍNEAS MÁGICAS:
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);  // Muestra la flecha de atrás
            getSupportActionBar().setDisplayShowHomeEnabled(true);  // Activa el comportamiento de botón
            // Al poner el listener directo en la navegación de la toolbar:
            toolbar.setNavigationOnClickListener(v -> {
                finish(); // Cierra la pantalla al pulsar la flecha
            });
        }
        // El modo MODE_PRIVATE asegura que solo tu aplicación pueda leer este archivo
        SharedPreferences prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
        // === [2] TRUCO DE PRECARGA AUTOMÁTICA (Añadir al chuletario) ===

        // Intentamos leer de SharedPreferences.
        // Si es la primera vez y no hay nada registrado, saltarán los de por defecto ("admin" y "1234")
                String usuarioPrecargado = prefs.getString("usuario_guardado", "admin");
                String passwordPrecargada = prefs.getString("password_guardada", "1234");

        // Inyectamos los textos directamente en los inputs para que aparezcan ya escritos
                etUsuario.setText(usuarioPrecargado);
                etPassword.setText(passwordPrecargada);

        // ============================================================
        // A partir de aquí sigue el btnLogin.setOnClickListener...
        // COMPROBACIÓN REPETIDA (Para saltarse el Login si ya se logueó antes)
        boolean yaLogueado = prefs.getBoolean("sesion_activa", false); // El 'false' es el valor por defecto
        if (yaLogueado) {
            // Si ya inició sesión, viajamos directo al catálogo de películas sin pasar por aquí
            Intent intent = new Intent(LoginActivityTema4.this, CategoriaListaActivityTema4.class);
            startActivity(intent);
            finish(); // Cerramos el Login para que no pueda volver atrás al pulsar el botón físico
        }
        btnLogin.setOnClickListener(v -> {
            String userTxt = etUsuario.getText().toString().trim();
            String passTxt = etPassword.getText().toString().trim();

            // Validación exprés para evitar campos vacíos
            if (userTxt.isEmpty() || passTxt.isEmpty()) {
                Toast.makeText(LoginActivityTema4.this, "Rellena todos los campos", Toast.LENGTH_SHORT).show();
                return;
            }

            // 2. RECUPERAMOS LAS CREDENCIALES REGISTRADAS
            // (Imaginemos que se guardaron al registrarse. Si no existen, usamos unos valores por defecto)
            String usuarioCorrecto  = prefs.getString("nombre_guardado", "admin");
            String passwordCorrecta  = prefs.getString("contraseña_guardada", "1234");
            // 3. LA VALIDACIÓN
            if (userTxt.equals(usuarioCorrecto) && passTxt.equals(passwordCorrecta)) {

                // 4. GUARDAR ESTADO (Abrimos el editor para modificar las SharedPreferences)
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("sesion_activa", true); // Activamos el interruptor
                editor.apply(); // ⚠️ OBLIGATORIO: Guarda los cambios en segundo plano

                Toast.makeText(LoginActivityTema4.this, "¡Bienvenido de nuevo!", Toast.LENGTH_SHORT).show();

                // Saltamos a la pantalla del catálogo
                Intent intent = new Intent(LoginActivityTema4.this, CategoriaListaActivityTema4.class);
                startActivity(intent);
                finish();

            } else {
                Toast.makeText(LoginActivityTema4.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
            }
        });

        tvRegistroLogin.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivityTema4.this, NuevoUsuarioTema4.class);
            startActivity(intent);
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}