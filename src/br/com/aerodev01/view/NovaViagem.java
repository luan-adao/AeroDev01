/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author luan
 */
public class NovaViagem {
    public NovaViagem(JFrame frame) {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        janela.addLabelTitulo(painel, "Cadastro de viagem", 230, 200, 20);
        janela.addLabel(painel, "Origem", 100, 150, 100, 20);
        JTextField tfieldOrigem = janela.addTextField(painel, false, 100, 180, 200, 20);
        janela.addLabel(painel, "Preço", 340, 150, 100, 20);
        JTextField tfieldPreco = janela.addTextField(painel, false, 340, 180 , 200, 20);
        janela.addLabel(painel, "Destino", 100, 220, 100, 20);
        JTextField tfieldDestino = janela.addTextField(painel, false, 100, 250, 200, 20);
        janela.addLabel(painel, "Avião", 340 , 220, 100, 20);
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setVisibleWindowListener(frame);
    }
}
