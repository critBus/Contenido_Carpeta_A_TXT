/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.MetodosUtiles;

import static Utiles.MetodosUtiles.Archivo.*;
import static Utiles.MetodosUtiles.Arreglos.*;
import static Utiles.MetodosUtiles.Conversiones.*;
import static Utiles.MetodosUtiles.Conversiones.*;
import static Utiles.MetodosUtiles.MetodosUtiles.*;
import static Utiles.MetodosUtiles.Operaciones.*;
import static Utiles.MetodosUtiles.Visual.*;
import static Utiles.MetodosUtiles.Tempus.*;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Arrays;
import java.util.Formatter;
import java.util.LinkedList;
import java.util.Vector;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.StringTokenizer;
import Utiles.Exepciones.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utiles.ClasesUtiles.Fichero;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Paquete.Paquete;
import static Utiles.ClasesUtiles.Multimedia.Paquete.Paquete.invalidosPaquete;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Utiles.ClasesUtiles.NombreClave;
import Utiles.ClasesUtiles.Z;
import Utiles.ClasesUtiles.tipoClaseDeRespuesta;
import java.util.TreeSet;

/**
 * Metodos para la manipualcion de videos Version 0.8
 *
 * @author Rene
 */
public abstract class Videos {
// Arrays.toString ConfiguracionDeVideo cdv detenciones
//"Temp ", "Temp"  separacion separacionString

    // public static final String DETENCION_ABSOLUTA = ">!=";
    //"S",
    public static final String saltarAntesNumero[][] = {{"0", "Gate "}, {"1", ".amzn.web-d"}, {"2", ".dd+"}, {"3", "Tenshi no "}, {"9", "Norn"}, {"10", "Brave "}, {"15", "R-"}, {"35", "taimadou-gakuen-"}, {"38", "Acacias "}, {"69", "-ajp"}, {"100", "Mob Psycho ", "Los ", "The ", "the.", "The."}, {"264", ".x", ".h."}};//, "-MTB", "-TBS", "P!"
    public static final String saltarDespuesNumero[][] = {{"7", " Seeds", " Ghost", "-Ghost"}, {"8", " TAGE"}, {"91", " Alerta Policía"}};//Persona , "persona-", "Persona-"
    public static final String saltarAntes[] = {"parte ", "parte", "MP", "mp", "New", "H", "Parte ", "Parte", "version ", "version", "Extreno ", "Emision ", "Extreno", "Emision", "copy", "copy ", "fin de semana ", "v", "Strike Witches_ ", "Part ", "Magic Kaito ", " V"},
            saltarDespues[] = {"p", " Mb", "Mb", "fps", " fps", "kbit", " kbit", "Bit", "bit", " Gb", "Gb", "gb", " gb", " Butai"}, union[] = {" - ", " -", "- ", "-", " y ", " y", "y ", "y", ","},
            UNION_PREDETERMINADA = "-", saltarAlPrincipio[] = {"The ", " DC's ", "DCs ", "[Dark Termplar]", "Marvels ", "M ", "Marvel "}, detenciones[] = {"episodio", "capitulo", "temp", "fin de semana", "(", "[", "{", "copy", "Extreno ", "Emision ", "Extreno", "Emision", "parte", "version", "The Animation", " OVA ", "www.", " FDT", " ESP", " US", ".US"}, identificadoresTemporadas[] = {"temporada", "temp", "tem", "season", "period"},
            identificadoresCapitulo[] = {"capitulo", "cap", "capi", "chapter", "episodio", "epi", "epis", "episo", "episod"}, identificadoresCantidadCapituloTemporada[] = {"capitulos", "caps", "cap", "episodios", "chapters"}, detencionesAbsolutas[] = {">!="};
    public static final String rodearIgnorar[][] = {{"(", ")"}, {"[", "]"}, {"{", "}"}}, noSaltarAlPrincipio[] = {"The 100"};

    public static enum extencionVideo {

        MPG(".mpg", ".mp g"), MPEG(".mpeg", ".mpe"), MKV(".mkv", ".m"), MP4(".mp4", ".mp"), RMVB(".rmvb", ".r"), GP3(".3gp", ".3g"), AVI(".avi", ".av"), WMV(".wmv", ".w"), VOB(".vob", ".vo"), MOV(".mov", ".mo"), FLV(".flv", ".f"), RM(".rm", ".r m"), WEBM(".webm", ".web"), TS(".ts", ".t");
        private final String extencion, extencionDesactivada;

        private extencionVideo(String extencion, String extencionDesactivada) {
            this.extencion = extencion;
            this.extencionDesactivada = extencionDesactivada;

        }

        public String getExtencion() {
            return extencion;
        }

        public String getExtencionDesactivada() {
            return extencionDesactivada;
        }

        public static String[] extencionesVideos() {
            String extencionesVideos[] = new String[extencionVideo.values().length];
            for (int i = 0; i < extencionesVideos.length; i++) {
                extencionesVideos[i] = extencionVideo.values()[i].getExtencion();
            }
            return extencionesVideos;
        }

        public static String[] extencionesVideos_DESACTIVADO() {
            String extencionesVideos_DESACTIVADO[] = new String[extencionVideo.values().length];
            for (int i = 0; i < extencionesVideos_DESACTIVADO.length; i++) {
                extencionesVideos_DESACTIVADO[i] = extencionVideo.values()[i].getExtencionDesactivada();
            }
            return extencionesVideos_DESACTIVADO;
        }
    }

    public static enum extencionSubtitulo {//es-latsrt//es-essrt

        SRT(".srt"), ASS(".ass"), SSA(".ssa"), SUB(".sub"), ES_LATSRT(".es-latsrt"), ES_ESSRT(".es-essrt");
        private final String extencion;

        private extencionSubtitulo(String extencion) {
            this.extencion = extencion;
        }

        public String getExtencion() {
            return extencion;
        }

        public static String[] extencionesSubtitulo() {
            String extencionesSubtitulo[] = new String[extencionSubtitulo.values().length];
            for (int i = 0; i < extencionesSubtitulo.length; i++) {
                extencionesSubtitulo[i] = extencionSubtitulo.values()[i].getExtencion();
            }
            return extencionesSubtitulo;
        }
    }

    /**
     * double {tamaño, Cantidad De Videos}
     *
     * @param f
     * @return
     */
    public static double[] tamañoArchivoYCantidadDeVideos(File f) {
        if (f.exists()) {
            double res[] = {0, 0};

            if (f.isDirectory()) {
                File F[] = f.listFiles();
                for (int i = 0; i < F.length; i++) {
                    double tem[] = tamañoArchivoYCantidadDeVideos(F[i]);
                    res[0] += tem[0];
                    res[1] += tem[1];
//                    tam += tamañoArchivo(F[i]);
                }
            } else {
                if (esVideo(f)) {
                    res[1] = 1;
                }
                res[0] = f.length();
//                return res;
            }
            return res;
        }
        return null;
    }

    public static boolean lineaValida(File f, ConfiguracionDeVideo cdv) {
        return lineaValida(getNombre(f), cdv);
    }

    public static boolean lineaValida(String a, ConfiguracionDeVideo cdv) {
//        if(a.equals(" -C.Ficcion")){
//            System.out.println("aaaaaa");
//        }
        StringTokenizer S = new StringTokenizer(a, cdv.Delimiters);
//        TreeSet<String> palabras = new TreeSet<String>(Arrays.asList(invalidosPaquete));
        while (S.hasMoreTokens()) {
            String s = S.nextToken();
//            System.out.println("s="+s);
//            System.out.println("MetodosUtiles.arreglarPalabra(s)="+MetodosUtiles.arreglarPalabra(s));
            if (!or(MetodosUtiles.arreglarPalabra(s), true, cdv.invalidosPaquete)) {
//            if (!palabras.contains(MetodosUtiles.arreglarPalabra(a.toLowerCase()))) {
                return true;
            }

//            }
        }
        return false;

    }

    public static boolean containsSoloYNumeros(File f, ConfiguracionDeVideo cdv, String... B) {
        return containsSolo(getNombre(f), true, cdv, B);
    }

    public static boolean containsSolo(String a, boolean aceptarNumeros, ConfiguracionDeVideo cdv, String... B) {
        if (a.equals("! Subtitulos")) {
            System.out.println("aaaaa");
        }
        StringTokenizer S = new StringTokenizer(a, cdv.Delimiters);
        while (S.hasMoreTokens()) {
            String s = S.nextToken();
//            System.out.println("s=" + s);
            if (esNumero(s)) {
                if (!aceptarNumeros) {
                    return false;
                } else {
                    continue;
                }

            }
            if (!or(MetodosUtiles.arreglarPalabra(s), true, B)) {
                return false;
            }
        }
        return true;
    }

    public static void crearVideos(File carpeta, String invalidos[]) throws FileNotFoundException, IOException {
        crearVideos(carpeta, false, invalidos);
    }

    public static void crearVideos(File carpeta, boolean invasivo, String invalidos[]) throws FileNotFoundException, IOException {
        if (carpeta.exists() && carpeta.isDirectory()) {
            LinkedList<String> Fl = new LinkedList<String>();
            //  LinkedList<LinkedList<String>> directoriosNoValidos = new LinkedList<LinkedList<String>>();
            File F[] = carpeta.listFiles();
            for (int i = 0; i < F.length; i++) {
                if (or(getNombre(F[i]), invalidos)) {
                    continue;
                }
                if (invasivo && F[i].isDirectory()) {
                    crearVideos(F[i], invasivo, invalidos);
                    continue;
                }
                if (esTXT(F[i])) {
//                    String lineas[]=leerTXT(F[i]);
                    Fl.addAll(Arrays.asList(leerTXT(F[i])));
                }
            }
//            System.out.println(Fl.size());
            for (int i = 0; i < Fl.size(); i++) {
//                if(esVideo(Fl.get(i))){}
                String nombre = Fl.get(i).replace('?', ' ');
                File f = new File(carpeta + "/" + nombre);
                if (esVideo(f) && !f.exists()) {
//                    System.out.println("f="+f);
//                    System.out.println("f="+f);
                    boolean creado = f.createNewFile();
//                    System.out.println("creado="+creado);
                }
            }
        }
    }

    public static void crearTXTUltimosCapitulos(File carpeta, File txt, String nombre) throws FileNotFoundException {
        if (carpeta.exists() && carpeta.isDirectory() && txt.exists() && txt.isDirectory()) {
//            String Ignorar[] = {"__Finalizadas", "__Nuevo", "__ver", "__viejos", "_Comics", "_Peliculas Mangas", "_Videos de (Series Mangas)", "_Wallpaper (Mangas)", "_Presentaciones",
//                "!! Wallpaper Mangas", "Finalizadas",
//                "!! Videos de Series Mangas", "!! Wallpaper Mangas", "Peliculas Mangas (Clasicas)", "Series Mangas Finalizadas Por Temporadas",
//                "! Subtitulos", "!!!Estrenos"};
            LinkedList<String> Fl = new LinkedList<String>();
            //  LinkedList<LinkedList<String>> directoriosNoValidos = new LinkedList<LinkedList<String>>();
            File F[] = carpeta.listFiles();
            for (int i = 0; i < F.length; i++) {
                if (esVideo(F[i])) {
                    Fl.add(getNombre(F[i]));
                    //directoriosNoValidos.add(new LinkedList<String>());
                    // System.out.println("F[i]="+F[i]);
                }
            }
            CatalogoDeSeries c = new CatalogoDeSeries(Fl.toArray(new String[]{}), ConfiguracionDeVideo.getDefault());
            // c.imprimir();
            crearTXT(txt, nombre, c.getNombresDeUltimosCapitulos());
        }
    }

    public static void crearTXTContenidoDeCarpetaVideo(File carpeta, File txt, String nombre) {
        if (carpeta.exists() && carpeta.isDirectory() && txt.exists() && txt.isDirectory()) {
            LinkedList<String> l = new LinkedList<String>();
            File F[] = carpeta.listFiles();
            for (int i = 0; i < F.length; i++) {
                if ((!F[i].isDirectory()) && esVideo(F[i])) {
                    l.add(getNombre(F[i]));
                }
            }
            crearTXT(txt, nombre, l.toArray(new String[]{}));
        }
    }

    public static String getNombreSerie(String a, ConfiguracionDeVideo cdv) {
        int ini[] = Videos.getInicioEnd(a, cdv);
        return getNombreSerie(new File(a), ini[0], ini[1], cdv);
    }

    public static String sustituirAND(String a, ConfiguracionDeVideo cdv) {
        int indice = 0;
        int indiceAMP = 0;
//        System.out.println("entro");
        while ((indiceAMP = a.indexOf("&", indice)) >= 0) {
            if (indiceAMP != 0 && indiceAMP != a.length()
                    && (or(a.charAt(indiceAMP - 1), cdv.separacion)) && (or(a.charAt(indiceAMP + 1), cdv.separacion))) {
                a = a.substring(0, indiceAMP) + " " + a.substring(indiceAMP + 1);
            }
            indice++;
            if (!(indiceAMP < a.length())) {
                break;
            }
        }
        while ((indiceAMP = a.indexOf("and", indice)) >= 0) {
            if (indiceAMP != 0 && indiceAMP != a.length() && (indiceAMP + 3) < a.length()
                    && (or(a.charAt(indiceAMP - 1), cdv.separacion)) && (or(a.charAt(indiceAMP + 3), cdv.separacion))) {
                a = a.substring(0, indiceAMP) + " " + a.substring(indiceAMP + 3);
            }
            indice++;
            if (!(indiceAMP < a.length())) {
                break;
            }
        }
        return a;
    }

