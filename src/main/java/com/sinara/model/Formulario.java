package com.sinara.model;

public class Formulario {
    private final int id;
    private String nome;
    private final String cnpjEmpresa;
  //  private CampoForm[] campos;

    public int getId() {
        return id;
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

    public Formulario(int id, String nome, String cnpjEmpresa) {
        this.id = id;
        this.nome = nome;
        this.cnpjEmpresa = cnpjEmpresa;
    }

}
