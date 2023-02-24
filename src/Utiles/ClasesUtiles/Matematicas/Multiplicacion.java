/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

//import Utiles.ClasesUtiles.Arreglo;
import Utiles.ClasesUtiles.Matematicas.Funciones.*;
import Utiles.MetodosUtiles.Arreglos;
import java.util.ArrayList;
import static Utiles.ClasesUtiles.Matematicas.Formulas.*;
import static Utiles.MetodosUtiles.MetodosUtiles.esEntero;
import static Utiles.MetodosUtiles.MetodosUtiles.bicondicional;
import static Utiles.MetodosUtiles.MetodosUtiles.condicional;
//import static Utiles.MetodosUtiles.MetodosUtiles.eliminarPuntoCero;
//import static Utiles.MetodosUtiles.MetodosUtiles.mostrarArrayList;
import static Utiles.MetodosUtiles.MetodosUtiles.contienenLasClases;
import static Utiles.MetodosUtiles.MetodosUtiles.ordenarObjetosEnClases;
import static Utiles.MetodosUtiles.MetodosUtiles.entre;
//import static Utiles.MetodosUtiles.Arreglos.MostrarMatrizObject;
//import static Utiles.MetodosUtiles.Arreglos.eliminarIndiceObject;
import static Utiles.MetodosUtiles.Arreglos.eliminarObjetos;
import static Utiles.MetodosUtiles.Arreglos.IsEmpty;
import Utiles.MetodosUtiles.Operaciones;
import static Utiles.MetodosUtiles.Operaciones.regulador;

/**
 *
 * @author Rene
 */
public class Multiplicacion extends ArrayListOperando {

    // private ArrayList<Operando> operacionMultiplicacion = new ArrayList<Operando>();
    public Multiplicacion() {
        this(1);
        clear();
    }

    public Multiplicacion(double numero) {
        this(numero, numero >= 0);
    }

    public Multiplicacion(double numero, boolean positivo) {
        this(new Numero(numero), positivo);
    }

    public Multiplicacion(Operando o) {
        this(o, o.esPositivo());
    }

    public Multiplicacion(Operando o, boolean positivo) {
        operandos.add(o);
        inicializarConstructor(operandos, positivo);

    }

    public Multiplicacion(Multiplicacion m, boolean positivo) {
        this(new ArrayList<Operando>(m.getOperandos()), positivo);
    }

    public Multiplicacion(Multiplicacion m) {
        this(new ArrayList<Operando>(m.getOperandos()), m.extraerPositivo());
        //  this(new ArrayList<Operando>(m.getOperacionMultiplicacion()), true);
    }

    public Multiplicacion(Operando... m) {
        this(m[0].esPositivo(), m);
        //  this(Multiplicacion.extraerSignoDeMultiplicacion(Multiplicacion.crearMultiplicacion(m)),m);
//       
//        operacionMultiplicacion=Multiplicacion.crearMultiplicacion(m);
//       // this(Multiplicacion.crearMultiplicacion(m), m.length > 0 ? m[0].esPositivo() : true);
//        inicializarOperador(operacionMultiplicacion, Multiplicacion.extraerSignoDeMultiplicacion(operacionMultiplicacion));
    }

    public Multiplicacion(boolean positivo, Operando... m) {
        //operacionMultiplicacion=Multiplicacion.crearMultiplicacion(m);
        // this(Multiplicacion.crearMultiplicacion(m), m.length > 0 ? m[0].esPositivo() : true);
        inicializarConstructor(Multiplicacion.crearMultiplicacion(m), positivo);
    }

    public Multiplicacion(ArrayList<Operando> operacionMultiplicacion) {
        this(operacionMultiplicacion, operacionMultiplicacion.get(operacionMultiplicacion.size() - 1).extraerPositivo());
        // this(operacionMultiplicacion, Multiplicacion.extraerSignoDeMultiplicacion(operacionMultiplicacion));
    }

    public Multiplicacion(ArrayList<Operando> operacionMultiplicacion, boolean positivo) {
        //   isMultiplicacion=true;
        inicializarConstructor(operacionMultiplicacion, positivo);
    }

    private void inicializarConstructor(ArrayList<Operando> operacionMultiplicacion, boolean positivo) {
        this.operandos = operacionMultiplicacion;
        // mostrarArrayList(operandos);
        //  obtenerSignoDeMultiplicacion(operacionMultiplicacion);
        //  System.out.println("positivo=" + positivo);
        setPositivo(positivo);
        // mostrarArrayList(operandos);
        //  inicializarOptimo();
    }

    public int lengh() {
        return operandos.size();
    }

    public boolean isEmpty() {
        return operandos.isEmpty();
    }

    @Override
    public boolean extraerPositivo() {
        if (isEmpty()) {
            return true;
        }
        return operandos.get(0).extraerPositivo();
    }

//    public ArrayList<Operando> getOperacionMultiplicacion() {
//        return operacionMultiplicacion;
//    }
//
//    public void setOperacionMultiplicacion(ArrayList<Operando> operacionMultiplicacion) {
//        this.operacionMultiplicacion = operacionMultiplicacion;
//        inicializarOptimo();
//        // actualizarPositivo();
//        //this.positivo=operacionMultiplicacion.size()>0?obtenerSignoDeMultiplicacion(operacionMultiplicacion):positivo;
//    }
//
//    public Operando extraerFirstOperando() {
//        return extraerOperando(0);
//    }
//
//    public Operando extraerLastOperando() {
//        return extraerOperando(operacionMultiplicacion.size() - 1);
//    }
//
//    public Operando extraerOperando(int indice) {
//        Operando extraido = operacionMultiplicacion.remove(indice);
//        inicializarOptimo();
//        return extraido;
//    }
//
//    public Operando getFirstOperando() {
//        return getOperando(0);
//    }
//
//    public Operando getLastOperando() {
//        return getOperando(operacionMultiplicacion.size() - 1);
//    }
//
//    public Operando getOperando(int indice) {
//        return operacionMultiplicacion.get(indice);
//    }
//
//    public void setFirstOperando(Operando o) {
//        setOperando(0, o);
//    }
//
//    public void setLastOperando(Operando o) {
//        setOperando(operacionMultiplicacion.size() - 1, o);
//    }
//
//    public void setOperando(int indice, Operando o) {
//        operacionMultiplicacion.set(indice, o);
//        inicializarOptimo();
//    }
//
//    public void addOperando(double numero) {
//        addOperando(operacionMultiplicacion.size(), new Numero(numero));
//    }
//
//    public void addOperando(int indice, double numero) {
//        addOperando(indice, new Numero(numero));
//    }
//
//    public void addOperando(Operando o) {
//        addOperando(operacionMultiplicacion.size(), o);
//    }
//
//    public void addOperando(int indice, Operando o) {
//        if (o instanceof Multiplicacion) {
//            operacionMultiplicacion.addAll(indice, ((Multiplicacion) o).getOperacionMultiplicacion());
//        } else {
//            operacionMultiplicacion.add(indice, o);
//        }
//        inicializarOptimo();
//        // actualizarPositivo();
//    }
    public void actualizarPositivo() {
        this.positivo = operandos.size() > 0 ? obtenerSignoDeMultiplicacion(operandos) : positivo;
    }

//    public void clear() {
//        operandos.clear();
//        positivo = true;
//        optimo = this;
//    }
    @Override
    public void setPositivo(boolean positivo) {
        //actualizarPositivo(); System.out.println("1 this="+this+" positivo="+positivo);
        if (operandos.size() > 0) {
            // operacionMultiplicacion.get(0).setPositivo(bicondicional(positivo, operacionMultiplicacion.get(0).esPositivo()));
            operandos.get(0).setPositivo(positivo);
            this.positivo = operandos.get(0).esPositivo();
            // System.out.println("this=" + this);
        } else {
            this.positivo = bicondicional(positivo, this.positivo);
        }
        // actualizarPositivo(); System.out.println("2 this="+this+" positivo="+positivo);
    }

