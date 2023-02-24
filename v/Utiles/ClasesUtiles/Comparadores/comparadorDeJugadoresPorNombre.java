/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Comparadores;

import Utiles.ClasesUtiles.Jugador;
import java.util.Comparator;
import static Utiles.MetodosUtiles.MetodosUtiles.compararStringAConStringB;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class comparadorDeJugadoresPorNombre implements Comparator<Jugador>, Serializable {

    @Override
    public int compare(Jugador o1, Jugador o2) {
       return compararStringAConStringB(o1.getNombre(), o2.getNombre(), true);
    }
    
}
