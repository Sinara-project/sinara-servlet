package com.sinara.servlet;

import com.sinara.utils.SenhaUtils;
import com.sinara.dao.AdministradorDAO;
import com.sinara.model.Administrador;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.sinara.servlet.ServletAreaRestrita;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;


@WebServlet("/administracao")
public class ServletAdministracao extends HttpServlet {

    // ID fixo da empresa para garantir que este servlet gerencie apenas os administradores corretos.
    public static final int idEmpresaSinara = 11;


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> erros = new LinkedList<>();
        // Verifica se o usuário está logado
        listarAdministradores(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> erros = new LinkedList<>();
        // Verifica se o usuário está logado
        if (!ServletAreaRestrita.ehLogado(request, response, erros)) {
            response.sendRedirect(request.getContextPath()); // redireciona para login
            return;
        }

        String action = request.getParameter("action");
        if (action == null) {
            listarAdministradores(request, response);
            return;
        }

        switch (action) {
            case "excluir" -> excluirAdministrador(request, response);
            case "alterar" -> editarAdministrador(request, response);
            case "adicionar" -> inserirAdministrador(request, response);
            default -> listarAdministradores(request, response);
        }
    }


    /**
     * Busca todos os administradores da empresa e os encaminha para a página de listagem (JSP).
     */
    protected void listarAdministradores(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        AdministradorDAO administradorDao = new AdministradorDAO();

        // Utiliza um Map como filtro para passar parâmetros para a camada DAO.
        Map<String, Object> filtro = new HashMap<>();
        filtro.put("id_empresa", idEmpresaSinara);

        List<Administrador> administradores = administradorDao.buscarPorFiltro(filtro);

        // Adiciona a lista de administradores ao request para ser acessada na JSP.
        request.setAttribute("administradores", administradores);

        RequestDispatcher dispatcher =
                request.getRequestDispatcher("WEB-INF/views/admin.jsp");
        dispatcher.forward(request, response);
    }


