package com.marto.tp_sqlite_polshu.Helpers;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.marto.tp_sqlite_polshu.model.Usuario;

public class UsuariosService {
    public static final String NOMBRE_TABLA = "Usuarios";
    // columnas:
    public static final String KEY_USUARIOS_ID= "Id";
    public static final String KEY_USUARIOS_NOMBRE= "Nombre";
    public static final String KEY_USUARIOS_CONTRA= "Password";
    public static final String KEY_USUARIOS_APELLIDO= "Apellido";
    public static final String KEY_USUARIOS_FECHA= "FechaNacimiento";
    public static final String KEY_USUARIOS_GENERO = "Genero";

    private final DbHelper _helper;

    public UsuariosService(Context c){
        _helper = new DbHelper(c);
    }


    public static final String CREAR_TABLA = "CREATE TABLE " + NOMBRE_TABLA +
            "(" +
            KEY_USUARIOS_ID + " INTEGER PRIMARY KEY," + // Define a primary key
            KEY_USUARIOS_NOMBRE + " TEXT " +
            KEY_USUARIOS_CONTRA + "TEXT" +
            KEY_USUARIOS_APELLIDO + " TEXT" +
            KEY_USUARIOS_FECHA +  "INTEGER" +
            KEY_USUARIOS_GENERO + "INTEGER" +
            ")";

    public static ContentValues insertarUsuario (Usuario user){
        ContentValues values = new ContentValues();
        values.put(KEY_USUARIOS_ID, user.getId());
        values.put(KEY_USUARIOS_CONTRA,user.getContra());
        values.put(KEY_USUARIOS_NOMBRE, user.getNombre());
        values.put(KEY_USUARIOS_APELLIDO, user.getApellido());
        values.put(KEY_USUARIOS_FECHA, user.getFechaNacimiento());
        values.put(KEY_USUARIOS_GENERO, booleanToNum(user.getGenero()));
        return values;
    }

    public Usuario findUser(String nombre, String contra){
        Usuario aux = null;
        SQLiteDatabase db;
        Cursor cursor;
         db = _helper.getReadableDatabase();
         cursor = db.rawQuery(String.format("SELECT * FROM %s WHERE Nombre = ? AND Password = ?",NOMBRE_TABLA),
                 new String[]{nombre, contra});
         if (cursor!= null) aux = cursorToEntity(cursor);
         else CustomLog.log("el cursor llego nulo");
        return aux;
    }

    private static boolean numToBoolean (int n) {return n==1;}

    private static int booleanToNum (boolean b){
        if(b) return 1;
        return 0;
    }

    public static Usuario cursorToEntity(Cursor c){
        Usuario aux = null;
        if(c!= null){
            aux = new Usuario(c.getInt(c.getColumnIndex(KEY_USUARIOS_ID)),
                    c.getInt(c.getColumnIndex(KEY_USUARIOS_FECHA)),
                    c.getString(c.getColumnIndex(KEY_USUARIOS_NOMBRE)),
                    c.getString(c.getColumnIndex(KEY_USUARIOS_APELLIDO)),
                    c.getString(c.getColumnIndex(KEY_USUARIOS_CONTRA)),
                    numToBoolean(c.getInt(c.getColumnIndex(KEY_USUARIOS_GENERO))));
                    c.close();
        }
        return  aux;
    }


}
