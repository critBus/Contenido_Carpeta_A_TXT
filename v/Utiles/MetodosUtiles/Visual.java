/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.MetodosUtiles;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeTabla;
import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Interfases.editingTabla;
import Utiles.ClasesUtiles.Interfases.selectionFila;
//import Utiles.ClasesUtiles.Interfases.AccionTabla;
import Utiles.ClasesUtiles.Tablas.ManejadorCellEditor;
import Utiles.ClasesUtiles.Tablas.ManejadorSelectionFila;
import Utiles.ClasesUtiles.Tablas.Tabla;
import Utiles.ClasesUtiles.Tiempo2.Tiempo;
import Utiles.Exepciones.*;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.Formatter;
import javax.swing.JComponent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import java.io.FileOutputStream;
//import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.swing.text.JTextComponent;
//import static Utiles.MetodosUtiles.Arreglos.arregloFill;
import static Utiles.MetodosUtiles.Arreglos.arregloObjectFill;
import static Utiles.MetodosUtiles.Arreglos.arregloObject;
import static Utiles.MetodosUtiles.MetodosUtiles.or;
import static Utiles.MetodosUtiles.MetodosUtiles.eliminarEspaciosDelPrincipioString;
import static Utiles.MetodosUtiles.Tempus.esBisiesto;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.MenuBar;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.util.Arrays;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
//import java.io.FileDescriptor;

import javax.swing.AbstractButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.event.CellEditorListener;
import javax.swing.event.ListSelectionEvent;
//import planificaciondeguardiaobrera.Ventana_Parejas_De_Guardia;

/**
 * Metodos que fasilitan las acciones basicas con los elementos de la GUI
 * Version 2.8
 *
 * @author Rene
 */
public abstract class Visual {
//instanceof Arreglo
//<b></b>
    //<ul></ul>
    //<li></li>

    public static final int AGARRE_EXTACTO_EN_X = -11, AGARRE_EXTACTO_EN_Y = -56, ALTO_FRAME = 400, ANCHO_FRAME = 400, X_FRAME = 20, Y_FRAME = 20;
    public static final String INT = "int", DOUBLE = "double", STRING = "String", AÑO = "año", TIEMPO60 = "tiempo60", DIA = "dia", MSEGUNDOS = "milisegundos";
//
////    public static void visualizar
//    public static void centrarHorizantalLabel(JPanel p, JLabel l) {
////        int X = (p.getWidth() / 2) - (l.getWidth() / 2);
//         int X = (p.getWidth() / 2) - 300;
////        int X =  (l.getWidth() / 2);
////        System.out.println("l.getWidth()="+l.getWidth());
////        l.setLocation(X, l.getY());
//       p.add(l, new org.netbeans.lib.awtextra.AbsoluteConstraints(X, l.getY(), -1, 20));
////         l.setLocation(0, 0);
//    }

    public static void señalarJTextTarea(JTextArea T, KeyEvent evt, ConfiguracionDeVideo cdv) {
        String A[] = Visual.leerJTextArea(T);
        int ini = 0, end = -1;

        int sel = T.getSelectionEnd();
//        System.out.println("sel="+sel);
        if (!señalarJTextTarea(T, evt, cdv, sel)) {
            señalarJTextTarea(T, evt, cdv, 0, sel);
        }
//        for (int i = 0; i < A.length; i++) {
////            System.out.println("A[i]="+A[i]);
//            if (ini >= sel) {
//                StringTokenizer St = new StringTokenizer(A[i], cdv.Delimiters);
//                if (St.hasMoreTokens() && St.nextToken().startsWith(evt.getKeyChar() + "")) {
//                    end = ini + A[i].length() + (i != 0 ? 1 : 0);
//                    break;
//                }
//            }
//            ini += A[i].length() + (i != 0 ? 1 : 0);
//        }
//        if (end != -1) {
//            T.setSelectionStart(ini);
//            T.setSelectionEnd(end);
//        } else {
//
//        }
    }

    private static boolean señalarJTextTarea(JTextArea T, KeyEvent evt, ConfiguracionDeVideo cdv, int inicio) {
        return señalarJTextTarea(T, evt, cdv, inicio, -1);
    }

    private static boolean señalarJTextTarea(JTextArea T, KeyEvent evt, ConfiguracionDeVideo cdv, int inicio, int fin) {
        String A[] = Visual.leerJTextArea(T);
        int ini = 0, end = -1;
        for (int i = 0; i < A.length; i++) {
//            System.out.println("i="+i+" A[i]"+A[i]);
            if (fin >= 0 && (ini + A[i].length() + (i != 0 ? 1 : 0)) > fin) {
                return false;
            }

            if (ini >= inicio) {
                StringTokenizer St = new StringTokenizer(A[i], cdv.Delimiters);
                if (St.hasMoreTokens() && (St.nextToken().charAt(0) + "").equalsIgnoreCase((evt.getKeyChar() + ""))) {
//                        St.nextToken().startsWith(evt.getKeyChar() + "")) {
                    end = ini + A[i].length() + (i != 0 ? 1 : 0);

                    T.setSelectionStart(ini);
                    T.setSelectionEnd(end);
//                    T.setA

                    return true;
                }
            }
            ini += A[i].length() + (i != 0 ? 1 : 0);
        }
//        System.out.println("false con "+evt.getKeyChar());
        return false;
    }

    public static void escribirSoloNumerosEnteros(java.awt.event.KeyEvent evt) {
        if (!(evt.getKeyChar() >= '0' && evt.getKeyChar() <= '9')) {
            evt.consume();
        }
    }

    public static boolean seguridadJTextField_Entero(JTextField T, JComponent... componentes) {
        return seguridadJTextField(INT, true, -1, 3000, componentes, T);
    }

    public static boolean seguridadJTextField_File_Directorio(JTextField T, JComponent... componente) {
        File f = new File(T.getText());
        boolean activar = f.exists() && f.isDirectory();
        T.setForeground(activar ? Color.BLACK : Color.RED);
        return activarJComponent(activar, componente);
    }

    public static boolean seguridadControlesTabla(boolean activar, JComponent Clear, JComponent Eliminar, JComponent Guardar, JComponent Señalar) {
        return activarJComponent(activar, Clear, Eliminar, Guardar, Señalar);
    }

    public static void seleccionarUltimoAgregadoJComboBox(JComboBox CB) {
        CB.setSelectedIndex(CB.getItemCount() - 1);
    }

    public static void actualizarJComboBox(JComboBox CB, LinkedList l) {
        CB.removeAllItems();
        //  LinkedList<SeccionPersonalizada> p = getPersonalizadosBase();
        for (int i = 0; i < l.size(); i++) {
            CB.addItem(l.get(i).toString());
        }
    }

