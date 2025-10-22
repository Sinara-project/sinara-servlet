package com.sinara.servlet;
import java.io.IOException;
import java.sql.Date;
import java.util.*;
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
    private String emailRegex = "[^@]*@.*\\..*";
    private String cnpjRegex = "[0-9]{2}\\.?[0-9]{3}\\.?[0-9]{3}/0001-?[0-9]{2}";
    private String telefoneRegex = "\\(?[0-9]{2}\\)?[ ]*9[0-9]{4}[- ]?[0-9]{4}";
    private String planoRegex = "(MENSAL|ANUAL|GRÁTIS)";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null) {
            listarEmpresas(req, resp);
        } else switch (action) {
            case "listar" -> {
                listarEmpresas(req, resp);    
            }
            case "editar" -> {
                editarEmpresa(req, resp);
            }
            case "excluir" -> {
                excluirEmpresa(req, resp);    
            }
            case "add" -> {
                req.getRequestDispatcher("/WEB-INF/views/addEmpresa.jsp").forward(req, resp);
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
        req.getRequestDispatcher("/WEB-INF/views/empresasAnalise.jsp").forward(req, resp);
    }

    public void editarEmpresa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Map<String, Object> filtro = new HashMap<>();
        filtro.put("id", id);
        List<String> erro = new LinkedList<>();
        List<Empresa> empresa = empDao.buscarPorFiltro(filtro);
        if (!empresa.isEmpty()) {
            Empresa emp = empresa.get(0);
            req.setAttribute("empresa", emp);
            req.getRequestDispatcher("/WEB-INF/views/editarEmpresa.jsp").forward(req, resp);
        } else {
            erro.add("A Empresa não foi encontrada!");
            req.setAttribute("erro", erro);
        }
        req.getRequestDispatcher("/empresas").forward(req, resp);
    }

    public void excluirEmpresa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        List<String> erro = new LinkedList<>();
        if (empDao.deletarEmpresa(id)) {
            req.setAttribute("mensagem", "Empresa deletada com sucesso!");
        } else {
            erro.add("Não foi possível deletar a empresa.");
            req.setAttribute("erro", erro);
        }
        req.getRequestDispatcher("/empresas").forward(req, resp);
    }

    public void adicionarEmpresa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // Atribuir variáveis aos valores da empresa
        String cnpj = req.getParameter("cnpj");
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String ramo = req.getParameter("ramo");
        String telefone = req.getParameter("telefone");
        boolean status = Boolean.getBoolean(req.getParameter("status"));
        String data = req.getParameter("inicioPlano");
        Date inicioPlano = null;
        if (data!=null && !data.isEmpty()) inicioPlano = Date.valueOf(data);
        String plano = req.getParameter("plano");

        List<String> erro = new LinkedList<>();
        boolean errado = false;

        // Checar se existe algum campo vazio
        if (nome == null || ramo == null || inicioPlano == null || plano == null) {
            errado = true;
            erro.add("Existem campos necessários não preenchidos.");
        }

        // Checar sintaxe do CNPJ
        if (!checarSintaxe(cnpj, cnpjRegex)) {
            errado = true;
            erro.add("A sintaxe do CNPJ foi inserida incorretamente.");
        } else cnpj = pegarNumeros(cnpj);

        // Checar sintaxe do endereço de email
        if (!checarSintaxe(email, emailRegex)) {
            errado = true;
            erro.add("A sintaxe do email foi inserida incorretamente.");
        }

        // Checar sintaxe do telefone
        if (!checarSintaxe(telefone, telefoneRegex)) {
            errado = true;
            erro.add("A sintaxe do telefone foi inserida incorretamente.");
        } else telefone = pegarNumeros(telefone);

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
            req.getRequestDispatcher("/empresas?action=listar").forward(req, resp);
        } else {
            req.setAttribute("erro", erro);
            req.getRequestDispatcher("/WEB-INF/views/addEmpresa.jsp").forward(req, resp);
        }
    }

    public void atualizarEmpresa(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nome = req.getParameter("nome");
        String email = req.getParameter("email");
        String ramo = req.getParameter("ramo");
        String telefone = req.getParameter("telefone");
        boolean status = Boolean.getBoolean(req.getParameter("status"));
        Date inicioPlano = Date.valueOf(req.getParameter("inicioPlano"));
        String plano = req.getParameter("plano");

        boolean errado = false;
        List<String> erro = new ArrayList<>();
        String mensagem = null;
        Map<String, Object> filtro = new HashMap<>();
        filtro.put("id", id);
        Empresa empresaOriginal = empDao.buscarPorFiltro(filtro).get(0);

        if (empresaOriginal != null) {
            if (nome!=null && !nome.equals(empresaOriginal.getNome())) empDao.alterarNome(id, nome);
            if (!email.equals(empresaOriginal.getEmail()) && checarSintaxe(telefone, plano)) empDao.alterarEmail(id, email);
            if (ramo != null && !ramo.equals(empresaOriginal.getRamo())) empDao.alterarRamo(id, ramo);
            if (!telefone.equals(empresaOriginal.getTelefone()) && checarSintaxe(telefone, telefoneRegex)) empDao.alterarTelefone(id, pegarNumeros(telefone));
            if (status != empresaOriginal.isStatus()) empDao.alterarAtividade(id, status);
            if (inicioPlano != empresaOriginal.getInicioPlano() && inicioPlano!=null) empDao.alterarInicioPlano(id, inicioPlano);
            if (!plano.equals(empresaOriginal.getPlano()) && checarSintaxe(plano, planoRegex)) empDao.alterarPlano(id, pegarNumeros(plano));
            mensagem = "Dados alterados com sucesso!";
        } else {
            errado = true;
            erro.add("Empresa não encontrada!");
        }
        req.getSession().setAttribute("erro", erro);
        req.getSession().setAttribute("mensagem", mensagem);
        if (errado) req.getRequestDispatcher("/WEB-INF/views/editarEmpresa.jsp").forward(req, resp);
        else req.getRequestDispatcher("/empresas?action=listar").forward(req, resp);
    }

    public String pegarNumeros(String str) {
        Pattern telRegex = Pattern.compile("[0-9]*");
        Matcher matcher = telRegex.matcher(str);
        String r = "";
        while (matcher.find()) {
            r+=matcher.group();
        }
        return r;
    }

    public boolean checarSintaxe(String str, String regex) {
        if (str==null) return false;
        Pattern telRegex = Pattern.compile(regex);
        Matcher matcher = telRegex.matcher(str);
        return matcher.matches();
    }
}
