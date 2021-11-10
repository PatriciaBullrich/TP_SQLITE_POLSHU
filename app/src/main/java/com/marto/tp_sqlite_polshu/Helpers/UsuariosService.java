package com.marto.tp_sqlite_polshu.Helpers;

import android.database.Cursor;

import com.marto.tp_sqlite_polshu.model.Usuario;

public class UsuariosService {
    public static final String NOMBRE_TABLA = "Usuarios";
    // columnas:
    public static final String KEY_USUARIOS_ID= "Id";
    public static final String KEY_USUARIOS_NOMBRE= "Nombre";
    public static final String KEY_USUARIOS_APELLIDO= "Apellido";
    public static final String KEY_USUARIOS_FECHA= "FechaNacimiento";
    public static final String KEY_USUARIOS_GENERO = "Genero";

    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA +
            "(" +
            KEY_USUARIOS_ID + " INTEGER PRIMARY KEY," + // Define a primary key
            KEY_USUARIOS_NOMBRE + " TEXT " +
            KEY_USUARIOS_APELLIDO + " TEXT" +
            KEY_USUARIOS_FECHA +  "INTEGER" +
            KEY_USUARIOS_GENERO + "INTEGER" +
            ")";

    static boolean numToBoolean (int n) {return n==1;}

    public static Usuario cursorToEntity(Cursor c){
        Usuario aux = null;
        if(c!= null){
            aux = new Usuario(c.getInt(c.getColumnIndex(KEY_USUARIOS_ID)),
                    c.getInt(c.getColumnIndex(KEY_USUARIOS_FECHA)),
                    c.getString(c.getColumnIndex(KEY_USUARIOS_NOMBRE)),
                    c.getString(c.getColumnIndex(KEY_USUARIOS_APELLIDO)),
                    numToBoolean(c.getInt(c.getColumnIndex(KEY_USUARIOS_GENERO))));
        }
        return  aux;
    }


}
