package com.optic.overtime;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class ActivityList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private ListElementAdapter listElementAdapter;
    ArrayList<ListElement> mList = new ArrayList<>();

    private Button mbtMostrar;

    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);


        mRecyclerView = (RecyclerView) findViewById(R.id.lrvListaDatos);
        //mDatabase = FirebaseDatabase.getInstance().getReference("Persona");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);

        listElementAdapter = new ListElementAdapter(mList);
        //mRecyclerView.setAdapter(listElementAdapter);


       /* mDatabase.orderByKey().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mList.clear();
                for (DataSnapshot dataSnapshot: snapshot.getChildren()){
                    ListElement listar_datos = dataSnapshot.getValue(ListElement.class);
                    mList.add(listar_datos);
                    //Log.e("Datos: ", "" +dataSnapshot.getValue());
                }

                listElementAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
       init();


    }

    private void MostrarDatos() {
        
    }

    public void init(){
        ArrayList<ListElement> mList = new ArrayList<>();
        mList.add(new ListElement("segfdsgfs","53452"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("segfdsgfs","53452"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("segfdsgfs","53452"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("segfdsgfs","53452"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
        mList.add(new ListElement("1321","wedfwe"));
       // mList.add(new ListElement(1321,"wedfwe",13113));
       // mList.add(new ListElement(1321,"wedfwe",13113));
       // mList.add(new ListElement(1321,"wedfwe",13113));
       // mList.add(new ListElement(1321,"wedfwe",13113));


        ListElementAdapter listElementAdapter = new ListElementAdapter(mList);
        RecyclerView recyclerView = findViewById(R.id.lrvListaDatos);
       // recyclerView.setHasFixedSize(true);
       // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(listElementAdapter);
    }
}