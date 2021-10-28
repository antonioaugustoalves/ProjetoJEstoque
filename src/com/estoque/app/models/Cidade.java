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
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ANTONIO
 */
public class Cidade {

    private int id;
    private String cidade;
    private String estado;

    public Cidade() {
    }

    public Cidade(String cidade, String estado) {
        this.cidade = cidade;
        this.estado = estado;
    }

    public Cidade(int id, String cidade, String estado) {
        this.id = id;
        this.cidade = cidade;
        this.estado = estado;
    }
    
    
    
    
    
    

    @Override
    public String toString() {
        return this.cidade+
                "("+this.estado+")";
    }
    
    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    public static void carregaCombo(JComboBox combo, int id) {
        String sql = "SELECT id, cidade, estado FROM cidade ORDER BY cidade";
        try {
            Connection con = Conexao.getConnection();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            
            combo.removeAllItems();//Apaga todos os itens
            combo.addItem("SELECIONE A CIDADE");
            Cidade cidadeSelecionada = null;
            
            //Preenchendo o JComboBox
            while (rs.next()) {
                Cidade cid = new Cidade(rs.getInt("id"),
                        rs.getString("cidade").toUpperCase(),
                        rs.getString("estado").toUpperCase());
                
                if (id == rs.getInt("id")) {
                    cidadeSelecionada = cid;
                }
                combo.addItem(cid);
            }
            
            if (cidadeSelecionada != null) {
                combo.setSelectedItem(cidadeSelecionada);
            }
        } catch (SQLException error) {
            JOptionPane.showMessageDialog(null,
                    "Erro ao carregar a lista de cidades.");
        }
        
    }
   

}
