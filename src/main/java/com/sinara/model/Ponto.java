package com.sinara.model;

import java.time.LocalDate;

public class Ponto {
    private Integer id;
    private final LocalDate horarioEntrada;
    private final LocalDate horarioSaida;

    // Construtor
    public Ponto(int id, LocalDate horarioEntrada, LocalDate horarioSaida) {
        this.id = null;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
    }

    // Getters
    public int getId() {
        return id;
    }
    public LocalDate getHorarioEntrada() {
        return horarioEntrada;
    }
    public LocalDate getHorarioSaida() {
        return horarioSaida;
    }

    // Setters
    public void setId(Integer id) {
        if (this.id == null) {
            this.id = id;
        }
        throw new IllegalStateException("ERRO: O ID já foi definido e não pode mais ser alterado!");
    }
}