/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.etoque.db;

import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author ANTONIO
 */
public class Conexao {
    final static String USER = "root";
    final static String PASSWORD = "usbw";
    final static String DATABASE = "jestoque";
    final static String SERVER = "jdbc:mysql://localhost:3307/"
            +DATABASE+"?useTimezone=true&serverTimezone=UTC";
    final static String DRIVER = "com.mysql.cj.jdbc.Driver";
    
    public static Connection getConnection() throws SQLException{
        
    }
    
    
    
}
