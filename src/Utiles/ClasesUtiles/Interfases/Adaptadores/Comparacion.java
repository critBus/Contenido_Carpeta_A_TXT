/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Interfases.Adaptadores;

import Utiles.ClasesUtiles.Interfases.*;

/**
 *
 * @author Rene
 */
public abstract class Comparacion<E> implements Comparaciones<E> {

    @Override
    public boolean mayorQue(E a) {
        return compareTo(a) == 1;
    }

    @Override
    public boolean menorQue(E a) {
        return compareTo(a) == -1;
    }

    @Override
    public boolean igualQue(E a) {
        return compareTo(a) == 0;
    }

    @Override
    public boolean mayorIgualQue(E a) {
        int c = compareTo(a);
        return c == 0 || c == 1;
    }

    @Override
    public boolean menorIgualQue(E a) {
        int c = compareTo(a);
        return c == 0 || c == -1;
    }

    @Override
    public abstract int compareTo(E o);

}
