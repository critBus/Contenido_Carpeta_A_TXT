/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Medidas;



import Utiles.ClasesUtiles.Medidas.Unidades.*;


/**
 *
 * @author Rene
 */
public enum TipoDeMedida {

    Masa(TiposDeMasa.values()),Potencia(TiposDePotencia.values());
    //String unidades[];
    UnidadDeMedida unidadDeMedidas[];
    String unidades[];

    private TipoDeMedida(UnidadDeMedida[] unidadDeMedidas) {
        this.unidadDeMedidas = unidadDeMedidas;
        unidades = new String[unidadDeMedidas.length];
        for (int i = 0; i < unidadDeMedidas.length; i++) {
            unidades[i] = unidadDeMedidas[i] + "";
        }
    }

//    private TipoDeMedida(UnidadDeMedida unidadDeMedida) {
//        this.unidadDeMedida = unidadDeMedida;
//    }
//    TipoDeMedida() {
//        unidades = obternerTiposDeUnidades(this);
//    }
    public String[] getUnidadesString() {
//        String unidades[] = new String[unidadDeMedidas.length];
//        for (int i = 0; i < unidadDeMedidas.length; i++) {
//            unidades[i] = unidadDeMedidas[i] + "";
//        }
        return unidades;
    }

    public UnidadDeMedida[] getUnidades() {
        return unidadDeMedidas;
    }

}
