/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas.Funciones;

import Utiles.ClasesUtiles.Matematicas.*;
import static Utiles.MetodosUtiles.MetodosUtiles.bicondicional;
//import static Utiles.MetodosUtiles.MetodosUtiles.bicondicional;
//import java.util.Arrays;

/**
 *
 * @author Rene
 */
public class Raiz extends Funcion {

    public Raiz(Raiz f) {
        this(f.getParametro1().copia(), f.getParametro2().copia(), f.esPositivo());
    }

    public Raiz(double parametro1, Operando parametro2, boolean positivo) {
        this(new Numero(parametro1), parametro2, positivo);
    }

    public Raiz(Operando parametro1, double parametro2, boolean positivo) {
        this(parametro1, new Numero(parametro2), positivo);
    }

    public Raiz(double parametro1, double parametro2, boolean positivo) {
        this(new Numero(parametro1), new Numero(parametro2), positivo);
    }

    public Raiz(double parametro1, Operando parametro2) {
        this(new Numero(parametro1), parametro2, true);
    }

    public Raiz(Operando parametro1, double parametro2) {
        this(parametro1, new Numero(parametro2), true);
    }

    public Raiz(double parametro1, double parametro2) {
        this(new Numero(parametro1), new Numero(parametro2), true);
    }

    public Raiz(Operando parametro1, Operando parametro2) {
        this(parametro1, parametro2, true);
    }

    public Raiz(Operando parametro1, Operando parametro2, boolean positivo) {
        inicializarConstructorDosParametros(TipoDeEnvoltura.RAIZ, parametro1, parametro2, positivo);
    }

    @Override
    protected void inicializarOptimo() {
        super.inicializarOptimo();
        if (getParametro2() instanceof Raiz) {
            setPositivo(positivo, getParametro2().extraerPositivo());
            setParametros(new Multiplicacion(getParametro1(), ((Funcion) getParametro2()).getParametro1()), ((Funcion) getParametro2()).getParametro2());
        }
    }

    @Override
    public Raiz[] crearArreglo(int leng) {
        return new Raiz[leng];
    }

    @Override
    public Raiz[][] crearArreglo(int filas, int columnas) {
        return new Raiz[filas][columnas];
    }
    @Override
    public Raiz[][][] crearArreglo(int filas, int columnas, int cantidad) {
         return new Raiz[filas][columnas][cantidad];
    }
     @Override
    public Operando inicializarDerivada(char v) {//getParametro1().copia()
        return (derivado = new Multiplicacion(bicondicional(positivo, true),new FraccionGeneral(new Numero(1),getParametro1().copia()) ,new Exponente(getParametro2().copia(), Suma.inicializarRestando(new FraccionGeneral(new Numero(1),getParametro1().copia()),new Numero(1))), getParametro2().inicializarDerivada(v))); //To change body of generated methods, choose Tools | Templates.
    }
}
