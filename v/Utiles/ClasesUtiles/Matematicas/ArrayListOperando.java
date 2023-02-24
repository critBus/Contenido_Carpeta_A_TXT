/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Matematicas;

import static Utiles.MetodosUtiles.MetodosUtiles.mismaClase;
//import static Utiles.MetodosUtiles.MetodosUtiles.mostrarList;
//import static Utiles.MetodosUtiles.MetodosUtiles.mostrarArrayList;
import java.util.ArrayList;

/**
 *
 * @author Rene
 */
public abstract class ArrayListOperando extends Operando {

    ArrayList<Operando> operandos = new ArrayList<Operando>();

    public ArrayList<Operando> getOperandos() {
        return operandos;
    }

    public void setOperandos(ArrayList<Operando> operandos) {
        this.operandos = operandos;
        //  inicializarOptimo();
        // actualizarPositivo();
        //this.positivo=operacionMultiplicacion.size()>0?obtenerSignoDeMultiplicacion(operacionMultiplicacion):positivo;
    }

    public Operando extraerFirstOperando() {
        return extraerOperando(0);
    }

    public Operando extraerLastOperando() {
        return extraerOperando(operandos.size() - 1);
    }

    public Operando extraerOperando(int indice) {
        Operando extraido = operandos.remove(indice);
        //   inicializarOptimo();
        return extraido;
    }

    public Operando getFirstOperando() {
        return getOperando(0);
    }

    public Operando getLastOperando() {
        return getOperando(operandos.size() - 1);
    }

    public Operando getOperando(int indice) {
        return operandos.get(indice);
    }

    public void setFirstOperando(Operando o) {
        setOperando(0, o);
    }

    public void setLastOperando(Operando o) {
        setOperando(operandos.size() - 1, o);
    }

    public void setOperando(int indice, Operando o) {
        operandos.set(indice, o);
        //inicializarOptimo();
    }

    public void addOperando(double numero) {
        addOperando(operandos.size(), new Numero(numero));
    }

    public void addOperando(int indice, double numero) {
        addOperando(indice, new Numero(numero));
    }

    public void addOperandoAA(Operando[]... O) {
        addOperandoAA(operandos.size(), O);
    }

    public void addOperandoAA(int indice, Operando[]... O) {
        for (Operando[] o : O) {
            addOperando(indice, o);
            indice += o.length;
        }
    }

    public void addOperando(Operando... O) {
        addOperando(operandos.size(), O);
    }

    public void addOperando(int indice, Operando... O) {
        for (Operando o : O) {
            addOperando(indice++, o);
        }
    }

    public void addOperando(Operando o) {
        addOperando(operandos.size(), o);
    }

    public void addOperando(int indice, Operando o) {
//if(o.getClass()==Suma.class){
//((Suma)o).imponerSigno();
// operandos.addAll(indice, ((Suma) o).getOperandos());
//}
        // System.out.println("o=" + o + " " + o.getClass().getSimpleName() + " this=" + this + " " + this.getClass().getSimpleName());
        if (mismaClase(o, this)) {
            if (this instanceof Suma) {
                ((Suma) o).imponerSigno();
            }
            //  mostrarArrayList(operandos);
            operandos.addAll(indice, ((ArrayListOperando) o).getOperandos());
            // mostrarArrayList(operandos);
        } else {
            operandos.add(indice, o);
        }
        //  inicializarOptimo();
        // actualizarPositivo();
    }

    public void clear() {
        operandos.clear();
        positivo = true;
        optimo = this;
    }

    @Override
    public void simplificar(boolean continuar) {
       //  mostrarArrayList(operandos);
        for (int i = 0; i < operandos.size(); i++) {
            operandos.get(i).simplificar(continuar);
        }
       //  mostrarArrayList(operandos);
        inicializarOptimo();
      //    System.out.println("this=" + this);
        if (continuar) {
            simplificar(false);
        }
    }

    public boolean contineneUnNumero() {
        return firstIndexOfNumero() != -1;
    }

    public int firstIndexOfNumero() {
        for (int i = 0; i < operandos.size(); i++) {
            if (operandos.get(i).IsNumero()) {
                return i;
            }
        }
        return -1;
    }

}
