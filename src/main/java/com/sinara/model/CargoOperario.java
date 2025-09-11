package com.sinara.model;

public class CargoOperario {
    private Integer id;
    private String cargo;

    // Construtor
    public CargoOperario(String cargo) {
        this.id = null;
        this.cargo = cargo;
    }

    // Getters
    public int getId() {
        return id;
    }
    public String getCargo() {
        return cargo;
    }

    // Setters
    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
    public void setId(int id) {
        if (this.id == null) {
            this.id = id;
        }
    }
}
