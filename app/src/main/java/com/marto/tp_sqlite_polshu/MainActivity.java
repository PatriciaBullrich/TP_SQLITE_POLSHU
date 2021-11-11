package com.marto.tp_sqlite_polshu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.marto.tp_sqlite_polshu.model.Noticia;
import com.marto.tp_sqlite_polshu.model.Session;

public class MainActivity extends BaseActivity {
    public static final int ITEM_LOGOUT = R.id.action_logout;
    public static final int ITEM_PEERFIL = R.id.action_perfil;

    InsertarFragment frg_insertar;
    DetalleNoticia frg_detalle;
    ListadoFragment frg_list;
    LoginFragment frg_login;
    PerfilFragment frg_perfil;

    private void inicializar(){
        frg_detalle = new DetalleNoticia();
        frg_insertar = new InsertarFragment();
        frg_list = new ListadoFragment();
        frg_login = new LoginFragment();
        frg_perfil = new PerfilFragment();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicializar();
        if(!ReadPreferences("nombre", "string").equals("") && !ReadPreferences("contra","string").equals("")){
            frg_login.silentLogin(ReadPreferences("nombre", "string").toString(), ReadPreferences("contra","string").toString());
        }
        reemplazarFragment(frg_login, false);
    }

    public void irAInsert(){reemplazarFragment(frg_insertar);}
    public void irALogin(){reemplazarFragment(frg_login);}
    public void irADetalle(){reemplazarFragment(frg_detalle);}
    public void irAListado(){reemplazarFragment(frg_list);}
    public void irAPerfil(){reemplazarFragment(frg_perfil);}

    public void llenarDetalle(Noticia n){frg_detalle.llenarNoticia(n);}

    @Override public  boolean onCreateOptionsMenu(Menu menu)  {
            getMenuInflater().inflate(R.menu.mi_menu,  menu);
            return  true;
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        boolean aux = true;
        String itemTitle;
        itemTitle = item.getTitle().toString();
        switch (item.getItemId()){
            case ITEM_LOGOUT:
                logout();
            case ITEM_PEERFIL:
                irAPerfil();
            default:
                aux = false;
        }
        return aux;
    }

    private void logout() {
        clearPreferences();
        Session.currentUser = null;
        irALogin();
    }

}
