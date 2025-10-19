package com.sinara.servlet;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sinara.dao.EmpresaDAO;
import com.sinara.model.Empresa;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;   

@WebServlet(name = "Empresas", value = "/empresas")
public class EmpresaServlet extends HttpServlet {
    private EmpresaDAO empDao = new EmpresaDAO();
    private String emailRegex = "";
    private String cnpjRegex = "[0-9]{2}\\.?[0-9]{3}\\.?[0-9]\\/0001-?[0-9]{2}";
    private String telefoneRegex = "";
    private String planoRegex = "(MENSAL|ANUAL|GRÁTIS)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "listar" -> {
                listarEmpresas(req, resp);    
            }
            case "editar" -> {
                editarEmpresa(req, resp);    
            }
            case "excluir" -> {
                excluirEmpresa(req, resp);    
            }
            default -> {
                listarEmpresas(req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "adicionar" -> {
                adicionarEmpresa(req, resp);    
            }
            case "atualizar" -> {
                atualizarEmpresa(req, resp);    
            }
            default -> {
                listarEmpresas(req, resp);
            }
        }
    }

    public void listarEmpresas(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Empresa> empresas = empDao.listarEmpresas();
        req.setAttribute("empresas", empresas);
        req.getRequestDispatcher("/WEB-INF/views/EmpresasAnalise.jsp").forward(req, resp);
    }

    public void editarEmpresa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Map<String, Object> filtro = new HashMap<>(); 
        filtro.put("id", id);
        Empresa emp = empDao.buscarPorFiltro(filtro).get(0);
        if (emp != null) {
            req.setAttribute("empresa", emp);
            req.getRequestDispatcher("/WEB-INF/views/editarEmpresa.jsp").forward(req, resp);
        } else {
            req.setAttribute("erro", "A Empresa não foi encontrada!");
        }
    }

    public void excluirEmpresa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        if (empDao.deletarEmpresa(id)) {
            req.setAttribute("mensagem", "Empresa deletada com sucesso!");
        } else {
            req.setAttribute("erro", "Não foi possível deletar a empresa.");
        }
        req.getRequestDispatcher("/WEB-INF/views/EmpresasAnalise.jsp").forward(req, resp);
    }

    public void adicionarEmpresa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Atribuir variáveis aos valores da empresa
        String cnpj = req.getParameter("cnpj");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String ramo = req.getParameter("ramo");
        String telefone = req.getParameter("telefone");
        boolean status = Boolean.getBoolean(req.getParameter("status"));
        Date inicioPlano = Date.valueOf(req.getParameter("inicioPlano"));
        String plano = req.getParameter("plano");

        List<String> erro = new LinkedList<>();
        boolean errado = false;

        // Checar se existe algum campo vazio
        if (nome == null || ramo == null || inicioPlano == null || plano == null) {
            errado = true;
            erro.add("Existem campos necessários não preenchidos.");
        }

        // Checar sintaxe do CNPJ
        if (checarSintaxe(cnpj, cnpjRegex)) {
            errado = true;
            erro.add("A sintaxe do CNPJ foi inserida incorretamente.");
        }

        // Checar sintaxe do endereço de email
        if (checarSintaxe(email, emailRegex)) {
            errado = true;
            erro.add("A sintaxe do email foi inserida incorretamente.");
        }

        // Checar sintaxe do telefone
        if (checarSintaxe(telefone, telefoneRegex)) {
            errado = true;
            erro.add("A sintaxe do telefone foi inserida incorretamente.");
        }

        // Se não há erro de sintaxe, adicionar Empresa
        if (!errado) {
            // Checar se mesmo com sintaxe correta, há erro
            if (!empDao.inserirEmpresa(new Empresa(cnpj, nome, email, ramo, telefone, 
            status, inicioPlano, plano))) {
                errado = true;
                erro.add(0, "Não foi possível registrar a Empresa.");
            }
        }

        // Caso algum erro tenha ocorrido
        if (!errado) {
            req.setAttribute("mensagem", "Empresa registrada com sucesso!");
            req.getRequestDispatcher("/WEB-INF/views/EmpresasAnalise.jsp").forward(req, resp);
        } else {
            req.setAttribute("erro", erro);
            req.getRequestDispatcher("/WEB-INF/views/editarEmpresa.jsp").forward(req, resp);
        }
    }

    public void atualizarEmpresa(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String ramo = req.getParameter("ramo");
        String telefone = req.getParameter("telefone");
        boolean status = Boolean.getBoolean(req.getParameter("status"));
        Date inicioPlano = Date.valueOf(req.getParameter("inicioPlano"));
        String plano = req.getParameter("plano");

        Map<String, Object> filtro = new HashMap<>();
        Empresa empresaOriginal = empDao.buscarPorFiltro(filtro).get(0);

        if (empresaOriginal != null) {
            if (nome != empresaOriginal.getNome() && nome!=null) empDao.alterarNome(id, nome);
            if (email != empresaOriginal.getEmail() && checarSintaxe(telefone, plano)) empDao.alterarEmail(id, email);
            if (ramo != empresaOriginal.getRamo() && ramo!=null) empDao.alterarRamo(id, ramo);
            if (telefone != empresaOriginal.getTelefone() && checarSintaxe(telefone, telefoneRegex)) empDao.alterarTelefone(id, telefone);
            if (status != empresaOriginal.isStatus()) empDao.alterarAtividade(id, status);
            if (inicioPlano != empresaOriginal.getInicioPlano() && inicioPlano!=null) empDao.alterarInicioPlano(id, inicioPlano);
            if (plano != empresaOriginal.getPlano() && checarSintaxe(plano, planoRegex)) empDao.alterarPlano(id, plano);
        }
    }

    public boolean checarSintaxe(String str, String regex) {
        Pattern telRegex = Pattern.compile(regex);
        Matcher matcher = telRegex.matcher(str);
        return str!=null && matcher.matches();
    }
}
