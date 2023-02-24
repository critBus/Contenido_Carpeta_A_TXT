/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaMangas.*;
//import static Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaMangas.CarpetaMangas.ONLINE;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.*;
//import static Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.CarpetaSeries.DOBLADAS;
import static Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.CarpetaSeries.ESPANOL;
import static Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.CarpetaSeries.invalidosSerie;
import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import Utiles.MetodosUtiles.Videos;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.StringTokenizer;
import java.util.TreeSet;

/**
 * Version 0.3
 *
 * @author Rene
 */
public class Paquete {//P1080= "1080p" , K4= "4k" , P720= "720p"  //P1080 , K4 , P720

//    public static final String FALTARON="faltaron",QUE="que",PENDIENTES="pendientes",FIN = "fin", FICCION = "ficcion", DRAMA = "drama", TERROR = "terror", SUSP = "susp", SUSPENSOS = "suspensos", SUSPENSO = "suspenso", MUNNES = "mu�es", MUÑES = "muñes", CIENCIA = "ciencia", C = "c", AVENTURAS = "aventuras", ACCION = "accion", ESPANNOL = "espa�ol", CONVERTIR = "convertir", P1080 = "1080p", K4 = "4k", P720 = "720p", LATINAS = "Latinas", ONLINE = "Online", ACTORES = "actores", ACTUALIZACION = "actualizacion", ACTUALIZADA = "actualizada", ACTUALIZADOS = "actualizados", AL = "al", ANDROID = "android", ANIMADOS = "animados", ANIME = "anime", ANTIVIRUS = "antivirus", ANUNCIOS = "anuncios", APLICACIONES = "aplicaciones", AVI = "avi", CAP = "cap", CLASICAS = "clasicas", CLASICOS = "clasicos", CLASIFICADOS = "clasificados", CLIP = "clip", COMICS = "comics", CON = "con", CRISTIANA = "cristiana", CUBA = "cuba", DE = "de", DEL = "del", DEPORTE = "deporte", DISCOGRAFIA = "discografia", DOBLADAS = "dobladas", DOCUMENTAL = "documental", DOCUMENTALES = "documentales", DORAMAS = "doramas", EN = "en", ESPAÑOL = "español", ESPAÑOLA = "española", ESPAÑOLAS = "españolas", ESTRENO = "estreno", ESTRENOS = "estrenos", EX = "ex", EXCLUSIVA = "exclusiva", FESTIVAL = "festival", FILMES = "filmes", FINALIZADAS = "finalizadas", FPS = "fps", GAMES = "games", GAMESPLAYS = "gamesplays", GENERO = "genero", GRAFICAS = "graficas", HD = "hd", HISTORIETAS = "historietas", HUMOR = "humor", INGLES = "ingles", INTERESANTES = "interesantes", IOS = "ios", JUEGOS = "juegos", JUVENTUD = "juventud", KAV = "kav", LA = "la", LATINA = "latina", LATINOS = "latinos", LINUX = "linux", MAC = "mac", MANGAS = "mangas", MAR = "mar", MINISERIES = "miniseries", MKV = "mkv", MOVILES = "moviles", MP3 = "mp3", MP4 = "mp4", MUSICA = "musica", MUSICALES = "musicales", NOD32 = "nod32", NOVELAS = "novelas", ORGANIZADAS = "organizadas", PAIS = "pais", PARA = "para", PC = "pc", PELICULAS = "peliculas", PHONE = "phone", POR = "por", PREMIOS = "premios", PROGRAMAS = "programas", REALITY = "reality", REVISTAS = "revistas", SECCION = "seccion", SEG = "seg", SEMANA = "semana", SEMANAL = "semanal", SERIES = "series", SHOW = "show", SHOWS = "shows", SITIOS = "sitios", SOFTWARES = "softwares", SUB = "sub", SUBTITULADAS = "subtituladas", SUBTITULADOS = "subtitulados", SUBTITULO = "subtitulo", SUBTITULOS = "subtitulos", SUELTOS = "sueltos", TEMPORADA = "temporada", TEMPORADAS = "temporadas", TRAILERS = "trailers", TRANSMISION = "transmision", TUTORIALES = "tutoriales", TV = "tv", TX = "tx", VARIADOS = "variados", VIDEOS = "videos", VIDEOS2BRAIN = "videos2brain", VIÑA = "viña", WALLPAPERS = "wallpapers", WINDOW = "window", WINDOWS = "windows", X = "x", Y = "y";
//    public static final String invalidosPaquete[] = {FICCION, DRAMA, TERROR, SUSP, SUSPENSOS, SUSPENSO, MUNNES, MUÑES, CIENCIA, C, AVENTURAS, ACCION, ESPANNOL, CONVERTIR, P1080, K4, P720, LATINAS, LATINOS, MUSICA, ONLINE, SHOW, TRANSMISION, ACTORES, ACTUALIZACION, ACTUALIZADA, ACTUALIZADOS, AL, ANDROID, ANIMADOS, ANIME, ANTIVIRUS, ANUNCIOS, APLICACIONES, AVI, CAP, CLASICAS, CLASICOS, CLASIFICADOS, CLIP, COMICS, CON, CRISTIANA, CUBA, DE, DEL, DEPORTE, DISCOGRAFIA, DOBLADAS, DOCUMENTAL, DOCUMENTALES, DORAMAS, EN, ESPAÑOL, ESPAÑOLA, ESPAÑOLAS, ESTRENO, ESTRENOS, EX, EXCLUSIVA, FESTIVAL, FILMES, FINALIZADAS, FPS, GAMES, GAMESPLAYS, GENERO, GRAFICAS, HD, HISTORIETAS, HUMOR, INGLES, INTERESANTES, IOS, JUEGOS, JUVENTUD, KAV, LA, LATINA, LATINOS, LINUX, MAC, MANGAS, MAR, MINISERIES, MKV, MOVILES, MP3, MP4, MUSICA, MUSICALES, NOD32, NOVELAS, ORGANIZADAS, PAIS, PARA, PC, PELICULAS, PHONE, POR, PREMIOS, PROGRAMAS, REALITY, REVISTAS, SECCION, SEG, SEMANA, SEMANAL, SERIES, SHOW, SHOWS, SITIOS, SOFTWARES, SUB, SUBTITULADAS, SUBTITULADOS, SUBTITULO, SUBTITULOS, SUELTOS, TEMPORADA, TEMPORADAS, TRAILERS, TRANSMISION, TUTORIALES, TV, TX, VARIADOS, VIDEOS, VIDEOS2BRAIN, VIÑA, WALLPAPERS, WINDOW, WINDOWS, X, Y};
    public static final String FALTARON="faltaron",QUE="que",PENDIENTES="pendientes",FIN = "fin",
            FICCION = "ficcion", DRAMA = "drama", TERROR = "terror", SUSP = "susp", SUSPENSOS = "suspensos", SUSPENSO = "suspenso", MUNNES  = "mu�es", MUÑES = "muñes", CIENCIA = "ciencia", C = "c", AVENTURAS = "aventuras", ACCION = "accion",
            ESPANNOL  = "espa�ol", CONVERTIR = "convertir", P1080  = "1080p", K4  = "4k", P720  = "720p", LATINAS = "Latinas",
            LATINOS = "latinos",
            MUSICA = "musica",
            ONLINE = "Online", SHOW = "show",
            TRANSMISION = "transmision",
            ACTORES = "actores", ACTUALIZACION = "actualizacion", ACTUALIZADA = "actualizada", ACTUALIZADOS = "actualizados", AL = "al", ANDROID = "android", ANIMADOS = "animados", ANIME = "anime", ANTIVIRUS = "antivirus", ANUNCIOS = "anuncios", APLICACIONES = "aplicaciones", AVI = "avi", CAP = "cap", CLASICAS = "clasicas", CLASICOS = "clasicos", CLASIFICADOS = "clasificados", CLIP = "clip", COMICS = "comics", CON = "con", CRISTIANA = "cristiana", CUBA = "cuba", DE = "de", DEL = "del", DEPORTE = "deporte", DISCOGRAFIA = "discografia", DOBLADAS = "dobladas", DOCUMENTAL = "documental", DOCUMENTALES = "documentales", DORAMAS = "doramas", EN = "en", ESPAÑOL = "español", ESPAÑOLA = "española", ESPAÑOLAS = "españolas", ESTRENO = "estreno", ESTRENOS = "estrenos", EX = "ex", EXCLUSIVA = "exclusiva", FESTIVAL = "festival", FILMES = "filmes", FINALIZADAS = "finalizadas", FPS = "fps", GAMES = "games", GAMESPLAYS = "gamesplays", GENERO = "genero", GRAFICAS = "graficas", HD = "hd", HISTORIETAS = "historietas", HUMOR = "humor", INGLES = "ingles", INTERESANTES = "interesantes", IOS = "ios", JUEGOS = "juegos", JUVENTUD = "juventud", KAV = "kav", LA = "la", LATINA = "latina",
             LINUX = "linux", MAC = "mac", MANGAS = "mangas", MAR = "mar", MINISERIES = "miniseries", MKV = "mkv", MOVILES = "moviles", MP3 = "mp3", MP4 = "mp4",
             MUSICALES = "musicales", NOD32 = "nod32", NOVELAS = "novelas", ORGANIZADAS = "organizadas", PAIS = "pais", PARA = "para", PC = "pc", PELICULAS = "peliculas", PHONE = "phone", POR = "por", PREMIOS = "premios", PROGRAMAS = "programas", REALITY = "reality", REVISTAS = "revistas", SECCION = "seccion", SEG = "seg", SEMANA = "semana", SEMANAL = "semanal", SERIES = "series", 
             SHOWS = "shows", SITIOS = "sitios", SOFTWARES = "softwares", SUB = "sub", SUBTITULADAS = "subtituladas", SUBTITULADOS = "subtitulados", SUBTITULO = "subtitulo", SUBTITULOS = "subtitulos", SUELTOS = "sueltos", TEMPORADA = "temporada", TEMPORADAS = "temporadas", TRAILERS = "trailers", 
             TUTORIALES = "tutoriales", TV = "tv", TX = "tx", VARIADOS = "variados", VIDEOS = "videos", VIDEOS2BRAIN = "videos2brain", VIÑA = "viña", WALLPAPERS = "wallpapers", WINDOW = "window", WINDOWS = "windows", X = "x", Y = "y";
    public static final String invalidosPaquete[] = {FICCION, DRAMA, TERROR, SUSP, SUSPENSOS, SUSPENSO
            , MUNNES, CIENCIA, C, AVENTURAS, ACCION,
            ESPANNOL , CONVERTIR, P1080 , K4 , P720 , LATINAS, LATINOS, MUSICA, ONLINE, SHOW, TRANSMISION, ACTORES, ACTUALIZACION, ACTUALIZADA, ACTUALIZADOS, AL, ANDROID, ANIMADOS, ANIME, ANTIVIRUS, ANUNCIOS, APLICACIONES, AVI, CAP, CLASICAS, CLASICOS, CLASIFICADOS, CLIP, COMICS, CON, CRISTIANA, CUBA, DE, DEL, DEPORTE, DISCOGRAFIA, DOBLADAS, DOCUMENTAL, DOCUMENTALES, DORAMAS, EN, ESPAÑOL, ESPAÑOLA, ESPAÑOLAS, ESTRENO, ESTRENOS, EX, EXCLUSIVA, FESTIVAL, FILMES, FINALIZADAS, FPS, GAMES, GAMESPLAYS, GENERO, GRAFICAS, HD, HISTORIETAS, HUMOR, INGLES, INTERESANTES, IOS, JUEGOS, JUVENTUD, KAV, LA, LATINA, LATINOS, LINUX, MAC, MANGAS, MAR, MINISERIES, MKV, MOVILES, MP3, MP4, MUSICA, MUSICALES, NOD32, NOVELAS, ORGANIZADAS, PAIS, PARA, PC, PELICULAS, PHONE, POR, PREMIOS, PROGRAMAS, REALITY, REVISTAS, SECCION, SEG, SEMANA, SEMANAL, SERIES, SHOW, SHOWS, SITIOS, SOFTWARES, SUB, SUBTITULADAS, SUBTITULADOS, SUBTITULO, SUBTITULOS, SUELTOS, TEMPORADA, TEMPORADAS, TRAILERS, TRANSMISION, TUTORIALES, TV, TX, VARIADOS, VIDEOS, VIDEOS2BRAIN, VIÑA, WALLPAPERS, WINDOW, WINDOWS, X, Y
};

//
//    public static final String ANIMADOS = "animados", ANIME = "anime", MANGAS = "mangas",
//            TEMPORADAS = "temporadas", TEMPORADA = "temporada", SERIES = "series",
//            CLASICAS = "clasicas", FINALIZADAS = "finalizadas", TRANSMISION = "Transmision", ESTRENO = "estreno",
//            COMICS = "comics", VIDEOS = "videos", WALLPAPERS = "wallpapers", INTERESANTES = "interesantes", ONLINE = "Online", EN = "en", CON = "con", NOVELAS = "novelas", PELICULAS = "peliculas", TX = "tx", SUBTITULOS = "subtitulos", SUBTITULO = "subtitulo", SUB = "sub", DOCUMENTALES = "documentales", DOCUMENTAL = "documental", VARIADOS = "variados",
//            MINISERIES = "miniseries", MUSICA = "Musica", ESPANOLA = "españolas", SUBTITULADAS = "subtituladas", SUBTITULADOS = "subtitulados", SHOW = "Show", LATINOS = "Latinos", LATINAS = "LatinAs", HD = "hd";
//    public static final String invalidosPaquete[] = {ANIMADOS, ANIME, CLASICAS, ESTRENO, FINALIZADAS, FINALIZADAS, MANGAS,
//        SERIES, TEMPORADA, TEMPORADAS, TRANSMISION, COMICS, VIDEOS, WALLPAPERS, INTERESANTES, ONLINE, CON, EN, NOVELAS, TX, SUB, SUBTITULO, SUBTITULOS, DOCUMENTAL, DOCUMENTALES, VARIADOS, MINISERIES, MUSICA, ESPANOLA, SUBTITULADAS, SUBTITULADOS, SHOW, LATINAS, LATINOS, HD};
private CarpetaSeries Series;
    private CarpetaMangas Mangas;

