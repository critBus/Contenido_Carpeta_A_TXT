/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Series;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Fichero;
import Utiles.ClasesUtiles.NombreClave;
import Utiles.ClasesUtiles.Z;
import Utiles.MetodosUtiles.Archivo;
//import static Utiles.MetodosUtiles.Archivo.separacion;
import Utiles.MetodosUtiles.MetodosUtiles;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
import Utiles.MetodosUtiles.Videos;
import java.io.File;
import java.io.Serializable;
//import java.util.Arrays;
import java.util.LinkedList;
import java.util.TreeSet;
import Utiles.ClasesUtiles.Interfases.Clonable;
import java.util.Arrays;

/**
 * Version 0.4
 *
 * @author Rene
 */
public class Serie implements Serializable, Comparable<Serie>, Clonable<Serie> {
// Arrays.toString //ConfiguracionDeVideo cdv
    //  private String nombrePrincipal; Detuvo ****************************
//d 1 4 3

    private LinkedList<String> nombresRelacionados, claves;
    private estadoSerie estado;
    private LinkedList<Temporada> temporadas;

    public Serie(LinkedList<String> nombresRelacionados, LinkedList<String> claves, estadoSerie estado) {
        inicializar(nombresRelacionados, claves, estado, new LinkedList<Temporada>());
    }

    public Serie(LinkedList<Fichero> contenido, ConfiguracionDeVideo cdv) {
//        this(new LinkedList<String>(new TreeSet<>(Videos.getNombresDeSerieFichero(contenido, cdv))), contenido, cdv);
        this(eliminarNombreIguales(Videos.getNombresDeSerieFichero(contenido, cdv)), contenido, cdv);
    }

    private static LinkedList<String> eliminarNombreIguales(LinkedList<String> l) {
        LinkedList<String> a = new LinkedList<String>(new TreeSet<>(l));
        for (int i = 0; i < a.size(); i++) {
            for (int j = i + 1; j < a.size(); j++) {
                if (a.get(i).equalsIgnoreCase(a.get(j))) {
                    a.remove(j--);
                }
            }
        }
        return a;
    }

//***********
    public Serie(LinkedList<String> nombresRelacionados, LinkedList<Fichero> contenido, ConfiguracionDeVideo cdv) {
//        for (int i = 0; i < nombresRelacionados.size(); i++) {
//            System.out.println("i="+i+" nombresRelacionados.get(i)="+nombresRelacionados.get(i));
//        }
//        System.out.println("crear serie "+nombresRelacionados.get(0));
        if (!contenido.isEmpty()) {
            TreeSet<String> clavesOrdenadas = new TreeSet<>();
            for (Fichero fi : contenido) {
                clavesOrdenadas.addAll(fi.getClaves());
            }

            claves = new LinkedList<>(clavesOrdenadas);

            estado = estadoSerie.DESCONOCIDO;
            temporadas = new LinkedList<>();
//             System.out.println("nombresRelacionados");
//        for (int i = 0; i < nombresRelacionados.size(); i++) {
//            System.out.println( nombresRelacionados.get(i));
//        }System.out.println("");
            for (int i = 0; i < contenido.size(); i++) {
                addSeccion(contenido.get(i).getDireccion(), contenido.get(i).getDireccion().getName(), contenido.size(), false, cdv);
            }

            inicializar(nombresRelacionados, claves, estado, temporadas);
        }

    }

