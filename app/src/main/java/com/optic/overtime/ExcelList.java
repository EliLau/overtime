package com.optic.overtime;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class ExcelList extends AppCompatActivity {

    RecyclerView listaexcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excel_list);

        listaexcel = findViewById(R.id.RVlistaexcel);

        ExcelAdapter adapter = new ExcelAdapter(MostrarDetalles.listaExcels, ExcelList.this);
        listaexcel.setHasFixedSize(true);
        listaexcel.setLayoutManager(new LinearLayoutManager(ExcelList.this));
        listaexcel.setAdapter(adapter);
    }
}