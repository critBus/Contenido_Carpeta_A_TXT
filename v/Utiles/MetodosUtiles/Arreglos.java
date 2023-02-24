/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.MetodosUtiles;

import Utiles.ClasesUtiles.Interfases.Arreglo;
import Utiles.ClasesUtiles.Comparadores.ComparadorOrdenAlfabetico;
import static Utiles.MetodosUtiles.MetodosUtiles.mostrarList;
import java.awt.Point;
import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.Vector;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Metodos para poder realizar tareas complicadas con arreglos de uso frecuente
 *
 * Version 2.4
 *
 * @author Rene
 */
public abstract class Arreglos {
//<b></b> // Arrays.toString 
    //<ul></ul>
    //<li></li>

    static Scanner s = new Scanner(System.in);

    public static double[] convertirArrgloAAUnaColumnaEnUnaFila(double A[][]) {
        double[] B = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i][0];
        }
        return B;
    }

    public static double[][] convertirUnaFilaEnUnaColumna(double A[]) {
        double[][] B = new double[A.length][1];
        for (int i = 0; i < A.length; i++) {
            B[i][0] = A[i];
        }
        return B;
    }

    public static int max(int... A) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < A.length; i++) {
            if (A[i] > max) {
                max = A[i];
            }
        }
        return max;
    }

    public static double[][] copiaDeArreglo(double A[][]) {
        if (A == null) {
            return null;
        }
//        double[][] m = new double[A.length][A[0].length];
        double[][] m = new double[A.length][];
        for (int i = 0; i < A.length; i++) {
            m[i] = new double[A[i].length];
            for (int j = 0; j < A[i].length; j++) {
                m[i][j] = A[i][j];
            }
        }
        return m;
    }

    public static <E> E[] copiaDeArreglo(E A[]) {
        if (A == null) {
            return null;
        }
        E[] m = arregloObjectA(A[0], A.length);
        for (int i = 0; i < A.length; i++) {
            m[i] = A[i];
        }
        return m;
    }

    public static String[][] copiaDeArreglo(String A[][]) {
        if (A == null) {
            return null;
        }
//        String[][] m = new String[A.length][A[0].length];
        String[][] m = new String[A.length][];
        for (int i = 0; i < A.length; i++) {
            m[i] = new String[A[i].length];
            for (int j = 0; j < A[i].length; j++) {
                m[i][j] = A[i][j];
            }
        }
        return m;
    }

    public static <E> E[][] copiaDeArreglo(E A[][]) {
        if (A == null) {
            return null;
        }
        E[][] m = arregloAA(A[0][0], A.length, A[0].length);
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                m[i][j] = A[i][j];
            }
        }
        return m;
    }

    public static double[] copiaDeArreglo(double A[]) {
        if (A == null) {
            return null;
        }
        double[] m = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            m[i] = A[i];
        }
        return m;
    }

    public static boolean[] copiaDeArreglo(boolean A[]) {
        if (A == null) {
            return null;
        }
        boolean[] m = new boolean[A.length];
        for (int i = 0; i < A.length; i++) {
            m[i] = A[i];
        }
        return m;
    }

    public static int[] copiaDeArreglo(int A[]) {
        if (A == null) {
            return null;
        }
        int[] m = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            m[i] = A[i];
        }
        return m;
    }

    public static char[] copiaDeArreglo(char A[]) {
        if (A == null) {
            return null;
        }
        char[] m = new char[A.length];
        for (int i = 0; i < A.length; i++) {
            m[i] = A[i];
        }
        return m;
    }

    public static double[][] ampliarConMatrizIdentidad(double A[][]) {
        double[][] m = matrizIdentidad(A), mA = new double[A.length][2 * A[0].length];

        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                mA[i][j] = A[i][j];
            }
            for (int j = 0; j < m[i].length; j++) {
                mA[i][j + A[i].length] = m[i][j];
            }
        }
        return mA;
    }

    public static double[][] matrizIdentidad(double A[][]) {
        return matrizIdentidad(A.length);
    }

    public static double[][] matrizIdentidad(int n) {
        double[][] m = new double[n][n];
        for (int i = 0; i < m.length; i++) {
            for (int j = 0; j < m[i].length; j++) {
                m[i][j] = i == j ? 1 : 0;
            }
        }
        return m;
    }

    public static double[] diagonalPrincipal(double A[][]) {
        LinkedList<Double> r = new LinkedList<Double>();
        for (int i = 0; i < (A.length < A[0].length ? A.length : A[0].length); i++) {
            r.add(A[i][i]);
        }
        return Arreglos.convertirArregloAdouble(r);
    }

    public static double[] diagonalEncimaDePrincipal(double A[][]) {
        LinkedList<Double> r = new LinkedList<Double>();
        for (int i = 0; i < (A.length < A[0].length ? A.length : A[0].length); i++) {
            r.add(A[i][i + 1]);
        }
        return Arreglos.convertirArregloAdouble(r);
    }

    public static double[] diagonalDebajoDePrincipal(double A[][]) {
        LinkedList<Double> r = new LinkedList<Double>();
        for (int i = 1; i < (A.length < A[0].length ? A.length : A[0].length); i++) {
            r.add(A[i][i - 1]);
        }
        return Arreglos.convertirArregloAdouble(r);
    }

    public static double[][] aumentarEnNulo(double A[][]) {
        double r[][] = new double[A.length + 1][A[0].length + 1];
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {
                r[i][j] = (i == 0 || j == 0) ? 0 : A[i - 1][j - 1];
            }
        }
        return r;
    }

    public static BigDecimal[][] aumentarEnNulo(BigDecimal A[][]) {
        BigDecimal r[][] = new BigDecimal[A.length + 1][A[0].length + 1];
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {
                r[i][j] = (i == 0 || j == 0) ? new BigDecimal(0) : A[i - 1][j - 1];
            }
        }
        return r;
    }

    public static BigDecimal[] aumentarEnNulo(BigDecimal A[]) {
        BigDecimal r[] = new BigDecimal[A.length + 1];
        for (int i = 0; i < r.length; i++) {
            r[i] = (i == 0) ? new BigDecimal(0) : A[i - 1];
        }
        return r;
    }

    public static double[][] disminuirEnNulo(double A[][]) {
        double r[][] = new double[A.length - 1][A[0].length - 1];
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {
                r[i][j] = A[i + 1][j + 1];
            }
        }
        return r;
    }

    public static double[] disminuirEnNulo(double A[]) {
        double r[] = new double[A.length - 1];
        for (int i = 0; i < r.length; i++) {
            r[i] = A[i + 1];
        }
        return r;
    }

    public static double[] aumentarEnNulo(double A[]) {
        double r[] = new double[A.length + 1];
        for (int i = 0; i < r.length; i++) {
            r[i] = (i == 0) ? 0 : A[i - 1];
        }
        return r;
    }

    public static void intercanviarFila(double[][] A, int a, int b) {
        double B[] = A[a];
        A[a] = A[b];
        A[b] = B;
    }

    public static void multiplicarLandaAArreglo(double[] A, double m) {
        for (int i = 0; i < A.length; i++) {
            A[i] *= m;
            A[i] = Operaciones.regulador(A[i]);
        }
    }

    public static double[] multiplicarLandaAArregloCopia(double[] A, double m) {
        double C[] = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            C[i] = Operaciones.regulador(A[i] * m);
            //  C[i] = A[i] * m;
        }
        return C;
    }

    public static double[] dividirLandaAArregloCopia(double[] A, double m) {
        double C[] = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            C[i] = Operaciones.regulador(A[i] / m);
            // C[i] = A[i] / m;
        }
        return C;
    }

    public static double[] restarArreglos(double[] A, double[] B) {
        return sumarArreglos(A, B, false);
    }

    public static double[] sumarArreglos(double[] A, double[] B) {
        return sumarArreglos(A, B, true);
    }

    public static double[] sumarArreglos(double[] A, double[] B, boolean suma) {
        double C[] = new double[(A.length < B.length ? A.length : B.length)];
        //(A.length>B.length?A.length:B.length)
        for (int i = 0; i < A.length; i++) {
            if (i < B.length) {
                C[i] = suma ? A[i] + B[i] : A[i] - B[i];
            } else {
                break;
            }
        }
        return C;
    }

    /**
     * <b> Crea una matriz en la que su primer valor es el inicio y se
     * incrementa hacia la derecha elevando el valor anterior al exponente de
     * incremento , luego en las filas siguientes se divide el valor
     * correspondiente en la fila anterior entre el incremento</b>
     * <ul>Ejemplo: inicio 1 incremento 10 filas 3 columnas 3</ul>
     * <ul>1.0 10.0 100.0</ul>
     * <ul>0.1 1.0 10.0</ul>
     * <ul>0.01 0.1 1.0</ul>
     *
     * @param inicio double ; valor inicial
     * @param incremento double ; exponente a incrementar
     * @param filas int ; cantidad de filas
     * @param columnas int ; cantidad de columnas
     * @return
     */
    public static double[][] matrizDeIncremento(double inicio, double incremento, int filas, int columnas) {
        double m[][] = new double[filas][columnas];
        for (int f = 0; f < filas; f++) {
            for (int c = 0; c < columnas; c++) {
                if (f == 0) {
                    m[f][c] = inicio * Math.pow(incremento, c);
                    continue;
                }
                m[f][c] = m[f - 1][c] / incremento;
            }
        }
        return m;
    }

    public static boolean[] arregloFill(boolean b, int lengh) {
        boolean e[] = new boolean[lengh];
        Arrays.fill(e, b);
        return e;
    }

    public static short[] arregloFill(short b, int lengh) {
        short e[] = new short[lengh];
        Arrays.fill(e, b);
        return e;
    }

    public static int[] arregloFill(int b, int lengh) {
        int e[] = new int[lengh];
        Arrays.fill(e, b);
        return e;
    }

    public static long[] arregloFill(long b, int lengh) {
        long e[] = new long[lengh];
        Arrays.fill(e, b);
        return e;
    }

    public static float[] arregloFill(float b, int lengh) {
        float e[] = new float[lengh];
        Arrays.fill(e, b);
        return e;
    }

    public static char[] arregloFill(char b, int lengh) {
        char e[] = new char[lengh];
        Arrays.fill(e, b);
        return e;
    }

    public static double[] arregloFill(double b, int lengh) {
        double e[] = new double[lengh];
        Arrays.fill(e, b);
        return e;
    }
