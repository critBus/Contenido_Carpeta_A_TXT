/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

import Utiles.ClasesUtiles.Matematicas.Funciones.Cotagente;
import Utiles.ClasesUtiles.Matematicas.Funciones.Exponente;
import Utiles.ClasesUtiles.Matematicas.Funciones.Logaritmo;
import Utiles.ClasesUtiles.Matematicas.Funciones.Tangente;
import static Utiles.MetodosUtiles.MetodosUtiles.bicondicional;
import static Utiles.MetodosUtiles.MetodosUtiles.condicional;
import Utiles.MetodosUtiles.Operaciones;
//import static Utiles.MetodosUtiles.MetodosUtiles.eliminarMasMenos;
//import static Utiles.MetodosUtiles.MetodosUtiles.eliminarPuntoCero;
import static Utiles.MetodosUtiles.Operaciones.simplificarFraccion;
//import static Utiles.MetodosUtiles.Operaciones.calcular;//esSignoMenos
//import java.util.ArrayList;

/**
 *
 * @author Rene
 */
//public class FraccionGeneral extends Operando implements TipoOperando {
public class FraccionGeneral extends Operando {

    private Operando numerador = new Operando();
    private Operando denominador = new Operando();
//    boolean isNumero;
//    double numero;
    //private boolean positivo;

    public FraccionGeneral() {
        // this(1, 1);
    }

    public FraccionGeneral(boolean positivo) {
        this.positivo = positivo;
    }

//    public FraccionGeneral(FraccionGeneral a,FraccionGeneral b) {
//        this(f.getNumerador(), f.getDenominador(), f.isPositivo());
//    }
    public FraccionGeneral(FraccionGeneral f) {
        this(f.getNumerador(), f.getDenominador(), f.esPositivo());
    }

    //----
    public FraccionGeneral(Operando numerador, boolean positivo) {
        this(numerador, new Operando(), positivo);
        // System.out.println("ooooooooooooooooooooooooooooooooooooooooooooooooooooo");

    }

    public FraccionGeneral(double numerador, boolean positivo) {
        this(new Numero(numerador), new Operando(), positivo);
    }

//    public FraccionGeneral(TipoOperando numerador, boolean positivo) {
//        this(numerador.getOperando(), new Operando(1), positivo);
//    }
//
//    public FraccionGeneral(TipoOperando numerador, TipoOperando denominador, boolean positivo) {
//        this(numerador.getOperando(), denominador.getOperando(), positivo);
//    }
//
//    public FraccionGeneral(TipoOperando numerador, double denominador, boolean positivo) {
//        this(numerador.getOperando(), new Operando(denominador), positivo);
//    }
//
//    public FraccionGeneral(double numerador, TipoOperando denominador, boolean positivo) {
//        this(new Operando(numerador), denominador.getOperando(), positivo);
//    }
    public FraccionGeneral(double numerador, double denominador, boolean positivo) {
        this(new Numero(numerador), new Numero(denominador), positivo);
    }
    //----

    public FraccionGeneral(Operando numerador) {
        this(numerador, new Operando(), true);

    }

    public FraccionGeneral(double numerador) {
        this(new Numero(numerador), new Operando(), true);
    }

//    public FraccionGeneral(TipoOperando numerador) {
//        this(numerador.getOperando(), new Operando(1), true);
//    }
//
//    public FraccionGeneral(TipoOperando numerador, TipoOperando denominador) {
//        this(numerador.getOperando(), denominador.getOperando(), true);
//    }
//
//    public FraccionGeneral(TipoOperando numerador, double denominador) {
//        this(numerador.getOperando(), new Operando(denominador), true);
//    }
//
//    public FraccionGeneral(double numerador, TipoOperando denominador) {
//        this(new Operando(numerador), denominador.getOperando(), true);
//    }
    public FraccionGeneral(double numerador, double denominador) {
        this(new Numero(numerador), new Numero(denominador), true);
    }

    public FraccionGeneral(double numerador, Operando denominador) {
        this(new Numero(numerador), denominador, true);
    }

    public FraccionGeneral(Operando numerador, double denominador) {
        this(numerador, new Numero(denominador), true);
    }

    public FraccionGeneral(double numerador, Operando denominador, boolean positivo) {
        this(new Numero(numerador), denominador, positivo);
    }

    public FraccionGeneral(Operando numerador, double denominador, boolean positivo) {
        this(numerador, new Numero(denominador), positivo);
    }

    public FraccionGeneral(Operando numerador, Operando denominador) {
        this(numerador, denominador, true);
    }

    public FraccionGeneral(Operando numerador, Operando denominador, boolean positivo) {
        this.numerador = numerador;
        this.denominador = denominador;
        this.positivo = positivo;
        // inicializarOptimo();
    }

//    public boolean isPositivo() {
//        return positivo;
//    }
//    public void setPositivo(boolean Positivo) {
//        this.positivo = Positivo;
//    }
    public Operando getNumerador() {
        return numerador;
    }

    public Operando getFirstNumerador() {
        if (numerador instanceof Multiplicacion) {
            return ((Multiplicacion) getNumerador()).getFirstOperando();
        }
        return numerador;
    }

    public Operando getLastNumerador() {
        if (numerador instanceof Multiplicacion) {
            return ((Multiplicacion) getNumerador()).getLastOperando();
        }
        return numerador;
    }

    public Operando extraerNumerador() {
        Operando o = numerador.copia();
        numerador = new Numero(1);
        return o;
    }

    public Operando extraerLastNumerador() {
        if (numerador instanceof Multiplicacion) {
            return ((Multiplicacion) getNumerador()).extraerLastOperando();
        }
        return extraerNumerador();
    }

