/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenido_carpeta_a_txt;

//import Aplicaciones.actualizador.de.series.*;
import Utiles.ClasesUtiles.Interfases.IsSeleccionado;
import java.io.File;
import java.io.Serializable;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class Direccion implements Serializable,IsSeleccionado{

    private File f;
    private boolean seleccionado;
    private LinkedList<DirectoriosInvalidos> directoriosInvalidos;

    public Direccion(File f, boolean seleccionado, LinkedList<DirectoriosInvalidos> directoriosInvalidos) {
        this.f = f;
        this.seleccionado = seleccionado;
        this.directoriosInvalidos = directoriosInvalidos;
    }

    public Direccion(File f, boolean seleccionado) {
        this(f, seleccionado, new LinkedList<DirectoriosInvalidos>());
    }

    public File getF() {
        return f;
    }

    public void setF(File f) {
        this.f = f;
    }

    @Override
    public boolean isSeleccionado() {
        return seleccionado;
    }

    @Override
    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    public LinkedList<DirectoriosInvalidos> getDirectoriosInvalidos() {
        return directoriosInvalidos;
    }

    public void setDirectoriosInvalidos(LinkedList<DirectoriosInvalidos> directoriosInvalidos) {
        this.directoriosInvalidos = directoriosInvalidos;
    }

    @Override
    public String toString() {
        return f.toString();
    }

}
