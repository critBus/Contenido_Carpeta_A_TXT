/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Interfases;

import javax.swing.JTable;
import javax.swing.event.ChangeEvent;

/**
 *
 * @author Rene
 */
public interface editingTabla {

    public void editingStopped(ChangeEvent e,JTable t, int fila, int columna);

    public void editingCanceled(ChangeEvent e,JTable t, int fila, int columna);
}
