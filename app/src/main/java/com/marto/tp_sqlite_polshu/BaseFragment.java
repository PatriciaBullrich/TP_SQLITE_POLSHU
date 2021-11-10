package com.marto.tp_sqlite_polshu;


import androidx.fragment.app.Fragment;



public class BaseFragment extends Fragment {

    public void ponerTitulo(String t){

        MainActivity main = (MainActivity) getActivity();

        main.setTitle(t);

    }



}