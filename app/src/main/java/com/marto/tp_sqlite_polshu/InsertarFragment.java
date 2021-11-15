package com.marto.tp_sqlite_polshu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.marto.tp_sqlite_polshu.Helpers.AlertHelper;
import com.marto.tp_sqlite_polshu.Helpers.CustomLog;
import com.marto.tp_sqlite_polshu.Helpers.DbHelper;
import com.marto.tp_sqlite_polshu.Helpers.NoticiasService;
import com.marto.tp_sqlite_polshu.Helpers.ParseHelper;
import com.marto.tp_sqlite_polshu.model.Noticia;
import com.marto.tp_sqlite_polshu.model.Session;

import java.util.Date;


public class InsertarFragment extends BaseFragment implements BasicMethods{
    View rootlayout;
    MainActivity main;
    Button btn_insertar;
    Button btn_volver2;
    EditText et_nombre, et_desc;
    DbHelper helper;

    //TODO FALTA EL XML


    public InsertarFragment() {
        // Required empty public constructor
    }

    @Override
    public void inicializar(){
        main = (MainActivity) getActivity();
        helper = new DbHelper(main);
        if(rootlayout != null){
            btn_insertar = (Button) rootlayout.findViewById(R.id.btn_insertar);
            if(btn_insertar == null) CustomLog.log("lpm");
            btn_volver2 = (Button) rootlayout.findViewById(R.id.btn_volver2);
            et_desc = (EditText) rootlayout.findViewById(R.id.etDesc);
            et_nombre = (EditText) rootlayout.findViewById(R.id.etTitulo);
        }
    }

    private boolean esFormValido(){
    if(!et_nombre.getText().toString().trim().equals("") && !et_desc.getText().toString().trim().equals("")) return true;
        AlertHelper.mostrarAlertaError(main, "debe llenar todos las campos para crear una noticia");
        return false;
    }

    View.OnClickListener btn_insertar_click = v ->{
        if(esFormValido()){
            Noticia nueva = new Noticia(-1, Session.currentUser.getId(), et_nombre.getText().toString().trim()
            ,et_desc.getText().toString().trim(), ParseHelper.dateToInteger(new Date()));
            helper.insertarNoticia(helper.getWritableDatabase(), nueva);
            main.irAListado();
        }
    };

    View.OnClickListener btn_voler_click = v -> main.irAListado();


    @Override
    public void setearListeners() {
        btn_insertar.setOnClickListener(btn_insertar_click);
        btn_volver2.setOnClickListener(btn_voler_click);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(rootlayout == null) rootlayout = inflater.inflate(R.layout.fragment_insertar, container, false);
        inicializar();
        setearListeners();
        return  rootlayout;
    }
}