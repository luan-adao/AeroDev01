/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.entity;

/**
 *
 * @author luan.adao
 */
public class Aviao {
    private int id;
    private String nome;
    private int assentos;

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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @param nome the nome to set
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * @return the assentos
     */
    public int getAssentos() {
        return assentos;
    }

    /**
     * @param assentos the assentos to set
     */
    public void setAssentos(int assentos) {
        this.assentos = assentos;
    }
}
