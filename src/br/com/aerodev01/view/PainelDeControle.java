/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 *
 * @author luan
 */
public class PainelDeControle {
            
    public PainelDeControle(boolean isAdmin) {
        Window janela = new Window(false);
        
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        janela.addLabelTitulo(painel, "Painel de Controle", 200, 230, 20);
        JButton btnNovoCliente = janela.addButton(painel, "Novo Cliente", 100,150, 200, 40);
        JButton btnComprar = janela.addButton(painel, "Comprar Passagem", 320, 150, 200, 40);
        JButton btnCadastrarViagem = janela.addButton(painel, "Cadastrar Viagem", 100, 210, 200, 40);
        JButton btnCancelarCompra = janela.addButton(painel, "Cancelar Compra", 320, 210, 200, 40);
        JButton btnNovoFuncionario = janela.addButton(painel, "Novo Funcionario", 100, 270, 200, 40);   
        JButton btnNovoAviao = janela.addButton(painel, "Novo Aviao", 320, 270, 200, 40);
        
        btnNovoFuncionario.setEnabled(isAdmin);
        
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        
        btnCadastrarViagem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NovaViagem(janela);
            }
        });
        
        btnNovoAviao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(".actionPerformed()");
                new NovoAviao(janela);
            }
        });
        
        btnNovoFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new NovoFuncionario(janela);
            }
        });
        
        btnNovoCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println(".actionPerformed()");
                NovoCliente novoCliente = new NovoCliente(janela);
            }
        });
        
    }
    public static void main(String[] args) {
        new PainelDeControle(false);
    }
}
