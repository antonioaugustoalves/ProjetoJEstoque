/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.controller;

import com.estoque.app.models.Cliente;
import com.estoque.app.services.BancoService;
import com.etoque.app.db.Conexao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class ClienteController implements BancoService{
    private Cliente cliente;

    public ClienteController(Cliente cliente) {
        this.cliente = cliente;
    }
    
    public static void main(String[] args) {
        Cliente cli = new Cliente();
        cli.setNome("Joe Nobody");
        cli.setTelefone("79953-2378");
        cli.setEmail("joseph.nobody@darkweb.us");
        cli.setCpf("12323413333");
        cli.setId(3);
        
        ClienteController bean = new ClienteController(cli);
        /**
        if(bean.delete()){
            System.out.println("Sucesso");
        }else{
            System.out.println("Erro.");
        }**/
        
       int total = bean.counterRecord();
       
        System.out.println("Last:"+total);
        
        
    }
    
    public int getLastIndex(){
        int id = 0;
        String sql = "select max(id) from "
                +"cliente";
        try{
            Connection con = Conexao.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            id = rs.getInt("max(id)");
        }catch(SQLException erro){
            erro.printStackTrace();
        }
        
        return id;
    }
    
    public int counterRecord(){
        int total = 0;
        String  sql= "SELECT count(*) from cliente";
        try{
            Connection con = Conexao.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);
            rs.next();
            total = rs.getInt("count(*)");
        }catch(SQLException erro){
            erro.printStackTrace();
        }
        
        return total;
    }

    @Override
    public boolean save() {
         String sql = "INSERT INTO cliente (nome, "
                 + "telefone, email, data_nasc, cpf) "
                + "VALUES (?,?,?,?,?)";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,cliente.getNome());
            pst.setString(2, cliente.getTelefone());
            pst.setString(3, cliente.getEmail());
            pst.setDate(4, (Date) cliente.getDataNasc());
            pst.setString(5, cliente.getCpf());
            pst.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public boolean update() {
         String sql = "UPDATE cliente SET "
                + "nome = ?, telefone = ?, "
                + "email = ?, data_nasc = ?, cpf = ? "
                 +"WHERE id = ?";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1,cliente.getNome());
            pst.setString(2, cliente.getTelefone());
            pst.setString(3, cliente.getEmail());
            pst.setDate(4, (Date) cliente.getDataNasc());
            pst.setString(5, cliente.getCpf());
            pst.setInt(6, cliente.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete() {
         String sql = "DELETE from cliente WHERE id = ?";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, cliente.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public boolean findById() {
        String sql = "select  nome, telefone, email,  "
                +"data_nasc, cpf"
                + "from cliente where id=" + cliente.getId();
        try {
            Connection con = Conexao.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            if (rs.getRow() > 0) {
                cliente.setNome(rs.getString("nome"));
                cliente.setTelefone(rs.getString("telefone"));
                cliente.setEmail(rs.getString("email"));
                cliente.setDataNasc(rs.getDate("data_nasc"));
                cliente.setCpf(rs.getString("cpf"));
                return true;
            } else {
                return false;
            }
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }
    
    
}
