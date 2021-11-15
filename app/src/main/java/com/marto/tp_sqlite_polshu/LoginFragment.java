package com.marto.tp_sqlite_polshu;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.marto.tp_sqlite_polshu.Helpers.AlertHelper;
import com.marto.tp_sqlite_polshu.Helpers.UsuariosService;
import com.marto.tp_sqlite_polshu.model.Session;
import com.marto.tp_sqlite_polshu.model.Usuario;


public class LoginFragment extends BaseFragment {
    View rootlayout;
    MainActivity main;
    EditText etUsername, etUserpassword;
    Button btnLogin;
    UsuariosService service;

    public LoginFragment() {
        // Required empty public constructor
    }
    public void inicializar(){
        main = (MainActivity) getActivity();
        service = new UsuariosService(main);
        if(rootlayout != null){
            etUsername = (EditText) rootlayout.findViewById(R.id.etUsername);
            etUserpassword = (EditText) rootlayout.findViewById(R.id.etUserpassword);
            btnLogin = (Button) rootlayout.findViewById(R.id.btnLogin);
        }
    }

    public void setearListeners(){
        btnLogin.setOnClickListener(btn_login_click);
    }

    View.OnClickListener btn_login_click = v -> {
        if(esFormValido()){
            Usuario aux = service.findUser(etUsername.getText().toString().trim(),
                    etUserpassword.getText().toString().trim());
            if(aux != null){
                Session.currentUser = aux;
                main.SavePreferences("nombre", aux.getNombre());
                main.SavePreferences("contra", aux.getContra());
                main.irAListado();
            }
            else AlertHelper.mostrarAlertaError(getContext(), "El usuario o contrseña no son correctos");
        }
    };


    private boolean esFormValido(){
        if(etUserpassword.getText().toString().trim().equals("") && etUsername.getText().toString().trim().equals("")){
            AlertHelper.mostrarAlertaError(getContext(), "Por favor, el usuario y contraseña deben estar llenos");
            return false;
        }
        return true;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         if(rootlayout == null) rootlayout =  inflater.inflate(R.layout.fragment_login, container, false);
         inicializar();
         setearListeners();
         return  rootlayout;
    }
}