/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Interfases.addAll;
import static Utiles.ClasesUtiles.Multimedia.Paquete.Paquete.*;
import static Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.CarpetaSeries.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * Version 0.1
 *
 * @author Rene
 */
public class CarpetaSeriesEnTransmision implements addAll<CarpetaSeriesEnTransmision> {

    private CarpetaSeriesTX TX;
    private CarpetaSeriesClasicas Clasicas;
    private CarpetaSeriesDobladas Dobladas;

    public CarpetaSeriesEnTransmision() {
        this(new CarpetaSeriesTX(), new CarpetaSeriesClasicas(), new CarpetaSeriesDobladas());
    }

    public boolean isEmpty() {
        return TX == null && Clasicas == null && Dobladas == null;
    }

    public CarpetaSeriesEnTransmision(File F, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        this();
        if (F.exists() && F.isDirectory()) {
            File[] Fl = F.listFiles();
            for (int i = 0; i < Fl.length; i++) {
                if (Fl[i].isDirectory()) {
                    if (contains(Fl[i], CLASICAS)) {
                        Clasicas.addAll(new CarpetaSeriesClasicas(Fl[i], invalidosSerie, cdv));
                        continue;
                    }
                    if (contains(Fl[i], DOBLADAS, ESPANOL)) {
                        Dobladas.addAll(new CarpetaSeriesDobladas(Fl[i], invalidosSerie, cdv));
                        continue;
                    }
                    if (contains(Fl[i], SERIES)) {
                        TX.addAll(new CarpetaSeriesTX(Fl[i], invalidosSerie, cdv));
                        continue;
                    }
                }
            }
        }
    }

    public CarpetaSeriesEnTransmision(CarpetaSeriesTX TX, CarpetaSeriesClasicas Clasicas, CarpetaSeriesDobladas Dobladas) {
//        inicializar(TX, Clasicas, Dobladas);
        this.TX = TX;
        this.Clasicas = Clasicas;
        this.Dobladas = Dobladas;
    }
//    public void inicializar(CarpetaSeriesTX TX, CarpetaSeriesClasicas Clasicas, CarpetaSeriesDobladas Dobladas) {
//        this.TX = TX;
//        this.Clasicas = Clasicas;
//        this.Dobladas = Dobladas;
//    }

    public CarpetaSeriesTX getTX() {
        return TX;
    }

    public void setTX(CarpetaSeriesTX TX) {
        this.TX = TX;
    }

    public CarpetaSeriesClasicas getClasicas() {
        return Clasicas;
    }

    public void setClasicas(CarpetaSeriesClasicas Clasicas) {
        this.Clasicas = Clasicas;
    }

    public CarpetaSeriesDobladas getDobladas() {
        return Dobladas;
    }

    public void setDobladas(CarpetaSeriesDobladas Dobladas) {
        this.Dobladas = Dobladas;
    }

    @Override
    public void addAll(CarpetaSeriesEnTransmision c) {
        TX.addAll(c.TX);
        Clasicas.addAll(c.Clasicas);
        Dobladas.addAll(c.Dobladas);
    }

}