    public static String[] leerJTextArea(JTextArea T) {
        LinkedList<String> lineas = new LinkedList<String>();
        Scanner s = new Scanner(T.getText());
        while (s.hasNextLine()) {
            lineas.add(s.nextLine());
        }
        return lineas.toArray(new String[]{});
    }

    public static void setTextJTextArea(JTextArea t, String... A) {
//        t.setText("");
//        String tt = "";
//        for (String a : A) {
//            tt += a + "\n";
//        }
//        t.setText(tt);
        t.setText("");
        for (String a : A) {
            //  System.out.println("a="+a);
            t.append(a + "\n");
        }
    }

    /**
     * si fila=-1 es en toda la columna
     *
     * @param t
     * @param Et
     * @param fila
     * @param columna
     * @param menosLaUltima
     */
    public static void addCellEditor(JTable t, editingTabla Et, int fila, int columna, boolean menosLaUltima) {
//        if(t.getToolTipText().equals("direccion base")){
//            System.out.println("");
//        }
        if (fila < 0) {
            int max = menosLaUltima ? t.getRowCount() - 1 : t.getRowCount();
//            System.out.println("columna="+columna);
            for (int i = 0; i < max; i++) {
//                System.out.println("i="+i);
                //CellEditorListener []l= t.getCellEditor(i, columna).g

                // t.getCellEditor(i, columna).
                t.getCellEditor(i, columna).addCellEditorListener(new ManejadorCellEditor(i, columna, Et, t));
            }
        } else {
            t.getCellEditor(fila, columna).addCellEditorListener(new ManejadorCellEditor(fila, columna, Et, t));
        }
        //t.getColumnModel().getColumn(0).getCellEditor().
        //t.getCellEditor(fila, columna).shouldSelectCell(null)

    }

    public static void addSelectionFila(JTable t, selectionFila Sf) {
        MouseListener m[] = t.getMouseListeners();
//        System.out.println("m.length="+m.length);
        for (int i = 0; i < m.length; i++) {
            if (m[i] instanceof ManejadorSelectionFila) {
                t.removeMouseListener(m[i]);
            }

        }
//         for (int i = m.length-1; i >0; i--) {
//            t.removeMouseListener(m[i]);
//        }
        t.addMouseListener(new ManejadorSelectionFila(Sf, t));

    }

    public static void actualizarTabla(JTable jt, Tabla t) {
        jt.setModel(t);
    }

    public static void actualizarTablaSeleccionable(ConfiguracionDeTabla cdt) {
        actualizarTabla(cdt.jt, cdt.t);
        if (cdt.Et != null) {
            for (int i = 0; i < cdt.columnasEditables.length; i++) {
                if (i < cdt.jt.getColumnCount()) {
                    addCellEditor(cdt.jt, cdt.Et, -1, cdt.columnasEditables[i], false);
                }

            }

        } else {
            // System.out.println("no "+cdt.jt.getToolTipText());
        }
        if (cdt.Sf != null) {
            addSelectionFila(cdt.jt, cdt.Sf);
        }
        //  sp.setViewportView(jt);
        if (cdt.jt.getColumnModel().getColumnCount() > 0) {
            cdt.jt.getColumnModel().getColumn(0).setResizable(false);
            cdt.jt.getColumnModel().getColumn(1).setResizable(false);
        }
        if (cdt.anchoSelect != -1) {
            cdt.jt.getColumnModel().getColumn(0).setMaxWidth(cdt.anchoSelect);
            cdt.jt.getColumnModel().getColumn(0).setMinWidth(cdt.anchoSelect);
        } else {
            cdt.anchoSelect = 40;
        }

        if (cdt.anchoInformacion == -1) {
            cdt.anchoInformacion = cdt.t.getLengthInformacion();
        }
//        cdt.jt.getColumnModel().getColumn(1).setMaxWidth(cdt.anchoInformacion);
//        cdt.jt.getColumnModel().getColumn(1).setMinWidth(cdt.anchoInformacion);

        int anchoTablaMinimo = (cdt.anchoSelect + cdt.anchoInformacion) * 5;

        cdt.pTabla.add(cdt.jt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, anchoTablaMinimo < cdt.sp.getWidth() ? cdt.sp.getWidth() - 2 : anchoTablaMinimo, -1));
        actualizarCoordenadas(cdt.p, cdt.sp);
    }

    public static void actualizarTablaString(ConfiguracionDeTabla cdt) {
        actualizarTabla(cdt.jt, cdt.t);
        int anchoTabla = 0;
        int mul = 7;
        if (cdt.t.getAnchoColumnas() != null && cdt.t.getAnchoColumnas().length == cdt.jt.getColumnCount()) {
            for (int i = 0; i < cdt.jt.getColumnCount(); i++) {
                
                int cant=cdt.t.getAnchoColumnas()[i]<7?cdt.t.getAnchoColumnas()[i] * (mul+3):(cdt.t.getAnchoColumnas()[i]>12?cdt.t.getAnchoColumnas()[i] * mul:cdt.t.getAnchoColumnas()[i] * (mul+2));
                cdt.jt.getColumnModel().getColumn(i).setMinWidth(cant);
                cdt.jt.getColumnModel().getColumn(i).setMaxWidth(cant);
                anchoTabla += cant;
//                System.out.println("cdt.t.getAnchoColumnas()[i]="+cdt.t.getAnchoColumnas()[i]);
//                System.out.println("cdt.t.getAnchoColumnas()[i] * mul="+cdt.t.getAnchoColumnas()[i] * mul);
            }
        }

        int anchoTablaMinimo = (anchoTabla);

        for (int i = 0; i < cdt.sp.getMouseWheelListeners().length; i++) {
            cdt.jt.addMouseWheelListener(cdt.sp.getMouseWheelListeners()[i]);
        }

        cdt.pTabla.add(cdt.jt, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, anchoTablaMinimo < cdt.sp.getWidth() ? cdt.sp.getWidth() - 2 : anchoTablaMinimo, -1));
        actualizarCoordenadas(cdt.p, cdt.sp);
    }

    public static void actualizarCoordenadas(JPanel p, JScrollPane sp) {
        p.add(sp, new org.netbeans.lib.awtextra.AbsoluteConstraints(sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight()));
    }

