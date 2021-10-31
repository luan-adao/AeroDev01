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
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author luan
 */
public class FuncionarioDao implements Serializable{
    protected Connection con;
    protected PreparedStatement stmt = null;
    protected ResultSet rs = null;
    public boolean isAdmin = false;
    
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
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso :)");
            rs.next();
            //funcionario.setCpf(rs.getString("fun_cpf"));
        } catch (SQLException e) {
            if (e.getErrorCode() == 1062) {
                JOptionPane.showMessageDialog(null, "Esta Pessoa já esta cadastrada", "Pessoa já é Cliente", JOptionPane.WARNING_MESSAGE);
            }
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
        } catch (SQLException e) {
            System.err.println("Ocorreu um erro ao ler: " + e.getMessage());
        }
        return lista;
    }
    
    public boolean Login(String cpf, String pass) throws SQLException {
        boolean login = false;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT fun_cpf, fun_senha FROM Funcionario";
            stmt = con.prepareStatement(sql);
            //stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            while (rs.next()) {
                if (cpf.equals(rs.getString("fun_cpf")) && pass.equals(rs.getString("fun_senha"))) {
                    login = true;
                    if (rs.getString("fun_cpf").equals("admin")) {
                        this.isAdmin = true;
                    }
                    break;
                } else {
                    login = false;
                }   
            }
        } catch (SQLException e) {
            System.err.println("ERRO" + e.getMessage());
        }
        return login;
    }
    
    public void Update(Funcionario funcionario) throws SQLException {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "";
        } catch (Exception e) {
        }
    }
}
