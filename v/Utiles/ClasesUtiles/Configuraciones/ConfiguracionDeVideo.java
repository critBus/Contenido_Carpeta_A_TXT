/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Configuraciones;

import java.util.Arrays;
import Utiles.ClasesUtiles.Interfases.Clonable;
import Utiles.ClasesUtiles.Multimedia.Paquete.Paquete;
import Utiles.ClasesUtiles.Tablas.Tabla;
import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.Arreglos;
import static Utiles.MetodosUtiles.Arreglos.copiaDeArreglo;
import static Utiles.MetodosUtiles.Arreglos.igualesA;
import Utiles.MetodosUtiles.MetodosUtiles;
import static Utiles.MetodosUtiles.MetodosUtiles.contieneStringAEnIndiceAArregloStringB;
import static Utiles.MetodosUtiles.MetodosUtiles.contieneStringAEnIndiceAArregloStringBInverso;
import static Utiles.MetodosUtiles.MetodosUtiles.inT;
import Utiles.MetodosUtiles.Videos;
import java.io.Serializable;

/**
 * Version 0.2
 *
 * @author Rene
 */
public class ConfiguracionDeVideo implements Serializable, Clonable<ConfiguracionDeVideo> {

    public boolean renombrarCarpetasInternas, incluirTemporada;
    public String antesIngnorar[], despuesIngnorar[], rodearIgnorar[][], union[], saltarAlPrincipio[], detenciones[], separacionString[], saltarHastaDespuesDe[], identificadoresTemporadas[], identificadoresCapitulo[], identificadoresCantidadCapituloTemporada[], terminacionesNumericas[][], noSaltarAlPrincipio[], detencionesAbsolutas[], invalidosPaquete[], saltarAntesNumero[][], saltarDespuesNumero[][];
    public int nivelesCarpetasInternas, cantidadAMover, cantidadASaltarO, cantidadAntesIngnorarO[], cantidadDespuesIngnorarO[], formato[];
    public char separacion[];
    public String Delimiters;

//    public ConfiguracionDeVideo() {
//    }
    public ConfiguracionDeVideo(boolean renombrarCarpetasInternas, boolean incluirTemporada, String[] antesIngnorar,
            String[] despuesIngnorar, String[][] rodearIgnorar, String[] union,
            String[] saltarAlPrincipio, String[] detenciones, int nivelesCarpetasInternas,
            int cantidadAMover, int cantidadASaltarO, int[] cantidadAntesIngnorarO,
            int[] cantidadDespuesIngnorarO, int[] formato, String separacionString[],
            String saltarHastaDespuesDe[], String identificadoresTemporadas[], String identificadoresCapitulo[],
            String identificadoresCantidadCapituloTemporada[], String terminacionesNumericas[][], String noSaltarAlPrincipio[],
            String detencionesAbsolutas[], String invalidosPaquete[], String saltarAntesNumero[][], String saltarDespuesNumero[][]) {
        this.renombrarCarpetasInternas = renombrarCarpetasInternas;
        this.antesIngnorar = antesIngnorar;
        this.despuesIngnorar = despuesIngnorar;
        this.rodearIgnorar = rodearIgnorar;
        this.union = union;
        this.saltarAlPrincipio = saltarAlPrincipio;
        this.detenciones = detenciones;
        this.nivelesCarpetasInternas = nivelesCarpetasInternas;
        this.cantidadAMover = cantidadAMover;
        this.cantidadASaltarO = cantidadASaltarO;
        this.cantidadAntesIngnorarO = cantidadAntesIngnorarO;
        this.cantidadDespuesIngnorarO = cantidadDespuesIngnorarO;
        this.formato = formato;
        this.separacionString = separacionString;
        this.saltarHastaDespuesDe = saltarHastaDespuesDe;
        this.separacion = obtenerSeparacion();
        this.identificadoresCantidadCapituloTemporada = identificadoresCantidadCapituloTemporada;
        this.identificadoresCapitulo = identificadoresCapitulo;
        this.identificadoresTemporadas = identificadoresTemporadas;
        this.terminacionesNumericas = terminacionesNumericas;
        this.noSaltarAlPrincipio = noSaltarAlPrincipio;
        this.detencionesAbsolutas = detencionesAbsolutas;
        this.Delimiters = obtenerDelimiters();
        this.invalidosPaquete = invalidosPaquete;
        this.saltarAntesNumero = saltarAntesNumero;
        this.saltarDespuesNumero = saltarDespuesNumero;
        this.incluirTemporada = incluirTemporada;
    }

