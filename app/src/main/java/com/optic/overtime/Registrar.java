package com.optic.overtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class Registrar extends AppCompatActivity {

    private EditText etNumemp, etNombre, etApellido, etcodebar;
   // private ImageView imvFotoPerfil2;
    private Button btRegistrar;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        etNombre = (EditText) findViewById(R.id.etxNombre);
        etNumemp = (EditText) findViewById(R.id.etxId);
        etApellido= (EditText) findViewById(R.id.etxApellido);
        etcodebar= (EditText) findViewById(R.id.etxCodebar);
        btRegistrar = (Button) findViewById(R.id.btnRegistro);

        mDatabase = FirebaseDatabase.getInstance().getReference("Persona");


        btRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = etNombre.getText().toString();
                String apellido = etApellido.getText().toString();
                String codebar = etcodebar.getText().toString();
                String id = etNumemp.getText().toString();

                if (nombre.isEmpty()){
                    Toast.makeText(Registrar.this, "El nombre es inválido", Toast.LENGTH_SHORT).show();
                }else if (id.isEmpty() || id.equals(id)){
                    Toast.makeText(Registrar.this, "El Id es inválido", Toast.LENGTH_SHORT).show();
                }else{
                    Map<String, Object> personaMap = new HashMap<>();
                    personaMap.put("id", id);
                    personaMap.put("nombre", nombre);
                    personaMap.put("apellido", apellido);
                    personaMap.put("codebar", codebar);

                    mDatabase.push().setValue(personaMap).addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void unused) {
                            Toast.makeText(Registrar.this, "Se ha registrado un nuevo dato.", Toast.LENGTH_SHORT).show();

                        }

                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(Registrar.this, "Hubo un error, intente de nuevo.", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }
}