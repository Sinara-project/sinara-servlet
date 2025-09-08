package com.sinara.model;


import java.util.List;

public class Administrador {
    private final int id;
    private String nome;
    private List<String> email;
    private String senha;
    private String cpf;
    private List<String> cargo;
    private final String cnpjEmpresa;

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getEmail() {
        return email;
    }

    public void addEmail(String email) {
        this.email.add(email);
    }
    public boolean removeEmail(int index) {
        if (this.email.get(index) != null) {
            this.email.remove(index);
            return true;
        } else return false;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public void addCargo(String cargo) {
        this.email.add(cargo);
    }
    public boolean removeCargo(int index) {
        if (this.cargo.get(index) != null) {
            this.cargo.remove(index);
            return true;
        } else return false;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }


    public Administrador(int id, String nome, List<String> email, String senha, String cpf, List<String> cargo, String cnpjEmpresa) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.cpf = cpf;
        this.cargo = cargo;
        this.cnpjEmpresa = cnpjEmpresa;
    }
}
