/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.controller;

import com.estoque.app.models.Endereco;
import com.estoque.app.services.BancoService;
import com.etoque.app.db.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ANTONIO
 */
public class EnderecoController implements BancoService{
    private Endereco endereco;

    public EnderecoController(Endereco endereco) {
        this.endereco = endereco;
    }
    
    public static void main(String[] args) {
        
    }
    
    

    @Override
    public boolean save() {
         String sql = "INSERT INTO endereco "
                 + "(logradouro, estado) "
                + "VALUES (?,?)";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cidade.getCidade());
            pst.setString(2, cidade.getEstado());
            pst.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public boolean update() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean findById() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
