package com.sinara.model;

import java.util.Date;
import java.util.List;

public class Empresa {
    private final String cnpj;
    private String nome;
    private List<String> email;
    private Ramos ramo;
    private List<Long> telefone;
    private boolean status;
    private final Date inicioPlano;
    private int plano;
    // Construtor

    public Empresa(String cnpj, String nome, List<String> email, Ramos ramo, List<Long> telefone, boolean status,
                   Date inicioPlano, int plano) {
        this.cnpj = cnpj; this.nome = nome; this.email = email; this.ramo = ramo; this.telefone = telefone;
        this.status = status; this.inicioPlano = inicioPlano; this.plano = plano;
    }

    // Getters
    public String getNome() {
        return this.nome;
    }
    public List<String> getEmail() {
        return this.email;
    }
    public Ramos getRamo() {
        return this.ramo;
    }
    public List<Long> getTelefone() {
        return this.telefone;
    }
    public boolean isStatus() {
        return this.status;
    }
    public Date getInicioPlano() {
        return this.inicioPlano;
    }

    // Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void addEmail(String email) {
        this.email.add(email);
    }
    public boolean removeEmail(int index) {
        if (this.email.get(index)!=null) this.email.remove(index);
        return this.email.get(index)!=null;
    }
    public boolean removeEmail(String email) {
        if (this.email.contains(email)) this.email.remove(email);
        return this.email.contains(email);
    }
    public void setRamo(Ramos ramo) {
        this.ramo = ramo;
    }
    public void addTelefone(long telefone) {
        this.telefone.add(telefone);
    }
    public boolean removeTelefone(int index) {
        if (this.telefone.get(index)!=null) this.telefone.remove(index);
        return this.telefone.get(index)!=null;
    }
    public boolean removeTelefone(long telefone) {
        if (this.telefone.contains(telefone)) this.telefone.remove(telefone);
        return this.telefone.contains(telefone);
    }
    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setPlano(int plano) {
        this.plano = plano;
    }

    // Enum Ramos
    public enum Ramos {
        Alimentacao,
        Farmaceutico,
        Siderurgia,
        Automobilistica,
        Outros
    }
}
