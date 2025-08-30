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
import com.example.datashield.model.entities.EquipeProjeto;

public class EquipeProjetoDAOJDBC {

    public void inserir(EquipeProjeto ep) {
        String sql = "INSERT INTO Equipe_Projeto (id_contrato, id_funcionario, funcao_no_projeto) VALUES (?, ?, ?)";

        try (Connection conn = DB.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, ep.getIdContrato());
            stmt.setInt(2, ep.getIdFuncionario());
            stmt.setString(3, ep.getFuncaoNoProjeto());
            stmt.executeUpdate();

        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    public List<EquipeProjeto> listar() {
        List<EquipeProjeto> lista = new ArrayList<>();
        String sql = "SELECT * FROM Equipe_Projeto";

        try (Connection conn = DB.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                EquipeProjeto ep = new EquipeProjeto();
                ep.setIdContrato(rs.getInt("id_contrato"));
                ep.setIdFuncionario(rs.getInt("id_funcionario"));
                ep.setFuncaoNoProjeto(rs.getString("funcao_no_projeto"));
                lista.add(ep);
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        }
        return lista;
    }

    public void update(EquipeProjeto ep) {
        String sql = "UPDATE Equipe_Projeto SET funcao_no_projeto = ? WHERE id_contrato = ? AND id_funcionario = ?";
        PreparedStatement statement = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);

            statement.setString(1, ep.getFuncaoNoProjeto());
            statement.setInt(2, ep.getIdContrato());
            statement.setInt(3, ep.getIdFuncionario());

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
            statement = conn.prepareStatement("DELETE FROM EquipeProjeto WHERE id_equipeprojeto = ?");
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

    public EquipeProjeto findById(Integer id) {
        String sql = "SELECT * FROM EquipeProjeto WHERE id_equipeprojeto = ?";
        EquipeProjeto ep = null;
        PreparedStatement statement = null;
        ResultSet rs = null;
        Connection conn = null;

        try {
            conn = DB.conectar();
            statement = conn.prepareStatement(sql);
            statement.setInt(1, id);
            rs = statement.executeQuery();

            if (rs.next()) {
                ep = new EquipeProjeto();
                ep.setIdContrato(rs.getInt("id_contrato"));
                ep.setIdFuncionario(rs.getInt("id_funcionario"));
                ep.setFuncaoNoProjeto(rs.getString("funcao_no_projeto"));
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeResultSet(rs);
            DB.closeStatement(statement);
            DB.closeConnection();
        }
        return ep;
    }
}
