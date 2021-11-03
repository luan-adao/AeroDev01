/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.dao;

import br.com.aerodev01.entity.Aviao;
import br.com.aerodev01.entity.Passagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PassagemDao {
    protected Connection con;
    protected PreparedStatement stmt = null;
    protected ResultSet rs = null;
    
    public void create(Passagem passagem) {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "INSERT INTO Passagem(psgm_numeroAssento, psgm_passageiroID, psgm_funcionarioID, psgm_viagemID)"
                    + "VALUES (?, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, passagem.getNumeroAsseno());
            stmt.setString(2, passagem.getIdPassageiro());
            stmt.setString(3, passagem.getIdFuncionario());
            stmt.setInt(4, passagem.getIdViagem());
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            passagem.setId(rs.getInt(1));
            
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao salvar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public List<Passagem> listAll() {
        List<Passagem> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM Passagem";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Passagem passagem = new Passagem();
                passagem.setId(rs.getInt("psgm_id"));
                passagem.setNumeroAsseno(rs.getString("psgm_numeroAssento"));
                passagem.setIdPassageiro(rs.getString("psgm_passageiroID"));
                passagem.setIdFuncionario(rs.getString("psgm_funcionarioID"));
                passagem.setIdViagem(rs.getInt("psgm_viagemID"));
                lista.add(passagem);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
    
    
    public List<Aviao> listAviao() {
        List<Aviao> lista = new ArrayList<>();
        
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select avi_nome from Passagem as pas"
                + " inner join Viagem as via on pas.psgm_viagemID = via.via_id"
                + " inner join Aviao as avi on via.via_aviaoID = avi.avi_id";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Aviao aviao = new Aviao();
                aviao.setNome(rs.getString("avi_nome"));
                lista.add(aviao);
            }
        } catch (Exception e) {
            System.err.println("Erro ao lista avião");
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
}
