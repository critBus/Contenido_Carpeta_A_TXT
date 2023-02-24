/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.BasesDeDatos;

import Utiles.ClasesUtiles.Admininistrador.Administrador;
import Utiles.ClasesUtiles.BasesDeDatos.BDConexionController;
import Utiles.ClasesUtiles.Interfases.Funcionales.Creador;
import Utiles.ClasesUtiles.Interfases.Funcionales.Creador2;
import Utiles.ClasesUtiles.Interfases.Funcionales.UtilizarConException;
import Utiles.Exepciones.ExisteException;
import Utiles.Exepciones.NoEncontradoException;
import static Utiles.MetodosUtiles.Archivo.cargarArchivoYCrearDeSerNesesario;
import Utiles.MetodosUtiles.BD;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.function.Consumer;
import javafx.util.Callback;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
//import AWT.Visual;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rene
 */
public class BDAdministrador<E> implements Administrador<E> {
    
    public File carpetaAdministrador;
    public String nombreAdministrador;
    public static final String EXTENCION = ".bdad",EXTENCION_PARCIAL= "bdad";
    private static final String NOMBRE_ASIGNADO = "nombreAsigando", TABLA_ADMINISTRADOR = "TablaAdministrador", TABLA = "tabla", NOMBRE = "nombre";
    private static final int indiceNombre = 2, indiceTabla = 1;
    private String nombreTabla;
    private Callback<Object[][], E> creardorDeRespuesta;
    //private UtilizarConException<BDConexion> creardorDeTabla;
    private Creador<String[]> creadorDeColumnas;
    private Callback<E, String[][]> agregarInformacion;
    private BDConexionController conexion;
    
    public BDAdministrador(Class c, String nombreTabla) throws Exception {
        iniDirecciones();
        this.nombreTabla = nombreTabla;
        if (or(c, File[].class)) {
          //  System.out.println("tipo correccto");
          final  String columnaDireccion = "direccion";
            creardorDeRespuesta = v -> {
                LinkedList<File> l = new LinkedList<>();
                for (int i = 0; i < v.length; i++) {
                    // l.add((File) v[i][2]);
                    //l.add((File) v[i][0]);
                    l.add((File) v[i][1]);
                }
                return (E) l.toArray(new File[0]);
            };
            creadorDeColumnas = () -> new String[]{columnaDireccion};
//            creardorDeTabla = v -> {
//                v.crearTablaYBorrarSiExiste(nombreTabla, NOMBRE_ASIGNADO, columnaDireccion);
//            };
            agregarInformacion = v -> {
                File F[]=(File[])v;
                String S[][]=new String[F.length+1][1];
                for (int i = 0; i < S.length; i++) {
                    S[i]=new String[]{(i==0?columnaDireccion:F[i-1].getAbsolutePath())};
                }
//                String S[]=new String[F.length*2];
//                for (int i = 0; i < F.length; i++) {
//                    S[i*2]=columnaDireccion;
//                    S[i*2+1]="'"+F[i].getAbsolutePath()+"'";
//                }
                return S;
            //    new String[]{columnaDireccion, ((File)v).getAbsolutePath()};
            
            };
            
        }
        conexion = crearBDConexion();
        crearTablaNombres();
        crearTablasInformacion();
    }
    
    private E llamarACreadorDeRespuesta() throws Exception {
//        Object O[][] = conexion.getConjuntoResultadoMatris();
//        Object O2[][] = new Object[O.length][];
//        int distancia = 2;
//        for (int i = 0; i < O.length; i++) {
//            O2[i] = new Object[O[i].length - distancia];
//            for (int j = distancia; j < O[i].length; j++) {
//                O2[i][j - distancia] = O[i][j];
//            }
//        }
//        return creardorDeRespuesta.call(O2);
 return creardorDeRespuesta.call(conexion.getConjuntoResultadoMatris());
    }
    
    private void crearTablaNombres() throws Exception {
        conexion.crearTablaSiNoExiste(TABLA_ADMINISTRADOR, TABLA, NOMBRE);
    }
    private void crearTablasInformacion() throws Exception {
        conexion.crearTablaSiNoExiste(nombreTabla,new String[]{NOMBRE_ASIGNADO},creadorDeColumnas.crear());
    }
    
