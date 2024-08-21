package com.example.ecoreciclaje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity {
    TextInputEditText textEmail, txtContraseña;
    Button btnAceptar, btnCancelar;
    TextView olvidaContraseña;
    UserManager userManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        textEmail = findViewById(R.id.textEmail);
        txtContraseña = findViewById(R.id.txtContraseña);
        btnAceptar = findViewById(R.id.btnAceptar);
        btnCancelar = findViewById(R.id.btnCancelar);
        olvidaContraseña = findViewById(R.id.olvidaContraseña);

        userManager = new UserManager(this);

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                String password = txtContraseña.getText().toString();

                if (userManager.loginUser(email, password)) {
                    Intent intent = new Intent(Login.this, MenuPrincipal.class);
                    startActivity(intent);
                    finish();
                } else {
                    Toast.makeText(Login.this, "Email o Password Invalidos", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, inicio.class);
                startActivity(intent);
            }
        });
        olvidaContraseña.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });
    }
}