    public enum tipoFormato {

        NINGUNO(new int[]{1, 1, 1, 1}), AUTOMATICO(new int[]{1, -1, 1, 1}), MANUAL(new int[]{-2, -2, -2, -2});
        private final int formatoPredeterminado[];

        private tipoFormato(int[] formatoPredeterminado) {
            this.formatoPredeterminado = formatoPredeterminado;
        }

        public int[] getFormatoPredeterminado() {
            return formatoPredeterminado;
        }

    };

    public void setFormato(tipoFormato t) {
        setFormato(t.getFormatoPredeterminado());
    }

    public tipoFormato getTipoDeFormatoActual() {
        if (igualesA(formato, tipoFormato.AUTOMATICO.getFormatoPredeterminado())) {
            return tipoFormato.AUTOMATICO;
        }
        if (igualesA(formato, tipoFormato.NINGUNO.getFormatoPredeterminado())) {
            return tipoFormato.NINGUNO;
        }
        return tipoFormato.MANUAL;
    }
//     public void establecerPredeterminado() {
//        T.establecerPredeterminado();
//        renombrarCarpetaInterna = true;
//        // formato = new int[]{1, -1, 1, 1};
//        formato = tipoFormato.AUTOMATICO.getFormatoPredeterminado();
//    }

    public void setArreglosString(String[] antesIngnorar,
            String[] despuesIngnorar, String[][] rodearIgnorar, String[] union,
            String[] saltarAlPrincipio, String[] detenciones,
            String separacionString[],
            String saltarHastaDespuesDe[], String identificadoresTemporadas[], String identificadoresCapitulo[], String identificadoresCantidadCapituloTemporada[], String noSaltarAlPrincipio[], String detencionesAbsolutas[], String[] invalidosPaquete, String saltarAntesNumero[][], String saltarDespuesNumero[][]) {
        this.antesIngnorar = antesIngnorar;
        this.despuesIngnorar = despuesIngnorar;
        this.rodearIgnorar = rodearIgnorar;
        this.union = union;
        this.saltarAlPrincipio = saltarAlPrincipio;
        this.detenciones = detenciones;
        this.separacionString = separacionString;
        this.saltarHastaDespuesDe = saltarHastaDespuesDe;
        this.separacion = obtenerSeparacion();
        this.identificadoresCantidadCapituloTemporada = identificadoresCantidadCapituloTemporada;
        this.identificadoresCapitulo = identificadoresCapitulo;
        this.identificadoresTemporadas = identificadoresTemporadas;
        this.noSaltarAlPrincipio = noSaltarAlPrincipio;
        this.detencionesAbsolutas = detencionesAbsolutas;
        this.Delimiters = obtenerDelimiters();
        this.invalidosPaquete = invalidosPaquete;
        this.saltarAntesNumero = saltarAntesNumero;
        this.saltarDespuesNumero = saltarDespuesNumero;
    }

    public char[] obtenerSeparacion() {
        char separacion[] = new char[separacionString.length];
        for (int i = 0; i < separacionString.length; i++) {
            separacion[i] = separacionString[i].isEmpty() ? ' ' : separacionString[i].charAt(0);
        }
        return separacion;
    }

    public String obtenerDelimiters() {
        String Delimiters = " \t\n\r\f";
        for (int i = 0; i < separacionString.length; i++) {
            Delimiters += separacion[i] != ' ' ? separacion[i] : "";
        }
        return Delimiters;
    }

    public String[] getSeparacionString() {
        return separacionString;
    }

    public void setSeparacionString(String[] separacionString) {
        this.separacionString = separacionString;
        this.separacion = obtenerSeparacion();
        this.Delimiters = obtenerDelimiters();
    }

    public String[] getSaltarHastaDespuesDe() {
        return saltarHastaDespuesDe;
    }

    public void setSaltarHastaDespuesDe(String[] saltarHastaDespuesDe) {
        this.saltarHastaDespuesDe = saltarHastaDespuesDe;
    }

