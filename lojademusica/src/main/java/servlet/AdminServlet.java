package servlet;

import dao.administradorDao;
import model.Admin;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String admNome = req.getParameter("nome");
        String admEmail = req.getParameter("email");
        String admSenha = req.getParameter("senha");
        String admTelfone = req.getParameter("telefone");
        String admId = req.getParameter("admId");
        String admStatus = req.getParameter("status");
        String admGrupo = req.getParameter("grupo");

        administradorDao administradorDao = new administradorDao();
        Admin admin = new Admin(nome, email, senha, telfone, admId, status, grupo);

        if (admId == null) {
            administradorDao.criarAdministrador(admin);

        }

    }
}
