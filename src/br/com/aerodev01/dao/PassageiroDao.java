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
            String sql = "INSERT INTO Passageiro (pas_cpf, pas_nome, pas_sexo, pas_cidade) VALUES (?, ?, ?, ?)";
            //stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt = con.prepareStatement(sql);
            stmt.setString(1, passageiro.getCpf());
            stmt.setString(2, passageiro.getNome());
            stmt.setString(3, passageiro.getSexo());
            stmt.setString(4, passageiro.getCidade());
            stmt.executeUpdate();
            //rs = stmt.getGeneratedKeys();
            //rs.next();
            //passageiro.setCpf(rs.getString("pas_cpf"));
        } catch (SQLException e) {
            ErrorCheck.DuplicateEntry(e, "Pessoa já está cadastrada", "Erro: Esta pessoa já é cliente");
            System.err.println("Ocorreu um erro ao salvar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
}
