package Utiles.ClasesUtiles.Colecciones;

import java.util.LinkedList;

public class ArbolBinario<E> {

    E raiz;
    ArbolBinario hijoIzquierdo;
    ArbolBinario hijoDerecho;

    public ArbolBinario(E valor, ArbolBinario hijoIzquierdo, ArbolBinario hijoDerecho) {
        this.raiz = valor;
        this.hijoIzquierdo = hijoIzquierdo;
        this.hijoDerecho = hijoDerecho;
    }

    public ArbolBinario(E valor) {
        this.raiz = valor;
        this.hijoIzquierdo = null;
        this.hijoDerecho = null;
    }

    public boolean EsHoja() {
        return (hijoIzquierdo == hijoDerecho && hijoDerecho == null);
    }

    public <E> LinkedList<E> RecorridoEntreorden(LinkedList<E> lista) throws Exception {
        lista.add((E) raiz);
        hijoIzquierdo.RecorridoEntreorden(lista);
        hijoDerecho.RecorridoEntreorden(lista);
        return lista;
    }

    public <E> LinkedList<E> RecorridoPostorden(LinkedList<E> lista) throws Exception {
        hijoDerecho.RecorridoPostorden(lista);
        lista.add((E) raiz);
        hijoIzquierdo.RecorridoPostorden(lista);
        return lista;
    }

    public <E> LinkedList<E> RecorridoPreorden(LinkedList<E> lista) throws Exception {
        hijoDerecho.RecorridoPreorden(lista);
        hijoIzquierdo.RecorridoPreorden(lista);
        lista.add((E) raiz);
        return lista;
    }

    public int GradoNodo() {
        int grado = 0;
        if (hijoIzquierdo != null) {
            grado++;
        }
        if (hijoDerecho != null) {
            grado++;
        }
        return grado;
    }

    public int GradoArbol() {
        int mayor = GradoNodo();
        if (hijoIzquierdo != null) {
            int gradoArbolHijo = hijoIzquierdo.GradoArbol();
            mayor = gradoArbolHijo > mayor ? gradoArbolHijo : mayor;
        }
         if (hijoDerecho != null) {
            int gradoArbolHijo = hijoDerecho.GradoArbol();
            mayor = gradoArbolHijo > mayor ? gradoArbolHijo : mayor;
        }
        return mayor;
    }

}
