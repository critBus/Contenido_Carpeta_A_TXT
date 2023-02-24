/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas.Funciones;

//import Utiles.ClasesUtiles.Arreglo;
import Utiles.ClasesUtiles.Matematicas.*;
import static Utiles.MetodosUtiles.Arreglos.alMenosIgualesAconAA;
import static Utiles.MetodosUtiles.Arreglos.colocarDeUltimoObject;
import static Utiles.MetodosUtiles.Arreglos.igualesAconAA;
//import static Utiles.MetodosUtiles.Arreglos.mostrarMatriz;
import static Utiles.MetodosUtiles.Arreglos.convertirArreglo2AArrayList;

import static Utiles.MetodosUtiles.MetodosUtiles.bicondicional;
import static Utiles.MetodosUtiles.MetodosUtiles.mismaClase;
import static Utiles.MetodosUtiles.Operaciones.suma;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Rene
 */
public class Exponente extends Funcion {

    public Exponente() {
    }

    public Exponente(Exponente f) {
        this(f.getParametro1().copia(), f.getParametro2().copia(), f.esPositivo());
    }

    public Exponente(double parametro1, Operando parametro2, boolean positivo) {
        this(new Numero(parametro1), parametro2, positivo);
    }

    public Exponente(Operando parametro1, double parametro2, boolean positivo) {
        this(parametro1, new Numero(parametro2), positivo);
    }

    public Exponente(double parametro1, double parametro2, boolean positivo) {
        this(new Numero(parametro1), new Numero(parametro2), positivo);
    }

    public Exponente(double parametro1, Operando parametro2) {
        this(new Numero(parametro1), parametro2, true);
    }

    public Exponente(Operando parametro1, double parametro2) {
        this(parametro1, new Numero(parametro2), true);
    }

    public Exponente(double parametro1, double parametro2) {
        this(new Numero(parametro1), new Numero(parametro2), true);
    }

    public Exponente(Operando parametro1, Operando parametro2) {
        this(parametro1, parametro2, true);
    }

    public Exponente(Operando parametro1, Operando parametro2, boolean positivo) {
        inicializarConstructorDosParametros(TipoDeEnvoltura.EXPONETE, parametro1, parametro2, positivo);
    }

    @Override
    public void addParametro2(Operando o) {
        if (parametros.get(1).IsNumero()) {
            setParametro2(o);
        } else {
            super.addParametro2(o);
        }
    }

    @Override
    protected void inicializarOptimo() {
        super.inicializarOptimo();
        if (!isNumero) {

            if (getParametro2() instanceof Logaritmo) {
                if (getParametro1().igualA(((Funcion) getParametro2()).getParametro1())) {
                    optimo = ((Funcion) getParametro2()).getParametro2();
                    optimo.setPositivo(positivo, optimo.esPositivo());
                    return;
                }
            }

            if (getParametro2().IsNumero()) {
                //caso a^0
                if (getParametro2().getNumero() == 0) {
                    optimo = new Numero(1);
                    return;
                }
                if (getParametro2().getNumero() == 1) {
                    optimo = getParametro1();
                    return;
                }

                if (getParametro2().getNumero() < 0) {
                    Exponente exponente = (Exponente) this.copia();
                    exponente.getParametro2().setPositivo(true);
                    optimo = new FraccionGeneral(1, exponente).getOperandoOptimizado();
                    return;
                }

            }

            // caso raiz^exponente
            if (getParametro1() instanceof Raiz) {
//            System.out.println("sf this=" + this);
//            Operando indiceRaiz = ((Funcion) getParametro1()).getParametro1();
//            System.out.println("indiceRaiz=" + indiceRaiz + " " + indiceRaiz.esPositivo());
//            Operando indiceExponente = getParametro2();
//            System.out.println("indiceExponente=" + indiceExponente + " " + indiceExponente.esPositivo());
//            Operando base = ((Funcion) getParametro1()).getParametro2();
//            System.out.println("base=" + base + " " + base.esPositivo());
//           
//            
//            Parentesis parentesis = new Parentesis(base);
//           Exponente  exponente= new Exponente(parentesis.getOperandoOptimizado(), indiceExponente);
//            optimo = new Raiz(indiceRaiz, exponente);
//         
//            System.out.println("optimo=" + optimo);
//            System.out.println("p1=" + getParametro1().esPositivo() + " p2=" + getParametro2().esPositivo());
//           
//             Parentesis parentesis2 = new Parentesis(((Funcion) getParametro1()).getParametro2());
//              Exponente  exponente2= new Exponente(new Parentesis(((Funcion) getParametro1()).getParametro2()).getOperandoOptimizado(),getParametro2());
//              
                optimo = new Raiz(((Funcion) getParametro1()).getParametro1(), new Exponente(new Parentesis(((Funcion) getParametro1()).getParametro2()).getOperandoOptimizado(), getParametro2()));
                return;
            }
            //caso Exponente^Exponente

            if (getParametro1() instanceof Exponente) {

                // System.out.println("this=" + this + " " + this.esPositivo() + " p1=" + getParametro1() + " " + getParametro1().esPositivo() + " p2=" + getParametro2() + " " + getParametro2().esPositivo());
                positivo = bicondicional(positivo, getParametro1().extraerPositivo());

                // System.out.println("indice" + Arrays.toString(indice));
                // System.out.println("((Funcion) getParametro1()).getParametro1()=" + ((Funcion) getParametro1()).getParametro1());
                setParametro1(((Funcion) getParametro1()).getParametro1());
                // System.out.println("getParametro1()=" + getParametro1());
                setParametro2(new Multiplicacion(getParametro2(), ((Funcion) getParametro1()).getParametro2()));
                // System.out.println("getParametro2()=" + getParametro2());
                //  System.out.println("this=" + this + " " + this.esPositivo());
                optimo = this;
            }
        }

    }

