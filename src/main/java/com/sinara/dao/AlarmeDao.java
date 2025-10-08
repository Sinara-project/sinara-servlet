package com.sinara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import com.sinara.connection.ConexaoDB;
import com.sinara.model.*;

public class AlarmeDao {
    public boolean inserirAlarme(Alarme alarme) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement(
                    "INSERT INTO ALARME (id_impresa, gerado_em, descricao, status_alerta) VALUES(? ? ? ?)");
            pstmt.setInt(1, alarme.getIdEmpresa());
            pstmt.setTimestamp(2, Timestamp.valueOf(alarme.getGeradoEm())); // ou talvez nem precise dessa conversão pq
                                                                            // é gerado na hora que o alerta é criado
            pstmt.setString(3, alarme.getDescricao());
            pstmt.setString(4, alarme.getStatus_alerta());
            resultado = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conexao.desconectar(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return resultado;

    }

    public boolean alterarDescricao(int id, String descricao) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("UPDATE alarme SET descricao = ? WHERE id = ? ");
            pstmt.setString(1, descricao);
            pstmt.setInt(2, id);
            resultado = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    public boolean alterarStatusAlerta(int id, String statusAlerta) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("UPDATE alarme SET status_alerta = ? WHERE id = ?");
            pstmt.setString(1, statusAlerta);
            pstmt.setInt(2, id);
            resultado = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conexao.desconectar(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    public Alarme buscarPorid(int id) {
        Alarme alarme = null;
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("SELECT * FROM alarme WHERE ID = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                alarme = new Alarme(
                        rs.getInt("id"),
                        rs.getInt("id_empresa"),
                        rs.getTimestamp("gerado_em").toLocalDateTime(), // <-- conversão aqui, senão da erro
                        rs.getString("descricao"),
                        rs.getString("status_alerta"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conexao.desconectar(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return alarme;

    }

    public List<Alarme> buscarPorFiltro(Map<String, Object> campos) {
        ConexaoDB conMan = new ConexaoDB();
        List<Alarme> listaAlarmes = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = conMan.conectar();

            StringBuilder sql = new StringBuilder("SELECT * FROM alarme WHERE ");
            int i = 0;
            for (String coluna : campos.keySet()) {
                sql.append(coluna).append(" = ?");
                if (++i < campos.size())
                    sql.append(" AND ");
            }

            pstm = conn.prepareStatement(sql.toString());
            int contador = 1;
            for (Object valor : campos.values()) {
                pstm.setObject(contador++, valor);
            }

            rs = pstm.executeQuery();
            while (rs.next()) {
                Alarme alarme = new Alarme(
                        rs.getInt("id"),
                        rs.getInt("id_empresa"),
                        rs.getTimestamp("gerado_em").toLocalDateTime(),
                        rs.getString("descricao"),
                        rs.getString("status_alerta"));
                listaAlarmes.add(alarme);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstm != null)
                    pstm.close();
                if (conn != null)
                    conMan.desconectar(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaAlarmes;
    }


    public boolean deletarAlarme(int id) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("DELETE FROM alarme WHERE id = ?");
            pstmt.setInt(1, id);
            resultado = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conexao.desconectar(conn);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

}