    public Operando extraerFirstNumerador() {
        if (numerador instanceof Multiplicacion) {
            return ((Multiplicacion) getNumerador()).extraerFirstOperando();
        }
        return extraerNumerador();
    }

    public void setNumerador(Operando numerador) {
        this.numerador = numerador;
    }

    public Operando getDenominador() {
        return denominador;
    }

    public Operando getFirstDenominador() {
        if (denominador instanceof Multiplicacion) {
            return ((Multiplicacion) getDenominador()).getFirstOperando();
        }
        return denominador;
    }

    public Operando getLastDenominador() {
        if (denominador instanceof Multiplicacion) {
            return ((Multiplicacion) getDenominador()).getLastOperando();
        }
        return denominador;
    }

    public Operando extraerDenominador() {
        Operando o = denominador.copia();
        denominador = new Numero(1);
        return o;
    }

    public Operando extraerLastDenominador() {
        if (denominador instanceof Multiplicacion) {
            return ((Multiplicacion) getDenominador()).extraerLastOperando();
        }
        return extraerDenominador();
    }

    public Operando extraerFirstDenominador() {
        if (denominador instanceof Multiplicacion) {
            return ((Multiplicacion) getDenominador()).extraerFirstOperando();
        }
        return extraerDenominador();
    }

    public void setDenominador(Operando denominador) {
        this.denominador = denominador;
    }

//    public boolean IsNumero() {
//        return isNumero;
//    }
//
//    public double getNumero() {
//        return numero;
//    }
    @Override
    protected void inicializarOptimo() {
        // System.out.println("i this=" + this + " denominador.esPositivo()=" + denominador.esPositivo());
        numerador = numerador.getOptimo();
        // System.out.println("numerador=" + numerador + " " + numerador.getClass().getSimpleName() + " " + numerador.esPositivo());
        denominador = denominador.getOptimo();
        // System.out.println("denominador=" + denominador + " " + denominador.getClass().getSimpleName() + " " + denominador.esPositivo());
        //signos positivos -/- 
        // System.out.println("numerador.isPositivo()=" + numerador.esPositivo() + " denominador.isPositivo()=" + denominador.esPositivo());
        simplificarOperandosEnFraccion();
//******************
        if (!numerador.esPositivo() && !denominador.esPositivo()) {
            numerador.setPositivo(true);
            denominador.setPositivo(true);

        } else {
            if (!numerador.esPositivo() || !denominador.esPositivo()) {
                // System.out.println("this=" + this);
                //  System.out.println("1 positivo=" + positivo);
                numerador.setPositivo(true);
                // System.out.println("numerador positivo " + numerador.esPositivo());
                denominador.setPositivo(true);
                //System.out.println("denominador positivo " + denominador.esPositivo());
//        if(positivo){
//        positivo=false;
//        }else{
//        positivo=true;
//        }
                positivo = !positivo;
                // System.out.println("2 positivo=" + positivo);
            }
        }
        //*********************
//        if(!denominador.isPositivo()){
//        denominador.setPositivo(true);
//        positivo=!positivo;
//        }

        //--------------
        if (getNumerador().IsNumero() && getDenominador().IsNumero()) {
            numero = Operaciones.regulador(getNumerador().getNumero() / getDenominador().getNumero());
            // numero = getNumerador().getNumero() / getDenominador().getNumero();
            isNumero = true;
            optimo = new Numero(numero, positivo);

        } else {
            isNumero = false;
            optimo = this;
            if (denominador.IsNumero()) {
                if (denominador.getNumero() == 1) {

                    optimo = numerador.copia();
                    optimo.setPositivo(bicondicional(positivo, numerador.esPositivo()));
                    //System.out.println("numerador positivo= " + numerador.esPositivo());
                    // System.out.println("optimo.esPositivo()=" + optimo.esPositivo());
                    return;
                }
                if (denominador.getNumero() == -1) {
                    optimo = numerador.copia();
                    optimo.setPositivo(bicondicional(positivo, numerador.esPositivo()));
                    //optimo.setPositivo(!optimo.isPositivo());
                    return;
                }

            }

            if (getNumerador() instanceof FraccionGeneral) {
//                Operando numerador2 = ((FraccionGeneral) getNumerador()).getNumerador();
                Operando O[] = {getDenominador(), ((FraccionGeneral) getNumerador()).getDenominador()};
//                Operando denominador2 = Ecuacion.inicializarConMultiplicacion(O);
//                positivo = bicondicional(getNumerador().isPositivo(), positivo);
//                numerador = numerador2;
//                denominador = denominador2;
//                inicializarOptimo();
                //ffffffffffffffffffffff
                //  recrearOperandos(((FraccionGeneral) getNumerador()).getNumerador(), Ecuacion.inicializarConMultiplicacion(O), getNumerador().extraerPositivo());
                //ffffffffffff
                //+++++++++++++++++++++
                recrearOperandos(((FraccionGeneral) getNumerador()).getNumerador(), new Multiplicacion(O), getNumerador().extraerPositivo());
                //++++++++++++++++++++++
                //System.out.println("n numerador=" + numerador + " denominador=" + denominador);
                // simplificarSignosEnOptimo();//*************************************
                return;
            }
            //System.out.println("getDenominador()=" + getDenominador());
            if (getDenominador() instanceof FraccionGeneral) {
                Operando O[] = {getNumerador(), ((FraccionGeneral) getDenominador()).getDenominador()};
//                Operando numerador2 = Ecuacion.inicializarConMultiplicacion(O);
//                Operando denominador2 = ((FraccionGeneral) getDenominador()).getNumerador();
//                positivo = bicondicional(getDenominador().isPositivo(), positivo);
//                numerador = numerador2;
//                denominador = denominador2;
//                inicializarOptimo();
                recrearOperandos(new Multiplicacion(O), ((FraccionGeneral) getDenominador()).getNumerador(), getDenominador().extraerPositivo());
                // System.out.println("d numerador=" + numerador + " denominador=" + denominador);
                //  simplificarSignosEnOptimo();//*********************
                return;
            }
            if (getNumerador() instanceof Multiplicacion) {
                // System.out.println("aqui");
                for (int i = 0; i < ((Multiplicacion) getNumerador()).getOperandos().size(); i++) {
                    // System.out.println("this=" + this);
                    if (((Multiplicacion) getNumerador()).getOperandos().get(i) instanceof FraccionGeneral) {
                        FraccionGeneral f = (FraccionGeneral) ((Multiplicacion) getNumerador()).getOperandos().get(i);
                        ((Multiplicacion) getNumerador()).getOperandos().remove(i);

                        //iiiii
                        // addNumerador(f.getNumerador());
                        //iiiiii
                        ((Multiplicacion) getNumerador()).getOperandos().add(f.getNumerador());
                        //  numerador = Ecuacion.inicializarConMultiplicacion(getNumerador().getMultiplicacion());

                        positivo = bicondicional(positivo, f.extraerPositivo());
                        addDenominador(f.getDenominador());
                        //System.out.println("n1 numerador=" + numerador + " denominador=" + denominador);
                        return;

                    }
                }

            }
            if (getDenominador() instanceof Multiplicacion) {
                for (int i = 0; i < ((Multiplicacion) getDenominador()).getOperandos().size(); i++) {
                    if (((Multiplicacion) getDenominador()).getOperandos().get(i) instanceof FraccionGeneral) {
                        FraccionGeneral f = (FraccionGeneral) ((Multiplicacion) getDenominador()).getOperandos().get(i);
                        ((Multiplicacion) getDenominador()).getOperandos().remove(i);
                        //iiiii
                        // addDenominador(f.getNumerador());
                        //iiiiii

                        ((Multiplicacion) getDenominador()).getOperandos().add(f.getNumerador());
                        // denominador=Ecuacion.inicializarConMultiplicacion(getDenominador().getMultiplicacion());
                        positivo = bicondicional(positivo, f.extraerPositivo());
                        addNumerador(f.getDenominador());
                        // System.out.println("d2 numerador=" + numerador + " denominador=" + denominador);
                        return;

                    }
                }
            }

            //System.out.println("1numerador=" + numerador);
            //simplificar numeros
            int posicionNumerador = -1;
            double numero1 = 0;
            if (getNumerador() instanceof Multiplicacion) {
                posicionNumerador = ((Multiplicacion) getNumerador()).firstIndexOfNumero();
                // System.out.println("posicionNumerador=" + posicionNumerador);
                numero1 = posicionNumerador != -1 ? ((Multiplicacion) getNumerador()).getOperandos().get(((Multiplicacion) getNumerador()).firstIndexOfNumero()).getNumero() : 0;
                //  System.out.println("numero1=" + numero1);
            } else {
                if (getNumerador().IsNumero() && getNumerador().getNumero() != 1 && getNumerador().getNumero() != -1) {
                    numero1 = getNumerador().getNumero();
                    //  System.out.println("numero1=" + numero1);
                    //posicionNumerador = -2;
                } else {
                    return;
                }
            }
            //  System.out.println("1denominador=" + denominador);
            double numero2 = 0;
            int posicionDenominador = -1;
            if (getDenominador() instanceof Multiplicacion) {
                posicionDenominador = ((Multiplicacion) getDenominador()).firstIndexOfNumero();
                //  System.out.println("posicionDenominador=" + posicionDenominador);
                numero2 = posicionDenominador != -1 ? ((Multiplicacion) getDenominador()).getOperandos().get(((Multiplicacion) getDenominador()).firstIndexOfNumero()).getNumero() : 0;
                //   System.out.println("numero2=" + numero2);
            } else {
                if (getDenominador().IsNumero() && getDenominador().getNumero() != 1 && getDenominador().getNumero() != -1) {
                    //posicionDenominador = -2;
                    numero2 = getDenominador().getNumero();
                    //  System.out.println("numero2=" + numero2);
                } else {
                    return;
                }
            }
            if (numero1 != 0 && numero2 != 0) {
                //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
//                double n=numero1 / numero2; System.out.println("n="+n);
//                int fraccion[] = decimalAFraccionInt(n);
                //vvvvvvvvvvvvvvvvvvvvvvvvvvvvvvvv
                int fraccion[] = simplificarFraccion(numero1, numero2);
                //System.out.println("fraccion[0]=" + fraccion[0] + " fraccion[1]=" + fraccion[1]);
                if (posicionNumerador > -1) {
                    ((Multiplicacion) getNumerador()).getOperandos().set(posicionNumerador, new Numero(fraccion[0]));
                    ((Multiplicacion) getNumerador()).inicializarOptimo();
                } else {
                    numerador = new Numero(fraccion[0]);
                }
                // System.out.println("2numerador=" + numerador);
                if (posicionDenominador > -1) {
                    ((Multiplicacion) getDenominador()).getOperandos().set(posicionDenominador, new Numero(fraccion[1]));
                    ((Multiplicacion) getDenominador()).inicializarOptimo();
                } else {
                    denominador = new Numero(fraccion[1]);
                }
                //  System.out.println("2denominador=" + denominador);

            }

        }

    }

