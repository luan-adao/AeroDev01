/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import br.com.aerodev01.dao.ViagemDao;
import br.com.aerodev01.entity.Viagem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author luan
 */
public class ComprarPassagem {
    String preco = ""; 
    String aviao = "";
    public ComprarPassagem(JFrame frame) {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        Vector origens = new Vector();
        //Vector destinos = new Vector();
        
        janela.addLabelTitulo(painel, "Comprar Passagem", 210, 250, 20);
        janela.addLabel(painel, "Origem", 50, 150, 100, 20);
        JComboBox comboOrigem = janela.addComboBox(origens, painel, 50, 180, 150, 20);
        janela.addLabel(painel, "Destino", 260, 150, 100, 20);
        JComboBox comboDestino = janela.addComboBox(painel, 260, 180, 150, 20);
        janela.addLabel(painel, "Data", 420, 150, 100, 20);
        JComboBox comboData = janela.addComboBox(painel, 420, 180, 150, 20);
        janela.addLabel(painel, "Assento:", 50, 220, 100, 20);
        JComboBox comboAssento = janela.addComboBox(painel, 50, 250, 150, 20);
        janela.addLabel(painel, "Preço: " + this.preco, 260, 250, 150, 20);
        janela.addLabel(painel, "Aviao: " + this.aviao, 420, 250, 150, 20);
        
        comboOrigem.setSelectedIndex(-1);
        comboDestino.setEnabled(false);
        //comboDestino.setSelectedIndex(-1);
        comboData.setEnabled(false);
        comboAssento.setEnabled(false);
        
        ViagemDao viagemDao = new  ViagemDao();
        try {
            for(Viagem via: viagemDao.listAll() ){
                if (!origens.contains(via.getOrigem())) {
                    origens.add(via.getOrigem());
                }
                //System.out.println(comboOrigem.get);
            }
        } catch (Exception e) {
        }
        
        comboOrigem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboOrigem.isFocusOwner()) {
                    comboDestino.removeAllItems();
                    //ViagemDao viagemDao = new ViagemDao();
                    try {
                        System.out.println(viagemDao.listaDestinoPorOrigem(comboOrigem.getSelectedItem().toString()));
                        for (Object destinos: viagemDao.listaDestinoPorOrigem(comboOrigem.getSelectedItem().toString())) {
                            comboDestino.addItem(destinos);
                        }
                        comboDestino.setEnabled(true);
                        comboData.setEnabled(false);
                        comboDestino.setSelectedIndex(-1);
                    } catch (Exception er) {
                    }   
                }
            }
        });
        
        comboDestino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboDestino.isFocusOwner()) {
                    comboData.removeAllItems();
                    try {
                        for (Object datas: viagemDao.listaData(comboOrigem.getSelectedItem().toString(), comboDestino.getSelectedItem().toString())) {
                            comboData.addItem(datas);
                        }
                        comboData.setEnabled(true);
                        comboData.setSelectedIndex(-1);
                        System.out.println("comboDestino action");
                    } catch (Exception er) {
                    }   
                }
            }
        });
        
        
        comboData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboData.isFocusOwner()) {
                    System.out.println("Fous Owner");
                    System.out.println("Ação no combo Data");
                    int idAviao = viagemDao.retornaAviaoId(comboOrigem.getSelectedItem().toString(),
                        comboDestino.getSelectedItem().toString(),
                        comboData.getSelectedItem().toString());
                    preco = viagemDao.retornaPreco(comboOrigem.getSelectedItem().toString(),
                        comboDestino.getSelectedItem().toString(),
                        comboData.getSelectedItem().toString(),
                        idAviao);
                    System.out.println(preco);
                }
            }
        });
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setVisibleWindowListener(frame);
    }
}
