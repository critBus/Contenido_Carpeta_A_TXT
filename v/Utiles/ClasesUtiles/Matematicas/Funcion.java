/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

//import Utiles.ClasesUtiles.Arreglo;
import Utiles.ClasesUtiles.Matematicas.Funciones.*;

import static Utiles.MetodosUtiles.Arreglos.alMenosIgualesAconAA;
import static Utiles.MetodosUtiles.Arreglos.convertirArreglo2AArrayList;
import static Utiles.MetodosUtiles.Arreglos.igualesAconAA;
import static Utiles.MetodosUtiles.Operaciones.*;
import static Utiles.MetodosUtiles.MetodosUtiles.*;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Rene
 */
//public class Funcion implements TipoOperando {
public class Funcion extends Operando {

    protected TipoDeEnvoltura tipoDeEnvoltura;
    protected String inicio;
    protected String medio;
    protected String fin;
    protected ArrayList<Operando> parametros = new ArrayList<Operando>();
    //private boolean positivo;
    protected int cantidadDeParametros;

    public Funcion() {
    }

    protected void inicializarConstructorUnParamtro(TipoDeEnvoltura tipoDeEnvoltura, Operando parametro1, boolean positivo) {
        inicializarArrayList();
        this.tipoDeEnvoltura = tipoDeEnvoltura;
        this.positivo = positivo;
        this.cantidadDeParametros = 1;
        parametros.add(parametro1);
        inicializarString(tipoDeEnvoltura.getInicio(), tipoDeEnvoltura.getMedio(), tipoDeEnvoltura.getFin());

    }

    protected void inicializarConstructorDosParametros(TipoDeEnvoltura tipoDeEnvoltura, Operando parametro1, Operando parametro2, boolean positivo) {
        inicializarArrayList();
        this.tipoDeEnvoltura = tipoDeEnvoltura;
        this.positivo = positivo;
        this.cantidadDeParametros = 2;
        parametros.add(parametro1);
        parametros.add(parametro2);
        inicializarString(tipoDeEnvoltura.getInicio(), tipoDeEnvoltura.getMedio(), tipoDeEnvoltura.getFin());

    }

    public Operando getParametro1() {
        return parametros.get(0);
    }

    public Operando getFirstParametro1() {
        if (getParametro1() instanceof Multiplicacion) {
            return ((Multiplicacion) getParametro1()).getFirstOperando();
        }
        return parametros.get(0);
    }

    public Operando getLastParametro1() {
        if (getParametro1() instanceof Multiplicacion) {
            return ((Multiplicacion) getParametro1()).getLastOperando();
        }
        return parametros.get(0);
    }

    public Operando extraerParametro1() {
        Operando o = getParametro1().copia();
        parametros.set(0, new Numero(1));
        return o;
    }

    public Operando extraerFirstParametro1() {
        if (getParametro1() instanceof Multiplicacion) {
            return ((Multiplicacion) getParametro1()).extraerFirstOperando();
        }
        return extraerParametro1();
    }

    public Operando extraerLastParametro1() {
        if (getParametro1() instanceof Multiplicacion) {
            return ((Multiplicacion) getParametro1()).extraerLastOperando();
        }
        return extraerParametro1();
    }

    public void setParametros(Operando... O) {
        parametros.clear();
        for (Operando o : O) {
            parametros.add(o);
        }
        //  inicializarOptimo();
    }

    public void setParametros(ArrayList<Operando> parametros) {
        this.parametros = parametros;
        // inicializarOptimo();
    }

    public void setParametro1(double numero) {
        setParametro1(new Numero(numero));
    }

    public void setParametro1(Operando parametro1) {
        parametros.set(0, parametro1);
        //inicializarOptimo();
    }

    public Operando getParametro2() {
        return parametros.get(1);
    }

    public Operando getFirstParametro2() {
        if (getParametro2() instanceof Multiplicacion) {
            return ((Multiplicacion) getParametro2()).getFirstOperando();
        }
        return parametros.get(1);
    }

    public Operando getLastParametro2() {
        if (getParametro2() instanceof Multiplicacion) {
            return ((Multiplicacion) getParametro2()).getLastOperando();
        }
        return parametros.get(1);
    }

    public Operando extraerParametro2() {
        Operando o = getParametro2().copia();
        parametros.set(1, new Numero(1));
        return o;
    }

