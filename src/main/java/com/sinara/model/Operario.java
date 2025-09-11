package com.sinara.model;

import java.time.LocalDate;
import java.util.List;

public class Operario {
    private final String cpf;
    private String nome;
    private List<String> emails;
    private List<Ponto> pontos;
    private List<CargoOperario> cargos;
    private final int cnpjEmpresa;

    //Construtor
    public Operario(String cpf, String nome, List<String> emails, int cnpj_empresa) {
        this.cpf = cpf;
        this.nome = nome;
        this.emails = emails;
        this.cnpjEmpresa = cnpj_empresa;
    }

    //Getters
    public String getCpf() {
        return cpf;
    }
    public String getNome() {
        return nome;
    }
    public int getCnpjEmpresa() {
        return cnpjEmpresa;
    }

    //Setters
    public void setNome(String nome) {
        this.nome = nome;
    }
    public void addEmail(String email) {
        this.emails.add(email);
    }
    public boolean removeEmail(int index) {
        if (this.emails.get(index)!=null) this.emails.remove(index);
        return this.emails.get(index)!=null;
    }
        public boolean removeEmail(String email) {
        if (this.emails.contains(email)) this.emails.remove(email);
        return this.emails.contains(email);
    }
    public void addPonto(Ponto ponto) {
        this.pontos.add(ponto);
    }
    public void addPonto(int id, LocalDate horarioEntrada, LocalDate horarioSaida) {
        addPonto(new Ponto(id, horarioEntrada, horarioSaida));
    }
    public void addCargo(CargoOperario cargo) {
        this.cargos.add(cargo);
    }
    public void addCargo(int id, String cargo) {
        addCargo(new CargoOperario(cargo));
    }
    public boolean removeCargo(int index) {
        if (this.cargos.get(index) != null) {
            this.cargos.remove(index);
            return true;
        } else return false;
    }
}