package com.optic.overtime;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListElementAdapter extends RecyclerView.Adapter<ListElementAdapter.ViewHolder> {

    ArrayList<ListElement> mData;
    private LayoutInflater mInflater;


    public ListElementAdapter(ArrayList<ListElement> itemList) {//, Context context) {
        // this.mInflater = LayoutInflater.from(context);
        //  this.context = context;
        // this.mData = itemList;
        mData = itemList;
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // holder.bindData(mData.get(position));
        ListElement nombre = mData.get(position);
        ListElement id = mData.get(position);

        holder.txvNombre.setText(nombre.getNombre());
        holder.txvNumero.setText(id.getId());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_element, parent, false);
        return new ViewHolder(view);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        Context context;
        private TextView txvNombre, txvNumero;


        ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();

            txvNombre = itemView.findViewById(R.id.txtvNombre);
            txvNumero = itemView.findViewById(R.id.txtvNumEmp);




        }
    }
}