    public String[] getIdentificadoresTemporadas() {
        return identificadoresTemporadas;
    }

    public void setIdentificadoresTemporadas(String[] identificadoresTemporadas) {
        this.identificadoresTemporadas = identificadoresTemporadas;
    }

    public String[] getIdentificadoresCapitulo() {
        return identificadoresCapitulo;
    }

    public void setIdentificadoresCapitulo(String[] identificadoresCapitulo) {
        this.identificadoresCapitulo = identificadoresCapitulo;
    }

    public String[] getInvalidosPaquete() {
        return invalidosPaquete;
    }

    public void setInvalidosPaquete(String[] invalidosPaquete) {
        this.invalidosPaquete = invalidosPaquete;
    }

    public String[] getIdentificadoresCantidadCapituloTemporada() {
        return identificadoresCantidadCapituloTemporada;
    }

    public void setIdentificadoresCantidadCapituloTemporada(String[] identificadoresCantidadCapituloTemporada) {
        this.identificadoresCantidadCapituloTemporada = identificadoresCantidadCapituloTemporada;
    }

    public String[][] getTerminacionesNumericas() {
        return terminacionesNumericas;
    }

    public void setTerminacionesNumericas(String[][] terminacionesNumericas) {
        this.terminacionesNumericas = terminacionesNumericas;
    }

    public String[] getNoSaltarAlPrincipio() {
        return noSaltarAlPrincipio;
    }

    public void setNoSaltarAlPrincipio(String[] noSaltarAlPrincipio) {
        this.noSaltarAlPrincipio = noSaltarAlPrincipio;
    }

    public String[] getDetencionesAbsolutas() {
        return detencionesAbsolutas;
    }

    public void setDetencionesAbsolutas(String[] detencionesAbsolutas) {
        this.detencionesAbsolutas = detencionesAbsolutas;
    }

    public char[] getSeparacion() {
        return separacion;
    }

//    public void setSeparacion(char[] separacion) {
//        this.separacion = separacion;
//    }
    public String getDelimiters() {
        return Delimiters;
    }

//    public void setDelimiters(String Delimiters) {
//        this.Delimiters = Delimiters;
//    }
    public boolean isRenombrarCarpetasInternas() {
        return renombrarCarpetasInternas;
    }

    public void setRenombrarCarpetasInternas(boolean renombrarCarpetasInternas) {
        this.renombrarCarpetasInternas = renombrarCarpetasInternas;
    }

    public String[] getAntesIngnorar() {
        return antesIngnorar;
    }

    public void setAntesIngnorar(String[] antesIngnorar) {
        this.antesIngnorar = antesIngnorar;
    }

    public String[] getDespuesIngnorar() {
        return despuesIngnorar;
    }

    public void setDespuesIngnorar(String[] despuesIngnorar) {
        this.despuesIngnorar = despuesIngnorar;
    }

    public String[][] getRodearIgnorar() {
        return rodearIgnorar;
    }

    public void setRodearIgnorar(String[][] rodearIgnorar) {
        this.rodearIgnorar = rodearIgnorar;
    }

    public String[] getUnion() {
        return union;
    }

    public void setUnion(String[] union) {
        this.union = union;
    }

    public String[] getSaltarAlPrincipio() {
        return saltarAlPrincipio;
    }

    public void setSaltarAlPrincipio(String[] saltarAlPrincipio) {
        this.saltarAlPrincipio = saltarAlPrincipio;
    }

    public String[] getDetenciones() {
        return detenciones;
    }

    public void setDetenciones(String[] detenciones) {
        this.detenciones = detenciones;
    }

    public int getNivelesCarpetasInternas() {
        return nivelesCarpetasInternas;
    }

    public void setNivelesCarpetasInternas(int nivelesCarpetasInternas) {
        this.nivelesCarpetasInternas = nivelesCarpetasInternas;
    }

    public int getCantidadAMover() {
        return cantidadAMover;
    }

    public void setCantidadAMover(int cantidadAMover) {
        this.cantidadAMover = cantidadAMover;
    }

    public int getCantidadASaltarO() {
        return cantidadASaltarO;
    }

    public void setCantidadASaltarO(int cantidadASaltarO) {
        this.cantidadASaltarO = cantidadASaltarO;
    }