    public Serie(ConfiguracionDeVideo cdv, LinkedList<NombreClave> contenido) {
//        for (int i = 0; i < nombresRelacionados.size(); i++) {
//            System.out.println("i="+i+" nombresRelacionados.get(i)="+nombresRelacionados.get(i));
//        }
        if (!contenido.isEmpty()) {
            TreeSet<String> set = new TreeSet<>();
            for (NombreClave fi : contenido) {
                set.addAll(fi.getClaves());
            }

            claves = new LinkedList<>(set);
            //set.clear();System.out.println("aaaaaaaaaaaaaaaaaaaa");
            // LinkedList<String> nombresRelacionados=
//            set.addAll(Videos.getNombresDeSerie(contenido, cdv));

            temporadas = new LinkedList<>();
//             System.out.println("nombresRelacionados");
//        for (int i = 0; i < nombresRelacionados.size(); i++) {
//            System.out.println( nombresRelacionados.get(i));
//        }System.out.println("");
            for (int i = 0; i < contenido.size(); i++) {
                addSeccion(contenido.get(i).getNombre(), contenido.size(), cdv);
            }

            //inicializar(new LinkedList<String>(new TreeSet<>(Videos.getNombresDeSerie(contenido, cdv))), claves, estado, temporadas);
            inicializar(new LinkedList<String>(new TreeSet<>(Videos.getNombresDeSerie(contenido, cdv))), claves, estado, temporadas);
        }

    }
// int cantidadDeTemporadas,cantidadDecapitulos

//    public Serie(String direccion, final ConfiguracionDeVideo cdv) {
//        this(new File(direccion), cdv);
//    }
////    public Serie(File f, final ConfiguracionDeVideo cdv) {
////
////        LinkedList<String> subNombres = Videos.getSubNombres(f, cdv);
////
////        for (int i = 0; i < subNombres.size(); i++) {
////            subNombres.set(i, Videos.getNombreSerie(subNombres.get(i), cdv));
////        }
////
////        // Z<LinkedList<String>> respuestaGetClaves=Videos.getClavesZ(subNombres, cdv);
////        claves = Videos.getClaves(subNombres, cdv);
////        //  claves =respuestaGetClaves.respuestaLinkedListString;
////        if (Videos.esVideo(f)) {
////            Capitulo cap = new Capitulo(f, cdv);
////            inicializarTemporadasVacias(cap.getTemporada());
////            temporadas.get(cap.getTemporada() - 1).addCapitulo(cap);
////            inicializar(subNombres, claves, estadoSerie.SALIENDO, temporadas);
////        } else {
////            if (f.isDirectory()) {
////                inicializarTemporadasVacias(1);
////                estado = estadoSerie.DESCONOCIDO;
////                String contenido[] = f.list();
////                for (String c : contenido) {
////                    File c2 = new File(f + "/" + c);
////                    addSeccion(c2, c, contenido.length, true, cdv);
////
////                }
////
////                inicializar(subNombres, claves, estado, temporadas);
////
////            } else {
////
////            }
////        }
////    }
    public Serie(LinkedList<String> nombresRelacionados, LinkedList<String> claves, estadoSerie estado, LinkedList<Temporada> temporadas) {
        this.nombresRelacionados = nombresRelacionados;
        this.claves = claves;
        this.estado = estado;
        this.temporadas = temporadas;
    }

//    public Serie(String nombrePrincipal, LinkedList<String> nombresRelacionados, LinkedList<String> claves, estadoSerie estado, LinkedList<Temporada> temporadas) {
//        nombresRelacionados.add(0, nombrePrincipal);
//        inicializar(nombresRelacionados, claves, estado, temporadas);
//    }
    public void inicializar(LinkedList<String> nombresRelacionados, LinkedList<String> claves, estadoSerie estado, LinkedList<Temporada> temporadas) {
        this.nombresRelacionados = nombresRelacionados;
        this.claves = claves;
        this.estado = estado;
        this.temporadas = temporadas;
    }

    public LinkedList<String> getNombresRelacionados() {
        return nombresRelacionados;
    }

    public void setNombresRelacionados(LinkedList<String> nombresRelacionados) {
        this.nombresRelacionados = nombresRelacionados;
    }

    public LinkedList<String> getClaves() {
        return claves;
    }

    public void setClaves(LinkedList<String> claves) {
        this.claves = claves;
    }

    public estadoSerie getEstado() {
        return estado;
    }

