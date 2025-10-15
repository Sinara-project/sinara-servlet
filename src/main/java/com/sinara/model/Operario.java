package com.sinara.model;

public class Operario {
    private Integer id;
    private final String cpf;
    private String nome;
    private String email;
    private String cargo;
    private String senha;
    private final String cnpjEmpresa; // id
    private Permissoes permissoes;
    private String horarioTrabalho;

    //Construtor
    public Operario(String cpf, String nome, String email, String cargo, String cnpj_empresa, String horarioTrabalho) {
        this.id = null;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.cnpjEmpresa = cnpj_empresa;
        this.permissoes = new Permissoes();
        this.horarioTrabalho = horarioTrabalho;
    }
    
    public Operario(String cpf, String nome, String email, String cargo, String cnpj_empresa, String horarioTrabalho, String senha) {
        this.id = null;
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.cargo = cargo;
        this.cnpjEmpresa = cnpj_empresa;
        this.permissoes = new Permissoes();
        this.horarioTrabalho = horarioTrabalho;
        this.senha = senha;
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
    public String getCnpjEmpresa() {
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
    public Permissoes getPermissoes() {
        return permissoes;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getHorarioTrabalho() {
        return horarioTrabalho;
    }
    public void setHorarioTrabalho(String horarioTrabalho) {
        this.horarioTrabalho = horarioTrabalho;
    }
    

}