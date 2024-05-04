package dao;

import model.Produto;
import servlet.config.ConnectionPoolConfig;

import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ProdutoDAO {
    public void criarProduto(Produto produto) {
        String SQL = "INSERT INTO Produto (nome, avaliacao, descricao, preco, qtdEstoque, status, imagem) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            preparedStatement.setString(1, produto.getNome());
            preparedStatement.setString(2, produto.getAvaliacao());
            preparedStatement.setString(3, produto.getDescricao());
            preparedStatement.setBigDecimal(4, produto.getPreco());
            preparedStatement.setInt(5, produto.getQtdEstoque());
            preparedStatement.setBoolean(6, true);
            preparedStatement.setString(7, produto.getImagem());

            preparedStatement.execute();


        } catch (SQLException e) {


            System.out.println("fail in database connection 1");
            throw new RuntimeException(e);

        }
    }

    public List<Produto> encontrarTodosProdutos() {

        String SQL = "SELECT * FROM PRODUTO";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet resultSet = preparedStatement.executeQuery();

            List<Produto> produtos = new ArrayList<>();

            while (resultSet.next()) {

                String produtoId = resultSet.getString("id");
                String produtoNome = resultSet.getString("nome");
                int produtoQtdEstoque = resultSet.getInt("qtdEstoque");
                BigDecimal produtoValor = resultSet.getBigDecimal("preco");
                boolean produtoStatus = resultSet.getBoolean("status");

                Produto produto = new Produto(produtoId, produtoNome, produtoQtdEstoque, produtoValor, produtoStatus);

                produtos.add(produto);

                System.out.println(produtos.get(0).getNome() + produtos.get(0).getId() + produtos.get(0).getStatus() + produtos.get(0).getPreco());


            }

            System.out.println(produtos.get(0).getNome() + produtos.get(0).getId() + produtos.get(0).getStatus() + produtos.get(0).getPreco());
            System.out.println(produtos.get(1));

            System.out.println("success in select * produto");

            connection.close();

            return produtos;

        } catch (Exception e) {

            System.out.println("fail in database connection");

            return Collections.emptyList();
        }
    }

    public Produto encontrarProdutoPorId(String id) {
        String SQL = "SELECT * FROM Produto WHERE id = ?";
        Produto produto = null;

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setString(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                produto = new Produto(
                        resultSet.getString("id"),
                        resultSet.getString("nome"),
                        resultSet.getInt("qtdEstoque"),
                        resultSet.getBigDecimal("preco"),
                        resultSet.getBoolean("status")
                );
            }
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao encontrar produto por ID", e);
        }

        return produto;
    }

    public void atualizarProduto(Produto produto) {
        String SQL = "UPDATE Produto SET status = ? WHERE id = ?";

        try (Connection connection = ConnectionPoolConfig.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SQL)) {
            preparedStatement.setBoolean(1, produto.getStatus());
            preparedStatement.setString(2, produto.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao atualizar produto", e);
        }
    }

}
