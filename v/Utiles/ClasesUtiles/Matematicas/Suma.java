/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

//import static Utiles.ClasesUtiles.Matematicas.Multiplicacion.unirOperandosPorMultiplicacion;
import static Utiles.MetodosUtiles.MetodosUtiles.*;
import static Utiles.ClasesUtiles.Matematicas.Funcion.*;
import Utiles.ClasesUtiles.Matematicas.Funciones.Coseno;
import Utiles.ClasesUtiles.Matematicas.Funciones.Exponente;
import Utiles.ClasesUtiles.Matematicas.Funciones.Seno;
//import Utiles.MetodosUtiles.Arreglos;
import static Utiles.MetodosUtiles.Arreglos.arregloObject;
//import static Utiles.MetodosUtiles.Arreglos.MostrarMatriz;
//import static Utiles.MetodosUtiles.Arreglos.arregloObjetc;

import static Utiles.MetodosUtiles.Operaciones.maximoComunDivisor;
import java.util.ArrayList;
import java.util.Arrays;

/**
 *
 * @author Rene
 */
public class Suma extends ArrayListOperando {

    //private ArrayList<Operando> operacionesSuma = new ArrayList<Operando>();
//    public Suma(Suma s) {
//        this(s.getOperacionesSuma(), s.esPositivo());
//    }
    public Suma(Suma s) {
        this(s.getOperandos(), s.esPositivo());
    }

    public Suma(double... numero) {
        this(true, numero);
    }

    public Suma(boolean positivo, double... numero) {
        ArrayList<Operando> numeros = new ArrayList<Operando>();
        for (int i = 0; i < numero.length; i++) {
            numeros.add(new Numero(numero[i]));
        }
        inicializarConstructor(numeros, positivo);
    }

    public Suma(double numero) {
        this(numero, true);
    }

    public Suma(double numero, boolean positivo) {
        this(new Numero(numero), positivo);
    }

    public Suma(Operando o) {
        this(o, true);
    }

    public Suma(Operando o, boolean positivo) {
        operandos.add(o);
        inicializarConstructor(operandos, positivo);
    }

    public Suma(Operando... operacionesSuma) {
        this(operacionesSuma, true);
    }

    public Suma(boolean positivo, Operando... operacionesSuma) {
        this(operacionesSuma, positivo);
    }

    public Suma(Operando operacionesSuma[], boolean positivo) {
        this(new ArrayList<Operando>(Arrays.asList(operacionesSuma)), positivo);
    }

    public Suma(ArrayList<Operando> operacionesSuma) {
        this(operacionesSuma, true);
    }

    public Suma(ArrayList<Operando> operacionesSuma, boolean positivo) {
        inicializarConstructor(operacionesSuma, positivo);
    }

    private void inicializarConstructor(ArrayList<Operando> operacionesSuma, boolean positivo) {
        this.operandos = operacionesSuma;
        // mostrarArrayList(operandos);
        simplificarSuma();
        // mostrarArrayList(operandos);
        this.positivo = positivo;
        // inicializarOptimo();
    }

    @Override
    public String toString() {
        String suma = "";
        //  System.out.println("operacionesSuma.size()=" + operandos.size());
        for (int i = 0; i < operandos.size(); i++) {
            //System.out.println("i=" + i + " operacionesSuma.get(i)=" + operandos.get(i) + " operacionesSuma.get(i).isPositivo()=" + operandos.get(i).esPositivo());
            if (operandos.get(i).IsNumero()) {
                suma += (i != 0 ? (operandos.get(i).getNumero() < 0 ? "" : "+") : "") + operandos.get(i).getNumero();
            } else {
                // suma += (i != 0 ? (operandos.get(i).esPositivo() ? "+" : "") : "") + operandos.get(i);
                suma += (i != 0 ? (operandos.get(i).esPositivo() ? "+" : "") : "") + (operandos.size() > 1 && operandos.get(i).esPositivo() && (operandos.get(i).esSuma() || operandos.get(i).esFraccion()) ? "(" : "") + operandos.get(i) + (operandos.size() > 1 && operandos.get(i).esPositivo() && (operandos.get(i).esSuma() || operandos.get(i).esFraccion()) ? ")" : "");
            }
            //System.out.println("ecucaion1=" + suma);
        }
        return (positivo ? "" : "-(") + eliminarPuntoCero(suma, getPermitidos(), Funcion.getInicios()) + (positivo ? "" : ")");
    }

