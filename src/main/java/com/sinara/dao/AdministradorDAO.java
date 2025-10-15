package com.sinara.dao;
import com.sinara.connection.ConexaoDB;
import com.sinara.model.Administrador;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AdministradorDAO {
    public boolean inserirAdministrador(Administrador admin) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;
        int id = 0;

        try (Connection conn = conMan.conectar();) {
            String sql = "INSERT INTO Permissoes (inserir_dados, editar_dados, visualizar_relatorios, aprovar_registros, gerenciar_usuarios) " +
                    "VALUES (?, ?, ?, ?, ?);RETURNING id";
//                    "INSERT INTO Administrador (cpf, nome, email_admin, cargo, senha, id_permissao) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmPerm = conn.prepareStatement(sql)) {
                // Valores para criação da permissão
                pstmPerm.setBoolean(1, admin.getPermissoes().temPermissao(1));
                pstmPerm.setBoolean(2, admin.getPermissoes().temPermissao(2));
                pstmPerm.setBoolean(3, admin.getPermissoes().temPermissao(3));
                pstmPerm.setBoolean(4, admin.getPermissoes().temPermissao(4));
                pstmPerm.setBoolean(5, admin.getPermissoes().temPermissao(5));

                ResultSet rset = pstmPerm.executeQuery();
                while (rset.next()) {
                    id = rset.getInt("id_permissao");
                }
            }
            sql = "INSERT INTO Administrador (cpf, nome, email_admin, cargo, senha, id_permissao) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement pstmAdm = conn.prepareStatement(sql)) {
                pstmAdm.setString(1, admin.getCpf());
                pstmAdm.setString(2, admin.getNome());
                pstmAdm.setString(3, admin.getEmail());
                pstmAdm.setString(4, admin.getCargo());
                pstmAdm.setString(5, admin.getSenha());
                pstmAdm.setInt(6, id);
                resultado = pstmAdm.executeUpdate() > 0;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return resultado;
    }
    // Alterações

    public boolean alterarNome(int id, String nome) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Administrador WHERE id = ? SET nome = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, id);
                pstm.setString(2, nome);

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
            String sql = "UPDATE Administrador WHERE id = ? SET email_admin = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, id);
                pstm.setString(2, email);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }

    public boolean alterarCargo(int id, String cargo) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Administrador WHERE id = ? SET cargo = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, id);
                pstm.setString(2, cargo);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }

    public boolean alterarSenha(int id, String senha) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Administrador WHERE id = ? SET senha = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, id);
                pstm.setString(2, senha);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }

    public List<Administrador> buscarPorFiltro(Map<String, Object> campos) {
        ConexaoDB conMan = new ConexaoDB();
        List<Administrador> listaAdministradores = new LinkedList<Administrador>();
        int i = 0;

        try (Connection conn = conMan.conectar()) {
            String sql = "SELECT * FROM Administrador WHERE ";

            // script sql recebe mais um set para busca a cada filtro fornecido
            for (String s : campos.keySet()) {
                sql+=s+" = ?";
                i++;
                if (i < campos.size()) sql+=" AND ";
            }

            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                i = 1;
                // pstm seta os campos de acordo com a quantidade e valores no Map
                for (Object s : campos.values()) {
                    pstm.setObject(i++, s);
                }
                ResultSet rset = pstm.executeQuery();
                while (rset.next()) {
                    listaAdministradores.add(new Administrador(rset.getString("nome"),
                            rset.getString("email_admin"), rset.getString("senha"),
                            rset.getString("cpf"), rset.getString("cargo"),
                            rset.getString("id_empresa")));
                    listaAdministradores.get(listaAdministradores.size()-1).setId(rset.getInt("id"));
                }
            }
            conMan.desconectar(conn);
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return listaAdministradores;
    }

    public boolean deletarAdministrador(int idPerm, int id) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;

        try (Connection conn = conMan.conectar();) {
            String sql = "DELETE FROM Permissoes WHERE id = ?; " +
                    "DELETE FROM Administrador WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, idPerm);
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