/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

/**
 *
 * @author luan
 */
public class Window extends JFrame{
    
    public Window(boolean dispose) {
        if (dispose) {
            super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        } else {
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        super.setTitle("AeroDev");
        super.setSize(600, 450);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
    }
    
    public void addLabelTitulo(JPanel painel, String text, int x, int width, int FontSize) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Verdana", Font.BOLD, FontSize));
        label.setBounds(x, 30, width, 40);
        painel.add(label);
    }
    
    public void addLabel(JPanel painel, String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        painel.add(label);
    }
    
    public JTextField addTextField(JPanel painel, boolean password, int x, int y, int width, int height) {
        JTextField textField;
        if (password) {
            textField = new JPasswordField();
        } else {
            textField = new JTextField();
        }
        textField.setBounds(x, y,width, height);
        painel.add(textField);
        return textField;
    }
    
    public JButton addButton(JPanel painel, String text, int x, int y, int width, int height) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        painel.add(button);
        return button;
    }
    
    
}
