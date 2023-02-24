/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Comparadores;

import Utiles.ClasesUtiles.Partida;
import static Utiles.MetodosUtiles.MetodosUtiles.compararStringAConStringB;
import java.io.Serializable;
import java.util.Comparator;

/**
 *
 * @author Rene
 */
public class ComparadorDePartidaPorNombre implements Comparator<Partida>, Serializable {

        @Override
        public int compare(Partida o1, Partida o2) {
            return compararStringAConStringB(o1.getNombreJugador(), o2.getNombreJugador(), true);
        }

    }