/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Subtitulo;

//import Aplicaciones.editor_de_subtitulos2.*;
import Utiles.ClasesUtiles.Tiempo2.Tiempo;
import Utiles.Exepciones.IndiceIncorrectoException;
import Utiles.Exepciones.IndiceFinalIncorrectoException;
import Utiles.Exepciones.IndiceInicialIncorrectoException;
import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import java.util.Arrays;
//import static editor_de_subtitulos.Ventana_Principal.T;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Formatter;
import java.util.Scanner;
import javax.swing.JTextArea;
import static Utiles.MetodosUtiles.MetodosUtiles.iguales;
//import AWT.Visual;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.TreeSet;

/**
 * Version 0.2
 *
 * @author Rene
 */
public class Subtitulo {

    private Seccion_De_Subtitulo S[] = new Seccion_De_Subtitulo[0];
    private File direccion;

    public Subtitulo() {
    }

    public Subtitulo(File f) throws FileNotFoundException {
        direccion = f;
        leerSubtituloActual(f);
    }

    public int sise() {
        return S.length;
    }

    public Seccion_De_Subtitulo getFirst() {
        return S[0];
    }

    public Seccion_De_Subtitulo getLast() {
        return S[S.length - 1];
    }

    public Tiempo getFirstTaimInicial() {
        return getFirst().getTiempoInicial();
    }

    public Tiempo getLastTaimInicial() {
        return getLast().getTiempoInicial();
    }

    public Seccion_De_Subtitulo crearSeccion(int MSegundo1, int MSegundo2, int Segundo1, int Segundo2, int Minuto1, int Minuto2, int Hora1, int Hora2, String dialogo) {
        return new Seccion_De_Subtitulo(MSegundo1, MSegundo2, Segundo1, Segundo2, Minuto1, Minuto2, Hora1, Hora2, dialogo);
    }

    public void escribirSubtitulo(File e) throws FileNotFoundException {
        Formatter Fo = new Formatter(e);
        Fo.close();
        JTextArea T = new JTextArea(getSubtitulo());
        PrintWriter p = new PrintWriter(e);
        Scanner s = new Scanner(T.getText());
        int numeroDeLinea = 0;
        while (numeroDeLinea < T.getLineCount() - 1) {
            numeroDeLinea++;
            p.println(s.nextLine());
        }
        p.close();
    }

    public String getSubtitulo() {
        String subtitulo = "";
        for (int i = 0; i < S.length; i++) {
            subtitulo += getSeccion(i);
        }
        return subtitulo + "";
    }

    public String getSeccion(int indice) {
        return String.format("%s\n%02d:%02d:%02d,%03d%s%02d:%02d:%02d,%03d\n%s\n",
                indice + 1, S[indice].getHora1(), S[indice].getMinuto1(), S[indice].getSegundo1(), S[indice].getMSegundo1(), S[indice].intermedio, S[indice].getHora2(), S[indice].getMinuto2(),
                S[indice].getSegundo2(), S[indice].getMSegundo2(), S[indice].dialogo);
    }

    public void setDialogo(String a, int indice) {
        S[indice].dialogo = a;
    }

    public void aumentarIndice(int indice, int horasIsqierda, int minutosIsqierda, int segundosIsqierda, int miliSegundosIsqierda, int horasDerecha, int minutosDerecha, int segundosDerecha, int miliSegundosDerecha) {
        if (!iguales(0, miliSegundosIsqierda, miliSegundosDerecha)) { //Isqierda
            aumentarMSegundos(miliSegundosIsqierda, miliSegundosDerecha, indice);
        }
        if (!iguales(0, segundosIsqierda, segundosDerecha)) { //Isqierda
            aumentarSegundos(segundosIsqierda, segundosDerecha, indice);
        }
        if (!iguales(0, minutosIsqierda, minutosDerecha)) { //Isqierda
            aumentarMinutos(minutosIsqierda, minutosDerecha, indice);
        }
        if (!iguales(0, horasIsqierda, horasDerecha)) { //Isqierda
            aumentarHoras(horasIsqierda, horasDerecha, indice);
        }
    }

