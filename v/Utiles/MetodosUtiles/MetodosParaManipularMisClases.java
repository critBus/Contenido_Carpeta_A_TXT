/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.MetodosUtiles;

import Utiles.ClasesUtiles.Matematicas.*;
//import Utiles.ClasesUtiles.*;
//import Utiles.ClasesUtiles.Matematicas.Constantes.*;
import Utiles.ClasesUtiles.Matematicas.Funciones.*;
import static Utiles.ClasesUtiles.Matematicas.Operando.getPermitidos;
import static Utiles.MetodosUtiles.MetodosUtiles.*;
import static Utiles.MetodosUtiles.Arreglos.*;
import static Utiles.MetodosUtiles.Visual.seguridadJTextField;
import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Metodos que implementas las clases dentro del paquete Utiles Version 1.8
 *
 * @author Rene
 */
public abstract class MetodosParaManipularMisClases {
//<b></b>
//1-sen(-8)

    /**
     * Si JTextField no cumplue con los requisitos para el calculo se desactiva
     * el componente y se cambia el color de la letra del JTextField al color
     * rojo, si cumple se activa el componente y sus letras pasan al color
     * negro.Devuelve false si JTextField fue erroneo
     *
     * @param componente JComponente: a activar o desactivar
     * @param T JTextField
     * @return boolean
     */
    public static boolean seguridadStringOperando(JComponent componente, JTextField T) {
        return seguridadStringOperando(Color.BLACK, Color.RED, new JComponent[]{componente}, T);
    }

    /**
     * Si un JTextField no cumplue con los requisitos para el calculo se
     * desactivan los componentes y se cambia el color de la letra del
     * JTextField al color de error, si todos cumplen se activan los componentes
     * y sus letras pasan al color inicial.Devuelve false si algún JTextField
     * fue erroneo
     *
     * @param colorInicial Color: de estado true
     * @param colorError Color: de estado false
     * @param componentes Arreglo JComponentes: a activar o desactivar
     * @param T Arreglo JTextField:
     * @return boolean
     */
    public static boolean seguridadStringOperando(Color colorInicial, Color colorError, JComponent componentes[], JTextField... T) {
        boolean estado[] = new boolean[T.length];
        for (int i = 0; i < T.length; i++) {
            estado[i] = comprobarStringOperando(eliminarEspaciosDeStringLine(T[i].getText()));
        }
        return seguridadJTextField(colorInicial, colorError, estado, componentes, T);
    }

    /**
     * <b>Si el String contiene alguna combinación no permitida para la
     * ejecución del interpreteString() devuelve false </b>
     *
     * @param a String
     * @return
     */
    public static boolean comprobarStringOperando(String a) {

        if ((a.length() == 1 && a.charAt(0) == '-') || a.length() > 1 && (a.charAt(0) == '-' && a.charAt(1) == '-') || (a.charAt(a.length() - 1) == '-' && a.charAt(a.length() - 2) == '-')) {
            return false;
        }

        if (!masDeUnPuntoOComa(a) || !comprobarCierres(a, TipoDeEnvoltura.getInicios(), TipoDeEnvoltura.getMedios(), TipoDeEnvoltura.getFinales()) || !comprobarCierreDeParentesisCorchete(a) || charEnPosicionInicialoFinal(a, new char[]{'+', '*', '/', '.', ',', '^'}, '+', '*', '/', '.', ',', '^', '-')
                || containsString(a, "++", "-+", "---", "+^", "^+", "-^", "^--", "**", "()", "(+", "(--", "(*", "(/", "(^", "(]", "[)",
                        "/*", "*/", "*+", "+*", "-*", "*--", "..", ",,", ",.", ".,", "^^", "^*", "*^", "[]", "[+", "[--", "[*", "[/", "[^",
                        "/^", "^/", "//", "/+", "+/", "-/", "/--", ".+", ".-", ".*", "./", "+)", "-)", "*)", "/)", "^)", "+]", "-]", "*]", "/]", "^]",
                        "." + TipoDeConstante.EULER.getCaracter(), "." + TipoDeConstante.PI.getCaracter(), ".^", ",+", ",-", ",*", ",/", "," + TipoDeConstante.EULER.getCaracter(), "," + TipoDeConstante.PI.getCaracter(), ",^", "+.", "-.", "*.", "/.",
                        TipoDeConstante.EULER.getCaracter() + ".", TipoDeConstante.PI.getCaracter()
                        + ".", "^.", "+,", "-,", "*,", "/,", TipoDeConstante.EULER.getCaracter() + ",",
                        TipoDeConstante.PI.getCaracter() + ",", "^,")) {
            return false;
        }
        if (!esPemitido(a, UnirDosArreglosObject(TipoDeEnvoltura.getInicios().toArray(new String[]{}),
                new String[]{"+", "-", "*", "/", "^", "](", ")", "(", ".", ","}, StringMinusculas, StringNumeros, TipoDeConstante.getCaracteresString()))) {
            return false;
        }
        //System.out.println("ssssssssssssssss");
        for (int i = 0; i < a.length() - 1; i++) {
            if ((esCharLetraMinuscula(a.charAt(i)) && puntoOComa(a.charAt(i + 1)))
                    || (puntoOComa(a.charAt(i)) && esCharLetraMinuscula(a.charAt(i + 1)))) {
                return false;
            }
        }
        return true;

    }

    public static boolean seguridadStringRespuestaCola(JComponent componente, JTextField T) {
        return seguridadStringRespuestaCola(Color.BLACK, Color.RED, new JComponent[]{componente}, T);
    }

    public static boolean seguridadStringRespuestaCola(Color colorInicial, Color colorError, JComponent componentes[], JTextField... T) {
        boolean estado[] = new boolean[T.length];
        for (int i = 0; i < T.length; i++) {
            estado[i] = comprobarStringRespuestaCola(eliminarEspaciosDeStringLine(T[i].getText()));
        }
        return seguridadJTextField(colorInicial, colorError, estado, componentes, T);
    }