    private void simplificarSignosEnOptimo() {
        // if(thisEsOptimo){optimo=this;}

        if (optimo instanceof FraccionGeneral && !((FraccionGeneral) optimo).getNumerador().esPositivo() && !((FraccionGeneral) optimo).getDenominador().esPositivo()) {

            ((FraccionGeneral) optimo).getNumerador().setPositivo(true);
            ((FraccionGeneral) optimo).getNumerador().setPositivo(true);

        } else {
            if (!numerador.esPositivo() || !denominador.esPositivo()) {
                ((FraccionGeneral) optimo).getNumerador().setPositivo(true);
                ((FraccionGeneral) optimo).getNumerador().setPositivo(true);
                ((FraccionGeneral) optimo).setPositivo(!((FraccionGeneral) optimo).esPositivo());
            }
        }
    }

    private void recrearOperandos(Operando numerador2, Operando denominador2, boolean signo) {
        positivo = bicondicional(signo, positivo);
        numerador = numerador2;
        denominador = denominador2;
        inicializarOptimo();
    }

//    @Override
//    public Operando getOperando() {
//        return new Operando(this);
//    }
    public void addNumerador(Operando b) {
        if (numerador.getClass() != Operando.class) {
            if (numerador instanceof Multiplicacion && ((Multiplicacion) numerador).getOperandos().size() == 0) {
                setDenominador(new Multiplicacion(b));
            } else {
                setNumerador(new Multiplicacion(getNumerador(), b));
            }
        } else {
            setNumerador(new Multiplicacion(b));
        }

//        
//        ArrayList<Operando> operacionMultiplicacion = new ArrayList<Operando>();
//        operacionMultiplicacion.add(getNumerador());
//        operacionMultiplicacion.add(b);
//      
//        setNumerador(new Multiplicacion(operacionMultiplicacion));
        //+++++++++++++
        // setNumerador(new Operando(new Funcion(TipoDeEnvoltura.NUMERO, new Ecuacion(new ArrayList<Operando>(), operacionMultiplicacion2))));
        //setNumerador(Ecuacion.inicializarConMultiplicacion(null))
        //inicializarOptimo();
// return fraccion;
    }

