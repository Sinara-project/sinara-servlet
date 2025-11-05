package com.sinara.model;

public class Imagem {

    private Integer id;
    private Integer adminId;
    private String url;

    public int getId() {
        return id;
    }

    public int getAdminId() {
        return adminId;
    }

    public String getUrl() {
        return url;
    }

    public void setId(int id) {
        if (this.id == null) {
            this.id = id;
        } else {
            throw new IllegalStateException("ERRO: O ID já foi definido e não pode mais ser alterado!");
        }
    }


    public void setAdminId(int adminId) {
        if (this.adminId == null) {
            this.adminId = adminId;
        } else {
            throw new IllegalStateException("ERRO: O ID já foi definido e não pode mais ser alterado!");
        }
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
