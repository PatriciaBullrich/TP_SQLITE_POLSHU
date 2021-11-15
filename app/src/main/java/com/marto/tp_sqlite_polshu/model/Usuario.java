package com.marto.tp_sqlite_polshu.model;

public class Usuario {
    private int Id;
    private int FechaNacimiento;
    private String Nombre,Apellido,Contra;
    private boolean Genero;

    public Usuario(int id, int fecha, String nombre, String apellido,String contra ,boolean genero){
        this.Id = id;
        this.FechaNacimiento = fecha;
        this.Nombre = nombre;
        this.Contra = contra;
        this.Apellido = apellido;
        this.Genero = genero;
    }

    public Usuario(int fecha, String nombre, String apellido,String contra ,boolean genero){
        this.FechaNacimiento = fecha;
        this.Nombre = nombre;
        this.Contra = contra;
        this.Apellido = apellido;
        this.Genero = genero;
    }



    public String getContra() {
        return Contra;
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
