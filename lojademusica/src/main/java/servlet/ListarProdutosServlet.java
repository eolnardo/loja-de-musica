package servlet;


import dao.ProdutoDAO;
import model.Produto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/encontrar-todos-produtos")
public class ListarProdutosServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Produto> produtos = new ProdutoDAO().encontrarTodosProdutos();

        req.setAttribute("produtos", produtos);

        req.getRequestDispatcher("listar-produtos.jsp").forward(req, resp);

    }

}