/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Admininistrador;

//import static Aplicaciones.MoverNumero.Administrador.NO_ENCONTRADO;
import Utiles.Exepciones.ExisteException;
import Utiles.Exepciones.NoEncontradoException;
import Utiles.Exepciones.PINException;
import java.io.File;

import static Utiles.MetodosUtiles.Archivo.*;
import static Utiles.MetodosUtiles.Visual.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

import java.util.HashMap;
import javax.swing.JFileChooser;

/**
 * Version 0.2
 *
 * @author Rene
 */
public class Admininistrador_De_Guardado<E> implements Serializable {

    HashMap<String, E> map;
    public static final String YA_EXITE = "Este nombre ya existe", NO_ENCONTRADO = "No se encontro el nombre";

    public Admininistrador_De_Guardado() {
        map = new HashMap<String, E>();
    }

    public Admininistrador_De_Guardado(Admininistrador_De_Guardado A, String direccion) throws IOException, ClassNotFoundException {
        cargarAdministradorDesdeArchivo(direccion);
        map.putAll(A.getMap());

    }

    public Admininistrador_De_Guardado(Admininistrador_De_Guardado A) {
        map = A.getMap();

    }

    public Admininistrador_De_Guardado(String direccion) throws IOException, ClassNotFoundException {

        cargarAdministradorDesdeArchivo(direccion);

    }

    public void cargarAdministradorDesdeArchivo(String direccion) throws IOException, ClassNotFoundException {
        Object O = cargarArchivoYCrearDeSerNesesario(direccion, new Admininistrador_De_Guardado());
        Admininistrador_De_Guardado d = (Admininistrador_De_Guardado) O;
        map = d.getMap();
    }

    public HashMap<String, E> getMap() {
        return map;
    }

    public String[] getNombres() {
        String respesta[] = map.keySet().toArray(new String[]{});
        Arrays.sort(respesta);
        return respesta;
    }

    public E getInformacion(String nombre) {
        return map.get(nombre);
    }

    public void eliminarInformacion(String nombre) {
        map.remove(nombre);
    }

    public void agregarInformacion(File direccion) throws ClassNotFoundException, IOException, ExisteException {
        Object O = cargarArchivoYCrearDeSerNesesario(direccion, new Object());
        E p = (E) O;
        agregarInformacion(direccion.getName(), p);
    }

    public void canviarInformacion(String nombre, E informacion) throws NoEncontradoException, PINException {
        if (!map.containsKey(nombre)) {
            throw new NoEncontradoException(NO_ENCONTRADO);
        }
        map.remove(nombre);
        map.put(nombre, informacion);
//        agregarInformacion(nombre, nuevaDireccion);
//        map.get(nombre).setDireccion(nuevaDireccion);
    }

    public void agregarInformacion(String nombre, E informacion) throws ExisteException {
        comprobarSiYaExiste(nombre);
        map.put(nombre, informacion);
    }

    public void guardarAdminastrador(JFileChooser jf) throws FileNotFoundException, IOException {
        guardarAdminastrador(jf.getSelectedFile());
    }

    public void guardarAdminastrador(File f) throws FileNotFoundException, IOException {
//        ObjectOutputStream F = new ObjectOutputStream(new FileOutputStream(f));
//        F.writeObject(this);
//        F.close();
        crearArchivo(f, this);
    }

    public void guardarAdminastrador(String direccion) throws FileNotFoundException, IOException {
        guardarAdminastrador(new File(direccion));
//        ObjectOutputStream F = new ObjectOutputStream(new FileOutputStream(direccion));
//        F.writeObject(this);
//        F.close();
    }

    public static Admininistrador_De_Guardado cargarAdministrador(String direccion) throws FileNotFoundException, IOException, ClassNotFoundException {
        Object o = cargarArchivoYCrearDeSerNesesario(direccion, new Admininistrador_De_Guardado());
        return ((Admininistrador_De_Guardado) o);
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public boolean contieneNombre(String nombre) {
        return map.containsKey(nombre);
    }

    public void canviarNombre(String nombre, String nuevoNombre) throws NoEncontradoException, PINException, ExisteException {
        comprobarSiNoExiste(nombre);
        comprobarSiYaExiste(nuevoNombre);
        map.put(nuevoNombre, map.get(nombre));
        map.remove(nombre);
    }

    public void comprobarSiYaExiste(String nombre) throws ExisteException {
        if (map.containsKey(nombre) && !dialogoSiNo("Desea Sobrescribir", "Advertencia")) {
            throw new ExisteException(YA_EXITE);
        }
    }

    public void comprobarSiNoExiste(String nombre) throws NoEncontradoException {
        if (!map.containsKey(nombre)) {
            throw new NoEncontradoException(NO_ENCONTRADO);
        }
    }

    public void addAdministrador(Admininistrador_De_Guardado<E> A) {
        map.putAll(A.map);
    }
}
