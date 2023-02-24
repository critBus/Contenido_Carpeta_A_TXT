/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles;

import static Utiles.MetodosUtiles.MetodosUtiles.modificarTiempo;
import static Utiles.MetodosUtiles.Arreglos.arreglo;

/**
 * 1.2
 *
 * @author Rene
 */
public class Tiempo {

    private int MSegundo;
    private int Segundo;
    private int Minuto;
    private int Hora;
    private boolean admitirNegativo;

    public Tiempo(Tiempo t) {
        this(t.getHora(), t.getMinuto(), t.getSegundo(), t.MSegundo, t.isAdmitirNegativo());
    }

    public Tiempo(int Hora, int Minuto, int Segundo, int MSegundo) {
        this(Hora, Minuto, Segundo, MSegundo, false);
    }

    public Tiempo(int Hora, int Minuto, int Segundo, int MSegundo, boolean admitirNegativo) {
        this.MSegundo = MSegundo;
        //System.out.println("MSegundo=" + MSegundo);
        this.Segundo = Segundo;
        //System.out.println("Segundo=" + Segundo);
        this.Minuto = Minuto;
        //  System.out.println("Minuto=" + Minuto);
        this.Hora = Hora;
        // System.out.println("Hora=" + Hora);
        this.admitirNegativo = admitirNegativo;
        asegararPositivo();
    }

    public boolean isAdmitirNegativo() {
        return admitirNegativo;
    }

    public int getMSegundo() {
        return MSegundo;
    }

    public void setMSegundo(int MSegundo) {
        this.MSegundo = MSegundo;
    }

    public int getSegundo() {
        return Segundo;
    }

    public void setSegundo(int Segundo) {
        this.Segundo = Segundo;
    }

    public int getMinuto() {
        return Minuto;
    }

    public void setMinuto(int Minuto) {
        this.Minuto = Minuto;
    }

    public int getHora() {
        return Hora;
    }

    public void setHora(int Hora) {
        this.Hora = Hora;
    }

    public void modificar(int horas, int minutos, int segundos, int miliSegundos) {
        // int A[]={this.MSegundo,this.Segundo,this.Minuto,this.Hora};//arreglo(this.MSegundo,this.Segundo,this.Minuto,this.Hora)
        int A[] = modificarTiempo(0, arreglo(miliSegundos, segundos, minutos, horas), arreglo(this.MSegundo, this.Segundo, this.Minuto, this.Hora), 1000, 60, 60, 60);
        this.MSegundo = !admitirNegativo && A[0] < 0 ? 0 : A[0];
        this.Segundo = !admitirNegativo && A[1] < 0 ? 0 : A[1];
        this.Minuto = !admitirNegativo && A[2] < 0 ? 0 : A[2];
        this.Hora = !admitirNegativo && A[3] < 0 ? 0 : A[3];
    }

    public void aumentar(Tiempo t) {
        aumentar(t.getHora(), t.getMinuto(), t.getSegundo(), t.MSegundo);
    }

    public void restar(Tiempo t) {
        restar(t.getHora(), t.getMinuto(), t.getSegundo(), t.MSegundo);
    }

    public Tiempo copia(boolean admitirNegativo) {
        return new Tiempo(Hora, Minuto, Segundo, MSegundo, admitirNegativo);
    }

    public Tiempo copia() {
        return new Tiempo(Hora, Minuto, Segundo, MSegundo, admitirNegativo);
    }

    public void setAdmitirNegativo(boolean admitirNegativo) {
        this.admitirNegativo = admitirNegativo;
        asegararPositivo();
    }

    public void volverPostivo() {
        this.MSegundo = MSegundo < 0 ? MSegundo*-1 : MSegundo;
        this.Segundo =  Segundo < 0 ? Segundo*-1 : Segundo;
        this.Minuto =  Minuto < 0 ? Minuto*-1 : Minuto;
        this.Hora =  Hora < 0 ? Hora *-1: Hora;
    }

