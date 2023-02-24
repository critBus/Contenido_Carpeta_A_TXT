/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles;

import Utiles.MetodosUtiles.Arreglos;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Un archivo con datos como su File ,Nombre ,Nombre Clave (para relacionarlo
 * con otros archivos) Version 0.1
 *
 * @author Rene
 */
public class Fichero extends NombreClave {

    private File direccion;
//    private String nombre;
//    private LinkedList<String> claves;
//    public static final String SEPARADOR = "===";

    public Fichero(File direccion, String nombre, LinkedList<String> claves) {
        super(nombre, claves);
        this.direccion = direccion;
    }

    public Fichero(File direccion, String clave, String nombre) {
        this(direccion, nombre, new LinkedList<String>(Arrays.asList(new String[]{clave})));
    }

    public File getDireccion() {
        return direccion;
    }
    
    
//
//    public String getClave() {
//        return claves.get(0);
//    }
//
//    public LinkedList<String> getClaves() {
//        return claves;
//    }
//
//    public String getNombre() {
//        return nombre;
//    }
///**
// * Si contine el separador "===" una o mas veses devuelbe un int[][] donde las
// * filas son la cantidad de separadores y en cada fila {i don cominza el separador,
// * i donde comienza despues de terminar} si no contine nigunao esta vacio leng=0
// * @param a
// * @return 
// */
//    
//    public static int[][] indicesSeparador(String a) {
//        LinkedList<LinkedList<Integer>> res = new LinkedList();
//        int pos = -1;
//        while ((pos = a.indexOf(SEPARADOR,pos==-1?0:pos+1)) != -1) {
//            res.add(new LinkedList());
//            res.getLast().add(pos);
//            res.getLast().add(pos+3);
//        }
//
//        return Arreglos.convertirLinkedListIntegerAArregloint(res);
//    }

    @Override
    public String toString() {
        return direccion.toString(); //To change body of generated methods, choose Tools | Templates.
    }
}
