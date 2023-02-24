/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.MetodosUtiles;

//import java.awt.Point;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Interfases.Arreglo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import static Utiles.MetodosUtiles.Arreglos.*;

import static Utiles.MetodosUtiles.Operaciones.suma;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Métodos static que facilitan acciones comunes para la mayoría de los
 * proyectos utilizando los objetos de java de mayor uso //
 * *********************** Version 3.1
 *
 * @author Rene
 */
public abstract class MetodosUtiles {
//ConfiguracionDeVideo cdv cantidadAntesIngnorar[  separacionString
    // Arrays.toString  cdv.despuesIngnorar

    public static final String StringNumeros[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    public static final String StringOperadoresBasicos[] = {"+", "-", "*", "/"};
    public static final String StringVocalesMinusculas[] = {"a", "e", "i", "o", "u"};
    public static final String StringVocalesMayusculas[] = {"A", "E", "I", "O", "U"};
    public static final String StringConsonantesMinusculas[] = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t", "v", "w", "x", "y", "z"};
    public static final String StringConsonantesMayusculas[] = {"B", "C", "D", "F", "G", "H", "J", "K", "L", "M", "N", "P", "Q", "R", "S", "T", "V", "W", "X", "Y", "Z"};
    public static final String StringMinusculas[] = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"};
    public static final String StringMayusculas[] = {"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    public static final String terminacionesNumericas[][] = {{}, {"ra", "er"}, {"da", "do", "nd"}, {"ra", "ro", "rd"}, {"ta", "to"}, {"ta", "to"}, {"ta", "tima", "to", "timo"}, {"ba", "va", "bo", "vo"}, {"na", "no"}};
    //numeros (a.charAt(i) >= 48 && a.charAt(i)<= 57) "+","-","*","/"
    //letras minusculas (a.charAt(i) >= 97 && a.charAt(i) <= 122)
    //letras mayusculas (a.charAt(i) >= 65 && a.charAt(i) <= 90)
//<b></b>
    //<ul></ul>
    //<li></li>

//   public static boolean cerca(int a,int b){
//   return
//   }
//    public static double recortarNumero(double numero) {
//       String a=String.format("f.3%", StringNumeros)
//        return 
//    }
    public static int inT(String a) {
        return (int) Double.parseDouble(a);
    }

    public static boolean containsAll(String a, boolean toLowerCase, String... B) {
//        System.out.println("a=" + a);
        boolean contiene = false;

        for (int i = 0; i < B.length; i++) {
//            System.out.println("i=" + i + " " + B[i]);
            StringTokenizer S = new StringTokenizer(a, Archivo.Delimiters);

            while (S.hasMoreTokens()) {
                String s = S.nextToken();
//                System.out.println("s=" + s);
                if (toLowerCase ? s.equalsIgnoreCase(B[i]) : s.equals(B[i])) {
                    contiene = true;
                    break;
                }
            }
            if (!contiene) {
                return false;
            }
            contiene = false;
        }
        return true;
    }

    /**
     * Compara por espacios con StringTokenizer
     *
     * @param a
     * @param toLowerCase
     * @param B
     * @return
     */
    public static boolean contains(String a, boolean toLowerCase, String... B) {
//        if(a.equals("En Transmision")){
//            System.out.println("bbbbbbbbbbb");
//        }
//        if (toLowerCase) {
//            a = a.toLowerCase();
//        }
////        System.out.println("a="+a);
        for (int i = 0; i < B.length; i++) {
//            if (toLowerCase) {
//                B[i] = B[i].toLowerCase();
//            }
//           System.out.println("i=" + i + " " + B[i]);
            StringTokenizer S = new StringTokenizer(a, Archivo.Delimiters);
            while (S.hasMoreTokens()) {
                String s = S.nextToken();
//                 System.out.println("s=" + s);
                if (toLowerCase ? s.equalsIgnoreCase(B[i]) : s.equals(B[i])) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {i de indexOf,i de B} o {-1,-1}
     *
     * @param a
     * @param B
     * @return
     */
    public static int[] indexOf(String a, String... B) {
        for (int i = 0; i < B.length; i++) {
            int j = a.indexOf(B[i]);
            if (j != -1) {
                return new int[]{j, i};
            }
        }
        return new int[]{-1, -1};

    }

    public static boolean startsWith(String a, char... A) {
        for (int i = 0; i < A.length; i++) {
            if (!a.isEmpty() && a.charAt(0) == A[i]) {
                return true;
            }

        }
        return false;
    }

    public static boolean startsWith(String a, String... A) {
        for (int i = 0; i < A.length; i++) {
            if (a.startsWith(A[i])) {
                return true;
            }

        }
        return false;
    }

    public static int[] encerradoDentroDeIndice(String a, String elemento, String envolturas[][]) {
        //  System.out.println("a=" + a + " elemento=" + elemento);
        int pos = a.indexOf(elemento);
        if (pos != -1 && pos > 2) {
            String sub = a;
            int posContinuacion = sub.length();
            int indicesEnvoltura[] = new int[]{-1, -1, -1, -1}, indicesEnvolturaMomentania[];
            while ((indicesEnvolturaMomentania = lastIndexOf(pos, sub, 0, envolturas))[0] != -1) {

                indicesEnvoltura = indicesEnvolturaMomentania;
                if (indicesEnvoltura[2] - indicesEnvoltura[0] < 4) {
                    break;
                }
                posContinuacion = posicionFinalDe(sub, envolturas[indicesEnvoltura[1]][0], envolturas[indicesEnvoltura[1]][1], indicesEnvoltura[0]);
                sub = sub.substring(indicesEnvoltura[0] + 1, (posContinuacion = posContinuacion != -1 ? posContinuacion : sub.length()));
                if (!sub.contains(envolturas[indicesEnvoltura[1]][0])) {
                    break;
                }
            }

            if (indicesEnvoltura[0] != -1) {
//                System.out.println("envolturas[indicesEnvoltura[1]][0]="+envolturas[indicesEnvoltura[1]][0]);
//                System.out.println("envolturas[indicesEnvoltura[1]][1]="+envolturas[indicesEnvoltura[1]][1]);
                int posFinalEnvoltura = posicionFinalDe(a, envolturas[indicesEnvoltura[1]][0], envolturas[indicesEnvoltura[1]][1], indicesEnvoltura[0]);
                if (posFinalEnvoltura != -1 && posFinalEnvoltura >= pos + elemento.length()) {
                    return new int[]{indicesEnvoltura[0], indicesEnvoltura[1], posContinuacion};
                }
            }
        }

        return new int[]{-1, -1, -1};
    }

    public static boolean encerradoDentroDe(String a, String elemento, String envolturas[][]) {
        return encerradoDentroDeIndice(a, elemento, envolturas)[0] != -1;

//        System.out.println("a="+a+" elemento="+elemento);
//        int pos = a.indexOf(elemento);
//        if (pos != -1 && pos > 2) {
//            String sub = a;
//            int indicesEnvoltura[] = new int[]{-1, -1, -1, -1}, indicesEnvolturaMomentania[];
//            while ((indicesEnvolturaMomentania = lastIndexOf(pos, sub, 0, envolturas))[0] != -1) {
//                indicesEnvoltura = indicesEnvolturaMomentania;
//                if (indicesEnvoltura[2] - indicesEnvoltura[0] < 4) {
//                    break;
//                }
//                sub = sub.substring(indicesEnvoltura[0] + 1, indicesEnvoltura[2] - 1);
//            }
//
//            if (indicesEnvoltura[0] != -1) {
////                System.out.println("envolturas[indicesEnvoltura[1]][0]="+envolturas[indicesEnvoltura[1]][0]);
////                System.out.println("envolturas[indicesEnvoltura[1]][1]="+envolturas[indicesEnvoltura[1]][1]);
//                int posFinalEnvoltura = posicionFinalDe(a, envolturas[indicesEnvoltura[1]][0], envolturas[indicesEnvoltura[1]][1], indicesEnvoltura[0]);
//                if (posFinalEnvoltura != -1 && posFinalEnvoltura >= pos + elemento.length()) {
//                    return true;
//                }
//            }
//        }
//        return false;
    }

    public static int[] lastIndexOf(int indice, String a, String... B) {
        if (indice > 0 && indice < a.length()) {
            a = a.substring(0, indice);
            for (int i = 0; i < B.length; i++) {
                int k = a.lastIndexOf(B[i]);
                if (k != -1) {
                    return new int[]{k, i};
                }
            }
        }

        return new int[]{-1, -1};
    }

    /**
     * recorre B[indiceEnB][] (la columna)<br>
     * {i de a,i de fila B,i de a continuacion} o {-1,-1,-1}
     *
     * @param indice
     * @param a
     * @param indiceEnB
     * @param B
     * @return
     */
    public static int[] lastIndexOf(int indice, String a, int indiceEnB, String[][] B) {
        if (indice > 0 && indice < a.length()) {
            a = a.substring(0, indice);
            for (int i = 0; i < B.length; i++) {
                int k = a.lastIndexOf(B[i][indiceEnB]);
                if (k != -1) {
                    return new int[]{k, i, B[i][indiceEnB].length() + k};
                }
            }
        }

        return new int[]{-1, -1, -1};
    }

    public static String arreglarPalabra(String a) {
//        System.out.println("a="+a);
        String b = a;
        char malos[] = {'ä', 'á', 'é', 'í', 'ó', 'ú'};
        char buenos[] = {'a', 'a', 'e', 'i', 'o', 'u'};
        for (int i = 0; i < malos.length; i++) {
            b = b.replace(malos[i], buenos[i]);
        }
//        System.out.println("b="+b);
        return b;
    }

    public static boolean cls() {

        try {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        } catch (Exception ex) {
            return false;
        }
        return true;
    }

    /**
     * si esta rodeado por una de las parejas<br/> <ul> <li>[0] j pareja</li>
     * <li>[1] i inicio del cirre</li><li>[2] i a continacion del cierre</li>
     * </ul>
     * <br/>si no {-1,-1,-1}
     *
     * @param a
     * @param indice
     * @param envolturas [0] las del principio <br/> [1] las del final
     *
     * @return
     */
    public static int[] rodeadoPor(String a, int indice, String envolturas[][]) {
        return rodeadoPor(a, indice, envolturas, false);
    }

    /**
     * si esta rodeado por una de las parejas<br/> <ul> <li>[0] j pareja</li>
     * <li>[1] i inicio del cirre</li><li>[2] i a continacion del cierre</li>
     * <li>[3] en caso de ser antes(false) el inidice despues del final del
     * inicio de la envoltura</li></ul>
     * <br/>si no {-1,-1,-1}
     *
     * @param a
     * @param indice
     * @param envolturas [0] las del principio <br/> [1] las del final
     * @param antes si es true el indice pertenece al inicio de la
     * envoltura<br/>si es false a la posicion despues del final del inicio de
     * la envoltura
     * @return
     */
    public static int[] rodeadoPor(String a, int indice, String envolturas[][], boolean antes) {
//        if (a.equals("Chaos Head [12-Full]") && a.charAt(indice) == '[') {
////            // MostrarMatrizObject(envolturas);
//            System.out.println("aaa");
//
//        }
        int j = -1, indiceInicial = -1, indiceAntesDelFinal = -1, indiceAContinuacion, indicePareja = -1;
//         System.out.println("indice="+indice);
        for (int i = 0; i < envolturas.length; i++) {
//            System.out.println("i="+i);
//            System.out.println(Arrays.toString(envolturas[i]));
            j = antes ? contieneStringAEnIndiceAArregloStringBInverso(indice, a, envolturas[i]) : contieneStringAEnIndiceAArregloStringB(indice, a, envolturas[i]);
//            System.out.println("j="+j);
            if (j != -1) {
                if (antes) {
                    indice += envolturas[0][j].length();
                    indiceInicial = indice;
                    //  indicePareja = j;
                }
                indicePareja = i;
                for (int k = indice; k < a.length(); k++) {
//                    System.out.println("indicePareja="+indicePareja);
                    // j = contieneStringAEnIndiceAArregloStringB(indice, a, envolturas[1][indicePareja]);
//                    System.out.println("k="+k+" a.charAt(k)="+a.charAt(k));
                    j = contieneStringAEnIndiceAArregloStringB(k, a, envolturas[indicePareja][1]);
                    if (j != -1) {
                        int aa = posicionFinalDe(a, envolturas[indicePareja][0], envolturas[indicePareja][1], indice);
//                        System.out.println("aa="+aa+" envolturas[indicePareja][0]="+envolturas[indicePareja][0]+" envolturas[indicePareja][1]="+envolturas[indicePareja][1]);
                        //return new int[]{indicePareja, k, envolturas[0][indicePareja].length() + k, indiceInicial};
//                        System.out.println("");
                        if (aa != -1) {
                            return new int[]{indicePareja, k, envolturas[indicePareja][0].length() + aa, indiceInicial};
                        }

                    }
                }

            }
        }

        return new int[]{-1, -1, -1, -1};

    }

    public static boolean charIgual(int i, String a, String... B) {
        for (String b : B) {
            if (a.charAt(i) != b.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean validarEmail(String email) {
        int posicionarroba, posicionpunto, longitud;
        longitud = email.length();
        posicionarroba = email.indexOf("@");
        posicionpunto = email.indexOf(".");
        if (posicionarroba == -1) {
            System.out.println("No hay arroba en el mail");
            return false;
        } else if (posicionarroba == 0 || posicionarroba == longitud - 1) {
            System.out.println("La arroba no puede estar ni al principio ni al final");
            return false;
        } else if (posicionarroba != email.lastIndexOf("@")) {
            System.out.println("Existe mas de una @ dentro del mail");
            return false;
        } else if (posicionpunto == -1) {
            System.out.println("No hay punto en el mail");
            return false;
        } else if (posicionpunto == 0 || posicionpunto == longitud - 1) {
            System.out.println("El punto no puede estar ni al principio ni al final");
            return false;
        } else if (email.indexOf("..") != -1) {
            System.out.println("No pueden existir dos puntos seguidos");
            return false;
        } else if (email.indexOf("@.") != -1 || email.indexOf(".@") != -1) {
            System.out.println("El punto no puede ir seguido de la @");
            return false;
        } else if (email.indexOf(" ") != -1) {
            System.out.println("Un email no puede contenter espacios");
            return false;
        } else {
            String dominio;
            int ultimopunto;
            ultimopunto = email.lastIndexOf(".");
            dominio = email.substring(ultimopunto + 1);
            if (dominio.length() >= 2 && dominio.length() <= 4) {
                System.out.println("EMAIL CORRECTO");
                return true;
            } else {
                System.out.println("El dominio solo puede ser de 2 a 4 caracteres");
                return false;
            }
        }
        // return true;
    }

    public static BigDecimal sumaBigDecimal(BigDecimal a, BigDecimal b) {
        BigDecimal suma = a.add(b);
        if (suma.toString().contains("E")) {
            // System.out.println("a="+a);
            double a2 = regularBigDecimal(a).doubleValue();
            // System.out.println("a2="+a2);
            // System.out.println("b="+b);
            double b2 = regularBigDecimal(b).doubleValue();
            // System.out.println("b2="+b2);
            double c = a2 + b2;
            // System.out.println("c="+c);
            return regularBigDecimal(c);
        }
        return regularBigDecimal(suma);
    }

    public static BigDecimal regularBigDecimal(double numero) {
        return regularBigDecimal(new BigDecimal(numero + ""));
    }

    public static BigDecimal regularBigDecimal(BigDecimal numero) {
        return regularBigDecimal(numero, 10);
    }

    public static BigDecimal regularBigDecimal(BigDecimal numero, final int lugaresDespuesDeLaComa) {
        return (numero.toString().length() > 20 && (numero.toString().substring(numero.toString().indexOf(".")).length() > lugaresDespuesDeLaComa)) ? eliminarCerosDerechaBigDecimal(new BigDecimal(numero.toString().substring(0, numero.toString().indexOf(".")) + numero.toString().substring(numero.toString().indexOf("."), lugaresDespuesDeLaComa))) : numero;
    }

    public static BigDecimal eliminarCerosDerechaBigDecimal(BigDecimal numero) {
//        int i = numero.toString().length();
//        while (i > 0 && numero.toString().charAt(i - 1) == '0') {
//            i--;
//        }
//        return new BigDecimal(numero.toString().substring(0, i));
        return new BigDecimal(eliminarCerosDerecha(numero.toString()));
    }

    public static String eliminarCerosDerecha(String numero) {
        int i = numero.length();
        while (i > 0 && numero.charAt(i - 1) == '0') {
            i--;
        }
        return numero.substring(0, i);
    }

    public static boolean esPIN(String numero) {
        return esPIN(4, numero);
    }

    public static boolean esPIN(int cantidadDeDigitos, String numero) {
        return !numero.isEmpty() && numero.length() == cantidadDeDigitos && esNumero(numero);
    }

    public static int contieneStringContrario(String original, String busqueda) {
//        int indice = -1, respuesta = -1;
//        while ((indice = original.indexOf(busqueda, ++indice)) != -1) {
//            respuesta = indice;
//        }
//        return respuesta;
        return original.lastIndexOf(busqueda);
    }

    public static boolean masDeUnPuntoOComa(String a) {
        int CantidadDePuntos = 0;
        for (int i = 0; CantidadDePuntos <= 1 && i < a.length(); i++) {//System.out.println("i="+i+" "+a.charAt(i));
            // System.out.println("esCharNumero(a.charAt(i))="+esCharNumero(a.charAt(i)));   
            //  System.out.println("puntoOComa(a.charAt(i))="+puntoOComa(a.charAt(i)));
            // System.out.println("1 CantidadDePuntos="+CantidadDePuntos);

            CantidadDePuntos = esCharNumero(a.charAt(i)) ? (puntoOComa(a.charAt(i)) ? ++CantidadDePuntos : CantidadDePuntos) : 0;
            //  System.out.println("2 CantidadDePuntos="+CantidadDePuntos);
        }
        return CantidadDePuntos <= 1;
    }

    public static boolean puntoOComa(String a) {
        return a.length() == 0 || a.length() > 1 ? false : puntoOComa(a.charAt(0));
    }

    public static boolean puntoOComa(char a) {
        return a == '.' || a == ',';
    }

    public static boolean esCharNumero(char a) {
        return esCharNumero(a, true);
    }

    public static boolean esCharNumero(char a, boolean aceptarPuntoOComa) {
        return esCharNumero(a, aceptarPuntoOComa, aceptarPuntoOComa);
    }

    public static boolean esCharNumero(char a, boolean aceptarPunto, boolean aceptarComa) {
        return entre('0', a, '9', true, true) || (aceptarPunto ? a == '.' : false) || (aceptarComa ? a == ',' : false);
    }

    public static boolean esCharLetra(char a) {
        return esCharLetraMinuscula(a) || esCharLetraMayuscula(a);
    }

    public static boolean esCharLetraMinuscula(char a) {
        return Arrays.asList(StringMinusculas).contains(a + "");
    }

    public static boolean esCharLetraMayuscula(char a) {
        return Arrays.asList(StringMayusculas).contains(a + "");
    }

    public static boolean esCharVocal(char a) {
        return esCharVocalMinuscula(a) || esCharVocalMayuscula(a);
    }

    public static boolean esCharVocalMinuscula(char a) {
        return Arrays.asList(StringVocalesMinusculas).contains(a + "");
    }

    public static boolean esCharVocalMayuscula(char a) {
        return Arrays.asList(StringVocalesMayusculas).contains(a + "");
    }

    public static boolean esCharConsonante(char a) {
        return esCharConsonanteMinuscula(a) || esCharConsonanteMayuscula(a);
    }

    public static boolean esCharConsonanteMinuscula(char a) {
        return Arrays.asList(StringConsonantesMinusculas).contains(a + "");
    }

    public static boolean esCharConsonanteMayuscula(char a) {
        return Arrays.asList(StringConsonantesMayusculas).contains(a + "");
    }

    public static boolean esPemitido(String a, ArrayList<String> permitidos) {
        return esPemitido(a, permitidos.toArray(new String[]{}));
    }

    public static boolean esPemitido(String a, String permitidos[]) {
        for (int i = 0; i < a.length();) {
            int j = contieneStringAEnIndiceAArregloStringB(i, a, permitidos);
            if (j == -1) {
                return false;
            } else {
                i += permitidos[j].length();
            }
        }
        return true;
    }

    public static boolean comprobarCierres(String a, ArrayList<String> comienzo, ArrayList<String> terminacion) {
        return comprobarCierres(a, comienzo.toArray(new String[]{}), null, terminacion.toArray(new String[]{}));
    }

    public static boolean comprobarCierres(String a, ArrayList<String> comienzo, ArrayList<String> medios, ArrayList<String> terminacion) {
        return comprobarCierres(a, comienzo.toArray(new String[]{}), medios.toArray(new String[]{}), terminacion.toArray(new String[]{}));
    }

    public static boolean comprobarCierres(String a, String comienzo[], String terminacion[]) {
        return comprobarCierres(a, comienzo, null, terminacion);
    }

    public static boolean comprobarCierres(String a, String comienzo[], String medios[], String terminacion[]) {
        int contadorDeComienzo = 0;
        int contadorDemedios = 0;
        for (int i = 0; i < a.length();) { //System.out.println("i="+i);
            int j = contieneStringAEnIndiceAArregloStringB(i, a, comienzo);
            if (j > -1) {
                contadorDeComienzo++;
                if (!medios[j].equals("")) {
                    contadorDemedios++;
                }
                i += comienzo[j].length();
                continue;
            }
            if (medios != null && medios.length != 0) {
                j = contieneStringAEnIndiceAArregloStringB(i, a, medios);
                if (j > -1 && a.charAt(i) != '^') {
                    contadorDemedios--;
                    i += medios[j].length();
                    continue;
                }

            }

            j = contieneStringAEnIndiceAArregloStringB(i, a, terminacion);
            if (j > -1) {
                contadorDeComienzo--;
                i += terminacion[j].length();
                continue;
            }
            i++;
        }
        return iguales(0, contadorDeComienzo, contadorDemedios);

    }

    public static boolean esPor(String a, int indice, ArrayList<String> permitidosAntes, ArrayList<String> permitidosAConinuacion) {
        return esPor(a, indice, permitidosAntes.toArray(new String[]{}), permitidosAConinuacion.toArray(new String[]{}));
    }

    public static boolean esPor(String a, int indice, String permitidosAntes[], String permitidosAConinuacion[]) {
        //   System.out.println("a="+a);
        if (indice == 0 || fuera(0, indice, a.length() - 1)) {
            return false;
        }
        String permitidos[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        permitidosAntes = UnirDosArreglosObject(permitidosAntes, permitidos, new String[]{")"});
        permitidosAConinuacion = UnirDosArreglosObject(permitidosAConinuacion, permitidos, new String[]{"(", "-"});

        if ((esNumero(a.charAt(indice) + "") && (esNumero(a.charAt(indice - 1) + "")))
                || (orCantidad(2, '-', a.charAt(indice), a.charAt(indice - 1)))) {
            return false;
        }

        if (!(contieneStringAEnIndiceAArregloStringBInverso(indice - 1, a, permitidosAntes) > -1)) {
            return false;
        }
        if (!(contieneStringAEnIndiceAArregloStringB(indice, a, permitidosAConinuacion) > -1)) {
            return false;
        }
        //System.out.println("siiiiiiiii");
        return true;
    }

    public static boolean fuera(double menores, double numeros[], double mayores, boolean iguales) {
        return fuera(menores, numeros, mayores, iguales, iguales);
    }

    public static boolean fuera(double menores, double numeros[], double mayores) {
        return fuera(menores, numeros, mayores, true, true);
    }

    public static boolean fuera(double menores, double numeros[], double mayores, boolean igualesMenores, boolean igualesMayores) {
        for (int i = 0; i < numeros.length; i++) {
            if (!fuera(menores, numeros[i], mayores, igualesMenores, igualesMayores)) {
                return false;
            }
        }
        return true;
    }

//****************
    public static boolean fuera(double menores, double numero, double mayores, boolean iguales) {
        return fuera(menores, numero, mayores, iguales, iguales);
    }

    public static boolean fuera(double menores, double numero, double mayores) {
        return fuera(menores, numero, mayores, true, true);
    }

    public static boolean fuera(double menores, double numero, double mayores, boolean igualesMenores, boolean igualesMayores) {
        return ((igualesMenores ? numero < menores : numero <= menores) || (igualesMayores ? numero > mayores : numero >= mayores));
    }

    public static boolean menorQue(double a, double... B) {
        return menorQue(false, a, B);
    }

    public static boolean menorQue(boolean AceptarIgual, double a, double... B) {
        for (double b : B) {
            if (AceptarIgual ? b > a : !(b < a)) {
                return false;
            }
        }
        return true;
    }

    public static String contenidoDeParentesis(String a, int indice) {
        return a.substring(indice + 1, posicionFinalDelParentesis(a, indice));
    }

    public static String contenidoDeCorchete(String a, int indice) {
        return a.substring(indice + 1, posicionFinalDelCorchete(a, indice));
    }

    public static boolean esNumero(String... A) {
        try {
            for (String a : A) {
                Double.parseDouble(a);
            }
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * En una línea String devuelve false si no se cierran o abren los
     * paréntesis
     *
     * @param a String
     * @return
     */
    public static boolean comprobarCierreDeParentesisCorchete(String a) {
        int p = 0;
        int c = 0;
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == '(') {
                p++;
                continue;
            }
            if (a.charAt(i) == '[') {
                c++;
                continue;
            }
            if (a.charAt(i) == ')') {
                if (p == 0) {
                    return false;
                }
                p--;
                continue;
            }
            if (a.charAt(i) == ']') {
                if (c == 0) {
                    return false;
                }
                c--;
            }

        }
        return p == 0 && c == 0;
    }

    public static <E> boolean orCantidad(int cantidadDeTrue, E a, E... B) {
        return orCantidad(cantidadDeTrue, false, a, B);
    }

    public static <E> boolean orCantidad(int cantidadDeTrue, boolean noMas, E a, E... B) {
        int c = 0;
        for (E b : B) {
            if (a.equals(b)) {
                c++;
            }
            if (!noMas && c == cantidadDeTrue) {
                return true;
            }
        }

        return c == cantidadDeTrue;
    }

    /**
     * Si a es igual a algún elemento del arreglo devuelve true
     *
     * @param <E> Cualquier tipo de objeto
     * @param a Cualquier tipo de objeto
     * @param B Un arrglo de objetos del mismo tipo que "a"
     * @return
     */
    public static <E> boolean or(E a, E... B) {
        for (E b : B) {
            if (a.equals(b)) {
                return true;
            }
        }
        return false;
    }

    public static boolean or(String a, boolean toLowerCase, String... B) {

        for (String b : B) {
            //           transmision
            if (b.equals("transmicion")) {
                System.out.println("a");
            }
            if (toLowerCase) {
                if (a.equalsIgnoreCase(b)) {
                    return true;
                }
            } else {
                if (a.equals(b)) {
                    return true;
                }
            }

        }
        return false;
    }

    public static int orIndice(String a, boolean toLowerCase, String... B) {

        for (int i = 0; i < B.length; i++) {
            String b = B[i];
            if (toLowerCase) {
                if (a.equalsIgnoreCase(b)) {
                    return i;
                }
            } else {
                if (a.equals(b)) {
                    return i;
                }
            }
        }

        return -1;
    }

    public static int orIndice(String a, boolean toLowerCase, int indiceB, String B[][]) {
//        if (toLowerCase) {
//            a.toLowerCase();
//        }
        for (int i = 0; i < B.length; i++) {
            String b = B[i][indiceB];
            if (toLowerCase) {
                if (a.equalsIgnoreCase(b)) {
                    return i;
                }
            } else {
                if (a.equals(b)) {
                    return i;
                }
            }
//            if (toLowerCase) {
//                b.toLowerCase();
//            }
//            if (a.equals(b)) {
//                return i;
//            }

        }

        return -1;
    }

    /**
     * Si a es igual a algún elemento del arreglo devuelve true
     *
     *
     * @param a
     * @param B
     * @return
     */
    public static boolean or(char a, char B[]) {
        for (char b : B) {
            if (a == b) {
                return true;
            }
        }
        return false;
    }

    public static boolean orFalse(boolean... A) {
        for (boolean a : A) {
            if (!a) {
                return false;
            }
        }
        return true;
    }

    public static <E> boolean or(E A[], E... B) {

        for (E b : B) {
            for (E a : A) {
                if (a.equals(b)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static String eliminarUnionesDeStringLine(String a) {
        return eliminarUnionesDeStringLine(a, ConfiguracionDeVideo.getDefault());
    }

    public static String eliminarUnionesDeStringLine(String a, ConfiguracionDeVideo cdv) {
        String respuesta = "";
        for (int i = 0; i < a.length(); i++) {
            respuesta += or(a.charAt(i), cdv.separacion) ? "" : a.charAt(i);
        }
        return respuesta;
    }

    /**
     * <b>En un String tipo oración elimina los espacios en blanco</b>
     * <b>Ejemplo:</b>
     * <b>En una línea</b>
     * <b>Devuelve: Enunalinea     </b>
     *
     * @param a String
     * @return
     */
    public static String eliminarEspaciosDeStringLine(String a) {
        String respuesta = "";
        for (int i = 0; i < a.length(); i++) {
            respuesta += a.charAt(i) == ' ' ? "" : a.charAt(i);
        }
        return respuesta;
    }

    /**
     * <b>Si el String contiene en su última o primera posición a algún de estos
     * char devuelve true </b>
     *
     * @param a String
     * @param inicial char[]
     * @param fin char[]
     * @return
     */
    public static boolean charEnPosicionInicialoFinal(String a, char[] inicial, char... fin) {
        return charEnPosicionInicialoFinal(new String[]{a}, inicial, fin);
    }

    /**
     * <b>Si el String contiene en su última o primera posición a algún de estos
     * char devuelve true </b>
     *
     * @param a String
     * @param B char[]
     * @return
     */
    public static boolean charEnPosicionInicialoFinal(String a, char... B) {
        return charEnPosicionInicialoFinal(new String[]{a}, B);
    }

    /**
     * <b>Si algunos de los String contiene en su última o primera posición a
     * algún de estos char devuelve true </b>
     *
     * @param A String[]
     * @param B char[]
     * @return
     */
    public static boolean charEnPosicionInicialoFinal(String A[], char... B) {
        return charEnPosicionInicialoFinal(A, B, B);
    }

    /**
     * <b>Si algunos de los String contiene en su última o primera posición a
     * algún de estos char devuelve true </b>
     *
     * @param A String[]
     * @param inicial char[] Los estarán al comienzo
     * @param fin char[] Los estarán al final
     * @return
     */
    public static boolean charEnPosicionInicialoFinal(String A[], char[] inicial, char... fin) {
        return charEnPosicionInicial(A, inicial) || charEnPosicionFinal(A, fin);
    }

    /**
     * <b>Si el String contiene en su primera posición a algún de estos char
     * devuelve true </b>
     *
     * @param a String
     * @param B char[]
     * @return
     */
    public static boolean charEnPosicionInicial(String a, char... B) {
        return charEnPosicionInicial(new String[]{a}, B);
    }

    /**
     * <b>Si algunos de los String contiene en su primera posición a algún de
     * estos char devuelve true </b>
     *
     * @param A String[]
     * @param B char[]
     * @return
     */
    public static boolean charEnPosicionInicial(String A[], char... B) {
        for (String a : A) {
            for (int i = 0; a.length() > 0 && i < B.length; i++) {
                if (a.charAt(0) == B[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * <b>Si el String contiene en su última posición a algún de estos char
     * devuelve true </b>
     *
     * @param a String
     * @param B char[]
     * @return
     */
    public static boolean charEnPosicionFinal(String a, char... B) {
        return charEnPosicionFinal(new String[]{a}, B);
    }

    //<b></b>
    /**
     * <b>Si algunos de los String contiene en su última posición a algún de
     * estos char devuelve true </b>
     *
     * @param A String[]
     * @param B char[]
     * @return
     */
    public static boolean charEnPosicionFinal(String A[], char... B) {
        for (String a : A) {
            for (int i = 0; a.length() > 0 && i < B.length; i++) {
                if (a.charAt(a.length() - 1) == B[i]) {
                    return true;
                }
            }
        }
        return false;
    }

    public static boolean containsString(String a, String... B) {
        return containsStringIndice(new String[]{a}, false, B)[0] != -1;
    }

    /**
     * {0,i B,i0,i a coninuacion} o {-1,-1,-1,-1}
     *
     * @param a
     * @param B
     * @return
     */
    public static int[] containsStringIndice(String a, String... B) {
        return containsStringIndice(new String[]{a}, false, B);
    }

    public static boolean containsString(String a, boolean toLowerCase, String... B) {
        return containsStringIndice(new String[]{a}, toLowerCase, B)[0] != -1;
    }

    /**
     * {0,i B,i0,i a coninuacion} o {-1,-1,-1,-1}
     *
     * @param a
     * @param toLowerCase
     * @param B
     * @return
     */
    public static int[] containsStringIndice(String a, boolean toLowerCase, String... B) {
        return containsStringIndice(new String[]{a}, toLowerCase, B);
    }

    /**
     * {i A,i B,i0,i a coninuacion} o {-1,-1,-1,-1}
     *
     * @param A
     * @param toLowerCase
     * @param B
     * @return
     */
    public static int[] containsStringIndice(String A[], boolean toLowerCase, String... B) {
//      if(A[0].toString().equals("Mayoi Neko Overrun [Temp 1] [12 Cap.] [1,63 Gb]")){
//          if(B[0].equals("capitulo")){
//          System.out.println("aaaaa");
//          } 
//          
//        }
        // System.out.println("A="+A[0]);
        //  System.out.println(Arrays.toString(B));
        int res[] = {-1, -1, -1, -1};
        boolean soloUnaPasada = true;
        for (int i = 0; i < A.length; i++) {
            String a = A[i];

            if (toLowerCase) {
                a = a.toLowerCase();
            }
            //   System.out.println("i="+i+" a="+a);
            for (int j = 0; j < B.length; j++) {
                String b = B[j];

                if (soloUnaPasada && toLowerCase) {
                    b = b.toLowerCase();
                }
                //   System.out.println("j="+j+" b="+b);

                if (a.contains(b)) {
                    return new int[]{i, j, a.indexOf(b), a.indexOf(b) + b.length()};
                }
            }
            soloUnaPasada = false;
        }
//        for (String a : A) {
//            if (toLowerCase) {
//                a = a.toLowerCase();
//            }
//
//            for (String b : B) {
//                if (soloUnaPasada && toLowerCase) {
//                    b = b.toLowerCase();
//                }
//////                System.out.println("a="+a);
//////                System.out.println("b="+b);
//////                System.out.println("a.contains(b)="+a.contains(b));
//                if (a.contains(b)) {
//                    return true;
//                }
//            }
//            soloUnaPasada = false;
//        }
        return res;
        //return false;
    }

    public static boolean containsStringSeparado(String a, boolean toLowerCase, String... B) {
        return containsStringSeparadoIndice(a, toLowerCase, B)[0] != -1;
//        int j[] = containsStringIndice(a, toLowerCase, B);
//        if (j[0] != -1) {
//            //   System.out.println("a.charAt(j[2]-1)="+a.charAt(j[2]-1));
//            //  System.out.println("a.charAt(j[3])="+a.charAt(j[3]));
//            if (!(j[2] != 0 && esCharLetra(a.charAt(j[2] - 1))) || (j[3] != a.length() && esCharLetra(a.charAt(j[3])))) {
//                return true;
//            }
//        }
//        return false;
    }

    /**
     * {0,i B,i0,i a coninuacion} o {-1,-1,-1,-1}
     *
     * @param a
     * @param toLowerCase
     * @param B
     * @return
     */
    public static int[] containsStringSeparadoIndice(String a, boolean toLowerCase, String... B) {

        int j[] = containsStringIndice(a, toLowerCase, B);

        if (j[0] != -1) {
//              System.out.println(B[j[1]]);
            //   System.out.println("a.charAt(j[2]-1)="+a.charAt(j[2]-1));
            //  System.out.println("a.charAt(j[3])="+a.charAt(j[3]));
            if (!(j[2] != 0 && (esCharLetra(a.charAt(j[2] - 1)) || esCharNumero(a.charAt(j[2] - 1)))) && !(j[3] != a.length() && (esCharLetra(a.charAt(j[3])) || esCharNumero(a.charAt(j[3]))))) {
                return j;
            }
        }
        return new int[]{-1, -1, -1, -1};
    }

    public static void mostrarList(List a) {
        String m = "[ ";
        for (int i = 0; i < a.size(); i++) {
            m += a.get(i).toString() + " " + (i == a.size() - 1 ? "]" : "; ");
        }
        System.out.println(m);
    }

    public static void mostrarSet(Set a) {
        String m = "[ ";
        Iterator i = a.iterator();
        while (i.hasNext()) {
            m += i.next().toString() + " " + (!i.hasNext() ? "]" : "; ");
        }
        System.out.println(m);
    }

    public static <E> String[] ArregloAStringArreglo(E... A) {
        String respuesta[] = new String[A.length];
        for (int i = 0; i < A.length; i++) {
            respuesta[i] = A[i].toString();
        }
        return respuesta;
    }

    public static int[] modificarTiempo(int indice, int aumentos[], int horas[], int... limites) {
        if (aumentos[indice] != 0) {
           // System.out.println("aumentos=" + Arrays.toString(aumentos));
            // System.out.println("horas=" + Arrays.toString(horas));

            //  System.out.println("indice=" + indice);
            //  System.out.println("horas[indice]=" + horas[indice] + " aumentos[indice]=" + aumentos[indice]);
            horas[indice] += aumentos[indice];
            //  System.out.println("horas[indice]=" + horas[indice]);
            if (horas[indice] > 0) {
                if (indice > 0) {
                    boolean cambio = false;
                    for (int i = horas.length - 1; i >= 0; i--) {
                        if (!cambio) {
                            if (i == indice) {
                                break;
                            }
                            if (horas[i] < 0) {
                                cambio = true;
                                horas[i]++;
                                continue;
                            }
                        } else {
                            if (i == indice) {
                                horas[i] -= limites[i];
                                continue;
                            }
                            if (horas[i] < 0) {
                                horas[i] += limites[i];
                            }
                        }
                    }

                    boolean aumento = false;
                    for (int i = indice - 1; !cambio && i >= 0; i--) {
                        // System.out.println("horas[i]=" + horas[i]);
                        if (horas[i] < 0) {
                            if (!aumento) {
                                //  System.out.println("horas[indice]=" + horas[indice]);
                                horas[indice]--;
                                //  System.out.println("horas[indice]=" + horas[indice]);
                                aumento = true;
                            }
                            //System.out.println("horas[i]=" + horas[i] + " limites[i]=" + limites[i]);
                            horas[i] += limites[i];
                            //  System.out.println("horas[i]=" + horas[i]);
                        }
                    }
                }
            }
            if (horas[indice] < 0) {
                //--------------
                if (indice > 0) {
                    //System.out.println("2 horas=" + Arrays.toString(horas));
                    //************************
                    //nnnnnnnnnnn
                    boolean cambio = false;
                    horas[indice] += limites[indice];
                    for (int i = indice + 1; i < horas.length; i++) {
                        horas[i]--;
                        if (horas[i] < 0) {
                            horas[i] += limites[i];
                        } else {
                            cambio = true;
                            break;
                        }
                    }
                    //nnnnnnnnnnnnn
                    //vvvvvvvvvvvvvvvvvv
//                    boolean cambio = false;
//                    for (int i = horas.length - 1; i >= 0; i--) {
//                        System.out.println("i=" + i);
//                        System.out.println("1 horas[i]=" + horas[i]);
//                        if (!cambio) {
//                            if (i == indice) {
//                                break;
//                            }
//
//                            if (horas[i] > 0) {
//                                cambio = true;
//                                horas[i]--;
//                                System.out.println("2 horas[i]=" + horas[i]);
//                                //  continue;
//                            }
//                        } else {
//                            System.out.println("limites[i]=" + limites[i]);
//                            if (i == indice) {
//                                horas[i] += limites[i];
//                                System.out.println("2 horas[i]=" + horas[i]);
//                                break;
//                            }
//                            //vvvvvvvvvvvvvv
////                            if (horas[i] > 0) {
////                                horas[i] -= limites[i];
////                                System.out.println("2 horas[i]=" + horas[i]);
////                            }
//                            //vvvvvvvvvvvvvv
//                            //nnnnnnnnnnnn
//                            horas[i]--;
//                            if (horas[i] < 0) {
//                                horas[i] += limites[i];
//                                System.out.println("2 horas[i]=" + horas[i]);
//                            }
//                            //nnnnnnnnnnn
//                        }
//                    }
                    //vvvvvvvvvvvvvv
                    // System.out.println("3 horas=" + Arrays.toString(horas));
                    //***************************
                    //vvvvvvvvvvvvvvvvvv
                    boolean aumento = false;
                    for (int i = indice - 1; !cambio && i >= 0; i--) {
                        // System.out.println("horas[i]=" + horas[i]);
                        if (horas[i] > 0) {
                            if (!aumento) {
                                //  System.out.println("horas[indice]=" + horas[indice]);
                                horas[indice]++;
                                //   System.out.println("horas[indice]=" + horas[indice]);
                                aumento = true;
                            }
                            //  System.out.println("horas[i]=" + horas[i] + " limites[i]=" + limites[i]);
                            horas[i] -= limites[i];
                            //     System.out.println("horas[i]=" + horas[i]);
                        }
                    }
                    //  System.out.println("4 horas=" + Arrays.toString(horas));
                    //vvvvvvvvvvvvvvvvvvvv

                }
                //nnnnnnnnnnn
                if (indice == 0) {
                    boolean cambio = false;
                    horas[indice] += limites[indice];
                    for (int i = indice + 1; i < horas.length; i++) {
                        horas[i]--;
                        if (horas[i] < 0) {
                            horas[i] += limites[i];
                        } else {
                            cambio = true;
                            break;
                        }
                    }

//                    for (int i = horas.length - 1; i >= 0; i--) {
//                        if (!cambio) {
//                            if (i == indice) {
//                                break;
//                            }
//                            if (horas[i] > 0) {
//                                cambio = true;
//                                horas[i]--;
//                                //  continue;
//                            }
//                        } else {
//                            if (i == indice) {
//                                horas[i] += limites[i];
//                                break;
//                            }
//                            //if (horas[i] > 0) {
//                            //horas[i] -= limites[i];
//                            //  }
//                            horas[i]--;
//                            if (horas[i] < 0) {
//                                horas[i] += limites[i];
//                                System.out.println("2 horas[i]=" + horas[i]);
//                            }
//                        }
//                    }
                    //  System.out.println("5 horas=" + Arrays.toString(horas));
                    boolean aumento = false;
                    for (int i = indice + 1; !cambio && i < horas.length; i++) {
                        //  System.out.println("horas[i]=" + horas[i]);
                        if (horas[i] > 0) {
                            if (!aumento) {
                                //    System.out.println("horas[indice]=" + horas[indice]);
                                horas[indice]--;
                                //     System.out.println("horas[indice]=" + horas[indice]);
                                aumento = true;
                            }
                            //   System.out.println("horas[i]=" + horas[i] + " limites[i]=" + limites[i]);
                            horas[i] += limites[i];
                            //    System.out.println("horas[i]=" + horas[i]);
                        }
                    }
                    //    System.out.println("6 horas=" + Arrays.toString(horas));
                }
                    //nnnnnnnnnn

                //------------------
            }
            //   System.out.println("indice=" + indice);
            // System.out.println("horas[indice]=" + horas[indice]);
            if (indice < horas.length - 1 && horas[indice] != 0 && !(Math.abs(horas[indice]) < limites[indice])) {
                int exeso = (horas[indice] / limites[indice]);
                //   System.out.println("exeso=" + exeso);
                //  System.out.println("horas[indice]=" + horas[indice] + " aumentos[indice]=" + aumentos[indice]);
                horas[indice] = horas[indice] % limites[indice];
                //  System.out.println("horas[indice]=" + horas[indice]);
                //  System.out.println("aumentos[indice]=" + aumentos[indice]);
                aumentos[indice + 1] += exeso;
                //  System.out.println("aumentos[indice]=" + aumentos[indice]);
            }

        }
        if (++indice < horas.length) {
            modificarTiempo(indice, aumentos, horas, limites);
        }
        return horas;
    }

    public static int[] modificarTiempoConNegativos(int indice, int aumentos[], int horas[], int... limites) {
        if (aumentos[indice] != 0) {
//            System.out.println("aumentos=" + Arrays.toString(aumentos));
//            System.out.println("horas=" + Arrays.toString(horas));
//
//            System.out.println("indice=" + indice);
//            System.out.println("horas[indice]=" + horas[indice] + " aumentos[indice]=" + aumentos[indice]);
            horas[indice] += aumentos[indice];
//            System.out.println("horas[indice]=" + horas[indice]);
            if (horas[indice] > 0) {
                if (indice > 0) {
                    boolean cambio = false;
                    for (int i = horas.length - 1; i >= 0; i--) {
                        if (!cambio) {
                            if (i == indice) {
                                break;
                            }
                            if (horas[i] < 0) {
                                cambio = true;
                                horas[i]++;
                                continue;
                            }
                        } else {
                            if (i == indice) {
                                horas[i] -= limites[i];
                                continue;
                            }
                            if (horas[i] < 0) {
                                horas[i] += limites[i];
                            }
                        }
                    }

                    boolean aumento = false;
                    for (int i = indice - 1; !cambio && i >= 0; i--) {
//                        System.out.println("horas[i]=" + horas[i]);
                        if (horas[i] < 0) {
                            if (!aumento) {
//                                System.out.println("horas[indice]=" + horas[indice]);
                                horas[indice]--;
//                                System.out.println("horas[indice]=" + horas[indice]);
                                aumento = true;
                            }
//                            System.out.println("horas[i]=" + horas[i] + " limites[i]=" + limites[i]);
                            horas[i] += limites[i];
//                            System.out.println("horas[i]=" + horas[i]);
                        }
                    }
                }
            }
            if (horas[indice] < 0) {
                //--------------
                if (indice > 0) {
//                    System.out.println("2 horas=" + Arrays.toString(horas));
                    //************************
                    //nnnnnnnnnnn
                    boolean cambio = false;
                    for (int i = indice + 1; i < horas.length; i++) {
                        if (horas[i] > 0) {
                            break;
                        }
                        if (i == horas.length - 1) {
                            return horas;
                        }
                    }
                    horas[indice] += limites[indice];
                    for (int i = indice + 1; i < horas.length; i++) {
                        horas[i]--;
                        if (horas[i] < 0) {
                            horas[i] += limites[i];
                        } else {
                            cambio = true;
                            break;
                        }
                    }

                    //vvvvvvvvvvvvvvvvvv
                    boolean aumento = false;
                    for (int i = indice - 1; !cambio && i >= 0; i--) {
//                        System.out.println("horas[i]=" + horas[i]);
                        if (horas[i] > 0) {
                            if (!aumento) {
//                                System.out.println("horas[indice]=" + horas[indice]);
                                horas[indice]++;
//                                System.out.println("horas[indice]=" + horas[indice]);
                                aumento = true;
                            }
//                            System.out.println("horas[i]=" + horas[i] + " limites[i]=" + limites[i]);
                            horas[i] -= limites[i];
//                            System.out.println("horas[i]=" + horas[i]);
                        }
                    }
//                    System.out.println("4 horas=" + Arrays.toString(horas));
                    //vvvvvvvvvvvvvvvvvvvv

                }
                //nnnnnnnnnnn
                if (indice == 0) {
                    boolean cambio = false;
                    for (int i = indice + 1; i < horas.length; i++) {
                        if (horas[i] > 0) {
                            break;
                        }
                        if (i == horas.length - 1) {
                            return horas;
                        }
                    }
                    horas[indice] += limites[indice];
                    for (int i = indice + 1; i < horas.length; i++) {
                        horas[i]--;
                        if (horas[i] < 0) {
                            horas[i] += limites[i];
                        } else {
                            cambio = true;
                            break;
                        }
                    }

//                    System.out.println("5 horas=" + Arrays.toString(horas));
                    boolean aumento = false;
                    for (int i = indice + 1; !cambio && i < horas.length; i++) {
//                        System.out.println("horas[i]=" + horas[i]);
                        if (horas[i] > 0) {
                            if (!aumento) {
////                                System.out.println("horas[indice]=" + horas[indice]);
                                horas[indice]--;
//                                System.out.println("horas[indice]=" + horas[indice]);
                                aumento = true;
                            }
//                            System.out.println("horas[i]=" + horas[i] + " limites[i]=" + limites[i]);
                            horas[i] += limites[i];
//                            System.out.println("horas[i]=" + horas[i]);
                        }
                    }
//                    System.out.println("6 horas=" + Arrays.toString(horas));
                }
                    //nnnnnnnnnn

                //------------------
            }
//            System.out.println("indice=" + indice);
//            System.out.println("horas[indice]=" + horas[indice]);
            if (indice < horas.length - 1 && horas[indice] != 0 && !(Math.abs(horas[indice]) < limites[indice])) {
                int exeso = (horas[indice] / limites[indice]);
//                System.out.println("exeso=" + exeso);
//                System.out.println("horas[indice]=" + horas[indice] + " aumentos[indice]=" + aumentos[indice]);
                horas[indice] = horas[indice] % limites[indice];
//                System.out.println("horas[indice]=" + horas[indice]);
//                System.out.println("aumentos[indice]=" + aumentos[indice]);
                aumentos[indice + 1] += exeso;
//                System.out.println("aumentos[indice]=" + aumentos[indice]);
            }

        }
        if (++indice < horas.length) {
            modificarTiempoConNegativos(indice, aumentos, horas, limites);
        }
        return horas;
    }

    public static ArrayList<String> ArrayListCharacterAArrayListString(ArrayList<Character> C) {
        ArrayList<String> S = new ArrayList<String>();
        for (Character c : C) {
            S.add(c + "");
        }
        return S;
    }

    public static boolean entre(double menores, double numero, double mayores, boolean iguales) {
        return entre(menores, numero, mayores, iguales, iguales);
    }

    public static boolean entre(double menores, double numero, double mayores) {
        return entre(menores, numero, mayores, false, false);
    }

    public static boolean entre(double menores, double numero, double mayores, boolean igualesMenores, boolean igualesMayores) {
        return !((igualesMenores ? numero < menores : numero <= menores) || (igualesMayores ? numero > mayores : numero >= mayores));
    }

    public static boolean contienenLasClases(int cantidad, String nombresDeClases[], Object... objetos) {
        int cantidadPorCadaUna[] = new int[nombresDeClases.length];
        Arrays.fill(cantidadPorCadaUna, cantidad);
        return contienenLasClases(cantidadPorCadaUna, false, nombresDeClases, objetos);
    }

    public static boolean contienenLasClases(int cantidad, boolean mismaCantidadDeClases, String nombresDeClases[], Object... objetos) {
        int cantidadPorCadaUna[] = new int[nombresDeClases.length];
        Arrays.fill(cantidadPorCadaUna, cantidad);
        return contienenLasClases(cantidadPorCadaUna, mismaCantidadDeClases, nombresDeClases, objetos);
    }

    public static boolean contienenLasClases(int cantidad, ArrayList objetos, String... nombresDeClases) {
        return contienenLasClases(cantidad, nombresDeClases, objetos);
    }

    public static boolean contienenLasClases(int cantidadPorCadaUna[], ArrayList<Object> objetos, String... nombresDeClases) {
        return contienenLasClases(cantidadPorCadaUna, nombresDeClases, objetos.toArray());
    }

    public static boolean contienenLasClases(int cantidad, String nombresDeClases[], ArrayList<Object> objetos) {
        int a[] = new int[nombresDeClases.length];
        Arrays.fill(a, cantidad);
        return contienenLasClases(a, nombresDeClases, objetos);
    }

    public static boolean contienenLasClases(int cantidadPorCadaUna[], String nombresDeClases[], ArrayList<Object> objetos) {
        return contienenLasClases(cantidadPorCadaUna, nombresDeClases, objetos.toArray());
    }

    public static boolean contienenLasClases(int cantidadPorCadaUna[], String nombresDeClases[], Object... objetos) {
        Object O[][] = {objetos};
        return contienenLasClases(cantidadPorCadaUna, false, nombresDeClases, O);
    }

    public static boolean contienenLasClases(int cantidadPorCadaUna[], boolean mismaCantidadDeClases, String nombresDeClases[], Object... objetos) {
        Object O[][] = {objetos};
        return contienenLasClases(cantidadPorCadaUna, mismaCantidadDeClases, nombresDeClases, O);
    }

    public static boolean contienenLasClases(int cantidadPorCadaUna[], boolean mismaCantidadDeClases, String nombresDeClases[], Object[]... objetos) {
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
            // System.out.println("i=" + i);

            for (int j = 0; j < nombresDeClases.length; j++) {
                // System.out.println("j=" + j);
                for (int k = 0; k < objetos[i].length; k++) {
                   // System.out.println("k=" + k);

                    //   System.out.println("nombresDeClases[j]=" + nombresDeClases[j] + "  objetos[i][k]=" + objetos[i][k]);
                    if (mismaClase(nombresDeClases[j], objetos[i][k])) {
                        ++cantidad[i][j];
                        // mostrarMatriz(cantidad);
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
        // mostrarMatriz(cantidad);
        // System.out.println(Arrays.toString(cantidadPorCadaUna));
        // System.out.println("alMenosIgualesAconAA(cantidadPorCadaUna, cantidad)="+alMenosIgualesAconAA(cantidadPorCadaUna, cantidad));

        return mismaCantidadDeClases ? igualesAconAA(cantidadPorCadaUna, cantidad) : alMenosIgualesAconAA(cantidadPorCadaUna, cantidad);
    }

    public static <E> E[][] ordenarObjetosEnClases(String nombresDeClases[], E... objetos) {
        // System.out.println("aa=" + objetos.getClass().getSimpleName());
        return ordenarObjetosEnClases(new ArrayList<String>(Arrays.asList(nombresDeClases)), objetos);
    }

    public static <E> E[][] ordenarObjetosEnClases(ArrayList<String> nombresDeClases, E... objetos) {
        //  System.out.println("bb=" + objetos.getClass().getSimpleName());
        ArrayList<ArrayList<E>> B = new ArrayList<ArrayList<E>>();
        B.add(new ArrayList<E>(Arrays.asList(objetos)));
        //System.out.println("cc=" + B.getClass().getSimpleName());
        return ordenarObjetosEnClasesAA(nombresDeClases, B)[0];
    }

    public static <E> E[][][] ordenarObjetosEnClases(String nombresDeClases[], E[]... objetos) {
        return ordenarObjetosEnClases(new ArrayList<String>(Arrays.asList(nombresDeClases)), objetos);
    }

    public static <E> E[][][] ordenarObjetosEnClases(ArrayList<String> nombresDeClases, E[]... objetos) {
        return ordenarObjetosEnClasesAA(nombresDeClases, convertirArreglo2AArrayList(objetos));
    }

//      public static <E> E[][] ordenarObjetosEnClases(ArrayList<String> nombresDeClases, ArrayList<ArrayList<E>> objetos) {
//      return ordenarObjetosEnClases(nombresDeClases, objetos,objetos.get(0).get(0));
//      }
//    
    public static <E> E[][] ordenarObjetosEnClases(ArrayList<E> objetos, String... nombresDeClases) {
        return ordenarObjetosEnClases(nombresDeClases, objetos);
    }

    public static <E> E[][] ordenarObjetosEnClases(String nombresDeClases[], ArrayList<E> objetos) {
        return ordenarObjetosEnClases(new ArrayList<String>(Arrays.asList(nombresDeClases)), objetos);
    }

    public static <E> E[][] ordenarObjetosEnClases(ArrayList<String> nombresDeClases, ArrayList<E> objetos) {
        ArrayList<ArrayList<E>> o = new ArrayList<ArrayList<E>>();
        o.add(objetos);
        return ordenarObjetosEnClasesAA(nombresDeClases, o)[0];
    }

    public static <E> E[][][] ordenarObjetosEnClasesAA(ArrayList<String> nombresDeClases, ArrayList<ArrayList<E>> objetos) {
      //  E r=objetos.get(0).get(0);
        //   System.out.println("r="+r.getClass().getSimpleName());
        //   E e[][] = (E[][]) new Object[objetos.size()][nombresDeClases.size()];

        //  if (objetos.get(0).get(0) instanceof Arreglo) {
        E e[][][] = (E[][][]) (objetos.get(0).get(0) instanceof Arreglo ? ((Arreglo) objetos.get(0).get(0)).crearArregloSuperclase(objetos.size(), nombresDeClases.size(), 0) : arregloAAA(objetos.get(0).get(0), objetos.size(), nombresDeClases.size(), 0));

//  }
        //
        //mostrarArrayList(nombresDeClases);
        // System.out.println("a="+e.getClass().getSimpleName());
        // System.out.println("t="+t.getClass().getSimpleName());
        for (int j = 0; j < objetos.size(); j++) {
            for (int k = 0; k < nombresDeClases.size(); k++) {
                int indiceCoincidencias = 0;
                for (int i = 0; i < objetos.get(j).size(); i++) {
                    if (mismaClase(nombresDeClases.get(k), objetos.get(j).get(i))) {
                        e[j][k] = colocarDeUltimoObject(e[j][k], objetos.get(j).remove(i--));
                        // e[j][k][indiceCoincidencias++] = objetos.get(j).remove(i);

                    }
                }
            }
//            for (String a : nombresDeClases) {
//
//            }

        }
//        System.out.println("a="+e.getClass().getSimpleName());
        //  System.out.println("b="+e[0][0].getClass().getSimpleName());
        return e;
    }

    public static boolean igualesC(Comparable... A) {
        if (A.length < 2) {
            return false;
        }
        for (int i = 1; i < A.length; i++) {
            if (A[i - 1].compareTo(A[i]) != 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean iguales(short... A) {
        return igualesObject(convertirArregloAObjetoShort(A));
    }

    public static boolean iguales(int... A) {
        return igualesObject(convertirArregloAObjetoInteger(A));
    }

    public static boolean iguales(long... A) {
        return igualesObject(convertirArregloAObjetoLong(A));
    }

    public static boolean iguales(double... A) {
        return igualesObject(convertirArregloAObjetoDouble(A));
    }

    public static boolean iguales(float... A) {
        return igualesObject(convertirArregloAObjetoFloat(A));
    }

    public static boolean iguales(char... A) {
        return igualesObject(convertirArregloAObjetoCharacter(A));
    }

    public static boolean iguales(ArrayList A) {
        return igualesObject(A.toArray());
    }

    public static <E> boolean igualesObject(E... A) {
        if (A.length < 2) {
            return false;
        }
        for (int i = 1; i < A.length; i++) {
            if (!A[i - 1].equals(A[i])) {
                return false;
            }
        }
        return true;
    }

    public static boolean mismaClase(String a, Object... b) {
        if (b.length == 0 || !a.equals(b[0].getClass().getSimpleName())) {
            return false;
        }
        return b.length == 1 ? true : mismaClase(b);
    }

    public static boolean mismaClase(Object... a) {
        //mostrarArreglo(a);
        if (a.length == 1 || a.length == 0) {
            return false;
        }
        for (int i = 1; i < a.length; i++) {
            // System.out.println("i=" + i);
            // System.out.println("a[i - 1].getClass().getSimpleName()=" + a[i - 1].getClass().getSimpleName() + " a[i].getClass().getSimpleName()=" + a[i].getClass().getSimpleName());
            if (!a[i - 1].getClass().getSimpleName().equals(a[i].getClass().getSimpleName())) {
                return false;
            }
        }
        return true;
    }

    public static String eliminarMasMenos(String a) {
        if (a.contains("+-")) {
            a = a.substring(0, a.indexOf("+-")) + a.substring(a.indexOf("+-") + 1);
            a = eliminarMasMenos(a);
        }
        return a;
    }

    public static boolean esNatural(String a) {
        try {
            return esNatural(Double.parseDouble(a));
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean esNatural(double a) {
        return esEntero(a) && a >= 0;
    }

    public static boolean esEntero(String a) {
        try {
            return esEntero(Double.parseDouble(a));
        } catch (Exception e) {
            return false;
        }

    }

    public static boolean esEntero(double a) {
        // System.out.println("a=" + a);
        // System.out.println("lugaresDespuesDeLaComa(a) == 0=" + (lugaresDespuesDeLaComa(a) == 0));
        return lugaresDespuesDeLaComa(a) == 0;
    }

    //
//    public static boolean equivalente(boolean a,boolean b){
//        
//    }
    //Entra un 86.0+2.0+log(3.0)  y elimina los .0
    public static String eliminarPuntoCero(String a, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes) {
        String b = "";
        int contadorSalto = 0;
        for (int i = 0; i < a.length(); i++) {
            // System.out.println("i=" + i + " a.length()=" + a.length());
            if (contadorSalto-- != 0) {
                continue;
            } else {
                contadorSalto = 0;
            }

            try {

                //en caso de haber anteriormente un numero 5-5           
                double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(a, i, permitidosAContinuacion, permitidosAntes);
                if (lugaresDespuesDeLaComa(numero[0]) == 0) {
                    b += (int) numero[0];
                } else {
                    b += numero[0];
                }
                contadorSalto += (int) numero[1] - 1;
            } catch (NumberFormatException e) {
                //nada lo salta si no es un numero
                b += a.charAt(i);
                // System.out.println("a.charAt(i)=" + a.charAt(i) + " b=" + b);
            }
        }
        return b;
    }

    public static String eliminarPuntoCero(String a) {
        return eliminarPuntoCero(a, new ArrayList<String>(), new ArrayList<String>());
    }

    public static String eliminarPuntoCero(String a, ArrayList<String> permitidosAContinuacion) {
        return eliminarPuntoCero(a, permitidosAContinuacion, new ArrayList<String>());
    }

    public static int lugaresDespuesDeLaComa(double numero) {
        String decimal = (numero + "").substring((numero + "").indexOf(".") + 1);
        return decimal.length() == 1 && decimal.equals("0") ? 0 : decimal.length();
    }

    public static <E> ArrayList<ArrayList<E>> inicializarDobleArrayList(E a, int cantidadInicial) {
        ArrayList<ArrayList<E>> A = new ArrayList<ArrayList<E>>();
        for (int i = 0; i < cantidadInicial; i++) {
            A.add(new ArrayList<E>());
        }
        return A;
    }

    public static int cantidadDeCaracteresDeNumero(double a) {
        return new String(a + "").length();
    }

    public static int cantidadDeCaracteresDeNumero(int a) {
        return new String(a + "").length();
    }

    //devuelbe -1 si no lo contiene o el indice que contiene
    public static int contieneStringAEnIndiceAArrayListStringB(int indice, String a, ArrayList<String> b) {
        return contieneStringAEnIndiceAArregloStringB(indice, a, b.toArray(new String[0]));
    }

    public static int contieneStringAEnIndiceAArregloStringB(int indice, String a, String... b) {
        return contieneStringAEnIndiceAArregloStringB(false, indice, a, b);
    }

    public static int contieneStringAEnIndiceAArregloStringB(boolean ignoreCase, int indice2, String a, String... b2) {
        return contieneStringAEnIndiceAArregloStringB(ignoreCase, indice2, a, 0,0, b2);
    }

    public static int contieneStringAEnIndiceAArregloStringB(boolean ignoreCase, int indice2, String a, int indiceInicialB,int indiceInicialArregloB, String... B) {
        // System.out.println("a=" + a + " a.length()=" + a.length() + " indice=" + indice2);
        // String b[] = new String[b2.length];
        // System.arraycopy(b2, 0, b, 0, b2.length);
        if (ignoreCase) {
            a = a.toLowerCase();
        }
        For1:
        for (int j = indiceInicialArregloB; j < B.length; j++) {
            String b = B[j];
            if (ignoreCase) {
                b = B[j].toLowerCase();
            }
            if (indiceInicialB != 0) {
                b = b.substring(indiceInicialB);
            }

            int indice = indice2;
            //System.out.println(" j=" + j + " b[j]=" + b[j] + " b[j].length()=" + b[j].length());
            //System.out.println("a.length() - indice=" + (a.length() - indice));
            if (b.length() > a.length() - indice) {
                //System.out.println("continue");
                continue;
            }
            for (int i = 0; i < b.length(); i++, indice++) {
                //  System.out.println("i=" + i);
                //System.out.println("indice=" + indice + " a.charAt(indice)=" + a.charAt(indice) + " b[j].charAt(i)=" + b[j].charAt(i));
                if (a.charAt(indice) != b.charAt(i)) {
                    // System.out.println("continue2");
                    continue For1;
                }
                // System.out.println("j=" + j);
                if (i == b.length() - 1) {
                    return j;
                }
            }

        }
        // System.out.println("-1");
        return -1;
    }

    public static int contieneStringAEnIndiceAArregloStringBConSeparacion(boolean ignoreCase, int indice2, String a, int indiceInicialB, ConfiguracionDeVideo cdv, String... b2) {
        // System.out.println("a=" + a + " a.length()=" + a.length() + " indice=" + indice2);
        String separacion = Archivo.obtenerSeparacion(a, indice2, cdv);
        if (!separacion.isEmpty()) {
            String b[] = new String[b2.length];
            System.arraycopy(b2, 0, b, 0, b2.length);
            if (ignoreCase) {
                separacion = separacion.toLowerCase();
            }
            for (int i = 0; i < b.length; i++) {
                if (ignoreCase) {
                    b[i] = b[i].toLowerCase();
                }
                b[i] = b[i].substring(indiceInicialB);
                //System.out.println(" j=" + j + " b[j]=" + b[j] + " b[j].length()=" + b[j].length());
                //System.out.println("a.length() - indice=" + (a.length() - indice));
                if (b[i].length() > a.length() - indice2) {
                    //System.out.println("continue");
                    continue;
                }
                if (separacion.equals(b[i])) {
                    return indice2;
                }

            }
        }

        // System.out.println("-1");
        return -1;
    }

    public static int[] posicionesDeTerminacionDeInverso2(String a, ArrayList<String> comienzo, ArrayList<String> medios, ArrayList<String> terminacion, int indice) {
        return posicionesDeTerminacionDeInverso2(a, comienzo.toArray(new String[0]), medios.toArray(new String[0]), terminacion.toArray(new String[0]), indice);
    }

    public static int[] posicionesDeTerminacionDeInverso2(String a, String comienzo[], String medios[], String terminacion[], int indice) {
        int j[] = {-1, -1, -1};
        //System.out.println("a=" + a + " indice=" + indice + " a.charAt(indice)=" + a.charAt(indice));
        int j2 = contieneStringAEnIndiceAArregloStringBInverso(indice, a, terminacion);
        if (j2 >= 0) {//                                             indice - Funcion.getFinales().get(j).length()
            /// System.out.println("j2=" + j2);
            j = posicionesDeTerminacionDeInverso(a, comienzo, medios, terminacion, indice - terminacion[j2].length());
        }
        //  System.out.println(Arrays.toString(j) + " j=" + j.length);
        return j;
    }

    //devuelbe -1 si no lo contiene o el indice que contiene
    public static int contieneStringAEnIndiceAArrayListStringBInverso(int indice, String a, ArrayList<String> b) {
        return contieneStringAEnIndiceAArregloStringBInverso(indice, a, b.toArray(new String[0]));
    }

    public static int contieneStringAEnIndiceAArregloStringBInverso(int indice2, String a, String... b) {
        return contieneStringAEnIndiceAArregloStringBInverso(false, indice2, a, 0, b);

    }
public static int contieneStringAEnIndiceAArregloStringBInverso(int indice2, String a,int indice0B, String... b) {
        return contieneStringAEnIndiceAArregloStringBInverso(false, indice2, a, indice0B, b);

    }
//    public static int contieneStringAEnIndiceAArregloStringBInverso(boolean ignoreCase, int indice2, String a, String... b) {
//        return contieneStringAEnIndiceAArregloStringBInverso(ignoreCase, indice2, a, 0, b);
//    }

    public static int contieneStringAEnIndiceAArregloStringBInverso(boolean ignoreCase, int indice2, String a, int indice0B, String... b) {
        //System.out.println("a=" + a + " a.length()=" + a.length() + " indice=" + indice2);
        if (ignoreCase) {
            a = a.toLowerCase();
        }
        For1:
        for (int j = indice0B; j < b.length; j++) {
            if (ignoreCase) {
                b[j] = b[j].toLowerCase();
            }
            int indice = indice2;
            //  System.out.println(" j=" + j + " b[j]=" + b[j] + " b[j].length()=" + b[j].length());
            // System.out.println("b[j].length()=" + b[j].length() + " indice+1=" + (indice + 1) + " " + (b[j].length() > indice + 1));
            if (b[j].length() > indice + 1) {
                // System.out.println("continue1");
                continue;
            }
            for (int i = b[j].length() - 1; i >= 0; i--, indice--) {
                // System.out.println("r i=" + i);
                // System.out.println("indice=" + indice + " a.charAt(indice)=" + a.charAt(indice) + " b[j].charAt(i)=" + b[j].charAt(i));
                if (a.charAt(indice) != b[j].charAt(i)) {
                    //  System.out.println("continue 2");
                    continue For1;
                }
                if (i == 0) {
                    //  System.out.println("j=" + j);
                    return j;
                }
            }

        }
        // System.out.println("-1");
        return -1;
    }
//    public static boolean contieneStringAEnIndiceAStringB(String a, String b[], int indice) {
//        for (int j = 0; j < b.length; j++) {
//            if (b[j].length() > a.length() - indice) {
//                continue;
//            }
//            for (int i = 0; i < b[j].length(); i++, indice++) {
//                if (a.charAt(indice) != b[j].charAt(i)) {
//                    continue;
//                }
//            }
//            //variableLengthDeBuscador=j;
//           return true;
//        }
//      return false;
//    }

    //si es -1 no lo encontro
    public static int posicionFinalDe(String a, String comienzo[], String terminacion[], int indice) {
        int contadorDeComienzo = 0;
        for (int i = indice; i < a.length();) {
            int j = contieneStringAEnIndiceAArregloStringB(i, a, comienzo);
            if (j > -1) {
                contadorDeComienzo++;
                i += comienzo[j].length();
                continue;
            }
            j = contieneStringAEnIndiceAArregloStringB(i, a, terminacion);
            if (contadorDeComienzo == 0 && j > -1) {
                return i;
            }
            j = contieneStringAEnIndiceAArregloStringB(i, a, terminacion);
            if (j > -1) {
                contadorDeComienzo--;
                i += terminacion[j].length();
                continue;
            }
            i++;
        }
        return -1;

    }

    //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    // se busca si se conoce que anteriormente a este indice habia un comienzo
    //[0]y[1] no se incluyen
    public static int[] posicionesDeTerminacionDe(String a, ArrayList<String> comienzo, ArrayList<String> medios, ArrayList<String> terminacion, int indice) {
        return posicionesDeTerminacionDe(a, comienzo.toArray(new String[0]), medios.toArray(new String[0]), terminacion.toArray(new String[0]), indice);
    }

    public static int[] posicionesDeTerminacionDe(String a, String comienzo[], String terminacion[], int indice) {
        return posicionesDeTerminacionDe(a, comienzo, null, terminacion, indice);
    }

    public static int[] posicionesDeTerminacionDe(String a, String comienzo[], String medios[], String terminacion[], int indice) {
        int posiciones[] = {-1, -1};
        int contadorDeComienzo = 0;
        for (int i = indice; i < a.length();) { //System.out.println("i="+i);
            int j = contieneStringAEnIndiceAArregloStringB(i, a, comienzo);
            if (j > -1) {
                contadorDeComienzo++;
                i += comienzo[j].length();
                continue;
            }
            if (medios != null && medios.length != 0) {
                j = contieneStringAEnIndiceAArregloStringB(i, a, medios);
                if (j > -1 && a.charAt(i) != '^') {
                    if (contadorDeComienzo == 0) {
                        posiciones[0] = i;
                    }
                    i += medios[j].length();
                    continue;
                }
            }
            j = contieneStringAEnIndiceAArregloStringB(i, a, terminacion);
            if (contadorDeComienzo == 0 && j > -1 && !comienzo[j].isEmpty()) {
                posiciones[1] = i;
                return posiciones;
            }

            j = contieneStringAEnIndiceAArregloStringB(i, a, terminacion);
            if (j > -1) {
                contadorDeComienzo--;
                i += terminacion[j].length();
                continue;
            }
            i++;
        }
        return posiciones;

    }
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!1
    // se busca si se conoce que anteriormente en este indice habia una terminacion
    //[0]y[1] no se incluyen

    public static int[] posicionesDeTerminacionDeInverso(String a, ArrayList<String> comienzo, ArrayList<String> medios, ArrayList<String> terminacion, int indice) {
        return posicionesDeTerminacionDeInverso(a, comienzo.toArray(new String[0]), medios.toArray(new String[0]), terminacion.toArray(new String[0]), indice);
    }

    public static int[] posicionesDeTerminacionDeInverso(String a, String comienzo[], String medios[], String terminacion[], int indice) {
        int posiciones[] = {-1, -1, -1};

        int contadorDeTerminacion = 0;
        for (int i = indice; i >= 0;) {
            int j = contieneStringAEnIndiceAArregloStringBInverso(i, a, terminacion);
            if (j > -1) {
                contadorDeTerminacion++;
                i -= comienzo[j].length();
                continue;
            }
            j = contieneStringAEnIndiceAArregloStringBInverso(i, a, medios);
            if (j > -1 && a.charAt(i) != '^') {
                if (contadorDeTerminacion == 0) {
                    posiciones[0] = i;
                }
                i -= medios[j].length();
                continue;
            }
            j = contieneStringAEnIndiceAArregloStringBInverso(i, a, comienzo);
            if (contadorDeTerminacion == 0 && j > -1 && !comienzo[j].isEmpty()) {
                posiciones[1] = i;
                posiciones[2] = j;
                return posiciones;
            }

            j = contieneStringAEnIndiceAArregloStringBInverso(i, a, comienzo);
            if (j > -1) {
                contadorDeTerminacion--;
                i -= terminacion[j].length();
                continue;
            }
            i--;
        }
        return posiciones;

    }

    public static boolean contieneStringAEnIndiceAStringB(String a, String b, int indice) {
        if (b.length() > a.length() - indice) {
            return false;
        }
        for (int i = 0; i < b.length(); i++, indice++) {
            if (a.charAt(indice) != b.charAt(i)) {
                return false;
            }
        }

        return true;
    }

    public static int posicionFinalDe(String a, String comienzo, String terminacion, int indice) {
        int contadorDeComienzo = 0;
        for (int i = indice + 1; i < a.length();) {
            if (a.charAt(i) == comienzo.charAt(0) && contieneStringAEnIndiceAStringB(a, comienzo, i)) {
                contadorDeComienzo++;
                i += comienzo.length();
                continue;
            }
            if (contadorDeComienzo == 0 && a.charAt(i) == terminacion.charAt(0) && contieneStringAEnIndiceAStringB(a, terminacion, i)) {
                return i;
            }
            if (contieneStringAEnIndiceAStringB(a, terminacion, i)) {
                contadorDeComienzo--;
                i += terminacion.length();
                continue;
            }
            i++;
        }
        // return a.length();
        return -1;

    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion} o {-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param conSigno
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, new ArrayList<String>(), new ArrayList<String>(), conSigno);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion} o {-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidos
     * @param conSigno
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, String permitidos[], boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, new ArrayList<String>(Arrays.asList(permitidos)), new ArrayList<String>(), conSigno);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion} o {-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidos
     * @param conSigno
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, ArrayList<String> permitidos, boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, permitidos, new ArrayList<String>(), conSigno);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion} o {-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, new ArrayList<String>(), new ArrayList<String>(), true);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion} o {-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidos
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, String permitidos[]) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, new ArrayList<String>(Arrays.asList(permitidos)), new ArrayList<String>(), true);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion} o {-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidos
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, ArrayList<String> permitidos) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, permitidos, new ArrayList<String>(), true);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion} o {-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidosAContinuacion
     * @param permitidosAntes
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, permitidosAContinuacion, permitidosAntes, true);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion} o {-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidosAContinuacion
     * @param permitidosAntes
     * @param conSigno
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes, boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, permitidosAContinuacion, permitidosAntes, conSigno, false);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion} o {-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param conSigno
     * @param soloEntero
     * @return
     */
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, boolean conSigno, boolean soloEntero) {
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, new ArrayList<String>(), new ArrayList<String>(), conSigno, soloEntero);
    }

    /**
     * explota si en esta posicion no hay un numero<br>
     * {numero,cantidad De Caracteres original,i a continuacion} o {-1,-1,-1}
     *
     * @param a
     * @param posicionInicial
     * @param permitidosAContinuacion
     * @param permitidosAntes
     * @param conSigno
     * @param soloEntero
     * @return
     */
    //A[0] es el numero , A[1] es la cantidad de caracteres
    public static double[] buscarNumeroYCantidadDeCaracteresOriginal(String a, int posicionInicial, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes, boolean conSigno, boolean soloEntero) {
        double A[] = {-1, -1, -1};
        A[1] = 0;
        String numero = "";
        for (int i = posicionInicial; i < a.length() && ((a.charAt(i) >= 48 && a.charAt(i) <= 57) || (!soloEntero && (a.charAt(i) == '.' || a.charAt(i) == ',')) || (conSigno && numero.isEmpty() && esSignoMenos(a, i, permitidosAContinuacion, permitidosAntes))); i++) {
            //System.out.println("a.charAt(i)=" + a.charAt(i));
            if (a.charAt(i) == ',') {
                numero += '.' + "";
            } else {
                numero += a.charAt(i) + "";
            }
            A[1]++;
        }
        A[0] = Double.parseDouble(numero);
        A[2] = posicionInicial + A[1];
        return A;
    }

    public static double[] buscarNumeroYCantidadDeCaracteresOriginalInverso(String a, int posicionInicial, boolean conSigno,boolean soloEnteros) {
        return buscarNumeroYCantidadDeCaracteresOriginalInverso(a, posicionInicial, new ArrayList<String>(), new ArrayList<String>(), conSigno,soloEnteros);
    }

    public static double[] buscarNumeroYCantidadDeCaracteresOriginalInverso(String a, int posicionInicial, String permitidos[], boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginalInverso(a, posicionInicial, new ArrayList<String>(Arrays.asList(permitidos)), new ArrayList<String>(), conSigno,false);
    }

    public static double[] buscarNumeroYCantidadDeCaracteresOriginalInverso(String a, int posicionInicial, ArrayList<String> permitidos, boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginalInverso(a, posicionInicial, permitidos, new ArrayList<String>(), conSigno,false);
    }

    public static double buscarNumeroInverso(String a, int posicionInicial, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes, boolean conSigno) {
        return buscarNumeroYCantidadDeCaracteresOriginalInverso(a, posicionInicial, permitidosAContinuacion, permitidosAntes, conSigno,false)[0];
    }

    public static double[] buscarNumeroYCantidadDeCaracteresOriginalInverso(String a, int posicionInicial) {
        return buscarNumeroYCantidadDeCaracteresOriginalInverso(a, posicionInicial, new ArrayList<String>(), new ArrayList<String>(), true,false);
    }

    public static double[] buscarNumeroYCantidadDeCaracteresOriginalInverso(String a, int posicionInicial, String permitidos[]) {
        return buscarNumeroYCantidadDeCaracteresOriginalInverso(a, posicionInicial, new ArrayList<String>(Arrays.asList(permitidos)), new ArrayList<String>(), true,false);
    }

    public static double[] buscarNumeroYCantidadDeCaracteresOriginalInverso(String a, int posicionInicial, ArrayList<String> permitidos) {
        return buscarNumeroYCantidadDeCaracteresOriginalInverso(a, posicionInicial, permitidos, new ArrayList<String>(), true,false);
    }

    public static double buscarNumeroInverso(String a, int posicionInicial, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes) {
        return buscarNumeroYCantidadDeCaracteresOriginalInverso(a, posicionInicial, permitidosAContinuacion, permitidosAntes, true,false)[0];
    }

    //A[0] es el numero y A[1] es la cantidad de caracteres
    public static double[] buscarNumeroYCantidadDeCaracteresOriginalInverso(String a, int posicionInicial, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes, boolean conSigno,boolean soloEnteros) {
        double A[] = new double[2];
        A[1] = 0;
        String numero = "";
        for (int i = posicionInicial; i >= 0 && ((a.charAt(i) >= 48 && a.charAt(i) <= 57) || ((!soloEnteros)&&(a.charAt(i) == '.' || a.charAt(i) == ',')) || (conSigno && !numero.isEmpty() && esSignoMenos(a, i, permitidosAContinuacion, permitidosAntes))); i--) {
            if (a.charAt(i) == ',') {
                numero = '.' + numero;
            } else {
                numero = a.charAt(i) + numero;
            }
            A[1]++;

        }

        A[0] = Double.parseDouble(numero);
        return A;
    }

    public static double buscarNumero(String a, int posicionInicial) {
        return buscarNumero(a, posicionInicial, new ArrayList<String>());
    }

    public static double buscarNumero(String a, int posicionInicial, String permitidos[]) {
        return buscarNumero(a, posicionInicial, new ArrayList<String>(Arrays.asList(permitidos)));
    }

    public static double buscarNumero(String a, int posicionInicial, ArrayList<String> permitidos) {
//        String numero = "";
//        for (int i = posicionInicial; i < a.length() && ((a.charAt(i) >= 48 && a.charAt(i) <= 57) || a.charAt(i) == '.' || a.charAt(i) == ',' || esSignoMenos(a, i, permitidos)); i++) {
//            if (a.charAt(i) == ',') {
//                numero += '.' + "";
//            } else {
//                numero += a.charAt(i) + "";
//            }
//        }
//        return Double.parseDouble(numero);
        return buscarNumeroYCantidadDeCaracteresOriginal(a, posicionInicial, permitidos, new ArrayList<String>())[0];
    }

    public static boolean esSignoMenos(String a, int indice) {
        return esSignoMenos(a, indice, new ArrayList<String>());
    }

//    public static boolean esSignoMenos(String a, int indice, ArrayList<String> permitidosAcontinuacion) {
//        String permitidosAntes[] = {"!", "+", "/", "*", "^"};
//        return esSignoMenos(a, indice, permitidosAcontinuacion, new ArrayList<String>(Arrays.asList(permitidosAntes)));
//    }
    public static boolean esSignoMenos(String a, int indice, ArrayList<String> permitidosAcontinuacion) {
        //String permitidosAntes[] = {"!", "+", "/", "*", "^"};
        return esSignoMenos(a, indice, new ArrayList<String>(), new ArrayList<String>());
    }
//numeros (a.charAt(i) >= 48 && a.charAt(i)<= 57)
    //letras minusculas (a.charAt(i) >= 97 && a.charAt(i) <= 122)
    //letras mayusculas (a.charAt(i) >= 65 && a.charAt(i) <= 90)

    public static boolean esSignoMenos(String a, int indice, ArrayList<String> permitidosAContinuacion, ArrayList<String> permitidosAntes) {
        //  System.out.println("***********************");
        //System.out.println("a=" + a + " a.charAt(indice)=" + a.charAt(indice) + " indice=" + indice);

        if (a.charAt(indice) != '-' || indice == a.length() - 1 || (indice > 0 ? (a.charAt(indice - 1) >= '0' && a.charAt(indice - 1) <= '9') || (a.charAt(indice - 1) >= 'a' && a.charAt(indice - 1) <= 'z') : false)) {
            return false;
        }
        if (indice == 0 && a.charAt(indice) == '-') {
            return true;
        }

        if (indice == 0 && a.length() > 0 && ((a.charAt(indice + 1) >= '0' && a.charAt(indice + 1) <= '9') || (a.charAt(indice + 1) >= 'a' && a.charAt(indice + 1) <= 'z') || contieneStringAEnIndiceAArrayListStringB(indice + 1, a, permitidosAContinuacion) >= 0)) {
            return true;
        }
        String permitidosAntes2[] = {"!", "+", "/", "*", "^", "("};
        permitidosAntes.addAll(new ArrayList<String>(Arrays.asList(permitidosAntes2)));

        if ((indice > 0 && indice < a.length() - 1) && ((a.charAt(indice + 1) >= '0' && a.charAt(indice + 1) <= '9') || (a.charAt(indice + 1) >= 'a' && a.charAt(indice + 1) <= 'z') || contieneStringAEnIndiceAArrayListStringB(indice + 1, a, permitidosAContinuacion) >= 0)
                && (a.charAt(indice - 1) == '-' || (a.charAt(indice - 1) >= '0' && a.charAt(indice - 1) <= '9') || (a.charAt(indice - 1) >= 'a' && a.charAt(indice - 1) <= 'z') || (contieneStringAEnIndiceAArregloStringBInverso(indice - 1, a, permitidosAntes.toArray(new String[0])) >= 0))) {
            return true;
        }

        return false;
    }

    public static String[] eliminarStringVaciosDeArregloString(String A[]) {
        return eliminarStringVaciosDeArrayListString(new ArrayList<String>(Arrays.asList(A))).toArray(A);
    }

    public static ArrayList<String> eliminarStringVaciosDeArrayListString(ArrayList<String> A) {
        for (int i = 0; i < A.size();) {
            if (StringRealmenteVacio(A.get(i))) {
                A.remove(i);
            } else {
                i++;
            }
        }
        return A;
    }

    public static String[] eliminarEspaciosDelPrincipioStringEnArregloString(String A[]) {
        A = eliminarStringVaciosDeArregloString(A);
        for (int i = 0; i < A.length; i++) {
            A[i] = eliminarEspaciosDelPrincipioString(A[i]);
        }
        return A;
    }

    public static ArrayList<String> eliminarEspaciosDelPrincipioStringEnArrayListString(ArrayList<String> A) {
        A = eliminarStringVaciosDeArrayListString(A);
        for (int i = 0; i < A.size(); i++) {
            A.set(i, eliminarEspaciosDelPrincipioString(A.get(i)));
        }
        return A;
    }

    public static String eliminarEspaciosDelPrincipioString(String a) {
        if (a.isEmpty()) {
            return a;
        }
        int i = 0;
        while (a.charAt(i) == ' ' || a.charAt(i) == 10) {
            i++;
        }
        return a.substring(i);
    }

    //devuelbe si A==B 0 , A>B 1,  A<B -1;
    public static int compararStringAConStringB(String A, String B, boolean prioridadNumerica) {
        int PRIMERO_MAYOR = 1;
        int PRIMERO_MENOR = -1;
        if (A.equals(B)) {
            //  System.out.println("uno");
            return 0;
        }
        if (StringRealmenteVacio(A)) {
            return PRIMERO_MAYOR;
        }
        if (StringRealmenteVacio(B)) {
            return PRIMERO_MENOR;
        }
        A = eliminarEspaciosDelPrincipioString(A);
        A = eliminarEspaciosDelPrincipioString(A);

        boolean iguales = false;
        boolean o1Mayor = false;
        if (A.length() == B.length()) {
            iguales = true;
        } else {
            if (A.length() > B.length()) {
                o1Mayor = true;
            }
        }
        //A 65-90 a 97-122   32
        //
        String mayor = o1Mayor ? A : B;
        String menor = o1Mayor ? B : A;
        for (int i = 0; i < menor.length(); i++) {
            char menorChar = menor.charAt(i);
            char mayorChar = mayor.charAt(i);

            if (menorChar >= 97 && menorChar <= 122) {
                menorChar -= 32;// System.out.println("resto1");
            }
            if (mayorChar >= 97 && mayorChar <= 122) {
                mayorChar -= 32;// System.out.println("resto2");
            }
            if (prioridadNumerica && (menorChar >= 48 && menorChar <= 57) && (mayorChar >= 48 && mayorChar <= 57)) {
                return (int) (buscarNumero(A, i) - buscarNumero(B, i));
            }

            if (menorChar == mayorChar) {
                continue;
            }

            if (menorChar < mayorChar) {

                if (o1Mayor) {
                    // System.out.println("dos");
                    return PRIMERO_MAYOR;
                } else {
                    //System.out.println("tres");
                    return PRIMERO_MENOR;
                }

            } else {
                if (o1Mayor) {
                    //System.out.println("cuatro");
                    return PRIMERO_MENOR;
                } else {
                    // System.out.println("cinco");
                    return PRIMERO_MAYOR;
                }

            }

        }
        // System.out.println("llego a qui");
        return o1Mayor ? PRIMERO_MAYOR : PRIMERO_MENOR;
    }

//devuelbe true cuando el String esta vacio o solo contiene espacios
    public static boolean StringRealmenteVacio(String a) {
        if (a == null || a.isEmpty() || a.length() == 0) {
            //System.out.println("true");
            return true;
        }
        //  System.out.println("a="+a+" leng"+a.length());
        for (int i = 0; i < a.length(); i++) {//System.out.println("i="+i);
            //  System.out.println("a.charAt(i)="+a.charAt(i));
            if (a.charAt(i) != ' ' && a.charAt(i) != 10) {
                return false;
            }
        }
        //  System.out.println("true 2");
        return true;
    }

    //→
    public static boolean condicional(boolean a, boolean b) {
        return a == true & b == false ? false : true;
    }

    public static boolean bicondicional(boolean... A) {
        boolean respuesta = true;
        for (int i = 0; i < A.length; i++) {
            respuesta = bicondicional(respuesta, A[i]);
        }
        return respuesta;
    }

    //↔
    public static boolean bicondicional(boolean a, boolean b) {
        return !(a ^ b);
    }

    //inicio y fin  se sustituyen
    public static String sustituirPorcionString(String a, String porcionNueva, int inicio, int fin) {
        return a.substring(0, inicio) + porcionNueva + a.substring(fin - 1);
    }

    public static String agregarPorcionString(String a, String porcionNueva, int indice) {
        return a.substring(0, indice) + porcionNueva + a.substring(indice);
    }

    public static int posicionInicialDelCorchete(String a, int PosicionFinalDelCorchete) {
        int contadorCorchetes = 0;
        for (int i = PosicionFinalDelCorchete - 1; i >= 0; i--) {
            if (a.charAt(i) == ']') {
                contadorCorchetes++;
            }
            if (contadorCorchetes == 0 && a.charAt(i) == '[') {
                return i;
            }
            if (a.charAt(i) == '[') {
                contadorCorchetes--;
            }
        }
        return 99;
    }

    public static int posicionFinalDelCorchete(String a, int PosicionInicialDelCorchete) {
        int contadorCorchetes = 0;
        for (int i = PosicionInicialDelCorchete + 1; i < a.length(); i++) {
            if (a.charAt(i) == '[') {
                contadorCorchetes++;
            }
            if (contadorCorchetes == 0 && a.charAt(i) == ']') {
                return i;
            }
            if (a.charAt(i) == ']') {
                contadorCorchetes--;
            }
        }
        return a.length();
    }
//

    public static int posicionInicialDelParentesis(String a, int PosicionFinalDelParentesis) {
        int contadorCorchetes = 0;
        for (int i = PosicionFinalDelParentesis - 1; i > 0; i--) {
            if (a.charAt(i) == ')') {
                contadorCorchetes++;
            }
            if (contadorCorchetes == 0 && a.charAt(i) == '(') {
                return i;
            }
            if (a.charAt(i) == '(') {
                contadorCorchetes--;
            }
        }
        return 0;
    }

    public static int posicionFinalDelParentesis(String a, int PosicionInicialDelParentesis) {
        int contadorCorchetes = 0;
        for (int i = PosicionInicialDelParentesis + 1; i < a.length(); i++) {
            if (a.charAt(i) == '(') {
                contadorCorchetes++;
            }
            if (contadorCorchetes == 0 && a.charAt(i) == ')') {
                return i;
            }
            if (a.charAt(i) == ')') {
                contadorCorchetes--;
            }
        }
        return a.length();
    }

    //Subcadena donde y cuantas
    public static void subcadena(String palabra, String subcadena) {
        String lugares = "";
        int cantidad = 0;
        salto:
        for (int i = 0; i < palabra.length() - (palabra.length() - 1); i++) {
            if (palabra.charAt(i) == subcadena.charAt(0)) {
                for (int j = 1; j < subcadena.length() & j + i < palabra.length(); j++) {
                    if (palabra.charAt(i + j) != subcadena.charAt(j)) {
                        continue salto;
                    }
                }
                lugares += i + 1 + "  ";
                cantidad++;
            }
        }
        if (cantidad != 0) {
            System.out.println("La subcadena se encuentra contenida unas " + cantidad);
            System.out.println("En las posiciones " + lugares);
        } else {
            System.out.println("No se encuentra contenidad");
        }
    }

    //Sacar las bocales de una palabra
    public static String vocales(String a) {
        TreeSet Vocales = new TreeSet();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) == 'a' | a.charAt(i) == 'e' | a.charAt(i) == 'i' | a.charAt(i) == 'o' | a.charAt(i) == 'u') {
                Vocales.add(a.charAt(i));
            }
        }
        return Vocales.toString();
    }

    public static String consonates(String a) {
        TreeSet Consonantes = new TreeSet();
        for (int i = 0; i < a.length(); i++) {
            if (a.charAt(i) != 'a' && a.charAt(i) != 'e' && a.charAt(i) != 'i' && a.charAt(i) != 'o' && a.charAt(i) != 'u') {
                Consonantes.add(a.charAt(i));
            }
        }
        return Consonantes.toString();
    }

    public static boolean palindrome(String a) {
        for (int i = 0; i < a.length() / 2; i++) {
            if (a.charAt(i) != a.charAt(a.length() - i - 1)) {
                return false;
            }
        }
        return true;
    }

    //De Binario a numero
    public static double binarioANumero(String a) {
        double numero = 0;
        for (int i = 0; i < a.length(); i++) {
            numero += Math.pow(2, i) * Integer.parseInt(a.charAt(a.length() - 1 - i) + "");
        }
        return numero;
    }

    //De numero a binario
    public static String numeroAbinario(int a) {
        String binario = "";
        while (a != 1) {
            binario = a % 2 == 0 ? "0" + binario : "1" + binario;
            a /= 2;
        }
        return "1" + binario;
    }

    //suma binaria
    public static String sumaBinaria(String a, String b) {
        String suma = "";
        char acarreo;
        String Mayor = a.length() > b.length() ? a : b;
        String Menor = b.length() < a.length() ? b : a;
        suma += s(a.charAt(a.length() - 1), b.charAt(b.length() - 1));
        acarreo = a(a.charAt(a.length() - 1), b.charAt(b.length() - 1));
        for (int i = 1; i < Mayor.length(); i++) {
            if (i < Menor.length()) {
                suma = s(acarreo, s(Mayor.charAt(Mayor.length() - i - 1), Menor.charAt(Menor.length() - i - 1))) + suma;
                acarreo = a(acarreo, s(Mayor.charAt(Mayor.length() - i - 1), Menor.charAt(Menor.length() - i - 1)));
                acarreo = acarreo == '1' ? '1' : a(Mayor.charAt(Mayor.length() - i - 1), Menor.charAt(Menor.length() - i - 1));
            } else {
                suma = s(acarreo, s(Mayor.charAt(Mayor.length() - i - 1), '0')) + suma;
                acarreo = a(acarreo, s(Mayor.charAt(Mayor.length() - i - 1), '0'));
                acarreo = acarreo == '1' ? '1' : a(Mayor.charAt(Mayor.length() - i - 1), '0');
            }
        }
        return acarreo == '1' ? '1' + suma : suma;
    }

    private static char s(char a, char b) {
        return a != b ? '1' : '0';
    }

    private static char a(char a, char b) {
        return a == '0' | b == '0' ? '0' : '1';
    }

//    //Es primo o no
//    public static boolean primo(int a) {
//        int primos[] = primos(a);
//        if (primos[primos.length - 1] == a) {
//            return true;
//        }
//        return false;
//    }
    public static double[] StringLineAArregloDouble(String a) {
        StringTokenizer T = new StringTokenizer(a);
        double A[] = new double[T.countTokens()];
        for (int i = 0; i < A.length; i++) {
            A[i] = Double.parseDouble(T.nextToken());
        }
        return A;
    }

    public static String[] StringLineAArregloString(String a) {
        StringTokenizer T = new StringTokenizer(a);
        String A[] = new String[T.countTokens()];
        for (int i = 0; i < A.length; i++) {
            A[i] = T.nextToken();
        }
        return A;
    }

    public static int[] StringLineAArregloInt(String a) {
        StringTokenizer T = new StringTokenizer(a);
        int A[] = new int[T.countTokens()];
        for (int i = 0; i < A.length; i++) {
            A[i] = Integer.parseInt(T.nextToken());
        }
        return A;
    }

    public static ArrayList<Double> StringLineAArrayListDouble(String a) {
        StringTokenizer T = new StringTokenizer(a);
        ArrayList<Double> A = new ArrayList<Double>();
        while (T.hasMoreTokens()) {
            A.add(Double.parseDouble(T.nextToken()));
        }
        return A;
    }

    public static ArrayList<String> StringLineAArrayListString(String a) {
        StringTokenizer T = new StringTokenizer(a);
        ArrayList<String> A = new ArrayList<String>();
        while (T.hasMoreTokens()) {
            A.add(T.nextToken());
        }
        return A;
    }
//Devuelbe un oarcion con las palabras separadas por un espacio

    public static String ArregloStringAStringLine(String A[], boolean deIsquierdaADerecha) {
        String StringLine = "";
        for (String a : A) {
            if (deIsquierdaADerecha) {
                StringLine += " " + a;
            } else {
                StringLine = a + " " + StringLine;
            }
        }
        return StringLine;
    }

    //Devuelbe un oarcion con las palabras separadas por un espacio
    public static String ArrayListStringAStringLine(ArrayList<String> A, boolean deIsquierdaADerecha) {
        String StringLine = "";
        for (String a : A) {
            if (deIsquierdaADerecha) {
                StringLine += " " + a;
            } else {
                StringLine = a + " " + StringLine;
            }
        }
        return StringLine;
    }

    //Devuelbe un oarcion que comiensa con el String(inicio) y 
    //los intervalos separados por el String(intervalos) ,la oacion termina
    //con el String(fin)
    public static String ArregloStringAStringLine(String A[], boolean deIsquierdaADerecha, String intervalos, String principio, String fin) {
        String StringLine = principio;
        for (int i = 0; i < A.length; i++) {
            if (i == 0) {
                StringLine += A[i];
                continue;
            }
            if (deIsquierdaADerecha) {
                StringLine += intervalos + A[i];
            } else {
                StringLine = A[i] + intervalos + StringLine;
            }
        }

        return StringLine + fin;
    }

    //Devuelbe un oarcion que comiensa con el String(inicio) y 
    //los intervalos separados por el String(intervalos) ,la oacion termina
    //con el String(fin)
    public static String ArrayListStringAStringLine(ArrayList<String> A, boolean deIsquierdaADerecha, String intervalos, String principio, String fin) {
        String StringLine = principio;
        for (int i = 0; i < A.size(); i++) {
            if (i == 0) {
                StringLine += A.get(i);
                continue;
            }
            if (deIsquierdaADerecha) {
                StringLine += intervalos + A.get(i);
            } else {
                StringLine = A.get(i) + intervalos + StringLine;
            }
        }

        return StringLine + fin;
    }

}
