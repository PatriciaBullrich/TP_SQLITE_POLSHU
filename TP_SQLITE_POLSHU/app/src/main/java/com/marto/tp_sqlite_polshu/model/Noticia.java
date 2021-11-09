package com.marto.tp_sqlite_polshu.model;

public class Noticia {
    private int Id, IdUsuario;
    private String Nombre, Descripcion, Encabezado;

    public int getId() {
        return Id;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getEncabezado() {
        return Encabezado;
    }

    public String getNombre() {
        return Nombre;
    }

    public Noticia(int id, int idUsuario, String nombre, String descripcion, String encabezado){
        this.Id = id;
        this.IdUsuario = idUsuario;
        this.Nombre = nombre;
        this.Encabezado = encabezado;
        this.Descripcion = descripcion;
    }

    public Noticia(int idUsuario, String nombre, String descripcion, String encabezado){
        this.IdUsuario = idUsuario;
        this.Nombre = nombre;
        this.Encabezado = encabezado;
        this.Descripcion = descripcion;
    }


}
