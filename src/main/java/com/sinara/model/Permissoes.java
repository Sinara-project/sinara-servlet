package com.sinara.model;

public class Permissoes {
    private Integer id;
    private boolean inserirDados;
    private boolean editarDados;
    private boolean visualizarRegistros;
    private boolean aprovarRegistros;
    private boolean gerenciarUsuarios;

    // Construtores
    public Permissoes() {
        this.id = null;
        this.inserirDados = false;
        this.editarDados = false;
        this.visualizarRegistros = false;
        this.aprovarRegistros = false;
        this.gerenciarUsuarios = false;
    }
    public Permissoes(boolean inserirDados, boolean editarDados, boolean visualizarRegistros,
                      boolean aprovarRegistros, boolean gerenciarUsuarios) {
        this.id = null;
        this.inserirDados = inserirDados;
        this.editarDados = editarDados;
        this.visualizarRegistros = visualizarRegistros;
        this.aprovarRegistros = aprovarRegistros;
        this.gerenciarUsuarios = gerenciarUsuarios;
    }
    public Permissoes(int id, boolean inserirDados, boolean editarDados, boolean visualizarRegistros,
                      boolean aprovarRegistros, boolean gerenciarUsuarios) {
        this.id = id;
        this.inserirDados = inserirDados;
        this.editarDados = editarDados;
        this.visualizarRegistros = visualizarRegistros;
        this.aprovarRegistros = aprovarRegistros;
        this.gerenciarUsuarios = gerenciarUsuarios;
    }
    // Getters
    public Integer getId() {
        return id;
    }
    public boolean temPermissao(int atributo) {
        switch (atributo) {
            // Pode inserir dados?
            case (1) -> {
                return this.inserirDados;
            }
            // Pode editar dados?
            case (2) -> {
                return this.editarDados;
            }
            // Pode visualizar registros?
            case (3) -> {
                return this.visualizarRegistros;
            }
            // Pode aprovar registros?
            case (4) -> {
                return this.aprovarRegistros;
            }
            // Pode gerenciar usuários?
            case (5) -> {
                return this.gerenciarUsuarios;
            }
            default -> {
                return false;
            }
        }
    }

    // Setters
    public void setId(int id) {
        if (this.id == null) {
            this.id = id;
        }
        throw new IllegalStateException("ERRO: O ID já foi definido e não pode mais ser alterado!");
    }
    public void setPermissao(int atributo, boolean mudanca) {
        switch (atributo) {
            case (1) -> {
                this.inserirDados = mudanca;
            }
            case (2) -> {
                this.editarDados = mudanca;
            }
            case (3) -> {
                this.visualizarRegistros = mudanca;
            }
            case (4) -> {
                this.aprovarRegistros = mudanca;
            }
            case (5) -> {
                this.gerenciarUsuarios = mudanca;
            }
        }
    }
}
