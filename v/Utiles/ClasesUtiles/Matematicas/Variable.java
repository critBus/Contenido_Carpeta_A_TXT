/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;



/**
 *
 * @author Rene
 */
//public class Variable implements TipoOperando {
public class Variable extends Operando{
   //private FraccionGeneral cociente;
  // private FraccionGeneral indice;

    char letra;
   // boolean positivo = true;      //true positivo //false si es negativo
//    public Variable(Variable v,boolean positivo){
//    this(v.getLetra(),v.getCociente(),v.getIndice(),positivo);
//    }
    
//   public Variable(char letra,boolean positivo) {
//        this(letra, new FraccionGeneral(), new FraccionGeneral(),positivo);
//    }
//
//    public Variable(char letra, FraccionGeneral cociente,boolean positivo) {
//        this(letra, cociente, new FraccionGeneral(),positivo);
//    }
    
    
//    public Variable(char letra) {
//        this(letra, new FraccionGeneral(), new FraccionGeneral(),true);
//    }

//    public Variable(char letra, FraccionGeneral cociente) {
//        this(letra, cociente, new FraccionGeneral(),true);
//    }

//    public Variable(char letra, FraccionGeneral cociente, FraccionGeneral indice,boolean positivo) {
//        this.cociente = cociente;
//        this.indice = indice;
//        this.letra = letra;
//        this.positivo=positivo;
//        //this.signo = signo;
//    }//esSignoMenos
    
    public Variable(Variable v){
    this(v.getLetra(), v.esPositivo());
    }
    public Variable(char letra) {
        this(letra,true);
    }
    public Variable(char letra,boolean positivo) {
//        this.cociente = cociente;
//        this.indice = indice;
        this.letra = letra;
        this.positivo=positivo;
        
        optimo=this;
        //this.signo = signo;
    }

//    public FraccionGeneral getCociente() {
//        return cociente;
//    }
//
//    public void setCociente(FraccionGeneral cociente) {
//        this.cociente = cociente;
//    }
//
//    public FraccionGeneral getIndice() {
//        return indice;
//    }

//    public boolean isPositivo() {
//        return positivo;
//    }

//    public void setPositivo(boolean positivo) {
//        this.positivo = positivo;
//    }

//    public void setIndice(FraccionGeneral indice) {
//        this.indice = indice;
//    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

//    static public Variable inicializarConSoloIndice(char a, FraccionGeneral indice) {
//        return new Variable(a, new FraccionGeneral(), indice,true);
//    }
//    static public Variable inicializarConSoloIndice(char a, FraccionGeneral indice,boolean positivo) {
//        return new Variable(a, new FraccionGeneral(), indice,positivo);
//    }

//    @Override
//    public Operando getOperando() {
//        return new Operando(this);
//    }

//     public void addCocienteNumerador( Operando b) {
//        getCociente().addNumerador( b);
//        //variable.setCociente(addNumeradorAFraccionGeneral(variable.getCociente(), b));
//        // return variable;
//    }
//
//     public void addCocienteNumerador( TipoOperando b) {
//        addCocienteNumerador( b.getOperando());
//    }
//
//     public void addCocienteNumerador( double b) {
//        addCocienteNumerador( new Operando(b));
//    }

    @Override
    public String toString() {
       return (positivo?"":"-")+letra+"";
    }

    @Override
    public boolean igualA(Operando a,boolean valorarSigno) {
        //System.out.println("v this="+this+" a="+a);
        if(a instanceof Variable){
           return valorarSigno?(a.esPositivo()==positivo&&letra==((Variable)a).getLetra()):letra==((Variable)a).getLetra();
        }
        
        return false;
    }
       @Override
    public Variable[] crearArreglo(int leng) {
        return new Variable[leng];
    }

    @Override
    public Variable[][] crearArreglo(int filas, int columnas) {
        return new Variable[filas][columnas];
    }

    @Override
    public Variable[][][] crearArreglo(int filas, int columnas, int cantidad) {
       return new Variable[filas][columnas][cantidad];
    }

    @Override
    public boolean contieneVariable(char v) {
        return getLetra()==v; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Operando inicializarDerivada(char v) {
        return (derivado=new Numero(contieneVariable(v)?1:0)); //To change body of generated methods, choose Tools | Templates.
    }
    
 
}
