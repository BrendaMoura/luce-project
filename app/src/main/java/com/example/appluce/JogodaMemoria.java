package com.example.appluce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.appluce.config.ConfiguracaoFirebase;
import com.example.appluce.ui.home.Crianca;
import com.example.appluce.ui.home.Evolucao;
import com.google.firebase.database.DatabaseReference;

import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class JogodaMemoria extends AppCompatActivity {
    Button voltar;
    public static int primeiro = 0;
    GridView gridView;
    ImageView curlView = null;
    private int coutPair=0;
    final int [] imagem = new int[]{R.drawable.cat,
            R.drawable.duckling,R.drawable.pigeon,R.drawable.dog,R.drawable.rabbit,R.drawable.bear,
            R.drawable.quem};

    int posicaoAtual = -1;
    int [] tem = new int[]{0,1,2,3,4,5,0,1,2,3,4,5};
    int [] parte = new int[12];
    int i = 0, j = 0, tamanho = 12, posicaoInicial = -1, erros = 0, tentativas = 0;
    ImageAdapter imageAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogoda_memoria);

        Sortear();

        voltar = findViewById(R.id.voltarJogo);
        gridView = (GridView)findViewById(R.id.gridMemoria);
        imageAdapter = new ImageAdapter(JogodaMemoria.this);
        gridView.setAdapter(imageAdapter);

        Escolha();
        AcaoBotao();
    }

    public void AcaoBotao(){
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    //Esse método faz o sorteio das posicoes das imagens no gridView

    public void Sortear(){
        for(i=0;i<12;i++){
            int posicao = new Random().nextInt(tamanho);
            parte[i] = tem[posicao];
            for(j=posicao;j<tamanho;j++){
                if(tem[j]==-1)break;
                else{
                    if(j == tamanho-1)tem[j]=-1;
                    else tem[j]=tem[j+1];
                }
            }
            tamanho=tamanho-1;
        }
    }

    public void Escolha(){
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(posicaoAtual<0){
                    posicaoAtual = position;
                    curlView = (ImageView)view;
                    ((ImageView)view).setImageResource(imagem[parte[position]]);
                }
                else {
                    tentativas = tentativas + 1;
                    ((ImageView)view).setImageResource(imagem[parte[position]]);
                    if(posicaoAtual==position){
                        ((ImageView)view).setImageResource(R.drawable.quem);

                    }
                    else if(parte[posicaoAtual] != parte[position]){
                        erros = erros + 1;
                        Toast.makeText(getApplicationContext(), "Não encaixa", Toast.LENGTH_SHORT).show();
                        curlView.setImageResource(R.drawable.quem);
                        ((ImageView)view).setImageResource(R.drawable.quem);
                    }
                    else{
                        ((ImageView)view).setImageResource(imagem[parte[position]]);
                        coutPair++;
                        if(coutPair ==6){
                            Toast.makeText(getApplicationContext(), "Você ganhou!!!!!", Toast.LENGTH_LONG).show();
                            //Verifica se está cadastrando uma nova criança
                            if(primeiro == 1){
                                Comparar();
                            }
                            else{
                                //Salvar();
                            }
                            finish();
                        }

                    }
                    posicaoAtual = -1;
                }
            }
        });
    }

    public void Comparar(){
        int certas = tentativas - erros;
        int calculo = (certas / erros);
        int memoria = 0;

        if(calculo > 1){
            //bom
            memoria = 3;
        }
        else if(calculo > 0.6 && calculo <= 1){
            //medio
            memoria = 2;
        }
        else{
            //ruim
            memoria = 1;
        }


        if(memoria >= Crianca.getLogica()){
            Crianca.setTipo(1);
        }
        else{
            Crianca.setTipo(2);
        }

        CadastrarCrianca.primeiro = 1;
        Intent intent = new Intent(JogodaMemoria.this, CadastrarCrianca.class);
        startActivity(intent);
        finish();
    }

    public void Salvar(){
        Usuario usuario = new Usuario();
        Evolucao evolucao = new Evolucao();
        String id = usuario.getId();
        int posicao = AreaCrianca.posicao;
        String idCrianca = AreaCrianca.criancaInfoA.get(posicao).getId();
        DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
        DatabaseReference usuarios = firebase.child("usuario").child(id).child("pedendente").child(idCrianca).child("Evolucao").push();
        evolucao = new Evolucao(tentativas, erros);
        usuarios.setValue(evolucao);
    }

}
