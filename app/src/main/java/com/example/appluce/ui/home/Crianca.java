package com.example.appluce.ui.home;

public class Crianca {
    public Crianca(String nome, int vitoria, int tipoEvolucao) {
        this.nome = nome;
        this.vitoria = vitoria;
        this.tipoEvolucao = tipoEvolucao;
    }



    public Crianca() {
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getVitoria() {
        return vitoria;
    }

    public void setVitoria(int vitoria) {
        this.vitoria = vitoria;
    }

    public int getTipoEvolucao() {
        return tipoEvolucao;
    }

    public void setTipoEvolucao(int tipoEvolucao) {
        this.tipoEvolucao = tipoEvolucao;
    }


    private String nome;


    public static String id2;
    public String id;

    public static String getId2() {
        return id2;
    }

    public static void setId2(String id2) {
        Crianca.id2 = id2;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    private int vitoria, tipoEvolucao;

    //Essa parte é para o cadastro da crianca
    //Para saber em qual ela tem mais dificuldade
    public static int logica;

    public static int getTipo() {
        return tipo;
    }

    public static void setTipo(int tipo) {
        Crianca.tipo = tipo;
    }

    public static int tipo;

    public static int getLogica() {
        return logica;
    }

    public static void setLogica(int logica) {
        Crianca.logica = logica;
    }

}
