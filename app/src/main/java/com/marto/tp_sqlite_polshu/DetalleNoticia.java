package com.marto.tp_sqlite_polshu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.marto.tp_sqlite_polshu.Helpers.AlertHelper;
import com.marto.tp_sqlite_polshu.model.Noticia;


public class DetalleNoticia extends Fragment {
    View rootlayout;
    MainActivity main;
    TextView tv_titulo, tv_detalle;
    Noticia laNoticia;
    Button btn_volver;
    public void llenarNoticia(Noticia n){
        if(n == null) AlertHelper.mostrarAlertaError(getContext(), "me llego una noticia nula");
        else laNoticia = n;
    }


    public DetalleNoticia() {
        // Required empty public constructor
    }

    View.OnClickListener btn_volver_click = v -> main.irAListado();

    public void setearListeners(){btn_volver.setOnClickListener(btn_volver_click);}


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         if (rootlayout == null) rootlayout =  inflater.inflate(R.layout.fragment_detalle_noticia, container, false);
         inicializar();
         setearListeners();
         return rootlayout;
    }

    private void inicializar() {
        main = (MainActivity) getActivity();
        if (rootlayout != null && laNoticia != null){
            tv_titulo = (TextView) rootlayout.findViewById(R.id.tv_titulo);
            tv_detalle = (TextView) rootlayout.findViewById(R.id.tv_desc);
            btn_volver = (Button) rootlayout.findViewById(R.id.btn_volver);
            tv_titulo.setText(laNoticia.getNombre());
            tv_detalle.setText(laNoticia.getDescripcion());
        }
    }
}