    public void restarIndice(int indice, int horasIsqierda, int minutosIsqierda, int segundosIsqierda, int miliSegundosIsqierda, int horasDerecha, int minutosDerecha, int segundosDerecha, int miliSegundosDerecha) {
        if (!iguales(0, miliSegundosIsqierda, miliSegundosDerecha)) { //Isqierda
            restarMSegundos(miliSegundosIsqierda, miliSegundosDerecha, indice);
        }
        if (!iguales(0, segundosIsqierda, segundosDerecha)) { //Isqierda
            restarSegundos(segundosIsqierda, segundosDerecha, indice);
        }
        if (!iguales(0, minutosIsqierda, minutosDerecha)) { //Isqierda
            restarMinutos(minutosIsqierda, minutosDerecha, indice);
        }
        if (!iguales(0, horasIsqierda, horasDerecha)) { //Isqierda
            restarHoras(horasIsqierda, horasDerecha, indice);
        }
    }

    public void aumentar(int horasIsqierda, int minutosIsqierda, int segundosIsqierda, int miliSegundosIsqierda, int horasDerecha, int minutosDerecha, int segundosDerecha, int miliSegundosDerecha) {
        if (!iguales(0, miliSegundosIsqierda, miliSegundosDerecha)) { //Isqierda
            aumentarMSegundosASubtitulo(miliSegundosIsqierda, miliSegundosDerecha);
        }
        if (!iguales(0, segundosIsqierda, segundosDerecha)) { //Isqierda
            aumentarSegundosASubtitulo(segundosIsqierda, segundosDerecha);
        }
        if (!iguales(0, minutosIsqierda, minutosDerecha)) { //Isqierda
            aumentarMinutosASubtitulo(minutosIsqierda, minutosDerecha);
        }
        if (!iguales(0, horasIsqierda, horasDerecha)) { //Isqierda
            aumentarHorasASubtitulo(horasIsqierda, horasDerecha);
        }

    }

    public void restar(int horasIsqierda, int minutosIsqierda, int segundosIsqierda, int miliSegundosIsqierda, int horasDerecha, int minutosDerecha, int segundosDerecha, int miliSegundosDerecha) {
        if (!iguales(0, miliSegundosIsqierda, miliSegundosDerecha)) { //Isqierda
            restarMSegundosASubtitulo(miliSegundosIsqierda, miliSegundosDerecha);
        }
        if (!iguales(0, segundosIsqierda, segundosDerecha)) { //Isqierda
            restarSegundosASubtitulo(segundosIsqierda, segundosDerecha);
        }
        if (!iguales(0, minutosIsqierda, minutosDerecha)) { //Isqierda
            restarMinutosASubtitulo(minutosIsqierda, minutosDerecha);
        }
        if (!iguales(0, horasIsqierda, horasDerecha)) { //Isqierda
            restarHorasASubtitulo(horasIsqierda, horasDerecha);
        }

    }

    public void aumentarMSegundosASubtitulo(int a, int b) {
        for (int i = 0; i < S.length; i++) {
            aumentarMSegundos(a, b, i);
        }
    }

    public void aumentarMSegundos(int a, int b, int indice) {

        int T = S[indice].getMSegundo1();
        T += a;
        if (T >= 1000) {
            aumentarSegundos(T / 1000, 0, indice);
            T -= T / 1000 * 1000;
        }
        S[indice].setMSegundo1(T);
        T = S[indice].getMSegundo2();
        T += b;
        if (T >= 1000) {
            aumentarSegundos(0, T / 1000, indice);
            T -= T / 1000 * 1000;
        }//***********************
        S[indice].setMSegundo2(T);

    }

    public void aumentarSegundosASubtitulo(int a, int b) {
        for (int i = 0; i < S.length; i++) {
            aumentarSegundos(a, b, i);
        }
    }

    public void aumentarMinutosASubtitulo(int a, int b) {
        for (int i = 0; i < S.length; i++) {
            aumentarMinutos(a, b, i);
        }
    }

    public void aumentarHorasASubtitulo(int a, int b) {
        for (int i = 0; i < S.length; i++) {
            aumentarHoras(a, b, i);
        }
    }

