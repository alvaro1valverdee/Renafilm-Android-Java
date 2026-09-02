package com.rena.renafilm.modelo;
import com.google.gson.annotations.SerializedName;
public class Pelicula {
    @SerializedName("id")
    private int id;

    @SerializedName(value = "title", alternate = {"name"})
    private String title;

    @SerializedName("overview")
    private String overview;

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("vote_average")
    private double voteAverage;
    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getOverview() { return overview; }
    public String getPosterPath() { return posterPath; }
    public double getVoteAverage() { return voteAverage; }
}
