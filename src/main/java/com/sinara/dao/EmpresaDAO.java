package com.sinara.dao;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sinara.connection.ConexaoDB;
import com.sinara.model.*;

public class EmpresaDAO {

    public boolean inserirEmpresa(Empresa empresa) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;

        try (Connection conn = conMan.conectar();) {
            String sql = "INSERT INTO Empresa (cnpj, nome, email_corporativo, telefone, ramo_atuacao, status_atividade, " +
                    "inicio_plano, tipo_assinatura) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, empresa.getCnpj());
                pstm.setString(2, empresa.getNome());
                pstm.setString(3, empresa.getEmail());
                pstm.setString(4, empresa.getTelefone());
                pstm.setString(5, empresa.getRamo());
                pstm.setBoolean(6, empresa.isStatus());
                pstm.setDate(7, empresa.getInicioPlano()); /* AINDA NÃO IMPLEMENTADO*/
                /* Como transformar util.Date em sql.Date */
                pstm.setString(8, String.valueOf(empresa.getPlano()));

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }
    // Alterações

    public boolean alterarNome(int id, String nome) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Empresa SET nome = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(2, id);
                pstm.setString(1, nome);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }

    public boolean alterarEmail(int id, String email) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Empresa SET email_corporativo = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(2, id);
                pstm.setString(1, email);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }

    public boolean alterarTelefone(int id, String telefone) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Empresa SET telefone = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(2, id);
                pstm.setString(1, telefone);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }

    public boolean alterarRamo(int id, String ramo) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Empresa SET ramo_atuacao = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(2, id);
                pstm.setString(1, ramo);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }

    public boolean alterarAtividade(int id, boolean atividade) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Empresa SET status_atividade = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(2, id);
                pstm.setBoolean(1, atividade);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }

    public boolean alterarInicioPlano(int id, Date inicioPlano) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Empresa SET incio_plano = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(2, id);
                pstm.setDate(1, inicioPlano);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }

    public boolean alterarPlano(int id, String tipoPlano) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Empresa SET tipo_assinatura = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(2, id);
                pstm.setString(1, tipoPlano);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }

    public List<Empresa> listarEmpresas() {
        ConexaoDB conMan = new ConexaoDB();
        List<Empresa> listaAdministradores = new LinkedList<Empresa>();

        try (Connection conn = conMan.conectar();) {
            String sql = "SELECT * FROM Empresa";

            try (Statement stm = conn.createStatement()) {
                ResultSet rset = stm.executeQuery(sql);
                while (rset.next()) {
                    listaAdministradores.add(new Empresa(rset.getString("cnpj"),
                            rset.getString("nome"), rset.getString("email_corporativo"),
                            rset.getString("ramo_atuacao"), rset.getString("telefone"),
                            rset.getBoolean("status_atividade"), rset.getDate("inicio_plano"),
                            rset.getString("tipo_assinatura")));
                    listaAdministradores.get(listaAdministradores.size()-1).setId(rset.getInt("id"));
                }
            }
            conMan.desconectar(conn);
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return listaAdministradores;
    }

    public List<Empresa> buscarPorFiltro(Map<String, Object> campos) {
        ConexaoDB conMan = new ConexaoDB();
        List<Empresa> listaAdministradores = new LinkedList<Empresa>();
        int contador = 0;

        try (Connection conn = conMan.conectar();) {
            String sql = "SELECT * FROM Empresa WHERE ";

            // script sql recebe mais um set para busca a cada filtro fornecido
            for (String s : campos.keySet()) {
                sql+=s+" = ?";
                contador++;
                if (contador < campos.size()) sql+=" AND ";
            }

            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                contador = 1;
                // pstm seta os campos de acordo com a quantidade e valores no Map
                for (Object s : campos.values()) {
                    pstm.setObject(contador++, s);
                }
                ResultSet rset = pstm.executeQuery();
                while (rset.next()) {
                    listaAdministradores.add(new Empresa(rset.getString("cnpj"),
                            rset.getString("nome"), rset.getString("email_corporativo"),
                            rset.getString("ramo_atuacao"), rset.getString("telefone"),
                            rset.getBoolean("status_atividade"), rset.getDate("inicio_plano"),
                            rset.getString("tipo_assinatura")));
                    listaAdministradores.get(listaAdministradores.size()-1).setId(rset.getInt("id"));
                }
            }
            conMan.desconectar(conn);
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return listaAdministradores;
    }

    public boolean deletarEmpresa(int id) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;

        try (Connection conn = conMan.conectar();) {
            String sql = "DELETE FROM Empresa WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(2, id);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
                    System.out.println(exc.getMessage());
            return resultado;
        }
    }
}