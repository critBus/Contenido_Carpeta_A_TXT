/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles.Configuraciones.Ventanas;

import static Utiles.MetodosUtiles.Operaciones.mod;
import Utiles.ClasesUtiles.Interfases.Accion_Cargar_Cancelar;
import Utiles.ClasesUtiles.Admininistrador.Admininistrador_De_Guardado;
import Utiles.ClasesUtiles.Admininistrador.Ventana_Administrador;
import Utiles.ClasesUtiles.Configuraciones.*;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeTabla;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo.tipoFormato;
//import Utiles.ClasesUtiles.Configuraciones.Ventanas.Informacion_ConfiguracionDeVideo.tipoFormato;
import Utiles.ClasesUtiles.Interfases.editingTabla;
import Utiles.ClasesUtiles.Interfases.selectionFila;
import Utiles.ClasesUtiles.Tablas.Tabla;
import static Utiles.MetodosUtiles.Archivo.cargarArchivo;
import Utiles.MetodosUtiles.Arreglos;
import Utiles.MetodosUtiles.MetodosUtiles;
import Utiles.MetodosUtiles.Visual;
import static Utiles.MetodosUtiles.Visual.activarJComponent;
import static Utiles.MetodosUtiles.Visual.responerException;
import java.awt.GraphicsConfiguration;
import java.awt.event.ItemEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.ChangeEvent;

/**
 * Version 0.3
 *
 * @author Rene
 */
public class Ventana_ConfiguracionDeVideo extends javax.swing.JFrame {
//ConfiguracionDeVideo cdv;

    private TablasAmpliableConfiguracionDeVideo TActual, TOriginal;
//    private Informacion_ConfiguracionDeVideo inf;
    private String direccionAdministrador;
    private Ventana_Administrador<TablasAmpliableConfiguracionDeVideo> ventana_Administrador;
    private Accion_Cargar_Cancelar ac;
    private boolean inicializo = false;

    private enum get {

        STRING, BOOLEAN, LABEL_STRING, AMPLIAR, SET_STRING, SET_BOOLEAN;
        String A[], A2[][];
        boolean B[], B2[][];

    }
//boolean  inicio=false;

    /**
     * Creates new form Ventana_Configuracion
     */
    public Ventana_ConfiguracionDeVideo(TablasAmpliableConfiguracionDeVideo T, String direccionAdministrador, Accion_Cargar_Cancelar ac) {
        initComponents();
        this.direccionAdministrador = direccionAdministrador;
        this.ac = ac;
        this.TActual = T.copia();
        TOriginal = T.copia();
//        inicio=true;
        CBIcluirTemporadas.setSelected(TActual.getCdv().isIncluirTemporada());
        CBRenombrarCarpetasInternas.setSelected(TActual.getCdv().renombrarCarpetasInternas);
        actualizarFormato();
        actualizarConfiguaracion();
        inicializo = true;
        actualizarCXSaltarSiAntes(0);
        actualizarCXSaltarSiDespues(0);
        seguridad();
        setLocationRelativeTo(null);
//        TActual = new TablasAmpliableConfiguracionDeVideo(cdv);
    }

    public void actualizarCXSaltarSiAntes(int indice) {
        CXSaltarSiAntes.removeAllItems();
        for (int i = 0; i < TActual.getCdv().getSaltarAntesNumero().length; i++) {
            CXSaltarSiAntes.addItem(TActual.getCdv().getSaltarAntesNumero()[i][0]);
        }
//        System.out.println("e");
        if (indice >= 0 && indice < CXSaltarSiAntes.getItemCount()) {
            CXSaltarSiAntes.setSelectedIndex(indice);
        }
//        System.out.println("f");
//        int sel = CXSaltarSiAntes.getSelectedIndex();
//        if (sel >= 0) {
//            for (int i = 0; i < TActual.getCdv().getSaltarAntesNumero()[sel].length; i++) {
//                CXSaltarSiAntes.addItem(TActual.getCdv().getSaltarAntesNumero()[sel][i]);
//            }
//        }
    }

    public void actualizarCXSaltarSiDespues(int indice) {
        CXSaltarSiDespues.removeAllItems();
        for (int i = 0; i < TActual.getCdv().getSaltarDespuesNumero().length; i++) {
            CXSaltarSiDespues.addItem(TActual.getCdv().getSaltarDespuesNumero()[i][0]);
        }
        if (indice >= 0 && indice < CXSaltarSiDespues.getItemCount()) {
            CXSaltarSiDespues.setSelectedIndex(indice);
        }

//        int sel = CXSaltarSiDespues.getSelectedIndex();
//        if (sel >= 0) {
//            for (int i = 0; i < TActual.getCdv().getSaltarDespuesNumero()[sel].length; i++) {
//                CXSaltarSiDespues.addItem(TActual.getCdv().getSaltarDespuesNumero()[sel][i]);
//            }
//        }
    }

    @Override
    public void setVisible(boolean b) {
        CBRenombrarCarpetasInternas.setSelected(TActual.getCdv().renombrarCarpetasInternas);
        actualizarFormato();
        actualizarTablaCapitulos();
        actualizarLabel();
        seguridad();
        super.setVisible(b); //To change body of generated methods, choose Tools | Templates.
    }

    public void setDireccionAdministrador(String direccionAdministrador) {
        this.direccionAdministrador = direccionAdministrador;
    }

    public ConfiguracionDeVideo getCdvSelecionado() {
        return TActual.getCdvSelecionado();
    }

    private void actualizarFormato() {
        switch (TActual.getCdv().getTipoDeFormatoActual()) {
            case AUTOMATICO:
                RAutomatico.setSelected(true);
            case NINGUNO:
                RSinFormato.setSelected(true);
            case MANUAL:
                SCapitulos.setValue(TActual.getCdv().getFormato()[1]);
                STemporadas.setValue(TActual.getCdv().getFormato()[0]);
                RManual.setSelected(true);
        }
    }

    private void actualizarConfiguaracion() {
//        guardarTablas();
        actualizarTablaCapitulos();
        TActual.getCdv().setRenombrarCarpetasInternas(CBRenombrarCarpetasInternas.isSelected());
        TActual.getCdv().setIncluirTemporada(CBIcluirTemporadas.isSelected());
        if (RAutomatico.isSelected()) {
            TActual.getCdv().setFormato(tipoFormato.AUTOMATICO);
        } else if (RSinFormato.isSelected()) {
            TActual.getCdv().setFormato(tipoFormato.NINGUNO);
        } else if (RManual.isSelected()) {
            TActual.getCdv().setFormato(getFormatoManual());
        }
        actualizarLabel();
//        try {
////            guardarConfiguracion();
//        } catch (Exception ex) {
//            responerException(ex);
//        }
    }

