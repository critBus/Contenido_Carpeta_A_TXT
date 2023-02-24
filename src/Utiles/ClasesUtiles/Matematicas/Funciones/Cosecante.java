/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas.Funciones;

import Utiles.ClasesUtiles.Matematicas.*;
import Utiles.MetodosUtiles.MetodosUtiles;
import static Utiles.MetodosUtiles.MetodosUtiles.bicondicional;

/**
 *
 * @author Rene
 */
public class Cosecante extends Funcion {

    public Cosecante(Cosecante f) {
        this(f.getParametro1().copia(), f.esPositivo());
    }

    public Cosecante( double parametro1) {
        this(new Numero(parametro1), true);
    }
     public Cosecante( double parametro1, boolean positivo) {
        this(new Numero(parametro1), true);
    }

    public Cosecante(Operando parametro1) {
        this(parametro1, true);
    }

    public Cosecante(Operando parametro1, boolean positivo) {
        inicializarConstructorUnParamtro(TipoDeEnvoltura.COSECANTE, parametro1, positivo);

    }

    @Override
    public Cosecante[] crearArreglo(int leng) {
        return new Cosecante[leng];
    }

    @Override
    public Cosecante[][] crearArreglo(int filas, int columnas) {
        return new Cosecante[filas][columnas];
    }

    @Override
    public Cosecante[][][] crearArreglo(int filas, int columnas, int cantidad) {
         return new Cosecante[filas][columnas][cantidad];
    }

    @Override
    public Operando inicializarDerivada(char v) {
        return (derivado=new Multiplicacion(new Cosecante(getParametro1().copia(), bicondicional(positivo,false)),new Cotagente(getParametro1().copia()),getParametro1().inicializarDerivada(v))); //To change body of generated methods, choose Tools | Templates.
    }
    

}
