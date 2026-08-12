package com.rena.renafilm.api;
import com.rena.renafilm.modelo.Peli;
import com.rena.renafilm.modelo.PeliResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface TmdbApi {
    // Le decimos que haga una petición GET a la ruta "movie/popular"
    @GET("movie/popular")
    Call<PeliResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language // Para pedir que nos devuelva los datos en español
    );
}