    private int[] getFormatoManual() {
        return new int[]{(int) STemporadas.getValue(), (int) SCapitulos.getValue(), (int) SCapitulos.getValue(), (int) SCapitulos.getValue()};
    }

    public void actualizarTablaCapitulos() {
        editingTabla Et = new editingTabla() {

            @Override
            public void editingStopped(ChangeEvent e, JTable t, int fila, int columna) {
                try {
                    if (fila == t.getRowCount() - 1) {
                        if (columna != 0) {
//                            if (t.getColumnCount() == 2) {
////                                System.out.println(getUltimoString1(t) + " bbbbbbbbbbbbbbbb");
////                                TActual.actualizar(getArregloString2A(), getArregloBoolean(), getUltimoString1(t));
////                                System.out.println("bb " + getUltimoString1(t));
//                            } else {
////                                TActual.actualizar(getArregloString3A(), getArregloBoolean(), getUltimoString1(t), getUltimoString2(t));
////                                System.out.println("aaaaaaaaaa");
//                            }

                            ampliarGeterSeter();
                            actualizarTablaCapitulos();
                            //                            System.out.println("cc " + getUltimoString1(t));
                        } else {
                            t.setValueAt(false, fila, columna);
                        }

                    } else {
                        switch (columna) {
                            case 0:
//                                if(fila==t.getRowCount() - 1){
//                                
//                                }else{
//                                
//                                }
                                boolean incompleta = false;
                                for (int i = 1; i < t.getColumnCount(); i++) {
                                    if (t.getValueAt(fila, i) == null || MetodosUtiles.StringRealmenteVacio(((String) t.getValueAt(fila, i)))) {
                                        incompleta = true;
                                        break;
                                    }
                                }
                                if (incompleta) {
                                    t.setValueAt(false, fila, columna);
                                }

//                                    System.out.println("b");
                                getArregloBoolean()[fila] = getBoolean(t, fila);
                                break;
                            case 1:
                                int aumento = TPSeleccionDeTabla.getSelectedIndex() == 3 ? 1 : 0;
//                                System.out.println("a");
                                if (t.getColumnCount() == 3) {
                                    getArregloString3A()[fila][1] = getString1(t, fila);
                                } else {
                                    getArregloString2A()[fila + aumento] = getString1(t, fila);
                                }
                                for (int i = 1; i < t.getColumnCount(); i++) {
                                    if (t.getValueAt(fila, i) == null || MetodosUtiles.StringRealmenteVacio(((String) t.getValueAt(fila, i)))) {
                                        t.setValueAt(false, fila, 0);
                                        getArregloBoolean()[fila] = false;
                                        break;
                                    }
                                }
//                                System.out.println("b");
                                break;
                            case 2:
                                getArregloString3A()[fila][2] = getString2(t, fila);
                                for (int i = 1; i < t.getColumnCount(); i++) {
                                    if (t.getValueAt(fila, i) == null || MetodosUtiles.StringRealmenteVacio(((String) t.getValueAt(fila, i)))) {
                                        t.setValueAt(false, fila, 0);
                                        getArregloBoolean()[fila] = false;
                                        break;
                                    }
                                }
                                break;
                        }
                    }
                    seguridad();
                } catch (Exception ex) {
                    responerException(ex);
                }
            }

            @Override
            public void editingCanceled(ChangeEvent e, JTable t, int fila, int columna) {
                //   System.out.println("canc");
            }
        };
        selectionFila Sf = new selectionFila() {

            @Override
            public void mousePressed(MouseEvent e, JTable t) {

            }

        };

        ConfiguracionDeTabla cdt = new ConfiguracionDeTabla(PCapitulos, PTablaCapitulos, SPTablaCapitulos, JTablaCapitulos, getTablaActual(), Et, Sf, 40, -1, getColumnasEditables());
        Visual.actualizarTablaSeleccionable(cdt);
    }

//    public void TActualizar() {
//
//    }
    public static boolean getBoolean(JTable t, int fila) {
        return (boolean) t.getValueAt(fila, 0);
    }

    public static String getString1(JTable t, int fila) {
        return t.getValueAt(fila, 1) == null ? "" : (String) t.getValueAt(fila, 1);
    }

    public static String getString2(JTable t, int fila) {
        return t.getValueAt(fila, 2) == null ? "" : (String) t.getValueAt(fila, 2);
    }

    public static String getUltimoString1(JTable t) {
        String a = (String) t.getValueAt(t.getRowCount() - 1, 1);
//        System.out.println("1 a="+a);
        return a == null ? "" : (String) t.getValueAt(t.getRowCount() - 1, 1);
    }

    public static String getUltimoString2(JTable t) {
        String a = (String) t.getValueAt(t.getRowCount() - 1, 2);
//         System.out.println("2 a="+a);
        return a == null ? "" : (String) t.getValueAt(t.getRowCount() - 1, 2);
    }

    public boolean isDosColumnas() {
        return !(TPSeleccionDeTabla.getSelectedIndex() == 0 && RRodearIgnorar.isSelected());
        //|| (TPSeleccionDeTabla.getSelectedIndex() == 1 && RTerminacionesNumericas.isSelected());
    }

    public int[] getColumnasEditables() {
        return isDosColumnas() ? new int[]{0, 1, 2} : new int[]{0, 1};
    }

    public Tabla getTablaActual() {
        Object o = getArregloString();
//        String a[] = null, b[][] = null;
        if (o instanceof String[]) {
            if (TPSeleccionDeTabla.getSelectedIndex() == 3) {
                return Tabla.crearStringSeleccionableAmpliable(adaptarMenos0((String[]) o), getArregloBoolean());
            }
            return Tabla.crearStringSeleccionableAmpliable((String[]) o, getArregloBoolean());
//        a=(String[]) o;
        } else {

            return Tabla.crearStringSeleccionableAmpliable((String[][]) o, getArregloBoolean());
//        b=(String[][]) o;
        }
//        return Tabla.crearStringSeleccionableAmpliable(, TActual.getDespuesIngnorar());
    }

    private String[] adaptarMenos0(String[] A) {
        String[] B = new String[A.length == 0 ? 0 : A.length - 1];
        for (int i = 0; i < B.length; i++) {
            B[i] = A[i + 1];
        }
        return B;
    }

    private String[][] adaptarMenos0(String[][] A) {
        String[][] B = new String[A.length][];
        for (int i = 0; i < A.length; i++) {
            B[i] = new String[A.length == 0 ? 0 : A.length - 1];
            for (int j = 0; j < 10; j++) {
                B[i][j] = A[i][j + 1];
            }
        }
        return B;
    }

    public String[] getArregloString2A() {
        return (String[]) get(get.STRING);
    }

    public String[][] getArregloString3A() {
        return (String[][]) get(get.STRING);
    }

