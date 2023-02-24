/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Configuraciones.Ventanas;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Interfases.Clonable;
import Utiles.ClasesUtiles.Tablas.Tabla;
import Utiles.MetodosUtiles.Arreglos;
import static Utiles.MetodosUtiles.Arreglos.copiaDeArreglo;
import java.io.Serializable;
import java.util.LinkedList;

/**
 * Version 0.2
 *
 * @author Rene
 */
public class TablasAmpliableConfiguracionDeVideo implements Serializable, Clonable<TablasAmpliableConfiguracionDeVideo> {

    boolean antesIngnorar[], despuesIngnorar[], rodearIgnorar[], union[], saltarAlPrincipio[], detenciones[], separacionString[], saltarHastaDespuesDe[], identificadoresTemporadas[], identificadoresCapitulo[], identificadoresCantidadCapituloTemporada[], noSaltarAlPrincipio[], detencionesAbsolutas[], invalidosPaquete[], saltarAntesNumero[][], saltarDespuesNumero[][];
    ;
// boolean antesIngnorar[];
    ConfiguracionDeVideo cdv;
//                                                                                                                                                                                                                                                                                                            ini(cdv.)

    public TablasAmpliableConfiguracionDeVideo() {
        this(ConfiguracionDeVideo.getDefault());
    }

    public TablasAmpliableConfiguracionDeVideo(ConfiguracionDeVideo cdv) {
      //  this(ini(cdv.getAntesIngnorar()), cdv);//new boolean[]{}

        // this(antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, separacionString, saltarHastaDespuesDe, identificadoresTemporadas, identificadoresCapitulo, identificadoresCantidadCapituloTemporada, terminacionesNumericas, noSaltarAlPrincipio, detencionesAbsolutas, cdv);
        this(ini(cdv.antesIngnorar), ini(cdv.despuesIngnorar), ini(cdv.rodearIgnorar), ini(cdv.union), ini(cdv.saltarAlPrincipio), ini(cdv.detenciones), ini(cdv.separacionString), ini(cdv.saltarHastaDespuesDe), ini(cdv.identificadoresTemporadas), ini(cdv.identificadoresCapitulo), ini(cdv.identificadoresCantidadCapituloTemporada), ini(cdv.noSaltarAlPrincipio), ini(cdv.detencionesAbsolutas), ini(cdv.invalidosPaquete), iniMenos0(cdv.saltarAntesNumero), iniMenos0(cdv.saltarDespuesNumero), cdv);
    }

    public TablasAmpliableConfiguracionDeVideo(boolean[] antesIngnorar, boolean[] despuesIngnorar, boolean[] rodearIgnorar, boolean[] union, boolean[] saltarAlPrincipio, boolean[] detenciones, boolean[] separacionString, boolean[] saltarHastaDespuesDe, boolean[] identificadoresTemporadas, boolean[] identificadoresCapitulo, boolean[] identificadoresCantidadCapituloTemporada, boolean[] noSaltarAlPrincipio, boolean[] detencionesAbsolutas, boolean invalidosPaquete[], boolean saltarAntesNumero[][], boolean saltarDespuesNumero[][], ConfiguracionDeVideo cdv) {
//        this.antesIngnorar = antesIngnorar;
//        this.despuesIngnorar = despuesIngnorar;
//        this.rodearIgnorar = rodearIgnorar;
//        this.union = union;
//        this.saltarAlPrincipio = saltarAlPrincipio;
//        this.detenciones = detenciones;
//        this.separacionString = separacionString;
//        this.saltarHastaDespuesDe = saltarHastaDespuesDe;
//        this.identificadoresTemporadas = identificadoresTemporadas;
//        this.identificadoresCapitulo = identificadoresCapitulo;
//        this.identificadoresCantidadCapituloTemporada = identificadoresCantidadCapituloTemporada;
//        this.terminacionesNumericas = terminacionesNumericas;
//        this.noSaltarAlPrincipio = noSaltarAlPrincipio;
//        this.detencionesAbsolutas = detencionesAbsolutas;
//        this.cdv = cdv;
        inicializar(antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, separacionString, saltarHastaDespuesDe, identificadoresTemporadas, identificadoresCapitulo, identificadoresCantidadCapituloTemporada, noSaltarAlPrincipio, detencionesAbsolutas, invalidosPaquete, saltarAntesNumero, saltarDespuesNumero, cdv);
    }

