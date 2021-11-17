/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import br.com.aerodev01.entity.Funcionario;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelDeControle {
            
    public PainelDeControle(Funcionario funcionario, boolean isAdmin) {
        Window janela = new Window(false);
        janela.setSize(750, 450);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        janela.addLabelTitulo(painel, "Painel de Controle", 250, 230, 20);
        if (funcionario != null) {
            janela.addLabel(painel, funcionario.getCpf(), 5, 5, 150, 20);
        }
        JButton btnNovoCliente = janela.addButton(painel, "Novo Cliente", 50, 150, 200, 40);
        JButton btnComprar = janela.addButton(painel, "Comprar Passagem", 270, 150, 200, 40);
        JButton btnCancelarCompra = janela.addButton(painel, "Cancelar Compra", 490, 150, 200, 40);
        JButton btnNovoAviao = janela.addButton(painel, "Cadastrar Avião", 50, 210, 200, 40);
        JButton btnCadastrarViagem = janela.addButton(painel, "Cadastrar VOO", 270, 210, 200, 40);
        JButton btnPassagens = janela.addButton(painel, "Ver Passagens", 490, 210, 200, 40); 
        JButton btnNovoFuncionario = janela.addButton(painel, "Novo Funcionario", 50, 390, 180, 30);
        JButton btnFechar = janela.addButton(painel, "Fechar", 540, 390, 150, 30);
        
        btnNovoFuncionario.setVisible(isAdmin);
        
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        
        
        
        btnPassagens.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Passagens(janela);
            }
        });
        
        btnCancelarCompra.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Cancelamento(janela);
            }
        });
        
        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new ComprarPassagem(funcionario, janela);
            }
        });
        
        btnFechar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        
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
        new PainelDeControle(null, false);
    }
}
