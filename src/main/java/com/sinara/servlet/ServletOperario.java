package com.sinara.servlet;

import com.sinara.dao.EmpresaDAO;
import com.sinara.dao.OperarioDAO;
import com.sinara.model.Empresa;
import com.sinara.model.Operario;
import com.sinara.utils.SenhaUtils;
import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Time;
import java.util.*;

@WebServlet("/operarios")
public class ServletOperario extends HttpServlet {

    @Override
    public void init() {
        System.out.println("Servlet Operario inicializado");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Roteia as requisições GET para os métodos apropriados com base no parâmetro "action".
        String action = request.getParameter("action");
        if (action == null) {
            // A ação padrão, se nenhuma for especificada, é listar os operários.
            listarOperarios(request,response);
            return;
        }
        switch (action) {
            case "listar" -> listarOperarios(request,response);
            case "editar" -> buscarFuncionario(request,response);
            case "adicionar" -> adicionarOperario(request, response);
            default -> listarOperarios(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Roteia as requisições POST, que geralmente envolvem modificação de dados.
        String action = request.getParameter("action");
        if (action == null) {
            listarOperarios(request,response);
            return;
        }

        switch (action) {
            case "excluir" -> excluirOperario(request,response);
            case "atualizar" -> atualizarFuncionario(request,response);
            case "inserir" -> inserirOperario(request, response);
            default -> listarOperarios(request, response);
        }
    }

    /**
     * Lista todos os operários e busca o nome da empresa correspondente a cada um para exibição.
     */
    private void listarOperarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Map para armazenar nomes de empresas já buscados e evitar consultas repetidas ao banco.
        Map<Integer, String> idParaNome = new HashMap<>();
        List<Operario> listaOperarios = null;
        List<Empresa> listaEmpresas = null;

        try {
            OperarioDAO operarioDao = new OperarioDAO();
            EmpresaDAO empresaDao = new EmpresaDAO();

            listaEmpresas = empresaDao.listarEmpresas();
            listaOperarios = operarioDao.listarOperarios();
            // Para cada operário na lista, busca o nome da empresa associada.
            for (Operario operario : listaOperarios) {
                int empresaId = operario.getIdEmpresa();

                // Verifica se o nome da empresa já foi buscado para otimizar a performance.
                if (!idParaNome.containsKey(empresaId)) {
                    Map<String, Object> filtro = new HashMap<>();
                    filtro.put("id", empresaId);
                    List<Empresa> empresas = empresaDao.buscarPorFiltro(filtro);
                    if (!empresas.isEmpty()) {
                        idParaNome.put(empresaId,empresas.get(0).getNome());

                    } else {
                        idParaNome.put(empresaId, "Empresa não encontrada");
                    }
                }
            }
        } finally {
            // Garante que os atributos sejam enviados para a JSP mesmo que ocorra um erro.
            request.setAttribute("listaOperarios", listaOperarios);
            request.setAttribute("idParaNome", idParaNome);
            request.setAttribute("listaEmpresas", listaEmpresas);
            request.getRequestDispatcher("WEB-INF/views/funcionario.jsp").forward(request,response);
        }
    }

    /**
     * Exclui um operário com base no ID recebido como parâmetro.
     */
    private void excluirOperario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> erros = new ArrayList<>();

        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                erros.add("Erro: ID inválido");
            } else {
                OperarioDAO operarioDao = new OperarioDAO();
                int operarioId = Integer.parseInt(idParam);
                Map<String, Object> filtro = new HashMap<>();
                filtro.put("id", operarioId);
                List<Operario> operario = operarioDao.buscarPorFiltro(filtro);
                // Valida se o operário a ser excluído realmente existe.
                if (operario == null || operario.isEmpty()) {
                    erros.add("Erro: Operário não encontrado");
                } else {
                    boolean excluido = operarioDao.deletarOperario(operarioId);
                    if (!excluido) {
                        erros.add("Não foi possível excluir o operário");
                    }
                }
            }
        } catch (NumberFormatException e) {
            erros.add("Erro: ID inválido");
        } finally {
            // Utiliza a sessão para enviar mensagens de feedback (sucesso ou erro) após o redirecionamento.
            if (erros.isEmpty()) {
                request.getSession().setAttribute("message", "Operário excluído com sucesso");
            } else {
                request.getSession().setAttribute("erros", erros);
            }
            response.sendRedirect(request.getContextPath() + "/operarios");
        }
    }

    /**
     * Atualiza os dados de um funcionário (operário) existente.
     */
    private void atualizarFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> erros = new ArrayList<>();
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                erros.add("Erro: ID inválido");
            } else {
                int operarioId = Integer.parseInt(idParam);
                OperarioDAO operarioDao = new OperarioDAO();
                Operario operario = operarioDao.buscarPorId(operarioId);

                if (operario == null) {
                    erros.add("Erro: Operário não encontrado");
                } else {
                    String nome = request.getParameter("nome");
                    String email = request.getParameter("email");
                    String cargo = request.getParameter("cargo");

                    // Flag para verificar se alguma alteração foi de fato realizada.
                    boolean alterado = false;
                    // Verifica cada campo individualmente para atualizar apenas o que mudou.
                    if (nome != null && !nome.trim().isEmpty() && !nome.equals(operario.getNome())) {
                        operarioDao.alterarNome(operarioId, nome);
                        alterado = true;
                    }
                    if (email != null && !email.trim().isEmpty() && !email.equals(operario.getEmail())) {
                        operarioDao.alterarEmail(operarioId, email);
                        alterado = true;
                    }
                    if (cargo != null && !cargo.trim().isEmpty() && !cargo.equals(operario.getCargo())) {
                        operarioDao.alterarCargo(operarioId, cargo);
                        alterado = true;
                    }

                    if (!alterado) {
                        erros.add("Erro: Nenhuma alteração foi feita.");
                    }
                }
            }
        } catch (NumberFormatException e) {
            erros.add("Erro: ID inválido");
        } finally {
            if (erros.isEmpty()) {
                request.getSession().setAttribute("message", "Operário atualizado com sucesso");
            } else {
                request.getSession().setAttribute("erros", erros);
            }
            response.sendRedirect(request.getContextPath() + "/operarios");
        }
    }

    /**
     * Busca um funcionário específico pelo ID e o encaminha para a página de edição.
     * Também busca o nome da empresa para exibição no formulário.
     */
    private void buscarFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> erros = new ArrayList<>();
        try {
            String idParam = request.getParameter("id");
            if (idParam == null || idParam.trim().isEmpty()) {
                erros.add("Erro: ID inválido");
            } else {
                int operarioId = Integer.parseInt(idParam);
                EmpresaDAO empresaDao = new EmpresaDAO();
                OperarioDAO operarioDAO = new OperarioDAO();

                Operario operario = operarioDAO.buscarPorId(operarioId);
                if (operario == null) {
                    erros.add("Erro: Operário não encontrado");
                } else {
                    // Busca o nome da empresa para exibir na página de edição.
                    int empresaId = operario.getIdEmpresa();
                    Map<String, Object> filtro = new HashMap<>();
                    filtro.put("id", empresaId);
                    List<Empresa> empresas = empresaDao.buscarPorFiltro(filtro);

                    if (empresas == null || empresas.isEmpty()) {
                        erros.add("Empresa não encontrada");
                    } else {
                        String nomeEmpresa = empresas.get(0).getNome();
                        request.setAttribute("operario", operario);
                        request.setAttribute("nomeEmpresa", nomeEmpresa);
                    }
                }
            }
        } catch (NumberFormatException e) {
            erros.add("Erro: ID inválido");
        } finally {
            if (erros.isEmpty()) {
                // Se não houver erros, encaminha para a página de edição.
                request.getRequestDispatcher("WEB-INF/views/editarOperario.jsp").forward(request,response);
            } else {
                // Se houver erros, armazena na sessão e redireciona para a listagem.
                request.getSession().setAttribute("erros", erros);
                response.sendRedirect(request.getContextPath() + "/operarios");
            }
        }
    }

    /**
     * Prepara o formulário de adição de operário, carregando a lista de empresas disponíveis.
     */
    private void adicionarOperario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Busca todas as empresas para popular um campo <select> no formulário de inserção.
            EmpresaDAO empresaDao = new EmpresaDAO();
            List<Empresa> listaEmpresas = empresaDao.listarEmpresas();
            request.setAttribute("empresas",listaEmpresas);
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("WEB-INF/views/inserirOperario.jsp");
            dispatcher.forward(request,response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Processa os dados do formulário para inserir um novo operário no banco de dados.
     */
    protected void inserirOperario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<String> erros = new ArrayList<>();

        try {
            String cpf = request.getParameter("cpf");
            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cargo = request.getParameter("cargo");
            String senhaPura = request.getParameter("senha");
            String empresa = request.getParameter("empresa");
            String horarioParam = request.getParameter("horarioTrabalho");

            // Validação inicial para garantir que todos os campos obrigatórios foram preenchidos.
            if (cpf == null || cpf.trim().isEmpty() ||
                    nome == null || nome.trim().isEmpty() ||
                    email == null || email.trim().isEmpty() ||
                    cargo == null || cargo.trim().isEmpty() ||
                    senhaPura == null || senhaPura.trim().isEmpty() ||
                    empresa == null || empresa.trim().isEmpty() ||
                    horarioParam == null || horarioParam.trim().isEmpty()) {
                erros.add("Erro: todos os campos são obrigatórios.");
            } else {
                // Remove a formatação do CPF para armazenamento consistente.
                String cpfNumeros = cpf.replace(".", "").replace("-", "").trim();

                if (!cpfNumeros.matches("\\d{11}")) {
                    erros.add("Erro: CPF inválido.");
                } else {
                    OperarioDAO operarioDao = new OperarioDAO();
                    EmpresaDAO empresaDao = new EmpresaDAO();

                    // Verifica se o CPF já existe no banco de dados para evitar duplicidade.
                    Map<String, Object> filtroCpf = new HashMap<>();
                    filtroCpf.put("cpf", cpfNumeros);
                    if (!operarioDao.buscarPorFiltro(filtroCpf).isEmpty()) {
                        erros.add("Erro: CPF já cadastrado.");
                    }

                    // Valida e converte o horário de trabalho para o formato SQL Time.
                    Time horarioTrabalho = null;
                    if (horarioParam.matches("^([01]\\d|2[0-3]):[0-5]\\d$")) {
                        horarioTrabalho = Time.valueOf(horarioParam + ":00");
                    } else {
                        erros.add("Erro: o horário deve estar no formato HH:mm (ex: 08:30).");
                    }

                    // Valida se a empresa selecionada no formulário realmente existe.
                    Map<String, Object> filtro = new HashMap<>();
                    filtro.put("nome", empresa);
                    List<Empresa> empresas = empresaDao.buscarPorFiltro(filtro);
                    if (empresas == null || empresas.isEmpty()) {
                        erros.add("Erro: A empresa informada não existe");
                    } else {
                        // Gera um hash seguro da senha antes de salvá-la no banco.
                        String senhaHasheada = SenhaUtils.hashearSenha(senhaPura);

                        Operario operario = new Operario(
                                cpfNumeros, nome, email, cargo, senhaHasheada,
                                empresas.get(0).getId(), // Pega o ID da empresa encontrada.
                                horarioTrabalho
                        );

                        // A inserção só é tentada se todas as validações anteriores passaram.
                        if (erros.isEmpty()) {
                            if (!operarioDao.inserirOperario(operario)) {
                                erros.add("Erro: Não foi possível adicionar o operário (Erro interno)");
                            }
                        }
                    }
                }
            }
        } finally {
            if (erros.isEmpty()) {
                request.getSession().setAttribute("message", "Operário adicionado com sucesso");
            } else {
                request.getSession().setAttribute("erros", erros);
            }
            response.sendRedirect(request.getContextPath() + "/operarios");
        }
    }
}