/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import br.com.aerodev01.dao.CancelarDao;
import br.com.aerodev01.dao.PassagemDao;
import br.com.aerodev01.entity.AssentoOcupado;
import br.com.aerodev01.entity.Cancelar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import jdk.internal.org.objectweb.asm.tree.LabelNode;

/**
 *
 * @author luan
 */
public class Cancelamento {
    private boolean checked = false;
    private final JButton btnCancelar, btnConfirmar;
    private JTextField tfieldCodigo;
    private JLabel labelNome, labelCpf, labelOrigem, labelDestino, labelData, labelPreco;
    public Cancelamento(JFrame frame) {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);
        
        janela.addLabelTitulo(painel, "Cancelar", 210, 250, 20);
        janela.addLabel(painel, "Código:", 100, 150, 100, 20);
        tfieldCodigo = janela.addTextField(painel, false, 100, 180, 50, 20);
        JButton btnChecar = janela.addButton(painel, "Checar", 155, 180, 90, 20);
        btnCancelar = janela.addButton(painel, "Cancelar", 50, 380, 100, 30);
        btnConfirmar = janela.addButton(painel, "Confirmar", 160, 380, 110, 30);
        changeButtonStatus();
        
        janela.addLabel(painel, "Nome: ", 330, 150, 50, 20);
        janela.addLabel(painel, "C.P.F: ", 330, 180, 50, 20);
        janela.addLabel(painel, "Origem: ", 330, 210, 100, 20);
        janela.addLabel(painel, "Destino: ", 330, 240, 100, 20);
        janela.addLabel(painel, "Data: ", 330, 270, 50, 20);
        janela.addLabel(painel, "Preço: ", 330, 300, 50, 20);
        
        labelNome = janela.addLabelWithReturn(painel, "", 385, 150, 250, 20);
        labelCpf = janela.addLabelWithReturn(painel, "", 385, 180, 250, 20);
        labelOrigem = janela.addLabelWithReturn(painel, "", 395, 210, 200, 20);
        labelDestino = janela.addLabelWithReturn(painel, "", 395, 240, 200, 20);
        labelData = janela.addLabelWithReturn(painel, "", 385, 270, 200, 20);
        labelPreco = janela.addLabelWithReturn(painel, "", 385, 300, 200, 20);
        
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checked = false;
                changeButtonStatus();
                resetInfos();
            }
        });
        
        btnConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer cod = Integer.parseInt(tfieldCodigo.getText());
                CancelarDao cancelarDao = new CancelarDao();
                PassagemDao pasDao = new PassagemDao();
                Cancelar cancelar = new Cancelar();
                cancelar.setPassagemID(cod);
                AssentoOcupado aso = new AssentoOcupado();
                Integer viagemId = pasDao.getViagemId(cod);
                aso.setIdViagem(viagemId);
                Integer numeroAssento = Integer.parseInt(pasDao.getInfos(cod).get(3).toString());
                aso.setNumeroAssento(numeroAssento);
                cancelarDao.create(cancelar, aso);
                resetInfos();
                checked = false;
                changeButtonStatus();
            }
        });
        
        btnChecar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Integer cod = Integer.parseInt(tfieldCodigo.getText());
                PassagemDao pasDao = new PassagemDao();
                System.out.println(cod);
                if (!tfieldCodigo.getText().isEmpty() && pasDao.checkExists(cod)) {
                    String cpf = pasDao.getInfos(cod).get(4).toString();
                    labelNome.setText(pasDao.getPassageiroNome(cpf));
                    labelCpf.setText(cpf);
                    labelOrigem.setText(pasDao.getInfos(cod).get(0).toString());
                    labelDestino.setText(pasDao.getInfos(cod).get(1).toString());
                    labelData.setText(pasDao.getInfos(cod).get(2).toString());
                    labelPreco.setText(pasDao.getInfos(cod).get(5).toString());
                    checked = true;
                    changeButtonStatus();
                } else {
                    checked = false;
                    changeButtonStatus();
                }
            }
        });
        
        janela.getContentPane().add(painel);
        janela.setVisible(true);
    }
    
    private void resetInfos() {
        tfieldCodigo.setText("");
        labelCpf.setText("");
        labelNome.setText("");
        labelData.setText("");
        labelDestino.setText("");
        labelOrigem.setText("");
        labelPreco.setText("");
        
    }
    
    private void changeButtonStatus() {
        btnCancelar.setEnabled(checked);
        btnConfirmar.setEnabled(checked);
    }
    
    public static void main(String[] args) {
        JFrame f = new JFrame();
        new Cancelamento(f);
    }
}
