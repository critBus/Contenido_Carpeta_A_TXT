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
import Utiles.MetodosUtiles.MetodosUtiles;
//import static Utiles.MetodosUtiles.Archivo.relacionarArchivosPorNombre;
import Utiles.MetodosUtiles.Videos;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
//import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * Version 0.5
 *
 * @author Rene
 */
public class CatalogoDeSeries implements Serializable {
//ConfiguracionDeVideo cdv d 1 4 3
    //bbbb Detuvo ****************************

    public static String EXTENCION_CATALGO = ".catl";
    private String nombre;
    private LinkedList<Serie> series;

    public CatalogoDeSeries(File f, ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        this(f, new String[]{}, cdv);
    }

    public CatalogoDeSeries(LinkedList<File> F, LinkedList<LinkedList<String>> directoriosNoValidos, ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
//        if (!F.isEmpty()) {
//            System.out.println(F.get(0));
//            if (F.get(0).toString().equals("C:\\1\\Experimento\\Series [TX]\\! Subtitulos")) {
//                System.out.println("aaaaaaa");
//            }
//        }

        LinkedList<File> directorios = new LinkedList();
        LinkedList<LinkedList<String>> noValidosDirectorios = new LinkedList();
        LinkedList<File> txt = new LinkedList();
        LinkedList<LinkedList<String>> noValidosTXT = new LinkedList();
        LinkedList<File> catalogos = new LinkedList();
        LinkedList<LinkedList<String>> noValidosCatalogos = new LinkedList();
        for (int i = 0; i < F.size(); i++) {
//             System.out.println("F.get(i)="+F.get(i));
//            System.out.println("");
            if (F.get(i).isDirectory()) {
//               System.out.println("sumo a "+F.get(i));
                directorios.add(F.get(i));
                noValidosDirectorios.add(directoriosNoValidos.get(i));
                continue;
            }
            if (Archivo.esTXT(F.get(i))) {
//                System.out.println("txt "+F.get(i));
                txt.add(F.get(i));
                noValidosTXT.add(directoriosNoValidos.get(i));
                continue;
            }
            if (esCatalogo(F.get(i))) {
                catalogos.add(F.get(i));
                noValidosCatalogos.add(directoriosNoValidos.get(i));

            }

        }
        LinkedList<LinkedList<Fichero>> organizados = Archivo.relacionarPorNombre(Videos.getFicherosDeVideo(Archivo.directoriosValidos(directorios, noValidosDirectorios), cdv));
//        System.out.println("aaa");
        series = new LinkedList<Serie>();
//        System.out.println("organizados.size()="+organizados.size());
        for (int i = 0; i < organizados.size(); i++) {
//            System.out.println("i="+i+" organizados.get(i)="+organizados.get(i));

            series.add(new Serie(organizados.get(i), cdv));
        }
//        for (LinkedList<Fichero> fls : organizados) {
//            //  System.out.println("bbbb");
//            Serie s = new Serie(fls, cdv);
//            //   System.out.println("aaaa");
//            series.add(s);
//        }
//        System.out.println("bbbb");
        LinkedList<LinkedList<NombreClave>> organizados2 = Archivo.relacionarPorNombre(Videos.getNombreClavesDeVideo(Archivo.leerTXTSinEspacios(txt, noValidosTXT, cdv), cdv));
//        System.out.println("cccc");
        for (LinkedList<NombreClave> fls : organizados2) {
            addSerie(new Serie(cdv, fls));
        }
//        System.out.println("ddddd");
        for (int i = 0; i < catalogos.size(); i++) {
            addAll((CatalogoDeSeries) Archivo.cargarArchivo(catalogos.get(i)));
        }
//        System.out.println("eeee");
        nombre = "";
    }

