package com.sinara.dao;
import java.sql.*;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sinara.connection.ConexaoDB;
import com.sinara.model.*;

import static com.sinara.model.Empresa.telefoneRegex;

public class EmpresaDAO {

    // Método de inserção
    public int inserirEmpresa(Empresa empresa) {
        // Variáveis para conexão com banco
        ConexaoDB conMan = new ConexaoDB();

        try (Connection conn = conMan.conectar()) {
            // Script SQL usado para inserir nova empresa
            String sql = "INSERT INTO Empresa (cnpj, nome, email_corporativo, telefone, ramo_atuacao, status_atividade, " +
                    "inicio_plano, tipo_assinatura) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                // Setar variáveis de acordo com parâmetro 'empresa'
                pstm.setString(1, empresa.getCnpj());
                pstm.setString(2, empresa.getNome());
                pstm.setString(3, empresa.getEmail());
                pstm.setString(4, empresa.getTelefone());
                pstm.setString(5, empresa.getRamo());
                pstm.setBoolean(6, empresa.isStatus());
                pstm.setDate(7, empresa.getInicioPlano()); /* AINDA NÃO IMPLEMENTADO*/
                /* Como transformar util.Date em sql.Date */
                pstm.setString(8, String.valueOf(empresa.getPlano()));

                // Retornar se a empresa foi ou não adicionado
                // (variável de apoio usada para fechar conexão antes de retornar método)
                if (pstm.executeUpdate() <= 0) return 0;
                conMan.desconectar(conn);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            return 2;
        }
        return 1;
    }

