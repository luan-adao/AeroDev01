/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.dao;

import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author luan
 */
public class ErrorCheck {
    public static void DuplicateEntry(SQLException exp, String message, String title) {
        if (exp.getErrorCode() == 1062) {
            JOptionPane.showMessageDialog(null, message, title, JOptionPane.WARNING_MESSAGE);
        }
    }
}
