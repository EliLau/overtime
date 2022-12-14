package com.optic.overtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;

public class MostrarDetalles extends AppCompatActivity {

    private EditText etNumemp, etNombre, etcodebar;
    private DatabaseReference mDatabase;
    private ImageView imvBuscar, imvScan;
    private Button mbtAgregar, btLimpiar,btnlista,btngenerarexcel,btnvaciarlista;
    public static ArrayList<ListaExcel> listaExcels = new ArrayList<>();
    File filePath = new File(Environment.getExternalStorageDirectory() + "/Lista_Empleados.xls");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_detalles);

        etNombre = (EditText) findViewById(R.id.etxtNombre);
        etNumemp = (EditText) findViewById(R.id.etxtNumEmp);
        etcodebar= (EditText) findViewById(R.id.etxCodebar);

        imvBuscar= (ImageView) findViewById(R.id.imbSearch);
        imvScan= (ImageView) findViewById(R.id.imbScan);

        mbtAgregar = (Button)findViewById(R.id.btnAgregar);
        btLimpiar = (Button)findViewById(R.id.btnLimpiar);

        btnlista = findViewById(R.id.BTNverlista);
        btngenerarexcel = findViewById(R.id.BTNexcel);
        btnvaciarlista = findViewById(R.id.BTNvaciarlista);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        botonBuscar();
        botonScan();
        
        botonEnviar();
        limpiarInput();

        mbtAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lista();
            }
        });

        btnlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent (MostrarDetalles.this, ExcelList.class);
                startActivity(intent);
            }
        });

        btngenerarexcel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                generar_excel();
            }
        });
        btnvaciarlista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listaExcels.clear();
            }
        });
    }

    public void generar_excel() {

        Workbook workbook = new HSSFWorkbook();

        Sheet sheet;
        Row row;

        String EXCEL_SHEET_NAME = "Asistencia_Alumnos";

        sheet = workbook.createSheet(EXCEL_SHEET_NAME);
        int row1 = 1;

        int celda = 0;
        int recorrido = 0;
        row = sheet.createRow(0);
        row.createCell(0).setCellValue("ID");
        row.createCell(1).setCellValue("Nombre");
        row.createCell(2).setCellValue("CodeBar");

        for (int i = 0; i < listaExcels.size(); i++) {

            row = sheet.createRow(row1);
            row.createCell(0)
                    .setCellValue(listaExcels.get(recorrido).getId());
            row.createCell(1).setCellValue(listaExcels.get(recorrido).getNombre());
            row.createCell(2).setCellValue(listaExcels.get(recorrido).getCodebar());
            row1++;
            recorrido++;


        }
        try {



        if (!filePath.exists()) {
            filePath.createNewFile();

        }

        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        workbook.write(fileOutputStream);

        if (fileOutputStream != null) {
            fileOutputStream.flush();
            fileOutputStream.close();
        }

            Toast.makeText(this, "Excel Generado Exitosamente", Toast.LENGTH_SHORT).show();
        
        }catch (Exception e){
            Toast.makeText(this, e+"", Toast.LENGTH_SHORT).show();
        }
    }

    private void botonEnviar() {
        mbtAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MostrarDetalles.this,AgregarLista.class);
                intent.putExtra("nombre", etNombre.getText().toString());
                //bundle.putString("nombre",etNombre.getText().toString());
                intent.putExtra("id", etNumemp.getText().toString());
                startActivity(intent);
            }
        });

    }

    private void botonScan() {
        imvScan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                IntentIntegrator intentIntegrator = new IntentIntegrator(MostrarDetalles.this);
                intentIntegrator.setPrompt("Scan Code");
                intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                intentIntegrator.setCameraId(0);
                intentIntegrator.setBarcodeImageEnabled(true);
                //beep
                intentIntegrator.setBeepEnabled(true);
                ///locked orientation
                intentIntegrator.setOrientationLocked(true);
                //capture activity
                intentIntegrator.setCaptureActivity(Capture.class);
                //Scan
                intentIntegrator.initiateScan();
            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        //intent result
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (intentResult != null){
            if (intentResult.getContents() == null){
                //cancelar proceso
                Toast.makeText(this, "Lectura cancelada", Toast.LENGTH_LONG).show();

            }else{
                Toast.makeText(this, intentResult.getContents(), Toast.LENGTH_SHORT).show();
                etcodebar.setText(intentResult.getContents());
                scan();
            }

        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void scan() {
        int codebar = Integer.parseInt(etcodebar.getText().toString());

        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference(Empleado.class.getSimpleName());

        dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String aux = Integer.toString(codebar);
                for (DataSnapshot x : snapshot.getChildren()){
                    if(aux.equalsIgnoreCase(x.child("codebar").getValue().toString())){
                        ocultarTeclado();
                        etNombre.setText(x.child("nombre").getValue().toString());
                        etNumemp.setText(x.child("id").getValue().toString());
                        break;

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(MostrarDetalles.this, "Se produjo un error.", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void botonBuscar(){
        imvBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etNumemp.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(MostrarDetalles.this, "Ingrese n??mero de empleado", Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(etNumemp.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = db.getReference("Empleado");

                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean res = false;
                            String aux = Integer.toString(id);
                            for (DataSnapshot x : snapshot.getChildren()){
                                if(aux.equalsIgnoreCase(x.child("id").getValue().toString())){
                                    res = true;
                                    ocultarTeclado();
                                    etNombre.setText(x.child("nombre").getValue().toString());
                                    etcodebar.setText(x.child("codebar").getValue().toString());
                                    break;

                                }
                            }
                            if (res == false){
                                ocultarTeclado();
                                Toast.makeText(MostrarDetalles.this, "Id("+aux+")No encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }// cierra if/ese
            }
        });

    }// cierra buscar

    private void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    } // Cierra el m??todo ocultarTeclado.


    private void limpiarInput(){
        btLimpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etcodebar.setText("");
                etNumemp.setText("");
                etNombre.setText("");
            }
        });

    }
    public void Lista(){
        listaExcels.add(new ListaExcel(etNombre.getText().toString(),etNumemp.getText().toString(),
                etcodebar.getText().toString()));
        Toast.makeText(this, listaExcels.get(listaExcels.size()-1).getNombre(), Toast.LENGTH_SHORT).show();
    }
}