    public Operando extraerFirstParametro2() {
        if (getParametro2() instanceof Multiplicacion) {
            return ((Multiplicacion) getParametro2()).extraerFirstOperando();
        }
        return extraerParametro2();
    }

    public Operando extraerLastParametro2() {
        if (getParametro2() instanceof Multiplicacion) {
            return ((Multiplicacion) getParametro2()).extraerLastOperando();
        }
        return extraerParametro2();
    }

    public void setParametro2(double numero) {
        setParametro2(new Numero(numero));
    }

    public void setParametro2(Operando parametro2) {
        if (parametros.size() > 1) {
            parametros.set(1, parametro2);
        } else {
            parametros.add(parametro2);
        }

        //inicializarOptimo();
    }

    public Operando getParametro(int indice) {
        return parametros.get(indice);
    }

    public void setParametroN(int indice, double numero) {
        setParametroN(indice, new Numero(numero));
    }

    public void setParametroN(int indice, Operando parametro) {
        parametros.set(indice, parametro);
        // inicializarOptimo();
    }

    public TipoDeEnvoltura getTipoDeEnvoltura() {
        return tipoDeEnvoltura;
    }

    public String getInicio() {
        return inicio;
    }

    public String getMedio() {
        return medio;
    }

    public String getFin() {
        return fin;
    }
//    public int cantidadDeCaracteres(){
//    
//    }

//    public boolean isPositivo() {
//        return positivo;
//    }
//    public void setPositivo(boolean positivo) {
//        this.positivo = positivo;
//    }

    /*
     Pi ( ~ ) Euler ( E )  + - / * () ^ sqr[]() sen() cos() tan() cot() csc() sec() lnx()
     */
//    private void crearEstructura() {
//        switch (tipoDeEnvoltura) { //case :  break;
//            case NUMERO:
//                inicializarString("", "", "");
//                break;
//            case PARENTESIS:
//                inicializarString("(", "", ")");
//                break;
//            case LOGARITMO:
//                inicializarString("log(", "", ")");
//                break;
//            case LOGARITMO_NAPERIANO:
//                inicializarString("lnx(", "", ")");
//                break;
//            case SENO:
//                inicializarString("sen(", "", ")");
//                break;
//            case COSENO:
//                inicializarString("cos(", "", ")");
//                break;
//            case TANGENTE:
//                inicializarString("tan(", "", ")");
//                break;
//            case COTAGENTE:
//                inicializarString("cot(", "", ")");
//                break;
//            case SECANTE:
//                inicializarString("sec(", "", ")");
//                break;
//            case COSECANTE:
//                inicializarString("cos(", "", ")");
//                break;
//        }
//    }
    protected void inicializarString(String inicio, String medio, String fin) {
        this.inicio = inicio;
        this.medio = medio;
        this.fin = fin;
    }

    public void addParametro1(Operando o) {
        if (parametros.size() > 0) {
            setParametro1(new Multiplicacion(getParametro1(), o));
        } else {
            setParametro1(new Multiplicacion(o));
        }

//        ArrayList<Operando> operacionMultiplicacion = new ArrayList<Operando>();
//        operacionMultiplicacion.add(getParametro1());
//        operacionMultiplicacion.add(o);
//        setParametro1(new Multiplicacion(operacionMultiplicacion));
        //operacionMultiplicacion.clear();
        //inicializarOptimo();
    }