    public CatalogoDeSeries(String lineas[], ConfiguracionDeVideo cdv) throws FileNotFoundException {
        LinkedList<String> l = new LinkedList<>();
        for (int i = 0; i < lineas.length; i++) {
            if (!MetodosUtiles.StringRealmenteVacio(lineas[i].trim())
                    && Videos.lineaValida(lineas[i], cdv)) {
                l.add(lineas[i]);
            }
        }
        lineas = l.toArray(new String[]{});
        series = new LinkedList<Serie>();
        LinkedList<LinkedList<NombreClave>> organizados2 = Archivo.relacionarPorNombre(Videos.getNombreClavesDeVideo(lineas, cdv));
        for (LinkedList<NombreClave> fls : organizados2) {
            addSerie(new Serie(cdv, fls));
        }
        nombre = "";
    }

//    public CatalogoDeSeries(File F[], String directoriosNoValidos[][], ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        LinkedList<File> directorios = new LinkedList<File>();
//        LinkedList<String[]> noValidosDirectorios = new LinkedList<String[]>();
//        LinkedList<File> txt = new LinkedList<File>();
//        LinkedList<String[]> noValidosTXT = new LinkedList<String[]>();
//
//        for (int i = 0; i < F.length; i++) {
//            if (F[i].isDirectory()) {
//                directorios.add(F[i]);
//                noValidosDirectorios.add(directoriosNoValidos[i]);
//            }
//            if (Archivo.esTXT(F[i])) {
//                txt.add(F[i]);
//                noValidosTXT.add(directoriosNoValidos[i]);
//            }
//        }
//        LinkedList<LinkedList<Fichero>> organizados = Archivo.relacionarPorNombre(Videos.getFicherosDeVideo(Archivo.directoriosValidos(directorios.toArray(new File[]{}), noValidosDirectorios.toArray(new String[][]{})), cdv));
//        series = new LinkedList<Serie>();
//        for (LinkedList<Fichero> fls : organizados) {
//            Serie s = new Serie(fls, cdv);
//            series.add(s);
//        }
//        LinkedList<LinkedList<NombreClave>> organizados2 = Archivo.relacionarPorNombre(Videos.getNombreClavesDeVideo(Archivo.leerTXT(txt.toArray(new File[]{}), noValidosTXT.toArray(new String[][]{})), cdv));
//        for (LinkedList<NombreClave> fls : organizados2) {
//            addSerie(new Serie(cdv, fls));
//       }
//
//        nombre = "";
//    }
    public CatalogoDeSeries(File f, String directoriosNoValidos[], ConfiguracionDeVideo cdv) throws FileNotFoundException, IOException, ClassNotFoundException {
        if (!f.exists()) {
            series = new LinkedList<Serie>();
            nombre = "";
            return;
        }

        if (f.isDirectory()) {
//             System.out.println("f="+f);
            //   File contenido[] = Archivo.directoriosValidos(f, directoriosNoValidos);
            // LinkedList<Fichero> ficheros = Videos.getFicherosDeVideo(Archivo.directoriosValidos(f, directoriosNoValidos), cdv);
            LinkedList<LinkedList<Fichero>> organizados = Archivo.relacionarPorNombre(Videos.getFicherosDeVideo(Archivo.directoriosValidos(f, directoriosNoValidos), cdv));
//            System.out.println("organizados.size()="+organizados.size());
            series = new LinkedList<Serie>();
            for (LinkedList<Fichero> fls : organizados) {
//                System.out.println("aaa");
//                 System.out.println("fls.get(0).getDireccion()"+fls.get(0).getDireccion()+" es file "+fls.get(0).getDireccion().isFile());

                Serie s = new Serie(fls, cdv);
//                s.imprimir();
                series.add(s);
                // System.out.println("ser ");
                // s.imprimir();
            }
            //pruebas 
//            System.out.println("aaaaaaaaaaaaaa");
//            for (int i = 0; i < series.size(); i++) {
//                series.get(i).imprimir();
//            }
            //pruebas
        } else {
            if (Archivo.esTXT(f)) {
                LinkedList<LinkedList<NombreClave>> organizados = Archivo.relacionarPorNombre(Videos.getNombreClavesDeVideo(Archivo.leerTXTSinEspacios(f, cdv), cdv));
                series = new LinkedList<Serie>();
                for (LinkedList<NombreClave> fls : organizados) {
                    series.add(new Serie(cdv, fls));
                }
            }
            if (esCatalogo(f)) {
                addAll((CatalogoDeSeries) Archivo.cargarArchivo(f));
            }
        }
        nombre = "";
    }

//    public CatalogoDeSeries(File f, ConfiguracionDeVideo cdv) {
//
//    }
    public CatalogoDeSeries(String nombre, LinkedList<Serie> series) {
        this.nombre = nombre;
        this.series = series;
    }

    public CatalogoDeSeries() {
        this("", new LinkedList<Serie>());
    }

