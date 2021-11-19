/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import br.com.aerodev01.dao.PassagemDao;
import br.com.aerodev01.entity.Passagem;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author luan
 */
public class Passagens {
    public  Passagens(JFrame frame) {
        Window janela = new Window(true);
        janela.setLayout(null);
        JPanel painelTitulo = new JPanel();
        painelTitulo.setLayout(null);
        painelTitulo.setBounds(0, 0, 600, 80);
        
        JPanel painelTable = new JPanel();
        painelTable.setBounds(0, 100, 600, 280);
        
        JPanel painelBottom = new JPanel();
        painelBottom.setBounds(0, 380, 600, 50);
        painelBottom.setLayout(null);
        
        janela.addLabelTitulo(painelTitulo, "Passagens", 220, 250, 20);
        
        DefaultTableModel modeloTabela = new DefaultTableModel();
        JTable tabela = new JTable(modeloTabela);
        tabela.setLocale(null);
        tabela.setBounds(0, 0, 600, 280);
        modeloTabela.addColumn("Nº");
        modeloTabela.addColumn("Nome");
        modeloTabela.addColumn("Origem");
        modeloTabela.addColumn("Destino");
        modeloTabela.addColumn("Preço");
        modeloTabela.addColumn("Data");
        tabela.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tabela.getColumnModel().getColumn(0).setPreferredWidth(40);
        tabela.getColumnModel().getColumn(1).setPreferredWidth(150);
        tabela.getColumnModel().getColumn(2).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(3).setPreferredWidth(100);
        tabela.getColumnModel().getColumn(4).setPreferredWidth(85);
        tabela.getColumnModel().getColumn(5).setPreferredWidth(100);
        
        painelTable.setLayout(null);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(10, 0, 580, 280);
        painelTable.add(scroll);
        
        JCheckBox checkCanceladas = new JCheckBox("Canceladas");
        checkCanceladas.setBounds(50, 10, 150, 20);
        painelBottom.add(checkCanceladas);
        
        carregaTabela(modeloTabela, 0);
        
        janela.getContentPane().add(painelTitulo);
        janela.getContentPane().add(painelTable);
        janela.getContentPane().add(painelBottom);
        janela.setVisible(true);
        janela.setVisibleWindowListener(frame);
        
        checkCanceladas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (checkCanceladas.isSelected()) {
                    carregaTabela(modeloTabela, 1);
                } else {
                    carregaTabela(modeloTabela, 0);
                }
            }
        });
        
    }
    
    private void carregaTabela(DefaultTableModel modeloTabela, int cancelada) {
        try {
            //modeloTabela.getDataVector().clear();
            List<Passagem> passagens = new ArrayList<>();
            PassagemDao pasDao = new PassagemDao();
            passagens =  pasDao.listAll(cancelada);
            
            System.out.println(passagens);
            if (!passagens.isEmpty()) {
                modeloTabela.getDataVector().clear();
                for (Passagem pas: passagens) {
                    List passagemInfos = pasDao.getInfos(pas.getId(), cancelada);
                    System.out.println(pasDao.getPassageiroNome(pas.getIdPassageiro(), cancelada));
                    modeloTabela.addRow(
                        new Object[] {
                            pas.getId(), pasDao.getPassageiroNome(pas.getIdPassageiro(), cancelada),
                            passagemInfos.get(0), passagemInfos.get(1), passagemInfos.get(5), passagemInfos.get(2)
                        }    
                    );
                }
            } else {
                modeloTabela.setRowCount(0);
                System.out.println(passagens);
            }
        
        } catch (Exception e) {
            System.err.println("Erro ao listar passagens na tabela " + e.getMessage());
        }
    }
    
    public static void main(String[] args) {
        JFrame frame = new JFrame();
        new Passagens(frame);
    }
}
