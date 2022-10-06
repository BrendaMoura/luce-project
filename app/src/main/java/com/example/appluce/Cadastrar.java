package com.example.appluce;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appluce.config.ConfiguracaoFirebase;
import com.example.appluce.helper.Base64Alterar;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Cadastrar extends AppCompatActivity {
    EditText nome, email,senha, confirmacao;
    Button voltar, cadastrar;
    private FirebaseAuth autenticacao;

    //O DatabaseReference aponta para o banco de dados
    //O FirebaseDatabase aponta para o firebase
    //O FirebaseDatabase.getInstance() cria uma instancia do banco de dados
    //O FirebaseDatabase.getInstance().getReference() aponta para a raiz do JSON
    //private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference("usuario");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar);

        EventoBotao();
    }

    private void EventoBotao() {
        voltar = (Button) findViewById(R.id.voltar);
        cadastrar = (Button) findViewById(R.id.cadastrar);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cadastrar();
            }
        });
    }


    public void Cadastrar() {
        nome = (EditText) findViewById(R.id.nome);
        email = (EditText) findViewById(R.id.email);
        senha = (EditText) findViewById(R.id.senha);
        confirmacao =  findViewById(R.id.confirmar);
        String nomeT = nome.getText().toString(), emailT = email.getText().toString(), senhaT = senha.getText().toString(), confirma = confirmacao.getText().toString();

//          O .push() cria um UID unico
//          O referencia.child("usuario") aponta para o nó usuario do JSON
//          referencia.child("usuario").push().setValue(usuario);
        if(!nomeT.isEmpty() && !emailT.isEmpty() && !senhaT.isEmpty() && !confirma.isEmpty())
        {
            if(senhaT.equalsIgnoreCase(confirma)){
                final Usuario usuario = new Usuario();
                usuario.setNome(nomeT);
                usuario.setEmail(emailT);
                usuario.setSenha(senhaT);

                autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
                autenticacao.createUserWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(Cadastrar.this, new OnCompleteListener<AuthResult>() {
                    @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            //pega o uid do usuario cadastrado
                            String id = task.getResult().getUser().getUid();
                            usuario.setId(id);
                            Usuario.setId2(id);
                            usuario.salvar();

                            Toast.makeText(Cadastrar.this, "Você foi cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Cadastrar.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        }
                        else{
                            String erro;
                            try{
                                throw task.getException();
                            }catch (FirebaseAuthInvalidUserException e){
                                erro = "Por favor , digite uma senha mais forte!";
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                erro = "Por favor, digite um email válido!";
                            }catch (FirebaseAuthUserCollisionException e){
                                erro = "Essa conta já foi cadastrada!";
                            }catch (Exception e){
                                erro = "Erro ao cadastrar usuário " + e.getMessage();
                                //printa o  erro no banco de dados
                                e.printStackTrace();
                            }
                            Toast.makeText(Cadastrar.this, erro, Toast.LENGTH_SHORT).show();

                        }
                    }

                });
            }
            else {
                Toast.makeText(Cadastrar.this, "Digite a mesma senha na confirmação de senha", Toast.LENGTH_SHORT).show();
            }

        }
        else{
            Toast.makeText(Cadastrar.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }

    }

}