    public boolean isEmpty() {
        return series.isEmpty();
    }

    /**
     * this son el marcador y c son todas<br>
     * retorna las series de c que estan relacionadas con this
     *
     * @param c
     * @return
     */
    public CatalogoDeSeries getCatalogoRelacionado(CatalogoDeSeries c) {
        CatalogoDeSeries res = new CatalogoDeSeries();

        LinkedList<Serie> series2 = new LinkedList<Serie>(c.series);
        For1:
        for (int i = 0; i < series.size(); i++) {
//            int j = 0;
            boolean añadio = false;
            For2:
            for (int k = 0; k < series.get(i).getClaves().size(); k++) {

                for (int j = 0; j < series2.size(); j++) {
                    if (series2.get(j).relacionadaCon(series.get(i).getClaves().get(k))) {
                        if (!añadio) {
                            añadio = true;
                            res.series.add(series2.get(j));
                        } else {
                            res.series.getLast().addAll(series2.get(j));
                        }

                        series2.remove(j);
//                        j++;
                        continue For2;
                    }
                }
//                j = 0;
            }

        }
//        for (Serie s : series) {
//            for (int i = 0; i < series2.size(); i++) {
//                if (s.realacionadaCon(series2.get(i))) {
//                    res.series.add(series2.get(i));
//                    series2.remove(i);
////                    series2.remove(i--);
//                    continue For1;
//                }
//            }
//        }
        return res;
    }

    /**
     * <big>tine el objetivo de indentificar cuales son los ultimos capitulos
     * para actualizar a this</big><p>
     * toma a this como marcador<br>
     * <strong>si c contiene a una serie de this</strong>
     * <ul><li>y esta en this esta vacia la añade</li>
     * <li>y esta en this tiene capitulos entonces añade los capitulos de c
     * mayores de los de this</li></ul>
     *
     * @param C
     * @return
     */
    public CatalogoDeSeries getCatalogoSeriesUltimasFaltantes(CatalogoDeSeries C) {
        CatalogoDeSeries res = new CatalogoDeSeries();

        LinkedList<Serie> series2 = new LinkedList<Serie>(C.series);
        addSeriesUltimasFaltantes(res, series2);

        return res;
    }

    /**
     * <big>tine el objetivo de indentificar cuales series faltan por copiarle a
     * this</big><p>
     * toma a this como marcador siendo las ya copiadas<br>
     * toma c como todas las que se tienen que copiar<br>
     * <strong>si c contiene a una serie de this</strong>
     * <ul><li>y esta en this esta vacia la añade</li>
     * <li>y esta en this tiene capitulos entonces añade los capitulos de c
     * mayores de los de this</li></ul>
     * <strong>si c contiene a una serie que no esta en this</strong>
     * <ul><li> la añade</li></ul>
     *
     * @param Completo
     * @return
     */
    public CatalogoDeSeries getCatalogoSeriesFaltantes(CatalogoDeSeries Completo) {
        CatalogoDeSeries res = new CatalogoDeSeries();

        LinkedList<Serie> series2 = new LinkedList<Serie>(Completo.series);
//        System.out.println("d 1 4 1");
        addSeriesUltimasFaltantes(res, series2);
//        System.out.println("d 1 4 2");
        res.series.addAll(series2);
//        for (Serie s : series2) {
//            res.addSerie(s);
//        }
//        System.out.println("d 1 4 3");
        return res;
    }

    /**
     * <big>tiene el objetivo de indentificar cuales serie no estan en
     * this</big><p>
     *
     * <strong>si c contiene a una serie relacionada con una de this</strong>
     * <ul><li>No la añade</li>
     * </ul>
     * <strong>si c contiene a una serie que no esta relacionada con una de
     * this</strong>
     * <ul><li> la añade</li></ul>
     *
     * @param Completo
     * @return
     */
    public CatalogoDeSeries getCatalogoSeriesContrario(CatalogoDeSeries Completo) {
        CatalogoDeSeries res = new CatalogoDeSeries();

        LinkedList<Serie> series2 = new LinkedList<Serie>(Completo.series);
        For1:
        for (int i = 0; i < series.size(); i++) {
            for (int j = 0; j < series2.size(); j++) {
                if (series.get(i).realacionadaCon(series2.get(j))) {
                    series2.remove(j);
                    continue For1;
                }
            }
        }

        res.series.addAll(series2);
        return res;
    }

