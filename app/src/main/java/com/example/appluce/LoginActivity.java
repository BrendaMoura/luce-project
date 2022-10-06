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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private Usuario usuario;
    private FirebaseAuth autenticacao;
    private String email, password;
    private EditText emailE, senhaE;
    private Button voltar, entrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        acaoBotao();
    }

    private void acaoBotao() {
        voltar = (Button) findViewById(R.id.voltarPrincipal);
        voltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        entrar = (Button) findViewById(R.id.entrar);
        entrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logar();
            }
        });
    }
    public void logar(){
        emailE = findViewById(R.id.emaillogin);
        senhaE = findViewById(R.id.senhalogin);

        email = emailE.getText().toString();
        password = senhaE.getText().toString();

        if(!email.isEmpty() && !password.isEmpty()){
            usuario = new Usuario();
            usuario.setEmail(email);
            usuario.setSenha(password);
            validar();

        }
        else{
            Toast.makeText(LoginActivity.this, "Preencha todos os campos!", Toast.LENGTH_SHORT).show();
        }


    }

    public void validar(){
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(usuario.getEmail(), usuario.getSenha()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    //pega o id do usuario que logou
                    String id = task.getResult().getUser().getUid();
                    usuario.setId(id);
                    Usuario.setId2(id);
                    Intent intent = new Intent(LoginActivity.this, Principal.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    String erro;
                    try{
                        throw task.getException();
                    }catch (FirebaseAuthInvalidUserException e){
                        erro = "Usuário não cadastrado!";
                    }catch (FirebaseAuthInvalidCredentialsException e){
                        erro  = "Email e senha não correspondem a um usuario cadastrado!";
                    }catch (Exception e){
                        erro = "Erro ao efetuar login!";
                    }

                    Toast.makeText(LoginActivity.this, erro, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
