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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class NovoFuncionario { 
    public NovoFuncionario(JFrame frame) {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        JRadioButton radioMasculino = new JRadioButton("Masculino");
        JRadioButton radioFeminino = new JRadioButton("Femininio");
        ButtonGroup sexoGroup = new ButtonGroup();
        sexoGroup.add(radioFeminino);
        sexoGroup.add(radioMasculino);
        
        janela.addLabelTitulo(painel, "Novo Funcionário", 210, 200, 20);
        janela.addLabel(painel, "Nome", 100, 150, 100, 20);
        JTextField tfieldNome = janela.addTextField(painel, false, 100, 180, 200, 20);
        janela.addLabel(painel, "Senha", 350, 150, 100, 20);
        JTextField tfieldSenha = janela.addTextField(painel, true, 350, 180, 200, 20);
        janela.addLabel(painel, "C.P.F", 100, 220, 100, 20);
        JTextField tfieldCpf = janela.addTextField(painel, false, 100, 250, 200, 20);
        janela.addLabel(painel, "Sexo:", 350, 220, 100, 20);
        radioMasculino.setBounds(350, 250, 100, 20);
        radioFeminino.setBounds(470, 250, 100, 20);
        painel.add(radioFeminino);
        painel.add(radioMasculino);
        
        JButton buttonCadastrar = janela.addButton(painel, "Cadastrar", 230, 380, 150, 30);
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setVisibleWindowListener(frame);
        
        buttonCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (! tfieldNome.getText().isEmpty() && ! tfieldCpf.getText().isEmpty() && ! tfieldSenha.getText().isEmpty() ) {
                    if (radioFeminino.isSelected() || radioMasculino.isSelected()) {
                        FuncionarioDao funcionarioDao = new FuncionarioDao();
                        try {
                            Funcionario funcionario = new Funcionario();
                            funcionario.setCpf(tfieldCpf.getText());
                            funcionario.setNome(tfieldNome.getText());
                            funcionario.setSenha(tfieldSenha.getText());
                            if(radioFeminino.isSelected()) {
                                funcionario.setSexo(radioFeminino.getText());
                            } else {
                                funcionario.setSexo(radioMasculino.getText());
                            }
                            funcionarioDao.Create(funcionario);
                        } catch (Exception er) {

                        }
                    }
                }
            }
        });
        
    }
    
    public static void main(String[] args) {
        //new NovoFuncionario();
    }
}