    private void inicializar(boolean[] antesIngnorar, boolean[] despuesIngnorar, boolean[] rodearIgnorar, boolean[] union, boolean[] saltarAlPrincipio, boolean[] detenciones, boolean[] separacionString, boolean[] saltarHastaDespuesDe, boolean[] identificadoresTemporadas, boolean[] identificadoresCapitulo, boolean[] identificadoresCantidadCapituloTemporada, boolean[] noSaltarAlPrincipio, boolean[] detencionesAbsolutas, boolean invalidosPaquete[], boolean saltarAntesNumero[][], boolean saltarDespuesNumero[][], ConfiguracionDeVideo cdv) {
        this.antesIngnorar = antesIngnorar;
        this.despuesIngnorar = despuesIngnorar;
        this.rodearIgnorar = rodearIgnorar;
        this.union = union;
        this.saltarAlPrincipio = saltarAlPrincipio;
        this.detenciones = detenciones;
        this.separacionString = separacionString;
        this.saltarHastaDespuesDe = saltarHastaDespuesDe;
        this.identificadoresTemporadas = identificadoresTemporadas;
        this.identificadoresCapitulo = identificadoresCapitulo;
        this.identificadoresCantidadCapituloTemporada = identificadoresCantidadCapituloTemporada;
//        this.terminacionesNumericas = terminacionesNumericas;
        this.noSaltarAlPrincipio = noSaltarAlPrincipio;
        this.detencionesAbsolutas = detencionesAbsolutas;
        this.invalidosPaquete = invalidosPaquete;
        this.saltarAntesNumero = saltarAntesNumero;
        this.saltarDespuesNumero = saltarDespuesNumero;
        this.cdv = cdv;
    }

    private static boolean[] ini(String A[]) {
        return Arreglos.arregloFill(true, A.length);
    }

    private static boolean[] ini(String A[][]) {
//        System.out.println("A.length="+A.length);
        return Arreglos.arregloFill(true, A.length);
    }

    private static boolean[][] iniMenos0(String A[][]) {
//        System.out.println("A.length="+A.length);

        boolean B[][] = new boolean[A.length][];
        for (int i = 0; i < A.length; i++) {
            B[i] = Arreglos.arregloFill(true, A[i].length == 0 ? 0 : A[i].length - 1);
        }
        return B;
    }

