/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Tiempo2;

//import java.util.Arrays;
import static Utiles.MetodosUtiles.Tempus.*;
import Utiles.ClasesUtiles.Interfases.Iteradora;
import Utiles.ClasesUtiles.Interfases.metodosParaInterfases;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class Dia implements Comparable<Dia>, Iteradora<Dia>, Serializable {

    private final Fecha fecha;
    private final String nombre;
    private boolean feriado;
    //  public static final String nombres[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};

    public Dia(Fecha fecha) {
        this(fecha, nombreDia(fecha));
//        this.fecha = fecha;
//        nombre=nombreDia(fecha);
    }
//************

    public Dia(Fecha fecha, String nombre) {
        this(fecha, nombre, esFeriado(fecha));
    }

    public Dia(Fecha fecha, String nombre, boolean feriado) {
        this.fecha = fecha;
        this.nombre = nombre;
        this.feriado = feriado;
    }

    public Dia(String dia, String mes) {
        this(dia, mes, 1);
    }

    public Dia(String dia, String mes, int año) {
        this(new Fecha(posicionDia(dia), posicionMes(mes), año));

    }

    public Dia(int dia, String mes) {
        this(dia, mes, 1);
    }

    public Dia(int dia, String mes, int año) {
        this(new Fecha(dia, posicionMes(mes), año));
        // this.fecha = ;
    }

    public Dia(String dia, int mes) {
        this(dia, mes, 1);
    }

    public Dia(String dia, int mes, int año) {
        this(new Fecha(posicionDia(dia), mes, año));

    }

    //**********
    public Dia(int dia, int mes) {
        this(dia, mes, 1);
    }

    public Dia(int dia, int mes, int año) {
        this(new Fecha(dia, mes, año));
        // this.fecha =;
    }

    public Dia(int dia, int mes, int año, String nombre) {
        this(new Fecha(dia, mes, año), nombre);
        // this.fecha =;
    }

    public Fecha getFecha() {
        return fecha;
    }

    public String getNombre() {
        return nombre;
    }

    public boolean isFeriado() {
        return feriado;
    }

    public void setFeriado(boolean feriado) {
        this.feriado = feriado;
    }

    @Override
    public String toString() {
        return String.format("%-9s %s", getNombre(), getFecha());
    }

    @Override
    public int compareTo(Dia o) {
        return fecha.compareTo(o.getFecha());
    }

    @Override
    public Dia next() {
//        int mes = fecha.getMes();
//        int año = fecha.getAño();
//        int dia = fecha.getDia() + 1;
//        if (!(cantidadDeDiasMes(mes) > dia)) {
//            if (mes == 11) {
//                ++año;
//            }
//            mes = aumentoMes(mes);
//            dia = 0;
//        }
        return new Dia(fecha.next(), aumentoNombreDia(nombre));
    }

    @Override
    public Dia next(int siguientes) {
        return (Dia) metodosParaInterfases.next(this, siguientes);
    }

    @Override
    public Dia before() {
//        int mes = fecha.getMes();
//        int año = fecha.getAño();
//        int dia = fecha.getDia() + 1;
//        if (dia<0) {
//            if (mes == 0) {
//                --año;
//            }
//            mes = retrocesoMes(mes);
//            dia = 0;
//        }
        return new Dia(fecha.before(), retrocesoNombreDia(nombre));
    }

    @Override
    public Dia before(int anteriores) {
        return (Dia) metodosParaInterfases.before(this, anteriores);
    }

    public boolean esNoLaborable() {
        return isFeriado() || esFinDeSemana();
    }

    public boolean esFinDeSemana() {
        return or(nombre, nombresDiaFinesDeSemana);
    }

    public boolean esEntreSemana() {
        return or(nombre, nombresDiaEntreSemana);
    }

}
