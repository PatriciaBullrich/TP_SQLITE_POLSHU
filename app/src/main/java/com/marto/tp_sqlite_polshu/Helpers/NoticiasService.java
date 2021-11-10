package com.marto.tp_sqlite_polshu.Helpers;

import android.database.Cursor;

import com.marto.tp_sqlite_polshu.model.Noticia;

public class NoticiasService {
    public static final String NOMBRE_TABLA = "Noticias";
    // columnas:
    public static final String KEY_NOTICIAS_ID= "Id";
    public static final String KEY_NOTICIAS_NOMBRE= "Nombre";
    public static final String KEY_NOTICIAS_DESC= "Descripcion";
    public static final String KEY_NOTICIAS_FECHA= "Fecha";
    public static final String FOREIGN_KEY_NOTICIAS_IDusuario = "IdUsuario";

    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA +
            "(" +
            KEY_NOTICIAS_ID + " INTEGER PRIMARY KEY," + // Define a primary key
            KEY_NOTICIAS_NOMBRE + " TEXT " +
            KEY_NOTICIAS_DESC + " TEXT" +
            KEY_NOTICIAS_FECHA +  "INTEGER" +
            FOREIGN_KEY_NOTICIAS_IDusuario + "INTEGER REFERENCES" + UsuariosService.NOMBRE_TABLA + "," +
        ")";


    public static Noticia cursorToEntity(Cursor c){
        Noticia aux = null;
        if(c!= null){
            aux = new Noticia(c.getInt(c.getColumnIndex(KEY_NOTICIAS_ID)),
                    c.getInt(c.getColumnIndex(FOREIGN_KEY_NOTICIAS_IDusuario)),
                    c.getString(c.getColumnIndex(KEY_NOTICIAS_NOMBRE)),
                    c.getString(c.getColumnIndex(KEY_NOTICIAS_DESC)),
                    c.getInt(c.getColumnIndex(KEY_NOTICIAS_FECHA)));
        }
       return  aux;
    }
}
