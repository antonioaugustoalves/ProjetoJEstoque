/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.models;

/**
 *
 * @author ANTONIO
 */
public class Cidade {

    private int id;
    private String cidade;
    private String estado;

    public Cidade() {
    }

    public Cidade(String cidade, String estado) {
        this.cidade = cidade;
        this.estado = estado;
    }

    public Cidade(int id, String cidade, String estado) {
        this.id = id;
        this.cidade = cidade;
        this.estado = estado;
    }
    
    
    
    
    
    

    @Override
    public String toString() {
        return this.cidade+
                "("+this.estado+")";
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
   

}
