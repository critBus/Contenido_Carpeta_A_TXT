/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Configuraciones;

//import Utiles.ClasesUtiles.Interfases.AccionTabla;
import Utiles.ClasesUtiles.Interfases.editingTabla;
import Utiles.ClasesUtiles.Interfases.selectionFila;
import Utiles.ClasesUtiles.Tablas.Tabla;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * Version 0.1
 *
 * @author Rene
 */
public class ConfiguracionDeTabla {

    public JPanel p;
    public JPanel pTabla;
    public JScrollPane sp;
    public JTable jt;
    public Tabla t;
    public editingTabla Et;
    public selectionFila Sf;
    public int anchoSelect;
    public int anchoInformacion;
    public int[] columnasEditables;
//    public int[] anchoColumnas;

     public JScrollPane spE;

    public ConfiguracionDeTabla(JPanel p, JPanel pTabla, JScrollPane sp, JTable jt, Tabla t, editingTabla Et, selectionFila Sf, int anchoSelect, int anchoInformacion, int[] columnasEditables, JScrollPane spE) {
        this.p = p;
        this.pTabla = pTabla;
        this.sp = sp;
        this.jt = jt;
        this.t = t;
        this.Et = Et;
        this.Sf = Sf;
        this.anchoSelect = anchoSelect;
        this.anchoInformacion = anchoInformacion;
        this.columnasEditables = columnasEditables;
//        this.anchoColumnas = anchoColumnas;
        this.spE = spE;
    }
    
     public ConfiguracionDeTabla(JPanel p, JPanel pTabla, JScrollPane sp, JTable jt, Tabla t) {
        this(p, pTabla, sp, jt, t, null, null, -1, -1, null,null);
    }

//    public ConfiguracionDeTabla(JPanel p, JPanel pTabla, JScrollPane sp, JTable jt, Tabla t, editingTabla Et, int[] anchoColumnas) {
//        this(p, pTabla, sp, jt, t, Et, null, -1, -1, null, anchoColumnas,null);
//    }

    public ConfiguracionDeTabla(JPanel p, JPanel pTabla, JScrollPane sp, JTable jt, Tabla t, editingTabla Et, selectionFila Sf, int anchoSelect, int anchoInformacion, int[] columnasEditables) {
        this(p, pTabla, sp, jt, t, Et, Sf, anchoSelect, anchoInformacion, columnasEditables, null);
    }

    public ConfiguracionDeTabla(JPanel p, JPanel pTabla, JScrollPane sp, JTable jt, Tabla t, editingTabla Et, selectionFila Sf, int anchoSelect, int anchoInformacion) {
        this(p, pTabla, sp, jt, t, Et, Sf, anchoSelect, anchoInformacion, new int[]{0}, null);
    }

}
