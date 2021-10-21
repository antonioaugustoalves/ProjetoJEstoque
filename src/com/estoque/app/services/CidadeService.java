/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app.services;

import com.estoque.app.models.Cidade;
import java.util.List;

/**
 *
 * @author ANTONIO
 */
public interface CidadeService extends BancoService{
    List<Cidade> listarCidades();
    Cidade buscarCidade(int id);
    
}