    public void addDenominador(Operando b) {
        // System.out.println("b=" + b);
        if (denominador.getClass() != Operando.class) {
            if (denominador instanceof Multiplicacion && ((Multiplicacion) denominador).getOperandos().size() == 0) {
                setDenominador(new Multiplicacion(b));
            } else {
                //  System.out.println("getDenominador()=" + getDenominador() + " " + getDenominador().getClass().getSimpleName());
                //  System.out.println("new Multiplicacion(getDenominador(), b)=" + new Multiplicacion(getDenominador(), b));
                setDenominador(new Multiplicacion(getDenominador(), b));
                //  System.out.println("getDenominador()=" + getDenominador());
            }
        } else {
            //  System.out.println("new Multiplicacion(b)=" + new Multiplicacion(b));
            setDenominador(new Multiplicacion(b));
            // System.out.println("getDenominador()=" + getDenominador());
        }

//        Operando O[] = {getDenominador(), b};
//        setDenominador(new Multiplicacion(O));
//        System.out.println("d1 this=" + this);
//        System.out.println("d1 denominador=" + denominador + " " + denominador.esPositivo());
//        System.out.println("d1 numerador=" + numerador + " " + numerador.esPositivo() + " numerador optimo " + numerador.getOptimo().esPositivo());
//        System.out.println("m1 denominador.isPositivo()=" + denominador.esPositivo());
//        System.out.println("positivo denominador optimo=" + denominador.getOptimo().esPositivo());
//        inicializarOptimo();
//        System.out.println("d1 this=" + this);
//        System.out.println("d positivo=" + positivo);
//        System.out.println("m2 denominador.isPositivo()=" + denominador.esPositivo() + " positivo=" + positivo);
//        System.out.println("positivo denominador optimo=" + denominador.getOptimo().esPositivo());
//        System.out.println("IsNumero=" + IsNumero());
//        System.out.println("d2 denominador=" + denominador + " " + denominador.esPositivo());
//        System.out.println("d2 numerador=" + numerador + " " + numerador.esPositivo() + " numerador optimo " + numerador.getOptimo().esPositivo());
    }

    public void addDenominador(double b) {
        addDenominador(new Numero(b));
    }

//    public void addDenominador(TipoOperando b) {
//        addDenominador(b.getOperando());
//    }
    public void addNumerador(double b) {
        addNumerador(new Numero(b));
    }

//    public void addNumerador(TipoOperando b) {
//        addNumerador(b.getOperando());
//    }
    @Override
    public String toString() {
        // System.out.println("numerador=" + numerador + " denominador=" + denominador);
        // System.out.println("/////////////////////////////");
        String fraccion = "";
        //   fraccion += (positivo ? "" : "-(") + numerador;
        fraccion += (positivo ? "" : "-(") + (numerador.esPositivo() && (numerador.esSuma() || numerador.esFraccion()) ? "(" : "") + numerador + (numerador.esPositivo() && (numerador.esSuma() || numerador.esFraccion()) ? ")" : "");
        //  System.out.println("1fraccion=" + fraccion);
        if (!(denominador instanceof Numero && ((Numero) denominador).getNumero() == 1)) {
            //fraccion += "/" + denominador;
            fraccion += "/" + (denominador.esPositivo() && (denominador.esSuma() || denominador.esFraccion()) ? "(" : "") + denominador + (denominador.esPositivo() && (denominador.esSuma() || denominador.esFraccion()) ? ")" : "");
            // System.out.println("2fraccion=" + fraccion);
        }
        return fraccion + (positivo ? "" : ")");
    }

