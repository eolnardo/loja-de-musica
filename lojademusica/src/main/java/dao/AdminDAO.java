package dao;

import model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class administradorDao {
    public void criarAdministrador(Admin adm) {
        String SQL = "INSERT INTO ADM (NOME,EMAIL, SENHA, STATUS, TELEFONE, GRUPO) VALUES (?,?,?,?,?,?)";

        try {

            Connection connection = ConnectionPoolConfig.getConnection();

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);

            ResultSet rs = preparedStatement.getGeneratedKeys();

            preparedStatement.setString(1, adm.getNome());

            ;
            preparedStatement.setString(2, adm.getEmail());
            preparedStatement.setString(3, adm.getSenha());
            preparedStatement.setString(4, adm.getStatus());
            preparedStatement.setString(5, adm.getTelefone());
            preparedStatement.setString(6, adm.getGrupo());

            preparedStatement.execute();


        } catch (SQLException e) {


            System.out.println("fail in database connection 1");
            throw new RuntimeException(e);

        }
    }

    public Admin exibirADM(String id) {
        String SQL = "SELECT * FROM ADM WHERE ADMID = ?";
        ADM adm = null;

        try {
            Connection connection = ConnectionPoolConfig.getConnection();

            System.out.println("Sucesso na conexão com o banco de dados");

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            preparedStatement.setString(1, id);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String admNome = resultSet.getString("nome");

                String admEmail = resultSet.getString("email");
                String admSenha = resultSet.getString("senha");
                String admStatus = resultSet.getString("status");
                String admTelefone = resultSet.getString("telefone");
                String admId = resultSet.getString("admId");
                String admGrupo = resultSet.getString("grupo");

                adm = new Adm(admNome,admEmail, admSenha, admStatus, admTelefone, admId, admGrupo);
            }

            System.out.println("Sucesso na consulta ao adm");

        } catch (SQLException e) {
            System.err.println("Erro na conexão com o banco de dados: " + e.getMessage());
        }
        if (adm == null) {
            adm = new Adm();
        }

    }
}
