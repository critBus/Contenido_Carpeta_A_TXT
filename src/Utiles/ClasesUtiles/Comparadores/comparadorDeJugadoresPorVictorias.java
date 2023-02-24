/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Comparadores;

import Utiles.ClasesUtiles.Jugador;
import static Utiles.MetodosUtiles.MetodosUtiles.compararStringAConStringB;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Rene
 */
public class comparadorDeJugadoresPorVictorias implements Comparator<Jugador>, Serializable {

    @Override
    public int compare(Jugador o1, Jugador o2) {
       return o1.compareTo(o2);
    }
    
}