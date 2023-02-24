/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Admininistrador;

import Utiles.Exepciones.*;
import Utiles.MetodosUtiles.Archivo;
import static Utiles.MetodosUtiles.Archivo.cargarArchivoYCrearDeSerNesesario;
import static Utiles.MetodosUtiles.Archivo.crearArchivo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Rene
 */
public interface Administrador<E> {
/**
 * Sustitulle el administrador actual por el de la direccion
 * @param direccion
 * @throws Exception 
 */
    public default void cargarAdministradorDesdeArchivo(String direccion) throws Exception {
        vaciar();
        Object O = Archivo.cargarArchivo(direccion);
        Administrador<E> d = (Administrador) O;
        addAdministrador(d);
        
    }
    public default void addAdministradorDesdeArchivo(File direccion) throws Exception {
        Object O = Archivo.cargarArchivo(direccion);
        Administrador<E> d = (Administrador) O;
        addAdministrador(d);
        
    }
    public default void addAdministrador(Administrador<E> A) throws Exception {
        String nombres[] = A.getNombres();
        for (int i = 0; i < nombres.length; i++) {
            if (!existe(nombres[i])) {
                agregarInformacion(nombres[i], A.getInformacion(nombres[i]));
            }
        }
        
    }

    public String[] getNombres() throws Exception;

    public E getInformacion(String nombre)  throws Exception;
    
//    public String getExtecionAdminstrador();
//    public default E getInformacionAll(String nombre)  throws Exception{
//    String nombres[]=getNombres();
//    
//    };

    public void eliminarInformacion(String nombre) throws Exception;
/**
 * carga una informacion desde un archivo y la añade con la direccion como nombre
 * @param direccion
 * @throws Exception 
 */
    public default void agregarInformacion(File direccion) throws Exception{
         Object O = cargarArchivoYCrearDeSerNesesario(direccion, new Object());
        E p = (E) O;
        agregarInformacion(direccion.getName(), p);
    }

    public void canviarInformacion(String nombre, E informacion) throws Exception;

    public void agregarInformacion(String nombre, E informacion) throws Exception;

   public default void guardarAdminastrador(File f) throws FileNotFoundException, IOException {
        crearArchivo(f, this);
    }
   public default void guardarAdminastrador(String direccion) throws FileNotFoundException, IOException {
        guardarAdminastrador(new File(direccion));
   }


   
    public boolean isEmpty() throws Exception;

    public boolean contieneNombre(String nombre)throws Exception;

    public void canviarNombre(String nombre, String nuevoNombre) throws Exception;

    public boolean existe(String nombre) throws Exception;
    
   public default void vaciar()throws Exception{
     String nombres[]=getNombres();
       for (int i = 0; i < nombres.length; i++) {
           if(existe(nombres[i])){
               eliminarInformacion(nombres[i]);
           }
       }
         
     }

    
    
}
