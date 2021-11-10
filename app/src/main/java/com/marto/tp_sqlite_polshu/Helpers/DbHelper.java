package com.marto.tp_sqlite_polshu.Helpers;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.marto.tp_sqlite_polshu.model.Noticia;

public class DbHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "MartoDB";
    public static final int DB_VERSION = 1;

    public DbHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
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
        CustomLog.log("base de datos creada");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        switch (DB_VERSION){
            case 1:
                //hacer algo
                break;
            case 2:
                db.execSQL("DROP TABLE IF EXISTS " +UsuariosService.NOMBRE_TABLA );
                db.execSQL("DROP TABLE IF EXISTS " +NoticiasService.NOMBRE_TABLA );
                CustomLog.log("borre tablas viejas");
                onCreate(db);
                break;
        }
    }
}
