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
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author luan
 */
public class NovoFuncionario {
    public NovoFuncionario(JFrame frame) {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        janela.addLabelTitulo(painel, "Novo Funcionário", 210, 200, 20);
        janela.addLabel(painel, "Nome", 200, 150, 100, 20);
        JTextField tfieldNome = janela.addTextField(painel, false, 200, 180, 200, 20);
        janela.addLabel(painel, "C.P.F", 200, 210, 100, 20);
        JTextField tfieldCpf = janela.addTextField(painel, false, 200, 240, 200, 20);
        janela.addLabel(painel, "Senha", 200, 270, 100, 20);
        JTextField tfieldSenha = janela.addTextField(painel, true, 200, 300, 200, 20);
        JButton buttonCadastrar = janela.addButton(painel, "Cadastrar", 230, 340, 150, 30);
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setVisibleWindowListener(frame);
        
        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (! tfieldNome.getText().isEmpty() && ! tfieldCpf.getText().isEmpty() && ! tfieldSenha.getText().isEmpty() ) {
                    FuncionarioDao funcionarioDao = new FuncionarioDao();
                    try {
                        Funcionario funcionario = new Funcionario();
                        funcionario.setCpf(tfieldCpf.getText());
                        funcionario.setNome(tfieldNome.getText());
                        funcionario.setSenha(tfieldSenha.getText());
                        funcionarioDao.Create(funcionario);
                        
                    } catch (Exception er) {
                        
                    }
                }
            }
        });
        
    }
    
    public static void main(String[] args) {
        //new NovoFuncionario();
    }
}
