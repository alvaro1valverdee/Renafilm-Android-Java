package com.rena.renafilm.modelo;
import com.google.gson.annotations.SerializedName;
import java.util.List;
public class PeliResponse {
    @SerializedName("results")
    private List<Peli> results;

    public List<Peli> getResults() {
        return results;
    }
}
