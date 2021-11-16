/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.dao;

import br.com.aerodev01.entity.AssentoOcupado;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author luan
 */
public class AssentoOcupadoDao implements Serializable{
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    
    public void create(AssentoOcupado aso) {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "INSERT INTO AssentoOcupado(aso_viagemID, aso_assentoNumero) VALUES (?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, aso.getIdViagem());
            stmt.setInt(2, aso.getNumeroAssento());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            aso.setId(rs.getInt(1));
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public void Delete(AssentoOcupado aso) {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "DELETE FROM AssentoOcupado WHERE aso_viagemID=? AND aso_assentoNumero=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, aso.getIdViagem());
            stmt.setInt(2, aso.getNumeroAssento());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao deletar de AssentoOcupado " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public List retornaAssentos(int idViagem) {
        List lista = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT aso_assentoNumero FROM AssentoOcupado where aso_viagemID=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, idViagem);
            rs = stmt.executeQuery();
            while (rs.next()) {
                lista.add(rs.getInt("aso_assentoNumero"));
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar assentos " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
    
    
}