    public void asegararPositivo() {
        this.MSegundo = !admitirNegativo && MSegundo < 0 ? 0 : MSegundo;
        this.Segundo = !admitirNegativo && Segundo < 0 ? 0 : Segundo;
        this.Minuto = !admitirNegativo && Minuto < 0 ? 0 : Minuto;
        this.Hora = !admitirNegativo && Hora < 0 ? 0 : Hora;
    }

    public void aumentar(int horas, int minutos, int segundos, int miliSegundos) {
        modificar(horas, minutos, segundos, miliSegundos);
//        if (horas != 0) {
//            aumentarHoras(horas);
//        }
//        if (minutos != 0) {
//            aumentarMinutos(minutos);
//        }
//        if (segundos != 0) {
//            aumentarSegundos(segundos);
//        }
//        if (miliSegundos != 0) {
//            aumentarMSegundos(miliSegundos);
//        }

    }

    public void restar(int horas, int minutos, int segundos, int miliSegundos) {
        modificar(horas * -1, minutos * -1, segundos * -1, miliSegundos * -1);
//        if (horas != 0) {
//            System.out.println("1 horas=" + horas);
//            restarHoras(horas);
//            System.out.println("2 horas=" + horas);
//        }
//        if (minutos != 0) {
//            System.out.println("1 minutos=" + minutos);
//            restarMinutos(minutos);
//            System.out.println("2 minutos=" + minutos);
//        }
//        if (segundos != 0) {
//            System.out.println("1 segundos=" + segundos);
//            restarSegundos(segundos);
//            System.out.println("2 segundos=" + segundos);
//        }
//        if (miliSegundos != 0) {
//            System.out.println("1 miliSegundos=" + miliSegundos);
//            restarMSegundos(miliSegundos);
//            System.out.println("2 miliSegundos=" + miliSegundos);
//        }

    }

//    public void aumentarMSegundos(int a) {
//
//        int T = MSegundo;
//        T += a;
//        if (T >= 1000) {
//            aumentarSegundos(T / 1000);
//            T -= T / 1000 * 1000;
//        }
//        if (admitirNegativo && MSegundo < 0) {
//            if (T > 0 && !sonPositivosSegundos()) {
//                T -= 1000;
//                aumentarSegundos(1);
//            }
//        }
//
//        MSegundo = T;
//
//    }
//
//    public void aumentarSegundos(int a) {
//        int T = Segundo;
//        T += a;
//        if (T >= 60) {
//            aumentarMinutos(T / 60);
//            T -= T / 60 * 60;
//        }
//        if (admitirNegativo && Segundo < 0) {
//            if (T > 0 && !sonPositivosMinutos()) {
//                T -= 60;
//                aumentarMinutos(1);
//            }
//        }
//        Segundo = T;
//
//    }
//
//    public void aumentarMinutos(int a) {
//        int T = Minuto;
//        T += a;
//        if (T >= 60) {
//            aumentarHoras(T / 60);
//            T -= T / 60 * 60;
//        }
//        if (admitirNegativo && Minuto < 0) {
//            if (T > 0 && !sonPositivosHoras()) {
//                T -= 60;
//                aumentarHoras(1);
//            }
//        }
//        Minuto = T;
//
//    }
//
//    public void aumentarHoras(int a) {
//        Hora += a;
//    }
//
//    public void restarMSegundos(int a) {
//        System.out.println("1 a=" + a);
//        int T = a / 1000;
//        System.out.println("1 T=" + T);
//        if (T > 0) {
//            restarSegundos(T);
//            System.out.println("2 a=" + a);
//            System.out.println("2 T=" + T);
//            a -= (T * 1000);
//            System.out.println("3 a=" + a);
//        }
//        System.out.println("MSegundo=" + MSegundo);
//        if (a > MSegundo && admitirNegativo && MSegundo < 0) {
//            if (admitirNegativo && MSegundo < 0) {
//                MSegundo -= a;
//                if (MSegundo <= 1000) {
//                    MSegundo += 1000;
//                    restarSegundos(1);
//                }
//            }
//            return;
//
//        }
//
//        if (a > MSegundo && sonPositivosSegundos()) {
//            //   if (Math.abs(a) > Math.abs(MSegundo)) {
////            if (admitirNegativo && MSegundo < 0) {
////                MSegundo -= a;
////                if (MSegundo <= 1000) {
////                    MSegundo += 1000;
////                    restarSegundos(1);
////                }
////            } else {
//
//            MSegundo = 1000 + MSegundo - a;
//            System.out.println("MSegundo=" + MSegundo);
//            restarSegundos(1);
//            //   }
//        } else {
//            T = MSegundo;
//            T -= a;
//            if (T < 0) {
//                if (!sonPositivosSegundos()) {
//                    if (admitirNegativo) {
//                        MSegundo = T;
//                        System.out.println("this=" + this);
//                        if (sonPositivosSegundos()) {
//                            restarSegundos(1);
//                        }
//                    } else {
//                        ceroAbsoluto();
//                    }
//                } else {
//                    restarSegundos(((a - 1) / 1000) + 1);
//                    T = 1000 - (T * (-1) - (T / 1000) * 1000);
//                    MSegundo = T;
//                }
//            } else {
//                MSegundo = T;
//            }
//        }
//
//    }
//
//    public void restarSegundos(int a) {
//        System.out.println("1 a=" + a);
//        int T = a / 60;
//        System.out.println("1 T=" + T);
//        if (T > 0) {
//            restarMinutos(T);
//            System.out.println("2 a=" + a);
//            System.out.println("2 T=" + T);
//            a -= (T * 60);
//            System.out.println("3 a=" + a);
//        }
//        System.out.println("Segundo=" + Segundo);
//        if (a > Segundo && admitirNegativo && Segundo < 0) {
//            Segundo -= a;
//            if (Segundo <= 60) {
//                Segundo += 60;
//                restarMinutos(1);
//            }
//            return;
//        }
//
//        if (a > Segundo && sonPositivosMinutos()) {
//            //if (Math.abs(a) > Math.abs(Segundo)) {
////
////            if (admitirNegativo && Segundo < 0) {
////                Segundo -= a;
////                if (Segundo <= 60) {
////                    Segundo += 60;
////                    restarMinutos(1);
////                }
////            } else {
//            Segundo = 60 + Segundo - a;
//            restarMinutos(1);
//            // }
//        } else {
//            T = Segundo;
//            T -= a;
//            System.out.println("T=" + T);
//            if (T < 0) {
//                if (!sonPositivosMinutos()) {
//                    if (admitirNegativo) {
//                        //***************
//                        if (MSegundo > 0) {
//                            T++;
//                            MSegundo -= 1000;
//                        }
//                        //***************
//                        Segundo = T;
//                        if (sonPositivosMinutos()) {
//                            restarMinutos(1);
//                        }
//                    } else {
//                        ceroAbsoluto();
//                    }
//                } else {
//                    restarMinutos(((a - 1) / 60) + 1);
//                    T = 60 - (T * (-1) - (T / 60) * 60);
//                    Segundo = T;
//                }
//            } else {
//                Segundo = T;
//            }
//
//        }
//
//    }
//
//    public void restarMinutos(int a) {
//        int T = a / 60;
//        if (T > 0) {
//            restarHoras(T);
//            a -= (T * 60);
//        }
//
//        if (a > Minuto && admitirNegativo && Minuto < 0) {
//            Minuto -= a;
//            if (Minuto <= 60) {
//                Minuto += 60;
//                restarHoras(1);
//            }
//            return;
//        }
//        if (a > Minuto && sonPositivosHoras()) {
//            // if (Math.abs(a) > Math.abs(Minuto)) {
////            if (admitirNegativo && Minuto < 0) {
////                Minuto -= a;
////                if (Minuto <= 60) {
////                    Minuto += 60;
////                    restarHoras(1);
////                }
////            } else {
//            Minuto = 60 + Minuto - a;
//            restarHoras(1);
//            // }
//        } else {
//            T = Minuto;
//            T -= a;
//            if (T < 0) {
//                if (!sonPositivosHoras()) {
//                    if (admitirNegativo) {
//                        //*******************
//                        if (Segundo > 0 || MSegundo > 0) {
//                            T++;
//                        }
//                        if (Segundo > 0) {
//                            Segundo -= 60;
//                        }
//                        if (MSegundo > 0) {
//                            MSegundo -= 1000;
//                        }
//                        //****************
//                        if (sonPositivosHoras()) {
//                            restarHoras(1);
//                        }
//                        Minuto = T;
//                    } else {
//                        ceroAbsoluto();
//                    }
//                } else {
//                    restarHoras(((a - 1) / 60) + 1);
//                    T = 60 - (T * (-1) - (T / 60) * 60);
//                    Minuto = T;
//                }
//            } else {
//                Minuto = T;
//            }
//
//        }
//
//    }
//
//    public void restarHoras(int a) {
//        int T = Hora;
//        T -= a;
//        if (T < 0) {
//            if (admitirNegativo) {
//                //*******************
//                         if(Segundo>0||MSegundo>0||Minuto>0){
//                         T++;
//                         }
//                         if(Minuto>0){
//                         Minuto-=60;
//                         }
//                         if(Segundo>0){
//                         Segundo-=60;
//                         }
//                          if(MSegundo>0){
//                         MSegundo-=1000;
//                         }
//                         //****************
//                
//                Hora = T;
//            } else {
//                ceroAbsoluto();
//            }
//        } else {
//            Hora = T;
//        }
//
//    }
//
//    void ceroAbsoluto() {
//        Hora = 0;
//        Minuto = 0;
//        Segundo = 0;
//        MSegundo = 0;
//
//    }
    boolean sonCeroHoras() {
        return Hora == 0;
    }

