/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

import Utiles.ClasesUtiles.Interfases.Arreglo;
import java.util.ArrayList;
//import java.util.ArrayList;

/**
 * sqr[]()
 *
 * @author Rene
 */
public enum TipoDeEnvoltura implements Arreglo<TipoDeEnvoltura> {

    RAIZ("sqr[", "](", ")"),
    EXPONETE("", "^", ""),
    PARENTESIS("(", "", ")"),
    LOGARITMO("log[", "](", ")"),//[]base ()argumento
    LOGARITMO_NAPERIANO("ln(", "", ")"),
    SENO("sen(", "", ")"),
    COSENO("cos(", "", ")"),
    TANGENTE("tan(", "", ")"),
    COTAGENTE("cot(", "", ")"),
    SECANTE("sec(", "", ")"),
    COSECANTE("csc(", "", ")");
    private final String inicio;
    private final String medio;
    private final String fin;

//    ArrayList<String> inicios= new ArrayList<String>();
//     ArrayList<String> medios= new ArrayList<String>();
//    ArrayList<String> finales= new ArrayList<String>();
    TipoDeEnvoltura(String inicio, String medio, String fin) {
        this.inicio = inicio;
        this.medio = medio;
        this.fin = fin;
        // inicializarArrayList();
    }

//    public ArrayList<String> getInicios() {
//        //inicializarArrayList();
//        return inicios;
//    }
//
//    public ArrayList<String> getMedios() {
//        //inicializarArrayList();
//        return medios;
//    }
//
//    public ArrayList<String> getFinales() {
//        //inicializarArrayList();
//        return finales;
//    }
    public String getInicio() {
        return inicio;
    }

    public String getMedio() {
        return medio;
    }

    public String getFin() {
        return fin;
    }

   
    public static ArrayList<String> getInicios() {
        ArrayList<String> inicios = new ArrayList<String>();
        for (TipoDeEnvoltura t : TipoDeEnvoltura.values()) {
            inicios.add(t.getInicio());
        }
        return inicios;
    }

    public static ArrayList<String> getMedios() {
        ArrayList<String> medios = new ArrayList<String>();
        for (TipoDeEnvoltura t : TipoDeEnvoltura.values()) {
            medios.add(t.getMedio());
        }
        return medios;
    }

    public static ArrayList<String> getFinales() {
        ArrayList<String> finales = new ArrayList<String>();
        for (TipoDeEnvoltura t : TipoDeEnvoltura.values()) {
            finales.add(t.getFin());
        }
        return finales;
    }

//    private void inicializarArrayList() {
//        for (TipoDeEnvoltura t : TipoDeEnvoltura.values()) {
//            inicios.add(t.getInicio());
//            medios.add(t.getMedio());
//            finales.add(t.getFin());
//        }
//    }
    @Override
    public TipoDeEnvoltura[] crearArreglo(int leng) {
        return new TipoDeEnvoltura[leng];
    }

    @Override
    public TipoDeEnvoltura[][] crearArreglo(int filas, int columnas) {
        return new TipoDeEnvoltura[filas][columnas];
    }

    @Override
    public TipoDeEnvoltura[] crearArregloSuperclase(int leng) {
        return new TipoDeEnvoltura[leng];
    }

    @Override
    public TipoDeEnvoltura[][] crearArregloSuperclase(int filas, int columnas) {
        return new TipoDeEnvoltura[filas][columnas];
    }

    @Override
    public TipoDeEnvoltura[] crearArregloSuperclaseSuperior(int leng) {
        return new TipoDeEnvoltura[leng];
    }

    @Override
    public TipoDeEnvoltura[][] crearArregloSuperclaseSuperior(int filas, int columnas) {
        return new TipoDeEnvoltura[filas][columnas];
    }

    @Override
    public TipoDeEnvoltura[][][] crearArreglo(int filas, int columnas, int cantidad) {
        return new TipoDeEnvoltura[filas][columnas][cantidad];
    }

    @Override
    public TipoDeEnvoltura[][][] crearArregloSuperclase(int filas, int columnas, int cantidad) {
        return new TipoDeEnvoltura[filas][columnas][cantidad];
    }

    @Override
    public TipoDeEnvoltura[][][] crearArregloSuperclaseSuperior(int filas, int columnas, int cantidad) {
        return new TipoDeEnvoltura[filas][columnas][cantidad];
    }

}