    public void setEstado(estadoSerie estado) {
        this.estado = estado;
    }

    public LinkedList<Temporada> getTemporadas() {
        return temporadas;
    }

    public void setTemporadas(LinkedList<Temporada> temporadas) {
        this.temporadas = temporadas;
    }

    public void addSeccion(String c, int contenidoLengh, final ConfiguracionDeVideo cdv) {
        int cap[] = Videos.getCapitulosDeNombre(c, cdv);
        if (cap[1] != -1) {
            addCapitulo(new Capitulo(c, cap[0], new int[]{cap[1], cap[2]}, null));
            estado = estadoSerie.SALIENDO;
        }

    }

    public void addSeccion(File c2, String c, int contenidoLengh, boolean UnSoloNivel, final ConfiguracionDeVideo cdv) {
        //c2 direccion completa //tem!=-1 es solo esta temporada
        class auxiliar {

            class Fichero_de_Capitulo2 extends Fichero {

                int cap[];

                public Fichero_de_Capitulo2(int[] cap, File direccion, String clave, String nombre) {
                    super(direccion, clave, nombre);
                    this.cap = cap;
                }

            }

            LinkedList<LinkedList<Fichero_de_Capitulo2>> noRelacionados = new LinkedList();

            void addCapituloAdecuado(File c2, String c, int tem, Z zz) {
//              temp=-1 significa que desconosco la temporada 
//                 System.out.println("c=" + c);
//                if (c2.toString().equals(new File("F:\\nuevo\\Manga\\07 Ghost [25 Cap](NO BORRAR POR VER)\\[Dark Termplar] 07-Ghost 01.mp4").toString())) {
//            System.out.println("aaaaa");
//        }

                if (Videos.esVideo(c2) || c2.isDirectory() || Videos.esSubtitulo(c2)) {
//                    System.out.println("c2="+c2);
                    String nombre = Archivo.getNombre(c2);
//                    System.out.println("nombre="+nombre);
                    if (MetodosUtiles.esEntero(nombre)) {
                        if (Videos.esSubtitulo(c2)) {
                            addSubtitulo(new Subtitulo_De_Serie(nombre, tem == -1 ? 1 : tem, new int[]{Integer.parseInt(nombre)}, c2));
                        } else {
                            addCapitulo(new Capitulo(nombre, tem == -1 ? 1 : tem, new int[]{Integer.parseInt(nombre)}, c2));
                        }

                        // System.out.println("add1");
                        estado = estadoSerie.SALIENDO;
                        return;
                    }
//                    System.out.println("c="+c);
                    Z<String> respuestaGetClave = Videos.getClaveZ(c, cdv, zz);
                    int cap[] = respuestaGetClave.getCapitulosDecisivos();
//                    System.out.println(Arrays.toString(cap));
//                    System.out.println("claves ");
//                    MetodosUtiles.mostrarList(claves);
//                    System.out.println("respues claves="+respuestaGetClave.respuestaString);
//                   if(respuestaGetClave.respuestaString.isEmpty()){
//                   respuestaGetClave.respuestaString=
//                   }
//                    System.out.println("respuestaGetClave.indicesCapPrincipio[7]="+respuestaGetClave.indicesCapPrincipio[7]);
//                    System.out.println("respuestaGetClave.respuestaString="+respuestaGetClave.respuestaString);
                    if (respuestaGetClave.indicesCapPrincipio[7] != -1 || Archivo.nombresRelacionados(claves, respuestaGetClave.respuestaString)) {
                        //   System.out.println("c2.getName()=" + c2.getName());
                        // int cap[] = Videos.getCapitulosDeNombre(c2.getName(), cdv);
                        // System.out.println(Arrays.toString(cap));
                        if (cap[0] != -1) {
                            if (Videos.esSubtitulo(c2)) {
                                addSubtitulo(new Subtitulo_De_Serie(nombre, tem == -1 ? cap[0] : tem, new int[]{cap[1], cap[2]}, c2));
                            } else {
                                addCapitulo(new Capitulo(nombre, tem == -1 ? cap[0] : tem, new int[]{cap[1], cap[2]}, c2));
                            }

                            // System.out.println("add2");
                            estado = estadoSerie.SALIENDO;
                        }
                        // addCapitulo(new Capitulo(c2, cdv));

                    } else {
                        if (zz == null) {
                            addFicheroNoRelacionado(c2, respuestaGetClave.respuestaString, nombre, cap);
                        }

                    }

                } else {
//                    if (Videos.esSubtitulo(c2)) {
//                        String nombre = Archivo.getNombre(c2);
//                        Z<String> respuestaGetClave = Videos.getClaveZ(c, cdv, zz);
//                        int cap[] = respuestaGetClave.getCapitulosDecisivos();
//                        if (respuestaGetClave.indicesCapPrincipio[7] != -1 || Archivo.nombresRelacionados(claves, respuestaGetClave.respuestaString)) {
//                            if (cap[0] != -1) {
//                                addSubtitulo(new Subtitulo_De_Serie(nombre, tem == -1 ? cap[0] : tem, new int[]{cap[1], cap[2]}, c2));
//
//                                estado = estadoSerie.SALIENDO;
//                            }
//
//                        }
//                    }

                }
            }

            void addFicheroNoRelacionado(File direccion, String clave, String nombre, int cap[]) {
                if (Videos.esVideo(direccion) || Videos.esSubtitulo(direccion)) {
//                    if (noRelacionados.isEmpty()) {
//                        noRelacionados.add(new LinkedList<Fichero>());
//                        noRelacionados.get(0).add(new Fichero(direccion, clave, nombre));
//                        return;
//                    }
                    for (int i = 0; i < noRelacionados.size(); i++) {
                        if (Archivo.nombresRelacionados(noRelacionados.get(i).get(0).getClaves(), clave)) {
                            noRelacionados.get(i).add(new auxiliar.Fichero_de_Capitulo2(cap, direccion, clave, nombre));
                            return;
                        }
                    }
                    noRelacionados.add(new LinkedList<auxiliar.Fichero_de_Capitulo2>());
                    noRelacionados.getLast().add(new auxiliar.Fichero_de_Capitulo2(cap, direccion, clave, nombre));
                }
            }

            void addCapitulos(File f, int tem) {
                addCapitulos(f, tem, true);
            }

            void addCapitulos(File f, int tem, boolean UnSoloNivel) {
                String contenido[] = f.list();
                //     if (contenido.length != 0) {
                for (String c : contenido) {
                    File c2 = new File(f + "/" + c);
                    // System.out.println("c2=" + c2);
                    if (c2.isDirectory() && !UnSoloNivel) {
                        addCapitulos(c2, tem, true);
                        continue;
                    }
                    addCapituloAdecuado(c2, c, tem, null);
                }

                for (int i = 0; i < noRelacionados.size(); i++) {
                    if (noRelacionados.get(i).size() > 3) {
                        for (int j = 0; j < noRelacionados.get(i).size(); j++) {
                            Fichero_de_Capitulo2 fc = noRelacionados.get(i).get(j);
                            boolean addClave = true;
                            for (int k = 0; k < claves.size(); k++) {
                                if (claves.get(k).equals(fc.getClave())) {
                                    addClave = false;
                                    break;
                                }
                            }
                            if (addClave) {
                                claves.add(fc.getClave());
                            }

                            if (Videos.esSubtitulo(fc.getDireccion())) {
                                addSubtitulo(new Subtitulo_De_Serie(fc.getNombre(), tem == -1 ? fc.cap[0] : tem, new int[]{fc.cap[1], fc.cap[2]}, fc.getDireccion()));
                            } else {
                                addCapitulo(new Capitulo(fc.getNombre(), tem == -1 ? fc.cap[0] : tem, new int[]{fc.cap[1], fc.cap[2]}, fc.getDireccion()));
                            }
                        }

                    }
                }
                noRelacionados.clear();
            }

        }
//detener
//        System.out.println("aa  c2=" + c2);
//        if (c2.toString().equals(new File("G:\\Nueva carpeta\\[[26-08-2019]]\\Series\\Finalizadas\\Series [Temporadas Finalizadas]\\Bent - [Temp 1] [Cap-06]").toString())) {
//            System.out.println("aaaaa");
//        }
        //detener

        Z z = Videos.getCapitulosDeNombreZ(c, cdv);
//             System.out.println("1 capi[]="+Arrays.toString(z.indicesCap));
        if (c2.isDirectory()) {
            if (MetodosUtiles.esEntero(c2.getName())) {
                //tratar como temporada de este numero
                // new auxiliar().addCapitulos(c2, Integer.parseInt(c2.getName()));
                new auxiliar().addCapitulos(c2, Integer.parseInt(c2.getName()));
                return;
            }
            try {
                if (c.length() < 12) {
                    String nom = Archivo.getNombre(c).trim();
                    double numero[] = MetodosUtiles.buscarNumeroYCantidadDeCaracteresOriginal(nom, 0, false, false);
                    int indiceAContinuacion = (int) numero[2], numero1 = (int) numero[0];
                    while (or(nom.charAt(indiceAContinuacion++), cdv.separacion));
                    numero = MetodosUtiles.buscarNumeroYCantidadDeCaracteresOriginal(nom, indiceAContinuacion, false, false);
                    if ((int) numero[2] == nom.length()) {
                        new auxiliar().addCapitulos(c2, 1);
                    }
                }

            } catch (Exception e) {
            }
//               int capi[] = Videos.getCapitulosDeNombre(c, cdv);
            int capi[] = z.indicesCap;
//            System.out.println("capi[]=" + Arrays.toString(capi));
            if (capi[0] != -1) {
                if (capi[1] == -1) {
                    //tratar como temporada
                    new auxiliar().addCapitulos(c2, capi[0]);
                    return;
                }
                if (capi[5] != -1) {
                    if (!(capi[6] != -1)) {

//tratar como temporada capi[1]
                        new auxiliar().addCapitulos(c2, capi[0]);
                    } else {
                        // System.out.println("capi[1]="+capi[1]);
                        if (capi[1] >= contenidoLengh && capi[1] > 5) {
                            //tratar como temporada capi[1]
                            new auxiliar().addCapitulos(c2, capi[0]);
                        } else {
                            //tratar como capitulo
                            new auxiliar().addCapituloAdecuado(c2, c, -1, z);
                        }
                    }

                } else {
                    //System.out.println("MetodosUtiles.encerradoDentroDe(c, capi[1] + \"\", cdv.rodearIgnorar)="+MetodosUtiles.encerradoDentroDe(c, capi[1] + "", cdv.rodearIgnorar));
                    if (capi[0] == 0 && capi[1] > contenidoLengh && capi[1] > 6 && c2.list().length > capi[1] - 3) {
                        //tratar como temporada capi[1]
                        new auxiliar().addCapitulos(c2, 1);
                    } else {
//                        System.out.println("contenidoLengh="+contenidoLengh);
                        if (capi[0] == 0 && c2.listFiles().length >= capi[1] && c2.listFiles().length > 5) {
                            //tratar como temporada capi[1]
                            new auxiliar().addCapitulos(c2, capi[1]);
                        } else {
                            if (capi[0] != 0 && capi[1] > 5 && c2.listFiles().length >= capi[1] - 2 && c2.listFiles().length > 5) {
                                //tratar como temporada capi[1]
                                new auxiliar().addCapitulos(c2, capi[1]);
                            } else {
                                //tratar como capitulo
                                new auxiliar().addCapituloAdecuado(c2, c, -1, z);
                            }

                        }

                    }

                }
                return;

//                if (MetodosUtiles.containsStringSeparado(c, true, cdv.identificadoresTemporadas)) {
//                    if (!(MetodosUtiles.containsStringSeparado(c, true, cdv.identificadoresCapitulo))) {
//
////tratar como temporada capi[1]
//                        new auxiliar().addCapitulos(c2, capi[0]);
//                    } else {
//                        // System.out.println("capi[1]="+capi[1]);
//                        if (capi[1] > contenidoLengh && capi[1] > 6) {
//                            //tratar como temporada capi[1]
//                            new auxiliar().addCapitulos(c2, capi[0]);
//                        } else {
//                            //tratar como capitulo
//                            new auxiliar().addCapituloAdecuado(c2, c, -1, z);
//                        }
//                    }
//
//                } else {
//                    //System.out.println("MetodosUtiles.encerradoDentroDe(c, capi[1] + \"\", cdv.rodearIgnorar)="+MetodosUtiles.encerradoDentroDe(c, capi[1] + "", cdv.rodearIgnorar));
//                    if (capi[0] == 0 && capi[1] > contenidoLengh && capi[1] > 6 && c2.list().length > capi[1] - 3) {
//                        //tratar como temporada capi[1]
//                        new auxiliar().addCapitulos(c2, 1);
//                    } else {
//                        //tratar como capitulo
//                        new auxiliar().addCapituloAdecuado(c2, c, -1, z);
//                    }
//
//                }
//                return;
            } else {
                if (!UnSoloNivel) {
                    String contenido[] = c2.list();
                    if (contenido != null) {
                        for (int i = 0; i < contenido.length; i++) {
                            File c3 = new File(c2 + "/" + contenido[i]);
                            addSeccion(c3, contenido[i], contenido.length, true, cdv);
                        }
//                        for (String cc : contenido) {
//                            File c3 = new File(c2 + "/" + cc);
//                            // System.out.println("c2=" + c2);
//                            addSeccion(c3, cc, contenido.length, true, cdv);
//                        }
                    }

                }

            }
            return;
        }
        new auxiliar().addCapituloAdecuado(c2, c, -1, z);

    }

