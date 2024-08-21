package com.example.ecoreciclaje;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class estadisticasReciclaje extends AppCompatActivity {

    private TextView textViewDate;
    private TableLayout tableLayout;
    private AppCompatButton backInicio, actualizarButton, clearButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas_reciclaje);
        textViewDate = findViewById(R.id.textViewDate);
        tableLayout = findViewById(R.id.myTableLayout);
        backInicio = findViewById(R.id.backInicio);
        actualizarButton = findViewById(R.id.actualizarButton);
        clearButton = findViewById(R.id.clearButton);

        loadData();

        // Obtener la fecha actual en español
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d 'de' MMMM 'de' yyyy", new Locale("es", "CO"));
        String currentDate = sdf.format(new Date());
        textViewDate.setText(currentDate);

        backInicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(estadisticasReciclaje.this, MenuPrincipal.class);
                startActivity(intent);
            }
        });

        actualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableLayout.removeAllViews();
                loadData();
            }
        });

        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearData();  // Llama al método para limpiar los datos
            }
        });
    }

    private void loadData() {
        SharedPreferences recyclingPrefs = getSharedPreferences("RecyclingData", MODE_PRIVATE);
        int recyclingIndex = recyclingPrefs.getInt("index", 0);

        for (int i = 0; i < recyclingIndex; i++) {
            String material = recyclingPrefs.getString("material_" + i, "");
            String kilogramos = recyclingPrefs.getString("kilogramos_" + i, "");
            String valorGanado = recyclingPrefs.getString("valorGanado_" + i, "");
            String fecha = recyclingPrefs.getString("fecha_" + i, "");

            TableRow tableRow = new TableRow(this);

            TextView textViewMaterial = new TextView(this);
            textViewMaterial.setText(material);
            textViewMaterial.setBackgroundResource(R.drawable.green_round);
            tableRow.addView(textViewMaterial);

            TextView textViewKilogramos = new TextView(this);
            textViewKilogramos.setText(kilogramos);
            textViewKilogramos.setBackgroundResource(R.drawable.green_round);
            tableRow.addView(textViewKilogramos);

            TextView textViewValorGanado = new TextView(this);
            textViewValorGanado.setText(valorGanado);
            textViewValorGanado.setBackgroundResource(R.drawable.green_round);
            tableRow.addView(textViewValorGanado);

            TextView textViewFecha = new TextView(this);
            textViewFecha.setText(fecha);
            textViewFecha.setBackgroundResource(R.drawable.green_round);
            tableRow.addView(textViewFecha);

            tableLayout.addView(tableRow);
        }
    }
    private void clearData() {
        SharedPreferences recyclingPrefs = getSharedPreferences("RecyclingData", MODE_PRIVATE);
        SharedPreferences.Editor editor = recyclingPrefs.edit();
        editor.clear();  // Elimina todos los datos guardados
        editor.apply();  // Aplica los cambios

        tableLayout.removeAllViews();  // Limpia las tabla
    }
}