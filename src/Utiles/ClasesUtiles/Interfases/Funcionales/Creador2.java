/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Interfases.Funcionales;

/**
 * C es el tipo respuesta y A,B los parametros
 * @author Rene
 */
@FunctionalInterface
public interface Creador2<A,B,C> {
    public C crear(A a,B b);
}
