/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.MetodosUtiles;

//import static Utiles.ClasesUtiles.Tiempo.Mes.*;
import Utiles.ClasesUtiles.Tiempo2.Dia;
import Utiles.ClasesUtiles.Tiempo2.Fecha;
import static Utiles.MetodosUtiles.MetodosUtiles.*;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Collections;

/**
 * metodos para trabajar con datos relacionados con el paso del tiempo 
 * Version 0.3
 *
 * @author Rene
 */
public abstract class Tempus {

    public final static int CANTIDAD_DIAS_AÑO = 365;
    public final static int CANTIDAD_DIAS_AÑO_BISIESTO = CANTIDAD_DIAS_AÑO + 1;
    public static final String nombresMes[] = {"enero", "febrero", "marzo", "abril",
        "mayo", "junio", "julio", "agosto", "septiembre", "octubre", "noviembre", "diciembre"};
    public static final String nombresDia[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo"};
    public static final String nombresDiaEntreSemana[] = {"Lunes", "Martes", "Miercoles", "Jueves", "Viernes"};
    public static final String nombresDiaFinesDeSemana[] = {"Sabado", "Domingo"};
//1900 0 lunes

    public static boolean esFecha(String a, int indice) {
        return obtenerFecha(a, indice) != null;
    }

    public static Fecha obtenerFecha(String a, int indice) {
        
        Object o = obtenerFechaYcantidadDeCaracteresOriginal(a, indice) ;
        return (o != null ? (Fecha)((Object[])o )[0] : null);
    }

    /**
     * En [0] es la fecha , en [1] la cantidad de caracteres ,en [2] la posicion
     * del indice que iria a continuacion
     *
     * @param a
     * @param indice
     * @return en caso de no ser una fecha null
     */
    public static Object[] obtenerFechaYcantidadDeCaracteresOriginal(String a, int indice) {
        class auxiliar {

            int continuarYcomprobar(String a, int indice, double Numero[], int max, int maxCarateres, boolean ultimo) throws Exception {
//                System.out.println(Arrays.toString(Numero));
//                System.out.println("Numero[0] < ++max="+(Numero[0] < ++max));
//                System.out.println("esEntero(Numero[0]="+(esEntero(Numero[0])));
//                System.out.println("(int) Numero[0]="+(int) Numero[0]);
//                System.out.println("cantidadDeCaracteresDeNumero((int) Numero[0])="+cantidadDeCaracteresDeNumero((int) Numero[0]));
//                System.out.println("maxCarateres="+maxCarateres);
//                System.out.println("cantidadDeCaracteresDeNumero((int) Numero[1]) < ++maxCarateres="+(cantidadDeCaracteresDeNumero((int) Numero[0]) < ++maxCarateres));
                if (!(Numero[0] < ++max && esEntero(Numero[0]) && cantidadDeCaracteresDeNumero((int) Numero[0]) < ++maxCarateres)) {
                    throw new Exception();
                }
               // System.out.println("Numero[1]="+Numero[1]);
                //  System.out.println("1 indice="+indice);
                indice += Numero[1];
                // System.out.println("2 indice="+indice);
                if (!ultimo) {
                    if (!or(a.charAt(indice), '/', '-')) {
                        throw new Exception();
                    }
                    
                    System.arraycopy(buscarNumeroYCantidadDeCaracteresOriginal(a, ++indice), 0, Numero, 0, 2);
                    //   Numero = buscarNumeroYCantidadDeCaracteresOriginal(a, ++indice);
                }
                //  System.out.println("3 indice="+indice);
                return indice;
            }
        }
        double dia = -1, mes = -1, año = -1, cantidadDeCaracteres = 0;
        try {
            double Numero[] = buscarNumeroYCantidadDeCaracteresOriginal(a, indice);
            // System.out.println(Arrays.toString(Numero));
            dia = Numero[0];
            //  System.out.println("dia="+dia);
            cantidadDeCaracteres += Numero[1];
            //  System.out.println("cantidadDeCaracteres="+cantidadDeCaracteres);
            indice = new auxiliar().continuarYcomprobar(a, indice, Numero, 31, 2, false);
           // System.out.println("4 indice="+indice);
            // System.out.println(Arrays.toString(Numero));
            mes = Numero[0];
            //  System.out.println("mes="+mes);
            cantidadDeCaracteres += Numero[1];
            // System.out.println("cantidadDeCaracteres="+cantidadDeCaracteres);
            indice = new auxiliar().continuarYcomprobar(a, indice, Numero, 12, 2, false);
          //  System.out.println("4 indice="+indice);
            //   System.out.println(Arrays.toString(Numero));
            año = Numero[0];
            //  System.out.println("año="+año );
            cantidadDeCaracteres += Numero[1] + 2;
            //  System.out.println("cantidadDeCaracteres="+cantidadDeCaracteres);
            indice = new auxiliar().continuarYcomprobar(a, indice, Numero, 9999, 4, true);
            //System.out.println("4 indice="+indice);
        } catch (Exception e) {
            return null;
        }

        return new Object[]{new Fecha((int) --dia, (int) --mes, (int) año), cantidadDeCaracteres, indice};

    }

    public static boolean esFeriado(Fecha f) {
        return esFeriado(f.getDia(), f.getMes());
    }

    public static boolean esFeriado(int dia, int mes) {
        switch (mes) {
            case 0:
                if (or(++dia, 1, 2)) {
                    return true;
                }
                break;
            case 2:
                if (or(++dia, 30)) {
                    return true;
                }
                break;
            case 4:
                if (or(++dia, 1)) {
                    return true;
                }
                break;
            case 6:
                if (or(++dia, 25, 26, 27)) {
                    return true;
                }
                break;
            case 9:
                if (or(++dia, 10)) {
                    return true;
                }
                break;
            case 11:
                if (or(++dia, 25, 31)) {
                    return true;
                }
                break;
        }
        return false;
    }

    // public static 
    public static LinkedList<Fecha> fechasPosterioresA(LinkedList<Fecha> fechas, Fecha f) {
        return new LinkedList<Fecha>(Arrays.asList(fechasAnterioresOPosterioresA(fechas.toArray(new Fecha[]{}), f, true)));
    }

    public static LinkedList<Fecha> fechasAnterioresA(LinkedList<Fecha> fechas, Fecha f) {
        return new LinkedList<Fecha>(Arrays.asList(fechasAnterioresOPosterioresA(fechas.toArray(new Fecha[]{}), f, false)));
    }

    public static Fecha[] fechasPosterioresA(Fecha fechas[], Fecha f) {
        return fechasAnterioresOPosterioresA(fechas, f, true);
    }

    public static Fecha[] fechasAnterioresA(Fecha fechas[], Fecha f) {
        return fechasAnterioresOPosterioresA(fechas, f, false);
    }

    private static Fecha[] fechasAnterioresOPosterioresA(Fecha fechas[], Fecha f, boolean posterior) {
        LinkedList<Fecha> l = new LinkedList<Fecha>(Arrays.asList(fechas));
        for (int i = 0; i < l.size();) {
            if (l.get(i).compareTo(f) != (posterior ? 1 : -1)) {
                l.remove(i);
            } else {
                i++;
            }
        }
        return l.toArray(new Fecha[]{});
    }

    public static boolean esFinDeSemana(Fecha f) {
        return or(nombreDia(f), nombresDiaFinesDeSemana);
    }

    public static boolean esEntreSemana(Fecha f) {
        return or(nombreDia(f), nombresDiaEntreSemana);
    }

    public static Dia[] getDiasNoLaborables(Dia dias[]) {
        return getDias(dias, true, "Sabado", "Domingo");
    }

    public static Dia[] getFeriados(Dia dias[]) {
        return getDias(dias, true, new String[0]);
    }

    public static Dia[] getDiasEntreSemana(Dia dias[]) {
        return getDias(dias, "Lunes", "Martes", "Miercoles", "Jueves", "Viernes");
    }

    public static Dia[] getFinesDeSemana(Dia dias[]) {
        return getDias(dias, "Sabado", "Domingo");
    }

    public static Dia[] getDias(Dia dias[], String... nombres) {
        return getDias(dias, false, nombres);
    }

    public static Dia[] getDias(Dia dias[], boolean losFeriados, String... nombres) {
        LinkedList<Dia> respuesta = new LinkedList<Dia>();
        For1:
        for (Dia d : dias) {
            for (String n : nombres) {
                if (d.getNombre().equals(n)) {
                    respuesta.add(d);
                    continue For1;
                }
            }
            if (losFeriados && d.isFeriado()) {
                respuesta.add(d);
            }
        }
        Collections.sort(respuesta);
        return respuesta.toArray(new Dia[0]);
    }

    public static Dia[] inicializarDias(int dia, int mes, int año, int cantidadDeDias) {
        return inicializarDias(new Fecha(dia, mes, año), cantidadDeDias);
    }

    public static Dia[] inicializarDias(Fecha a, int cantidadDeDias) {
        if (cantidadDeDias == 0) {
            return null;
        }
        Dia dias[] = new Dia[cantidadDeDias];
        dias[0] = new Dia(a);
        if (cantidadDeDias == 1) {
            return dias;
        }
//        int posicion = posicionDia(dias[0].getNombre()) + 1;
//        int mes = a.getMes();
//        int cantidadDeDiasDelMes = cantidadDeDiasMes(a.getMes());
//        int año = a.getAño();
//        int dia = a.getDia() + 1;
        //  for (int i = 1; i < dias.length; i++, posicion++, dia++) {
//            posicion = aumentoPosicionDia(posicion);
//            if (!(cantidadDeDiasDelMes > dia)) {
//                if (mes == 11) {
//                    ++año;
//                }
//                mes = aumentoMes(mes);
//                cantidadDeDiasDelMes = cantidadDeDiasMes(a.getMes());
//                dia = 0;
//            }
//            dias[i] = new Dia(dia, mes, año, nombreDia(posicion));
        //  }
        for (int i = 1; i < cantidadDeDias; i++) {
            dias[i] = dias[i - 1].next();
        }
        return dias;
    }

    public static void imprimirDias(Dia dias[]) {
        System.out.println(stringDias(dias));
    }

    public static String stringDias(Dia dias[]) {
        String diasString = "";
        for (Dia d : dias) {
            diasString += d + "\n";
        }
        return diasString;
    }

    public static int aumentoMes(int mes) {
        return ++mes > 11 ? 0 : mes;
    }

    public static int retrocesoMes(int mes) {
        return --mes < 0 ? 11 : mes;
    }

//    public static String aumentoNombreDia(String nombre) {
//        return nombreDia(aumentoPosicionDia(posicionDia(nombre)));
//    }
//
//    public static String retrocesoNombreDia(String nombre) {
//        return nombreDia(retrocesoPosicionDia(posicionDia(nombre)));
//    }
    public static String aumentoNombreDia(String nombre) {
        return nombreDia(aumentoPosicionDia(posicionDia(nombre)));
    }

    public static String retrocesoNombreDia(String nombre) {
        return nombreDia(retrocesoPosicionDia(posicionDia(nombre)));
    }

    public static int aumentoPosicionDia(int posicion) {
        return ++posicion > 6 ? 0 : posicion;
    }

    public static int retrocesoPosicionDia(int posicion) {
        return --posicion < 0 ? 6 : posicion;
    }

    public static String nombreDia(int dia, int mes, int año) {
        return nombreDia(new Fecha(dia, mes, año));
    }

    public static String nombreDia(Fecha a) {
        if (a.getAño() < 1900) {
            return "desconocido";
        }
        return nombreDia(new Fecha(0, 0, 1900), a, "Lunes");
    }

    public static String nombreDia(int diaA, int mesA, int añoA, int diaB, int mesB, int añoB, String nombreA) {
        return nombreDia(new Fecha(diaA, mesA, añoA), new Fecha(diaB, mesB, añoB), nombreA);
    }

    public static String nombreDia(Fecha a, Fecha b, String nombreA) {
        if (a.igualA(b)) {
            return nombreA;
        }
        // System.out.println("cantidadDeDiasEntreFechas(a, b)="+cantidadDeDiasEntreFechas(a, b));
        int resto = cantidadDeDiasEntreFechas(a, b) % 7;
        // System.out.println("resto="+resto);
        if (resto == 0) {
            return nombreA;
        }
        // System.out.println("posicionDia(nombreA)="+posicionDia(nombreA));
        int posicion = posicionDia(nombreA) + resto;
        return nombreDia(posicion < 7 ? posicion : 7 - posicion);
    }

    public static boolean esBisiesto(int año) {
        return año % 4 == 0 && (año % 100 == 0 ? año % 400 == 0 : true);
    }

    public static int cantidadDeDiasEntreFechas(int diaA, int mesA, int añoA, int diaB, int mesB, int añoB) {
        return cantidadDeDiasEntreFechas(new Fecha(diaA, mesA, añoA), new Fecha(diaB, mesB, añoB));
    }

    public static int cantidadDeDiasEntreFechas(Fecha a, Fecha b) {
        if (a.igualA(b)) {
            return 0;
        }
        if (a.getAño() == b.getAño()) {
            //  System.out.println("cantidadDeDiasHastaFinalesDelAño(a)="+cantidadDeDiasHastaFinalesDelAño(a));
            //   System.out.println("cantidadDeDiasDesdePrinciposDelAño(b)="+cantidadDeDiasDesdePrinciposDelAño(b));
            //  System.out.println("cantidadDeDiasAño(a.getAño())="+cantidadDeDiasAño(a.getAño()));
            //      System.out.println("(cantidadDeDiasHastaFinalesDelAño(a) + cantidadDeDiasDesdePrinciposDelAño(b))-cantidadDeDiasAño(a.getAño())="+((cantidadDeDiasHastaFinalesDelAño(a) + cantidadDeDiasDesdePrinciposDelAño(b))-cantidadDeDiasAño(a.getAño())));
            return (cantidadDeDiasHastaFinalesDelAño(a) + cantidadDeDiasDesdePrinciposDelAño(b)) - cantidadDeDiasAño(a.getAño());
        }

        //System.out.println("a.igualA(b)="+a.igualA(b));
        //    System.out.println("cantidadDeDiasHastaFinalesDelAño(a)="+cantidadDeDiasHastaFinalesDelAño(a));
        //  System.out.println("cantidadDeDiasEntreAños(a.getAño(), b.getAño())="+cantidadDeDiasEntreAños(a.getAño()+1, b.getAño()));
        //   System.out.println("cantidadDeDiasDesdePrinciposDelAño(b)="+cantidadDeDiasDesdePrinciposDelAño(b));
        return cantidadDeDiasHastaFinalesDelAño(a) + cantidadDeDiasDesdePrinciposDelAño(b) + (a.getAño() + 1 == b.getAño() ? 0 : cantidadDeDiasEntreAños(a.getAño() + 1, b.getAño()));
    }

    public static int cantidadDeDiasEntre4Años() {
        return 3 * CANTIDAD_DIAS_AÑO + CANTIDAD_DIAS_AÑO_BISIESTO;
    }

    public static int cantidadDeDiasHastaFinalesDelAño(int mes) {
        return cantidadDeDiasHastaFinalesDelAño(0, mes);
    }

    public static int cantidadDeDiasHastaFinalesDelAño(int dia, int mes) {
        return cantidadDeDiasHastaFinalesDelAño(dia, mes, 1);
    }

    public static int cantidadDeDiasHastaFinalesDelAño(int dia, int mes, int año) {
        return cantidadDeDiasHastaFinalesDelAño(new Fecha(dia, mes, año));
    }

    /**
     * debuelbe la cantidad de dias que quedan hasta finalizar el año sin tener
     * en quenta el dia en que nos encontramos, utiliza el año para conocer si
     * es bisiesto
     *
     * @param f clase Fecha creada por mi
     * @return int
     */
    public static int cantidadDeDiasHastaFinalesDelAño(Fecha f) {
        if (f.getMes() == 11) {
            return cantidadDeDiasMes(11) - f.getDia();
        }
        int cantidadDeDias = 0;
        for (int i = f.getMes() + 1; i < 12; i++) {
            cantidadDeDias += cantidadDeDiasMes(i, f.getAño());
        }
        return cantidadDeDias + cantidadDeDiasMes(f.getMes()) - f.getDia();
    }

    public static int cantidadDeDiasDesdePrinciposDelAño(int dia, int mes) {
        return cantidadDeDiasDesdePrinciposDelAño(dia, mes, 1);
    }

    public static int cantidadDeDiasDesdePrinciposDelAño(int dia, int mes, int año) {
        return cantidadDeDiasDesdePrinciposDelAño(new Fecha(dia, mes, año));
    }

    public static int cantidadDeDiasDesdePrinciposDelAño(Fecha f) {
        if (f.getMes() == 0) {
            return f.getDia();
        }
        int cantidadDeDias = 0;
        for (int i = 0; i < f.getMes(); i++) {
            cantidadDeDias += cantidadDeDiasMes(i, f.getAño());
        }
        //  System.out.println("cantidadDeDias="+cantidadDeDias);
        // System.out.println("cantidadDeDiasEntreMeses(0, f.getMes(), f.getAño())="+cantidadDeDiasEntreMeses(0, f.getMes(), f.getAño()));
        return cantidadDeDiasEntreMeses(0, f.getMes(), f.getAño()) + f.getDia();
        // return cantidadDeDias+ f.getDia() + 1;
    }

    /**
     * debuelbe la cantidad de dias entre los meses sin tener encuenta los del
     * ultimo
     *
     * @return int
     */
    public static int cantidadDeDiasEntreMeses(int mesA, int mesB) {
        return cantidadDeDiasEntreMeses(mesA, mesB, 1);
    }

    /**
     * debuelbe la cantidad de dias entre los meses sin tener encuenta los del
     * ultimo, utiliza el año para conocer si es bisiesto
     *
     * @return int
     */
    public static int cantidadDeDiasEntreMeses(int mesA, int mesB, int año) {
        if (mesA - mesB == 0) {
            return cantidadDeDiasAño(año);
        }
        if ((mesB - mesA) < 0) {
            return cantidadDeDiasHastaFinalesDelAño(mesA) + cantidadDeDiasDesdePrinciposDelAño(0, mesB, año);
        }

        int cantidadDeDias = 0;
        for (int i = mesA; i < mesB; i++) {
            cantidadDeDias += cantidadDeDiasMes(i, año);
        }
        return cantidadDeDias;
    }

    public static int cantidadDeDiasEntreAños(int añoA, int añoB) {
        int diferencia = añoB - añoA;
        if (diferencia < 1) {
            return 0;
        }
        int divicion = diferencia / 4;
        int resto = diferencia % 4;
        if (resto == 0) {
            return divicion * cantidadDeDiasEntre4Años();
        }
        int posicionBisiesto = -1;
        for (int i = 0; i < resto; i++) {
            if (esBisiesto(añoA + i)) {
                posicionBisiesto = i + 1;
                break;
            }
        }
//        System.out.println("divicion * cantidadDeDiasEntre4Años()="+(divicion * cantidadDeDiasEntre4Años()));
//        System.out.println("posicionBisiesto != -1 ? (resto - 1) * CANTIDAD_DIAS_AÑO + CANTIDAD_DIAS_AÑO_BISIESTO : resto * CANTIDAD_DIAS_AÑO="+(posicionBisiesto != -1 ? (resto - 1) * CANTIDAD_DIAS_AÑO + CANTIDAD_DIAS_AÑO_BISIESTO : resto * CANTIDAD_DIAS_AÑO));

        return (divicion * cantidadDeDiasEntre4Años()) + (posicionBisiesto != -1 ? (resto - 1) * CANTIDAD_DIAS_AÑO + CANTIDAD_DIAS_AÑO_BISIESTO : resto * CANTIDAD_DIAS_AÑO);

    }

    public static int cantidadDeDiasAño(int año) {
        return esBisiesto(año) ? 366 : 365;
    }

    public static String nombreDia(int posicion) {
        return nombresDia[posicion];
    }

    public static int posicionDia(String dia) {
        return Arrays.asList(nombresDia).indexOf(dia);
    }

    public static int cantidadDeDiasMes(int mes) {
        return cantidadDeDiasMes(mes, 1);
    }

    public static int cantidadDeDiasMes(String mes) {
        return cantidadDeDiasMes(mes, 1);
    }

    public static int cantidadDeDiasMes(String mes, int año) {
        return cantidadDeDiasMes(posicionMes(mes), año);
    }

    public static int cantidadDeDiasMes(int mes, int año) {
        final int cantidadDeDias[] = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        return mes == 1 ? esBisiesto(año) ? 29 : 28 : cantidadDeDias[mes];
    }

    public static String nombreMes(int posicion) {
        return nombresMes[posicion];
    }

    public static int posicionMes(String mes) {
        return Arrays.asList(nombresMes).indexOf(mes);
    }

    public static Fecha fechaDeString(String f) {
        return new Fecha(Integer.parseInt(f.substring(0, 2)) - 1, Integer.parseInt(f.substring(3, 5)) - 1, Integer.parseInt(f.substring(6)));
    }
}
