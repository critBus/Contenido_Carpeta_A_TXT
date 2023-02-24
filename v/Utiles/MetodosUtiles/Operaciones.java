/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.MetodosUtiles;

import Utiles.ClasesUtiles.Matematicas.*;
import Utiles.ClasesUtiles.Colecciones.*;
import Utiles.ClasesUtiles.Num;
import Utiles.Exepciones.ExedeCapacidadException;
import static Utiles.MetodosUtiles.MetodosUtiles.*;
import static Utiles.MetodosUtiles.MetodosParaManipularMisClases.*;
import static Utiles.MetodosUtiles.Arreglos.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Stack;
import java.util.TreeSet;
//import java.util.logging.Level;
//import java.util.logging.Logger;

/**
 * Version 1.8
 *
 * @author Rene
 */
public abstract class Operaciones {
//instanceof Arreglo

    static public final int primosHasta1000[] = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31,
        37, 41, 43, 47, 53, 59, 61, 67, 71, 73, 79, 83, 89, 97, 101, 103, 107,
        109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167, 173, 179, 181, 191,
        193, 197, 199, 211, 223, 227, 229, 233, 239, 241, 251, 257, 263, 269, 271,
        277, 281, 283, 293, 307, 311, 313, 317, 331, 337, 347, 349, 353, 359, 367,
        373, 379, 383, 389, 397, 401, 409, 419, 421, 431, 433, 439, 443, 449, 457,
        461, 463, 467, 479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563,
        569, 571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643, 647,
        653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733, 739, 743, 751,
        757, 761, 769, 773, 787, 797, 809, 811, 821, 823, 827, 829, 839, 853, 857,
        859, 863, 877, 881, 883, 887, 907, 911, 919, 929, 937, 941, 947, 953, 967,
        971, 977, 983, 991, 997};
//Newton

    public static double splineCubicoNatura(String funciones[], double limiteInferior[], double limiteSuperior[], double X[], double y[], double x, double errorPrefijado) {
//        int n = X.length - 1;
//        double h[] = new double[n];
//        for (int i = 0; i <= n - 1; i++) {
//            h[i] = X[i + 1] - X[i];
//        }
//        double[][] H = matrizTresDigonalesDeSplineNatural(h);
//        double[] Y = matrizColumnaDeSplineNatural(h, y);
        double[] M = matrizMcolumnaDeSplineNatural(funciones, limiteInferior, limiteSuperior, X);
        //  return splineCubicoNatura(X, Y, M, H, h, x, errorPrefijado);
        return splineCubicoNatura(X, y, M, x, errorPrefijado);
    }

    public static double splineCubicoNatura(double X[], double y[], double M[], double x, double errorPrefijado) {
        int n = X.length - 1;
        double h[] = new double[n];
        for (int i = 0; i <= n - 1; i++) {
            h[i] = X[i + 1] - X[i];
        }
        double[][] H = matrizTresDigonalesDeSplineNatural(h);
        double[] Y = matrizColumnaDeSplineNatural(h, y);
        return splineCubicoNatura(X, Y, M, H, h, x, errorPrefijado);
    }

    public static double splineCubicoNatura(double X[], double Y[], double M[], double H[][], double h[], double x, double errorPrefijado) {
        System.out.println("Y.length=" + Y.length + " M.length" + M.length + " H.length=" + H.length + " " + H[0].length);
        Y = Seidel(H, Y, M, errorPrefijado);
        System.out.println(" X.length=" + X.length);
        int n = X.length - 1;
        int i = n;
        while (X[i] > x) {
            i--;
        }
        double u = x - X[i], v = X[i + 1] - x;
        return ((Math.pow(v, 3) * M[i] + Math.pow(u, 3) * M[i + 1]) / (6 * h[i])) + ((v * Y[i] + u * Y[i + 1]) / h[i]) - ((h[i] * (v * M[i] + u * M[i + 1])) / 6);
    }

//    public static double splineCubicoNatura(double X[], double Y[][], double M[][], double H[][], double errorPrefijado) {
//     double  Y2[]=Seidel(H, X, X, errorPrefijado);
//    }
    public static double[] matrizMcolumnaDeSplineNatural(String funciones[], double limiteInferior[], double limiteSuperior[], double X[]) {
        String segunadasDerivadas[] = new String[funciones.length];
        for (int i = 0; i < segunadasDerivadas.length; i++) {
            segunadasDerivadas[i] = interpreteStringSimplificado(funciones[i]).obtenerDerivadaOptima('x', 2).toString();
        }
        double M[] = new double[X.length];
        superior:
        for (int i = 0; i < X.length; i++) {
            for (int j = 0; j < X.length; j++) {
                if (X[i] < limiteSuperior[j] && X[i] >= limiteInferior[j]) {
                    M[i] = interpreteStringSimplificado(sustituirVariableString(funciones[j], 'x', X[i])).getNumero();
                    continue superior;
                }
            }
        }
        return M;
    }

    public static double[] matrizColumnaDeSplineNatural(double h[], double y[]) {
        int n = h.length;
        double[] Y = arregloFill(0.0, n - 2);
//        for (int i = 0; i < y.length; i++) {
//            double h0 = h[i], h1 = h[i + 1], y0 = y[i], y1 = y[i + 1], y2 = y[i + 2];
//        }
        for (int i = 0; i < n - 2; i++) {
            double h0 = h[i], h1 = h[i + 1], y0 = y[i], y1 = y[i + 1], y2 = y[i + 2];
            Y[i] = ((y2 - y1) / h1) - ((y1 - y0) / h0);
        }
        return Y;
    }

    public static double[][] matrizTresDigonalesDeSplineNatural(double h[]) {
        int n = h.length;
        // double[][] H = arregloFill(0.0, n - 1, n - 1);
        double[][] H = arregloFill(0.0, n - 2, n - 2);
        //  for (int i = 0; i < n - 1; i++) {
        for (int i = 0; i < n - 2; i++) {
            double h1 = h[i], h2 = h[i + 1];
            if (i != 0) {
                H[i][i - 1] = h1 / 6;
            }
            H[i][i] = (h1 + h2) / 3;
            if (i != H[0].length - 1) {
                H[i][i + 1] = h2 / 6;
            }
        }
        return H;
//        double[][] H = arregloFill(0.0, n+1 , n+1 );
//        for (int i = 0; i < n - 1; i++) {
//            double h0 = h[i], h1 = h[i + 1];
//            if (i != 0) {
//                H[i][i - 1] = h0 / 6;
//            }
//            H[i][i] = (h0 + h1) / 3;
//            if (i != H[0].length - 1) {
//                H[i][i + 1] = h1 / 6;
//            }
//        }
//        return H;
    }

    public static double metodoDeNewton(double X[], String funcion, double x, double errorPrefijado) {
        double d[] = new double[X.length];
        for (int i = 0; i < X.length; i++) {
            d[i] = interpreteStringSimplificado(sustituirVariableString(funcion, 'x', X[i])).getNumero();
        }
        // System.out.println(Arrays.toString(Y));
        return metodoDeNewton(X, d, x, errorPrefijado);
    }

    public static double metodoDeNewton(double X[], double d[], double x, double errorPrefijado) {
        int n = X.length - 1;
        // X = aumentarEnNulo(X);
        //  d = aumentarEnNulo(d);
        for (int i = 1; i <= n; i++) {
            int j = n;
            // while (!(j < i)) {
            while (j >= i) {
                //d[j] = regulador((d[j] - d[j - 1]) / (X[j] - X[j - 1]));
                d[j] = (d[j] - d[j - 1]) / (X[j] - X[j - 1]);
                j--;
            }
        }
        // double p = d[1], producto = x - X[1], error = regulador(d[1] * producto);
        //d[1]=-0.29790;
        double p = d[0], producto = x - X[0], error = d[1] * producto;
        int i = 1;
        //System.out.println(" d[1]="+ d[1]);
        //   System.out.println("error="+error);
//        System.out.println("n="+n);
//        System.out.println("errorPrefijado="+errorPrefijado);
        while (mod(error) > errorPrefijado && i < n) {
//            System.out.println("i="+i);
            p += error;
            //producto = regulador((producto * (x - X[i])));
            producto *= (x - X[i]);
            i++;
            //error = regulador(d[i] * producto);
            error = d[i] * producto;
            //  System.out.println("error="+error);
        }
        return p;

    }
//    public static double metodoDeNewton(double X[], double d[], double x, double errorPrefijado) {
//        int n = X.length;
//        X = aumentarEnNulo(X);
//        d = aumentarEnNulo(d);
//        for (int i = 1; i <= n; i++) {
//            int j = n;
//            //while (!(j < i)) {
//                while ((j >= i)) {
//                //d[j] = regulador((d[j] - d[j - 1]) / (X[j] - X[j - 1]));
//                d[j] = (d[j] - d[j - 1]) / (X[j] - X[j - 1]);
//                j--;
//            }
//        }
//       // double p = d[1], producto = x - X[1], error = regulador(d[1] * producto);
//         double p = d[1], producto = x - X[1], error = d[1] * producto;
//        int i = 1;
//        while (error > errorPrefijado && i < n) {
//            p += error;
//            //producto = regulador((producto * (x - X[i])));
//            producto *= (x - X[i]);
//            i++;
//           // error = regulador(d[i] * producto);
//            error = d[i] * producto;
//        }
//        return p;
//
//    }

    public static double Lagrange(double X[], String funcion, double x) {
        double Y[] = new double[X.length];
        for (int i = 0; i < X.length; i++) {
            Y[i] = interpreteStringSimplificado(sustituirVariableString(funcion, 'x', X[i])).getNumero();
        }
        // System.out.println(Arrays.toString(Y));
        return Lagrange(X, Y, x);
    }

    public static double Lagrange(double X[], double Y[], double x) {
        int n = X.length - 1;
        // X = aumentarEnNulo(X);
        // Y = aumentarEnNulo(Y);
        double resultado = 0;
        for (int i = 0; i <= n; i++) {
            double L = 1;
            for (int j = 0; j <= n; j++) {
                if (j != i) {
                    L = L * ((x - X[j]) / (X[i] - X[j]));
                }
            }
            resultado += Y[i] * L;
        }
        return resultado;

    }

    public static double[] metodoDeLaPotencia(double A[][]) {
        return metodoDeLaPotencia(A, arregloFill(1.0, A.length), 0.001);
    }

    public static double[] metodoDeLaPotencia(double A[][], double X0[], double errorPrefijado) {
        int n = X0.length;
        X0 = aumentarEnNulo(X0);
        A = aumentarEnNulo(A);
        double z[] = copiaDeArreglo(X0), landaAnterior = Double.MAX_VALUE, error = Double.MAX_VALUE, w[] = new double[n];
        while (error > errorPrefijado) {
            w = copiaDeArreglo(MultiplicarMatrizPorVector(A, z));
            double NormaW = 0;
            int imax = 0;
            for (int i = 1; i <= n; i++) {
                if (mod(w[i]) > NormaW) {
                    NormaW = mod(w[i]);
                    imax = i;
                }
            }
            double landaAproximada = regulador(w[imax] / z[imax]);
            error = mod(landaAproximada - landaAnterior);
            landaAnterior = landaAproximada;
            z = dividirLandaAArregloCopia(w, NormaW);
        }
        return disminuirEnNulo(z);
        //el valor propio de mayor valor absoluto de A es landaAproximada y
        //z es un vector propio asociado
    }

    public static double factorConvergenciaSeidel(double[][] B, double[] b) {
        // MostrarMatriz(B);
        double[][] A = copiaDeArreglo(B);
        double factorConvergencia = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (i != j) {
                    //System.out.print(A[i][j]+"/"+A[i][i]+"  ");
                    A[i][j] /= (-1 * A[i][i]);
                }
            }
            // System.out.print("\n");
        }
        //  System.out.print("\n");
        for (int i = 0; i < A.length; i++) {
            A[i][i] = 0;
        }
        // MostrarMatriz(A);
        for (int i = 0; i < A.length; i++) {
            double sumaArriba = 0;
            double sumaDebajo = 0;
            for (int j = 0; j < A[i].length; j++) {
                if (i < j) {
                    sumaArriba += mod(A[i][j]);
                } else {
                    sumaDebajo += mod(A[i][j]);
                }

                //    System.out.print(A[i][j]+" ");
            }
            // System.out.println("sumaArriba="+sumaArriba);
            //   System.out.println("sumaDebajo="+sumaDebajo);
            //  System.out.println("suma="+suma+"\n");
            if (sumaArriba / (1 - sumaDebajo) > factorConvergencia) {
                factorConvergencia = sumaArriba / (1 - sumaDebajo);
            }
        }
        return factorConvergencia;
    }

    public static double factorConvergenciaJacobi(double[][] B) {
        double[][] A = copiaDeArreglo(B);
        double factorConvergencia = 0;
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (i != j) {
                    //System.out.print(A[i][j]+"/"+A[i][i]+"  ");
                    A[i][j] /= A[i][i];
                }
            }
            // System.out.print("\n");
        }
        //  System.out.print("\n");
        for (int i = 0; i < A.length; i++) {
            A[i][i] = 0;
        }
        for (int i = 0; i < A.length; i++) {
            double suma = 0;
            for (int j = 0; j < A[i].length; j++) {
                suma += mod(A[i][j]);
                //    System.out.print(A[i][j]+" ");
            }
            //  System.out.println("suma="+suma+"\n");
            if (suma > factorConvergencia) {
                factorConvergencia = suma;
            }
        }
        return factorConvergencia;
    }

