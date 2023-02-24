package Utiles.ClasesUtiles.Colecciones;

//import com.sun.jmx.remote.internal.ArrayQueue;
//import java.util.Queue;
import java.util.LinkedList;

public class Arbol<T> {

    T raiz;
    LinkedList<Arbol<T>> hijos;

    public Arbol(T raiz) {
        this.raiz = raiz;
        hijos = new LinkedList<Arbol<T>>();
    }

    public boolean EsHoja() {
        if (hijos.size() == 0) {
            return true;
        }
        return false;
    }

    public int Altura() {
        int alturaMaxima = 0;
        if (EsHoja()) {
            return alturaMaxima;
        }
        for (int i = 0; i < hijos.size(); i++) {
            int altura = hijos.get(i).Altura();
            if (altura > alturaMaxima) {
                alturaMaxima = altura;
            }
        }
        return alturaMaxima + 1;
    }

    public void RecorridoPreorden(LinkedList<T> lista) {
        lista.add(raiz);
        for (int i = 0; i < hijos.size(); i++) {
            hijos.get(i).RecorridoPreorden(lista);
        }
    }

    public void RecorridoEntreorden(LinkedList<T> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            hijos.get(i).RecorridoEntreorden(lista);
            if (i == hijos.size() / 2) {
                lista.add(raiz);
            }
        }
    }

    public void RecorridoPostorden(LinkedList<T> lista) {
        for (int i = 0; i < hijos.size(); i++) {
            hijos.get(i).RecorridoPostorden(lista);
        }
        lista.add(raiz);
    }

    public void RecorridoAncho(LinkedList<T> lista) throws Exception {
        CQueue<Arbol<T>> cola = new CQueue<Arbol<T>>();
        cola.Enqueue(this);
        while (!cola.IsEmpty()) {
            Arbol<T> top = cola.Dequeue();
            lista.add(top.raiz);
            for (int i = 0; i < top.hijos.size(); i++) {
                cola.Enqueue(top.hijos.get(i));
            }
        }
    }

    public int GradoNodo() {
        return hijos.size();
    }

    public int GradoArbol() {
        int mayor = GradoNodo();
        for (int i = 0; i < hijos.size(); i++) {
            int gradoArbolHijo = hijos.get(i).GradoArbol();
            mayor = gradoArbolHijo > mayor ? gradoArbolHijo : mayor;
        }
        return mayor;
    }

    private Boolean Padre2(T elemento) {
        if (raiz.equals(elemento)) {
            return true;
        }
        for (int i = 0; i < hijos.size(); i++) {
            if (hijos.get(i).Padre2(elemento)) {
                return true;
            }
        }
        return false;
    }
	 
}