    private void inicializarTemporadasVacias(int tem) {
        if (tem < 20) {
            if (tem == 0) {
                tem = 1;
            }
            temporadas = new LinkedList<>();
            for (int i = 0; i < tem; i++) {
                temporadas.add(new Temporada(i + 1));
            }
        } else {
            System.out.println("tem=" + tem);
        }

    }

    public Serie getCapitulosEnSerieMayoresQue(int cap) {
        LinkedList<Temporada> t = new LinkedList<Temporada>();
        boolean empty = true;
        for (int i = 0; i < temporadas.size(); i++) {
            LinkedList<Capitulo> c = temporadas.get(i).getCapitulosMayoresQue(cap);
            t.add(new Temporada(i + 1));
            if (!c.isEmpty()) {
                empty = false;
                t.get(i).setCapitulos(c);
            }
        }
        return empty ? null : new Serie(nombresRelacionados, claves, estado, t);
    }

    public Serie getCapitulosEnSerieMenoresQue(int cap) {
        LinkedList<Temporada> t = new LinkedList<Temporada>();
        boolean empty = true;
        for (int i = 0; i < temporadas.size(); i++) {
            LinkedList<Capitulo> c = temporadas.get(i).getCapitulosMenoresQue(cap);
            t.add(new Temporada(i + 1));
            if (!c.isEmpty()) {
                empty = false;
                t.get(i).setCapitulos(c);
            }
        }
        return empty ? null : new Serie(nombresRelacionados, claves, estado, t);
    }