    static public ArrayList<Operando> crearMultiplicacion(Operando... O) {
        ArrayList<Operando> operacionMultiplicacion = new ArrayList<Operando>();
        for (Operando o : O) {
            operacionMultiplicacion.add(o);
        }
        return operacionMultiplicacion;
    }

    static public boolean extraerSignoDeMultiplicacion(ArrayList<Operando> operacionMultiplicacion) {
        int signo = 1;
        for (int i = 0; i < operacionMultiplicacion.size(); i++) {
            if (!operacionMultiplicacion.get(i).esPositivo()) {
                operacionMultiplicacion.get(i).setPositivo(true);
                signo *= -1;
            }
        }
        return signo == 1;
    }

    static public boolean obtenerSignoDeMultiplicacion(ArrayList<Operando> operacionMultiplicacion) {
        return obtenerSignoDeMultiplicacion(operacionMultiplicacion, true);
    }

    static public boolean obtenerSignoDeMultiplicacion(ArrayList<Operando> operacionMultiplicacion, boolean modificar) {
        int signo = 1;
        for (int i = 0; i < operacionMultiplicacion.size(); i++) {
            // System.out.println("i=" + i + "operacionMultiplicacion.get(i)=" + operacionMultiplicacion.get(i));
            if (!operacionMultiplicacion.get(i).esPositivo()) {
                if (modificar) {
                    operacionMultiplicacion.get(i).setPositivo(true);
                    //System.out.println("1 operacionMultiplicacion.get(i)=" + operacionMultiplicacion.get(i));
                }
                signo *= -1;
            }
            if (i == operacionMultiplicacion.size() - 1 && modificar) {
                operacionMultiplicacion.get(0).setPositivo(signo == 1);
                // System.out.println("2 operacionMultiplicacion.get(i)=" + operacionMultiplicacion.get(0));
            }
        }
        // System.out.println("signo=" + (signo == 1));
        return signo == 1;
    }

    @Override
    public String toString() {
        String multiplicacion = "";
        // boolean encerrar=false;
        for (int i = 0; i < operandos.size(); i++) {
//            if(!encerrar&&(operandos.get(i).esSuma()||operandos.get(i).esFraccion())){
//            encerrar=true;
//            }
            multiplicacion += (i > 0 && operandos.get(i - 1) instanceof Numero && operandos.get(i) instanceof Numero ? "*" : "")
                    + (operandos.get(i).esPositivo() ? "" + (operandos.get(i) instanceof Suma || (operandos.get(i) instanceof Funcion && ((Funcion) operandos.get(i)).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) ? "(" : "") : i != 0 ? "(" : "")
                    + operandos.get(i)
                    + (operandos.get(i).esPositivo() ? "" + (operandos.get(i) instanceof Suma || (operandos.get(i) instanceof Funcion && ((Funcion) operandos.get(i)).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) ? ")" : "") : i != 0 ? ")" : "");
            // multiplicacion +=""+

        }
        // return eliminarPuntoCero(multiplicacion, getPermitidos(), Funcion.getInicios());
        return multiplicacion;
        //  System.out.println("mul "+(encerrar?"(":"")+ multiplicacion+(encerrar?")":""));
        //  return (encerrar?"(":"")+ multiplicacion+(encerrar?")":"");
    }

    @Override
    protected void inicializarOptimo() {
        // System.out.println("this=" + this);
        actualizarPositivo();
        // System.out.println("this=" + this);
        for (int i = 0; i < operandos.size(); i++) {
            // System.out.println("operacionMultiplicacion.get(i)=" + operandos.get(i) + " optimo=" + operandos.get(i).getOptimo());
            operandos.set(i, operandos.get(i).getOptimo());
        }
        if (SiseEsUno()) {
            return;
        }

        if (operandos.size() > 0) {
            simplificarMultiplicacion(operandos);
            // System.out.println("1 this=" + this);
           
            eliminarUnosEnMultiplicacion(operandos);
            // System.out.println("1 this=" + this);
            simplificarNumeros(operandos);
            // System.out.println("1 this=" + this);
            simplificarOperandosEnMultiplicacion(operandos);
            //System.out.println("1 this=" + this);
            simplificarFracciones(operandos);
            // System.out.println("1 this=" + this);
            actualizarPositivo(); 
            for (int i = 0; i < operandos.size(); i++) {
                if(operandos.get(i).IsNumero()&&operandos.get(i).getNumero()==0){
                optimo=new Numero(0);
                return;
                }
            }
            //System.out.println("1 this=" + this);
            if (SiseEsUno()) {
                return;
            }
//            if (operandos.size() == 1) {
//                optimo = operandos.get(0).copia();
//                if (optimo.IsNumero()) {
//                    isNumero = optimo.IsNumero();
//                    numero = optimo.getNumero();
//                }
//                return;
//            }
            
        }
        optimo = this;
//        double multiplicacion = 1;
//        for (int j = 0; j < operacionMultiplicacion.size(); j++) {
//            if (!operacionMultiplicacion.get(j).IsNumero()) {
//                System.out.println("falso");
//                isNumero = false;
//                return;
//            }
//            multiplicacion *= operacionMultiplicacion.get(j).getNumero();
//            System.out.println("multiplicacion=" + multiplicacion);
//        }
//        isNumero = true;
//        numero = multiplicacion;
//        System.out.println("numero=" + numero);
//        optimo = new Numero(numero);
    }

