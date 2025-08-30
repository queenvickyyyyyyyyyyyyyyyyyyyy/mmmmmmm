package com.example.datashield.model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.example.datashield.model.dao.DB;
import com.example.datashield.model.entities.Fatura;
import com.example.datashield.model.dao.DbException;

public class FaturaDAOJDBC {

    public void inserir(Fatura f) {
        String sql = "INSERT INTO Fatura (id_contrato, valor_total, data_emissao, status_pagamento) VALUES (?, ?, ?, ?)";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, f.getIdContrato());
            stmt.setDouble(2, f.getValorTotal());
            stmt.setDate(3, f.getDataEmissao());
            stmt.setString(4, f.getStatusPagamento());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<Fatura> listar() {
        List<Fatura> lista = new ArrayList<>();
        String sql = "SELECT * FROM Fatura";

        try (Connection conn = DB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Fatura f = new Fatura();
                f.setIdFatura(rs.getInt("id_fatura"));
                f.setIdContrato(rs.getInt("id_contrato"));
                f.setValorTotal(rs.getDouble("valor_total"));
                f.setDataEmissao(rs.getDate("data_emissao"));
                f.setStatusPagamento(rs.getString("status_pagamento"));
                lista.add(f);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lista;
    }

    public void update(Fatura f) {
        String sql = "UPDATE Fatura SET valor_total = ?, data_emissao = ?, statua_pagamento WHERE id_fatura = ? AND id_contrato = ?";
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);

            statement.setDouble(1, f.getValorTotal());
            statement.setDate(2, f.getDataEmissao());
            statement.setString(3, f.getStatusPagamento());
            statement.setInt(4, f.getIdFatura());
            statement.setInt(4, f.getIdContrato());

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
            statement = conn.prepareStatement("DELETE FROM Fatura WHERE id_fatura = ?");
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

    public Fatura findById(Integer id) {
        String sql = "SELECT * FROM Fatura WHERE id_fatura = ?";
        Fatura f = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                f = new Fatura();
                f.setIdFatura(rs.getInt("id_fatura"));
                f.setIdContrato(rs.getInt("id_contrato"));
                f.setValorTotal(rs.getDouble("valor_total"));
                f.setDataEmissao(rs.getDate("data_emissao"));
                f.setStatusPagamento(rs.getString("status_pagamento"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(statement);
            DB.closeConnection();
        }
        return f;
    }
}
