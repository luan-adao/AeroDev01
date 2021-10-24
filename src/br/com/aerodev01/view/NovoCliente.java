/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import br.com.aerodev01.dao.PassageiroDao;
import br.com.aerodev01.entity.Passageiro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jdk.nashorn.internal.ir.BreakNode;

/**
 *
 * @author luan
 */
public class NovoCliente {
    public NovoCliente() {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        janela.addLabelTitulo(painel, "Novo Cliente", 230, 200, 20);
        janela.addLabel(painel, "C.P.F", 200, 150, 100, 20);
        JTextField tfCpf = janela.addTextField(painel, false, 200, 180, 200, 20);
        janela.addLabel(painel, "Nome", 200, 220, 100, 20);
        JTextField tfNome = janela.addTextField(painel, false, 200, 250, 200, 20);
        JButton btnCadastrar = janela.addButton(painel, "Cadastrar", 230, 290, 150, 30);
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( !tfCpf.getText().isEmpty() && !tfNome.getText().isEmpty() ) {
                    Passageiro passageiro = new Passageiro();
                    passageiro.setCpf(tfCpf.getText());
                    passageiro.setNome(tfNome.getText());
                    PassageiroDao passageiroDao = new PassageiroDao();
                    try {
                        boolean createPass = passageiroDao.create(passageiro);
                        if (createPass){
                            JOptionPane.showMessageDialog(janela,"Cadastro efetuado com sucesso","Cadastro Efetuado", JOptionPane.PLAIN_MESSAGE);

                        }
                    } catch (SQLException er){
                        System.err.println("Erro ao salvar Passageiro " + er.getMessage());
                    }
                }
            }
        });
        
    }
    public static void main(String[] args) {
        new NovoCliente();
    }
}
