/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenido_carpeta_a_txt;

import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;

/**
 *
 * @author Rene
 */
public class Carpetas {

    LinkedList<Archivo_Existente> archivos;

    public Carpetas(File f, String invalidos[]) {
        this(new LinkedList<Archivo_Existente>());
        if (f.exists() && f.isDirectory()) {
            File F[] = f.listFiles();
            for (int i = 0; i < F.length; i++) {
                if (or(F[i].getName(), invalidos)) {
                    continue;
                }
                archivos.add(new Archivo_Existente(F[i]));
            }
        }

    }

    public Carpetas(LinkedList<Archivo_Existente> archivos) {
        this.archivos = archivos;
    }

    public void ordenarMayoresPrimero() {
        ordenar(formaDeComparar.TAMAÑO_MAYORES);
    }

    public void ordenarMenoresPrimero() {
        ordenar(formaDeComparar.TAMAÑO_MENORES);
    }

    public void ordenarNombre() {
        ordenar(formaDeComparar.NOMBRE);
    }

    private void ordenar(formaDeComparar form) {
        for (int i = 0; i < archivos.size(); i++) {
            archivos.get(i).setForma(form);
        }
        Collections.sort(archivos);
    }

    public void crearTXT(File f,String nombre) {
        String lineas[] = new String[archivos.size()];
        int max = 0;
        for (int i = 0; i < archivos.size(); i++) {
            if (archivos.get(i).getNombre().length() > max) {
                max = archivos.get(i).getNombre().length();
            }
        }
        for (int i = 0; i < archivos.size(); i++) {
            lineas[i]=String.format("%-"+(max)+"s %"+(10)+"s",archivos.get(i).getNombre() ,archivos.get(i).getTamañoString());
        }
        Archivo.crearTXT(f, nombre, lineas);
    }
}
