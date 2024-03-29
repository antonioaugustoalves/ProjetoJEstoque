/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.controller;

import com.estoque.app.models.Cidade;
import com.estoque.app.models.Endereco;
import com.estoque.app.models.EnumTipoEndereco;
import com.estoque.app.services.BancoService;
import com.etoque.app.db.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author ANTONIO
 */
public class EnderecoController implements BancoService {

    private Endereco endereco;

    public EnderecoController(Endereco endereco) {
        this.endereco = endereco;
    }

    public static void main(String[] args) {
        Endereco casa = new Endereco();
        casa.setId(1);
        EnderecoController bean = new EnderecoController(casa);
        
        bean.findById();

    }

    @Override
    public boolean save() {
        String sql = "INSERT INTO endereco "
                + "(logradouro, numero, complemento,"
                + "bairro,cep,cod_cidade,tipo_endereco, cod_cliente) "
                + "VALUES (?,?,?,?,?,?,?,?)";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, endereco.getLogradouro());
            pst.setString(2, endereco.getNumero());
            pst.setString(3, endereco.getComplemento());
            pst.setString(4, endereco.getBairro());
            pst.setString(5, endereco.getCep());
            pst.setInt(6, endereco.getCodCidade());
            pst.setString(7, endereco.getTipoEndereco().toString());
            pst.setInt(8, endereco.getCodCliente());
            pst.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public boolean update() {
        String sql = "UPDATE endereco SET "
                + "logradouro = ?, "
                + "numero = ?, complemento = ?,"
                + "bairro = ?,cep = ?,"
                + "cod_cidade = ?,"
                + "tipo_endereco = ?, cod_cliente = ? "
                + "WHERE id = ?";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, endereco.getLogradouro());
            pst.setString(2, endereco.getNumero());
            pst.setString(3, endereco.getComplemento());
            pst.setString(4, endereco.getBairro());
            pst.setString(5, endereco.getCep());
            pst.setInt(6, endereco.getCodCidade());
            pst.setString(7, endereco.getTipoEndereco().toString());
            pst.setInt(8, endereco.getCodCliente());
            pst.setInt(9, endereco.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete() {
        String sql = "DELETE FROM  endereco "
                + "WHERE id = ?";

        try {
            Connection con = Conexao.getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, endereco.getId());
            pst.executeUpdate();
            return true;
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }

    @Override
    public boolean findById() {
        String sql = "SELECT logradouro, numero, complemento, "
                + "bairro, cep, cod_cidade, tipo_endereco, "
                + "cod_cliente FROM endereco "
                + "where id=" + endereco.getId();
        try {
            Connection con = Conexao.getConnection();
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            rs.next();
            if (rs.getRow() > 0) {
                endereco.setLogradouro(rs.getString("logradouro"));
                endereco.setNumero(rs.getString("numero"));
                endereco.setComplemento(rs.getString("complemento"));
                endereco.setCep(rs.getString("cep"));
                endereco.setBairro(rs.getString("bairro"));
                endereco.setCodCidade(rs.getInt("cod_cidade"));
                /*
                if(rs.getString("tipo_endereco").equals("COMERCIAL")){
                    endereco.setTipoEndereco(EnumTipoEndereco.COMERCIAL);
                }else{
                    endereco.setTipoEndereco(EnumTipoEndereco.RESIDENCIAL);
                }*/
                
                String tipo = rs.getString("tipo_endereco");
                endereco.setTipoEndereco(
                        tipo.equals("RESIDENCIAL")?
                                EnumTipoEndereco.RESIDENCIAL:
                                EnumTipoEndereco.COMERCIAL);
                endereco.setCodCliente(rs.getInt("cod_cliente"));
                
                return true;
            } else {
                return false;
            }
        } catch (SQLException erro) {
            System.out.println(erro.getMessage());
            return false;
        }
    }
    
    
    public Endereco findAddressByClient(String nome){
        String sql = "SELECT e.id," +
"	e.logradouro," +
"	e.numero, "+
"	e.complemento," +
"	e.bairro, " +
"	e.cep, " +
"	cid.cidade," +
"	e.tipo_endereco," +
"       e.cod_cidade, "+                
"	cli.nome " +
"        from " +
"	endereco e, " +
"	cidade cid, " +
"	cliente cli " +
"       where e.cod_cidade = cid.id " +
"       and e.cod_cliente = cli.id " +
"       and upper(cli.nome) like upper('%"+nome+"%')";
        
        Endereco enderecoProcurado = new Endereco();//objeto vazio
        try{
         Connection con = Conexao.getConnection();
         Statement stm = con.createStatement();
         ResultSet rs = stm.executeQuery(sql);
         rs.next(); //Pega o resultado encontrado.
         
         if(rs.getRow() > 0){
             
             enderecoProcurado
                     .setLogradouro(rs.getString("e.logradouro"));
             enderecoProcurado.setNumero(rs.getString("e.numero"));
             enderecoProcurado
                     .setComplemento(rs.getString("e.complemento"));
             enderecoProcurado.setBairro(rs.getString("e.bairro"));
             enderecoProcurado.setCodCidade(rs.getInt("e.cod_cidade"));
             enderecoProcurado.setCep(rs.getString("e.cep"));
             String tipoEndereco = rs.getString("e.tipo_endereco");
         
             enderecoProcurado.setTipoEndereco(
                     tipoEndereco.equals("COMERCIAL")?EnumTipoEndereco.COMERCIAL
                             :EnumTipoEndereco.RESIDENCIAL);
             
             
         }
        }catch(SQLException erro){
            erro.printStackTrace();
            return null;
        }
        return enderecoProcurado;
        
    }
    
    
    public Endereco findAddressByClient(int cod){
        String sql = "SELECT e.id," +
"	e.logradouro," +
"	e.numero, "+
"	e.complemento," +
"	e.bairro, " +
"	e.cep, " +
"	cid.cidade," +
"	e.tipo_endereco," +
"       e.cod_cidade, "+                
"	cli.nome " +
"        from " +
"	endereco e, " +
"	cidade cid, " +
"	cliente cli " +
"       where e.cod_cidade = cid.id " +
"       and e.cod_cliente = cli.id " +
"       and e.cod_cliente = "+ cod;
        
        Endereco enderecoProcurado = new Endereco();//objeto vazio
        try{
         Connection con = Conexao.getConnection();
         Statement stm = con.createStatement();
         ResultSet rs = stm.executeQuery(sql);
         rs.next(); //Pega o resultado encontrado.
         
         if(rs.getRow() > 0){
             
             enderecoProcurado
                     .setLogradouro(rs.getString("e.logradouro"));
             enderecoProcurado.setNumero(rs.getString("e.numero"));
             enderecoProcurado
                     .setComplemento(rs.getString("e.complemento"));
             enderecoProcurado.setBairro(rs.getString("e.bairro"));
             enderecoProcurado.setCodCidade(rs.getInt("e.cod_cidade"));
             enderecoProcurado.setCep(rs.getString("e.cep"));
             String tipoEndereco = rs.getString("e.tipo_endereco");
         
             enderecoProcurado.setTipoEndereco(
                     tipoEndereco.equals("COMERCIAL")?EnumTipoEndereco.COMERCIAL
                             :EnumTipoEndereco.RESIDENCIAL);
             
             
         }
        }catch(SQLException erro){
            erro.printStackTrace();
            return null;
        }
        return enderecoProcurado;
        
    }

}
