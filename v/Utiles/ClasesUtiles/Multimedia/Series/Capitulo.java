/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Series;

//import Utiles.ClasesUtiles.*;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import java.io.File;
import Utiles.MetodosUtiles.*;
//import Utiles.Exepciones.*;
import java.io.Serializable;
//import java.util.Arrays;

/**
 * Version 0.2
 *
 * @author Rene
 */
public class Capitulo implements Serializable, Comparable<Capitulo> {
//ConfiguracionDeVideo cdv // Arrays.toString
    // private Fichero fichero;

    private String nombre;
    private int temporada, capitulos[];//generalmente solo almacena un valor
    private File direccion;

    public Capitulo(File f, ConfiguracionDeVideo cdv) {
        int cap[] = Videos.getCapitulosDeNombre(f.getName(), cdv);
        // System.out.println(Arrays.toString(cap));
        inicializar(Archivo.getNombre(f), cap[0], Arreglos.eliminarIndice(cap, 0),f);
    }

    public Capitulo(String nombre, int temporada, int[] capitulos, File direccion) {
        inicializar(nombre, temporada, capitulos, direccion);
    }

    

    public void inicializar(String nombre, int temporada, int[] capitulos, File direccion) {
        this.nombre = nombre;
        this.temporada = temporada;
        this.capitulos = capitulos;
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public int getTemporada() {
        return temporada;
    }

    public int[] getCapitulos() {
        return capitulos;
    }

    public String getCapitulosString() {
        return capitulos.length > 1 && capitulos[1] != -1 ? capitulos[0] + " - " + capitulos[1] : capitulos[0] + "";
    }

    public int getCapituloInicial() {
        return capitulos[0];
    }

    public File getDireccion() {
        return direccion;
    }

    public void setDireccion(File direccion) {
        this.direccion = direccion;
    }

    public String getExtencion() {
        return direccion!=null&&direccion.isFile()?Archivo.getExtencion(direccion):"";
    }
    public int comparacion(Capitulo o) {
//        System.out.println("capitulos="+Arrays.toString(capitulos)+"  o.capitulos"+Arrays.toString(o.capitulos));
        int com = new Integer(temporada).compareTo(o.temporada);
        if (com == 0) {
            if ((com = new Integer(capitulos[0]).compareTo(o.capitulos[0])) == 0) {
                return new Integer(capitulos.length).compareTo(o.capitulos.length);
//                if ((com = new Integer(capitulos.length).compareTo(o.capitulos.length)) == 0) {
//                    return MetodosUtiles.compararStringAConStringB(nombre, o.nombre, true);
//                }
            }
        }
        return com;
    }

    public int comparacion2(Capitulo o) {
//        System.out.println("capitulos="+Arrays.toString(capitulos)+"  o.capitulos"+Arrays.toString(o.capitulos));
        int com = new Integer(temporada).compareTo(o.temporada);
        if (com == 0) {
            if ((com = new Integer(capitulos[0]).compareTo(o.capitulos[0])) == 0) {
                return new Integer(capitulos.length).compareTo(o.capitulos.length);
//                if ((com = new Integer(capitulos.length).compareTo(o.capitulos.length)) == 0) {
//                    return MetodosUtiles.compararStringAConStringB(nombre, o.nombre, true);
//                }
            }
        }
        return com;
    }

    @Override
    public int compareTo(Capitulo o) {
        int com = comparacion(o);
        if (com == 0) {
            return MetodosUtiles.compararStringAConStringB(nombre, o.nombre, true);
        }
//        int com = new Integer(temporada).compareTo(o.temporada);
//        if (com == 0) {
//            if ((com = new Integer(capitulos[0]).compareTo(o.capitulos[0])) == 0) {
//                if ((com = new Integer(capitulos.length).compareTo(o.capitulos.length)) == 0) {
//                    return MetodosUtiles.compararStringAConStringB(nombre, o.nombre, true);
//                }
//            }
//        }
        return com;
    }

    @Override
    public String toString() {
//        return nombre + " Temporada " + temporada + " Capitulo " + getCapitulosString(); //To change body of generated methods, choose Tools | Templates.
        return nombre;
    }

}