//    public static double[] Jacobi(double[][] A,double Aii, double[] b, double errorPrefijado) {
//        // double[][] X0=new double[2][2];aumentarEnNulo(new double[A.length][1])
//        return Jacobi(A, Aii, b, arregloFill((double) 0, A.length), errorPrefijado);
//    }
//
//    public static double[] Jacobi(double[][] A,double Aii, double[] b, double[] X0, double errorPrefijado) {
//        System.out.println(factorConvergencia(A));
//        return Jacobi(A,Aii, b, X0, factorConvergencia(A), errorPrefijado);
//    }
    public static double[] Seidel(double[][] A, double[] b, double errorPrefijado) {
        // double[][] X0=new double[2][2];aumentarEnNulo(new double[A.length][1])
        return Seidel(A, b, arregloFill((double) 0, A.length), errorPrefijado);
    }

    public static double[] Seidel(double[][] A, double[] b, double[] X0, double errorPrefijado) {
        // System.out.println(factorConvergenciaSeidel(A, b));
        return Seidel(A, b, X0, factorConvergenciaSeidel(A, b), errorPrefijado);
    }

    public static double[] Seidel(double[][] A, double[] b, double[] X0, double factorConvergencia, double errorPrefijado) {
        double[] X = X0;//, Xa = new double[A.length];
        double error;
        int n = A.length;
        b = aumentarEnNulo(b);
        A = aumentarEnNulo(A);
        X = aumentarEnNulo(X);
        //  Xa = aumentarEnNulo(Xa);
        do {
            error = 0;
            for (int i = 1; i <= n; i++) {
                //  System.out.println("i=" + i);

                double prov = b[i];
                //System.out.println("1 Xa[i]="+Xa[i]);
                for (int j = 1; j <= n; j++) {
                    if (j != i) {
                        prov -= A[i][j] * X[j];
                    }
                    // MostrarMatriz(A);

                }
                //System.out.println("A[i][i]="+A[i][i]);
                prov /= A[i][i];
                //  System.out.println("2 Xa[i]="+Xa[i]);
                if (mod(prov - X[i]) > error) {
                    error = mod(prov - X[i]);
                }
                X[i] = prov;
            }
            // System.out.println("r=" + Arrays.toString(Xa));
            // X = copiaDeArreglo(Xa);

            error *= factorConvergencia / (1 - factorConvergencia);
            // System.out.println("error=" + error);
            //System.out.println("errorPrefijado="+errorPrefijado);
            // System.out.println("error > errorPrefijado="+(error > errorPrefijado));
        } while (error > errorPrefijado);
        return disminuirEnNulo(X);
    }

    public static double[] Jacobi(double[][] A, double[] b, double errorPrefijado) {
        // double[][] X0=new double[2][2];aumentarEnNulo(new double[A.length][1])
        return Jacobi(A, b, arregloFill((double) 0, A.length), errorPrefijado);
    }

    public static double[] Jacobi(double[][] A, double[] b, double[] X0, double errorPrefijado) {
        //  System.out.println(factorConvergencia(A));
        return Jacobi(A, b, X0, factorConvergenciaJacobi(A), errorPrefijado);
    }

    public static double[] Jacobi(double[][] A, double[] b, double[] X0, double factorConvergencia, double errorPrefijado) {
        double[] Xv = X0, Xa = new double[A.length];
        double error;
        int n = A.length;
        b = aumentarEnNulo(b);
        A = aumentarEnNulo(A);
        Xv = aumentarEnNulo(Xv);
        Xa = aumentarEnNulo(Xa);
        do {
            error = 0;
            for (int i = 1; i <= n; i++) {
                System.out.println("i=" + i);

                Xa[i] = b[i];
                //System.out.println("1 Xa[i]="+Xa[i]);
                for (int j = 1; j <= n; j++) {
                    if (j != i) {
                        Xa[i] -= A[i][j] * Xv[j];
                    }
                    // MostrarMatriz(A);

                }
                //System.out.println("A[i][i]="+A[i][i]);
                Xa[i] /= A[i][i];
                //  System.out.println("2 Xa[i]="+Xa[i]);
                if (mod(Xa[i] - Xv[i]) > error) {
                    error = mod(Xa[i] - Xv[i]);
                }
            }
            // System.out.println("r=" + Arrays.toString(Xa));
            Xv = copiaDeArreglo(Xa);
            error *= factorConvergencia / (1 - factorConvergencia);
            //   System.out.println("error=" + error);
            //System.out.println("errorPrefijado="+errorPrefijado);
            // System.out.println("error > errorPrefijado="+(error > errorPrefijado));
        } while (error > errorPrefijado);
        return disminuirEnNulo(Xa);
    }