    public Paquete(File F, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        if (F.exists() && F.isDirectory()) {
            File[] Fl = F.listFiles();
//            System.out.println("Fl.length="+Fl.length);
            for (int i = 0; i < Fl.length; i++) {

//                System.out.println("i="+i+" "+Fl[i]);
//                if(Fl[i].getName().equals("Series")){
//                    System.out.println("aaaaaaa");
//                }
                if (Fl[i].isDirectory()) {
                    if (esCarpetaSubtitulos(Fl[i], cdv)) {
                        if (Series == null) {
                            Series = new CarpetaSeries();
                        }
                        Series.getEnTransmision().getTX().getSubtitulos().addAll(new CarpetaDeSubtitulos(Fl[i], new String[]{}, cdv));
//                        break;
                        continue;
                    }

                    if (containsAll(Fl[i], SERIES, MANGAS)
                            || Fl[i].getName().equalsIgnoreCase(ANIME) || Fl[i].getName().equalsIgnoreCase(MANGAS)
                            || (containsAll(Fl[i], ANIME, MANGAS))
                            || (containsAll(Fl[i], SERIES, MANGAS) && Fl[i].getName().length() < SERIES.length() + MANGAS.length() + 4)
                            || (contains(Fl[i], ANIME) && Fl[i].getName().length() < ANIME.length() + 5)
                            || (contains(Fl[i], MANGAS) && Fl[i].getName().length() < MANGAS.length() + 5)
                            || (containsAll(Fl[i], ANIMADOS, MANGAS)))//&& Fl[i].getName().length() < ANIMADOS.length() + MANGAS.length() + 8
                    {
                        if (Mangas == null) {
                            Mangas = new CarpetaMangas(Fl[i], cdv);
                        } else {
                            Mangas.addAll(new CarpetaMangas(Fl[i], cdv));
                        }
                        continue;
                    }
                    if (Fl[i].getName().equalsIgnoreCase(SERIES)) {
                        if (Series == null) {
                            Series = new CarpetaSeries(Fl[i], cdv);
                        } else {
                            Series.addAll(new CarpetaSeries(Fl[i], cdv));
                        }
                        continue;
                    }
                    if (contains(Fl[i], ANIME)) {
                        if (Mangas == null) {
                            Mangas = new CarpetaMangas();
                        }
                        if (contains(Fl[i], CLASICAS)) {
                            Mangas.getClasicas().addAll(new CarpetaMangasClasicas(Fl[i], CarpetaMangas.invalidosManga, cdv));
                            continue;
                        }
                        if (contains(Fl[i], FINALIZADAS)) {
                            Mangas.getFinalizadas().addAll(new CarpetaMangasFinalizadas(Fl[i], CarpetaMangas.invalidosManga, cdv));
                            continue;
                        }
                        if (contains(Fl[i], ONLINE)) {
                            Mangas.getTX().addAll(new CarpetaMangasTX(Fl[i], cdv));
                        }
                        continue;
                    }
                    if (contains(Fl[i], SERIES)) {
                        if (Series == null) {
                            Series = new CarpetaSeries();
                        }
                        if (contains(Fl[i], TEMPORADAS, TEMPORADA, FINALIZADAS)) {
                            if (contains(Fl[i], ESPANOL) || contains(Fl[i], DOBLADAS)) {
                                Series.getFinalizadas().getFinalizadasEspañol().addAll(new CarpetaSeriesTemporadasFinalizadasEspañol(Fl[i], invalidosSerie, cdv));
                                continue;
                            }
                            // if (contains(Fl[i], TEMPORADAS, TEMPORADA)) {
                            Series.getFinalizadas().getFinalizadas().addAll(new CarpetaSeriesTemporadasFinalizadas(Fl[i], invalidosSerie, cdv));
                            continue;
                            //}
                        }
                        if (contains(Fl[i], CLASICAS)) {
                            Series.getEnTransmision().getClasicas().addAll(new CarpetaSeriesClasicas(Fl[i], invalidosSerie, cdv));
                            continue;
                        }
                        if (contains(Fl[i], DOBLADAS, ESPANOL)) {
                            Series.getEnTransmision().getDobladas().addAll(new CarpetaSeriesDobladas(Fl[i], invalidosSerie, cdv));
                            continue;
                        }
                        if ((contains(Fl[i], "tx") && Fl[i].getName().length() < (SERIES.length() + 5))
                                || (contains(Fl[i], TRANSMISION) && Fl[i].getName().length() <= "Series en Transmision [Subtituladas]".length() + 2)
                                || (contains(Fl[i], ESTRENO) && Fl[i].getName().length() <= "Series tx [Estreno][Subtituladas]".length() + 2)) {

                            Series.getEnTransmision().getTX().addAll(new CarpetaSeriesTX(Fl[i], invalidosSerie, cdv));
                            continue;
                        }

                    }
                }
            }
        }
    }

