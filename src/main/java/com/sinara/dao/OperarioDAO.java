package com.sinara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;



import com.sinara.connection.ConexaoDB;
import com.sinara.model.*;

public class OperarioDAO {

    public boolean inserirOperario(Operario operario) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null; // PreparedStatement de inserção de usuário
        PreparedStatement permstmt = null; // PreparedStatement de permissões
        boolean resultado = false;
        ResultSet rs = null;
        Integer idPermissao = 0;
        try {
            conn = conexao.conectar(); // INSERT INTO operario (cpf, nome, email, cargo, cnpj_empresa) VALUES (?, ?, ?,
            // ?, ?)"
            permstmt = conn.prepareStatement("INSERT INTO Permissoes (inserir_dados, editar_dados, visualizar_relatorios, aprovar_registros, gerenciar_usuarios) VALUES (?, ?, ?, ?, ?) RETURNING id");
            permstmt.setBoolean(1, operario.getPermissoes().temPermissao(1));
            permstmt.setBoolean(2, operario.getPermissoes().temPermissao(2));
            permstmt.setBoolean(3, operario.getPermissoes().temPermissao(3));
            permstmt.setBoolean(4, operario.getPermissoes().temPermissao(4));
            permstmt.setBoolean(5, operario.getPermissoes().temPermissao(5));

            rs = permstmt.executeQuery();

            if (rs.next()) {
                idPermissao = rs.getInt("id");
            }


            pstmt = conn.prepareStatement("INSERT INTO operario (cpf, nome, horario_trabalho, email_operario, cargo_operario, id_empresa, id_permissoes, senha) VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            pstmt.setString(1, operario.getCpf());
            pstmt.setString(2, operario.getNome());
            pstmt.setTime(3, operario.getHorarioTrabalho());
            pstmt.setString(4, operario.getEmail());
            pstmt.setString(5, operario.getCargo());
            pstmt.setInt(6, operario.getIdEmpresa());
            pstmt.setInt(7, idPermissao);
            pstmt.setString(8, operario.getSenha());


            resultado = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (permstmt != null) {
                    permstmt.close();
                }
                if (rs != null) {
                    rs.close();
                }
                if (conn !=null) {
                    conexao.desconectar(conn);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return resultado;
    }

    public boolean alterarNome(int id, String novoNome) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("UPDATE operario SET nome = ? WHERE ID = ?");

            pstmt.setString(1, novoNome);
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

    public boolean alterarEmail(int id, String novoEmail) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("UPDATE operario SET email_operario = ? WHERE id = ?");
            pstmt.setString(1, novoEmail);
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


    public boolean alterarCargo(int id, String novoCargo) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("UPDATE operario SET cargo_operario = ? WHERE id = ?");
            pstmt.setString(1, novoCargo);
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



    public Operario buscarPorId(int id) {
        Operario operario = null;
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("SELECT * FROM operario WHERE id = ?");
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                operario = new Operario(
                        rs.getString("cpf"),
                        rs.getInt("id_empresa"),
                        rs.getString("nome"),
                        rs.getString("email_operario"),
                        rs.getString("cargo_operario")
                );
                operario.setId(rs.getInt("id"));
            }

        } catch (Exception e ) {
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
        return operario;
    }

    public List<Operario> buscarPorFiltro(Map<String, Object> campos) {
        ConexaoDB conMan = new ConexaoDB();
        List<Operario> listaOperarios = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            conn = conMan.conectar();

            StringBuilder sql = new StringBuilder("SELECT * FROM operario WHERE ");
            int i = 0;
            for (String coluna : campos.keySet()) {
                sql.append(coluna).append(" = ?");
                if (++i < campos.size()) sql.append(" AND ");
            }

            pstm = conn.prepareStatement(sql.toString());
            int contador = 1;
            for (Object valor : campos.values()) {
                pstm.setObject(contador++, valor);
            }

            rs = pstm.executeQuery();
            while (rs.next()) {
                Operario operario = new Operario(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("email_operario"),
                        rs.getString("cargo_operario"),
                        rs.getInt("id_empresa"),
                        rs.getTime("horario_trabalho")
                );
                operario.setId(rs.getInt("id"));
                listaOperarios.add(operario);
            }

        } catch (SQLException exc) {
            System.out.println("Erro ao buscar por filtro: " + exc.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstm != null) pstm.close();
                if (conn != null) conMan.desconectar(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return listaOperarios;
    }

    public boolean deletarOperario(int id) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("DELETE FROM operario WHERE ID = ?");
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

    public List<Operario> listarOperarios() {
        List<Operario> listaOperarios = new ArrayList<Operario>();
        ConexaoDB conMan = new ConexaoDB();

        String sql = "SELECT * FROM operario";

        try (Connection conn = conMan.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rset = pstmt.executeQuery()) {

            while (rset.next()) {
                Operario operario = new Operario(
                        rset.getString("cpf"),
                        rset.getInt("id_empresa"),
                        rset.getString("nome"),
                        rset.getString("email_operario"),
                        rset.getString("cargo_operario")
                );
                operario.setId(rset.getInt("id"));
                listaOperarios.add(operario);
            }
        } catch (SQLException exc) {
            System.err.println(exc.getMessage());
        }
        return listaOperarios;
    }


}