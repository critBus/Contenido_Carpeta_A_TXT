/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Comparadores;

import Utiles.MetodosUtiles.MetodosUtiles;
import java.util.Comparator;
import static Utiles.MetodosUtiles.MetodosUtiles.StringRealmenteVacio;
import static Utiles.MetodosUtiles.MetodosUtiles.eliminarEspaciosDelPrincipioString;
import static Utiles.MetodosUtiles.MetodosUtiles.buscarNumero;
import java.io.Serializable;

/**
 * 1.4
 *
 * @author Rene
 */
public class ComparadorOrdenAlfabetico implements Comparator<String>, Serializable {

    //private boolean contrario;
    private int PRIMERO_MAYOR = 1;
    private int PRIMERO_MENOR = -1;
    private boolean prioridadNumerica = false;

    public ComparadorOrdenAlfabetico() {
        //contrario=false;
    }

    public ComparadorOrdenAlfabetico(boolean prioridadNumerica) {
        //this.contrario = contrario;
        this(prioridadNumerica, false);
    }

    public ComparadorOrdenAlfabetico(boolean prioridadNumerica, boolean contrario) {
        if (contrario) {
            PRIMERO_MAYOR = -1;
            PRIMERO_MENOR = 1;
        }
        this.prioridadNumerica = prioridadNumerica;
    }

//    private void inicializarOrden(boolean contrario){
//    if(!contrario){}
//    }
    @Override
    public int compare(String o1, String o2) {
        //System.out.println("o1="+o1+" o2="+o2);
        if (o1.equals(o2)) {
            //  System.out.println("uno");
            return 0;
        }

        if (StringRealmenteVacio(o1)) {
            return PRIMERO_MAYOR;
        }
        if (StringRealmenteVacio(o2)) {
            return PRIMERO_MENOR;
        }
        o1 = eliminarEspaciosDelPrincipioString(o1);
        o2 = eliminarEspaciosDelPrincipioString(o2);

        boolean iguales = false;
        boolean o1Mayor = false;
        if (o1.length() == o2.length()) {
            iguales = true;
        } else {
            if (o1.length() > o2.length()) {
                o1Mayor = true;
            }
        }
        //A 65-90 a 97-122   32
        //
        String mayor = o1Mayor ? o1 : o2;
        String menor = o1Mayor ? o2 : o1;
        for (int i = 0; i < menor.length(); i++) {
            char menorChar = sustituirCaracter(menor.charAt(i));
            char mayorChar = sustituirCaracter(mayor.charAt(i));
//            char menorChar = menor.charAt(i);
//            char mayorChar = mayor.charAt(i);

//            if (menorChar >= 97 && menorChar <= 122) {
//                menorChar -= 32;// System.out.println("resto1");
//            }
//            if (mayorChar >= 97 && mayorChar <= 122) {
//                mayorChar -= 32;// System.out.println("resto2");
//            }
            if (prioridadNumerica && (menorChar >= 48 && menorChar <= 57) && (mayorChar >= 48 && mayorChar <= 57)) {
                return (int) (buscarNumero(o1, i) - buscarNumero(o2, i));
            }

            if (menorChar == mayorChar) {
                continue;
            }

            if (menorChar < mayorChar) {

                if (o1Mayor) {
                    // System.out.println("dos");
                    return PRIMERO_MAYOR;
                } else {
                    //System.out.println("tres");
                    return PRIMERO_MENOR;
                }

            } else {
                if (o1Mayor) {
                    //System.out.println("cuatro");
                    return PRIMERO_MENOR;
                } else {
                    // System.out.println("cinco");
                    return PRIMERO_MAYOR;
                }

            }

        }
        // System.out.println("llego a qui");
        return o1Mayor ? PRIMERO_MAYOR : PRIMERO_MENOR;
    }

    private char sustituirCaracter(char c) {
        c = MetodosUtiles.arreglarChar(c);
        if (c >= 97 && c <= 122) {//new Character( c - 32)
            return (c -= 32);// System.out.println("resto1");
        }

        return c;
    }
}
