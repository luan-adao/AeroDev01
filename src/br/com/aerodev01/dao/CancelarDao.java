/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.dao;

import br.com.aerodev01.entity.AssentoOcupado;
import br.com.aerodev01.entity.Cancelar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author luan
 */
public class CancelarDao {
    protected Connection con;
    protected PreparedStatement stmt = null;
    protected ResultSet rs = null;
    
    public void create(Cancelar cancelar, AssentoOcupado aso) {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "INSERT INTO Cancelamento (can_passagemID) VALUE (?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setInt(1, cancelar.getPassagemID());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            cancelar.setId(rs.getInt(1));
            AssentoOcupadoDao asoDao = new AssentoOcupadoDao();
            asoDao.Delete(aso);
        } catch (Exception e) {
            System.err.println("Erro ao salvar em Cancelamento " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
