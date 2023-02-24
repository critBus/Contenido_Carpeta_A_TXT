package Utiles.ClasesUtiles.Colecciones.otro;

import Utiles.ClasesUtiles.Colecciones.*;
import java.util.LinkedList;

public class CNArbolBinarioB {

    int raiz;
    CNArbolBinarioB hijoIzquierdo;
    CNArbolBinarioB hijoDerecho;

    public CNArbolBinarioB(int valor, CNArbolBinarioB hijoIzquierdo, CNArbolBinarioB hijoDerecho) {
        this.raiz = valor;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }

    public CNArbolBinarioB(int valor) {
        this.raiz = valor;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    public boolean EsHoja() {
        return (hijoIzquierdo == hijoDerecho && hijoDerecho == null);
    }

    //Método iterativo
    protected CNArbolBinarioB BuscarArbol(Comparable valor) {
        CNArbolBinarioB cursor = this;
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

    public CNArbolBinarioB Minimo() {
        CNArbolBinarioB cursor = this;
        while (cursor.hijoIzquierdo != null) {
            cursor = cursor.hijoIzquierdo;
        }
        return cursor;
    }

    protected void Insert(CNArbolBinarioB nuevoArbol, CNArbolBinarioB arbol) {
        if (nuevoArbol.raiz < arbol.raiz) {
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

    public void Insert(int valor) {
        Insert(new CNArbolBinarioB(valor), this);
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

    public int Intervalo(CNArbolBinarioB cursor, int n, int m) {
        int cantidad = 0;
        if (cursor.raiz > n && cursor.hijoIzquierdo != null) {
            cantidad += Intervalo(cursor.hijoIzquierdo, n, m);
        }
        if (cursor.raiz < m && cursor.hijoDerecho != null) {
            cantidad += Intervalo(cursor.hijoDerecho, n, m);
        }
        return (cursor.raiz <= m && cursor.raiz >= n) ? ++cantidad : cantidad;
    }

    public int Intervalo2(CNArbolBinarioB cursor, int n, int m) {
        int a = 0;
        if (cursor.raiz < m) {
            if (cursor.hijoDerecho != null) {
                a = a + Intervalo(cursor.hijoDerecho, n, m);
            }
        }
        if (cursor.raiz > n) {
            if (cursor.hijoIzquierdo != null) {
                a = a + Intervalo(cursor.hijoIzquierdo, n, m);
            }
        }
        if (!(cursor.raiz <= m && cursor.raiz >= n)) {
            return a;
        } else {
            a++;
        }
        return a;
    }

    public int Menores(int n) {
        int cant = 0;
        if (hijoDerecho != null) {
            cant += hijoDerecho.Menores(n);
        }
        if (raiz<n) {
            if (hijoIzquierdo != null) {
                cant += hijoIzquierdo.Menores(n);
            }
            return ++cant;
        }
        return cant;

    }
}
