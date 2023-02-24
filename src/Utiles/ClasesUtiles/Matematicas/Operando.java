/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

//import static Utiles.MetodosUtiles.Operaciones.calcular;
import Utiles.ClasesUtiles.Interfases.Arreglo;
import Utiles.ClasesUtiles.Matematicas.Funciones.*;
import Utiles.ClasesUtiles.Matematicas.Constantes.*;
import java.util.ArrayList;
//import Utiles.ClasesUtiles.Semejantes;
import static Utiles.MetodosUtiles.MetodosUtiles.bicondicional;
//import static Utiles.MetodosUtiles.MetodosUtiles.mostrarArrayList;
import static Utiles.MetodosUtiles.MetodosUtiles.ArrayListCharacterAArrayListString;
import static Utiles.MetodosUtiles.Arreglos.arreglo;
import static Utiles.MetodosUtiles.Arreglos.arregloObject;

/**
 *
 * @author Rene
 */
public class Operando implements Comparable<Operando>, Arreglo<Operando> {

    // private double numero;
//    private Funcion funcion;
//    private Variable variable;
//    private FraccionGeneral fracciongeneral;
//    private boolean EsFuncion;
//    private boolean EsVariable;
//    private boolean EsFraccion;
    // private boolean EsNumero;
    protected boolean positivo = true;
    protected boolean isNumero = false;
    //boolean isMultiplicacion = false;
    protected double numero;
    protected Operando optimo;
    protected Operando derivado;

    public Operando() {
    }

    public Operando(boolean positivo) {
        this.positivo = positivo;
        optimo = this;
    }

    public boolean IsNumero() {
        return isNumero;
    }

    public double getNumero() {
        // return numero * (positivo ? 1 : -1);
        return numero;
    }

    public double getNumeroInicializarOptimo() {
        inicializarOptimo();
        return numero;
    }

    public boolean esMultiplicacion() {
        return this instanceof Multiplicacion;
    }

    public boolean esFuncion() {
        return this instanceof Funcion;
    }

    public boolean esVariable() {
        return this instanceof Variable;
    }

    public boolean esFraccion() {
        return this instanceof FraccionGeneral;
    }

    public boolean esSuma() {
        return this instanceof Suma;
    }

    public boolean esPositivo() {
        return positivo;
    }

    public boolean extraerPositivo() {
        boolean p = positivo;
        positivo = true;

        //optimo.positivo=true;
        // inicializarOptimo();
        return p;
    }

    public void setPositivo(boolean... positivo) {
        setPositivo(bicondicional(positivo));
    }

    public static void setPositivo(Operando... O) {
        setPositivo(true, O);
    }

    public static void setPositivo(boolean positivo, Operando... O) {
        setPositivo(arreglo(positivo), O);
    }

    public static void setPositivo(boolean positivos[], Operando... O) {
        setPositivoAA(positivos, arregloObject(O));
    }

    public static void setPositivoAA(Operando[]... O) {
        setPositivoAA(true, O);
    }

    public static void setPositivoAA(boolean positivo, Operando[]... O) {
        setPositivoAA(arreglo(positivo), O);
    }

    public static void setPositivoAA(boolean positivos[], Operando[]... O) {
        boolean positivo = bicondicional(positivos);
        for (int i = 0; i < O.length; i++) {
            for (int j = 0; j < O[i].length; j++) {
                if (O[i][j].esPositivo() != positivo) {
                    O[i][j].setPositivo(positivo);
                }
            }
        }

    }

    public void setPositivo(boolean positivo) {

        if (IsNumero() && this.positivo != positivo) {
            //  System.out.println("1 numero=" + numero);
            //  System.out.println("this.positivo =" + this.positivo + " positivo=" + positivo);
            numero *= -1;
            // System.out.println("2 numero=" + numero);
            positivo = true;
        } else {
            this.positivo = positivo;
        }
//        if(isMultiplicacion()){
//            for (int i = 0; i <getMultiplicacion().size(); i++) {
//                getMultiplicacion().get(i).setPositivo(positivo);
//            }
//        }
        // optimo.positivo=positivo;//esSignoMenos
        // inicializarOptimo();
    }

    public Operando getOptimo() {
        return optimo;
    }

    public Operando getOperandoOptimizado() {
        return getOperandoOptimizado(false);
    }

    public Operando getOperandoOptimizado(boolean continuar) {
        simplificar(continuar);
        return optimo;
    }

    protected void inicializarOptimo() {
        optimo = this;
    }

    public Operando copiaOptima() {
        return getOperandoOptimizado().copia();
    }

    public Operando copiaOptima(boolean continuar) {
        return getOperandoOptimizado(continuar).copia();
    }

