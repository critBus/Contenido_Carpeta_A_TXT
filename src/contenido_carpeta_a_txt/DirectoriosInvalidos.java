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

/**
 *
 * @author Rene
 */
public class DirectoriosInvalidos implements Serializable,IsSeleccionado {

    private String directorioInvalido;
    private boolean seleccionado;

    public DirectoriosInvalidos(File f) {
        this(f.toString(), true);
    }

    public DirectoriosInvalidos(String directorioInvalido, boolean seleccionado) {
        this.directorioInvalido = directorioInvalido;
        this.seleccionado = seleccionado;
    }

    public String getDirectorioInvalido() {
        return directorioInvalido;
    }

    public void setDirectorioInvalido(String directorioInvalido) {
        this.directorioInvalido = directorioInvalido;
    }

    @Override
    public boolean isSeleccionado() {
        return seleccionado;
    }

    public void setSeleccionado(boolean seleccionado) {
        this.seleccionado = seleccionado;
    }

    @Override
    public String toString() {
        return directorioInvalido; //To change body of generated methods, choose Tools | Templates.
    }

}
