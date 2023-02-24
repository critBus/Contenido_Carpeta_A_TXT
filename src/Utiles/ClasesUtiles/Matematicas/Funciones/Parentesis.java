/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas.Funciones;

import Utiles.ClasesUtiles.Matematicas.*;
//import static Utiles.MetodosUtiles.MetodosUtiles.bicondicional;

/**
 *
 * @author Rene
 */
public class Parentesis extends Funcion {

    public Parentesis(Parentesis f) {
        this(f.getParametro1().copia(), f.esPositivo());
    }

    public Parentesis( double parametro1) {
        this(new Numero(parametro1), true);
    }
     public Parentesis( double parametro1, boolean positivo) {
        this(new Numero(parametro1), true);
    }

    public Parentesis(Operando parametro1) {
        this(parametro1, true);
    }

    public Parentesis(Operando parametro1, boolean positivo) {
        inicializarConstructorUnParamtro(TipoDeEnvoltura.PARENTESIS, parametro1, positivo);

    }

    @Override
    protected void inicializarOptimo() {
        //  super.inicializarOptimo(); 
        for (int i = 0; i < cantidadDeParametros; i++) {
            //System.out.println("parametros.get(i)=" + parametros.get(i) + " " + parametros.get(i).esPositivo() + " parametros.get(i).getOptimo()=" + parametros.get(i).getOptimo() + " " + parametros.get(i).getOptimo().esPositivo() + " 2=" + parametros.get(i).getOptimo() + " " + parametros.get(i).getOptimo().esPositivo());
            parametros.set(i, parametros.get(i).getOptimo());
        }

        optimo = this;
//        if (!(parametros.get(0) instanceof Suma)) {
          //  System.out.println("parametros.get(0)=" + parametros.get(0) + " parametros.get(0).copia()=" + parametros.get(0).copia());
            optimo = parametros.get(0).copia();//es por extraer 
           // System.out.println("1 optimo=" + optimo);
            optimo.setPositivo(positivo, optimo.esPositivo());
         //   System.out.println("2 optimo=" + optimo + " es=" + optimo.getClass().getSimpleName() + " positivo=" + optimo.esPositivo());
      //  }
        isNumero = parametros.get(0).IsNumero();
    }

    @Override
    public Parentesis[] crearArreglo(int leng) {
        return new Parentesis[leng];
    }

    @Override
    public Parentesis[][] crearArreglo(int filas, int columnas) {
        return new Parentesis[filas][columnas];
    }
    @Override
    public Parentesis[][][] crearArreglo(int filas, int columnas, int cantidad) {
         return new Parentesis[filas][columnas][cantidad];
    }

    @Override
    public Operando inicializarDerivada(char v) {
        return (derivado=getParametro1().inicializarDerivada(v)); //To change body of generated methods, choose Tools | Templates.
    }
    
}
