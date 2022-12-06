package com.optic.overtime;

public class ListaExcel {

    String Nombre;
    String id;

    public ListaExcel(String nombre, String id) {
        this.Nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "ListaExcel{" +
                "Nombre='" + Nombre + '\'' +
                ", id='" + id + '\'' +
                '}';
    }
}