    private void addSeriesUltimasFaltantes(CatalogoDeSeries res, LinkedList<Serie> series2) {
        For1:
        for (Serie s : series) {
            for (int i = 0; i < series2.size(); i++) {
                if (s.realacionadaCon(series2.get(i))) {
                    if (s.isEmpty()) {
                        // System.out.println("series2.get(i)=" + series2.get(i).getNombrePrincipal() + " " + series2.get(i).isEmpty());
                        if (!series2.get(i).isEmpty()) {
                            res.series.add(series2.get(i));
                        }

                        //res.addSerie(series2.get(i));
                    } else {
                        if (!series2.get(i).isEmpty()) {
                            Capitulo ultimo = s.getLastCapitulo();
                            // System.out.println(" ultimo=" + ultimo);
                            Serie nueva = new Serie(s.getNombresRelacionados(), s.getClaves(), s.getEstado());
                            for (int j = 0; j < series2.get(i).sise(); j++) {
                                Temporada t = series2.get(i).getTemporada(j);
                                for (int k = 0; k < t.sise(); k++) {
//                                    System.out.println("ultimo="+ultimo+"  |||| t.getCapitulo(k)="+t.getCapitulo(k));
//                                    System.out.println("ultimo.comparacion(t.getCapitulo(k))="+ultimo.comparacion2(t.getCapitulo(k)));
                                    if (ultimo.comparacion2(t.getCapitulo(k)) == -1) {
//                                        System.out.println("add");
                                        nueva.addCapitulo((ultimo = t.getCapitulo(k)));
                                        //  System.out.println("add " + ultimo);
                                    }
                                }
                            }
                            if (!nueva.isEmpty()) {
                                res.series.add(nueva);
                                //res.addSerie(nueva);
                            }

                        }
                    }
                    //res.series.add(series2.get(i));
                    series2.remove(i);
                    continue For1;
                }
            }
        }
    }
//public CatalogoDeSeries getCatalogoSeriesCapitulos1y0() {

    public CatalogoDeSeries getCatalogoSeriesCapitulos1y0() {
        CatalogoDeSeries res = new CatalogoDeSeries();
        for (Serie s : series) {
//            System.out.println("");
//            s.imprimir();
            if (!s.isEmpty()) {
                Capitulo primero = s.getFirstCapitulo();

                if (primero != null && (primero.getTemporada() == 1 || primero.getTemporada() == 0) && (primero.getCapituloInicial() == 0 || primero.getCapituloInicial() == 1)) {
                    Serie ss = new Serie(s.getNombresRelacionados(), s.getClaves(), s.getEstado());
                    ss.addCapitulo(primero);
                    res.series.add(ss);
                    //  res.addSerie(ss,true,true);
                }
            }
        }
        return res;
    }

    public CatalogoDeSeries getCatalogoSeriesPrimerosCapitulos() {
        CatalogoDeSeries res = new CatalogoDeSeries();
        for (Serie s : series) {
//            System.out.println("");
//            s.imprimir();
            if (!s.isEmpty()) {
                Capitulo primero = s.getFirstCapitulo();

                if (primero != null) {
                    Serie ss = new Serie(s.getNombresRelacionados(), s.getClaves(), s.getEstado());
                    ss.addCapitulo(primero);
                    res.series.add(ss);
                    //  res.addSerie(ss,true,true);
                }
            }
        }
        return res;
    }

    public CatalogoDeSeries getCatalogoSeriesConPrimerosCapitulos() {
        CatalogoDeSeries res = new CatalogoDeSeries();
        for (Serie s : series) {
//            System.out.println("");
//            s.imprimir();
            if (!s.isEmpty()) {
                Capitulo primero = s.getFirstCapitulo();

                if (primero != null && (primero.getTemporada() == 1 || primero.getTemporada() == 0) && (primero.getCapituloInicial() == 0 || primero.getCapituloInicial() == 1)) {
                    // Serie ss = new Serie(s.getNombresRelacionados(), s.getClaves(), s.getEstado());
                    //ss.addCapitulo(primero);
                    res.series.add(s);
                    //  res.addSerie(ss,true,true);
                }
            }
        }
        return res;
    }

