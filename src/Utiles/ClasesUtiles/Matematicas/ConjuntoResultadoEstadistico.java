/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

import Utiles.MetodosUtiles.MetodosUtiles;
import static Utiles.MetodosUtiles.MetodosUtiles.eliminarCerosDerecha;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import static Utiles.MetodosUtiles.MetodosUtiles.redondearA3String;
import Utiles.MetodosUtiles.Operaciones;
import java.io.Serializable;

/**
 *
 * @author Rene
 */
public class ConjuntoResultadoEstadistico implements Serializable{

    protected double datos[], datosOrdenados[];
    protected double promedio, varianza, posicionMedia, desviacionEstandar, varibilidadRelativa ;//, estimadorBootstrap;

    public ConjuntoResultadoEstadistico() {
    }

    public ConjuntoResultadoEstadistico(String conjuntoDeDatos) {
       
        inicializar(conjuntoDeDatos);
    }

    public ConjuntoResultadoEstadistico(double[] datos) {
        inicializar(datos);
    }
 protected void inicializar(String conjuntoDeDatos) {
  if (!conjuntoDeDatos.isEmpty()) {
//            final LinkedList<Double> l = new LinkedList<>();
//            MetodosUtiles.recorrerTokenizer(conjuntoDeDatos, (token, i) -> {
//                if (MetodosUtiles.esNumero(token)) {
//                    l.add(MetodosUtiles.dou(token));
//                }
//            });
//            double datos[] = new double[l.size()];
//            for (int i = 0; i < l.size(); i++) {
//                datos[i] = l.get(i);
//            }
            inicializar(MetodosUtiles.getArreglodouble(conjuntoDeDatos));
        }
 }
    protected void inicializar(double[] datos) {
        this.datos = datos;
        iniBasicos(datos);
        iniDatosOrdenados();
        posicionMedia = Operaciones.posicionMediaDatosOrdenados(datosOrdenados);
//        estimadorBootstrap = Operaciones.estimadorBootstrap(datos, promedio);

    }

    protected void iniBasicos(double[] datos) {
        promedio = Operaciones.promedio(datos);
        varianza = Operaciones.varianza(datos, promedio);
        desviacionEstandar = Operaciones.desviacionEstandar(varianza);
        varibilidadRelativa = Operaciones.varibilidadRelativa(desviacionEstandar, promedio);

    }

    private void iniDatosOrdenados() {
        datosOrdenados = new double[datos.length];
        System.arraycopy(datos, 0, datosOrdenados, 0, datos.length);
        Arrays.sort(datosOrdenados);
    }

   

    public double[] getDatosOrdenados() {
        return datosOrdenados;
    }

    public double getPromedio() {
        return promedio;
    }

    public double getVarianza() {
        return varianza;
    }

    public double getPosicionMedia() {
        return posicionMedia;
    }

    public double getDesviacionEstandar() {
        return desviacionEstandar;
    }

    public double getVaribilidadRelativa() {
        return varibilidadRelativa;
    }

    public double[] getDatos() {
        return datos;
    }

//    public double getEstimadorBootstrap() {
//        return estimadorBootstrap;
//    }

}
