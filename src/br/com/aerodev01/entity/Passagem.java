/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.aerodev01.entity;


public class Passagem {
    private int id, idViagem;
    private String numeroAsseno, idPassageiro, idFuncionario;

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
     * @return the numeroAsseno
     */
    public String getNumeroAsseno() {
        return numeroAsseno;
    }

    /**
     * @param numeroAsseno the numeroAsseno to set
     */
    public void setNumeroAsseno(String numeroAsseno) {
        this.numeroAsseno = numeroAsseno;
    }

    /**
     * @return the idPassageiro
     */
    public String getIdPassageiro() {
        return idPassageiro;
    }

    /**
     * @param idPassageiro the idPassageiro to set
     */
    public void setIdPassageiro(String idPassageiro) {
        this.idPassageiro = idPassageiro;
    }

    /**
     * @return the idFuncionario
     */
    public String getIdFuncionario() {
        return idFuncionario;
    }

    /**
     * @param idFuncionario the idFuncionario to set
     */
    public void setIdFuncionario(String idFuncionario) {
        this.idFuncionario = idFuncionario;
    }
}
