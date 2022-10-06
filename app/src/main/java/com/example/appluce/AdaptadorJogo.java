package com.example.appluce;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

public class AdaptadorJogo<J> extends ArrayAdapter {
    ArrayList<Jogo> lista = new ArrayList<Jogo>();


    public AdaptadorJogo(@NonNull Context context, int textViewResourceId, List<Jogo> lista) {
        super(context, textViewResourceId, lista);
        this.lista = (ArrayList<Jogo>) lista;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item_grid, null);
        TextView textView = (TextView) view.findViewById(R.id.titulo);
        ImageView imageView = (ImageView) view.findViewById(R.id.capa);

        textView.setText(lista.get(position).getNome());
        imageView.setImageResource(lista.get(position).getId());

        return view;

    }

}
