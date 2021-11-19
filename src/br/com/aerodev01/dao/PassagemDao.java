/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.dao;

import br.com.aerodev01.entity.AssentoOcupado;
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
            String sql = "INSERT INTO Passagem(psgm_numeroAssento, psgm_passageiroID, psgm_funcionarioID, psgm_viagemID, psgm_cancelada)"
                    + " VALUES (?, ?, ?, ?, ?)";
            stmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, passagem.getNumeroAssento());
            stmt.setString(2, passagem.getIdPassageiro());
            stmt.setString(3, passagem.getIdFuncionario());
            stmt.setInt(4, passagem.getIdViagem());
            stmt.setInt(5, 0);
            stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            passagem.setId(rs.getInt(1));
            
            AssentoOcupadoDao asoDao = new AssentoOcupadoDao();
            AssentoOcupado aso = new AssentoOcupado();
            aso.setIdViagem(passagem.getIdViagem());
            aso.setNumeroAssento(Integer.parseInt(passagem.getNumeroAssento()));
            asoDao.create(aso);
            
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao salvar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
    }
    
    public void Cancela(int passagemId) {
        try {
            con = ConnectionFactory.getConnection();
            String sql = "UPDATE Passagem SET psgm_cancelada = 1"
                    + " WHERE psgm_id=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, passagemId);
            stmt.executeUpdate();
        } catch (Exception e) {
            System.err.println("Ocorreu um erro ao atualizar " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt);
        }
    }
    
    public List<Passagem> listAll(int cancelada) {
        List<Passagem> lista = new ArrayList<>();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT * FROM Passagem WHERE psgm_cancelada=?";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, cancelada);
            rs = stmt.executeQuery();
            while(rs.next()) {
                Passagem passagem = new Passagem();
                passagem.setId(rs.getInt("psgm_id"));
                passagem.setNumeroAssento(rs.getString("psgm_numeroAssento"));
                passagem.setIdPassageiro(rs.getString("psgm_passageiroID"));
                passagem.setIdFuncionario(rs.getString("psgm_funcionarioID"));
                passagem.setIdViagem(rs.getInt("psgm_viagemID"));
                passagem.setCancelada(rs.getInt("psgm_cancelada"));
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
                + " inner join Aviao as avi on via.via_aviaoID = avi.avi_id where psgm_cancelada=0";
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
    
    public List getInfos(int idPassagem, int cancelada) {
        List lista = new ArrayList();
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT via_origem, via_destino, via_data, psgm_numeroAssento, psgm_passageiroID, via_preco FROM Passagem AS pas"
                    + " INNER JOIN Viagem AS via ON pas.psgm_viagemID=via.via_id"
                    + " WHERE psgm_id=? AND psgm_cancelada=?";
            stmt = con.prepareStatement(sql);
            //stmt.setInt(1, idViagem);
            stmt.setInt(1, idPassagem);
            stmt.setInt(2, cancelada);
            rs = stmt.executeQuery();
            if (rs.next()) {
                lista.add(rs.getString("via_origem"));
                lista.add(rs.getString("via_destino"));
                lista.add(rs.getString("via_data"));
                lista.add(rs.getString("psgm_numeroAssento"));
                lista.add(rs.getString("psgm_passageiroID"));
                lista.add(rs.getInt("via_preco"));
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter Origem e Destino " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return lista;
    }
    
    public String getPassageiroNome(String cpf, int cancelada) {
        String nome = "";
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT pas_nome from Passagem as pas"
                    + " INNER JOIN Passageiro as psg on pas.psgm_passageiroID=psg.pas_cpf"
                    + " WHERE psg.pas_cpf=? AND psgm_cancelada=?";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            stmt.setInt(2, cancelada);
            rs = stmt.executeQuery();
            if (rs.next()) {
                nome = rs.getString("pas_nome");
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter nome do passageiro " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return nome;
    }
    
    public String getFuncionarioNome(String cpf) {
        String nome = "";
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT fun_nome from Passagem as pas"
                    + " INNER JOIN Funcionario as fun on pas.psgm_funcionarioID=fun.fun_cpf"
                    + " WHERE fun.fun_cpf=? AND psgm_cancelada=0";
            stmt = con.prepareStatement(sql);
            stmt.setString(1, cpf);
            rs = stmt.executeQuery();
            if (rs.next()) {
                nome = rs.getString("fun_nome");
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter nome do funcionario " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return nome;
    }
    
    public String getAviaoNome(int id) {
        String nome = "";
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT avi_nome from Passagem as pas"
                    + " INNER JOIN Viagem as via on pas.psgm_viagemID=via.via_id"
                    + " INNER JOIN Aviao as avi on via.via_aviaoID=avi.avi_id"
                    + " WHERE pas.psgm_id=? AND psgm_cancelada=0";
            stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                nome = rs.getString("avi_nome");
            }
        } catch (Exception e) {
            System.err.println("Erro ao obter nome do Avião " + e.getMessage());
        } finally {
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        return nome;
    }
    
    public boolean checkExists(int id) {
        boolean exists = false;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT psgm_id FROM Passagem where psgm_id=? AND psgm_cancelada=0";
            stmt = con.prepareCall(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                exists =  true;
            }
        } catch (Exception e) {
            System.err.println("Erro ao checar Passagem " + e.getMessage());
        }
        return exists;
    }
    public Integer getViagemId(int id) {
        Integer viagemId = 0;
        try {
            con = ConnectionFactory.getConnection();
            String sql = "SELECT psgm_viagemID FROM Passagem where psgm_id=? AND psgm_cancelada=0";
            stmt = con.prepareCall(sql);
            stmt.setInt(1, id);
            rs = stmt.executeQuery();
            if (rs.next()) {
                viagemId =  rs.getInt("psgm_viagemID");
            }
        } catch (Exception e) {
            System.err.println("Erro ao checar Passagem " + e.getMessage());
        }
        return viagemId;
    }
}
