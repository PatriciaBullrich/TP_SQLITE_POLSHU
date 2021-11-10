package com.marto.tp_sqlite_polshu.model;

public class Noticia {
    private int Id, IdUsuario, Fecha;
    private String Nombre;
    private String Descripcion;

    public int getId() {
        return Id;
    }

    public int getIdUsuario() {
        return IdUsuario;
    }

    public int getFecha() {
        return Fecha;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public String getNombre() {
        return Nombre;
    }

    public Noticia(int id, int idUsuario, String nombre, String descripcion, int fecha){
        this.Id = id;
        this.IdUsuario = idUsuario;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Fecha = fecha;
    }

    public Noticia(int idUsuario, String nombre, String descripcion,int fecha){
        this.IdUsuario = idUsuario;
        this.Nombre = nombre;
        this.Descripcion = descripcion;
        this.Fecha = fecha;
    }


}
