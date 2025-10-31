package com.sinara.model;
import java.util.List;

public class Administrador {
    private Integer id;
    private String nome;
    private String email;
    private String senha;
    private final String cpf;
    private String cargo;
    private final int idEmpresa;
    private Permissoes permissoes;

    // Construtores
    public Administrador(String nome, String email, String senha, String cpf, String cargo, int idEmpresa) {
        this.id = null;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.cargo = cargo;
        this.idEmpresa = idEmpresa;
        this.permissoes = new Permissoes(false, false,
                true, true, true);
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(Integer id) {
        if (this.id == null) {
            this.id = id;
        }
        else throw new IllegalStateException("ERRO: O ID já foi definido e não pode mais ser alterado!");
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCargo() {
        return cargo;
    }
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public int getIdEmpresa() {
        return idEmpresa;
    }
    public String getCpf() {
        return cpf;
    }
    public Permissoes getPermissoes() {
        return permissoes;
    }
    public String getSenha() {
        return senha;
    }
    
}