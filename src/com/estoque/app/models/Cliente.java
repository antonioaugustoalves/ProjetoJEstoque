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
public class Cliente {
    private int id;
    private String nome;
    private String endereco;
    private String estado;
    private int idade;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
    
    public void exibirCliente(){
        System.out.println("--------------------");
        System.out.println("Id:"+ this.id);
        System.out.println("Name:"+ this.nome);
        System.out.println("Address:"+ this.endereco);
        System.out.println("State:"+ this.estado);
        System.out.println("Age:"+ this.idade);
        System.out.println("--------------------");
    }

    @Override
    public String toString() {
        return "Id:"+this.id+"\n"
                +"Nome: "+this.nome+"\n"
                +"Endereço: "+this.endereco+"\n"
                +"Idade: "+this.idade;
    }
    
    public Cliente(){
        
    }
    
    public Cliente(int id, String nome, 
            String endereco, String estado, int idade){
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
        this.estado = estado;
        this.idade = idade;
    }
    
    
}
