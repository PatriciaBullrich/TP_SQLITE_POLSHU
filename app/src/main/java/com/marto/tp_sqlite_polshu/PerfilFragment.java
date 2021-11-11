package com.marto.tp_sqlite_polshu;

import android.annotation.SuppressLint;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.marto.tp_sqlite_polshu.Helpers.ParseHelper;
import com.marto.tp_sqlite_polshu.model.Session;


public class PerfilFragment extends BaseFragment implements BasicMethods {
    View rootlayout;
    MainActivity main;
    TextView tv_userName, tv_fechaUser, tv_genero;
    Button btn_volver3;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        if(rootlayout == null) rootlayout = inflater.inflate(R.layout.fragment_perfil, container, false);
        inicializar();
        setearListeners();
        return rootlayout;
    }

    @SuppressLint("SetTextI18n")
    public void llenarDatos(){
        String nombreFormato = String.format("Nombre: %s %s", Session.currentUser.getNombre(), Session.currentUser.getApellido());
        tv_userName.setText(nombreFormato);
        tv_fechaUser.setText(String.format("Fecha de nacimiento: %s", ParseHelper.integerToDate(Session.currentUser.getFechaNacimiento())));
        if(Session.currentUser.getGenero()) tv_genero.setText("Genero: masculino");
        else tv_genero.setText("Genero: femenino");
    }

    View.OnClickListener btn_volver3_click = v -> main.irAListado();

    @Override
    public void inicializar() {
        main = (MainActivity) getActivity();
        if(rootlayout != null){
           tv_userName = (TextView) rootlayout.findViewById(R.id.tv_userName);
           tv_fechaUser = (TextView) rootlayout.findViewById(R.id.tv_fechaUser);
           tv_genero = (TextView) rootlayout.findViewById(R.id.tv_genero);
           btn_volver3 = (Button) rootlayout.findViewById(R.id.btn_volver3);
           llenarDatos();
        }
    }

    @Override
    public void setearListeners() {
        btn_volver3.setOnClickListener(btn_volver3_click);
    }
}