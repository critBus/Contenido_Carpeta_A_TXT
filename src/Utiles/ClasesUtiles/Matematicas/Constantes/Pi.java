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
public class Pi extends Constante {

    public Pi() {
        this(true);
    }

    public Pi(boolean positivo) {
        inicializarConstructor(TipoDeConstante.PI, positivo);
    }

    @Override
    public Pi[] crearArreglo(int leng) {
        return new Pi[leng];
    }

    @Override
    public Pi[][] crearArreglo(int filas, int columnas) {
        return new Pi[filas][columnas];
    }

    @Override
    public Pi[][][] crearArreglo(int filas, int columnas, int cantidad) {
        return new Pi[filas][columnas][cantidad];
    }
}
