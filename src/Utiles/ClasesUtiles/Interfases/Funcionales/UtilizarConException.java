/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Interfases.Funcionales;

/**
 * Del Tipo ( E ... e -> void )
 * @author Rene
 */
@FunctionalInterface
public interface UtilizarConException<E> {
    public void utilizar(E  e)throws Exception;
}
