/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

import Utiles.ClasesUtiles.Interfases.Arreglo;
import java.util.ArrayList;

/**
 *
 * @author Rene
 */
public enum TipoDeConstante implements Arreglo<TipoDeConstante> {

    PI('~', Math.PI), EULER('E', Math.E);
    private char caracter;
    private double valor;

    private TipoDeConstante(char caracter, double valor) {
        this.caracter = caracter;
        this.valor = valor;
    }

    public char getCaracter() {
        return caracter;
    }

    public double getValor() {
        return valor;
    }

    public static char[] getCaracteres() {
        char C[] = new char[TipoDeConstante.values().length];
        for (int i = 0; i < TipoDeConstante.values().length; i++) {
            C[i]=TipoDeConstante.values()[i].getCaracter();
        }
        return C;
    }
    public static String[] getCaracteresString() {
        String C[] = new String[TipoDeConstante.values().length];
        for (int i = 0; i < TipoDeConstante.values().length; i++) {
            C[i]=TipoDeConstante.values()[i].getCaracter()+"";
        }
        return C;
    }
    public static TipoDeConstante TipoDeConstante(char a) {
        for (TipoDeConstante t : TipoDeConstante.values()) {
            if (t.getCaracter() == a) {
                return t;
            }
        }
        return null;
    }

    public static TipoDeConstante TipoDeConstante(double valor) {
        for (TipoDeConstante t : TipoDeConstante.values()) {
            if (t.getValor() == valor) {
                return t;
            }
        }
        return null;
    }
    
    public static int contienenCaracter(char a) {
        ArrayList<Character> c = getCaracteres2();
        for (int i = 0; i < c.size(); i++) {
            if (a == c.get(i)) {
                return i;
            }
        }
        return -1;
    }

    public static ArrayList<Character> getCaracteres2() {
        ArrayList<Character> c = new ArrayList<Character>();
        for (TipoDeConstante t : TipoDeConstante.values()) {
            c.add(t.getCaracter());
        }
        return c;
    }

    @Override
    public TipoDeConstante[] crearArreglo(int leng) {
        return new TipoDeConstante[leng];
    }

    @Override
    public TipoDeConstante[][] crearArreglo(int filas, int columnas) {
        return new TipoDeConstante[filas][columnas];
    }

    @Override
    public TipoDeConstante[][][] crearArreglo(int filas, int columnas, int cantidad) {
        return new TipoDeConstante[filas][columnas][cantidad];
    }

    @Override
    public TipoDeConstante[] crearArregloSuperclase(int leng) {
        return new TipoDeConstante[leng];
    }

    @Override
    public TipoDeConstante[][] crearArregloSuperclase(int filas, int columnas) {
        return new TipoDeConstante[filas][columnas];
    }

    @Override
    public TipoDeConstante[][][] crearArregloSuperclase(int filas, int columnas, int cantidad) {
        return new TipoDeConstante[filas][columnas][cantidad];
    }

    @Override
    public TipoDeConstante[] crearArregloSuperclaseSuperior(int leng) {
        return new TipoDeConstante[leng];
    }

    @Override
    public TipoDeConstante[][] crearArregloSuperclaseSuperior(int filas, int columnas) {
        return new TipoDeConstante[filas][columnas];
    }

    @Override
    public TipoDeConstante[][][] crearArregloSuperclaseSuperior(int filas, int columnas, int cantidad) {
        return new TipoDeConstante[filas][columnas][cantidad];
    }

}