//    public static Num[] JacobiN(double[][] A,double Aii, double[] b, double errorPrefijado) {
//        // double[][] X0=new double[2][2];aumentarEnNulo(new double[A.length][1])
//        return JacobiN(Num.convertirANum(A),new Num(Aii), Num.convertirANum(b), new Num(errorPrefijado));
//    }
//
//    public static Num[] JacobiN(Num[][] A,Num Aii, Num[] b, Num errorPrefijado) {
//        // double[][] X0=new double[2][2];aumentarEnNulo(new double[A.length][1])
//        return JacobiN(A,Aii, b, arregloObjectFill(new Num(0), A.length), errorPrefijado);
//    }
//
//    public static Num[] JacobiN(Num[][] A,Num Aii, Num[] b, Num[] X0, Num errorPrefijado) {
//        System.out.println(factorConvergencia(A));
//        return JacobiN(A,Aii, b, X0, factorConvergencia(A), errorPrefijado);
//    }
//    public static Num[] JacobiN(double[][] A, double[] b, double errorPrefijado) {
//        // double[][] X0=new double[2][2];aumentarEnNulo(new double[A.length][1])
//        return JacobiN(Num.convertirANum(A), Num.convertirANum(b), new Num(errorPrefijado));
//    }
//
//    public static Num[] JacobiN(Num[][] A, Num[] b, Num errorPrefijado) {
//        // double[][] X0=new double[2][2];aumentarEnNulo(new double[A.length][1])
//        return JacobiN(A, b, arregloObjectFill(new Num(0), A.length), errorPrefijado);
//    }
//
//    public static Num[] JacobiN(Num[][] A, Num[] b, Num[] X0, Num errorPrefijado) {
//        System.out.println(factorConvergencia(A));
//        return JacobiN(A, b, X0, factorConvergencia(A), errorPrefijado);
//    }
//
//    public static Num[] JacobiN(Num[][] A, Num[] b, Num[] X0, Num factorConvergencia, Num errorPrefijado) {
//        Num[] Xv = X0, Xa = new Num[A.length];
//        Num error;
//        int n = A.length;
//        System.out.println("1 b=" + Arrays.toString(b));
//        b = Num.aumentarEnNulo(b);
//        System.out.println("2 b=" + Arrays.toString(b));
//        A = Num.aumentarEnNulo(A);
//        Xv = Num.aumentarEnNulo(Xv);
//        Xa = Num.aumentarEnNulo(Xa);
//        do {
//            error = new Num(0);
//            for (int i = 1; i <= n; i++) {
//                Xa[i] = b[i].copia();
//                for (int j = 1; j <= n; j++) {
//                    if (j != i) {
//                        Xa[i].disminuir(Num.multiplicacion(A[i][j], Xv[j]));
//                        // Xa[i] -= A[i][j] * Xv[j];
//                    }
////                    MostrarMatrizObject(A);
////                    Xa[i].dividir(A[i][i]);
//                    // Xa[i] /= A[i][i];
//                }
//                //*******
//                //MostrarMatrizObject(A);
//                    Xa[i].dividir(A[i][i]);
//               //**********
//                if (Num.mod(Num.resta(Xa[i], Xv[i])).mayorQue(error)) {
//                    error = Num.mod(Num.resta(Xa[i], Xv[i]));
//                }
////                if (mod(Xa[i] - Xv[i]) > error) {
////                    error = mod(Xa[i] - Xv[i]);
////                }
//            }
//            // System.out.println("r="+Arrays.toString(Xa));
//            Xv = copiaDeArreglo(Xa);
//            error.multiplicar(Num.divicion(factorConvergencia, Num.resta(new Num(1), factorConvergencia)));
//            //   error *= factorConvergencia / (1 - factorConvergencia);
//            // System.out.println("error="+error);
//        } while (!(error.menorQue(errorPrefijado)));
//        //  } while (!(error < errorPrefijado));
//
//        return Num.disminuirEnNulo(Xa);
//    }
    public static double[][] matrizInversaGauss(double[][] A) {
        int n = A.length;
        double[][] C = ampliarConMatrizIdentidad(A), X = new double[A.length][A[0].length];
        C = aumentarEnNulo(C);
        X = aumentarEnNulo(X);

        for (int i = 1; i <= n; i++) {
            pivoteParcial(C, i);
            anularDebajoDiagonal(C, i, n);
        }
        for (int j = 1; j <= n; j++) {
            int i = n;
            do {
                X[i][j] = C[i][n + j];
                for (int k = i + 1; k <= n; k++) {
                    X[i][j] -= C[i][k] * X[k][j];
                }
                X[i][j] /= C[i][i];
                i--;
            } while (i != 0);
        }
        return disminuirEnNulo(X);
    }

    public static void anularDebajoDiagonal(double C[][], int i, int n) {

        for (int k = i + 1; k <= n; k++) {
            double m = C[k][i] / C[i][i];
            C[k] = restarArreglos(C[k], multiplicarLandaAArregloCopia(C[i], m));
        }

    }

    public static double determinanteGauss(double[][] A) {
        A = aumentarEnNulo(A);

        int signo = 1, n = A.length - 1;
        // System.out.println("n=" + n);
        //  double max=0,m=-1;
        for (int i = 1; i <= n - 1; i++) {
            // System.out.println("i=" + i);
            MostrarMatriz(A);
            // pivoteParcial(A,i);
            MostrarMatriz(A);
            double max = mod(A[i][i]);
            int filaDeMax = i;
            int k = i + 1;
            for (; k <= n; k++) {
                //  System.out.println("k="+k);
                if (mod(A[k][i]) > max) {
                    max = mod(A[k][i]);
                    filaDeMax = k;
                }
            }
            k--;
            if (max == 0) {
                return 0;
            }
            //   System.out.println("2 k="+k);
            if (filaDeMax > i) {
                intercanviarFila(A, filaDeMax, i);
                //    MostrarMatriz(A);
                //    System.out.println("3 k="+k);
                signo = -1 * signo;
            }
            //*******
            // anularDebajoDiagonal(A, i);
            //*****
            // MostrarMatriz(A);
            k = i + 1;
            //  System.out.println("4 k="+k);
            for (; k <= n; k++) {
                //      System.out.println("5 k="+k);
                // System.out.println("A[k][i]=" + A[k][i]);
                // System.out.println("A[i][i]=" + A[i][i]);
                double m = A[k][i] / A[i][i];
                //   System.out.println(Arrays.toString(A[k]));
                //    System.out.println(Arrays.toString(A[i]));
                A[k] = restarArreglos(A[k], multiplicarLandaAArregloCopia(A[i], m));
                //   System.out.println(Arrays.toString(A[k]));
                MostrarMatriz(A);
            }
        }
        double determinante = signo;
        for (int i = 1; i <= n; i++) {
            determinante *= A[i][i];
        }
        return determinante;
    }

    public static double[] sistemasTridiagonalesGauss(double[][] A, double[] d) {
        double a[] = aumentarEnNulo(diagonalPrincipal(A)), b[] = aumentarEnNulo(diagonalDebajoDePrincipal(A)), c[] = aumentarEnNulo(diagonalEncimaDePrincipal(A)), p[] = new double[a.length], q[] = new double[a.length], X[] = new double[a.length + 1], r[] = new double[a.length];
        int n = a.length;
        d = aumentarEnNulo(d);
        p[1] = c[1] / a[1];
        q[1] = d[1] / a[1];
        int i = 2;
        for (; i <= 2; i++) {
            r[i] = a[i] - b[i] * p[i - 1];
            p[i] = c[i] / r[i];
            q[i] = (d[i] - b[i] * q[i - 1]) / r[i];
        }
        i--;
        X[n] = q[n];
        i = n - 1;
        do {
            X[i] = q[i] - p[i] * X[i + 1];
            i--;
        } while (i != 0);
        eliminarIndice(X, 0);
        return X;
    }

    public static void pivoteElemental(double[][] A) {
        int n = A.length - 1;
        for (int i = 1; i <= n - 1; i++) {
            pivoteElemental(A, i);
//            if (A[i][i] == 0) {
//                int k = i;
//                do {//System.out.println("c");
//                    k++;
//                } while (A[k][i] == 0 && !(k > n));
//                if (k > n) {
//                    throw new ArithmeticException("El sistema no tiene solucon unica pues la matris no es singular");
//                } else {
//                    intercanviarFila(A, k, i);
//                }
//            }
        }
    }

    public static void pivoteParcial(double[][] A, int i) {
        int n = A.length - 1;
        double max = mod(A[i][i]);
        int Imax = i;
        for (int k = i + 1; k <= n; k++) {// System.out.println("b");
            if (mod(A[k][i]) > max) {
                max = mod(A[k][i]);
                Imax = k;
            }
        }
        if (Imax > i) {
            intercanviarFila(A, Imax, i);
        }
    }

    public static void pivoteElemental(double[][] A, int i) {
        int n = A.length - 1;
        if (A[i][i] == 0) {
            int k = i;
            do {//System.out.println("c");
                k++;
            } while (A[k][i] == 0 && !(k > n));
            if (k > n) {
                throw new ArithmeticException("El sistema no tiene solucon unica pues la matris no es singular");
            } else {
                intercanviarFila(A, k, i);
            }
        }

    }

    public static BigDecimal[] gaussMatris(double[][] A, boolean pivoteParcial) {
        A = aumentarEnNulo(A);
        int n = A.length - 1;
        BigDecimal X[] = new BigDecimal[n + 1];
        for (int i = 1; i <= n - 1; i++) {//System.out.println("a");
            if (pivoteParcial) {
//                double max = mod(A[i][i]);
//                int Imax = i;
//                for (int k = i + 1; k <= n; k++) {// System.out.println("b");
//                    if (mod(A[k][i]) > max) {
//                        max = mod(A[k][i]);
//                        Imax = k;
//                    }
//                }
//                if (Imax > i) {
//                    intercanviarFila(A, Imax, i);
//                }
            } else {
                pivoteElemental(A, i);
//                if (A[i][i] == 0) {
//                    int k = i;
//                    do {//System.out.println("c");
//                        k++;
//                    } while (A[k][i] == 0 && !(k > n));
//                    if (k > n) {
//                        throw new ArithmeticException("El sistema no tiene solucon unica pues la matris no es singular");
//                    } else {
//                        intercanviarFila(A, k, i);
//                    }
//                }
            }
            //    MostrarMatriz(A);
            for (int k = i + 1; k <= n; k++) {//System.out.println("e");
                double m = A[k][i] / A[i][i];
                A[k] = restarArreglos(A[k], multiplicarLandaAArregloCopia(A[i], m));
                //  System.out.println("K="+k+"  "+ Arrays.toString(A[k ]));
                ;
            }
            // MostrarMatriz(A);
        }
        regularMatriz(A);
        MostrarMatriz(A);
        int i = n;
        do {//System.out.println("f");
            // X[i] = new BigDecimal(A[i][n+1]);//!!!!!!!!!!!!!!!!!!1111
            X[i] = regularBigDecimal(A[i][n + 1]);
            //System.out.println("1 X[i ]=" + X[i]);
            for (int j = i + 1; j <= n; j++) {
                //  System.out.println("A[i ][j ]=" + A[i][j]);
                //  System.out.println(" X[j ]=" + X[j]);
                // X[i] -= regulador(A[i][j] * X[j]);
                BigDecimal a = regularBigDecimal(A[i][j]);
                // System.out.println("a="+a);
                //  System.out.println("X[j]="+X[j]);
                BigDecimal b = regularBigDecimal(X[j].multiply(a).negate());
                // System.out.println("b="+b);
                //   System.out.println(" X[i]="+ X[i]);
                //   System.out.println("X[i].add(b)="+X[i].add(b));
                //  BigDecimal c=regularBigDecimal(X[i].add(b));
                BigDecimal c = sumaBigDecimal(X[i], b);
                //  System.out.println("c="+c);
                X[i] = c;
                //   System.out.println("X[i]=b ="+X[i]);
                // X[i].add(X[j].multiply(regularBigDecimal(A[i][j])).negate());
                //    System.out.println("2 X[i ]=" + X[i]);
            }
            //  System.out.println("A[i ][i ]=" + A[i][i]);
            //X[i] = regulador(X[i] / A[i][i]);

            //  System.out.println("ff="+ff);
            //  System.out.println("1 X[i]="+ X[i]);
            //  System.out.println("A[i][i]="+A[i][i]);
            try {
               // X[i]=new BigDecimal(X[i].divide(regularBigDecimal(A[i][i])).toString());

                // System.out.println("A[i][i]="+regularBigDecimal(A[i][i]));
                // System.out.println("1 X[i]="+ X[i]);
                // System.out.println("X[i].divide(regularBigDecimal(A[i][i]))="+X[i].divide(regularBigDecimal(A[i][i])));
                //  System.out.println("reg="+regularBigDecimal(X[i].divide(regularBigDecimal(A[i][i]))));
                X[i] = regularBigDecimal(X[i].divide(regularBigDecimal(A[i][i])));
                //   System.out.println("2 X[i]="+ X[i]);
            } catch (ArithmeticException e) {
                System.out.println("error");
                System.out.println("2 X[i]=" + X[i]);

                X[i] = regularBigDecimal(X[i].doubleValue() / A[i][i]);
            }
            //   System.out.println("2 X[i]="+ X[i]);
            //   System.out.println("3 X[i ]=" + X[i]);
            i--;
        } while (i != 0);
        // System.out.println("h");
        return X;
    }

    public static void regularMatriz(double a[][]) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < a[i].length; j++) {
                a[i][j] = regulador(a[i][j]);
            }
        }
    }

    public static double mod(double a) {
        return Math.abs(a);
    }

    public static int mod(int a) {
        return Math.abs(a);
    }

    public static int[] primos(int limite) {
        if (limite < 1000) {
            return convertirArregloAint((Integer[]) new TreeSet(Arrays.asList(convertirArregloAObjetoInteger(primosHasta1000))).headSet(limite + 1).toArray(new Integer[0]));
        }
        LinkedList<Integer> I = new LinkedList<Integer>();
        boolean b[] = new boolean[limite];
        Arrays.fill(b, true);
        for (int i = 2; i < limite; i++) {
            if (b[i]) {
                I.add(i);
                for (int j = i + i; j < limite; j += i) {
                    b[j] = false;
                }
            }
        }
        return Arreglos.convertirArregloAint(I);
    }

    public static int[] primosObsoletos(int limite) {
        if (limite < 1000) {

            return convertirArregloAint((Integer[]) new TreeSet(Arrays.asList(convertirArregloAObjetoInteger(primosHasta1000))).headSet(limite + 1).toArray(new Integer[0]));
        }

        ArrayList<Integer> A = new ArrayList<Integer>(Arrays.asList(convertirArregloAObjetoInteger(primosHasta1000)));

        salto:
        for (int i = 1001; i <= limite; i++) {
            if (((i - 1) % 4 == 0 | (i - 3) % 4 == 0) && ((i - 1) % 6 == 0 | (i + 1) % 6 == 0)) {
                for (Integer a : A) {
                    if (i % a == 0) {
                        continue salto;
                    }
                }
                A.add(i);

            }
        }
        return convertirArregloAint(A);
    }

    public static boolean esPrimo(int a) {
        if (Arrays.asList(convertirArregloAObjetoInteger(primosHasta1000)).contains(new Integer(a))) {

            return true;
        } else {

            return a > 1000 ? Arrays.asList(convertirArregloAObjetoInteger(primos(a))).contains(new Integer(a)) : false;
            //return primo(a);
        }

    }

    public static String derivada(String funcion, char variable) {
        return derivada(funcion, variable, 1);
    }

    public static String derivada(String funcion, char variable, int derivada) {
        Operando o = interpreteStringSimplificado(funcion);
        while (--derivada > -1 && (o.IsNumero() ? o.getNumero() != 0 : true)) {
            o.obtenerDerivadaOptima(variable);
        }
        return o.toString();
    }

    public static double[] Newton_Raphson(String funcion, double a, double b, double errorPrefijado) {
        double respuesta[] = {-1, 0};
        String funcion1derivada = derivada(funcion, 'x', 1), funcion2derivada = derivada(funcion, 'x', 2);
        if (esFuncionEnX(funcion)) {
            double x0 = funcionEnX(funcion, a) * funcionEnX(funcion2derivada, a) > 0 ? a : b, x1 = x0, error = 1000000;
            do {
                if (respuesta[1] > 1000) {
                    System.out.println("infinito");
                    return respuesta;
                }
                respuesta[1]++;
                x0 = Newton_RaphsonX0(funcion, funcion1derivada, x1);
                error = Math.abs(x0 - x1);
                x1 = x0;
            } while (error > errorPrefijado);
            respuesta[0] = x0;
        }
        return respuesta;
    }

    public static double Newton_RaphsonX0(String funcion, double valor) {
        return Newton_RaphsonX0(funcion, derivada(funcion, 'x', 1), valor);
    }

    public static double Newton_RaphsonX0(String funcion, String funcion1derivada, double valor) {
        return interpreteStringSimplificado(valor + "-(" + funcionEnX(funcion, valor) + ")/(" + funcionEnX(funcion1derivada, valor) + ")").getNumero();

    }

    public static double[] regulaFalsi(String funcion, double a, double b, double errorPrefijado) {
        double respuesta[] = {-1, 0};
        if (esFuncionEnX(funcion)) {
            double x1 = 1000000000, x0 = -1, error = 1000000;
            do {
                if (respuesta[1] > 1000) {
                    System.out.println("infinito");
                    return respuesta;
                }
                respuesta[1]++;
                x0 = regulaFalsiX0(funcion, a, b);
                System.out.println("x0=" + x0);
                error = respuesta[1] != 1 ? Math.abs(x0 - x1) : 100000;
                System.out.println("error=" + error);
                double funcionEnX = funcionEnX(funcion, x0);
                if (funcionEnX == 0) {
                    break;
                } else {

                    if (funcionEnX(funcion, a) * funcionEnX < 0) {
                        b = x0;
                    } else {
                        a = x0;
                    }
                    System.out.println("a=" + a + " b=" + b);
                }
                x1 = x0;
            } while (error > errorPrefijado);
            respuesta[0] = x0;
        }
        return respuesta;
    }

    public static double regulaFalsiX0(String funcion, double a, double b) {
        // System.out.println("a="+a+"  b="+b);
        // Operando fa = interpreteStringSimplificado(sustituirVariableString(funcion, 'x', a));
        // System.out.println("fa="+fa);
        //   Operando fb = interpreteStringSimplificado(sustituirVariableString(funcion, 'x', b));
        // System.out.println("fb="+fb);
        // Numero aa=new Numero(a);
        //  System.out.println("aa="+aa);
        //Operando o=Suma.inicializarRestando(aa, new Multiplicacion(fa, new FraccionGeneral(Suma.inicializarRestando(b, a), Suma.inicializarRestando(fb, fa))));
        //   Operando o=Suma.inicializarRestando(new Numero(a), new Multiplicacion(new Numero(funcionEnX(funcion, a)), new FraccionGeneral(Suma.inicializarRestando(b, a), Suma.inicializarRestando(new Numero(funcionEnX(funcion, b)), new Numero(funcionEnX(funcion, a))))));
        //  System.out.println("o3="+o);
        //  o.simplificar();
        // o=o.getOperandoOptimizado();
        //  System.out.println("o2="+o);
        // System.out.println("o2.getClass()="+o.getClass().getSimpleName());
        //  return   o.getNumero();
        double fa = funcionEnX(funcion, a), fb = funcionEnX(funcion, b);
        return interpreteStringSimplificado(a + "-" + fa + "(" + b + "-" + a + ")/(" + fb + "-" + fa + ")").getNumero();
    }

    public static double funcionEnX(String funcion, double valor) {
        return funcionEn(funcion, 'x', valor);
    }

    public static double funcionEn(String funcion, char X, double valor) {
        return interpreteStringSimplificado(sustituirVariableString(funcion, X, valor)).getNumero();
    }

    /**
     * devuelve un double[] donde [0] es la raiz y [1] la cantidad de
     * iteraciones que fueron nesesarias, En caso de que el string pasado no sea
     * una funcion donde con la sustitucion de 'x' por cualquier Numero su
     * imagen no sea otro numero devuelve {-1,0}
     *
     * @param funcion String ejemplo x^2-x+2 (solo una variable que tiene que
     * ser 'x')
     * @param a double extremo isquierdo
     * @param b double extremo derecho
     * @param errorPrefijado double error prefijado
     * @return { raiz , iteraciones }
     */
    public static double[] biseccion(String funcion, double a, double b, double errorPrefijado) {
        double respuesta[] = {-1, 0};
        //System.out.println("sustituirVariableString(funcion, 'x', 2)="+sustituirVariableString(funcion, 'x', 2));
        // if (interpreteStringSimplificado(sustituirVariableString(funcion, 'x', 2)).IsNumero()) {
        if (esFuncionEnX(funcion)) {
            double error = -1, x0 = -1;
            do {
                if (respuesta[1] > 1000) {
                    System.out.println("infinito");
                    return respuesta;
                }
                respuesta[1]++;
                x0 = (a + b) / 2;
                // System.out.println("x0="+x0+" n="+respuesta[1]);
                error = (b - a) / 2;

                //double funcionEnX = interpreteStringSimplificado(sustituirVariableString(funcion, 'x', x0)).getNumero();
                double funcionEnX = funcionEnX(funcion, x0);

                if (funcionEnX == 0) {
                    break;
                } else {
                    //  if (interpreteStringSimplificado(sustituirVariableString(funcion, 'x', a)).getNumero() * funcionEnX < 0) {
                    if (funcionEnX(funcion, a) * funcionEnX < 0) {
                        b = x0;
                    } else {
                        a = x0;
                    }
                }

            } while (error > errorPrefijado);
            respuesta[0] = x0;
        }

        return respuesta;
    }

    public static boolean esFuncionEnX(String funcion) {
        return interpreteStringSimplificado(sustituirVariableString(funcion, 'x', 2)).IsNumero();
    }

    public static int max(int... A) {
        return ((Integer) Collections.max(new ArrayList(Arrays.asList(A))));
    }

    public static double max(double... A) {
        return ((Double) Collections.max(new ArrayList(Arrays.asList(A)))).doubleValue();
    }

    public static int min(int... A) {
        return ((Integer) Collections.min(new ArrayList(Arrays.asList(A)))).intValue();
    }

    public static double min(double... A) {
        return ((Double) Collections.min(new ArrayList(Arrays.asList(A)))).doubleValue();
    }

    public static double sumaD(ArrayList<Double> a) {
        return suma(convertirArregloAdouble(a.toArray(new Double[0])));
    }

    public static double sumaF(ArrayList<Float> a) {
        return suma(convertirArregloAdouble(a.toArray(new Float[0])));
    }

    public static double sumaI(ArrayList<Integer> a) {
        return suma(convertirArregloAdouble(a.toArray(new Integer[0])));
    }

    public static double sumaI(Integer... a) {
        return suma(convertirArregloAdouble(a));
    }

    public static double sumaF(Float... a) {
        return suma(convertirArregloAdouble(a));
    }

    public static double sumaD(Double... a) {
        return suma(convertirArregloAdouble(a));
    }

    public static int suma(int... a) {
        int suma = 0;
        for (double b : a) {
            suma += b;
        }
        return suma;
    }

    public static float suma(float... a) {
        float suma = 0;
        for (double b : a) {
            suma += b;
        }
        return suma;
    }

    public static double suma(double... a) {
        double suma = 0;
        for (double b : a) {
            suma += b;
        }
        return suma;
    }

    public static double restaD(ArrayList<Double> a) {
        return resta(convertirArregloAdouble(a.toArray(new Double[0])));
    }

    public static double restaF(ArrayList<Float> a) {
        return resta(convertirArregloAdouble(a.toArray(new Float[0])));
    }

    public static double restaI(ArrayList<Integer> a) {
        return resta(convertirArregloAdouble(a.toArray(new Integer[0])));
    }

    public static double resta(Integer... a) {
        return resta(convertirArregloAdouble(a));
    }

    public static double resta(Float... a) {
        return resta(convertirArregloAdouble(a));
    }

    public static double resta(Double... a) {
        return resta(convertirArregloAdouble(a));
    }

    public static double resta(double... a) {
        double resta = 0;
        for (double b : a) {
            resta -= b;
        }
        return resta;
    }

    public static double multiplicacionD(ArrayList<Double> a) {
        return multiplicacion(convertirArregloAdouble(a.toArray(new Double[0])));
    }

    public static double multiplicacionF(ArrayList<Float> a) {
        return multiplicacion(convertirArregloAdouble(a.toArray(new Float[0])));
    }

    public static double multiplicacionI(ArrayList<Integer> a) {
        return multiplicacion(convertirArregloAdouble(a.toArray(new Integer[0])));
    }

    public static double multiplicacion(Integer... a) {
        return multiplicacion(convertirArregloAdouble(a));
    }

    public static double multiplicacion(Float... a) {
        return multiplicacion(convertirArregloAdouble(a));
    }

    public static double multiplicacion(Double... a) {
        return multiplicacion(convertirArregloAdouble(a));
    }

    public static double multiplicacion(double... a) {
        double multiplicacion = 0;
        for (double b : a) {
            multiplicacion *= b;
        }
        return multiplicacion;
    }

    public static double divicionD(ArrayList<Double> a) {
        return divicion(convertirArregloAdouble(a.toArray(new Double[0])));
    }

    public static double divicionF(ArrayList<Float> a) {
        return divicion(convertirArregloAdouble(a.toArray(new Float[0])));
    }

    public static double divicionI(ArrayList<Integer> a) {
        return divicion(convertirArregloAdouble(a.toArray(new Integer[0])));
    }

    public static double divicion(Integer... a) {
        return divicion(convertirArregloAdouble(a));
    }

    public static double divicion(Float... a) {
        return divicion(convertirArregloAdouble(a));
    }

    public static double divicion(Double... a) {
        return divicion(convertirArregloAdouble(a));
    }

    public static double divicion(double... a) {
        double divicion = 0;
        for (double b : a) {
            divicion /= b;
        }
        return divicion;
    }

    public static String decimalAFraccionString(double numero) {
//        double operadores[] = {numero * decimo(numero), decimo(numero)}; //[0]=numerador   [1]=denominador
//        return (int) operadores[0] / maximoComunDivisor(operadores) + "/" + (int) operadores[1] / maximoComunDivisor(operadores);
        int fraccion[] = decimalAFraccionInt(numero);
        return fraccion[0] + "/" + fraccion[1];
    }

    public static int[] simplificarFraccion(double numerador, double denominador) {
        return simplificarFraccion((int) numerador, (int) denominador);
    }

    public static int[] simplificarFraccion(int numerador, int denominador) {
        //  System.out.println("numerador=" + numerador + " denominador=" + denominador);
        int fraccion[] = {numerador, denominador};
        if (esPrimo(numerador) && esPrimo(denominador)) {
            return fraccion;
        }
        boolean mayorNumerador = numerador > denominador;
        if (esPrimo(numerador)) {
            if (denominador % numerador == 0) {
                fraccion[0] = 1;
                fraccion[1] = denominador / numerador;
                //System.out.println(Arrays.toString(fraccion));
                return fraccion;
            } else {
                return fraccion;
            }

//        if(mayorNumerador){
//        if(numerador%denominador==0){
//        
//        }else{}
//        }else{}
        }

        if (esPrimo(denominador)) {
            if (numerador % denominador == 0) {
                fraccion[0] = numerador / denominador;
                fraccion[1] = 1;
                return fraccion;
            } else {
                return fraccion;
            }

        }
        double A[] = {fraccion[0], fraccion[1]};
        int a = maximoComunDivisor(A);
        if (a != 1) {
            fraccion[0] /= a;
            fraccion[1] /= a;

        }

        return fraccion;

        //return decimalAFraccionInt((double)numerador/(double)denominador);
    }

    public static int[] decimalAFraccionInt(double numero) {
//        System.out.println("numero="+numero);
        if (lugaresDespuesDeLaComa(numero) > 6) {
            for (int i = 1; i < 1000; i++) {
                for (int j = 1; j < 1000; j++) {
                    if ((double) i / (double) j == numero) {
                        int operadores[] = {i, j};
                        // System.out.println("i=" + i + " j=" + j);
                        return operadores;
                    }
                }
            }

            int operadores[] = {(int) (numero * decimo(numero)), decimo(numero)};
            return operadores;

        }

        // System.out.println("decimo(numero)=" + decimo(numero));
        // System.out.println("numero * decimo(numero)=" + numero * decimo(numero));
        double operadores[] = {numero * decimo(numero), decimo(numero)}; //[0]=numerador   [1]=denominador
        //System.out.println("maximoComunDivisor(operadores)=" + maximoComunDivisor(operadores));
        int fraccion[] = {(int) operadores[0] / maximoComunDivisor(operadores), (int) operadores[1] / maximoComunDivisor(operadores)};
        return fraccion;
    }