//*******

    public static boolean[][] arregloFill(boolean b, int filas, int columnas) {
        boolean e[][] = new boolean[filas][columnas];
        for (int i = 0; i < e.length; i++) {
            e[i] = arregloFill(b, filas);
        }
        return e;
    }

    public static short[][] arregloFill(short b, int filas, int columnas) {
        short e[][] = new short[filas][columnas];
        for (int i = 0; i < e.length; i++) {
            e[i] = arregloFill(b, filas);
        }
        return e;
    }

    public static int[][] arregloFill(int b, int filas, int columnas) {
        int e[][] = new int[filas][columnas];
        for (int i = 0; i < e.length; i++) {
            e[i] = arregloFill(b, filas);
        }
        return e;
    }

    public static long[][] arregloFill(long b, int filas, int columnas) {
        long e[][] = new long[filas][columnas];
        for (int i = 0; i < e.length; i++) {
            e[i] = arregloFill(b, filas);
        }
        return e;
    }

    public static float[][] arregloFill(float b, int filas, int columnas) {
        float e[][] = new float[filas][columnas];
        for (int i = 0; i < e.length; i++) {
            e[i] = arregloFill(b, filas);
        }
        return e;
    }

    public static char[][] arregloFill(char b, int filas, int columnas) {
        char e[][] = new char[filas][columnas];
        for (int i = 0; i < e.length; i++) {
            e[i] = arregloFill(b, filas);
        }
        return e;
    }

    public static double[][] arregloFill(double b, int filas, int columnas) {
        double e[][] = new double[filas][columnas];
        for (int i = 0; i < e.length; i++) {
            e[i] = arregloFill(b, filas);
        }
        return e;
    }

    public static <E> E[][] arregloObjectAAFill(E a, int filas, int columnas) {
        E[][] A = arregloAA(a, filas, columnas);
        for (int i = 0; i < A.length; i++) {
            Arrays.fill(A[i], a);
        }
        return A;
    }

    public static <E> E[] arregloObjectFill(E a, int leng) {
        E e[] = arregloObjectA(a, leng);
        Arrays.fill(e, a);
        return e;
    }

    public static boolean igualesA(short[]... A) {
        return igualesObjectA(convertirArregloAObjetoShort(A));
    }

    public static boolean igualesA(int[]... A) {
        return igualesObjectA(convertirArregloAObjetoInteger(A));
    }

    public static boolean igualesA(long[]... A) {
        return igualesObjectA(convertirArregloAObjetoLong(A));
    }

    public static boolean igualesA(double[]... A) {
        return igualesObjectA(convertirArregloAObjetoDouble(A));
    }

    public static boolean igualesA(float[]... A) {
        return igualesObjectA(convertirArregloAObjetoFloat(A));
    }

    public static boolean igualesA(char[]... A) {
        return igualesObjectA(convertirArregloAObjetoCharacter(A));
    }

    public static <E> boolean igualesA(ArrayList<E>[] A) {
        return igualesObjectA(convertirArraylistAArreglo(A));
    }

    public static <E> boolean igualesA(ArrayList<ArrayList<E>> A) {
        return igualesObjectA(convertirArraylist2AArreglo(A));
    }

    public static <E> boolean igualesObjectA(E[]... a) {
        for (int i = 1; a.length > 1 && i < a.length; i++) {
            if (a[i - 1].length != a[i].length) {
                return false;
            }
            for (int j = 0; j < a[i].length; j++) {
                if (!a[i][j].equals(a[i - 1][j])) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean alMenosIgualesAconAA(int[] B, int[]... A) {
        return alMenosIgualesAconAA(arregloDouble(B), arregloDoubleAA(A));
    }

    public static boolean alMenosIgualesAconAA(double[] B, double[]... A) {
        for (int i = 0; i < A.length; i++) {
            System.out.println("A[i].length=" + A[i].length + " B.length=" + B.length);
            if (A[i].length != B.length) {
                return false;
            }
            for (int j = 0; j < B.length; j++) {
                if (B[i] > A[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean igualesAconAA(short[] B, short[]... A) {
        return igualesObjectAconAA(Arreglos.convertirArregloAObjetoShort(B), convertirArregloAObjetoShort(A));
    }

    public static boolean igualesAconAA(int[] B, int[]... A) {
        return igualesObjectAconAA(Arreglos.convertirArregloAObjetoInteger(B), convertirArregloAObjetoInteger(A));
    }

    public static boolean igualesAconAA(long[] B, long[]... A) {
        return igualesObjectAconAA(Arreglos.convertirArregloAObjetoLong(B), convertirArregloAObjetoLong(A));
    }

    public static boolean igualesAconAA(double[] B, double[]... A) {
        return igualesObjectAconAA(Arreglos.convertirArregloAObjetoDouble(B), convertirArregloAObjetoDouble(A));
    }

    public static boolean igualesAconAA(float[] B, float[]... A) {
        return igualesObjectAconAA(Arreglos.convertirArregloAObjetoFloat(B), convertirArregloAObjetoFloat(A));
    }

    public static boolean igualesAconAA(char[] B, char[]... A) {
        return igualesObjectAconAA(Arreglos.convertirArregloAObjetoCharacter(B), convertirArregloAObjetoCharacter(A));
    }

    public static <E> boolean igualesAconAA(ArrayList<E>[] B, ArrayList<E>[] A) {
        return igualesObjectAconAA(convertirArraylistAArreglo(B), convertirArraylistAArreglo(A));
    }

    public static <E> boolean igualesAconAA(ArrayList<E>[] B, ArrayList<ArrayList<E>> A) {
        return igualesObjectAconAA(convertirArraylistAArreglo(B), convertirArraylist2AArreglo(A));
    }

    public static <E> boolean igualesObjectAconAA(E[] b, E[]... a) {
        for (int i = 0; i < a.length; i++) {
            if (a[i].length != b.length) {
                return false;
            }
            if (!igualesObjectA(b, a[i])) {
                return false;
            }
        }
        return true;
    }

    public static <E> ArrayList<ArrayList<E>> convertirArreglo2AArrayList(E A[][]) {
        ArrayList<ArrayList<E>> B = new ArrayList<ArrayList<E>>();
        for (E[] a : A) {
            B.add(new ArrayList<E>(Arrays.asList(a)));
        }
        return B;
    }

    public static <E> E[][] convertirArraylistAArreglo(ArrayList<E>[] A) {

        E e[][] = (E[][]) new Object[A.length];
        for (int i = 0; i < A.length; i++) {
            e[i] = (E[]) A[i].toArray();
        }

        return e;
    }

    public static <E> E[][] convertirArraylist2AArreglo(ArrayList<ArrayList<E>> A) {
        E e[][] = (E[][]) (A.get(0).get(0) instanceof Arreglo ? ((Arreglo) A.get(0).get(0)).crearArregloSuperclase(A.size(), A.get(0).size()) : arregloAA(A.get(0).get(0), A.size(), A.get(0).size())); //E e[][] = (E[][]) new Object[A.size()];
        //   E e[][] =arreglo(A.get(0).get(0),A.size(), A.get(0).size());
        for (int i = 0; i < A.size(); i++) {
            e[i] = (E[]) A.get(i).toArray();
        }
        return e;
    }

    public static int[][] convertirLinkedListIntegerAArregloint(LinkedList<LinkedList<Integer>> A) {
        int e[][] = new int[A.size()][];
        //   E e[][] =arreglo(A.get(0).get(0),A.size(), A.get(0).size());
        for (int i = 0; i < A.size(); i++) {
            //   e[i] =  A.get(i).toArray(new int[]{});
            e[i] = Arreglos.convertirArregloAint(A.get(i));
        }
        return e;
    }

    public static boolean IsEmpty(Object A[][]) {

        for (int i = 0; i < A.length; i++) {

            // if(A[i].length>0){
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] != null) {
                    return false;
                }
            }
            //return false;
            //}
        }
        return true;
    }

    public static boolean[] arreglo(boolean... a) {
        return a;
    }

    public static short[] arreglo(short... a) {
        return a;
    }

    public static int[] arreglo(int... a) {
        return a;
    }

    public static long[] arreglo(long... a) {
        return a;
    }

    public static float[] arreglo(float... a) {
        return a;
    }

    public static char[] arreglo(char... a) {
        return a;
    }

    public static double[] arregloD(double... a) {
        return a;
    }

    public static boolean[][] arreglo(boolean[]... a) {
        boolean A[][] = new boolean[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            A[i] = arreglo(a[i]);
        }
        return A;
    }

    public static double[] arregloDouble(int... a) {
        double A[] = new double[a.length];
        for (int i = 0; i < a.length; i++) {
            A[i] = a[i];
        }
        return A;
    }

    public static double[][] arregloDoubleAA(int[]... a) {
        double A[][] = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[0].length; j++) {
                A[i][j] = a[i][j];
            }

        }
        return A;
    }

    public static double[][] arregloAA(double[]... a) {
        double A[][] = new double[a.length][a[0].length];
        for (int i = 0; i < a.length; i++) {
            //  for (int j = 0; j < a[0].length; j++) {
            A[i] = a[i];
            // }

        }
        return A;
    }

    public static void OrdenarAlfabeticamenteArrgloString(String A[]) {
        Arrays.sort(A, new ComparadorOrdenAlfabetico());
    }

    public static void OrdenarAlfabeticamenteArrgloString(String A[], boolean contrario) {
        Arrays.sort(A, new ComparadorOrdenAlfabetico(contrario));
    }

    public static void OrdenarAlfabeticamenteArrayListString(ArrayList<String> A) {
        Collections.sort(A, new ComparadorOrdenAlfabetico());

    }

    public static void OrdenarAlfabeticamenteArrayListString(ArrayList<String> A, boolean contrario) {
        Collections.sort(A, new ComparadorOrdenAlfabetico(contrario));
    }

    public static <E> E[] eliminarRepetidos(E A[]) {
        return new TreeSet<E>(Arrays.asList(A)).toArray(A);
    }

    public static double[][] AdquirirMatrizDouble() {
        System.out.println("Escriba la cantidad de filas y columnas separados por un espacio ");
        double A[][] = new double[s.nextInt()][s.nextInt()];
        for (int i = 0; i < A.length; i++) {
            System.out.println("Escriba los elementos de la fila separados por un espacio" + (i + 1));
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = s.nextDouble();
            }
        }
        return A;
    }

    public static int[][] AdquirirMatrizInt() {
        System.out.println("Escriba la cantidad de filas y columnas separados por un espacio ");
        int A[][] = new int[s.nextInt()][s.nextInt()];
        for (int i = 0; i < A.length; i++) {
            System.out.println("Escriba los elementos de la fila separados por un espacio" + (i + 1));
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = s.nextInt();
            }
        }
        return A;
    }

    public static String[][] AdquirirMatrizStrign() {
        System.out.println("Escriba la cantidad de filas y columnas separados por un espacio ");
        String A[][] = new String[s.nextInt()][s.nextInt()];
        for (int i = 0; i < A.length; i++) {
            System.out.println("Escriba los elementos de la fila separados por un espacio" + (i + 1));
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = s.next();
            }
        }
        return A;
    }

    public static char[][] AdquirirMatrizChar() {
        System.out.println("Escriba la cantidad de filas y columnas separados por un espacio ");
        char A[][] = new char[s.nextInt()][s.nextInt()];
        for (int i = 0; i < A.length; i++) {
            System.out.println("Escriba los elementos de la fila separados por un espacio" + (i + 1));
            for (int j = 0; j < A[i].length; j++) {
                A[i][j] = s.next().charAt(0);
            }
        }
        return A;
    }

    public static <E> void MostrarMatrizObject(E A[][]) {
        System.out.println("La Matris resultante es:");
        String f1 = "";
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                f1 += "  " + A[i][j];
            }
            System.out.println(f1);
            f1 = "";
        }
    }

    public static <E> E[] eliminarSeccion(E A[], int inicial) {
        return eliminarSeccion(A, A.length);
    }

    public static <E> E[] eliminarSeccion(E A[], int inicial, int fin) {
        ArrayList<E> a = new ArrayList<E>(Arrays.asList(A));
        a.subList(inicial, fin).clear();
        return a.toArray(A);
    }

