package com.optic.overtime;

public class ListaExcel {

    String Nombre;
    String id;
    String codebar;

    public ListaExcel(String nombre, String id, String codebar) {
        Nombre = nombre;
        this.id = id;
        this.codebar = codebar;
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

    public String getCodebar() {
        return codebar;
    }

    public void setCodebar(String codebar) {
        this.codebar = codebar;
    }

    @Override
    public String toString() {
        return "ListaExcel{" +
                "Nombre='" + Nombre + '\'' +
                ", id='" + id + '\'' +
                ", codebar='" + codebar + '\'' +
                '}';
    }
}
