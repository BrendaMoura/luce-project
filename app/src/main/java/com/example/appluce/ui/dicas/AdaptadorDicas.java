package com.example.appluce.ui.dicas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appluce.R;

import java.util.ArrayList;
import java.util.List;

class AdaptadorDicas<D> extends ArrayAdapter {
    ArrayList<Dicas> dicas = new ArrayList<Dicas>();

    public AdaptadorDicas(Context context, int textViewResourseId, List<Dicas> objects){
        super(context, textViewResourseId, objects);
        dicas = (ArrayList<Dicas>) objects;
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

        System.out.println("Posicao: " + position + "titulo: " + dicas.get(position).getTitulo());
        textView.setText(dicas.get(position).getTitulo());
        //String que armazena o local da imagem
        //String imagem = filmesList.get(position).getId();
        //bota uma imagem no ImageView
        imageView.setImageResource(dicas.get(position).getId());
        return view;

    }

}
