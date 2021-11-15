package com.marto.tp_sqlite_polshu.Helpers;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.marto.tp_sqlite_polshu.model.Noticia;
import com.marto.tp_sqlite_polshu.model.Usuario;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MartoDB";
    public static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void insertarNoticia(SQLiteDatabase db, Noticia n){
        ContentValues values = NoticiasService.insertarNoticia(n);
        db.insert(NoticiasService.NOMBRE_TABLA, null, values);
    }

    private void insertarUsuario(SQLiteDatabase db, Usuario user){
        ContentValues values = UsuariosService.insertarUsuario(user);
        db.insert(UsuariosService.NOMBRE_TABLA, null, values);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
        db.setForeignKeyConstraintsEnabled(true);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(UsuariosService.CREAR_TABLA);
        db.execSQL(NoticiasService.CREAR_TABLA);
        // insertamos algnos registros
        Usuario[] usuarios = new Usuario[5];
        Noticia[] noticias = new Noticia[3];
        usuarios[0] = new Usuario(20010811,"Jorge","Nitales","contra", true);
        usuarios[1] = new Usuario(20010811,"Martin","Schifer","contra", true);
        usuarios[2] = new Usuario(19990618,"Martina","yiffer","contra",  false);
        usuarios[3] = new Usuario(20010811,"Patricia","Bullrich","contra",  false);
        usuarios[4] = new Usuario(20010811,"Polshu","Polsheta","contra",  true);

        noticias[0] = new Noticia(2,"Aguante el comunismo", "el comunismo es muy bueno!!!", 20210505);
        noticias[1] = new Noticia(1,"Manuel martinez nuevo modeloo", "potencial miss universo", 20210506);
        noticias[2] = new Noticia(3,"Doge coin es revolucionario", "Compren todos Doge ya!!!", 20210507);

        for (Usuario u:
             usuarios) {
            insertarUsuario(db, u);
        }

        for (Noticia n:
                noticias) {
            insertarNoticia(db, n);
        }
        CustomLog.log("base de datos creada");
    }

    public void borrarNoticia(int id){
        this.getWritableDatabase().delete(NoticiasService.NOMBRE_TABLA,
                NoticiasService.KEY_NOTICIAS_ID + "=" + id, null);
        CustomLog.log("borrado con exito");
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        switch (DB_VERSION){
            case 1:
                //hacer algo
                break;
            case 2:
                db.execSQL("DROP TABLE IF EXISTS " +NoticiasService.NOMBRE_TABLA );
                db.execSQL("DROP TABLE IF EXISTS " +UsuariosService.NOMBRE_TABLA );
                CustomLog.log("borre tablas viejas");
                onCreate(db);
                break;
        }

    }
    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion , int newVersion){
        db.setVersion(oldVersion);
    }
}
