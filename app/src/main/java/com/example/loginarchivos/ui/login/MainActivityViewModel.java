package com.example.loginarchivos.ui.login;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.loginarchivos.model.Usuario;
import com.example.loginarchivos.requst.ApiClient;
import com.example.loginarchivos.ui.registro.RegistroActivity;

public class MainActivityViewModel extends AndroidViewModel {

    private Context context;
    private ApiClient apiClient;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        context=application.getApplicationContext();
    }

    public void validar(String email, String pass){
        Usuario usuarioRegistrado = new Usuario();
        usuarioRegistrado = apiClient.leer(context);
        if((usuarioRegistrado == null) || (usuarioRegistrado.getDni() == -1)){
            Toast.makeText(context,"Registrate!",Toast.LENGTH_LONG).show();
        }else{
            usuarioRegistrado=apiClient.login(context,email,pass);
            if(usuarioRegistrado != null){
                Intent intent = new Intent(context, RegistroActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }else{
                Toast.makeText(getApplication(), "Datos incorrectos", Toast.LENGTH_LONG).show();
            }

        }

    }

    public void registrarse()
    {
        Usuario usuario = new Usuario(0,"","","","");
        apiClient.guardar(context,usuario);
        Intent intent = new Intent(context,RegistroActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent);

    }

}
