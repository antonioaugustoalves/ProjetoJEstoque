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
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

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
        String sql = "UPDATE categoria SET nome = ? "
                +"WHERE id = ?";
        try{
          Connection con = Conexao.getConnection();
          PreparedStatement ps = con.prepareStatement(sql);
          ps.setString(1,categoria.getNome());
          ps.setInt(2, categoria.getId());
          ps.executeUpdate();
          return true;
        }catch(SQLException error){
            error.printStackTrace();
            return false; 
        }
        
    }

    @Override
    public boolean delete() {
        
        String sql = "DELETE FROM categoria WHERE id = ?";
        try{
           Connection con = Conexao.getConnection();
           PreparedStatement ps = con.prepareStatement(sql);
           ps.setInt(1,categoria.getId());
           ps.executeUpdate();
           return true;
        }catch(SQLException erro){
            erro.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean findById() {
         String sql = "SELECT id, nome FROM categoria "
                 +"WHERE id = "+categoria.getId();
         try{
             Connection con = Conexao.getConnection();
             Statement stm = con.createStatement();
             ResultSet rs = stm.executeQuery(sql);
             rs.next();
             
             if(rs.getRow() > 0){
                 categoria.setId(rs.getInt("id"));
                 categoria.setNome(rs.getString("nome"));
                 return true;
             }else{
                 System.out.println("Nenhum resultado");
                 return false;
             }
             
         }catch(SQLException error){
             error.printStackTrace();
             return false;
         }
    }

    @Override
    public List<Categoria> findAll() {
       String sql = "SELECT id, nome FROM categoria "
               +"ORDER BY nome";
       List<Categoria> categorias = new ArrayList<>();
       
       try{
          Connection con = Conexao.getConnection();
          Statement stm = con.createStatement();
           ResultSet rs = stm.executeQuery(sql);
          
          while(rs.next()){
              Categoria cat = new Categoria();
              cat.setId(rs.getInt("id"));
              cat.setNome(rs.getString("nome"));
              categorias.add(cat);
          }
          return categorias;
       }catch(SQLException error){
           error.printStackTrace();
           return null;
       }
       
    }
    
    public static void main(String[] args) {
        List<Categoria> lista;
        Categoria cat = new Categoria();
        CategoriaController controller =
                new CategoriaController(cat);
        lista = controller.findAll();
        for(Categoria c:lista){
            System.out.println("Id:"+ c.getId());
            System.out.println("Nome:"+ c.getNome());
            System.out.println("-----------------------");
        }
    }
}