    public Paquete(CarpetaSeries Series, CarpetaMangas Mangas) {
        this.Series = Series;
        this.Mangas = Mangas;
    }

    public CarpetaSeries getSeries() {
        return Series;
    }

    public void setSeries(CarpetaSeries Series) {
        this.Series = Series;
    }

    public CarpetaMangas getMangas() {
        return Mangas;
    }

    public void setMangas(CarpetaMangas Mangas) {
        this.Mangas = Mangas;
    }

    public static boolean esCarpetaSubtitulos(File f, ConfiguracionDeVideo cdv) {
//        System.out.println("contains(f, SUBTITULO, SUBTITULOS, SUB)="+contains(f, SUBTITULO, SUBTITULOS, SUB));
        return f.isDirectory() ?contains(f, SUBTITULO, SUBTITULOS, SUB) &&Videos.containsSoloYNumeros(f, cdv, SUBTITULO, SUBTITULOS, SUB, EN, CON, DE, SERIES, FIN, SEMANA, SEMANAL, LA,PENDIENTES,QUE,FALTARON) : false;
    }

    public static boolean containsAll(File f, String... b) {
        return f.isDirectory() ? MetodosUtiles.containsAll(MetodosUtiles.arreglarPalabra(f.getName()), true, b) : false;
    }