    private boolean SiseEsUno() {
        if (operandos.size() == 1) {
            optimo = operandos.get(0).copia();
            if (optimo.IsNumero()) {
                isNumero = optimo.IsNumero();
                numero = optimo.getNumero();
            }
            return true;
        }
        return false;
    }

    private void eliminarUnosEnMultiplicacion(ArrayList<Operando> m) {
        int signo = 1;
        for (int i = 0; i < m.size() && m.size() > 1; i++) {
            if (m.get(i).IsNumero() && m.get(i).getNumero() == 1) {
                // signo *= (operacionesMultiplicacion.get(j).get(i).getNumero()<0?-1:1);
                m.remove(i);
                i--;
                continue;
            }
            if (m.get(i).IsNumero() && m.get(i).getNumero() == -1) {
                signo *= -1;
                m.remove(i);
                i--;
                //  continue;
            }
        }
        if (m.size() > 0) {
            m.get(0).setPositivo(bicondicional(m.get(0).esPositivo(), signo == 1));
            //   System.out.println("2 operacionesMultiplicacion.get(j).get(0)=" + m.get(0) + " " + m.get(0).esPositivo() + " " + m.get(0).getClass().getSimpleName());
        }
    }

    private void simplificarFracciones() {
        simplificarFracciones(operandos);
    }

    private void simplificarFracciones(ArrayList<Operando> m) {
        int posicionFraccion = -1;
//        System.out.println("operacionesMultiplicacion.size()=" + operacionesMultiplicacion.size());
        // for (int j = 0; j < operacionesMultiplicacion.size(); j++) {
        //System.out.println("o j=" + j);
        for (int i = 0; i < m.size() && m.size() > 1; i++) {
            //System.out.println("operacionesMultiplicacion.get(j).size()=" + m.size() + " i=" + i);
            // System.out.println("operacionesMultiplicacion.get(j).get(i)=" + m.get(i) + " " + m.get(i).getClass().getSimpleName());
            if (m.get(i) instanceof FraccionGeneral) {

                if (posicionFraccion == -1) {
                    posicionFraccion = i;
                    //  System.out.println("posicionFraccion=" + posicionFraccion);

                } else {
                    ((FraccionGeneral) m.get(posicionFraccion)).addNumerador(((FraccionGeneral) m.get(i)).getNumerador());
                    // System.out.println("1 operacionesMultiplicacion.get(j).get(posicionFraccion)=" + m.get(posicionFraccion));
                    ((FraccionGeneral) m.get(posicionFraccion)).addDenominador(((FraccionGeneral) m.get(i)).getDenominador());
                    //  System.out.println("2 operacionesMultiplicacion.get(j).get(posicionFraccion)=" + m.get(posicionFraccion));
                    m.get(posicionFraccion).setPositivo(bicondicional(m.get(posicionFraccion).extraerPositivo(), m.get(i).extraerPositivo()));
                    m.remove(i);
                    i--;
                }

            } else {
                if (posicionFraccion != -1) {
                    ((FraccionGeneral) m.get(posicionFraccion)).addNumerador(m.get(i));
                    //  System.out.println("3 operacionesMultiplicacion.get(j).get(posicionFraccion)=" + m.get(posicionFraccion));
                    m.remove(i);
                    i--;
                }

            }
        }
        if (posicionFraccion > 0) {

            for (int i = posicionFraccion - 1; i > -1; i--) {
                //  System.out.println("i=" + i);
//                    FraccionGeneral f = ((FraccionGeneral) operacionesMultiplicacion.get(j).get(posicionFraccion));
//                    operacionesMultiplicacion.get(j).remove(posicionFraccion);
//                    f.addNumerador(operacionesMultiplicacion.get(j).get(i));
//                    operacionesMultiplicacion.get(j).set(i, f);
//                    posicionFraccion = i;
                //---------------------
                ((FraccionGeneral) m.get(posicionFraccion)).addNumerador(m.get(i));
                m.remove(i);
                posicionFraccion--;
            }
        }//kajd/chlb

        // posicionFraccion = -1;
        //}
    }

    private void simplificarMultiplicacion() {
        simplificarMultiplicacion(operandos);
    }

    private void simplificarMultiplicacion(ArrayList<Operando> m) {
        for (int j = 0; j < m.size(); j++) {
            if (m.get(j) instanceof Multiplicacion && !m.get(j).IsNumero()) {
                ArrayList<Operando> m2 = ((Multiplicacion) m.get(j)).getOperandos();
                m.remove(j--);
                m.addAll(m2);
            }
        }
        // actualizarPositivo();

    }

    private void simplificarNumeros() {
        simplificarNumeros(operandos);
    }

    private void simplificarNumeros(ArrayList<Operando> m) {
        int posicion = -1;
        for (int i = 0; i < m.size(); i++) {
            if (m.get(i).IsNumero()) {
                if (posicion == -1) {
                    posicion = i;
                    m.set(posicion, new Numero(m.get(posicion).getNumero()));

                } else {
                    m.set(posicion, new Numero(regulador(m.get(posicion).getNumero() * m.get(i).getNumero())));
                    m.remove(i);
                    i--;
                }
                continue;
            }
        }
        if (posicion != -1) {
            operandos.add(0, operandos.remove(posicion));
        }
    }

    private void simplificarOperandosEnMultiplicacion() {
        simplificarOperandosEnMultiplicacion(operandos);
    }

