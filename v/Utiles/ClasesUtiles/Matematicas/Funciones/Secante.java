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
public class Secante  extends Funcion{
    
     public Secante(Secante f) {
        this(f.getParametro1().copia(), f.esPositivo());
    }

     public Secante( double parametro1) {
        this(new Numero(parametro1), true);
    }
     public Secante( double parametro1, boolean positivo) {
        this(new Numero(parametro1), true);
    }

    public Secante(Operando parametro1) {
        this(parametro1, true);
    }
    
    public Secante(Operando parametro1, boolean positivo) {
          inicializarConstructorUnParamtro(TipoDeEnvoltura.SECANTE,parametro1, positivo);

    }

    
     @Override
    public Secante[] crearArreglo(int leng) {
        return new Secante[leng];
    }

    @Override
    public Secante[][] crearArreglo(int filas, int columnas) {
        return new Secante[filas][columnas];
    }
    @Override
    public Secante[][][] crearArreglo(int filas, int columnas, int cantidad) {
         return new Secante[filas][columnas][cantidad];
    }
     @Override
    public Operando inicializarDerivada(char v) {
        return (derivado=new Multiplicacion(new Secante(getParametro1().copia(), bicondicional(positivo,true)),new Tangente(getParametro1().copia()),getParametro1().inicializarDerivada(v))); //To change body of generated methods, choose Tools | Templates.
    }
}