    public static boolean comprobarStringRespuestaCola(String a) {

        if ((a.length() == 1 && a.charAt(0) == '-') || a.length() > 1 && (a.charAt(0) == '-' && a.charAt(1) == '-') || (a.charAt(a.length() - 1) == '-' && a.charAt(a.length() - 2) == '-')) {
            return false;
        }

        if (!masDeUnPuntoOComa(a) || !comprobarCierres(a, TipoDeEnvoltura.getInicios(), TipoDeEnvoltura.getMedios(), TipoDeEnvoltura.getFinales()) || !comprobarCierreDeParentesisCorchete(a) || charEnPosicionInicialoFinal(a, new char[]{'+', '*', '/', '.', ',', '^'}, '+', '*', '/', '.', ',', '^', '-')
                || containsString(a, "++", "-+", "---", "+^", "^+", "-^", "^--", "**", "()", "(+", "(--", "(*", "(/", "(^", "(]", "[)",
                        "/*", "*/", "*+", "+*", "-*", "*--", "..", ",,", ",.", ".,", "^^", "^*", "*^", "[]", "[+", "[--", "[*", "[/", "[^",
                        "/^", "^/", "//", "/+", "+/", "-/", "/--", ".+", ".-", ".*", "./", "+)", "-)", "*)", "/)", "^)", "+]", "-]", "*]", "/]", "^]",
                        "." + TipoDeConstante.EULER.getCaracter(), "." + TipoDeConstante.PI.getCaracter(), ".^", ",+", ",-", ",*", ",/", "," + TipoDeConstante.EULER.getCaracter(), "," + TipoDeConstante.PI.getCaracter(), ",^", "+.", "-.", "*.", "/.",
                        TipoDeConstante.EULER.getCaracter() + ".", TipoDeConstante.PI.getCaracter()
                        + ".", "^.", "+,", "-,", "*,", "/,", TipoDeConstante.EULER.getCaracter() + ",",
                        TipoDeConstante.PI.getCaracter() + ",", "^,")) {
            return false;
        }//TipoDeEnvoltura.getInicios().toArray(new String[]{})
        if (!esPemitido(a, UnirDosArreglosObject(TipoDeEnvoltura.getInicios().toArray(new String[]{}),
                new String[]{"+", "-", "*", "/", "^", "](", ")", "(", ".", ","}, StringNumeros, TipoDeConstante.getCaracteresString()))) {
            return false;
        }

        return true;
    }

    /**
     * <b>Sustituye las constantes en un Operando </b>
     *
     * @param o Operando contiene operaciones matemáticas
     *
     * @return
     */
    public static Operando sustituirConstantes(Operando o) {
        return sustituirConstantes(o, true);
    }

    /**
     * <b>Sustituye las constantes en un Operando </b>
     *
     *
     * @param o Operando contiene operaciones matemáticas
     * @param simplificar  <b>Si el parámetro simplificar es true entonces
     * después de la sustitución se simplifica el Operando resultante</b>
     * @return
     */
    public static Operando sustituirConstantes(Operando o, boolean simplificar) {
        String operacion = o.toString();
//        // System.out.println("operacion="+operacion);
        String salida = sustituirConstantesOperando(operacion);
//        for (int i = 0; i < operacion.length(); i++) {
//            int j = Constante.contienenCaracter(operacion.charAt(i));
//            salida += j > -1 ? TipoDeConstante.values()[j].getValor() : operacion.charAt(i) + "";
//            //  System.out.println("sss "+salida);
//        }
        return salida.equals(operacion) ? o : (simplificar ? interpreteStringSimplificado(salida) : interpreteString(salida));
    }

    public static String sustituirConstantesOperando(String a) {
        ArrayList<String> antes = TipoDeEnvoltura.getFinales();
        antes.addAll(Arrays.asList(TipoDeConstante.getCaracteresString()));
        antes.addAll(Arrays.asList(StringMinusculas));
        ArrayList<String> despues = TipoDeEnvoltura.getInicios();
        despues.addAll(Arrays.asList(TipoDeConstante.getCaracteresString()));
        despues.addAll(Arrays.asList(StringMinusculas));

        return sustituirConstantes(a, antes, despues);
    }

    public static String sustituirConstantesCola(String a) {
        ArrayList<String> antes = TipoDeEnvoltura.getFinales();
        antes.addAll(Arrays.asList(TipoDeConstante.getCaracteresString()));
        ArrayList<String> despues = TipoDeEnvoltura.getInicios();
        despues.addAll(Arrays.asList(TipoDeConstante.getCaracteresString()));
        return sustituirConstantes(a, antes, despues);
    }

    public static String sustituirConstantes(String a, ArrayList<String> antes, ArrayList<String> despues) {
        String salida = "";
        //boolean aContinuacion = false;
        for (int i = 0; i < a.length(); i++) {
            System.out.println("salida1=" + salida);
            int j = TipoDeConstante.contienenCaracter(a.charAt(i));
//            ArrayList<String> antes = TipoDeEnvoltura.getFinales();
//            antes.addAll(Arrays.asList(TipoDeConstante.getCaracteresString()));
//            ArrayList<String> despues = TipoDeEnvoltura.getInicios();
//            despues.addAll(Arrays.asList(TipoDeConstante.getCaracteresString()));
            if (j > -1 && esPor(a, i, antes, despues)) {
                salida += "*";

            }
            salida += j > -1 ? TipoDeConstante.values()[j].getValor() : a.charAt(i) + "";

//                if (aContinuacion) {
//                    salida += "*";
//                }
//                aContinuacion = true;
//            }else{
//            aContinuacion = false;
//            }
            //  System.out.println("salida2="+salida);
        }
        return salida;
    }

    public static String sustituirVariableString(String a, char X, double valor) {
        return sustituirVariableString(a, X, valor, new ArrayList<String>(), new ArrayList<String>());
    }

