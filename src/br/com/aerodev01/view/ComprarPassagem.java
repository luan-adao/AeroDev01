/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import br.com.aerodev01.dao.AviaoDao;
import br.com.aerodev01.dao.ViagemDao;
import br.com.aerodev01.entity.Viagem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author luan
 */
public class ComprarPassagem {
    JTextField tfieldPreco, tfieldAviao;
    JComboBox comboData, comboDestino, comboOrigem;
    public ComprarPassagem(JFrame frame) {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        Vector origens = new Vector();
        //Vector destinos = new Vector();
        
        janela.addLabelTitulo(painel, "Comprar Passagem", 210, 250, 20);
        janela.addLabel(painel, "Origem", 50, 150, 100, 20);
        comboOrigem = janela.addComboBox(origens, painel, 50, 180, 150, 20);
        janela.addLabel(painel, "Destino", 260, 150, 100, 20);
        comboDestino = janela.addComboBox(painel, 260, 180, 150, 20);
        janela.addLabel(painel, "Data", 420, 150, 100, 20);
         comboData = janela.addComboBox(painel, 420, 180, 150, 20);
        janela.addLabel(painel, "Assento:", 50, 220, 100, 20);
        JComboBox comboAssento = janela.addComboBox(painel, 50, 250, 150, 20);
        janela.addLabel(painel, "Preço: ", 260, 250, 50, 20);
        tfieldPreco = janela.addTextField(painel, false, 315, 250, 80, 20);
        janela.addLabel(painel, "Aviao: ", 420, 250, 50, 20);
        tfieldAviao = janela.addTextField(painel, false, 475, 250, 100, 20);
        
        tfieldAviao.setEditable(false);
        tfieldPreco.setEditable(false);
        
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
                        List destinosDisponiveis = viagemDao.listaDestinoPorOrigem(comboOrigem.getSelectedItem().toString());
                        System.out.println(viagemDao.listaDestinoPorOrigem(comboOrigem.getSelectedItem().toString()));
                        
                        for (Object destinos: destinosDisponiveis) {
                            comboDestino.addItem(destinos);
                        }

                        comboDestino.setEnabled(true);
                        comboData.setEnabled(false);
                        if (destinosDisponiveis.size() == 1) {
                            comboDestino.setSelectedIndex(0);
                        } else {
                            comboDestino.setSelectedIndex(-1);
                        }
                        comboData.setSelectedIndex(-1);
                        setPrecoAviao(true);
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
                        List datasDisponiveis = viagemDao.listaData(comboOrigem.getSelectedItem().toString(), comboDestino.getSelectedItem().toString());
                        for (Object datas: datasDisponiveis) {
                            comboData.addItem(datas);
                        }
                        comboData.setEnabled(true);
                        if (datasDisponiveis.size() == 1) {
                            comboData.setSelectedIndex(0);
                            setPrecoAviao(false);
                        } else {
                            comboData.setSelectedIndex(-1);
                            setPrecoAviao(true);
                        }
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
                    setPrecoAviao(false);
                }
            }
        });
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setVisibleWindowListener(frame);
    }
    
    private void setPrecoAviao(boolean zero) {
        try {
            if (zero) {
                tfieldPreco.setText("");
                tfieldAviao.setText("");
            } else {
            ViagemDao viagemDao = new ViagemDao();
            AviaoDao aviaoDao = new AviaoDao();
            int idAviao = viagemDao.retornaAviaoId(comboOrigem.getSelectedItem().toString(),
                    comboDestino.getSelectedItem().toString(),
                    comboData.getSelectedItem().toString());
            String preco = viagemDao.retornaPreco(comboOrigem.getSelectedItem().toString(),
                    comboDestino.getSelectedItem().toString(),
                    comboData.getSelectedItem().toString(),
                    idAviao);
            System.out.println(preco);
            tfieldPreco.setText(preco);  
            tfieldAviao.setText(aviaoDao.retornaNome(idAviao));
            }
        } catch (Exception e) {
        }
    }
}