    public CatalogoDeSeries getCatalogoActualizado(CatalogoDeSeries... C) {
        CatalogoDeSeries res = new CatalogoDeSeries();
        res.series = new LinkedList<Serie>(series);
        //res.actualizar(this);
        res.actualizar(C);
//        LinkedList<Serie> series2 = new LinkedList<Serie>(C.series);
//        For1:
//        for (Serie s : series) {
//            for (int i = 0; i < series2.size(); i++) {
//                if (s.realacionadaCon(series2.get(i))) {
//                    res.addSerie(series2.get(i));
//                    series2.remove(i);
//                    continue For1;
//                }
//            }
//        }
        return res;
    }

    public CatalogoDeSeries getCatalogoSeriesUltimosCapitulos() {
        CatalogoDeSeries res = new CatalogoDeSeries();
        for (int i = 0; i < series.size(); i++) {
            if (!series.get(i).isEmpty()) {
                Capitulo c = series.get(i).getLastCapitulo();

                if (c != null) {
                    Serie s = new Serie(series.get(i).getNombresRelacionados(), series.get(i).getClaves(), series.get(i).getEstado());
                    s.addCapitulo(c);
                    res.series.add(s);
                    //  res.addSerie(ss,true,true);
                }
            }
        }

        return res;
    }

    public CatalogoDeSeries getCatalogoSeriesSoloCapitulos(int cap) {
        CatalogoDeSeries res = new CatalogoDeSeries();
        for (int i = 0; i < series.size(); i++) {
            if (!series.get(i).isEmpty()) {
                Serie s = series.get(i).getCapitulosEnSerie(cap);
                if (s != null) {
                    res.series.add(s);
                }
            }
        }

        return res;
    }

    public CatalogoDeSeries getCatalogoSeriesMayoresQue(int cap) {
        CatalogoDeSeries res = new CatalogoDeSeries();
        for (int i = 0; i < series.size(); i++) {
            if (!series.get(i).isEmpty()) {
                Serie s = series.get(i).getCapitulosEnSerieMayoresQue(cap);
                if (s != null) {
                    res.series.add(s);
                }
            }
        }

        return res;
    }

    public CatalogoDeSeries getCatalogoSeriesMenoresQue(int cap) {
        CatalogoDeSeries res = new CatalogoDeSeries();
        for (int i = 0; i < series.size(); i++) {
            if (!series.get(i).isEmpty()) {
                Serie s = series.get(i).getCapitulosEnSerieMenoresQue(cap);
                if (s != null) {
                    res.series.add(s);
                }
            }
        }

        return res;
    }

