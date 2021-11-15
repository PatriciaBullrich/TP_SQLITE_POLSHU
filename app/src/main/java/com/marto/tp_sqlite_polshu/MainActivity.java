package com.marto.tp_sqlite_polshu;

import androidx.annotation.NonNull;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.marto.tp_sqlite_polshu.Helpers.CustomLog;
import com.marto.tp_sqlite_polshu.Helpers.UsuariosService;
import com.marto.tp_sqlite_polshu.model.Noticia;
import com.marto.tp_sqlite_polshu.model.Session;

public class MainActivity extends BaseActivity {
    public static final int ITEM_LOGOUT = R.id.action_logout;
    public static final int ITEM_PEERFIL = R.id.action_perfil;

    Menu miMenu;
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
            silentLogin(ReadPreferences("nombre", "string").toString(), ReadPreferences("contra","string").toString());
        }
         else reemplazarFragment(frg_login, false);
    }

    public void silentLogin(String nombre, String contra){
        UsuariosService service = new UsuariosService(this);
        Session.currentUser = service.findUser(nombre, contra);
        reemplazarFragment(frg_list);
    }

    public void irAInsert(){reemplazarFragment(frg_insertar);}
    public void irALogin(){reemplazarFragment(frg_login);}
    public void irADetalle(){reemplazarFragment(frg_detalle);}
    public void irAListado(){
        reemplazarFragment(frg_list);
        if(!miMenu.hasVisibleItems()){
            miMenu.findItem(ITEM_LOGOUT).setVisible(true);
            miMenu.findItem(ITEM_PEERFIL).setVisible(true);
        }
    }
    public void irAPerfil(){reemplazarFragment(frg_perfil);}

    public void llenarDetalle(Noticia n){frg_detalle.llenarNoticia(n);}

    @Override public  boolean onCreateOptionsMenu(Menu menu)  {
            getMenuInflater().inflate(R.menu.mi_menu,  menu);
            miMenu = menu;
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
                break;
            case ITEM_PEERFIL:
                CustomLog.log("vine por aca");
                irAPerfil();
                break;
            default:
                aux = false;
                break;
        }
        return aux;
    }

     @Override  public boolean onPrepareOptionsMenu(Menu menu)
    {
        MenuItem logout = menu.findItem(ITEM_LOGOUT);
        MenuItem perfil = menu.findItem(ITEM_PEERFIL);
        logout.setVisible(estaLogeado());
        perfil.setVisible(estaLogeado());
        return true;
    }
    public boolean estaLogeado(){
        return !ReadPreferences("nombre", "string").equals("");
    }

    private void logout() {
        clearPreferences();
        Session.cleanSession();
        irALogin();
        CustomLog.log("me deslogeo");
    }

}
