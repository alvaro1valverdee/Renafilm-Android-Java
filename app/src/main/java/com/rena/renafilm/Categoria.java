package com.rena.renafilm;

public class Categoria {
    // Atributos privados
    private String nombre;
    private int imagenResId; // ⚠️ ID del drawable (R.drawable.lo_que_sea)

    private String codigoEndpoint; // 🔑 NUEVO: LA LLAVE MAESTRA

    // CONSTRUCTOR: El molde para crear plataformas individuales
    public Categoria(String nombre, int imagenResId, String codigoEndpoint) {
        this.nombre = nombre;
        this.imagenResId = imagenResId;
        this.codigoEndpoint = codigoEndpoint;
    }
    // GETTERS: Obligatorios para que el Adapter pueda leer los datos en onBindViewHolder
    public String getNombre() {
        return nombre;
    }
    public int getImagenResId() {
        return imagenResId;
    }

    public String getCodigoEndpoint() { return codigoEndpoint; }

    public void setCodigoEndpoint(String codigoEndpoint) { this.codigoEndpoint = codigoEndpoint; }
}