    public void addParametro2(Operando o) {
        if (parametros.size() > 1) {
            setParametro2(new Multiplicacion(getParametro2(), o));
        } else {
            setParametro2(new Multiplicacion(o));
        }
//        ArrayList<Operando> operacionMultiplicacion = new ArrayList<Operando>();
//       operacionMultiplicacion.add(getParametro2());
//        operacionMultiplicacion.add(o);
        // setParametro2(new Multiplicacion(operacionMultiplicacion));
        // operacionMultiplicacion.clear();
        //  inicializarOptimo();
    }

//    @Override
//    public Operando getOperando() {
//        return new Operando(this);
//    }
    @Override
    public String toString() {
        // System.out.println("fracion="+this);
        if (cantidadDeParametros == 1) {
            //   return (positivo ? "" : "-") + eliminarPuntoCero(tipoDeEnvoltura.getInicio() + (parametros.get(0).esPositivo() ? "" : "(") + parametros.get(0) + (parametros.get(0).esPositivo() ? "" : ")") + tipoDeEnvoltura.getFin(), getPermitidos(), Funcion.getInicios());

            return (positivo ? "" : "-") + tipoDeEnvoltura.getInicio() + (parametros.get(0).esPositivo() ? "" : "(") + parametros.get(0) + (parametros.get(0).esPositivo() ? "" : ")") + tipoDeEnvoltura.getFin();
        } else {
            //  return (positivo ? "" : "-") + eliminarPuntoCero((tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !positivo ? "(" : "") + tipoDeEnvoltura.getInicio() + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !parametros.get(0).esPositivo() ? "(" : "") + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && positivo && (parametros.get(0) instanceof FraccionGeneral || parametros.get(0) instanceof Multiplicacion) ? "(" : "") + parametros.get(0) + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && positivo && (parametros.get(0) instanceof FraccionGeneral || parametros.get(0) instanceof Multiplicacion) ? ")" : "") + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !parametros.get(0).esPositivo() ? ")" : "") + tipoDeEnvoltura.getMedio() + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !parametros.get(1).IsNumero() && !(parametros.get(1) instanceof Variable) ? "(" : "") + parametros.get(1) + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !parametros.get(1).IsNumero() && !(parametros.get(1) instanceof Variable) ? ")" : "") + tipoDeEnvoltura.getFin() + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !positivo ? ")" : ""), getPermitidos(), Funcion.getInicios());
            return (positivo ? "" : "-") + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !positivo ? "(" : "") + tipoDeEnvoltura.getInicio() + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !parametros.get(0).esPositivo() ? "(" : "") + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && positivo && (parametros.get(0) instanceof FraccionGeneral || parametros.get(0) instanceof Multiplicacion) ? "(" : "") + parametros.get(0) + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && positivo && (parametros.get(0) instanceof FraccionGeneral || parametros.get(0) instanceof Multiplicacion) ? ")" : "") + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !parametros.get(0).esPositivo() ? ")" : "") + tipoDeEnvoltura.getMedio() + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !parametros.get(1).IsNumero() && !(parametros.get(1) instanceof Variable) ? "(" : "") + parametros.get(1) + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !parametros.get(1).IsNumero() && !(parametros.get(1) instanceof Variable) ? ")" : "") + tipoDeEnvoltura.getFin() + (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE && !positivo ? ")" : "");
        }

    }

    protected static ArrayList<String> inicios;
    protected static ArrayList<String> medios;
    protected static ArrayList<String> finales;

    protected static void inicializarArrayList() {
        inicios = new ArrayList<String>();
        medios = new ArrayList<String>();
        finales = new ArrayList<String>();
        for (TipoDeEnvoltura t : TipoDeEnvoltura.values()) {
            inicios.add(t.getInicio());
            medios.add(t.getMedio());
            finales.add(t.getFin());
        }
    }

    public static ArrayList<String> getInicios() {
        inicializarArrayList();
        return inicios;
    }

    public static ArrayList<String> getMedios() {
        inicializarArrayList();
        return medios;
    }

    public static ArrayList<String> getFinales() {
        inicializarArrayList();
        return finales;
    }

    public static TipoDeEnvoltura[] aTdEs(String... a) {
        return arregloTipoDeEnvolturaString(a);
    }

    public static TipoDeEnvoltura[] arregloTipoDeEnvolturaString(String... a) {
        TipoDeEnvoltura T[] = new TipoDeEnvoltura[a.length];

        for (int i = 0; i < T.length; i++) {
            for (TipoDeEnvoltura t : TipoDeEnvoltura.values()) {
                if (t.toString().equals(a[i])) {
                    T[i] = t;
                }
            }
        }

        return T;
    }

    public static Funcion[] arregloFuncion(Operando... o) {
        Funcion f[] = new Funcion[o.length];
        for (int i = 0; i < o.length; i++) {
            f[i] = (Funcion) o[i];
        }
        return f;
    }

    public static Funcion[][] arregloFuncionAA(Operando[]... o) {
        Funcion f[][] = new Funcion[o.length][o[0].length];
        for (int i = 0; i < o.length; i++) {
            for (int j = 0; j < o[0].length; j++) {
                //System.out.println("o.length="+o.length+" o[0].length="+o[0].length);
                // System.out.println("f[i][j].getClass().getSimpleName()="+f[i][j].getClass().getSimpleName());
                f[i][j] = (Funcion) o[i][j];
            }

        }
        return f;
    }

    public static boolean contienenLasEnvolturas(int cantidad, TipoDeEnvoltura tiposDeEnvolturas[], Operando... objetos) {
        return contienenLasEnvolturas(cantidad, false, tiposDeEnvolturas, arregloFuncion(objetos));
    }

    public static boolean contienenLasEnvolturas(int cantidad, boolean mismaCantidadDeClases, TipoDeEnvoltura tiposDeEnvolturas[], Operando... objetos) {
        return contienenLasEnvolturas(cantidad, mismaCantidadDeClases, tiposDeEnvolturas, arregloFuncion(objetos));
    }

    public static boolean contienenLasEnvolturas(int cantidad, TipoDeEnvoltura tiposDeEnvolturas[], Funcion... objetos) {
        int cantidadPorCadaUna[] = new int[tiposDeEnvolturas.length];
        Arrays.fill(cantidadPorCadaUna, cantidad);
        return contienenLasEnvolturasAA(cantidadPorCadaUna, false, tiposDeEnvolturas, objetos);
    }

    public static boolean contienenLasEnvolturas(int cantidad, boolean mismaCantidadDeClases, TipoDeEnvoltura tiposDeEnvolturas[], Funcion... objetos) {
        int cantidadPorCadaUna[] = new int[tiposDeEnvolturas.length];
        Arrays.fill(cantidadPorCadaUna, cantidad);
        return contienenLasEnvolturasAA(cantidadPorCadaUna, mismaCantidadDeClases, tiposDeEnvolturas, objetos);
    }

    public static boolean contienenLassEnvolturas(int cantidadPorCadaUna[], TipoDeEnvoltura tiposDeEnvolturas[], Operando... objetos) {
        return contienenLassEnvolturas(cantidadPorCadaUna, false, tiposDeEnvolturas, arregloFuncion(objetos));
    }

    public static boolean contienenLassEnvolturas(int cantidadPorCadaUna[], boolean mismaCantidadDeClases, TipoDeEnvoltura tiposDeEnvolturas[], Operando... objetos) {
        return contienenLassEnvolturas(cantidadPorCadaUna, mismaCantidadDeClases, tiposDeEnvolturas, arregloFuncion(objetos));
    }

    public static boolean contienenLassEnvolturas(int cantidadPorCadaUna[], TipoDeEnvoltura tiposDeEnvolturas[], Funcion... objetos) {
        Funcion O[][] = {objetos};
        return contienenLasEnvolturasAA(cantidadPorCadaUna, false, tiposDeEnvolturas, O);
    }

    public static boolean contienenLassEnvolturas(int cantidadPorCadaUna[], boolean mismaCantidadDeClases, TipoDeEnvoltura tiposDeEnvolturas[], Funcion... objetos) {
        Funcion O[][] = {objetos};
        return contienenLasEnvolturasAA(cantidadPorCadaUna, mismaCantidadDeClases, tiposDeEnvolturas, O);
    }

    public static boolean contienenLasEnvolturasAA(int cantidadPorCadaUna[], TipoDeEnvoltura tiposDeEnvolturas[], Operando[]... objetos) {
        return contienenLasEnvolturasAA(cantidadPorCadaUna, false, tiposDeEnvolturas, arregloFuncionAA(objetos));
    }

    public static boolean contienenLasEnvolturasAA(int cantidadPorCadaUna[], boolean mismaCantidadDeClases, TipoDeEnvoltura tiposDeEnvolturas[], Operando[]... objetos) {
        return contienenLasEnvolturasAA(cantidadPorCadaUna, mismaCantidadDeClases, tiposDeEnvolturas, arregloFuncionAA(objetos));
    }

    public static boolean contienenLasEnvolturasAA(int cantidad, TipoDeEnvoltura tiposDeEnvolturas[], Operando[]... objetos) {
        return contienenLasEnvolturasAA(cantidad, false, tiposDeEnvolturas, arregloFuncionAA(objetos));
    }

    public static boolean contienenLasEnvolturasAA(int cantidad, boolean mismaCantidadDeClases, TipoDeEnvoltura tiposDeEnvolturas[], Operando[]... objetos) {
        for (int i = 0; i < objetos.length; i++) {
            for (int j = 0; j < objetos[i].length; j++) {
                if (!objetos[i][j].esFuncion()) {
                    return false;
                }
            }
        }
        return contienenLasEnvolturasAA(cantidad, mismaCantidadDeClases, tiposDeEnvolturas, arregloFuncionAA(objetos));
    }

    public static boolean contienenLasEnvolturasAA(int cantidad, TipoDeEnvoltura tiposDeEnvolturas[], Funcion[]... objetos) {
        int cantidadPorCadaUna[] = new int[tiposDeEnvolturas.length];
        Arrays.fill(cantidadPorCadaUna, cantidad);
        return contienenLasEnvolturasAA(cantidadPorCadaUna, false, tiposDeEnvolturas, objetos);

    }

    public static boolean contienenLasEnvolturasAA(int cantidad, boolean mismaCantidadDeClases, TipoDeEnvoltura tiposDeEnvolturas[], Funcion[]... objetos) {
        int cantidadPorCadaUna[] = new int[tiposDeEnvolturas.length];
        Arrays.fill(cantidadPorCadaUna, cantidad);
        return contienenLasEnvolturasAA(cantidadPorCadaUna, mismaCantidadDeClases, tiposDeEnvolturas, objetos);

    }

    public static boolean contienenLasEnvolturasAA(int cantidadPorCadaUna[], boolean mismaCantidadDeClases, TipoDeEnvoltura tiposDeEnvolturas[], Funcion[]... objetos) {

        if (objetos.length == 0) {
            return false;
        }

        for (int i = 0; i < objetos.length; i++) {
            if (mismaCantidadDeClases ? suma(cantidadPorCadaUna) != objetos[i].length : suma(cantidadPorCadaUna) > objetos[i].length) {
                return false;
            }
        }

        int cantidad[][] = new int[objetos.length][tiposDeEnvolturas.length];
        for (int i = 0; i < objetos.length; i++) {
            for (int k = 0; k < objetos[i].length; k++) {
                for (int j = 0; j < tiposDeEnvolturas.length; j++) {
                    if (mismaEnvoltura(tiposDeEnvolturas[j], objetos[i][k])) {
                        ++cantidad[i][j];
                        if (mismaCantidadDeClases && cantidad[i][j] > cantidadPorCadaUna[j]) {
                            return false;
                        }
                    }

//                    if (mismaEnvoltura(tiposDeEnvolturas[j], objetos[i][k]) && ++cantidad[i][j] > cantidadPorCadaUna[j]) {
//                        return false;
//                    }
                }
            }

        }

        return mismaCantidadDeClases ? igualesAconAA(cantidadPorCadaUna, cantidad) : alMenosIgualesAconAA(cantidadPorCadaUna, cantidad);
    }

    public static Funcion[] ordenarObjetosEnEnvolturas(TipoDeEnvoltura tiposDeEnvolturas[], Operando... objetos) {
        return ordenarObjetosEnEnvolturas(tiposDeEnvolturas, arregloFuncion(objetos));
    }

    public static Funcion[] ordenarObjetosEnEnvolturas(TipoDeEnvoltura tiposDeEnvolturas[], Funcion... objetos) {
        return ordenarObjetosEnEnvolturas(new ArrayList<TipoDeEnvoltura>(Arrays.asList(tiposDeEnvolturas)), objetos);
    }

    public static Funcion[] ordenarObjetosEnEnvolturas(ArrayList<TipoDeEnvoltura> tiposDeEnvolturas, Operando... objetos) {
        return ordenarObjetosEnEnvolturas(tiposDeEnvolturas, arregloFuncion(objetos));
    }

    public static Funcion[] ordenarObjetosEnEnvolturas(ArrayList<TipoDeEnvoltura> tiposDeEnvolturas, Funcion... objetos) {
        ArrayList<ArrayList<Funcion>> B = new ArrayList<ArrayList<Funcion>>();
        B.add(new ArrayList<Funcion>(Arrays.asList(objetos)));
        return ordenarObjetosEnEnvolturas(tiposDeEnvolturas, B)[0];
    }

    public static Funcion[][] ordenarObjetosEnEnvolturas(TipoDeEnvoltura tiposDeEnvolturas[], Operando[]... objetos) {
        return ordenarObjetosEnEnvolturas(tiposDeEnvolturas, arregloFuncionAA(objetos));
    }

    public static Funcion[][] ordenarObjetosEnEnvolturas(TipoDeEnvoltura tiposDeEnvolturas[], Funcion[]... objetos) {
        return ordenarObjetosEnEnvolturas(new ArrayList<TipoDeEnvoltura>(Arrays.asList(tiposDeEnvolturas)), objetos);
    }

    public static Funcion[][] ordenarObjetosEnEnvolturas(ArrayList<TipoDeEnvoltura> tiposDeEnvolturas, Operando[]... objetos) {
        return ordenarObjetosEnEnvolturas(tiposDeEnvolturas, arregloFuncionAA(objetos));
    }

    public static Funcion[][] ordenarObjetosEnEnvolturas(ArrayList<TipoDeEnvoltura> tiposDeEnvolturas, Funcion[]... objetos) {
        return ordenarObjetosEnEnvolturas(tiposDeEnvolturas, convertirArreglo2AArrayList(objetos));
    }

    public static Funcion[][] ordenarObjetosEnEnvolturas(ArrayList<TipoDeEnvoltura> tiposDeEnvolturas, ArrayList<ArrayList<Funcion>> objetos) {
        Funcion e[][] = new Funcion[objetos.size()][tiposDeEnvolturas.size()];
        // mostrarArrayList(objetos.get(0));
        //  mostrarArrayList(objetos.get(1));
        for (int j = 0; j < objetos.size(); j++) { //System.out.println("j="+j);
            for (int k = 0; k < tiposDeEnvolturas.size(); k++) {
                for (int i = 0; i < objetos.get(j).size(); i++) {//System.out.println("i="+i);
                    //  System.out.println("a="+a+" objetos.get(j).get(i)="+objetos.get(j).get(i));
                    if (mismaEnvoltura(tiposDeEnvolturas.get(k), objetos.get(j).get(i))) {
                        e[j][k] = objetos.get(j).remove(i); //System.out.println("e[j][i]="+e[j][i]+" j="+j+" i="+i);
                        break;
                    }
                }
            }
//            for (TipoDeEnvoltura a : tiposDeEnvolturas) { //System.out.println("a="+a);
//                
//            }

        }
        return e;
    }

    public static boolean mismaEnvoltura(TipoDeEnvoltura t, Funcion... f) {
        if (f.length == 0 || t != f[0].getTipoDeEnvoltura()) {
            return false;
        }
        return f.length == 1 ? true : mismaEnvoltura(f);
    }

    public static boolean mismaEnvoltura(Funcion... f) {
        if (f.length == 1 || f.length == 0) {
            return false;
        }
        for (int i = 1; i < f.length; i++) {
            if (f[i - 1].getTipoDeEnvoltura() != f[i].getTipoDeEnvoltura()) {
                return false;
            }
        }
        return true;
    }

    public static Funcion crearFuncion(TipoDeEnvoltura t, Operando parametro1, boolean positivo) {

        switch (t) {//case  :  return new ;
            case COSECANTE:
                return new Cosecante(parametro1, positivo);
            case COSENO:
                return new Coseno(parametro1, positivo);
            case COTAGENTE:
                return new Cotagente(parametro1, positivo);
            case LOGARITMO_NAPERIANO:
                return new Logaritmo_Naperiano(parametro1, positivo);
            case PARENTESIS:
                return new Parentesis(parametro1, positivo);
            case SECANTE:
                return new Secante(parametro1, positivo);
            case SENO:
                return new Seno(parametro1, positivo);
            case TANGENTE:
                return new Tangente(parametro1, positivo);
        }
        return new Funcion();
    }

    public static Funcion crearFuncion(TipoDeEnvoltura t, Operando parametro1, Operando parametro2, boolean positivo) {
        switch (t) {//case  :  return new ;
            case EXPONETE:
                return new Exponente(parametro1, parametro2, positivo);
            case LOGARITMO:
                return new Logaritmo(parametro1, parametro2, positivo);
            case RAIZ:
                return new Raiz(parametro1, parametro2, positivo);

        }
        return new Funcion();
    }

    @Override
    protected void inicializarOptimo() {
        for (int i = 0; i < cantidadDeParametros; i++) {
            // System.out.println("parametros.get(i)=" + parametros.get(i) + " " + parametros.get(i).esPositivo() + " parametros.get(i).getOptimo()=" + parametros.get(i).getOptimo() + " " + parametros.get(i).getOptimo().esPositivo() + " 2=" + parametros.get(i).getOptimo() + " " + parametros.get(i).getOptimo().esPositivo());
            parametros.set(i, parametros.get(i).getOptimo());
        }

        optimo = this;

        //----------------
        simplificarResolverFuncion();

    }

    protected void simplificarResolverFuncion() {
        //  boolean resuelto=false;

        for (int i = 0; i < cantidadDeParametros; i++) {
            if (!parametros.get(i).IsNumero()) {
                isNumero = false;
                return;
            }
        }
        double numeros[] = new double[cantidadDeParametros];
        for (int i = 0; i < cantidadDeParametros; i++) {
            numeros[i] = parametros.get(i).getNumero();
        }
        isNumero = true;
        numero = resolverFuncion(inicio, medio, numeros);
        optimo = new Numero(numero);
        //nuuuuuuuuuuuuuuuu
        optimo.setPositivo(optimo.esPositivo(), positivo);
        //nuuuuuuuuuuuuuuuuuu
        // return;

    }

    ;
    
    
