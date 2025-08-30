package com.example.datashield.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.datashield.model.dao.DB;
import com.example.datashield.model.entities.Contrato;
import com.example.datashield.model.dao.DbException;

public class ContratoDAOJDBC {

    public void inserir(Contrato contrato) {
        String sql = "INSERT INTO Contrato (id_cliente, data_inicio, data_fim, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, contrato.getIdCliente());
            stmt.setDate(2, contrato.getDataInicio());
            stmt.setDate(3, contrato.getDataFim());
            stmt.setString(4, contrato.getStatus());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<Contrato> listar() {
        List<Contrato> contratos = new ArrayList<>();
        String sql = "SELECT * FROM Contrato";

        try (Connection conn = DB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Contrato c = new Contrato();
                c.setIdContrato(rs.getInt("id_contrato"));
                c.setIdCliente(rs.getInt("id_cliente"));
                c.setDataInicio(rs.getDate("data_inicio"));
                c.setDataFim(rs.getDate("data_fim"));
                c.setStatus(rs.getString("status"));
                contratos.add(c);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return contratos;
    }

    public void update(Contrato contrato) {
        String sql = "UPDATE Contrato SET id_cliente = ?, data_inicio = ?, data_fim = ?, status = ? WHERE id_contrato = ?";
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);

            statement.setInt(1, contrato.getIdCliente());
            statement.setDate(2, contrato.getDataInicio());
            statement.setDate(3, contrato.getDataFim());
            statement.setString(4, contrato.getStatus());
            statement.setInt(5, contrato.getIdContrato());

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
            statement = conn.prepareStatement("DELETE FROM Contrato WHERE id_contrato = ?");
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

    public Contrato findById(Integer id) {
        String sql = "SELECT * FROM Contrato WHERE id_contrato = ?";
        Contrato contrato = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                contrato = new Contrato();
                contrato.setIdContrato(rs.getInt("id_contrato"));
                contrato.setIdCliente(rs.getInt("id_cliente"));
                contrato.setDataInicio(rs.getDate("data_inicio"));
                contrato.setDataFim(rs.getDate("data_fim"));
                contrato.setStatus(rs.getString("status"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(statement);
            DB.closeConnection();
        }
        return contrato;
    }
}