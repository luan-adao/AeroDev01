/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author luan
 */
public class ConnectionFactory {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/aerodevdb1";
    private static final String TIMEZONE = "?useTimezone=true&serverTimezone=America/Sao_Paulo&zeroDateTimeBehavior=convertToNull";
    private static final String USER = "root";
    private static final String PASS = "admin";
    
    public static Connection getConnection() {
        try  {
            Class.forName(DRIVER);
            return DriverManager.getConnection(URL+TIMEZONE, USER, PASS);
        } catch (ClassNotFoundException | SQLException ex) {
            throw new RuntimeException("Erro na conexão", ex);
        
        }
        
    }
    
    public static void closeConnection(Connection con) {
        try {
            if (con != null) {
                con.close();
            }   
        } catch (Exception e) {
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt){
        try {
            closeConnection(con);
            if (stmt != null) {
                stmt.close();
            }   
        } catch (Exception e) {
        }
    }
    public static void closeConnection(Connection con, PreparedStatement stmt, ResultSet rs){
        try {
            closeConnection(con, stmt);
            if (rs != null) {
                rs.close();
            }   
        } catch (Exception e) {
        }
    }
    
}