    private void simplificarOperandosEnMultiplicacion(ArrayList<Operando> m) {
        // System.out.println("this=" + this);
        if (contienenLasClases(1, m, "Numero", "Seno", "Coseno")) {
            Operando O[][] = ordenarObjetosEnClases(m, "Numero", "Seno", "Coseno");

            if (O[0][0].getNumero() % 2 == 0) {
                Finalizar:
                for (int i = 0; i < O[1].length; i++) {
                    // System.out.println("i=" + i);
                    for (int j = 0; j < O[2].length; j++) {
                        //  System.out.println("j=" + j);
                        //caso 4sen(x)cos(x) devuelbe sen(2x)
                        if (((Seno) O[1][i]).getParametro1().igualA(((Coseno) O[2][j]).getParametro1())) {//O[0][0].esPositivo()
                            addOperando(new Seno(new Multiplicacion(new Numero(2), ((Seno) O[1][i]).getParametro1())).getOperandoOptimizado());
                            if (Math.abs(O[0][0].getNumero()) == 2) {
                                getLastOperando().setPositivo(O[0][0].esPositivo());
                                O = eliminarObjetos(O, O[0][0], O[1][i], O[2][j]);
                                break Finalizar;
                            } else {
                                O[0][0] = new Numero(O[0][0].getNumero() / 2);
                                // System.out.println("O[1].length=" + O[1].length + " O[2].length=" + O[2].length);
                                O = eliminarObjetos(O, O[1][i], O[2][j]);
                                // System.out.println("O[1].length=" + O[1].length + " O[2].length=" + O[2].length);
                                if (O[1].length > 0 && O[2].length > 0 && O[0][0].getNumero() > 2 && esEntero(O[0][0].getNumero())) {
                                    i = -1;
                                    break;
                                } else {
                                    break Finalizar;
                                }
                            }

                        }
                    }
                }
                if (!IsEmpty(O)) {
                    addOperandoAA(O);
                }

            }

//            MostrarMatrizObject(O);
            //    mostrarArrayList(m);
        }
        if (contienenLasClases(1, m, "Numero", "Exponente", "Coseno")) {
            Operando O[][] = ordenarObjetosEnClases(m, "Numero", "Exponente", "Coseno");
            if (O[0][0].getNumero() % 2 == 0) {
                Finalizar:
                for (int i = 0; i < O[1].length; i++) {
                    if (((Exponente) O[1][i]).getParametro1() instanceof Multiplicacion) {

                    } else {
                        if (((Exponente) O[1][i]).getParametro1() instanceof Seno) {
                            if (((Exponente) O[1][i]).getParametro2() instanceof Suma) {

                            }//
                            if (((Exponente) O[1][i]).getParametro2() instanceof Numero && entre(2, ((Exponente) O[1][i]).getParametro2().getNumero(), 10, true) && esEntero(((Exponente) O[1][i]).getParametro2().getNumero())) {
                                Multiplicacion respuesta = new Multiplicacion();
                                for (int j = 0; j < O[2].length;) {
                                    // System.out.println("j=" + j);
                                    // System.out.println("O[2][j]=" + O[2][j]);
                                    if (O[0][0].getNumero() % 2 == 0 && ((Exponente) O[1][i]).getParametro2().getNumero() > 0 && ((Seno) ((Exponente) O[1][i]).getParametro1()).getParametro1().igualA(((Coseno) O[2][j]).getParametro1())) {
                                        //double indice = ((Exponente) O[1][i]).getParametro2().getNumero();

                                        // while (indice-- > 0 && O[0][0].getNumero() % 2 == 0) {
                                        // System.out.println("((Exponente) O[1][i]).getParametro2()=" + ((Exponente) O[1][i]).getParametro2());
                                        ((Exponente) O[1][i]).setParametro2(((Exponente) O[1][i]).getParametro2().getNumero() - 1);
                                        //System.out.println("((Exponente) O[1][i]).getParametro2()=" + ((Exponente) O[1][i]).getParametro2());
                                        // System.out.println("O[0][0]=" + O[0][0]);
                                        O[0][0] = new Numero(O[0][0].getNumero() / 2);
                                        // System.out.println("O[0][0]=" + O[0][0]);
                                        respuesta.addOperando(sen2x(((Seno) ((Exponente) O[1][i]).getParametro1()).getParametro1().copia()));
                                        //  System.out.println("respuesta=" + respuesta);
                                        //  }
                                        eliminarObjetos(O, O[2][j]);
                                    } else {
                                        j++;
                                    }

                                }
                                if (((Exponente) O[1][i]).getParametro2().getNumero() != 0) {
                                    // ((Exponente) O[1][i]).setParametro2(((Exponente) O[1][i]).getParametro2().getNumero());
                                    //  System.out.println("O[1][i]="+O[1][i]);
                                    respuesta.addOperando(O[1][i].copiaOptima());
                                    // System.out.println("respuesta=" + respuesta);
                                }
                                addOperando(respuesta);
                                //  System.out.println("this="+this);

                                eliminarObjetos(O, O[1][i]);
                                i--;
                                break;
                            }

                        }

                    }

                }

            }
            if (!IsEmpty(O)) {
                addOperandoAA(O);
            }

        }

        Operando operandoUninonActual = m.get(0).copia();
        //  System.out.println("operandoUninonActual=" + operandoUninonActual);
        int indiceActual = 0;
        //   Operando operandoActual = m.get(0);
        for (int i = 1; m.size() > 1 && i < m.size() && indiceActual < m.size(); i++) {
            // System.out.println(" m.size()=" + m.size() + " i=" + i + " m.get(i)=" + m.get(i) + " " + m.get(i).getClass().getSimpleName());
            if (i == indiceActual) {
                //  System.out.println("salto");
                continue;
            }
            operandoUninonActual = unirOperandosPorMultiplicacion(m.get(indiceActual).copia(), m.get(i).copia());
            // System.out.println("operandoUninonActual=" + operandoUninonActual + " " + operandoUninonActual.getClass().getSimpleName() + " m.get(i)=" + m.get(i) + " " + m.get(i).getClass().getSimpleName());
            if (operandoUninonActual.getClass() != Operando.class) {
                m.set(indiceActual, operandoUninonActual.copia());
                //System.out.println("m.get(indiceActual)=" + m.get(indiceActual));            // operandoActual=operandoUninonActual.copia();
                m.remove(i--);
            }
            if (i == m.size() - 1 && indiceActual != m.size() - 1) {
                operandoUninonActual = m.get(++indiceActual).copia();
                //System.out.println("operandoUninonActual=" + operandoUninonActual + " indiceActual=" + indiceActual);
                i = -1;
            }
        }

    }

