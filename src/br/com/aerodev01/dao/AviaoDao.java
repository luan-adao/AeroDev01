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
import java.util.ArrayList;
import java.util.List;
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
            rs.next();
            aviao.setId(rs.getInt(1));
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso :)");
        } catch (SQLException e) {
            ErrorCheck.DuplicateEntry(e, "Avião já está cadastrado no sistema", "Erro: Avião já existe");
            System.err.println("Ocorreu um erro ao salvar" + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public List<Aviao> ListAll() throws SQLException {
        List<Aviao> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM Aviao";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Aviao aviao = new Aviao();
                aviao.setId(rs.getInt("avi_id"));
                aviao.setNome(rs.getString("avi_nome"));
                aviao.setAssentos(rs.getInt("avi_assentos"));
                lista.add(aviao);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
    
    public Integer pesquisaPorNome(String nome) {
        Integer idAviao = 0;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT avi_id FROM Aviao where avi_nome=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            rs = stmt.executeQuery();
            if (rs.next()) {
                idAviao = rs.getInt("avi_id");                
            }
        } catch (Exception e) {
            System.err.println("Erro ao pesquisar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return idAviao;
    }
    
    public String retornaNome(int id) {
        String aviaoNome = "";
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT avi_nome FROM Aviao where avi_id=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                aviaoNome = rs.getString("avi_nome");
            }
        } catch (Exception e) {
        }
        return aviaoNome;
    }
    
}
