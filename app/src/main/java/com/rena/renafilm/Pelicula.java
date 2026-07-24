package com.rena.renafilm;

public class Pelicula {
    // Atributos privados
    private String nombre;
    private int imagenResId; // ⚠️ ID del drawable (R.drawable.lo_que_sea)
    private float puntuacion;
    private String descripcionPelicula;

    // CONSTRUCTOR: El molde para crear plataformas individuales
    public Pelicula(String nombre, int imagenResId, float puntuacion, String descripcionPelicula) {
        this.nombre = nombre;
        this.imagenResId = imagenResId;
        this.puntuacion = puntuacion;
        this.descripcionPelicula = descripcionPelicula;
    }
    // GETTERS: Obligatorios para que el Adapter pueda leer los datos en onBindViewHolder
    public String getNombre() {
        return nombre;
    }
    public int getImagenResId() {
        return imagenResId;
    }
    public float getPuntuacion(){return puntuacion;}
    public String getDescripcionPelicula(){return descripcionPelicula;}
}
