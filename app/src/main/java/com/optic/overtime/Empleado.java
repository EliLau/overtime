package com.optic.overtime;

public class Empleado {

    private int id;
    private String nombre;
    private int codebar;

    public Empleado() {
    }

    public Empleado(int id, String nombre, int codebar) {
        this.id = id;
        this.nombre = nombre;
        this.codebar = codebar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCodebar() {
        return codebar;
    }

    public void setCodebar(int codebar) {
        this.codebar = codebar;
    }


    @Override
    public String toString() {
        return "" +
                "" + id +"\n"+
                "" + nombre + "" +
                ' ';
    }
}
