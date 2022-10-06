package com.example.appluce;

public class Pergunta {
    String pergunta, resposta, resp1, resp2,resp3;

    public Pergunta(){

    }
    public Pergunta(String pergunta, String resposta, String resp1, String resp2, String resp3) {
        this.pergunta = pergunta;
        this.resposta = resposta;
        this.resp1 = resp1;
        this.resp2 = resp2;
        this.resp3 = resp3;
    }

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }

    public String getResp1() {
        return resp1;
    }

    public void setResp1(String resp1) {
        this.resp1 = resp1;
    }

    public String getResp2() {
        return resp2;
    }

    public void setResp2(String resp2) {
        this.resp2 = resp2;
    }

    public String getResp3() {
        return resp3;
    }

    public void setResp3(String resp3) {
        this.resp3 = resp3;
    }
}
