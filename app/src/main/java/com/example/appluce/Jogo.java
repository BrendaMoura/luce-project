package com.example.appluce;

public class Jogo {
    String nome;

    public String getClasse() {
        return classe;
    }

    public void setClasse(String classe) {
        this.classe = classe;
    }

    public Integer getCod() {
        return cod;
    }

    public void setCod(Integer cod) {
        this.cod = cod;
    }

    String classe;
    Integer id, cod;

    public String getNome() {
        return nome;
    }

    public Jogo(String nome, String classe, Integer id, Integer cod) {
        this.nome = nome;
        this.classe = classe;
        this.id = id;
        this.cod = cod;
    }

    public Integer getId() {
        return id;
    }

    public Jogo() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