    public void actualizar(CatalogoDeSeries... CC) {

        for (CatalogoDeSeries C : CC) {
            LinkedList<Serie> series2 = new LinkedList<Serie>(C.series);
            For1:
            for (int k = 0; k < series.size(); k++) {
                for (int i = 0; i < series2.size(); i++) {
                    if (series.get(k).realacionadaCon(series2.get(i))) {
                        series.get(k).actualizar(series2.get(i), true, true);
                        //    addSerie(series2.get(i));
                        series2.remove(i);
                        continue For1;
                    }
                }
            }
//            for (Serie s : series) {
//                for (int i = 0; i < series2.size(); i++) {
//                    if (s.realacionadaCon(series2.get(i))) {
//                        
//                    //    addSerie(series2.get(i));
//                        series2.remove(i);
//                        continue For1;
//                    }
//                }
//            }
        }
//        for (int i = 0; i < CC.length; i++) {
//            //System.out.println("act");
//            LinkedList<Serie> series2 = new LinkedList<Serie>(CC[i].series);
//            For1:
//            for (int j = 0; j < series.size(); j++) {
//                for (int k = 0; k < series2.size(); k++) {
//                    if (series.get(j).realacionadaCon(series2.get(k))) {
//                        addSerie(series2.get(k));
//                        series2.remove(k);
//                    }
//                }
//            }
//        }
    }

//    public void addActualizar(CatalogoDeSeries... C) {
//
//        for (int i = 0; i < C.length; i++) {
//            System.out.println("add i=" + i + " en proceso");
//            //  System.out.println(C[i].series.get(i).getNombrePrincipal());
//            //LinkedList<Serie> s=new LinkedList<Serie>(series);
////            LinkedList<Serie> d = new LinkedList<Serie>();
//            // LinkedList<Serie> m = new LinkedList<Serie>();
//            for (int j = 0; j < C[i].series.size(); j++) {
////                boolean add = false;
////                for (int k = 0; k < series.size(); k++) {
////                    if (series.get(k).realacionadaCon(C[i].series.get(j))) {
////                        series.get(k).actualizar(C[i].series.get(j), true, true);
////                        d.add(series.remove(k--));
////                        add = true;
////                        //  System.out.println("borro");
////                        break;
////                    }
////                }
////                if (!add) {
////                    d.add(C[i].series.get(j));
////                }
//                series.add(C[i].series.get(j));
//            }
////            series.addAll(d);
////            d.clear();
//            System.out.println("add i=" + i + " completado");
//        }
////        for (CatalogoDeSeries C : CC) {
////            for (Serie s : C.series) {
////                addSerie(s);
////            }
//////            LinkedList<Serie> series2 = new LinkedList<Serie>(C.series);
//////            For1:
//////            for (Serie s : series) {
//////                for (int i = 0; i < series2.size(); i++) {
//////                    if (s.realacionadaCon(series2.get(i))) {
//////                        addSerie(series2.get(i));
//////                        series2.remove(i);
//////                        continue For1;
//////                    }
//////                }
//////            }
////        }
//
//    }
    public void addNecesarios(CatalogoDeSeries... C) {

        for (int i = 0; i < C.length; i++) {
//            System.out.println("add i=" + i + " en proceso");
            //   LinkedList<Serie> d = new LinkedList<Serie>();
            for (int j = 0; j < C[i].series.size(); j++) {
                boolean add = true;
                for (int k = 0; k < series.size(); k++) {
                    if (C[i].series.get(j).getNombrePrincipal().equalsIgnoreCase(series.get(k).getNombrePrincipal())) {
                        //d.add(C[i].series.get(j));
                        add = false;
                        break;
                    }
                }
                if (add) {
                    series.add(C[i].series.get(j));
                }

            }

//            System.out.println("add i=" + i + " completado");
        }
    }

    public void addAll(CatalogoDeSeries... C) {
        for (int i = 0; i < C.length; i++) {
//            System.out.println("add i=" + i + " en proceso");
            //   LinkedList<Serie> d = new LinkedList<Serie>();
            for (int j = 0; j < C[i].series.size(); j++) {
                boolean add = true;
                for (int k = 0; k < series.size(); k++) {
                    if (C[i].series.get(j).getNombrePrincipal().equalsIgnoreCase(series.get(k).getNombrePrincipal())) {
//                        System.out.println("C[i].series.get(j).getNombrePrincipal()="+C[i].series.get(j).getNombrePrincipal());
//                        System.out.println("series.get(k).getNombrePrincipal()="+series.get(k).getNombrePrincipal());
                        series.get(k).addAll(C[i].series.get(j));
//                        d.add(C[i].series.get(j));
                        add = false;
                        break;
                    }
                }
                if (add) {
                    series.add(C[i].series.get(j).copia());
                }

            }

//            System.out.println("add i=" + i + " completado");
        }
    }
//public void addSerie(Serie s,boolean comprobado,boolean estanRelaciondas) {
//
//        for (Serie ss : series) {
//             if (comprobado?estanRelaciondas:ss.realacionadaCon(s)) {
//              ss.actualizar(s,true,true);
//                // System.out.println("ac  "+s.getNombrePrincipal());
//                return;
//             }
//           
//        }
//        series.add(s);
//
//    }

    public void addSerie(Serie s) {

        for (int i = 0; i < series.size(); i++) {
            if (series.get(i).realacionadaCon(s)) {
                series.get(i).actualizar(s, true, true);
                return;
            }
        }
//        for (Serie ss : series) {
//            if (ss.realacionadaCon(s)) {
//                ss.actualizar(s, true, true);
//                // System.out.println("ac  "+s.getNombrePrincipal());
//                return;
//            }
//        }
        series.add(s);

    }

