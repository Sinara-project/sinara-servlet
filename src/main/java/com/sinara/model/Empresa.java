package com.sinara.model;
import java.util.Date;
import java.util.List;

public class Empresa {
    private Integer id;
    private final String cnpj;
    private String nome;
    private String email;
    private String ramo;
    private String telefone;
    private boolean status;
    private final Date inicioPlano;
    private char plano;

    // Construtor
    public Empresa(String cnpj, String nome, String email, String ramo, String telefone, boolean status,
                   Date inicioPlano, char plano) {
        this.id = null; this.cnpj = cnpj; this.nome = nome; this.email = email; this.ramo = ramo; this.telefone = telefone;
        this.status = status; this.inicioPlano = inicioPlano; this.plano = plano;
    }

    // Getters
    public Integer getId() {
        return this.id;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public String getNome() {
        return this.nome;
    }
    public String getEmail() {
        return this.email;
    }
    public String getRamo() {
        return this.ramo;
    }
    public String getTelefone() {
        return this.telefone;
    }
    public boolean isStatus() {
        return this.status;
    }
    public Date getInicioPlano() {
        return this.inicioPlano;
    }
    public int getPlano() {
        return plano;
    }

    // Setters
    public void setId(int id) {
        if (this.id == null) {
            this.id = id;
        }
        throw new IllegalStateException("ERRO: O ID já foi definido e não pode mais ser alterado!");
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    public void setRamo(String ramo) {
        this.ramo = ramo;
    }
    public void setStatus(boolean status) {
        this.status = status;
    }
    public void setPlano(char plano) {
        this.plano = plano;
    }
}