    public void aumentarSegundos(int a, int b, int indice) {
        int T = S[indice].getSegundo1();
        T += a;
        if (T >= 60) {
            aumentarMinutos(T / 60, 0, indice);
            T -= T / 60 * 60;
        }
        S[indice].setSegundo1(T);
        T = S[indice].getSegundo2();
        T += b;
        if (T >= 60) {
            aumentarMinutos(0, T / 60, indice);
            T -= T / 60 * 60;
        }
        S[indice].setSegundo2(T);
    }

    public void aumentarMinutos(int a, int b, int indice) {
        int T = S[indice].getMinuto1();
        T += a;
        if (T >= 60) {
            aumentarHoras(T / 60, 0, indice);
            T -= T / 60 * 60;
        }
        S[indice].setMinuto1(T);
        T = S[indice].getMinuto2();
        T += b;
        if (T >= 60) {
            aumentarHoras(0, T / 60, indice);
            T -= T / 60 * 60;
        }
        S[indice].setMinuto2(T);
    }

    public void aumentarHoras(int a, int b, int indice) {
        S[indice].setHora1(S[indice].getHora1() + a);
        S[indice].setHora2(S[indice].getHora2() + b);
    }

    public void añadirSeccion(Seccion_De_Subtitulo SdS) {
        S = Arrays.copyOf(S, S.length + 1);
        S[S.length - 1] = SdS;
    }

    public void añadirSeccion(Seccion_De_Subtitulo SdS, int indice) {
        LinkedList<Seccion_De_Subtitulo> s=new LinkedList<Seccion_De_Subtitulo>(Arrays.asList(S));
        s.add(indice, SdS);
        S=s.toArray(new Seccion_De_Subtitulo[0]);
//        S[indice] = SdS;
    }

    public void eliminarSeccion(int indice) {
        //   System.out.println("aqui");
        //    System.out.println("indice =" + indice);
        S = UnirDosArreglos(Arrays.copyOfRange(S, 0, indice), Arrays.copyOfRange(S, indice + 1, S.length));
    }

    public void eliminarSecciones(int inicio, int fin) throws IndiceFinalIncorrectoException, IndiceInicialIncorrectoException, IndiceIncorrectoException {
        //   System.out.println("en este sitio");
        //    System.out.println("inicio=" + inicio + " fin=" + fin);
        Seccion_De_Subtitulo S1[] = {};
        Seccion_De_Subtitulo S2[] = {};
        if (inicio > fin) {
            throw new IndiceIncorrectoException("El inicio es con " + inicio + " es mayor que el fin con " + fin);
        }
        if (fin <= 0) {
            throw new IndiceFinalIncorrectoException("El indice final es menor que cero con " + fin);
        }
        if (inicio >= S.length - 1) {
            throw new IndiceInicialIncorrectoException("El inicion es mayor que el total de indice el cual es " + S.length);
        }
        if (!(inicio <= 0)) {
            S1 = Arrays.copyOfRange(S, 0, inicio);
        }
        if (!(fin >= S.length)) {
            S2 = Arrays.copyOfRange(S, fin + 1, S.length);
        }
        S = UnirDosArreglos(S1, S2);
    }

    private Seccion_De_Subtitulo[] UnirDosArreglos(Seccion_De_Subtitulo A[], Seccion_De_Subtitulo B[]) {
        Seccion_De_Subtitulo union[] = new Seccion_De_Subtitulo[A.length + B.length];
        for (int i = 0; i < union.length; i++) {
            if (i < A.length) {
                union[i] = A[i];
            } else {
                union[i] = B[i - A.length];
            }
        }
        return union;
    }

    public Seccion_De_Subtitulo[] añadirUnElemento(int indice, Seccion_De_Subtitulo s) {
        Seccion_De_Subtitulo B[] = Arrays.copyOfRange(S, 0, indice);
        B = Arrays.copyOf(B, B.length + 1);
        B[B.length - 1] = s;
        return UnirDosArreglos(B, Arrays.copyOfRange(S, indice, S.length));
    }

    public void restarMSegundosASubtitulo(int a, int b) {
        for (int i = 0; i < S.length; i++) {
            restarMSegundos(a, b, i);
        }
    }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