    public LinkedList<Temporada> getCapitulosEnTemporada(int cap) {
        LinkedList<Temporada> t = new LinkedList<Temporada>();
        boolean empty = true;
        for (int i = 0; i < temporadas.size(); i++) {
            LinkedList<Capitulo> c = temporadas.get(i).getCapitulos(cap);
            t.add(new Temporada(i + 1));
            if (!c.isEmpty()) {
                empty = false;
                t.get(i).setCapitulos(c);
            }
        }
        return empty ? new LinkedList<Temporada>() : t;
    }

    public Serie getCapitulosEnSerie(int cap) {
        LinkedList<Temporada> t = getCapitulosEnTemporada(cap);
        return t == null ? null : new Serie(nombresRelacionados, claves, estado, t);
    }

    public Capitulo getLastCapitulo() {
        ordenar();
        if (!temporadas.isEmpty() && !temporadas.getLast().getCapitulos().isEmpty()) {
//            System.out.println("temporadas.size()=" + temporadas.size());
//            System.out.println("temporadas.getLast().getCapitulos().size()=" + temporadas.getLast().getCapitulos().size());
            return temporadas.getLast().getCapitulos().getLast();
        }
        return null;

    }

    public Capitulo getFirstCapitulo() {
        ordenar();
        if (!temporadas.isEmpty() && !temporadas.getFirst().getCapitulos().isEmpty()) {
            return temporadas.getFirst().getCapitulos().getFirst();
        }
//        System.out.println("null "+this);
        return null;

    }