    public Object getArregloString() {
        return get(get.STRING);
    }

    public boolean[] getArregloBoolean() {
        return (boolean[]) get(get.BOOLEAN);
    }

    private Object[] ampliar(String A[], boolean B[]) {
        A = Arreglos.ampliarArregloObject(A, 1);
        B = Arreglos.ampliarArreglo(B, 1);
        A[A.length - 1] = getUltimoString1(JTablaCapitulos);
        B[B.length - 1] = true;
        return new Object[]{A, B};
    }

    private Object[] ampliar(String A[][], boolean B[]) {
        A = Arreglos.ampliarArreglo(A, 1);
        B = Arreglos.ampliarArreglo(B, 1);
        String a = getUltimoString1(JTablaCapitulos);
        String b = getUltimoString2(JTablaCapitulos);
        A[A.length - 1][0] = a;
        A[A.length - 1][1] = b;
        B[B.length - 1] = true;
        return new Object[]{A, B};
    }

    private void ampliarGeterSeter() {
        Object o = getArregloString();
        Object O[] = null;
        if (o instanceof String[]) {
            O = ampliar(getArregloString2A(), getArregloBoolean());

            setStringActual((String[]) O[0]);
        } else {
            O = ampliar(getArregloString3A(), getArregloBoolean());
            setStringActual((String[][]) O[0]);
        }
        setBooleanActual((boolean[]) O[1]);
    }

    private void setStringActual(String A[]) {
        get.SET_STRING.A = A;
        get(get.SET_STRING);
    }

    private void setStringActual(String A[][]) {
        get.SET_STRING.A2 = A;
        get(get.SET_STRING);
    }

    private void setBooleanActual(boolean A[]) {
        get.SET_BOOLEAN.B = A;
        get(get.SET_BOOLEAN);
    }

    private void setBooleanActual(boolean B[][]) {
        get.SET_BOOLEAN.B2 = B;
        get(get.SET_BOOLEAN);
    }

    private Object get(get g) {
        switch (TPSeleccionDeTabla.getSelectedIndex()) {
            case 0:
                if (RSaltarAntes.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getAntesIngnorar();
                        case STRING:
                            return TActual.getCdv().antesIngnorar;
                        case LABEL_STRING:
                            return "Saltar el numero si antes de el se encuentra: ";
                        case SET_BOOLEAN:
                            TActual.setAntesIngnorar(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setAntesIngnorar(g.A);
                            return g.A;
//                        case AMPLIAR:
//                            Object a[] = ampliar(TActual.getCdv().antesIngnorar, TActual.getAntesIngnorar());
//                          TActual.setAntesIngnorar((boolean[]) a[1]);
//                            TActual.getCdv().setAntesIngnorar((String[]) a[0]);
//
//                            return null;
                    }

                }
                if (RSaltarDespues.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getDespuesIngnorar();
                        case STRING:
                            return TActual.getCdv().despuesIngnorar;
                        case LABEL_STRING:
                            return "Saltar el numero si despues de el se encuentra:";
                        case SET_BOOLEAN:
                            TActual.setDespuesIngnorar(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setDespuesIngnorar(g.A);
                            return g.A;
                    }
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().despuesIngnorar, TActual.getDespuesIngnorar());
                }
                if (RRodearIgnorar.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getRodearIgnorar();
                        case STRING:
                            return TActual.getCdv().rodearIgnorar;
                        case LABEL_STRING:
                            return "Ignorar si el numero esta rodeado por:";
                        case SET_BOOLEAN:
                            TActual.setRodearIgnorar(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setRodearIgnorar(g.A2);
                            return g.A;
                    }
////                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().rodearIgnorar, TActual.getRodearIgnorar());
                }
                if (RUnion.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getUnion();
                        case STRING:
                            return TActual.getCdv().union;
                        case LABEL_STRING:
                            return "Identifica a los numeros antes y despues como capitulos que coniene";
                        case SET_BOOLEAN:
                            TActual.setUnion(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setUnion(g.A);
                            return g.A;
                    }

//                    return Tabla.crearStringSeleccionableAmpliable(inf.getT().getCdv().union, inf.getT().getUnion());
                }

            case 1:
                if (RTemporadas.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getIdentificadoresTemporadas();
                        case STRING:
                            return TActual.getCdv().identificadoresTemporadas;
                        case LABEL_STRING:
                            return "Se identifica como un sinonimo de temporada";
                        case SET_BOOLEAN:
                            TActual.setIdentificadoresTemporadas(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setIdentificadoresTemporadas(g.A);
                            return g.A;
                    }
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().identificadoresTemporadas, TActual.getIdentificadoresTemporadas());
                }
                if (RCapitulo.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getIdentificadoresCapitulo();
                        case STRING:
                            return TActual.getCdv().identificadoresCapitulo;
                        case LABEL_STRING:
                            return "Se identifica como un sinonimo de capitulo";
                        case SET_BOOLEAN:
                            TActual.setIdentificadoresCapitulo(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setIdentificadoresCapitulo(g.A);
                            return g.A;
                    }
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().identificadoresCapitulo, TActual.getIdentificadoresCapitulo());
                }
                if (RCantidadCapituloTemporada.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getIdentificadoresCantidadCapituloTemporada();
                        case STRING:
                            return TActual.getCdv().identificadoresCantidadCapituloTemporada;
                        case LABEL_STRING:
                            return "Se identifica como un sinonimo de la cantidad de capitulos que coniene";
                        case SET_BOOLEAN:
                            TActual.setIdentificadoresCantidadCapituloTemporada(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setIdentificadoresCantidadCapituloTemporada(g.A);
                            return g.A;
                    }
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().identificadoresCantidadCapituloTemporada, TActual.getIdentificadoresCantidadCapituloTemporada());
                }
