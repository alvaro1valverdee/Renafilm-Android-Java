package com.rena.renafilm.api;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {

    // URL principal de la API de TMDB
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    private static Retrofit retrofit = null;

    // Obtener la instancia de Retrofit
    public static TmdbApi getApi() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()) // Aquí usa el GSON que pusimos en el Gradle
                    .build();
        }
        return retrofit.create(TmdbApi.class);
    }
}