    public static String getNombreSerie(File f, int inicio, int end, ConfiguracionDeVideo cdv) {
//        System.out.println("aa  c2="+f);
//        if (f.toString().equals(new File("Dr. Stone Episodio 1").toString())) {
//            System.out.println("aaaaa");
//        }
        String nombreSerie = "";

        String nombre = MetodosUtiles.arreglarPalabra(getNombre(f).trim());
//        System.out.println("nombre =" + nombre);
//        nombre = nombre.replace("&", "y");
//        nombre = nombre.replace(" and ", " y ");
        nombre = sustituirAND(nombre, cdv);
        if (inicio < 0) {
            inicio = 0;
        }
        if (end > nombre.length() || end < 0) {
            end = nombre.length();
        }
//        System.out.println("inicio=" + inicio + " end=" + end);
        if (!(inicio > end)) {
            if (inicio < nombre.length() && end <= nombre.length()) {
                int separaciones = 0;
//                  System.out.println("nombre="+nombre);

                for (int i = inicio; i < end; i++) {
//                      System.out.println("i=" + i + " nombre.charAt(i)=" + nombre.charAt(i));

//                  
                    int indiceRodeado[] = rodeadoPor(nombre, i, cdv.rodearIgnorar);
                    if (indiceRodeado[0] != -1) {
                        separaciones++;
                        i = indiceRodeado[2] - 1;
//                        System.out.println("indiceRodeado[2]="+indiceRodeado[2]);
//                        System.out.println("salto a i="+i);
//                        break;
                        continue;
                    }
                    if (i < nombre.length() - 1 && i > 0 && or(nombre.charAt(i), 'S', 's') && esCharNumero(nombre.charAt(i + 1)) && or(nombre.charAt(i - 1), cdv.separacion)) {
                        end = i;
                        break;
                    }
                    int j = contieneStringAEnIndiceAArregloStringB(true, i, nombre, cdv.detencionesAbsolutas);
                    if (j != -1) {
                        break;
                    }
                    j = contieneStringAEnIndiceAArregloStringB(true, i, nombre, cdv.detenciones);
                    if (j != -1) {
                        break;
                    }

                    if (Archivo.esSeparacion(nombre.charAt(i), cdv)) {
                        separaciones++;
                        String separacion = obtenerSeparacion(nombre, i, cdv);
                        if (!separacion.isEmpty()) {
                            separaciones++;
                            if (esAleaterizacion(separacion)) {

                                break;
                            } else {
                                try {
                                    double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(separacion, 0, false, false);
                                    if (numero[0] > 1980) {
                                        end = i;
                                        break;
                                    }
                                } catch (Exception ex) {

                                }
//                                int j = contieneStringAEnIndiceAArregloStringB(true, 0, separacion, 1, Videos.extencionVideo.extencionesVideos());
                                j = contieneStringAEnIndiceAArregloStringB(true, 0, separacion, 1, 0, Videos.extencionVideo.extencionesVideos());
                                if (j != -1) {
                                    if (!(Videos.extencionVideo.extencionesVideos()[j].length() + i == end || Videos.extencionVideo.extencionesVideos()[j].length() + i == nombre.length())) {
                                        int k = contieneStringAEnIndiceAArregloStringB(true, Videos.extencionVideo.extencionesVideos()[j].length() + i, nombre, 0, 0, cdv.separacionString);
                                        if (k == -1) {
                                            end = i;
                                            break;
//                                            //System.out.println("salto");
//                                          
                                        }

                                    }
//                                     System.out.println("j="+j+"Videos.extencionVideo.extencionesVideos()[j]="+Videos.extencionVideo.extencionesVideos()[j]);

                                    break;
                                }
                            }

                        }
                    } else {
//                        if(i==14){
//                            System.out.println("i != nombre.length() - 1 ="+(i != nombre.length() - 1 ));
//                            System.out.println("");
//                        }
                        if (i > 5 && i != nombre.length() - 1 && nombre.charAt(i) == 'S' && esNumero(nombre.charAt(i + 1) + "")) {
//                            try {
//                                double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(nombre, i+1, false, false);
//                                if (numero[0] < 1980) {
//                            System.out.println("nombre="+nombre);
//                            System.out.println("aaaaaaaaaa");
                            end = i;
                            break;
//                                }
//                            } catch (Exception ex) {
//
//                            }
                        }

                        //*********
                        Object O[] = obtenerFechaYcantidadDeCaracteresOriginal(nombre, i);
                        if (O != null) {
                            break;
                        }
//                        int indiceRodeado[] = rodeadoPor(nombre, i, cdv.rodearIgnorar);
//                        if (indiceRodeado[0] != -1) {
//                            i = indiceRodeado[2] - 1;
//                            continue;
//                        }
//                        int j = contieneStringAEnIndiceAArregloStringB(true, i, nombre, cdv.detencionesAbsolutas);
//                        if (j != -1) {
//                            break;
//                        }
//                        j = contieneStringAEnIndiceAArregloStringB(true, i, nombre, cdv.detenciones);
//                        if (j != -1) {
//                            break;
//                        } //*********
//                        Object O[] = obtenerFechaYcantidadDeCaracteresOriginal(nombre, i);
//                        if (O != null) {
//                            break;
//                        }
                        nombreSerie += (separaciones > 0 ? " " : "") + nombre.charAt(i);
                        separaciones = 0;
//                         System.out.println("nombreSerie=" + nombreSerie);
                    }

                }
            }
        }
        return nombreSerie.trim();
    }

    public static LinkedList<String> getNombresDeSerieFichero(LinkedList<Fichero> contenido, final ConfiguracionDeVideo cdv) {
//        System.out.println("aaaaaaaaaaaaaaaaaaa");
        //  LinkedList<String> nombresDeSeries = new LinkedList<String>();
        if (!contenido.isEmpty()) {
//            if (contenido.size() > 1) {
//                System.out.println("aa  c2=" + contenido.get(0).getDireccion());
//                if (contenido.get(0).getDireccion().toString().equals(new File("G:\\Paquete\\Mangas [Anime] 2019\\Anime Online [Transmision]\\Araburu Kisetsu no Otome-domo yo. Episodio 1.mp4").toString())) {
//                    System.out.println("aaaaa");
//                }
//            }

            LinkedList<Fichero> files = new LinkedList<Fichero>(), directorios = new LinkedList<Fichero>();
            final LinkedList<String> libres = new LinkedList<String>(), soloTemporada = new LinkedList<String>(), soloCap = new LinkedList<String>(), temporadaYCap = new LinkedList<String>();
            class auxiliar {

                void addDistribuido(Fichero fi) {
                    LinkedList<String> subNombres = Videos.getSubNombres(fi.getDireccion(), cdv);
                    for (String sub : subNombres) {
//                           System.out.println("sub=" + sub);
                        boolean temporada = false, cap = false;
                        if (MetodosUtiles.containsString(sub, true, cdv.identificadoresTemporadas)) {
                            temporada = true;
                        }
                        if (MetodosUtiles.containsString(sub, true, cdv.identificadoresCapitulo)) {
                            cap = true;
                        }
                        int ini[] = getInicioEnd(sub, cdv);
//                        System.out.println(Arrays.toString(ini));
                        //String nombreSerie =recortarNombre(getNombreSerie(new File(sub), ini[0], ini[1], cdv)) ;
                        String nombreSerie = getNombreSerie(new File(sub), ini[0], ini[1], cdv);
//                           System.out.println("nombreSerie=" + nombreSerie);
                        if (temporada && cap) {
//                             System.out.println("1");
                            temporadaYCap.add(nombreSerie);
                            return;
                        }
                        if (temporada) {
//                               System.out.println("2");
                            soloTemporada.add(nombreSerie);
                            return;
                        }
                        if (cap) {
//                              System.out.println("3");
                            soloCap.add(nombreSerie);
                            return;
                        }
//                          System.out.println("4");
                        libres.add(nombreSerie);
                    }

                }

            }
            for (Fichero fi : contenido) {
                if (fi.getDireccion().isDirectory()) {
                    directorios.add(fi);
                } else {
                    files.add(fi);
                }
            }
//            System.out.println("nombre 1");
            for (Fichero fi : files) {
                new auxiliar().addDistribuido(fi);
            }
            if (!libres.isEmpty()) {
                return libres;
            }
//             System.out.println("nombre 2");
            for (Fichero fi : directorios) {
                new auxiliar().addDistribuido(fi);
            }
            if (!libres.isEmpty()) {
                return libres;
            }
            if (!soloTemporada.isEmpty()) {
                return soloTemporada;
            }
            if (!soloCap.isEmpty()) {
                return soloCap;
            }
            return temporadaYCap;
        }

        return new LinkedList<String>();
    }

    public static LinkedList<String> getNombresDeSerie(LinkedList<NombreClave> contenido, final ConfiguracionDeVideo cdv) {

        //  LinkedList<String> nombresDeSeries = new LinkedList<String>();
        if (!contenido.isEmpty()) {
//            if (contenido.size() > 1) {
//                System.out.println("aa  c2=" + contenido.get(1).getDireccion());
//                if (contenido.get(1).getDireccion().toString().equals(new File("F:\\nuevo\\Manga\\Rizelmine [1 TEMP]").toString())) {
//                    System.out.println("aaaaa");
//                }
//            }

            final LinkedList<String> libres = new LinkedList<String>(), soloTemporada = new LinkedList<String>(), soloCap = new LinkedList<String>(), temporadaYCap = new LinkedList<String>();
            class auxiliar {

                void addDistribuido(NombreClave fi) {
                    LinkedList<String> subNombres = Videos.getSubNombres(fi.getNombre(), cdv);
                    for (String sub : subNombres) {
                        //   System.out.println("sub=" + sub);
                        boolean temporada = false, cap = false;
                        if (MetodosUtiles.containsString(sub, true, cdv.identificadoresTemporadas)) {
                            temporada = true;
                        }
                        if (MetodosUtiles.containsString(sub, true, cdv.identificadoresCapitulo)) {
                            cap = true;
                        }
                        int ini[] = getInicioEnd(sub, cdv);
                        //String nombreSerie =recortarNombre(getNombreSerie(new File(sub), ini[0], ini[1], cdv)) ;
                        String nombreSerie = getNombreSerie(new File(sub), ini[0], ini[1], cdv);
                        //   System.out.println("nombreSerie=" + nombreSerie);
                        if (temporada && cap) {
                            // System.out.println("1");
                            temporadaYCap.add(nombreSerie);
                            return;
                        }
                        if (temporada) {
                            //   System.out.println("2");
                            soloTemporada.add(nombreSerie);
                            return;
                        }
                        if (cap) {
                            //  System.out.println("3");
                            soloCap.add(nombreSerie);
                            return;
                        }
                        //  System.out.println("4");
                        libres.add(nombreSerie);
                    }

                }

            }

            for (NombreClave fi : contenido) {
                new auxiliar().addDistribuido(fi);
            }
            if (!libres.isEmpty()) {
                return libres;
            }
            if (!soloTemporada.isEmpty()) {
                return soloTemporada;
            }
            if (!soloCap.isEmpty()) {
                return soloCap;
            }
            return temporadaYCap;
        }

        return new LinkedList<>();
    }

    /**
     * <p>
     * tiene que tener <br>
     * boolean renombrarCarpetasInternas,int NivelesCarpetasInternas
     *
     * @param contenido
     * @param cdv
     * @return
     */
    public static LinkedList<Fichero> getFicherosDeVideo(File contenido[], ConfiguracionDeVideo cdv) {
        LinkedList<Fichero> ficheros = new LinkedList<Fichero>();
        for (File a : contenido) {
            if ((a.isDirectory()) || esVideo(a) || esSubtitulo(a)) {
                LinkedList<String> subNombres = getSubNombres(a, cdv);
                LinkedList<String> claves = getClaves(subNombres, cdv);
                ficheros.add(new Fichero(a, getNombre(a), claves));
            }
        }
        return ficheros;
    }

    public static LinkedList<Fichero> getFicherosDeVideo(LinkedList<File> contenido, ConfiguracionDeVideo cdv) {
        LinkedList<Fichero> ficheros = new LinkedList<Fichero>();
        for (File a : contenido) {
            if ((a.isDirectory() && Videos.lineaValida(a, cdv)) || esVideo(a)||esSubtitulo(a)) {
                LinkedList<String> subNombres = getSubNombres(a, cdv);
                LinkedList<String> claves = getClaves(subNombres, cdv);
                ficheros.add(new Fichero(a, getNombre(a), claves));
            }
        }
        return ficheros;
    }

    public static LinkedList<NombreClave> getNombreClavesDeVideo(String A[], ConfiguracionDeVideo cdv) {
        LinkedList<NombreClave> res = new LinkedList<NombreClave>();
        for (String a : A) {
            res.add(new NombreClave(a, cdv));
        }
        return res;
    }

    public static LinkedList<NombreClave> getNombreClavesDeVideo(LinkedList<String> A, ConfiguracionDeVideo cdv) {
        LinkedList<NombreClave> res = new LinkedList<NombreClave>();
        for (String a : A) {
            res.add(new NombreClave(a, cdv));
        }
        return res;
    }

    /**
     * <p>
     * tiene que tener <br>
     * boolean renombrarCarpetasInternas,int NivelesCarpetasInternas
     *
     * @param direccion
     * @param cdv
     * @return
     */
    public static LinkedList<Fichero> getFicherosDeVideo(String direccion, ConfiguracionDeVideo cdv) {
        return getFicherosDeVideo(new File(direccion), cdv);
    }

    /**
     * <p>
     * tiene que tener <br>
     * boolean renombrarCarpetasInternas,int NivelesCarpetasInternas
     *
     * @param f
     * @param cdv
     */
    public static LinkedList<Fichero> getFicherosDeVideo(String direccion, String DirectoriosNoValidos[], ConfiguracionDeVideo cdv) {
        return getFicherosDeVideo(new File(direccion), DirectoriosNoValidos, cdv);
    }

    /**
     * <p>
     * tiene que tener <br>
     * boolean renombrarCarpetasInternas,int NivelesCarpetasInternas
     *
     * @param f
     * @param cdv
     */
    public static LinkedList<Fichero> getFicherosDeVideo(File f, final ConfiguracionDeVideo cdv) {
        return getFicherosDeVideo(f, new String[]{}, cdv);
    }

    public static LinkedList<Fichero> getFicherosDeVideo(File f, String DirectoriosNoValidos[], final ConfiguracionDeVideo cdv) {

        LinkedList archivos = getArchivosNombre(f, DirectoriosNoValidos, cdv.renombrarCarpetasInternas, cdv.getNivelesCarpetasInternas());
        LinkedList<Fichero> ficheros = new LinkedList<Fichero>();
        for (File a : (LinkedList<File>) archivos) {
            //if ((a.isDirectory()&&!(or(a.getName(),DirectoriosNoValidos))) || esVideo(a)) {
            if ((a.isDirectory()) || esVideo(a)) {
                String nombre = getNombre(a), nombreAsignado = esNumero(nombre) ? a.getParent() : nombre;

//            System.out.println("nombre=" + nombre);
                int indicesSeparador[][] = Fichero.indicesSeparador(nombreAsignado);
                if (indicesSeparador.length != 0) {
//                System.out.println("1");
                    int indiceComienzo = 0;
                    LinkedList<String> claves = new LinkedList<String>();
                    for (int i = 0; i <= indicesSeparador.length; i++) {
                        String subNombre = nombreAsignado.substring(indiceComienzo, i < indicesSeparador.length ? indicesSeparador[i][0] : nombreAsignado.length());
                        // claves.add(new auxiliar().claveActual(subNombre));
                        claves.add(getClave(subNombre, cdv));
                        indiceComienzo = i < indicesSeparador.length ? indicesSeparador[i][1] : 0;//por ponerlo 0
                    }
                    //para pruevas
//                System.out.println("claves");
//                for (String c : claves) {
//                    System.out.println(c);
//                }
                    //para pruevas
                    ficheros.add(new Fichero(a, nombreAsignado, claves));
                } else {
//                System.out.println("2");
                    // ficheros.add(new Fichero(a, new auxiliar().claveActual(nombreAsignado), nombre));
                    ficheros.add(new Fichero(a, getClave(nombreAsignado, cdv), nombre));
                }
            }

        }
        // System.out.println("sssssssssssss");
        return ficheros;
    }

    public static Z getClaveZ(String a, ConfiguracionDeVideo cdv, Z zz) {

        Z<String> z = getInicioEndZ(a, cdv, zz);
        z.tipoDeRespuesta = tipoClaseDeRespuesta.STRING;
//         System.out.println("a="+a+" z.indiceInicial="+z.indiceInicial+" z.indiceFinal="+z.indiceFinal);
        z.clave = (z.respuesta = (z.respuestaString = Archivo.getNombreRelacionadoClave(a, cdv, z.indiceInicial, z.indiceFinal)));
        return z;
    }

    public static int[] getInicioEnd(String a, ConfiguracionDeVideo cdv) {
        return getInicioEndZ(a, cdv, null).respuestaArregloInt;
    }

    public static Z getInicioEndZ(String a, ConfiguracionDeVideo cdv, Z zz) {
//        if(a.equals("[Rakuen] DearS 01.rmvb")){
//            System.out.println("bbbbbbbbb");
//        }
        Z<int[]> z = new Z();
        if (zz != null && zz.indicesCapPrincipio != null) {
            z.indicesCapPrincipio = zz.indicesCapPrincipio;
        } else {
            z.indicesCapPrincipio = getCapitulosDeNombreDelPrincipio(a, cdv);
            //System.out.println(Arrays.toString(z.indicesCapPrincipio));
        }
        if (zz != null && zz.indicesCapFinal != null) {
            z.indicesCapFinal = zz.indicesCapFinal;
        } else {
            z.indicesCapFinal = getCapitulosDeNombreDelFinal(z.indicesCapPrincipio[6] == -1 ? a : a.substring(z.indicesCapPrincipio[6]), cdv);
        }

        int end = z.indicesCapFinal[4];
        //    System.out.println("clave " + Arrays.toString(z.indicesCapFinal));
        int inicio = 0;
        if (z.indicesCapPrincipio[6] != -1) {
            inicio += z.indicesCapPrincipio[6];
            if (end != -1) {
                end += z.indicesCapPrincipio[6];
            }
        }
//        z.indicesCap=
        z.indiceInicial = inicio;
        z.indiceFinal = end;
        z.tipoDeRespuesta = tipoClaseDeRespuesta.INT_ARREGLO;
        z.respuesta = (z.respuestaArregloInt = new int[]{inicio, end});
        return z;
    }

