/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas.Constantes;

import Utiles.ClasesUtiles.Matematicas.Constante;
import Utiles.ClasesUtiles.Matematicas.TipoDeConstante;

/**
 *
 * @author Rene
 */
public class Euler extends Constante {

    public Euler() {
        this(true);
    }

    public Euler(boolean positivo) {
        inicializarConstructor(TipoDeConstante.EULER, positivo);
    }

    @Override
    public Euler[] crearArreglo(int leng) {
        return new Euler[leng];
    }

    @Override
    public Euler[][] crearArreglo(int filas, int columnas) {
        return new Euler[filas][columnas];
    }

    @Override
    public Euler[][][] crearArreglo(int filas, int columnas, int cantidad) {
        return new Euler[filas][columnas][cantidad];
    }
}
