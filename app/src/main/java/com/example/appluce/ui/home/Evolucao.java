package com.example.appluce.ui.home;

public class Evolucao {
    int tentativa, erro;

    public Evolucao(int tentativa, int erro) {
        this.tentativa = tentativa;
        this.erro = erro;
    }
    public Evolucao(){}


    public int getTentativa() {
        return tentativa;
    }

    public void setTentativa(int tentativa) {
        this.tentativa = tentativa;
    }

    public int getErro() {
        return erro;
    }

    public void setErro(int erro) {
        this.erro = erro;
    }
}
