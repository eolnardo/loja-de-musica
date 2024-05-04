package servlet;

import dao.ProdutoDAO;
import model.Produto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/alterar-status-produto")
public class AlterarStatusProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        ProdutoDAO produtoDAO = new ProdutoDAO();

        Produto produto = produtoDAO.encontrarProdutoPorId(id);

        produto.setStatus(!produto.getStatus()); // Inverte o status

        produtoDAO.atualizarProduto(produto);

        resp.sendRedirect("encontrar-todos-produtos");
    }
}