    public void restarMSegundos(int a, int b, int indice) {
        int T = a / 1000;
        if (T > 0) {
            restarSegundos(T, 0, indice);
            a -= (T * 1000);
        }
        //nnnnnnnnnnnnnnnnnnnnnnnn

        if (a > S[indice].getMSegundo1() && !sonCeroSegundos(indice, false)) {
            S[indice].setMSegundo1(1000 + S[indice].getMSegundo1() - a);
            restarSegundos(1, 0, indice);
        } else {
            //nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn

            T = S[indice].getMSegundo1();
            T -= a;
            if (T < 0) {
                if (sonCeroSegundos(indice, false)) { //System.out.println("MSi  0");
                    //S[indice].MSegundo1=0;
                    ceroAbsoluto(indice, false);
                } else { //System.out.println("MSi a="+a+"  a/1000="+a/1000);
                    restarSegundos(((a - 1) / 1000) + 1, 0, indice);
                    T = 1000 - (T * (-1) - (T / 1000) * 1000);   //System.out.println("T="+T);
                    S[indice].setMSegundo1(T);
                }
            } else {
                S[indice].setMSegundo1(T);
            }

        }

        T = b / 1000;
        if (T > 0) {
            restarSegundos(0, T, indice);
            b -= (T * 1000);
        }

        //nnnnnnnnnnnnnnnnnnnnnnnn
        if (b > S[indice].getMSegundo2() && !sonCeroSegundos(indice, true)) {
            S[indice].setMSegundo2(1000 + S[indice].getMSegundo2() - b);
            restarSegundos(0, 1, indice);
        } else {
            //nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn

            T = S[indice].getMSegundo2();
            T -= b;
            if (T < 0) {
                if (sonCeroSegundos(indice, true)) {// System.out.println("MSd  0");
                    //S[indice].MSegundo2=0;

                    ceroAbsoluto(indice, true);
                } else {       //System.out.println("MSd  a="+a+"  a/1000="+a/1000);
                    restarSegundos(0, ((a - 1) / 1000) + 1, indice);
                    T = 1000 - (T * (-1) - (T / 1000) * 1000);// System.out.println("T="+T);
                    S[indice].setMSegundo2(T);

                }
            } else {
                S[indice].setMSegundo1(T);
            }
        }
    }

    public void restarSegundosASubtitulo(int a, int b) {
        for (int i = 0; i < S.length; i++) {
            restarSegundos(a, b, i);
        }
    }

    public void restarMinutosASubtitulo(int a, int b) {
        for (int i = 0; i < S.length; i++) {
            restarMinutos(a, b, i);
        }
    }

    public void restarHorasASubtitulo(int a, int b) {
        for (int i = 0; i < S.length; i++) {
            restarHoras(a, b, i);
        }
    }

    public void restarSegundos(int a, int b, int indice) {
        int T = a / 60;
        if (T > 0) {
            restarMinutos(T, 0, indice);
            a -= (T * 60);
        }

        //nnnnnnnnnnnnnnnnnnnnnnnn
        if (a > S[indice].getSegundo1() && !sonCeroSegundos(indice, false)) {
            S[indice].setSegundo1(60 + S[indice].getSegundo1() - a);
            restarMinutos(1, 0, indice);
        } else {
            //nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn

            T = S[indice].getSegundo1();
            T -= a;
            if (T < 0) { // System.out.println("Si  0");
                //  System.out.println("son " + sonCeroMinutos(indice, false));
                if (sonCeroMinutos(indice, false)) {
                    // T=0;
                    //S[indice].MSegundo1=0;
                    ceroAbsoluto(indice, false);
                } else { //System.out.println("Si a="+a+"  a/60="+a/60);
                    restarMinutos(((a - 1) / 60) + 1, 0, indice);
                    T = 60 - (T * (-1) - (T / 60) * 60);// System.out.println("T="+T);
                    //S[indice].MSegundo1=T;
                }
            } else {
                S[indice].setSegundo1(T);
            }

        }

        T = b / 60;
        if (T > 0) {
            restarMinutos(0, T, indice);
            b -= (T * 60);
        }

        //nnnnnnnnnnnnnnnnnnnnnnnn
        if (b > S[indice].getSegundo2() && !sonCeroSegundos(indice, true)) {
            S[indice].setSegundo2(60 + S[indice].getSegundo2() - b);
            restarMinutos(0, 1, indice);
        } else {
            //nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn

            T = S[indice].getSegundo2();
            T -= b;
            if (T < 0) {
                if (sonCeroMinutos(indice, true)) { //System.out.println("Sd  0");
                    //T=0;
                    ceroAbsoluto(indice, true);
                    //S[indice].MSegundo2=0;
                } else {  //System.out.println("Sd a="+a+"  a/60="+a/60);
                    restarMinutos(0, ((a - 1) / 60) + 1, indice);
                    T = 60 - (T * (-1) - (T / 60) * 60); //System.out.println("T="+T);
                    // S[indice].MSegundo2=T;
                }
            } else {
                S[indice].setSegundo1(T);
            }

        }
    }

