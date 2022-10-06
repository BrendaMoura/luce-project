package com.example.appluce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.appluce.config.ConfiguracaoFirebase;
import com.example.appluce.ui.home.Crianca;
import com.example.appluce.ui.home.Evolucao;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;

public class CadastrarCrianca extends AppCompatActivity {
    public static int primeiro = 0;

    EditText nomeC;
    Button voltar, cadastrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_crianca);

        EventoBotao();
    }

    private void EventoBotao() {
        voltar = (Button) findViewById(R.id.voltarC);
        cadastrar = (Button) findViewById(R.id.cadastrarC);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Salvar();
            }
        });
    }

    public void Salvar(){
        nomeC = findViewById(R.id.nomeC);
        String nome = nomeC.getText().toString();

        if(!nome.isEmpty())
        {
            Crianca crianca = new Crianca();
            String id = Usuario.getId2();

            crianca = new Crianca(nome,0, Crianca.getTipo());
            DatabaseReference firebase = ConfiguracaoFirebase.getFirebaseDatabase();
            //DatabaseReference dependente = firebase.child("usuario").child(id).child("dependente").push().child("informacaoCrianca");
            DatabaseReference dependente = firebase.child("usuario").child(id).child("dependente").push();
            dependente.setValue(crianca);

            Toast.makeText(CadastrarCrianca.this, "Criança cadastrada com sucesso!", Toast.LENGTH_SHORT).show();
            if(primeiro == 1){
                Intent intent = new Intent(CadastrarCrianca.this, Principal.class);
                startActivity(intent);
            }
            finish();
        }
        else{
            Toast.makeText(CadastrarCrianca.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
        }

    }
}