    public ConfiguracionDeVideo getCdvSelecionado() {
//        try {
        ConfiguracionDeVideo cdv2 = cdv.copia();
        cdv2.setArreglosString(getStringSelecionado2A(cdv.antesIngnorar, antesIngnorar), getStringSelecionado2A(cdv.despuesIngnorar, despuesIngnorar), getStringSelecionado3A(cdv.rodearIgnorar, rodearIgnorar),
                getStringSelecionado2A(cdv.union, union), getStringSelecionado2A(cdv.saltarAlPrincipio, saltarAlPrincipio), getStringSelecionado2A(cdv.detenciones, detenciones),
                getStringSelecionado2A(cdv.separacionString, separacionString), getStringSelecionado2A(cdv.saltarHastaDespuesDe, saltarHastaDespuesDe),
                getStringSelecionado2A(cdv.identificadoresTemporadas, identificadoresTemporadas), getStringSelecionado2A(cdv.identificadoresCapitulo, identificadoresCapitulo),
                getStringSelecionado2A(cdv.identificadoresCantidadCapituloTemporada, identificadoresCantidadCapituloTemporada),
                getStringSelecionado2A(cdv.noSaltarAlPrincipio, noSaltarAlPrincipio),
                getStringSelecionado2A(cdv.detencionesAbsolutas, detencionesAbsolutas),
                getStringSelecionado2A(cdv.invalidosPaquete, invalidosPaquete),
                getStringSelecionado3AMenos0(cdv.saltarAntesNumero, saltarAntesNumero),
                getStringSelecionado3AMenos0(cdv.saltarDespuesNumero, saltarDespuesNumero)
        );
        return cdv2;
//            return ConfiguracionDeVideo.getDefaultDiferenteEnString(getStringSelecionado2A(cdv.antesIngnorar, antesIngnorar), getStringSelecionado2A(cdv.despuesIngnorar, despuesIngnorar), getStringSelecionado3A(cdv.rodearIgnorar, rodearIgnorar),
//                    getStringSelecionado2A(cdv.union, union), getStringSelecionado2A(cdv.saltarAlPrincipio, saltarAlPrincipio), getStringSelecionado2A(cdv.detenciones, detenciones),
//                    getStringSelecionado2A(cdv.separacionString, separacionString), getStringSelecionado2A(cdv.saltarHastaDespuesDe, saltarHastaDespuesDe),
//                    getStringSelecionado2A(cdv.identificadoresTemporadas, identificadoresTemporadas), getStringSelecionado2A(cdv.identificadoresCapitulo, identificadoresCapitulo),
//                    getStringSelecionado2A(cdv.identificadoresCantidadCapituloTemporada, identificadoresCantidadCapituloTemporada),
//                    cdv.terminacionesNumericas, getStringSelecionado2A(cdv.noSaltarAlPrincipio, noSaltarAlPrincipio),
//                    getStringSelecionado2A(cdv.detencionesAbsolutas, detencionesAbsolutas));
//        } catch (Exception e) {
//            System.out.println("eeeee");
//            e.printStackTrace();
//        }
//        return null;
    }

    public boolean[] getInvalidosPaquete() {
        return invalidosPaquete;
    }

    public void setInvalidosPaquete(boolean[] invalidosPaquete) {
        this.invalidosPaquete = invalidosPaquete;
    }

    private String[] getStringSelecionado2A(String A[], boolean B[]) {
        LinkedList<String> a = new LinkedList<String>();
//        try {
        for (int i = 0; i < B.length; i++) {
            if (B[i]) {
                a.add(A[i]);
            }
        }
//        } catch (Exception e) {
//            System.out.println("bbbbbb");
//            e.printStackTrace();
//        }
        return a.toArray(new String[]{});
    }

    private String[][] getStringSelecionado3A(String A[][], boolean B[]) {
        LinkedList<String[]> a = new LinkedList<String[]>();
//        try {
//            System.out.println("");
        for (int i = 0; i < B.length; i++) {
            if (B[i]) {
                a.add(new String[]{A[i][0], A[i][1]});
            }
        }
//            System.out.println("termi");
//        } catch (Exception e) {
//            System.out.println("ccccccc");
//            e.printStackTrace();
//        }
        return a.toArray(new String[][]{});
    }

    private String[][] getStringSelecionado3AMenos0(String A[][], boolean B[][]) {
        LinkedList<String[]> a = new LinkedList<String[]>();

        for (int i = 0; i < B.length; i++) {
            LinkedList<String> l = new LinkedList<String>();
            l.add(A[i][0]);
            for (int j = 0; j < B[i].length; j++) {
                if (B[i][j]) {
                    l.add(A[i][j + 1]);
                }
            }
            if (l.size() > 1) {
                a.add(l.toArray(new String[]{}));
            }
        }

        return a.toArray(new String[][]{});
    }
//    private static boolean[] ini(String[][] A) {
//        return Arreglos.arregloFill(true, A.length);
//    }

    public void actualizar(String A[], boolean B[], String a) {
        A = Arreglos.ampliarArregloObject(A, 1);
        B = Arreglos.ampliarArreglo(B, 1);
        A[A.length - 1] = a;
        B[B.length - 1] = true;
    }

