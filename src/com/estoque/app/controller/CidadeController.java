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
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author ANTONIO
 * @version 1.0.0
 * @since 20/10/2021
 */
public class CidadeController implements CidadeService {

    private Cidade cidade;

    /**
     * Este construtor inicializa o controler para a utilização do aplicativo
     * com o banco de dados.
     *
     * @param args
     * @param cidade
     */
    public static void main(String[] args) {
        Cidade cid = new Cidade();
        cid.setId(3);
        CidadeController bean = new CidadeController(cid);
        if(bean.findById()){
            System.out.println(cid.getCidade());
            System.out.println(cid.getEstado());
        }
        
    }

    public CidadeController(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public List<Cidade> listarCidades() {
        String sql = "select id, cidade,  estado "
                + "from cidade";
        List<Cidade> cidades = new ArrayList<>();

        try {
            Connection con = Conexao.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                Cidade cid = new Cidade(
                        rs.getInt("id"),
                        rs.getString("cidade"),
                        rs.getString("estado")
                );

                cidades.add(cid);
            }

            return cidades;

        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return null;
        }
    }

    @Override
    public Cidade buscarCidade(int id) {
        String sql = "select id, cidade, estado "
                + "from cidade where id=" + id;
        try {
            Connection con = Conexao.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            if (rs.getRow() > 0) {
                Cidade cidade = new Cidade(rs.getInt("id"),
                        rs.getString("cidade"), rs.getString("estado"));
                return cidade;
            } else {
                return null;
            }
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return null;
        }
    }

    @Override
    /**
     * Este método realiza a conexão com o banco de dados e envia as informações
     * para serem salvas na tabela Cidade.
     *
     */
    public boolean save() {
        String sql = "INSERT INTO cidade (cidade, estado) "
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
        String sql = "UPDATE cidade SET "
                + "cidade = ?, estado = ? "
                + "WHERE id = ?";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, cidade.getCidade());
            pst.setString(2, cidade.getEstado());
            pst.setInt(3, cidade.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete() {
        String sql = "DELETE from cidade WHERE id = ?";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, cidade.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public boolean findById() {
        String sql = "select id, cidade, estado "
                + "from cidade where id=" + cidade.getId();
        try {
            Connection con = Conexao.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            if (rs.getRow() > 0) {
               cidade.setCidade(rs.getString("cidade"));
               cidade.setEstado(rs.getString("estado"));
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
