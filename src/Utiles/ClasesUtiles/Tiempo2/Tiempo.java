/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Tiempo2;

import static Utiles.MetodosUtiles.MetodosUtiles.modificarTiempo;
import static Utiles.MetodosUtiles.Arreglos.arreglo;
import java.io.Serializable;
import Utiles.ClasesUtiles.Interfases.Comparaciones;
import Utiles.ClasesUtiles.Interfases.Adaptadores.Comparacion;
import static Utiles.MetodosUtiles.MetodosUtiles.modificarTiempoConNegativos;

/**
 * 1.2
 *
 * @author Rene
 */
public class Tiempo extends Comparacion<Tiempo> implements Serializable {

    private int MSegundo;
    private int Segundo;
    private int Minuto;
    private int Hora;
    private boolean admitirNegativo;

    public Tiempo(Tiempo t) {
        this(t.getHora(), t.getMinuto(), t.getSegundo(), t.MSegundo, t.isAdmitirNegativo());
    }

    public Tiempo(int Hora, int Minuto, int Segundo) {
        this(Hora, Minuto, Segundo, 0, false);
    }

    public Tiempo(int Hora, int Minuto, int Segundo, int MSegundo) {
        this(Hora, Minuto, Segundo, MSegundo, false);
    }

    public Tiempo(int Hora, int Minuto, int Segundo, int MSegundo, boolean admitirNegativo) {
        this.MSegundo = MSegundo;
        //System.out.println("MSegundo=" + MSegundo);
        this.Segundo = Segundo;
        //System.out.println("Segundo=" + Segundo);
        this.Minuto = Minuto;
        //  System.out.println("Minuto=" + Minuto);
        this.Hora = Hora;
        // System.out.println("Hora=" + Hora);
        this.admitirNegativo = admitirNegativo;
        asegararPositivo();
    }

    public boolean isAdmitirNegativo() {
        return admitirNegativo;
    }

    public int getMSegundo() {
        return MSegundo;
    }

    public void setMSegundo(int MSegundo) {
        this.MSegundo = MSegundo;
    }

    public int getSegundo() {
        return Segundo;
    }

    public void setSegundo(int Segundo) {
        this.Segundo = Segundo;
    }

    public int getMinuto() {
        return Minuto;
    }

    public void setMinuto(int Minuto) {
        this.Minuto = Minuto;
    }

    public int getHora() {
        return Hora;
    }

    public void setHora(int Hora) {
        this.Hora = Hora;
    }

    public void modificar(int horas, int minutos, int segundos, int miliSegundos) {
        // int A[]={this.MSegundo,this.Segundo,this.Minuto,this.Hora};//arreglo(this.MSegundo,this.Segundo,this.Minuto,this.Hora)
        int A[] = modificarTiempo(0, arreglo(miliSegundos, segundos, minutos, horas), arreglo(this.MSegundo, this.Segundo, this.Minuto, this.Hora), 1000, 60, 60, 60);
        this.MSegundo = !admitirNegativo && A[0] < 0 ? 0 : A[0];
        this.Segundo = !admitirNegativo && A[1] < 0 ? 0 : A[1];
        this.Minuto = !admitirNegativo && A[2] < 0 ? 0 : A[2];
        this.Hora = !admitirNegativo && A[3] < 0 ? 0 : A[3];
    }

    public void modificarConNegativos(int horas, int minutos, int segundos, int miliSegundos) {
        // int A[]={this.MSegundo,this.Segundo,this.Minuto,this.Hora};//arreglo(this.MSegundo,this.Segundo,this.Minuto,this.Hora)
        int A[] = modificarTiempoConNegativos(0, arreglo(miliSegundos, segundos, minutos, horas), arreglo(this.MSegundo, this.Segundo, this.Minuto, this.Hora), 1000, 60, 60, 60);
        this.MSegundo = !admitirNegativo && A[0] < 0 ? 0 : A[0];
        this.Segundo = !admitirNegativo && A[1] < 0 ? 0 : A[1];
        this.Minuto = !admitirNegativo && A[2] < 0 ? 0 : A[2];
        this.Hora = !admitirNegativo && A[3] < 0 ? 0 : A[3];
    }

    public void aumentar(Tiempo t) {
        aumentar(t.getHora(), t.getMinuto(), t.getSegundo(), t.getMSegundo());
    }

    public void restar(Tiempo t) {
        restar(t.getHora(), t.getMinuto(), t.getSegundo(), t.getMSegundo());
    }

    public void aumentarConNegativos(Tiempo t) {
        aumentarConNegativos(t.getHora(), t.getMinuto(), t.getSegundo(), t.getMSegundo());
    }

    public void restarConNegativos(Tiempo t) {
        restarConNegativos(t.getHora(), t.getMinuto(), t.getSegundo(), t.getMSegundo());
    }

