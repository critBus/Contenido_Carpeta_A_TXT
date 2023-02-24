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
public class Logaritmo extends Funcion {

    public Logaritmo(Logaritmo f) {
        this(f.getParametro1().copia(), f.getParametro2().copia(), f.esPositivo());
    }

    public Logaritmo(double parametro1, Operando parametro2, boolean positivo) {
        this(new Numero(parametro1), parametro2, positivo);
    }

    public Logaritmo(Operando parametro1, double parametro2, boolean positivo) {
        this(parametro1, new Numero(parametro2), positivo);
    }

    public Logaritmo(double parametro1, double parametro2, boolean positivo) {
        this(new Numero(parametro1), new Numero(parametro2), positivo);
    }

    public Logaritmo(double parametro1, Operando parametro2) {
        this(new Numero(parametro1), parametro2, true);
    }

    public Logaritmo(Operando parametro1, double parametro2) {
        this(parametro1, new Numero(parametro2), true);
    }

    public Logaritmo(double parametro1, double parametro2) {
        this(new Numero(parametro1), new Numero(parametro2), true);
    }

    public Logaritmo(Operando parametro1, Operando parametro2) {
        this(parametro1, parametro2, true);
    }

    public Logaritmo(Operando parametro1, Operando parametro2, boolean positivo) {
        inicializarConstructorDosParametros(TipoDeEnvoltura.LOGARITMO, parametro1, parametro2, positivo);
    }

    @Override
    protected void inicializarOptimo() {
        super.inicializarOptimo();
        if (getParametro2() instanceof Exponente) {

            if (((Funcion) getParametro2()).getParametro1().igualA(getParametro1())) {
                optimo = ((Funcion) getParametro2()).getParametro2();
                optimo.setPositivo(positivo, optimo.esPositivo());
                return;
            }
        }
    }

    @Override
    public Logaritmo[] crearArreglo(int leng) {
        return new Logaritmo[leng];
    }

    @Override
    public Logaritmo[][] crearArreglo(int filas, int columnas) {
        return new Logaritmo[filas][columnas];
    }
    @Override
    public Logaritmo[][][] crearArreglo(int filas, int columnas, int cantidad) {
         return new Logaritmo[filas][columnas][cantidad];
    }
    @Override
    public Operando inicializarDerivada(char v) {
        return (derivado=new Multiplicacion(bicondicional(positivo, true),new FraccionGeneral(new Numero(1), new Logaritmo_Naperiano(getParametro1().copia())) ,getParametro2().inicializarDerivada(v))); //To change body of generated methods, choose Tools | Templates.
    }
}
