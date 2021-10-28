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
public class Endereco {
    private int id;
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    private int codCidade;
    private int codCliente;
    private EnumTipoEndereco tipoEndereco;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public int getCodCidade() {
        return codCidade;
    }

    public void setCodCidade(int codCidade) {
        this.codCidade = codCidade;
    }

    public int getCodCliente() {
        return codCliente;
    }

    public void setCodCliente(int codCliente) {
        this.codCliente = codCliente;
    }

    public EnumTipoEndereco getTipoEndereco() {
        return tipoEndereco;
    }

    public void setTipoEndereco(EnumTipoEndereco tipoEndereco) {
        this.tipoEndereco = tipoEndereco;
    }

    public Endereco(int id, String logradouro, String numero) {
        this.id = id;
        this.logradouro = logradouro;
        this.numero = numero;
    }

    public Endereco() {
    }
    
    public static void main(String[] args) {
        Endereco casa = new Endereco();
        casa.setLogradouro("Rua das Rosas");
        casa.setNumero("45");
        casa.setBairro("Centro");
        casa.setTipoEndereco(EnumTipoEndereco.RESIDENCIAL);
        
        System.out.println("Tipo:"
        +casa.getTipoEndereco());
    }
}
