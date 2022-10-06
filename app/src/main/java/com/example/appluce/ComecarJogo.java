package com.example.appluce;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ComecarJogo extends AppCompatActivity {

    Button cancelar, comecar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comecar_jogo);

        cancelar = findViewById(R.id.cancelar);
        comecar = findViewById(R.id.comecar);

        AcaoBotao();

    }

    public void AcaoBotao(){
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        comecar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ComecarJogo.this, Indicador.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
