
package Utiles.ClasesUtiles.Colecciones;

import java.util.LinkedList;

public class CArbolBinarioB {
    CNArbolBinarioB ArbolRaiz;   

    public CArbolBinarioB() {
        this.ArbolRaiz = null;
    }
    
    public CArbolBinarioB(Comparable valor) {
        this.ArbolRaiz = new CNArbolBinarioB(valor);
    }
    
    protected CNArbolBinarioB BuscarArbol(Comparable valor) {
        return ArbolRaiz.BuscarArbol(valor);
    }
    
    public boolean Contiene(Comparable valor){
        return BuscarArbol(valor) != null;
    }
    
    public Comparable Minimo(){
        if (ArbolRaiz==null)
            return null;
        return ArbolRaiz.Minimo().raiz;
    }
    
    public void Insert(Comparable valor){
        if(ArbolRaiz == null)
            ArbolRaiz = new CNArbolBinarioB(valor);
        else
            ArbolRaiz.Insert(valor);
    }
    
    public LinkedList<Comparable> RecorridoEntreOrden(){
      if (ArbolRaiz==null)
          return null;
      LinkedList<Comparable> lista=new LinkedList<Comparable>();
      ArbolRaiz.RecorridoEntreOrden(lista);
      return lista;      
    }
    
   
}
