/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Colecciones;

import Utiles.ClasesUtiles.Colecciones.otro.Persona;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class provar {

    public static void main(String[] args) {
        Persona p1 = new Persona("95101123451", "Roberto", "San José");
        Persona p2 = new Persona("80101123451", "Favia", "Güines");
        Persona p3 = new Persona("90101123451", "Alberto", "Güines");
        Persona p4 = new Persona("80101123451", "Pedro", "Jaruco");
        Persona p5 = new Persona("89101123451", "Juana", "Caraballo");
        Persona p6 = new Persona("80101123451", "Camilo", "Bainoa");
        Persona p[]={p1,p2,p3,p4,p5,p6};
        CNArbolBinarioB b=new CNArbolBinarioB(p1);
        for (int i = 1; i < p.length; i++) {
            b.Insert(p[i]);
        }
        LinkedList<Comparable> l=new LinkedList<Comparable>();
      //  b.RecorridoEntreOrden(l);
        b.Busqueda(l,new Persona("80101123451", "Camilo", "Bainoa") );
        for (Comparable l1 : l) {
            System.out.println(l1);
        }
    }
}