//    @Override
//    void inicializarOptimo() {
//        for (int i = 0; i < cantidadDeParametros; i++) {
//            System.out.println("parametros.get(i)=" + parametros.get(i) + " " + parametros.get(i).esPositivo() + " parametros.get(i).getOptimo()=" + parametros.get(i).getOptimo() + " " + parametros.get(i).getOptimo().esPositivo() + " 2=" + parametros.get(i).getOptimo() + " " + parametros.get(i).getOptimo().esPositivo());
//            parametros.set(i, parametros.get(i).getOptimo());
//        }
//        // System.out.println("this=" + this);//!!!!!!!!!!!!
//        //fffffffffffffff
//        // if (tipoDeEnvoltura == TipoDeEnvoltura.PARENTESIS && (!(parametros.get(0) instanceof Ecuacion) || parametros.get(0).isMultiplicacion())) {
//        //ffffffffffff
//        //+++++++++++
//        if (tipoDeEnvoltura == TipoDeEnvoltura.PARENTESIS && !(parametros.get(0) instanceof Suma)) {
//            //+++++++++++
//            //((((((((((((((((((((((((((((((((((((((((9
//            System.out.println("parametros.get(0)=" + parametros.get(0) + " parametros.get(0).copia()=" + parametros.get(0).copia());
//            optimo = parametros.get(0).copia();//es por extraer 
//            System.out.println("1 optimo=" + optimo);
//            optimo.setPositivo(bicondicional(positivo, optimo.esPositivo()));
//            //  optimo.establecerPositivo(positivo);
//            //   optimo.positivo=bicondicional(positivo, optimo.esPositivo());
//            System.out.println("2 optimo=" + optimo + " es=" + optimo.getClass().getSimpleName() + " positivo=" + optimo.esPositivo());
//        } else {
//            optimo = this;
//            //if (tipoDeEnvoltura == TipoDeEnvoltura.EXPONETE) {
//            simplificarFuncionExponente();//es por extraer
//            // optimo.setPositivo(bicondicional(positivo, getParametro1().esPositivo()));
//            //  } else {
//            //if (tipoDeEnvoltura == TipoDeEnvoltura.RAIZ) {
//            simplificarFuncionRaiz();
//            simplificarFuncionLogaritmo();
//                //}
//
//            //optimo = this;
//            // }
//        }
//
//        //----------------
//        for (int i = 0; i < cantidadDeParametros; i++) {
//            System.out.println("cantidadDeParametros=" + cantidadDeParametros);
//            System.out.println("i=" + i + " parametros.get(i)=" + parametros.get(i));
//            System.out.println(" IsNumero=" + parametros.get(i).IsNumero());
//            if (!parametros.get(i).IsNumero()) {
//                isNumero = false;
//                return;
//            }
//        }
//        double numeros[] = new double[cantidadDeParametros];
//        for (int i = 0; i < cantidadDeParametros; i++) {
//            numeros[i] = parametros.get(i).getNumero();
//        }
//        isNumero = true;
//        numero = resolverFuncion(inicio, medio, numeros);
//        optimo = new Numero(numero);
//
//        System.out.println("numero=" + numero);
//
//    }
//
//    protected void simplificarFuncionLogaritmo() {
//        if (tipoDeEnvoltura == TipoDeEnvoltura.LOGARITMO) {
//
//            if (getParametro2() instanceof Funcion && ((Funcion) getParametro2()).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
//
//                if (((Funcion) getParametro2()).getParametro1().igualA(getParametro1())) {
//                    optimo = ((Funcion) getParametro2()).getParametro2();
//                    optimo.setPositivo(positivo, optimo.esPositivo());
//                    return;
//                }
//            }
//
//        }
//
//    }



    protected void simplificarFuncionRaiz() {
        if (tipoDeEnvoltura == TipoDeEnvoltura.RAIZ) {
            //  System.out.println("this=" + this + " p1=" + getParametro1() + " p2=" + getParametro2());
//caso Raiz2deRaiz3deA
            if (getParametro2() instanceof Funcion && ((Funcion) getParametro2()).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                setPositivo(bicondicional(positivo, getParametro2().extraerPositivo()));
                // System.out.println("");
                Operando indice[] = {getParametro1(), ((Funcion) getParametro2()).getParametro1()};
                // System.out.println("indice" + Arrays.toString(indice));
                Operando parametros[] = {new Multiplicacion(indice), ((Funcion) getParametro2()).getParametro2()};
                setParametros(parametros);

//            setParametro1(Ecuacion.inicializarConMultiplicacion(indice));
//            setParametro2(((Funcion) getParametro2()).getParametro2());
            }
            optimo = this;
        }
    }

    public static double resolverFuncion(String inicio, String medio, double... A) {

        if (medio.equals("^")) {
            return Math.pow(A[0], A[1]);
        }

        // if (medio.equals("")) {
        switch (inicio) {// case "": return ;

            case "(":
                return A[0];
            case "ln(":
                return Math.log(A[0]);
            case "sen(":
                return sin(A[0]);
            case "cos(":
                return cos(A[0]);
            case "tan(":
                return tan(A[0]);
            case "cot(":
                return cot(A[0]);
            case "sec(":
                return sec(A[0]);
            case "csc(":
                return csc(A[0]);
            //con medio
            case "sqr[":

                if (A[0] < 2 || !esNatural(A[0]) || A[1] < 0) {
                    throw new ArithmeticException("la raiz tiene parametros incorrectos");
                }
                if (A[0] == 2) {
                    return sqrt(A[1]);
                } else {
                    return sqrt(A[0], A[1]);
                }
            case "log[":
                if (A[0] < 2 || !esNatural(A[0]) || A[1] < 0) {
                    throw new ArithmeticException("el logarimo tiene parametros incorrectos");
                }
                return log(A[0], A[1]);

        }
//        }else{
//        
//        }

        return 0;
    }

    @Override
    public boolean igualA(Operando a, boolean valorarSigno) {
        // System.out.println("fu this=" + this + " a=" + a);
        if (a instanceof Funcion && ((Funcion) a).getTipoDeEnvoltura() == tipoDeEnvoltura) {
            for (int i = 0; i < cantidadDeParametros; i++) {
                if (valorarSigno ? a.esPositivo() != positivo || !((Funcion) a).getParametro(i).igualA(getParametro(i))
                        : !((Funcion) a).getParametro(i).igualA(getParametro(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public void simplificar(boolean continuar) {
        //  mostrarArrayList(parametros);
        for (int i = 0; i < parametros.size(); i++) {
            parametros.get(i).simplificar(continuar);
        }
        //  mostrarArrayList(parametros);
        inicializarOptimo();
        // System.out.println("this=" + this);
        if (continuar) {
            simplificar(false);
        }
    }

    @Override
    public Funcion[] crearArregloSuperclaseSuperior(int leng) {
        return new Funcion[leng];
    }

    @Override
    public Funcion[][] crearArregloSuperclaseSuperior(int filas, int columnas) {
        return new Funcion[filas][columnas];
    }

    @Override
    public Funcion[][][] crearArregloSuperclaseSuperior(int filas, int columnas, int cantidad) {
        return new Funcion[filas][columnas][cantidad];
    }

    @Override
    public boolean contieneVariable(char v) {
        for (int i = 0; i < cantidadDeParametros; i++) {
            if (parametros.get(i).contieneVariable(v)) {
                return true;
            }
        }
        return false;
    }

   

}

//public static ArrayList<String> obtenerInicios() {
//        ArrayList<String> inicios = new ArrayList<String>();
//        for (TipoDeEnvoltura t : TipoDeEnvoltura.values()) {
//            inicios.add(t.getInicio());
//        }
//        return inicios;
//    }
