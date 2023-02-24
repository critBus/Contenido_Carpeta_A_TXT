/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Subtitulo;

//import Aplicaciones.editor_de_subtitulos2.*;
import Utiles.ClasesUtiles.Tiempo2.Tiempo;
import java.util.Arrays;

/**
 * Version 0.2
 * @author Rene
 */
public class Seccion_De_Subtitulo implements Comparable<Seccion_De_Subtitulo> {

    //int Indice;
    private int MSegundo1, MSegundo2;
    private int Segundo1, Segundo2;
    private int Minuto1, Minuto2;
    private int Hora1, Hora2;
    final String intermedio = " --> ";
    String dialogo;
    private int tiempos[];

    public Seccion_De_Subtitulo() {
        this(0, 0, 0, 0, 0, 0, 0, 0, "");
    }

    public Seccion_De_Subtitulo(Seccion_De_Subtitulo s) {
        this(s.getTiempoFinal(), s.getTiempoFinal(), s.getDialogo());
    }

    public Seccion_De_Subtitulo(Tiempo a) {
        this(a.getMSegundo(), a.getMSegundo() + 1, a.getSegundo(), a.getSegundo(), a.getMinuto(), a.getMinuto(), a.getHora(), a.getHora(), "");
    }

    public Seccion_De_Subtitulo(Tiempo a, Tiempo b, String dialogo) {
        this(a.getMSegundo(), b.getMSegundo(), a.getSegundo(), b.getSegundo(), a.getMinuto(), b.getMinuto(), a.getHora(), b.getHora(), dialogo);
    }

    public Seccion_De_Subtitulo(int MSegundo1, int MSegundo2, int Segundo1, int Segundo2, int Minuto1, int Minuto2, int Hora1, int Hora2, String dialogo) {
        //this.Indice = Indice;
        this.MSegundo1 = MSegundo1;
        this.MSegundo2 = MSegundo2;
        this.Segundo1 = Segundo1;
        this.Segundo2 = Segundo2;
        this.Minuto1 = Minuto1;
        this.Minuto2 = Minuto2;
        this.Hora1 = Hora1;
        this.Hora2 = Hora2;
        this.dialogo = dialogo;
        //System.out.println("this.MSegundo1="+this.MSegundo1);
        actualizarTiempos();
        // System.out.println(Arrays.toString(tiempos));
    }

    private void setTiempos(Tiempo a, Tiempo b) {
        setTiempos(a.getMSegundo(), b.getMSegundo(), a.getSegundo(), b.getSegundo(), a.getMinuto(), b.getMinuto(), a.getHora(), b.getHora());
    }

    private void setTiempos(int MSegundo1, int MSegundo2, int Segundo1, int Segundo2, int Minuto1, int Minuto2, int Hora1, int Hora2) {
        //this.Indice = Indice;
        this.MSegundo1 = MSegundo1;
        this.MSegundo2 = MSegundo2;
        this.Segundo1 = Segundo1;
        this.Segundo2 = Segundo2;
        this.Minuto1 = Minuto1;
        this.Minuto2 = Minuto2;
        this.Hora1 = Hora1;
        this.Hora2 = Hora2;
        actualizarTiempos();
    }

    private void actualizarTiempos() {
        tiempos = new int[]{this.Hora1, this.Minuto1, this.Segundo1, this.MSegundo1, this.Hora2, this.Minuto2, this.Segundo2, this.MSegundo2};
    }

    public void ceroTiempos1() {
        setHora1(0);
        setMSegundo1(0);
        setMinuto1(0);
        setSegundo1(0);
    }

    public void ceroTiempos2() {
        setHora2(0);
        setMSegundo2(0);
        setMinuto2(0);
        setSegundo2(0);
    }

    public int getTiempo(int indice) {
        //  System.out.println("2 indice=" + indice);

        return tiempos[indice];
    }

    public int getMSegundo1() {
        return MSegundo1;
    }

    public void setMSegundo1(int MSegundo1) {
        tiempos[3] = MSegundo1;
        this.MSegundo1 = MSegundo1;
    }

    public int getMSegundo2() {
        return MSegundo2;
    }

    public void setMSegundo2(int MSegundo2) {
        tiempos[7] = MSegundo2;
        this.MSegundo2 = MSegundo2;
    }

    public int getSegundo1() {
        return Segundo1;
    }

    public void setSegundo1(int Segundo1) {
        tiempos[2] = Segundo1;
        this.Segundo1 = Segundo1;
    }

    public int getSegundo2() {
        return Segundo2;
    }

    public void setSegundo2(int Segundo2) {
        tiempos[6] = Segundo2;
        this.Segundo2 = Segundo2;
    }

    public int getMinuto1() {
        return Minuto1;
    }

    public void setMinuto1(int Minuto1) {
        tiempos[1] = Minuto1;
        this.Minuto1 = Minuto1;
    }

    public int getMinuto2() {
        return Minuto2;
    }

    public void setMinuto2(int Minuto2) {
        tiempos[5] = Minuto2;
        this.Minuto2 = Minuto2;
    }

    public int getHora1() {
        return Hora1;
    }

    public void setHora1(int Hora1) {
        tiempos[0] = Hora1;
        this.Hora1 = Hora1;
    }

    public int getHora2() {
        return Hora2;
    }

    public void setHora2(int Hora2) {
        tiempos[4] = Hora2;
        this.Hora2 = Hora2;
    }

    public String getDialogo() {
        return dialogo;
    }

    public void setDialogo(String dialogo) {
        this.dialogo = dialogo;
    }

    public int[] getTiempos() {
        return tiempos;
    }

    public Tiempo getTiempoInicial() {
        return new Tiempo(Hora1, Minuto1, Segundo1, MSegundo1);
    }

    public Tiempo getTiempoFinal() {
        return new Tiempo(Hora2, Minuto2, Segundo2, MSegundo2);
    }

//    public void setTiempoInicial(Tiempo a) {
//    }
//
//    public void setTiempoFinal(Tiempo a) {
//    }
    @Override
    public int compareTo(Seccion_De_Subtitulo o) {
        return getTiempoInicial().compareTo(o.getTiempoInicial());
    }

    public void aumentar(Tiempo aumento) {
        Tiempo inicial = getTiempoInicial(), fin = getTiempoFinal();
        inicial.aumentar(aumento);
        fin.aumentar(aumento);
        setTiempos(inicial, fin);
    }

    public void aumentar(Tiempo aumentoIsquerda,Tiempo aumentoDerecha) {
        Tiempo inicial = getTiempoInicial(), fin = getTiempoFinal();
        inicial.aumentar(aumentoIsquerda);
        fin.aumentar(aumentoDerecha);
        setTiempos(inicial, fin);
    }

    @Override
    public String toString() {
         return String.format("%s\n%02d:%02d:%02d,%03d%s%02d:%02d:%02d,%03d\n%s\n",
                0, getHora1(), getMinuto1(), getSegundo1(), getMSegundo1(), intermedio, getHora2(), getMinuto2(),
               getSegundo2(), getMSegundo2(), dialogo);
   
    }
    
    
}