    public void actualizar(String A[][], boolean B[], String a, String b) {
        A = Arreglos.ampliarArregloObject(A, 1);
        B = Arreglos.ampliarArreglo(B, 1);
        A[A.length - 1][0] = a;
        A[A.length - 1][1] = b;
        B[B.length - 1] = true;
    }

    public void establecerPredeterminado() {
        cdv = ConfiguracionDeVideo.getDefault();
        inicializar(ini(cdv.antesIngnorar), ini(cdv.despuesIngnorar), ini(cdv.rodearIgnorar), ini(cdv.union), ini(cdv.saltarAlPrincipio), ini(cdv.detenciones), ini(cdv.separacionString), ini(cdv.saltarHastaDespuesDe), ini(cdv.identificadoresTemporadas), ini(cdv.identificadoresCapitulo), ini(cdv.identificadoresCantidadCapituloTemporada), ini(cdv.noSaltarAlPrincipio), ini(cdv.detencionesAbsolutas), ini(cdv.invalidosPaquete), iniMenos0(cdv.saltarAntesNumero), iniMenos0(cdv.saltarDespuesNumero), cdv);
    }

    public ConfiguracionDeVideo getCdv() {
        return cdv;
    }

    public void setCdv(ConfiguracionDeVideo cdv) {
        this.cdv = cdv;
    }

    public boolean[] getAntesIngnorar() {
        return antesIngnorar;
    }

    public void setAntesIngnorar(boolean[] antesIngnorar) {
        this.antesIngnorar = antesIngnorar;
    }

    public boolean[] getDespuesIngnorar() {
        return despuesIngnorar;
    }

    public void setDespuesIngnorar(boolean[] despuesIngnorar) {
        this.despuesIngnorar = despuesIngnorar;
    }

    public boolean[] getRodearIgnorar() {
        return rodearIgnorar;
    }

    public void setRodearIgnorar(boolean[] rodearIgnorar) {
        this.rodearIgnorar = rodearIgnorar;
    }

    public boolean[] getUnion() {
        return union;
    }

    public void setUnion(boolean[] union) {
        this.union = union;
    }

    public boolean[] getSaltarAlPrincipio() {
        return saltarAlPrincipio;
    }

    public void setSaltarAlPrincipio(boolean[] saltarAlPrincipio) {
        this.saltarAlPrincipio = saltarAlPrincipio;
    }

    public boolean[] getDetenciones() {
        return detenciones;
    }

    public void setDetenciones(boolean[] detenciones) {
        this.detenciones = detenciones;
    }

    public boolean[] getSeparacionString() {
        return separacionString;
    }

    public void setSeparacionString(boolean[] separacionString) {
        this.separacionString = separacionString;
    }

    public boolean[] getSaltarHastaDespuesDe() {
        return saltarHastaDespuesDe;
    }

    public void setSaltarHastaDespuesDe(boolean[] saltarHastaDespuesDe) {
        this.saltarHastaDespuesDe = saltarHastaDespuesDe;
    }

    public boolean[] getIdentificadoresTemporadas() {
        return identificadoresTemporadas;
    }

    public void setIdentificadoresTemporadas(boolean[] identificadoresTemporadas) {
        this.identificadoresTemporadas = identificadoresTemporadas;
    }

    public boolean[] getIdentificadoresCapitulo() {
        return identificadoresCapitulo;
    }

    public void setIdentificadoresCapitulo(boolean[] identificadoresCapitulo) {
        this.identificadoresCapitulo = identificadoresCapitulo;
    }

    public boolean[] getIdentificadoresCantidadCapituloTemporada() {
        return identificadoresCantidadCapituloTemporada;
    }

