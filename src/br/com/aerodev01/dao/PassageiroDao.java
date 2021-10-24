/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.dao;

import br.com.aerodev01.entity.Passageiro;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author luan
 */
public class PassageiroDao implements Serializable{
    protected Connection con;
    protected PreparedStatement stmt = null;
    protected ResultSet rs = null;
    
    public void create(Passageiro passageiro)throws SQLException {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "INSERT INTO Passageiro (pas_cpf, pas_nome) VALUES (?, ?)";
            //stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt = con.prepareStatement(sql);
            stmt.setString(1, passageiro.getCpf());
            stmt.setString(2, passageiro.getNome());
            stmt.executeUpdate();
            //rs = stmt.getGeneratedKeys();
            //rs.next();
            //passageiro.setCpf(rs.getString("pas_cpf"));
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Esta Pessoa já esta cadastrada", "Pessoa já é Cliente", JOptionPane.WARNING_MESSAGE);
            }
            System.err.println("Ocorreu um erro ao salvarc " + e.getMessage());
        }
    }
}
