package com.marto.tp_sqlite_polshu.model;

public class Usuario {
    private int Id;
    private int FechaNacimiento;
    private String Nombre,Apellido;
    private boolean Genero;

    public Usuario(int id, int fecha, String nombre, String apellido, boolean genero){
        this.Id = id;
        this.FechaNacimiento = fecha;
        this.Nombre = nombre;
        this.Apellido = apellido;
        this.Genero = genero;
    }

    public String getNombre() {
        return Nombre;
    }

    public int getId() {
        return Id;
    }

    public int getFechaNacimiento() {
        return FechaNacimiento;
    }

    public String getApellido() {
        return Apellido;
    }

    public boolean getGenero() {
        return Genero;
    }
}
