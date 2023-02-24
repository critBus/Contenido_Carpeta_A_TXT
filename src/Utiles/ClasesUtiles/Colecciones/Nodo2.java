/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Colecciones;

/**
 *
 * @author Yutdyel
 */
public class Nodo2 implements Comparable<Nodo2>{
 private   int valor;
 private   Nodo2 izquierdo;
 private   Nodo2 derecho;

    public Nodo2(int valor) {
        this.valor = valor;
    }

    
    
    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Nodo2 getIzquierdo() {
        return izquierdo;
    }

    public void setIzquierdo(Nodo2 izquierdo) {
        this.izquierdo = izquierdo;
    }

    public Nodo2 getDerecho() {
        return derecho;
    }

    public void setDerecho(Nodo2 derecho) {
        this.derecho = derecho;
    }

    @Override
    public int compareTo(Nodo2 o) {
        return new Integer(valor).compareTo(new Integer(o.getValor()));
    }
    
    
}