    public static String getClave(String a, ConfiguracionDeVideo cdv) {
        return getClaveZ(a, cdv, null).respuestaString;
//        int indicesCapPrincipio[] = getCapitulosDeNombreDelPrincipio(a);
//        int indicesCapFinal[] = getCapitulosDeNombreDelFinal(indicesCapPrincipio[6] == -1 ? a : a.substring(indicesCapPrincipio[6]), cdv, false);
//        int end = indicesCapFinal[4];
//        System.out.println("clave " + Arrays.toString(indicesCapFinal));
//        int inicio = 0;
//        if (indicesCapPrincipio[6] != -1) {
//            inicio += indicesCapPrincipio[6];
//            if (end != -1) {
//                end += indicesCapPrincipio[6];
//            }
//        }
//        return Archivo.getNombreRelacionadoClave(a, cdv.saltarAlPrincipio, cdv.detenciones, inicio, end);
    }

    public static Z<LinkedList<String>> getClavesZ(LinkedList<String> A, ConfiguracionDeVideo cdv) {
        return getClavesZ(cdv, A.toArray(new String[]{}));
    }

    public static Z<LinkedList<String>> getClavesZ(ConfiguracionDeVideo cdv, String... A) {
        LinkedList<String> claves = new LinkedList<String>();
        Z<LinkedList<String>> z = new Z();
        z.linkedListZ = new LinkedList<>();
        for (String a : A) {
            Z sub = getClaveZ(a, cdv, null);
            z.linkedListZ.add(sub);
            // System.out.println("sub.respuestaString="+sub.respuestaString);
            claves.add(sub.respuestaString);
        }
        z.respuesta = (z.respuestaLinkedListString = claves);
        return z;
    }

    public static LinkedList<String> getClaves(LinkedList<String> A, ConfiguracionDeVideo cdv) {
        return getClaves(cdv, A.toArray(new String[]{}));
    }

    public static LinkedList<String> getClaves(ConfiguracionDeVideo cdv, String... A) {
        return getClavesZ(cdv, A).respuestaLinkedListString;
//        LinkedList<String> claves = new LinkedList<String>();a
//        for (String a : A) {
//            claves.add(getClave(a, cdv));
//        }
//        return claves;
    }

    public static LinkedList<String> getSubNombres(File f, ConfiguracionDeVideo cdv) {
        return getSubNombres(f.toString(), cdv);
    }

    public static LinkedList<String> getSubNombres(String a, ConfiguracionDeVideo cdv) {
        LinkedList<String> SubNombres = new LinkedList<String>();
        String nombre = getNombre(a);
        //  System.out.println("nom="+nombre);
        int indicesSeparador[][] = Fichero.indicesSeparador(nombre);
        if (indicesSeparador.length != 0) {

            int indiceComienzo = 0;
            for (int i = 0; i <= indicesSeparador.length; i++) {
                String subNombre = nombre.substring(indiceComienzo, i < indicesSeparador.length ? indicesSeparador[i][0] : nombre.length());
                indiceComienzo = i < indicesSeparador.length ? indicesSeparador[i][1] : 0;//por ponerlo 0
                SubNombres.add(subNombre);
            }
        } else {
            SubNombres.add(nombre);
        }
        //  System.out.println("SubNombres="+SubNombres.size()+"  "+SubNombres.get(0));
        return SubNombres;
    }
//    public static LinkedList<String> getClaves(String a, ConfiguracionDeVideo cdv) {
//        LinkedList<String> claves = new LinkedList<String>();
//        
//    }

    public static Fichero crearFicheroDeVideo(File f, ConfiguracionDeVideo cdv) {
        String nombre = Archivo.getNombre(f);
        return new Fichero(f, nombre, Archivo.getNombreRelacionadoClave(nombre, cdv));
    }

    /**
     * <p>
     * tiene que tener <br>
     * int nivelesCarpetasInternas, String antesIngnorar[], String
     * despuesIngnorar[], String rodearIgnorar[][], String union[], int
     * cantidadAMover, int cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO
     *
     * @param direccion
     * @param cdv
     * @throws FileNotFoundException
     */
    public static void capitulosQueFaltan(String direccion, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        capitulosQueFaltan(new File(direccion), cdv);
    }

    /**
     * <p>
     * tiene que tener <br>
     * int nivelesCarpetasInternas, String antesIngnorar[], String
     * despuesIngnorar[], String rodearIgnorar[][], String union[], int
     * cantidadAMover, int cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO
     *
     * @param f
     * @param cdv
     * @throws FileNotFoundException
     */
    public static void capitulosQueFaltan(File f, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        if (f.isDirectory()) {
            LinkedList<LinkedList<int[]>> temporadas = capitulosQueTengo(f, cdv);

            LinkedList<int[]> capitulosFaltantes = new LinkedList<int[]>();
            for (int i = 0; i < temporadas.size(); i++) {
                int temporadaActual = temporadas.get(i).get(0)[0];
                int numerosDeCapitulos[] = fillCapitulos(temporadas.get(i));
                //***
                // System.out.println("ssssssssss");
                //  System.out.println(Arrays.toString(numerosDeCapitulos));
                //***
                for (int k = temporadas.get(i).getFirst()[1] + 1; k < temporadas.get(i).getLast()[1]; k++) {
                    //  System.out.println("k="+k);
                    // System.out.println("Arrays.asList(numerosDeCapitulos).contains(k)="+Arrays.asList(numerosDeCapitulos).contains(k));
                    // if (!Arrays.asList(numerosDeCapitulos).contains(new Integer(k))) {
                    // System.out.println("Arrays.binarySearch(numerosDeCapitulos, k)="+Arrays.binarySearch(numerosDeCapitulos, k));
                    if (Arrays.binarySearch(numerosDeCapitulos, k) < 0) {
                        //   System.out.println("entro");
                        capitulosFaltantes.add(new int[]{temporadaActual, k});
                    }

                }
            }
            File txt = new File(f.getPath() + "/Lista de Archibos faltantes.txt");
            PrintWriter p = new PrintWriter(txt);

            if (temporadas.size() == 1 && temporadas.get(0).get(0)[0] == 1) {
                for (int[] c : capitulosFaltantes) {
                    p.println(c[1]);
                }
            } else {
                int caracteresMayorTemporada = cantidadDeCaracteresDeNumero(capitulosFaltantes.getLast()[1]);
                int soloCapitulos[] = fillCapitulos(capitulosFaltantes);
                Arrays.sort(soloCapitulos);
                int caracteresMayorCapitulo = cantidadDeCaracteresDeNumero(soloCapitulos[soloCapitulos.length - 1]);
                for (int[] c : capitulosFaltantes) {
                    //  System.out.println(Arrays.toString(c));
                    // System.out.printf("%" + caracteresMayorTemporada + "ds  %" + caracteresMayorCapitulo + "ds\n",c[0], c[1]);
                    p.println(String.format("Temp:%-" + caracteresMayorTemporada + "d Cap:%-" + caracteresMayorCapitulo + "d", c[0], c[1]));
                    //   p.printf("%" + caracteresMayorTemporada + "ds  %" + caracteresMayorCapitulo + "ds", c[0], c[1]);
                }
            }
            if (capitulosFaltantes.isEmpty()) {
                p.println("Estan todos los capitulos");
            }

            p.close();

        }

    }

    public static void eliminarNumeroDelPricipio(String direccion) throws NoPermitidoException, ExisteException {
        eliminarNumeroDelPricipio(new File(direccion), false, 0);
    }

    public static void eliminarNumeroDelPricipio(String direccion, boolean renombrarCarpetasInternas, int nivelesCarpetasInternas) throws NoPermitidoException, ExisteException {
        eliminarNumeroDelPricipio(new File(direccion), renombrarCarpetasInternas, nivelesCarpetasInternas);
    }

    public static void eliminarNumeroDelPricipio(File f, boolean renombrarCarpetasInternas, int nivelesCarpetasInternas) throws NoPermitidoException, ExisteException {

        if (f.isDirectory()) {
            String contenido[] = f.list();
            for (String c : contenido) {
                File c2 = new File(f + "/" + c);
//                if (c2.isDirectory()) {
//                    continue;
//                }
                if (c2.isDirectory()) {

                    if (renombrarCarpetasInternas && nivelesCarpetasInternas < 2) {
                        String nombreCarpeta = nombreNumeroMovidoInverso(c);
                        if (!nombreCarpeta.isEmpty()) {
                            File c3 = new File(f + "/" + nombreCarpeta);
                            c2.renameTo(c3);
                            c2 = new File(c3.toString());
                        }
                    }
                    if (!(nivelesCarpetasInternas > 0)) {
                        continue;
                    }

                    eliminarNumeroDelPricipio(c2, renombrarCarpetasInternas, nivelesCarpetasInternas - 1);
                } else {
                    eliminarNumeroDelPricipio(c2, renombrarCarpetasInternas, nivelesCarpetasInternas);
                }
                //  System.out.println("numeros.size()=" + numeros.size());
            }
        } else {

            String nuevoNombre = nombreNumeroEliminadoDelPrincipio(f + "");
            //System.out.println("nuevoNombre" + nuevoNombre);
            if (!nuevoNombre.isEmpty()) {
                renombrar(f, nuevoNombre);
            }
        }
    }

    public static String nombreNumeroEliminadoDelPrincipio(String a) {
        String nombre = getNombre(a), nuevoNombre = "";
        // System.out.println("nombre=" + nombre);
        StringTokenizer t = new StringTokenizer(nombre);
        boolean conteniaNumero = false;
        while (t.hasMoreTokens()) {
            if (!conteniaNumero) {
                try {
                    String token = t.nextToken();
                    if (token.equals("-")) {
                        continue;
                    }
                    Integer.parseInt((nuevoNombre = token));
                } catch (Exception e) {
                    conteniaNumero = true;
                }
            } else {
                nuevoNombre += " " + t.nextToken();
            }
        }
        return conteniaNumero ? nuevoNombre : "";
    }

    public static String nombreNumeroMovidoInverso(String nombre) {
        if (nombre.isEmpty()) {
            return "";
        }
        String nuevoNombre = "";
        StringTokenizer t = new StringTokenizer(nombre);
        boolean conteniaNumero = false;
        while (t.hasMoreTokens()) {
            // String token=t.nextToken();
            if (!conteniaNumero) {
                try {
                    String token = t.nextToken();
                    if (token.equals("-")) {
                        continue;
                    }
                    Integer.parseInt((nuevoNombre = token));
                } catch (Exception e) {
                    conteniaNumero = true;
                }
            } else {
                nuevoNombre += " " + t.nextToken();
            }
        }
        if (conteniaNumero && !nuevoNombre.isEmpty()) {
            return nuevoNombre;
        }
        return "";
    }

    public static void moverNumeroInverso(String direccion) throws NoPermitidoException, ExisteException {
        moverNumeroInverso(new File(direccion));
    }

    public static void moverNumeroInverso(File f) throws NoPermitidoException, ExisteException {

        if (f.isDirectory()) {
            String contenido[] = f.list();
            for (String c : contenido) {
                File c2 = new File(f + "/" + c);
                if (c2.isDirectory()) {
                    continue;
                }
                moverNumeroInverso(c2);
                //  System.out.println("numeros.size()=" + numeros.size());
            }
        } else {
            String nombre = getNombre(f), nuevoNombre = "";
            StringTokenizer t = new StringTokenizer(nombre);
            int numerosNombre[] = {-1, -1, -1};//si[1]!=-1 [0] temporada [1] capitulo else [0] capitulo
            boolean conUnion = false;
            try {
                for (int i = 0; t.hasMoreTokens() && i < 3; i++) {
                    String token = t.nextToken();
                    if (token.equals("-")) {
                        i--;
                        conUnion = true;
                        continue;
                    }
                    numerosNombre[i] = Integer.parseInt((nuevoNombre = token));
                    if (conUnion && i == 1) {
                        numerosNombre[2] = numerosNombre[1];
                        numerosNombre[1] = numerosNombre[0];
                        numerosNombre[0] = 1;
                        nuevoNombre = "";
                        break;
                    }
                    if (i == 2 && t.hasMoreTokens()) {
                        nuevoNombre = t.nextToken();
                    }

                }
            } catch (Exception e) {

            }
            // System.out.println(Arrays.toString(numerosNombre));
            if (numerosNombre[0] == -1) {
                return;
            }
            while (t.hasMoreTokens()) {
                nuevoNombre += " " + t.nextToken();
            }
            if (!Arreglos.igualesAconAA(numerosNombre, getCapitulosDeNombreDelFinal(nuevoNombre, ConfiguracionDeVideo.getDefault()))) {

                for (int i = 0; i < numerosNombre.length && numerosNombre[i] != -1; i++) {
                    // System.out.println("nuevoNombre=" + nuevoNombre);
                    nuevoNombre += (i == 0 && numerosNombre[0] == 1 && numerosNombre[1] != -1 ? " [Temp " : " ") + (i == 2 ? "- " : "") + numerosNombre[i] + (i == 0 && numerosNombre[0] == 1 && numerosNombre[1] != -1 ? "]" : "");
                    //  System.out.println("2nuevoNombre=" + nuevoNombre);
                }

            }
//            System.out.println("nuevoNombre="+nuevoNombre);
            renombrar(f, nuevoNombre);

        }
    }

    /**
     * <p>
     * tiene que tener <br>
     * int nivelesCarpetasInternas, String antesIngnorar[], String
     * despuesIngnorar[], String rodearIgnorar[][], String union[], int
     * cantidadAMover, int cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO
     *
     * @param f
     * @param cdv
     * @return
     */
    public static LinkedList<LinkedList<int[]>> capitulosQueTengo(File f, ConfiguracionDeVideo cdv) {
        LinkedList<LinkedList<int[]>> temporadas = new LinkedList<LinkedList<int[]>>();
        temporadas.add(new LinkedList<int[]>());
        return capitulosQueTengo(f, temporadas, cdv);
    }

    /**
     * <p>
     * tiene que tener <br>
     * int nivelesCarpetasInternas, String antesIngnorar[], String
     * despuesIngnorar[], String rodearIgnorar[][], String union[], int
     * cantidadAMover, int cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO
     *
     * @param f
     * @param numeros
     * @param cdv
     * @return
     */
    public static LinkedList<LinkedList<int[]>> capitulosQueTengo(File f, LinkedList<LinkedList<int[]>> numeros, ConfiguracionDeVideo cdv) {

        if (f.isDirectory()) {
            String contenido[] = f.list();
            for (String c : contenido) {
                File c2 = new File(f + "/" + c);
                if (c2.isDirectory()) {
                    if (!(cdv.nivelesCarpetasInternas > 0)) {
                        continue;
                    }
                    capitulosQueTengo(c2, numeros, cdv.referenciaDecrementoNivelesCarpetasInternas());
                } else {
                    capitulosQueTengo(c2, numeros, cdv);
                }
                //  System.out.println("numeros.size()=" + numeros.size());
            }
        } else {
            addCapitulosQueTiene(f, numeros, cdv);

            return numeros;

        }
        ordenadorTemporadas(numeros);
        // Collections.sort(numeros);

        return numeros;
    }

    /**
     * <p>
     * tiene que tener <br>
     * String antesIngnorar[], String despuesIngnorar[], String
     * rodearIgnorar[][], String union[], int cantidadAMover, int
     * cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO
     *
     * @param f
     * @param numeros
     * @param cdv
     */
    public static void addCapitulosQueTiene(File f, LinkedList<LinkedList<int[]>> numeros, ConfiguracionDeVideo cdv) {
        String nombre = f.getName();
        int numerosNombre[] = getCapitulosDeNombre(nombre, cdv);
        // System.out.println("2 len="+numerosNombre.length);
        //*******
        if (numerosNombre[1] != -1) {
            //   System.out.println("paso");
            class auxiliar2 {

                public boolean agregoConUnion(int numerosNombre[], LinkedList<int[]> numeros) {
                    if (numerosNombre[2] != -1) {
                        if (numerosNombre[1] - numerosNombre[2] == 1) {
                            numeros.add(new int[]{numerosNombre[0], numerosNombre[1]});
                            numeros.add(new int[]{numerosNombre[0], numerosNombre[2]});
                        } else {
                            for (int i = numerosNombre[1]; i <= numerosNombre[2]; i++) {
                                numeros.add(new int[]{numerosNombre[0], i});
                            }
                        }
                        //  System.out.println("numeros.size()"+numeros.size());
                        return true;
                    } else {
                        numeros.add(numerosNombre);
                    }
                    return false;
                }
            }

            if (numeros.get(0).isEmpty()) {
                // numeros.get(0).add(numerosNombre);
                new auxiliar2().agregoConUnion(numerosNombre, numeros.get(0));
            } else {

                boolean agregado = false;
                for (int i = 0; i < numeros.size(); i++) {
                    if (numeros.get(i).get(0)[0] == numerosNombre[0]) {
                        // numeros.get(i).add(numerosNombre);
                        new auxiliar2().agregoConUnion(numerosNombre, numeros.get(i));
                        agregado = true;
                        break;
                    }
                }
                if (!agregado) {
                    LinkedList<int[]> nuevaTemporada = new LinkedList<int[]>();
                    new auxiliar2().agregoConUnion(numerosNombre, nuevaTemporada);
                    //nuevaTemporada.add(numerosNombre);
                    numeros.add(nuevaTemporada);
                }

            }
            //   System.out.println("2 numeros.size()"+numeros.size());
        } else {
            if (numerosNombre[0] != -1) {
                numeros.get(0).add(new int[]{1, numerosNombre[0]});
            }
        }
    }

