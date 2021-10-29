/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.dao;

import br.com.aerodev01.entity.Aviao;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

/**
 *
 * @author luan.adao
 */
public class AviaoDao implements Serializable {
    protected Connection con;
    protected PreparedStatement stmt;
    protected ResultSet rs;
    
    public void create(Aviao aviao) throws SQLException {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "INSERT INTO Aviao (avi_nome, avi_assentos) VALUES (?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, aviao.getNome());
            stmt.setInt(2, aviao.getAssentos());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso :)");
            rs.next();
            aviao.setId(rs.getInt("avi_id"));
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao salvar" + e.getMessage());
        }
    }
}
