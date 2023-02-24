/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;
import static Utiles.MetodosUtiles.MetodosUtiles.eliminarPuntoCero;

/**
 *
 * @author Rene
 */
public class Numero extends Operando {
    //private double numero;//esSignoMenos

    public Numero(Numero n) {
         
        this(n,  n.getNumero()>=0);
       // System.out.println("this="+this);
//        numero = n.getNumero();
//        inicializar(true);
        // positivo=getNumero()>=0;
    }
    public Numero(double numero) {
       
        
        
        this(numero, numero >= 0 );
//        this.numero = numero;
//        inicializar(true);
        // positivo=getNumero()>=0;
    }

    

    public Numero(Operando o) {
        this((Numero) o);
    }
    public Numero(Numero n, boolean positivo) {
        numero = n.getNumero();
        inicializar(positivo);
        // positivo=getNumero()>=0;
    }

    public Numero(Operando o, boolean positivo) {
        this((Numero) o,positivo);
    }

    public Numero(double numero,boolean positivo) {
        this.numero = numero;
        inicializar(positivo);
        // positivo=getNumero()>=0;
    }
    

//    public double getNumero() {
//        return numero;
//    }
    public void setNumero(double numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        //return (positivo?"":"(")+numero + (positivo?"":")");
       // return (numero<0?"(":"")+eliminarPuntoCero(numero+"", Funcion.getInicios(), Funcion.getInicios()) + (numero<0?")":"");
         return eliminarPuntoCero(numero+"", getPermitidos(), Funcion.getInicios()) ;
    }

    private void inicializar(boolean positivo) {
        isNumero = true;
        optimo=this;
      //  inicializarOptimo();
        setPositivo(positivo);
    }

    @Override
    public void setPositivo(boolean positivo) {
        this.positivo = numero >= 0;
        super.setPositivo(positivo); //To change body of generated methods, choose Tools | Templates.
         this.positivo = numero >= 0;
    }
    
    

    @Override
    public boolean igualA(Operando a,boolean valorarSigno) {
      //  System.out.println("n this="+this+" a="+a);
        if (a instanceof Numero) {
            return !valorarSigno?Math.abs(a.getNumero())==Math.abs(numero):numero == a.getNumero();
       }
        return false;
    }
  @Override
    public Numero[] crearArreglo(int leng) {
        return new Numero[leng];
    }

    @Override
    public Numero[][] crearArreglo(int filas, int columnas) {
        return new Numero[filas][columnas];
    }
    

    @Override
    public Numero[][][] crearArreglo(int filas, int columnas, int cantidad) {
     return new Numero[filas][columnas][cantidad];
    }

      @Override
    public Operando inicializarDerivada(char v) {
        return (derivado=new Numero(0)); //To change body of generated methods, choose Tools | Templates.
    }
    
    

}
