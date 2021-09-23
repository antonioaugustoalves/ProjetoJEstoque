/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.models;

import com.etoque.app.db.Conexao;
import javax.swing.JComboBox;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.Connection;
import javax.swing.JOptionPane;

/**
 *
 * @author ANTONIO
 */
public class Categoria {

    private int id;
    private String nome;
    
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
    
    public Categoria(int id, String nome) {
        this.id = id;
        this.nome = nome;
    }
    
    public Categoria() {
    }
    
    @Override
    public String toString() {
        return this.id + " - " + this.nome;
    }
    
    public static void carregaCombo(JComboBox combo, int id) {
        String sql = "SELECT id, nome FROM categoria"
                + " ORDER BY nome";
        try {
            Connection con = Conexao.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            combo.removeAllItems();//Apaga todos os itens
            combo.addItem("SELECIONE A CATEGORIA");
            Categoria categoriaSelecionada = null;
            
            while (rs.next()) {
                Categoria cat = new Categoria(rs.getInt("id"),
                        rs.getString("nome").toUpperCase());
                
                if (id == rs.getInt("id")) {
                    categoriaSelecionada = cat;
                }
                combo.addItem(cat);
            }
            
            if (categoriaSelecionada != null) {
                combo.setSelectedItem(categoriaSelecionada);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar as categorias.");
        }
        
    }
}
