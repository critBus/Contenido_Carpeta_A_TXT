/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Interfases;

/**
 *
 * @author Rene
 */
public interface Arreglo<E> {

    public E[] crearArreglo(int leng);

    public E[][] crearArreglo(int filas, int columnas);

    public E[][][] crearArreglo(int filas, int columnas, int cantidad);

    public E[] crearArregloSuperclase(int leng);

    public E[][] crearArregloSuperclase(int filas, int columnas);

    public E[][][] crearArregloSuperclase(int filas, int columnas, int cantidad);

    public E[] crearArregloSuperclaseSuperior(int leng);

    public E[][] crearArregloSuperclaseSuperior(int filas, int columnas);

    public E[][][] crearArregloSuperclaseSuperior(int filas, int columnas, int cantidad);
}
