package com.example.loginarchivos.ui.registro;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.loginarchivos.model.Usuario;
import com.example.loginarchivos.requst.ApiClient;
import com.example.loginarchivos.ui.login.MainActivity;

public class RegistroActivityViewModel extends AndroidViewModel {

    private MutableLiveData<Usuario> usuarioMutableLiveData;
    private ApiClient apiClient;
    private Context context;



    public RegistroActivityViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }


    public LiveData<Usuario> getUsuarioLiveData(){
        if(usuarioMutableLiveData == null){
            usuarioMutableLiveData = new MutableLiveData<>();
        }
        return usuarioMutableLiveData;
    }

    public void guardar(Usuario usuario){



        if(usuario.getDni()==0) {
            Toast.makeText(getApplication(), "ingrese datos y/o cambie valor de dni 0", Toast.LENGTH_LONG).show();
        }else{


            apiClient.guardar(context, usuario);
            Toast.makeText(getApplication(), "Datos guardados correctamente", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(context, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }

    }

    public void cargar(){


        Usuario user=apiClient.leer(context);
        if(user.getDni()!= 0){
            usuarioMutableLiveData.setValue(user);
        } else{
            Usuario usu = new Usuario();
            usuarioMutableLiveData.setValue(usu);
        }


    }

}
