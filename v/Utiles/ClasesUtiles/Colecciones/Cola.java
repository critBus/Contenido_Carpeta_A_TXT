/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Colecciones;
import Utiles.Exepciones.*;
import java.io.Serializable;
import java.util.Stack;


/**
 *
 * @author Rene
 */
public class Cola<E> implements Serializable{

    private Lista<E> cola;

    public Cola(Lista<E> cola) {
        this.cola = cola;
    }

    public Cola(Cola<E> cola) {
        while (!cola.empty()) {
            try {
                enqueue(cola.dequeue());
            } catch (Exception ex) {
             
            }
        }
    }

    public Cola() {
        this.cola = new Lista<E>();
    }

    public void enqueue(E objeto) {
        cola.add(objeto);
        //cantidadDeNodos++;
    }

    public E dequeue() throws ExedeCapacidadException {
        // cantidadDeNodos++;
        return cola.removeFirst();
    }

    public int Size() {
        return cola.Size();
    }

    public void clear() {
        cola.clear();
    }

    public boolean empty() {
        return cola.empty();
    }
     public E mirar(int indice) {
        return cola.get(indice);
    }

    //************
//    public void addAll(Cola<E>... A) throws Exception {
//        for (int i = 0; i < A.length; i++) {
//            while (!A[i].empty()) {
//                enqueue(A[i].dequeue());
//            }
//        }
//
//    }
//
//    public void addAll(Pila<E>... A) throws Exception {
//        for (int i = 0; i < A.length; i++) {
//            while (!A[i].empty()) {
//                enqueue(A[i].pop());
//            }
//        }
//    }
    public E Front() {
        return cola.getPrimero();
    }

    public void imprimir() {
        cola.imprimir("cola");
    }
    
    //**************
     public void addAll(Cola<E>... A) {
        for (int i = 0; i < A.length; i++) {
            while (!A[i].empty()) {
                try {
                    enqueue(A[i].dequeue());
                } catch (ExedeCapacidadException ex) {
                  //  Logger.getLogger(Cola.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    public void addAll(Stack<E>... A) {
        for (int i = 0; i < A.length; i++) {
        while (!A[i].empty()) {
            enqueue(A[i].pop());
        }
        }
    }
    
}
