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
public class Tangente extends Funcion {

    public Tangente(Tangente f) {
        this(f.getParametro1().copia(), f.esPositivo());
    }

    public Tangente( double parametro1) {
        this(new Numero(parametro1), true);
    }
     public Tangente( double parametro1, boolean positivo) {
        this(new Numero(parametro1), true);
    }

    public Tangente(Operando parametro1) {
        this(parametro1, true);
    }

    public Tangente(Operando parametro1, boolean positivo) {
        inicializarConstructorUnParamtro(TipoDeEnvoltura.TANGENTE, parametro1, positivo);

    }

    @Override
    public Tangente[] crearArreglo(int leng) {
        return new Tangente[leng];
    }

    @Override
    public Tangente[][] crearArreglo(int filas, int columnas) {
        return new Tangente[filas][columnas];
    }
    @Override
    public Tangente[][][] crearArreglo(int filas, int columnas, int cantidad) {
         return new Tangente[filas][columnas][cantidad];
    }
     @Override
    public Operando inicializarDerivada(char v) {//new Seno(getParametro1().copia()) 
        return (derivado=new Multiplicacion(bicondicional(positivo, true), new Exponente(new Secante(getParametro1().copia()), new Numero(2)),getParametro1().inicializarDerivada(v))); //To change body of generated methods, choose Tools | Templates.
    }
}

