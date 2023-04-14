package dao;

import conexaojdbc.SingleConnection;
import dto.TelefoneUserDTO;
import model.Telefone;
import model.UserPosJava;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserPosDAO {

    private Connection connection;

    public UserPosDAO() {
        connection = SingleConnection.getConnection();
    }

    public List<UserPosJava> obterTodos() {
        List<UserPosJava> users = new ArrayList<>();
        try {
            String sql = "SELECT * FROM userposjava";
            PreparedStatement select = connection.prepareStatement(sql);
            ResultSet resultado = select.executeQuery();
            while (resultado.next()) {
                UserPosJava userPosJava = new UserPosJava();
                userPosJava.setId(resultado.getLong("id"));
                userPosJava.setNome(resultado.getString("nome"));
                userPosJava.setEmail(resultado.getString("email"));
                users.add(userPosJava);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return users;
    }

    public void salvar(UserPosJava userPosJava) {
        try {
            String sql = "INSERT INTO userposjava (nome, email) VALUES (?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, userPosJava.getNome());
            insert.setString(2, userPosJava.getEmail());
            insert.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void salvarTelefone(Telefone telefone) {
        try {
            String sql = "INSERT INTO telefone_user (numero, tipo, id_user) VALUES (?, ?, ?)";
            PreparedStatement insert = connection.prepareStatement(sql);
            insert.setString(1, telefone.getNumero());
            insert.setString(2, telefone.getTipo());
            insert.setLong(3, telefone.getUsuario());
            insert.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public List<TelefoneUserDTO> obterTelefonePorPessoa(Long idUser) {
        List<TelefoneUserDTO> telefonesDto = new ArrayList<>();
        try {
            StringBuilder sql = new StringBuilder();
            sql.append("SELECT t.numero, t.tipo, u.nome");
            sql.append(" FROM userposjava as u");
            sql.append(" JOIN telefone_user as t");
            sql.append(" ON u.id = t.id_user");
            sql.append(" WHERE u.id = ? ");
            PreparedStatement select = connection.prepareStatement(sql.toString());
            select.setLong(1, idUser);
            ResultSet resultado = select.executeQuery();
            while (resultado.next()) {
                TelefoneUserDTO telefoneUserDTO = new TelefoneUserDTO();
                telefoneUserDTO.setNumero(resultado.getString("numero"));
                telefoneUserDTO.setTipo(resultado.getString("tipo"));
                telefoneUserDTO.setNome(resultado.getString("nome"));
                telefonesDto.add(telefoneUserDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return telefonesDto;
    }

    public UserPosJava obterPorId(Long id) {
        UserPosJava userPosJava = new UserPosJava();
        try {
            String sql = "SELECT * FROM userposjava as userpos WHERE userpos.id = ?";
            PreparedStatement select = connection.prepareStatement(sql);
            select.setLong(1, id);
            ResultSet resultSet = select.executeQuery();
            if (resultSet.next()) {
                userPosJava.setId(resultSet.getLong("id"));
                userPosJava.setNome(resultSet.getString("nome"));
                userPosJava.setEmail(resultSet.getString("email"));
            }
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return userPosJava;
    }

    public void atualizar(UserPosJava userPosJava) {
        try {
            String sql = "UPDATE userposjava SET nome = ?, email = ? WHERE id = ? ";
            PreparedStatement update = connection.prepareStatement(sql);
            update.setString(1, userPosJava.getNome());
            update.setString(2, userPosJava.getEmail());
            update.setLong(3, userPosJava.getId());
            update.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    public void excluir(Long id) {
        try {
            String sql = "DELETE FROM userposjava WHERE id = ?";
            PreparedStatement delete = connection.prepareStatement(sql);
            delete.setLong(1, id);
            delete.executeUpdate();
            connection.commit();
        } catch (Exception e) {
            e.printStackTrace();
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

}
