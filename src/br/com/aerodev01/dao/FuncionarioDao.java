/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.dao;

import br.com.aerodev01.entity.Funcionario;
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
public class FuncionarioDao implements Serializable{
    protected Connection con;
    protected PreparedStatement stmt = null;
    protected ResultSet rs = null;
    
    public void Create(Funcionario funcionario) throws SQLException {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "INSERT INTO Funcionario (fun_cpf, fun_nome, fun_senha) values (?, ?, ?)";
            //stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt = con.prepareStatement(sql);
            stmt.setString(1, funcionario.getCpf());
            stmt.setString(2, funcionario.getNome());
            stmt.setString(3, funcionario.getSenha());
            stmt.executeUpdate();
            //rs = stmt.getGeneratedKeys();
            rs.next();
            //funcionario.setCpf(rs.getString("fun_cpf"));
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao salvar: " + e.getMessage());
        }
    }
    
    public List<Funcionario> Read() throws SQLException {
        ArrayList<Funcionario> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM Funcionario";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Funcionario funcionario = new Funcionario();
                funcionario.setCpf(rs.getString("fun_cpf"));
                funcionario.setNome(rs.getString("fun_nome"));
                funcionario.setSenha(rs.getString("fun_senha"));
                lista.add(funcionario);
            }
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao ler: " + e.getMessage());
        }
        return lista;
    }
    
    public void Update(Funcionario funcionario) throws SQLException {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "";
        } catch (Exception e) {
        }
    }
}
