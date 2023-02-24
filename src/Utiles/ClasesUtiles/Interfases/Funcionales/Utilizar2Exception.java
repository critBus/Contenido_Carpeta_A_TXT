/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Interfases.Funcionales;

/**
 *Del Tipo ( a,b-> void )
 * @author Rene
 */
@FunctionalInterface
public interface Utilizar2Exception<A,B> {
     public void utilizar(A a,B b) throws Exception;
}