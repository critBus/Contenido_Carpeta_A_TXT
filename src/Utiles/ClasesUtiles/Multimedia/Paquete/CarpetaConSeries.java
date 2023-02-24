/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Paquete;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Interfases.addAll;
import Utiles.ClasesUtiles.Multimedia.Series.*;
import Utiles.MetodosUtiles.Archivo;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.LinkedList;

/**
 * Version 0.1
 * @author Rene
 */
public abstract class CarpetaConSeries implements addAll<CarpetaConSeries> {

//    private CatalogoDeSeries catalogo;
    LinkedList<File> direcciones;
//    String invalidos[];
    LinkedList<LinkedList<String>> invalidos;

    public CarpetaConSeries() {
        this(new LinkedList<File>(), new LinkedList<LinkedList<String>>());
//        catalogo=new CatalogoDeSeries();
    }

    public CarpetaConSeries(File F, String invalidos[], ConfiguracionDeVideo cdv) throws FileNotFoundException {//
        this(new LinkedList<File>(Arrays.asList(new File[]{F})), new LinkedList<LinkedList<String>>());
        this.invalidos.add(new LinkedList<String>(Arrays.asList(invalidos)));//Arrays.asList(invalidos)
//        this(new CatalogoDeSeries(F, Archivo.getInvalidos(F, invalidos), cdv));
    }

    public CarpetaConSeries(LinkedList<File> direcciones, LinkedList<LinkedList<String>> invalidos) {
        this.direcciones = direcciones;
        this.invalidos = invalidos;
    }

    public LinkedList<File> getDirecciones() {
        return direcciones;
    }

    public void setDirecciones(LinkedList<File> direcciones) {
        this.direcciones = direcciones;
    }

    public LinkedList<LinkedList<String>> getInvalidos() {
        return invalidos;
    }

    public void setInvalidos(LinkedList<LinkedList<String>> invalidos) {
        this.invalidos = invalidos;
    }

//    public CarpetaConSeries(CatalogoDeSeries catalogo) {
//        this.catalogo = catalogo;
//    }
//    
//    public CatalogoDeSeries getCatalogo() {
//        return catalogo;
//    }
//    
//    public void setCatalogo(CatalogoDeSeries catalogo) {
//           this.catalogo = catalogo;
//    }
    public void addAll(CarpetaConSeries c) {
//        catalogo.addAll(c.getCatalogo());
        direcciones.addAll(c.direcciones);
        invalidos.addAll(c.invalidos);
    }

    public boolean isEmpty(){
    return direcciones.isEmpty();
    }
}
