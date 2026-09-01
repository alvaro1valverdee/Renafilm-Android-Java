package com.rena.renafilm;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class NuevoUsuarioTema4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_nuevo_usuario_tema4);
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

        //BINDING
        EditText etRegUsuario = findViewById(R.id.nuevoUsuario);
        EditText etRegEmail = findViewById(R.id.nuevoUsuarioEmail);
        EditText etRegTlf = findViewById(R.id.nuevoUsuarioTlf);
        EditText etRegPassword = findViewById(R.id.nuevoUsuarioPassword);
        EditText etRegConfirmPassword = findViewById(R.id.nuevoUsuarioPasswordRepetida);
        Button btnRegistrar = findViewById(R.id.btnNuevoUsuario);

        // 2. Escuchador del botón de registro
        btnRegistrar.setOnClickListener(v -> {
        // El "Directo": Leemos los textos en el milisegundo del clic
        String user = etRegUsuario.getText().toString().trim();
        String email = etRegEmail.getText().toString().trim();
        String tlf = etRegTlf.getText().toString().trim();
        String pass = etRegPassword.getText().toString().trim();
        String confirmPass = etRegConfirmPassword.getText().toString().trim();
        // ❌ VALIDACIÓN 1: Comprobar que ningún campo esté vacío
        if (user.isEmpty() || email.isEmpty() || tlf.isEmpty() || pass.isEmpty() || confirmPass.isEmpty()) {
            Toast.makeText(NuevoUsuarioTema4.this, "Por favor, rellena todos los campos", Toast.LENGTH_SHORT).show();
            return; // Frena la ejecución
        }
        // ❌ VALIDACIÓN 2: Comprobar que las contraseñas coinciden
        // Usamos !pass.equals(...) porque en Java los Strings no se comparan con ==
        if (!pass.equals(confirmPass)) {
            Toast.makeText(NuevoUsuarioTema4.this, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show();
            etRegConfirmPassword.setError("La contraseña debe ser idéntica"); // Alerta visual en el input
            return; // Frena la ejecución
        }
        // ❌ VALIDACIÓN 3 (Opcional de examen): Longitud mínima de contraseña
        if (pass.length() < 4) {
            Toast.makeText(NuevoUsuarioTema4.this, "La contraseña debe tener al menos 4 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }
            // ======= TODO CORRECTO: GUARDAMOS EN SHAREDPREFERENCES =======

            // 3. Abrimos el MISMO fichero que el Login ("LoginPrefs")
            SharedPreferences prefs = getSharedPreferences("LoginPrefs", MODE_PRIVATE);
            SharedPreferences.Editor editor = prefs.edit();

            // 4. Guardamos los datos con las CLAVES EXACTAS que busca el Login
            editor.putString("nombre_guardado", user);
            editor.putString("contraseña_guardada", pass);

            // Guardamos el resto por si los necesitas en el perfil de la app
            editor.putString("email_guardado", email);
            editor.putString("tlf_guardado", tlf);

            // 5. Aplicamos cambios obligatoriamente
            editor.apply();
            Toast.makeText(NuevoUsuarioTema4.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();

            // 6. Volvemos al Login de forma limpia
            finish(); // Cierra esta Activity y destruye la pantalla, volviendo a la anterior (el Login)
        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
        Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
        v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
        return insets;
        });
    }
}