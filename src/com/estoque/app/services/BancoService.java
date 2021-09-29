/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.services;

/**
 *
 * @author ANTONIO
 */
public interface BancoService {
    public boolean save();
    public boolean update();
    public boolean delete();
    public boolean findById();
    
}
