package com.sinara.dao;
import com.sinara.connection.ConexaoDB;
import com.sinara.model.Administrador;
import com.sinara.model.Permissoes;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class AdministradorDAO {
    public boolean inserirAdministrador(Administrador admin) throws SQLException, NullPointerException {
        if (admin.getCpf()==null || admin.getNome()==null || admin.getEmail()==null || admin.getCargo()==null || admin.getSenha()==null) throw new NullPointerException();
        if (admin.getCpf().isBlank() || admin.getNome().isBlank() || admin.getEmail().isBlank() || admin.getCargo().isBlank() || admin.getSenha().isBlank()) throw new NullPointerException();
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado;
        int id = 0;

            Connection conn = conMan.conectar();
            String sql = "INSERT INTO Permissoes (inserir_dados, editar_dados, visualizar_relatorios, aprovar_registros, gerenciar_usuarios) " +
                    "VALUES (?, ?, ?, ?, ?)";
//                    "INSERT INTO Administrador (cpf, nome, email_admin, cargo, senha, id_permissao) VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmPerm = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            // Valores para criação da permissão
            pstmPerm.setBoolean(1, admin.getPermissoes().temPermissao(1));
            pstmPerm.setBoolean(2, admin.getPermissoes().temPermissao(2));
            pstmPerm.setBoolean(3, admin.getPermissoes().temPermissao(3));
            pstmPerm.setBoolean(4, admin.getPermissoes().temPermissao(4));
            pstmPerm.setBoolean(5, admin.getPermissoes().temPermissao(5));
            pstmPerm.executeUpdate();
            ResultSet rset = pstmPerm.getGeneratedKeys();
            if (rset.next()) {
                id = rset.getInt(1);
            }
            sql = "INSERT INTO Administrador (cpf, nome, email_admin, cargo, senha, id_permissoes, id_empresa) VALUES (?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmAdm = conn.prepareStatement(sql);
            pstmAdm.setString(1, admin.getCpf());
            pstmAdm.setString(2, admin.getNome());
            pstmAdm.setString(3, admin.getEmail());
            pstmAdm.setString(4, admin.getCargo());
            pstmAdm.setString(5, admin.getSenha());
            pstmAdm.setInt(6, id);
            pstmAdm.setInt(7, admin.getIdEmpresa());
            resultado = pstmAdm.executeUpdate() > 0;
        return resultado;
    }
    // Alterações

    public boolean inserirUUID(int id, String uuid) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Administrador SET token_administracao = ? WHERE id = ? ";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(2, id);
                pstm.setString(1, uuid);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }
    public boolean alterarNome(int id, String nome) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar()) {
            String sql = "UPDATE Administrador SET nome = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, nome);
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

    public boolean alterarEmail(int id, String email) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar()) {
            String sql = "UPDATE Administrador SET email_admin = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, email);
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

    public boolean alterarCargo(int id, String cargo) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Administrador set cargo = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setString(1, cargo);
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

    public boolean alterarSenha(int id, String senha) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Administrador SET senha = ? WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(2, id);
                pstm.setString(1, senha);

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
                            rset.getInt("id_empresa")));
                    listaAdministradores.get(listaAdministradores.size()-1).setId(rset.getInt("id"));
                }
            }
            conMan.desconectar(conn);
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return listaAdministradores;
    }


    public List<Administrador> listarAdministradores() {
        ConexaoDB conexaoDB = new ConexaoDB();
        List<Administrador> administradores = new ArrayList<>();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Connection conn = null;
        try {
            conn = conexaoDB.conectar();
            pstmt = conn.prepareStatement("SELECT *  FROM Administrador");
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Administrador admin = new Administrador(
                        rs.getString("nome"),
                        rs.getString("email_admin"),
                        rs.getString("senha"),
                        rs.getString("cpf"),
                        rs.getString("cargo"),
                        rs.getInt("id_empresa")
                );
                admin.setId(rs.getInt("id"));
                administradores.add(admin);
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return administradores;
    }




    public boolean deletarAdministrador(int idPerm, int id) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;

        try (Connection conn = conMan.conectar();) {
            String sql = "DELETE FROM Administrador WHERE id = ?; " +
                    "DELETE FROM Permissoes WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, id);
                pstm.setInt(2, idPerm);

                resultado = pstm.executeUpdate() > 0;
                conMan.desconectar(conn);
                return resultado;
            }
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
            return resultado;
        }
    }
    public boolean deletarUUID(int id) {
        ConexaoDB conMan = new ConexaoDB();
        boolean resultado = false;


        try (Connection conn = conMan.conectar();) {
            String sql = "UPDATE Administrador SET token_administracao = '' WHERE id = ? ";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, id);

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