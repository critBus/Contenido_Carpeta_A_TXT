/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.MetodosUtiles.Arreglos;
import Utiles.MetodosUtiles.Videos;
import static Utiles.MetodosUtiles.Videos.getSubNombres;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class NombreClave {

    private String nombre;
    private LinkedList<String> claves;
    public static final String SEPARADOR = "===";

    public NombreClave(String nombre, ConfiguracionDeVideo cdv) {
        this(nombre, Videos.getClaves(getSubNombres(nombre, cdv), cdv));
    }

    public NombreClave(String nombre, LinkedList<String> claves) {
        this.nombre = nombre;
        this.claves = claves;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<String> getClaves() {
        return claves;
    }

    public String getClave() {
        return claves.get(0);
    }

    public void setClaves(LinkedList<String> claves) {
        this.claves = claves;
    }

    /**
     * Si contine el separador "===" una o mas veses devuelbe un int[][] donde
     * las filas son la cantidad de separadores y en cada fila {i don cominza el
     * separador, i donde comienza despues de terminar} si no contine nigunao
     * esta vacio leng=0
     *
     * @param a
     * @return
     */

    public static int[][] indicesSeparador(String a) {
        LinkedList<LinkedList<Integer>> res = new LinkedList();
        int pos = -1;
        while ((pos = a.indexOf(SEPARADOR, pos == -1 ? 0 : pos + 1)) != -1) {
            res.add(new LinkedList());
            res.getLast().add(pos);
            res.getLast().add(pos + 3);
        }

        return Arreglos.convertirLinkedListIntegerAArregloint(res);
    }
}
