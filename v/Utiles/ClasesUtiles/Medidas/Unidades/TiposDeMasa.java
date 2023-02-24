/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Medidas.Unidades;
import static Utiles.ClasesUtiles.Interfases.Valor.lbsKi;
/**
 *
 * @author Rene
 */
public enum TiposDeMasa implements UnidadDeMedida {

    Miligramo(1, 0.1, 0.01, 0.001, 0.0001, 0.00001, 0.000001,lbsKi*0.000001),
    Centigramo(10, 1, 0.1, 0.01, 0.001, 0.0001, 0.00001,lbsKi*0.00001),
    Decigramo(100, 10, 1, 0.1, 0.01, 0.001, 0.0001,lbsKi*0.0001),
    Gramo(1000, 100, 10, 1, 0.1, 0.01, 0.001,lbsKi*0.001),
    Decagramo(10000, 1000, 100, 10, 1, 0.1, 0.01,lbsKi*0.01),
    Hectogramo(100000, 10000, 1000, 100, 10, 1, 0.1,lbsKi*0.1),
    Kilogramo(1000000, 100000, 10000, 1000, 100, 10, 1,lbsKi),
    Libra(lbsKi*1000000,lbsKi* 100000,lbsKi* 10000,lbsKi* 1000,lbsKi* 100, lbsKi*10, lbsKi,1);
    private final double miligramo;
    private final double centigramo;
    private final double decigramo;
    private final double gramo;
    private final double decagramo;
    private final double hectogramo;
    private final double kilogramo;
    private final double libras;
    private final double equvalencias[];

    private TiposDeMasa(double miligramo, double centigramo, double decigramo, double gramo, double decagramo, double hectogramo, double kilogramo, double libras) {
        this.miligramo = miligramo;
        this.centigramo = centigramo;
        this.decigramo = decigramo;
        this.gramo = gramo;
        this.decagramo = decagramo;
        this.hectogramo = hectogramo;
        this.kilogramo = kilogramo;
        this.libras = libras;
        double equivalencias[] = {miligramo, centigramo, decagramo, gramo, decagramo, hectogramo, kilogramo};
        this.equvalencias = equivalencias;
    }
    

//    private TiposDeMasa(double miligramo, double centigramo, double decigramo, double gramo, double decagramo, double hectogramo, double kilogramo) {
//        this.miligramo = miligramo;
//        this.centigramo = centigramo;
//        this.decigramo = decigramo;
//        this.gramo = gramo;
//        this.decagramo = decagramo;
//        this.hectogramo = hectogramo;
//        this.kilogramo = kilogramo;
//        double equivalencias[] = {miligramo, centigramo, decagramo, gramo, decagramo, hectogramo, kilogramo};
//        this.equvalencias = equivalencias;
//
//    }

    public double getMiligramo() {
        return miligramo;
    }

    public double getCentigramo() {
        return centigramo;
    }

    public double getDecigramo() {
        return decigramo;
    }

    public double getGramo() {
        return gramo;
    }

    public double getDecagramo() {
        return decagramo;
    }

    public double getHectogramo() {
        return hectogramo;
    }

    public double getKilogramo() {
        return kilogramo;
    }
    @Override

    public double[] getEquvalencias() {
        return equvalencias;
    }

    @Override
    public double getEquivalencia(UnidadDeMedida a) {
        //  if(a instanceof TiposDeMasa){
        switch (((TiposDeMasa) a)) {
            case Centigramo:
                return centigramo;
            case Decagramo:
                return decagramo;
            case Decigramo:
                return decigramo;

            case Gramo:
                return gramo;
            case Hectogramo:
                return hectogramo;
            case Kilogramo:
                return kilogramo;
            case Miligramo:
                return miligramo;

        }
        return 0;
        // }
    }

    @Override
    public double getEquivalencia(UnidadDeMedida a, double cantidad) {
        return getEquivalencia(a)*cantidad;
    }

    @Override
    public UnidadDeMedida[] getUnidades() {
        return TiposDeMasa.values();
    }

}
