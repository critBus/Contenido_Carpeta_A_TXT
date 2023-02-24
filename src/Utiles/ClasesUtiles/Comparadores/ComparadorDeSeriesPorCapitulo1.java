/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Comparadores;

import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Utiles.ClasesUtiles.Multimedia.Series.Serie;
import Utiles.MetodosUtiles.MetodosUtiles;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Rene
 */
public class ComparadorDeSeriesPorCapitulo1  implements Comparator<Serie>, Serializable{

    @Override
    public int compare(Serie o1, Serie o2) {
       return MetodosUtiles.compararStringAConStringB(getString(o1), getString(o2), true);
        
    }
    
    private String getString(Serie o){
        return o.getFirstCapitulo()==null?o.getNombrePrincipal():o.getFirstCapitulo().getNombre();
//        return o.getFirstCapitulo().getDireccion().getName();
    }
 
  
}
