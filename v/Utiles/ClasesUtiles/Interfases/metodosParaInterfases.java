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
public abstract class metodosParaInterfases {
    
    public static <E> Iteradora<E> next(Iteradora<E> e, int siguientes) {
        if (siguientes < 1) {
            return null;
        }
        Iteradora<E> f = (Iteradora<E>) e.next();
        while (--siguientes > 0) {
            f = (Iteradora<E>) f.next();
        }
        return f;
    }

    public static <E> Iteradora<E> before(Iteradora<E> e, int anteriores) {
        if (anteriores < 1) {
            return null;
        }
        Iteradora<E> f = (Iteradora<E>) e.before();
        while (--anteriores > 0) {
            f = (Iteradora<E>) f.next();
        }
        return f;
    }
}
