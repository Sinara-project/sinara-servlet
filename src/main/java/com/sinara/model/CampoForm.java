package com.sinara.model;

public class CampoForm {
    private final int id;
    private String tipoCampo;
    private String descricao;
    // private RespostaForm[] respostas;
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

    // Setters
    public void setTipoCampo(String tipoCampo) {
        this.tipoCampo = tipoCampo;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}