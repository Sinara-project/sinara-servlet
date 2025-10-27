package com.sinara.servlet;

import com.sinara.dao.EmpresaDAO;
import com.sinara.dao.OperarioDAO;
import com.sinara.model.Empresa;
import com.sinara.model.Operario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Time;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/operarios")
public class ServletOperario extends HttpServlet {

    @Override
    public void init() {
        System.out.println("Servlet Operario inicializado");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";
        switch (action) {
            case "listar" -> listarOperarios(request,response);
            case "editar" -> buscarFuncionario(request,response);
            case "adicionar" -> adicionarOperario(request, response);
            default -> listarOperarios(request, response);
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) action = "";

        switch (action) {
            case "excluir" -> excluirOperario(request,response);
            case "atualizar" -> atualizarFuncionario(request,response);
            case "inserir" -> inserirOperario(request, response);
            default -> listarOperarios(request, response);
        }
    }

    private void listarOperarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            OperarioDAO operarioDao = new OperarioDAO();
            EmpresaDAO empresaDao = new EmpresaDAO();

            List<Operario> listaOperarios = operarioDao.listarOperarios();

            Map<Integer, String> idParaNome = new HashMap<>();

            for (Operario operario : listaOperarios) {
                int empresaId = operario.getIdEmpresa();

                if (!idParaNome.containsKey(empresaId)) {
                    Map<String, Object> filtro = new HashMap<>();
                    filtro.put("id", empresaId);

                    List<Empresa> empresas = empresaDao.buscarPorFiltro(filtro);
                    if (!empresas.isEmpty()) {
                        idParaNome.put(empresaId, empresas.get(0).getNome());
                    } else {
                        idParaNome.put(empresaId, "Indústria não encontrada");
                    }
                }
            }

            request.setAttribute("listaOperarios", listaOperarios);
            request.setAttribute("idParaNome", idParaNome);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/views/operarios.jsp");
            dispatcher.forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("message", "Erro ao listar operários: " + e.getMessage());
            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/views/operarios.jsp");
            dispatcher.forward(request, response);
        }
    }

    private void excluirOperario(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        OperarioDAO operaioDao = new OperarioDAO();
        int id = Integer.parseInt(request.getParameter("id"));
        boolean excluido = operaioDao.deletarOperario(id);
        if (excluido) {
            request.getSession().setAttribute("message","Operário excluído com sucesso");
        } else {
            request.getSession().setAttribute("message","Erro interno");
        }
        response.sendRedirect(request.getContextPath() + "/operarios");

    }



    private void atualizarFuncionario(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            OperarioDAO operarioDao = new OperarioDAO();
            int id = Integer.parseInt(request.getParameter("id"));

            Operario operario = operarioDao.buscarPorId(id);

            if (operario == null) {
                request.getSession().setAttribute("message", "Erro: Operário não encontrado.");
                response.sendRedirect(request.getContextPath() + "/operarios");
                return;
            }

            String nome = request.getParameter("nome");
            String email = request.getParameter("email");
            String cargo = request.getParameter("cargo");

            boolean alterado = false;

            if (nome != null && !nome.trim().isEmpty() && !nome.equals(operario.getNome())) {
                operario.setNome(nome);
                alterado = true;
            }
            if (email != null && !email.trim().isEmpty() && !email.equals(operario.getEmail())) {
                operario.setEmail(email);
                alterado = true;
            }
            if (cargo != null && !cargo.trim().isEmpty() && !cargo.equals(operario.getCargo())) {
                operario.setCargo(cargo);
                alterado = true;
            }

            if (alterado) {
                // Só atualiza os campos que realmente foram preenchidos
                if (nome != null && !nome.trim().isEmpty()) {
                    operarioDao.alterarNome(id, nome);
                }
                if (email != null && !email.trim().isEmpty()) {
                    operarioDao.alterarEmail(id, email);
                }
                if (cargo != null && !cargo.trim().isEmpty()) {
                    operarioDao.alterarCargo(id, cargo);
                }

                request.getSession().setAttribute("message", "Operário atualizado com sucesso!");
            } else {
                request.getSession().setAttribute("message", "Nenhuma alteração foi feita.");
            }

            response.sendRedirect(request.getContextPath() + "/operarios");

        } catch (Exception e) {
            e.printStackTrace();
            request.getSession().setAttribute("message", "Erro ao atualizar o operário: " + e.getMessage());
            response.sendRedirect(request.getContextPath() + "/operarios");
        }
    }


    private void buscarFuncionario(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        String erro;

        int id = Integer.parseInt(request.getParameter("id"));
        EmpresaDAO empresaDao = new EmpresaDAO();
        OperarioDAO operarioDao = new OperarioDAO();
         Operario operario = operarioDao.buscarPorId(id);

        if (operario != null) {
            int empresaId = operario.getIdEmpresa();
            String nomeEmpresa;
            Map<String, Object> filtro = new HashMap<>();
            filtro.put("id", empresaId);

            List<Empresa> empresas = empresaDao.buscarPorFiltro(filtro);

            if (!empresas.isEmpty()) {
                nomeEmpresa = empresas.get(0).getNome();
            } else {
                nomeEmpresa = "Empresa não encontrada";
            }
            request.setAttribute("operario", operario);
            request.setAttribute("nomeEmpresa", nomeEmpresa);

            RequestDispatcher dispatcher =
                    request.getRequestDispatcher("/WEB-INF/views/editarOperario.jsp");
            dispatcher.forward(request, response);


        } else {
            erro = "Nenhum funcionário com o ID informado foi encontrado.";
            request.getSession().setAttribute("message", erro);
            response.sendRedirect(request.getContextPath() + "/operarios");

        }

    }

    private void adicionarOperario(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
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


    protected void inserirOperario(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        try {
            OperarioDAO operarioDAO = new OperarioDAO();
            EmpresaDAO empresaDao = new EmpresaDAO();

            Time horarioTrabalho = null;

            String horarioParam = request.getParameter("horarioTrabalho");

            if (horarioParam != null && !horarioParam.isEmpty()) {
                horarioParam += ":00"; // adiciona os segundos
                horarioTrabalho = Time.valueOf(horarioParam);
            }

            String empresa = request.getParameter("empresa");

            Map<String, Object> filtro = new HashMap<>();

            filtro.put("nome", empresa);

            List<Empresa> empresas = empresaDao.buscarPorFiltro(filtro);

            if (empresas.isEmpty()) {
                request.getSession().setAttribute("message", "Erro: empresa não encontrada.");
                response.sendRedirect(request.getContextPath() + "/operarios");
                return; // não continua
            }

            int idEmpresa = empresas.get(0).getId();


            Operario operario = new Operario(
                    request.getParameter("cpf"),
                    request.getParameter("nome"),
                    request.getParameter("email"),
                    request.getParameter("cargo"),
                    idEmpresa,
                    horarioTrabalho
            );
            if (operarioDAO.inserirOperario(operario)) {
                request.getSession().setAttribute("message", "operário adicionado com sucesso" );
            } else {
                request.getSession().setAttribute("message", "Erro interno: o operário não foi adicionado");
            }
            response.sendRedirect(request.getContextPath() + "/operarios");



        } catch (NullPointerException e) {
            request.getSession().setAttribute("message", "Erro: algum dos campos do formulário não foi enviado");
            response.sendRedirect(request.getContextPath() + "/operarios");

        } catch (NumberFormatException e) {
            request.getSession().setAttribute("message", "Erro: campo númerico preenchido com texto");
            response.sendRedirect(request.getContextPath() + "/operarios");
        }
    }
}