package com.marto.tp_sqlite_polshu.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marto.tp_sqlite_polshu.model.Noticia;
import com.marto.tp_sqlite_polshu.model.Usuario;

import java.util.ArrayList;

public class NoticiasService {
    public static final String NOMBRE_TABLA = "Noticias";
    // columnas:
    public static final String KEY_NOTICIAS_ID= "Id";
    public static final String KEY_NOTICIAS_NOMBRE= "Nombre";
    public static final String KEY_NOTICIAS_DESC= "Descripcion";
    public static final String KEY_NOTICIAS_FECHA= "Fecha";
    public static final String FOREIGN_KEY_NOTICIAS_IDusuario = "IdUsuario";

    private final DbHelper _helper;
    public NoticiasService(Context c){
        _helper = new DbHelper(c);
    }


    public static ContentValues insertarNoticia (Noticia n){
        ContentValues values = new ContentValues();
        if(n.getId() >=0) values.put(KEY_NOTICIAS_ID, n.getId());
        values.put(KEY_NOTICIAS_NOMBRE, n.getNombre());
        values.put(KEY_NOTICIAS_DESC, n.getDescripcion());
        values.put(KEY_NOTICIAS_FECHA, n.getFecha());
        values.put(FOREIGN_KEY_NOTICIAS_IDusuario, n.getIdUsuario());
        return  values;
    }

    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA +
            "(" +
            KEY_NOTICIAS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + // Define a primary key
            KEY_NOTICIAS_NOMBRE + " TEXT " +
            KEY_NOTICIAS_DESC + " TEXT" +
            KEY_NOTICIAS_FECHA +  "INTEGER" +
            FOREIGN_KEY_NOTICIAS_IDusuario + "INTEGER REFERENCES" + UsuariosService.NOMBRE_TABLA + "," +
        ")";

    public ArrayList<Noticia> getALl(){
        ArrayList<Noticia> lista = new ArrayList<>();
        SQLiteDatabase db;
        Cursor cursor;
        db = _helper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM " + NOMBRE_TABLA + " ORDER BY " + KEY_NOTICIAS_FECHA, null);
        if(cursor != null) {
            while (cursor.moveToNext()) {
                lista.add(cursorToEntity(cursor));
            }
        }
        return lista;
    }


    public String findAutor(int id){
        String autor = null;
        SQLiteDatabase db;
        Cursor c;
        db = _helper.getReadableDatabase();
        c = db.rawQuery("SELECT Usuarios.nombre AS autor FROM Noticias INNER JOIN Usuarios ON Noticias.IdUsuario = Usuarios.Id WHERE Noticias.IDusuario=? ", new String[]{String.valueOf(id)});
        if(c != null){
            while (c.moveToFirst()){
                autor = c.getString(c.getColumnIndex("autor"));
            }
            c.close();
        }
        return autor;
    }


    public static Noticia cursorToEntity(Cursor c){
        Noticia aux = null;
        if(c!= null){
            aux = new Noticia(c.getInt(c.getColumnIndex(KEY_NOTICIAS_ID)),
                    c.getInt(c.getColumnIndex(FOREIGN_KEY_NOTICIAS_IDusuario)),
                    c.getString(c.getColumnIndex(KEY_NOTICIAS_NOMBRE)),
                    c.getString(c.getColumnIndex(KEY_NOTICIAS_DESC)),
                    c.getInt(c.getColumnIndex(KEY_NOTICIAS_FECHA)));
                    c.close();
        }
       return  aux;
    }
}
