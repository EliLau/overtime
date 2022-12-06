package com.optic.overtime;

import android.widget.Button;
import android.widget.ImageView;

public class ListElement {

    String nombre;
    String id;
    String codebar;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
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


   /* public ImageView getmFoto() {
        return mFoto;
    }

    public void setmFoto(ImageView mFoto) {
        this.mFoto = mFoto;
    }*/


    /*@Override
    public String toString(){
        return "ListElement{" +
                "nombre='" + nombre + '\'' +
             //   ", apellido='" + apellido + '\'' +
                ", id='" + id + '\'' +
              //  ", codebar='" + codebar + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return "" +
                "" + id +"\n"+
                "" + nombre + "" +
                ' ';
    }

    public ListElement(String nombre, String id) {
        this.nombre = nombre;
        this.id = id;
      //  this.mElminar = mElminar;
      //  this.mMostrar = mMostrar;
    }



}
