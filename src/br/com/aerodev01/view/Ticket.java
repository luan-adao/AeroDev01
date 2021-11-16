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
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author luan
 */
public class Ticket {
    public Ticket(JFrame frame, Passagem passagem, ComprarPassagem comprar) {
        System.out.println(passagem.getId());
        JDialog janela = new JDialog(frame, "Ticket");
        janela.setLocationRelativeTo(frame);
        janela.setSize(450, 250);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        Color myBlue = new Color(152, 181, 223);
        painel.setBackground(myBlue);
        
        JLabel labelNomePassageiro, labelAviao, labelCodigoPassagem, labelOrigem, labelDestino,
                    labelAssento, labelData, labelNomeDoFuncionario;
        JLabel labelNomePassageiroInfo, labelAviaoInfo, labelCodigoPassagemInfo, labelOrigemInfo, labelDestinoInfo,
                    labelAssentoInfo, labelDataInfo, labelNomeDoFuncionarioInfo;
        
        labelNomePassageiro = new JLabel("Nome do Passageiro:");
        labelAviao = new JLabel("Avião: ");
        labelCodigoPassagem = new JLabel("Nº do VOO:");
        labelOrigem = new JLabel("De: ");
        labelDestino = new JLabel("Para: ");
        labelData = new JLabel("Data:");
        labelAssento = new JLabel("Assento:");
        labelNomeDoFuncionario = new JLabel("Nome do Funcionário:");
        JButton btnOk = new JButton("OK");
        
        btnOk.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                janela.dispose();
                comprar.resetAllFields();
            }
        });
        
        PassagemDao pasDao = new PassagemDao();
        
        labelNomePassageiroInfo = new JLabel(pasDao.getPassageiroNome(passagem.getIdPassageiro()));
        labelAviaoInfo = new JLabel(pasDao.getAviaoNome(passagem.getId()));
        labelCodigoPassagemInfo = new JLabel(String.valueOf(passagem.getId()));
        labelOrigemInfo = new JLabel(pasDao.getInfos(passagem.getId()).get(0).toString());
        labelDestinoInfo = new JLabel(pasDao.getInfos(passagem.getId()).get(1).toString());
        labelDataInfo = new JLabel(pasDao.getInfos(passagem.getId()).get(2).toString());
        labelAssentoInfo = new JLabel(pasDao.getInfos(passagem.getId()).get(3).toString());
        labelNomeDoFuncionarioInfo = new JLabel(pasDao.getFuncionarioNome(passagem.getIdFuncionario()));
        
        System.out.println(pasDao.getInfos(passagem.getId()).get(4));
        
        labelNomePassageiro.setBounds(10, 10, 200, 20);
        labelAviao.setBounds(215, 10, 100, 20);
        labelCodigoPassagem.setBounds(320, 10, 100, 20);
        labelOrigem.setBounds(10, 80, 250, 20);
        labelDestino.setBounds(10, 105, 250, 20);
        labelData.setBounds(215, 80, 100, 20);
        labelAssento.setBounds(320, 80, 100, 20);
        labelNomeDoFuncionario.setBounds(10, 180, 200, 20);
        btnOk.setBounds(380, 200, 70, 30);
        
        labelNomePassageiroInfo.setBounds(10, 35, 200, 20);
        labelAviaoInfo.setBounds(215, 35, 100, 20);
        labelCodigoPassagemInfo.setBounds(320, 35, 100, 20);
        labelOrigemInfo.setBounds(65, 80, 50, 20);
        labelDestinoInfo.setBounds(65, 105, 50, 20);
        labelDataInfo.setBounds(215, 105, 100, 20);
        labelAssentoInfo.setBounds(320, 105, 100, 20);
        labelNomeDoFuncionarioInfo.setBounds(10, 205, 200, 20);
        
        
        painel.add(labelNomePassageiro);
        painel.add(labelAviao);
        painel.add(labelCodigoPassagem);
        painel.add(labelOrigem);
        painel.add(labelDestino);
        painel.add(labelData);
        painel.add(labelAssento);
        painel.add(labelNomeDoFuncionario);
        painel.add(labelNomePassageiroInfo);
        painel.add(labelAviaoInfo);
        painel.add(labelCodigoPassagemInfo);
        painel.add(labelOrigemInfo);
        painel.add(labelDestinoInfo);
        painel.add(labelDataInfo);
        painel.add(labelAssentoInfo);
        painel.add(labelNomeDoFuncionarioInfo);
        painel.add(btnOk);
        
        janela.getContentPane().add(painel);
        
        janela.setVisible(true);
        
    }
    
}
