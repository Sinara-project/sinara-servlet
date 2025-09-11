package com.sinara.model;

import java.util.Date;

public class RespostaForm {
    private Integer id;
    private final int valor;
    private final int cpf_operario;
    private final Date data_resposta;

    //Construtor
    public RespostaForm(int id, int valor, int cpf_operario, Date data_resposta) {
        this.id = id;
        this.cpf_operario = cpf_operario;
        this.valor = valor;
        this.data_resposta = data_resposta;
    }

    //Getters
    public int getId() {
        return id;
    }
    public int getValor() {
        return valor;
    }
    public int getCpf_operario() {
        return cpf_operario;
    }
    public Date getData_resposta() {
        return data_resposta;
    }

    // Setters
    public void setId(Integer id) {
        if (this.id == null) {
            this.id = id;
        }
        throw new IllegalStateException("ERRO: O ID já foi definido e não pode mais ser alterado!");
    }
}
