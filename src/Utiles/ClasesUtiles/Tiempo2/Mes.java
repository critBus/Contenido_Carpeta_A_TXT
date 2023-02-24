/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Tiempo2;

import Utiles.ClasesUtiles.Interfases.Iteradora;
import Utiles.ClasesUtiles.Interfases.metodosParaInterfases;
import Utiles.ClasesUtiles.Interfases.mostrarEnConsola;
import static Utiles.MetodosUtiles.Tempus.*;
import Utiles.MetodosUtiles.Tempus;
import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Rene
 */
public class Mes implements mostrarEnConsola, Comparable<Mes>, Iteradora<Mes>, Serializable {

    private final int posicion;
    private final String nombre;
    private final int cantidadDeDias;
    private final int año;
    private final Dia[] dias;

    public Mes(int posicion) {
        this(posicion, 1);
    }

    public Mes(int posicion, int año) {
        this(posicion, nombreMes(posicion), cantidadDeDiasMes(posicion), año);

    }

    public Mes(String nombre) {
        this(nombre, 1);
    }

    public Mes(String nombre, int año) {
        this(posicionMes(nombre), nombre, cantidadDeDiasMes(nombre), año);

    }

    public Mes(int posicion, String nombre, int cantidadDeDias) {
        this(posicion, nombre, cantidadDeDias, 1);

    }

    public Mes(int posicion, String nombre, int cantidadDeDias, int año) {
        this.posicion = posicion;
        this.nombre = nombre;
        this.cantidadDeDias = cantidadDeDias;
        this.año = año;
        dias = inicializarDias(0, posicion, año, cantidadDeDias);
    }

    public int getPosicion() {
        return posicion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadDeDias() {
        return cantidadDeDias;
    }

    public int getAño() {
        return año;
    }

    public Dia[] getDiasNoLaborables() {
        return Tempus.getDiasNoLaborables(dias);
    }

    public Dia[] getFeriados() {
        return Tempus.getFeriados(dias);
    }

    public Dia[] getDiasEntreSemana() {
        return Tempus.getDiasEntreSemana(dias);
    }

    public Dia[] getFinesDeSemana() {
        return Tempus.getFinesDeSemana(dias);
    }

    @Override
    public void imprimir() {
        imprimirDias(dias);
    }

    public Dia[] getDias() {
        return dias;
    }

    @Override
    public int compareTo(Mes o) {
        if (año == o.año) {
            return new Integer(posicion).compareTo(o.posicion);
        }
        return new Integer(año).compareTo(o.año);
    }
//private final int posicion;
//    private final String nombre;
//    private final int cantidadDeDias;
//    private final int año;
//    private final Dia[] dias;

    @Override
    public Mes next() {
        int mes = this.posicion;
        int año = this.año;
        if (++mes > 11) {
            ++año;
            mes = 0;
        }
        return new Mes(mes, año);
    }

    @Override
    public Mes next(int siguientes) {
        return (Mes) metodosParaInterfases.next(this, siguientes);
    }

    @Override
    public Mes before() {
        int mes = this.posicion;
        int año = this.año;
        if (--mes < 0) {
            --año;
            mes = 11;
        }
        return new Mes(mes, año);
    }

    @Override
    public Mes before(int anteriores) {
        return (Mes) metodosParaInterfases.before(this, anteriores);
    }

    @Override
    public String toString() {
        //return nombre+" de "+año;
        return String.format("%-11s de  %s", nombre,año);
    }

    
}
