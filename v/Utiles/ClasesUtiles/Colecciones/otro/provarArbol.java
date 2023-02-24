/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Colecciones.otro;

/**
 *
 * @author Rene
 */
public class provarArbol {
    public static void main(String[] args) {
        int a[]={15,6,3,12,10,7,13,16,20,18,23};
        CNArbolBinarioB b=new CNArbolBinarioB(a[0]);
        for (int i = 1; i < a.length; i++) {
            b.Insert(a[i]);
        }
        System.out.println(b.Intervalo2(b, 1 ,4));
        System.out.println(b.Menores(1));
        
       
        
        
    }
}
