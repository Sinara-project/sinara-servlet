package com.sinara.model;

import java.sql.Time;

public class Operario {
    private Integer id;
    private final String cpf;
    private String nome;
    private String email;
    private String cargo;
    private String senha;
    private final int idEmpresa; // id, é bom alterar depois
    private Permissoes permissoes;
    private Time horarioTrabalho;

    //Construtor
    public Operario(String cpf, String nome, String email, String cargo, int idEmpresa, Time horarioTrabalho) {
        this.id = null;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.idEmpresa = idEmpresa;
        this.permissoes = new Permissoes();
        this.horarioTrabalho = horarioTrabalho;
    }
    
    public Operario(String cpf, String nome, String email, String cargo, int idEmpresa, Time horarioTrabalho, String senha) {
        this.id = null;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.idEmpresa = idEmpresa;
        this.permissoes = new Permissoes();
        this.horarioTrabalho = horarioTrabalho;
        this.senha = senha;
    }

    public Operario(String cpf, int idEmpresa, String nome, String email, String cargo) {
        this.id = null;
        this.cpf = cpf;
        this.idEmpresa = idEmpresa;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
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
    public int getIdEmpresa() {
        return idEmpresa;
    }

    //Setters
    public void setId(int id) {
        if (this.id == null) {
            this.id = id;
        } else {
            throw new IllegalStateException("ERRO: O ID já foi definido e não pode mais ser alterado!");
        }
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
    public Permissoes getPermissoes() {
        return permissoes;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public Time getHorarioTrabalho() {
        return horarioTrabalho;
    }
    public void setHorarioTrabalho(Time horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }
    

}