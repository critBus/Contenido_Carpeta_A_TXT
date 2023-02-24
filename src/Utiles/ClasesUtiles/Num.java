/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles;

import java.math.BigDecimal;
import static Utiles.MetodosUtiles.MetodosUtiles.*;
import static Utiles.MetodosUtiles.Operaciones.*;

/**
 *
 * @author Rene
 */
public class Num implements Comparable<Num> {

    BigDecimal n;

    public Num(BigDecimal n) {
        this.n = n;
    }

    public Num(double a) {
        n = new BigDecimal(a + "");
    }

    public Num(String a) {
        n = new BigDecimal(a);
    }

    void aumentar(BigDecimal... a) {
        for (int i = 0; i < a.length; i++) {
            n = sumaBigDecimal(n, a[i]);
        }
    }

    void disminuir(BigDecimal... a) {
        for (int i = 0; i < a.length; i++) {
            n = sumaBigDecimal(n, a[i].negate());
        }
    }

    public void aumentar(Num... a) {
        for (int i = 0; i < a.length; i++) {
            n = sumaBigDecimal(n, a[i].getN());
        }
    }

    public void disminuir(Num... a) {
        for (int i = 0; i < a.length; i++) {
            n = sumaBigDecimal(n, a[i].getN().negate());
        }
    }

    public void multiplicar(Num... a) {
        for (int i = 0; i < a.length; i++) {
            n = regularBigDecimal(n.multiply(a[i].getN()));
        }
    }

    public void dividir(Num... a) {
        for (int i = 0; i < a.length; i++) {
         //   n = regularBigDecimal(n.divide(a[i].getN()));
               n = regularBigDecimal(dividirBigDecimal(n, a[i].getN()));
        }
    }

    public Num copia() {
        return new Num(n.toString());
    }

    Num suma(BigDecimal... a) {
        Num n2 = copia();
        n2.aumentar(a);
        return n2;
    }

    Num resta(BigDecimal... a) {
        Num n2 = copia();
        n2.disminuir(a);
        return n2;
    }

//    public Num suma(Num... a) {
//        Num n2 = copia();
//        n2.aumentar(a);
//        return n2;
//    }
//
//    public Num resta(Num... a) {
//        Num n2 = copia();
//        n2.disminuir(a);
//        return n2;
//    }
//    public Num multiplicar(Num... a) {
//        Num n2 = copia();
//        n2.multiplica(a);
//        return n2;
//    }
//
//    public Num dividir(Num... a) {
//        Num n2 = copia();
//        n2.divide(a);
//        return n2;
//    }
    public boolean mayorQue(Num a) {
        return compareTo(a) == 1;
    }

    public boolean menorQue(Num a) {
        return compareTo(a) == -1;
    }

    public boolean igualQue(Num a) {
        return compareTo(a) == 0;
    }

    static Num sumar(BigDecimal... a) {
        BigDecimal n2 = new BigDecimal(0);
        for (int i = 0; i < a.length; i++) {
            n2 = sumaBigDecimal(n2, a[i]);
        }
        return new Num(n2);
    }

    static Num restar(BigDecimal... a) {
        BigDecimal n2 = new BigDecimal(0);
        for (int i = 0; i < a.length; i++) {
            n2 = sumaBigDecimal(n2, a[i].negate());
        }
        return new Num(n2);
    }

    public static Num suma(Num... a) {
        BigDecimal n2 = new BigDecimal(0);
        for (int i = 0; i < a.length; i++) {
            n2 = sumaBigDecimal(n2, a[i].getN());
        }
        return new Num(n2);
    }

    public static Num resta(Num... a) {
        BigDecimal n2 = new BigDecimal(0);
        for (int i = 0; i < a.length; i++) {
            n2 = sumaBigDecimal(n2, a[i].getN().negate());
        }
        return new Num(n2);
    }

    public static Num multiplicacion(Num... a) {
        BigDecimal n2 = new BigDecimal(1);
        for (int i = 0; i < a.length; i++) {
            n2 = regularBigDecimal(n2.multiply(a[i].getN()));
        }
        return new Num(n2);
    }

    public static Num divicion(Num a, Num... b) {
        BigDecimal n2 = a.getN();
        for (int i = 0; i < b.length; i++) {
          //  n2 = regularBigDecimal(n2.divide(b[i].getN()));
              n2 = regularBigDecimal(dividirBigDecimal(n2, b[i].getN()));
        }
        return new Num(n2);
    }

    private static BigDecimal dividirBigDecimal(BigDecimal a, BigDecimal b) {
        try {
            return a.divide(b);
        } catch (ArithmeticException e) {
            return new BigDecimal((regularBigDecimal(a).doubleValue() / regularBigDecimal(b).doubleValue()) + "");
        }
    }

    public BigDecimal getN() {
        return n;
    }

    public void setN(BigDecimal n) {
        this.n = n;
    }

    public static Num[][] aumentarEnNulo(Num A[][]) {
        Num r[][] = new Num[A.length + 1][A[0].length + 1];
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {
                r[i][j] = (i == 0 || j == 0) ? new Num(0) : A[i - 1][j - 1];
            }
        }
        return r;
    }

    public static Num[] aumentarEnNulo(Num A[]) {
        Num r[] = new Num[A.length + 1];
        for (int i = 0; i < r.length; i++) {
            r[i] = (i == 0) ? new Num(0) : A[i - 1];
        }
        return r;
    }

    public static Num[][] disminuirEnNulo(Num A[][]) {
        Num r[][] = new Num[A.length - 1][A[0].length - 1];
        for (int i = 0; i < r.length; i++) {
            for (int j = 0; j < r[0].length; j++) {
                r[i][j] = A[i + 1][j + 1];
            }
        }
        return r;
    }

    public static Num[] disminuirEnNulo(Num A[]) {
        Num r[] = new Num[A.length - 1];
        for (int i = 0; i < r.length; i++) {
            r[i] = A[i + 1];
        }
        return r;
    }

    public static Num mod(Num a) {
        return new Num(a.getN().abs());
    }

    @Override
    public int compareTo(Num o) {
        return n.compareTo(o.getN());
    }

    @Override
    public String toString() {
        return n.toString(); //To change body of generated methods, choose Tools | Templates.
    }

    public static Num[][] convertirANum(double A[][]) {
        Num[][] m = new Num[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                m[i][j] = new Num(regularBigDecimal(A[i][j]));
            }
        }
        return m;
    }

    public static Num[] convertirANum(double A[]) {
        Num[] m = new Num[A.length];
        for (int i = 0; i < A.length; i++) {
            m[i] = new Num(regularBigDecimal(A[i]));
        }
        return m;
    }

    public static BigDecimal[][] convertirABigDecimal(Num A[][]) {
        BigDecimal[][] m = new BigDecimal[A.length][A[0].length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                m[i][j] = A[i][j].getN();
            }
        }
        return m;
    }

    public static BigDecimal[] convertirABigDecimal(Num A[]) {
        BigDecimal[] m = new BigDecimal[A.length];
        for (int i = 0; i < A.length; i++) {
            m[i] = A[i].getN();
        }
        return m;
    }

}
