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
import com.example.datashield.model.entities.Relatorio;

public class RelatorioDAOJDBC {

    public void inserir(Relatorio r) {
        String sql = "INSERT INTO Relatorio (id_contrato, data_entrega, tipo, descricao_resultado) VALUES (?, ?, ?, ?)";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, r.getIdContrato());
            stmt.setDate(2, r.getDataEntrega());
            stmt.setString(3, r.getTipo());
            stmt.setString(4, r.getDescricaoResultado());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<Relatorio> listar() {
        List<Relatorio> lista = new ArrayList<>();
        String sql = "SELECT * FROM Relatorio";

        try (Connection conn = DB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Relatorio r = new Relatorio();
                r.setIdRelatorio(rs.getInt("id_relatorio"));
                r.setIdContrato(rs.getInt("id_contrato"));
                r.setDataEntrega(rs.getDate("data_entrega"));
                r.setTipo(rs.getString("tipo"));
                r.setDescricaoResultado(rs.getString("descricao_resultado"));
                lista.add(r);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lista;
    }

    public void update(Relatorio r) {
        String sql = "UPDATE Relatorio SET data_entrega = ?, tipo = ?, descricao_resultado WHERE id_relatorio = ? AND id_contrato = ?";
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);

            statement.setDate(1, r.getDataEntrega());
            statement.setString(2, r.getTipo());
            statement.setString(3, r.getDescricaoResultado());
            statement.setInt(4, r.getIdRelatorio());
            statement.setInt(4, r.getIdContrato());

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
            statement = conn.prepareStatement("DELETE FROM Relatorio WHERE id_relatorio = ?");
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

    public Relatorio findById(Integer id) {
        String sql = "SELECT * FROM Fatura WHERE id_fatura = ?";
        Relatorio r = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                r = new Relatorio();
                r.setIdRelatorio(rs.getInt("id_relatorio"));
                r.setIdContrato(rs.getInt("id_contrato"));
                r.setDataEntrega(rs.getDate("data_entrega"));
                r.setTipo(rs.getString("tipo"));
                r.setDescricaoResultado(rs.getString("descricao_resultado"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(statement);
            DB.closeConnection();
        }
        return r;
    }
}