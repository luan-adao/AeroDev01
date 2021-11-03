/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import br.com.aerodev01.dao.AviaoDao;
import br.com.aerodev01.entity.Aviao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NovoAviao {
    public NovoAviao(JFrame frame) {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        janela.addLabelTitulo(painel, "Novo Avião", 200, 200, 20);
        janela.addLabel(painel, "Nome", 200, 150, 200, 20);
        JTextField tfieldNome = janela.addTextField(painel, false, 200, 180, 200, 20);
        janela.addLabel(painel, "Numero de assentos", 200,210, 200, 20);
        JTextField tfieldAssentos = janela.addTextField(painel, false, 200, 240, 200, 20);
        JButton buttonCadastrar = janela.addButton(painel, "Cadastrar", 230, 280, 150, 30);
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setVisibleWindowListener(frame);
        
        buttonCadastrar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (! tfieldNome.getText().isEmpty() && ! tfieldAssentos.getText().isEmpty()) {
                    Aviao aviao = new Aviao();
                    aviao.setNome(tfieldNome.getText());
                    aviao.setAssentos(Integer.parseInt(tfieldAssentos.getText()));
                    AviaoDao aviaoDao = new AviaoDao();
                    try {
                        aviaoDao.create(aviao);
                    } catch (Exception er) {
                        System.err.println(er.getMessage());
                    }
                }
            }
        });
    }
    
    public static void main(String[] args) {
        //new NovoAviao();
    }
}
