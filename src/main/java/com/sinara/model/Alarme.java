package com.sinara.model;

import java.sql.Date;
import java.sql.Time;

public class Alarme {
    private int id;
    private Date dia;
    private Time horario;
    private String descricao;
    private boolean status;


    public void setStatus(boolean status) {
        this.status = status;
    }
    public boolean getStatus() {
        return this.status;
    }

    public int getId() {
        return id;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public void setHorario(Time horario) {
        this.horario = horario;
    }

    public Time getHorario() {
        return horario;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }


}
