package Utiles.ClasesUtiles.Colecciones;

import java.util.LinkedList;

public class CNArbolBinarioB {

    Comparable raiz;
    CNArbolBinarioB hijoIzquierdo;
    CNArbolBinarioB hijoDerecho;

    public CNArbolBinarioB(Comparable valor, CNArbolBinarioB hijoIzquierdo, CNArbolBinarioB hijoDerecho) {
        this.raiz = valor;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }

    public CNArbolBinarioB(Comparable valor) {
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

    public void Busqueda(LinkedList<Comparable> Listado, Comparable valor) {
        //int year=Integer.parseInt(getId().charAt(0)+""+getId().charAt(1));
        if (raiz.compareTo(valor) == 0) {
            Listado.add(raiz);
            if (hijoIzquierdo != null) {
                hijoIzquierdo.Busqueda(Listado, valor);
            }
            if (hijoDerecho != null) {
                hijoDerecho.Busqueda(Listado, valor);
            }
            return;
        }
        if (raiz.compareTo(valor) == 1) {
            if (hijoIzquierdo != null) {
                hijoIzquierdo.Busqueda(Listado, valor);
            }
        } else {
            if (hijoDerecho != null) {
                hijoDerecho.Busqueda(Listado, valor);
            }
        }

    }

}
