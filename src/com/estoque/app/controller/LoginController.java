/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

import com.estoque.app.models.Usuario;
import com.etoque.app.db.Conexao;


/**
 *
 * @author ANTONIO
 */
public class LoginController {
    private Usuario usuario;
    

    public LoginController(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Usuario efetuarLogin(){
       
        String sql = "SELECT nome, login, senha "
                +"FROM usuario WHERE "
                +"login = '"+usuario.getLogin()+"' AND "
                +"senha = '"+usuario.getSenha()+"'";
        
        try{
           Connection con = Conexao.getConnection();
           Statement stm = con.createStatement();
           ResultSet rs = stm.executeQuery(sql);
           Usuario usuarioLogado;
           rs.next();
           if(rs.getRow() > 0){
               usuarioLogado = new Usuario(
                       rs.getString("nome"),
                       rs.getString("login"),
                       rs.getString("senha"));
               return usuarioLogado;
           }else{
               return null;
           }
        }catch(SQLException erro){
            System.out.println("Usuario não cadastrado");
            return null;
        }
    }
    
    public static void main(String[] args) {
        String login = "vendas";
        String senha = "2021";
        
        Usuario user = new Usuario();
        user.setLogin(login);
        user.setSenha(senha);
        
        LoginController lc = new LoginController(user);
        
        Usuario usuarioLogado = lc.efetuarLogin();
        
        if(usuarioLogado != null){
            System.out.println("Login realizado com sucesso.");
            System.out.println("Bem vindo, "+ 
                    usuarioLogado.getNome());
        }else{
            System.out.println("Senha ou Usuario incorretos");
        }
    }
    
}
