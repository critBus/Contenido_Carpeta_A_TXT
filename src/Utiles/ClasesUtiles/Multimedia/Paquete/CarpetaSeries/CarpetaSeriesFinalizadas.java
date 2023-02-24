/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Interfases.addAll;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaConSeries;
import static Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.CarpetaSeries.*;
import static Utiles.ClasesUtiles.Multimedia.Paquete.Paquete.*;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Version 0.1
 *
 * @author Rene
 */
public class CarpetaSeriesFinalizadas implements addAll<CarpetaSeriesFinalizadas> {

    private CarpetaSeriesTemporadasFinalizadas finalizadas;
    private CarpetaSeriesTemporadasFinalizadasEspañol finalizadasEspañol;

    public CarpetaSeriesFinalizadas() {
        this(new CarpetaSeriesTemporadasFinalizadas(), new CarpetaSeriesTemporadasFinalizadasEspañol());
    }

    public boolean isEmpty() {
        return finalizadas == null && finalizadasEspañol == null;
    }

    public CarpetaSeriesFinalizadas(File F, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        this();
        if (F.exists() && F.isDirectory()) {
            File[] Fl = F.listFiles();
            for (int i = 0; i < Fl.length; i++) {
                if (Fl[i].isDirectory()) {
                    if (contains(Fl[i], ESPANOL)) {
                        finalizadasEspañol.addAll(new CarpetaSeriesTemporadasFinalizadasEspañol(Fl[i], invalidosSerie, cdv));
                        continue;
                    }
                    if (contains(Fl[i], TEMPORADAS, TEMPORADA)) {
                        finalizadas.addAll(new CarpetaSeriesTemporadasFinalizadas(Fl[i], invalidosSerie, cdv));
                        continue;
                    }
                }
            }
        }
    }

    public CarpetaSeriesFinalizadas(CarpetaSeriesTemporadasFinalizadas Ingles, CarpetaSeriesTemporadasFinalizadasEspañol Español) {
//        inicializar(Ingles, Español);
        this.finalizadas = Ingles;
        this.finalizadasEspañol = Español;
    }

//    public void inicializar(CarpetaSeriesTemporadasFinalizadas Ingles, CarpetaSeriesTemporadasFinalizadasEspañol Español) {
//        this.finalizadas = Ingles;
//        this.finalizadasEspañol = Español;
//    }
    public CarpetaSeriesTemporadasFinalizadas getFinalizadas() {
        return finalizadas;
    }

    public void setFinalizadas(CarpetaSeriesTemporadasFinalizadas finalizadas) {
        this.finalizadas = finalizadas;
    }

    public CarpetaSeriesTemporadasFinalizadasEspañol getFinalizadasEspañol() {
        return finalizadasEspañol;
    }

    public void setFinalizadasEspañol(CarpetaSeriesTemporadasFinalizadasEspañol finalizadasEspañol) {
        this.finalizadasEspañol = finalizadasEspañol;
    }

    @Override
    public void addAll(CarpetaSeriesFinalizadas c) {
        finalizadas.addAll(c.finalizadas);
        finalizadasEspañol.addAll(c.finalizadasEspañol);
    }

}
