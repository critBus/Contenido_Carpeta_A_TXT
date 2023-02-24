/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaMangas;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Interfases.addAll;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.CarpetaSeriesEnTransmision;
import java.io.File;
import static Utiles.ClasesUtiles.Multimedia.Paquete.Paquete.*;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.Videos;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * Version 0.1
 *
 * @author Rene
 */
public class CarpetaMangas implements addAll<CarpetaMangas> {

    public static final String invalidosManga[] = {CLASICAS, TRANSMISION, FINALIZADAS, COMICS, VIDEOS, WALLPAPERS, INTERESANTES};
    private CarpetaMangasTX TX;
    private CarpetaMangasFinalizadas Finalizadas;
    private CarpetaMangasClasicas Clasicas;

    public CarpetaMangas() {
        this(new CarpetaMangasTX(), new CarpetaMangasFinalizadas(), new CarpetaMangasClasicas());
    }

    public boolean isEmpty() {
        return TX == null && Finalizadas == null && Clasicas == null;
    }

    public CarpetaMangas(File F, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        this();
        if (F.exists() && F.isDirectory()) {
            File[] Fl = F.listFiles();
            LinkedList<File> directoriosInvalidos = new LinkedList<File>();
            int cantidadDeVideos = 0;
            for (int i = 0; i < Fl.length; i++) {
//                System.out.println("");
                if (Fl[i].isDirectory()) {
                    directoriosInvalidos.add(Fl[i]);
                    if (containsAll(Fl[i], SERIES, CLASICAS)) {
                        Clasicas.addAll(new CarpetaMangasClasicas(Fl[i], invalidosManga, cdv));
                        continue;
                    }
                    if (contains(Fl[i], FINALIZADAS)) {
                        Finalizadas.addAll(new CarpetaMangasFinalizadas(Fl[i], invalidosManga, cdv));
                        continue;
                    }
                    if (contains(Fl[i], ONLINE)) {
                        TX.addAll(new CarpetaMangasTX(Fl[i], cdv));
                        continue;
                    }
                }

                if (Videos.esVideo(Fl[i])) {
//                    System.out.println("cantidadDeVideos="+cantidadDeVideos);
                    cantidadDeVideos++;
                }
            }
            if (cantidadDeVideos != 0) {
//                System.out.println("aa");
//                CatalogoDeSeries c;
                TX.addAll(new CarpetaMangasTX(F, cdv));
//                TX.addAll(new CatalogoDeSeries(F, directoriosInvalidos.toArray(new String[]{}), cdv));
            }

        }

    }

    public CarpetaMangas(CarpetaMangasTX TX, CarpetaMangasFinalizadas Finalizadas, CarpetaMangasClasicas Clasicas) {
        this.TX = TX;
        this.Finalizadas = Finalizadas;
        this.Clasicas = Clasicas;
    }

    public CarpetaMangasTX getTX() {
        return TX;
    }

    public void setTX(CarpetaMangasTX TX) {
        this.TX = TX;
    }

    public CarpetaMangasFinalizadas getFinalizadas() {
        return Finalizadas;
    }

    public void setFinalizadas(CarpetaMangasFinalizadas Finalizadas) {
        this.Finalizadas = Finalizadas;
    }

    public CarpetaMangasClasicas getClasicas() {
        return Clasicas;
    }

    public void setClasicas(CarpetaMangasClasicas Clasicas) {
        this.Clasicas = Clasicas;
    }

    @Override
    public void addAll(CarpetaMangas c) {
        TX.addAll(c.TX);
        Clasicas.addAll(c.Clasicas);
        Finalizadas.addAll(c.Finalizadas);
    }

}
