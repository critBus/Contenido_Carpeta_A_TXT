/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Tablas;

//import Utiles.ClasesUtiles.Interfases.AccionTabla;
import Utiles.MetodosUtiles.Arreglos;
import java.awt.Point;
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ChangeEvent;
import javax.swing.table.DefaultTableModel;

/**
 * Version 0.2
 *
 * @author Rene
 */
public class Tabla extends DefaultTableModel {

    private Object informacion[][];
    private String titulos[];
    private Class[] types;
    private boolean[] canEdit;
    private int lengthInformacion;
    private int[] anchoColumnas;
    //private Manejador manejador;

    public Tabla(Object[][] informacion, String[] titulos, Class[] types, boolean[] canEdit, int lengthInformacion) {
        super(informacion, titulos);
        getDataVector().setSize(informacion.length);
        this.informacion = informacion;
        this.titulos = titulos;
        this.types = types;
        this.canEdit = canEdit;
        this.lengthInformacion = lengthInformacion;
    }

    public Tabla(Object[][] informacion, String[] titulos, Class[] types, boolean[] canEdit) {
        super(informacion, titulos);
        
         getDataVector().setSize(informacion.length);
        this.informacion = informacion;
        this.titulos = titulos;
        this.types = types;
        this.canEdit = canEdit;

    }

    public int[] getAnchoColumnas() {
        return anchoColumnas;
    }

    public void setAnchoColumnas(int[] anchoColumnas) {
        this.anchoColumnas = anchoColumnas;
    }

    public int getLengthInformacion() {
        return lengthInformacion;
    }

    @Override
    public Class getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return canEdit[columnIndex];
    }

    public static Tabla crearStringSeleccionableAmpliable(String[][] a) {
        return crearStringSeleccionableAmpliable(a, Arreglos.arregloFill(true, a.length));
    }

    public static Tabla crearStringSeleccionableAmpliable(String[][] a, boolean b[]) {
        return crearStringSeleccionable3Cols(a, b, true, true);
    }

    public static Tabla crearStringSeleccionableAmpliable(String[] a) {
        return crearStringSeleccionableAmpliable(a, Arreglos.arregloFill(true, a.length));
    }

    public static Tabla crearStringSeleccionableAmpliable(String[] a, boolean b[]) {
        return crearStringSeleccionable(a, b, true, true);
    }

    public static Tabla crearStringSeleccionable(String[] a, boolean[] selecionados) {
        return crearStringSeleccionable(a, selecionados, false);
    }

    public static Tabla crearStringSeleccionable(String[] a, boolean[] selecionados, boolean editable2columna) {
        return crearStringSeleccionable(a, selecionados, editable2columna, false);
    }

    private static Tabla crearStringSeleccionable(String[] a, boolean[] selecionados, boolean editable2columna, boolean ampliable) {
        int max = (ampliable ? 1 : 0) + a.length;
        Object informacion[][] = new Object[max][2];
        int maxLength = 0;
//         System.out.println("selecionados.length="+selecionados.length+" a.length="+a.length);
        for (int i = 0; i < max; i++) {
            if (i < a.length) {

                informacion[i][0] = selecionados[i];
                informacion[i][1] = a[i];
                //   System.out.println("a[i].length()="+a[i].length());
                if (a[i].length() > maxLength) {
                    maxLength = a[i].length();
                }
                continue;
            }
            informacion[i][0] = false;
            informacion[i][1] = "";
        }
        Class[] types = new Class[]{
            java.lang.Boolean.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean[]{
            true, editable2columna
        };
        return new Tabla(informacion, new String[]{"", ""}, types, canEdit, maxLength);
    }

    private static Tabla crearStringSeleccionable3Cols(String[][] a, boolean[] selecionados, boolean editable2columna, boolean ampliable) {
        int max = (ampliable ? 1 : 0) + a.length;
        Object informacion[][] = new Object[max][3];
        int maxLength = 0;
        for (int i = 0; i < max; i++) {
            if (i < a.length) {
                informacion[i][0] = selecionados[i];
                informacion[i][1] = a[i][0];
                informacion[i][2] = a[i][1];
                //   System.out.println("a[i].length()="+a[i].length());
                for (int j = 0; j < 2; j++) {
                    if (a[i][j].length() > maxLength) {
                        maxLength = a[i][j].length();
                    }
                }

                continue;
            }
            informacion[i][0] = false;
            informacion[i][1] = "";
            informacion[i][2] = "";
        }
        Class[] types = new Class[]{
            java.lang.Boolean.class, java.lang.String.class, java.lang.String.class
        };
        boolean[] canEdit = new boolean[]{
            true, editable2columna, editable2columna
        };
        return new Tabla(informacion, new String[]{"", "", ""}, types, canEdit, maxLength);
    }

    public static Tabla crearStringMatriz(String[][] A,int columnas) {
//        Object informacion[][] = new Object[A.length][A[0].length];
//        int maxLength = 0;
//        for (int i = 0; i < A.length; i++) {
//           
//            informacion[i][0] = false;
//            informacion[i][1] = "";
//        }
        int max[] = Arreglos.arregloFill(0, columnas);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j].length() > max[j]) {
//                    max[j] = A[i][j].length()*10;
                     max[j] = A[i][j].length();
                }
            }
        }
//        System.out.println(Arrays.toString(max));
//        String B[][]=new 
       
        Class[] types = new Class[columnas];
        Arrays.fill(types, java.lang.String.class);
//        Class[] types = new Class[]{
//            java.lang.Boolean.class, java.lang.String.class
//        };
        boolean[] canEdit = new boolean[columnas];
        Arrays.fill(canEdit, false);
//        boolean[] canEdit = new boolean[]{
//            true, editable2columna
//        };
        String tiulos[] = new String[columnas];
        Arrays.fill(tiulos, "");
        Tabla T=new Tabla(A, tiulos, types, canEdit, -1);
        T.setAnchoColumnas(max);
//        Arreglos.MostrarMatrizObject(A);
//        aa
//          System.out.println(A[0].length);
        return T;
    }

    public Object[][] getInformacion() {
        return informacion;
    }
    
//private static Tabla crearStringSeleccionable(String[] a, boolean[] selecionados, boolean editable2columna, boolean ampliable) {
//        Object informacion[][] = new Object[a.length][2];
//        int maxLength = 0;
//        for (int i = 0; i < a.length; i++) {
//            informacion[i][0] = selecionados[i];
//            informacion[i][1] = a[i];
//            //   System.out.println("a[i].length()="+a[i].length());
//            if (a[i].length() > maxLength) {
//                maxLength = a[i].length();
//            }
//        }
//        Class[] types = new Class[]{
//            java.lang.Boolean.class, java.lang.String.class
//        };
//        boolean[] canEdit = new boolean[]{
//            true, editable2columna
//        };
//        return new Tabla(informacion, new String[]{"", ""}, types, canEdit, maxLength);
//    }

    public void imprimir() {
        for (int i = 0; i < informacion[0].length; i++) {
            String fi = "";
            for (int j = 0; j < informacion.length; j++) {
                fi += informacion[j][i] + " ";
            }
            System.out.println(fi);
        }

    }
}
