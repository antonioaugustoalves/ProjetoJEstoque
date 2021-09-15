/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etoque.app.db;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author ANTONIO
 */
public class Conexao {
    final static String USER = "root";
    final static String PASSWORD = "usbw";
    final static String DATABASE = "jestoque";
    
    final static String SERVER = "jdbc:mysql://localhost:3307/"
            +DATABASE+"?useTimezone=true&serverTimeZone=UTC";
    
    final static String DRIVER="com.mysql.cj.jdbc.Driver";
    
    public static Connection getConnection() throws SQLException{
        try{
          Class.forName(DRIVER);
          return DriverManager.getConnection(SERVER, USER, PASSWORD);
        }catch(ClassNotFoundException error){
            error.getMessage();
            return null;
        }
    }
    //teste
    public static void main(String[] args) {
        try{
          Connection con = Conexao.getConnection();
            System.out.println("Servidor está online!");
        }catch(SQLException erro){
            System.out.println("Servidor offline");
        }
    }
}
