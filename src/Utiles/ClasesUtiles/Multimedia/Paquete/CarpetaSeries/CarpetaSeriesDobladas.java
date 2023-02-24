/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaConSeries;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Version 0.1
 * @author Rene
 */
public class CarpetaSeriesDobladas extends CarpetaConSeries{

    public CarpetaSeriesDobladas() {
        super();
    }

    public CarpetaSeriesDobladas(File F, String[] invalidos, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        super(F, invalidos, cdv);
    }
    
    
}
