/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Interfases.Funcionales;

/**
 *  Del Tipo ( void -> E )
 * @author Rene
 */
@FunctionalInterface
public interface Creador<E> {
    public E crear();
}