    public Temporada getTemporada(int i) {
        return i < sise() ? temporadas.get(i) : null;
    }

    public Temporada getLastTemporada() {
        return temporadas.getLast();
    }

    public void addCapitulo(Capitulo cap) {
//        int tem = cap.getTemporada() == 0 ? 1 : cap.getTemporada();
//        while (temporadas.size() < tem) {
//            temporadas.add(new Temporada(temporadas.size() + 1));
//        }
        int tem = ampliarTemporas(cap.getTemporada());
        temporadas.get(tem - 1).addCapitulo(cap);
    }

    public void addSubtitulo(Subtitulo_De_Serie s) {
        int tem = ampliarTemporas(s.getTemporada());
        temporadas.get(tem - 1).addSubtitulo(s);
    }

    private int ampliarTemporas(int tem) {
        tem = tem == 0 ? 1 : tem;
        while (temporadas.size() < tem) {
            temporadas.add(new Temporada(temporadas.size() + 1));
        }
        return tem;
    }

    public void addTemporada(Temporada t) {
        if (isEmpty()) {
            if (t.getNumero() > 1) {
                inicializarTemporadasVacias(t.getNumero() - 1);
            }
            temporadas.add(t.copia());
            return;
        }
        if (t.getNumero() > sise()) {
            addTemporadasVAcias(t.getNumero());
            temporadas.add(t.copia());

        } else {
            temporadas.get(t.getNumero() - 1).actualizar(t);
        }

    }

