package com.marto.tp_sqlite_polshu.model;

public class Session {
    public static Usuario currentUser = new Usuario(0,"invitado", "","", false);

    public static  void cleanSession(){
        currentUser = new Usuario(0,"invitado", "","", false);;
    }
}
