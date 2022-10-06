package com.example.appluce.ui.dicas;

import com.example.appluce.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dicas implements Serializable {

    public Dicas() {
    }

    public Dicas(String titulo, Integer id, String link) {
        this.titulo = titulo;
        this.id = id;
        this.link = link;
    }

    String titulo;
    Integer id;
    String link;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }


}