    public void addAllTemporada(Temporada t) {
//        if (isEmpty()) {
//        temporadas.add(new Temporada(1));
//        }
//        temporadas.get(0).addAll(t);
        //*************
        if (isEmpty()) {
            if (t.getNumero() > 1) {
                inicializarTemporadasVacias(t.getNumero() - 1);
            }

            temporadas.add(t.copia());
            return;
        }
//        System.out.println("entro");
        if (t.getNumero() > temporadas.size()) {
//            System.out.println("aaaaaaaaa");
            addTemporadasVAcias(t.getNumero());
            temporadas.add(t.copia());
//            System.out.println("bbbbbbbbbbbbbbbbbb");
        } else {
//            System.out.println("cccccccccccc");
            temporadas.get(t.getNumero() - 1).addAll(t);
//            System.out.println("dddddddddddddd");
        }
//        System.out.println("salio");
    }

    private void addTemporadasVAcias(int before) {
        if (before < 20) {
            for (int i = temporadas.size(); i < before - 1; i++) {
//               if (temporadas.size() == i) {
                temporadas.add(new Temporada(i + 1));
//                }

            }
        } else {
            System.out.println("before=" + before);
        }
        //************
//        if (before < 20) {
//            for (int i = 0; i < before - 1; i++) {
//                //if (temporadas.size() <=i) {
//                if (temporadas.size() == i) {
//                    temporadas.add(new Temporada(i + 1));
//                }
//
//            }
//        } else {
//            System.out.println("before=" + before);
//        }

    }