    boolean sonCeroMinutos() {
        return (Minuto == 0) && (sonCeroHoras());
    }

    boolean sonCeroSegundos() {
        return (Segundo == 0) && (sonCeroMinutos());
    }

    boolean sonCeroMSegundos() {
        return (MSegundo == 0) && (sonCeroSegundos());
    }
    ///-----------------

    boolean sonPositivosHoras() {
        return Hora > 0;
    }

    boolean sonPositivosMinutos() {
        return (Minuto > 0) || (sonPositivosHoras());
    }

    boolean sonPositivosSegundos() {
        return (Segundo > 0) || (sonPositivosMinutos());
    }

    boolean sonPositivosMSegundos() {
        return (MSegundo > 0) || (sonPositivosSegundos());
    }

    //-------------------------
    boolean sonNegativosHoras() {
        return Hora < 0;
    }

    boolean sonNegativosMinutos() {
        return (Minuto < 0) || (sonNegativosHoras());
    }

    boolean sonNegativosSegundos() {
        return (Segundo < 0) || (sonNegativosMinutos());
    }

    boolean sonNegativosMSegundos() {
        return (MSegundo < 0) || (sonNegativosSegundos());
    }

    @Override
    public String toString() {

//        System.out.println("Hora=" + Hora);
//        System.out.println("Minuto=" + Minuto);
//        System.out.println("Segundo=" + Segundo);
//        System.out.println("MSegundo=" + MSegundo);
        return sonNegativosMSegundos() ? ("- " + Hora * -1 + ":" + Minuto * -1 + ":" + Segundo * -1 + ":" + MSegundo * -1) : (Hora + ":" + Minuto + ":" + Segundo + ":" + MSegundo);
    }

}
