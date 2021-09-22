/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.estoque.app;

import com.estoque.app.models.Cliente;
import com.estoque.app.views.FrmPrincipal;
import javax.swing.JFrame;

/**
 *
 * @author ANTONIO
 */
public class JEstoque {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        FrmPrincipal form = new FrmPrincipal();
        form.setExtendedState(JFrame.MAXIMIZED_BOTH);
        form.setLocationRelativeTo(form);
        form.setVisible(true);
        
    }
    
}
