/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.controller;

import com.estoque.app.models.Cidade;
import com.estoque.app.services.CidadeService;
import com.etoque.app.db.Conexao;
import java.util.ArrayList;
import java.util.List;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ANTONIO
 * @version 1.0.0
 * @since 20/10/2021
 */
public class CidadeController implements CidadeService{
    private Cidade cidade;
    
    /**
     * Este construtor inicializa o controler para a utilização
     * do aplicativo com o banco de dados.
     * @param cidade 
     */
    public static void main(String[] args) {
        Cidade c = new Cidade();
        List<Cidade> cidades = 
                new CidadeController(c).listarCidades();
        for(Cidade cid:cidades){
            System.out.println(cid);
            System.out.println("-------------------");
        }
    }
    
    
    public CidadeController(Cidade cidade){
        this.cidade = cidade;
    }

    @Override
    public List<Cidade> listarCidades() {
        String sql = "select id, cidade,  estado "
                +"from cidade";
        List<Cidade> cidades = new ArrayList<>();
        
        try{
          Connection con = Conexao.getConnection();
          Statement st = con.createStatement();
          ResultSet rs = st.executeQuery(sql);
          
          while(rs.next()){
              Cidade cid = new Cidade(
                      rs.getInt("id"),
                      rs.getString("cidade"),
                      rs.getString("estado")
              );
             
              cidades.add(cid);
          }
          
          return cidades;
          
        }catch(SQLException erro){
            System.out.println(erro.getMessage());
            return null;
        }
    }

    @Override
    public Cidade buscarCidade(int id) {
        String sql = "select id, cidade, estado "
                +"from cidade where id="+id;
        try{
        Connection con = Conexao.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        if(rs.getRow()> 0){
            Cidade cidade = new Cidade(rs.getInt("id"),
            rs.getString("cidade"), rs.getString("estado"));
            return cidade;
        }else{
            return null;
        }
        }catch(SQLException erro){
            System.out.println(erro.getMessage());
            return null;
        }
    }

    @Override
    /**
     * Este método realiza a conexão com o banco
     * de dados e envia as informações para serem
     * salvas na tabela Cidade.
     *
     */
    public boolean save() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
