package com.example.appluce.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.example.appluce.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Adaptador<C> extends ArrayAdapter {
    ArrayList<Crianca> lista = new ArrayList<Crianca>();


    public Adaptador(@NonNull Context context, int textViewResourceId, List<Crianca> lista) {
        super(context, textViewResourceId, lista);
        this.lista = (ArrayList<Crianca>) lista;
    }
    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        view = inflater.inflate(R.layout.item, null);
        TextView textView = (TextView) view.findViewById(R.id.nome);
        ImageView imageView = (ImageView) view.findViewById(R.id.img);

        textView.setText(lista.get(position).getNome());

        int[] imagem = {R.drawable.cat, R.drawable.duckling, R.drawable.pigeon,R.drawable.dog,R.drawable.rabbit,R.drawable.bear,R.drawable.monkey};
        int posicao = new Random().nextInt(imagem.length);
        imageView.setImageResource(imagem[posicao]);
        return view;

    }

}