//    public static double decimo2(double numero) {
//    String a="1";
//    int lugaresDespuesDeLacoma=lugaresDespuesDeLaComa(numero);
//        while(lugaresDespuesDeLacoma-->0){
//        a+="0";
//        }
//        return Double.parseDouble(a);
//    }
    //entrado un numero con coma debuelbe el numero de 10 por el que
    //tine que multiplicarse para volbrese entero
    public static int decimo(double numero) {
        int lugares = lugaresDespuesDeLaComa(numero);
        // System.out.println("lugares=" + lugares);
        // System.out.println("Math.pow(10, lugaresDespuesDeLaComa(numero)=" + Math.pow(10, lugaresDespuesDeLaComa(numero)));
        return (int) (lugaresDespuesDeLaComa(numero) == 0 ? 1 : Math.pow(10, lugaresDespuesDeLaComa(numero)));
    }

    public static double log(double base, double argumento) {
        if (base < 0 || argumento < 0 || base == 1) {
            throw new ArithmeticException();
        }
        return Math.log(argumento) / Math.log(base);
    }

    public static String dividirDosFracciones(int numerador1, int denominador1, int numerador2, int denominador2) {
        String fraccionResultante = "";
        fraccionResultante = numerador1 * denominador2 + "/" + denominador1 * numerador2;
        return fraccionResultante;
    }

    public static String multiplicarDosFracciones(int numerador1, int denominador1, int numerador2, int denominador2) {
        String fraccionResultante = "";

        fraccionResultante = numerador1 * numerador2 + "/" + denominador1 * denominador2;
        return fraccionResultante;
    }

    public static String restarDosFracciones(int numerador1, int denominador1, int numerador2, int denominador2) {
        String fraccionResultante = "";
        int numerador = minimoComunMultiplo(denominador1, denominador2) / denominador1 * numerador1 - minimoComunMultiplo(denominador1, denominador2) / denominador2 * numerador2;
        int denominador = minimoComunMultiplo(denominador1, denominador2);
        fraccionResultante = numerador + "/" + denominador;
        return fraccionResultante;
    }

    public static String sumarDosFracciones(int numerador1, int denominador1, int numerador2, int denominador2) {
        String fraccionResultante = "";
        int numerador = minimoComunMultiplo(denominador1, denominador2) / denominador1 * numerador1 + minimoComunMultiplo(denominador1, denominador2) / denominador2 * numerador2;
        int denominador = minimoComunMultiplo(denominador1, denominador2);
        fraccionResultante = numerador + "/" + denominador;
        return fraccionResultante;
    }

    public static String simplificarFraccion(String fraccion) {
        String fraccionSimplificada = "";
        boolean simplificar = true;
        int numerador = adquirirFraccion(fraccion)[0];
        int denominador = adquirirFraccion(fraccion)[1];
        //[0]n , [1]d
        if (numerador * numerador != 1) {
            if (numerador % denominador != 0) {
                if (denominador % numerador != 0) {
                    for (int i = 1000; i > 1 & simplificar == true; i--) {
                        if (numerador % i == 0 & denominador % i == 0) {
                            fraccionSimplificada += numerador / i + "/" + denominador / i;

                            simplificar = false;
                        }
                    }
                } else {
                    fraccionSimplificada += "1/" + denominador / numerador;
                }
            } else {

                fraccionSimplificada += numerador / denominador;
            }
        } else {
            fraccionSimplificada += fraccion;
        }
        simplificar = true;

        return fraccionSimplificada;
    }
    //devuelbe un arregle de 2 int done [0]=numerador y en [1]=denominador

    public static int[] adquirirFraccion(String fraccion) {
        int A[] = new int[2];
        String numero = "";
        String carater = "";
        int numerador = 0;
        int denominador = 1;
        for (int i = 0; i < fraccion.length(); i++) {
            carater = fraccion.charAt(i) + "";

            if (carater.equals("/")) {
                numerador = Integer.parseInt(numero);
                numero = "";

            } else if (carater.equals(",")) {
                numero += ".";
            } else {
                numero += fraccion.charAt(i);
            }

        }
        if (numerador == 0) {
            numerador = Integer.parseInt(numero);
        } else {
            denominador = Integer.parseInt(numero);
        }
        A[0] = numerador;
        A[1] = denominador;
        return A;
    }

    public static int[] convertirArregloDoubleAInt(double A[]) {
        int B[] = new int[A.length];
        for (int i = 0; i < A.length; i++) {
            B[i] = (int) A[i];
        }
        return B;
    }

    public static int maximoComunDivisor(double... A) {
        return maximoComunDivisor(convertirArregloDoubleAInt(A));
    }

    public static int maximoComunDivisor(int... A) {
        int menor = Collections.min(Arrays.asList(convertirArregloAObjetoInteger(A)));
        //System.out.println("menor="+menor);
        for (int i = 0; i < A.length && A[i] % menor == 0; i++) {
            if (i == A.length - 1) {
                return menor;
            }
        }

        int primos[] = primos(menor / 2);                        //System.out.println("primos="+Arrays.toString(primos));

        for (int i = 0; i < primos.length; i++) { //System.out.println("primo="+primos[i]); 
            //System.out.println("menor/primos[i]="+menor/primos[i]);
//            System.out.println("menor%primos[i]==0 "+(menor%primos[i]==0));
//            for (int j = 0; j < A.length&&menor%primos[i]==0; j++) {
//                System.out.println("A[j]="+A[j]+" A[j]/(menor/primos[i])="+A[j]/(menor/primos[i]));
//                boolean a=A[j]%(menor/primos[i])==0; System.out.println("a="+a);
//               if(!a)break;
//                if(j==A.length-1){
//                return (menor/primos[i]);
//                }
//            }

            for (int j = 0; j < A.length && menor % primos[i] == 0 && A[j] % (menor / primos[i]) == 0; j++) {
                if (j == A.length - 1) {
                    return (menor / primos[i]);
                }
            }
        }

//        for (int numero : A) {
//                                              System.out.println("mc numero=" + numero);
//            menor = numero < menor ? numero : menor;
//        }
//                                                 System.out.println("menor=" + menor);
//        for (int i =  menor; i >= 2; i--) {
//                                                  System.out.println("mc i=" + i);
//            for (int j = 0; j < A.length & A[j] % i == 0; j++) {
//                                                 System.out.println("mc j=" + j);
//                if (j == A.length - 1) {
//                                               System.out.println(" retun i=" + i);
//                    return i;
//                }
//            }
//        }
        return 1;
    }

    public static int minimoComunDivisor(double A[]) {
        double mayor = A[0];
        for (double numero : A) {
            mayor = numero > mayor ? numero : mayor;
        }
        for (int i = 2; i <= mayor; i++) {
            for (int j = 0; j < A.length & A[j] % i == 0; j++) {
                if (j == A.length - 1) {
                    return i;
                }
            }
        }
        return 0;
    }

    public static int minimoComunMultiplo(int numero1, int numero2) {
        int mcm = 1;
        int A[] = new int[20];
        int B[] = new int[20];
        A = pasoMinimoComunMultiplo(numero1, numero1, numero2);
        B = pasoMinimoComunMultiplo(numero2, numero1, numero2);
        if (A[0] == 0) {//System.out.println(" A[1]="+A[1]+" B[1]="+B[1]+" A[2]="+A[2]+" B[2]="+B[2]+" A[3]="+A[3]+" B[3]="+B[3]+" A[4]="+A[4]+" B[4]="+B[4]);
            //System.out.println("2= "+(int)Math.pow(2,A[1]>B[1]?A[1]:B[1])+" 3="+(int)Math.pow(3,A[2]>B[2]?A[2]:B[2])+" 5="+(int)Math.pow(5,A[3]>B[3]?A[3]:B[3])+" 7="+(int)Math.pow(7,A[4]>B[4]?A[4]:B[4]));
            mcm = (int) Math.pow(2, A[1] > B[1] ? A[1] : B[1]) * (int) Math.pow(3, A[2] > B[2] ? A[2] : B[2]) * (int) Math.pow(5, A[3] > B[3] ? A[3] : B[3]) * (int) Math.pow(7, A[4] > B[4] ? A[4] : B[4]);
            for (int p1 = 1; p1 <= A[5]; p1++) {//System.out.println("A"+(5+p1)+"="+A[5+p1]+" *"+mcm);
                mcm *= A[5 + p1]; //System.out.println("mcm="+mcm);
            }
            for (int p2 = 1; p2 <= B[5]; p2++) {//System.out.println("B"+(5+p2)+"="+B[5+p2]+" *"+mcm);
                mcm *= B[5 + p2]; //System.out.println("mcm="+mcm);
            }
        } else {
            mcm = A[0];
        }
        return mcm;
    }

    private static int[] pasoMinimoComunMultiplo(int numero, int numero1, int numero2) {
        int A[] = new int[20];  //A[0] 1 o no ,A[1] c2 ,A[2] c3,A[3] c5,A[4] c7,A[5] cp

        int lugar = 6;

        if (numero1 == 1) {
            A[0] = numero2;
        }
        if (numero2 == 1) {
            A[0] = numero1;
        }

        if (A[0] == 0) {
            while (numero != 1) {
                if (numero % 2 != 0) {
                    if (numero % 3 != 0) {
                        if (numero % 5 != 0) {
                            if (numero % 7 != 0) {
                                A[lugar] = numero;
                                numero /= numero;
                                lugar++;
                                ++A[5];
                            } else {
                                numero /= 7;
                                A[4]++;
                            }
                        } else {
                            numero /= 5;
                            A[3]++;
                        }
                    } else {
                        numero /= 3;
                        A[2]++;
                    }
                } else {
                    numero /= 2;
                    A[1]++;
                }

            }

        }

        return A;
    }
    //promedio

    public static double promedio(double A[]) {
        double total = 0;
        for (double a : A) {
            total += a;
        }
        return total / A.length;
    }

    //simplifica una raiz cuadrada
    public static void simplificarRaiz(int a) {
        int primos[] = new int[29];
        int lugar = 2;
        primos[0] = 2;
        primos[1] = 3;
        primos[2] = 5;
        for (int i = 6; i < 100; i++) {
            if (((i - 1) % 4 == 0 | (i - 3) % 4 == 0) && ((i - 1) % 6 == 0 | (i + 1) % 6 == 0) && i % 5 != 0) {
                lugar++;
                primos[lugar] = i;
            }
        }
        int c = 0;
        int A = 1;
        int B = 1;
        for (int i = 0; a > 1 && i < primos.length; i++) {
            while (a % primos[i] == 0) {
                c++;
                if (c == 2) {
                    A *= primos[i];
                    c = 0;
                }
                a = a / primos[i];
            }
            if (c != 0) {
                B *= primos[i];
                c = 0;
            }
        }
        System.out.println(A + "*sqrt(" + B + ")");
    }

    public static double mediaAritmetica(int a, int b) {
        int Mayor = Math.max(a, b);
        int Menor = Math.min(a, b);
        double total = 0;
        for (int i = Mayor; i <= Menor; i++) {
            total += i;
        }
        return total / (double) (Mayor - Menor + 1);
    }

