/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles;

/**
 * Costantes de utiles para problemas practicos
 *
 * @author Rene
 */
public interface Valor {
    //<ul></ul>
    //<li></li>
/**
 * <ul>Equivalente de una libra en newtons </ul>
 * <ul> lbN = 4.448221615260</ul>
 */
    public final static double lbN = 4.448221615260; // Equivalente de una libra en newtons
    
    /**
     * <ul>valor real de un segundo</ul>
     * <ul> ciclos dede cierta frecuencia exacta de radiación de microondas.</ul>
     * <ul>sReal = 919263177*10</ul>
     */
    public final static double sReal = 919263177*10; //ciclos dede cierta frecuencia exacta de radiación de microondas.

    
    /**
     * <ul> libras en un kilogramo</ul>
     * <ul>lbsKi = 2.2046226218488</ul>
     */
    public final static double lbsKi = 2.2046226218488; //libras en un kilogramo
    /**
     * <ul> Costante de Coulomb</ul>
     *
     */
    public final static double KCoulomb = 8.98755187 * Math.pow(10, 9);
    /**
     * <ul> constante dieléctrica o permitividad del vacío</ul>
     */
    public final static double E0 = 8.8541878176 * Math.pow(10, -12);// constante dieléctrica o  permitividad del vacío
    public final static double Culombio = 6.25 * Math.pow(10, 18);
    /**
     * <ul> gravedad </ul>
     */
    public final static double g = 9.80665;//gravedad
    /**
     * <ul> Impedancia característica en el vacío </ul>
     */
    public final static double Z0 = 376.730313461;//Impedancia característica en el vacío
    /**
     * <ul> Permeabilidad magnética en el vacío </ul>
     */
    public final static double U0 = 1.2566370614 * Math.pow(10, -6);//Permeabilidad magnética en el vacío
    /**
     * <ul> Constante de gravitación universal </ul>
     */
    public final static double G = 6.6742 * Math.pow(10, -11);//Constante de gravitación universal
    /**
     * <ul> Constante de Planck </ul>
     */
    public final static double h = 6.6260693 * Math.pow(11, -34);//Constante de Planck
    /**
     * <ul> Constante reducida de Planck </ul>
     */
    public final static double hReducida = 1.054571628 * Math.pow(18, -34);//Constante reducida de Planck

    /**
     * <ul> Velocidad de la luz en el vacío </ul>
     */
    public final static double c = 299792458;//Velocidad de la luz en el vacío
    /**
     * <ul> Magnetón de Bohr </ul>
     */

    public final static double UB = 9.27400949 * (80) * Math.pow(10, -24);//Magnetón de Bohr
    /**
     * <ul> Magnetón nuclear </ul>
     */

    public final static double UN = 5.05078343 * (43) * Math.pow(10, -27);//Magnetón nuclear
    /**
     * <ul> Resistencia cuántica </ul>
     */

    public final static double R0 = 12906.403725 * (43);//Resistencia cuántica

    /**
     * <ul> Constante de von Klitzing </ul>
     */
    public final static double RK = 25812.807449 * (86);//Constante de von Klitzing
    /**
     * <ul> Radio de Bohr </ul>
     */

    public final static double a0 = 0.5291772108 * (18) * Math.pow(10, -10);//Radio de Bohr

    /**
     * <ul> Constante de acoplamiento de Fermi </ul>
     */
    public final static double AdF = 1.16639 * Math.pow(10, -5);//Constante de acoplamiento de Fermi
    /**
     * <ul> Constante de estructura fina </ul>
     */

    public final static double alfa = 7.297352568 * (24) * Math.pow(10, -3);//Constante de estructura fina

    /**
     * <ul> Energía de Hartree </ul>
     */
    public final static double Eh = 4.35974417 * (75) * Math.pow(10, -18);//Energía de Hartree
    /**
     * <ul> Quantum de circulation </ul>
     */

    public final static double QdC = 3.636947550 * (24) * Math.pow(10, -4);//Quantum de circulation

    /**
     * <ul> Constante de Rydberg </ul>
     */
    public final static double Roo = 10973731.568525 * (73);//Constante de Rydberg
    /**
     * <ul> Sección eficaz de Thomson </ul>
     */