    @Override
    public boolean igualA(Operando a, boolean valorarSigno) {
        // System.out.println("fr this=" + this + " a=" + a);
//        if(a instanceof Funcion&&((Funcion)a).getTipoDeEnvoltura()==TipoDeEnvoltura.PARENTESIS){
//        return igualA(((Funcion)a).getParametro1(),valorarSigno);
//        }
        if (a instanceof FraccionGeneral) {
            return valorarSigno ? a.esPositivo() == positivo && ((FraccionGeneral) a).getNumerador().igualA(numerador) && ((FraccionGeneral) a).getDenominador().igualA(denominador)
                    : ((FraccionGeneral) a).getNumerador().igualA(numerador) && ((FraccionGeneral) a).getDenominador().igualA(denominador);

        }
        return false;
    }

    private void simplificarOperandosEnFraccion() {
        //if (operandoUninonActual.getClass() != Operando.class) {
        Operando operandoUninonActual = new Operando();
        operandoUninonActual = unirOperandosPorFraccion(numerador.copia(), denominador.copia());
        // System.out.println("operandoUninonActual=" + operandoUninonActual);
        if (operandoUninonActual.getClass() != Operando.class) {
            numerador = operandoUninonActual.copia();
            //   System.out.println("numerador=" + numerador);
            denominador = new Numero(1);
            return;
        }

        if (numerador instanceof Multiplicacion) {
            //operandoUninonActual =((Multiplicacion)numerador).getOperandos().get(0);
            if (denominador instanceof Multiplicacion) {
                for (int i = 0; i < ((Multiplicacion) numerador).getOperandos().size(); i++) {
                    //  System.out.println("i=" + i);
                    for (int j = 0; j < ((Multiplicacion) denominador).getOperandos().size(); j++) {
                        //   System.out.println("j=" + j);
                        //   System.out.println("((Multiplicacion) numerador).getOperandos().get(i)=" + ((Multiplicacion) numerador).getOperandos().get(i) + " ((Multiplicacion) denominador).getOperandos().get(j)=" + ((Multiplicacion) denominador).getOperandos().get(j));
                        operandoUninonActual = unirOperandosPorFraccion(((Multiplicacion) numerador).getOperandos().get(i).copia(), ((Multiplicacion) denominador).getOperandos().get(j).copia());
                        //   System.out.println("operandoUninonActual=" + operandoUninonActual);
                        if (operandoUninonActual.getClass() != Operando.class) {
                            ((Multiplicacion) numerador).getOperandos().set(i, operandoUninonActual.copia());
                            //     System.out.println("numerador=" + numerador);
                            ((Multiplicacion) denominador).getOperandos().remove(j--);
                            //    System.out.println("denominador=" + denominador);
                        }

                    }
                }
                if (((Multiplicacion) denominador).isEmpty()) {
                    denominador = new Numero(1);
                } else {
                    denominador.simplificar(false);
                }
            } else {
                for (int i = 0; i < ((Multiplicacion) numerador).getOperandos().size(); i++) {
                    operandoUninonActual = unirOperandosPorFraccion(((Multiplicacion) numerador).getOperandos().get(i).copia(), denominador.copia());
                    if (operandoUninonActual.getClass() != Operando.class) {
                        ((Multiplicacion) numerador).getOperandos().set(i, operandoUninonActual.copia());
                        denominador = new Numero(1);
                        break;
                    }
                }
            }

            numerador.simplificar(false);
        } else {
            if (denominador instanceof Multiplicacion) {
                for (int i = 0; i < ((Multiplicacion) denominador).getOperandos().size(); i++) {
                    operandoUninonActual = unirOperandosPorFraccion(numerador.copia(), ((Multiplicacion) denominador).getOperandos().get(i).copia());
                    if (operandoUninonActual.getClass() != Operando.class) {
                        numerador = operandoUninonActual.copia();
                        ((Multiplicacion) denominador).getOperandos().remove(i--);
                    }
                }
                if (((Multiplicacion) denominador).isEmpty()) {
                    denominador = new Numero(1);
                } else {
                    denominador.simplificar(false);
                }
                denominador.simplificar(false);
            }
//            else {
//                operandoUninonActual = unirOperandosPorFraccion(numerador.copia(), denominador.copia());
//                System.out.println("operandoUninonActual=" + operandoUninonActual);
//                if (operandoUninonActual.getClass() != Operando.class) {
//                    numerador = operandoUninonActual.copia();
//                    System.out.println("numerador=" + numerador);
//                    denominador = new Numero(1);
//                }
//            }
        }
//        if (operandoUninonActual.getClass() != Operando.class) {
//        inicializarOptimo();
//        }

    }