    static public Exponente[] separarEnMultiplicacion(Exponente... A) {
        ArrayList<Exponente> e = new ArrayList<Exponente>();
        for (int i = 0; i < A.length; i++) {
            if (A[i].getParametro1() instanceof Multiplicacion) {
                for (int j = 0; j < ((Multiplicacion) A[i].getParametro1()).lengh(); j++) {
                    e.add(new Exponente(((Multiplicacion) A[i].getParametro1()).getOperando(i), A[i].getParametro2(), j == 0 ? A[i].getParametro1().esPositivo() : true));
                }
            } else {
                e.add(A[i]);
            }
        }
        return e.toArray(new Exponente[0]);
    }

    public static boolean contienenLasClases(int cantidad, String nombresDeClases[], Exponente... objetos) {
        int cantidadPorCadaUna[] = new int[nombresDeClases.length];
        Arrays.fill(cantidadPorCadaUna, cantidad);
        return contienenLasClases(cantidadPorCadaUna, false, nombresDeClases, objetos);
    }

    public static boolean contienenLasClases(int cantidad, boolean mismaCantidadDeClases, String nombresDeClases[], Exponente... objetos) {
        int cantidadPorCadaUna[] = new int[nombresDeClases.length];
        Arrays.fill(cantidadPorCadaUna, cantidad);
        return contienenLasClases(cantidadPorCadaUna, mismaCantidadDeClases, nombresDeClases, objetos);
    }

    public static boolean contienenLasClases(int cantidad, ArrayList<Exponente> objetos, String... nombresDeClases) {
        return contienenLasClases(cantidad, nombresDeClases, objetos);
    }

    public static boolean contienenLasClases(int cantidadPorCadaUna[], ArrayList<Exponente> objetos, String... nombresDeClases) {
        return contienenLasClases(cantidadPorCadaUna, nombresDeClases, objetos.toArray(new Exponente[0]));
    }

    public static boolean contienenLasClases(int cantidad, String nombresDeClases[], ArrayList<Exponente> objetos) {
        int a[] = new int[nombresDeClases.length];
        Arrays.fill(a, cantidad);
        return contienenLasClases(a, nombresDeClases, objetos);
    }

    public static boolean contienenLasClases(int cantidadPorCadaUna[], String nombresDeClases[], ArrayList<Exponente> objetos) {
        return contienenLasClases(cantidadPorCadaUna, nombresDeClases, objetos.toArray(new Exponente[0]));
    }

    public static boolean contienenLasClases(int cantidadPorCadaUna[], String nombresDeClases[], Exponente... objetos) {
        Exponente O[][] = {objetos};
        return contienenLasClases(cantidadPorCadaUna, false, nombresDeClases, O);
    }

    public static boolean contienenLasClases(int cantidadPorCadaUna[], boolean mismaCantidadDeClases, String nombresDeClases[], Exponente... objetos) {
        Exponente O[][] = {objetos};
        return contienenLasClases(cantidadPorCadaUna, mismaCantidadDeClases, nombresDeClases, O);
    }

    public static boolean contienenLasClases(int cantidadPorCadaUna[], boolean mismaCantidadDeClases, String nombresDeClases[], Exponente[]... objetos) {
        if (objetos.length == 0) {
            return false;
        }

        for (int i = 0; i < objetos.length; i++) {
            if (mismaCantidadDeClases ? suma(cantidadPorCadaUna) != objetos[i].length : suma(cantidadPorCadaUna) > objetos[i].length) {
                return false;
            }
        }

        int cantidad[][] = new int[objetos.length][nombresDeClases.length];
        for (int i = 0; i < objetos.length; i++) {
         //   System.out.println("i=" + i);

            for (int j = 0; j < nombresDeClases.length; j++) {
                //System.out.println("j=" + j);
                for (int k = 0; k < objetos[i].length; k++) {
              //  System.out.println("k=" + k);

                    // System.out.println("nombresDeClases[j]=" + nombresDeClases[j] + "  objetos[i][k]=" + objetos[i][k]);
                    if (mismaClase(nombresDeClases[j], objetos[i][k].getParametro1())) {
                        ++cantidad[i][j];
                        //mostrarMatriz(cantidad);
                        if (mismaCantidadDeClases && cantidad[i][j] > cantidadPorCadaUna[j]) {
                            return false;
                        }

                    }

//                    if (mismaClase(nombresDeClases[j], objetos[i][k]) && ++cantidad[i][j] > cantidadPorCadaUna[j]) {
//                        return false;
//                    }
                }
            }

        }
        //mostrarMatriz(cantidad);
        // System.out.println(Arrays.toString(cantidadPorCadaUna));
        // System.out.println("alMenosIgualesAconAA(cantidadPorCadaUna, cantidad)="+alMenosIgualesAconAA(cantidadPorCadaUna, cantidad));

        return mismaCantidadDeClases ? igualesAconAA(cantidadPorCadaUna, cantidad) : alMenosIgualesAconAA(cantidadPorCadaUna, cantidad);
    }