//                if (RTerminacionesNumericas.isSelected()) {
//                    switch (g) {
//                        case BOOLEAN:
//                            return TActual.getTerminacionesNumericas();
//                        case STRING:
//                            return TActual.getCdv().terminacionesNumericas;
//                    }
//
//                }
            case 2:
                if (RSaltarAlPricipio.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getSaltarAlPrincipio();
                        case STRING:
                            return TActual.getCdv().saltarAlPrincipio;
                        case LABEL_STRING:
                            return "El nombre esencial del capitulo comineza despues de:";
                        case SET_BOOLEAN:
                            TActual.setSaltarAlPrincipio(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setSaltarAlPrincipio(g.A);
                            return g.A;
                    }
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().saltarAlPrincipio, TActual.getSaltarAlPrincipio());
                }
                if (RDetenerAlFinal.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getDetenciones();
                        case STRING:
                            return TActual.getCdv().detenciones;
                        case LABEL_STRING:
                            return "El nombre esencial del capitulo termina cuando se encuentra con:";
                        case SET_BOOLEAN:
                            TActual.setDetenciones(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setDetenciones(g.A);
                            return g.A;
                    }
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().detenciones, TActual.getDetenciones());
                }
                if (RSeparaciones.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getSeparacionString();
                        case STRING:
                            return TActual.getCdv().separacionString;
                        case LABEL_STRING:
                            return "Caracteres que se consideran espacios";
                        case SET_BOOLEAN:
                            TActual.setSeparacionString(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setSeparacionString(g.A);
                            return g.A;
                    }
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().separacionString, TActual.getSeparacionString());
                }
                if (RSaltarHastaDespues.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getSaltarHastaDespuesDe();
                        case STRING:
                            return TActual.getCdv().saltarHastaDespuesDe;
                        case LABEL_STRING:
                            return "Se ignora todo el contenido anterior";
                        case SET_BOOLEAN:
                            TActual.setSaltarHastaDespuesDe(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setSaltarHastaDespuesDe(g.A);
                            return g.A;
                    }
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().saltarHastaDespuesDe, TActual.getSaltarHastaDespuesDe());
                }
                if (RNoSaltarAlPrincipio.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getNoSaltarAlPrincipio();
                        case STRING:
                            return TActual.getCdv().noSaltarAlPrincipio;
                        case LABEL_STRING:
                            return "En caso de ser identificar un salto al principio se cancela";
                        case SET_BOOLEAN:
                            TActual.setNoSaltarAlPrincipio(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setNoSaltarAlPrincipio(g.A);
                            return g.A;
                    }
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().noSaltarAlPrincipio, TActual.getNoSaltarAlPrincipio());
                }
                if (RDetencionesAbsolutas.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getDetencionesAbsolutas();
                        case STRING:
                            return TActual.getCdv().detencionesAbsolutas;
                        case LABEL_STRING:
                            return "Detiene por completo el analizis del nombre del archivo";
                        case SET_BOOLEAN:
                            TActual.setDetencionesAbsolutas(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setDetencionesAbsolutas(g.A);
                            return g.A;
                    }
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().detencionesAbsolutas, TActual.getDetencionesAbsolutas());
                }
                if (RPalabrasInvalidas.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
                            return TActual.getInvalidosPaquete();
                        case STRING:
                            return TActual.getCdv().invalidosPaquete;
                        case LABEL_STRING:
                            return "Las frases conformadas solo con estas palabras son ignoradas";
                        case SET_BOOLEAN:
                            TActual.setInvalidosPaquete(g.B);
                            return g.B;
                        case SET_STRING:
                            TActual.getCdv().setInvalidosPaquete(g.A);
                            return g.A;
                    }
                }
            case 3:
                if (RSaltarSiAntes.isSelected()) {

                    switch (g) {
                        case BOOLEAN:
//                            return TActual.getSaltarAntesNumero();
                            return TActual.getSaltarAntesNumero()[CXSaltarSiAntes.getSelectedIndex()];
                        case STRING:
                            return TActual.getCdv().saltarAntesNumero[CXSaltarSiAntes.getSelectedIndex()];
//                            return TActual.getCdv().saltarAntesNumero;
                        case LABEL_STRING:
                            return "Saltar el numero si antes de el se encuentra: ";
                        case SET_BOOLEAN:

                            TActual.getSaltarAntesNumero()[CXSaltarSiAntes.getSelectedIndex()] = g.B;
//                            TActual.setSaltarAntesNumero(g.B2);
                            return g.B;
                        case SET_STRING:
//                            TActual.getCdv().setSaltarAntesNumero(g.A2);
//                            return g.A2;
                            TActual.getCdv().saltarAntesNumero[CXSaltarSiAntes.getSelectedIndex()] = g.A;
                            return g.A;
                    }

                }
                if (RSaltarSiDespues.isSelected()) {
                    switch (g) {
                        case BOOLEAN:
//                            return TActual.getSaltarAntesNumero();
                            return TActual.getSaltarDespuesNumero()[CXSaltarSiDespues.getSelectedIndex()];
                        case STRING:
                            return TActual.getCdv().saltarDespuesNumero[CXSaltarSiDespues.getSelectedIndex()];
//                            return TActual.getCdv().saltarAntesNumero;
                        case LABEL_STRING:
                            return "Saltar el numero si antes de el se encuentra: ";
                        case SET_BOOLEAN:

                            TActual.getSaltarDespuesNumero()[CXSaltarSiDespues.getSelectedIndex()] = g.B;
//                            TActual.setSaltarAntesNumero(g.B2);
                            return g.B;
                        case SET_STRING:
//                            TActual.getCdv().setSaltarAntesNumero(g.A2);
//                            return g.A2;
                            TActual.getCdv().saltarDespuesNumero[CXSaltarSiDespues.getSelectedIndex()] = g.A;
                            return g.A;
                    }
//                    switch (g) {
//                        case BOOLEAN:
//                            return TActual.getSaltarDespuesNumero();
//                        case STRING:
//                            return TActual.getCdv().saltarDespuesNumero;
//                        case LABEL_STRING:
//                            return "Saltar el numero si despues de el se encuentra: ";
//                        case SET_BOOLEAN:
//                            TActual.setSaltarDespuesNumero(g.B2);
//                            return g.B2;
//                        case SET_STRING:
//                            TActual.getCdv().setSaltarDespuesNumero(g.A2);
//                            return g.A2;
//                    }

                }
        }
        return null;
    }

    public int indiceCX() {
        return RSaltarSiAntes.isSelected() ? CXSaltarSiAntes.getSelectedIndex() : CXSaltarSiDespues.getSelectedIndex();
    }

    public void actualizarLabel() {
        L.setText((String) get(get.LABEL_STRING));
//        Visual.centrarHorizantalLabel(PCapitulos, L);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        BGIgnorarNumero = new javax.swing.ButtonGroup();
        BGIdentificadores = new javax.swing.ButtonGroup();
        BGRecortar = new javax.swing.ButtonGroup();
        BGFormato = new javax.swing.ButtonGroup();
        BGNumerosExpecificos = new javax.swing.ButtonGroup();
        PTodo = new javax.swing.JPanel();
        TPTodo = new javax.swing.JTabbedPane();
        PGeneral = new javax.swing.JPanel();
        CBRenombrarCarpetasInternas = new javax.swing.JCheckBox();
        PFormato = new javax.swing.JPanel();
        RSinFormato = new javax.swing.JRadioButton();
        SCapitulos = new javax.swing.JSpinner();
        LCapitulos = new javax.swing.JLabel();
        RAutomatico = new javax.swing.JRadioButton();
        STemporadas = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        RManual = new javax.swing.JRadioButton();
        jLabel2 = new javax.swing.JLabel();
        CBIcluirTemporadas = new javax.swing.JCheckBox();
        PCapitulos = new javax.swing.JPanel();
        SPTablaCapitulos = new javax.swing.JScrollPane();
        PTablaCapitulos = new javax.swing.JPanel();
        JTablaCapitulos = new javax.swing.JTable();
        TPSeleccionDeTabla = new javax.swing.JTabbedPane();
        PIgnorarNumero = new javax.swing.JPanel();
        RSaltarAntes = new javax.swing.JRadioButton();
        RUnion = new javax.swing.JRadioButton();
        RSaltarDespues = new javax.swing.JRadioButton();
        RRodearIgnorar = new javax.swing.JRadioButton();
        PIdentificadores = new javax.swing.JPanel();
        RTemporadas = new javax.swing.JRadioButton();
        RCapitulo = new javax.swing.JRadioButton();
        RCantidadCapituloTemporada = new javax.swing.JRadioButton();
        PRecortar = new javax.swing.JPanel();
        RSaltarAlPricipio = new javax.swing.JRadioButton();
        RSaltarHastaDespues = new javax.swing.JRadioButton();
        RDetenerAlFinal = new javax.swing.JRadioButton();
        RSeparaciones = new javax.swing.JRadioButton();
        RNoSaltarAlPrincipio = new javax.swing.JRadioButton();
        RDetencionesAbsolutas = new javax.swing.JRadioButton();
        RPalabrasInvalidas = new javax.swing.JRadioButton();
        PNumerosExpecificos = new javax.swing.JPanel();
        RSaltarSiAntes = new javax.swing.JRadioButton();
        RSaltarSiDespues = new javax.swing.JRadioButton();
        CXSaltarSiAntes = new javax.swing.JComboBox();
        CXSaltarSiDespues = new javax.swing.JComboBox();
        BAgregarSaltarSiAntes = new javax.swing.JButton();
        BEliminarSaltarSiAntes = new javax.swing.JButton();
        BAgregarSaltarSiDespues = new javax.swing.JButton();
        BEliminarSaltarSiDespues = new javax.swing.JButton();
        L = new javax.swing.JLabel();
        BCargarConfiguracion = new javax.swing.JButton();
        BCancelar = new javax.swing.JButton();
        BConfiguracionPredetermiada = new javax.swing.JButton();
        BAceptar = new javax.swing.JButton();
        BGuardarConfiguracion = new javax.swing.JButton();

        BGIgnorarNumero.add(RSaltarAntes);
        BGIgnorarNumero.add(RSaltarDespues);
        BGIgnorarNumero.add(RRodearIgnorar);
        BGIgnorarNumero.add(RUnion);

        BGIdentificadores.add(RTemporadas);
        BGIdentificadores.add(RCapitulo);
        BGIdentificadores.add(RCantidadCapituloTemporada);

        BGRecortar.add(RSaltarAlPricipio);
        BGRecortar.add(RDetenerAlFinal);
        BGRecortar.add(RSeparaciones);
        BGRecortar.add(RSaltarHastaDespues);
        BGRecortar.add(RNoSaltarAlPrincipio);
        BGRecortar.add(RDetencionesAbsolutas);
        BGRecortar.add(RPalabrasInvalidas);

        BGFormato.add(RSinFormato);
        BGFormato.add(RAutomatico);
        BGFormato.add(RManual);

        BGNumerosExpecificos.add(RSaltarSiAntes);
        BGNumerosExpecificos.add(RSaltarSiDespues);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Configuracion de Video");
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                cerroVentana(evt);
            }
        });

        PTodo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PGeneral.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        CBRenombrarCarpetasInternas.setText("Renombrar Carpetas Internas");
        PGeneral.add(CBRenombrarCarpetasInternas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 60, -1, -1));

        PFormato.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        PFormato.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RSinFormato.setText("Sin formato");
        RSinFormato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RSinFormatoapretoFormato(evt);
            }
        });
        PFormato.add(RSinFormato, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        SCapitulos.setModel(new javax.swing.SpinnerNumberModel(2, 1, 5, 1));
        SCapitulos.setEnabled(false);
        PFormato.add(SCapitulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 90, -1, -1));

        LCapitulos.setText("Capitulos");
        PFormato.add(LCapitulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 90, -1, 20));

        RAutomatico.setSelected(true);
        RAutomatico.setText("Automatico");
        RAutomatico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RAutomaticoapretoFormato(evt);
            }
        });
        PFormato.add(RAutomatico, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, -1, -1));

        STemporadas.setModel(new javax.swing.SpinnerNumberModel(2, 1, 5, 1));
        STemporadas.setEnabled(false);
        PFormato.add(STemporadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, -1, -1));

        jLabel1.setText("Temporadas");
        PFormato.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, 20));

        RManual.setText("Manual");
        RManual.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                RManualapretoFormato(evt);
            }
        });
        PFormato.add(RManual, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        jLabel2.setText("Cantidad de ceros conque se daran los resultados");
        PFormato.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 10, -1, -1));

        PGeneral.add(PFormato, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 140, 360, 130));

        CBIcluirTemporadas.setText("Icluir Temporadas");
        CBIcluirTemporadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CBIcluirTemporadasActionPerformed(evt);
            }
        });
        PGeneral.add(CBIcluirTemporadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 90, -1, -1));

        TPTodo.addTab("General", PGeneral);

        PCapitulos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        PTablaCapitulos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JTablaCapitulos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        PTablaCapitulos.add(JTablaCapitulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        SPTablaCapitulos.setViewportView(PTablaCapitulos);

        PCapitulos.add(SPTablaCapitulos, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 390, 200));

        TPSeleccionDeTabla.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                apretoTPSeleccionDeTabla(evt);
            }
        });

        PIgnorarNumero.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RSaltarAntes.setSelected(true);
        RSaltarAntes.setText("Saltar Antes");
        RSaltarAntes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PIgnorarNumero.add(RSaltarAntes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, -1, -1));

        RUnion.setText("Union");
        RUnion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PIgnorarNumero.add(RUnion, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        RSaltarDespues.setText("Saltar Despues");
        RSaltarDespues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PIgnorarNumero.add(RSaltarDespues, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, -1, -1));

        RRodearIgnorar.setText("Rodear Ignorar");
        RRodearIgnorar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PIgnorarNumero.add(RRodearIgnorar, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        TPSeleccionDeTabla.addTab("Ignorar Numero", PIgnorarNumero);

        PIdentificadores.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RTemporadas.setSelected(true);
        RTemporadas.setText("Temporadas ");
        RTemporadas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PIdentificadores.add(RTemporadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 40, 230, -1));

        RCapitulo.setText("Capitulo");
        RCapitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PIdentificadores.add(RCapitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 10, -1, -1));

        RCantidadCapituloTemporada.setText("Cantidad de Capitulos en Temporada");
        RCantidadCapituloTemporada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PIdentificadores.add(RCantidadCapituloTemporada, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        TPSeleccionDeTabla.addTab("Identificadores", PIdentificadores);

        PRecortar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RSaltarAlPricipio.setSelected(true);
        RSaltarAlPricipio.setText("Saltar al Pricipio");
        RSaltarAlPricipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PRecortar.add(RSaltarAlPricipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        RSaltarHastaDespues.setText("Saltar Hasta Despues");
        RSaltarHastaDespues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PRecortar.add(RSaltarHastaDespues, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        RDetenerAlFinal.setText("Detener Al Final");
        RDetenerAlFinal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PRecortar.add(RDetenerAlFinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 10, -1, -1));

        RSeparaciones.setText("Separaciones");
        RSeparaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PRecortar.add(RSeparaciones, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, -1, -1));

        RNoSaltarAlPrincipio.setText("No Saltar Al Principio");
        RNoSaltarAlPrincipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PRecortar.add(RNoSaltarAlPrincipio, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        RDetencionesAbsolutas.setText("Detenciones Absolutas");
        RDetencionesAbsolutas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PRecortar.add(RDetencionesAbsolutas, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 40, -1, -1));

        RPalabrasInvalidas.setText("Palabras Invalidas");
        RPalabrasInvalidas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PRecortar.add(RPalabrasInvalidas, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 10, -1, -1));

        TPSeleccionDeTabla.addTab("Recortar", PRecortar);

        PNumerosExpecificos.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        RSaltarSiAntes.setSelected(true);
        RSaltarSiAntes.setText("Saltar si  Antes");
        RSaltarSiAntes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PNumerosExpecificos.add(RSaltarSiAntes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));

        RSaltarSiDespues.setText("Saltar si Despues");
        RSaltarSiDespues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoRSeleccion(evt);
            }
        });
        PNumerosExpecificos.add(RSaltarSiDespues, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, -1, -1));

        CXSaltarSiAntes.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CXSaltarSiAntes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                apretoCXSaltar(evt);
            }
        });
        PNumerosExpecificos.add(CXSaltarSiAntes, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 10, 80, -1));

        CXSaltarSiDespues.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CXSaltarSiDespues.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                apretoCXSaltar(evt);
            }
        });
        PNumerosExpecificos.add(CXSaltarSiDespues, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 40, 80, -1));

        BAgregarSaltarSiAntes.setText("+");
        BAgregarSaltarSiAntes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarSaltarSiAntesActionPerformed(evt);
            }
        });
        PNumerosExpecificos.add(BAgregarSaltarSiAntes, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 10, -1, -1));

        BEliminarSaltarSiAntes.setText("-");
        BEliminarSaltarSiAntes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarSaltarSiAntesActionPerformed(evt);
            }
        });
        PNumerosExpecificos.add(BEliminarSaltarSiAntes, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        BAgregarSaltarSiDespues.setText("+");
        BAgregarSaltarSiDespues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAgregarSaltarSiDespuesActionPerformed(evt);
            }
        });
        PNumerosExpecificos.add(BAgregarSaltarSiDespues, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 40, -1, -1));

        BEliminarSaltarSiDespues.setText("-");
        BEliminarSaltarSiDespues.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEliminarSaltarSiDespuesActionPerformed(evt);
            }
        });
        PNumerosExpecificos.add(BEliminarSaltarSiDespues, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 40, -1, -1));

        TPSeleccionDeTabla.addTab("Numeros Expecificos", PNumerosExpecificos);

        PCapitulos.add(TPSeleccionDeTabla, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 100));

        L.setText("Saltar el numero si antes de el se encuentra: ");
        PCapitulos.add(L, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 110, -1, 20));

        TPTodo.addTab("Capitulos", PCapitulos);

        PTodo.add(TPTodo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 380));

        BCargarConfiguracion.setText("Cargar ");
        BCargarConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCargarConfiguracionActionPerformed(evt);
            }
        });
        PTodo.add(BCargarConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 110, 30));

        BCancelar.setText("Cancelar");
        BCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCancelarActionPerformed(evt);
            }
        });
        PTodo.add(BCancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 110, 30));

        BConfiguracionPredetermiada.setText(" Predetermiada");
        BConfiguracionPredetermiada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BConfiguracionPredetermiadaActionPerformed(evt);
            }
        });
        PTodo.add(BConfiguracionPredetermiada, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 390, 140, 70));

        BAceptar.setText("Aceptar");
        BAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAceptarActionPerformed(evt);
            }
        });
        PTodo.add(BAceptar, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 430, 100, 30));

        BGuardarConfiguracion.setText("Guardar ");
        BGuardarConfiguracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGuardarConfiguracionActionPerformed(evt);
            }
        });
        PTodo.add(BGuardarConfiguracion, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 390, 100, 30));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void RSinFormatoapretoFormato(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RSinFormatoapretoFormato
        // mirar("apreto antes");
//        actualizarConfiguaracion();
        seguridad();
        //   mirar("despues antes");
    }//GEN-LAST:event_RSinFormatoapretoFormato

    private void RAutomaticoapretoFormato(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RAutomaticoapretoFormato
        // mirar("apreto antes");
//        actualizarConfiguaracion();
        seguridad();
        //   mirar("despues antes");
    }//GEN-LAST:event_RAutomaticoapretoFormato

    private void RManualapretoFormato(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_RManualapretoFormato
        // mirar("apreto antes");
//        actualizarConfiguaracion();
        seguridad();
        //   mirar("despues antes");
    }//GEN-LAST:event_RManualapretoFormato

    private void BCargarConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCargarConfiguracionActionPerformed
        try {
            visualizarVentanaAdministradorConfiguraciones();
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BCargarConfiguracionActionPerformed

    private void BCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCancelarActionPerformed
//        visualizarVentanaPrincipal();
        TActual = TOriginal.copia();
        ac.cancelar();
    }//GEN-LAST:event_BCancelarActionPerformed

    private void BConfiguracionPredetermiadaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BConfiguracionPredetermiadaActionPerformed
        TActual.establecerPredeterminado();
        actualizarTablaCapitulos();
        CBIcluirTemporadas.setSelected(TActual.getCdv().isIncluirTemporada());
        CBRenombrarCarpetasInternas.setSelected(TActual.getCdv().isRenombrarCarpetasInternas());
        RAutomatico.setSelected(true);
        SCapitulos.setValue(2);
        STemporadas.setValue(2);
        seguridad();