//    public static void actualizarCoordenadas(JPanel p, JScrollPane sp) {
//        p.add(sp, new org.netbeans.lib.awtextra.AbsoluteConstraints(sp.getX(), sp.getY(), sp.getWidth(), sp.getHeight()));
//    }
    public static boolean algunaEsVisible(Frame... F) {
        return algunaEsVisible(false, null, F);
    }

    public static boolean algunaEsVisible(boolean ventanasPrincipales, Frame... F) {
        return algunaEsVisible(ventanasPrincipales, new int[]{0}, F);
    }

    public static boolean algunaEsVisible(boolean ventanasPrincipales, int posVentanasPrincipales[], Frame... F) {
        if (ventanasPrincipales) {
            LinkedList<Frame> Fs = new LinkedList<>(Arrays.asList(F));
            //prueba

//            if( F[0]  != null&&F[0] instanceof Aplicaciones.modificarextencion.Ventana_Principal){
//                System.out.println("si");
//            }
            //prueba
            for (int i = 0; i < posVentanasPrincipales.length; i++) {
                // System.out.println("pos="+posVentanasPrincipales[i]);
                if (F[posVentanasPrincipales[i]] == null) {
                    return false;
                } else if (F[posVentanasPrincipales[i]].isVisible()) {
                    return true;
                }
                Fs.remove(posVentanasPrincipales[i]);
            }
//            for (int i = 0; i < F.length; i++) {
//                if(F[i]!=null&&F[i].isVisible()){
//                return true;
//                }
//            }
            for (int i = 0; i < Fs.size(); i++) {
                if (Fs.get(i) != null && Fs.get(i).isVisible()) {
                    return true;
                }
            }
        } else {
            for (Frame f : F) {
                if (f != null && f.isVisible()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void minimizarVentana(Frame f) {
        f.setExtendedState(Frame.ICONIFIED);
    }

    public static void maximizarVentana(Frame f) {
        f.setExtendedState(Frame.NORMAL);
    }

    public static void setLocationY(Component c, int y) {
        c.setLocation(c.getLocation().x, y);
    }

    public static boolean sonTodosCero(JTextField... textoNumerico) {
        return sonTodosCeroAA(textoNumerico);
    }

    public static boolean sonTodosCeroAA(JTextField[]... textoNumerico) {
        try {
            for (JTextField[] te : textoNumerico) {
                for (JTextField t : te) {
                    if (doublE(t) != 0) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static Tiempo extraerTiempo(JTextField... tiempos) {
        return extraerTiempo(tiempos[0], tiempos[1], tiempos[2], tiempos[3]);
    }

    public static Tiempo extraerTiempo(JTextField hora, JTextField minuto, JTextField segundo, JTextField mSegundo) {
        return new Tiempo(inT(hora), inT(minuto), inT(segundo), inT(mSegundo));
    }

    public static double doublE(JTextField a) {
        return Double.parseDouble(a.getText());
    }

    public static int inT(JTextField a) {
        return Integer.parseInt(a.getText());
    }

    public static void responerException(Exception ex) {
        if (ex instanceof NoEncontradoException || ex instanceof PINException
                || ex instanceof ExisteException || ex instanceof NoPermitidoException
                || ex instanceof IndiceFinalIncorrectoException || ex instanceof IndiceIncorrectoException
                || ex instanceof IndiceInicialIncorrectoException) {
            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
//        if (ex instanceof PINException) {
//            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
//        if (ex instanceof ExisteException) {
//            JOptionPane.showMessageDialog(null, ex.getLocalizedMessage(), "Error", JOptionPane.ERROR_MESSAGE);
//            return;
//        }
        if (ex instanceof IOException) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el archibo", "Error", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
            return;
        }
        if (ex instanceof FileNotFoundException) {
            JOptionPane.showMessageDialog(null, "No se encuentra el archibo", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
         if (ex instanceof NumberFormatException) {
            JOptionPane.showMessageDialog(null, "No es un numero valido", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        ex.printStackTrace();
    }

    public static void eliminarEspaciosDelPrincipioJTextField(JTextField... t) {
        for (int i = 0; i < t.length; i++) {
            t[i].setText(eliminarEspaciosDelPrincipioString(t[i].getText()));
        }

    }

    public static void estiloNimbus() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static boolean buscador(JFileChooser jf, Frame f) {
        JFileChooser jf2 = new JFileChooser(jf.getSelectedFile());
        jf2.setFileSelectionMode(jf.getFileSelectionMode());
        jf2.setFileFilter(jf.getFileFilter());
        if (jf2.showDialog(f, "aceptar") == JFileChooser.APPROVE_OPTION) {
            jf.setSelectedFile(jf2.getSelectedFile());
            return true;
        }
        return false;
        //  return jf.showDialog(f, "aceptar") == JFileChooser.APPROVE_OPTION;
    }

    public static JFrame frame() {
        return frame("");
    }

    public static JFrame frame(int ancho, int alto) {
        return frame(ancho, alto, "");
    }

    public static JFrame frame(String titulo) {
        return frame(ANCHO_FRAME, ALTO_FRAME, titulo);
    }

    public static JFrame frame(int ancho, int alto, String titulo) {
        return frame(ancho, alto, titulo, true);
    }

    public static JFrame frame(String titulo, boolean center) {
        return frame(ANCHO_FRAME, ALTO_FRAME, titulo, center);
    }

    public static JFrame frame(int ancho, int alto, boolean center) {
        return frame(ancho, alto, "", center);
    }

    public static JFrame frame(int ancho, int alto, String titulo, boolean center) {
        return frame(ancho, alto, titulo, true, center);
    }

    public static JFrame frame(int ancho, int alto, String titulo, boolean visible, boolean center) {
        return frame(ancho, alto, titulo, visible, X_FRAME, Y_FRAME, center);
    }

    public static JFrame frame(String titulo, int x, int y) {
        return frame(ANCHO_FRAME, ALTO_FRAME, titulo, x, y);
    }

    public static JFrame frame(int ancho, int alto, int x, int y) {
        return frame(ancho, alto, "", x, y);
    }

    public static JFrame frame(int ancho, int alto, String titulo, int x, int y) {
        return frame(ancho, alto, titulo, true, x, y);
    }

    public static JFrame frame(int ancho, int alto, String titulo, boolean visible, int x, int y) {
        return frame(ancho, alto, titulo, visible, x, y, false);
    }

    private static JFrame frame(int ancho, int alto, String titulo, boolean visible, int x, int y, boolean center) {
        JFrame f = new JFrame(titulo);
        f.setSize(ancho, alto);
        if (center) {
            f.setLocationRelativeTo(null);
        } else {
            f.setLocation(x, y);
        }

        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(visible);
        return f;
    }

    public static boolean isEmpty(JComboBox... jc) {
        for (JComboBox j : jc) {
            if (j.getItemCount() == 0) {
                return true;
            }
        }
        return false;
    }

    public static boolean fueSelected(ItemEvent evt) {
        return evt.getStateChange() == ItemEvent.SELECTED;
    }

    public static void imprimirSise(Component c) {
        System.out.println("c.getWidth()=" + c.getWidth() + " c.getHeight()=" + c.getHeight());
    }

    public static void aumentarAlto(Component c, int alto) {
        aumentarTamaño(c, 0, alto);
    }

    public static void aumentarAncho(Component c, int ancho) {
        aumentarTamaño(c, ancho, 0);
    }

    public static void aumentarTamaño(Component c, int ancho, int alto) {
        c.setSize(c.getWidth() + ancho, c.getHeight() + alto);
    }

    public static Point pointEnMovimiento(Point coordenadaActual, Point interiror) {
        return new Point(coordenadaActual.x - interiror.x, coordenadaActual.y - interiror.y);
    }

    public static void pintarImagenEnMovimiento(Graphics g, Frame f, Image i, Rectangle r, Point coordenadaActual, Point interirorDeImagen) {
        Point enMovimiento = pointEnMovimiento(coordenadaActual, interirorDeImagen);
        g.drawImage(i, enMovimiento.x, enMovimiento.y, (int) r.getWidth(), (int) r.getHeight(), f);

    }

    public static void pintarImagenEnMovimientoRectangle(Graphics g, Frame f, Image i, Rectangle r, Point coordenadaActual, Point interirorDeImagen) {
        pintarImagenEnMovimiento(g, f, i, r, coordenadaActual, interirorDeImagen);
        pintarRectangleEnMovimiento(g, r, coordenadaActual, interirorDeImagen);
    }

    public static void pintarRectangleEnMovimiento(Graphics g, Rectangle r, Point coordenadaActual, Point interiror) {
        Point enMovimiento = pointEnMovimiento(coordenadaActual, interiror);
        g.drawRect(enMovimiento.x, enMovimiento.y, (int) r.getWidth(), (int) r.getHeight());
    }

    public static Point pointInterior(MouseEvent evt, Rectangle r) {
        return new Point((int) (evt.getX() - r.getX()), (int) (evt.getY() - r.getY()));
    }

    public static void pintarImagen(Graphics g, Frame f, Image I, Rectangle r) {
        g.drawImage(I, (int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight(), f);

    }

    //JOptionPane.showConfirmDialog(this, "Desea sobrescribir la partida", "Advertencia", JOptionPane.YES_NO_OPTION) != JOptionPane.NO_OPTION
    public static boolean dialogoSiNo(String mensaje, String titulo) {
        return JOptionPane.showConfirmDialog(null, mensaje, titulo, JOptionPane.YES_NO_OPTION) != JOptionPane.NO_OPTION;
    }

    public static void pintarRectangle(Graphics2D g, Rectangle r) {
        g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
    }

    public static void fillRectangle(Graphics g, Rectangle r) {
        fillRectangle(g, r, Color.WHITE);
    }

    public static void fillRectangle(Graphics g, Rectangle r, Color c) {
        Color fondo = g.getColor();
        g.setColor(c);
        g.fillRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
        g.setColor(fondo);
        pintarRectangle(g, r);
    }

    public static void pintarRectangle(Graphics g, Rectangle r) {
        g.drawRect((int) r.getX(), (int) r.getY(), (int) r.getWidth(), (int) r.getHeight());
    }

    public static Point coordenadaReal(MouseEvent evt, Frame f, Component... C) {
        boolean contieneBarraDeDesplasamiento = false;
        for (int i = 0; i < f.getComponentCount(); i++) {
            if (f.getComponent(i) instanceof JMenuBar) {
                contieneBarraDeDesplasamiento = true;
                break;
            }

        }//System.out.println("contieneBarraDeDesplasamiento="+contieneBarraDeDesplasamiento);
        int aumentoEnX = 0;
        int aumentoEnY = 0;
        for (int i = 0; i < C.length; i++) {
            aumentoEnX += C[i].getX();//System.out.println("C[i].getX()="+C[i].getX());
            aumentoEnY += C[i].getY();//System.out.println("C[i].getY()="+C[i].getY());
        }
        return new Point(evt.getXOnScreen() - f.getX() + aumentoEnX + (contieneBarraDeDesplasamiento ? AGARRE_EXTACTO_EN_X : 0), evt.getYOnScreen() - f.getY() + aumentoEnY + (contieneBarraDeDesplasamiento ? AGARRE_EXTACTO_EN_Y : 0));
    }

    public static void movimiento(Component c, MouseEvent evt, Frame f) {
        movimiento(c, evt, f, 0, 0);
    }

    public static void movimiento(Component c, MouseEvent evt, Frame f, int distanciaDeAgarreX, int distanciaDeAgarreY) {
        c.setLocation(evt.getXOnScreen() - f.getX() - distanciaDeAgarreX + AGARRE_EXTACTO_EN_X, evt.getYOnScreen() - f.getY() - distanciaDeAgarreY + AGARRE_EXTACTO_EN_Y);
    }

    /**
     * <ul>no es mio pero funciona</ul>
     * <ul>Método que al hacer click sobre la “x” pide al usuario que confirme
     * si quiere salir del programa</ul>
     *
     * @param evt
     */
    public static void windowClosing(WindowEvent evt) {
        JFrame frame = (JFrame) evt.getSource();
        int confirm = JOptionPane.showOptionDialog(frame, "¿Seguro que quieres salir?", "Cuadro confirmación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, null);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void Punto(JTextComponent T) {
        if (T.getText().isEmpty() || or(T.getText(), ".", ",")) {
            T.setText("0.");
        }
    }

    public static void Backspace(JTextComponent T) {
        T.setText(T.getText().substring(0, T.getText().length() - 1));
    }

    public static void escribirDouble(KeyEvent evt, JTextComponent T) {
        if (evt.getKeyChar() == ',') {
            append(T, ".");
        } else {
            append(T, evt.getKeyChar() + "");
        }
    }

    public static void escribir(KeyEvent evt, JTextComponent T) {
        if (!evt.isActionKey()) {
            append(T, evt.getKeyChar() + "");
        }

    }

    public static void append(JTextComponent T, String a) {
        T.setText(T.getText() + a);
    }

    public static void selecionar(boolean selecionar, AbstractButton... BotonSeleccionable) {
        for (int i = 0; i < BotonSeleccionable.length; i++) {
            BotonSeleccionable[i].setSelected(selecionar);
        }
    }

    public static boolean algunoEstaSelecionadoExepto(AbstractButton BotonSeleccionable, AbstractButton... BotonSeleccionables) {
        for (int i = 0; i < BotonSeleccionables.length; i++) {
            if (!BotonSeleccionables[i].equals(BotonSeleccionable) && BotonSeleccionables[i].isSelected()) {
                return true;
            }
        }
        return false;
    }

    public static boolean algunoEstaSelecionado(AbstractButton... BotonSeleccionable) {
        for (int i = 0; i < BotonSeleccionable.length; i++) {
            if (BotonSeleccionable[i].isSelected()) {
                return true;
            }
        }
        return false;
    }

    public static void activarSiEstaSeleccionado(AbstractButton BotonSeleccionable, Component... ComponentesDesactibables) {
        activarSiEstaSeleccionado(arregloObject(BotonSeleccionable), ComponentesDesactibables);

    }

    public static void activarSiEstaSeleccionado(AbstractButton[] BotonesSeleccionables, Component... ComponentesDesactibables) {
        for (int i = 0; i < BotonesSeleccionables.length; i++) {
            if (BotonesSeleccionables[i].isSelected()) {
                if (!ComponentesDesactibables[i].isEnabled()) {
                    ComponentesDesactibables[i].setEnabled(true);
                }
            } else {
                if (ComponentesDesactibables[i].isEnabled()) {
                    ComponentesDesactibables[i].setEnabled(false);
                }
            }
        }
    }

    public static void eliminarTextoPredeterminado(KeyEvent evt, String textoPredeterminado, JTextComponent... A) {
        eliminarTextoPredeterminado(evt, arregloObjectFill(textoPredeterminado, A.length), A);
    }

    public static void eliminarTextoPredeterminado(KeyEvent evt, String textoPredeterminado[], JTextComponent... A) {
        for (int i = 0; i < A.length; i++) {
            if (evt.getSource() == A[i]) {
                eliminarTextoPredeterminado(textoPredeterminado[i], A[i]);
//                if (A[i].getText().equals(textoPredeterminado[i])) {
//                    A[i].setText("");
//                }
                break;
            }

        }
    }

    public static void eliminarTextoPredeterminado(String textoPredeterminado, JTextComponent A) {
        if (A.getText().equals(textoPredeterminado)) {
            A.setText("");
        }
    }

    public static void establecerTextoPredeterminado(KeyEvent evt, String textoPredeterminado, JTextComponent... A) {
        establecerTextoPredeterminado(false, evt, arregloObjectFill(textoPredeterminado, A.length), A);
    }

    public static void establecerTextoPredeterminado(boolean siempre, KeyEvent evt, String textoPredeterminado, JTextComponent... A) {
        establecerTextoPredeterminado(siempre, evt, arregloObjectFill(textoPredeterminado, A.length), A);
    }

    public static void establecerTextoPredeterminado(KeyEvent evt, String textoPredeterminado[], JTextComponent... A) {
        establecerTextoPredeterminado(false, evt, textoPredeterminado, A);
    }

    public static void establecerTextoPredeterminado(boolean siempre, KeyEvent evt, String textoPredeterminado[], JTextComponent... A) {
        for (int i = 0; i < A.length; i++) {
            if (evt.getSource() == A[i]) {
                establecerTextoPredeterminado(siempre, textoPredeterminado[i], A[i]);
                break;
            }

        }
    }

    public static void establecerTextoPredeterminado(String textoPredeterminado, JTextComponent A) {
        establecerTextoPredeterminado(false, textoPredeterminado, A);
    }

    public static void establecerTextoPredeterminado(boolean siempre, String textoPredeterminado, JTextComponent A) {
        if (A.getText().isEmpty() || siempre) {
            A.setText(textoPredeterminado);
        }
    }

    public static boolean seguridadJList(JList L, JComponent... componentes) {
        return activarJComponent(!(L.getComponentCount() == 0 || L.getSelectedIndex() == -1), componentes);
    }

    public static boolean seguridadJTextField(String tipo, EventObject evt, JTextField T, JComponent... componentes) {
        return seguridadJTextField(tipo, evt, componentes, new JTextField[]{T});
    }

    //******
    public static boolean seguridadJTextField(String tipo, boolean SoloPositivo, EventObject evt, JComponent componente, JTextField... T) {
        return seguridadJTextField(tipo, SoloPositivo, evt, new JComponent[]{componente}, T);
    }

    public static boolean seguridadJTextField(String tipo, boolean SoloPositivo, EventObject evt, JComponent componentes[], JTextField... T) {
        return seguridadJTextField(Color.BLACK, Color.RED, tipo, SoloPositivo, evt, componentes, T);
    }

    public static boolean seguridadJTextField(Color colorInicial, Color colorError, String tipo, boolean SoloPositivo, EventObject evt, JComponent componentes[], JTextField... T) {
        return seguridadJTextField(colorInicial, colorError, tipo, SoloPositivo, evt, false, componentes, T);
    }
//****

    public static boolean seguridadJTextField(String tipo, EventObject evt, JComponent componente, JTextField... T) {
        return seguridadJTextField(tipo, evt, new JComponent[]{componente}, T);
    }

    public static boolean seguridadJTextField(String tipo, EventObject evt, JComponent componentes[], JTextField... T) {
        return seguridadJTextField(Color.BLACK, Color.RED, tipo, evt, componentes, T);
    }

    public static boolean seguridadJTextField(Color colorInicial, Color colorError, String tipo, EventObject evt, JComponent componentes[], JTextField... T) {
        return seguridadJTextField(colorInicial, colorError, tipo, evt, true, componentes, T);
    }

    public static boolean seguridadJTextField(Color colorInicial, Color colorError, String tipo, EventObject evt, boolean colorATodos, JComponent componentes[], JTextField... T) {
        return seguridadJTextField(colorInicial, colorError, tipo, false, evt, colorATodos, componentes, T);

    }

    private static boolean seguridadJTextField(Color colorInicial, Color colorError, String tipo, boolean SoloPositivo, EventObject evt, boolean colorATodos, JComponent componentes[], JTextField... T) {
        class auxiliar {

            void comprobarPositivo(double a, boolean soloPositivo) throws Exception {
                if (soloPositivo && a < 0) {
                    throw new Exception("No es positivo");
                }
            }
        }

        try {
            switch (tipo) {
                case DOUBLE:
                    for (int i = 0; i < T.length; i++) {
                        if (T[i].getText().isEmpty()) {
                            T[i].setText("0");
                            continue;
                        }
                        if (T[i].getText().contains(",") && T[i].getText().indexOf(",") != T.length - 1) {
                            T[i].setText(T[i].getText().substring(0, T[i].getText().indexOf(",")) + "." + T[i].getText().substring(T[i].getText().indexOf(",") + 1));
                        }
                        new auxiliar().comprobarPositivo(Double.parseDouble(T[i].getText()), SoloPositivo);
                    }
                    break;
                case INT:
                    for (int i = 0; i < T.length; i++) {
                        if (T[i].getText().isEmpty()) {
                            T[i].setText("0");
                            continue;
                        }
                        new auxiliar().comprobarPositivo(Integer.parseInt(T[i].getText()), SoloPositivo);
                    }
                    break;
                case STRING:
                    for (int i = 0; i < T.length; i++) {
                        if (T[i].getText().isEmpty()) {
                            throw new Exception();
                        }
                    }
                    break;
            }
            for (int i = 0; i < T.length; i++) {
                if (evt.getSource() == T[i] || colorATodos) {
                    //  if (T[i].getForeground() != colorInicial) {
                    T[i].setForeground(colorInicial);
                    //     }
                    if (!colorATodos) {
                        break;
                    }
                }
            }

            for (int i = 0; i < componentes.length; i++) {
                if (!componentes[i].isEnabled()) {
                    componentes[i].setEnabled(true);
                }
            }
        } catch (Exception ex) {
            for (int i = 0; i < T.length; i++) {
                if (evt.getSource() == T[i] || colorATodos) {
                    if (T[i].getForeground() != colorError) {
                        T[i].setForeground(colorError);
                    }
                    if (!colorATodos) {
                        break;
                    }
                }
            }

            for (int i = 0; i < componentes.length; i++) {
                if (componentes[i].isEnabled()) {
                    componentes[i].setEnabled(false);
                }
            }
            return false;
        } //JComponent
        return true;
    }

    public static boolean seguridadJTextField(EventObject evt, int cantidadMaximaDeCaracteres, JTextField T, JComponent... componentes) {
        return seguridadJTextField(evt, cantidadMaximaDeCaracteres, componentes, T);
    }

    public static boolean seguridadJTextField(EventObject evt, int cantidadMaximaDeCaracteres, JComponent componentes[], JTextField... T) {
        return seguridadJTextField(Color.BLACK, Color.RED, evt, cantidadMaximaDeCaracteres, componentes, T);
    }

    public static boolean seguridadJTextField(Color colorInicial, Color colorError, EventObject evt, int cantidadMaximaDeCaracteres, JComponent componentes[], JTextField... T) {
        boolean errores[] = new boolean[T.length];
        try {
            boolean error = false;
            for (int i = 0; i < T.length; i++) {
                if (T[i].getText().isEmpty() || T[i].getText().length() > cantidadMaximaDeCaracteres) {
                    error = true;
                    errores[i] = true;
                    continue;
                }
                errores[i] = false;
                T[i].setForeground(colorInicial);
                //  System.out.println("ssdsd");
            }

            if (error) {
                throw new Exception();
            }

            for (int i = 0; i < componentes.length; i++) {
                if (!componentes[i].isEnabled()) {
                    componentes[i].setEnabled(true);
                }
            }
        } catch (Exception ex) {
            for (int i = 0; i < T.length; i++) {
                //if (evt.getSource() == T[i]) {
                if (errores[i]) {
                    if (T[i].getForeground() != colorError) {
                        T[i].setForeground(colorError);
                    }
                    //if (!colorATodos) {
                }
                //   }
                //  }
            }

            for (int i = 0; i < componentes.length; i++) {
                if (componentes[i].isEnabled()) {
                    componentes[i].setEnabled(false);
                }
            }
            return false;
        } //JComponent
        return true;
    }

    public static boolean seguridadJTextField(String tipo, boolean soloPositivos, double min, double max, JComponent componente, JTextField... T) {
        return seguridadJTextField(tipo, soloPositivos, min, max, new JComponent[]{componente}, T);
    }

    public static boolean seguridadJTextField(String tipo, JComponent componentes[], JTextField... T) {
        return seguridadJTextField(tipo, false, -1, -1, componentes, T);
    }

    public static boolean seguridadJTextField(String tipos[], JComponent componente, JTextField... T) {
        return seguridadJTextField(tipos, new JComponent[]{componente}, T);
    }

    public static boolean seguridadJTextField(String tipos[], JComponent componentes[], JTextField... T) {
        return seguridadJTextField(Color.BLACK, Color.RED, tipos, false, true, true, -1, -1, componentes, T);
    }

    public static boolean seguridadJTextField(String tipo, boolean soloPositivos, double min, double max, JComponent componentes[], JTextField... T) {
        String tipos[] = new String[T.length];
        Arrays.fill(tipos, tipo);
        return seguridadJTextField(Color.BLACK, Color.RED, tipos, soloPositivos, true, true, min, max, componentes, T);

    }

    private static boolean seguridadJTextField(Color colorInicial, Color colorError, String tipos[], boolean soloPositivos, boolean limiteInferior, boolean limiteSuperior, double min, double max, JComponent componentes[], JTextField... T) {
//        System.out.println("sssss");
//        for (int i = 0; i < T.length; i++) {
//            System.out.print(T[i].getText()+" ");
//        }
//        System.out.println(Arrays.toString(tipos));
        class auxiliar {

            void comprobarNumero(double numero, boolean soloPositivos, boolean limiteInferior, boolean limiteSuperior, double min, double max) throws Exception {
//                System.out.println("min="+min);
//                System.out.println("numero="+numero);
//                System.out.println("");
                if (!(numero < max) || (soloPositivos && numero < 0) || (limiteInferior && !(numero > min)) || (limiteSuperior && !(numero < max))) {
                    throw new Exception();
                }
            }
        }
        boolean errores[] = new boolean[T.length];
        try {
            boolean error = false;
            for (int i = 0; i < T.length; i++) {
                if (T[i].getText().isEmpty()) {
                    error = true;
                    errores[i] = true;
                    continue;
                }
                try {
                    // double numero;
                    switch (tipos[i]) {
                        case INT:
                            new auxiliar().comprobarNumero(Integer.parseInt(T[i].getText()), soloPositivos, limiteInferior, limiteSuperior, min, max);
                            break;
                        case DOUBLE:
                            new auxiliar().comprobarNumero(Double.parseDouble(T[i].getText()), soloPositivos, limiteInferior, limiteSuperior, min, max);
                            break;
                        case TIEMPO60:
                            new auxiliar().comprobarNumero(Integer.parseInt(T[i].getText()), true, true, true, -1, 60);
                            break;
                        case DIA:
                            new auxiliar().comprobarNumero(Integer.parseInt(T[i].getText()), true, true, true, -1, 31);
                            break;
                        case AÑO:
                            int numero = Integer.parseInt(T[i].getText());
                            new auxiliar().comprobarNumero(numero, true, true, true, -1, esBisiesto(numero) ? 366 : 365);
                            break;
                        case MSEGUNDOS:
                            // System.out.println("Integer.parseInt(T[i].getText())="+Integer.parseInt(T[i].getText()));
                            new auxiliar().comprobarNumero(Integer.parseInt(T[i].getText()), true, true, true, -1, 1000);
                            break;

                    }
                } catch (Exception e) {
                    //  System.out.println("feu false "+tipos[i]);
                    error = true;
                    errores[i] = true;
                    continue;
                }
                errores[i] = false;
                T[i].setForeground(colorInicial);
                //  System.out.println("ssdsd");
            }

            if (error) {
                throw new Exception();
            }

            for (int i = 0; i < componentes.length; i++) {
                if (!componentes[i].isEnabled()) {
                    componentes[i].setEnabled(true);
                }
            }
        } catch (Exception ex) {
            for (int i = 0; i < T.length; i++) {
                //if (evt.getSource() == T[i]) {
                if (errores[i]) {
                    if (T[i].getForeground() != colorError) {
                        T[i].setForeground(colorError);
                    }
                    //if (!colorATodos) {
                }
                //   }
                //  }
            }

            for (int i = 0; i < componentes.length; i++) {
                if (componentes[i].isEnabled()) {
                    componentes[i].setEnabled(false);
                }
            }
            return false;
        } //JComponent
        return true;
    }

    /**
     * Según un booleano en caso de ser false se desactivan los componentes y se
     * cambia el color de la letra del JTextField con un estado false al color
     * rojo,si es true se activan los componentes y sus letras pasan al color
     * negro.Devuelve false si estado fue false
     *
     * @param estado boolean: Estado del JTextField
     * @param T Arreglo JComponentes: a activar o desactivar
     * @param componente JTextField
     * @return boolean
     */
    public static boolean seguridadJTextField(boolean estado, JTextField T, JComponent... componente) {
        return seguridadJTextField(new boolean[]{estado}, componente, T);
    }

    public static boolean seguridadJTextField(JTextField T, JComponent... componente) {
        return seguridadJTextField(!T.getText().isEmpty(), T, componente);
    }

//    public static boolean seguridadJTextField(JTextField T,JList L, JComponent... componente) {
//        return seguridadJTextField(!T.getText().isEmpty()&&L.getComponentCount()!=0&&L.getSelectedIndex()!=-1, T, componente);
//    }
    public static boolean seguridadJTextField(JTextField T, String direccion, JComponent... componente) {
        return seguridadJTextField(T, new File(direccion), componente);
    }

    public static boolean seguridadJTextField(JTextField T, File f, JComponent... componente) {
        return seguridadJTextField(!T.getText().isEmpty() && f.exists(), T, componente);
    }

    /**
     * Según un booleano en caso de ser false se desactiva el componente y se
     * cambia el color de la letra del JTextField con un estado false al color
     * rojo,si es true se activa los componentes y sus letras pasan al color
     * negro.Devuelve false si estado fue false
     *
     * @param estado boolean: Estado del JTextField
     * @param componente JComponente: a activar o desactivar
     * @param T JTextField
     * @return boolean
     */
    public static boolean seguridadJTextField(boolean estado, JComponent componente, JTextField T) {
        return seguridadJTextField(new boolean[]{estado}, componente, T);
    }

    /**
     * Según un arreglo booleano en caso de un elemento ser false se desactiva
     * el componente y se cambia el color de la letra del JTextField con un
     * estado false al color rojo, si todos son true se activa los componentes y
     * sus letras pasan al color negro.Devuelve false si algún estado fue false
     *
     * @param estado Arreglo boolean: que tiene que tener la misma capacidad que
     * el de JTextField y contiene los estados ordenados de cada uno de ellos
     * @param componente JComponente: a activar o desactivar
     * @param T Arreglo JTextField: que tiene que tener la misma capacidad que
     * el de los estados
     * @return boolean
     */
    public static boolean seguridadJTextField(boolean[] estado, JComponent componente, JTextField... T) {
        return seguridadJTextField(estado, new JComponent[]{componente}, T);
    }

    /**
     * Según un arreglo booleano en caso de un elemento ser false se desactivan
     * los componentes y se cambia el color de la letra del JTextField con un
     * estado false al color rojo, si todos son true se activan los componentes
     * y sus letras pasan al color negro.Devuelve false si algún estado fue
     * false
     *
     * @param estado Arreglo boolean: que tiene que tener la misma capacidad que
     * el de JTextField y contiene los estados ordenados de cada uno de ellos
     * @param componentes Arreglo JComponentes: a activar o desactivar
     * @param T Arreglo JTextField: que tiene que tener la misma capacidad que
     * el de los estados
     * @return boolean
     */
    public static boolean seguridadJTextField(boolean[] estado, JComponent componentes[], JTextField... T) {
        return seguridadJTextField(Color.BLACK, Color.RED, estado, componentes, T);
    }

    public static boolean seguridadJTextField(boolean estado, JComponent componentes[], JTextField... T) {
        boolean[] estado2 = new boolean[T.length];
        Arrays.fill(estado2, estado);
        return seguridadJTextField(estado2, componentes, T);
    }

    public static boolean seguridadJTextField(boolean estado, JComponent componente, JTextField... T) {
        return seguridadJTextField(estado, new JComponent[]{componente}, T);
    }

    /**
     * Según un arreglo booleano en caso de un elemento ser false se desactivan
     * los componentes y se cambia el color de la letra del JTextField con un
     * estado false al color de error, si todos son true se activan los
     * componentes y sus letras pasan al color inicial.Devuelve false si algún
     * estado fue false
     *
     * @param colorInicial Color: de estado true
     * @param colorError Color: de estado false
     * @param estado Arreglo boolean: que tiene que tener la misma capacidad que
     * el de JTextField y contiene los estados ordenados de cada uno de ellos
     * @param componentes Arreglo JComponentes: a activar o desactivar
     * @param T Arreglo JTextField: que tiene que tener la misma capacidad que
     * el de los estados
     * @return boolean
     *
     */
    public static boolean seguridadJTextField(Color colorInicial, Color colorError, boolean[] estado, JComponent componentes[], JTextField... T) {
        boolean seguro = true;
        for (int i = 0; i < estado.length; i++) {//System.out.println("i="+i+" de="+ estado.length);
            if (T[i].getForeground() != (estado[i] ? colorInicial : colorError)) {
                T[i].setForeground(estado[i] ? colorInicial : colorError);
            }
            //  System.out.println("ee="+estado[i]);
            seguro = seguro ? estado[i] : seguro;
        }
        // System.out.println("seguro="+seguro);
        activarJComponent(seguro, componentes);
        return seguro;
    }

    /**
     * activa en caso de ser nesesario todos los JComponent
     *
     * @param componentes Arreglo JComponentes: a activar
     */
    public static void activarJComponent(JComponent... componentes) {
        activarJComponent(true, componentes);
    }

    /**
     * Si activar es true activa en caso de ser nesesario todos los
     * JComponent,si es false lo contrario
     *
     * @param activar boolean
     * @param componentes Arreglo JComponentes: a activar o desactivar
     */
    public static boolean activarJComponent(boolean activar, JComponent... componentes) {
        for (int i = 0; i < componentes.length; i++) {
            //  System.out.println("componentes[i].isEnabled()="+componentes[i].isEnabled());
            // System.out.println("activar="+activar);
            //System.out.println("componentes[i].isEnabled() == activar ="+(componentes[i].isEnabled() == activar));
            if (componentes[i].isEnabled() != activar) {
                componentes[i].setEnabled(activar);
            }
        }
        return activar;
    }

    public static void leerFileJTextArea(JTextArea T) {
        JFileChooser jf = new JFileChooser();
        if (jf.showOpenDialog(null) != JFileChooser.CANCEL_OPTION) {
            try {
                leerFileJTextArea(T, jf);

            } catch (IOException ex) {
                Logger.getLogger(Visual.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public static void leerFileJTextArea(JTextArea T, JFileChooser jf) throws FileNotFoundException, IOException {
        leerFileJTextArea(T, jf.getSelectedFile());
    }

    public static void leerFileJTextArea(JTextArea T, String direccion) throws FileNotFoundException, IOException {
        leerFileJTextArea(T, new File(direccion));
    }

    public static void leerFileJTextArea(JTextArea T, File direccion) throws FileNotFoundException, IOException {
        FileReader Fr = new FileReader(direccion);
        T.read(Fr, direccion);
    }//,String nombrePorDefecto

    public static void guardarEnFileJTextArea(JTextArea T, String direccion) throws FileNotFoundException, IOException {
        guardarEnFileJTextArea(T, new File(direccion));
    }

    public static void guardarEnFileJTextArea(JTextArea T, File direccion) throws FileNotFoundException, IOException {
        PrintWriter p = new PrintWriter(direccion);
        Scanner s = new Scanner(T.getText());
        int numeroDeLinea = 0;
        while (numeroDeLinea < T.getLineCount()) {
            numeroDeLinea++;
            try {
                p.println(s.nextLine());
            } catch (NoSuchElementException e) {
                //nada
            }
        }
        p.close();
    }

    public static void guardarEnFileJTextArea(JTextArea T, File direccionDeSalida, String extencion, Frame frame) throws FileNotFoundException, IOException {
        JFileChooser jfGuardar = new JFileChooser(direccionDeSalida);
        if (buscador(jfGuardar, frame)) {
            guardarEnFileJTextArea(T, jfGuardar.getSelectedFile(), extencion);
        }
    }

    public static void guardarEnTXTJTextArea(JTextArea T, File direccion) throws FileNotFoundException, IOException {
        Archivo.crearTXT(direccion.getParent(), direccion.getName(), leerJTextArea(T));
    }

    public static void guardarEnTXTJTextArea(JTextArea T, File direccion, String nombre) throws FileNotFoundException, IOException {
        if (Archivo.esTXT(direccion)) {
            direccion.delete();
            Archivo.crearTXT(direccion.getParent(), nombre, leerJTextArea(T));
        } else {
            Archivo.crearTXT(direccion, nombre, leerJTextArea(T));
        }

    }

    public static void guardarEnFileJTextArea(JTextArea T, File direccion, String extencion) throws FileNotFoundException, IOException {

        PrintWriter p = new PrintWriter(Archivo.adaptarExtencion(direccion, extencion));
        Scanner s = new Scanner(T.getText());
        int numeroDeLinea = 0;
        while (numeroDeLinea < T.getLineCount()) {
            numeroDeLinea++;
            try {
                p.println(s.nextLine());
            } catch (NoSuchElementException e) {
                //nada
            }
        }
        p.close();
        s.close();
    }

    public static String[] JTextAreaAarregloStringLine(JTextArea T) {
        String A[] = new String[T.getLineCount()];
        Scanner s = new Scanner(T.getText());
        for (int i = 0; i < A.length; i++) {
            A[i] = s.nextLine();
        }
        return A;
    }

    public static String[] JTextAreaAarregloString(JTextArea T) {
        StringTokenizer S = new StringTokenizer(T.getText());
        String A[] = new String[S.countTokens()];
        for (int i = 0; i < A.length; i++) {
            A[i] = S.nextToken();
        }
        return A;
    }

    public static ArrayList<String> JTextAreaAArrayListString(JTextArea T) {
        ArrayList<String> A = new ArrayList<String>();
        StringTokenizer S = new StringTokenizer(T.getText());
        while (S.hasMoreTokens()) {
            A.add(S.nextToken());
        }
        return A;
    }

    public static ArrayList<String> JTextAreaAArrayListStringLine(JTextArea T) {
        ArrayList<String> A = new ArrayList<String>();
        Scanner s = new Scanner(T.getText());
        System.out.println(" t=" + T.getLineCount());
        for (int i = 0; i < T.getLineCount(); i++) {
            try {
                A.add(s.nextLine());
            } catch (NoSuchElementException e) {
                //nada
            }
        }
        return A;
    }

    //----------------
    public static void anadirArregloStringAJTextAreaEnLineas(JTextArea T, String[] A) {
        for (String a : A) {
            T.append(a + "\n");
        }
    }

    public static void añadirArregloStringAJTextAreaEnPalabras(JTextArea T, String[] A) {
        for (String a : A) {
            T.append(a + " ");
        }
    }

    public static void añadirArrayListStringAJTextAreaEnLineas(JTextArea T, ArrayList<String> A) {
        for (String a : A) {
            T.append(a + "\n");
        }

    }

    public static void añadirArrayListStringAJTextAreaEnPalabras(JTextArea T, ArrayList<String> A) {
        for (String a : A) {
            T.append(a + " ");
        }
    }

    //-----------------------
    public static void sustituirArregloStringAJTextAreaEnLineas(JTextArea T, String[] A) {
        T.setText("");
        for (String a : A) {
            T.append(a + "\n");
        }
    }

    public static void sustituirArregloStringAJTextAreaEnPalabras(JTextArea T, String[] A) {
        T.setText("");
        for (String a : A) {
            T.append(a + " ");
        }
    }

    public static void sustituirArrayListStringAJTextAreaEnLineas(JTextArea T, ArrayList<String> A) {
        T.setText("");
        for (String a : A) {
            T.append(a + "\n");
        }

    }

    public static void sustituirArrayListStringAJTextAreaEnPalabras(JTextArea T, ArrayList<String> A) {
        T.setText("");
        for (String a : A) {
            T.append(a + " ");
        }
    }
}
