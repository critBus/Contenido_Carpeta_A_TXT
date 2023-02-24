package Utiles.ClasesUtiles.Colecciones;

import java.util.LinkedList;

public class CNArbolBinarioBInt {

    Comparable raiz;
    CNArbolBinarioBInt hijoIzquierdo;
    CNArbolBinarioBInt hijoDerecho;

    public CNArbolBinarioBInt(Comparable valor, CNArbolBinarioBInt hijoIzquierdo, CNArbolBinarioBInt hijoDerecho) {
        this.raiz = valor;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }

    public CNArbolBinarioBInt(Comparable valor) {
        this.raiz = valor;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    public boolean EsHoja() {
        return (hijoIzquierdo == hijoDerecho && hijoDerecho == null);
    }

    //Método iterativo
    protected CNArbolBinarioBInt BuscarArbol(Comparable valor) {
        CNArbolBinarioBInt cursor = this;
        while (cursor != null && valor.compareTo(cursor.raiz) != 0) {
            if (valor.compareTo(cursor.raiz) == -1) {
                cursor = cursor.hijoIzquierdo;
            } else {
                cursor = cursor.hijoDerecho;
            }
        }
        return cursor;
    }

    public boolean Contiene(Comparable valor) {
        return BuscarArbol(valor) != null;
    }

    public CNArbolBinarioBInt Minimo() {
        CNArbolBinarioBInt cursor = this;
        while (cursor.hijoIzquierdo != null) {
            cursor = cursor.hijoIzquierdo;
        }
        return cursor;
    }

    protected void Insert(CNArbolBinarioBInt nuevoArbol, CNArbolBinarioBInt arbol) {
        if (nuevoArbol.raiz.compareTo(arbol.raiz) < 1) {
            if (arbol.hijoIzquierdo != null) {
                Insert(nuevoArbol, arbol.hijoIzquierdo);
            } else {
                arbol.hijoIzquierdo = nuevoArbol;
            }
        } else {
            if (arbol.hijoDerecho != null) {
                Insert(nuevoArbol, arbol.hijoDerecho);
            } else {
                arbol.hijoDerecho = nuevoArbol;
            }
        }
    }

    public void Insert(Comparable valor) {
        Insert(new CNArbolBinarioBInt(valor), this);
    }

    public void RecorridoEntreOrden(LinkedList<Comparable> lista) {
        if (hijoIzquierdo != null) {
            hijoIzquierdo.RecorridoEntreOrden(lista);
        }
        lista.add(raiz);
        if (hijoDerecho != null) {
            hijoDerecho.RecorridoEntreOrden(lista);
        }
    }

    public int Menores(Comparable n) {
        int cant = 0;
        if (hijoDerecho != null) {
            cant += hijoDerecho.Menores(n);
        }
        if (raiz.compareTo(n) == -1) {
            if (hijoIzquierdo != null) {
                cant += hijoIzquierdo.Menores(n);
            }
            return ++cant;
        }
        return cant;

    }
}
