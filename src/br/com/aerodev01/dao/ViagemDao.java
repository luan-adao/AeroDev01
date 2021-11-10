/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.dao;

import br.com.aerodev01.entity.Viagem;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author luan
 */
public class ViagemDao extends AviaoDao{
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;
    
    public Integer retornaAviaoId(String origem, String destino, String data) {
        Integer aviao = 0;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT via_aviaoID FROM Viagem"
                    + " WHERE via_origem=? and via_destino=? and via_data=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, origem);
            stmt.setString(2, destino);
            stmt.setString(3, data);
            rs = stmt.executeQuery();
            if (rs.next()) {
                aviao = rs.getInt("via_aviaoID");
            }
        } catch (Exception e) {
            System.err.println("Erro ao retornar id do aviao " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return aviao;
    }
    
    public String retornaPreco(String origem, String destino, String data, int aviaoId) {
        String preco = "";
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT via_preco FROM Viagem"
                    + " WHERE via_origem=? and via_destino=? and via_data=? and via_aviaoID=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, origem);
            stmt.setString(2, destino);
            stmt.setString(3, data);
            stmt.setInt(4, aviaoId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                preco = rs.getString("via_preco");
            }
        } catch (Exception e) {
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return preco;
    }
    
    public void create(Viagem viagem) throws SQLException {
        try {
            if (retornaID(viagem.getOrigem(), viagem.getDestino(), viagem.getData(), viagem.getIdAviao()) == 0) {
                con = ConnectionFactory.getConnection();
                String sql = "INSERT INTO Viagem(via_origem, via_destino, via_preco, via_data, via_aviaoID)"
                        +" VALUES (?, ?, ?, ?, ?)";
                stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, viagem.getOrigem());
                stmt.setString(2, viagem.getDestino());
                stmt.setString(3, viagem.getPreco());
                stmt.setString(4, viagem.getData());
                stmt.setInt(5, viagem.getIdAviao());
                stmt.executeUpdate();
                rs = stmt.getGeneratedKeys();
                rs.next();
                viagem.setId(rs.getInt(1));
                
            } else {
                System.out.println("Voo já esta cadastrado;");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao salvar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public List<Viagem> listAll() throws SQLException {
        List<Viagem> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM Viagem";
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Viagem viagem = new Viagem();
                viagem.setId(rs.getInt("via_id"));
                viagem.setOrigem(rs.getString("via_origem"));
                viagem.setDestino(rs.getString("via_destino"));
                viagem.setData(rs.getString("via_data"));
                viagem.setPreco(rs.getString("via_preco"));
                viagem.setIdAviao(rs.getInt("via_aviaoID"));
                lista.add(viagem);
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
    
    public List listaDestinoPorOrigem(String origem) throws SQLException {
        List lista = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT via_destino FROM Viagem where via_origem=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, origem);
            rs = stmt.executeQuery();
            while(rs.next()) {
                if (!lista.contains(rs.getString("via_destino"))) {
                    lista.add(rs.getString("via_destino"));
                    //System.out.println(lista);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao lista destinos " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
    public List listaData(String origem, String destino) throws SQLException {
        List lista = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT via_data FROM Viagem where via_origem=? and via_destino=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, origem);
            stmt.setString(2, destino);
            rs = stmt.executeQuery();
            while(rs.next()) {
                if (!lista.contains(rs.getString("via_data"))) {
                    lista.add(rs.getString("via_data"));
                    //System.out.println(lista);
                }
            }
        } catch (Exception e) {
            System.err.println("Erro ao listar data " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
    
    public Integer retornaID(String origem, String destino, String data, int aviaoId) {
        Integer existeId = 0;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "select via_id from Viagem" 
                    + " where via_origem=? and via_destino=?"
                    + " and via_data=? and via_aviaoID=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, origem);
            stmt.setString(2, destino);
            stmt.setString(3, data);
            stmt.setInt(4, aviaoId);
            rs = stmt.executeQuery();
            if (rs.next()) {
                existeId = rs.getInt("via_id");
            }
        } catch (Exception e) {
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return existeId;
    }
    
}