    public static String sustituirVariableString(String a, char X, double valor, ArrayList<String> antes, ArrayList<String> despues) {
        a = eliminarEspaciosDeStringLine(a);
        if (a.isEmpty()) {
            return "";
        }
        antes.addAll(TipoDeEnvoltura.getFinales());
        antes.addAll(Arrays.asList(TipoDeConstante.getCaracteresString()));
        antes.addAll(Arrays.asList(StringMinusculas));
        ArrayList<String> inicioDeEnvolturas = TipoDeEnvoltura.getInicios();
        despues.addAll(inicioDeEnvolturas);
        despues.addAll(Arrays.asList(TipoDeConstante.getCaracteresString()));
        despues.addAll(Arrays.asList(StringMinusculas));
        String salida = "";

        //boolean aContinuacion = false;
        for (int i = 0; i < a.length(); i++) {

            int indiceEnvolturaInicial = contieneStringAEnIndiceAArrayListStringB(i, a, inicioDeEnvolturas);
            if (indiceEnvolturaInicial > -1) {
                salida += inicioDeEnvolturas.get(indiceEnvolturaInicial);
                i += inicioDeEnvolturas.get(indiceEnvolturaInicial).length() - 1;
                continue;
            }
            if (a.charAt(i) == X) {
                if (esPor(a, i, antes, despues)) {
                    salida += "*";
                }
                salida += valor;
            } else {
                salida += a.charAt(i) + "";
            }

        }
        return salida;
    }

    /**
     * <b>Interpreta de forma separada y matemática una línea String y luego
     * trata de simplificarla, si todos los datos son números puede utilizarse
     * como calculadora </b>
     * <b>Ejemplo:</b>
     * <b>Entiende que en "sen(a)+cos(b)" 'a' y 'b' son variables diferentes y
     * que sen() es una funcion que tiene como un operando a la variable a, lo
     * mismo pasa con el coseno</b>
     *
     * @param a String
     * @return
     */
    public static Operando interpreteStringSimplificado(String a) {
        Operando o = interpreteString(a);
        // System.out.println("o=" + o);
        o.simplificar(true);
        return o.getOptimo();
    }

