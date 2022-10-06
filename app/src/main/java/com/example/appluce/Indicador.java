package com.example.appluce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appluce.ui.home.Crianca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Indicador extends AppCompatActivity {
    TextView pergunta, countLabel;
    RadioGroup grupo;
    RadioButton resp1, resp2, resp3;
    Button prox;

    private String respostaCerta;
    private  int contador_respostaCerta = 0, quizCount = 1, quantidade = 0;
    List<Pergunta> quizArray = new ArrayList<>();

    public static String quizData[][] = new String[][]{
        // {"Pergunta", "Resposta", "Escolha 1", "Escolha 2", "Escolha 3"}

        {"Numa jaula onde há coelhos e pombos, podem contar-se 35 cabeças\n" +
                "e 94 patas. Quantos animais há de cada classe?","12 coelhos e 23 pombas","11 coelhos e 32 pombas","22 coelhos e 23 pombas", "12 colhoes e 23 pombos"},
        {"O João é mais rápido que a Sara e a Eva é mais lenta que o João.\n" +
                "Qual das seguintes afirmações é a certa?","Não podemos saber se a Sara é mais rápida que a Eva","A eva é mais lenta que a Sara"," Não podemos saber se a Sara é mais rápida que a Eva", "A Eva é tão rápida como a Sara"},
        {"A planta está para a semente como o ser humano está para…","embrião","ovario","embrião", "coração"},
        {"Temos 3 caixas. Dentro de cada uma das 3 caixas estão duas mais\n" +
                "pequenas, e em cada uma dessas estão quatro ainda mais pequenas.\n" +
                "Quantas caixas há no total?","33","35","33", "32"},
        {"Suponhamos que as seguintes afirmações são corretas:\n" +
                "-Todos os polícias dizem a verdade\n" +
                "– Todos os que dizem a verdade são inteligentes\n" +
                "Podemos deduzir que todos os polícias são inteligentes?","Não podemos assegura-lo","Sim","Não", "Não podemos assegura-lo"},
        {"Hoje fui comprar laranjas. No supermercado deram-me 6, comi uma, o\n" +
                "meu pai comeu 2, e outra caiu ao chão e estragou-se. Quantas\n" +
                "laranjas ainda tenho eu?","2","Nenhuma","3", "2"},
        {"Comprova se a seguinte dedução é correta:\n" +
                "– Alguns brinquedos são peluches\n" +
                "– Alguns peluches são verdes\n" +
                "Logo podemos assegurar que todos os brinquedos são verdes","Não podemos ter certeza ","certo"," falso", "Não podemos ter certeza"},
        {"Uma pessoa que ia a Sevilha deu boleia a um homem e 7\n" +
                "mulheres. Cada mulher tinha 7 sacos, cada saco tinha 7 gatos e\n" +
                "cada gato 7 filhotes. Filhotes, gatos, sacos, mulheres e homens,\n" +
                "quantos vão agora a Sevilha?","2802","2801","2802", "2712"},
        {"Suponhamos que as seguintes afirmações estão certas:\n" +
                "– Alguns criminosos são milionários\n" +
                "-Todos os magnatas são milionários\n" +
                "Portanto podemos deduzir que alguns criminosos são magnatas","certo","Não se pode saber","certo","errado"},
        {"Eva tem 4 anos. A sua irmã mais velha, Ana, é 3 vezes mais velha\n" +
                "que ela. Que idade terá Ana quanto tiver o duplo da idade de\n" +
                "Eva?","16","22","23", "16"},
        {"Uma taça está para os cereais como um envelope está para:","Uma carta","Uma carta","UM cachorro", "Uma zebra"},
        {"DIDIIDID está para 49499494 como DIIDIIDD está para:","49949944","49949944","94944949","949244492"},
        {"Quantos quartos são 6 metades?","12 quartos","12 quartos","14 quartos", "13 quartos"},
        {"Nunca caixa temos 10 sapatos vermelhos, e 10 sapatos pretos. Qual é\n" +
                "o menor número de sapatos que temos de tirar da caixa sem\n" +
                "olhar para nos assegurarmos que temos um par da mesma cor?","3","2","3", "7"},
        {"Numa caixa temos 5 pares de luvas pretas e outras tantas brancas.\n" +
                "Qual é o menor número de luvas que devemos tirar da caixa sem\n" +
                "olhar para nos assegurarmos que há um par de cada cor?","3","10","11", "3"},
        {"SACO está para ASCO como 7683 está para:","6783", "8376", "3678", "6783"}
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_indicador);

        countLabel = findViewById(R.id.countLabel);
        pergunta = findViewById(R.id.Pergunta);
        grupo = findViewById(R.id.GrupoResposta);
        resp1 = findViewById(R.id.resposta1);
        resp2 = findViewById(R.id.resposta2);
        resp3 = findViewById(R.id.resposta3);
        prox = findViewById(R.id.proxima);

        quantidade = quizData.length;
        for(int i=0; i<quizData.length; i++){
            Pergunta questao = new Pergunta();
            questao = new Pergunta(quizData[i][0], quizData[i][1], quizData[i][2], quizData[i][3], quizData[i][4]);
            quizArray.add(questao);
        }
        Preencher();
        AcaoBotao();
    }

    public void AcaoBotao(){
        prox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Verifica qual ta selecionado
                int checkedId = 0;
                if(resp1.isChecked()){
                    checkedId = resp1.getId();
                }
                else if(resp2.isChecked()){
                    checkedId = resp2.getId();
                }
                else{
                    checkedId = resp3.getId();
                }
                quizCount = quizCount + 1;
                //Pega o txt do radioButton selecionado
                String respostaEscolhida = "";
                switch (checkedId){
                    case R.id.resposta1:
                        respostaEscolhida = resp1.getText().toString();
                        break;
                    case R.id.resposta2:
                        respostaEscolhida = resp2.getText().toString();
                        break;
                    case R.id.resposta3:
                        respostaEscolhida = resp3.getText().toString();
                        break;
                }

                //Verifica se acertou
                if(respostaEscolhida.equalsIgnoreCase(respostaCerta)){
                    contador_respostaCerta = contador_respostaCerta + 1;
                    Toast.makeText(Indicador.this, "Você acertou!!!!!!!!!", Toast.LENGTH_SHORT).show();
                }
                else{
                    Toast.makeText(Indicador.this, "Opaaaaaa, vai dar bom na prox...", Toast.LENGTH_SHORT).show();
                }


                if(quizCount == quantidade + 1){
                    JogodaMemoria.primeiro = 1;
                    Toast.makeText(Indicador.this, "Agr você jogará um joguinho, bb"+contador_respostaCerta, Toast.LENGTH_SHORT).show();
                    //acabou as perguntas

                    if(contador_respostaCerta<=3){
                        //ruim
                        Crianca.setLogica(1);
                    }
                    else if(contador_respostaCerta>3 && contador_respostaCerta<=6){
                        //medio
                        Crianca.setLogica(2);
                    }
                    else{
                        //bom
                        Crianca.setLogica(3);
                    }
                    //Chama o jogo da memória
                    Intent intent = new Intent(Indicador.this, JogodaMemoria.class);
                    startActivity(intent);
                }
                else{
                    Preencher();
                }
            }
        });
    }

    public void Preencher(){
        countLabel.setText("Questão " + quizCount);
        Random random = new Random();
        int randomNum  = random.nextInt(quizArray.size());

        pergunta.setText(quizArray.get(randomNum).getPergunta());
        respostaCerta = quizArray.get(randomNum).getResposta();
        resp1.setText(quizArray.get(randomNum).getResp1());
        resp2.setText(quizArray.get(randomNum).getResp2());
        resp3.setText(quizArray.get(randomNum).getResp3());
        quizArray.remove(randomNum);
    }
}
