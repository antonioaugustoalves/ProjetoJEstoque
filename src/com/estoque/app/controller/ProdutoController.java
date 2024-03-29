/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.controller;

import com.estoque.app.models.Produto;
import com.estoque.app.services.ProdutoService;
import com.etoque.app.db.Conexao;
import java.util.ArrayList;
import java.util.List;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.PreparedStatement;

/**
 *
 * @author ANTONIO
 */
public class ProdutoController implements ProdutoService {

    public static void main(String[] args) {
        Produto pro = new Produto();
        pro.setId(1);
        ProdutoController pc
                = new ProdutoController(pro);

        if (pc.findById()) {
            System.out.println("id:"+ pro.getId());
            System.out.println("Produto:"+ pro.getNome());
            System.out.println("Preço:"+ pro.getPreco());
            System.out.println("Estoque:"+ pro.getQuantidade());
        } else {
            System.out.println("Não existem resultados.");
        }

    }
    private Produto produto = new Produto();

    public ProdutoController(Produto produto) {
        this.produto = produto;
    }

    @Override
    public List<Produto> findAll() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT id, nome, quantidade "
                + "preco, cod_categoria "
                + "FROM produto";
        try {
            Connection con = Conexao.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql);

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getInt("id"));
                prod.setNome(rs.getString("nome"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setQuantidade(rs.getDouble("quantidade"));
                prod.setIdCategoria(rs.getInt("cod_categoria"));

                produtos.add(prod);
            }
        } catch (SQLException error) {
            error.printStackTrace();
        }

        return produtos;
    }

    @Override
    public boolean save() {

        String sql = "INSERT INTO produto "
                + "(nome, preco, quantidade, cod_categoria) "
                + "values (?,?,?,?)";
        try {
            Connection con = Conexao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setDouble(3, produto.getQuantidade());
            ps.setInt(4, produto.getIdCategoria());
            ps.executeUpdate();
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            return false;
        }

    }

    @Override
    public boolean update() {
        String sql = "UPDATE produto SET "
                + "nome = ?, preco = ?, "
                + "quantidade = ?, cod_categoria = ? "
                +"WHERE id = ?";
        try {
            Connection con = Conexao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setDouble(2, produto.getPreco());
            ps.setDouble(3, produto.getQuantidade());
            ps.setInt(4, produto.getIdCategoria());
            ps.setInt(5, produto.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete() {
         String sql = "DELETE FROM produto "
                 +"WHERE id = ?";
         try {
            Connection con = Conexao.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, produto.getId());
            ps.executeUpdate();
            return true;
        } catch (SQLException error) {
            error.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean findById() {
         String sql = "SELECT "
                 + "nome, preco, quantidade, cod_categoria "
                 + "FROM produto WHERE id = " + produto.getId();
         try {
            Connection con = Conexao.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(sql); 
            rs.next();
            if(rs.getRow() > 0){
                produto.setNome(rs.getString("nome"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setQuantidade(rs.getDouble("quantidade"));
                produto.setIdCategoria(rs.getInt("cod_categoria"));
                return true;
            }else{
                System.out.println("Nenhum produto encontrado");
                return false;
            }
            
        } catch (SQLException error) {
            error.printStackTrace();
            return false;
        }
    }

}