//        actualizarConfiguaracion();
//        ponerTablasEnGUI();
//        addFilaATodas();
//       
//        actualizarVariables();
    }//GEN-LAST:event_BConfiguracionPredetermiadaActionPerformed

    private void BGuardarConfiguracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGuardarConfiguracionActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el nuevo nombre de la direccion");

        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                Admininistrador_De_Guardado<TablasAmpliableConfiguracionDeVideo> A = (Admininistrador_De_Guardado<TablasAmpliableConfiguracionDeVideo>) cargarArchivo(direccionAdministrador);
                actualizarConfiguaracion();
                A.agregarInformacion(nuevoNombre, TActual);
                A.guardarAdminastrador(direccionAdministrador);
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BGuardarConfiguracionActionPerformed

    private void BAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAceptarActionPerformed
//        actualizarConfiguaracion();
//        visualizarVentanaPrincipal();
        actualizarConfiguaracion();
        TOriginal = TActual.copia();
        ac.aceptar_Y_cargar();
    }//GEN-LAST:event_BAceptarActionPerformed

    private void apretoRSeleccion(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoRSeleccion
        actualizarConfiguaracion();
        seguridad();
    }//GEN-LAST:event_apretoRSeleccion

    private void apretoTPSeleccionDeTabla(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_apretoTPSeleccionDeTabla
        if (TActual != null && TOriginal != null && ac != null && TActual.getCdv() != null && TActual.getCdv().antesIngnorar != null) {
            actualizarConfiguaracion();
        }

    }//GEN-LAST:event_apretoTPSeleccionDeTabla

    private void cerroVentana(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_cerroVentana
//       alCerrarVentana();
    }//GEN-LAST:event_cerroVentana

    private void CBIcluirTemporadasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CBIcluirTemporadasActionPerformed
        TActual.getCdv().setIncluirTemporada(CBIcluirTemporadas.isSelected());
    }//GEN-LAST:event_CBIcluirTemporadasActionPerformed

    private void BAgregarSaltarSiAntesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarSaltarSiAntesActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el numero de la seccion");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                int numero = Integer.parseInt(nuevoNombre.trim());
                int indice = TActual.getCdv().addSaltarAntes(numero);

                if (indice >= 0) {
                    TActual.setSaltarAntesNumero(Arreglos.añadirEnElIndice(TActual.getSaltarAntesNumero(), new boolean[]{}, indice));

                }
