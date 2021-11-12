/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.entity;

/**
 *
 * @author luan
 */
public class AssentoOcupado {
     private int id, idViagem, numeroAssento;

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the idViagem
     */
    public int getIdViagem() {
        return idViagem;
    }

    /**
     * @param idViagem the idViagem to set
     */
    public void setIdViagem(int idViagem) {
        this.idViagem = idViagem;
    }

    /**
     * @return the numeroAssento
     */
    public int getNumeroAssento() {
        return numeroAssento;
    }

    /**
     * @param numeroAssento the numeroAssento to set
     */
    public void setNumeroAssento(int numeroAssento) {
        this.numeroAssento = numeroAssento;
    }
}
