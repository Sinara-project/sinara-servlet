package com.sinara.model;

import java.time.LocalDate;


public class Pagamentos {
    private int id;
    private String cpfAdministrador;
    private int valor;
    private LocalDate dataPagamento;
    private boolean status;

    public int getId() {
        return id;
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
        dinheiro,
        cheque
    }
}