    public void crearTXT_UltimosCapitulos(File f, String nombre) {
        if (!isEmpty()) {
            ordenar();
            String lineas[] = new String[series.size()];
            for (int i = 0; i < lineas.length; i++) {
                Capitulo c = series.get(i).getLastCapitulo();
                if (c == null) {
                    lineas[i] = series.get(i).getNombrePrincipal() + " [ sin capitulos ]";
                } else {
                    lineas[i] = c.toString();
                }
                // lineas[i] = c.getNombre() + " Temporada " + series.get(i).getLastTemporada().getNumero() + " Capitulo " + c.getCapitulosString();

                Archivo.crearTXT(f, nombre, lineas);
                // lineas[i]=c.toString();
            }
        }
    }

    public void imprimir() {
        ordenar();
        if (nombre != null && !nombre.isEmpty()) {
            System.out.println("Nombre de Catalogo: " + nombre);
        }
        if (isEmpty()) {
            System.out.println("[ vacio ]");
            return;
        }
        System.out.println("Series:");
        for (Serie s : series) {
            s.imprimir();
//            System.out.println("salto*******************************************");
        }

    }

    public String[] getNombresDeSeries() {
        ordenar();
        String[] n = new String[series.size()];
        for (int i = 0; i < series.size(); i++) {
            n[i] = series.get(i).getNombrePrincipal();
        }
        return n;
    }

    public void imprimirNombresDeSeries() {
        ordenar();
        for (Serie s : series) {
            System.out.println(s.getNombrePrincipal());
        }

    }

    public String[] getNombresDeUltimosCapitulos() {
        ordenar();
        String[] n = new String[series.size()];
        for (int i = 0; i < series.size(); i++) {
            Capitulo c = series.get(i).getLastCapitulo();
            n[i] = c == null ? series.get(i).getNombrePrincipal() + " [ sin capitulos ]" : c.toString();
        }
        return n;
    }

    public void imprimirUltimosCapitulos() {
        ordenar();
        for (int i = 0; i < series.size(); i++) {
            Capitulo c = series.get(i).getLastCapitulo();
            if (c == null) {
                System.out.println(series.get(i).getNombrePrincipal() + " [ sin capitulos ]");
            } else {
//                System.out.println(c.getNombre() + " Temporada " + series.get(i).getLastTemporada().getNumero() + " Capitulo " + c.getCapitulosString());
                System.out.println(c);
            }

        }

    }

    public String[] getNombresDeCapitulos() {
//        System.out.println("ss");
        ordenarPorCap1();
//        System.out.println("ss ss");
        LinkedList<String> n = new LinkedList<String>();
        //String[] n = new String[series.size()];
//        for (int i = 0; i < series.size(); i++) {
//            Capitulo c = series.get(i).getLastCapitulo();
//            n[i] = c == null ? series.get(i).getNombrePrincipal() + " [ sin capitulos ]" : c.toString();
//        }
        for (int i = 0; i < series.size(); i++) {
            if (series.get(i).isEmpty()) {
                n.add(series.get(i).getNombrePrincipal() + " [ sin capitulos ]");
            }
            //                series.get(i).ordenar();
            for (int j = 0; j < series.get(i).sise(); j++) {

                for (int k = 0; k < series.get(i).getTemporada(j).sise(); k++) {
                    Capitulo c = series.get(i).getTemporada(j).getCapitulo(k);
                    if (c != null) {
                        n.add(c.toString());
                    }

                    // System.out.println(series.get(i).getTemporada(j).getCapitulo(k));
                }
            }
        }
        return n.toArray(new String[]{});
    }

    public String[] getNombresDeCapitulosNoVacios() {
        ordenarPorCap1();
        LinkedList<String> n = new LinkedList<String>();
        for (int i = 0; i < series.size(); i++) {
//            if (series.get(i).isEmpty()) {
//                n.add(series.get(i).getNombrePrincipal() + " [ sin capitulos ]");
//            }
            //                series.get(i).ordenar();
            for (int j = 0; j < series.get(i).sise(); j++) {

                for (int k = 0; k < series.get(i).getTemporada(j).sise(); k++) {
                    Capitulo c = series.get(i).getTemporada(j).getCapitulo(k);
                    if (c != null) {
                        n.add(c.toString());
                    }

                    // System.out.println(series.get(i).getTemporada(j).getCapitulo(k));
                }
            }
        }
        return n.toArray(new String[]{});
    }

