/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Tablas;

//import Utiles.ClasesUtiles.Interfases.AccionTabla;
import Utiles.ClasesUtiles.Interfases.editingTabla;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;

/**
 * Version 0.1
 * @author Rene
 */
public class ManejadorCellEditor implements CellEditorListener {

    private int fila;
    private int columna;
    private editingTabla accion;
    private JTable t;

    public ManejadorCellEditor(int fila, int columna, editingTabla accion, JTable t) {
        this.fila = fila;
        this.columna = columna;
        this.accion = accion;
        this.t = t;
    }

    @Override
    public void editingStopped(ChangeEvent e) {
       // System.out.println("******************************** fila="+fila+" columna="+columna);
         //System.out.println(t.getToolTipText());
        if (t.getSelectedRow() == fila&&t.getSelectedColumn()==columna) {
            accion.editingStopped(e, t, fila, columna);
        }

    }

    @Override
    public void editingCanceled(ChangeEvent e) {
        if (t.getSelectedRow() == fila&&t.getSelectedColumn()==columna) {
            accion.editingCanceled(e, t, fila, columna);
        }

    }

}
