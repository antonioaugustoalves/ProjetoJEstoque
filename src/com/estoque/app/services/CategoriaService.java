/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.services;

import com.estoque.app.models.Categoria;
import java.util.List;

/**
 *
 * @author ANTONIO
 */
public interface CategoriaService {
    public boolean save();
    public boolean update();
    public boolean delete();
    public boolean findById();
    public List<Categoria> findAll();
}
