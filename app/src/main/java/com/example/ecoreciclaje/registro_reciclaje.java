package com.example.ecoreciclaje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class registro_reciclaje extends AppCompatActivity {

    private Button btnDatePicker;
    private Calendar calendar;
    Spinner spinner;
    private Button registrar;
    EditText kilogramos, valorGanado;
    AppCompatButton backInicio;
    private String selectedMaterial = "None";
    private String selectedDate = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_reciclaje);

        btnDatePicker = findViewById(R.id.btnDatePicker);
        spinner = findViewById(R.id.spinner);
        registrar = findViewById(R.id.registrar);
        kilogramos = findViewById(R.id.kilogramos);
        backInicio = findViewById(R.id.backInicio);
        valorGanado = findViewById(R.id.valorGanado);

        llenarSpinner();

        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveRecyclingData();
            }
        });

        backInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(registro_reciclaje.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

        calendar = Calendar.getInstance();

        btnDatePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDatePicker();
            }
        });
    }

    private void llenarSpinner() {
        ArrayList<ReclajeUsu> reciclajeusu = new ArrayList<ReclajeUsu>();
        reciclajeusu.add(new ReclajeUsu(0, "None"));
        reciclajeusu.add(new ReclajeUsu(1, "Plastico"));
        reciclajeusu.add(new ReclajeUsu(2, "Carton"));
        reciclajeusu.add(new ReclajeUsu(3, "Aluminio"));

        ArrayAdapter<ReclajeUsu> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, reciclajeusu);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                ReclajeUsu selectedItem = (ReclajeUsu) parent.getItemAtPosition(position);
                selectedMaterial = selectedItem.getReciclaje();
                if (!selectedMaterial.equals("None")) {
                    Toast.makeText(registro_reciclaje.this, "Seleccionado: " + selectedMaterial, Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Nada
            }
        });
    }

    private void showDatePicker() {
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(registro_reciclaje.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                btnDatePicker.setText(selectedDate);
            }
        }, year, month, day);
        datePickerDialog.show();
    }

    private void saveRecyclingData() {
        String kg = kilogramos.getText().toString();
        String valor = valorGanado.getText().toString();

        if (selectedMaterial.equals("None") || kg.isEmpty() || valor.isEmpty() || selectedDate.isEmpty()) {
            Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        SharedPreferences preferences = getSharedPreferences("RecyclingData", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        int index = preferences.getInt("index", 0);
        editor.putString("material_" + index, selectedMaterial);
        editor.putString("kilogramos_" + index, kg);
        editor.putString("valorGanado_" + index, valor);
        editor.putString("fecha_" + index, selectedDate);
        editor.putInt("index", index + 1);
        editor.apply();

        Toast.makeText(this, "Datos guardados", Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(registro_reciclaje.this, estadisticasReciclaje.class);
        startActivity(intent);

        clearFields();
        //Para que el usuario no vuelva atras
        finish();
    }

    private void clearFields() {
        spinner.setSelection(0);
        kilogramos.setText("");
        valorGanado.setText("");
        btnDatePicker.setText("Seleccionar Fecha");
        selectedDate = "";
    }
}