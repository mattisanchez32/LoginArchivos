package com.example.loginarchivos.ui.registro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginarchivos.R;
import com.example.loginarchivos.model.Usuario;

public class RegistroActivity extends AppCompatActivity {

    private EditText etDni,etApellido,etNombre,etEmail,etPass;
    private Button btnGuardar;
    private RegistroActivityViewModel vm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        inicializar();
    }

    public void inicializar(){
        etDni=findViewById(R.id.etDni);
        etApellido=findViewById(R.id.etApellido);
        etNombre=findViewById(R.id.etNombre);
        etEmail=findViewById(R.id.etEmailRegistro);
        etPass=findViewById(R.id.etPassRegistro);
        btnGuardar=findViewById(R.id.btnGuardar);


        vm = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(RegistroActivityViewModel.class);
        vm.getUsuarioLiveData().observe(this, new Observer<Usuario>() {
            @Override
            public void onChanged(Usuario usuario) {
                etDni.setText(usuario.getDni()+"");
                etApellido.setText(usuario.getApellido());
                etNombre.setText(usuario.getNombre());
                etEmail.setText(usuario.getEmail());
                etPass.setText(usuario.getPass());
            }
        });


        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Long dni=Long.parseLong(etDni.getText().toString());
                String apellido=etApellido.getText().toString();
                String nombre=etNombre.getText().toString();
                String email=etEmail.getText().toString();
                String pass=etPass.getText().toString();
                Usuario usi = new Usuario(dni,apellido,nombre,email,pass);
                vm.guardar(usi);
            }
        });

        vm.cargar();

    }

}