//    public static void mostrarArreglo(int[]... A) {
//      
//        for (int i = 0; i < A.length; i++) {
//            System.out.println( Arrays.toString(A[i]));
//           
//        }
//    }
    // sustitulle
    public static <E> E[] colocarEnElIndiceObject(E A[], E a, int indice) {
        ArrayList U = new ArrayList(Arrays.asList(A));
        U.set(indice, a);
        return (E[]) U.toArray(A);
    }

    //no sustitulle
    public static <E> E[] añadirEnElIndiceObject(E A[], E a, int indice) {
        ArrayList U = new ArrayList(Arrays.asList(A));
        U.add(indice, a);
        return (E[]) U.toArray(A);
    }

    public static String[][] añadirEnElIndice(String[][] A, String a[], int indice) {
        String B[][] = new String[A.length + 1][];
        for (int i = 0; i < B.length; i++) {
            if (i == indice) {
                B[i] = a;
            } else {
                int indiceA = i >= indice ? i - 1 : i;
                B[i] = new String[A[indiceA].length];
                for (int j = 0; j < A[indiceA].length; j++) {
                    B[i][j] = A[indiceA][j];
                }
            }

        }
        if (indice == A.length) {
            B[indice] = a;
        }
        return B;
    }

    public static boolean[][] añadirEnElIndice(boolean[][] A, boolean a[], int indice) {
        boolean B[][] = new boolean[A.length + 1][];
        for (int i = 0; i < B.length; i++) {
            if (i == indice) {
                B[i] = a;
            } else {
                int indiceA = i >= indice ? i - 1 : i;
                B[i] = new boolean[A[indiceA].length];
                for (int j = 0; j < A[indiceA].length; j++) {
                    B[i][j] = A[indiceA][j];
                }
            }

        }
        if (indice == A.length) {
            B[indice] = a;
        }
        return B;
    }

    public static <E> E[] colocarDePrimeroObject(E A[], E a) {
        ArrayList U = new ArrayList(Arrays.asList(A));
        U.add(0, a);
        return (E[]) U.toArray(A);
    }

    public static <E> E[] colocarDeUltimoObject(E A[], E a) {
        ArrayList U = new ArrayList(Arrays.asList(A));
        U.add(a);
        // A=(E[]) U.toArray(A);
        return (E[]) U.toArray(A);
    }

    public static <E> E[] UnirDosArreglosObject(E[]... A) {
        ArrayList U = new ArrayList(Arrays.asList(A[0]));
        for (int i = 1; i < A.length; i++) {
            U.addAll(Arrays.asList(A[i]));
        }
        return (E[]) U.toArray(A[0]);
    }

    public static <E> E[] ampliarArregloObject(E A[], int ampliacion) {
        Vector v = new Vector(Arrays.asList(A));
        v.setSize(A.length + ampliacion);
        return (E[]) v.toArray(A);
//        Array.newInstance(new Class, ampliacion);
//        LinkedList<E> e = new LinkedList<E>(Arrays.asList(A));
//        e.add(null);
//        return (E[]) e.toArray(A);
    }

    public static String[][] ampliarArreglo(String A[][], int ampliacion) {
        String B[][] = new String[A.length + ampliacion][];
        for (int i = 0; i < A.length; i++) {
            B[i] = new String[A[i].length];
            for (int j = 0; j < A[i].length; j++) {
                B[i][j] = A[i][j];
            }
        }
        for (int i = A.length; i < B.length; i++) {
            B[i] = new String[2];
        }
        return B;
    }

    public static boolean[][] ampliarArreglo(boolean A[][], int ampliacion) {
        boolean B[][] = new boolean[A.length + ampliacion][];
        for (int i = 0; i < A.length; i++) {
            B[i] = new boolean[A[i].length];
            for (int j = 0; j < A[i].length; j++) {
                B[i][j] = A[i][j];
            }
        }
//        for (int i = A.length; i < B.length; i++) {
//            B[i] = new String[2];
//        }
        return B;
    }

    public static <E> E[][] eliminarObjetos(E A[][], E... B) {
//      ArrayList<ArrayList<E>> C=convertirArreglo2AArrayList(A);
//        for (int i = 0; i < A.length; i++) {
//            C.get(i).removeAll(new ArrayList<E>(Arrays.asList(B)));
//        }
        // return convertirArraylist2AArreglo(C);
        for (int i = 0; i < A.length; i++) {
            //   System.out.println("A[i].length=" + A[i].length);
            A[i] = eliminarObjetos(A[i], B);
            //  System.out.println("A[i].length=" + A[i].length);
        }
        return A;
    }

    public static <E> E[] eliminarObjetos(E A[], E... B) {
        E C[] = Arrays.copyOf(A, 0);
        ArrayList<E> a = new ArrayList<E>(Arrays.asList(A));
        //  System.out.println("a.size()=" + a.size());
        a.removeAll(new ArrayList<E>(Arrays.asList(B)));
        // System.out.println("a.size()=" + a.size());
        return a.toArray(C);
    }

    public static <E> E[] eliminarIndiceObject(E A[], int... indice) {
        ArrayList<E> a = new ArrayList<E>(Arrays.asList(A));
        for (int i : indice) {
            a.remove(i);
        }
        return a.toArray(A);
    }

    public static <E> E[] eliminarIndiceObject(E A[], int indice) {
        ArrayList<E> a = new ArrayList<E>(Arrays.asList(A));
        a.remove(indice);
//        Array.newInstance(A[indice].getClass(), A.length-1);
//        return a.toArray(Arrays.copyOf(A, A.length - 1));
        return a.toArray(Arrays.copyOf(A, A.length - 1));
    }

    public static String[][] eliminarIndice(String A[][], int indice) {
        if (indice >= 0 && indice < A.length) {
            String B[][] = new String[A.length == 0 ? 0 : A.length - 1][];
            for (int i = 0; i < B.length; i++) {
                int indiceA = i >= indice ? i + 1 : i;
                B[i] = new String[A[indiceA].length];
                for (int j = 0; j < A[indiceA].length; j++) {
                    B[i][j] = A[indiceA][j];
                }
            }
            return B;
        }
        return null;
    }

    public static boolean[][] eliminarIndice(boolean A[][], int indice) {
        if (indice >= 0 && indice < A.length) {
            boolean B[][] = new boolean[A.length == 0 ? 0 : A.length - 1][];
            for (int i = 0; i < B.length; i++) {
                int indiceA = i >= indice ? i + 1 : i;
                B[i] = new boolean[A[indiceA].length];
                for (int j = 0; j < A[indiceA].length; j++) {
                    B[i][j] = A[indiceA][j];
                }
            }
            return B;
        }
        return null;
    }

    public static String[][] eliminarUltimo(String A[][]) {
        String B[][] = new String[A.length == 0 ? 0 : A.length - 1][];
        for (int i = 0; i < B.length; i++) {
            B[i] = new String[A[i].length];
            for (int j = 0; j < 10; j++) {
                B[i][j] = A[i][j];
            }
        }
        return B;
    }

    public static boolean[][] eliminarUltimo(boolean A[][]) {
        boolean B[][] = new boolean[A.length == 0 ? 0 : A.length - 1][];
        for (int i = 0; i < B.length; i++) {
            B[i] = new boolean[A[i].length];
            for (int j = 0; j < 10; j++) {
                B[i][j] = A[i][j];
            }
        }
        return B;
    }

    public static void mostrarArregloObject(Object... A) {
        mostrarList(new ArrayList(Arrays.asList(A)));
    }

    public static void mostrarArregloPoint(Point A[]) {
        String e = "";
        for (int i = 0; i < A.length; i++) {
            e += "[" + A[i].x + ";" + A[i].y + "] ";
        }
        System.out.println(e);
    }

    static public void MostrarMatriz(int A[][]) {
        MostrarMatrizObject(convertirArregloAObjetoInteger(A));
    }

    static public void MostrarMatriz(double A[][]) {
        MostrarMatrizObject(convertirArregloAObjetoDouble(A));
    }

    static public void MostrarMatriz(short A[][]) {
        MostrarMatrizObject(convertirArregloAObjetoShort(A));
    }

    static public void MostrarMatriz(long A[][]) {
        MostrarMatrizObject(convertirArregloAObjetoLong(A));
    }

    static public void MostrarMatriz(float A[][]) {
        MostrarMatrizObject(convertirArregloAObjetoFloat(A));
    }

    static public void MostrarMatriz(char A[][]) {
        MostrarMatrizObject(convertirArregloAObjetoCharacter(A));
    }

    //-------
    public static int[] eliminarSeccion(int A[], int inicial, int fin) {
        return Arreglos.convertirArregloAint(eliminarSeccion(Arreglos.convertirArregloAObjetoInteger(A), inicial, fin));
    }

    public static short[] eliminarSeccion(short A[], int inicial, int fin) {
        return Arreglos.convertirArregloAshort(eliminarSeccion(Arreglos.convertirArregloAObjetoShort(A), inicial, fin));
    }

    public static long[] eliminarSeccion(long A[], int inicial, int fin) {
        return Arreglos.convertirArregloAlong(eliminarSeccion(Arreglos.convertirArregloAObjetoLong(A), inicial, fin));
    }

    public static float[] eliminarSeccion(float A[], int inicial, int fin) {
        return Arreglos.convertirArregloAfloat(eliminarSeccion(Arreglos.convertirArregloAObjetoFloat(A), inicial, fin));
    }

    public static double[] eliminarSeccion(double A[], int inicial, int fin) {
        return Arreglos.convertirArregloAdouble(eliminarSeccion(Arreglos.convertirArregloAObjetoDouble(A), inicial, fin));
    }

    public static char[] eliminarSeccion(char A[], int inicial, int fin) {
        return Arreglos.convertirArregloAchar(eliminarSeccion(Arreglos.convertirArregloAObjetoCharacter(A), inicial, fin));
    }

    //-----------
    public static int[] colocarEnElIndice(int A[], int B, int indice) {
        return Arreglos.convertirArregloAint(colocarEnElIndiceObject(Arreglos.convertirArregloAObjetoInteger(A), new Integer(B), indice));
    }

    public static short[] colocarEnElIndice(short A[], short B, int indice) {
        return Arreglos.convertirArregloAshort(colocarEnElIndiceObject(Arreglos.convertirArregloAObjetoShort(A), new Short(B), indice));
    }

    public static long[] colocarEnElIndice(long A[], long B, int indice) {
        return Arreglos.convertirArregloAlong(colocarEnElIndiceObject(Arreglos.convertirArregloAObjetoLong(A), new Long(B), indice));
    }

    public static float[] colocarEnElIndice(float A[], float B, int indice) {
        return Arreglos.convertirArregloAfloat(colocarEnElIndiceObject(Arreglos.convertirArregloAObjetoFloat(A), new Float(B), indice));
    }

    public static double[] colocarEnElIndice(double A[], double B, int indice) {
        return Arreglos.convertirArregloAdouble(colocarEnElIndiceObject(Arreglos.convertirArregloAObjetoDouble(A), new Double(B), indice));
    }

    public static char[] colocarEnElIndice(char A[], char B, int indice) {
        return Arreglos.convertirArregloAchar(colocarEnElIndiceObject(Arreglos.convertirArregloAObjetoCharacter(A), new Character(B), indice));
    }
    //---------------

    public static int[] añadirEnElIndice(int A[], int B, int indice) {
        return Arreglos.convertirArregloAint(añadirEnElIndiceObject(Arreglos.convertirArregloAObjetoInteger(A), new Integer(B), indice));
    }

    public static short[] añadirEnElIndice(short A[], short B, int indice) {
        return Arreglos.convertirArregloAshort(añadirEnElIndiceObject(Arreglos.convertirArregloAObjetoShort(A), new Short(B), indice));
    }

    public static long[] añadirEnElIndice(long A[], long B, int indice) {
        return Arreglos.convertirArregloAlong(añadirEnElIndiceObject(Arreglos.convertirArregloAObjetoLong(A), new Long(B), indice));
    }

    public static float[] añadirEnElIndice(float A[], float B, int indice) {
        return Arreglos.convertirArregloAfloat(añadirEnElIndiceObject(Arreglos.convertirArregloAObjetoFloat(A), new Float(B), indice));
    }

    public static double[] añadirEnElIndice(double A[], double B, int indice) {
        return Arreglos.convertirArregloAdouble(añadirEnElIndiceObject(Arreglos.convertirArregloAObjetoDouble(A), new Double(B), indice));
    }

    public static char[] añadirEnElIndice(char A[], char B, int indice) {
        return Arreglos.convertirArregloAchar(añadirEnElIndiceObject(Arreglos.convertirArregloAObjetoCharacter(A), new Character(B), indice));
    }

    //-----------------
    public static int[] colocarDePrimero(int A[], int B) {
        return Arreglos.convertirArregloAint(colocarDePrimeroObject(Arreglos.convertirArregloAObjetoInteger(A), new Integer(B)));
    }

    public static short[] colocarDePrimero(short A[], short B) {
        return Arreglos.convertirArregloAshort(colocarDePrimeroObject(Arreglos.convertirArregloAObjetoShort(A), new Short(B)));
    }

    public static long[] colocarDePrimero(long A[], long B) {
        return Arreglos.convertirArregloAlong(colocarDePrimeroObject(Arreglos.convertirArregloAObjetoLong(A), new Long(B)));
    }

    public static float[] colocarDePrimero(float A[], float B) {
        return Arreglos.convertirArregloAfloat(colocarDePrimeroObject(Arreglos.convertirArregloAObjetoFloat(A), new Float(B)));
    }

    public static double[] colocarDePrimero(double A[], double B) {
        return Arreglos.convertirArregloAdouble(colocarDePrimeroObject(Arreglos.convertirArregloAObjetoDouble(A), new Double(B)));
    }

    public static char[] colocarDePrimero(char A[], char B) {
        return Arreglos.convertirArregloAchar(colocarDePrimeroObject(Arreglos.convertirArregloAObjetoCharacter(A), new Character(B)));
    }

    //---------------
    public static int[] colocarDeUltimo(int A[], int B) {
        return Arreglos.convertirArregloAint(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoInteger(A), new Integer(B)));
    }

    public static short[] colocarDeUltimo(short A[], short B) {
        return Arreglos.convertirArregloAshort(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoShort(A), new Short(B)));
    }

    public static long[] colocarDeUltimo(long A[], long B) {
        return Arreglos.convertirArregloAlong(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoLong(A), new Long(B)));
    }

    public static float[] colocarDeUltimo(float A[], float B) {
        return Arreglos.convertirArregloAfloat(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoFloat(A), new Float(B)));
    }

    public static double[] colocarDeUltimo(double A[], double B) {
        return Arreglos.convertirArregloAdouble(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoDouble(A), new Double(B)));
    }

    public static char[] colocarDeUltimo(char A[], char B) {
        return Arreglos.convertirArregloAchar(colocarDeUltimoObject(Arreglos.convertirArregloAObjetoCharacter(A), new Character(B)));
    }

    //--------------
    public static int[] UnirDosArreglos(int A[], int B[]) {
        return Arreglos.convertirArregloAint(UnirDosArreglosObject(Arreglos.convertirArregloAObjetoInteger(A), Arreglos.convertirArregloAObjetoInteger(B)));
    }

    public static short[] UnirDosArreglos(short A[], short B[]) {
        return Arreglos.convertirArregloAshort(UnirDosArreglosObject(Arreglos.convertirArregloAObjetoShort(A), Arreglos.convertirArregloAObjetoShort(B)));
    }

    public static long[] UnirDosArreglos(long A[], long B[]) {
        return Arreglos.convertirArregloAlong(UnirDosArreglosObject(Arreglos.convertirArregloAObjetoLong(A), Arreglos.convertirArregloAObjetoLong(B)));
    }

    public static float[] UnirDosArreglos(float A[], float B[]) {
        return Arreglos.convertirArregloAfloat(UnirDosArreglosObject(Arreglos.convertirArregloAObjetoFloat(A), Arreglos.convertirArregloAObjetoFloat(B)));
    }

    public static double[] UnirDosArreglos(double A[], double B[]) {
        return Arreglos.convertirArregloAdouble(UnirDosArreglosObject(Arreglos.convertirArregloAObjetoDouble(A), Arreglos.convertirArregloAObjetoDouble(B)));
    }

    public static char[] UnirDosArreglos(char A[], char B[]) {
        return Arreglos.convertirArregloAchar(UnirDosArreglosObject(Arreglos.convertirArregloAObjetoCharacter(A), Arreglos.convertirArregloAObjetoCharacter(B)));
    }
    //------------

    public static boolean[] ampliarArreglo(boolean A[], int ampliacion) {
        Boolean B[] = convertirArregloAObjetoBoolean(A);
        return Arreglos.convertirArregloAboolean(ampliarArregloObject(B, ampliacion));
    }

    public static int[] ampliarArreglo(int A[], int ampliacion) {
        return Arreglos.convertirArregloAint(ampliarArregloObject(Arreglos.convertirArregloAObjetoInteger(A), ampliacion));
    }

    public static short[] ampliarArreglo(short A[], int ampliacion) {
        return Arreglos.convertirArregloAshort(ampliarArregloObject(Arreglos.convertirArregloAObjetoShort(A), ampliacion));
    }

    public static long[] ampliarArreglo(long A[], int ampliacion) {
        return Arreglos.convertirArregloAlong(ampliarArregloObject(Arreglos.convertirArregloAObjetoLong(A), ampliacion));
    }

    public static float[] ampliarArreglo(float A[], int ampliacion) {
        return Arreglos.convertirArregloAfloat(ampliarArregloObject(Arreglos.convertirArregloAObjetoFloat(A), ampliacion));
    }

    public static double[] ampliarArreglo(double A[], int ampliacion) {
        return Arreglos.convertirArregloAdouble(ampliarArregloObject(Arreglos.convertirArregloAObjetoDouble(A), ampliacion));
    }

    public static char[] ampliarArreglo(char A[], int ampliacion) {
        return Arreglos.convertirArregloAchar(ampliarArregloObject(Arreglos.convertirArregloAObjetoCharacter(A), ampliacion));
    }
