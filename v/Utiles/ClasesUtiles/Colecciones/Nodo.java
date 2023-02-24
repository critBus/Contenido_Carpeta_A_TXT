/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Colecciones;

import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class Nodo<E> implements Serializable {

    public E datos;
    public Nodo siguienteNodo;

    public Nodo(E datos) {
        this.datos = datos;
    }

    public Nodo(E datos, Nodo siguienteNodo) {
        this.datos = datos;
        this.siguienteNodo = siguienteNodo;
    }

    public E getDatos() {
        return datos;
    }

    public void setDatos(E datos) {
        this.datos = datos;
    }

    public Nodo getSiguienteNodo() {
        return siguienteNodo;
    }

    public void setSiguienteNodo(Nodo siguienteNodo) {
        this.siguienteNodo = siguienteNodo;
    }

}