    // Método para listar empresas
    public List<Empresa> listarEmpresas() {
        // Criar conexão com BD, e lista que será retornada
        ConexaoDB conMan = new ConexaoDB();
        List<Empresa> listaAdministradores = new LinkedList<Empresa>();

        try (Connection conn = conMan.conectar()) {
            String sql = "SELECT * FROM Empresa ORDER BY nome";

            try (Statement stm = conn.createStatement()) {
                // Adicionar novo objeto de Empresa na lista a cada linha do rset
                ResultSet rset = stm.executeQuery(sql);
                while (rset.next()) {
                    listaAdministradores.add(new Empresa(null, rset.getString("cnpj"),
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
    public List<VisaoGeral> listarVisoes() {
        // Criar conexão com BD, e lista que será retornada
        ConexaoDB conMan = new ConexaoDB();
        List<VisaoGeral> listaVisoes = new LinkedList<>();
        List<String> erros =  new LinkedList<>();

        try (Connection conn = conMan.conectar()) {
            String sql = "SELECT * FROM visao_geral ORDER BY nome";

            try (Statement stm = conn.createStatement()) {
                // Adicionar novo objeto de Empresa na lista a cada linha do rset
                ResultSet rset = stm.executeQuery(sql);
                while (rset.next()) {
                    listaVisoes.add(new VisaoGeral(rset, erros));
                }
            }
            conMan.desconectar(conn);
        } catch (SQLException exc) {
            System.out.println(exc.getMessage());
        }
        return listaVisoes;
    }

    // Método para busca de empresas
    public List<Empresa> buscarPorFiltro(Map<String, Object> campos) {
        // Criar conexão com DB, lista que será retornada e variável de apoio para contagem
        ConexaoDB conMan = new ConexaoDB();
        List<Empresa> listaAdministradores = new LinkedList<Empresa>();
        int cont = 0;

        try (Connection conn = conMan.conectar()) {
            // Criar  Script SQL para selecionar empresas com condicional
            StringBuilder sql = new StringBuilder("SELECT * FROM Empresa WHERE ");

            // script sql recebe um set a cada filtro com base no campo (chave do Map)
            for (String s : campos.keySet()) {
                sql.append(s).append(" = ?");
                cont++;
                if (cont < campos.size()) sql.append(" AND ");
                // Adicionar 'AND' apenas caso chegue no tamanho dos filtros
            }

            // Criar PreparedStatement pstm
            try (PreparedStatement pstm = conn.prepareStatement(sql.toString())) {
                cont = 1;
                // setar valores no pstm de acordo com filtros fornecidos 
                for (Object s : campos.values()) {
                    pstm.setObject(cont++, s);
                }
                
                // Executar query e adicionar Empresa a cada linha do rset na lista
                ResultSet rset = pstm.executeQuery();
                while (rset.next()) {
                    listaAdministradores.add(new Empresa(null, rset.getString("cnpj"),
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

    // Métodos para alterações
    public boolean alterarEmpresa(Empresa empresa, List<String> erros) {
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("id", empresa.getId());
        Empresa empOrg = buscarPorFiltro(filtros).get(0);

        if (erros.isEmpty()) {
            // Chama os métodos 'alterarCampo' e trata os resultados dos mesmos,
            // adicionando-os aos erros com 'tratarResultado'
            Empresa.tratarResultado(alterarNome(empresa.getId(), empresa.getNome(), empOrg.getNome()), erros, "nome");
            Empresa.tratarResultado(alterarEmail(empresa.getId(), empresa.getEmail(), empOrg.getEmail()), erros, "email");
            Empresa.tratarResultado(alterarTelefone(empresa.getId(), empresa.getTelefone(), empOrg.getTelefone()), erros, "telefone");
            Empresa.tratarResultado(alterarRamo(empresa.getId(), empresa.getRamo(), empOrg.getRamo()), erros, "ramo de atuação");
            Empresa.tratarResultado(alterarAtividade(empresa.getId(), empresa.isStatus(), empOrg.isStatus()), erros, "status de atividade");
        }
        return erros.isEmpty();
    }
    public int alterarNome(int id, String nome, String nomeOrg) {
        // Criando conexão com BD
        ConexaoDB conMan = new ConexaoDB();
        Connection conn = null;

        try {
            conn = conMan.conectar();

            // Se houve mudança no nome
            if (!nomeOrg.equals(nome)) {
                String sql = "UPDATE Empresa SET nome = ? WHERE id = ?";
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1, nome);
                pstm.setInt(2, id);
                pstm.executeUpdate();
            } else return 0;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        } catch (SQLException exc) {
            exc.printStackTrace();
            return 2;
        }
        conMan.desconectar(conn);
        return 1;
    }
    public int alterarEmail(int id, String email, String emailOrg) {
        // Criando conexão com BD
        ConexaoDB conMan = new ConexaoDB();
        Connection conn = null;

        // Checar sintaxe de email a partir de RegEx
        if (Empresa.checarSintaxe(email, Empresa.emailRegex)) {
            try {
                conn = conMan.conectar();

                // Se houve mudança no email
                if (!emailOrg.equals(email)) {
                    String sql = "UPDATE Empresa SET email_corporativo = ? WHERE id = ?";
                    PreparedStatement pstm = conn.prepareStatement(sql);
                    pstm.setString(1, email);
                    pstm.setInt(2, id);
                    pstm.executeUpdate();
                } else return 0;
            } catch (NullPointerException exc) {
                exc.printStackTrace();
                return -1;
            } catch (SQLException exc) {
                exc.printStackTrace();
                return 2;
            }
            conMan.desconectar(conn);
            return 1;
        } else return 3;
    }
    public int alterarTelefone(int id, String telefone, String telOrg) {
        // Criando conexão com BD
        String sql = "UPDATE Empresa SET telefone = ? WHERE id = ?";
        ConexaoDB conMan = new ConexaoDB();
        Connection conn = null;
        if (Empresa.checarSintaxe(telefone, telefoneRegex)) {
            telefone = Empresa.extrairNumeros(telefone);
            try {
                conn = conMan.conectar();

                // Se houve mudança no telefone
                if (!telOrg.equals(telefone)) {
                    // Mudar telefone
                    PreparedStatement pstm = conn.prepareStatement(sql);
                    pstm.setString(1, telefone);
                    pstm.setInt(2, id);
                    if (pstm.executeUpdate() <= 0) return 0;
                } else return 0;
            } catch (NullPointerException exc) {
                exc.printStackTrace();
                return -1;
            } catch (SQLException exc) {
                exc.printStackTrace();
                return 2;
            }
        } else return 3;
        conMan.desconectar(conn);
        return 1;
    }
    public int alterarRamo(int id, String ramo, String ramoOrg) {
        if (ramo==null || ramo.isBlank()) return -1;
        // Criando conexão com BD
        ConexaoDB conMan = new ConexaoDB();
        Connection conn = null;
        try {
            conn = conMan.conectar();

            // Se houve mudança no ramo
            if (!ramoOrg.equals(ramo)) {
                String sql = "UPDATE Empresa SET ramo_atuacao = ? WHERE id = ?";
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setString(1, ramo);
                pstm.setInt(2, id);
                pstm.executeUpdate();
            } else return 0;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        } catch (SQLException exc) {
            exc.printStackTrace();
            return 2;
        }
        conMan.desconectar(conn);
        return 1;
    }
    public int alterarAtividade(int id, boolean atividade, boolean statusOrg) {
        // Criando conexão com BD
        ConexaoDB conMan = new ConexaoDB();
        Connection conn = null;
        try {
            conn = conMan.conectar();

            // Se houve mudança na atividade
            if (statusOrg!=atividade) {
                String sql = "UPDATE Empresa SET status_atividade = ? WHERE id = ?";
                PreparedStatement pstm = conn.prepareStatement(sql);
                pstm.setBoolean(1, atividade);
                pstm.setInt(2, id);
                pstm.executeUpdate();
            } else return 0;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        } catch (SQLException exc) {
            exc.printStackTrace();
            return 2;
        }
        conMan.desconectar(conn);
        return 1;
    }

    // Método para remoção de empresa pelo ID
    public int deletarEmpresa(int id) {
        ConexaoDB conMan = new ConexaoDB();
        Connection conn = null;

        try {
            conn = conMan.conectar();
            String sql = "DELETE FROM Empresa WHERE id = ?";
            try (PreparedStatement pstm = conn.prepareStatement(sql)) {
                pstm.setInt(1, id);

                if (pstm.executeUpdate() <= 0) return 0;
                conMan.desconectar(conn);
            }
        } catch (SQLException exc) {
            exc.printStackTrace();
            return 2;
        } catch (NullPointerException exc) {
            exc.printStackTrace();
            return -1;
        }
        return 1;
    }
}