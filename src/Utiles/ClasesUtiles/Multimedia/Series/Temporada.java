/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Multimedia.Series;

import java.io.Serializable;
import java.util.Collections;
import java.util.LinkedList;
import Utiles.ClasesUtiles.Interfases.Clonable;

/**
 * Version 0.2
 *
 * @author Rene
 */
public class Temporada implements Serializable, Clonable<Temporada> {
//ConfiguracionDeVideo cdv Detuvo ****************************

    private String nombre;
    private LinkedList<Capitulo> capitulos;
    private LinkedList<Subtitulo_De_Serie> subtitulos;
    private int Numero;

    public Temporada(int Numero) {
        this(new LinkedList<Capitulo>(), Numero);
    }

    public Temporada(LinkedList<Capitulo> capitulos, int Numero) {
        this("", capitulos, new LinkedList<Subtitulo_De_Serie>(), Numero);
    }

    public Temporada(String nombre, LinkedList<Capitulo> capitulos, LinkedList<Subtitulo_De_Serie> subtitulos, int Numero) {
        this.nombre = nombre;
        this.capitulos = capitulos;
        this.subtitulos = subtitulos;
        this.Numero = Numero;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LinkedList<Capitulo> getCapitulos() {
        return capitulos;
    }

    /**
     * nesesaria optimizacion con ordenar
     *
     * @param cap
     * @return
     */
    public LinkedList<Capitulo> getCapitulos(int cap) {
        LinkedList<Capitulo> c = new LinkedList<Capitulo>();
        for (int i = 0; i < capitulos.size(); i++) {
            if (capitulos.get(i).getCapituloInicial() == cap) {
                c.add(capitulos.get(i));
            }
        }

        return c;
    }

    /**
     * nesesaria optimizacion con ordenar
     *
     * @param cap
     * @return
     */
    public LinkedList<Subtitulo_De_Serie> getSubtitulos(int cap) {
        LinkedList<Subtitulo_De_Serie> S = new LinkedList<Subtitulo_De_Serie>();
        for (int i = 0; i < subtitulos.size(); i++) {
            if (subtitulos.get(i).getCapituloInicial() == cap) {
                S.add(subtitulos.get(i));
            }
        }
        return S;
    }

    /**
     * nesesaria optimizacion con ordenar
     *
     * @param cap
     * @return
     */
    public LinkedList<Capitulo> getCapitulosMayoresQue(int cap) {
        LinkedList<Capitulo> c = new LinkedList<Capitulo>();
        for (int i = 0; i < capitulos.size(); i++) {
            if (capitulos.get(i).getCapituloInicial() > cap) {
                c.add(capitulos.get(i));
            }
        }

        return c;
    }

    /**
     * nesesaria optimizacion con ordenar
     *
     * @param cap
     * @return
     */
    public LinkedList<Capitulo> getCapitulosMenoresQue(int cap) {
        LinkedList<Capitulo> c = new LinkedList<Capitulo>();
        for (int i = 0; i < capitulos.size(); i++) {
            if (capitulos.get(i).getCapituloInicial() < cap) {
                c.add(capitulos.get(i));
            }
        }

        return c;
    }

    public void setCapitulos(LinkedList<Capitulo> capitulos) {
        this.capitulos = capitulos;
        //ordenarCapitulos();
    }

    public int getNumero() {
        return Numero;
    }

    public void setNumero(int Numero) {
        this.Numero = Numero;
    }

    public void addCapitulo(Capitulo c) {
        capitulos.add(c);
        // ordenarCapitulos();
    }

    public void addSubtitulo(Subtitulo_De_Serie s) {
        subtitulos.add(s);
    }

    public void ordenarCapitulos() {
        Collections.sort(capitulos);
    }

//    public boolean actualizar(Temporada t) {
//
//        if (t.Numero == Numero) {
//            ordenarCapitulos();
//            t.ordenarCapitulos();
//            for (Capitulo c : capitulos) {
//                addCapituloSiNoContiene(c);
//            }
//            return true;
//        }
//        return false;
//    }
    public boolean actualizar(Temporada t) {
        boolean add = false;
        // if (t.Numero == Numero) {
        //  ordenarCapitulos();
        //   t.ordenarCapitulos();
        for (int i = 0; i < t.capitulos.size(); i++) {
            capitulos.add(t.capitulos.get(i));
            add = true;
        }
//            for (Capitulo c : t.capitulos) {
//                capitulos.add(c);
//                add=true;
//            }
        // return true;
        //}
        //   return false;
        return add;
    }

    public void addAll(Temporada t) {
        LinkedList<Capitulo> C = new LinkedList<Capitulo>();
        for (int i = 0; i < t.capitulos.size(); i++) {
            if (!contiene(t.capitulos.get(i))) {
                C.add(t.capitulos.get(i));
            }
        }
        LinkedList<Subtitulo_De_Serie> S = new LinkedList<Subtitulo_De_Serie>();
        for (int i = 0; i < t.subtitulos.size(); i++) {
            if (!contiene(t.subtitulos.get(i))) {
                S.add(t.subtitulos.get(i));
            }
        }
        capitulos.addAll(C);
        subtitulos.addAll(S);
//        for (int i = 0; i < t.capitulos.size(); i++) {
//            capitulos.add(t.capitulos.get(i));
//        }
    }

    public boolean contiene(Capitulo c) {
        for (int i = 0; i < capitulos.size(); i++) {
            if (c.getNombre().equalsIgnoreCase(capitulos.get(i).getNombre())) {
                return true;
            }
        }
        return false;
    }

    public boolean contiene(Subtitulo_De_Serie s) {
        for (int i = 0; i < subtitulos.size(); i++) {
            if (s.getNombre().equalsIgnoreCase(subtitulos.get(i).getNombre())) {
                return true;
            }
        }
        return false;
    }

    public boolean addCapituloSiNoContiene(Capitulo c) {
        if (!contieneCapBusquedaRapida(c)) {
            addCapitulo(c);
            return true;
        }
        return false;
    }

    /**
     * asume que estan ordenados de menor (0) a mayor (leng)
     *
     * @param c
     * @return
     */
    public boolean contieneCapBusquedaRapida(Capitulo c) {
        if (!isEmpty()) {
            int indice = c.getCapituloInicial() <= capitulos.size() ? c.getCapituloInicial() - 1 : capitulos.size() - 1;

            if (capitulos.get(indice).getCapituloInicial() > c.getCapituloInicial()) {
                while (indice > -1) {
                    if (capitulos.get(indice).getCapituloInicial() == c.getCapituloInicial()) {
                        return true;
                    }
                    if (capitulos.get(indice--).getCapituloInicial() < c.getCapituloInicial()) {
                        break;
                    }
                    //  indice--;
                }
            } else {
                while (indice < capitulos.size()) {
                    if (capitulos.get(indice).getCapituloInicial() == c.getCapituloInicial()) {
                        return true;
                    }
                    if (capitulos.get(indice++).getCapituloInicial() > c.getCapituloInicial()) {
                        break;
                    }
                    // indice++;
                }
            }

        }

        return false;
    }

    public boolean isEmpty() {
        return capitulos.isEmpty()&&subtitulos.isEmpty();
    }

    public int sise() {
        return capitulos.size();
    }

    public Capitulo getCapitulo(int i) {
        return i < sise() ? capitulos.get(i) : null;
    }

    public void imprimir() {
        System.out.println("Temporada: " + Numero + "  " + nombre);
        for (Capitulo c : capitulos) {
            System.out.println(c);
        }
        if(!subtitulos.isEmpty()){
            System.out.println("Subtitulos:");
            for (Subtitulo_De_Serie s : subtitulos) {
                System.out.println(s);
            }
        }
    }

    @Override
    public Temporada copia() {
        //Temporada t=new Temporada(Numero);
        //   t.setNombre(nombre);
        LinkedList<Capitulo> c = new LinkedList<Capitulo>();
        for (int i = 0; i < capitulos.size(); i++) {
            c.add(capitulos.get(i));
        }
        LinkedList<Subtitulo_De_Serie> s = new LinkedList<Subtitulo_De_Serie>();
        for (int i = 0; i < subtitulos.size(); i++) {
            s.add(subtitulos.get(i));
        }
        //  t.setCapitulos(capitulos);
        return new Temporada(nombre, c, s, Numero);
    }
}