    public int[] getCantidadAntesIngnorarO() {
//        if (cantidadAntesIngnorarO == null ) {
//            int[] cantidadAntesIngnorar = new int[antesIngnorar.length];
//            Arrays.fill(cantidadAntesIngnorar, 100);
//        }
        return cantidadAntesIngnorarO;
    }

    public void setCantidadAntesIngnorarO(int[] cantidadAntesIngnorarO) {
        this.cantidadAntesIngnorarO = cantidadAntesIngnorarO;
    }

    public int[] getCantidadDespuesIngnorarO() {
//        if ( cantidadDespuesIngnorarO == null) {
//            int[] cantidadDespuesIngnorar = new int[despuesIngnorar.length];
//            Arrays.fill(cantidadDespuesIngnorar, 100);
//        }
        return cantidadDespuesIngnorarO;
    }

    public void setCantidadDespuesIngnorarO(int[] cantidadDespuesIngnorarO) {
        this.cantidadDespuesIngnorarO = cantidadDespuesIngnorarO;
    }

    public int[] getFormato() {
        return formato;
    }

    public void setFormato(int[] formato) {
        this.formato = formato;
    }

    public int addSaltarAntes(int numero) {
        int indice = indiceAddSaltar(saltarAntesNumero, numero);
        if (indice >= 0) {
            saltarAntesNumero = Arreglos.añadirEnElIndice(saltarAntesNumero, new String[]{numero + ""}, indice);
        }
        return indice;
    }

    public int addSaltarDespues(int numero) {
        int indice = indiceAddSaltar(saltarDespuesNumero, numero);
        if (indice >= 0) {
            saltarDespuesNumero = Arreglos.añadirEnElIndice(saltarDespuesNumero, new String[]{numero + ""}, indice);
        }

        return indice;
//        for (int i = 0; i < saltarDespuesNumero.length; i++) {
//            if (numero <= inT(saltarDespuesNumero[i][0])) {
//                return i;
//            }
//        }
//        return saltarDespuesNumero.length;
    }

    private int indiceAddSaltar(String[][] A, int numero) {
        for (int i = 0; i < A.length; i++) {
            if (numero == inT(A[i][0])) {
                return -1 * i;
            }
            if (numero < inT(A[i][0])) {
                return i;
            }
        }
        return A.length;
    }

    public boolean saltarAntes(String a, int indice, double[] numero) {
        String n = (int) numero[0] + "";
        if (saltarAntesNumero.length != 0) {
//                && inT(saltarAntesNumero[saltarAntesNumero.length - 1][0]) <= numero[0]) {
            int ultimo = inT(saltarAntesNumero[saltarAntesNumero.length - 1][0]);
            if (ultimo == numero[0]) {
//                for (int i = 1; i < saltarAntesNumero[saltarAntesNumero.length - 1].length; i++) {
                int indiceAnterior = saltarAntesNumero.length > 0 ? contieneStringAEnIndiceAArregloStringBInverso(indice, a, 1, saltarAntesNumero[saltarAntesNumero.length - 1]) : -1;
                return (!(indiceAnterior < 0));
//                    if ((!(indiceAnterior < 0))) {
//                        return true;
//                    }
//                }
//                return false;
            }
            if (ultimo > numero[0]) {
//                For1:
                for (int i = 0; i < saltarAntesNumero.length - 1; i++) {
//                    for (int j = 0; j < saltarAntesNumero[i].length; j++) {
//                        if (j == 0) {
                    if (!n.equals(saltarAntesNumero[i][0])) {
                        if (inT(saltarAntesNumero[i][0]) > numero[0]) {
                            return false;
                        }
                        continue;
                    }
//                        } else {
                    int indiceAnterior = saltarAntesNumero.length > 0 ? contieneStringAEnIndiceAArregloStringBInverso(indice, a, 1, saltarAntesNumero[i]) : -1;
//                            if ((!(indiceAnterior < 0))) {
//                                return true;
//                            }
                    return (!(indiceAnterior < 0));
//                            if (j == saltarAntesNumero[i].length - 1) {
//                                return false;
//                            }

//                        }
//                    }
                }

            }
        }

        return false;
    }

    public int[] saltarDespues(String a, int indice, double[] numero) {
        return saltarDespues(a, indice, numero[0]);
    }

