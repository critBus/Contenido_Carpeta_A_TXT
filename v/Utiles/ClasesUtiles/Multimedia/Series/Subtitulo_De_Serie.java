/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Series;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import java.io.File;

/**
 * Version 0.1
 * @author Rene
 */
public class Subtitulo_De_Serie extends Capitulo{

    public Subtitulo_De_Serie(File f, ConfiguracionDeVideo cdv) {
        super(f, cdv);
    }

    public Subtitulo_De_Serie(String nombre, int temporada, int[] capitulos, File direccion) {
        super(nombre, temporada, capitulos, direccion);
    }
    
}
