package com.sinara.servlet;
import com.sinara.dao.OperarioDAO;
import com.sinara.model.Operario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet(name = "Login", value ="/loginOper")
public class ServletLoginOperario extends HttpServlet {
    public void init() {
        System.out.println("Login Servlet inicializado");
    }
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String email = req.getParameter("email");
        String pass = req.getParameter("password");

        // Se o email, ou password forem nulos, mandar uma mensagem de erro
        if (email == null || pass == null || pass.trim().isEmpty() || email.trim().isEmpty()) {
            try {
                req.setAttribute("erro", "Dados necessários não foram preenchidos!");
                req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
            } catch (ServletException exc) {
                exc.printStackTrace();
            }
        } else {
            // Instanciar DAO de operario, criar um map para procurar pelo Usuário
            OperarioDAO operdao = new OperarioDAO();
            Map<String, Object> user = new HashMap<>();
            user.put("email_operario", email);
            user.put("senha", pass);
            // OBS: Adicionar sistema de Hash
            boolean temCookie = false;
            List<Operario> listaOper = operdao.buscarPorFiltro(user);

            // Procurar por todos os cookies, se algum for autorização com o email do usuário, o usuário tem cookie
            for (Cookie cookie : req.getCookies()) {
                if (cookie.getAttribute("autorizado")!=null) {
                    if (cookie.getName().equals("autorizado") && cookie.getValue().equals(email)) {
                        temCookie = true;
                    }
                }
            }
            // Se o usuário possui o cookie, manda-lo para home
            if (temCookie) {
                resp.sendRedirect("/WEB-INF/home.jsp");
                // Se existe algum usuário com essa senha e email, adicionar cookie ao usuário,
                // e redimencionar para home
            } else if (!listaOper.isEmpty()) {
                try {
                    Cookie cookie = new Cookie("autorizado", email);
                    cookie.setHttpOnly(true);
                    cookie.setSecure(true);
                    cookie.setMaxAge(30 * 60);
                    resp.addCookie(cookie);
                    req.getRequestDispatcher("/WEB-INF/home.jsp").forward(req, resp);
                }  catch (ServletException exc) {
                    exc.printStackTrace();
                }
            } else {
                try {
                    // Se não encontrar, mandar mensagem de erro
                    req.setAttribute("erro", "Credenciais inválidas!");
                    req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);
                } catch (ServletException exc) {
                    exc.printStackTrace();
                }
            }
        }
    }
}