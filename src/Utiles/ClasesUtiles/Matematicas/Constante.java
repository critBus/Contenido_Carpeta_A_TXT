/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

//import Utiles.ClasesUtiles.Arreglo;
import Utiles.ClasesUtiles.Matematicas.Constantes.Euler;
import Utiles.ClasesUtiles.Matematicas.Constantes.Pi;
import static Utiles.ClasesUtiles.Matematicas.TipoDeConstante.TipoDeConstante;
import java.util.ArrayList;

/**
 *
 * @author Rene
 */
public class Constante extends Operando {

    TipoDeConstante tipoDeConstante;
    private char caracter;
    // private double valor;

    public Constante() {
    }

//    public Constante(double valor) {
//        this(valor, true);
//    }
//
//    public Constante(double valor, boolean positivo) {
//        this(TipoDeConstante(valor), positivo);
//    }
//
//    public Constante(char caracter) {
//        this(caracter, true);
//    }
//
//    public Constante(char caracter, boolean positivo) {
//        this(TipoDeConstante(caracter), positivo);
//    }
//
//    public Constante(TipoDeConstante tipoDeConstante) {
//        this(tipoDeConstante, true);
//    }
//
//    public Constante(TipoDeConstante tipoDeConstante, boolean positivo) {
//        this.tipoDeConstante = tipoDeConstante;
//        this.caracter = tipoDeConstante.getCaracter();
//        this.valor = tipoDeConstante.getValor();
//        this.positivo=positivo;
//        isNumero=false;
//        optimo=this;
//    }
    protected void inicializarConstructor(TipoDeConstante tipoDeConstante, boolean positivo) {
        this.tipoDeConstante = tipoDeConstante;
        this.caracter = tipoDeConstante.getCaracter();
        numero = tipoDeConstante.getValor();
        this.positivo = positivo;
        isNumero = false;
        optimo = this;
    }

    public TipoDeConstante getTipoDeConstante() {
        return tipoDeConstante;
    }

    public char getCaracter() {
        return caracter;
    }

    public double getValor() {
        return numero;
    }

    public static Constante obtenerConstante(double valor) {
        return obtenerConstante(valor, true);
    }

    public static Constante obtenerConstante(double valor, boolean positivo) {
        return obtenerConstante(TipoDeConstante(valor), positivo);
    }

    public static Constante obtenerConstante(char caracter) {
        return obtenerConstante(caracter, true);
    }

    public static Constante obtenerConstante(char caracter, boolean positivo) {
        return obtenerConstante(TipoDeConstante(caracter), positivo);
    }

    public static Constante obtenerConstante(TipoDeConstante tipoDeConstante) {
        return obtenerConstante(tipoDeConstante, true);
    }

    public static Constante obtenerConstante(TipoDeConstante tipoDeConstante, boolean positivo) {
        switch (tipoDeConstante) {//case : return new ;
            case PI:
                return new Pi(positivo);
            case EULER:
                return new Euler(positivo);
        }
        return null;
    }

    @Override
    public String toString() {
        return (positivo ? "" : "-") + caracter + "";
    }

    @Override
    public boolean igualA(Operando a, boolean valorarSigno) {
        //   System.out.println("v this=" + this + " a=" + a);
        if (a instanceof Constante) {
            return valorarSigno ? (a.esPositivo() == positivo && caracter == ((Constante) a).getCaracter()) : caracter == ((Constante) a).getCaracter();
        }

        return false;
    }

    @Override
    public Constante[] crearArregloSuperclaseSuperior(int leng) {
        return new Constante[leng];
    }

    @Override
    public Constante[][] crearArregloSuperclaseSuperior(int filas, int columnas) {
        return new Constante[filas][columnas];
    }

    @Override
    public Constante[][][] crearArregloSuperclaseSuperior(int filas, int columnas, int cantidad) {
        return new Constante[filas][columnas][cantidad];
    }
     @Override
   public Operando inicializarDerivada(char v) {
        return (derivado=new Numero(0)); //To change body of generated methods, choose Tools | Templates.
    }
    

}
