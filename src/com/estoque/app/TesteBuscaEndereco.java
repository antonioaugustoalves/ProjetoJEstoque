/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app;

import com.estoque.app.controller.EnderecoController;
import com.estoque.app.models.Endereco;

/**
 *
 * @author ANTONIO
 */
public class TesteBuscaEndereco {
    public static void main(String[] args) {
        String nome = "Rodrigo Silva";
        Endereco endereco;
        Endereco end = new Endereco();
        EnderecoController ec = new EnderecoController(end);
        int cod = 5;
        endereco = ec.findAddressByClient(cod);
        System.out.println(endereco);
    }
}
