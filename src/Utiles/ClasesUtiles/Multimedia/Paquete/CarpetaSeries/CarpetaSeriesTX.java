/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries;

import static Utiles.ClasesUtiles.Multimedia.Paquete.Paquete.*;
import static Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.CarpetaSeries.*;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaConSeries;
import Utiles.ClasesUtiles.Multimedia.Paquete.Paquete;
import Utiles.ClasesUtiles.Multimedia.Subtitulo.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;

/**
 * Version 0.1
 *
 * @author Rene
 */
public class CarpetaSeriesTX extends CarpetaConSeries {

    public static final String ESTRENOS = "Estrenos";
//    LinkedList<Subtitulo> subtitulos;
    private CarpetaDeSubtitulos Subtitulos;

    public CarpetaSeriesTX() {
        super();
        Subtitulos = new CarpetaDeSubtitulos();
    }

    public CarpetaSeriesTX(File F, String[] invalidos, ConfiguracionDeVideo cdv) throws FileNotFoundException {
//        super(F, getNombresCarpetaSubtitulo(F), cdv);
        super(F, new String[]{}, cdv);
        if (F.exists() && F.isDirectory()) {
            Subtitulos = new CarpetaDeSubtitulos();
            File[] Fl = F.listFiles();
            for (int i = 0; i < Fl.length; i++) {
//                System.out.println("i="+i+" Fl="+Fl[i].toString());
//                if(Fl[i].getName().equals("! Subtitulos")){
//                    System.out.println("aaaaa");
//                }
                if (Paquete.esCarpetaSubtitulos(Fl[i], cdv)) {
                    Subtitulos.addAll(new CarpetaDeSubtitulos(Fl[i], invalidos, cdv));
                    continue;
//                    break;
                }
                if (contains(Fl[i], ESTRENOS) && Fl[i].getName().length() < ESTRENOS.length() + 5) {
                    addAll(new CarpetaSeriesTX(Fl[i], invalidos, cdv));
                    break;
                }

            }
        }
    }

    public CarpetaDeSubtitulos getSubtitulos() {
        return Subtitulos;
    }

    public void setSubtitulos(CarpetaDeSubtitulos Subtitulos) {
        this.Subtitulos = Subtitulos;
    }

   

}