//-------------

    public static int[] eliminarIndice(int A[], int indice) {
        return Arreglos.convertirArregloAint(eliminarIndiceObject(Arreglos.convertirArregloAObjetoInteger(A), indice));
    }

    public static short[] eliminarIndice(short A[], int indice) {
        return Arreglos.convertirArregloAshort(eliminarIndiceObject(Arreglos.convertirArregloAObjetoShort(A), indice));
    }

    public static long[] eliminarIndice(long A[], int indice) {
        return Arreglos.convertirArregloAlong(eliminarIndiceObject(Arreglos.convertirArregloAObjetoLong(A), indice));
    }

    public static float[] eliminarIndice(float A[], int indice) {
        return Arreglos.convertirArregloAfloat(eliminarIndiceObject(Arreglos.convertirArregloAObjetoFloat(A), indice));
    }

    public static double[] eliminarIndice(double A[], int indice) {
        return Arreglos.convertirArregloAdouble(eliminarIndiceObject(Arreglos.convertirArregloAObjetoDouble(A), indice));
    }

    public static char[] eliminarIndice(char A[], int indice) {
        return Arreglos.convertirArregloAchar(eliminarIndiceObject(Arreglos.convertirArregloAObjetoCharacter(A), indice));
    }

    public static Boolean[] convertirArregloAObjetoBoolean(boolean A[]) {
        Boolean B[] = new Boolean[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Integer[] convertirArregloAObjetoInteger(int A[]) {
        Integer B[] = new Integer[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Double[] convertirArregloAObjetoDouble(Integer A[]) {
        Double B[] = new Double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i].doubleValue();
        }
        return B;
    }

    public static Double[] convertirArregloAObjetoDouble(Float A[]) {
        Double B[] = new Double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i].doubleValue();
        }
        return B;
    }

    public static Double[] convertirArregloAObjetoDouble(double A[]) {
        Double B[] = new Double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Short[] convertirArregloAObjetoShort(short A[]) {
        Short B[] = new Short[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Long[] convertirArregloAObjetoLong(long A[]) {
        Long B[] = new Long[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Float[] convertirArregloAObjetoFloat(float A[]) {
        Float B[] = new Float[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static Character[] convertirArregloAObjetoCharacter(char A[]) {
        Character B[] = new Character[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    //--------
    public static boolean[] convertirArregloAboolean(ArrayList<Boolean> A) {
        return convertirArregloAboolean(A.toArray(new Boolean[]{}));
    }

    public static int[] convertirArregloAint(ArrayList<Integer> A) {
        return Arreglos.convertirArregloAint(A.toArray(new Integer[0]));
    }

    public static int[] convertirArregloAint(LinkedList<Integer> A) {
        return Arreglos.convertirArregloAint(A.toArray(new Integer[0]));
    }

    public static int[] convertirArregloAint(Integer A[]) {
//        System.out.println(Arrays.toString(A));

        int B[] = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static boolean[] convertirArregloAboolean(Boolean A[]) {
//        System.out.println(Arrays.toString(A));

        boolean B[] = new boolean[A.length];
        for (int i = 0; i < A.length; i++) {
            if (A[i] != null) {
                B[i] = A[i];
            }

        }
        return B;
    }

    public static double[] convertirArregloAdouble(ArrayList<Double> A) {
        return Arreglos.convertirArregloAdouble(A.toArray(new Double[0]));
    }

    public static double[] convertirArregloAdouble(LinkedList<Double> A) {
        return Arreglos.convertirArregloAdouble(A.toArray(new Double[0]));
    }

    public static double[] convertirArregloAdouble(Double A[]) {
        double B[] = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static double[] convertirArregloAdouble(Integer A[]) {
        double B[] = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static double[] convertirArregloAdouble(Float A[]) {
        double B[] = new double[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static short[] convertirArregloAshort(ArrayList<Short> A) {
        return Arreglos.convertirArregloAshort(A.toArray(new Short[0]));
    }

    public static short[] convertirArregloAshort(Short A[]) {
        short B[] = new short[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static long[] convertirArregloAlong(ArrayList<Long> A) {
        return Arreglos.convertirArregloAlong(A.toArray(new Long[0]));
    }

    public static long[] convertirArregloAlong(Long A[]) {
        long B[] = new long[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static float[] convertirArregloAfloat(ArrayList<Float> A) {
        return Arreglos.convertirArregloAfloat(A.toArray(new Float[0]));
    }

    public static float[] convertirArregloAfloat(Float A[]) {
        float B[] = new float[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    public static char[] convertirArregloAchar(ArrayList<Character> A) {
        return Arreglos.convertirArregloAchar(A.toArray(new Character[0]));
    }

    public static char[] convertirArregloAchar(Character A[]) {
        char B[] = new char[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = A[i];
        }
        return B;
    }

    //---------
    public static int[][] convertirArregloAint(Integer A[][]) {
        int B[][] = new int[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new int[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static double[][] convertirArregloAdouble(Integer A[][]) {
        double B[][] = new double[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new double[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c].doubleValue();
            }

        }
        return B;
    }

    public static double[][] convertirArregloAdouble(Float A[][]) {
        double B[][] = new double[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new double[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c].doubleValue();
            }

        }
        return B;
    }

    public static double[][] convertirArregloAdouble(Double A[][]) {
        double B[][] = new double[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new double[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static short[][] convertirArregloAshort(Short A[][]) {
        short B[][] = new short[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new short[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static long[][] convertirArregloAlong(Long A[][]) {
        long B[][] = new long[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new long[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static float[][] convertirArregloAfloat(Float A[][]) {
        float B[][] = new float[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new float[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static char[][] convertirArregloAchar(Character A[][]) {
        char B[][] = new char[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new char[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    //------------
    public static Integer[][] convertirArregloAObjetoInteger(int A[][]) {
        Integer B[][] = new Integer[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Integer[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static Double[][] convertirArregloAObjetoDouble(double A[][]) {
        Double B[][] = new Double[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Double[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static Short[][] convertirArregloAObjetoShort(short A[][]) {
        Short B[][] = new Short[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Short[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static Long[][] convertirArregloAObjetoLong(long A[][]) {
        Long B[][] = new Long[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Long[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static Float[][] convertirArregloAObjetoFloat(float A[][]) {
        Float B[][] = new Float[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Float[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static Character[][] convertirArregloAObjetoCharacter(char A[][]) {
        Character B[][] = new Character[A.length][];
        for (int f = 0; f < A.length; f++) {
            B[f] = new Character[A[f].length];
            for (int c = 0; c < A[f].length; c++) {
                B[f][c] = A[f][c];
            }

        }
        return B;
    }

    public static <E> E[] arregloObject(E... A) {
//        System.out.println("1 "+A.getClass().getSimpleName());
//        System.out.println("5 "+A[0].getClass().getSimpleName());
//       E e[] = (E[]) (A[0] instanceof Arreglo ? ((Arreglo) A[0]).crearArregloSuperclase(A.length) : new Object[A.length]);
//        for (int i = 0; i < A.length; i++) {
//
//            e[i] = A[i];
//        }
        return A;
    }

    //El Metodo Mas Importante !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
    public static <E> E[] arregloObjectA(E a, int lengh) {
//        //E e[] =(E[]) arregloObject(a);
//      //  System.out.println(e.getClass().getSimpleName());
//        E e[]= Arrays.copyOf(arregloObject(a), lengh);
//        return e;
        return (E[]) Array.newInstance(a.getClass(), lengh);
    }

    public static <E> E[][] arregloAA(E a, int filas, int columnas) {
        return (E[][]) Array.newInstance(a.getClass(), filas, columnas);
    }

    public static <E> E[][][] arregloAAA(E a, int filas, int columnas, int cantidad) {
        return (E[][][]) Array.newInstance(a.getClass(), filas, columnas, cantidad);
    }

//    public static <E> E[][][] arreglo(int filas, int columnas, int cantidad,E... a ) {
//        return arreglo(arreglo(a, filas, columnas), cantidad);
//    }
}