    public static Operando unirOperandosPorFraccion(Operando A, Operando B) {
        //A es numerador B denominador
        if (A.igualA(B, false)) {
            return new Numero(1, bicondicional(A.esPositivo(), B.esPositivo()));
        }

        if (A instanceof Funcion) {
            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                //caso A^b / A debuelve A^(b-1)
                //if (((Funcion) A).getParametro1().igualA(B, false) && (((Funcion) A).getParametro1().esPositivo() == false) ? B.esPositivo() == false : true) {
                if (((Funcion) A).getParametro1().igualA(B, false) && condicional(B.esPositivo(), ((Funcion) A).getParametro1().esPositivo())) {
                    if (((Funcion) A).getParametro1().esPositivo()) {
                        A.setPositivo(B.extraerPositivo(), A.extraerPositivo());
                    }

                    // ((Funcion) A).getParametro1().setPositivo(B.extraerPositivo(), ((Funcion) A).getParametro1().extraerPositivo());
                    ((Funcion) A).setParametro2(new Suma(((Funcion) A).getParametro2(), new Numero(-1)));
                    return A;
                }

                if (B instanceof Funcion) {
                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                        //caso A^c / B^c debuelve (A/B)^c
                        if (((Funcion) A).getParametro2().igualA(((Funcion) B).getParametro2())) {
                            A.setPositivo(A.extraerPositivo(), B.extraerPositivo());
                            ((Funcion) A).setParametro1(new FraccionGeneral(((Funcion) A).getParametro1(), ((Funcion) B).getParametro1()));
                            return A;
                        }

                        //caso A^b / A^c debuelve A^(b-c)
                        if (((Funcion) A).getParametro1().igualA(((Funcion) B).getParametro1())) {
                            A.setPositivo(A.extraerPositivo(), B.extraerPositivo());
                            ((Funcion) A).getParametro1().setPositivo(((Funcion) A).getParametro1().extraerPositivo(), ((Funcion) B).getParametro1().extraerPositivo());
                            ((Funcion) A).setParametro2(Suma.inicializarRestando(((Funcion) A).getParametro2(), ((Funcion) B).getParametro2()));
                            return A;
                        }
                    }
                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                        //caso A^b / sqr[c](A) debuelve A^(b-1/c)    
                        if (((Funcion) A).getParametro1().igualA(((Funcion) B).getParametro2())) {//((Funcion) B).getParametro2()
                            A.setPositivo(A.extraerPositivo(), B.extraerPositivo());
                            ((Funcion) A).setParametro2(Suma.inicializarRestando(((Funcion) A).getParametro2(), new FraccionGeneral(1, ((Funcion) B).getParametro1())));
                            return A;
                        }
                    }

                }

            }
            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                //caso sqr[b](A) / A debuelve A^(1/b-1)
                if (((Funcion) A).getParametro2().igualA(B, false) && condicional(B.esPositivo(), ((Funcion) A).getParametro2().esPositivo())) {
                    return new Exponente(B, Suma.inicializarRestando(new FraccionGeneral(1, ((Funcion) A).getFirstParametro1()), new Numero(1)), ((Funcion) A).getParametro2().esPositivo() ? A.esPositivo() : bicondicional(A.extraerPositivo(), B.extraerPositivo()));

                    // return new Funcion(TipoDeEnvoltura.EXPONETE, B, Suma.inicializarRestando(new FraccionGeneral(1, ((Funcion) A).getFirstParametro1()), new Numero(1)), ((Funcion) A).getParametro2().esPositivo() ? A.esPositivo() : bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                }

                if (((Funcion) A).getParametro2() instanceof Funcion && ((Funcion) ((Funcion) A).getParametro2()).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                    //caso sqr[b](A^c) / A debuelve A^(c/b-1)
                    if (((Funcion) ((Funcion) A).getParametro2()).getParametro1().igualA(B, false) && condicional(B.esPositivo(), ((Funcion) ((Funcion) A).getParametro2()).getParametro1().esPositivo())) {

                        return new Exponente(((Funcion) ((Funcion) A).getParametro2()).getParametro1(), Suma.inicializarRestando(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), new Numero(1)), ((Funcion) ((Funcion) A).getParametro2()).getParametro1().esPositivo()
                                ? bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo())
                                : bicondicional(A.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()));

//                        return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) ((Funcion) A).getParametro2()).getParametro1(), Suma.inicializarRestando(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), new Numero(1)), ((Funcion) ((Funcion) A).getParametro2()).getParametro1().esPositivo()
//                                ? bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo())
//                                : bicondicional(A.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()));
                    }

                    if (B instanceof Funcion) {
                        if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                            //caso sqr[b](A^c) / A^d debuelve A^(c/b-d)
                            if (((Funcion) ((Funcion) A).getParametro2()).getParametro1().igualA(((Funcion) B).getParametro1())) {
                                //  System.out.println("A=" + A + " B=" + B);
                                B.setPositivo(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo());
                                ((Funcion) B).setParametro2(Suma.inicializarRestando(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), ((Funcion) B).getParametro2()));
                                return B;
                            }
                        }

                        if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                            //caso sqr[b](A^c) / sqr[d](A) debuelve A^(c/b-1/d)
                            if (((Funcion) ((Funcion) A).getParametro2()).getParametro1().igualA(((Funcion) B).getParametro2())) {
                                return new Exponente(((Funcion) B).getParametro2(), Suma.inicializarRestando(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), new FraccionGeneral(1, ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()));
                                // return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) B).getParametro2(), Suma.inicializarRestando(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), new FraccionGeneral(1, ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo()));
                            }
                            if (((Funcion) B).getParametro2() instanceof Funcion && ((Funcion) ((Funcion) B).getParametro2()).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                                //caso sqr[b](A^c) / sqr[d](A^e) debuelve A^(c/b-e/d)
                                if (((Funcion) ((Funcion) A).getParametro2()).getParametro1().igualA(((Funcion) ((Funcion) B).getParametro2()).getParametro1())) {
                                    return new Exponente(((Funcion) ((Funcion) A).getParametro2()).getParametro1(), Suma.inicializarRestando(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()));
                                    //return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) ((Funcion) A).getParametro2()).getParametro1(), Suma.inicializarRestando(new FraccionGeneral(((Funcion) ((Funcion) A).getParametro2()).getParametro2(), ((Funcion) A).getParametro1()), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) A).getParametro2().extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()));
                                }

                            }
                        }

                    }

                }

                if (B instanceof Funcion) {
                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                        //caso sqr[c](A) / sqr[c](B) debolver sqr[c](A/B)
                        if (((Funcion) A).getParametro1().igualA(((Funcion) B).getParametro1())) {
                            A.setPositivo(A.extraerPositivo(), B.extraerPositivo());
                            ((Funcion) A).setParametro2(new FraccionGeneral(((Funcion) A).getParametro2(), ((Funcion) B).getParametro2()));
                            return A;
                        }
                        //caso sqr[b](A) / sqr[c](A) debolver A^(1/b-1/c)
                        if (((Funcion) A).getParametro2().igualA(((Funcion) B).getParametro2())) {//new FraccionGeneral(1, ((Funcion) A).getParametro1())
                            return new Exponente(((Funcion) A).getParametro2(), Suma.inicializarRestando(new FraccionGeneral(1, ((Funcion) A).getParametro1()), new FraccionGeneral(1, ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                            //  return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) A).getParametro2(), Suma.inicializarRestando(new FraccionGeneral(1, ((Funcion) A).getParametro1()), new FraccionGeneral(1, ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                        }
                    }
                }
            }
            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.LOGARITMO) {
                if (B instanceof Funcion) {
                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.LOGARITMO) {
                        //log[c](A) / log[c](B) debuelve log[B](A)
                        if (((Funcion) A).getParametro1().igualA(((Funcion) B).getParametro1())) {
                            A.setPositivo(A.extraerPositivo(), B.extraerPositivo());
                            ((Funcion) A).setParametro1(((Funcion) B).getParametro2());
                            return A;
                        }
                    }
                }
            }

            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.LOGARITMO_NAPERIANO) {
                if (B instanceof Funcion) {
                    //ln[c](A) / ln[c](B) debuelve log[B](A)
                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.LOGARITMO_NAPERIANO) {

                        // if (((Funcion) A).getParametro1().igualA(((Funcion) B).getParametro1())) {
                        // A.setPositivo(A.extraerPositivo(), B.extraerPositivo());
                        return new Logaritmo(((Funcion) A).getParametro1(), ((Funcion) B).getParametro1(), bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                        // return new Funcion(TipoDeEnvoltura.LOGARITMO, ((Funcion) A).getParametro1(), ((Funcion) B).getParametro1(), bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                        // }
                    }
                }
            }

            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.SENO) {
                if (B instanceof Funcion) {
                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.COSENO) {
                        //caso sen(A) / cos(B) debuelve tan(A)
                        if (((Funcion) A).getParametro1().igualA(((Funcion) A).getParametro1())) {
                            return new Tangente(((Funcion) A).getParametro1(), bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                            //  return new Funcion(TipoDeEnvoltura.TANGENTE, ((Funcion) A).getParametro1(), bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                        }
                    }
                }
            }
            if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.COSENO) {
                if (B instanceof Funcion) {
                    if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.SENO) {
                        //caso cos(A) / sen(B) debuelve cot(A)
                        if (((Funcion) A).getParametro1().igualA(((Funcion) A).getParametro1())) {
                            return new Cotagente(((Funcion) A).getParametro1(), bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                            //   return new Funcion(TipoDeEnvoltura.COTAGENTE, ((Funcion) A).getParametro1(), bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                        }
                    }
                }
            }

        }

        if (B instanceof Funcion) {
            if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                //caso A / A^b debuelve A^-(b-1)
                if (((Funcion) B).getParametro1().igualA(A, false) && condicional(A.esPositivo(), ((Funcion) B).getParametro1().esPositivo())) {
                    if (((Funcion) B).getParametro1().esPositivo()) {
                        B.setPositivo(B.extraerPositivo(), A.extraerPositivo());
                    }
                    ((Funcion) B).setParametro2(new Suma(Suma.inicializarRestando(new Numero(1), ((Funcion) B).getParametro2())));
                    return B;
                }

                if (A instanceof Funcion) {
                    if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                        //caso  sqr[c](A) / A^b debuelve A^(1/c-b)    
                        if (((Funcion) B).getParametro1().igualA(((Funcion) A).getParametro2())) {//((Funcion) B).getParametro2()
                            B.setPositivo(B.extraerPositivo(), A.extraerPositivo());
                            ((Funcion) B).setParametro2(Suma.inicializarRestando(new FraccionGeneral(1, ((Funcion) A).getParametro1()), ((Funcion) B).getParametro2()));
                            return B;
                        }
                    }
                }

            }

            if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                //caso  A / sqr[b](A) debuelve A^(1-1/b)
                if (((Funcion) B).getFirstParametro2().igualA(A, false)) {
                    return new Exponente(A, Suma.inicializarRestando(new Numero(1), new FraccionGeneral(1, ((Funcion) B).getFirstParametro1())), !((Funcion) B).getFirstParametro2().esPositivo() ? B.esPositivo() : bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                    //   return new Funcion(TipoDeEnvoltura.EXPONETE, A, Suma.inicializarRestando(new Numero(1), new FraccionGeneral(1, ((Funcion) B).getFirstParametro1())), !((Funcion) B).getFirstParametro2().esPositivo() ? B.esPositivo() : bicondicional(A.extraerPositivo(), B.extraerPositivo()));
                }

                if (((Funcion) B).getParametro2() instanceof Funcion && ((Funcion) ((Funcion) B).getParametro2()).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                    //caso B /sqr[b](B^c)   debuelve A^(c/b-1)
                    if (((Funcion) ((Funcion) B).getParametro2()).getParametro1().igualA(A, false) && condicional(A.esPositivo(), ((Funcion) ((Funcion) B).getParametro2()).getParametro1().esPositivo())) {
                        return new Exponente(((Funcion) ((Funcion) B).getParametro2()).getParametro1(), Suma.inicializarRestando(new Numero(1), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), ((Funcion) ((Funcion) B).getParametro2()).getParametro1().esPositivo() ? bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()) : bicondicional(B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()));
                        //     return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) ((Funcion) B).getParametro2()).getParametro1(), Suma.inicializarRestando(new Numero(1), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), ((Funcion) ((Funcion) B).getParametro2()).getParametro1().esPositivo() ? bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()) : bicondicional(B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()));
                    }

                    if (A instanceof Funcion) {
                        if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.EXPONETE) {
                            //caso  A^d / sqr[b](A^c)  debuelve A^(d-c/b)
                            if (((Funcion) ((Funcion) B).getParametro2()).getParametro1().igualA(((Funcion) A).getParametro1())) {
                                A.setPositivo(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo());
                                ((Funcion) A).setParametro2(Suma.inicializarRestando(((Funcion) A).getParametro2(), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())));
                                return A;
                            }
                        }
                        if (((Funcion) A).getTipoDeEnvoltura() == TipoDeEnvoltura.RAIZ) {
                            //caso sqr[d](A) / sqr[b](A^c)   debuelve A^(1/d-c/b)
                            if (((Funcion) ((Funcion) B).getParametro2()).getParametro1().igualA(((Funcion) A).getParametro2())) {
                                return new Exponente(((Funcion) A).getParametro2(), Suma.inicializarRestando(new FraccionGeneral(1, ((Funcion) A).getParametro1()), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()));
                                //   return new Funcion(TipoDeEnvoltura.EXPONETE, ((Funcion) A).getParametro2(), Suma.inicializarRestando(new FraccionGeneral(1, ((Funcion) A).getParametro1()), new FraccionGeneral(((Funcion) ((Funcion) B).getParametro2()).getParametro2(), ((Funcion) B).getParametro1())), bicondicional(A.extraerPositivo(), B.extraerPositivo(), ((Funcion) B).getParametro2().extraerPositivo()));
                            }
                        }

                    }

                }

            }
            if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.TANGENTE) {

                if (A.IsNumero()) {
                    //caso 1 / tan(A) debelve cot(A)
                    if (A.getNumero() == 1 || A.getNumero() == -1) {
                        return new Cotagente(((Funcion) B).getParametro1(), bicondicional(A.getNumero() == 1, B.esPositivo()));
                        //    return new Funcion(TipoDeEnvoltura.COTAGENTE, ((Funcion) B).getParametro1(), bicondicional(A.getNumero() == 1, B.esPositivo()));
                    }
                }
            }
            if (((Funcion) B).getTipoDeEnvoltura() == TipoDeEnvoltura.COTAGENTE) {

                if (A.IsNumero()) {
                    //caso 1 / tan(A) debelve cot(A)
                    if (A.getNumero() == 1 || A.getNumero() == -1) {
                        return new Tangente(((Funcion) B).getParametro1(), bicondicional(A.getNumero() == 1, B.esPositivo()));
                        //  return new Funcion(TipoDeEnvoltura.TANGENTE, ((Funcion) B).getParametro1(), bicondicional(A.getNumero() == 1, B.esPositivo()));
                    }
                }
            }

        }
        return new Operando();
    }

    @Override
    public void simplificar(boolean continuar) {
        //  System.out.println("this=" + this);
        //   System.out.println("numerador=" + numerador);
        numerador.simplificar(continuar);
        //  System.out.println("numerador=" + numerador);
        //    System.out.println("denominador=" + denominador);
        denominador.simplificar(continuar);
        //   System.out.println("denominador=" + denominador);
        //    System.out.println("this=" + this);
        inicializarOptimo();
        //    System.out.println("this=" + this);
        if (continuar) {
            simplificar(false);
        }

    }

    @Override
    public FraccionGeneral[] crearArreglo(int leng) {
        return new FraccionGeneral[leng];
    }

    @Override
    public FraccionGeneral[][] crearArreglo(int filas, int columnas) {
        return new FraccionGeneral[filas][columnas];
    }

    @Override
    public FraccionGeneral[][][] crearArreglo(int filas, int columnas, int cantidad) {
        return new FraccionGeneral[filas][columnas][cantidad];
    }

    @Override
    public boolean contieneVariable(char v) {
        return numerador.contieneVariable(v) || denominador.contieneVariable(v);
    }

    @Override
    public Operando inicializarDerivada(char v) {
        FraccionGeneral derivacion = (FraccionGeneral) (derivado = copia());
        Operando DerivadaNumerador = derivacion.getNumerador().inicializarDerivada(v);
        Operando DerivadaDenominador = derivacion.getDenominador().inicializarDerivada(v);
        return (derivado = new FraccionGeneral(Suma.inicializarRestando(new Multiplicacion(DerivadaDenominador, derivacion.getNumerador()), new Multiplicacion(DerivadaNumerador, derivacion.getDenominador())), new Exponente(derivacion.getDenominador().copia(), new Numero(2)),bicondicional(positivo, true)));
    }
}
//
//if (denominador.isNumero() && denominador.getNumero() == 1) {
//            if (numerador.isNumero() && (numerador.getNumero() == 1 || numerador.getNumero() == 1)) {
//                fraccion += numerador.getNumero() == 1 ? "1" : "-1";
//            } else {
//                fraccion += numerador.isPositivo() ? numerador : "-" + numerador;
//            }
//        } else {
//            
//        }


//2fraccion=     ***********************
