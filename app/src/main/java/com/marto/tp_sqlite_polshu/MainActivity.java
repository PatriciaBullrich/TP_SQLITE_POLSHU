package com.marto.tp_sqlite_polshu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends BaseActivity {
    public static final int ITEM_LOGOUT = R.id.action_logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

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
            default:
                aux = false;
        }
        return aux;
    }

    private void logout() {
    }

}