//Matrizes
    public static double[][] SumarDosMatrizes(double A[][], double B[][]) {
        double C[][] = new double[A.length][A[0].length];
        for (int f = 0; f < A.length; f++) {
            for (int c = 0; c < A[f].length; c++) {
                C[f][c] = A[f][c] + B[f][c];
            }
        }
        return C;
    }

    public static double[][] RestarDosMatrizes(double A[][], double B[][]) {
        double C[][] = new double[A.length][A[0].length];
        for (int f = 0; f < A.length; f++) {
            for (int c = 0; c < A[f].length; c++) {
                C[f][c] = A[f][c] - B[f][c];
            }
        }
        return C;
    }

    public static double[][] MatrizInversa(double A[][]) {
        double adjunta[][] = new double[A[0].length][A.length];
        double determinante = Determinante(A);
        double inversa[][] = new double[A[0].length][A.length];
        adjunta = MatrizAdjunta(A);
        for (int f = 0; f < A[0].length; f++) {
            for (int c = 0; c < A.length; c++) {
                inversa[f][c] = adjunta[f][c] * 1 / determinante;
            }
        }

        return inversa;
    }

    public static double[][] MatrizAdjunta(double A[][]) {

        double adjunta[][] = new double[A[0].length][A.length];
        if (A.length == 2 | A[0].length == 2) {
            adjunta[0][0] = A[1][1];
            adjunta[1][1] = A[0][0];
            adjunta[0][1] = -A[0][1];
            adjunta[1][0] = -A[1][0];
        } else {
            adjunta = MatrizTraspuesta(MatrizCofactor(A));
        }
        return adjunta;
    }

    public static double[][] MatrizTraspuesta(double A[][]) {
        double[][] traspuesta = new double[A[0].length][A.length];
        for (int f = 0; f < A.length; f++) {
            for (int c = 0; c < A[0].length; c++) {
                traspuesta[c][f] = A[f][c];
            }
        }
        return traspuesta;
    }

    public static double[][] MatrizCofactor(double A[][]) {
        double MatrizCofactor[][] = new double[A.length][A[0].length];
        for (int f = 0; f < A.length; f++) {
            for (int c = 0; c < A[0].length; c++) {
                MatrizCofactor[f][c] = Cofactor(A, f, c);
            }
        }

        return MatrizCofactor;
    }

    public static double Determinante(double[][] A) {
        int cerosc = 0;
        int cerosf = 0;
        int contadorc = 0;
        int contadorf = 0;
        int igualesf = 0;
        int igualesc = 0;
        if (A.length == 2 && A[0].length == 2) {
            return A[0][0] * A[1][1] - A[1][0] * A[0][1];
        }
        if (A.length == 3 && A[0].length == 3) {
            return A[0][0] * A[1][1] * A[2][2] + A[1][0] * A[2][1] * A[0][2]
                    + A[0][1] * A[1][2] * A[2][0] - A[2][0] * A[1][1] * A[0][2]
                    - A[1][0] * A[0][1] * A[2][2] - A[2][1] * A[1][2] * A[0][0];
        }
        //
        if (A.length == A[0].length) {
            for (int f = 0; f < A.length; f++) {
                if (f != A.length - 1) {
                    for (int f2 = f + 1; f2 < A.length; f2++) {
                        for (int c = 0; c < A[0].length; c++) {
                            if (A[f][c] == A[f2][c]) {
                                contadorf++;
                            }
                            if (contadorf == A[0].length) {
                                return 0;
                            }
                        }
                        contadorf = 0;
                    }
                }
            }
        }
        //***
        if (A.length == A[0].length) {
            for (int c = 0; c < A[0].length; c++) {
                if (c != A[0].length - 1) {
                    for (int c2 = c + 1; c2 < A[0].length; c2++) {
                        for (int f = 0; f < A.length; f++) {
                            if (A[f][c] == A[f][c2]) {
                                contadorc++;
                            }
                            if (contadorc == A[0].length) {
                                return 0;
                            }
                        }
                        contadorc = 0;
                    }
                }
            }
        }
        //
        if (A.length == A[0].length) {
            for (int f = 0; f < A.length; f++) {
                for (int c = 0; c < A[0].length; c++) {
                    if (f == 0) {
                        for (int f2 = 0; f2 < A[0].length; f2++) {
                            if (f2 != 0 && A[f2][c] == A[f2 - 1][c]) {
                                contadorf++;
                            }
                            if (f2 == A[0].length - 1 && contadorf == A[0].length - 1) {
                                igualesf++;
                                if (igualesf == 2) {
                                    return 0;
                                }
                            }
                        }
                        contadorf = 0;
                    }
                    if (f > c) {
                        cerosf += A[f][c];
                    }
                    if (c > f) {
                        cerosc += A[f][c];
                    }
                    if (c != 0) {
                        if (A[f][c] == A[f][c - 1]) {
                            contadorc++;
                        }
                    }
                    if (c == A[0].length - 1 && contadorc == A[0].length - 1) {
                        igualesc++;
                        if (igualesc == 2) {
                            return 0;
                        }
                    }
                }
                contadorc = 0;
            }
            if (cerosf == 0 || cerosc == 0) {
                double determinante = 1;
                for (int c = 0; c < A[0].length; c++) {
                    determinante *= A[c][c];
                }
                return determinante;
            }
        }
        double determinante = 0;
        for (int c = 0; c < A[0].length; c++) {
            determinante += A[0][c] * Cofactor(A, 0, c);
        }
        return determinante;
    }

    public static double Cofactor(double A[][], int fila, int columna) {
        //vvvvvvvvvvvvvvvv
//        Operaciones o = new Operaciones();
//        return Math.pow(-1, fila + columna) * o.Determinante(o.MatrizMenor(A, fila, columna));
        //vvvvvvvvvvvvvvv
        return Math.pow(-1, fila + columna) * Determinante(MatrizMenor(A, fila, columna));

    }

    public static double[][] MatrizMenor(double A[][], int fila, int columna) {
        double MatrizMenor[][] = new double[A.length - 1][A[0].length - 1];
        int cf = 0;
        int cc = 0;
        for (int c = 0; c < A[0].length - 1; c++) {
            if (c == columna) {
                cc++;
            }
            for (int f = 0; f < A.length - 1; f++) {
                if (f == fila) {
                    cf++;
                }
                MatrizMenor[f][c] = A[f + cf][c + cc];
            }
            cf = 0;
        }
        return MatrizMenor;
    }

    public static double[][] MatrizMultiplicacionPorEscalar(double A[][], double b) {
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[0].length; j++) {             // System.out.println("b="+b); System.out.println("A[i][j]="+A[i][j]);
                A[i][j] = A[i][j] * b;                                 //System.out.println("en "+i+j+"es "+A[i][j]);
            }
        }
        return A;
    }

    public static double[][] MultiplicarDosMatrizes(double A[][], double B[][]) {
        double s = 0;
        double V[][] = new double[A.length][B[0].length];
        for (int f1 = 0; f1 < A.length; f1++) {
            for (int c2 = 0; c2 < B[0].length; c2++) {
                for (int E = 0; E < B.length; E++) {
                    s += A[f1][E] * B[E][c2];
                }
                V[f1][c2] = s;
                s = 0;
            }
        }
        return V;
    }

    public static double[] MultiplicarMatrizPorVector(double A[][], double B[]) {
        return convertirArrgloAAUnaColumnaEnUnaFila(MultiplicarDosMatrizes(A, convertirUnaFilaEnUnaColumna(B)));
    }

    //*********
    public static double factorial(double numero) {
        if (numero == 0) {
            return 1;
        }
        return factorial(numero - 1) * numero;
    }

    public static double raiz(double numero1, double numero2) {
        return regulador(Math.pow(numero1, 1 / numero2));
    }

    public static double sin(double numero) {
        return regulador(Math.sin(radianesAbinario(numero)));
    }

    public static double cos(double numero) {
        return regulador(Math.cos(radianesAbinario(numero)));
    }

    public static double tan(double numero) {
        return regulador(Math.tan(radianesAbinario(numero)));
    }

    public static double cot(double numero) {
        return (1 / regulador(Math.tan(radianesAbinario(numero))));
    }

    public static double csc(double numero) {
        return (1 / regulador(Math.sin(radianesAbinario(numero))));
    }

    public static double sec(double numero) {
        return (1 / regulador(Math.cos(radianesAbinario(numero))));
    }

    public static double sqrt(double numero) {
        return Math.sqrt(numero);
    }

    public static double sqrt(double indice, double base) {
        return regulador(Math.pow(base, 1 / indice));
    }

    public static double radianesAbinario(double numero) {
        return numero * Math.PI / 180;
    }


    /*
     Pi ( ~ ) Euler ( E )  + - / * () ^ sqr[]() sen() cos() tan() cot() csc() sec() lnx()
     */
    public static double calcular(String operacion) {
        return calculo(operacion)[0];
    }

    private static double[] calculo(String operacion) {

//        Enunciados e = new Enunciados();
        //Revision r = new Revision();
        String suboperacion = "";
        double resultado[] = new double[2];
        int contadorSalto = 0;
        int contadorParentesis = 0;
        String numero = "";
        double operador1 = 0;
        double operador2 = 0;
        String sinParentesis = "";
        String sinMyD = "";
        String respuesta = "";

        for (int i = 0; i < operacion.length(); i++) {
            if (contadorSalto-- != 0) {
                continue;
            } else {
                contadorSalto = 0;
            }
            if ('(' == operacion.charAt(i)) {
                if (i != 0 && operacion.charAt(i - 1) != '*' && operacion.charAt(i - 1) != '/'
                        && operacion.charAt(i - 1) != '+' && operacion.charAt(i - 1) != '-' && operacion.charAt(i - 1) != ')'
                        && operacion.charAt(i - 1) != '[' && operacion.charAt(i - 1) != ']' && operacion.charAt(i - 1) != '^'
                        && (operacion.charAt(i - 1) >= 97 && operacion.charAt(i - 1) <= 122 ? false : true)) {
                    sinParentesis += "*";
                }
                for (int j = i + 1;; j++) {

                    if (operacion.charAt(j) == '(') {
                        contadorParentesis++;
                    }
                    if (operacion.charAt(j) == ')') {
                        contadorParentesis--;
                    }
                    contadorSalto++;
                    suboperacion += operacion.charAt(j);
                    if (contadorParentesis == 0 && operacion.charAt(j + 1) == ')') {
                        break;
                    }
                }
                contadorSalto++;
                // Operaciones o = new Operaciones();
                double A[] = new double[2];
                A = calculo(suboperacion);
                suboperacion = "";
                if (A[1] == 1) {
                    resultado[1] = 1;
                    return resultado;
                } else {
                    if (sinParentesis.length() == 1 && sinParentesis.charAt(0) == '-') {
                        sinParentesis += "1*";
                    }
                    if (sinParentesis.length() > 1 && sinParentesis.charAt(sinParentesis.length() - 1) == '-') {
                        String trasformado = "";
                        for (int j = 0; j < sinParentesis.length() - 1; j++) {
                            trasformado += sinParentesis.charAt(j) + "";
                        }
                        sinParentesis = trasformado + "+-1*";
                    }
                    sinParentesis += A[0];
                    if (i + contadorSalto != operacion.length() - 1 && operacion.charAt(i + contadorSalto + 1) != '*' && operacion.charAt(i + contadorSalto + 1) != '/'
                            && operacion.charAt(i + contadorSalto + 1) != '+' && operacion.charAt(i + contadorSalto + 1) != '-' && operacion.charAt(i + contadorSalto + 1) != ')'
                            && operacion.charAt(i + contadorSalto + 1) != ']') {
                        sinParentesis += "*";
                    }
                }
            } else {
                sinParentesis += operacion.charAt(i);
            }
        }
        //modifiacar
        if (errores(sinParentesis) == true) {
            resultado[1] = 1;
            return resultado;
        }
        for (int i = 0; i < sinParentesis.length(); i++) {

            if (contadorSalto-- != 0) {
                continue;
            } else {
                contadorSalto = 0;
            }
            double B[];
            if (i != sinParentesis.length() - 1 && sinParentesis.charAt(i) == '^') {
                B = Operaciones.numero(sinParentesis, i, 1);
                contadorSalto += B[1];
                respuesta += regulador(Math.pow(Double.parseDouble(numero), B[0]));
                numero = "";
                continue;
            }
            if (i < sinParentesis.length() - 4) {
                String caracteres3 = "";
                int salto = 0;
                for (int j = 0; j < 3; j++) {
                    caracteres3 += sinParentesis.charAt(i + j);
                }

                switch (caracteres3) {
                    //4-3
                    case "lnx":
                        contadorSalto += 2;
                        B = Operaciones.numero(sinParentesis, i, 3);
                        contadorSalto += (int) B[1];
                        if (numero != "") {
                            respuesta += numero + Math.log(B[0]);
                        } else {
                            respuesta += Math.log(B[0]);
                        }

                        continue;
                    case "csc":
                        contadorSalto += 2;
                        B = Operaciones.numero(sinParentesis, i, 3);
                        contadorSalto += (int) B[1];
                        if (numero != "") {
                            respuesta += numero + (1 / Operaciones.regulador(Math.sin(B[0] * Math.PI / 180)));
                        } else {
                            respuesta += (1 / Operaciones.regulador(Math.sin(B[0] * Math.PI / 180)));
                        }

                        continue;
                    case "sec":
                        contadorSalto += 2;
                        B = Operaciones.numero(sinParentesis, i, 3);
                        contadorSalto += (int) B[1];
                        if (numero != "") {
                            respuesta += numero + (1 / Operaciones.regulador(Math.cos(B[0] * Math.PI / 180)));
                        } else {
                            respuesta += (1 / Operaciones.regulador(Math.cos(B[0] * Math.PI / 180)));
                        }

                        continue;
                    case "sin":
                    case "sen":
                        contadorSalto += 2;
                        B = Operaciones.numero(sinParentesis, i, 3);
                        contadorSalto += (int) B[1];
                        if (numero != "") {
                            respuesta += numero + Operaciones.regulador(Math.sin(B[0] * Math.PI / 180));
                        } else {
                            respuesta += Operaciones.regulador(Math.sin(B[0] * Math.PI / 180));
                        }

                        continue;
                    case "cos":
                        contadorSalto += 2;
                        B = Operaciones.numero(sinParentesis, i, 3);
                        contadorSalto += (int) B[1];
                        if (numero != "") {
                            respuesta += numero + Operaciones.regulador(Math.cos(B[0] * Math.PI / 180));
                        } else {
                            respuesta += Operaciones.regulador(Math.cos(B[0] * Math.PI / 180));
                        }

                        System.out.println("contadorSalto=" + contadorSalto);
                        continue;
                    case "tan":
                        contadorSalto += 2;
                        B = Operaciones.numero(sinParentesis, i, 3);
                        contadorSalto += (int) B[1];
                        if (numero != "") {
                            respuesta += numero + Operaciones.regulador(Math.tan(B[0] * Math.PI / 180));
                        } else {
                            respuesta += Operaciones.regulador(Math.tan(B[0] * Math.PI / 180));
                        }

                        continue;
                    case "cot":
                        contadorSalto += 2;
                        B = Operaciones.numero(sinParentesis, i, 3);
                        contadorSalto += (int) B[1];
                        if (numero != "") {
                            respuesta += numero + (1 / Operaciones.regulador(Math.tan(B[0] * Math.PI / 180)));
                        } else {
                            respuesta += (1 / Operaciones.regulador(Math.tan(B[0] * Math.PI / 180)));
                        }

                        continue;
                    case "sqr":
                        contadorSalto += 2;
                        caracteres3 = "";
                        for (int j = 1; j < 4; j++) {
                            caracteres3 += sinParentesis.charAt(i + 2 + j);
                        }

                        if (caracteres3 == "[2]") {
                            //7-6
                            B = Operaciones.numero(sinParentesis, i, 6);
                            respuesta += Math.sqrt(B[0]);
                            salto = 1;
                        } else {
                            //[2.0]
                            if (caracteres3 == "[2.") {
                                B = Operaciones.numero(sinParentesis, i, 8);
                                respuesta += Math.sqrt(B[0]);
                                salto = 3;

                            } else {
                                numero = "";
                                for (int j = i + 4; sinParentesis.charAt(j) != ']'; j++) {
                                    numero += sinParentesis.charAt(j);
                                    salto++;
                                }
                                //6-5
                                B = Operaciones.numero(sinParentesis, i, salto + 5);
                                respuesta += Operaciones.regulador(Math.pow(B[0], 1 / Double.parseDouble(numero)));
                                numero = "";
                            }

                        }
                        contadorSalto += (int) B[1] + salto + 2;
                        continue;
                    default:
                }
            }
            if (sinParentesis.charAt(i) != '*' && sinParentesis.charAt(i) != '/' && sinParentesis.charAt(i) != '+'
                    & (sinParentesis.charAt(i) == '-' ? numero == "" : true)) {
                numero += sinParentesis.charAt(i);
            } else {
                respuesta += numero + sinParentesis.charAt(i);
                numero = "";
            }
        }
        if (numero != "") {
            respuesta += numero;
            numero = "";
        }
        sinParentesis = respuesta;
        respuesta = "";
        //Sin Parentesis
        //Multiplicacion y Divicion
        for (int i = 0; i < sinParentesis.length(); i++) {
            if (contadorSalto-- != 0) {
                continue;
            } else {
                contadorSalto = 0;
            }
            if ('*' == sinParentesis.charAt(i) | '/' == sinParentesis.charAt(i) & i != sinParentesis.length() - 1) {
                operador1 = Double.parseDouble(numero);
                numero = "";
                for (int j = i + 1; j < sinParentesis.length() && sinParentesis.charAt(j) != '*' && sinParentesis.charAt(j) != '/'
                        && sinParentesis.charAt(j) != '+'; j++) {
                    if (j == i + 1 && sinParentesis.charAt(j) == '-') {
                        numero += "-";
                        contadorSalto++;
                        continue;
                    }
                    if (sinParentesis.charAt(j) == '-') {
                        break;
                    }
                    numero += sinParentesis.charAt(j);
                    contadorSalto++;
                }
                operador2 = Double.parseDouble(numero);
                numero = "";
                if ('*' == sinParentesis.charAt(i)) {
                    numero = operador1 * operador2 + "";
                } else {
                    if (operador2 == 0) {
                        resultado[1] = 1;
                        Error_Cero();
                        return resultado;
                    } else {
                        numero = operador1 / operador2 + "";
                    }
                }
            } else {
                if ('+' == sinParentesis.charAt(i) | '-' == sinParentesis.charAt(i) && numero != "" ? true : false) {
                    sinMyD += numero + sinParentesis.charAt(i);
                    numero = "";
                } else {
                    numero += sinParentesis.charAt(i);
                }
            }
        }
        sinMyD += numero;
        numero = "";
        //Suma y Resta                

        for (int i = 0; i < sinMyD.length(); i++) {
            if (i == 0 && sinMyD.charAt(i) == '-') {
                numero += "-";
                continue;
            }
            if (contadorSalto-- != 0) {
                continue;
            } else {
                contadorSalto = 0;
            }
            if ('+' == sinMyD.charAt(i) | '-' == sinMyD.charAt(i) & i != sinMyD.length() - 1) {
                operador1 = Double.parseDouble(numero);
                numero = "";
                for (int j = i + 1; j < sinMyD.length() && '+' != sinMyD.charAt(j); j++) {
                    if (j == i + 1 && sinMyD.charAt(j) == '-') {
                        numero += "-";
                        contadorSalto++;
                        continue;
                    }
                    if (sinMyD.charAt(j) == '-') {
                        break;
                    }
                    numero += sinMyD.charAt(j);
                    contadorSalto++;
                }
                operador2 = Double.parseDouble(numero);
                numero = "";
                if ('+' == sinMyD.charAt(i)) {
                    respuesta = operador1 + operador2 + "";
                    numero = respuesta;
                } else {
                    respuesta = operador1 - operador2 + "";
                    numero = respuesta;
                }
            } else {
                numero += sinMyD.charAt(i);
            }
        }
        if (respuesta == "") {
            respuesta = numero;
        }
        resultado[0] = Double.parseDouble(respuesta);
        suboperacion = "";
        return resultado;

    }

    public static double regulador(double numero) {
        String N = numero + "";
        String n0 = "0.";
        int c9 = 0;
        int c0 = 0;
        boolean c = false;
        String N2 = "";
        for (int i = N.length() - 1; i >= 0 && c0 != 5; i--) {
            if (N.charAt(i) == '0') {
                c0++;
            } else {
                c0 = 0;
            }
        }
        if (c0 == 5) {
            //double ssss=0.0000000000000001;
            //  return Math.floor(numero);
            return Double.parseDouble((numero + "").substring(0, (numero + "").length() - 2));
        }
        c0 = 0;
        for (int i = N.length() - 1; i >= 0 && c9 != 3; i--) {
            if (N.charAt(i) == '9') {
                c9++;
            } else {
                c9 = 0;
            }
        }
        c = false;
        if (c9 == 3) {
            for (int i = 0; i < N.length() - 2; i++) {
                N2 += N.charAt(i);
            }
            for (int i = 0; i < N2.length(); i++) {
                if (N2.charAt(i) == '.') {
                    c = true;
                    continue;
                }
                if (c && i < N2.length() - 1) {
                    c0++;
                }
            }
            for (int i = 0; i < c0; i++) {
                n0 += "0";
            }
            n0 += "1";
            int ceroC;
            return Double.parseDouble(N2) + Double.parseDouble(n0);
        }
        return numero;
    }

    private static double[] numero(String a, int inicio, int salto) {
        String N = "";
        double n[] = new double[2];
        n[1] = 0;

        for (int i = inicio + salto; i < a.length() && a.charAt(i) != '*' && a.charAt(i) != '/' && a.charAt(i) != '+'
                && (a.charAt(i) == '-' ? i == inicio + salto : true); i++) {
            N += a.charAt(i);
            n[1]++;

        }
        n[0] = Double.parseDouble(N);
        return n;
    }

    // private  class Enunciados {
    private static void Error_parentecisCierre() {

        System.out.println("**  A la operacion le falto un parentecis de cierre **");
    }

    private static void Error_parentecisInicial() {

        System.out.println("*  A la operacion le falto un parentecis de apertura *");
    }

    private static void Error_coma() {

        System.out.println("****************  Escribio mas de una , **************");

    }

    private static void Error_Cero() {

        System.out.println("****  No se puede realizar una divicion por cero  ****");
    }

    private static void Error_caracterIncorrecto() {

        System.out.println("*******  Escribio un caracter no permitido  **********");

    }

    private static void Error_caracterDoble(String a, String b) {

        System.out.println("****  Escribio " + a + b + " que no es permitido *****");

    }

    private static void Error_caracterInicial(String a) {

        System.out.println("************  Comezo con solo " + a + " **************");

    }

    private static void Error_caracterFinal(String a) {

        System.out.println("****************  Termino con " + a + " **************");

    }
   // }

    //-----------
    private static String revisar(String entrada) {
        int parentesis = 0;
        int contadorComa = 0;
        String sinEspacio = "";
        for (int i = 0; i < entrada.length(); i++) {
            if (contadorComa == 2) {
                sinEspacio = "Error";
                Error_coma();
                return sinEspacio;
            }
            if (' ' != entrada.charAt(i)) {
                if (entrada.charAt(i) != '*' && entrada.charAt(i) != '/'
                        && entrada.charAt(i) != '+' && entrada.charAt(i) != '-'
                        && ' ' != entrada.charAt(i) && '(' != entrada.charAt(i) && ')' != entrada.charAt(i)
                        && '.' != entrada.charAt(i) && ',' != entrada.charAt(i) && '0' != entrada.charAt(i)
                        && '1' != entrada.charAt(i) && '2' != entrada.charAt(i) && '3' != entrada.charAt(i)
                        && '4' != entrada.charAt(i) && '5' != entrada.charAt(i) && '6' != entrada.charAt(i)
                        && '7' != entrada.charAt(i) && '8' != entrada.charAt(i) && '9' != entrada.charAt(i)) {
                    sinEspacio = "Error";
                    Error_caracterIncorrecto();
                    return sinEspacio;
                }
                if ('(' == entrada.charAt(i)) {
                    parentesis++;
                }
                if (')' == entrada.charAt(i)) {
                    if (parentesis == 0) {
                        sinEspacio = "Error";
                        Error_parentecisInicial();
                        return sinEspacio;
                    } else {
                        parentesis--;
                    }
                }
                if ('.' != entrada.charAt(i) && ',' != entrada.charAt(i) && '0' != entrada.charAt(i)
                        && '1' != entrada.charAt(i) && '2' != entrada.charAt(i) && '3' != entrada.charAt(i)
                        && '4' != entrada.charAt(i) && '5' != entrada.charAt(i) && '6' != entrada.charAt(i)
                        && '7' != entrada.charAt(i) && '8' != entrada.charAt(i) && '9' != entrada.charAt(i)) {
                    contadorComa = 0;
                }
                if ('.' == entrada.charAt(i)) {
                    contadorComa++;
                }
                if (',' == entrada.charAt(i)) {
                    contadorComa++;
                    sinEspacio += ".";
                } else {
                    sinEspacio += entrada.charAt(i);
                }
            }
        }
        if (parentesis != 0) {
            sinEspacio = "Error";
            Error_parentecisCierre();
        }
        return sinEspacio;
    }

    private static boolean errores(String operacion) {
        for (int i = 0; i < operacion.length(); i++) {
            if (i == 0 && '.' == operacion.charAt(i) | ',' == operacion.charAt(i) | '*' == operacion.charAt(i)
                    | '/' == operacion.charAt(i) | '+' == operacion.charAt(i)
                    | (i == operacion.length() - 1 && '-' == operacion.charAt(i) ? true : false)) {
                Error_caracterInicial(operacion.charAt(i) + "");
                return true;
            }
            if (i == operacion.length() - 1
                    && '.' == operacion.charAt(i) | ',' == operacion.charAt(i) | '*' == operacion.charAt(i)
                    | '/' == operacion.charAt(i) | '+' == operacion.charAt(i) | '-' == operacion.charAt(i)) {
                Error_caracterFinal(operacion.charAt(i) + "");
                return true;
            }
            if (i != operacion.length() - 1
                    && '*' == operacion.charAt(i) | '/' == operacion.charAt(i) | '+' == operacion.charAt(i) | '-' == operacion.charAt(i) | '.' == operacion.charAt(i) | ',' == operacion.charAt(i)
                    && '.' == operacion.charAt(i + 1) | ',' == operacion.charAt(i + 1) | '*' == operacion.charAt(i + 1)
                    | '/' == operacion.charAt(i + 1) | '+' == operacion.charAt(i + 1)
                    | ('.' == operacion.charAt(i) | ',' == operacion.charAt(i) ? '-' == operacion.charAt(i + 1) : false)) {
                Error_caracterDoble(operacion.charAt(i) + "", operacion.charAt(i + 1) + "");
                return true;
            }
            if (i != operacion.length() - 2
                    && '-' == operacion.charAt(i) && '-' == operacion.charAt(i + 1) && '.' == operacion.charAt(i + 2) | ',' == operacion.charAt(i + 2) | '*' == operacion.charAt(i + 2)
                    | '/' == operacion.charAt(i + 2) | '+' == operacion.charAt(i + 2) | '-' == operacion.charAt(i + 2)) {
                Error_caracterDoble(operacion.charAt(i) + "" + operacion.charAt(i + 1) + "", operacion.charAt(i + 2) + "");
                return true;
            }
        }
        return false;
    }

    public static double respuestaCola(String a) {
        return calculador(traductor(a));
    }

    public static double calculador(Cola<String> A) {
        Stack<String> B = new Stack<String>();
        try {
            while (!A.empty()) {
                if (esNumero(A.Front())) {

                    B.push(A.dequeue());

                } else {
                    String a = A.dequeue();

                    double numero2 = Double.parseDouble(B.pop());
                    switch (a) {
                        case "ln(":
                            B.push(Math.log(numero2) + "");
                            continue;
                        case "sen(":
                            B.push(sin(numero2) + "");
                            continue;
                        case "cos(":
                            B.push(cos(numero2) + "");
                            continue;
                        case "tan(":
                            B.push(tan(numero2) + "");
                            continue;
                        case "cot(":
                            B.push(cot(numero2) + "");
                            continue;
                        case "sec(":
                            B.push(sec(numero2) + "");
                            continue;
                        case "csc(":
                            B.push(csc(numero2) + "");
                            continue;
                    }

                    double numero1 = Double.parseDouble(B.pop());
                    switch (a) {
                        case "+":
                            B.push((numero1 + numero2) + "");
                            break;
                        case "-":
                            B.push((numero1 - numero2) + "");
                            break;
                        case "*":
                            B.push((numero1 * numero2) + "");
                            break;
                        case "/":
                            B.push((numero1 / numero2) + "");
                            break;
                        case "^":
                            B.push(Math.pow(numero1, numero2) + "");
                            break;
                        case "sqr[":
                            if (numero1 < 2 || !esNatural(numero1)) {
                                throw new ArithmeticException("la raiz tiene parametros incorrectos");
                            }
                            if (numero1 == 2) {
                                B.push(sqrt(numero2) + "");
                            } else {
                                B.push(sqrt(numero1, numero2) + "");
                            }
                            break;
                        case "log[":
                            if (menorQue(true, 0, numero1, numero2) || numero2 < 0) {
                                throw new ArithmeticException("el logarimo tiene parametros incorrectos");
                            }
                            B.push(log(numero1, numero2) + "");
                            break;
                    }

                }
            }
        } catch (ExedeCapacidadException ex) {
            // Logger.getLogger(Operaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Double.parseDouble(B.pop());
    }

//                double numero[]=buscarNumeroYCantidadDeCaracteresOriginal(a, i,false);
//                System.out.println("numero[0]="+numero[0]+" numero[1]="+numero[1]);
//                A.enqueue(numero[0] + "");
//                System.out.println("1 i="+i+" a.charAt(i)="+a.charAt(i));
//                i+=(int)numero[1]-1;  System.out.println("2 i="+i);
    public static Cola traductor(String a) {
        Cola<String> C = new Cola<String>();
        Stack<String> P = new Stack<String>();
        for (int i = 0; i < a.length(); i++) {
            boolean positivo = true;
            if (esSignoMenos(a, i, new ArrayList<String>(), Funcion.getInicios())) {
                positivo = false;
                //  C.enqueue("-1");
                i++;
            }

            if (esNumero(a.charAt(i) + "")) {
                double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(a, i, false);
                C.enqueue(numero[0] + "");
                addPorYMenosSiSeNecesita(a, i, positivo, C, P);
//                addMenosSiSeNecesita(C, positivo);
//                addPorSiSeNecesita(a, i, C, P);
                i += (int) numero[1] - 1;  //System.out.println("2 i="+i);
                continue;
            }
            //else {
            if (a.charAt(i) == '(') {
                C.addAll(traductor(contenidoDeParentesis(a, i)));
                addPorYMenosSiSeNecesita(a, i, positivo, C, P);
//                addMenosSiSeNecesita(C, positivo);
//                addPorSiSeNecesita(a, i, C, P);
                i = posicionFinalDelParentesis(a, i);
                continue;
            }
            int j = contieneStringAEnIndiceAArrayListStringB(i, a, Funcion.getInicios());
            if (j > -1) {
                int indices[] = posicionesDeTerminacionDe(a, Funcion.getInicios(), Funcion.getMedios(), Funcion.getFinales(), Funcion.getInicios().get(j).length() + i);
                if (Funcion.getMedios().get(j).equals("")) {
                    C.addAll(traductor(a.substring(Funcion.getInicios().get(j).length() + i, indices[1])));
                } else {
                    C.addAll(traductor(a.substring(Funcion.getInicios().get(j).length() + i, indices[0])), traductor(a.substring(Funcion.getMedios().get(j).length() + indices[0], indices[1])));
                }
                C.enqueue(Funcion.getInicios().get(j));
                addPorYMenosSiSeNecesita(a, i, positivo, C, P);
//                addMenosSiSeNecesita(C, positivo);
//                addPorSiSeNecesita(a, i, C, P);
                i = indices[1] - 1 + Funcion.getFinales().get(j).length();
                continue;
            }
            //else {

            addSigno(C, P, a.charAt(i));
            //---
//            if (!P.empty()) {
//                if (getPrioridad(P.peek().charAt(0)) > getPrioridad(a.charAt(i))) {
//                    C.addAll(P);
//                    P.push(a.charAt(i) + "");
//                    continue;
//                }
//                if (getPrioridad(P.peek().charAt(0)) == getPrioridad(a.charAt(i))) {
//                    C.enqueue(P.pop());
//                }
//
//            }
//            P.push(a.charAt(i) + "");
            //-------

//            if (a.charAt(i) == '+' || a.charAt(i) == '-') {
//                A.addAll(B);
//                B.push(a.charAt(i) + "");
//                continue;
//            }
//                        while (!B.empty()) {
//                            A.enqueue(B.pop());
//                        }
            // } else {
//            if (!B.empty() && (B.peek().equals("*") || B.peek().equals("/"))) {
//                A.enqueue(B.pop());
//            }
//            // }
//            B.push(a.charAt(i) + "");
            // }
            // }
        }
        C.addAll(P);
//        while (!B.empty()) {
//            A.enqueue(B.pop());
//        }
        return C;
    }
//5+6*2-3     25,3+(-0.1)

    static private void addPorYMenosSiSeNecesita(String a, int indice, boolean positivo, Cola<String> C, Stack<String> P) {
        if (!positivo) {
            C.enqueue("-1");
            C.enqueue("*");
        }
        if (esPor(a, indice, Funcion.getFinales(), Funcion.getInicios())) {
            addSigno(C, P, '*');
        }
    }

//    static private void addMenosSiSeNecesita(Cola<String> C, boolean positivo) {
//        if (!positivo) {
//            C.enqueue("-");
//        }
//    }
//
//    static private void addPorSiSeNecesita(String a, int indice, Cola<String> C, Stack<String> P) {
//        if (esPor(a, indice, Funcion.getFinales(), Funcion.getInicios())) {
//            addSigno(C, P, '*');
//        }
//    }
    static private void addSigno(Cola<String> C, Stack<String> P, char operador) {
        if (!P.empty()) {
            if (getPrioridad(P.peek().charAt(0)) > getPrioridad(operador)) {
                //  C.addAll(P);
                while (!P.empty() && getPrioridad(P.peek().charAt(0)) > getPrioridad(operador)) {
                    C.enqueue(P.pop());
                }
                P.push(operador + "");
                return;
            }
            if (getPrioridad(P.peek().charAt(0)) == getPrioridad(operador)) {
                C.enqueue(P.pop());
            }

        }
        P.push(operador + "");
    }

//    public static double resolver(String a) {
//        return resolverFunciones(a);
//    }
//
//    private static double resolverFunciones(String a) {
//        String sinFunciones = "";
//        boolean aContinuacion = false;
//        //if (a.length() > 4) {
//        for (int i = 0; i < a.length(); i++) {
//            //int j = i < a.length() - 3 ? contieneStringAEnIndiceAArrayListStringB(i, a, Funcion.getInicios()) : -1;
//            int j = contieneStringAEnIndiceAArrayListStringB(i, a, Funcion.getInicios());
//            if (j > -1) {
//                int indices[] = posicionesDeTerminacionDe(a, Funcion.getInicios(), Funcion.getMedios(), Funcion.getFinales(), Funcion.getInicios().get(j).length() + i);
//                if (Funcion.getMedios().get(j).equals("")) {
//                    sinFunciones += (aContinuacion ? "*" : "") + Funcion.resolverFuncion(Funcion.getInicios().get(j), Funcion.getMedios().get(j), resolver(a.substring(Funcion.getInicios().get(j).length() + i, indices[1])));
//                } else {
//                    sinFunciones += (aContinuacion ? "*" : "") + Funcion.resolverFuncion(Funcion.getInicios().get(j), Funcion.getMedios().get(j), resolver(a.substring(Funcion.getInicios().get(j).length() + i, indices[0])), resolver(a.substring(Funcion.getMedios().get(j).length() + indices[0], indices[1])));
//                }
//                i = indices[1] - 1 + Funcion.getFinales().get(j).length();
//                aContinuacion = true;
//            } else {
//                sinFunciones += a.charAt(i);
//                aContinuacion = false;
//            }
//        }
////
////        } else {
////            sinFunciones = a;
////        }
//        return resolverExponente(a);
//
//    }
//
//    private static double resolverExponente(String a) {
//        while (a.contains("^")) {
//            double base[] = buscarNumeroYCantidadDeCaracteresOriginalInverso(a, a.indexOf("^") - 1, Funcion.getInicios(), false);
//            double exponente[] = buscarNumeroYCantidadDeCaracteresOriginal(a, a.indexOf("^") + 1, Funcion.getInicios());
//            a = (a.indexOf("^") - (base[1] - 1) > -1 ? a.substring(0, (a.indexOf("^") - (int) base[1])) : "") + Funcion.resolverFuncion("", "^", base[0], exponente[0]) + (exponente[1] + a.indexOf("^") == a.length() ? "" : a.substring((int) exponente[1] + a.indexOf("^") + 1, a.length()));
//        }
//        return resolverMultiplicacion(a);
//    }
//
//    private static double resolverMultiplicacion(String a) {
//
//        String b = "";
//        double numero1[] = {-1, -1};
//      //  boolean aContinuacion = false;
//        for (int i = 0; i < a.length(); i++) {
//            try {
//                numero1 = buscarNumeroYCantidadDeCaracteresOriginal(a, i, Funcion.getInicios());
//                i += numero1[1];
//            } catch (NumberFormatException e) {
//                if (or(a.charAt(i), '*', '/')) {
//                    double numero2[] = buscarNumeroYCantidadDeCaracteresOriginal(a, i + 1, Funcion.getInicios());
//                   if(a.charAt(i)=='*'){
//                   numero1[0]*=numero2[0];
//                   }else{
//                   numero1[0]/=numero2[0];
//                   }
//                   // aContinuacion = true;
//                } else {
//                    
//                    b +=numero1[0]+ (a.charAt(i)+"");
//                    //aContinuacion = false;
//                }
//
//            }
//        }
//        b+=numero1[0];
//    }
    static private int getPrioridad(char a) {
        switch (a) {
            case '+':
            case '-':
                return 0;
            case '*':
            case '/':
                return 1;
            case '^':
                return 2;

        }
        return -1;
    }

}

//        ArrayList<ArrayList<Operando>> operacionMultiplicacion2 = new ArrayList<ArrayList<Operando>>();
//        operacionMultiplicacion2.get(0).add(variable.getCociente().getNumerador());
//        operacionMultiplicacion2.get(0).add(b);
//        variable.setCociente(new FraccionGeneral(new Operando(new Funcion(TipoDeEnvoltura.NUMERO, new Ecuacion(new ArrayList<Operando>(), operacionMultiplicacion2))), variable.getCociente().getDenominador()));

// ***********************