    /**
     * <b>Interpreta de forma separada y matematica una linea String </b>
     * <b>Ejemplo:</b>
     * <b>Entiende que en "sen(a)+cos(b)" 'a' y 'b' son variables diferentes y
     * que sen() es una funcion que tiene como un operando a la variable a, lo
     * mismo pasa con el coseno</b>
     *
     * @param a String
     * @return
     */
    public static Operando interpreteString(String a) {

        ArrayList<Operando> operacionesSuma = new ArrayList<Operando>();

        Numero numero = new Numero(0);

        Funcion funcion = new Funcion();//
        Funcion exponente = new Funcion();
        Variable variable = new Variable('x');//
        FraccionGeneral fraccion = new FraccionGeneral();
        Multiplicacion multiplicacion = new Multiplicacion();
        Constante constante = new Constante();
        ArrayList<Operando> operandos = new ArrayList<Operando>();
        ArrayList<Character> caracteresDeContantes = TipoDeConstante.getCaracteres2();
        int contadorSalto = 0;
        boolean hayMultiplicacion = false;
        boolean hayFuncion = false;
        boolean hayVariable = false;
        boolean hayFraccion = false;
        boolean hayNumero = false;
        boolean hayExponente = false;
        boolean hayConstante = false;
        boolean positivo = true;
        boolean ExponenteEnMultiplicacion = false;
        boolean ExponenteEnFraccion = false;
        boolean ExponenteEnExponente = false;

        for (int i = 0; i < a.length(); i++) {

            // System.out.println("i=" + i + " a.charAt(i)=" + a.charAt(i));
            if (contadorSalto-- != 0) {
                continue;
            } else {
                contadorSalto = 0;
            }
            try {

                //en caso de haber anteriormente un numero 5-5           
                double numero2[] = buscarNumeroYCantidadDeCaracteresOriginal(a, i, getPermitidos());
                if (hayNumero) {
                    operacionesSuma.add(new Numero(numero));
                }
                //  System.out.println("aqui");
                numero.setNumero(numero2[0]);
                //    System.out.println("numero=" + numero.getNumero());
                if (!positivo) {
                    numero.setNumero(numero.getNumero() * -1);
                }
                if (hayMultiplicacion) {
                    multiplicacion.addOperando(new Numero(numero));
                    // System.out.println("multiplicacion=" + multiplicacion);

                }
                //caso existe anterirormente una funcion fun65
                if (hayFuncion) {
                    multiplicacion.addOperando(funcion.copia());
                    multiplicacion.addOperando(new Numero(numero));
                    hayFuncion = false;
                    hayMultiplicacion = true;
                }
                //caso existe una variable x25
                if (hayVariable) {//!!!!!!!!!!!!!!!!!!!
                    multiplicacion.addOperando(new Variable(variable));
                    multiplicacion.addOperando(new Numero(numero));
                    hayVariable = false;
                    hayMultiplicacion = true;

                }
                if (hayConstante) {
                    multiplicacion.addOperando(constante.copia());
                    multiplicacion.addOperando(new Numero(numero));
                    hayConstante = false;
                    hayMultiplicacion = true;

                }
                //caso existe una fraccion (solo numerador) ope/45
                if (hayFraccion) {
                    // System.out.println("numero=" + numero);
                    // System.out.println("n1 numerador=" + fraccion.getNumerador());
                    fraccion.addDenominador(new Numero(numero));
                    // System.out.println("n2 numerador=" + fraccion.getNumerador());
                    //  System.out.println("denominador=" + fraccion.getDenominador().toString());
                }
                if (hayExponente) {
                    funcion.addParametro2(new Numero(numero));
                    //  System.out.println("funcion=" + funcion);
                }
                contadorSalto += (int) numero2[1] - 1;
                //  System.out.println("contadorSalto+=" + contadorSalto);
                hayNumero = !hayMultiplicacion && !hayVariable && !hayFraccion && !hayExponente;
                //  System.out.println("hayNumero=" + hayNumero);
                positivo = true;
                continue;

            } catch (NumberFormatException e) {
                //nada lo salta si no es un numero

            }
            //buscar funciones hay que diferenciar las que tienen medios y las que no
            //  System.out.println("a.charAt(i)=" + a.charAt(i));
            int j = contieneStringAEnIndiceAArrayListStringB(i, a, Funcion.getInicios());
            // System.out.println("j=" + j);
            if (j > -1) {
                Funcion exponente2 = funcion; //paso e1 se crea fuera de un if para que no se pierda
                int indices[] = posicionesDeTerminacionDe(a, Funcion.getInicios(), Funcion.getMedios(), Funcion.getFinales(), Funcion.getInicios().get(j).length() + i);
                //   System.out.println("indices=" + Arrays.toString(indices));
                if (hayFuncion) {
                    multiplicacion.addOperando(funcion.copia());
                }
                if (Funcion.getMedios().get(j).equals("")) {
                    //     System.out.println("1a.sub=" + a.substring(Funcion.getInicios().get(j).length() + i, indices[1]));
                    //     System.out.println("TipoDeEnvoltura.values()[j]=" + TipoDeEnvoltura.values()[j]);
                    funcion = Funcion.crearFuncion(TipoDeEnvoltura.values()[j], interpreteString(a.substring(Funcion.getInicios().get(j).length() + i, indices[1])), positivo);
                    //     System.out.println("funcion=" + funcion);
                    //     System.out.println("funcion.getTipoDeEnvoltura()=" + funcion.getTipoDeEnvoltura());
                } else {
                    //     System.out.println("2  1a.sub=" + a.substring(Funcion.getInicios().get(j).length() + i, indices[0]) + " 21a.sub=" + a.substring(Funcion.getMedios().get(j).length() + indices[0], indices[1]));
                    funcion = Funcion.crearFuncion(TipoDeEnvoltura.values()[j], interpreteString(a.substring(Funcion.getInicios().get(j).length() + i, indices[0])), interpreteString(a.substring(Funcion.getMedios().get(j).length() + indices[0], indices[1])), positivo);
                }
                //  System.out.println("s i=" + i + " indices[1]=" + indices[1] + " funcion.getFin()=" + funcion.getFin());
                //   System.out.println("indices[1] - 1 + funcion.getFin().length()=" + (indices[1] - 1 + funcion.getFin().length()));
                i = indices[1] - 1 + funcion.getFin().length();
                //    System.out.println("salto i=" + i);
                if (hayFuncion) {
                    multiplicacion.addOperando(funcion.copia());
                    hayMultiplicacion = true;
                } else {
                    //caso existe una multiplicacion
                    if (hayMultiplicacion) {
                        multiplicacion.addOperando(funcion.copia());
                    }
                    //caso existe un numero 45fun
                    if (hayNumero) {
                        multiplicacion.addOperando(new Numero(numero));
                        multiplicacion.addOperando(funcion.copia());
                        hayNumero = false;
                        hayMultiplicacion = true;
                    }
                    //caso existe una variable xfun
                    if (hayVariable) {
                        multiplicacion.addOperando(new Variable(variable));
                        // System.out.println("1 multiplicacion=" + multiplicacion);
                        multiplicacion.addOperando(funcion.copia());
                        //  System.out.println("2 multiplicacion=" + multiplicacion);
                        hayMultiplicacion = true;
                        hayVariable = false;
                    }
                    if (hayConstante) {
                        multiplicacion.addOperando(constante.copia());
                        multiplicacion.addOperando(funcion.copia());
                        hayConstante = false;
                        hayMultiplicacion = true;
                    }
                    if (hayFraccion) {
                        fraccion.addDenominador(funcion.copia());
                    }
                    if (hayExponente) {
                        exponente2.addParametro2(funcion.copia());
                        funcion = exponente2;
                    }
                }
                hayFuncion = !hayMultiplicacion && !hayFraccion && !hayExponente;
                positivo = true;
                // System.out.println("hayFuncion=" + hayFuncion);
                continue;
            }

            //buscar vatiables
            if (a.charAt(i) >= 97 && a.charAt(i) <= 122) {
                if (hayVariable) {
                    multiplicacion.addOperando(new Variable(variable));
                    //  System.out.println("1 multiplicacion=" + multiplicacion);
                    multiplicacion.addOperando(new Variable(a.charAt(i), positivo));
                    hayMultiplicacion = true;
                } else {
                    variable = new Variable(a.charAt(i), positivo);
                    // System.out.println("v positivo=" + variable.esPositivo());
                    if (hayMultiplicacion) {
                        //caso el ultimo operando en la multiplicacion es un numero
                        multiplicacion.addOperando(new Variable(variable));
                    }
                    if (hayNumero) {
                        multiplicacion.addOperando(new Numero(numero));
                        multiplicacion.addOperando(new Variable(variable));
                        hayMultiplicacion = true;
                        hayNumero = false;
                    }
                    if (hayFuncion) {
                        multiplicacion.addOperando(funcion.copia());
                        multiplicacion.addOperando(new Variable(variable));
                        hayMultiplicacion = true;
                        hayFuncion = false;
                    }
                    if (hayConstante) {
                        multiplicacion.addOperando(constante.copia());
                        multiplicacion.addOperando(new Variable(variable));
                        hayConstante = false;
                        hayMultiplicacion = true;
                    }
                    if (hayFraccion) {
                        //caso el ultimo el en la multiplicacion del denominador es un numero
                        //  System.out.println("1 fraccion=" + fraccion);
                        //   System.out.println("fraccion.getNumerador().esPositivo()=" + fraccion.getNumerador().esPositivo());
                        //   System.out.println("actual para el denominador  variable.isPositivo()=" + variable.esPositivo());
                        fraccion.addDenominador(new Variable(variable));
                        //  System.out.println("2 fraccion=" + fraccion + " fraccion.isPositivo()=" + fraccion.esPositivo());
                    }
                    if (hayExponente) {
                        //caso el ultimo el en la multiplicacion del indice es un numero
                        funcion.addParametro2(new Variable(variable));
                        // System.out.println("funcion=" + funcion);
                    }
                }
                hayVariable = !hayMultiplicacion && !hayFraccion && !hayExponente;
                // System.out.println("hayVariable=" + hayVariable);
                positivo = true;
                continue;
            }

            //encontro constantes ~ E
            if (caracteresDeContantes.contains(a.charAt(i))) {

                if (hayConstante) {
                    multiplicacion.addOperando(constante.copia());
                    // System.out.println("1 multiplicacion=" + multiplicacion);
                    multiplicacion.addOperando(Constante.obtenerConstante(a.charAt(i), positivo));
                    hayMultiplicacion = true;
                } else {
                    constante = Constante.obtenerConstante(a.charAt(i), positivo);
                    if (hayMultiplicacion) {
                        //caso el ultimo operando en la multiplicacion es un numero
                        multiplicacion.addOperando(constante.copia());
                    }
                    if (hayNumero) {
                        multiplicacion.addOperando(new Numero(numero));
                        multiplicacion.addOperando(constante.copia());
                        hayMultiplicacion = true;
                        hayNumero = false;
                    }
                    if (hayFuncion) {
                        multiplicacion.addOperando(funcion.copia());
                        multiplicacion.addOperando(constante.copia());
                        hayMultiplicacion = true;
                        hayFuncion = false;
                    }
                    if (hayVariable) {
                        multiplicacion.addOperando(variable.copia());
                        multiplicacion.addOperando(constante.copia());
                        hayMultiplicacion = true;
                        hayVariable = false;
                    }
                    if (hayFraccion) {
                        //  System.out.println("1 fraccion=" + fraccion);
                        //  System.out.println("fraccion.getNumerador().esPositivo()=" + fraccion.getNumerador().esPositivo());
                        fraccion.addDenominador(constante.copia());
                        //  System.out.println("2 fraccion=" + fraccion + " fraccion.isPositivo()=" + fraccion.esPositivo());
                    }
                    if (hayExponente) {
                        funcion.addParametro2(constante.copia());
                        // System.out.println("funcion=" + funcion);
                    }

                }
                hayConstante = !hayMultiplicacion && !hayFraccion && !hayExponente;
                // System.out.println("hayConstante=" + hayConstante);
                positivo = true;
                continue;

            }

            //fracion encontro '/'
            if (a.charAt(i) == '/') {
                if (hayMultiplicacion) {
                    fraccion = new FraccionGeneral(new Multiplicacion(multiplicacion), positivo);
                    hayMultiplicacion = false;
                    multiplicacion.clear();
                    // System.out.println("fraccion=" + fraccion);
                    //  System.out.println("numerador=" + fraccion.getNumerador());
                    //  System.out.println("n3 numerador=" + fraccion.getNumerador());
                }
                if (hayNumero) {
                    fraccion = new FraccionGeneral(new Numero(numero));
                    hayNumero = false;
                }
                if (hayFuncion) {
                    // System.out.println("positivo=" + positivo);
                    fraccion = new FraccionGeneral(funcion.copia(), positivo);
                    hayFuncion = false;
                }
                if (hayVariable) {
                    //  System.out.println("positivo=" + positivo);
                    fraccion = new FraccionGeneral(new Variable(variable), positivo);
                    //  System.out.println("f fraccion=" + fraccion + " numerador=" + fraccion.getNumerador() + " " + fraccion.getNumerador().esPositivo());
                    hayVariable = false;
                }
                if (hayConstante) {
                    fraccion = new FraccionGeneral(constante.copia(), positivo);
                    hayConstante = false;
                }
                //caso 5/4/ 
                if (hayFraccion) {
                    fraccion = new FraccionGeneral(new FraccionGeneral(fraccion), positivo);
                }
                if (hayExponente) {
                    if (operandos.size() > 1) {
                        ((Funcion) operandos.get(operandos.size() - 1)).addParametro2(funcion.copia());
                        for (int k = operandos.size() - 2; k >= 0; k--) {
                            if (k == 0) {
                                if (operandos.get(k) instanceof Multiplicacion) {
                                    ((Multiplicacion) operandos.get(k)).addOperando(operandos.get(k + 1));
                                }
                                if (operandos.get(k) instanceof FraccionGeneral) {
                                    ((FraccionGeneral) operandos.get(k)).addDenominador(operandos.get(k + 1));
                                }
                                if (operandos.get(k) instanceof Funcion) {
                                    ((Funcion) operandos.get(k)).addParametro2(operandos.get(k + 1));
                                }

                            } else {
                                ((Funcion) operandos.get(k)).addParametro2(operandos.get(k + 1));
                            }
                        }
                        //  new FraccionGeneral(operandos.get(0).copia(), positivo);

                        ExponenteEnMultiplicacion = false;
                        ExponenteEnFraccion = false;
                        ExponenteEnExponente = false;

                    } else {
                        if (ExponenteEnMultiplicacion) {

                            multiplicacion.addOperando(funcion.copia());
                            fraccion = new FraccionGeneral(new Multiplicacion(multiplicacion), positivo);
                            multiplicacion.clear();
                            ExponenteEnMultiplicacion = false;
                        } else {
                            if (ExponenteEnFraccion) {
                                fraccion.addDenominador(funcion.copia());
                                fraccion = new FraccionGeneral(new FraccionGeneral(fraccion));
                                ExponenteEnFraccion = false;
                            } else {
                                if (ExponenteEnExponente) {
                                    exponente.addParametro2(funcion.copia());
                                    fraccion = new FraccionGeneral(exponente.copia());
                                    ExponenteEnExponente = false;
                                } else {
                                    fraccion = new FraccionGeneral(funcion.copia(), positivo);
                                }
                            }
                        }
                    }
                    operandos.clear();
                    hayExponente = false;
                    // System.out.println("fraccion=" + fraccion);
                }
                hayFraccion = true;
                positivo = true;
                continue;
            }

            //se encontro con ^
            if (a.charAt(i) == '^') {
                if (hayFuncion) {
                    //  System.out.println("funcion=" + funcion);
                    exponente = (Funcion) funcion.copia();
                    funcion = new Exponente(exponente.copia(), 1, bicondicional(positivo, exponente.extraerPositivo()));
                    hayFuncion = false;
                } else {
                    if (hayExponente) {
                        exponente = (Funcion) funcion.copia();
                        ExponenteEnExponente = true;
                        funcion = new Exponente(exponente.extraerLastParametro2(), 1, bicondicional(positivo, funcion.getParametro1().extraerPositivo()));
                        operandos.add(exponente.copia());
                    }
                    if (hayFraccion) {
                        positivo = bicondicional(positivo, fraccion.getLastDenominador().esPositivo());
                        funcion = new Exponente(fraccion.extraerLastDenominador(), 1, positivo);
                        hayFraccion = false;
                        ExponenteEnFraccion = true;
                        operandos.add(fraccion.copia());
                    }
                    if (hayMultiplicacion) {
                        //  System.out.println("multiplicacion=" + multiplicacion);
                        positivo = bicondicional(positivo, multiplicacion.getLastOperando().esPositivo());
                        //   System.out.println("positivo=" + positivo);
                        funcion = new Exponente(multiplicacion.extraerLastOperando(), 1, positivo);
                        //   System.out.println("funcion=" + funcion);
                        //   System.out.println("multiplicacion=" + multiplicacion);
                        hayMultiplicacion = false;
                        ExponenteEnMultiplicacion = true;
                        operandos.add(multiplicacion.copia());

                    }
                    if (hayNumero) {
                        funcion = new Exponente(new Numero(numero), 1, bicondicional(positivo, numero.extraerPositivo()));
                        hayNumero = false;
                    }
                    if (hayVariable) {
                        funcion = new Exponente(new Variable(variable), 1, bicondicional(positivo, variable.extraerPositivo()));
                        hayVariable = false;
                    }
                    if (hayConstante) {
                        funcion = new Exponente(constante.copia(), 1, bicondicional(positivo, variable.extraerPositivo()));
                        hayConstante = false;
                    }

                }
                hayExponente = true;
                continue;
            }

            //condiciones de parada + - *
            if (a.charAt(i) == '+' || a.charAt(i) == '-') {
                positivo = a.charAt(i) != '-';
                // System.out.println("positivo=" + positivo);
                if (!esSignoMenos(a, i, getPermitidos(), Funcion.getInicios())) {
                    if (hayMultiplicacion) {
                        operacionesSuma.add(new Multiplicacion(multiplicacion));
                        multiplicacion.clear();
                        hayMultiplicacion = false;
                    }

                    if (hayNumero) {
                        //   System.out.println("añadio en suma a el numero " + numero);
                        operacionesSuma.add(new Numero(numero));
                        hayNumero = false;
                    }
                    if (hayFuncion) {
                        operacionesSuma.add(funcion.copia());
                        hayFuncion = false;
                    }
                    if (hayExponente) {
                        if (operandos.size() > 1) {
                            ((Funcion) operandos.get(operandos.size() - 1)).addParametro2(funcion.copia());
                            for (int k = operandos.size() - 2; k >= 0; k--) {
                                if (k == 0) {
                                    if (operandos.get(k) instanceof Multiplicacion) {
                                        ((Multiplicacion) operandos.get(k)).addOperando(operandos.get(k + 1));
                                    }
                                    if (operandos.get(k) instanceof FraccionGeneral) {
                                        ((FraccionGeneral) operandos.get(k)).addDenominador(operandos.get(k + 1));
                                    }
                                    if (operandos.get(k) instanceof Funcion) {
                                        ((Funcion) operandos.get(k)).addParametro2(operandos.get(k + 1));
                                    }

                                } else {
                                    ((Funcion) operandos.get(k)).addParametro2(operandos.get(k + 1));
                                }
                            }
                            operacionesSuma.add(operandos.get(0).copia());

                            ExponenteEnMultiplicacion = false;
                            ExponenteEnFraccion = false;
                            ExponenteEnExponente = false;

                        } else {
                            if (ExponenteEnMultiplicacion) {
                                // System.out.println("multiplicacion=" + multiplicacion + " funcion=" + funcion);
                                multiplicacion.addOperando(funcion.copia());
                                //   System.out.println("multiplicacion=" + multiplicacion);
                                operacionesSuma.add(new Multiplicacion(multiplicacion));
                                multiplicacion.clear();
                                ExponenteEnMultiplicacion = false;
                            } else {
                                if (ExponenteEnFraccion) {
                                    fraccion.addDenominador(funcion.copia());
                                    operacionesSuma.add(new FraccionGeneral(fraccion));
                                    ExponenteEnFraccion = false;
                                } else {
                                    if (ExponenteEnExponente) {
                                        exponente.addParametro2(funcion.copia());
                                        operacionesSuma.add(exponente.copia());
                                        ExponenteEnExponente = false;
                                    } else {
                                        operacionesSuma.add(funcion.copia());
                                    }
                                }
                            }
                        }
                        operandos.clear();
                        hayExponente = false;
                    }
                    if (hayVariable) {
                        operacionesSuma.add(new Variable(variable));
                        hayVariable = false;
                    }
                    if (hayConstante) {
                        operacionesSuma.add(constante.copia());
                        hayConstante = false;
                    }
                    if (hayFraccion) {
                        operacionesSuma.add(new FraccionGeneral(fraccion));
                        hayFraccion = false;
                    }
                }
                continue;
            }

            if (a.charAt(i) == '*') {
                if (hayMultiplicacion || hayExponente || hayFraccion) {
                    continue;
                }
                if (hayNumero) {
                    multiplicacion.addOperando(new Numero(numero));
                    // System.out.println("multiplicacion=" + multiplicacion);
                    hayNumero = false;
                    hayMultiplicacion = true;
                }
                if (hayFuncion) {
                    multiplicacion.addOperando(funcion.copia());
                    hayFuncion = false;
                    hayMultiplicacion = true;
                }
//                if (hayExponente) {
//                }
                if (hayVariable) {
                    multiplicacion.addOperando(new Variable(variable));
                    hayVariable = false;
                    hayMultiplicacion = true;
                }
                if (hayConstante) {
                    multiplicacion.addOperando(constante.copia());
                    hayConstante = false;
                    hayMultiplicacion = true;
                }
//                if (hayFraccion) {
//                   }
            }
            //continue;
        }

        if (hayMultiplicacion) {
            //  System.out.println("multiplicacion=" + multiplicacion);
            operacionesSuma.add(new Multiplicacion(multiplicacion));
            //System.out.println("multiplicacion=" + multiplicacion);
            // hayMultiplicacion = false;
            multiplicacion.clear();
        }

        if (hayNumero) {
            //System.out.println("añadio en suma a el numero " + numero);
            operacionesSuma.add(new Numero(numero));
            // hayNumero = false;
        }
        if (hayFuncion) {
            operacionesSuma.add(funcion.copia());
            // hayFuncion = false;
        }
        if (hayExponente) {
            if (operandos.size() > 1) {
                mostrarList(operandos);
                //System.out.println("operandos.size()=" + operandos.size());
                ((Funcion) operandos.get(operandos.size() - 1)).addParametro2(funcion.copia());
                for (int k = operandos.size() - 2; k >= 0; k--) {
                    if (k == 0) {
                        if (operandos.get(k) instanceof Multiplicacion) {
                            ((Multiplicacion) operandos.get(k)).addOperando(operandos.get(k + 1));
                        }
                        if (operandos.get(k) instanceof FraccionGeneral) {
                            ((FraccionGeneral) operandos.get(k)).addDenominador(operandos.get(k + 1));
                        }
                        if (operandos.get(k) instanceof Funcion) {
                            ((Funcion) operandos.get(k)).addParametro2(operandos.get(k + 1));
                        }

                    } else {
                        ((Funcion) operandos.get(k)).addParametro2(operandos.get(k + 1));
                    }
                }
                operacionesSuma.add(operandos.get(0).copia());

                // ExponenteEnMultiplicacion = false;
                // ExponenteEnFraccion = false;
                // ExponenteEnExponente = false;
            } else {
                if (ExponenteEnMultiplicacion) {
                    multiplicacion.addOperando(funcion.copia());
                    operacionesSuma.add(new Multiplicacion(multiplicacion));
                    multiplicacion.clear();
                    //ExponenteEnMultiplicacion = false;
                } else {
                    if (ExponenteEnFraccion) {
                        //   System.out.println(" fraccion=" + fraccion);
                        fraccion.addDenominador(funcion.copia());
                        //   System.out.println(" fraccion=" + fraccion);
                        operacionesSuma.add(new FraccionGeneral(fraccion));
                        // ExponenteEnFraccion = false;
                    } else {
                        if (ExponenteEnExponente) {
                            exponente.addParametro2(funcion.copia());
                            operacionesSuma.add(exponente.copia());
                            // ExponenteEnExponente = false;
                        } else {
                            operacionesSuma.add(funcion.copia());
                        }
                    }
                }
            }
            operandos.clear();
            //hayExponente = false;
        }
        if (hayVariable) {
            operacionesSuma.add(new Variable(variable));
            // hayVariable = false;
        }
        if (hayConstante) {
            operacionesSuma.add(constante.copia());
            // hayConstante = false;
        }
        if (hayFraccion) {
            operacionesSuma.add(new FraccionGeneral(fraccion));
            // hayFraccion = false;
        }
        //  System.out.println("------------------");
        if (operacionesSuma.size() == 1) {
//            if (operacionesSuma.get(0) instanceof FraccionGeneral) {
//                return (FraccionGeneral) operacionesSuma.get(0);
//            }
//            if (operacionesSuma.get(0) instanceof Funcion) {
//                return (Funcion) operacionesSuma.get(0);
//            }
//            if (operacionesSuma.get(0) instanceof Numero) {
//                return (Numero) operacionesSuma.get(0);
//            }
//            if (operacionesSuma.get(0) instanceof Multiplicacion) {
//                return (Multiplicacion) operacionesSuma.get(0);
//            }
//            if (operacionesSuma.get(0) instanceof Variable) {
//                return (Variable) operacionesSuma.get(0);
//            }
            return operacionesSuma.get(0);
        }
        return new Suma(operacionesSuma);

    }

