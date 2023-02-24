/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Medidas.Unidades;

/**
 *
 * @author Rene
 */
public interface UnidadDeMedida {

    public double getEquivalencia(UnidadDeMedida a);

    public double getEquivalencia(UnidadDeMedida a, double cantidad);

    public double[] getEquvalencias();
    
    public UnidadDeMedida[] getUnidades();
   
}
