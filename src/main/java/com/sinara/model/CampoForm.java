package com.sinara.model;

import java.util.Date;
import java.util.List;

public class CampoForm {
    private final int id;
    private String tipoCampo;
    private String descricao;
    private List<RespostaForm> respostas;
    // Construtor
    public CampoForm(int id, String tipoCampo, String descricao) {
        this.id = id;
        this.tipoCampo = tipoCampo;
        this.descricao = descricao;
    }

    // Getters
    public int getId() {
        return this.id;
    }
    public String getTipoCampo() {
        return this.tipoCampo;
    }
    public String getDescricao() {
        return this.descricao;
    }
    public List<RespostaForm> getRespostas() {
        return this.respostas;
    }

    // Setters
    public void setTipoCampo(String tipoCampo) {
        this.tipoCampo = tipoCampo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void addResposta(RespostaForm resp) {
        this.respostas.add(resp);
    }
    public void addResposta(int id, int valor, int cpf_operario, Date data_resposta) {
        addResposta(new RespostaForm(id, valor, cpf_operario, data_resposta));
    }
}