    private static int[] fillCapitulos(LinkedList<int[]> capitulos) {
        int numeros[] = new int[capitulos.size()];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = capitulos.get(i)[1];
        }
        return numeros;
    }

    private static void ordenadorTemporadas(LinkedList<LinkedList<int[]>> temporadas) {
        for (int i = 0; i < temporadas.size(); i++) {
            if (!temporadas.get(i).isEmpty()) {
                int temporada = temporadas.get(i).get(0)[0];
                int capitulos[] = fillCapitulos(temporadas.get(i));
                Arrays.sort(capitulos);
                LinkedList<int[]> temporadaOrdenada = new LinkedList<int[]>();
                for (int j = 0; j < capitulos.length; j++) {
                    temporadaOrdenada.add(new int[]{temporada, capitulos[j]});
                }
                temporadas.set(i, temporadaOrdenada);
            }
        }
    }

    /**
     * Mueve en un archivo de video los numeros del capitulo y de la
     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
     * - 029.rmvb"
     *
     * @param direccion direccion del arcchivo
     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
     * utilizar cararteres no permitidos
     * @throws ExisteException
     */
    public static void moverNumero(String direccion) throws NoPermitidoException, ExisteException {
        moverNumero(new File(direccion));
    }

    /**
     * Mueve en un archivo de video los numeros del capitulo y de la
     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
     * - 029.rmvb"
     *
     * @param f direccion del arcchivo
     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
     * utilizar cararteres no permitidos
     * @throws ExisteException
     */
    public static void moverNumero(File f) throws NoPermitidoException, ExisteException {
        moverNumero(f, false);
    }

    /**
     * Mueve en un archivo de video los numeros del capitulo y de la
     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
     * - 029.rmvb"
     *
     * @param f direccion del arcchivo
     * @param carpetasInternas decide si modificar tambien carpetas internas
     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
     * utilizar cararteres no permitidos
     * @throws ExisteException
     */
    public static void moverNumero(File f, boolean carpetasInternas) throws NoPermitidoException, ExisteException {
        moverNumero(f, ConfiguracionDeVideo.getDefaultDiferenteEnRenombrarCarpetasInternas(carpetasInternas));
    }
//
//    /**
//     * Mueve en un archivo de video los numeros del capitulo y de la
//     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
//     * - 029.rmvb"
//     *
//     * @param f direccion del arcchivo
//     * @param carpetasInternas decide si modificar tambien carpetas internas
//     * @param antesIngnorar palabras antes del numero que se toman en cuenta
//     * para ignorar un numero
//     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
//     * utilizar cararteres no permitidos
//     * @throws ExisteException
//     */
//    public static void moverNumero(File f, boolean carpetasInternas, String antesIngnorar[], String despuesIgnorar[], String rodearIgnorar[][], String saltarAlPrincipio[], String detenciones[]) throws NoPermitidoException, ExisteException {
//        moverNumero(f, carpetasInternas, antesIngnorar, despuesIgnorar, rodearIgnorar, saltarAlPrincipio, detenciones, 1);
//    }

    /**
     * Mueve en un archivo de video los numeros del capitulo y de la
     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
     * - 029.rmvb"
     *
     * @param direccion direccion del arcchivo
     * @param cantidadAMover cantidad de numeros que se moveran
     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
     * utilizar cararteres no permitidos
     * @throws ExisteException
     */
    public static void moverNumero(String direccion, int cantidadAMover) throws NoPermitidoException, ExisteException {
        moverNumero(new File(direccion), cantidadAMover);
    }

    /**
     * Mueve en un archivo de video los numeros del capitulo y de la
     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
     * - 029.rmvb"
     *
     * @param f direccion del arcchivo
     * @param cantidadAMover cantidad de numeros que se moveran
     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
     * utilizar cararteres no permitidos
     * @throws ExisteException
     */
    public static void moverNumero(File f, int cantidadAMover) throws NoPermitidoException, ExisteException {
        moverNumero(f, ConfiguracionDeVideo.getDefaultDiferenteEnCantidadAMover(cantidadAMover));
    }

//    public static void moverNumero(File f, boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, int cantidadAMover) throws NoPermitidoException, ExisteException {
//        moverNumero(f, renombrarCarpetasInternas, nivelesCarpetasInternas, saltarAntes, saltarDespues, rodearIgnorar, saltarAlPrincipio, detenciones, cantidadAMover);
//    }
//
//    /**
//     * Mueve en un archivo de video los numeros del capitulo y de la
//     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
//     * - 029.rmvb"
//     *
//     * @param f direccion del arcchivo
//     * @param antesIngnorar palabras antes del numero que se toman en cuenta
//     * para ignorar un numero
//     * @param despuesIngnorar palabras despues del numero que se toman en cuenta
//     * para ign
//     * @param rodearIgnorar
//     * @param rodearIgnorarorar un numero
//     * @param cantidadAMover cantidad de numeros que se moveran
//     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
//     * utilizar cararteres no permitidos
//     * @throws ExisteException
//     */
//    public static void moverNumero(File f, String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String saltarAlPrincipio[], String detenciones[], int cantidadAMover) throws NoPermitidoException, ExisteException {
//        moverNumero(f, false, antesIngnorar, despuesIngnorar, rodearIgnorar, saltarAlPrincipio, detenciones, cantidadAMover);
//    }
//
////    public static void moverNumero(File f, boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, String antesIngnorar[], String despuesIngnorar[], int cantidadAMover) throws NoPermitidoException, ExisteException {
////        moverNumero(f, renombrarCarpetasInternas, nivelesCarpetasInternas, antesIngnorar, despuesIngnorar, cantidadAMover);
////    }
//    /**
//     * Mueve en un archivo de video los numeros del capitulo y de la
//     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
//     * - 029.rmvb"
//     *
//     * @param f direccion del arcchivo
//     * @param carpetasInternas decide si modificar tambien carpetas internas
//     * @param antesIngnorar palabras antes del numero que se toman en cuenta
//     * para ignorar un numero
//     * @param despuesIngnorar palabras despues del numero que se toman en cuenta
//     * para ignorar un numero
//     * @param cantidadAMover cantidad de numeros que se moveran
//     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
//     * utilizar cararteres no permitidos
//     * @throws ExisteException
//     */
//    public static void moverNumero(File f, boolean carpetasInternas, String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String saltarAlPrincipio[], String detenciones[], int cantidadAMover) throws NoPermitidoException, ExisteException {
//        moverNumero(f, carpetasInternas, antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, cantidadAMover);
//    }
//
//    public static void moverNumero(File f, boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String saltarAlPrincipio[], String detenciones[], int cantidadAMover) throws NoPermitidoException, ExisteException {
//        moverNumero(f, renombrarCarpetasInternas, nivelesCarpetasInternas, antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, cantidadAMover);
//    }
//
//    /**
//     * Mueve en un archivo de video los numeros del capitulo y de la
//     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
//     * - 029.rmvb"
//     *
//     * @param f direccion del arcchivo
//     * @param carpetasInternas decide si modificar tambien carpetas internas
//     * @param antesIngnorar palabras antes del numero que se toman en cuenta
//     * para ignorar un numero
//     * @param despuesIngnorar palabras despues del numero que se toman en cuenta
//     * para ignorar un numero
//     * @param union palabras que se toman en cuenta para entender que son unn
//     * caonjunto de capitulos Ejemplo de "-" "227-228 One Piece - 227 -
//     * 228.rmvb"
//     * @param cantidadAMover cantidad de numeros que se moveran
//     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
//     * utilizar cararteres no permitidos
//     * @throws ExisteException
//     */
//    public static void moverNumero(File f, boolean carpetasInternas, String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String union[], String saltarAlPrincipio[], String detenciones[], int cantidadAMover) throws NoPermitidoException, ExisteException {
//        int[] cantidadAntesIngnorar = new int[antesIngnorar.length];
//        Arrays.fill(cantidadAntesIngnorar, 100);
//        int[] cantidadDespuesIngnorar = new int[despuesIngnorar.length];
//        Arrays.fill(cantidadDespuesIngnorar, 100);
//        moverNumero(f, carpetasInternas, (carpetasInternas ? 1 : 0), antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, cantidadAMover, 0, cantidadAntesIngnorar, cantidadDespuesIngnorar);
//    }
//
//    public static void moverNumero(File f, boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String union[], String saltarAlPrincipio[], String detenciones[], int cantidadAMover) throws NoPermitidoException, ExisteException {
//        int[] cantidadAntesIngnorar = new int[antesIngnorar.length];
//        Arrays.fill(cantidadAntesIngnorar, 100);
//        int[] cantidadDespuesIngnorar = new int[despuesIngnorar.length];
//        Arrays.fill(cantidadDespuesIngnorar, 100);
//        moverNumero(f, renombrarCarpetasInternas, nivelesCarpetasInternas, antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, cantidadAMover, 0, cantidadAntesIngnorar, cantidadDespuesIngnorar);
//    }
//
//    /**
//     * Mueve en un archivo de video los numeros del capitulo y de la
//     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
//     * - 029.rmvb"
//     *
//     * @param direccion direccion del arcchivo
//     * @param antesIngnorar palabras antes del numero que se toman en cuenta
//     * para ignorar un numero
//     * @param despuesIngnorar palabras despues del numero que se toman en cuenta
//     * para ignorar un numero
//     * @param cantidadASaltar cantidad de numeros que se saltaran
//     * @param cantidadAntesIngnorar es un int[] con lengh=antesIngnorar cantidad
//     * de veses que se usara el criterio para ignorar un numero
//     * @param cantidadDespuesIngnorar es un int[] con lengh=despuesIngnorar
//     * cantidad de veses que se usara el criterio para ignorar un numero
//     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
//     * utilizar cararteres no permitidos
//     * @throws ExisteException
//     */
//    public static void moverNumero(String direccion, String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String saltarAlPrincipio[], String detenciones[], int cantidadASaltar, int[] cantidadAntesIngnorar, int[] cantidadDespuesIngnorar) throws NoPermitidoException, ExisteException {
//        moverNumero(new File(direccion), false, 0, antesIngnorar, despuesIngnorar, rodearIgnorar, union, detenciones, detenciones, 2, cantidadASaltar, cantidadAntesIngnorar, cantidadDespuesIngnorar);
//    }
//
//    //, int formato
//    public static void moverNumero(String direccion, boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String saltarAlPrincipio[], String detenciones[], int cantidadASaltar, int[] cantidadAntesIngnorar, int[] cantidadDespuesIngnorar, int formato[]) throws NoPermitidoException, ExisteException {
//        //  System.out.println("1 formato="+formato);
//        moverNumero(new File(direccion), renombrarCarpetasInternas, nivelesCarpetasInternas, antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, 2, cantidadASaltar, cantidadAntesIngnorar, cantidadDespuesIngnorar, formato);
//    }
//
//    public static void moverNumero(String direccion, boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String saltarAlPrincipio[], String detenciones[], int cantidadASaltar, int[] cantidadAntesIngnorar, int[] cantidadDespuesIngnorar) throws NoPermitidoException, ExisteException {
//        moverNumero(new File(direccion), renombrarCarpetasInternas, nivelesCarpetasInternas, antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, 2, cantidadASaltar, cantidadAntesIngnorar, cantidadDespuesIngnorar);
//    }
//
//    public static void moverNumero(File f, boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String union[], String saltarAlPrincipio[], String detenciones[], int cantidadAMover, int cantidadASaltarO, int[] cantidadAntesIngnorarO, int[] cantidadDespuesIngnorarO) throws NoPermitidoException, ExisteException {
//        moverNumero(f, renombrarCarpetasInternas, nivelesCarpetasInternas, antesIngnorar, despuesIngnorar, rodearIgnorar, union, saltarAlPrincipio, detenciones, cantidadAMover, cantidadASaltarO, cantidadAntesIngnorarO, cantidadDespuesIngnorarO, new int[]{1, 1, 1, 1});
//    }
//    /**
//     * Mueve en un archivo de video los numeros del capitulo y de la
//     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
//     * - 029.rmvb"
//     *
//     * @param f direccion del arcchivo
//     * @param renombrarCarpetasInternas decide si modificar tambien carpetas
//     * internas
//     * @param nivelesCarpetasInternas int hasta que cantidad de carpetas
//     * internas modificar
//     * @param antesIngnorar palabras antes del numero que se toman en cuenta
//     * para ignorar un numero
//     * @param despuesIngnorar palabras despues del numero que se toman en cuenta
//     * para ignorar un numero
//     * @param union palabras que se toman en cuenta para entender que son unn
//     * caonjunto de capitulos Ejemplo de "-" "227-228 One Piece - 227 -
//     * 228.rmvb"
//     * @param cantidadAMover cantidad de numeros que se moveran
//     * @param cantidadASaltarO cantidad de numeros que se saltaran
//     * @param cantidadAntesIngnorarO es un int[] con lengh=antesIngnorar
//     * cantidad de veses que se usara el criterio para ignorar un numero
//     * @param cantidadDespuesIngnorarO es un int[] con lengh=despuesIngnorar
//     * cantidad de veses que se usara el criterio para ignorar un numero
//     * @param formato
//     * @throws NoPermitidoException Si se intenta dentro del nuevo nombre
//     * utilizar cararteres no permitidos
//     * @throws ExisteException
//     */
    // boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String union[], String saltarAlPrincipio[], String detenciones[], int cantidadAMover, int cantidadASaltarO, int[] cantidadAntesIngnorarO, int[] cantidadDespuesIngnorarO, final int formato[]
    /**
     * Mueve en un archivo de video los numeros del capitulo y de la
     * temporada(si es que tiene para delante del titulo) Ejemplo "29 One Piece
     * - 029.rmvb"
     * <p>
     * tiene que estar <br>
     * boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, String
     * antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][],
     * String union[], String saltarAlPrincipio[], String detenciones[], int
     * cantidadAMover, int cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO, final int formato[]
     *
     * @param f
     * @param cdv
     * @throws NoPermitidoException
     * @throws ExisteException
     */
    public static void moverNumero(File f, ConfiguracionDeVideo cdv) throws NoPermitidoException, ExisteException {
        if (cdv.cantidadAMover < 1) {
            return;
        }
        class Carpeta {

            File direccion;
            int[] formato;

            public Carpeta(File direccion, int[] formato) {
                this.direccion = direccion;
                this.formato = formato;
            }

        }

        LinkedList archivos = new LinkedList<File>(), carpetas = new LinkedList<Carpeta>();
        addArchivosNombre(f, cdv.renombrarCarpetasInternas, cdv.nivelesCarpetasInternas, archivos);
        if (cdv.formato[1] == -1) {
            LinkedList<Fichero> ficheros = new LinkedList<Fichero>();
            for (File a : (LinkedList<File>) archivos) {
                String nombre = getNombre(a);
                int indicesCapPrincipio[] = getCapitulosDeNombreDelPrincipio(nombre, cdv);
                // System.out.println("nom =" + (indicesCapPrincipio[6] == -1 ? nombre : nombre.substring(indicesCapPrincipio[6])));
                int end = getCapitulosDeNombreDelFinal(indicesCapPrincipio[6] == -1 ? nombre : nombre.substring(indicesCapPrincipio[6]), cdv)[4];
                // System.out.println("1 nombre=" + nombre);
                int inicio = 0;
                //System.out.println("nombre= " + nombre);
                //System.out.println(Arrays.toString(indicesCapPrincipio));
                if (indicesCapPrincipio[6] != -1) {
                    inicio += indicesCapPrincipio[6];
                    if (end != -1) {
                        end += indicesCapPrincipio[6];
                    }
                }
                ficheros.add(new Fichero(a, Archivo.getNombreRelacionadoClave(nombre, cdv, inicio, end), nombre));
            }
            //  LinkedList<LinkedList<Fichero>> organizados = relacionarArchivosPorNombre(ficheros);
            LinkedList<LinkedList<Fichero>> organizados = relacionarPorNombre(ficheros);

            //para prueba
//            for (int i = 0; i < organizados.size(); i++) {
//                System.out.println(i + " *********************");
//                for (Fichero o : organizados.get(i)) {
//                    System.out.println(getNombre(o.getDireccion()));
//                    System.out.println(o.getClave());
//                }
//            }
//            System.out.println("*******************");
            //para prueba
            for (int i = 0; i < organizados.size(); i++) {
                LinkedList<LinkedList<int[]>> cap = capitulosEnOrdenFichero(organizados.get(i), cdv);
                if (!cap.isEmpty() && !cap.getLast().isEmpty()) {
                    int maxCap = 0, maxTem = cap.getLast().getLast()[0];
                    for (int j = 0; j < cap.size(); j++) {
                        //  System.out.println("cap.get(i).getLast().length="+cap.get(i).getLast().length);
                        //   System.out.println(Arrays.toString(cap.get(i).getLast()));
                        int capituloActual = (cap.get(j).getLast()[1]);
                        if (capituloActual > maxCap) {
                            maxCap = capituloActual;
                        }
                    }
                    // System.out.println("maxCap=" + maxCap + " maxTem=" + maxTem);
                    int formatoCap = cantidadDeCaracteresDeNumero(maxCap), formatoTem = cantidadDeCaracteresDeNumero(maxTem);
                    //  System.out.println("formatoCap=" + formatoCap + " formatoTem=" + formatoTem);
                    for (int j = 0; j < organizados.get(i).size(); j++) {

                        if (cdv.renombrarCarpetasInternas && organizados.get(i).get(j).getDireccion().isDirectory()) {
                            carpetas.add(new Carpeta(organizados.get(i).get(j).getDireccion(), new int[]{formatoTem, formatoCap, formatoCap}));
                            continue;
                        }
//                        System.out.println("i="+i+" nombre="+getNombre(organizados.get(i).get(j)));
                        moverNumeroArchivo(organizados.get(i).get(j).getDireccion(), cdv.referenciaFormatoDiferente(new int[]{formatoTem, formatoCap, formatoCap}));
                        //   moverNumeroArchivo(organizados.get(i).get(j).getDireccion(), antesIngnorar, despuesIngnorar, rodearIgnorar, union, cantidadAMover, cantidadASaltarO, cantidadAntesIngnorarO, cantidadDespuesIngnorarO, new int[]{formatoTem, formatoCap, formatoCap});
                    }
                }

            }

        } else {
            for (File a : (LinkedList<File>) archivos) {
                if (a.isDirectory()) {
                    carpetas.add(new Carpeta(a, cdv.formato));
                    //   renombrar(a, nombreNumeroMovido(getNombre(a), antesIngnorar, despuesIngnorar, union, cantidadAMover, cantidadASaltarO, cantidadAntesIngnorarO, cantidadDespuesIngnorarO, formato));
                } else {
                    moverNumeroArchivo(a, cdv);
                }
            }
        }
        for (Carpeta c : (LinkedList<Carpeta>) carpetas) {
            //renombrar(c.direccion, nombreNumeroMovido(getNombre(c.direccion), antesIngnorar, despuesIngnorar, rodearIgnorar, union, cantidadAMover, cantidadASaltarO, cantidadAntesIngnorarO, cantidadDespuesIngnorarO, c.formato));
            renombrar(c.direccion, nombreNumeroMovido(getNombre(c.direccion), cdv.referenciaFormatoDiferente(c.formato)));
        }

    }

    private static LinkedList<File> getArchivosNombre(File f, String DirectoriosNoValidos[], boolean renombrarCarpetasInternas, int nivelesCarpetasInternas) {
        LinkedList archivos = new LinkedList<File>();
        addArchivosNombre(f, DirectoriosNoValidos, renombrarCarpetasInternas, nivelesCarpetasInternas, archivos);
        return archivos;
    }

    private static void addArchivosNombre(File f, boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, LinkedList<File> archivos) {
        addArchivosNombre(f, new String[]{}, renombrarCarpetasInternas, nivelesCarpetasInternas, archivos);
    }

    private static void addArchivosNombre(File f, String DirectoriosNoValidos[], boolean renombrarCarpetasInternas, int nivelesCarpetasInternas, LinkedList<File> archivos) {
        // LinkedList archivos = new LinkedList<File>();
        if (f.isDirectory()) {
            String contenido[] = f.list();
            for (String c : contenido) {
                File c2 = new File(f + "/" + c);
                //  System.out.println("c2=" + c2 + " nivelesCarpetasInternas=" + nivelesCarpetasInternas);
                if (c2.isDirectory()) {

                    if ((or(c2.getName(), DirectoriosNoValidos))) {
                        //System.out.println("c2.getName()="+c2.getName());
                        // System.out.println("sssssssssssssssssssssssssssssssssssssssssssssss");
                        continue;
                    }
                    if (renombrarCarpetasInternas && nivelesCarpetasInternas == 1) {
                        //    System.out.println("se add");
                        archivos.add(c2);
                    }
                    if (!(nivelesCarpetasInternas > 0)) {
                        continue;
                    }
                    addArchivosNombre(c2, renombrarCarpetasInternas, nivelesCarpetasInternas - 1, archivos);
                    // moverNumero(c2, renombrarCarpetasInternas, nivelesCarpetasInternas - 1, antesIngnorar, despuesIngnorar, union, cantidadAMover, cantidadASaltarO, cantidadAntesIngnorarO, cantidadDespuesIngnorarO, formato);

                } else {
                    archivos.add(c2);
                }
            }
        }
    }

    /**
     * <p>
     * tienen que estar<br>
     * String antesIngnorar[], String despuesIngnorar[], String
     * rodearIgnorar[][], String union[], int cantidadAMover, int
     * cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO
     *
     * @param F
     * @param cdv
     * @return
     */
    private static LinkedList<LinkedList<int[]>> capitulosEnOrden(LinkedList<File> F, ConfiguracionDeVideo cdv) {
        LinkedList<LinkedList<int[]>> cap = new LinkedList<LinkedList<int[]>>();
        cap.add(new LinkedList<int[]>());
        for (File f : F) {
            addCapitulosQueTiene(f, cap, cdv);
        }
        // System.out.println("2 "+Arrays.toString(cap.get(0).getLast()));
        ordenadorTemporadas(cap);
        //   System.out.println("1 "+Arrays.toString(cap.get(0).getLast()));
        return cap;
    }

    /**
     * <p>
     * tienen que estar<br>
     * String antesIngnorar[], String despuesIngnorar[], String
     * rodearIgnorar[][], String union[], int cantidadAMover, int
     * cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO
     *
     * @param F
     * @param cdv
     * @return
     */
    private static LinkedList<LinkedList<int[]>> capitulosEnOrdenFichero(LinkedList<Fichero> F, ConfiguracionDeVideo cdv) {
        LinkedList<LinkedList<int[]>> cap = new LinkedList<LinkedList<int[]>>();
        cap.add(new LinkedList<int[]>());
        for (Fichero f : F) {
            addCapitulosQueTiene(f.getDireccion(), cap, cdv);
        }
        // System.out.println("2 "+Arrays.toString(cap.get(0).getLast()));
        ordenadorTemporadas(cap);
        //   System.out.println("1 "+Arrays.toString(cap.get(0).getLast()));
        return cap;
    }