    public static Exponente[][] ordenarObjetosEnClases(String nombresDeClases[], Exponente... objetos) {
        //System.out.println("aa=" + objetos.getClass().getSimpleName());
        return ordenarObjetosEnClases(new ArrayList<String>(Arrays.asList(nombresDeClases)), objetos);
    }

    public static Exponente[][] ordenarObjetosEnClases(ArrayList<String> nombresDeClases, Exponente... objetos) {
        // System.out.println("bb=" + objetos.getClass().getSimpleName());
        ArrayList<ArrayList<Exponente>> B = new ArrayList<ArrayList<Exponente>>();
        B.add(new ArrayList<Exponente>(Arrays.asList(objetos)));
        // System.out.println("cc=" + B.getClass().getSimpleName());
        return ordenarObjetosEnClasesAA(nombresDeClases, B)[0];
    }

    public static Exponente[][][] ordenarObjetosEnClases(String nombresDeClases[], Exponente[]... objetos) {
        return ordenarObjetosEnClases(new ArrayList<String>(Arrays.asList(nombresDeClases)), objetos);
    }

    public static Exponente[][][] ordenarObjetosEnClases(ArrayList<String> nombresDeClases, Exponente[]... objetos) {
        return ordenarObjetosEnClasesAA(nombresDeClases, convertirArreglo2AArrayList(objetos));
    }

//      public static <E> E[][] ordenarObjetosEnClases(ArrayList<String> nombresDeClases, ArrayList<ArrayList<E>> objetos) {
//      return ordenarObjetosEnClases(nombresDeClases, objetos,objetos.get(0).get(0));
//      }
//    
    public static Exponente[][] ordenarObjetosEnClases(ArrayList<Exponente> objetos, String... nombresDeClases) {
        return ordenarObjetosEnClases(nombresDeClases, objetos);
    }

    public static Exponente[][] ordenarObjetosEnClases(String nombresDeClases[], ArrayList<Exponente> objetos) {
        return ordenarObjetosEnClases(new ArrayList<String>(Arrays.asList(nombresDeClases)), objetos);
    }

    public static Exponente[][] ordenarObjetosEnClases(ArrayList<String> nombresDeClases, ArrayList<Exponente> objetos) {
        ArrayList<ArrayList<Exponente>> o = new ArrayList<ArrayList<Exponente>>();
        o.add(objetos);
        return ordenarObjetosEnClasesAA(nombresDeClases, o)[0];
    }

    public static Exponente[][][] ordenarObjetosEnClasesAA(ArrayList<String> nombresDeClases, ArrayList<ArrayList<Exponente>> objetos) {
        Exponente e[][][] = new Exponente[objetos.size()][nombresDeClases.size()][0];
        for (int j = 0; j < objetos.size(); j++) {
            for (int k = 0; k < nombresDeClases.size(); k++) {
                int indiceCoincidencias = 0;
                for (int i = 0; i < objetos.get(j).size(); i++) {
                    if (mismaClase(nombresDeClases.get(k), objetos.get(j).get(i).getParametro1())) {
                        e[j][k] = colocarDeUltimoObject(e[j][k], objetos.get(j).remove(i--));

                    }
                }
            }
        }
        return e;
    }

    @Override
    public Exponente[] crearArreglo(int leng) {
        return new Exponente[leng];
    }

    @Override
    public Exponente[][] crearArreglo(int filas, int columnas) {
        return new Exponente[filas][columnas];
    }

    @Override
    public Exponente[][][] crearArreglo(int filas, int columnas, int cantidad) {
        return new Exponente[filas][columnas][cantidad];
    }

    @Override
    public Operando inicializarDerivada(char v) {//getParametro1().copia()
        return (derivado = new Multiplicacion(bicondicional(positivo, true),getParametro2().copia() ,new Exponente(getParametro1().copia(), Suma.inicializarRestando(getParametro2().copia(),new Numero(1))), getParametro1().inicializarDerivada(v))); //To change body of generated methods, choose Tools | Templates.
    }
}