    /**
     * debuelbe {indice fila A[],indice columna A[][], i a continuacion}
     *
     * @param a
     * @param indice
     * @param numero
     * @return
     */
    public int[] saltarDespues(String a, int indice, double numero) {
        int falso[] = {-1, -1, -1};
        String n = (int) numero + "";
//        System.out.println("a.charAt(indice)="+a.charAt(indice));
        if (saltarDespuesNumero.length != 0) {
            int ultimo = inT(saltarDespuesNumero[saltarDespuesNumero.length - 1][0]);
//            System.out.println("n="+n);
            if (ultimo == numero) {
                int indiceDespues = saltarDespuesNumero.length > 0 ? contieneStringAEnIndiceAArregloStringB(false, indice, a,0, 1, saltarDespuesNumero[saltarDespuesNumero.length - 1]) : -1;
                if ((!(indiceDespues < 0))) {
                    return new int[]{saltarDespuesNumero.length - 1, indiceDespues, indice += saltarDespuesNumero[saltarDespuesNumero.length - 1][indiceDespues].length()};
                }
                return falso;
//                return (!(indiceAnterior < 0));
            }
            if (ultimo > numero) {
                for (int i = 0; i < saltarDespuesNumero.length - 1; i++) {
//                    System.out.println("saltarDespuesNumero[i][0]="+saltarDespuesNumero[i][0]);
                    if (!n.equals(saltarDespuesNumero[i][0])) {
                        if (inT(saltarDespuesNumero[i][0]) > numero) {
                            return falso;
                        }
                        continue;
                    }
                    int indiceDespues = saltarDespuesNumero.length > 0 ? contieneStringAEnIndiceAArregloStringB(false, indice, a, 0,1, saltarDespuesNumero[i]) : -1;
//                    return (!(indiceAnterior < 0));
                    if ((!(indiceDespues < 0))) {
                        return new int[]{saltarDespuesNumero.length - 1, indiceDespues, indice += saltarDespuesNumero[saltarDespuesNumero.length - 1][indiceDespues].length()};
                    }
                    return falso;
                }
            }
        }

//        int indiceDespues = cdv.despuesIngnorar.length > 0 && (i + (int) numero[1]) < nombre.length() ? contieneStringAEnIndiceAArregloStringB(i + (int) numero[1], nombre, cdv.despuesIngnorar) : -1;
//        if ((!(indiceDespues < 0))) {
//            i += cdv.despuesIngnorar[indiceDespues].length() + (int) numero[1] - 1;
//            continue;
//        }
        return falso;
    }
//    private boolean saltar(String a,int indice,int []numero,String A[][]){}

    @Override
    public ConfiguracionDeVideo copia() {
        return new ConfiguracionDeVideo(renombrarCarpetasInternas, incluirTemporada, copiaDeArreglo(antesIngnorar), copiaDeArreglo(despuesIngnorar), copiaDeArreglo(rodearIgnorar), copiaDeArreglo(union), copiaDeArreglo(saltarAlPrincipio), copiaDeArreglo(detenciones), nivelesCarpetasInternas, cantidadAMover, cantidadASaltarO, copiaDeArreglo(cantidadAntesIngnorarO), copiaDeArreglo(cantidadDespuesIngnorarO), copiaDeArreglo(formato), copiaDeArreglo(separacionString), copiaDeArreglo(saltarHastaDespuesDe), copiaDeArreglo(identificadoresTemporadas), copiaDeArreglo(identificadoresCapitulo), copiaDeArreglo(identificadoresCantidadCapituloTemporada), copiaDeArreglo(terminacionesNumericas), copiaDeArreglo(noSaltarAlPrincipio), copiaDeArreglo(detencionesAbsolutas), copiaDeArreglo(invalidosPaquete), copiaDeArreglo(saltarAntesNumero), copiaDeArreglo(saltarDespuesNumero));
    }

    public ConfiguracionDeVideo copiaFormatoDiferente(int[] formato) {
        ConfiguracionDeVideo copia = copia();
        copia.setFormato(formato);
        return copia;
    }

