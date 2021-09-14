/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app;

import com.estoque.app.models.Cliente;

/**
 *
 * @author ANTONIO
 */
public class JEstoque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Cliente cli = new Cliente();
        cli.setId(101);
        cli.setNome("Antonio Alves");
        cli.setEstado("RS");
        cli.setEndereco("Rua General Osório, 12");
        cli.setIdade(39);
        
        Cliente cli2 = new Cliente(12,
                "Ezio Auditore Da Firenze", 
                "Rua Italia, 160", "RS", 22);
        
        cli.exibirCliente();
        cli2.exibirCliente();
        
    }
    
}
