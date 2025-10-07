package com.sinara.servlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Login", value ="/loginAdm")
public class ServletLoginAdm extends HttpServlet {
    public void init() {
        System.out.println("Login Servlet inicializado");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        // Se o email, ou password forem nulos, mandar uma mensagem de erro
        if (email == null || pass == null || pass.trim().isEmpty() || email.trim().isEmpty()) {
            req.setAttribute("erro", "Erro: Dados necessários não foram preenchidos!");
            resp.sendRedirect(req.getContextPath() + "/login.jsp");
        } else {
            // Instanciar DAO de admin, criar um map para procurar pelo Usuário
            AdministradorDAO admindao = new AdministradorDAO();
            Map<String, Object> user = new HashMap<>();
            user.put("email_admin", email);
            user.put("senha", pass);
            // OBS: Adicionar sistema de Hash
            boolean temCookie = false;
            List<Administrador> listaAdmin = admindao.buscarPorFiltro(user);

            // Procurar por todos os cookies, se algum for autorização com o email do usuário, o usuário tem cookie
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getAttribute("autorizado").equals(email)) temCookie = true;
            }
            // Se o usuário possui o cookie, manda-lo para home
            if (temCookie) {
                resp.sendRedirect(req.getContextPath() + "/home");
                // Se existe algum usuário com essa senha e email, adicionar cookie ao usuário,
                // e redimencionar para home
            } else if (!listaAdmin.isEmpty()) {
                Cookie cookie = new Cookie("autorizado", email);
                cookie.setHttpOnly(true);
                cookie.setSecure(true);
                cookie.setMaxAge(30 * 60);
                resp.addCookie(cookie);
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                // Se não encontrar, mandar mensagem de erro
                req.setAttribute("erro", "Erro: Credenciais inválidas!");
                req.getRequestDispatcher("/WEB-INF/login.jsp");
            }
        }
    }
}