/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Colecciones.otro;

/**
 *
 * @author Rene
 */
public class Persona implements Comparable<Persona>{
    
    private final  String id,nombre,direccion;

    public Persona(String id, String nombre, String direccion) {
        this.id = id;
        this.nombre = nombre;
        this.direccion = direccion;
    }

   
    public String getNombre() {
        return nombre;
    }

    public String getId() {
        return id;
    }

    public String getDireccion() {
        return direccion;
    }

    @Override
    public int compareTo(Persona o) {
       return new Integer(Integer.parseInt(getId().charAt(0)+""+getId().charAt(1))).compareTo(Integer.parseInt(o.getId().charAt(0)+""+o.getId().charAt(1)));
    }

    @Override
    public String toString() {
        return nombre; //To change body of generated methods, choose Tools | Templates.
    }
    
  
     
    
    
    
}