    /**
     * Exclui um administrador com base no ID fornecido.
     */
    protected void excluirAdministrador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> erros = new ArrayList<>();
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                erros.add("Erro: Nenhum id foi informado");
            } else {
                int adminId = Integer.parseInt(idParam);

                AdministradorDAO administradorDao = new AdministradorDAO();
                Map<String, Object> filtro = new HashMap<>();

                filtro.put("id", adminId);
                List<Administrador> admin = administradorDao.buscarPorFiltro(filtro);

                if (admin == null || admin.isEmpty()) {
                    erros.add("Erro: Administrador não encontrado");
                } else {
                    boolean excluido = administradorDao.deletarAdministrador(adminId);
                    if (!excluido) {
                        erros.add("Erro: Não foi possível excluir o administrador");
                    }
                }
            }
        } catch (NumberFormatException e) {
            erros.add("Erro: ID inválido");
        } finally {
            // Utiliza a sessão para enviar mensagens de feedback (padrão flash message).
            if (erros.isEmpty()) {
                request.getSession().setAttribute("message", "Administrador excluído com sucesso");
            } else {
                request.getSession().setAttribute("erros", erros);
            }
            response.sendRedirect(request.getContextPath() + "/administracao");
        }
    }


    /**
     * Processa a alteração dos dados de um administrador.
     */
    protected void editarAdministrador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> erros = new ArrayList<>();
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                erros.add("Erro: ID inválido");
            } else {
                int adminId = Integer.parseInt(idParam);
                AdministradorDAO administradorDao = new AdministradorDAO();
                Map<String, Object> filtro = new HashMap<>();
                filtro.put("id", adminId);
                List<Administrador> admins = administradorDao.buscarPorFiltro(filtro);
                if (admins == null || admins.isEmpty() || admins.get(0) == null) {
                    erros.add("Erro: administrador não encontrado");
                } else {
                    Administrador admin = admins.get(0);
                    String nome = request.getParameter("nome");
                    String email = request.getParameter("email");
                    String cargo = request.getParameter("cargo");

                    // Flag para verificar se alguma alteração foi de fato realizada.
                    boolean alterado = false;

                    // Verifica cada campo individualmente e o atualiza no banco apenas se um novo valor foi fornecido.
                    if (nome != null && !nome.trim().isEmpty() && !nome.equals(admin.getNome())) {
                        administradorDao.alterarNome(adminId, nome);
                        alterado = true;
                    }
                    if (email != null && !email.trim().isEmpty() && !email.equals(admin.getEmail())) {
                        administradorDao.alterarEmail(adminId, email);
                        alterado = true;
                    }
                    if (cargo != null && !cargo.trim().isEmpty() && !cargo.equals(admin.getCargo())) {
                        administradorDao.alterarCargo(adminId, cargo);
                        alterado = true;
                    }

                    if (!alterado) {
                        erros.add("Nenhuma alteração foi realizada");
                    }
                }
            }
        } catch (NumberFormatException e) {
            erros.add("Erro: ID inválido");
        } finally {
            if (erros.isEmpty()) {
                request.getSession().setAttribute("message", "Administrador editado com sucesso");
            } else {
                request.getSession().setAttribute("erros", erros);
            }
            response.sendRedirect(request.getContextPath() + "/administracao");
        }
    }


    /**
     * Processa os dados do formulário para inserir um novo administrador no banco de dados.
     */
    protected void inserirAdministrador(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> erros = new LinkedList<>();

        try {
            AdministradorDAO administradorDao = new AdministradorDAO();

            String cpf = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cargo = request.getParameter("cargo");
            String senhaPura = request.getParameter("senha");

            // Validação inicial para garantir que todos os campos foram preenchidos.
            if (cpf == null || cpf.trim().isEmpty() ||
                    nome == null || nome.trim().isEmpty() ||
                    email == null || email.trim().isEmpty() ||
                    cargo == null || cargo.trim().isEmpty() ||
                    senhaPura == null || senhaPura.trim().isEmpty()) {
                erros.add("Erro: todos os campos são obrigatórios.");
            }

            // Prossegue com validações mais específicas apenas se os campos não estiverem vazios.
            if (erros.isEmpty()) {
                // Limpa o CPF, removendo formatação para consistência no banco de dados.
                String cpfNumeros = cpf.replace(".", "").replace("-", "").trim();

                if (!cpfNumeros.matches("\\d{11}")) {
                    erros.add("Erro: CPF inválido.");
                } else {
                    // Verifica se o CPF já está cadastrado para evitar duplicidade.
                    Map<String, Object> filtro = new HashMap<>();
                    filtro.put("cpf", cpfNumeros);
                    List<Administrador> existentes = administradorDao.buscarPorFiltro(filtro);
                    if (existentes != null && !existentes.isEmpty()) {
                        erros.add("Erro: Já existe um administrador com este CPF.");
                    }

                    // Classe para gerar hash de senha
                    String senhaHasheada = SenhaUtils.hashearSenha(senhaPura);

                    Administrador admin = new Administrador(
                            nome.trim(),
                            email.trim(),
                            senhaHasheada,
                            cpfNumeros,
                            cargo.trim(),
                            idEmpresaSinara
                    );

                    // A inserção só é tentada se todas as validações anteriores passaram.
                    boolean inserido = false;
                    if (erros.isEmpty()) {
                        inserido = administradorDao.inserirAdministrador(admin);
                    }
                    if (!inserido) {
                        erros.add("Erro ao criar administrador.");
                    }
                }
            }
        } catch (SQLException e) {
            // Captura erros de banco de dados e informa uma mensagem genérica.
            e.printStackTrace();
            erros.add("Erro interno no sistema.");
        } finally {
            if (!erros.isEmpty()) {
                request.getSession().setAttribute("erros", erros);
            } else {
                request.getSession().setAttribute("message", "Administrador adicionado com sucesso!");
            }
            response.sendRedirect(request.getContextPath() + "/administracao");
        }
    }
}