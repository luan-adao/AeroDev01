/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import br.com.aerodev01.dao.AviaoDao;
import br.com.aerodev01.dao.ViagemDao;
import br.com.aerodev01.entity.Aviao;
import br.com.aerodev01.entity.Viagem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NovaViagem {
    public NovaViagem(JFrame frame){
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        janela.addLabelTitulo(painel, "Cadastro de VOO", 230, 200, 20);
        janela.addLabel(painel, "Origem", 50, 150, 100, 20);
        JTextField tfieldOrigem = janela.addTextField(painel, false, 50, 180, 200, 20);
        janela.addLabel(painel, "Destino", 340, 150, 100, 20);
        JTextField tfieldDestino = janela.addTextField(painel, false, 340, 180 , 200, 20);
        janela.addLabel(painel, "Data", 100, 220, 100, 20);
        JTextField tfieldData = janela.addTextField(painel, false, 50, 250, 200, 20);
        janela.addLabel(painel, "Preço", 340, 220, 100, 20);
        JTextField tfieldPreco = janela.addTextField(painel, false, 340, 250, 200, 20);
        janela.addLabel(painel, "Avião", 50 ,290, 100, 20);
        AviaoDao aviaoDao = new AviaoDao();
        JComboBox comboAviao = janela.addComboBox(painel, 50, 320, 150, 20);
        
        JButton btnCadastrar = janela.addButton(painel, "Cadastar", 340, 390, 150, 30);
        
        try {
            for (Aviao avi: aviaoDao.ListAll()) {
                comboAviao.addItem(avi.getNome());
            }
        } catch (SQLException ex) {
            Logger.getLogger(NovaViagem.class.getName()).log(Level.SEVERE, null, ex);
        }
        //comboAviao.setBounds(340, 250, 150, 20);
        //painel.add(comboAviao);
        
        btnCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Viagem viagem = new Viagem();
                viagem.setOrigem(tfieldOrigem.getText());
                viagem.setDestino(tfieldDestino.getText());
                viagem.setData(tfieldData.getText());
                viagem.setPreco(tfieldPreco.getText());
                AviaoDao aviaoDao = new AviaoDao();
                viagem.setIdAviao(aviaoDao.pesquisaPorNome(comboAviao.getSelectedItem().toString()));
                //System.out.println(comboAviao.getSelectedItem().toString());
                ViagemDao viagemDao = new ViagemDao();
                try {
                    viagemDao.create(viagem);
                } catch (Exception er) {
                    System.err.println("Erro ao salvar voo" + er.getMessage());
                }
            }
        });
        
                
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setVisibleWindowListener(frame);
    }
}