    public ConfiguracionDeVideo referenciaFormatoDiferente(int[] formato) {
        return new ConfiguracionDeVideo(renombrarCarpetasInternas, incluirTemporada, antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, nivelesCarpetasInternas, cantidadAMover, cantidadASaltarO, cantidadAntesIngnorarO, cantidadDespuesIngnorarO, formato, separacionString, saltarHastaDespuesDe, identificadoresTemporadas, identificadoresCapitulo, identificadoresCantidadCapituloTemporada, terminacionesNumericas, noSaltarAlPrincipio, detencionesAbsolutas, invalidosPaquete, saltarAntesNumero, saltarDespuesNumero);
    }

    public ConfiguracionDeVideo referenciaNivelesCarpetasInternasDiferente(int nivelesCarpetasInternas) {
        return new ConfiguracionDeVideo(renombrarCarpetasInternas, incluirTemporada, antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, nivelesCarpetasInternas, cantidadAMover, cantidadASaltarO, cantidadAntesIngnorarO, cantidadDespuesIngnorarO, formato, separacionString, saltarHastaDespuesDe, identificadoresTemporadas, identificadoresCapitulo, identificadoresCantidadCapituloTemporada, terminacionesNumericas, noSaltarAlPrincipio, detencionesAbsolutas, invalidosPaquete, saltarAntesNumero, saltarDespuesNumero);
    }

    public ConfiguracionDeVideo referenciaDecrementoNivelesCarpetasInternas() {
        return referenciaNivelesCarpetasInternasDiferente(nivelesCarpetasInternas - 1);
    }

    public static ConfiguracionDeVideo getDefault() {
//        return new ConfiguracionDeVideo(getRenombrarCarpetasInternasDefault(), Videos.saltarAntes, Videos.saltarDespues, Videos.rodearIgnorar, Videos.union, Videos.saltarAlPrincipio, Videos.detenciones, getNivelesCarpetasInternasDefault(), getCantidadAMoverDefault(), getCantidadASaltarDefault(), getCantidadAntesIngnorarODefault(), getCantidadDespuesIngnorarODefault(), new int[]{1, 1, 1, 1}, Archivo.separacionString, Archivo.saltarHastaDespuesDe, Videos.identificadoresTemporadas, Videos.identificadoresCapitulo, Videos.identificadoresCantidadCapituloTemporada, MetodosUtiles.terminacionesNumericas, Videos.noSaltarAlPrincipio, Videos.detencionesAbsolutas);
        return new ConfiguracionDeVideo(getRenombrarCarpetasInternasDefault(), getIncluirTemporasDefault(), Videos.saltarAntes, Videos.saltarDespues, Videos.rodearIgnorar, Videos.union, Videos.saltarAlPrincipio, Videos.detenciones, getNivelesCarpetasInternasDefault(), getCantidadAMoverDefault(), getCantidadASaltarDefault(), getCantidadAntesIngnorarODefault(), getCantidadDespuesIngnorarODefault(), tipoFormato.AUTOMATICO.getFormatoPredeterminado(), Archivo.separacionString, Archivo.saltarHastaDespuesDe, Videos.identificadoresTemporadas, Videos.identificadoresCapitulo, Videos.identificadoresCantidadCapituloTemporada, MetodosUtiles.terminacionesNumericas, Videos.noSaltarAlPrincipio, Videos.detencionesAbsolutas, Paquete.invalidosPaquete, Videos.saltarAntesNumero, Videos.saltarDespuesNumero);
    }

    public static ConfiguracionDeVideo getDefaultDiferenteEnString(String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String union[], String saltarAlPrincipio[], String detenciones[], String separacionString[], String saltarHastaDespuesDe[], String identificadoresTemporadas[], String identificadoresCapitulo[], String identificadoresCantidadCapituloTemporada[], String terminacionesNumericas[][], String noSaltarAlPrincipio[], String detencionesAbsolutas[]) {
        return new ConfiguracionDeVideo(getRenombrarCarpetasInternasDefault(), getIncluirTemporasDefault(), Videos.saltarAntes, Videos.saltarDespues, Videos.rodearIgnorar, Videos.union, Videos.saltarAlPrincipio, Videos.detenciones, getNivelesCarpetasInternasDefault(), getCantidadAMoverDefault(), getCantidadASaltarDefault(), getCantidadAntesIngnorarODefault(), getCantidadDespuesIngnorarODefault(), tipoFormato.AUTOMATICO.getFormatoPredeterminado(), Archivo.separacionString, Archivo.saltarHastaDespuesDe, Videos.identificadoresTemporadas, Videos.identificadoresCapitulo, Videos.identificadoresCantidadCapituloTemporada, MetodosUtiles.terminacionesNumericas, Videos.noSaltarAlPrincipio, Videos.detencionesAbsolutas, Paquete.invalidosPaquete, Videos.saltarAntesNumero, Videos.saltarDespuesNumero);
    }