    public static boolean contains(File f, String... b) {
        return f.isDirectory() ? contains(f.getName(), b) : false;
    }

    public static boolean contains(String a, String... b) {
        a = MetodosUtiles.arreglarPalabra(a);
//        for (int i = 0; i < b.length; i++) {
//            b[i]=MetodosUtiles.arreglarPalabra(a);
//        }
        return MetodosUtiles.contains(a, true, b);
    }

    public static String[] getNombresCarpetaSubtitulo(File f) {
        LinkedList<String> directoriosInvalidos = new LinkedList<>();
        if (f.isDirectory()) {
            String sub[] = {"! Subtitulos", "!!!Estrenos", "Subtitulos", "SUB", "Estrenos"};
            File F[] = f.listFiles();
            for (int i = 0; i < F.length; i++) {
                if (F[i].isDirectory()) {
                    if ((MetodosUtiles.startsWith(F[i].getName(), "!", "_")) || (MetodosUtiles.or(F[i].getName(), true, sub))) {
                        directoriosInvalidos.add(F[i].getName());
                    }

                }
            }
        }
        return directoriosInvalidos.toArray(new String[]{});
    }

    public static String[] getNombresCarpetasInvalidos(File f) {
        LinkedList<String> directoriosInvalidos = new LinkedList<>();
        if (f.isDirectory()) {
            String sub[] = {"!!!Estrenos", "Estrenos", SERIES, "extreno", DE, LA, SEMANA, "serie"};
            File F[] = f.listFiles();
            for (int i = 0; i < F.length; i++) {
                if (F[i].isDirectory()) {
                    if (Videos.containsSoloYNumeros(F[i], null, sub)) {
                        directoriosInvalidos.add(F[i].getName());
                    }

                }
            }
        }
        return directoriosInvalidos.toArray(new String[]{});
    }

