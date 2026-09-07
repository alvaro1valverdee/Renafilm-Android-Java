package com.rena.renafilm.api;
import com.rena.renafilm.modelo.PeliResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
public interface TmdbApi {
    // 1. Peliculas Populares
    @GET("movie/popular")
    Call<PeliResponse> getPopularMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language, // Para pedir que nos devuelva los datos en español
            @Query("page") int page
    );
    // 2. Películas Mejor Valoradas
    @GET("movie/top_rated")
    Call<PeliResponse> getTopRatedMovies(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );

    // 3. Series Populares
    @GET("tv/popular")
    Call<PeliResponse> getPopularTvShows(
            @Query("api_key") String apiKey,
            @Query("language") String language,
            @Query("page") int page
    );
}
