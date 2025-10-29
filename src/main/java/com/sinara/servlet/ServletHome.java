package com.sinara.servlet;

import com.sinara.dao.AdministradorDAO;
import com.sinara.dao.EmpresaDAO;
import com.sinara.model.Administrador;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet(name = "Home", value = "/home")
public class ServletHome extends HttpServlet {

    private List<String> erros = new LinkedList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ehLogado(req)) {
            req.getRequestDispatcher("/WEB-INF/views/homeAdm.jsp").forward(req, resp);
            erros.clear();
        } else {
            String action = req.getParameter("action");
            if (action==null) action = "login";

            if (action.equals("cadastro")) {
                    EmpresaDAO empDao = new EmpresaDAO();
                    req.setAttribute("empresas", empDao.listarEmpresas());
                    req.setAttribute("erros", erros);
                    req.getRequestDispatcher("/WEB-INF/views/cadastro.jsp").forward(req, resp);
                    erros.clear();
            } else {
                req.setAttribute("erros", erros);
                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
                erros.clear();
            }
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        switch (action) {
            case "cadastro" -> cadastrarAdmin(req, resp);
            case "login" -> verificarLogin(req, resp);
            case "logout" -> logOut(req, resp);
        }
    }
    private void cadastrarAdmin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            AdministradorDAO admDao = new AdministradorDAO();
            String idEmpresa = req.getParameter("cnpjEmpresa");
            Administrador adm = new Administrador(req.getParameter("nome"), req.getParameter("email"),
                    req.getParameter("senha"), req.getParameter("cpf"), req.getParameter("cargo"),
                    Integer.parseInt(idEmpresa));
            admDao.inserirAdministrador(adm);
            HttpSession session = req.getSession();
            addLogin(resp, adm, session);
            session.setAttribute("user", adm.getNome());
        } catch (NullPointerException exc) {
            erros.add("* Campos necessários não preenchidos!");
        } catch (IllegalArgumentException exc) {
            erros.add("* Campos preenchidos incorretamente!");
        } catch (SQLException exc) {
            erros.add("* Erro no banco de dados!");
        } finally {
            if (erros.isEmpty()) resp.sendRedirect(req.getContextPath() + "/home?action=login");
            else {
                resp.sendRedirect(req.getContextPath()+"/home?action=cadastro");
            }
        }
    }
    public void verificarLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            Administrador adm = buscarAdmin(email, senha);
            if (adm != null) addLogin(resp, adm, req.getSession());
        } catch (NullPointerException exc) {
            erros.add("* Campos necessários não preenchidos!");
        }  catch (IllegalArgumentException exc) {
            erros.add("* Campos preenchidos incorretamente!");
        } catch (IndexOutOfBoundsException exc) {
            erros.add("* Credenciais incorretas");
        } finally {
            if (erros.isEmpty()) resp.sendRedirect(req.getContextPath() + "/home");
            else {
                resp.sendRedirect(req.getContextPath()+"/home?action=login");
            }
        }
    }
    public void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth")) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        }
        req.getSession(false).invalidate();
        resp.sendRedirect(req.getContextPath()+"/home");
    }

    private void addLogin(HttpServletResponse resp, Administrador adm, HttpSession session) throws NullPointerException, IllegalArgumentException {
        AdministradorDAO admDao = new AdministradorDAO();
        String token = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("auth", adm.getEmail()+":"+token);
        cookie.setMaxAge(60*60*24*30); // Definido em segundos
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        admDao.inserirUUID(adm.getId(), token);
        resp.addCookie(cookie);
        session.setAttribute("user", adm.getNome());
    }
    private Administrador buscarAdmin(String email, String senha) throws IndexOutOfBoundsException, NullPointerException, IllegalArgumentException {
        if (email.isBlank() || senha.isBlank()) throw new NullPointerException();
        AdministradorDAO admDao = new AdministradorDAO();
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("email_admin", email);
        filtros.put("senha", senha);
        return (admDao.buscarPorFiltro(filtros).get(0));
    }

    private Administrador buscarAdmin(String email, String senha, String campo) throws IndexOutOfBoundsException, NullPointerException, IllegalArgumentException {
        if (email.isBlank() || senha.isBlank()) throw new NullPointerException();
        AdministradorDAO admDao = new AdministradorDAO();
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("email_admin", email);
        filtros.put(campo, senha);
        return (admDao.buscarPorFiltro(filtros).get(0));
    }
    private boolean ehLogado(HttpServletRequest req) throws NullPointerException, IllegalArgumentException, ServletException, IOException {
        // Procurar por todos os cookies, se algum for autorização com o email do usuário, o usuário tem cookie
        try {
            Cookie[] cookies = req.getCookies();

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("auth")) {
                    String[] credenciais = cookie.getValue().split(":");
                    String email = credenciais[0];
                    String token = credenciais[1];
                    return buscarAdmin(email, token, "token_administracao") != null;
                }
            }
        } catch (IndexOutOfBoundsException exc) {
            erros.add("* UUID não corresponde a nenhum usuário");
        }
        return false;
    }
}