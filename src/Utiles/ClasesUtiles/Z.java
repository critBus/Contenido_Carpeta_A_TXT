/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles;

import java.util.LinkedList;

/**
 * Version 0.1
 *
 * @author Rene
 */
public class Z<E> {

    public E respuesta;
    public String respuestaString;
    public LinkedList<String> respuestaLinkedListString;
    public int respuestaArregloInt[];
    public tipoClaseDeRespuesta tipoDeRespuesta;
    public int indicesCapPrincipio[];
    public int indicesCapFinal[];
    public int indicesCap[];
    public LinkedList<Z> linkedListZ;
    public int indiceInicial;
    public int indiceFinal;
    public String nombre;
    public String clave;

    public Z() {
    }

    public int[] getCapitulosDecisivos() {
        if (indicesCapPrincipio != null && indicesCapPrincipio[0] == -1) {
            return indicesCapFinal;
        }
        return indicesCapPrincipio;
    }

}