    public void restarMinutos(int a, int b, int indice) {
        int T = a / 60;
        if (T > 0) {
            restarHoras(T, 0, indice);
            a -= (T * 60);
        }

        //nnnnnnnnnnnnnnnnnnnnnnnn
        if (a > S[indice].getMinuto1() && !sonCeroSegundos(indice, false)) {
            S[indice].setMinuto1(60 + S[indice].getMinuto1() - a);
            restarHoras(1, 0, indice);
        } else {
            //nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn

            T = S[indice].getMinuto1();
            T -= a;
            if (T < 0) {
                if (sonCeroHoras(indice, false)) {//System.out.println("Mi  0");
                    //T=0;
                    //S[indice].Minuto1=0;
                    ceroAbsoluto(indice, false);
                } else {                    // System.out.println("Mi a="+a+"  a/60="+a/60);
                    restarHoras(((a - 1) / 60) + 1, 0, indice);
                    T = 60 - (T * (-1) - (T / 60) * 60); //System.out.println("T="+T);
                    //S[indice].Minuto1=T;
                }
            } else {
                S[indice].setMinuto1(T);
            }

        }

        T = b / 60;
        if (T > 0) {
            restarHoras(0, T, indice);
            b -= (T * 60);
        }
        //nnnnnnnnnnnnnnnnnnnnnnnn
        if (b > S[indice].getMinuto2() && !sonCeroSegundos(indice, true)) {
            S[indice].setMinuto2(60 + S[indice].getMinuto2() - b);
            restarHoras(0, 1, indice);
        } else {
            //nnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnnn

            T = S[indice].getMinuto2();
            T -= b;
            if (T < 0) {
                if (sonCeroHoras(indice, true)) {//System.out.println("Md  0");
                    // T=0;
                    // S[indice].Minuto2=0;
                    ceroAbsoluto(indice, true);
                } else {  //System.out.println("Md  a="+a+"  a/60="+a/60);
                    restarHoras(0, ((a - 1) / 60) + 1, indice);
                    T = 60 - (T * (-1) - (T / 60) * 60);// System.out.println("T="+T);
                    // S[indice].Minuto2=T;
                }
            } else {
                S[indice].setMinuto2(T);
            }
        }
    }

    public void restarHoras(int a, int b, int indice) {

        int T = S[indice].getHora1();
        T -= a; //System.out.println(" a="+a+"T -= a  ="+(T-a));
        if (T < 0) {// System.out.println("Hi  0");
            // T=0;
            //S[indice].Hora1=0;
            ceroAbsoluto(indice, false);

        } else //System.out.println("Hi T="+T);
        {
            S[indice].setHora1(T);
        }
        T = S[indice].getHora2();
        T -= b; // System.out.println(" b="+a+"T -= b  ="+(T-b));
        if (T < 0) {//System.out.println("Hd  0");
            // T=0;
            //S[indice].Hora2=0;
            ceroAbsoluto(indice, true);

        } else //System.out.println("Hd T="+T);
        {
            S[indice].setHora1(T);
        }
    }

    public void ceroAbsoluto(int indice, boolean esDerecha) {
        if (esDerecha) {
            S[indice].ceroTiempos2();
        } else {
            S[indice].ceroTiempos1();
        }
    }

    public boolean sonCeroHoras(int indice, boolean esDerecha) { //System.out.println("1 se utilizo");
        if (esDerecha) {//System.out.println("return "+(S[indice].Hora2==0?true:false));
            return S[indice].getHora2() == 0;
        }
        //System.out.println("return "+(S[indice].Hora1==0?true:false));
        return S[indice].getHora1() == 0;
    }

