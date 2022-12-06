package com.optic.overtime;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class AgregarLista extends AppCompatActivity  {

    private Button btRegresar;
    private TextView txNombre, txId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_lista);

        btRegresar = (Button) findViewById(R.id.btnRegresar);
        txNombre = (TextView) findViewById(R.id.txvNombre);
        txId = (TextView) findViewById(R.id.txvId) ;

        Bundle bundle = this.getIntent().getExtras();
        if (bundle != null){
            String nombre = bundle.getString("nombre");
            String id = bundle.getString("id");
            Toast.makeText(this, "El dato obtenido es: "+nombre+ "El id es: " +id, Toast.LENGTH_SHORT).show();
            txNombre.setText("Nombre: "+ nombre);
            txId.setText("Número de empleado: " + id);

        }

        RegresarActivity();
    }

    private void RegresarActivity() {
        btRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}