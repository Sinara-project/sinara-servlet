package com.sinara.model;

import java.time.LocalDate;
import java.util.List;

public class Operario {
    private Integer id;
    private final String cpf;
    private String nome;
    private String email;
    private String cargo;
    private final int cnpjEmpresa;
    private Permissoes permissoes;

    //Construtor
    public Operario(String cpf, String nome, String email, String cargo, int cnpj_empresa) {
        this.id = null;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.cnpjEmpresa = cnpj_empresa;
        this.permissoes = new Permissoes();
    }

    //Getters
    public Integer getId() {
        return id;
    }
    public String getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }
    public String getEmail() {
        return email;
    }
    public String getCargo() {
        return cargo;
    }
    public int getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    //Setters
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
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}