//                TActual.getCdv().setSaltarAntesNumero(Arreglos.ampliarArreglo(TActual.getCdv().getSaltarAntesNumero(), 1));
//                int lengh = TActual.getCdv().getSaltarAntesNumero().length;
//                TActual.getCdv().getSaltarAntesNumero()[lengh == 0 ? 0 : lengh - 1] = new String[]{numero + ""};

//                TActual.setSaltarAntesNumero(Arreglos.ampliarArreglo(TActual.getSaltarAntesNumero(), 1));
//                lengh = TActual.getSaltarAntesNumero().length;
//                TActual.getSaltarAntesNumero()[lengh == 0 ? 0 : lengh - 1] = new boolean[]{};
//                System.out.println("a");
                inicializo = false;
                actualizarCXSaltarSiAntes(mod(indice));
                inicializo = true;
//                System.out.println("b");
                actualizarTablaCapitulos();
//                System.out.println("c");
                seguridad();
//                System.out.println("d");
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BAgregarSaltarSiAntesActionPerformed

    private void BAgregarSaltarSiDespuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAgregarSaltarSiDespuesActionPerformed
        String nuevoNombre = JOptionPane.showInputDialog("Escriba el numero de la seccion");
        if (nuevoNombre != null && !nuevoNombre.isEmpty()) {
            try {
                int numero = Integer.parseInt(nuevoNombre.trim());
                int indice = TActual.getCdv().addSaltarDespues(numero);
                if (indice >= 0) {
                    TActual.setSaltarDespuesNumero(Arreglos.añadirEnElIndice(TActual.getSaltarDespuesNumero(), new boolean[]{}, indice));

                }

//                TActual.getCdv().setSaltarDespuesNumero(Arreglos.ampliarArreglo(TActual.getCdv().getSaltarDespuesNumero(), 1));
//                int lengh = TActual.getCdv().getSaltarDespuesNumero().length;
//                TActual.getCdv().getSaltarDespuesNumero()[lengh == 0 ? 0 : lengh - 1] = new String[]{numero + ""};
//
//                TActual.setSaltarDespuesNumero(Arreglos.ampliarArreglo(TActual.getSaltarDespuesNumero(), 1));
//                lengh = TActual.getSaltarDespuesNumero().length;
//                TActual.getSaltarDespuesNumero()[lengh == 0 ? 0 : lengh - 1] = new boolean[]{};
                inicializo = false;
                actualizarCXSaltarSiDespues(mod(indice));
                inicializo = true;
                actualizarTablaCapitulos();
                seguridad();
            } catch (Exception ex) {
                responerException(ex);
            }
        }
    }//GEN-LAST:event_BAgregarSaltarSiDespuesActionPerformed

    private void BEliminarSaltarSiAntesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarSaltarSiAntesActionPerformed
        TActual.getCdv().setSaltarAntesNumero(Arreglos.eliminarIndice(TActual.getCdv().getSaltarAntesNumero(), CXSaltarSiAntes.getSelectedIndex()));
        TActual.setSaltarAntesNumero(Arreglos.eliminarIndice(TActual.getSaltarAntesNumero(), CXSaltarSiAntes.getSelectedIndex()));
        actualizarCXSaltarSiAntes(0);
        actualizarTablaCapitulos();
        seguridad();
    }//GEN-LAST:event_BEliminarSaltarSiAntesActionPerformed

    private void BEliminarSaltarSiDespuesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEliminarSaltarSiDespuesActionPerformed
        TActual.getCdv().setSaltarDespuesNumero(Arreglos.eliminarIndice(TActual.getCdv().getSaltarDespuesNumero(), CXSaltarSiDespues.getSelectedIndex()));
        TActual.setSaltarDespuesNumero(Arreglos.eliminarIndice(TActual.getSaltarDespuesNumero(), CXSaltarSiDespues.getSelectedIndex()));
        actualizarCXSaltarSiDespues(0);
        actualizarTablaCapitulos();
        seguridad();
    }//GEN-LAST:event_BEliminarSaltarSiDespuesActionPerformed

    private void apretoCXSaltar(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_apretoCXSaltar
        if (inicializo && evt.getStateChange() == ItemEvent.SELECTED) {

            actualizarTablaCapitulos();
            seguridad();
        }
    }//GEN-LAST:event_apretoCXSaltar
