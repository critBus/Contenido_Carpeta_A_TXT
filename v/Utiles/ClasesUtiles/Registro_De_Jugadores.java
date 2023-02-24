/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
//import solitario3.Solitario_Juego;
import Utiles.ClasesUtiles.Comparadores.*;
import static Utiles.ClasesUtiles.Partida.*;
import java.util.Arrays;

import static Utiles.MetodosUtiles.Archivo.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class Registro_De_Jugadores implements Serializable {

    private HashMap<String, Jugador> jugadores;
    //LinkedList<Jugador> jugadores;

//    public Registro_De_Jugadores() {
//        this(new LinkedList<Jugador>());
//    }
//
//    public Registro_De_Jugadores(LinkedList<Jugador> jugadores) {
//        this.jugadores = jugadores;
//    }
    public Registro_De_Jugadores() {
        this(new HashMap<String, Jugador>());
    }

    public Registro_De_Jugadores(HashMap<String, Jugador> jugadores) {
        this.jugadores = jugadores;
    }

    public int cantidadDeJugadores() {
        return jugadores.size();
    }

    public LinkedList<Partida> getPartidas() {
        LinkedList<Partida> todasLasPartidas = new LinkedList<Partida>();
        //  System.out.println("jugadores.size()="+jugadores.size());

        for (int i = 0; i < jugadores.keySet().size(); i++) {//System.out.println("i="+i);
            // System.out.println("jugadores.get(i).cantidadDePartidas()="+jugadores.get(i).cantidadDePartidas());
            todasLasPartidas.addAll(jugadores.get(getNombres()[i]).getPartidas());
        }
        return todasLasPartidas;
    }

    public LinkedList<Partida> getPartidasOrdenadasPorCantidad() {
        LinkedList<Partida> todasLasPartidas = getPartidas();
        Collections.sort(todasLasPartidas, new ComparadorDePartidaPorCantidad());
        return todasLasPartidas;
    }

    public LinkedList<Partida> getPartidasOrdenadasPorNombre() {
        LinkedList<Partida> todasLasPartidas = getPartidas();
        Collections.sort(todasLasPartidas, new ComparadorDePartidaPorNombre());
        return todasLasPartidas;
    }

    public LinkedList<Partida> getPartidasOrdenadasPorNombreYCantidad() {
        LinkedList<Partida> todasLasPartidas = new LinkedList<Partida>();
        String nombresAlfabeticamente[] = getNombresAlfabeticamente();
        for (int i = 0; i < nombresAlfabeticamente.length; i++) {
            todasLasPartidas.addAll(getPartidasGanadasOrdenadasPorCantidad(nombresAlfabeticamente[i]));
            todasLasPartidas.addAll(getPartidasPerdidasOrdenadasPorCantidad(nombresAlfabeticamente[i]));
        }
        return todasLasPartidas;
    }

    public String[] getNombres() {
        return jugadores.keySet().toArray(new String[]{});
    }

    public String[] getNombresAlfabeticamente() {
        String A[] = getNombres();
        Arrays.sort(A, new ComparadorOrdenAlfabetico());
        return A;
    }

    public LinkedList<Partida> getPartidas(String nombreJugador) {
        return jugadores.get(nombreJugador).getPartidas();
    }

    public LinkedList<Partida> getPartidasGanadas() {
        LinkedList<Partida> todasLasPartidas = new LinkedList<Partida>();
        for (int i = 0; i < getNombres().length; i++) {
            todasLasPartidas.addAll(jugadores.get(getNombres()[i]).getPartidasGanadas());
        }
        return todasLasPartidas;
    }

    public LinkedList<Partida> getPartidasGanadas(String nombreJugador) {
        return jugadores.get(nombreJugador).getPartidasGanadas();
    }

    public LinkedList<Partida> getPartidasPerdidas() {
        LinkedList<Partida> todasLasPartidas = new LinkedList<Partida>();
        for (int i = 0; i < getNombres().length; i++) {
            todasLasPartidas.addAll(jugadores.get(getNombres()[i]).getPartidasPerdidas());
        }
        return todasLasPartidas;
    }

    public LinkedList<Partida> getPartidasPerdidas(String nombreJugador) {
        return jugadores.get(nombreJugador).getPartidasPerdidas();
    }

    public LinkedList<Partida> getPartidasGanadasOrdenadasPorCantidad() {
        LinkedList<Partida> todasLasPartidas = getPartidasGanadas();
        Collections.sort(todasLasPartidas, new ComparadorDePartidaPorCantidad());
        return todasLasPartidas;
    }

    public LinkedList<Partida> getPartidasGanadasOrdenadasPorCantidad(String nombreJugador) {
        LinkedList<Partida> todasLasPartidas = jugadores.get(nombreJugador).getPartidasGanadas();
        Collections.sort(todasLasPartidas, new ComparadorDePartidaPorCantidad());
        return todasLasPartidas;
    }

    public LinkedList<Partida> getPartidasGanadasOrdenadasPorNombreYCantidad() {
        LinkedList<Partida> todasLasPartidas = new LinkedList<Partida>();
        String nombresAlfabeticamente[] = getNombresAlfabeticamente();
        for (int i = 0; i < nombresAlfabeticamente.length; i++) {
            todasLasPartidas.addAll(getPartidasGanadasOrdenadasPorCantidad(nombresAlfabeticamente[i]));
        }
        return todasLasPartidas;
    }

    public LinkedList<Partida> getPartidasPerdidasOrdenadasPorNombreYCantidad() {
        LinkedList<Partida> todasLasPartidas = new LinkedList<Partida>();
        String nombresAlfabeticamente[] = getNombresAlfabeticamente();
        for (int i = 0; i < nombresAlfabeticamente.length; i++) {
            todasLasPartidas.addAll(getPartidasPerdidasOrdenadasPorCantidad(nombresAlfabeticamente[i]));
        }
        return todasLasPartidas;
    }

    public LinkedList<Partida> getPartidasPerdidasOrdenadasPorCantidad() {
        LinkedList<Partida> todasLasPartidas = getPartidasPerdidas();
        Collections.sort(todasLasPartidas, new ComparadorDePartidaPorCantidad());
        return todasLasPartidas;
    }

    public LinkedList<Partida> getPartidasPerdidasOrdenadasPorCantidad(String nombreJugador) {
        LinkedList<Partida> todasLasPartidas = jugadores.get(nombreJugador).getPartidasPerdidas();
        Collections.sort(todasLasPartidas, new ComparadorDePartidaPorCantidad());
        return todasLasPartidas;
    }

    public void addPartida(Partida p) {
        if (jugadores.containsKey(p.getNombreJugador())) {
            jugadores.get(p.getNombreJugador()).addPartida(p);

        } else {
            jugadores.put(p.getNombreJugador(), new Jugador(p));
        }
        // System.out.println("jugadores.get(p.getNombreJugador()).cantidadDePartidas()="+jugadores.get(p.getNombreJugador()).cantidadDePartidas());
        // System.out.println("jugadores.get(p.getNombreJugador()).getNombre()="+jugadores.get(p.getNombreJugador()).getNombre());   
        // imprimirLista(jugadores.get(p.getNombreJugador()).getPartidas());

    }

    public static Registro_De_Jugadores crearRegistro(String direccion) throws IOException, FileNotFoundException, ClassNotFoundException {
        Object o = cargarArchivoYCrearDeSerNesesario(direccion, new Registro_De_Jugadores());
        return (Registro_De_Jugadores) o;
    }

    public void cargarRegistro(String direccion) throws IOException, FileNotFoundException, ClassNotFoundException {
        Object o = cargarArchivoYCrearDeSerNesesario(direccion, new Registro_De_Jugadores());
        jugadores = ((Registro_De_Jugadores) o).jugadores;

    }

    public void guardarRegistro(String direccion) throws IOException {
        crearArchivo(direccion, this);
    }

    public void guardarRegistroTXT(File direccion) throws FileNotFoundException {
       crearArchivoDeTextoFormateado(direccion, getPartidasOrdenadasPorNombreYCantidad());
    }

    public void guardarRegistroTXT(String direccion) throws FileNotFoundException {
        crearArchivoDeTextoFormateado(direccion, getPartidasOrdenadasPorNombreYCantidad());
    }

    public boolean isEmpty() {
        return jugadores.isEmpty();
    }

}