    public boolean sonCeroMinutos(int indice, boolean esDerecha) { //System.out.println("2 se utilizo");

        if (esDerecha) {// System.out.println("return "+(S[indice].Minuto2==0?true:false));
            // System.out.println("indice ="+indice+" esDerecha="+esDerecha);
            return (S[indice].getMinuto2() == 0) && (sonCeroHoras(indice, esDerecha));
        }
        // System.out.println("return "+(S[indice].Minuto1==0?true:false));
        return (S[indice].getMinuto1() == 0) && (sonCeroHoras(indice, esDerecha));
    }

    public boolean sonCeroSegundos(int indice, boolean esDerecha) {//System.out.println("3 se utilizo");
        if (esDerecha) {//System.out.println("return "+(S[indice].Segundo2==0?true:false));
            return (S[indice].getSegundo2() == 0) && (sonCeroMinutos(indice, esDerecha));
        }
        return (S[indice].getSegundo1() == 0) && (sonCeroMinutos(indice, esDerecha));
    }

    public boolean sonCeroMSegundos(int indice, boolean esDerecha) {//System.out.println("4 se utilizo");
        if (esDerecha) {//System.out.println("return "+(S[indice].MSegundo2==0?true:false));
            return (S[indice].getMSegundo2() == 0) && (sonCeroSegundos(indice, esDerecha));
        }
        // System.out.println("return "+(S[indice].MSegundo1==0?true:false));
        return (S[indice].getMSegundo1() == 0) && (sonCeroSegundos(indice, esDerecha));
    }

    public static Seccion_De_Subtitulo crearSeccionDeSubtitulo(String a) {
        Seccion_De_Subtitulo s = new Seccion_De_Subtitulo();
        int MSegundo1, MSegundo2;
        int Segundo1, Segundo2;
        int Minuto1, Minuto2;
        int Hora1, Hora2;
        String dialogo = "";

        int c = 0;//contador
        int c2 = 0;
        String b = "";
        boolean segundaMitad = false;
        for (int i = 0; i < a.length(); i++) {// System.out.println(" 1 a.charAt("+i+")="+a.charAt(i));
            //if(i%3!=0&&c<3){b+=a.charAt(i);continue;}
            if (c < 8 && (a.charAt(i) >= 48 && a.charAt(i) <= 57)) {
                c2++;
                b += a.charAt(i);// System.out.println("dos c2++="+c2+" b += a.charAt("+i+")="+b);
                continue;
            }
            // if (i % 3 == 0 && i <= 9) {
            if (c2 == 2 && c == 0) {
                // System.out.println(" c=0 b=" + b);
                c2 = 0;
                c++;
                s.setHora1(Integer.parseInt(b));
                b = "";
                continue;
            }
            if (c2 == 2 && c == 1) {
                // System.out.println(" c=1 b=" + b);
                c2 = 0;
                c++;
                s.setMinuto1(Integer.parseInt(b));
                b = "";
                continue;
            }
            if (c2 == 2 && c == 2) {
                //System.out.println(" c=2 b=" + b);
                c2 = 0;
                c++;
                s.setSegundo1(Integer.parseInt(b));
                b = "";
                continue;
            }
            //  }
//            if (c > 2 && c < 4) {  System.out.println(" tres");
//                if (c2 <= 3) {   System.out.println("cuatro");
//                    c2++;
//                    b += a.charAt(i);
//                    continue;
//                }
            if (c2 == 3 && c == 3) {
                //System.out.println(" c=3 b=" + b);
                c2 = 0;
                c++;
                s.setMSegundo1(Integer.parseInt(b));
                b = "";
                continue;
            }
            //    }

            //if (c > 3 && c < 7) {
//                if (a.charAt(i) >= 48 && a.charAt(i) <= 57 ? true : false) {
//                    c2++;
//                    b += a.charAt(i);
//                    continue;
//                }
            if (c2 == 2 && c == 4) {
                //System.out.println(" c=4 b=" + b);
                c2 = 0;
                c++;
                s.setHora2(Integer.parseInt(b));
                b = "";
                continue;
            }
            if (c2 == 2 && c == 5) {
                //System.out.println(" c=5 b=" + b);
                c2 = 0;
                c++;
                s.setMinuto2(Integer.parseInt(b));
                b = "";
                continue;
            }
            if (c2 == 2 && c == 6) {
                //System.out.println(" c=6 b=" + b);
                c2 = 0;
                c++;
                s.setSegundo2(Integer.parseInt(b));
                b = "";
                continue;
            }
            // }
//            if (c > 6 && c < 8) { System.out.println("cinco");
//                if (c2 <= 3) { System.out.println("seis");
//                    c2++;
//                    b += a.charAt(i);
//                    continue;
//                }
            if (c2 == 3 && c == 7) {
                //System.out.println(" c="+c+" b=" + b);
                c2 = 0;
                c++;
                s.setMSegundo2(Integer.parseInt(b));
                b = "";
                continue;
            }
            // }
            if (c2 == 0 && c == 8 && a.charAt(i) != ' ') { //System.out.println("siete");
                c2++;

            }
            if (c == 8 && c2 == 1) {
                //System.out.println(" c=8 b=" + b);
                b += a.charAt(i);
            }
            //if(i==0|i==1){b+=a.charAt(i);}
        }
        //   System.out.println(Arrays.toString(s.getTiempos()));
        s.dialogo = b;
        return s;
    }

