package com.rena.renafilm;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rena.renafilm.api.RetrofitClient;
import com.rena.renafilm.modelo.Peli;
import com.rena.renafilm.modelo.PeliResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeliculasActivity extends AppCompatActivity {

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
        // 1. Recorremos el Intent para saber qué plataforma ha pulsado el usuario
        String plataforma = getIntent().getStringExtra("PLATAFORMA_SELECCIONADA");

        String apiKey = "83a8fe0de40d5d82e94bbeb24301f2da"; // Sustituye esto por tu API Key real de TMDB
        String idioma = "es-ES"; // Para que nos traiga los títulos y sinopsis en español
        // Preparamos la llamada usando la interfaz que creamos
        Call<PeliResponse> call = RetrofitClient.getApi().getPopularMovies(apiKey, idioma);

        // Ejecutamos la llamada en "segundo plano" (asíncrona)
        call.enqueue(new Callback<PeliResponse>() {
            @Override
            public void onResponse(Call<PeliResponse> call, Response<PeliResponse> response) {
                // Si la llamada ha ido bien (Código 200 OK)
                if (response.isSuccessful() && response.body() != null) {

                    List<Peli> listaPeliculas = response.body().getResults();

                    // Vamos a imprimir el título de la primera película en el Logcat para comprobar
                    if (!listaPeliculas.isEmpty()) {
                        Peli primeraPelicula = listaPeliculas.get(0);
                        Log.d("TMDB_EXITO", "¡Conexión lograda! La película #1 es: " + primeraPelicula.getTitle());
                    }

                } else {
                    // Si la API nos rechaza (ej. API Key mal escrita)
                    Log.e("TMDB_ERROR", "Error del servidor: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<PeliResponse> call, Throwable t) {
                // Si no hay internet o la URL está mal
                Log.e("TMDB_FALLO", "Fallo de conexión crítico: " + t.getMessage());
            }
        });

        // 3. Vinculamos el RecyclerView común
        RecyclerView rv = findViewById(R.id.rvPeliculas);
        rv.setLayoutManager(new GridLayoutManager(this, 3));//Indicamos que sea en grid y 3 por fila

        // 4. Lista vacía que llenaremos según la condición
        List<Pelicula> listaAFiltrar = new ArrayList<>();

        // 🚨 EXAMEN: Estructura condicional para decidir los datos
        if (plataforma != null) {
            if (plataforma.equalsIgnoreCase("Netflix")) {
                listaAFiltrar.add(new Pelicula("The Witcher", R.drawable.witcher, 4.0f, "Geralt de Rivia, un cazador de monstruos mutante, viaja en pos de su destino por un mundo turbulento en el que los humanos demuestran ser peores que las bestias."));
                listaAFiltrar.add(new Pelicula("Stranger Things", R.drawable.stranger, 3.5f, "Tras la misteriosa desaparición de un niño en un pequeño pueblo, un grupo de amigos se ve envuelto en una peligrosa conspiración con fuerzas sobrenaturales."));
                listaAFiltrar.add(new Pelicula("Squid Game", R.drawable.squid_game, 4.2f, "Cientos de jugadores con problemas económicos aceptan una extraña invitación para competir en juegos infantiles donde el premio es millonario, pero el precio es mortal."));
                listaAFiltrar.add(new Pelicula("Black Mirror", R.drawable.black_mirror, 4.5f, "Antología de ciencia ficción que explora un futuro próximo tecnológico, oscuro y retorcido donde las mayores innovaciones de la humanidad chocan con sus peores instintos."));
                listaAFiltrar.add(new Pelicula("Dark", R.drawable.dark, 4.7f, "La desaparición de un niño en el pequeño pueblo de Winden abre un misterio que abarca tres generaciones y está conectado con un complejo viaje en el tiempo."));
                listaAFiltrar.add(new Pelicula("The Crown", R.drawable.the_crown, 4.3f, "Crónica de la vida de la reina Isabel II de Inglaterra desde los años cuarenta hasta los tiempos modernos, mostrando las rivalidades políticas y romances de su reinado."));
                listaAFiltrar.add(new Pelicula("Wednesday", R.drawable.wednesday, 4.0f, "Miércoles Addams es enviada a la Academia Nunca Más, donde intentará dominar su emergente habilidad psíquica y resolver un misterio de asesinato de hace 25 años."));
                listaAFiltrar.add(new Pelicula("Narcos", R.drawable.narcos, 4.4f, "Crónica de la vida real de los cárteles de la droga colombianos y los esfuerzos de la DEA por detener la violenta expansión del narcotráfico internacional."));
                listaAFiltrar.add(new Pelicula("Bridgerton", R.drawable.bridgerton, 3.8f, "Desde la perspectiva de una influyente y misteriosa escritora, se narran las vidas y romances de los ocho hermanos de la aristocrática familia Bridgerton."));
                listaAFiltrar.add(new Pelicula("La casa de papel", R.drawable.casa_papel, 4.1f, "Un misterioso personaje apodado El Profesor recluta a ocho criminales para llevar a cabo el mayor atraco de la historia: asaltar la Fábrica Nacional de Moneda y Timbre."));

            } else if (plataforma.equalsIgnoreCase("HBO")) {
                listaAFiltrar.add(new Pelicula("Game of Thrones", R.drawable.got, 5.0f, "Nueve familias nobles luchan incansablemente por el control político del Trono de Hierro en Poniente, mientras un antiguo y gélido enemigo regresa para destruirlos a todos."));
                listaAFiltrar.add(new Pelicula("The Last of Us", R.drawable.tlou, 3.5f, "Veinte años después de que una pandemia fúngica destruya la civilización, Joel es contratado para contrabandear a Ellie, una niña que podría ser la clave para la cura."));
                listaAFiltrar.add(new Pelicula("Succession", R.drawable.succession, 4.8f, "La saga de una familia multimillonaria dueña de un imperio de medios de comunicación, cuyas dinámicas cambian por completo cuando el patriarca decide retirarse."));
                listaAFiltrar.add(new Pelicula("Chernobyl", R.drawable.chernobyl, 4.9f, "Miniserie que recrea la historia real de la tragedia nuclear de 1986, detallando los sacrificios realizados para salvar a Europa de un desastre inimaginable."));
                listaAFiltrar.add(new Pelicula("House of the Dragon", R.drawable.house_dragon, 4.4f, "Precuela ambientada 200 años antes de Juego de Tronos que narra el principio del fin de la Casa Targaryen y los eventos que llevaron a la guerra civil de dragones."));
                listaAFiltrar.add(new Pelicula("Euphoria", R.drawable.euphoria, 4.1f, "Una mirada cruda e intensa a la vida de un grupo de estudiantes de secundaria mientras navegan por los problemas del amor, los traumas, las redes sociales y las adicciones."));
                listaAFiltrar.add(new Pelicula("True Detective", R.drawable.true_detective, 4.5f, "Antología policial donde cada temporada sigue una investigación criminal distinta, arrastrando a los inspectores a oscuros secretos tanto del caso como de sus vidas."));
                listaAFiltrar.add(new Pelicula("The Wire", R.drawable.the_wire, 4.9f, "Crónica social y policial de la ciudad de Baltimore, analizando el mundo del narcotráfico a través de las escuchas telefónicas y las complejas dinámicas de sus instituciones."));
                listaAFiltrar.add(new Pelicula("Los Soprano", R.drawable.soprano, 4.9f, "El capo de la mafia de Nueva Jersey, Tony Soprano, lidia con las presiones de su organización criminal mientras asiste en secreto a terapia tras sufrir ataques de pánico."));
                listaAFiltrar.add(new Pelicula("Westworld", R.drawable.westworld, 4.0f, "En un parque temático futurista habitado por androides donde los humanos pueden cumplir cualquier fantasía, los robots comienzan a desarrollar conciencia propia."));

            } else if (plataforma.equalsIgnoreCase("Disney+")) {
                listaAFiltrar.add(new Pelicula("The Mandalorian", R.drawable.mandalorian, 2.5f, "Un cazarrecompensas solitario de la legendaria estirpe de los mandalorianos viaja por los confines de la galaxia protegiendo a una misteriosa y poderosa criatura."));
                listaAFiltrar.add(new Pelicula("Loki", R.drawable.loki, 4.3f, "El Dios del Engaño es arrestado por una misteriosa organización burocrática temporal tras alterar la línea del tiempo, obligándole a reparar el multiverso."));
                listaAFiltrar.add(new Pelicula("WandaVision", R.drawable.wandavision, 4.1f, "Wanda Maximoff y Visión viven una vida suburbana idealizada basada en sitcoms clásicas, pero pronto empiezan a sospechar que la realidad no es lo que parece."));
                listaAFiltrar.add(new Pelicula("Andor", R.drawable.andor, 4.5f, "Precuela de Rogue One que explora la perspectiva de Cassian Andor y el nacimiento de la rebelión contra el Imperio en una galaxia llena de peligros y espionaje."));
                listaAFiltrar.add(new Pelicula("Shogun", R.drawable.shogun, 4.8f, "En el Japón del año 1600, un náufrago inglés termina atrapado en medio de las complejas guerras políticas de los señores feudales por el control total del país."));
                listaAFiltrar.add(new Pelicula("Modern Family", R.drawable.modern_family, 4.6f, "Falso documental que sigue el día a día de tres ramificaciones de una familia muy diversa y divertida de Los Ángeles, lidiando con situaciones cotidianas."));
                listaAFiltrar.add(new Pelicula("Grey's Anatomy", R.drawable.greys_anatomy, 3.9f, "Drama médico que sigue las vidas personales y profesionales del equipo de cirujanos del hospital de Seattle, empezando por su exigente etapa de residentes."));
                listaAFiltrar.add(new Pelicula("Mentes criminales", R.drawable.mentes_criminales, 4.0f, "Un equipo de élite del FBI especializado en el análisis de la conducta se dedica a trazar perfiles psicológicos de los criminales más peligrosos para capturarlos."));
            } else if (plataforma.equalsIgnoreCase("Amazon Prime")) {
                listaAFiltrar.add(new Pelicula("The Boys", R.drawable.the_boys, 4.8f, "En un mundo donde los superhéroes abusan de sus poderes y su fama, un grupo de vigilantes sin superpoderes se propone destapar la verdad sobre la corporación Vought."));

                listaAFiltrar.add(new Pelicula("Invincible", R.drawable.invincible, 4.6f, "Mark Grayson es un adolescente normal, excepto por el hecho de que su padre es el superhéroe más poderoso del planeta. Al cumplir 17 años, sus propios poderes empiezan a despertar."));

                listaAFiltrar.add(new Pelicula("Rings of Power", R.drawable.rings_power, 3.9f, "Épico drama ambientado miles de años antes de los eventos de El Señor de los Anillos, que narra el resurgimiento del mal en la Segunda Edad de la Tierra Media y la forja de los anillos."));

                listaAFiltrar.add(new Pelicula("Fallout", R.drawable.fallout, 4.7f, "Basada en la mítica saga de videojuegos, narra la historia de los supervivientes de un apocalipsis nuclear 200 años después, obligados a regresar al yermo infernal que dejaron sus ancestros."));

                listaAFiltrar.add(new Pelicula("Reacher", R.drawable.reacher, 4.4f, "Jack Reacher, un veterano investigador de la policía militar, es arrestado falsamente por un asesinato en un pequeño pueblo, viéndose envuelto en una red de policías y políticos corruptos."));

                listaAFiltrar.add(new Pelicula("Fleabag", R.drawable.fleabag, 4.8f, "Una mirada tan divertida como desgarradora a la mente de una mujer con el corazón roto, ingeniosa y cargada de ira, que intenta salir adelante en Londres mientras rechaza cualquier tipo de ayuda."));

                listaAFiltrar.add(new Pelicula("Jack Ryan", R.drawable.jack_ryan, 4.2f, "Un analista de la CIA es catapultado de la seguridad de su escritorio al peligroso trabajo de campo por toda Europa y Oriente Medio tras descubrir un patrón de comunicaciones terroristas."));

                listaAFiltrar.add(new Pelicula("Wheel of Time", R.drawable.wheel_time, 4.0f, "En un vasto y épico mundo donde la magia existe pero solo unas pocas mujeres pueden usarla, una organización mágica guía a cinco jóvenes en un viaje para descubrir quién es el Dragón Renacido."));

                listaAFiltrar.add(new Pelicula("Good Omens", R.drawable.good_omens, 4.3f, "Un ángel quisquilloso y un demonio de vida disipada que se han encariñado demasiado con la vida en la Tierra se ven obligados a formar una alianza imposible para sabotear el Apocalipsis."));
            } else if (plataforma.equalsIgnoreCase("DAZN")) {
                listaAFiltrar.add(new Pelicula("Formula 1", R.drawable.f1, 4.9f, "Sigue en directo cada gran premio, las sesiones de clasificación y los entrenamientos libres del mundial de automovilismo, con análisis técnicos de expertos y cámaras exclusivas desde el paddock."));

                listaAFiltrar.add(new Pelicula("MotoGP", R.drawable.motogp, 4.7f, "Disfruta de la máxima emoción sobre dos ruedas con todos los grandes premios de las categorías de MotoGP, Moto2 y Moto3, además de reportajes y entrevistas en el pit lane."));

                listaAFiltrar.add(new Pelicula("LaLiga EA Sports", R.drawable.laliga, 4.5f, "Los partidos clave de cada jornada de la primera división del fútbol español en directo, acompañados de resúmenes detallados, análisis tácticos post-partido y un seguimiento minucioso."));

                listaAFiltrar.add(new Pelicula("Premier League", R.drawable.premier_league, 4.6f, "Vive la pasión del fútbol inglés con la cobertura total de la liga más intensa y competitiva del mundo, incluyendo retransmisiones en directo y programas de debate especializado."));
            }
        }

        // 4. Le pasamos la lista resultante al adaptador único de películas
        PeliculaAdapter adapter = new PeliculaAdapter(listaAFiltrar);
        rv.setAdapter(adapter);
    }
}