    @Override
    protected void inicializarOptimo() {
        //System.out.println("this="+this);
        for (int i = 0; i < operandos.size(); i++) {
            operandos.set(i, operandos.get(i).getOptimo());
            // System.out.println("operacionesSuma.get(i)=" + operandos.get(i) + " " + operandos.get(i).esPositivo() + " " + operandos.get(i).getClass().getSimpleName());
        }
        if (simplificarEnUno()) {
            return;
        }
        simplificarNumero();
        simplificarOperandosEnSuma(operandos);
        simplificarNumero();
        if (simplificarEnUno()) {
            return;
        }
//        if (operandos.isEmpty()) {
//            isNumero = true;
//            optimo = new Numero(0);
//            numero = 0;
//            return;
//        }
//        if (operandos.size() == 1) {
//            operandos.get(0).simplificar(false);
//            optimo = operandos.get(0).getOptimo().copia();
//            System.out.println("optimo=" + optimo);
//            if (optimo.IsNumero()) {
//                isNumero = optimo.IsNumero();
//                numero = optimo.getNumero();
//            }
//            return;
//        }
        optimo = this;
//        double subNumero = 0;
//        System.out.println("operacionesSuma.size()=" + operacionesSuma.size());
//        for (int i = 0; i < operacionesSuma.size(); i++) {
//            System.out.println("1 i=" + i + " operacionesSuma.get(i)=" + operacionesSuma.get(i));
//            if (!operacionesSuma.get(i).IsNumero()) {
//                System.out.println("falso");
//                isNumero = false;
//                return;
//            }
//            subNumero += operacionesSuma.get(i).getNumero();
//            System.out.println("subNumero=" + subNumero);
//        }
//        isNumero = true;
//        numero = subNumero;
//        System.out.println("numero=" + numero);
//        optimo = new Numero(numero);
    }

    private boolean simplificarEnUno() {
        boolean retornar = false;
        if (operandos.isEmpty()) {
            isNumero = true;
            optimo = new Numero(0);
            numero = 0;
            retornar = true;
        }
        if (operandos.size() == 1) {
            operandos.get(0).simplificar(false);
            optimo = operandos.get(0).getOptimo().copia();
            // System.out.println("optimo=" + optimo);
            if (optimo.IsNumero()) {
                isNumero = optimo.IsNumero();
                numero = optimo.getNumero();
            }
            retornar = true;
        }
        return retornar;
    }

    private void simplificarSuma() {
        for (int i = 0; i < operandos.size(); i++) {
            //  System.out.println("operandos.get(i)=" + operandos.get(i) + " " + operandos.get(i).getClass().getSimpleName());
            if (operandos.get(i) instanceof Suma) {
                //((Suma)operandos.get(i)).imponerSigno();
                addOperando(operandos.remove(i--));
            }
        }
    }

    private void simplificarNumero() {
        int posicion = -1;
        for (int i = 0; i < operandos.size(); i++) {
            //   System.out.println("i=" + i + " operacionesSuma.get(i)=" + operandos.get(i));
            if (operandos.get(i).IsNumero()) {
                //   System.out.println("es numero");
                if (posicion == -1) {
                    posicion = i;
                    operandos.set(posicion, new Numero(operandos.get(posicion).getNumero()));
                } else {
//                 operacionesSuma.set(posicion,operacionesSuma.get(posicion)+operacionesSuma.get(i));
                    operandos.set(posicion, new Numero(operandos.get(posicion).getNumero() + operandos.get(i).getNumero()));
                    operandos.remove(i);
                    i--;
                }
                //System.out.println("");
            }
        }
        if (posicion != -1 && operandos.get(posicion).getNumero() == 0) {
            operandos.remove(posicion);
        }
    }