//    public void alCerrarVentana(){
//    
//    }

    public void seguridad() {
        activarJComponent(RManual.isSelected(), SCapitulos, STemporadas);
        activarJComponent(RSaltarSiAntes.isSelected() && (TActual.getCdv().getSaltarAntesNumero().length > 0), CXSaltarSiAntes, BEliminarSaltarSiAntes);
        activarJComponent(RSaltarSiDespues.isSelected() && (TActual.getCdv().getSaltarDespuesNumero().length > 0), CXSaltarSiDespues, BEliminarSaltarSiDespues);
    }

    public void setTActual(TablasAmpliableConfiguracionDeVideo T) {
        this.TActual = T;
    }

    public TablasAmpliableConfiguracionDeVideo getTActual() {
        return TActual;
    }

    public TablasAmpliableConfiguracionDeVideo getTOriginal() {
        return TOriginal;
    }

    public void visualizarVentanaAdministradorConfiguraciones() throws IOException, ClassNotFoundException {
        if (ventana_Administrador == null) {
            ventana_Administrador = new Ventana_Administrador<TablasAmpliableConfiguracionDeVideo>(direccionAdministrador, new Accion_Cargar_Cancelar() {

                @Override
                public void aceptar_Y_cargar() {
                    setTActual(ventana_Administrador.getInformacionSelecionada());
                    actualizarConfiguaracion();
                    ventana_Administrador.setVisible(false);
                    setVisible(true);
                }

                @Override
                public void cancelar() {
                    ventana_Administrador.setVisible(false);
                    setVisible(true);
                }
            });

        }
        ventana_Administrador.setVisible(true);
        setVisible(false);
    }
