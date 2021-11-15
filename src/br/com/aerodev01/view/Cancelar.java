/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author luan
 */
public class Cancelar {
    public Cancelar(JFrame frame) {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        janela.addLabelTitulo(painel, "Cancelar", 210, 250, 20);
        janela.addLabel(painel, "Código:", 100, 150, 100, 20);
        JTextField tfieldCodigo = janela.addTextField(painel, false, 100, 180, 50, 20);
        JButton btnChecar = janela.addButton(painel, "Checar", 155, 180, 90, 20);
        JButton btnCancelar = janela.addButton(painel, "Cancelar", 0, 0, 0, 0);
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        new Cancelar(f);
    }
}