    @Override
    public boolean igualA(Operando a, boolean valorarSigno) {
        if (a instanceof Suma) {
            if (((Suma) a).operandos.size() != operandos.size() || (valorarSigno && a.esPositivo() != positivo)) {
                return false;
            }
            for (int i = 0; i < operandos.size(); i++) {
                if (!((Suma) a).operandos.get(i).igualA(operandos.get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    public void imponerSigno() {
        for (int i = 0; i < operandos.size(); i++) {
            operandos.get(i).setPositivo(bicondicional(positivo, operandos.get(i).esPositivo()));
        }
        positivo = true;
    }

    public static Suma inicializarRestando(double... numero) {
        Operando o[] = new Operando[numero.length];
        for (int i = 0; i < numero.length; i++) {
            o[i] = new Numero(numero[i]);
        }
        return inicializarRestando(o);
    }

    public static Suma inicializarRestando(Operando... o) {
        // System.out.println("o[0]=" + o[0] + " " + o[0].getClass().getSimpleName());
        for (int i = 1; i < o.length; i++) {
            //System.out.println("o[i]=" + o[i] + " " + o[i].getClass().getSimpleName());
            o[i].setPositivo(false);
        }
        Suma dos = new Suma(o);
        // System.out.println("dos=" + dos);
        return dos;
    }

    private void simplificarOperandosEnSuma(ArrayList<Operando> m) {
        //   Operando operandoUninonActual = m.get(0).copia();
        Operando operandoUninonActual;
        //System.out.println("operandoUninonActual=" + operandoUninonActual);
        int indiceActual = 0;

        for (int i = 1; m.size() > 1 && i < m.size() && indiceActual < m.size(); i++) {
            //System.out.println(" m.size()=" + m.size() + " i=" + i + " m.get(i)=" + m.get(i) + " " + m.get(i).getClass().getSimpleName());
            if (i == indiceActual) {
                //   System.out.println("salto");
                continue;
            }
            operandoUninonActual = unirOperandosPorSuma(m.get(indiceActual).copia(), m.get(i).copia());
            //System.out.println("operandoUninonActual=" + operandoUninonActual + " " + operandoUninonActual.getClass().getSimpleName() + " m.get(i)=" + m.get(i) + " " + m.get(i).getClass().getSimpleName());
            if (operandoUninonActual.getClass() != Operando.class) {
                m.set(indiceActual, operandoUninonActual.copia());
                // System.out.println("m.get(indiceActual)=" + m.get(indiceActual));            // operandoActual=operandoUninonActual.copia();
                m.remove(i--);
            }
            if (i == m.size() - 1 && indiceActual != m.size() - 1) {
                operandoUninonActual = m.get(++indiceActual).copia();
                //  System.out.println("operandoUninonActual=" + operandoUninonActual + " indiceActual=" + indiceActual);
                i = -1;
            }
        }

    }

    public static Operando unirOperandosPorSuma(Operando A, Operando B) {
        // System.out.println("A=" + A + " B=" + B);
        if (A.igualA(B, false)) {
            if (A.esPositivo() != B.esPositivo()) {
                return new Numero(0);
            } else {
                return new Multiplicacion(new Numero(2), A);
            }
        }
        if (A instanceof Funcion) {
            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.LOGARITMO) {

                if (B instanceof Funcion) {
                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.LOGARITMO) {
                        // log[a](b) + log[a](c) debuelve  log[a](b*c)
                        if (((Funcion) A).getParametro1().igualA(((Funcion) B).getParametro1())) {

                            ((Funcion) A).setParametro1(A.esPositivo() == B.esPositivo() ? new Multiplicacion(((Funcion) A).getParametro2(), ((Funcion) B).getParametro2()) : A.esPositivo() ? new FraccionGeneral(((Funcion) A).getParametro2(), ((Funcion) B).getParametro2()) : new FraccionGeneral(((Funcion) B).getParametro2(), ((Funcion) A).getParametro2()));
//         if(A.esPositivo()==B.esPositivo()){
//         ((Funcion)A).setParametro1(new Multiplicacion(((Funcion)A).getParametro2(),((Funcion)B).getParametro2()));
//         }else{
//          ((Funcion)A).setParametro1(A.esPositivo()?new FraccionGeneral(((Funcion)A).getParametro2(),((Funcion)B).getParametro2()):new FraccionGeneral(((Funcion)B).getParametro2(),((Funcion)A).getParametro2()));  
//         }

                        }
                    }

                }

            }

            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {

                if (B instanceof Funcion) {
                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {

                        // System.out.println("((Funcion) A).getParametro1()=" + ((Funcion) A).getParametro1() + " " + ((Funcion) A).getParametro1().getClass().getSimpleName() + " ((Funcion) B).getParametro1()=" + ((Funcion) B).getParametro1() + " " + ((Funcion) B).getParametro1().getClass().getSimpleName());
                        // System.out.println("((Funcion) A).getTipoDeEnvoltura()=" + ((Funcion) A).getTipoDeEnvoltura() + " ((Funcion) B).getTipoDeEnvoltura()=" + ((Funcion) B).getTipoDeEnvoltura());
                        if ((((Funcion) A).getParametro1() instanceof Funcion && ((Funcion) ((Funcion) A).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.SENO
                                && ((Funcion) B).getParametro1() instanceof Funcion && ((Funcion) ((Funcion) B).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.COSENO)
                                || (((Funcion) A).getParametro1() instanceof Funcion && ((Funcion) ((Funcion) A).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.COSENO
                                && ((Funcion) B).getParametro1() instanceof Funcion && ((Funcion) ((Funcion) B).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.SENO)) {

                            if (((Funcion) A).getParametro2().IsNumero() && ((Funcion) A).getParametro2().getNumero() % 2 == 0
                                    && ((Funcion) B).getParametro2().IsNumero() && ((Funcion) B).getParametro2().getNumero() % 2 == 0) {

                                //caso sen(A)^4 + cos(A)^4 devuelbe 
                                if (((Funcion) ((Funcion) A).getParametro1()).getParametro1().igualA(((Funcion) ((Funcion) B).getParametro1()).getParametro1())
                                        && ((Funcion) A).getParametro2().getNumero() == ((Funcion) B).getParametro2().getNumero()
                                        && ((Funcion) A).getParametro2().getNumero() >= 0 && ((Funcion) A).getParametro2().getNumero() % 2 == 0) {
                                    if (A.esPositivo() == B.esPositivo()) {//(((Funcion) A).getParametro2().getNumero()==2||esEntero(Math.sqrt(((Funcion) A).getParametro2().getNumero())))
                                        return A.esPositivo() ? new Numero(1) : new Numero(-1);
                                    } else {
                                        // System.out.println("((Funcion) ((Funcion) A).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.COSENO="+(((Funcion) ((Funcion) A).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.COSENO));
                                        //  System.out.println("((Funcion) A).getParametro1().esPositivo()="+((Funcion) A).getParametro1().esPositivo()+" ((Funcion) B).getParametro1().esPositivo()="+((Funcion) B).getParametro1().esPositivo());
                                        boolean signo = ((Funcion) ((Funcion) A).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.COSENO ? A.esPositivo() : B.esPositivo();
                                        // System.out.println("signo=" + signo);
                                        return ((Funcion) A).getParametro2().getNumero() == 2 ? new Coseno(new Multiplicacion(new Numero(2), ((Funcion) ((Funcion) A).getParametro1()).getParametro1()), signo)
                                                : new Exponente(new Coseno(new Multiplicacion(new Numero(2), ((Funcion) ((Funcion) A).getParametro1()).getParametro1()), signo), ((Funcion) A).getParametro2().getNumero() / 2);

                                        //  return ((Funcion) A).getParametro2().getNumero() == 2 ? new Funcion(TipoDeEnvoltura.COSENO, new Multiplicacion(new Numero(2), ((Funcion) ((Funcion) A).getParametro1()).getParametro1()), signo)
                                        //      : new Funcion(TipoDeEnvoltura.EXPONETE, new Funcion(TipoDeEnvoltura.COSENO, new Multiplicacion(new Numero(2), ((Funcion) ((Funcion) A).getParametro1()).getParametro1()), signo), ((Funcion) A).getParametro2().getNumero() / 2);
                                    }

                                }

                            }

                        }

                    }

                }
                if ((((Funcion) A).getParametro1() instanceof Funcion && ((Funcion) ((Funcion) A).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.SENO)) {
                    //caso -sen(A)^2 + 1  devuelbe cos(A)^2
                    // System.out.println("((Funcion) A).getParametro1().esPositivo() != B.esPositivo()=" + (((Funcion) A).getParametro1().esPositivo() + " " + B.esPositivo()));
                    // System.out.println("B " + B.getClass().getSimpleName() + " B.IsNumero()=" + B.IsNumero() + " B.getNumero() == 1 " + (B.getNumero() == 1));
                    if (A.esPositivo() != B.esPositivo() && B.IsNumero() && (B.getNumero() == 1 || B.getNumero() == -1)) {
                        return new Exponente(new Coseno(((Funcion) ((Funcion) A).getParametro1()).getParametro1()), 2, B.esPositivo());
                        //return new Funcion(TipoDeEnvoltura.EXPONETE, new Funcion(TipoDeEnvoltura.COSENO, ((Funcion) ((Funcion) A).getParametro1()).getParametro1()), 2, B.esPositivo());
                    }

                }
                if ((((Funcion) A).getParametro1() instanceof Funcion && ((Funcion) ((Funcion) A).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.COSENO)) {
                    //caso -cos(A)^2 + 1  devuelbe sen(A)^2
                    if (A.esPositivo() != B.esPositivo() && B.IsNumero() && (B.getNumero() == 1 || B.getNumero() == -1)) {
                        //hhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhhh
                        return new Exponente(new Seno(((Funcion) ((Funcion) A).getParametro1()).getParametro1()), 2, B.esPositivo());
                        //return new Funcion(TipoDeEnvoltura.EXPONETE, new Funcion(TipoDeEnvoltura.SENO, ((Funcion) ((Funcion) A).getParametro1()).getParametro1()), 2, B.esPositivo());
                    }

                }

            }
//            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.SENO) {
//             if(A.esPositivo()!=B.esPositivo()&&B.IsNumero()&&B.getNumero()==1){
//             return new Funcion(TipoDeEnvoltura.COSENO, B, true);
//             }
//             
//                
//            }

        }

        if (B instanceof Funcion) {
            if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {

                if ((((Funcion) B).getParametro1() instanceof Funcion && ((Funcion) ((Funcion) B).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.SENO)) {
                    //caso 1 -sen(A)^2   devuelbe cos(A)^2
                    if (B.esPositivo() != A.esPositivo() && A.IsNumero() && (A.getNumero() == 1 || A.getNumero() == -1)) {
                        return new Exponente(new Coseno(((Funcion) ((Funcion) B).getParametro1()).getParametro1()), 2, A.esPositivo());
                        //return new Funcion(TipoDeEnvoltura.EXPONETE, new Funcion(TipoDeEnvoltura.COSENO, ((Funcion) ((Funcion) B).getParametro1()).getParametro1()), 2, A.esPositivo());
                    }

                }
                if ((((Funcion) B).getParametro1() instanceof Funcion && ((Funcion) ((Funcion) B).getParametro1()).getTipoDeEnvoltura() == TipoDeEnvoltura.COSENO)) {
                    //caso 1 -cos(A)^2  devuelbe sen(A)^2
                    if (B.esPositivo() != A.esPositivo() && A.IsNumero() && (A.getNumero() == 1 || A.getNumero() == -1)) {
                        return new Exponente(new Seno(((Funcion) ((Funcion) B).getParametro1()).getParametro1()), 2, A.esPositivo());
                        //return new Funcion(TipoDeEnvoltura.EXPONETE, new Funcion(TipoDeEnvoltura.SENO, ((Funcion) ((Funcion) B).getParametro1()).getParametro1()), 2, A.esPositivo());
                    }

                }

            }
        }

        if (A instanceof Multiplicacion && B instanceof Multiplicacion) {
            //System.out.println("A="+A+"  B="+B);
            if (iguales(2, ((Multiplicacion) A).lengh(), ((Multiplicacion) B).lengh())
                    && contienenLasEnvolturasAA(1, true, aTdEs("SENO", "COSENO"),
                            arregloObject(((Multiplicacion) A).getOperando(0), ((Multiplicacion) A).getOperando(1)), arregloObject(((Multiplicacion) B).getOperando(0), ((Multiplicacion) B).getOperando(1)))) {
                          // new Operando[]{((Funcion) A).getParametro1(), ((Funcion) A).getParametro2()},new Operando[]{((Multiplicacion) B).getOperando(0), ((Multiplicacion) B).getOperando(1)})){
                Funcion f[][] = ordenarObjetosEnEnvolturas(aTdEs("SENO", "COSENO"), arregloObject(((Multiplicacion) A).getOperando(0), ((Multiplicacion) A).getOperando(1)), arregloObject(((Multiplicacion) B).getOperando(0), ((Multiplicacion) B).getOperando(1)));
                
//System.out.println(f[0][0]+" "+f[0][1]+"\n"+f[1][0]+" "+f[1][1]);
                //caso sen(x)cos(y) + sen(y)cos(x)  debuelve sen(x+y) 
                if (f[0][0].getParametro1().igualA(f[1][1].getParametro1()) && f[0][1].getParametro1().igualA(f[1][0].getParametro1())) {
                    setPositivoAA(f);
                    f[0][0].getParametro1().setPositivo((A.esPositivo() || B.esPositivo()) ? A.esPositivo() : true);
                    f[0][1].getParametro1().setPositivo((A.esPositivo() || B.esPositivo()) ? B.esPositivo() : true);
                    return new Seno(new Suma(f[0][0].getParametro1(), f[0][1].getParametro1()), A.esPositivo() || B.esPositivo());
                    //  return new Funcion(TipoDeEnvoltura.SENO, new Suma(f[0][0].getParametro1(), f[0][1].getParametro1()), A.esPositivo() || B.esPositivo());
                }

            }

            Multiplicacion coincidentes = new Multiplicacion();
            for (int i = 0; i < ((Multiplicacion) A).lengh(); i++) {
                //  System.out.println("i=" + i);
                if (i == 0 && ((Multiplicacion) A).getOperando(i) instanceof Numero) {
                    continue;
                }
                for (int j = 0; j < ((Multiplicacion) B).lengh(); j++) {
                    //System.out.println("j=" + j);
                    // System.out.println("((Multiplicacion) A).getOperando(i)=" + ((Multiplicacion) A).getOperando(i) + " ((Multiplicacion) B).getOperando(j)=" + ((Multiplicacion) B).getOperando(j));
                    if (j == 0 && ((Multiplicacion) B).getOperando(j) instanceof Numero) {
                        continue;
                    }
                    if (((Multiplicacion) A).getOperando(i).igualA(((Multiplicacion) B).getOperando(j), false)) {
                        //  ((Multiplicacion) A).getOperando(i).setPositivo(((Multiplicacion) A).getOperando(i).esPositivo(),((Multiplicacion) B).getOperando(j).esPositivo());
                        if (((Multiplicacion) A).getOperando(i).esPositivo() ^ ((Multiplicacion) B).getOperando(j).esPositivo()) {
                            if (!((Multiplicacion) A).getOperando(i).esPositivo()) {
                                ((Multiplicacion) A).getOperando(i).setPositivo(true);
                                ((Multiplicacion) A).addOperando(i + 1, new Numero(-1));
                            } else {
                                ((Multiplicacion) B).addOperando(j + 1, new Numero(-1));
                            }
                        }
                        coincidentes.addOperando(((Multiplicacion) A).extraerOperando(i));
                        ((Multiplicacion) B).extraerOperando(j);

                        //  System.out.println("coincidentes=" + coincidentes + " A=" + A + " B=" + B);
                        i--;
                        j--;
                        break;
                    }
                }
            }
            if (!coincidentes.isEmpty()) {
                if (((Multiplicacion) B).getOperando(0) instanceof Numero || ((Multiplicacion) A).getOperando(0) instanceof Numero) {
                    if (((Multiplicacion) A).getOperando(0) instanceof Numero) {
                        if (((Multiplicacion) B).getOperando(0) instanceof Numero) {
                            if (((Multiplicacion) A).lengh() == 1 && ((Multiplicacion) B).lengh() == 1) {
                                coincidentes.addOperando(0, ((Multiplicacion) A).extraerOperando(0).getNumero() + ((Multiplicacion) B).extraerOperando(0).getNumero());
                                // System.out.println("coincidentes=" + coincidentes);
                            } else {
                                // System.out.println("Math.abs(((Multiplicacion) A).getOperando(0).getNumero())=" + Math.abs(((Multiplicacion) A).getOperando(0).getNumero()));
                                //  System.out.println("Math.abs(((Multiplicacion) B).getOperando(0).getNumero())=" + Math.abs(((Multiplicacion) B).getOperando(0).getNumero()));
                                if (Math.abs(((Multiplicacion) A).getOperando(0).getNumero()) != Math.abs(((Multiplicacion) B).getOperando(0).getNumero())) {
                                    //crear metodo mcd para no enteros !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                                    if (esEntero(((Multiplicacion) A).getOperando(0).getNumero()) && esEntero(Math.abs(((Multiplicacion) B).getOperando(0).getNumero()))) {
                                        int mcd = maximoComunDivisor(Math.abs(((Multiplicacion) A).getOperando(0).getNumero()), Math.abs(((Multiplicacion) B).getOperando(0).getNumero()));
                                        // System.out.println("mcd=" + mcd);
                                        if (mcd != 1) {
                                            //  System.out.println("(Multiplicacion) A).getNumero()=" + ((Multiplicacion) A).getOperando(0));
                                            // System.out.println("dovidir="+((Multiplicacion) A).getNumero() / mcd);
                                            ((Numero) ((Multiplicacion) A).getOperando(0)).setNumero(((Multiplicacion) A).getOperando(0).getNumero() / mcd);
                                            //  System.out.println("(Multiplicacion) A).getOperando(0)=" + ((Multiplicacion) A).getOperando(0));
                                            //  System.out.println("(Multiplicacion) B).getNumero()=" + ((Multiplicacion) B).getOperando(0));
                                            // System.out.println("dovidir="+((Multiplicacion) A).getNumero() / mcd);
                                            ((Numero) ((Multiplicacion) B).getOperando(0)).setNumero(((Multiplicacion) B).getOperando(0).getNumero() / mcd);
                                            // System.out.println("(Multiplicacion) B).getOperando(0)=" + ((Multiplicacion) B).getOperando(0));
                                            coincidentes.addOperando(0, mcd);
                                            // System.out.println("coincidentes=" + coincidentes);
                                        }
                                    }

                                } else {
                                    ((Multiplicacion) A).getOperando(0).setPositivo(A.extraerPositivo(), B.extraerPositivo());
                                    coincidentes.addOperando(0, ((Multiplicacion) A).extraerOperando(0).getNumero());
                                    ((Multiplicacion) B).extraerOperando(0);
                                }
                                // coincidentes.setPositivo(signo);

                            }

                        } else {
                            if (((Multiplicacion) A).lengh() == 1 || ((Multiplicacion) B).lengh() == 0) {
                                coincidentes.addOperando(0, ((Multiplicacion) A).extraerOperando(0).getNumero() + 1);
                            }

                        }
                    } else {
                        if (((Multiplicacion) B).lengh() == 1 || ((Multiplicacion) A).lengh() == 0) {
                            coincidentes.addOperando(0, ((Multiplicacion) B).extraerOperando(0).getNumero() + 1);
                        }
                    }

                } else {
                    coincidentes.addOperando(0, 2);
                }
                // System.out.println("coincidentes=" + coincidentes);
                if (!((Multiplicacion) B).isEmpty() || !((Multiplicacion) A).isEmpty()) {
                    Operando unionDeNoCoincidentes = unirOperandosPorSuma((((Multiplicacion) A).isEmpty() ? new Numero(1) : A).copiaOptima(), (((Multiplicacion) B).isEmpty() ? new Numero(1) : B)).copiaOptima();
                    if (unionDeNoCoincidentes.getClass() == Operando.class) {
                        if (!A.esPositivo() && !B.esPositivo()) {
                            coincidentes.setPositivo(false);
                            A.setPositivo(true);
                            B.setPositivo(true);
                        }
                        coincidentes.addOperando(new Suma((((Multiplicacion) A).isEmpty() ? new Numero(1) : A), (((Multiplicacion) B).isEmpty() ? new Numero(1) : B)).getOperandoOptimizado());
                    } else {
                        coincidentes.addOperando(unionDeNoCoincidentes);
                    }
                    //  System.out.println("coincidentes=" + coincidentes);

                }
                coincidentes.actualizarPositivo();
                return coincidentes;
            }
        }
        if (A instanceof Multiplicacion || B instanceof Multiplicacion) {
            Operando o = A instanceof Multiplicacion ? B.copia() : A.copia(); //System.out.println("o="+o);
            if (!(o instanceof Numero)) {
                Multiplicacion m = A instanceof Multiplicacion ? (Multiplicacion) A.copia() : (Multiplicacion) B.copia();
                //  System.out.println("m="+m);
                Multiplicacion coincidentes = new Multiplicacion();
                for (int i = 0; i < m.lengh(); i++) {//System.out.println("i="+i+" m.getOperando(i)="+m.getOperando(i));
                    if (i == 0 && m.getOperando(i) instanceof Numero) {
                        continue;
                    }

                    if (m.getOperando(i).igualA(o, false)) {
                        if (m.getOperando(i).esPositivo() ^ o.esPositivo()) {
                            if (!o.esPositivo()) {
                                o = new Numero(-1);
                            } else {
                                m.addOperando(i + 1, new Numero(-1));
                                o = new Numero(1);
                            }
                        } else {
                            o = new Numero(1);
                        }
                        //   System.out.println("m.getOperando(i)="+m.getOperando(i));
                        coincidentes.addOperando(m.extraerOperando(i));
                        //System.out.println("coincidentes="+coincidentes);

                        //  System.out.println("o="+o);
                        break;
                    }

                }
                if (!coincidentes.isEmpty()) {
                    if (m.lengh() == 1 && m.getOperando(0).IsNumero()) {
                        coincidentes.addOperando(m.getNumeroInicializarOptimo() + o.getNumeroInicializarOptimo());
                    } else {
                        coincidentes.addOperando(new Suma(m, o).getOperandoOptimizado());
                    }
                    return coincidentes;

                }
            }
        }

        return new Operando();
    }

    @Override
    public Suma[] crearArreglo(int leng) {
        return new Suma[leng];
    }

    @Override
    public Suma[][] crearArreglo(int filas, int columnas) {
        return new Suma[filas][columnas];
    }

    @Override
    public Suma[][][] crearArreglo(int filas, int columnas, int cantidad) {
        return new Suma[filas][columnas][cantidad];
    }

    @Override
    public boolean contieneVariable(char v) {
        for (int i = 0; i < operandos.size(); i++) {
            if (operandos.get(i).contieneVariable(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Operando inicializarDerivada(char v) {
        Suma derivacion = (Suma) (derivado = copia());
        for (int i = 0; i < derivacion.getOperandos().size(); i++) {
            if (derivacion.getOperandos().get(i).contieneVariable(v)) {
                derivacion.getOperandos().set(i, derivacion.getOperandos().get(i).inicializarDerivada(v));
            } else {
                derivacion.getOperandos().set(i, new Numero(0));
            }
        }
        return (derivado = derivacion);
    }

}
