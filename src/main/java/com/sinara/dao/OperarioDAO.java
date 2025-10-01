package com.sinara.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.postgresql.core.SqlCommand;

import com.sinara.connection.ConexaoDB;
import com.sinara.model.*;

public class OperarioDAO {

    public boolean inserirOperario(Operario operario) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;
        try {
            conn = conexao.conectar(); // INSERT INTO operario (cpf, nome, email, cargo, cnpj_empresa) VALUES (?, ?, ?,
                                       // ?, ?)"

            pstmt = conn.prepareStatement(
                    "INSERT INTO operario (cpf, nome, email, cargo, cnpj_empresa) VALUES (?, ?, ?, ?, ?) ");

            pstmt.setString(1, operario.getCpf());
            pstmt.setString(2, operario.getNome());
            pstmt.setString(3, operario.getEmail());
            pstmt.setString(4, operario.getCargo());
            pstmt.setInt(5, operario.getCnpjEmpresa());

            resultado = pstmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conexao.desconectar(conn);
            }
        }
        return resultado; // TODO: AQUI FALTA ACHO QUE COLOCAR A PARA DE PERMISSOES QUE EU NÃO SEI FAZER
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
            pstmt = conn.prepareStatement("UPDATE operario SET email = ? WHERE id = ?");
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

    public boolean alterarCpf(int id, String cpf) {
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        boolean resultado = false;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("UPDATE operario SET cpf = ? WHERE id = ?");
            pstmt.setString(1, cpf);
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
            pstmt = conn.prepareStatement("UPDATE operario SET cargo = ? WHERE id = ?");
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
    // TODO: PERMISSOES ALTERAR

    // TODO: LISTAR PEMIRSSOES
    


    /* FIXME: FALHA AQUI, NÃO CONSEGUI O DER LISTAR PERMISSOES
    

    public List<Operario> ListarFuncionarios() {
        List<Operario> listaOperarios = new ArrayList<>();
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("Select * from operario");
            rs = pstmt.executeQuery();
            while (rs.next()) {
                
                Operario operario = new Operario(
                    rs.getInt("id"),
                    rs.getString("cpf"),
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getInt("cnpj_empresa"),
                    rs.getString("cargo"),
                    rs.getArray("permissoes")
                );

            }
        }
    }
    */
    

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
                    rs.getString("nome"),
                    rs.getString("email"),
                    rs.getString("cargo"),
                    rs.getInt("cnpj_empresa")           
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
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return operario;
    }





    public List<Operario> buscarPorNome(String nome) {
        List<Operario> listaOperarios = new ArrayList<>();
        ConexaoDB conexao = new ConexaoDB();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = conexao.conectar();
            pstmt = conn.prepareStatement("SELECT * FROM operario WHERE nome = ?"); // SELECT * FROM operario WHERE nome ILIKE ? pstmt.setString(1, "%" + nome + "%");
            pstmt.setString(1, nome);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Operario operario = new Operario(
                        rs.getString("cpf"),
                        rs.getString("nome"),
                        rs.getString("email"),
                        rs.getString("cargo"),
                        rs.getInt("cnpj_empresa"));
                operario.setId(rs.getInt("id"));
                listaOperarios.add(operario);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conexao.desconectar(conn);
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

}
