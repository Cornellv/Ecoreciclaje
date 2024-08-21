package com.example.ecoreciclaje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Register extends AppCompatActivity {
    TextInputEditText etName, etEmail, etContraseña;
    AppCompatButton btnRegistrar, btnCancelar;
    private UserManager userManager;
    private CheckBox checkBoxRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etEmail);
        etContraseña = findViewById(R.id.etContraseña);
        btnRegistrar = findViewById(R.id.btnRegistrar);
        btnCancelar = findViewById(R.id.btnCancelar);
        checkBoxRegister = findViewById(R.id.checkBoxRegister);

        userManager = new UserManager(this);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = etEmail.getText().toString().trim();
                String password = etContraseña.getText().toString().trim();

                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Ingrese un correo electrónico", Toast.LENGTH_SHORT).show();
                } else if (!isValidEmail(email)) {
                    Toast.makeText(Register.this, "Ingrese un correo electrónico válido", Toast.LENGTH_SHORT).show();
                } else if (TextUtils.isEmpty(password)) {
                    Toast.makeText(Register.this, "Ingrese una contraseña", Toast.LENGTH_SHORT).show();
                } else if (!checkBoxRegister.isChecked()) {
                    Toast.makeText(Register.this, "Debe aceptar los Términos y Condiciones", Toast.LENGTH_SHORT).show();
                } else {
                    registrarUsuario(email, password);
                }
            }
        });
        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Register.this, inicio.class);
                startActivity(intent);
            }
        });
    }
    private boolean isValidEmail(CharSequence target) {
        return (!TextUtils.isEmpty(target) && Patterns.EMAIL_ADDRESS.matcher(target).matches());
    }

    private void registrarUsuario(String email, String password) {
        userManager.RegisterUser(email, password);
        Toast.makeText(Register.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        finish();
    }
}