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
public interface Iteradora<E> {

    public E next();

    public E next(int siguientes);

    public E before();

    public E before(int anteriores);
   
}
