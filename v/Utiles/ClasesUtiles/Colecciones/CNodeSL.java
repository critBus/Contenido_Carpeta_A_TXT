/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Utiles.ClasesUtiles.Colecciones;


public class CNodeSL<T>{
    public T value;
    public CNodeSL<T> next;

    public CNodeSL(T value, CNodeSL<T> next){
        this.value = value;
        this.next = next;
    }

    public CNodeSL(T value){
        this.value = value;
        next = null;
    }
}
