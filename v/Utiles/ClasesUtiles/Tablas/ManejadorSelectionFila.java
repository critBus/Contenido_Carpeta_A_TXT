/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Tablas;

import Utiles.ClasesUtiles.Interfases.selectionFila;
import java.awt.event.MouseEvent;
import javax.swing.JTable;
import javax.swing.event.MouseInputListener;
import javax.swing.plaf.basic.BasicDesktopIconUI;

/**
 * Version 0.1
 * @author Rene
 */
public class ManejadorSelectionFila extends javax.swing.event.MouseInputAdapter {

  
    private selectionFila accion;
    private JTable t;

    public ManejadorSelectionFila(selectionFila accion, JTable t) {
        this.accion = accion;
        this.t = t;
    }

    

    @Override
    public void mousePressed(MouseEvent e) {
       accion.mousePressed(e, t);
    }

}
