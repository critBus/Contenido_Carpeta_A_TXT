/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Interfases.addAll;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaMangas.CarpetaMangas;
import Utiles.ClasesUtiles.Multimedia.Paquete.Paquete;
import static Utiles.ClasesUtiles.Multimedia.Paquete.Paquete.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Version 0.1
 *
 * @author Rene
 */
public class CarpetaSeries implements addAll<CarpetaSeries> {

    public static final String ESPANOL = "Español", invalidosSerie[] = {CLASICAS, TRANSMISION, FINALIZADAS, DOBLADAS, ESPANOL};
    private CarpetaSeriesEnTransmision EnTransmision;
    private CarpetaSeriesFinalizadas Finalizadas;
    

    public CarpetaSeries() {
        this(new CarpetaSeriesEnTransmision(), new CarpetaSeriesFinalizadas());
    }

    public CarpetaSeries(CarpetaSeriesEnTransmision EnTransmision, CarpetaSeriesFinalizadas Finalizadas) {
        this.EnTransmision = EnTransmision;
        this.Finalizadas = Finalizadas;
    }

    

    public boolean isEmpty() {
        return (EnTransmision == null || (EnTransmision.isEmpty())) && (Finalizadas == null || (Finalizadas.isEmpty())) ;
    }

    public CarpetaSeries(File F, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        if (F.exists() && F.isDirectory()) {
            File[] Fl = F.listFiles();
            for (int i = 0; i < Fl.length; i++) {
//                System.out.println("i="+i+" "+Fl[i]);
                if (Fl[i].isDirectory()) {
                    if (Paquete.esCarpetaSubtitulos(Fl[i], cdv)) {
                        
                        if (EnTransmision == null) {
                            EnTransmision = new CarpetaSeriesEnTransmision();
                        } 
                       
                         EnTransmision.getTX().getSubtitulos().addAll(new CarpetaDeSubtitulos(Fl[i], new String[]{}, cdv));
                        
                        continue;
//                        break;
                    }

                    if (contains(Fl[i], FINALIZADAS)) {
                        if (Finalizadas == null) {
                            Finalizadas = new CarpetaSeriesFinalizadas(Fl[i], cdv);
                        } else {
                            Finalizadas.addAll(new CarpetaSeriesFinalizadas(Fl[i], cdv));
                        }

                        continue;
                    }
                    if (contains(Fl[i], TRANSMISION)) {
                        if (EnTransmision == null) {
                            EnTransmision = new CarpetaSeriesEnTransmision(Fl[i], cdv);
                        } else {
                            EnTransmision.addAll(new CarpetaSeriesEnTransmision(Fl[i], cdv));
                        }

                        continue;
                    }

                    //**********************
                    if (contains(Fl[i], CLASICAS)) {
                        if (EnTransmision == null) {
                            EnTransmision = new CarpetaSeriesEnTransmision();
                        }
                        EnTransmision.getClasicas().addAll(new CarpetaSeriesClasicas(Fl[i], invalidosSerie, cdv));
                        continue;
                    }
                    if (contains(Fl[i], CLASICAS, ESPANOL)) {
                        if (EnTransmision == null) {
                            EnTransmision = new CarpetaSeriesEnTransmision();
                        }
                        EnTransmision.getDobladas().addAll(new CarpetaSeriesDobladas(Fl[i], invalidosSerie, cdv));
                        continue;
                    }
                    if (contains(Fl[i], SERIES)) {
                        if (EnTransmision == null) {
                            EnTransmision = new CarpetaSeriesEnTransmision();
                        }
                        EnTransmision.getTX().addAll(new CarpetaSeriesTX(Fl[i], invalidosSerie, cdv));
                        continue;
                    }
                    if (contains(Fl[i], ESPANOL)) {
                        if (Finalizadas == null) {
                            Finalizadas = new CarpetaSeriesFinalizadas(Fl[i], cdv);
                        }
                        Finalizadas.getFinalizadasEspañol().addAll(new CarpetaSeriesTemporadasFinalizadasEspañol(Fl[i], invalidosSerie, cdv));
                        continue;
                    }
                    if (contains(Fl[i], TEMPORADAS)) {
                        if (Finalizadas == null) {
                            Finalizadas = new CarpetaSeriesFinalizadas(Fl[i], cdv);
                        }
                        Finalizadas.getFinalizadas().addAll(new CarpetaSeriesTemporadasFinalizadas(Fl[i], invalidosSerie, cdv));
                        continue;
                    }
                }
            }
        }
    }

    public CarpetaSeriesEnTransmision getEnTransmision() {
        return EnTransmision;
    }

    public void setEnTransmision(CarpetaSeriesEnTransmision EnTransmision) {
        this.EnTransmision = EnTransmision;
    }

    public CarpetaSeriesFinalizadas getFinalizadas() {
        return Finalizadas;
    }

    public void setFinalizadas(CarpetaSeriesFinalizadas Finalizadas) {
        this.Finalizadas = Finalizadas;
    }

    @Override
    public void addAll(CarpetaSeries c) {
        EnTransmision.addAll(c.EnTransmision);
        Finalizadas.addAll(c.Finalizadas);
    }

}
