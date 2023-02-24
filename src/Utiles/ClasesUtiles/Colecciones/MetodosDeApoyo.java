/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Colecciones;

import Utiles.Exepciones.*;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

//import solitario.Carta;

/**
 *
 * @author Rene
 */
public abstract class MetodosDeApoyo implements Serializable {

    public static <E> void vaciarColaAenPilaB(Cola<E> A, Pila<E> B) {
        while (!A.empty()) {
            try {
                B.push(A.dequeue());
              
            } catch (ExedeCapacidadException ex) {

            }
        }
    }
    
    public static <E> void vaciarPilaAenColaB(Pila<E> A, Cola<E> B) {
        while (!A.empty()) {
            try {
                B.enqueue(A.pop());
            } catch (ExedeCapacidadException ex) {

            }
        }
    }

    public static <E> void vaciarPilaAenBMismoOrden(Pila<E> A, Pila<E> B) {
        Pila<E> C = new Pila<E>();
        try {
            while (!A.empty()) {
                C.push(A.pop());
            }
            while (!C.empty()) {
                B.push(C.pop());
            }
        } catch (Exception ex) {

        }
    }

    public static <E> Pila<E> sacarSubPila(Pila<E> A, int indice) throws ExedeCapacidadException {
      //  int cantidadATomar = A.Size() - indice;
        int cantidadATomar =  indice+1;
        Pila<E> pila2 = new Pila<E>();
        while (cantidadATomar-- != 0) {
            pila2.push(A.pop());
        }
        Pila<E> pila3 = new Pila<E>();
        while (!pila2.empty()) {
            pila3.push(pila2.pop());
        }
        return pila3;
    }

}
