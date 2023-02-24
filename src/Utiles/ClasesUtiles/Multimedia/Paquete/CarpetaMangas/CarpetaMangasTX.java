/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaMangas;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaConSeries;
import Utiles.MetodosUtiles.Archivo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * Version 0.1
 * @author Rene
 */
public class CarpetaMangasTX extends CarpetaConSeries {

    public CarpetaMangasTX() {
        super();
    }

    public CarpetaMangasTX(File F, ConfiguracionDeVideo cdv) throws FileNotFoundException {
        super(F,Archivo.getNombresDeCarpeta(F), cdv);
    }
    

}
