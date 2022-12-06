package com.optic.overtime;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CRUD extends AppCompatActivity {

    private EditText txtid, txtnom, txtcodebar;
    private Button btnbus, btnmod, btnreg, btneli;
    private ListView lvDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_crud);
        txtid   = (EditText) findViewById(R.id.txtid);
        txtnom  = (EditText) findViewById(R.id.etxtNom);
        //txtape  = (EditText) findViewById(R.id.etxtApe);
        txtcodebar  = (EditText) findViewById(R.id.etxtCodebar);

        btnbus  = (Button)   findViewById(R.id.btnbus);
        btnmod  = (Button)   findViewById(R.id.btnMod);
        btnreg  = (Button)   findViewById(R.id.btnReg);
        btneli  = (Button)   findViewById(R.id.btnEli);

        lvDatos = (ListView) findViewById(R.id.lvDatos);

        botonBuscar();
        botonModificar();
        botonRegistrar();
        botonEliminar();
        listarEmpleados();
    }//Cierra onCreate

    private void botonBuscar(){
        btnbus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtid.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(CRUD.this, "Ingrese número de empleado", Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(txtid.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = db.getReference(Empleado.class.getSimpleName());

                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            boolean res = false;
                            String aux = Integer.toString(id);
                            for (DataSnapshot x : snapshot.getChildren()){
                                if(aux.equalsIgnoreCase(x.child("id").getValue().toString())){
                                    res = true;
                                    ocultarTeclado();
                                    txtnom.setText(x.child("nombre").getValue().toString());
                                    txtcodebar.setText(x.child("codebar").getValue().toString());
                                    break;

                                }
                            }
                            if (res == false){
                                ocultarTeclado();
                                Toast.makeText(CRUD.this, "Id("+aux+")No encontrado", Toast.LENGTH_SHORT).show();
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

    private void botonModificar(){
        btnmod.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtid.getText().toString().trim().isEmpty()
                        || txtnom.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(CRUD.this, "Complete los campos faltantes", Toast.LENGTH_SHORT).show();
                }else {
                    int id = Integer.parseInt(txtid.getText().toString());
                    int codebar = Integer.parseInt(txtcodebar.getText().toString());
                    String nombre = txtnom.getText().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = db.getReference(Empleado.class.getSimpleName());

                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            boolean res2 = false;
                            for (DataSnapshot x: snapshot.getChildren()){
                                if (x.child("nombre").getValue().toString().equals(nombre)){
                                    res2 = true;
                                    ocultarTeclado();
                                    Toast.makeText(CRUD.this, "El nombre("+nombre+") ya existe.", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            if (res2 == false){
                                String aux = Integer.toString(id);
                                boolean res = false;
                                for (DataSnapshot x: snapshot.getChildren()){
                                    if (x.child("id").getValue().toString().equals(aux)){
                                        res = true;
                                        ocultarTeclado();
                                        x.getRef().child("nombre").setValue(nombre);
                                        txtid.setText("");
                                        txtnom.setText("");
                                        Toast.makeText(CRUD.this, "El nombre se ha actualizado.", Toast.LENGTH_SHORT).show();
                                        listarEmpleados();
                                        break;
                                    }
                                }
                                if (res == false){
                                    ocultarTeclado();
                                    Toast.makeText(CRUD.this, "El id("+aux+")no se ha encontrado.", Toast.LENGTH_SHORT).show();
                                    txtid.setText("");
                                    txtnom.setText("");;
                                }
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(CRUD.this, "Error al insertar", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

    }//Cierra modificar

    private void botonRegistrar(){
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtid.getText().toString().trim().isEmpty()
                        || txtnom.getText().toString().trim().isEmpty()
                        || txtcodebar.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(CRUD.this, "Complete los campos faltantes", Toast.LENGTH_SHORT).show();
                }else {
                    int id = Integer.parseInt(txtid.getText().toString());
                    int codebar = Integer.parseInt(txtcodebar.getText().toString());
                    String nombre = txtnom.getText().toString();

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = db.getReference(Empleado.class.getSimpleName());

                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                            String aux = Integer.toString(id);
                            boolean res = false;
                            for (DataSnapshot x: snapshot.getChildren()){
                                if (x.child("id").getValue().toString().equals(aux)){
                                    res = true;
                                    Toast.makeText(CRUD.this, "El id ("+aux+") ya existe", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            boolean res2 = false;
                            for (DataSnapshot x: snapshot.getChildren()){
                                if (x.child("nombre").getValue().toString().equals(nombre)){
                                    res2 = true;
                                    Toast.makeText(CRUD.this, "El nombre("+nombre+") ya existe", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            String aux2 = Integer.toString(codebar);
                            boolean res3 = false;
                            for (DataSnapshot x: snapshot.getChildren()){
                                if (x.child("codebar").getValue().toString().equals(aux2)){
                                    res3 = true;
                                    Toast.makeText(CRUD.this, "El codebar ("+aux2+") ya existe", Toast.LENGTH_SHORT).show();
                                    break;
                                }
                            }
                            if (res == false && res2 == false && res3 == false){
                                Empleado empleado = new Empleado(id,nombre,codebar);
                                dbRef.push().setValue(empleado);
                                ocultarTeclado();
                                Toast.makeText(CRUD.this, "Se registró con éxito", Toast.LENGTH_SHORT).show();
                                txtid.setText("");
                                txtnom.setText("");
                                txtcodebar.setText("");
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            Toast.makeText(CRUD.this, "Error al insertar", Toast.LENGTH_SHORT).show();
                        }
                    });
                }//cierra if/else
            }
        });

    }

    private void botonEliminar(){

        btneli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtid.getText().toString().trim().isEmpty()){
                    ocultarTeclado();
                    Toast.makeText(CRUD.this, "Ingrese número de empleado", Toast.LENGTH_SHORT).show();
                }else{
                    int id = Integer.parseInt(txtid.getText().toString());

                    FirebaseDatabase db = FirebaseDatabase.getInstance();
                    DatabaseReference dbRef = db.getReference(Empleado.class.getSimpleName());

                    dbRef.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            final boolean[] res = {false};
                            String aux = Integer.toString(id);
                            for (DataSnapshot x : snapshot.getChildren()){
                                if(aux.equalsIgnoreCase(x.child("id").getValue().toString())){

                                    AlertDialog.Builder a = new AlertDialog.Builder(CRUD.this);
                                    a.setCancelable(false);
                                    a.setTitle("Pregunta");
                                    a.setMessage("¿Desea eliminar registro?");
                                    a.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {

                                        }
                                    });
                                    a.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            res[0] = true;
                                            ocultarTeclado();
                                            x.getRef().removeValue();
                                            listarEmpleados();
                                        }
                                    });
                                    a.show();
                                    break;
                                }
                            }
                            if (res[0] == false){
                                ocultarTeclado();
                                Toast.makeText(CRUD.this, "El Id("+aux+")no se ha encontrado", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });

                }// cierra if/ese
            }
        });

    }

    private void ocultarTeclado(){
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    } // Cierra el método ocultarTeclado.

    //Listar empleados
    private  void listarEmpleados(){
        FirebaseDatabase db = FirebaseDatabase.getInstance();
        DatabaseReference dbRef = db.getReference(Empleado.class.getSimpleName());

        ArrayList <Empleado> listEmp = new ArrayList<>();
        ArrayAdapter<Empleado> adapter = new ArrayAdapter<>(CRUD.this, android.R.layout.simple_list_item_1, listEmp);
        lvDatos.setAdapter(adapter);

        dbRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Empleado emp = snapshot.getValue(Empleado.class);
                listEmp.add(emp);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        lvDatos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Empleado emp = listEmp.get(i);
                AlertDialog.Builder a = new AlertDialog.Builder(CRUD.this);
                a.setCancelable(true);
                a.setTitle("Datos");
                String msg = "Número de empleado: " + emp.getId()+"\n\n";
                msg += "Nombre: " + emp.getNombre()+"\n\n";
                msg += "CodeBar: " + emp.getCodebar()+"\n\n";

                a.setMessage(msg);
                a.show();
            }
        });
    }//Cierra listar luchadores

}//Cierra clase