/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Colecciones;
import static Utiles.ClasesUtiles.Colecciones.MetodosDeApoyo.*;
import Utiles.Exepciones.*;
import java.io.Serializable;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 *
 * @author Rene
 */
public class Pila<E> implements Serializable{

    private Lista<E> pila;

    public Pila() {
        pila = new Lista<E>();
    }

    public Pila(Lista<E> pila) {
        this.pila = pila;
    }

    public Pila(Pila<E> pila) {
        //Cola<E> cola = new Cola<E>();
        //Pila<E> pila2 = new Pila<E>();
        pila = new Pila<E>();
        try {
            vaciarPilaAenBMismoOrden(pila, this);
//            while (!pila.empty()) {
//                pila2.push(pila.pop());
//            }
//            while (!pila2.empty()) {
//                push(pila2.pop());
//            }

//            while (!pila.empty()) {
//                cola.enqueue(pila.pop());
//            }
//            while (!cola.empty()) {
//                push(cola.dequeue());
//            }
        } catch (Exception ex) {

        }

    }

    public void push(E objeto) {
        pila.addFirst(objeto);
    }

    public E pop() throws ExedeCapacidadException {
        return pila.removeFirst();
    }

    public E top() {
        return pila.getPrimero();
    }

    public int Size() {
        return pila.Size();
    }

    public void clear() {
        pila.clear();
    }

    public boolean empty() {
        return pila.empty();
    }

    public void imprimir() {
        pila.imprimir("pila");
    }

    public E mirar(int indice) {
        return pila.get(indice);
    }
}
