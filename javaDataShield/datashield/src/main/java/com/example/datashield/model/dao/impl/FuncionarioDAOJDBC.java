package com.example.datashield.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.datashield.model.entities.Funcionario;
import com.example.datashield.model.dao.DB;
import com.example.datashield.model.dao.DbException;

public class FuncionarioDAOJDBC {

    public void inserir(Funcionario f) {
        String sql = "INSERT INTO Funcionario (nome, cargo, salario) VALUES (?, ?, ?)";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, f.getNome());
            stmt.setString(2, f.getCargo());
            stmt.setDouble(3, f.getSalario());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<Funcionario> listar() {
        List<Funcionario> funcionarios = new ArrayList<>();
        String sql = "SELECT * FROM Funcionario";

        try (Connection conn = DB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Funcionario f = new Funcionario();
                f.setIdFuncionario(rs.getInt("id_funcionario"));
                f.setNome(rs.getString("nome"));
                f.setCargo(rs.getString("cargo"));
                f.setSalario(rs.getDouble("salario"));
                funcionarios.add(f);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return funcionarios;
    }

    public void update(Funcionario funcionario) {
        String sql = "UPDATE Funcionario SET nome = ?, cargo = ?, salario WHERE id_funcionario = ?";
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);

            statement.setString(1, funcionario.getNome());
            statement.setString(2, funcionario.getCargo());
            statement.setDouble(3, funcionario.getSalario());
            statement.setInt(4, funcionario.getIdFuncionario());

            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }

    public void deleteById(Integer id) {
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement("DELETE FROM Funcionario WHERE id_funcionario = ?");
            statement.setInt(1, id);

            statement.executeUpdate();
        }
        catch(SQLException e){
            throw new DbException(e.getMessage());
        }
        finally {
            DB.closeStatement(statement);
            DB.closeConnection();
        }
    }

    public Funcionario findById(Integer id) {
        String sql = "SELECT * FROM Funcionario WHERE id_funcionario = ?";
        Funcionario funcionario = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                funcionario = new Funcionario();
                funcionario.setIdFuncionario(rs.getInt("id_funcionario"));
                funcionario.setNome(rs.getString("nome"));
                funcionario.setCargo(rs.getString("cargo"));
                funcionario.setSalario(rs.getDouble("salario"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(statement);
            DB.closeConnection();
        }
        return funcionario;
    }
}
