/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Window extends JFrame{
    public Window(boolean dispose) {
        if (dispose) {
            super.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            JButton btnFechar = new JButton("Fechar");
            btnFechar.setBounds(50, 390, 100, 30);
            super.add(btnFechar);
            btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        } else {
            super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
        super.setTitle("AeroDev");
        super.setSize(600, 450);
        super.setLocationRelativeTo(null);
        super.setResizable(false);
    }
    
    public void setVisibleWindowListener(JFrame frame) {
        frame.setVisible(false);
        WindowListener windowListener = new WindowAdapter() {
            public void windowClosed(WindowEvent evt) {
                frame.setVisible(true);
            }
        };
        
        addWindowListener(windowListener);
    }
    
    public JComboBox addComboBox(JPanel painel, int x, int y, int width, int height) {
        JComboBox combo = new JComboBox();
        combo.setBounds(x, y, width, height);
        painel.add(combo);
        return combo;
    }
    
    public JComboBox addComboBox(Vector vector, JPanel painel, int x, int y, int width, int height) {
        JComboBox combo = new JComboBox(vector);
        combo.setBounds(x, y, width, height);
        painel.add(combo);
        return combo;
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
    public JLabel addLabelWithReturn(JPanel painel, String text, int x, int y, int width, int height) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        painel.add(label);
        return label;
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
