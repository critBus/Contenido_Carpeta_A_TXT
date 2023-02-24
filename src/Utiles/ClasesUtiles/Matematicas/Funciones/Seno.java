/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas.Funciones;

import Utiles.ClasesUtiles.Matematicas.*;
import static Utiles.MetodosUtiles.MetodosUtiles.bicondicional;

/**
 *
 * @author Rene
 */
public class Seno extends Funcion {

    public Seno(Seno f) {
        this(f.getParametro1().copia(), f.esPositivo());
    }

   public Seno( double parametro1) {
        this(new Numero(parametro1), true);
    }
     public Seno( double parametro1, boolean positivo) {
        this(new Numero(parametro1), true);
    }

    public Seno(Operando parametro1) {
        this(parametro1, true);
    }

    public Seno(Operando parametro1, boolean positivo) {
        inicializarConstructorUnParamtro(TipoDeEnvoltura.SENO, parametro1, positivo);

    }

    @Override
    public Seno[] crearArreglo(int leng) {
        return new Seno[leng];
    }

    @Override
    public Seno[][] crearArreglo(int filas, int columnas) {
        return new Seno[filas][columnas];
    }
    @Override
    public Seno[][][] crearArreglo(int filas, int columnas, int cantidad) {
         return new Seno[filas][columnas][cantidad];
    }
    @Override
    public Operando inicializarDerivada(char v) {
        return (derivado=new Multiplicacion(bicondicional(positivo, true),new Coseno(getParametro1().copia()) ,getParametro1().inicializarDerivada(v))); //To change body of generated methods, choose Tools | Templates.
    }
}