    public void ordernar() {
        Collections.sort(Arrays.asList(S));
    }

    public void eliminarSeccionesEntre(Tiempo a, Tiempo b) {
        TreeSet<Seccion_De_Subtitulo> t = new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S));
        t.removeAll(new TreeSet<Seccion_De_Subtitulo>((TreeSet<Seccion_De_Subtitulo>) t.subSet(new Seccion_De_Subtitulo(a), new Seccion_De_Subtitulo(b))));
        S = t.toArray(new Seccion_De_Subtitulo[]{});
    }

    public void eliminarDesde(Tiempo superior) {
        S = new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S)).headSet(superiorAumentada(superior)).toArray(new Seccion_De_Subtitulo[]{});
    }

    public void eliminarHasta(Tiempo inferior) {
        S = new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S)).tailSet(inferiorDisminuida(inferior)).toArray(new Seccion_De_Subtitulo[]{});
    }

    public void aumentar(Tiempo aumentoIsquerda, Tiempo aumentoDerecha) {
        for (int i = 0; i < S.length; i++) {
            S[i].aumentar(aumentoIsquerda, aumentoDerecha);
        }
    }

    public void aumentar(int indice, Tiempo aumentoIsquerda, Tiempo aumentoDerecha) {
        S[--indice].aumentar(aumentoIsquerda, aumentoDerecha);

    }

    //******
    public void aumentarEntre(Tiempo inferior, Tiempo superior, Tiempo aumento) {
        // TreeSet<Seccion_De_Subtitulo> t = new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S));
        aumentar((TreeSet<Seccion_De_Subtitulo>) new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S)).subSet(inferiorDisminuida(inferior), superiorAumentada(superior)), aumento);
    }
