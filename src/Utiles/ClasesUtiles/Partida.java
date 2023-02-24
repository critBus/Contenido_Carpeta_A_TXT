/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles;

import Utiles.ClasesUtiles.Comparadores.ComparadorDePartidaPorCantidad;
import Utiles.ClasesUtiles.Colecciones.Lista;
import static Utiles.MetodosUtiles.MetodosUtiles.cantidadDeCaracteresDeNumero;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.Formatter;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class Partida implements Comparable<Partida>, Serializable {

    private String nombreJugador;
    private int cantidadDemovimiento;
    private boolean gano;

    public Partida(String nombreJugador, int cantidadDemovimiento, boolean gano) {
        this.nombreJugador = nombreJugador;
        this.cantidadDemovimiento = cantidadDemovimiento;
        this.gano = gano;
    }

//    public Partida(int cantidadDemovimiento, boolean gano) {
//        this.cantidadDemovimiento = cantidadDemovimiento;
//        this.gano = gano;
//    }
    public int getCantidadDemovimiento() {
        return cantidadDemovimiento;
    }

    public boolean isGano() {
        return gano;
    }

    public String getNombreJugador() {
        return nombreJugador;
    }

    public void setNombreJugador(String nombreJugador) {
        this.nombreJugador = nombreJugador;
    }

    public static LinkedList<Partida> ordenarPorCantidad(LinkedList<Partida> P) {
        if (P.isEmpty() || P.size() == 1) {
            return P;
        }
        Collections.sort(P, new ComparadorDePartidaPorCantidad());
        return P;
    }

    public static int[] caracteres(LinkedList<Partida>... L) {
        int cantidad2[] = {0, 0};
        for (LinkedList<Partida> p : L) {
            int cantidad[] = {0, 0};
            for (Partida l : p) {
                cantidad[0] = cantidad[0] > l.getNombreJugador().length() ? cantidad[0] : l.getNombreJugador().length();
                cantidad[1] = cantidad[1] > l.getCantidadDemovimiento() ? cantidad[1] : l.getCantidadDemovimiento();
            }
            cantidad2[0] = cantidad2[0] > cantidad[0] ? cantidad2[0] : cantidad[0];
            cantidad2[1] = cantidad2[1] > cantidad[1] ? cantidad2[1] : cantidad[1];
        }

        cantidad2[1] = cantidadDeCaracteresDeNumero(cantidad2[1]);
        cantidad2[0] = cantidad2[0] + 2;
        return cantidad2;
    }

    public static String stringListaParida(LinkedList<Partida> L, int caracteres1, int caracteres2, int caracteres3) {
        if (L.isEmpty()) {
            return "";
        }
        String respuesta = "";
        for (int i = 0; i < L.size(); i++) {
            respuesta += String.format("%" + (-caracteres1) + "s %" + (-caracteres2) + "s %" + (caracteres3) + "s", L.get(i).getNombreJugador(), L.get(i).isGano() ? "Gano" : "Perdio", L.get(i).getCantidadDemovimiento());
            if (i != L.size() - 1) {
                respuesta += "\n";
            }
        }
        return respuesta;
    }

    public static String stringListaParida(LinkedList<Partida> L) {
        if (L.isEmpty()) {
            return "";
        }
        int cantidad[] = caracteres(L);
//        int cantidad[] = {0, 0};
//        for (Partida l : L) {
//            cantidad[0] = cantidad[0] > l.getNombreJugador().length() ? cantidad[0] : l.getNombreJugador().length();
//            cantidad[1] = cantidad[1] > l.getCantidadDemovimiento() ? cantidad[1] : l.getCantidadDemovimiento();
//        }
//        cantidad[1] = cantidadDeCaracteresDeNumero(cantidad[1]);
//        cantidad[0] = cantidad[0] + 2;
        //  String uno="%"+(-cantidad[0])+"s %-8s %"+(cantidad[1])+"s";
        //   System.out.println("uno="+uno);
//        String respuesta = "";
//        for (int i = 0; i < L.size(); i++) {
//            respuesta += String.format("%" + (-cantidad[0]) + "s %-8s %" + (cantidad[1]) + "s", L.get(i).getNombreJugador(), L.get(i).isGano() ? "Gano" : "Perdio", L.get(i).getCantidadDemovimiento());
//            if (i != L.size() - 1) {
//                respuesta += "\n";
//            }
//        }
        return stringListaParida(L, cantidad[0], 8, cantidad[1]);
    }

    public static String stringListaParidaConEnunciados(LinkedList<Partida>... P) {
        return stringListaParidaConEnunciados("Nombre", "Resultado", "Cantidad de Movimientos", 0, P);
    }

    public static String stringListaParidaConEnunciados(int distanciExtra, LinkedList<Partida>... P) {
        return stringListaParidaConEnunciados("Nombre", "Resultado", "Cantidad de Movimientos", distanciExtra, P);
    }

    public static String stringListaParidaConEnunciados(String nombre, String gano, String cantidadDeMovimientos, int distanciExtra, LinkedList<Partida>... P) {
        String respuesta = "";
        int cantidad[] = caracteres(P);
        cantidad[0] = cantidad[0] > nombre.length() ? cantidad[0] : nombre.length();
        // cantidad[1] = cantidad[1] > cantidadDeMovimientos.length() ? cantidad[1] : cantidadDeMovimientos.length();
        int ganoLengh = (8 > gano.length() ? 8 : gano.length());
        // respuesta += String.format("%"+(-cantidad[0])+"s %"+(-ganoLengh)+"s %"+(cantidad[1])+"s",nombre,gano,cantidadDeMovimientos)+"\n";
        respuesta += String.format("%" + (-cantidad[0]) + "s %" + (-ganoLengh) + "s %s", nombre, gano, cantidadDeMovimientos) + "\n";
        for (LinkedList<Partida> p : P) {
            respuesta += stringListaParida(p, cantidad[0] + distanciExtra, ganoLengh + distanciExtra, cantidad[1] + 5 + distanciExtra) + "\n";
        }
        return respuesta;
        //int cantidad[] = caracteres(L);
    }

    public static void imprimirLista(LinkedList<Partida> P) {
        System.out.println(stringListaParida(P));
    }

    public static void imprimirListaConEnunciados(LinkedList<Partida> P) {
        System.out.println(stringListaParidaConEnunciados(P));
    }

    public static void crearArchivoDeTextoFormateado(File direccion, LinkedList<Partida> P) throws FileNotFoundException {
        crearArchivoDeTextoFormateado(direccion, "", "Nombre", "Resultado", "Cantidad de Movimientos", P);
    }

    public static void crearArchivoDeTextoFormateado(String direccion, LinkedList<Partida> P) throws FileNotFoundException {
        crearArchivoDeTextoFormateado(null, direccion, "Nombre", "Resultado", "Cantidad de Movimientos", P);
    }

    private static void crearArchivoDeTextoFormateado(File f, String direccion, String nombre, String gano, String cantidadDeMovimientos, LinkedList<Partida> P) throws FileNotFoundException {
        Formatter F;
        PrintWriter p;
        if (!direccion.isEmpty()) {
            F = new Formatter(direccion);
            F.close();
            p = new PrintWriter(direccion);
        } else {
            F = new Formatter(f);
            F.close();
            p = new PrintWriter(f);
        }

        int cantidad[] = caracteres(P);
        cantidad[0] = cantidad[0] > nombre.length() ? cantidad[0] : nombre.length();
        int ganoLengh = (8 > gano.length() ? 8 : gano.length());
        p.println(String.format("%" + (-cantidad[0]) + "s %" + (-ganoLengh) + "s %s", nombre, gano, cantidadDeMovimientos) + "\n");

        for (Partida p2 : P) {
            p.println(String.format("%" + (-cantidad[0]) + "s %" + (-8) + "s %" + (cantidad[1]) + "s", p2.getNombreJugador(), p2.isGano() ? "Gano" : "Perdio", p2.getCantidadDemovimiento()));
            //p.append(String.format("%" + (-cantidad[0]) + "s %" + (-8) + "s %" + (cantidad[1]) + "s", p2.getNombreJugador(),p2.isGano() ? "Gano" : "Perdio", p2.getCantidadDemovimiento())+"\n");
        }
        p.close();
    }

    @Override
    public int compareTo(Partida o) {
        if (cantidadDemovimiento == o.getCantidadDemovimiento()) {
            return 0;
        }
        if (cantidadDemovimiento > o.getCantidadDemovimiento()) {
            return 1;
        }
//        if(cantidadDemovimiento<o.getCantidadDemovimiento()){
//        return -1;
//        }
        return -1;
    }

//    private final class comparadorPorCantidad implements Comparator<Partida> {
//
//        @Override
//        public int compare(Partida o1, Partida o2) {
//            return o1.compareTo(o2);
//        }
//
//    }
}
