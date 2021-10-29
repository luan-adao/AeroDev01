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
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author luan
 */
public class NovoCliente {
    public NovoCliente(JFrame frame) {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        JRadioButton radioMasculino = new JRadioButton("Masculino");
        JRadioButton radioFeminino = new JRadioButton("Femininio");
        ButtonGroup sexoGroup = new ButtonGroup();
        sexoGroup.add(radioFeminino);
        sexoGroup.add(radioMasculino);
        
        janela.addLabelTitulo(painel, "Novo Cliente", 230, 200, 20);
        janela.addLabel(painel, "C.P.F", 100, 150, 100, 20);
        JTextField tfCpf = janela.addTextField(painel, false, 100, 180, 200, 20);
        janela.addLabel(painel, "Sexo:", 340, 150, 100, 20);
        radioMasculino.setBounds(350, 180, 100, 20);
        radioFeminino.setBounds(470, 180, 100, 20);
        painel.add(radioFeminino);
        painel.add(radioMasculino);
        
        janela.addLabel(painel, "Cidade", 350, 220, 100, 20);
        JTextField tfCidade = janela.addTextField(painel, false, 350, 250, 200, 20);
        
        janela.addLabel(painel, "Nome", 100, 220, 100, 20);
        JTextField tfNome = janela.addTextField(painel, false, 100, 250, 200, 20);
        JButton btnCadastrar = janela.addButton(painel, "Cadastrar", 230, 290, 150, 30);
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setVisibleWindowListener(frame);
        
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( ! tfCpf.getText().isEmpty() && ! tfNome.getText().isEmpty() && ! tfCidade.getText().isEmpty()) {
                    if (radioFeminino.isSelected() || radioMasculino.isSelected()) {
                        Passageiro passageiro = new Passageiro();
                        passageiro.setCpf(tfCpf.getText());
                        passageiro.setNome(tfNome.getText());
                        passageiro.setCidade(tfCidade.getText());
                        if (radioFeminino.isSelected()) {
                            passageiro.setSexo(radioFeminino.getText());
                        } else {
                            passageiro.setSexo(radioMasculino.getText());
                        }
                        
                        PassageiroDao passageiroDao = new PassageiroDao();
                        try {
                            passageiroDao.create(passageiro);
                        } catch (SQLException er){
                            System.err.println("Erro ao salvar Passageiro " + er.getMessage());
                        }
                    }

                } else {
                    JOptionPane.showMessageDialog(janela, "Os campos devem ser preenchidos");
                }
            }
        });
        
    }
    public static void main(String[] args) {
        //new NovoCliente();
    }
}
