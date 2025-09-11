package com.sinara.model;

import java.util.List;

public class Formulario {
    private Integer id;
    private String nome;
    private final String cnpjEmpresa;
    private List<CampoForm> campos;

    // Construtores
    public Formulario(String nome, String cnpjEmpresa) {
        this.id = null;
        this.nome = nome;
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
    public String getCnpj() {
        return cnpjEmpresa;
    }
    public List<CampoForm> getCampos() {
        return this.campos;
    }
    public void addCampo(CampoForm campo) {
        this.campos.add(campo);
    }
    public void addCampo(int id, String tipoCampo, String descricao) {
        addCampo(new CampoForm(id, tipoCampo, descricao));
    }
    public boolean removeCampo(int id) {
        for (CampoForm campo : this.campos) {
            if (campo.getId() == id) {
                this.campos.remove(campo);
                return true;
            }
        }
        return false;
    }
}
