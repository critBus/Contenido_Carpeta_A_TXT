/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Colecciones;
import Utiles.Exepciones.*;
import java.io.Serializable;
/**
 *
 * @author Rene
 */
public class Lista<E> implements Serializable{

    private Nodo primerNodo;
    private Nodo ultimoNodo;
    int cantidadDeNodos = 0;

    public Lista() {
        primerNodo = ultimoNodo = null;
    }

    public boolean set(int indice, E a) {

        if (indice < 0 || indice > cantidadDeNodos) {
            return false;
        } else {
            if (indice == cantidadDeNodos) {
                add(a);
            } else {
                Nodo A = new Nodo(a);
                if (indice == 0) {
                    A.siguienteNodo = primerNodo.siguienteNodo;
                    primerNodo = A;
                } else {
                    Nodo Anterior = anterior(indice);
                    A.siguienteNodo = Anterior.siguienteNodo.siguienteNodo;
                    Anterior.siguienteNodo = A;
                }
            }
            return true;
        }
    }

    private Nodo anterior(int a) {
        Nodo Anterior = primerNodo;
        int i = 0;
        while (i++ < a - 1) {
            Anterior = Anterior.siguienteNodo;
        }
        return Anterior;
    }

    public void addFirst(E elementoInsertar) {
        if (empty()) // primerNodo y ultimoNodo hacen referencia al mismo objeto
        {
            primerNodo = ultimoNodo = new Nodo(elementoInsertar);
        } else // primerNodo hace referencia al nuevo nodo
        {
            primerNodo = new Nodo(elementoInsertar, primerNodo);
        }
        cantidadDeNodos++;
    }

    public void add(E elementoInsertar) {
        if (empty()) {
            primerNodo = ultimoNodo = new Nodo(elementoInsertar);
        } else {
            ultimoNodo = ultimoNodo.siguienteNodo = new Nodo(elementoInsertar);
        }
        cantidadDeNodos++;
    }

    public E removeFirst() throws ExedeCapacidadException {
        if (empty()) {
            throw new ExedeCapacidadException("La lista se encuentra vacia");
        }
        E elementoEliminado = (E) primerNodo.datos;
        if (primerNodo == ultimoNodo) {
            primerNodo = ultimoNodo = null;
        } else {
            primerNodo = primerNodo.siguienteNodo;
        }
        cantidadDeNodos--;
        return elementoEliminado;
    }

    public E removeLast() throws ExedeCapacidadException {
        if (empty()) {
            throw new ExedeCapacidadException("La lista se encuentra vacia");
        }
        E elementoEliminado = (E) ultimoNodo.datos;
        if (primerNodo == ultimoNodo) {
            primerNodo = ultimoNodo = null;
        } else {
            Nodo actual = primerNodo;

            while (actual.siguienteNodo != ultimoNodo) {
                actual = actual.siguienteNodo;
            }
            ultimoNodo = actual;
            actual.siguienteNodo = null;
        }
        cantidadDeNodos--;
        System.out.println("elementoEliminado="+elementoEliminado);
        return elementoEliminado;
    }

    public boolean empty() {
        return primerNodo == null;
    }

    public void imprimir() {
        imprimir("lista");
    }

    public void imprimir(String a) {
        if (empty()) {
            System.out.printf("La %s se encuentra vacia\n", a);
            return;
        }

        System.out.printf("La %s es:", a);
        Nodo actual = primerNodo;
        while (actual != null) {
            System.out.printf("%s ", actual.datos);
            actual = actual.siguienteNodo;
        }
        System.out.println("\n");
    }

    public E getPrimero() {
        return (E) primerNodo.getDatos();
    }

    public E getUltimo() {
        return (E) ultimoNodo.getDatos();
    }

    public int Size() {
        return cantidadDeNodos;
    }

    public void clear() {
        primerNodo = ultimoNodo = null;
        cantidadDeNodos = 0;
    }

    public E get(int indice) {
        if (!(indice < cantidadDeNodos)) {
            return null;
        }

        if (indice == 0) {
            return (E) primerNodo.getDatos();
        }
        if (indice == cantidadDeNodos - 1) {
            return(E) ultimoNodo.getDatos();
        }

        return (E) anterior(indice).siguienteNodo.getDatos();

    }


}
