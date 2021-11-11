package com.marto.tp_sqlite_polshu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.marto.tp_sqlite_polshu.Helpers.CustomLog;
import com.marto.tp_sqlite_polshu.Helpers.NoticiasService;
import com.marto.tp_sqlite_polshu.Helpers.ParseHelper;
import com.marto.tp_sqlite_polshu.model.Noticia;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;


public class ListadoFragment extends BaseFragment implements BasicMethods{
        View rootlayout;
        MainActivity main;
        ListView lv_noticias;
        ArrayList<Noticia> noticias = new ArrayList<>();
        NoticiasService service;
        Button btn_noticia;


    public ListadoFragment() {
        // Required empty public constructor
    }

    @Override
    public void inicializar(){
        main = (MainActivity) getActivity();
        if(rootlayout != null){
            service =   new NoticiasService(main);
            noticias = service.getALl();
            lv_noticias = (ListView) rootlayout.findViewById(R.id.gone); // falta xml
            btn_noticia = (Button) rootlayout.findViewById(R.id.gone); // falta xml
            llenarListView();
        }
    }
    View.OnClickListener btn_noticia_click = v -> main.irAInsert();

    private void llenarListView(){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(main, android.R.layout.simple_list_item_1,adaptarLista());
        lv_noticias.setAdapter(adapter);
    }

    private ArrayList<String> adaptarLista(){
        ArrayList<String> lista = new ArrayList<>();
        if(noticias.size()> 0){
            for (Noticia n:
                    noticias) {
                String autor = service.findAutor(n.getId());
                String formato = String.format("Tiulo: %s autor: %s + fecha: %s", n.getNombre(), autor, ParseHelper.integerToDate(n.getFecha()));
                lista.add(formato);
            }
        }
        return lista;
    }

    @Override
    public void setearListeners() {
        lv_noticias.setOnItemClickListener((parent, view, position, id) -> {
            main.llenarDetalle(noticias.get(position));
            main.irADetalle();
        });
        btn_noticia.setOnClickListener(btn_noticia_click);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       if(rootlayout == null)  rootlayout =  inflater.inflate(R.layout.fragment_listado, container, false);
       inicializar();
       setearListeners();
       return rootlayout;
    }
}