//String antesIngnorar[], String despuesIngnorar[], String rodearIgnorar[][], String union[], int cantidadAMover, int cantidadASaltarO, int[] cantidadAntesIngnorarO, int[] cantidadDespuesIngnorarO, int formato[]

    /**
     * <p>
     * tienen que estar<br>
     * String antesIngnorar[], String despuesIngnorar[], String
     * rodearIgnorar[][], String union[], int cantidadAMover, int
     * cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO, int formato[]
     *
     * @param f
     * @param cdv
     * @throws NoPermitidoException
     * @throws ExisteException
     */
    private static void moverNumeroArchivo(File f, ConfiguracionDeVideo cdv) throws NoPermitidoException, ExisteException {
        if (f.isDirectory()) {
            return;
        }
        String nombre = f.getName();
        if (esVideoNombre(nombre) || esSubtituloNombre(nombre)) {
            //   System.out.println("f=" + getNombre(f));
            String nuevoNombre = nombreNumeroMovido(getNombre(nombre), cdv);
            // System.out.println("nuevoNombre=" + nuevoNombre);
            if (!nuevoNombre.isEmpty()) {
                renombrar(f, nuevoNombre, getExtencion(nombre));
            }
        }

    }

    /**
     * <p>
     * tienen que estar<br>
     * String antesIngnorar[], String despuesIngnorar[], String
     * rodearIgnorar[][], String union[], int cantidadAMover, int
     * cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO, int formato[]
     *
     * @param nombre
     * @param cdv
     * @return
     */
    public static String nombreNumeroMovido(String nombre, ConfiguracionDeVideo cdv) {
        if (nombre.isEmpty()) {
            return "";
        }
        return (String) nombreNumeroMovidoYIndicePrimerNumero(nombre, cdv)[0];
    }

    /**
     * <p>
     * tienen que estar<br>
     * String antesIngnorar[], String despuesIngnorar[], String
     * rodearIgnorar[][], String union[], int cantidadAMover, int
     * cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO, final int formato[]
     *
     * @param nombre
     * @param cdv
     * @return
     */
    private static Object[] nombreNumeroMovidoYIndicePrimerNumero(String nombre, final ConfiguracionDeVideo cdv) {
        if (nombre.isEmpty()) {
            return new Object[]{"", -1};
        }
        class auxiliar {

            String extra(int cap[], String nombre2, int descartarTemp) {
                String extra = "";
                // if (cap[3] != -2) {
                if (cdv.incluirTemporada && cap[3] != descartarTemp) {
                    extra += (cdv.formato[0] > 1 ? String.format("%0" + cdv.formato[0] + "d", cap[0]) : cap[0]) + " ";
                }
                if (cap[1] != -1) {
                    extra += (cdv.formato[1] > 1 ? String.format("%0" + cdv.formato[1] + "d", cap[1]) : cap[1]);
                }
                if (cap[2] != -1) {
                    extra += " - " + (cdv.formato[2] > 1 ? String.format("%0" + cdv.formato[2] + "d", cap[2]) : cap[2]);
                }
                return extra + " " + nombre2;
            }
        }
        int cap[] = getCapitulosDeNombreDelFinal(nombre, cdv);
        String extra = "";
        if (cap[0] != -1) {

            return new Object[]{new auxiliar().extra(cap, nombre, -2), cap[4]};
        } else {

            if (((cap = getCapitulosDeNombreDelPrincipio(nombre, cdv))[1] != -1) && ((cap[3] != -1 && cap[3] != cdv.formato[0]) || (cap[4] != -1 && cap[4] != cdv.formato[1]) || (cap[5] != -1 && cap[5] != cdv.formato[2]))) {
                return new Object[]{new auxiliar().extra(cap, nombreNumeroEliminadoDelPrincipio(nombre), -1), cap[4]};
            }

        }

        return new Object[]{"", -1};
    }

    /**
     * Si contiene datos sobre un capitulo,no importa su extencion, develve un
     * int[]={temporada,capitulo,(si tiene varios el capitulo final, si no -1 )}
     * en caso de no tener datos {-1,-1,-1}
     *
     * <p>
     * tienen que estar<br>
     * String antesIngnorar[], String despuesIngnorar[], String
     * rodearIgnorar[][], String union[], int cantidadAMover, int
     * cantidadASaltarO, int[] cantidadAntesIngnorarO, int[]
     * cantidadDespuesIngnorarO
     *
     * @param nombre
     * @param cdv
     * @return
     */
    public static int[] getCapitulosDeNombre(String nombre, ConfiguracionDeVideo cdv) {
        return getCapitulosDeNombreZ(nombre, cdv).indicesCap;
//        int capitulos[] = getCapitulosDeNombreDelPrincipio(nombre);
//        if (capitulos[0] == -1) {
//            capitulos = getCapitulosDeNombreDelFinal(nombre, cdv);
//        }
//        return capitulos;
    }

    public static Z getCapitulosDeNombreZ(String nombre, ConfiguracionDeVideo cdv) {
        Z z = new Z();
        z.respuesta = (z.indicesCap = (z.indicesCapPrincipio = getCapitulosDeNombreDelPrincipio(nombre, cdv)));
        //  System.out.println("z prin="+Arrays.toString(z.indicesCapPrincipio) );
        if (z.indicesCapPrincipio[0] == -1) {
            z.respuesta = (z.indicesCap = (z.indicesCapFinal = getCapitulosDeNombreDelFinal(nombre, cdv)));
            // System.out.println("z fin="+Arrays.toString(z.indicesCapFinal) );
        }
        return z;
    }

    /**
     * si[1]!=-1 [0] temporada [1] capitulo else [0] capitulo
     * <ul><li>[3] formato de [0]</li>
     * <li>[4] formato de [1]</li>
     * <li>[5] formato de [2]</li>
     * <li>[6] indice de inicio despues de los numeros</li>
     * <li>[7] != -1 es que es solo el numero del capitulo y tiene que estar
     * relacionado con su carpeta<br>
     * no se debe tratar de sacar una clave de el pq solo contiene como mucho el
     * nombre del capitulo, no de la serie con que esta relacionado
     * </li></ul>
     *
     * @param cdv
     * @param nombre
     * @return
     */
    public static int[] getCapitulosDeNombreDelPrincipio(String nombre, ConfiguracionDeVideo cdv) {
//        String nombre = f.getName();
        //int cantidadDespuesIngnorar[] = new int[cdv.cantidadDespuesIngnorarO.length];//,cantidadAntesIngnorar[] = new int[cdv.cantidadAntesIngnorarO.length]
        //  System.arraycopy(cdv.cantidadDespuesIngnorarO, 0, cantidadDespuesIngnorar, 0, cantidadDespuesIngnorar.length); 
//        System.out.println("nombre=" + nombre);
        StringTokenizer t = new StringTokenizer(nombre);
        int numerosNombre[] = {-1, -1, -1, -1, -1, -1, -1, -1};//si[1]!=-1 [0] temporada [1] capitulo else [0] capitulo
        boolean conUnion = false, completo = false, capturoUnNumero = false;
        int cantidadSaltados = 0;
        try {
            int caracteresRecorridos = 0;
            for (int i = 0; t.hasMoreTokens() && i < 3; i++) {
                String token = t.nextToken();
//                System.out.println("token=" + token);

                if (numerosNombre[0] != -1 && token.equals(UNION_PREDETERMINADA)) {
                    i--;
                    conUnion = true;
                    caracteresRecorridos += UNION_PREDETERMINADA.length() + 1;
                    continue;
                }
                int numero = Integer.parseInt(token);

                caracteresRecorridos += token.length() + (i != 0 ? 1 : 0);

//                System.out.println("token="+token+" numero="+numero);
                //*********************    marca
                int indices[] = cdv.saltarDespues(nombre, caracteresRecorridos, numero);
                if (indices[0] >= 0) {
//                    System.out.println("entro");
                    i = indices[2] - 1;
                    continue;
                }

                //******************
//                  System.out.println("caracteresRecorridos="+caracteresRecorridos+"  "+nombre.charAt(caracteresRecorridos));
                int indiceDespues = cdv.despuesIngnorar.length > 0 ? contieneStringAEnIndiceAArregloStringB(caracteresRecorridos, nombre, cdv.despuesIngnorar) : -1;
//                 System.out.println("indiceDespues="+indiceDespues);
//                 System.out.println("cantidadDespuesIngnorar[indiceDespues]-- > 0="+(cantidadDespuesIngnorar[indiceDespues]-- > 0));
                // System.out.println("(!(indiceDespues < 0)="+(!(indiceDespues < 0)));
                //nnnnnnnnnnnnnnnnnnnn des
//                if ((!(indiceDespues < 0)) && cantidadDespuesIngnorar[indiceDespues]-- > 0) {
//                    //System.out.println("salto");
//                    continue;
//                }
                if ((!(indiceDespues < 0))) {
                    //System.out.println("salto");
                    cantidadSaltados++;
                    continue;
                }
                capturoUnNumero = true;
                //nnnnnnnnnnnnnnnnnnnnn des
                numerosNombre[i] = numero;
                //*******
                numerosNombre[i + 3] = token.length();
                //*******
                if (conUnion && i == 1) {
                    numerosNombre[2] = numerosNombre[1];
                    numerosNombre[1] = numerosNombre[0];
                    numerosNombre[0] = 0;
                    //*************
                    numerosNombre[5] = numerosNombre[4];
                    numerosNombre[4] = numerosNombre[3];
                    numerosNombre[3] = -1;
                    //*************
                    break;
                }
                if (i == 2) {
                    completo = true;
                }
            }
        } catch (Exception e) {
            if (!capturoUnNumero && cantidadSaltados == 0) {
                try {
                    double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(nombre, 0, false, false);
//                    System.out.println(Arrays.toString(numero));
                    if (numero[0] < 1900 && esEntero(numero[0])) {
                        int tem = (int) numero[0], indice = (int) numero[2], formato = (int) numero[1];
                        if (nombre.charAt(indice) == 'x') {
                            numero = buscarNumeroYCantidadDeCaracteresOriginal(nombre, indice + 1, false, false);
                            numerosNombre[0] = tem;
                            numerosNombre[1] = (int) numero[0];
                            numerosNombre[3] = formato;
                            numerosNombre[4] = (int) numero[1];
                            numerosNombre[6] = indice + 1 + (int) numero[1];
//                            System.out.println("nombre="+nombre);
//                            System.out.println("nombre.charAt(numerosNombre[6]="+nombre.charAt(numerosNombre[6]));
//                            if (nombre.charAt(numerosNombre[6]) == '.') {
//                                System.out.println("****************************************");
                            numerosNombre[7] = 0;
//                            }
                            return numerosNombre;
                        }
                        //*********
                        int j[] = MetodosUtiles.containsStringSeparadoIndice(nombre, true, cdv.identificadoresTemporadas);
                        if (j[0] != -1) {
                            for (int i = j[3]; i < nombre.length(); i++) {
                                if (esSeparacion(nombre.charAt(i), cdv)) {
                                    continue;
                                }
                                try {
                                    numero = buscarNumeroYCantidadDeCaracteresOriginal(nombre, i, false, false);
//                                    System.out.println(Arrays.toString(numero));
//                                    System.out.println("tem="+tem);
//                                    System.out.println("formato="+formato);
                                    if (numero[0] < 1900 && esEntero(numero[0])) {
                                        numerosNombre[1] = tem;
                                        numerosNombre[0] = (int) numero[0];
                                        numerosNombre[4] = formato;
                                        numerosNombre[3] = (int) numero[1];
                                        numerosNombre[6] = indice;
                                        return numerosNombre;
//                                        numerosNombre[0] = (int) numero2[0];
//                                        numerosNombre[3] = (int) numero2[1];
                                    }

                                } catch (Exception ex) {

                                }
                                break;
                            }
                        }
                        //*********
//                      
                    }

                } catch (Exception ex) {

                }
            }

            if (numerosNombre[0] != -1 && numerosNombre[1] == -1 && numerosNombre[2] == -1) {
                numerosNombre[1] = numerosNombre[0];
                numerosNombre[0] = 0;
                numerosNombre[4] = numerosNombre[3];
                numerosNombre[3] = -1;
            }

            if (numerosNombre[0] != -1) {
                int j[] = MetodosUtiles.containsStringSeparadoIndice(nombre, true, cdv.identificadoresTemporadas);
                if (j[0] != -1) {
                    for (int i = j[3]; i < nombre.length(); i++) {
                        if (esSeparacion(nombre.charAt(i), cdv)) {
                            continue;
                        }
                        try {
                            double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(nombre, i, false, false);
                            if (numero[0] < 1900 && esEntero(numero[0])) {
                                numerosNombre[1] = numerosNombre[0];
                                numerosNombre[4] = numerosNombre[3];
                                numerosNombre[0] = (int) numero[0];
                                numerosNombre[3] = (int) numero[1];
                            }

                        } catch (Exception ex) {

                        }
                        break;
                    }
                }
            }
        }
        //Obtiniene el indice inicial sumando los formatos de los numeros
        for (int i = 3; i < 6; i++) {
            if (numerosNombre[i] != -1) {
                if (numerosNombre[6] == -1) {
                    numerosNombre[6] = 0;
                }
                numerosNombre[6] += numerosNombre[i] + 1;
            }
        }
        return numerosNombre;
        //return rectificarArregloIntCapitulo(numerosNombre, true);
    }

    /**
     * <ul>
     * <li>[0] temporada</li>
     * <li>[1] capitulo inicial</li>
     * <li>[2] capitulo final en caso de tener mas de uno</li>
     * <li>[3] si==-2 es un solo capitulo de la 1ra Tem y no hay que que poner
     * el 1 de la Tem else ==-1 si hay que ponerlo</li>
     * <li>[4] i del primer numero</li>
     * <li>[5] indice de identificador temporada si contiene</li>
     * <li>[6] indice de identificador capitulo si contiene</li>
     * <li>[7] cantidad de caracteres original de la temporada</li>
     * <li>[8] cantidad de caracteres original del capitulo 1</li>
     * <li>[9] cantidad de caracteres original del capitulo 2</li>
     * </ul>
     *
     * @param nombre
     * @param cdv
     * @return
     */
    public static int[] getCapitulosDeNombreDelFinal(String nombre, ConfiguracionDeVideo cdv) {
        int capitulos[] = {-1, -1, -1, -1, -1, -1, -1, -1, -1, -1}, fort = 7;//[3] si==-2 es un solo capitulo de la 1ra Tem 
        //y no hay que que poner el 1 de la Tem  else
        // ==-1 si hay que ponerlo [4] i del primer numero
//nnnnnnnnnnnnnnnnnnn
        if (nombre.isEmpty()) {
            return capitulos;
        }
        int indiceFormato = 0;
        // LinkedList<Integer> numerosCapitulos = new LinkedList<Integer>();

        boolean utilizoUnion = false;
        int cantidadMovidos = 0, cantidadASaltar = cdv.cantidadASaltarO;// cantidadAntesIngnorar[] = new int[cdv.cantidadAntesIngnorarO.length], cantidadDespuesIngnorar[] = new int[cdv.cantidadDespuesIngnorarO.length];
//        System.arraycopy(cdv.cantidadAntesIngnorarO, 0, cantidadAntesIngnorar, 0, cantidadAntesIngnorar.length);
//        System.arraycopy(cdv.cantidadDespuesIngnorarO, 0, cantidadDespuesIngnorar, 0, cantidadDespuesIngnorar.length);
        int cantidadAMover = cdv.cantidadAMover, indicesAContinuacion[] = new int[]{-1, -1, -1};

        boolean estaEnUnion = false, identificadorTemporada = false, identificadoresCapitulo = false, establecioPorTemporada = false, borradoDeCapitulo = false, establecioPorCapitulo = false;//,identificadoresCantidadCapituloTemporada=false
//        System.out.println("nombre=" + nombre);
        For1:
        for (int i = 0; i < nombre.length(); i++) {
//            System.out.println("i=" + i + " nombre.charAt(i)=" + nombre.charAt(i));
            String separacion = obtenerSeparacion(nombre, i, cdv);
            if (!separacion.isEmpty()) {
//                System.out.println("separacion=" + separacion);
                if (esAleaterizacion(separacion)) {
                    i += separacion.length();
                    continue;
                }
                if (or(separacion.charAt(0), '(', '{', '[')) {
                    separacion = separacion.substring(1);
//                    System.out.println("  2separacion=" + separacion);
//                    System.out.println(Arrays.toString(Videos.identificadoresTemporadas));
                    i++;
                }
                if (separacion.length() > 2 && or(separacion.charAt(separacion.length() - 1), ')', '}', ']')) {
                    separacion = separacion.substring(0, separacion.length() - 1);
//                    System.out.println("  3separacion=" + separacion);
//                    System.out.println(Arrays.toString(Videos.identificadoresTemporadas));
                    i++;
                }
                // int indiceCapitulo=
                //int indiceTemporadas=orIndice(separacion, true, Videos.identificadoresTemporadas);

                if (or(separacion, true, cdv.identificadoresTemporadas)) {
                    capitulos[5] = 0;
                    if (capitulos[0] != -1) {
                        int j[] = encerradoDentroDeIndice(nombre, separacion, cdv.rodearIgnorar);
                        //   System.out.println(Arrays.toString(j));
                        if (j[0] != -1) {

                            // String sub = nombre.substring(j[0], j[2]);
                            //  System.out.println("nombre.substring(j[0], j[2])="+nombre.substring(j[0], j[2]));
                            if (nombre.substring(j[0], j[2]).contains(capitulos[0] + "")) {
                                for (int k = 2; k >= 1; k--) {
                                    if (capitulos[k] != -1) {
                                        int tem = capitulos[0];
                                        capitulos[0] = capitulos[k];
                                        capitulos[k] = tem;
                                        //*************
                                        tem = indicesAContinuacion[0];
                                        indicesAContinuacion[0] = indicesAContinuacion[k];
                                        indicesAContinuacion[k] = tem;
                                        //**************
                                        tem = capitulos[fort];
                                        capitulos[fort] = capitulos[k + fort];
                                        capitulos[k + fort] = tem;
                                    }
                                }
                                //System.out.println(Arrays.toString(capitulos));
                                i = j[2] - 1;
                                establecioPorTemporada = true;
                                continue;
                            }
                        }
                        //*
//                        System.out.println(Arrays.toString(capitulos));
                        for (int k = 2; k >= 0; k--) {
//                            System.out.println("k=" + k);
                            if (capitulos[k] > 0 && capitulos[k] < 10) {
                                int j2 = contieneStringAEnIndiceAArregloStringB(indicesAContinuacion[k], nombre, cdv.terminacionesNumericas[capitulos[k]]);
//                                System.out.println(Arrays.toString(MetodosUtiles.terminacionesNumericas[capitulos[k]]));
//                                System.out.println("j2=" + j2);
                                if (j2 != -1) {
                                    if (k != 0) {
                                        int tem = capitulos[0];
                                        capitulos[0] = capitulos[k];
                                        capitulos[k] = tem;
                                        //*************
                                        tem = indicesAContinuacion[0];
                                        indicesAContinuacion[0] = indicesAContinuacion[k];
                                        indicesAContinuacion[k] = tem;
                                        //**************
                                        tem = capitulos[fort];
                                        capitulos[fort] = capitulos[k + fort];
                                        capitulos[k + fort] = tem;
                                    }
//                                    System.out.println(Arrays.toString(capitulos));
                                    i += separacion.length();
                                    establecioPorTemporada = true;
                                    identificadoresCapitulo = false;
                                    continue For1;
                                }
                            }
                        }
                    }

                    i += separacion.length();
                    identificadorTemporada = true;
                    identificadoresCapitulo = false;
                    continue;
                }
                if (or(separacion, true, cdv.identificadoresCantidadCapituloTemporada)) {
                    capitulos[6] = 0;
                    i += separacion.length();
                    // identificadoresCantidadCapituloTemporada = true;
                    for (int j = 2; j >= 1; j--) {
                        if (capitulos[j] != -1) {
                            capitulos[j] = -1;
                            cantidadAMover++;
                            cantidadMovidos--;
                            borradoDeCapitulo = true;
                            if (j == 1 && capitulos[0] == -1) {
                                capitulos[4] = -1;
                            }
                            break;
                        }
                    }

//                    System.out.println("(!separacion.equalsIgnoreCase(\"cap\")=" + (!separacion.equalsIgnoreCase("cap")));
////                    System.out.println("new File(nombre).isDirectory()="+new File(nombre).isDirectory());
//                    System.out.println("establecioPorTemporada=" + establecioPorTemporada);
//                    System.out.println("Archivo.getExtencion(nombre)="+Archivo.getExtencion(nombre));
//                    System.out.println("Archivo.getExtencion(nombre).isEmpty()="+Archivo.getExtencion(nombre).isEmpty());
                    if ((!separacion.equalsIgnoreCase("cap")) && (!esVideo(nombre)) && (!esSubtitulo(nombre)) && establecioPorTemporada) {
                        capitulos[1] = -1;
                        break;
                    }

                    continue;
                }
                if (or(separacion, true, cdv.identificadoresCapitulo)) {
                    i += separacion.length();
                    identificadoresCapitulo = true;
                    identificadorTemporada = false;
                    for (int j = 3; j >= 1; j--) {
                        if (capitulos[j] != -1) {
                            capitulos[j] = -1;
                            borradoDeCapitulo = true;
                            cantidadAMover++;
                            cantidadMovidos--;
                            //        break;
                        }
                    }
                    if (capitulos[0] == -1) {
                        capitulos[4] = -1;
                    }
                    continue;
                }
            }

            // if(com){}
            try {
                if (nombre.charAt(i) == '.' || nombre.charAt(i) == ',') {
                    continue;
                }
                double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(nombre, i, false, true);

//                int indiceDetencion =  contieneStringAEnIndiceAArregloStringB(i + (int) numero[1], nombre, cdv.despuesIngnorar) ;
//                if ((!(indiceDespues < 0))) {
//                    i += cdv.despuesIngnorar[indiceDespues].length() + (int) numero[1] - 1;
//                    continue;
//                }
                if (or(nombre.charAt(i - 1), 'S', 's') && or(nombre.charAt(i - 2) + "", cdv.separacionString)) {
                    identificadorTemporada = true;
                    identificadoresCapitulo = false;
//                pone
                }
                if (or(nombre.charAt(i - 1), 'E', 'e') && esCharNumero(nombre.charAt(i - 2))) {
                    identificadoresCapitulo = true;
                    identificadorTemporada = false;
                }

                Object O[] = obtenerFechaYcantidadDeCaracteresOriginal(nombre, i);
                if (O != null) {
                    i = (int) O[2] - 1;
                    continue;
                }
//                System.out.println("nombre.charAt(i-1)="+nombre.charAt(i-1));
//                int indiceRodeado[] = rodeadoPor(nombre, i - 1, cdv.rodearIgnorar);
//                if (indiceRodeado[0] != -1) {
//                    i = indiceRodeado[2] - 1;
//                    continue;
//                }
                if (cantidadASaltar-- > 0) {
                    i += (int) numero[1] - 1;
                    throw new Exception();
                }
                //*********************    marca
                int indices[] = cdv.saltarDespues(nombre, i + (int) numero[1], numero);
                if (indices[0] >= 0) {
                    i = indices[2] - 1;
                    continue;
                }

                if (cdv.saltarAntes(nombre, i - 1, numero)) {
                    i += (int) numero[1] - 1;
                    continue;
                }
                //******************
                int indiceDespues = cdv.despuesIngnorar.length > 0 && (i + (int) numero[1]) < nombre.length() ? contieneStringAEnIndiceAArregloStringB(i + (int) numero[1], nombre, cdv.despuesIngnorar) : -1;
                if ((!(indiceDespues < 0))) {
                    i += cdv.despuesIngnorar[indiceDespues].length() + (int) numero[1] - 1;
                    continue;
                }
                if (i == 0 || i == 1) {
                    break;
                }
                int indiceAnterior = cdv.antesIngnorar.length > 0 ? contieneStringAEnIndiceAArregloStringBInverso(i - 1, nombre, cdv.antesIngnorar) : -1;
                if ((!(indiceAnterior < 0))) {
//                    System.out.println("cdv.antesIngnorar[indiceAnterior]="+cdv.antesIngnorar[indiceAnterior]);
                    i += (int) numero[1] - 1;
                    continue;
                }

//                if ((!(indiceDespues < 0)) && cantidadDespuesIngnorar[indiceDespues]-- > 0) {
//                    i += cdv.despuesIngnorar[indiceDespues].length() + (int) numero[1] - 1;
//                    continue;
//                }
                if (((nombre.charAt(i - 1) == 'x') && esCharNumero(nombre.charAt(i - 2))) || (nombre.charAt(i - 1) == 'E' && i > 5 && (nombre.charAt(i - 2) == 'X') && esCharNumero(nombre.charAt(i - 3)))) {
                    boolean ponerEnTemporada = true;
                    for (int j = 2; j > 0; j--) {
                        if (capitulos[j] != -1) {
                            if (ponerEnTemporada) {
                                ponerEnTemporada = false;
                                capitulos[0] = capitulos[j];
                                indicesAContinuacion[0] = indicesAContinuacion[j];
                                borradoDeCapitulo = true;
                                //**************
                                capitulos[fort] = capitulos[j + fort];
                            } else {
                                cantidadAMover++;
                                cantidadMovidos--;
                            }
                            capitulos[j] = -1;

                        }
                    }
//                     System.out.println("2 cap=" + Arrays.toString(capitulos));
                    capitulos[1] = (int) numero[0];
                    establecioPorCapitulo = true;
                    indicesAContinuacion[1] = (int) numero[2];
                    //**************
                    capitulos[fort + 1] = (int) numero[1];

                    if (ponerEnTemporada) {
                        double numeroAnterior[] = null;
                        if (esCharNumero(nombre.charAt(i - 2))) {
                            numeroAnterior = MetodosUtiles.buscarNumeroYCantidadDeCaracteresOriginalInverso(nombre, i - 2, false, true);
                        } else {
                            numeroAnterior = MetodosUtiles.buscarNumeroYCantidadDeCaracteresOriginalInverso(nombre, i - 3, false, true);
                        }

//                        capitulos[0] = (int) numero[0];
//                        //**************
//                        capitulos[fort] = (int) numero[1];
                        capitulos[0] = (int) numeroAnterior[0];
                        //**************
                        capitulos[fort] = (int) numeroAnterior[1];
                    }
                    establecioPorTemporada = true;

                    if (establecioPorTemporada && establecioPorCapitulo) {
                        break;
                    }

                    i += (int) numero[1] - 1;
                    continue;
                }

                if (numero[0] > 1900 && !identificadoresCapitulo) {
                    i += 3;
                    continue;
                }

                if (capitulos[4] == -1) {
                    capitulos[4] = i;
//                    if (soloParaConocerElIndice) {
//                        return capitulos;
//                    }
                }
                if (numero[0] > 100 && numero[0] < 1000) {
                    String anteiror = nombre.substring(0, i);
                    int I = (int) numero[2];
                    int temp = Integer.parseInt((((int) numero[0]) + "").charAt(0) + "");
                    int cap = Integer.parseInt((((int) numero[0]) + "").substring(1));
//                    System.out.println("nombre.charAt(I)="+nombre.charAt(I));
                    if (anteiror.contains("1080p") || anteiror.contains("720p")
                            && (I == nombre.length() || I == nombre.length() - 1 || I == nombre.length() - 2 || nombre.charAt(I - 1) == '.' || nombre.charAt(I) == '.' || nombre.charAt(I + 1) == '.')) {
                        if (capitulos[0] != temp) {
                            capitulos[0] = temp;
                            //*************
                            indicesAContinuacion[0] = i;
                            //**************
                            capitulos[fort] = 1;
                        }
                        for (int j = 3; j >= 1; j--) {
                            if (capitulos[j] != -1) {
                                capitulos[j] = -1;
                                borradoDeCapitulo = true;
                                cantidadAMover++;
                                cantidadMovidos--;
                                //        break;
                            }
                        }
//                        System.out.println("cap=" + cap);
                        establecioPorCapitulo = true;
                        capitulos[1] = cap;
                        //*************
                        indicesAContinuacion[0] = i + 1;
                        //**************
                        capitulos[fort] = 2;
                        establecioPorTemporada = true;
                        i += 2;
//                        continue; //*******************************************
                        break;
                    }
                }

                boolean ignorarUnion = false;
                int indiceUnion = cdv.union.length > 0 && (i + (int) numero[1]) < nombre.length() ? contieneStringAEnIndiceAArregloStringB(i + (int) numero[1], nombre, cdv.union) : -1;
                if (!(indiceUnion < 0)) {
                    if (nombre.charAt(i - 1) == ' ') {
                        i += cdv.union[indiceUnion].length();
                        estaEnUnion = (ignorarUnion = true);
                    }

                }

//                  System.out.println("estaEnUnion="+estaEnUnion);
                if (cantidadAMover > 0) {
                    if (!condicional(estaEnUnion, ignorarUnion)) {
                        utilizoUnion = true;
                    }
                    if (identificadorTemporada) {
                        identificadorTemporada = false;
                        if (capitulos[0] != -1) {
                            for (int j = 1; j < 3; j++) {
                                if (capitulos[j] == -1) {
                                    capitulos[j] = capitulos[0];
                                    //*************
                                    indicesAContinuacion[j] = indicesAContinuacion[0];
                                    //**************
                                    capitulos[fort + j] = capitulos[fort];
                                    break;
                                }
                            }
                        }
                        capitulos[0] = (int) numero[0];
                        //******
                        indicesAContinuacion[0] = (int) numero[2];
                        establecioPorTemporada = true;
                        //**************
                        capitulos[fort] = (int) numero[1];
                    } else {
                        //int min = 0, max = 3;
                        if (identificadoresCapitulo) {
                            establecioPorCapitulo = true;
                            identificadoresCapitulo = false;
                            capitulos[1] = (int) numero[0];
                            //******
                            indicesAContinuacion[1] = (int) numero[2];
                            //**************
                            capitulos[fort + 1] = (int) numero[1];
                        } else {
                            for (int j = 0; j < 3; j++) {
                                if (capitulos[j] == -1) {
                                    capitulos[j] = (int) numero[0];
                                    //******
                                    indicesAContinuacion[j] = (int) numero[2];
                                    //**************
                                    capitulos[fort + j] = (int) numero[1];
                                    break;
                                }
                            }
                        }
                    }
                    if (establecioPorCapitulo && establecioPorTemporada) {
                        break;
                    }
//                    System.out.println("cap=" + Arrays.toString(capitulos));

                    // numerosCapitulos.add((int) numero[0]);
                    if (!condicional(estaEnUnion, ignorarUnion)) {
                        estaEnUnion = false;
                    }
                    if (estaEnUnion && ignorarUnion) {
                        cantidadAMover++;
                    }
                }
                if (--cantidadAMover > 0) {
                    i += (int) numero[1] - 1;
                    cantidadMovidos++;

                } else {
                    cantidadMovidos++;
                    break;
                }

            } catch (Exception e) {
                estaEnUnion = false;
                int indiceDetencion = contieneStringAEnIndiceAArregloStringB(i, nombre, cdv.detencionesAbsolutas);
                if ((!(indiceDetencion < 0))) {
                    i += cdv.detencionesAbsolutas[indiceDetencion].length() - 1;
                    break;
//                    continue;
                }
                //identificadorTemporada = false;
                //  identificadoresCapitulo = false;
                continue;
            }

        }

//        if (numerosCapitulos.size() == 1 || (numerosCapitulos.size() == 2 && utilizoUnion)) {
//            capitulos[3] = -2;
//        }
        if (capitulos[0] == -1 && capitulos[1] != -1) {
            capitulos[0] = 0;
            capitulos[3] = -2;
        }
        if (capitulos[0] != -1 && capitulos[1] != -1 && capitulos[2] == -1 && utilizoUnion) {
            capitulos[2] = capitulos[1];
            capitulos[1] = capitulos[0];
            capitulos[0] = 0;
            capitulos[3] = -2;

            //**************
            capitulos[fort + 2] = capitulos[fort + 1];
            capitulos[fort + 1] = capitulos[fort];
        }
        if (capitulos[0] != -1 && capitulos[1] == -1 && capitulos[2] == -1) {
            if (!establecioPorTemporada && !borradoDeCapitulo) {
                capitulos[1] = capitulos[0];
                capitulos[0] = 0;
                capitulos[3] = -2;
                //**************
                capitulos[fort + 1] = capitulos[fort];
                capitulos[fort] = -1;
            } else {
                capitulos[1] = -1;
            }
        }
        if (capitulos[1] != -1 && capitulos[2] != -1 && capitulos[1] > capitulos[2]) {
            capitulos[1] = -1;
            capitulos[2] = -1;
        }

        //nnnnnnnnnnnnnnnn
        //capitulos = rectificarArregloIntCapitulo(capitulos);
//        System.out.println("capi1=" + Arrays.toString(capitulos));
        return capitulos;
    }