    public static Operando unirOperandosPorMultiplicacion(Operando A, Operando B) {
        // System.out.println("A=" + A + " B=" + B);
        //caso A*A en que ambos son iguales a a debuelve A^2
        if (A.igualA(B, false)) {
            return new Exponente(A, new Numero(2), bicondicional(A.esPositivo(), B.esPositivo()));
            // return new Funcion(TipoDeEnvoltura.EXPONETE, A, new Numero(2), bicondicional(A.esPositivo(), B.esPositivo()));
        }

        if (A instanceof Funcion) {
            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.LOGARITMO
                    && ((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.LOGARITMO) {
                //caso log[a](b) log[c](a) debuelve log[c](b)
                if (((Funcion) A).getParametro1().igualA(((Funcion) B).getParametro2())) {
                    A.setPositivo(bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                    ((Funcion) A).setParametro1(((Funcion) B).getParametro1());
                    return A;
                }
                //caso log[a](c) log[c](b) debuelve log[a](b)
                if (((Funcion) A).getParametro2().igualA(((Funcion) B).getParametro1())) {
                    A.setPositivo(bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                    ((Funcion) A).setParametro2(((Funcion) B).getParametro2());
                    return A;
                }

            }

            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                //caso en que A^2 A debuelve A^2+1
                if (((Funcion) A).getParametro1().igualA(B, false) && condicional(B.esPositivo(), ((Funcion) A).getParametro1().esPositivo())) {
                    A.setPositivo(((Funcion) A).getParametro1().esPositivo() ? bicondicional(A.extraerPositivo(), B.extraerPositivo()) : A.esPositivo());
                    ((Funcion) A).setParametro2(new Suma(new Numero(1), ((Funcion) A).getParametro2()));
                    return A;
                }

                if (B instanceof Funcion) {

                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                        //caso A^2 sqr[3](A) debuelve A^(2+1/3)
                        if (((Funcion) A).getParametro1().igualA(((Funcion) B).getParametro2())) {
                            A.setPositivo(A.extraerPositivo(), B.extraerPositivo());
                            ((Funcion) A).setParametro2(new Suma(((Funcion) A).getParametro2(), new FraccionGeneral(new Numero(1), ((Funcion) B).getParametro1())));
                            return A;
                        }

                    }
                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                        //caso a^b c^b debuelve (a*c)^b 
                        if (((Funcion) A).getParametro2().igualA(((Funcion) B).getParametro2())) {
                            A.setPositivo(A.extraerPositivo(), B.extraerPositivo());
                            ((Funcion) A).setParametro1(new Multiplicacion(((Funcion) A).getParametro1(), ((Funcion) B).getParametro1()));
                            //  System.out.println("((Funcion) A).getParametro1()=" + ((Funcion) A).getParametro1() + " A=" + A);
                            return A;
                        }

                        //caso A^2 A^3 devuelbe A^2+3
                        if (((Funcion) A).getParametro1().igualA(((Funcion) B).getParametro1())) {
                            A.setPositivo(A.extraerPositivo(), B.extraerPositivo());
                            ((Funcion) A).setParametro2(new Suma(((Funcion) A).getParametro2(), ((Funcion) B).getParametro2()));
                            return A;
                        }

                    }

                }

                if (B instanceof Numero) {
                    //asumo que ((Exponente)A).getParametro2().IsNumero() es superior a 2
                    if (((Exponente) A).getParametro1() instanceof Multiplicacion && B.getNumero() % 2 == 0
                            && contienenLasClases(1, ((Multiplicacion) ((Exponente) A).getParametro1()).getOperandos(), "Seno", "Coseno")) {

                        if (((Exponente) A).getParametro2().IsNumero() && ((Exponente) A).getParametro2().getNumero() < 10 && ((Exponente) A).getParametro2().getNumero() >= 2 && esEntero(((Exponente) A).getParametro2().getNumero())) {

                            Operando O[][] = ordenarObjetosEnClases(((Multiplicacion) ((Exponente) A).getParametro1()).getOperandos(), "Seno", "Coseno");
                            Multiplicacion salida = new Multiplicacion();
                            Finalizar:
                            for (int i = 0; i < O[0].length; i++) {
                                for (int j = 0; j < O[1].length; j++) {
                                    //caso 4(sen(x)cos(x))^2 devuelbe sen(2x)^2
                                    if (((Seno) O[0][i]).getParametro1().igualA(((Coseno) O[1][j]).getParametro1())) {
                                        boolean signo = B.extraerPositivo();

                                        ArrayList<Operando> descomposicion = new ArrayList<Operando>();
                                        descomposicion.add(new Numero(B.getNumero()));
                                        double indice = ((Exponente) A).getParametro2().getNumero();
                                        while (indice > 0) {
                                            indice--;
                                            descomposicion.add(O[0][i].copia());
                                            descomposicion.add(O[1][j].copia());
                                        }
                                        Multiplicacion d = new Multiplicacion();
                                        d.setOperandos(descomposicion);
                                        d.simplificarOperandosEnMultiplicacion();
                                        d.simplificarNumeros();
                                        if (d.getOperando(0).IsNumero()) {
                                            B = new Numero(d.extraerOperando(0).getNumero());
                                        } else {
                                            B = new Numero(1);
                                        }
                                        B.setPositivo(signo);
                                        salida.addOperando(d);

//********
//                                    salida.addOperando(new Seno(new Multiplicacion(new Numero(2), ((Seno) O[0][i]).getParametro1()), bicondicional(O[0][i].extraerPositivo(), O[1][j].extraerPositivo()) ? true : ((Exponente) A).getParametro2().getNumero() % 2 == 0));
//                                    
//                                    while (B.getNumero() % 2 == 0 && indice > 0) {
//                                        B = new Numero(B.getNumero() / 2).copiaOptima();
//                                        indice--;
//                                        salida.addOperando(new Seno(new Multiplicacion(new Numero(2),((Seno) O[0][i]).getParametro1().copia())));    
//                                    }
//                                   
//                                    if(indice!=0){
//                                    salida.addOperando(O[0][i].copia(),O[1][j].copia());
//                                    }
                                        O = eliminarObjetos(O, O[0][i], O[1][j]);

                                        //   
                                        //********  
                                        if (B.getNumero() == 0 || B.getNumero() % 2 != 0) {
                                            break Finalizar;
                                        }
                                    }
                                }
                            }

                            if (!Arreglos.IsEmpty(O)) {
                                ((Multiplicacion) ((Exponente) A).getParametro1()).addOperandoAA(O);
                            }
                            if (!salida.isEmpty()) {
                                salida.setPositivo(B.extraerPositivo());
                                salida.simplificar();
                                if (((Multiplicacion) ((Exponente) A).getParametro1()).isEmpty()) {
                                    if (B.getNumero() != 1) {
                                        return new Multiplicacion(B, salida).copiaOptima();
                                    } else {
                                        return salida;
                                    }

                                } else {
                                    Multiplicacion respuesta = new Multiplicacion();
                                    if (B.getNumero() != 1) {
                                        respuesta = new Multiplicacion(B, A, salida);
                                    } else {
                                        respuesta = new Multiplicacion(A, salida);
                                    }
                                    respuesta.simplificar();
                                    if (respuesta.toString().length() <= (B.toString().length() + A.toString().length())) {
                                        return respuesta;
                                    }

                                }

                            }
                        }

                        if (((Exponente) A).getParametro2() instanceof Suma && ((Suma) ((Exponente) A).getParametro2()).contineneUnNumero()) {
                            // int indice=((Suma)((Exponente) A).getParametro2()).firstIndexOfNumero();
                            double indiceNumero = ((Suma) ((Exponente) A).getParametro2()).getOperando(((Suma) ((Exponente) A).getParametro2()).firstIndexOfNumero()).getNumero();
                            if (indiceNumero < 10 && indiceNumero >= 2 && esEntero(indiceNumero)) {
                                // Numero indice=new Numero(((Suma)((Exponente) A).getParametro2()).extraerOperando(((Suma)((Exponente) A).getParametro2()).firstIndexOfNumero()).getNumero());
                                Multiplicacion respuesta = new Multiplicacion(B, new Exponente(((Exponente) A).getParametro1().copia(), new Numero(((Suma) ((Exponente) A).getParametro2()).extraerOperando(((Suma) ((Exponente) A).getParametro2()).firstIndexOfNumero()).getNumero())));
                                respuesta.simplificarOperandosEnMultiplicacion();
                                return new Multiplicacion(respuesta, A);
                            }
                        }
                    }

                }

            }

            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                //caso en que sqr[2](A) A debuelve A^(1/2+1)
                if (((Funcion) A).getParametro2().igualA(B, false) && condicional(B.esPositivo(), ((Funcion) A).getParametro2().esPositivo())) {
                    return new Exponente(B, new Suma(new Numero(1), new FraccionGeneral(new Numero(1), ((Funcion) A).getParametro1())), ((Funcion) A).getParametro2().esPositivo() ? bicondicional(A.extraerPositivo(), B.extraerPositivo()) : A.extraerPositivo());
                    //return new Funcion(TipoDeEnvoltura.EXPONETE, B, new Suma(new Numero(1), new FraccionGeneral(new Numero(1), ((Funcion) A).getParametro1())),((Funcion) A).getParametro2().esPositivo()? bicondicional(A.extraerPositivo(), B.extraerPositivo()):A.extraerPositivo());
                }
                if (B instanceof Funcion && ((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                    //caso sqr[a](b) sqr[a](c)  devuelbe sqr[a](bc)
                    if (((Funcion) A).getParametro1().igualA(((Funcion) B).getParametro1())) {
                        A.setPositivo(A.esPositivo(), B.esPositivo());
                        ((Funcion) A).setParametro2(new Multiplicacion(((Funcion) A).getParametro2(), ((Funcion) B).getParametro2()));
                        return A;
                    }

                    //caso sqr[2](A) sqr[3](A)  devuelbe A^(1/2+1/3)   
                    if (((Funcion) A).getParametro2().igualA(((Funcion) B).getParametro2())) {
                        return new Exponente(((Funcion) A).getParametro2(), new Suma(new FraccionGeneral(new Numero(1), ((Funcion) A).getParametro1()), new FraccionGeneral(new Numero(1), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                        // return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) A).getParametro2(), new Suma(new FraccionGeneral(new Numero(1), ((Funcion) A).getParametro1()), new FraccionGeneral(new Numero(1), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                    }
                }

                //Funcion a=(Funcion)((Funcion) A).getParametro2();
                if (((Funcion) A).getParametro2() instanceof Funcion && ((Funcion) ((Funcion) A).getParametro2()).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                    //caso sqr[2](A^3) A devuelbe  A^(3/2+1)
                    if (((Funcion) ((Funcion) A).getParametro2()).getParametro1().igualA(B, false) && condicional(B.esPositivo(), ((Funcion) ((Funcion) A).getParametro2()).getParametro1().esPositivo())) {
                        return new Exponente(B, new Suma(new Numero(1), new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1())), ((Funcion) ((Funcion) A).getParametro2()).getParametro1().esPositivo() ? bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()) : bicondicional(A.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()));
                        //  return new Funcion(TipoDeEnvoltura.EXPONETE, B, new Suma(new Numero(1), new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1())),((Funcion) ((Funcion) A).getParametro2()).getParametro1().esPositivo()? bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()):bicondicional(A.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()));
                    }

                    if (B instanceof Funcion) {

                        //caso sqr[2](A^3) A^4
                        if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE && ((Funcion) ((Funcion) A).getParametro2()).getParametro1().igualA(((Funcion) B).getParametro1())) {
                            return new Exponente(((Funcion) B).getParametro1(), new Suma(((Funcion) B).getParametro2(), new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()));
                            //  return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) B).getParametro1(), new Suma(((Funcion) B).getParametro2(), new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(),  ((Funcion) A).getParametro2().extraerPositivo()));

                        }

                        if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                            //caso sqr[2](A^3) sqr[4](A) devuelbe A^(3/2+1/4) 
                            if (((Funcion) ((Funcion) A).getParametro2()).getParametro1().igualA(((Funcion) B).getParametro2())) {
                                return new Exponente(((Funcion) B).getParametro2(), new Suma(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), new FraccionGeneral(new Numero(1), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()));
                                //  return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) B).getParametro2(), new Suma(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), new FraccionGeneral(new Numero(1), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()));
                            }

                            if (((Funcion) B).getParametro2() instanceof Funcion && ((Funcion) ((Funcion) B).getParametro2()).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                                //caso sqr[2](A^3) sqr[4](A^5) devuelbe A^(3/2+5/4) 
                                if (((Funcion) ((Funcion) A).getParametro2()).getParametro1().igualA(((Funcion) ((Funcion) B).getParametro2()).getParametro1())) {
                                    return new Exponente(((Funcion) ((Funcion) B).getParametro2()).getParametro1(), new Suma(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo(),
                                            ((Funcion) B).getParametro2().extraerPositivo()));
                                    // return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) ((Funcion) B).getParametro2()).getParametro1(), new Suma(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(),  ((Funcion) A).getParametro2().extraerPositivo(),
                                    //          ((Funcion) B).getParametro2().extraerPositivo()));
                                }
                            }

                        }

                    }

                }

            }

        }

        if (B instanceof Funcion) {
            if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                //caso en que B B^2  debuelve B^2+1
                if (((Funcion) B).getParametro1().igualA(A, false) && condicional(A.esPositivo(), ((Funcion) B).getParametro1().esPositivo())) {
                    B.setPositivo(((Funcion) B).getParametro1().esPositivo() ? bicondicional(A.extraerPositivo(), B.extraerPositivo()) : B.esPositivo());
                    ((Funcion) B).setParametro2(new Suma(new Numero(1), ((Funcion) B).getParametro2()));
                    return B;
                }

                if (A instanceof Funcion) {

                    if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                        //caso B^2 sqr[3](B)
                        if (((Funcion) B).getParametro1().igualA(((Funcion) A).getParametro2())) {
                            B.setPositivo(B.extraerPositivo(), A.extraerPositivo());
                            ((Funcion) B).setParametro2(new Suma(((Funcion) B).getParametro2(), new FraccionGeneral(new Numero(1), ((Funcion) A).getParametro1())));
                            return B;
                        }

                    }

                }

                //rrrrrrrrrrrr
                if (A instanceof Numero) {
                 //   System.out.println("A=" + A + " B=" + B);

                    //asumo que ((Exponente)A).getParametro2().IsNumero() es superior a 2
                    if (((Exponente) B).getParametro1() instanceof Multiplicacion && A.getNumero() % 2 == 0
                            && contienenLasClases(1, ((Multiplicacion) ((Exponente) B).getParametro1()).getOperandos(), "Seno", "Coseno")) {

                        //  if (((Exponente) B).getParametro2().IsNumero()) {
                        //  System.out.println("((Exponente) B).getParametro2().getNumero()=" + ((Exponente) B).getParametro2().getNumero());
                        //  System.out.println("((Exponente) B).getParametro2().getNumero() < 10=" + (((Exponente) B).getParametro2().getNumero() < 10));
                        //   System.out.println("((Exponente) B).getParametro2().getNumero() > 2=" + (((Exponente) B).getParametro2().getNumero() > 2));
                        // System.out.println("esEntero(((Exponente) B).getParametro2().getNumero())=" + esEntero(((Exponente) B).getParametro2().getNumero()));
                        // }
                        if (((Exponente) B).getParametro2().IsNumero() && ((Exponente) B).getParametro2().getNumero() < 10 && ((Exponente) B).getParametro2().getNumero() >= 2 && esEntero(((Exponente) B).getParametro2().getNumero())) {

                            Operando O[][] = ordenarObjetosEnClases(((Multiplicacion) ((Exponente) B).getParametro1()).getOperandos(), "Seno", "Coseno");
                            Multiplicacion salida = new Multiplicacion();
                            Finalizar:
                            for (int i = 0; i < O[0].length; i++) {
                                for (int j = 0; j < O[1].length; j++) {
                                    //caso 4(sen(x)cos(x))^2 devuelbe sen(2x)^2
                                    if (((Seno) O[0][i]).getParametro1().igualA(((Coseno) O[1][j]).getParametro1())) {
                                        boolean signo = A.extraerPositivo();

                                        ArrayList<Operando> descomposicion = new ArrayList<Operando>();
                                        descomposicion.add(new Numero(A.getNumero()));
                                        double indice = ((Exponente) B).getParametro2().getNumero();
                                        while (indice > 0) {
                                            indice--;
                                            descomposicion.add(O[0][i].copia());
                                            descomposicion.add(O[1][j].copia());
                                        }
                                        Multiplicacion d = new Multiplicacion();
                                        d.setOperandos(descomposicion);
                                        d.simplificarOperandosEnMultiplicacion();
                                        d.simplificarNumeros();
                                        if (d.getOperando(0).IsNumero()) {
                                            A = new Numero(d.extraerOperando(0).getNumero());
                                        } else {
                                            A = new Numero(1);
                                        }
                                        A.setPositivo(signo);
                                        salida.addOperando(d);

//********
//                                    salida.addOperando(new Seno(new Multiplicacion(new Numero(2), ((Seno) O[0][i]).getParametro1()), bicondicional(O[0][i].extraerPositivo(), O[1][j].extraerPositivo()) ? true : ((Exponente) A).getParametro2().getNumero() % 2 == 0));
//                                    
//                                    while (B.getNumero() % 2 == 0 && indice > 0) {
//                                        B = new Numero(B.getNumero() / 2).copiaOptima();
//                                        indice--;
//                                        salida.addOperando(new Seno(new Multiplicacion(new Numero(2),((Seno) O[0][i]).getParametro1().copia())));    
//                                    }
//                                   
//                                    if(indice!=0){
//                                    salida.addOperando(O[0][i].copia(),O[1][j].copia());
//                                    }
                                        O = eliminarObjetos(O, O[0][i], O[1][j]);

                                        //   
                                        //********  
                                        if (A.getNumero() == 0 || A.getNumero() % 2 != 0) {
                                            break Finalizar;
                                        }
                                    }
                                }
                            }

                            if (!Arreglos.IsEmpty(O)) {
                                ((Multiplicacion) ((Exponente) B).getParametro1()).addOperandoAA(O);
                            }
                            if (!salida.isEmpty()) {
                                salida.setPositivo(A.extraerPositivo());
                                salida.simplificar();
                                if (((Multiplicacion) ((Exponente) B).getParametro1()).isEmpty()) {
                                    if (A.getNumero() != 1) {
                                        return new Multiplicacion(A, salida).copiaOptima();
                                    } else {
                                        return salida;
                                    }

                                } else {
                                    Multiplicacion respuesta = new Multiplicacion();
                                    if (A.getNumero() != 1) {
                                        respuesta = new Multiplicacion(A, B, salida);
                                    } else {
                                        respuesta = new Multiplicacion(B, salida);
                                    }
                                    respuesta.simplificar();

                                    if (respuesta.toString().length() <= (A.toString().length() + B.toString().length())) {
                                        return respuesta;
                                    }
                                }

                            }

                        }
                        // System.out.println("((Exponente) B).getParametro2()=" + ((Exponente) B).getParametro2() + " " + ((Exponente) B).getParametro2().getClass().getSimpleName());

                        boolean g = ((Exponente) B).getParametro2() instanceof Suma;
                        //System.out.println("g=" + g);
//                         boolean g2=((Suma) ((Exponente) B).getParametro2()).contineneUnNumero();
                        //  System.out.println("g2="+g2);
                        if (((Exponente) B).getParametro2() instanceof Suma && ((Suma) ((Exponente) B).getParametro2()).contineneUnNumero()) {
                            double indiceNumero = ((Suma) ((Exponente) B).getParametro2()).getOperando(((Suma) ((Exponente) B).getParametro2()).firstIndexOfNumero()).getNumero();
                            //  System.out.println("indiceNumero=" + indiceNumero);
                            if (indiceNumero < 10 && indiceNumero >= 2 && esEntero(indiceNumero)) {
                                Multiplicacion respuesta = new Multiplicacion(A, new Exponente(((Exponente) B).getParametro1().copia(), new Numero(((Suma) ((Exponente) B).getParametro2()).extraerOperando(((Suma) ((Exponente) B).getParametro2()).firstIndexOfNumero()).getNumero())));
                                //  System.out.println("B=" + B + " respuesta=" + respuesta);
                                respuesta.simplificarOperandosEnMultiplicacion();
                                //  System.out.println("respuesta=" + respuesta);
                                //  System.out.println("new Multiplicacion(respuesta, B)=" + new Multiplicacion(respuesta, B));
                                return new Multiplicacion(respuesta, B);
                            }
                        }
                    }

                }

                ///rrrrrrrrrrrrrr
            }
            if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                //caso en que B sqr[2](B)  debuelve B^(1/2+1)
                if (((Funcion) B).getParametro2().igualA(A, false) && condicional(A.esPositivo(), ((Funcion) B).getParametro2().esPositivo())) {
                    return new Exponente(A, new Suma(new Numero(1), new FraccionGeneral(new Numero(1), ((Funcion) B).getParametro1())), ((Funcion) B).getParametro2().esPositivo() ? bicondicional(A.extraerPositivo(), B.extraerPositivo()) : B.extraerPositivo());
                    // return new Funcion(TipoDeEnvoltura.EXPONETE, A, new Suma(new Numero(1), new FraccionGeneral(new Numero(1), ((Funcion) B).getParametro1())),((Funcion) B).getParametro2().esPositivo()? bicondicional(A.extraerPositivo(),  B.extraerPositivo()):B.extraerPositivo());
                }

                if (((Funcion) B).getParametro2() instanceof Funcion && ((Funcion) ((Funcion) B).getParametro2()).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                    //caso B sqr[2](B^3)  devuelbe  B^(3/2+1)
                    if (((Funcion) ((Funcion) B).getParametro2()).getParametro1().igualA(A, false) && condicional(A.esPositivo(), ((Funcion) ((Funcion) B).getParametro2()).getParametro1().esPositivo())) {
                        return new Exponente(A, new Suma(new Numero(1), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), ((Funcion) ((Funcion) B).getParametro2()).getParametro1().esPositivo() ? bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()) : bicondicional(B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()));
                        // return new Funcion(TipoDeEnvoltura.EXPONETE, A, new Suma(new Numero(1), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), ((Funcion) ((Funcion) B).getParametro2()).getParametro1().esPositivo()? bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()):bicondicional( B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()));
                    }

                    if (A instanceof Funcion) {

                        //caso  B^4  sqr[2](B^3) 
                        if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE && ((Funcion) ((Funcion) B).getParametro2()).getParametro1().igualA(((Funcion) A).getParametro1())) {
                            return new Exponente(((Funcion) A).getParametro1(), new Suma(((Funcion) A).getParametro2(), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(),
                                    ((Funcion) B).getParametro2().extraerPositivo()));
                            //   return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) A).getParametro1(), new Suma(((Funcion) A).getParametro2(), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(),
                            //          ((Funcion) B).getParametro2().extraerPositivo()));

                        }
                        if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                            //caso sqr[4](A) sqr[2](A^3)  devuelbe A^(3/2+1/4) 
                            if (((Funcion) ((Funcion) B).getParametro2()).getParametro1().igualA(((Funcion) A).getParametro2(), false)) {
                                return new Exponente(((Funcion) A).getParametro2(), new Suma(new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1()), new FraccionGeneral(new Numero(1), ((Funcion) A).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(),
                                        ((Funcion) B).getParametro2().extraerPositivo()));
                                //  return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) A).getParametro2(), new Suma(new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1()), new FraccionGeneral(new Numero(1), ((Funcion) A).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(),
                                //          ((Funcion) B).getParametro2().extraerPositivo()));
                            }
                        }

                    }

                }
            }

        }

        return new Operando();
    }

    @Override
    public boolean igualA(Operando a, boolean valorarSigno) {
        if (a instanceof Multiplicacion && ((Multiplicacion) a).getOperandos().size() == operandos.size()) {
            if (valorarSigno && ((Multiplicacion) a).esPositivo() != positivo) {
                return false;
            }
            for (int i = 0; i < operandos.size(); i++) {
                if (!operandos.get(i).igualA(((Multiplicacion) a).getOperandos().get(i))) {
                    return false;
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Multiplicacion[] crearArreglo(int leng) {
        return new Multiplicacion[leng];
    }

    @Override
    public Multiplicacion[][] crearArreglo(int filas, int columnas) {
        return new Multiplicacion[filas][columnas];
    }

    @Override
    public Multiplicacion[][][] crearArreglo(int filas, int columnas, int cantidad) {
        return new Multiplicacion[filas][columnas][cantidad];
    }

    @Override
    public boolean contieneVariable(char v) {
        for (int i = 0; i < lengh(); i++) {
            if (operandos.get(i).contieneVariable(v)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Operando inicializarDerivada(char v) {
        if (isEmpty()) {
            return super.inicializarDerivada(v);
        }
        // Multiplicacion derivacion = (Multiplicacion)  copia();
        Operando primero = getOperandos().get(0).copia();
        Operando DerivadaPrimero = primero.inicializarDerivada(v);
        if (getOperandos().size() > 2) {
            Multiplicacion segundo = new Multiplicacion();
            for (int i = 1; i < getOperandos().size(); i++) {
                segundo.addOperando(getOperandos().get(i).copia());
            }
            Operando DerivadaSegundo = segundo.inicializarDerivada(v);
            return (derivado = new Suma(bicondicional(positivo, true), new Multiplicacion(primero, DerivadaSegundo), new Multiplicacion(segundo, DerivadaPrimero)));

        }
        Operando segundo = getOperandos().get(1).copia();
        Operando DerivadaSegundo = segundo.inicializarDerivada(v);
        return (derivado = new Suma(bicondicional(positivo, true), new Multiplicacion(primero, DerivadaSegundo), new Multiplicacion(segundo, DerivadaPrimero)));

    }

}
