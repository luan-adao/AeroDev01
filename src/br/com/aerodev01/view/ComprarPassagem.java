/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.view;

import br.com.aerodev01.dao.AssentoOcupadoDao;
import br.com.aerodev01.dao.AviaoDao;
import br.com.aerodev01.dao.PassageiroDao;
import br.com.aerodev01.dao.PassagemDao;
import br.com.aerodev01.dao.ViagemDao;
import br.com.aerodev01.entity.Funcionario;
import br.com.aerodev01.entity.Passagem;
import br.com.aerodev01.entity.Viagem;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author luan
 */
public class ComprarPassagem {

    JTextField tfieldPreco, tfieldAviao, tfieldCpf;
    JComboBox comboData, comboDestino, comboOrigem, comboAssento;
    JButton btnComprar;
    boolean cpfIsValid = false;

    public ComprarPassagem(Funcionario funcionario, JFrame frame) {
        Window janela = new Window(true);
        JPanel painel = new JPanel();
        painel.setLayout(null);

        if (funcionario != null) {
            System.out.println(funcionario.getCpf());
        }

        janela.addLabelTitulo(painel, "Comprar Passagem", 210, 250, 20);
        janela.addLabel(painel, "Origem", 50, 120, 100, 20);
        comboOrigem = janela.addComboBox(painel, 50, 150, 150, 20);
        janela.addLabel(painel, "Destino", 260, 120, 100, 20);
        comboDestino = janela.addComboBox(painel, 260, 150, 150, 20);
        janela.addLabel(painel, "Data", 420, 120, 100, 20);
        comboData = janela.addComboBox(painel, 420, 150, 150, 20);
        janela.addLabel(painel, "Assento:", 50, 190, 100, 20);
        comboAssento = janela.addComboBox(painel, 50, 220, 150, 20);
        janela.addLabel(painel, "Preço: ", 260, 220, 50, 20);
        tfieldPreco = janela.addTextField(painel, false, 315, 220, 80, 20);
        janela.addLabel(painel, "Aviao: ", 420, 220, 50, 20);
        tfieldAviao = janela.addTextField(painel, false, 475, 220, 100, 20);
        janela.addLabel(painel, "C.P.F:", 50, 260, 50, 20);
        tfieldCpf = janela.addTextField(painel, false, 50, 290, 150, 20);
        JButton btnChecar = janela.addButton(painel, "Checar", 75, 315, 100, 20);

        btnComprar = janela.addButton(painel, "Comprar", 300, 390, 100, 30);
        btnComprar.setEnabled(false);

        tfieldAviao.setEditable(false);
        tfieldPreco.setEditable(false);
        comboDestino.setEnabled(false);
        comboData.setEnabled(false);
        comboAssento.setEnabled(false);

        setOrigens();

        btnChecar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    PassageiroDao passDao = new PassageiroDao();
                    if (passDao.ChecarCpf(tfieldCpf.getText())) {
                        tfieldCpf.setForeground(Color.green);
                        cpfIsValid = true;
                        if (canBuy()) {
                            btnComprar.setEnabled(true);
                        } else {
                            btnComprar.setEnabled(false);
                        }
                    } else {
                        tfieldCpf.setForeground(Color.red);
                        btnComprar.setEnabled(false);
                        cpfIsValid = false;
                    }
                } catch (Exception er) {
                }
            }
        });

        comboOrigem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboOrigem.isFocusOwner()) {
                    setDestinos();
                }
            }
        });

        comboDestino.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboDestino.isFocusOwner()) {
                    setDatas();
                }
            }
        });

        comboData.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (comboData.isFocusOwner()) {
                    setPrecoAviao(false);
                }
            }
        });

        btnComprar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!tfieldCpf.getText().isEmpty() && comboOrigem.getSelectedIndex() != -1 && comboDestino.getSelectedIndex() != -1
                        && comboData.getSelectedIndex() != -1 && comboAssento.getSelectedIndex() != -1) {
                    Passagem passagem = new Passagem();
                    try {
                        ViagemDao viagemDao = new ViagemDao();
                        PassagemDao passagemDao = new PassagemDao();

                        int aviaoId = viagemDao.pesquisaPorNome(tfieldAviao.getText());
                        int viagemId = viagemDao.retornaID(comboOrigem.getSelectedItem().toString(),
                                comboDestino.getSelectedItem().toString(), comboData.getSelectedItem().toString(), aviaoId);
                        int assento = Integer.parseInt(comboAssento.getSelectedItem().toString());

                        passagem.setIdFuncionario(funcionario.getCpf());
                        passagem.setIdPassageiro(tfieldCpf.getText());
                        passagem.setIdViagem(viagemId);
                        passagem.setNumeroAssento(String.valueOf(assento));
                        passagemDao.create(passagem);
                        //asoDao.create(aso);
                    } catch (Exception er) {
                        System.out.println("Erro ao Comprar passagem " + er.getMessage());
                    } finally {
                        setAssentos();
                        Ticket myTicket = new Ticket(janela, passagem);
                    }
                }
            }
        });

        janela.getContentPane().add(painel);
        janela.setVisible(true);
        janela.setVisibleWindowListener(frame);
    }
    
    private boolean canBuy() {
        if (!tfieldCpf.getText().isEmpty() && comboOrigem.getSelectedIndex() != -1 && comboDestino.getSelectedIndex() != -1
                        && comboData.getSelectedIndex() != -1 && comboAssento.getSelectedIndex() != -1) {
            return true;
        } else {
            return false;
        }
    }

    private void setOrigens() {
        ViagemDao viagemDao = new ViagemDao();
        List origensList = new ArrayList();
        try {
            for (Viagem via : viagemDao.listAll()) {
                if (!origensList.contains(via.getOrigem())) {
                    origensList.add(via.getOrigem());
                }
            }
            origensList.forEach(origens -> {
                comboOrigem.addItem(origens);
            });
        } catch (Exception e) {
        } finally {
            comboOrigem.setSelectedIndex(-1);
        }
    }

    private void setDestinos() {
        comboDestino.removeAllItems();
        ViagemDao viagemDao = new ViagemDao();
        try {
            List destinosDisponiveis = viagemDao.listaDestinoPorOrigem(comboOrigem.getSelectedItem().toString());
            //System.out.println(viagemDao.listaDestinoPorOrigem(comboOrigem.getSelectedItem().toString()));
            for (Object destinos : destinosDisponiveis) {
                comboDestino.addItem(destinos);
            }
            comboDestino.setEnabled(true);
            comboData.setEnabled(false);
            comboAssento.setEnabled(false);
            if (destinosDisponiveis.size() == 1) {
                comboDestino.setSelectedIndex(0);
                setDatas();
            } else {
                comboDestino.setSelectedIndex(-1);
                comboData.setSelectedIndex(-1);
                setPrecoAviao(true);
            }
        } catch (Exception er) {
        }
    }

    private void setDatas() {
        comboData.removeAllItems();
        try {
            ViagemDao viagemDao = new ViagemDao();
            List datasDisponiveis = viagemDao.listaData(
                    comboOrigem.getSelectedItem().toString(), comboDestino.getSelectedItem().toString()
            );
            datasDisponiveis.forEach(datas -> {
                comboData.addItem(datas);
            });
            comboData.setEnabled(true);
            if (datasDisponiveis.size() == 1) {
                comboData.setSelectedIndex(0);
                setPrecoAviao(false);
            } else {
                comboData.setSelectedIndex(-1);
                comboAssento.setEnabled(false);
                comboAssento.removeAllItems();
                setPrecoAviao(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ComprarPassagem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void setAssentos() {
        comboAssento.removeAllItems();
        comboAssento.setEnabled(true);
        ViagemDao viagemDao = new ViagemDao();
        int aviaoId = viagemDao.pesquisaPorNome(tfieldAviao.getText());
        int numeroAssentos = viagemDao.retornaNumeroDeAssento(aviaoId);
        int viagemId = viagemDao.retornaID(comboOrigem.getSelectedItem().toString(),
                comboDestino.getSelectedItem().toString(), comboData.getSelectedItem().toString(), aviaoId);
        AssentoOcupadoDao asoDao = new AssentoOcupadoDao();
        List assentosOcupados = asoDao.retornaAssentos(viagemId);
        List assentosDisponiveis = new ArrayList();
        for (int i = 1; i <= numeroAssentos; i++) {
            //comboAssento.addItem(i);
            if (assentosOcupados.contains(i)) {
                continue;
            } else {
                assentosDisponiveis.add(i);
            }
        }
        assentosDisponiveis.forEach(assentos -> {
            comboAssento.addItem(assentos);
        });
        System.out.println(assentosDisponiveis);
        if (cpfIsValid) {
            btnComprar.setEnabled(true);
        }
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
                setAssentos();
            }
        } catch (Exception e) {
        }
    }
}
