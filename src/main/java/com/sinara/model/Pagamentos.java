package com.sinara.model;

import java.time.LocalDate;


public class Pagamentos {
    private Integer id;
    private String cpfAdministrador;
    private int valor;
    private LocalDate dataPagamento;
    private boolean status;

    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (this.id == null) {
            this.id = id;
        }
        throw new IllegalStateException("ERRO: O ID já foi definido e não pode mais ser alterado!");
    }

    public String getCpfAdministrador() {
        return cpfAdministrador;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public LocalDate getDataPagamento() {
        return dataPagamento;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus() {
        return this.status;
    }

    public enum MetodoPagamento {
        pix,
        cartaoCredito,
        cartaoDebito,
        dinheiro
    }
}
