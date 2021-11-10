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
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, aso.getIdViagem());
            stmt.setInt(2, aso.getNumeroAssento());
            stmt.executeUpdate();
            rs.next();
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    
}
