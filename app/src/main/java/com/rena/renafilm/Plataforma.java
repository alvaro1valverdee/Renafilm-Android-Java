package com.rena.renafilm;

public class Plataforma {
    // Atributos privados
    private String nombre;
    private int imagenResId; // ⚠️ ID del drawable (R.drawable.lo_que_sea)

    // CONSTRUCTOR: El molde para crear plataformas individuales
    public Plataforma(String nombre, int imagenResId) {
        this.nombre = nombre;
        this.imagenResId = imagenResId;
    }
    // GETTERS: Obligatorios para que el Adapter pueda leer los datos en onBindViewHolder
    public String getNombre() {
        return nombre;
    }
    public int getImagenResId() {
        return imagenResId;
    }
}
