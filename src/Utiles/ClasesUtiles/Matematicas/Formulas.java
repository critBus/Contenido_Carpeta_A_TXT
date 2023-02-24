/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

import Utiles.ClasesUtiles.Matematicas.Funciones.Seno;
import static Utiles.MetodosUtiles.MetodosParaManipularMisClases.*;


/**
 *
 * @author Rene
 */
public abstract class Formulas {

    public static Operando sen2x(double x) {
        return sen2x(x, true);
    }

    public static Operando sen2x(double x, boolean positivo) {
        return sen2x(new Numero(x), positivo);
    }

    public static Operando sen2x(Operando x) {
        return sen2x(x, true);
    }

    public static Operando sen2x(Operando x, boolean positivo) {
        return new Seno(new Multiplicacion(new Numero(2), x), positivo);
    }

    
}
