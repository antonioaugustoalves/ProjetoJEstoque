/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.controller;

import com.estoque.app.models.Categoria;
import com.estoque.app.services.CategoriaService;
import com.etoque.app.db.Conexao;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author ANTONIO
 */
public class CategoriaController implements CategoriaService{
    Categoria categoria = new Categoria();//model

    public CategoriaController(Categoria categoria) {
        this.categoria = categoria;
    }
    
    
    @Override
    public boolean save() {
        String sql = "INSERT INTO categoria(nome) "
                +"VALUES(?)";
        try{
         Connection con = Conexao.getConnection();
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, categoria.getNome());
         ps.executeUpdate();
         return true;
        
        }catch(SQLException error){
            error.printStackTrace();
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

    @Override
    public List<Categoria> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public static void main(String[] args) {
        Categoria cat1 = new Categoria();
        cat1.setNome("Livros");
        
        CategoriaController controller = new CategoriaController(cat1);
        
        if(controller.save()){
            System.out.println("Sucesso");
        }else{
            System.out.println("Erro ao salvar");
        }
    }
}
