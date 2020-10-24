package com.example.loginarchivos.ui.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.loginarchivos.R;

public class MainActivity extends AppCompatActivity {

    private EditText etEmail,etPass;
    private Button btnGuardar, btnRegistrar;
    private MainActivityViewModel vm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inicialiar();
    }

    private void inicialiar(){
        etEmail=findViewById(R.id.etEmailLogin);
        etPass=findViewById(R.id.etPassLogin);
        btnGuardar=findViewById(R.id.btnLogin);
        btnRegistrar=findViewById(R.id.btnRegistrarse);
        vm= ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(MainActivityViewModel.class);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.validar(etEmail.getText().toString(),etPass.getText().toString());
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vm.registrarse();
            }
        });
    }

}