    public Tiempo copia(boolean admitirNegativo) {
        return new Tiempo(Hora, Minuto, Segundo, MSegundo, admitirNegativo);
    }

    public Tiempo copia() {
        return new Tiempo(Hora, Minuto, Segundo, MSegundo, admitirNegativo);
    }

    public void setAdmitirNegativo(boolean admitirNegativo) {
        this.admitirNegativo = admitirNegativo;
        asegararPositivo();
    }

    public boolean sonTodosCero() {
        return sonCeroHoras() && sonCeroMinutos() && sonCeroSegundos() && sonCeroMSegundos();
    }

    public void todoACero() {
        this.MSegundo = 0;
        this.Segundo = 0;
        this.Minuto = 0;
        this.Hora = 0;
    }

    public void volverPostivo() {
        this.MSegundo = MSegundo < 0 ? MSegundo * -1 : MSegundo;
        this.Segundo = Segundo < 0 ? Segundo * -1 : Segundo;
        this.Minuto = Minuto < 0 ? Minuto * -1 : Minuto;
        this.Hora = Hora < 0 ? Hora * -1 : Hora;
    }

    public void volverNegativo() {
        this.MSegundo = MSegundo * -1;
        this.Segundo = Segundo * -1;
        this.Minuto = Minuto * -1;
        this.Hora = Hora * -1;
    }

    public void asegararPositivo() {
        this.MSegundo = !admitirNegativo && MSegundo < 0 ? 0 : MSegundo;
        this.Segundo = !admitirNegativo && Segundo < 0 ? 0 : Segundo;
        this.Minuto = !admitirNegativo && Minuto < 0 ? 0 : Minuto;
        this.Hora = !admitirNegativo && Hora < 0 ? 0 : Hora;
    }

    public void aumentar(int horas, int minutos, int segundos, int miliSegundos) {
        modificar(horas, minutos, segundos, miliSegundos);

    }

    public void restar(int horas, int minutos, int segundos, int miliSegundos) {
        modificar(horas * -1, minutos * -1, segundos * -1, miliSegundos * -1);

    }

    public void aumentarConNegativos(int horas, int minutos, int segundos, int miliSegundos) {
        modificarConNegativos(horas, minutos, segundos, miliSegundos);

    }

    public void restarConNegativos(int horas, int minutos, int segundos, int miliSegundos) {
        modificarConNegativos(horas * -1, minutos * -1, segundos * -1, miliSegundos * -1);

    }

    boolean sonCeroHoras() {
        return Hora == 0;
    }

    boolean sonCeroMinutos() {
        return (Minuto == 0) && (sonCeroHoras());
    }

    boolean sonCeroSegundos() {
        return (Segundo == 0) && (sonCeroMinutos());
    }

    boolean sonCeroMSegundos() {
        return (MSegundo == 0) && (sonCeroSegundos());
    }
    ///-----------------

    boolean sonPositivosHoras() {
        return Hora > 0;
    }

    boolean sonPositivosMinutos() {
        return (Minuto > 0) || (sonPositivosHoras());
    }

    boolean sonPositivosSegundos() {
        return (Segundo > 0) || (sonPositivosMinutos());
    }

    boolean sonPositivosMSegundos() {
        return (MSegundo > 0) || (sonPositivosSegundos());
    }

    //-------------------------
    boolean sonNegativosHoras() {
        return Hora < 0;
    }

    boolean sonNegativosMinutos() {
        return (Minuto < 0) || (sonNegativosHoras());
    }

    boolean sonNegativosSegundos() {
        return (Segundo < 0) || (sonNegativosMinutos());
    }

    boolean sonNegativosMSegundos() {
        return (MSegundo < 0) || (sonNegativosSegundos());
    }

    @Override
    public String toString() {

//        System.out.println("Hora=" + Hora);
//        System.out.println("Minuto=" + Minuto);
//        System.out.println("Segundo=" + Segundo);
//        System.out.println("MSegundo=" + MSegundo);
        return sonNegativosMSegundos() ? ("- " + Hora * -1 + ":" + Minuto * -1 + ":" + Segundo * -1 + ":" + MSegundo * -1) : (Hora + ":" + Minuto + ":" + Segundo + ":" + MSegundo);
    }

    public String horaString() {
        return sonNegativosMSegundos() ? ("- " + Hora * -1 + ":" + Minuto * -1 + ":" + Segundo * -1) : (Hora + ":" + Minuto + ":" + Segundo);
    }

    @Override
    public int compareTo(Tiempo o) {
        if (Hora == o.getHora()) {
            if (Minuto == o.getMinuto()) {
                if (Segundo == o.getSegundo()) {
                    return new Integer(MSegundo).compareTo(o.getMSegundo());

                }
                return new Integer(Segundo).compareTo(o.getSegundo());
            }
            return new Integer(Minuto).compareTo(o.getMinuto());
        }
        return new Integer(Hora).compareTo(o.getHora());
    }
}
