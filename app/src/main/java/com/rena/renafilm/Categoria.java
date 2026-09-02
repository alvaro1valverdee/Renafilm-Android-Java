package com.rena.renafilm;

public class Categoria {
    // Atributos privados
    private String nombre;
    private int imagenResId;
    private int fondoResId;

    private String codigoEndpoint; // 🔑 NUEVO: LA LLAVE MAESTRA

    // CONSTRUCTOR: El molde para crear plataformas individuales
    public Categoria(String nombre, int imagenResId, String codigoEndpoint, int fondoResId) {
        this.nombre = nombre;
        this.imagenResId = imagenResId;
        this.codigoEndpoint = codigoEndpoint;
        this.fondoResId = fondoResId;
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

    public int getFondoResId() { return fondoResId; }

    public void setFondoResId(int fondoResId) { this.fondoResId = fondoResId; }
}
