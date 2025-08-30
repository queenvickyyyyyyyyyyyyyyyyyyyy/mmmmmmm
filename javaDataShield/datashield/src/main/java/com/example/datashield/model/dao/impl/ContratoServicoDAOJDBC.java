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
import com.example.datashield.model.entities.ContratoServico;

public class ContratoServicoDAOJDBC {

    public void inserir(ContratoServico cs) {
        String sql = "INSERT INTO Contrato_Servico (id_contrato, id_servico, valor_negociado, prazo_entrega) VALUES (?, ?, ?, ?)";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cs.getIdContrato());
            stmt.setInt(2, cs.getIdServico());
            stmt.setDouble(3, cs.getValorNegociado());
            stmt.setDate(4, cs.getPrazoEntrega());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<ContratoServico> listar() {
        List<ContratoServico> lista = new ArrayList<>();
        String sql = "SELECT * FROM Contrato_Servico";

        try (Connection conn = DB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                ContratoServico cs = new ContratoServico();
                cs.setIdContrato(rs.getInt("id_contrato"));
                cs.setIdServico(rs.getInt("id_servico"));
                cs.setValorNegociado(rs.getDouble("valor_negociado"));
                cs.setPrazoEntrega(rs.getDate("prazo_entrega"));
                lista.add(cs);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lista;
    }

    public void update(ContratoServico cs) {
        String sql = "UPDATE Contrato_Servico SET valor_negociado = ?, prazo_entrega = ? WHERE id_contrato = ? AND id_servico = ?";
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);

            statement.setDouble(1, cs.getValorNegociado());
            statement.setDate(2, cs.getPrazoEntrega());
            statement.setInt(3, cs.getIdContrato());
            statement.setInt(4, cs.getIdServico());

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
            statement = conn.prepareStatement("DELETE FROM ContratoServico WHERE id_contratoServico = ?");
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

    public ContratoServico findById(Integer id) {
        String sql = "SELECT * FROM ContratoServico WHERE id_contratoServico = ?";
        ContratoServico cs = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                cs = new ContratoServico();
                cs.setIdContrato(rs.getInt("id_contrato"));
                cs.setIdServico(rs.getInt("id_servico"));
                cs.setValorNegociado(rs.getDouble("valor_negociado"));
                cs.setPrazoEntrega(rs.getDate("prazo_entrega"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(statement);
            DB.closeConnection();
        }
        return cs;
    }
}
