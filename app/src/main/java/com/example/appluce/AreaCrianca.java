package com.example.appluce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.example.appluce.ui.home.Crianca;

import java.util.ArrayList;
import java.util.List;

public class AreaCrianca extends AppCompatActivity {

    public static int posicao;
    public static List<Crianca> criancaInfoA = new ArrayList<Crianca>();
    List<Jogo> listaJogo = new ArrayList<Jogo>();
    Jogo jogo = new Jogo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area_crianca);

        if(criancaInfoA.get(posicao).getTipoEvolucao()==1){
            System.out.println("Tipo: "+criancaInfoA.get(posicao).getTipoEvolucao());
            jogo = new Jogo("Jogo da memória","com.example.appluce.JogodaMemoria", R.drawable.memory,0);
        }
        else{
            System.out.println("Tipo: "+criancaInfoA.get(posicao).getTipoEvolucao());
            jogo = new Jogo("Jogo de lógica","com.example.appluce.JogodeLogica", R.drawable.logic, 0);
        }

        listaJogo.add(jogo);

        GridView gridView = findViewById(R.id.gridDica);
        AdaptadorJogo<Jogo> adapter = new AdaptadorJogo<>(AreaCrianca.this, R.layout.item_grid, listaJogo);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == jogo.getCod()){
                    String classe = jogo.getClasse();
                    try {
                        Class<?> c = Class.forName(classe);
                        Intent intent = new Intent(AreaCrianca.this, c);
                        startActivity(intent);
                        finish();
                    } catch (ClassNotFoundException ignored) {
                        Toast.makeText(AreaCrianca.this, "Desculpe, houve um erro de carregamento!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
