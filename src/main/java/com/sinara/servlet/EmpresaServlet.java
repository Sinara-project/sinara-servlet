package com.sinara.servlet;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.sinara.dao.EmpresaDAO;
import com.sinara.model.Empresa;
import com.sinara.model.VisaoGeral;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "Empresas", value = "/empresas")
public class EmpresaServlet extends HttpServlet {
    private final EmpresaDAO empDao = new EmpresaDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> erros = new LinkedList<>();
        if (ServletAreaRestrita.ehLogado(req, resp, erros)) {
            String action = req.getParameter("action");
            if (action == null) {
                List<Empresa> empresas = empDao.listarEmpresas();
                req.setAttribute("empresas", empresas);
                req.getRequestDispatcher("/WEB-INF/views/config_industria.jsp").forward(req, resp);
            } else switch (action) {
                case "visaoGeral" -> {
                    visaoGeral(req, resp);
                }
                case "listar" -> {
                    listarEmpresas(req, resp);
                }
                default -> {
                    List<Empresa> empresas = empDao.listarEmpresas();
                    req.setAttribute("empresas", empresas);
                    req.getRequestDispatcher("/WEB-INF/views/config_industria.jsp");
                }
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "atualizar" -> {
                atualizarEmpresa(req, resp);
            }
            case "adicionar" -> {
                adicionarEmpresa(req, resp);
            } case "excluir" -> {
                excluirEmpresa(req, resp);
            }
            default -> {
                listarEmpresas(req, resp);
            }
        }
    }

    public void listarEmpresas(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Empresa> empresas = empDao.listarEmpresas();
        req.setAttribute("empresas", empresas);
        req.getRequestDispatcher("/WEB-INF/views/empresa.jsp").forward(req, resp);
    }
    public void visaoGeral(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<VisaoGeral> listaVisoes = empDao.listarVisoes();
        req.setAttribute("empresas", listaVisoes);
        req.getRequestDispatcher("/WEB-INF/views/visaoGeral.jsp").forward(req, resp);
    }

    public void excluirEmpresa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<String> erro = new LinkedList<>();
        if (empDao.deletarEmpresa(id) == 1) {
            req.setAttribute("mensagem", "Empresa deletada com sucesso!");
        } else {
            erro.add("Não foi possível deletar a empresa.");
            req.setAttribute("erro", erro);
        }
        req.getRequestDispatcher("/empresas?action=listar").forward(req, resp);
    }
    public void adicionarEmpresa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> erros = new LinkedList<>();
        String dataStr = req.getParameter("inicioPlano");
        Empresa empresa = new Empresa(req.getParameter("cnpj"), req.getParameter("nome"), req.getParameter("email"),
                req.getParameter("ramo"), req.getParameter("telefone"), req.getParameter("status"),
                dataStr, req.getParameter("plano"), erros);

        // Caso algum erro tenha ocorrido
        if (erros.isEmpty()) {
            switch (empDao.inserirEmpresa(empresa)) {
                case 0 -> erros.add("Não foi possível registrar a empresa...");
                case 2 -> erros.add("Erro no banco de dados!");
            }
            if (erros.isEmpty()) {
                req.setAttribute("mensagem", "Empresa registrada com sucesso!");
                req.getRequestDispatcher("/empresas?action=listar").forward(req, resp);
            } else {
                req.setAttribute("empresa", empresa);
                req.setAttribute("erro", erros);
                req.getRequestDispatcher("/empresas?action=add").forward(req, resp);
            }
        } else {
            req.setAttribute("empresa", empresa);
            req.setAttribute("erro", erros);
            req.getRequestDispatcher("/WEB-INF/views/empresa.jsp").forward(req, resp);
        }
    }
    public void atualizarEmpresa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<String> erros = new LinkedList<>();
        Empresa empresa = new Empresa(Integer.parseInt(req.getParameter("id")), req.getParameter("nome"), req.getParameter("email"),
                req.getParameter("ramo"), req.getParameter("telefone"), req.getParameter("status"), erros);

        if (empDao.alterarEmpresa(empresa, erros)) { // Se não há erros
            req.setAttribute("mensagem", "Dados alterados com sucesso!");
            req.getRequestDispatcher("/empresas?action=listar").forward(req, resp);
        } else { // Se há erros
            req.setAttribute("erro", erros);
            List<Empresa> empresas = new LinkedList<>();
            empresas.add(empresa);
            req.setAttribute("empresa", empresas);
            listarEmpresas(req, resp);
        }
    }
}