    public Operando copia() {
//        if (this instanceof Ecuacion) {
//            return new Ecuacion((Ecuacion) this);
//        }
        if (this instanceof Pi) {
            return new Pi(positivo);
        }
        if (this instanceof Euler) {
            return new Euler(positivo);
        }
        if (this instanceof Cosecante) {
            return new Cosecante((Cosecante) this);
        }
        if (this instanceof Coseno) {
            return new Coseno((Coseno) this);
        }
        if (this instanceof Cotagente) {
            return new Cotagente((Cotagente) this);
        }
        if (this instanceof Exponente) {
            return new Exponente((Exponente) this);
        }
        if (this instanceof Logaritmo) {
            return new Logaritmo((Logaritmo) this);
        }
        if (this instanceof Logaritmo_Naperiano) {
            return new Logaritmo_Naperiano((Logaritmo_Naperiano) this);
        }
        if (this instanceof Parentesis) {
            return new Parentesis((Parentesis) this);
        }
        if (this instanceof Raiz) {
            return new Raiz((Raiz) this);
        }
        if (this instanceof Secante) {
            return new Secante((Secante) this);
        }
        if (this instanceof Seno) {
            return new Seno((Seno) this);
        }
        if (this instanceof Tangente) {
            return new Tangente((Tangente) this);
        }
        if (this instanceof Suma) {
            return new Suma((Suma) this);
        }
        if (this instanceof Multiplicacion) {
            return new Multiplicacion((Multiplicacion) this);
        }
        if (this instanceof Numero) {
            return new Numero((Numero) this);
        }
        if (this instanceof Variable) {
            return new Variable((Variable) this);
        }
        if (this instanceof FraccionGeneral) {
            return new FraccionGeneral((FraccionGeneral) this);
        }
        return new Operando(positivo);
    }

    public boolean igualA(Operando a, boolean valorarSigno) {

        return false;
    }

    public boolean igualA(Operando a) {
        return igualA(a, true);
    }

    public static ArrayList<String> getPermitidos() {
        ArrayList<String> p = Funcion.getInicios();
        p.addAll(ArrayListCharacterAArrayListString(TipoDeConstante.getCaracteres2()));
        return p;
    }

    @Override
    public int compareTo(Operando o) {
        if (o.getNumero() == numero) {
            return 0;
        }
        return numero > o.getNumero() ? 1 : -1;
    }

    public void simplificar() {
        simplificar(false);
    }

    public void simplificar(boolean continuar) {
        inicializarOptimo();
    }

    @Override
    public Operando[] crearArreglo(int leng) {
        return new Operando[leng];
    }

    @Override
    public Operando[][] crearArreglo(int filas, int columnas) {
        return new Operando[filas][columnas];
    }

    @Override
    public Operando[] crearArregloSuperclase(int leng) {
        return new Operando[leng];
    }

    @Override
    public Operando[][] crearArregloSuperclase(int filas, int columnas) {
        return new Operando[filas][columnas];
    }

    @Override
    public Operando[] crearArregloSuperclaseSuperior(int leng) {
        return new Operando[leng];
    }

    @Override
    public Operando[][] crearArregloSuperclaseSuperior(int filas, int columnas) {
        return new Operando[filas][columnas];

    }

    @Override
    public Operando[][][] crearArreglo(int filas, int columnas, int cantidad) {
        return new Operando[filas][columnas][cantidad];
    }

    @Override
    public Operando[][][] crearArregloSuperclase(int filas, int columnas, int cantidad) {
        return new Operando[filas][columnas][cantidad];
    }

    @Override
    public Operando[][][] crearArregloSuperclaseSuperior(int filas, int columnas, int cantidad) {
        return new Operando[filas][columnas][cantidad];
    }

    public Operando obtenerDerivada(char v) {
        return !contieneVariable(v) ? new Numero(0) : inicializarDerivada(v);
    }

    public Operando obtenerDerivadaOptima(char v) {
        return !contieneVariable(v) ? new Numero(0) : inicializarDerivadaOptima(v);
    }

    public Operando obtenerDerivadaOptima(char v, int iesima) {
        Operando derivada = this;
        while (iesima-- > 0) {
            derivada = derivada.obtenerDerivadaOptima(v);
        }
        return derivada;
    }

    /**
     * !!!NO UTILIZAR!!!
     *
     * @param v
     * @return
     */
    public Operando inicializarDerivada(char v) {
        return (derivado = copia());
    }

    /**
     * !!!NO UTILIZAR!!!
     *
     * @param v
     * @return
     */
    public Operando inicializarDerivadaOptima(char v) {
        inicializarDerivada(v);
        //  System.out.println("derivado="+derivado);
        derivado.simplificar(true);

        return derivado;
    }

    public boolean contieneVariable(char v) {
        return false;
    }

}
