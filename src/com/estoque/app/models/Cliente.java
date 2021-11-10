/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.models;

import com.etoque.app.db.Conexao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ANTONIO
 */
public class Cliente {
    private int id;
    private String nome;
    private String telefone;
    private String email;
    private String cpf;
    private Date dataNasc;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public Cliente() {
    }

    public Cliente(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    @Override
    public String toString() {
        return this.id+" - "+ this.nome;
    }
    
    public static void carregaCombo(JComboBox combo, int id) {
        String sql = "SELECT id, nome from cliente order by id";
        try {
            Connection con = Conexao.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            combo.removeAllItems();//Apaga todos os itens
            combo.addItem("SELECIONE O CLIENTE");
            Cliente clienteSelecionado = null;
            
            //Preenchendo o JComboBox
            while (rs.next()) {
                Cliente cli = new Cliente(rs.getInt("id"),
                        rs.getString("nome").toUpperCase());
                
                if (id == rs.getInt("id")) {
                    clienteSelecionado = cli;
                }
                combo.addItem(cli);
            }
            
            if (clienteSelecionado != null) {
                combo.setSelectedItem(clienteSelecionado);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar a lista de clientes.");
        }
        
    }
    
}
