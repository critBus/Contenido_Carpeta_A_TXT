/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaMangas;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Series.*;
import Utiles.ClasesUtiles.Multimedia.Paquete.*;
import java.io.File;
import java.io.FileNotFoundException;
/**
 * Version 0.1
 * @author Rene
 */
public class CarpetaMangasClasicas extends CarpetaConSeries{

    public CarpetaMangasClasicas() {
        super();
    }

    public CarpetaMangasClasicas(File F, String[] invalidos, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        super(F, invalidos, cdv);
    }

    
    
}
