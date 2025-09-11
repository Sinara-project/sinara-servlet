package com.sinara.model;
import java.util.List;

public class Administrador {
    private Integer id;
    private String nome;
    private List<String> emails;
    private String senha;
    private final String cpf;
    private List<String> cargos;
    private final String cnpjEmpresa;

    // Construtores
    public Administrador(String nome, List<String> email, String senha, String cpf, List<String> cargos, String cnpjEmpresa) {
        this.id = null;
        this.nome = nome;
        this.emails = email;
        this.senha = senha;
        this.cpf = cpf;
        this.cargos = cargos;
        this.cnpjEmpresa = cnpjEmpresa;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }
    public void setId(Integer id) {
        if (this.id == null) {
            this.id = id;
        }
        throw new IllegalStateException("ERRO: O ID já foi definido e não pode mais ser alterado!");
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<String> getEmails() {
        return emails;
    }

    public void addEmail(String email) {
        this.emails.add(email);
    }

    public boolean removeEmail(int index) {
        if (this.emails.get(index) != null) {
            this.emails.remove(index);
            return true;
        } else return false;
    }

    public boolean removeEmail(String email) {
        if (this.emails.contains(email)) {
            this.emails.remove(email);
            return true;
        } else return false;
    }

    public void addCargo(String cargo) {
        this.cargos.add(cargo);
    }

    public boolean removeCargo(int index) {
        if (this.cargos.get(index) != null) {
            this.cargos.remove(index);
            return true;
        } else return false;
    }

    public String getCnpjEmpresa() {
        return cnpjEmpresa;
    }
}