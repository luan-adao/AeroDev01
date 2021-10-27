/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import br.com.aerodev01.dao.FuncionarioDao;
import br.com.aerodev01.entity.Funcionario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author luan
 */
public class AeroDev01 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        Window janela = new Window(false);
        
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        janela.addLabelTitulo(painel, "AeroDev", 230, 200, 30);
        janela.addLabel(painel, "Login", 200, 150, 100, 20);
        JTextField tfLoginName = janela.addTextField(painel, false, 200, 180, 200, 20);
        janela.addLabel(painel, "Senha", 200, 210, 100, 20);
        JTextField tfLoginPassword = janela.addTextField(painel, true, 200, 240, 200, 20);
        JButton btnLogin = janela.addButton(painel, "Entrar", 230, 280, 100, 30);
        
        janela.getContentPane().add(painel);
        
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfLoginName.getText().isEmpty() && !tfLoginPassword.getText().isEmpty()) {
                    FuncionarioDao funDao = new FuncionarioDao();
                    try {
                        boolean logar = funDao.Login(tfLoginName.getText(), tfLoginPassword.getText());
                        System.out.print(logar);
                        if (logar){
                            new PainelDeControle(true);
                            janela.dispose();
                        } else {
                            JOptionPane.showMessageDialog(janela, "Login Errado", "Erro de login", JOptionPane.ERROR_MESSAGE);
                        }
                        /*
                        for (Funcionario fun: funDao.Read()) {
                            if (tfLoginName.getText().equals(fun.getCpf()) && tfLoginPassword.getText().equals(fun.getSenha())){
                                //JOptionPane.showMessageDialog(janela, "Login Correto");
                                new PainelDeControle(true);
                                janela.dispose();
                                break;
                            } else {
                                JOptionPane.showMessageDialog(janela, "Login Errado", "Erro de login", JOptionPane.ERROR_MESSAGE);
                            }
                        }
                        */
                        
                    } catch (SQLException ex) {
                        Logger.getLogger(AeroDev01.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        
        janela.setVisible(true);
    }
    
}