    public void setIdentificadoresCantidadCapituloTemporada(boolean[] identificadoresCantidadCapituloTemporada) {
        this.identificadoresCantidadCapituloTemporada = identificadoresCantidadCapituloTemporada;
    }

//    public boolean[] getTerminacionesNumericas() {
//        return terminacionesNumericas;
//    }
//
//    public void setTerminacionesNumericas(boolean[] terminacionesNumericas) {
//        this.terminacionesNumericas = terminacionesNumericas;
//    }
    public boolean[] getNoSaltarAlPrincipio() {
        return noSaltarAlPrincipio;
    }

    public void setNoSaltarAlPrincipio(boolean[] noSaltarAlPrincipio) {
        this.noSaltarAlPrincipio = noSaltarAlPrincipio;
    }

    public boolean[] getDetencionesAbsolutas() {
        return detencionesAbsolutas;
    }

    public void setDetencionesAbsolutas(boolean[] detencionesAbsolutas) {
        this.detencionesAbsolutas = detencionesAbsolutas;
    }

    public boolean[][] getSaltarAntesNumero() {
        return saltarAntesNumero;
    }

    public void setSaltarAntesNumero(boolean[][] saltarAntesNumero) {
        this.saltarAntesNumero = saltarAntesNumero;
    }

    public boolean[][] getSaltarDespuesNumero() {
        return saltarDespuesNumero;
    }

    public void setSaltarDespuesNumero(boolean[][] saltarDespuesNumero) {
        this.saltarDespuesNumero = saltarDespuesNumero;
    }

    @Override
    public TablasAmpliableConfiguracionDeVideo copia() {
//               new TablasAmpliableConfiguracionDeVideo(                antesIngnorar,                 despuesIngnorar,                 rodearIgnorar,               union,                    saltarAlPrincipio,                 detenciones,                separacionString,                saltarHastaDespuesDe,                   identificadoresTemporadas,               identificadoresCapitulo,                 identificadoresCantidadCapituloTemporada,             noSaltarAlPrincipio,                   detencionesAbsolutas, cdv);
        return new TablasAmpliableConfiguracionDeVideo(copiaDeArreglo(antesIngnorar), copiaDeArreglo(despuesIngnorar), copiaDeArreglo(rodearIgnorar), copiaDeArreglo(union), copiaDeArreglo(saltarAlPrincipio), copiaDeArreglo(detenciones), copiaDeArreglo(separacionString), copiaDeArreglo(saltarHastaDespuesDe), copiaDeArreglo(identificadoresTemporadas), copiaDeArreglo(identificadoresCapitulo), copiaDeArreglo(identificadoresCantidadCapituloTemporada), copiaDeArreglo(noSaltarAlPrincipio), copiaDeArreglo(detencionesAbsolutas), copiaDeArreglo(invalidosPaquete), copiaDeArreglo(saltarAntesNumero), copiaDeArreglo(saltarDespuesNumero), cdv.copia());
    }
}

//    public Tabla getTablaAntesIngnorar() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.antesIngnorar);
//    }
//    public Tabla getTablaDespuesIngnorar() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.despuesIngnorar);
//    }
//
//    public Tabla getTablaDetenciones() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.detenciones);
//    }
//
//    public Tabla getTablaDetencionesAbsolutas() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.detencionesAbsolutas);
//    }
//
//    public Tabla getTablaIdentificadoresCantidadCapituloTemporada() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.identificadoresCantidadCapituloTemporada);
//    }
//
//    public Tabla getTablaIdentificadoresCapitulo() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.identificadoresCapitulo);
//    }
//
//    public Tabla getTablaIdentificadoresTemporadas() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.identificadoresTemporadas);
//    }
//
//    public Tabla getTablaNoSaltarAlPrincipio() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.noSaltarAlPrincipio);
//    }
//
//    public Tabla getTablaSaltarAlPrincipio() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.saltarAlPrincipio);
//    }
//
//    public Tabla getTablaSaltarHastaDespuesDe() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.saltarHastaDespuesDe);
//    }
//
//    public Tabla getTablaSeparacionString() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.separacionString);
//    }
//
//    public Tabla getTablaUnion() {
//        return Tabla.crearStringSeleccionableAmpliable(cdv.union);
//    }
