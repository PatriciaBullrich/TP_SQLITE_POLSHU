package com.marto.tp_sqlite_polshu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


public class ListadoFragment extends BaseFragment {
        View rootlayout;
        MainActivity main;


    public ListadoFragment() {
        // Required empty public constructor
    }

    public void inicializar(){
        main = (MainActivity) getActivity();
        if(rootlayout != null){

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       if(rootlayout == null)  rootlayout =  inflater.inflate(R.layout.fragment_listado, container, false);
       inicializar();
       return rootlayout;
    }
}