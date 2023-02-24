/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Comparadores;

import java.util.Comparator;
import Utiles.ClasesUtiles.Partida;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class ComparadorDePartidaPorCantidad implements Comparator<Partida>, Serializable {

        @Override
        public int compare(Partida o1, Partida o2) {
            return o1.compareTo(o2);
        }

    }