//public Tabla getTablaActual() {
//        switch (TPSeleccionDeTabla.getSelectedIndex()) {
//            case 0:
//                if (RSaltarAntes.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().antesIngnorar, TActual.getAntesIngnorar());
//                }
//                if (RSaltarDespues.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().despuesIngnorar, TActual.getDespuesIngnorar());
//                }
//                if (RRodearIgnorar.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().rodearIgnorar, TActual.getRodearIgnorar());
//                }
//                if (RUnion.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().union, TActual.getUnion());
//                }
//            case 1:
//                if (RTemporadas.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().identificadoresTemporadas, TActual.getIdentificadoresTemporadas());
//                }
//                if (RCapitulo.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().identificadoresCapitulo, TActual.getIdentificadoresCapitulo());
//                }
//                if (RCantidadCapituloTemporada.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().identificadoresCantidadCapituloTemporada, TActual.getIdentificadoresCantidadCapituloTemporada());
//                }
//                if (RTerminacionesNumericas.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().terminacionesNumericas, TActual.getTerminacionesNumericas());
//                }
//            case 2:
//                if (RSaltarAlPricipio.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().saltarAlPrincipio, TActual.getSaltarAlPrincipio());
//                }
//                if (RDetenerAlFinal.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().detenciones, TActual.getDetenciones());
//                }
//                if (RSeparaciones.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().separacionString, TActual.getSeparacionString());
//                }
//                if (RSaltarHastaDespues.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().saltarHastaDespuesDe, TActual.getSaltarHastaDespuesDe());
//                }
//                if (RNoSaltarAlPrincipio.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().noSaltarAlPrincipio, TActual.getNoSaltarAlPrincipio());
//                }
//                if (RDetencionesAbsolutas.isSelected()) {
//                    return Tabla.crearStringSeleccionableAmpliable(TActual.getCdv().detencionesAbsolutas, TActual.getDetencionesAbsolutas());
//                }
//        }
//        return null;
//    }
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Ventana_ConfiguracionDeVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Ventana_ConfiguracionDeVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Ventana_ConfiguracionDeVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Ventana_ConfiguracionDeVideo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Ventana_ConfiguracionDeVideo().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAceptar;
    private javax.swing.JButton BAgregarSaltarSiAntes;
    private javax.swing.JButton BAgregarSaltarSiDespues;
    private javax.swing.JButton BCancelar;
    private javax.swing.JButton BCargarConfiguracion;
    private javax.swing.JButton BConfiguracionPredetermiada;
    private javax.swing.JButton BEliminarSaltarSiAntes;
    private javax.swing.JButton BEliminarSaltarSiDespues;
    private javax.swing.ButtonGroup BGFormato;
    private javax.swing.ButtonGroup BGIdentificadores;
    private javax.swing.ButtonGroup BGIgnorarNumero;
    private javax.swing.ButtonGroup BGNumerosExpecificos;
    private javax.swing.ButtonGroup BGRecortar;
    private javax.swing.JButton BGuardarConfiguracion;
    private javax.swing.JCheckBox CBIcluirTemporadas;
    private javax.swing.JCheckBox CBRenombrarCarpetasInternas;
    private javax.swing.JComboBox CXSaltarSiAntes;
    private javax.swing.JComboBox CXSaltarSiDespues;
    private javax.swing.JTable JTablaCapitulos;
    private javax.swing.JLabel L;
    private javax.swing.JLabel LCapitulos;
    private javax.swing.JPanel PCapitulos;
    private javax.swing.JPanel PFormato;
    private javax.swing.JPanel PGeneral;
    private javax.swing.JPanel PIdentificadores;
    private javax.swing.JPanel PIgnorarNumero;
    private javax.swing.JPanel PNumerosExpecificos;
    private javax.swing.JPanel PRecortar;
    private javax.swing.JPanel PTablaCapitulos;
    private javax.swing.JPanel PTodo;
    private javax.swing.JRadioButton RAutomatico;
    private javax.swing.JRadioButton RCantidadCapituloTemporada;
    private javax.swing.JRadioButton RCapitulo;
    private javax.swing.JRadioButton RDetencionesAbsolutas;
    private javax.swing.JRadioButton RDetenerAlFinal;
    private javax.swing.JRadioButton RManual;
    private javax.swing.JRadioButton RNoSaltarAlPrincipio;
    private javax.swing.JRadioButton RPalabrasInvalidas;
    private javax.swing.JRadioButton RRodearIgnorar;
    private javax.swing.JRadioButton RSaltarAlPricipio;
    private javax.swing.JRadioButton RSaltarAntes;
    private javax.swing.JRadioButton RSaltarDespues;
    private javax.swing.JRadioButton RSaltarHastaDespues;
    private javax.swing.JRadioButton RSaltarSiAntes;
    private javax.swing.JRadioButton RSaltarSiDespues;
    private javax.swing.JRadioButton RSeparaciones;
    private javax.swing.JRadioButton RSinFormato;
    private javax.swing.JRadioButton RTemporadas;
    private javax.swing.JRadioButton RUnion;
    private javax.swing.JSpinner SCapitulos;
    private javax.swing.JScrollPane SPTablaCapitulos;
    private javax.swing.JSpinner STemporadas;
    private javax.swing.JTabbedPane TPSeleccionDeTabla;
    private javax.swing.JTabbedPane TPTodo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}
