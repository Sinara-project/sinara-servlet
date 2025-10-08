package com.sinara.model;

import java.time.LocalDateTime;

public class Alarme {
    private int id;
    private int idEmpresa;
    private LocalDateTime geradoEm;
    private String descricao;
    private String status_alerta;

    

    public Alarme(int id, int idEmpresa, LocalDateTime geradoEm, String descricao, String status_alerta) {
        this.id = id;
        this.idEmpresa = idEmpresa;
        this.geradoEm = geradoEm;
        this.descricao = descricao;
        this.status_alerta = status_alerta;
    }
    public String getDescricao() {
        return descricao;
    }
    public LocalDateTime getGeradoEm() {
        return geradoEm;
    }
    public int getId() {
        return id;
    }
    public int getIdEmpresa() {
        return idEmpresa;
    }
    public String getStatus_alerta() {
        return status_alerta;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    public void setGeradoEm(LocalDateTime geradoEm) {
        this.geradoEm = geradoEm;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setIdEmpresa(int idEmpresa) {
        this.idEmpresa = idEmpresa;
    }
    public void setStatus_alerta(String status_alerta) {
        this.status_alerta = status_alerta;
    }
}