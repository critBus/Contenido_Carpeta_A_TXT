/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Interfases;

/**
 *
 * @author Rene
 */
public interface Comparaciones<E> extends Comparable<E> {

    public boolean mayorQue(E a);

    public boolean menorQue(E a);

    public boolean mayorIgualQue(E a);

    public boolean menorIgualQue(E a);

    public boolean igualQue(E a);
}