    public String copiarSubtitulosACapitulos(boolean noCopiar, ConfiguracionDeVideo cdv) throws IOException {
        String a = "";
        for (int i = 0; i < series.size(); i++) {
            for (int j = 0; j < series.get(i).getTemporadas().size(); j++) {
                ForCapitulos:
                for (int k = 0; k < series.get(i).getTemporadas().get(j).getCapitulos().size(); k++) {
                    Capitulo c = series.get(i).getTemporadas().get(j).getCapitulos().get(k);
                    if (c.getDireccion() != null) {
                        LinkedList<Subtitulo_De_Serie> S = series.get(i).getTemporadas().get(j).getSubtitulos(c.getCapituloInicial());
                        if (c.getDireccion().isFile()) {
                            a += copiarSubtitulosA(noCopiar, c.getDireccion(), S, true);
//                            System.out.println("1 algo");
                        } else {
                            File F[] = c.getDireccion().listFiles();
                            for (int l = 0; l < F.length; l++) {

                                if (Videos.esVideo(F[l])) {
//                                    System.out.println("");
                                    Z z = Videos.getClaveZ(F[l].getName(), cdv, null);
                                    if (series.get(i).relacionadaCon(z.clave)) {
                                        int[] cap = z.getCapitulosDecisivos();
                                        if (c.getTemporada() == cap[0] && c.getCapituloInicial() == cap[1]) {
                                            a += copiarSubtitulosA(noCopiar, F[l], S, true);
// System.out.println("2 algo");
                                            continue ForCapitulos;
                                        }
                                    }
                                }

                            }
                            a += copiarSubtitulosA(noCopiar, c.getDireccion(), S, false);
// System.out.println("3 algo");
                        }
                    }
                }
            }
        }
        return a;
    }

    private String copiarSubtitulosA(boolean noCopiar, File capitulo, LinkedList<Subtitulo_De_Serie> S, boolean utilizarMismoNombre) throws IOException {
        String a = "";
        boolean ponerString = false;
        if (!S.isEmpty()) {
            a += "\n" + capitulo.getName() + "\n";
        }
        for (int i = 0; i < S.size(); i++) {
            if (utilizarMismoNombre && i == 0) {
                File mismoNombre = new File(capitulo.toString());
                mismoNombre = Archivo.sustituirExtencionFalsa(mismoNombre, S.get(i).getExtencion());
                if (!mismoNombre.exists()) {
//                    a += "\n" + "Origen=" + S.get(i).getDireccion() + "\n";
//                    a += "Mismo Nombre=" + mismoNombre + "\n";
                    a += "(Mismo Nombre) " + mismoNombre.getName() + "\n";
                    ponerString = true;
                    if (!noCopiar) {
                        Archivo.copiar(S.get(i).getDireccion(), mismoNombre);
                    }

                    continue;
                }
            }
            File destino = null;
            if (!utilizarMismoNombre) {
                destino = new File(capitulo.toString() + "/" + S.get(i).getDireccion().getName());
            } else {
                destino = Archivo.adjuntarAPadre(capitulo, S.get(i).getDireccion());
            }

            if (!destino.exists()) {
//                a += "\n" + "Origen=" + S.get(i).getDireccion() + "\n";
//                a += "Destino=" + destino + "\n";
                ponerString = true;
                a += destino.getName() + "\n";
                if (!noCopiar) {
                    Archivo.copiar(S.get(i).getDireccion(), destino);
                }

            }
        }
        return ponerString ? a : "";
    }

    public void imprimirCapitulos() {
        ordenar();
        for (int i = 0; i < series.size(); i++) {
            series.get(i).ordenar();
            for (int j = 0; j < series.get(i).sise(); j++) {
                for (int k = 0; k < series.get(i).getTemporada(j).sise(); k++) {
                    System.out.println(series.get(i).getTemporada(j).getCapitulo(k));
                }
            }
        }
    }

    public void ordenar() {
        Collections.sort(series);
    }

    public void ordenarPorCap1() {
//         Collections.sort(series);
//        System.out.println("aaaaaaaa");
//        series=new LinkedList<Serie>(series);
        Collections.sort(series,new Utiles.ClasesUtiles.Comparadores.ComparadorDeSeriesPorCapitulo1());
//        System.out.println(series.toString());
//        System.out.println("*****************************************");
    }

    public static boolean esCatalogo(File f) {
        return Archivo.getExtencion(f).equalsIgnoreCase(EXTENCION_CATALGO);
    }
}
