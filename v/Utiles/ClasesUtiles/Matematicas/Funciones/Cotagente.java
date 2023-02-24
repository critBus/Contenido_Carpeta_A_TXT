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
public class Cotagente extends Funcion{
    
     public Cotagente(Cotagente f) {
        this(f.getParametro1().copia(), f.esPositivo());
    }

   public Cotagente( double parametro1) {
        this(new Numero(parametro1), true);
    }
     public Cotagente( double parametro1, boolean positivo) {
        this(new Numero(parametro1), true);
    }

    public Cotagente(Operando parametro1) {
        this(parametro1, true);
    }
    public Cotagente(Operando parametro1, boolean positivo) {
         inicializarConstructorUnParamtro(TipoDeEnvoltura.COTAGENTE,parametro1, positivo);

    }

    
     @Override
    public Cotagente[] crearArreglo(int leng) {
        return new Cotagente[leng];
    }

    @Override
    public Cotagente[][] crearArreglo(int filas, int columnas) {
        return new Cotagente[filas][columnas];
    }
    @Override
    public Cotagente[][][] crearArreglo(int filas, int columnas, int cantidad) {
         return new Cotagente[filas][columnas][cantidad];
    }
    @Override
    public Operando inicializarDerivada(char v) {//new Seno(getParametro1().copia()) 
        //return (derivado=new Multiplicacion(bicondicional(positivo, false),new FraccionGeneral(new Numero(1), new Exponente(new Seno(getParametro1().copia()), new Numero(2))),getParametro1().inicializarDerivada(v))); //To change body of generated methods, choose Tools | Templates.
        return (derivado=new Multiplicacion(bicondicional(positivo, false), new Exponente(new Cosecante(getParametro1().copia()), new Numero(2)),getParametro1().inicializarDerivada(v)));
    }
}