    public BDConexionController crearBDConexion() {
        //return new BDConexionController(BDTipoDeConexion.SQL_LITE, getDireccionEA());
        return BDConexionController.getMY_SQLConexion(getDireccionEA());
    }
    
    public File getDireccionEA() {
        return new File(carpetaAdministrador + "/" + nombreAdministrador);
    }
    public void iniDirecciones() {
        setCarpetaAdministrador(new File("Data"));
        setNombreAdministrador("EstadoActual.s3db");
    }

    public File getCarpetaAdministrador() {
        return carpetaAdministrador;
    }

    public void setCarpetaAdministrador(File carpetaAdministrador) {
        this.carpetaAdministrador = carpetaAdministrador;
    }

    public String getNombreAdministrador() {
        return nombreAdministrador;
    }

    public void setNombreAdministrador(String nombreAdministrador) {
        this.nombreAdministrador = nombreAdministrador;
    }
     
    
    @Override
    public String[] getNombres() throws Exception {
        
        LinkedList<String> l = new LinkedList<>();
        conexion.executeSELECTyWHERE_Igual(TABLA_ADMINISTRADOR, TABLA, nombreTabla, NOMBRE).recorrerResultados((o, i) -> {
            l.add((String) o[indiceNombre]);
        }).close();
        return l.toArray(new String[0]);
        
    }
    
    @Override
    public E getInformacion(String nombre) throws Exception {
        conexion.executeSELECTyWHERE_Igual(nombreTabla, NOMBRE_ASIGNADO, nombre, "*");
        E e = llamarACreadorDeRespuesta();
        conexion.close();
        return e;
        
    }
    
    @Override
    public void eliminarInformacion(String nombre) throws Exception {
        conexion.executeDELETEyWHERE_Igual(nombreTabla, NOMBRE_ASIGNADO, nombre).executeDELETEyWHERE_Igual(TABLA_ADMINISTRADOR, NOMBRE, nombre);
    }
    
   
    
    @Override
    public void canviarInformacion(String nombre, E informacion) throws Exception {
        conexion.executeUPDATEyWHERE_Igual(nombreTabla, NOMBRE_ASIGNADO, nombre, creadorDeColumnas.crear());
    }
    
    @Override
    public void agregarInformacion(String nombre, E informacion) throws Exception {
        conexion.executeINSERT(nombreTabla, agregarInformacion.call(informacion));
    }
    
    
    
    
    @Override
    public boolean isEmpty() throws Exception {
        return conexion.executeSELECTyWHERE_Igual(TABLA_ADMINISTRADOR, TABLA, nombreTabla, NOMBRE).getConjuntoResultadoMatris().length == 0;
    }
    
    @Override
    public boolean contieneNombre(String nombre) throws Exception {//TABLA, nombreTabla
        return conexion.executeSELECTyWHERE_Igual(TABLA_ADMINISTRADOR, new String[]{TABLA, nombreTabla, NOMBRE, nombre}, NOMBRE).getConjuntoResultadoMatris().length == 0;
    }
    
    @Override
    public void canviarNombre(String nombre, String nuevoNombre) throws Exception {
        //NOMBRE_ASIGNADO, nombre
        conexion.executeUPDATEyWHERE_Igual(TABLA_ADMINISTRADOR, new String[]{TABLA, nombreTabla, NOMBRE, nombre}, NOMBRE, nuevoNombre)
                .executeUPDATEyWHERE_Igual(nombreTabla, NOMBRE_ASIGNADO, nombre, NOMBRE_ASIGNADO, nuevoNombre);
    }
    
    public boolean existe(String nombre) throws Exception {
        return conexion.executeSELECTyWHERE_Igual(TABLA_ADMINISTRADOR, new String[]{TABLA, nombreTabla, NOMBRE, nombre}, NOMBRE).getConjuntoResultadoMatris().length != 0;
    }
    
    
    
}
