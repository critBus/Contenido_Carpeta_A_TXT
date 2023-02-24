/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenido_carpeta_a_txt;

import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import java.io.File;

/**
 *
 * @author Rene
 */
public class Archivo_Existente implements Comparable<Archivo_Existente> {

    private String nombre;
    private double tamaño;
    private formaDeComparar forma;

    public Archivo_Existente(File f) {
        this(f.getName(), Archivo.tamañoArchivo(f), formaDeComparar.TAMAÑO_MAYORES);
    }

    public Archivo_Existente(String nombre, double tamaño, formaDeComparar forma) {
        this.nombre = nombre;
        this.tamaño = tamaño;
        this.forma = forma;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getTamaño() {
        return tamaño;
    }

    public String getTamañoString() {
        return Archivo.tamaño(tamaño);
    }

    public void setTamaño(double tamaño) {
        this.tamaño = tamaño;
    }

    public formaDeComparar getForma() {
        return forma;
    }

    public void setForma(formaDeComparar forma) {
        this.forma = forma;
    }

    @Override
    public int compareTo(Archivo_Existente o) {
        switch (forma) {
            case NOMBRE:
                return MetodosUtiles.compararStringAConStringB(nombre, o.nombre, true);
            case TAMAÑO_MAYORES:
                int r = new Double(tamaño).compareTo(o.tamaño);
                return r == 0 ? r : (r < 0 ? 1 : -1);
            case TAMAÑO_MENORES:
                return new Double(tamaño).compareTo(o.tamaño);
        }
        return -1;
    }

}
