/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles;

import Utiles.ClasesUtiles.Colecciones.Lista;
import java.io.Serializable;
import java.util.Arrays;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class Jugador implements Comparable<Jugador>, Serializable {

    private String Nombre;
    private LinkedList<Partida> partidas;
    //  Lista<Partida> partidas;

    public Jugador(String Nombre) {
        this(Nombre, new LinkedList<Partida>());
    }

    public Jugador(Partida p) {
        this(p.getNombreJugador(), new LinkedList<Partida>(Arrays.asList(new Partida[]{p})));
    }

    public Jugador(String Nombre, LinkedList<Partida> partidas) {
        this.Nombre = Nombre;
        this.partidas = partidas;
    }

    public LinkedList<Partida> getPartidas() {
        return partidas;
    }

    public void setPartidas(LinkedList<Partida> partidas) {
        this.partidas = partidas;
    }

    public String getNombre() {
        return Nombre;
    }

    public void addPartida(Partida p) {
        partidas.add(p);
    }

    public int cantidadDeVictorias() {
        int victorias = 0;
        for (int i = 0; i < partidas.size(); i++) {
            if (partidas.get(i).isGano()) {
                victorias++;
            }
        }
        return victorias;
    }

    public int cantidadDeDerrotas() {
        return partidas.size() - cantidadDeVictorias();
    }

    public boolean haGanado() {
        for (int i = 0; i < partidas.size(); i++) {
            if (partidas.get(i).isGano()) {
                return true;
            }
        }
        return false;
    }

    public boolean haPerdido() {
        for (int i = 0; i < partidas.size(); i++) {
            if (!partidas.get(i).isGano()) {
                return true;
            }
        }
        return false;
    }

    public LinkedList<Partida> getPartidasGanadas() {
        LinkedList<Partida> partidasGanadas = new LinkedList<Partida>();
        for (int i = 0; i < partidas.size(); i++) {
            if (partidas.get(i).isGano()) {
                partidasGanadas.add(partidas.get(i));
            }
        }
        return partidasGanadas;
    }

    public LinkedList<Partida> getPartidasPerdidas() {
        LinkedList<Partida> partidasPerdidas = new LinkedList<Partida>();
        for (int i = 0; i < partidas.size(); i++) {
            if (!partidas.get(i).isGano()) {
                partidasPerdidas.add(partidas.get(i));
            }
        }
        return partidasPerdidas;
    }

    public int getMenorCantidad() {
        if (partidas.isEmpty()) {
            return 0;
        }
        if (partidas.size() == 1) {
            return partidas.get(0).getCantidadDemovimiento();
        }
        int menorCaontidad = partidas.get(0).getCantidadDemovimiento();
        for (int i = 1; i < partidas.size(); i++) {
            if (partidas.get(0).getCantidadDemovimiento() < menorCaontidad) {
                menorCaontidad = partidas.get(0).getCantidadDemovimiento();
            }
        }
        return menorCaontidad;
    }

    @Override
    public int compareTo(Jugador o) {
        if (cantidadDeVictorias() == o.cantidadDeVictorias()) {
            if (getMenorCantidad() < o.getMenorCantidad()) {
                return -1;
            }
            if (getMenorCantidad() > o.getMenorCantidad()) {
                return 1;
            }

            return 0;
        }
        if (cantidadDeVictorias() > o.cantidadDeVictorias()) {
            return 1;
        }
        return -1;
    }
    
    public int cantidadDePartidas(){
    return partidas.size();
    }
}
