package com.example.datashield.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.datashield.model.dao.DB;
import com.example.datashield.model.dao.DbException;
import com.example.datashield.model.entities.Servico;

public class ServicoDAOJDBC {

    public void inserir(Servico servico) {
        String sql = "INSERT INTO Servico (nome_servico, descricao, valor_base) VALUES (?, ?, ?)";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, servico.getNomeServico());
            stmt.setString(2, servico.getDescricao());
            stmt.setDouble(3, servico.getValorBase());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<Servico> listar() {
        List<Servico> servicos = new ArrayList<>();
        String sql = "SELECT * FROM Servico";

        try (Connection conn = DB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Servico s = new Servico();
                s.setIdServico(rs.getInt("id_servico"));
                s.setNomeServico(rs.getString("nome_servico"));
                s.setDescricao(rs.getString("descricao"));
                s.setValorBase(rs.getDouble("valor_base"));
                servicos.add(s);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return servicos;
    }

    public void update(Servico s) {
        String sql = "UPDATE Servico SET nome_servico = ?, descricao = ?, valor_base = ? WHERE id_servico = ?";
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);

            statement.setString(1, s.getNomeServico());
            statement.setString(2, s.getDescricao());
            statement.setDouble(3, s.getValorBase());
            statement.setInt(4, s.getIdServico());

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
            statement = conn.prepareStatement("DELETE FROM Servico WHERE id_servico = ?");
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

    public Servico findById(Integer id) {
        String sql = "SELECT * FROM Servico WHERE id_servico = ?";
        Servico s = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                s = new Servico();
                s.setIdServico(rs.getInt("id_servico"));
                s.setNomeServico(rs.getString("nome_servico"));
                s.setDescricao(rs.getString("descricao"));
                s.setValorBase(rs.getDouble("valor_base"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(statement);
            DB.closeConnection();
        }
        return s;
    }
}