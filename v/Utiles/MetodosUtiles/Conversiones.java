/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.MetodosUtiles;

/**
 * Version 0.0
 * @author Rene
 */
public abstract class Conversiones {
    
    
     public static double GramoADecagramo(double kl) {
        return kl * 10;
    }

    public static double GramoAhectogramo(double kl) {
        return kl * 100;
    }

    public static double decagramoAGramo(double g) {
        return g / 10;
    }

    public static double hectogramoAGramo(double g) {
        return g / 100;
    }

   
    
    //*****************
    public static double GramoADecigramo(double kl) {
        return kl / 10;
    }

    public static double GramoACentigramo(double kl) {
        return kl / 100;
    }

    public static double decigramoAGramo(double g) {
        return g * 10;
    }

    public static double centigramoAGramo(double g) {
        return g * 100;
    }

    public static double GramoAKilogramo(double g) {
        return g * 1000;
    }

    public static double KilogramoAGramo(double kl) {
        return kl / 1000;
    }

    public static double KilogramoALibra(double kl) {
        return kl * 2.2046226218488;
    }

    public static double LibraAKilogramo(double l) {
        return l / 2.2046226218488;
    }
}