    public final static double SedT = 0.665245873 * (13) * Math.pow(10, -28);//Sección eficaz de Thomson
    /**
     * <ul> Ángulo de Weinberg </ul>
     */

    public final static double AdW = 0.22215 * (76);//Ángulo de Weinberg
    /**
     * <ul> Constante de masa atómica </ul>
     */

    public final static double mu = 1.66053886 * (28) * Math.pow(10, -27);//Constante de masa atómica

    /**
     * <ul> Número de Avogadro </ul>
     */
    public final static double Na = 6.02214199 * (47) * Math.pow(10, 23);//Número de Avogadro

    /**
     * <ul> Constante de Boltzmann </ul>
     */
    public final static double kBoltzmann = 1.3806505 * (24) * Math.pow(10, -23);//Constante de Boltzmann
    /**
     * <ul> Constante de Faraday </ul>
     * <ul> 96 485,3383(83)C·mol^-1 </ul>
     */

    public final static double F = 96485.3383 * (83);//Constante de Faraday

    /**
     * <ul> Primera constante de radiación </ul>
     */
    public final static double c1 = 3.74177138 * (64) * Math.pow(10, -16);//Primera constante de radiación

    /**
     * <ul> Primera constante de radiación para radiancia espectral </ul>
     */
    public final static double c1L = 1.19104282 * (20) * Math.pow(10, -16);//Primera constante de radiación para radiancia espectral
    /**
     * <ul> Número de Loschmidt </ul>
     */

    public final static double n0 = 2.6867773 * (47) * Math.pow(10, 25);//Número de Loschmidt
    /**
     * <ul> Constante universal de los gases ideales </ul>
     */

    public final static double R = 8.314472 * (15);//Constante universal de los gases ideales
    /**
     * <ul> Constante molar de Planck </ul>
     */

    public final static double Nah = 3.990312716 * (27) * Math.pow(10, -10);//Constante molar de Planck

    /**
     * <ul> Volumen molar de un gas ideal a T=273,15 K y p=100 kPa </ul>
     */
    public final static double Vm1 = 22.710981 * (40) * Math.pow(10, -3);//Volumen molar de un gas ideal a T=273,15 K y p=100 kPa
    /**
     * <ul> Volumen molar de un gas ideal a T=273.15 K y p=101.325 kPa </ul>
     */

    public final static double Vm2 = 22.413996 * (39) * Math.pow(10, -3);//Volumen molar de un gas ideal a T=273.15 K y p=101.325 kPa
    /**
     * <ul> Sackur-Tetrode constant a T=1 K y p=100 kPa </ul>
     */

    public final static double ST1 = -1.1517047 * (44);//Sackur-Tetrode constant a T=1 K y p=100 kPa
    /**
     * <ul> Sackur-Tetrode constant a T=1 K y p=101,325 kPa </ul>
     */

    public final static double ST2 = -1.1648677 * (44);//Sackur-Tetrode constant a T=1 K y p=101,325 kPa
    /**
     * <ul> Segunda constante de radiación </ul>
     */

    public final static double c2 = 1.4387752 * (25) * Math.pow(10, -2);//Segunda constante de radiación
    /**
     * <ul> Constante de Stefan-Boltzmann </ul>
     */

    public final static double SB = 5.670400 * (40) * Math.pow(10, -8);//Constante de Stefan-Boltzmann

    /**
     * <ul> Constante de la ley de desplazamiento de Wien </ul>
     */
    public final static double Benergia = 2.8977685 * (51) * Math.pow(10, -3);//Constante de la ley de desplazamiento de Wien

    /**
     * <ul> Constante de la ley de desplazamiento de Wien de la entropía </ul>
     */
    public final static double Bentriopia = 3.0029152 * (5) * Math.pow(10, -3);//Constante de la ley de desplazamiento de Wien de la entropía
    /**
     * <ul> Valor convencional de la constante de Josephson </ul>
     */

    public final static double Kj = 483597 * 9 * Math.pow(10, 9);//Valor convencional de la constante de Josephson
}
