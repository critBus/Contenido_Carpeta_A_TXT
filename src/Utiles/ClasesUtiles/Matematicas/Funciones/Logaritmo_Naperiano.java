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
public class Logaritmo_Naperiano extends Funcion {

    public Logaritmo_Naperiano(Logaritmo_Naperiano f) {
        this(f.getParametro1().copia(), f.esPositivo());
    }

     public Logaritmo_Naperiano( double parametro1) {
        this(new Numero(parametro1), true);
    }
     public Logaritmo_Naperiano( double parametro1, boolean positivo) {
        this(new Numero(parametro1), true);
    }

    public Logaritmo_Naperiano(Operando parametro1) {
        this(parametro1, true);
    }

    public Logaritmo_Naperiano(Operando parametro1, boolean positivo) {
        inicializarConstructorUnParamtro(TipoDeEnvoltura.LOGARITMO_NAPERIANO, parametro1, positivo);

    }

    @Override
    public Logaritmo_Naperiano[] crearArreglo(int leng) {
        return new Logaritmo_Naperiano[leng];
    }

    @Override
    public Logaritmo_Naperiano[][] crearArreglo(int filas, int columnas) {
        return new Logaritmo_Naperiano[filas][columnas];
    }
    @Override
    public Logaritmo_Naperiano[][][] crearArreglo(int filas, int columnas, int cantidad) {
         return new Logaritmo_Naperiano[filas][columnas][cantidad];
    }
    @Override
    public Operando inicializarDerivada(char v) {
        return (derivado=new Multiplicacion(bicondicional(positivo, true),new FraccionGeneral(new Numero(1), getParametro1().copia()) ,getParametro1().inicializarDerivada(v))); //To change body of generated methods, choose Tools | Templates.
    }
}