    //numeros (a.charAt(i) >= 48 && a.charAt(i)<= 57)
    //letras minusculas (a.charAt(i) >= 97 && a.charAt(i) <= 122)
    //letras mayusculas (a.charAt(i) >= 65 && a.charAt(i) <= 90)
//    public static String ponerParentesisAExponentes(String a) {
//        System.out.println("1 a=" + a);
//        int indice = a.indexOf("^");
//        System.out.println("indice=" + indice + " a.charAt(indice)=" + a.charAt(indice));
//        int extremoMenor = -1;
//        int extremoMayor = a.length();
//        while (indice > -1 && indice < a.length()) {
//            Funcion exponente = new Funcion(TipoDeEnvoltura.EXPONETE);
//            if (a.charAt(indice - 1) >= '0' && a.charAt(indice - 1) <= '9') {
//                double numero[] = buscarNumeroYCantidadDeCaracteresOriginalInverso(a, indice - 1);
//                exponente.setParametro1(numero[0]);
//                extremoMenor = indice - (int) numero[1];
//                // System.out.println("extremoMenor=" + extremoMenor + " a.charAt(extremoMenor)=" + a.charAt(extremoMenor));
//            }
//            if (a.charAt(indice - 1) >= 'a' && a.charAt(indice - 1) <= 'z') {
//                // boolean h=!(indice - 2 >= 0 && a.charAt(indice - 2) == '-' && esSignoMenos(a, indice - 2));
//                //System.out.println("h="+h);
//                Variable v = new Variable(a.charAt(indice - 1), !(indice - 2 >= 0 && a.charAt(indice - 2) == '-' && esSignoMenos(a, indice - 2)));
//                exponente.setParametro1(v);
//                extremoMenor = indice - (v.esPositivo() ? 1 : 2);
//                System.out.println("extremoMenor=" + extremoMenor + " a.charAt(extremoMenor)=" + a.charAt(extremoMenor));
//            }
//            System.out.println("indice - 1=" + (indice - 1) + " a.charAt(indice - 1)=" + a.charAt(indice - 1));
//            // int j = contieneStringAEnIndiceAArrayListStringBInverso(indice - 1, a, Funcion.getFinales());
//            int indices[] = posicionesDeTerminacionDeInverso2(a, Funcion.getInicios(), Funcion.getMedios(), Funcion.getFinales(), indice - 1);
//
//            System.out.println("indices[2]=" + indices[2]);
//            if (indices[2] >= 0) { //System.out.println("indice - Funcion.getFinales().get(j).length()="+(indice - Funcion.getFinales().get(indices[2]).length()));
//                //int indices[] = posicionesDeTerminacionDeInverso(a, Funcion.getInicios(), Funcion.getMedios(), Funcion.getFinales(),  indice - Funcion.getFinales().get(j).length() );
//                // System.out.println("indices[0]="+indices[0]+" a.charAt(indices[0])="+a.charAt(indices[0])+"indices[1]="+indices[1]+" a.charAt(indices[1])="+a.charAt(indices[1]));
//                Funcion funcion = new Funcion(TipoDeEnvoltura.EXPONETE);
//                if (Funcion.getMedios().get(indices[2]).equals("")) { //                      Funcion.getInicios().get(j).length() + i
//                    System.out.println("a.substring(indices[1]+1,indice-Funcion.getFinales().get(indices[2]).length())=" + a.substring(indices[1] + 1, indice - Funcion.getFinales().get(indices[2]).length()));
//                    funcion = new Funcion(TipoDeEnvoltura.values()[indices[2]], interpreteString(a.substring(indices[1] + 1, indice - Funcion.getFinales().get(indices[2]).length())));
//                } else {//                                                           Funcion.getInicios().get(j).length() + i         
//                    System.out.println("indices[1]+1=" + indices[1] + 1 + "  indices[0]+1-Funcion.getMedios().get(j).length()=" + (indices[0] + 1 - Funcion.getMedios().get(indices[2]).length()));
//                    System.out.println("indices[0]+1=" + indices[0] + 1 + "indice - Funcion.getFinales().get(j).length()=" + (indice - Funcion.getFinales().get(indices[2]).length()));
//                    funcion = new Funcion(TipoDeEnvoltura.values()[indices[2]], interpreteString(a.substring(indices[1] + 1, indices[0] + 1 - Funcion.getMedios().get(indices[2]).length())), interpreteString(a.substring(indices[0] + 1, indice - Funcion.getFinales().get(indices[2]).length())));
//                }
//                System.out.println("funcion=" + funcion);
//                extremoMenor = indices[1] + 1 - Funcion.getInicios().get(indices[2]).length();
//                System.out.println("extremoMenor=" + extremoMenor + " a.charAt(extremoMenor)=" + a.charAt(extremoMenor));
//                exponente.setParametro1(funcion);
//            }
//
//            Operando operandoDerecho[] = interpreteStringUnOperandoYCantidadDeCaracteresOriginal(a, indice + 1);
//            exponente.setParametro2(operandoDerecho[0]);
//            System.out.println("operandoDerecho[0]=" + operandoDerecho[0] + " operandoDerecho[1]=" + operandoDerecho[1]);
//            extremoMayor = indice + (int) operandoDerecho[1].getNumero();
//            System.out.println("extremoMayor=" + extremoMayor + " a.charAt(extremoMayor)=" + a.charAt(extremoMayor));
//
////            System.out.println("indice=" + indice);
////            if (a.charAt(indice + 1) >= '0' && a.charAt(indice + 1) <= '9') {
////                double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(a, indice + 1);
////                exponente.setParametro2(numero[0]);
////                extremoMayor = indice + (int) numero[1];             // System.out.println("extremoMayor="+extremoMayor);
////            }
//            int extremo = (a.substring(0, extremoMenor) + "(" + exponente + ")").length();
//            //if(!(extremoMenor==0&&extremoMayor==a.length()-1))
//            if (!(extremoMenor == 0 && extremoMayor == a.length() - 1)) {
//                if ((extremoMenor != 0 && extremoMayor != a.length() - 1)) {
//                    a = a.charAt(extremoMenor - 1) != '(' && a.charAt(extremoMayor + 1) != ')' ? a.substring(0, extremoMenor) + "(" + exponente + ")" + (extremoMayor < a.length() - 1 ? a.substring(extremoMayor + 1) : "") : a;
//                } else {
//                    a = a.substring(0, extremoMenor) + "(" + exponente + ")" + (extremoMayor < a.length() - 1 ? a.substring(extremoMayor + 1) : "");
//                }
//            }
//
//            System.out.println("a=" + a);
//            //indice=extremoMayor+2; 
//            indice = a.indexOf("^", extremo);
//            System.out.println("indice=" + indice);
//
//        }
//        return a;
//    }
//
}
