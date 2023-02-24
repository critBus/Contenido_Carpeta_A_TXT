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
public enum TiposDePotencia  implements UnidadDeMedida {

    Potencia(1, 0.746), Caballos(746, 1);
    private final double potencia;
    private final double caballos;
    private final double equvalencias[];

    private TiposDePotencia(double Potencia, double Caballos) {
        this.potencia = Potencia;
        this.caballos = Caballos;
        this.equvalencias = new double[]{potencia, caballos};

    }

    @Override
    public double getEquivalencia(UnidadDeMedida a) {
        switch (((TiposDePotencia) a)) {
            case Potencia:
                return potencia;
            case Caballos:
                return caballos;

        }
        return 0;
    }

    public double getPotencia() {
        return potencia;
    }

    public double getCaballos() {
        return caballos;
    }

    @Override
    public double getEquivalencia(UnidadDeMedida a, double cantidad) {
        return getEquivalencia(a) * cantidad;
    }

    @Override
    public double[] getEquvalencias() {
        return equvalencias;
    }

    @Override
    public UnidadDeMedida[] getUnidades() {
        return TiposDePotencia.values();
    }

}
