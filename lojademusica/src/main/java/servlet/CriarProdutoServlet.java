package servlet;

import dao.ProdutoDAO;
import model.Produto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet("/criar-produto")
public class CriarProdutoServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {

        String produtoNome = req.getParameter("nome");
        String produtoAvaliacao = req.getParameter("avaliacao");
        String produtoDescricao = req.getParameter("descricao");
        BigDecimal produtoPreco = new BigDecimal(req.getParameter("preco"));
        int produtoqtdEstoque = Integer.parseInt(req.getParameter("qtdEstoque"));
        boolean produtoStatus = Boolean.parseBoolean(req.getParameter("status"));
        String produtoImagem = req.getParameter("imagem");
        String produtoId = req.getParameter("id");

        ProdutoDAO produtoDao = new ProdutoDAO();

        Produto produto = new Produto(produtoId, produtoNome, produtoAvaliacao, produtoDescricao, produtoPreco, produtoqtdEstoque, produtoStatus, produtoImagem);

        System.out.println("oi");

        if (produtoId == null || produtoId.isBlank()) {
            System.out.println(produto);
            produtoDao.criarProduto(produto);
        } else {
            //produtoDao.atualizarProduto(produto);
        }

        System.out.println("tchau");

        resp.sendRedirect("encontrar-todos-produtos");
    }
}