    public boolean isEmpty() {
        return (Series == null || Series.isEmpty()) && (Mangas == null || Mangas.isEmpty());
    }

    public static String[] palabrasDelPaquete(File... f) {
        TreeSet<String> palabras = new TreeSet<String>(Arrays.asList(invalidosPaquete));
//        System.out.println(palabras.toString());
        for (int i = 0; i < f.length; i++) {
            if (f[i].exists() && f[i].isDirectory()) {
                File F[] = f[i].listFiles();
                for (int j = 0; j < F.length; j++) {
                    if (F[j].isDirectory()) {
                        StringTokenizer S = new StringTokenizer(F[j].getName(), Archivo.Delimiters);
                        while (S.hasMoreTokens()) {
                            String s = S.nextToken();
                            if (!MetodosUtiles.esNumero(s)) {
                                palabras.add(MetodosUtiles.arreglarPalabra(s.toLowerCase()));
                            }
                        }
                    }
                }
            }
        }

        return palabras.toArray(new String[]{});
    }
 public static String[] palabrasDelPaquete() {
     String B[]=new String[invalidosPaquete.length];
     for (int i = 0; i < B.length; i++) {
         B[i]=MetodosUtiles.arreglarPalabra(invalidosPaquete[i]);
     }
     return B;
 }
    public static String constantesDelPaquete(String A[]) {
        String a = "public static final String ";
        String b = "public static final String invalidosPaquete[] = {";
        for (int i = 0; i < A.length; i++) {

            a += A[i].toUpperCase() + "= \"" + A[i] + "\"" + (i != A.length - 1 ? " , " : ";");
            b += A[i].toUpperCase() + (i != A.length - 1 ? " , " : "};");
        }
        return a + "\n" + b;
    }
    
}
