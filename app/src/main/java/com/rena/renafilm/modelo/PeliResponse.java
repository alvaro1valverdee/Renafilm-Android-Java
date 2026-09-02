package com.rena.renafilm.modelo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
public class PeliResponse {
    @SerializedName("results")
    private List<Pelicula> results;

    public List<Pelicula> getResults() {
        return results;
    }
}