//    public static int[] getCapitulosDeNombreDelFinal(String nombre, ConfiguracionDeVideo cdv) {
//        int[] capitulos = {-1, -1, -1, -1, -1};//[3] si==-2 es un solo capitulo de la 1ra Tem 
//        //y no hay que que poner el 1 de la Tem  else
//        // ==-1 si hay que ponerlo [4] i del primer numero
////nnnnnnnnnnnnnnnnnnn
//        if (nombre.isEmpty()) {
//            return capitulos;
//        }
//        int indiceFormato = 0;
//        // LinkedList<Integer> numerosCapitulos = new LinkedList<Integer>();
//
//        boolean utilizoUnion = false;
//        int cantidadMovidos = 0, cantidadASaltar = cdv.cantidadASaltarO, cantidadAntesIngnorar[] = new int[cdv.cantidadAntesIngnorarO.length], cantidadDespuesIngnorar[] = new int[cdv.cantidadDespuesIngnorarO.length];
//        System.arraycopy(cdv.cantidadAntesIngnorarO, 0, cantidadAntesIngnorar, 0, cantidadAntesIngnorar.length);
//        System.arraycopy(cdv.cantidadDespuesIngnorarO, 0, cantidadDespuesIngnorar, 0, cantidadDespuesIngnorar.length);
//        int cantidadAMover = cdv.cantidadAMover, indicesAContinuacion[] = new int[]{-1, -1, -1};
//
//        boolean estaEnUnion = false, identificadorTemporada = false, identificadoresCapitulo = false, estblecioPorTemporada = false, borradoDeCapitulo = false;//,identificadoresCantidadCapituloTemporada=false
////        System.out.println("nombre=" + nombre);
//        For1:
//        for (int i = 0; i < nombre.length(); i++) {
////            System.out.println("i=" + i + " nombre.charAt(i)=" + nombre.charAt(i));
//            String separacion = obtenerSeparacion(nombre, i);
//            if (!separacion.isEmpty()) {
//                //  System.out.println("separacion="+separacion);
//                if (esAleaterizacion(separacion)) {
//                    i += separacion.length();
//                    continue;
//                }
//                if (or(separacion.charAt(0), '(', '{', '[')) {
//                    separacion = separacion.substring(1);
//////                    System.out.println("  2separacion=" + separacion);
////                    System.out.println(Arrays.toString(Videos.identificadoresTemporadas));
//                    i++;
//                }
//                if (separacion.length() > 2 && or(separacion.charAt(separacion.length() - 1), ')', '}', ']')) {
//                    separacion = separacion.substring(0, separacion.length() - 1);
////                    System.out.println("  3separacion=" + separacion);
////                    System.out.println(Arrays.toString(Videos.identificadoresTemporadas));
//                    i++;
//                }
//                // int indiceCapitulo=
//                //int indiceTemporadas=orIndice(separacion, true, Videos.identificadoresTemporadas);
//
//                if (or(separacion, true, cdv.identificadoresTemporadas)) {
//                    if (capitulos[0] != -1) {
//                        int j[] = encerradoDentroDeIndice(nombre, separacion, cdv.rodearIgnorar);
//                        //   System.out.println(Arrays.toString(j));
//                        if (j[0] != -1) {
//                            // String sub = nombre.substring(j[0], j[2]);
//                            //  System.out.println("nombre.substring(j[0], j[2])="+nombre.substring(j[0], j[2]));
//                            if (nombre.substring(j[0], j[2]).contains(capitulos[0] + "")) {
//                                for (int k = 2; k >= 1; k--) {
//                                    if (capitulos[k] != -1) {
//                                        int tem = capitulos[0];
//                                        capitulos[0] = capitulos[k];
//                                        capitulos[k] = tem;
//                                        //*************
//                                        tem = indicesAContinuacion[0];
//                                        indicesAContinuacion[0] = indicesAContinuacion[k];
//                                        indicesAContinuacion[k] = tem;
//                                    }
//                                }
//                                //System.out.println(Arrays.toString(capitulos));
//                                i = j[2] - 1;
//                                estblecioPorTemporada = true;
//                                continue;
//                            }
//                        }
//                        //*
////                        System.out.println(Arrays.toString(capitulos));
//                        for (int k = 2; k >= 0; k--) {
////                            System.out.println("k=" + k);
//                            if (capitulos[k] > 0 && capitulos[k] < 10) {
//                                int j2 = contieneStringAEnIndiceAArregloStringB(indicesAContinuacion[k], nombre, cdv.terminacionesNumericas[capitulos[k]]);
////                                System.out.println(Arrays.toString(MetodosUtiles.terminacionesNumericas[capitulos[k]]));
////                                System.out.println("j2=" + j2);
//                                if (j2 != -1) {
//                                    if (k != 0) {
//                                        int tem = capitulos[0];
//                                        capitulos[0] = capitulos[k];
//                                        capitulos[k] = tem;
//                                        //*************
//                                        tem = indicesAContinuacion[0];
//                                        indicesAContinuacion[0] = indicesAContinuacion[k];
//                                        indicesAContinuacion[k] = tem;
//                                    }
////                                    System.out.println(Arrays.toString(capitulos));
//                                    i += separacion.length();
//                                    estblecioPorTemporada = true;
//                                    continue For1;
//                                }
//                            }
//                        }
//                    }
//
//                    i += separacion.length();
//                    identificadorTemporada = true;
//                    continue;
//                }
//                if (or(separacion, true, cdv.identificadoresCantidadCapituloTemporada)) {
//                    i += separacion.length();
//                    // identificadoresCantidadCapituloTemporada = true;
//                    for (int j = 2; j >= 1; j--) {
//                        if (capitulos[j] != -1) {
//                            capitulos[j] = -1;
//                            cantidadAMover++;
//                            cantidadMovidos--;
//                            borradoDeCapitulo = true;
//                            if (j == 1 && capitulos[0] == -1) {
//                                capitulos[4] = -1;
//                            }
//                            break;
//                        }
//                    }
//
//                    continue;
//                }
//                if (or(separacion, true, cdv.identificadoresCapitulo)) {
//                    i += separacion.length();
//                    identificadoresCapitulo = true;
//                    for (int j = 3; j >= 1; j--) {
//                        if (capitulos[j] != -1) {
//                            capitulos[j] = -1;
//                            borradoDeCapitulo = true;
//                            cantidadAMover++;
//                            cantidadMovidos--;
//                            //        break;
//                        }
//                    }
//                    if (capitulos[0] == -1) {
//                        capitulos[4] = -1;
//                    }
//                    continue;
//                }
//            }
//
//            // if(com){}
//            try {
//                double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(nombre, i, false, false);
//
//                Object O[] = obtenerFechaYcantidadDeCaracteresOriginal(nombre, i);
//                if (O != null) {
//                    i = (int) O[2] - 1;
//                    continue;
//                }
//                int indiceRodeado[] = rodeadoPor(nombre, i, cdv.rodearIgnorar);
//                if (indiceRodeado[0] != -1) {
//                    i = indiceRodeado[2] - 1;
//                    continue;
//                }
//                if (cantidadASaltar-- > 0) {
//                    i += (int) numero[1] - 1;
//                    throw new Exception();
//                }
//
//                if (i == 0 || i == 1) {
//                    break;
//                }
//                int indiceAnterior = cdv.antesIngnorar.length > 0 ? contieneStringAEnIndiceAArregloStringBInverso(i - 1, nombre, cdv.antesIngnorar) : -1;
//                if ((!(indiceAnterior < 0)) && cantidadAntesIngnorar[indiceAnterior]-- > 0) {
//                    i += (int) numero[1] - 1;
//                    continue;
//                }
//                int indiceDespues = cdv.despuesIngnorar.length > 0 && (i + (int) numero[1]) < nombre.length() ? contieneStringAEnIndiceAArregloStringB(i + (int) numero[1], nombre, cdv.despuesIngnorar) : -1;
//
//                if ((!(indiceDespues < 0)) && cantidadDespuesIngnorar[indiceDespues]-- > 0) {
//                    i += cdv.despuesIngnorar[indiceDespues].length() + (int) numero[1] - 1;
//                    continue;
//                }
//
//                if (numero[0] > 1900) {
//                    i += 3;
//                    continue;
//                }
//                if (capitulos[4] == -1) {
//                    capitulos[4] = i;
////                    if (soloParaConocerElIndice) {
////                        return capitulos;
////                    }
//                }
//
//                boolean ignorarUnion = false;
//                int indiceUnion = cdv.union.length > 0 && (i + (int) numero[1]) < nombre.length() ? contieneStringAEnIndiceAArregloStringB(i + (int) numero[1], nombre, cdv.union) : -1;
//                if (!(indiceUnion < 0)) {
//                    i += cdv.union[indiceUnion].length();
//                    estaEnUnion = (ignorarUnion = true);
//                }
//                //  System.out.println("estaEnUnion="+estaEnUnion);
//                if (cantidadAMover > 0) {
//                    if (!condicional(estaEnUnion, ignorarUnion)) {
//                        utilizoUnion = true;
//                    }
//                    if (identificadorTemporada) {
//                        identificadorTemporada = false;
//                        if (capitulos[0] != -1) {
//                            for (int j = 1; j < 3; j++) {
//                                if (capitulos[j] == -1) {
//                                    capitulos[j] = capitulos[0];
//                                    //*************
//                                    indicesAContinuacion[j] = indicesAContinuacion[0];
//                                    break;
//                                }
//                            }
//                        }
//                        capitulos[0] = (int) numero[0];
//                        //******
//                        indicesAContinuacion[0] = (int) numero[2];
//                        estblecioPorTemporada = true;
//                    } else {
//                        //int min = 0, max = 3;
//                        if (identificadoresCapitulo) {
//                            identificadoresCapitulo = false;
//                            capitulos[1] = (int) numero[0];
//                            //******
//                            indicesAContinuacion[1] = (int) numero[2];
//                        } else {
//                            for (int j = 0; j < 3; j++) {
//                                if (capitulos[j] == -1) {
//                                    capitulos[j] = (int) numero[0];
//                                    //******
//                                    indicesAContinuacion[j] = (int) numero[2];
//                                    break;
//                                }
//                            }
//                        }
//                    }
////                    System.out.println("cap="+Arrays.toString(capitulos));
//
//                    // numerosCapitulos.add((int) numero[0]);
//                    if (!condicional(estaEnUnion, ignorarUnion)) {
//                        estaEnUnion = false;
//                    }
//                    if (estaEnUnion && ignorarUnion) {
//                        cantidadAMover++;
//                    }
//                }
//                if (--cantidadAMover > 0) {
//                    i += (int) numero[1] - 1;
//                    cantidadMovidos++;
//
//                } else {
//                    cantidadMovidos++;
//                    break;
//                }
//
//            } catch (Exception e) {
//                estaEnUnion = false;
//                //identificadorTemporada = false;
//                //  identificadoresCapitulo = false;
//                continue;
//            }
//
//        }
//
////        if (numerosCapitulos.size() == 1 || (numerosCapitulos.size() == 2 && utilizoUnion)) {
////            capitulos[3] = -2;
////        }
//        if (capitulos[0] == -1 && capitulos[1] != -1) {
//            capitulos[0] = 0;
//            capitulos[3] = -2;
//        }
//        if (capitulos[0] != -1 && capitulos[1] != -1 && capitulos[2] == -1 && utilizoUnion) {
//            capitulos[2] = capitulos[1];
//            capitulos[1] = capitulos[0];
//            capitulos[0] = 0;
//            capitulos[3] = -2;
//        }
//        if (capitulos[0] != -1 && capitulos[1] == -1 && capitulos[2] == -1) {
//            if (!estblecioPorTemporada && !borradoDeCapitulo) {
//                capitulos[1] = capitulos[0];
//                capitulos[0] = 0;
//                capitulos[3] = -2;
//            } else {
//                capitulos[1] = -1;
//            }
//        }
//        if (capitulos[1] != -1 && capitulos[2] != -1 && capitulos[1] > capitulos[2]) {
//            capitulos[1] = -1;
//            capitulos[2] = -1;
//        }
//
//        //nnnnnnnnnnnnnnnn
//        //capitulos = rectificarArregloIntCapitulo(capitulos);
//        // System.out.println("capi1=" + Arrays.toString(capitulos));
//        return capitulos;
//    }
// private static int[] getCapitulosDeNombreDelFinal(String nombre, ConfiguracionDeVideo cdv, boolean soloParaElIndice) {
//        int[] capitulos = {-1, -1, -1, -1, -1};//[3] si==-2 es un solo capitulo de la 1ra Tem 
//        //y no hay que que poner el 1 de la Tem  else
//        // ==-1 si hay que ponerlo [4] i del primer numero
////nnnnnnnnnnnnnnnnnnn
//        if (nombre.isEmpty()) {
//            return capitulos;
//        }
//        int indiceFormato = 0;
//        LinkedList<Integer> numerosCapitulos = new LinkedList<Integer>();
//        boolean utilizoUnion = false;
//        int cantidadMovidos = 0, cantidadASaltar = cdv.cantidadASaltarO, cantidadAntesIngnorar[] = new int[cdv.cantidadAntesIngnorarO.length], cantidadDespuesIngnorar[] = new int[cdv.cantidadDespuesIngnorarO.length];
//        System.arraycopy(cdv.cantidadAntesIngnorarO, 0, cantidadAntesIngnorar, 0, cantidadAntesIngnorar.length);
//        System.arraycopy(cdv.cantidadDespuesIngnorarO, 0, cantidadDespuesIngnorar, 0, cantidadDespuesIngnorar.length);
//        int cantidadAMover = cdv.cantidadAMover;
//        boolean estaEnUnion = false;
//        for (int i = 0; i < nombre.length(); i++) {
//            //  System.out.println("i=" + i + " nombre.charAt(i)=" + nombre.charAt(i));
//            String separacion = obtenerSeparacion(nombre, i);
//            if (!separacion.isEmpty() && esAleaterizacion(separacion)) {
//                i += separacion.length();
//                continue;
//            }
//            // if(com){}
//            try {
//                double numero[] = buscarNumeroYCantidadDeCaracteresOriginal(nombre, i, false, false);
//                //  System.out.println("num=" + Arrays.toString(numero));
//                Object O[] = obtenerFechaYcantidadDeCaracteresOriginal(nombre, i);
//                if (O != null) {
//                    i = (int) O[2] - 1;
//                    continue;
//                }
//                int indiceRodeado[] = rodeadoPor(nombre, i, cdv.rodearIgnorar);
//                if (indiceRodeado[0] != -1) {
//                    i = indiceRodeado[2] - 1;
//                    continue;
//                }
//                if (cantidadASaltar-- > 0) {
//                    i += (int) numero[1] - 1;
//                    throw new Exception();
//                }
//                if (i == 0 || i == 1 || i == 2) {
//                    break;
//                }
//
//                int indiceAnterior = cdv.antesIngnorar.length > 0 ? contieneStringAEnIndiceAArregloStringBInverso(i - 1, nombre, cdv.antesIngnorar) : -1;
//                if ((!(indiceAnterior < 0)) && cantidadAntesIngnorar[indiceAnterior]-- > 0) {
//                    i += (int) numero[1] - 1;
//                    continue;
//                }
//                int indiceDespues = cdv.despuesIngnorar.length > 0 && (i + (int) numero[1]) < nombre.length() ? contieneStringAEnIndiceAArregloStringB(i + (int) numero[1], nombre, cdv.despuesIngnorar) : -1;
//
//                if ((!(indiceDespues < 0)) && cantidadDespuesIngnorar[indiceDespues]-- > 0) {
//                    i += cdv.despuesIngnorar[indiceDespues].length() + (int) numero[1] - 1;
//                    continue;
//                }
//
//                if (numero[0] > 1900) {
//                    i += 3;
//                    continue;
//                }
//                if (capitulos[4] == -1) {
//                    capitulos[4] = i;
//                    if (soloParaElIndice) {
//                        return capitulos;
//                    }
//                }
//
//                boolean ignorarUnion = false;
//                int indiceUnion = union.length > 0 && (i + (int) numero[1]) < nombre.length() ? contieneStringAEnIndiceAArregloStringB(i + (int) numero[1], nombre, union) : -1;
//                if (!(indiceUnion < 0)) {
//                    i += union[indiceUnion].length();
//                    estaEnUnion = (ignorarUnion = true);
//                }
//                // System.out.println("estaEnUnion="+estaEnUnion);
//                if (cantidadAMover > 0) {
//                    if (!condicional(estaEnUnion, ignorarUnion)) {
//                        utilizoUnion = true;
//                    }
//                    numerosCapitulos.add((int) numero[0]);
//                    if (!condicional(estaEnUnion, ignorarUnion)) {
//                        estaEnUnion = false;
//                    }
//                    if (estaEnUnion && ignorarUnion) {
//                        cantidadAMover++;
//                    }
//                }
//                if (--cantidadAMover > 0) {
//                    i += (int) numero[1] - 1;
//                    cantidadMovidos++;
//
//                } else {
//                    cantidadMovidos++;
//                    break;
//                }
//
//            } catch (Exception e) {
//                estaEnUnion = false;
//                continue;
//            }
//
//        }
//        //System.out.println("capi1="+Arrays.toString(capitulos));
////        if (numerosCapitulos.size() == 1) {
////            capitulos[0] = 1;
////            capitulos[1] = numerosCapitulos.get(0);
////            capitulos[3] = -2;
////        } else if (numerosCapitulos.size() == 2) {
////            if (utilizoUnion) {
////                capitulos[0] = 1;
////                capitulos[1] = numerosCapitulos.get(0);
////                capitulos[2] = numerosCapitulos.get(1);
////                capitulos[3] = -2;
////            } else {
////                capitulos[0] = numerosCapitulos.get(0);
////                capitulos[1] = numerosCapitulos.get(1);
////            }
////
////        } else if (numerosCapitulos.size() > 2) {
////            capitulos[0] = numerosCapitulos.get(0);
////            capitulos[1] = numerosCapitulos.get(1);
////            capitulos[2] = numerosCapitulos.get(2);
////        }
//        //nnnnnnnnnnnnnn
//        for (int i = 0; i < numerosCapitulos.size(); i++) {
//            capitulos[i] = numerosCapitulos.get(i);
//        }
//        if (numerosCapitulos.size() == 1 || (numerosCapitulos.size() == 2 && utilizoUnion)) {
//            capitulos[3] = -2;
//        }
//        //nnnnnnnnnnnnnnnn
//        capitulos = rectificarArregloIntCapitulo(capitulos);
//        // System.out.println("capi1=" + Arrays.toString(capitulos));
//        return capitulos;
//    }

    private static int[] rectificarArregloIntCapitulo(int capitulos[]) {
        return rectificarArregloIntCapitulo(capitulos, false);
    }

    private static int[] rectificarArregloIntCapitulo(int capitulos[], boolean coordinar) {
        if (capitulos[0] != -1 && capitulos[1] == -1) {
            capitulos[1] = capitulos[0];
            capitulos[0] = 1;
            if (coordinar) {
                capitulos[4] = capitulos[3];
                capitulos[3] = -1;
            }
        }
        return capitulos;
    }

    public static boolean esVideoNombre(String nombre) {
        return Arrays.asList(extencionVideo.extencionesVideos()).contains(getExtencion(nombre.toLowerCase()));
    }

    public static boolean esVideoExtencion(String extencion) {
        return Arrays.asList(extencionVideo.extencionesVideos()).contains(extencion.toLowerCase());
    }

    public static boolean esVideo(String nombre) {
        return esVideo(new File(nombre));
    }

    public static boolean esVideo(File f) {
        return Arrays.asList(extencionVideo.extencionesVideos()).contains(getExtencion(f).toLowerCase());
    }

    public static boolean esSubtituloNombre(String nombre) {
        return Arrays.asList(extencionSubtitulo.extencionesSubtitulo()).contains(getExtencion(nombre.toLowerCase()));
    }

    public static boolean esSubtituloExtencion(String extencion) {
        return Arrays.asList(extencionSubtitulo.extencionesSubtitulo()).contains(extencion.toLowerCase());
    }

    public static boolean esSubtitulo(String nombre) {
        return esSubtitulo(new File(nombre));
    }

    public static boolean esSubtitulo(File f) {
        return Arrays.asList(extencionSubtitulo.extencionesSubtitulo()).contains(getExtencion(f).toLowerCase());
    }

    public static void activar(String direccion) {
        activar(direccion, false);
    }

    public static void activar(String direccion, boolean carpetasInternas) {
        activar(new File(direccion), carpetasInternas);
    }

    public static void activar(File f) {
        activar(f, false);
    }

    public static void activar(File f, boolean carpetasInternas) {
        if (f.isDirectory()) {
            String contenido[] = f.list();
            for (String c : contenido) {
                File c2 = new File(f + "/" + c);
                if (!carpetasInternas && c2.isDirectory()) {
                    continue;
                }
                activar(c2, carpetasInternas);

            }
        } else {

            String extencion = getExtencion(f).toLowerCase();
            if (!extencion.isEmpty() && !esVideoExtencion(extencion) && (extencion = getExtencionActivada(extencion)).length() > 2) {
                sustituirExtencionReal(f, extencion);
            }

        }
    }

    /**
     * ejemplo de ".mp4" podria ser ".mp" si no la encuentra devuelve ""
     *
     * @param extencion
     * @return
     */
    private static String getExtencionDesactivada(String extencion) {
        int indice = Arrays.asList(extencionVideo.extencionesVideos()).indexOf(extencion);
        return indice < 0 ? "" : extencionVideo.values()[indice].getExtencionDesactivada();
    }

    /**
     * ejemplo de ".mp" podria ser ".mp4" si no la encuentra devuelve ""
     *
     * @param extencionDesactivada
     * @return
     */
    private static String getExtencionActivada(String extencionDesactivada) {
        int indice = Arrays.asList(extencionVideo.extencionesVideos_DESACTIVADO()).indexOf(extencionDesactivada);
        return indice < 0 ? "" : extencionVideo.values()[indice].getExtencion();
    }

    public static void desactivar(String direccion) {
        desactivar(direccion, false);
    }

    public static void desactivar(String direccion, boolean carpetasInternas) {
        desactivar(new File(direccion), carpetasInternas);
    }

    public static void desactivar(File f) {
        desactivar(f, false);
    }

    public static void desactivar(File f, boolean carpetasInternas) {
        if (f.isDirectory()) {
            String contenido[] = f.list();
            for (String c : contenido) {
                File c2 = new File(f + "/" + c);
                if (!carpetasInternas && c2.isDirectory()) {
                    continue;
                }
                desactivar(c2, carpetasInternas);

            }
        } else {
            String extencion = getExtencion(f).toLowerCase();
//            System.out.println("extencion ="+extencion );
//            System.out.println("getExtencionDesactivada(extencion)="+getExtencionDesactivada(extencion));
//            System.out.println("!extencion.isEmpty()="+!extencion.isEmpty());
//            System.out.println("esVideoExtencion(extencion)="+esVideoExtencion(extencion));
//            System.out.println("getExtencionDesactivada(extencion).length() > 2="+((getExtencionDesactivada(extencion)).length() > 2));
            if (!extencion.isEmpty() && esVideoExtencion(extencion) && (extencion = getExtencionDesactivada(extencion)).length() > 0) {
                sustituirExtencionReal(f, extencion);
            }

        }

    }

}
