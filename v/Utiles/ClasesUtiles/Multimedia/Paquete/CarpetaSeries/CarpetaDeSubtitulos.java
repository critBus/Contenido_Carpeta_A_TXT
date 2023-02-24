/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaConSeries;
import Utiles.ClasesUtiles.Multimedia.Paquete.Paquete;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author Rene
 */
public class CarpetaDeSubtitulos extends CarpetaConSeries {

    public CarpetaDeSubtitulos() {
        super();
    }

    
    
    public CarpetaDeSubtitulos(File F, String[] invalidos, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        super(F, invalidos, cdv);
         if (F.exists() && F.isDirectory()) {
            File[] Fl = F.listFiles();
            for (int i = 0; i < Fl.length; i++) {
                if (Paquete.esCarpetaSubtitulos(Fl[i], cdv)) {
                    addAll(new CarpetaDeSubtitulos(Fl[i], invalidos, cdv));
//                    break;
                }

            }
        }
    }
    
}