    public int sise() {
        return temporadas.size();
    }

    public boolean isEmpty() {
        return temporadas.isEmpty();
    }

    public String getNombrePrincipal() {
        return nombresRelacionados.get(0);
    }

    public boolean realacionadaCon(Serie s) {
        for (String c : claves) {
            for (String c2 : s.claves) {
                if (Archivo.nombresRelacionados(c, c2)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean realacionadaCon(LinkedList<String> c) {
        for (int i = 0; i < claves.size(); i++) {
            for (int j = 0; j < c.size(); j++) {
                if (Archivo.nombresRelacionados(claves.get(i), c.get(j))) {
                    return true;
                }
            }
        }
//        for (String c : claves) {
//            for (String c2 : s.claves) {
//                if (Archivo.nombresRelacionados(c, c2)) {
//                    return true;
//                }
//            }
//        }
        return false;
    }

    public boolean relacionadaCon(File f, ConfiguracionDeVideo cdv) {
        return relacionadaCon(Archivo.getNombreRelacionadoClave(Archivo.getNombre(f), cdv));
    }

    public boolean relacionadaCon(String c) {
        for (int i = 0; i < claves.size(); i++) {
            if (Archivo.nombresRelacionados(claves.get(i), c)) {
                return true;
            }

        }
        return false;
    }

    public boolean actualizar(Serie s, boolean comprobado, boolean estanRelaciondas) {
        if (comprobado ? estanRelaciondas : realacionadaCon(s)) {
            for (int i = 0; i < s.sise(); i++) {
                addTemporada(s.temporadas.get(i));
            }
            return true;
        }
        return false;
    }

    public void addAll(Serie s) {
        for (int i = 0; i < s.sise(); i++) {
            addAllTemporada(s.temporadas.get(i));
        }
    }

    public void imprimir() {
        ordenar();
        System.out.println("nombres:");
        for (String nombresRelacionado : nombresRelacionados) {
            System.out.println(nombresRelacionado);
        }
        System.out.println("Claves:");
        for (String c : claves) {
            System.out.println(c);
        }
        for (int i = 0; i < temporadas.size(); i++) {

            temporadas.get(i).imprimir();
        }
//        System.out.println("********************************************");
        System.out.println("");
    }

    public void ordenar() {
        for (int i = 0; i < temporadas.size(); i++) {
            temporadas.get(i).ordenarCapitulos();
        }
    }

    @Override
    public int compareTo(Serie o) {
        return MetodosUtiles.compararStringAConStringB(getNombrePrincipal(), o.getNombrePrincipal(), true);
        // return getNombrePrincipal().compareTo(o.getNombrePrincipal());
    }

    @Override
    public Serie copia() {
        LinkedList<String> n = new LinkedList<String>(), c = new LinkedList<String>();
        LinkedList<Temporada> t = new LinkedList<Temporada>();

        for (int i = 0; i < nombresRelacionados.size(); i++) {
            n.add(nombresRelacionados.get(i));
        }
        for (int i = 0; i < claves.size(); i++) {
            c.add(claves.get(i));
        }
        for (int i = 0; i < temporadas.size(); i++) {
//            System.out.println("i="+i+" *************************************************");
//            temporadas.get(i).imprimir();
            t.add(temporadas.get(i).copia());
//             System.out.println("------------------------------------");
//             t.get(i).imprimir();
        }
        return new Serie(n, c, estado, t);

    }

    @Override
    public String toString() {
        return getNombrePrincipal(); //To change body of generated methods, choose Tools | Templates.
    }

}