    public static ConfiguracionDeVideo getDefaultDiferenteEnCantidadAMover(int cantidadAMover) {
        return new ConfiguracionDeVideo(getRenombrarCarpetasInternasDefault(), getIncluirTemporasDefault(), Videos.saltarAntes, Videos.saltarDespues, Videos.rodearIgnorar, Videos.union, Videos.saltarAlPrincipio, Videos.detenciones, getNivelesCarpetasInternasDefault(), cantidadAMover, getCantidadASaltarDefault(), getCantidadAntesIngnorarODefault(), getCantidadDespuesIngnorarODefault(), tipoFormato.AUTOMATICO.getFormatoPredeterminado(), Archivo.separacionString, Archivo.saltarHastaDespuesDe, Videos.identificadoresTemporadas, Videos.identificadoresCapitulo, Videos.identificadoresCantidadCapituloTemporada, MetodosUtiles.terminacionesNumericas, Videos.noSaltarAlPrincipio, Videos.detencionesAbsolutas, Paquete.invalidosPaquete, Videos.saltarAntesNumero, Videos.saltarDespuesNumero);
    }

    public static ConfiguracionDeVideo getDefaultDiferenteEnRenombrarCarpetasInternas(boolean carpetasInternas) {
        return new ConfiguracionDeVideo(carpetasInternas, getIncluirTemporasDefault(), Videos.saltarAntes, Videos.saltarDespues, Videos.rodearIgnorar, Videos.union, Videos.saltarAlPrincipio, Videos.detenciones, getNivelesCarpetasInternasDefault(), getCantidadAMoverDefault(), getCantidadASaltarDefault(), getCantidadAntesIngnorarODefault(), getCantidadDespuesIngnorarODefault(), tipoFormato.AUTOMATICO.getFormatoPredeterminado(), Archivo.separacionString, Archivo.saltarHastaDespuesDe, Videos.identificadoresTemporadas, Videos.identificadoresCapitulo, Videos.identificadoresCantidadCapituloTemporada, MetodosUtiles.terminacionesNumericas, Videos.noSaltarAlPrincipio, Videos.detencionesAbsolutas, Paquete.invalidosPaquete, Videos.saltarAntesNumero, Videos.saltarDespuesNumero);
    }

    public boolean isIncluirTemporada() {
        return incluirTemporada;
    }

    public void setIncluirTemporada(boolean incluirTemporada) {
        this.incluirTemporada = incluirTemporada;
    }

    public String[][] getSaltarAntesNumero() {
        return saltarAntesNumero;
    }

    public void setSaltarAntesNumero(String[][] saltarAntesNumero) {
        this.saltarAntesNumero = saltarAntesNumero;
    }

    public String[][] getSaltarDespuesNumero() {
        return saltarDespuesNumero;
    }

    public void setSaltarDespuesNumero(String[][] saltarDespuesNumero) {
        this.saltarDespuesNumero = saltarDespuesNumero;
    }

    public static boolean getIncluirTemporasDefault() {
        return false;
    }

    public static boolean getRenombrarCarpetasInternasDefault() {
        return true;
    }

    public static int getNivelesCarpetasInternasDefault() {
        return 1;
    }

    public static int getCantidadAMoverDefault() {
        return 5;
    }

    public static int getCantidadASaltarDefault() {
        return 0;
    }

    public static int[] getCantidadDespuesIngnorarODefault() {
        int[] cantidadDespuesIngnorar = new int[Videos.saltarDespues.length];
        Arrays.fill(cantidadDespuesIngnorar, 100);
        return cantidadDespuesIngnorar;
    }

    public static int[] getCantidadAntesIngnorarODefault() {
        int[] cantidadAntesIngnorar = new int[Videos.saltarAntes.length];
        Arrays.fill(cantidadAntesIngnorar, 100);
        return cantidadAntesIngnorar;
    }

}
