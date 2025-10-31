package com.sinara.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.BitSet;
import java.util.List;

public class VisaoGeral {
    private String nome;
    private int quant_administradores;
    private int quant_operarios;
    private int quant_alertas;
    private int quant_relatorios;

    // Construtor + Tratamento de erros
    public VisaoGeral(ResultSet rset, List<String> erros) {
        Empresa.tratarResultado(setarParametros(rset), erros, "");
    }
    public int setarParametros(ResultSet rset) {
        try {
            this.nome = rset.getString("nome");
            this.quant_administradores = rset.getInt("quant_admin");
            this.quant_operarios = rset.getInt("quant_operarios");
            this.quant_alertas = rset.getInt("quant_alertas");
            this.quant_relatorios = rset.getInt("quant_relatorios");
        } catch (SQLException exc) {
            return 2;
        } catch (NullPointerException exc) {
            return -1;
        }
        return 1;
    }

    // Getters
    public String getNome() {
        return nome;
    }
    public int getQuant_administradores() {
        return quant_administradores;
    }
    public int getQuant_alertas() {
        return quant_alertas;
    }
    public int getQuant_operarios() {
        return quant_operarios;
    }
    public int getQuant_relatorios() {
        return quant_relatorios;
    }
}
