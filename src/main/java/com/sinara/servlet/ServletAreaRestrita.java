package com.sinara.servlet;

import com.sinara.dao.AdministradorDAO;
import com.sinara.model.Administrador;
import com.sinara.utils.SenhaUtils;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.*;

@WebServlet(name = "Área Restrita", value = "/arearestrita")
public class ServletAreaRestrita extends HttpServlet {

    private List<String> erros = new LinkedList<>();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (ehLogado(req)) {
            String page = req.getParameter("page");
            if (page==null) page="analise";
            switch (page) {
                case "funcionarios" -> {
                    req.getRequestDispatcher("/operarios").forward(req, resp);
                }
                case "analise" -> {
                    req.getRequestDispatcher("/WEB-INF/views/analise.jsp").forward(req, resp);
                }
                case "alertas" -> {
                    req.getRequestDispatcher("/WEB-INF/views/alertas.jsp").forward(req, resp);
                }
                case "empresas" -> {
                    req.getRequestDispatcher("/empresas").forward(req, resp);
                }
                case "admin" -> {
                    req.getRequestDispatcher("/administracao").forward(req, resp);
                }
            }
            erros.clear();
        } else {
            req.setAttribute("erros", erros);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
            erros.clear();
        }
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action==null) action="login";
        switch (action) {
            case "login" -> verificarLogin(req, resp);
            case "logout" -> logOut(req, resp);
        }
    }
    public void verificarLogin(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String email = req.getParameter("email");
            String senha = req.getParameter("senha");
            if (verificarCredenciais(email, senha))
                addLogin(resp, buscarAdmin(email, senha), req.getSession());
        } catch (NullPointerException exc) {
            erros.add("* Campos necessários não preenchidos!");
        }  catch (IllegalArgumentException exc) {
            erros.add("* Campos preenchidos incorretamente!");
        } catch (IndexOutOfBoundsException exc) {
            erros.add("* Credenciais incorretas");
        } finally {
            if
                (erros.isEmpty()) resp.sendRedirect(req.getContextPath() + "/arearestrita");
            else
                resp.sendRedirect(req.getContextPath()+"/arearestrita?action=login");
        }
    }
    public void logOut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies = req.getCookies();
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("authAreaRestrita")) {
                cookie.setValue("");
                cookie.setMaxAge(0);
                cookie.setPath("/");
                resp.addCookie(cookie);
            }
        }
        req.getSession(false).invalidate();
        resp.sendRedirect(req.getContextPath()+"/arearestrita");
    }
    private void addLogin(HttpServletResponse resp, Administrador adm, HttpSession session) throws NullPointerException, IllegalArgumentException {
        AdministradorDAO admDao = new AdministradorDAO();
        String token = UUID.randomUUID().toString();
        Cookie cookie = new Cookie("authAreaRestrita", adm.getEmail()+":"+token);
        cookie.setMaxAge(60*60*24*30); // Definido em segundos
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        admDao.inserirUUID(adm.getId(), token);
        resp.addCookie(cookie);
        session.setAttribute("user", adm.getNome());
    }
    private boolean verificarCredenciais(String email, String senha) throws IndexOutOfBoundsException, NullPointerException, IllegalArgumentException {
        if (email.isBlank() || senha.isBlank()) throw new NullPointerException();
        Administrador admin = buscarAdmin(email, senha);
        return (SenhaUtils.verificarSenha(senha, admin.getSenha()));
    }
    private Administrador buscarAdmin(String email, String senha) throws IndexOutOfBoundsException, NullPointerException, IllegalArgumentException {
        if (email.isBlank() || senha.isBlank()) throw new NullPointerException();
        AdministradorDAO admDao = new AdministradorDAO();
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("email_admin", email);
        filtros.put("id_empresa", 11);
        return admDao.buscarPorFiltro(filtros).get(0);
    }
    private Administrador buscarUUID(String email, String token) throws IndexOutOfBoundsException, NullPointerException, IllegalArgumentException {
        if (email.isBlank() || token.isBlank()) throw new NullPointerException();
        AdministradorDAO admDao = new AdministradorDAO();
        Map<String, Object> filtros = new HashMap<>();
        filtros.put("email_admin", email);
        filtros.put("token_administracao", token);
        filtros.put("id_empresa", 11);
        return (admDao.buscarPorFiltro(filtros).get(0));
    }
    private boolean ehLogado(HttpServletRequest req) throws NullPointerException, IllegalArgumentException, ServletException, IOException {
        // Procurar por todos os cookies, se algum for autorização com o email do usuário, o usuário tem cookie
        try {
            Cookie[] cookies = req.getCookies();

            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("authAreaRestrita")) {
                    String[] credenciais = cookie.getValue().split(":");
                    String email = credenciais[0];
                    String token = credenciais[1];
                    return buscarUUID(email, token) != null;
                }
            }
        } catch (IndexOutOfBoundsException exc) {
            erros.add("* UUID não corresponde a nenhum usuário");
        }
        return false;
    }
}