//*******

    public void aumentarEntre(Tiempo inferior, Tiempo superior, Tiempo aumentoIsquerda, Tiempo aumentoDerecha) {
        // TreeSet<Seccion_De_Subtitulo> t = new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S));
        aumentar((TreeSet<Seccion_De_Subtitulo>) new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S)).subSet(inferiorDisminuida(inferior), superiorAumentada(superior)), aumentoIsquerda, aumentoDerecha);
    }

    public void aumentarEntre(int inferior, int superior, Tiempo aumento) {
        for (int i = --inferior; i < superior; i++) {
            S[i].aumentar(aumento);
        }
    }

    public void aumentarHasta(int superior, Tiempo aumento) {
        for (int i = 0; i < superior; i++) {
            S[i].aumentar(aumento);
        }
    }

    public void aumentarDesde(int inferior, Tiempo aumento) {
        for (int i = --inferior; i < S.length; i++) {
            S[i].aumentar(aumento);
        }
    }

    public void aumentarEntre(int inferior, int superior, Tiempo aumentoIsquerda, Tiempo aumentoDerecha) {
        for (int i = --inferior; i < superior; i++) {
            S[i].aumentar(aumentoIsquerda, aumentoDerecha);
        }
    }

    public void aumentarHasta(int superior, Tiempo aumentoIsquerda, Tiempo aumentoDerecha) {
        for (int i = 0; i < superior; i++) {
            S[i].aumentar(aumentoIsquerda, aumentoDerecha);
        }
    }

    public void aumentarDesde(int inferior, Tiempo aumentoIsquerda, Tiempo aumentoDerecha) {
        for (int i = --inferior; i < S.length; i++) {
            S[i].aumentar(aumentoIsquerda, aumentoDerecha);
        }
    }

    //**************
    public void aumentarHasta(Tiempo superior, Tiempo aumento) {
        aumentar((TreeSet<Seccion_De_Subtitulo>) new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S)).headSet(superiorAumentada(superior)), aumento);
    }

    public void aumentarDesde(Tiempo inferior, Tiempo aumento) {
        aumentar((TreeSet<Seccion_De_Subtitulo>) new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S)).tailSet(inferiorDisminuida(inferior)), aumento);
    }

    private Seccion_De_Subtitulo superiorAumentada(Tiempo superior) {
        Seccion_De_Subtitulo superiorAumentada = new Seccion_De_Subtitulo(superior);
        superiorAumentada.aumentar(new Tiempo(0, 0, 0, 1));
        return superiorAumentada;
    }

    private Seccion_De_Subtitulo inferiorDisminuida(Tiempo inferior) {
        Seccion_De_Subtitulo inferiorDisminuida = new Seccion_De_Subtitulo(inferior);
        inferiorDisminuida.aumentar(new Tiempo(0, 0, 0, -1));
        return inferiorDisminuida;
    }

    private void aumentar(TreeSet<Seccion_De_Subtitulo> t, Tiempo aumento) {
        Iterator I = t.iterator();
        while (I.hasNext()) {
            ((Seccion_De_Subtitulo) I.next()).aumentar(aumento);
        }
    }
//**********************

    public void aumentarHasta(Tiempo superior, Tiempo aumentoIsquerda, Tiempo aumentoDerecha) {
        aumentar((TreeSet<Seccion_De_Subtitulo>) new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S)).headSet(superiorAumentada(superior)), aumentoIsquerda, aumentoDerecha);
    }

    public void aumentarDesde(Tiempo inferior, Tiempo aumentoIsquerda, Tiempo aumentoDerecha) {
        aumentar((TreeSet<Seccion_De_Subtitulo>) new TreeSet<Seccion_De_Subtitulo>(Arrays.asList(S)).tailSet(inferiorDisminuida(inferior)), aumentoIsquerda, aumentoDerecha);
    }

    private void aumentar(TreeSet<Seccion_De_Subtitulo> t, Tiempo aumentoIsquerda, Tiempo aumentoDerecha) {
        Iterator I = t.iterator();
        while (I.hasNext()) {
            ((Seccion_De_Subtitulo) I.next()).aumentar(aumentoIsquerda, aumentoDerecha);
        }
    }

    public Seccion_De_Subtitulo[] getS() {
        return S;
    }

    public void setS(Seccion_De_Subtitulo[] S) {
        this.S = S;
    }

    private void leerSubtituloActual(File f) throws FileNotFoundException {

//        Subtitulo S = new Subtitulo();
        int numeroDeLinea = 0;
        int c = 0;
        String seccion = "";
        String entrada;

        String lineas[] = Archivo.leerTEXTO(f);
        for (int i = 0; i < lineas.length; i++) {
            entrada = lineas[i];
            if (entrada.equals("1")) {
                continue;
            }
            if (entrada.isEmpty()) {
                c++;
                if (!seccion.isEmpty()) {
                    //   System.out.println("seccion="+seccion);
                    añadirSeccion(Subtitulo.crearSeccionDeSubtitulo(seccion));
                    seccion = "";
                }
                continue;
            }
            if (c == 1) {
                c = 0;
                continue;
            }

            for (int j = 0; j < entrada.length(); j++) {
                if (entrada.charAt(j) == '�') {
                    seccion += "";
                } else {
                    seccion += entrada.charAt(j);
                }
            }
            seccion += "\n";
        }

    }

    public String[] getSubtituloLineas() {
        return MetodosUtiles.getLineasString(getSubtitulo());
    }

    public void guardarYSustituir(File carpeta) throws FileNotFoundException {
        Archivo.crearTextoYSustituir(direccion.getParentFile(), direccion.getName(), ".srt", getSubtituloLineas());
    }

}
