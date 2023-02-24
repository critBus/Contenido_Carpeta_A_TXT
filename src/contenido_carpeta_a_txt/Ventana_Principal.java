/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package contenido_carpeta_a_txt;

import Utiles.ClasesUtiles.Configuraciones.ConfiguracionDeVideo;
import Utiles.ClasesUtiles.Multimedia.Paquete.CarpetaSeries.CarpetaSeriesTX;
import Utiles.ClasesUtiles.Multimedia.Paquete.Paquete;
import Utiles.ClasesUtiles.Multimedia.Series.CatalogoDeSeries;
import Utiles.Exepciones.ExisteException;
import Utiles.Exepciones.IndiceFinalIncorrectoException;
import Utiles.Exepciones.IndiceIncorrectoException;
import Utiles.Exepciones.IndiceInicialIncorrectoException;
import Utiles.Exepciones.NoEncontradoException;
import Utiles.Exepciones.NoPermitidoException;
import Utiles.Exepciones.PINException;
import Utiles.MetodosUtiles.Archivo;
import Utiles.MetodosUtiles.MetodosUtiles;
import Utiles.MetodosUtiles.Videos;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;

/**
 *
 * @author Rene
 */
public class Ventana_Principal extends javax.swing.JFrame {

    File f = new File("").getAbsoluteFile();
    private final String invalidos[] = {"Contenido_Carpeta_A_TXT.jar"};

    /**
     * Creates new form Ventana_Principal
     */
    public Ventana_Principal() {
        initComponents();
        setLocationRelativeTo(null);
        if (f.getName().equals("Contenido_Carpeta_A_TXT")) {
            f = f.getParentFile();
        }
        System.out.println("f=" + f);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        PTodo = new javax.swing.JPanel();
        CopiarContenido = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        LLeyen = new javax.swing.JLabel();
        BUltimosCapitulos = new javax.swing.JButton();
        BTamañoContenido = new javax.swing.JButton();
        CBOrdenado = new javax.swing.JCheckBox();
        BCopiarSubtitulos = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        BRestarSubtitulo = new javax.swing.JButton();
        BAumentarSubtitulo = new javax.swing.JButton();
        SPSubtitulo = new javax.swing.JSpinner();
        BRectificarSrt = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("TXT Contenido");
        setResizable(false);

        PTodo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        CopiarContenido.setForeground(new java.awt.Color(0, 0, 102));
        CopiarContenido.setText("Copiar Contenido");
        CopiarContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CopiarContenidoActionPerformed(evt);
            }
        });

        jButton1.setForeground(new java.awt.Color(0, 0, 102));
        jButton1.setText("Copiar Videos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        LLeyen.setFont(new java.awt.Font("Blackadder ITC", 1, 24)); // NOI18N
        LLeyen.setForeground(new java.awt.Color(153, 0, 0));
        LLeyen.setText(" Leyen");

        BUltimosCapitulos.setForeground(new java.awt.Color(0, 0, 102));
        BUltimosCapitulos.setText("Ultimos Capitulos");
        BUltimosCapitulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUltimosCapitulosActionPerformed(evt);
            }
        });

        BTamañoContenido.setForeground(new java.awt.Color(102, 0, 0));
        BTamañoContenido.setText("Tamaño Contenido");
        BTamañoContenido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTamañoContenidoActionPerformed(evt);
            }
        });

        CBOrdenado.setForeground(new java.awt.Color(102, 0, 0));
        CBOrdenado.setSelected(true);
        CBOrdenado.setText("Ordenado");

        BCopiarSubtitulos.setForeground(new java.awt.Color(0, 0, 102));
        BCopiarSubtitulos.setText("Copiar Subtitulos");
        BCopiarSubtitulos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCopiarSubtitulosActionPerformed(evt);
            }
        });

        jButton2.setText("Mover Numero");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoMoverNumero(evt);
            }
        });

        jButton3.setText("Quitar Numero");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                apretoQuitarNumero(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, null, new java.awt.Color(51, 51, 51), new java.awt.Color(153, 153, 153)), "Subtituos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP));

        BRestarSubtitulo.setText("-");
        BRestarSubtitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRestarSubtituloActionPerformed(evt);
            }
        });

        BAumentarSubtitulo.setText("+");
        BAumentarSubtitulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAumentarSubtituloActionPerformed(evt);
            }
        });

        SPSubtitulo.setModel(new javax.swing.SpinnerNumberModel(1, 1, 1000, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(11, Short.MAX_VALUE)
                .addComponent(BRestarSubtitulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(SPSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(BAumentarSubtitulo)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BRestarSubtitulo)
                    .addComponent(BAumentarSubtitulo)
                    .addComponent(SPSubtitulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 9, Short.MAX_VALUE))
        );

        BRectificarSrt.setText("rectificar srt");

        javax.swing.GroupLayout PTodoLayout = new javax.swing.GroupLayout(PTodo);
        PTodo.setLayout(PTodoLayout);
        PTodoLayout.setHorizontalGroup(
            PTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PTodoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(LLeyen, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, PTodoLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(PTodoLayout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(PTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BRectificarSrt, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                    .addGroup(PTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BUltimosCapitulos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(CopiarContenido, javax.swing.GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)))
                .addGroup(PTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(PTodoLayout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(PTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(BCopiarSubtitulos, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                            .addComponent(BTamañoContenido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(PTodoLayout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(CBOrdenado)))
                .addContainerGap(32, Short.MAX_VALUE))
        );
        PTodoLayout.setVerticalGroup(
            PTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(PTodoLayout.createSequentialGroup()
                .addGap(4, 4, 4)
                .addComponent(LLeyen)
                .addGap(18, 18, 18)
                .addGroup(PTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CopiarContenido)
                    .addComponent(BCopiarSubtitulos))
                .addGap(18, 18, 18)
                .addGroup(PTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(BTamañoContenido))
                .addGap(18, 18, 18)
                .addGroup(PTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BUltimosCapitulos)
                    .addComponent(CBOrdenado))
                .addGap(18, 18, 18)
                .addGroup(PTodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 12, Short.MAX_VALUE)
                .addComponent(BRectificarSrt)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(PTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(PTodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CopiarContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CopiarContenidoActionPerformed
//      File a=new File("Data");
//      a.mkdirs();
//        System.out.println("a="+a.toString());
//        File f=new File(getClass().getResource("Data").getFile());

//        System.out.println(f.exists());
//        System.out.println(f.getAbsolutePath());
//        f=f.getParentFile();
//        System.out.println(f);
        Archivo.crearTXTContenidoDeCarpeta(f, f, "contenido", invalidos);

    }//GEN-LAST:event_CopiarContenidoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Videos.crearTXTContenidoDeCarpetaVideo(f, f, "contenido Videos");

    }//GEN-LAST:event_jButton1ActionPerformed

    private void BUltimosCapitulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUltimosCapitulosActionPerformed
        try {
            Videos.crearTXTUltimosCapitulos(f, f, "Ultimos Capitulos");
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BUltimosCapitulosActionPerformed

    private void BTamañoContenidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTamañoContenidoActionPerformed
        Carpetas c = new Carpetas(f, invalidos);
        if (CBOrdenado.isSelected()) {
            c.ordenarMayoresPrimero();
        }
        c.crearTXT(f, "Tamaño Contenido");
    }//GEN-LAST:event_BTamañoContenidoActionPerformed

    private void BCopiarSubtitulosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCopiarSubtitulosActionPerformed
        try {
            class auxiliar {

                LinkedList<Direccion> Direcciones = new LinkedList<>();

                public boolean addSerie(LinkedList<File> direcciones, ConfiguracionDeVideo cdv) throws FileNotFoundException {
                    boolean agrego = false;
                    for (int i = 0; i < direcciones.size(); i++) {
                        if (addSerie(direcciones.get(i), cdv)) {
                            agrego = true;
                        }
                    }
                    return agrego;
                }

                public boolean addSerie(File f, ConfiguracionDeVideo cdv) throws FileNotFoundException {
                    if (f.isDirectory()) {
                        JCheckBox jc = new JCheckBox("", false);
                        Direccion d = new Direccion(new File(f.toString()), true, getDirectoriosInvalidosSerie(f, cdv));
                        for (int i = 0; i < Direcciones.size(); i++) {
                            if (Direcciones.toString().equals(d.getF().toString())) {
                                return false;
                            }
                        }
                        Direcciones.add(d);
                        return true;
                    }
                    return false;
                }

                private LinkedList<DirectoriosInvalidos> getDirectoriosInvalidosSerie(File f, ConfiguracionDeVideo cdv) throws FileNotFoundException {
                    LinkedList<DirectoriosInvalidos> directoriosInvalidos = new LinkedList<DirectoriosInvalidos>();
                    if (f.isDirectory()) {
//                if (CBSinCarpetasBase.isSelected()) {
//                    File F[] = f.listFiles();
//                    for (int i = 0; i < F.length; i++) {
//                        if (F[i].isDirectory()) {
//                            directoriosInvalidos.add(new DirectoriosInvalidos(F[i].getName(), true));
//                        }
//                    }
//                } else {
                        String sub[] = {"! Subtitulos", "!!!Estrenos", "Subtitulos", "SUB", "Estrenos"};
                        File F[] = f.listFiles();
                        for (int i = 0; i < F.length; i++) {
                            if (F[i].isDirectory()) {
                                if (Paquete.esCarpetaSubtitulos(F[i], cdv)) {
//                        addSubtitulos(F[i]);
//                        EA.getSeccion().getSubtitulos().getDirecciones().add(new Direccion(new File(f.toString()), true, getDirectoriosInvalidosSerie(f, SinCarpetas)));
                                    directoriosInvalidos.add(new DirectoriosInvalidos(F[i].getName(), true));
                                    continue;
                                }

                                if ((MetodosUtiles.startsWith(F[i].getName(), "!", "_"))) {
                                    directoriosInvalidos.add(new DirectoriosInvalidos(F[i].getName(), true));
                                }

                            }
                        }
//                }
                    }
                    return directoriosInvalidos;
                }

                private CatalogoDeSeries getCatalogoDeSeries(ConfiguracionDeVideo cdv, LinkedList<Direccion>... DD) throws FileNotFoundException, IOException, ClassNotFoundException {

                    LinkedList<File> F = new LinkedList<File>();
                    LinkedList<LinkedList<String>> inva = new LinkedList<LinkedList<String>>();
                    for (int i = 0; i < DD.length; i++) {
                        //LinkedList<Direccion> D=DD[i];
                        for (int j = 0; j < DD[i].size(); j++) {
                            Direccion d = DD[i].get(j);
                            if (d.isSeleccionado()) {
                                LinkedList<String> invalidos = new LinkedList<String>();

                                for (int k = 0; k < d.getDirectoriosInvalidos().size(); k++) {
                                    DirectoriosInvalidos di = d.getDirectoriosInvalidos().get(k);
                                    if (di.isSeleccionado()) {
                                        invalidos.add(di.getDirectorioInvalido());
                                    }
                                }
                                F.add(d.getF());
                                inva.add(invalidos);
                            }
                        }
                    }
//       
                    return new CatalogoDeSeries(F, inva, cdv);
                }
            }
            auxiliar A = new auxiliar();
//            CatalogoDeSeries c = new CatalogoDeSeries();
            ConfiguracionDeVideo cdv = ConfiguracionDeVideo.getDefault();
            boolean esPaquete = true;
            File F[] = f.listFiles();
            for (int i = 0; i < F.length; i++) {
                if (Paquete.esCarpetaSubtitulos(F[i], cdv)) {
                    CarpetaSeriesTX tx = new CarpetaSeriesTX(f, new String[]{}, cdv);
                    if (!tx.getDirecciones().isEmpty()) {
                        A.addSerie(tx.getDirecciones(), cdv);
                        A.addSerie(tx.getSubtitulos().getDirecciones(), cdv);
//                        System.out.println("add");
                    }
//                    System.out.println("false");
                    esPaquete = false;
                    break;
                }
            }
            if (esPaquete) {
                Paquete p = new Paquete(f, cdv);
                if (p.getSeries() != null) {
                    if (p.getSeries().getEnTransmision() != null) {
                        if (p.getSeries().getEnTransmision().getTX() != null) {

                            A.addSerie(p.getSeries().getEnTransmision().getTX().getDirecciones(), cdv);

                            A.addSerie(p.getSeries().getEnTransmision().getTX().getSubtitulos().getDirecciones(), cdv);
                        }
                        if (p.getSeries().getEnTransmision().getClasicas() != null) {
                            A.addSerie(p.getSeries().getEnTransmision().getClasicas().getDirecciones(), cdv);
                        }
                        if (p.getSeries().getEnTransmision().getDobladas() != null) {
                            A.addSerie(p.getSeries().getEnTransmision().getDobladas().getDirecciones(), cdv);
                        }
                    }
                    if (p.getSeries().getFinalizadas() != null) {
                        if (p.getSeries().getFinalizadas().getFinalizadas() != null) {
                            A.addSerie(p.getSeries().getFinalizadas().getFinalizadas().getDirecciones(), cdv);
                        }
                        if (p.getSeries().getFinalizadas().getFinalizadasEspañol() != null) {
                            A.addSerie(p.getSeries().getFinalizadas().getFinalizadasEspañol().getDirecciones(), cdv);
                        }
                    }
                }
            }
            CatalogoDeSeries C = A.getCatalogoDeSeries(cdv, A.Direcciones);
//            C.imprimir();
            C.copiarSubtitulosACapitulos(false, cdv);
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BCopiarSubtitulosActionPerformed

    private void apretoMoverNumero(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoMoverNumero
        try {
            Videos.moverNumero(f);
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretoMoverNumero

    private void apretoQuitarNumero(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_apretoQuitarNumero
        try {
            Videos.eliminarNumeroDelPricipio(f.toString());
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_apretoQuitarNumero

    private void BAumentarSubtituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAumentarSubtituloActionPerformed
        try {
            Videos.atrasarSubtitulosSinMarca(f, (int) SPSubtitulo.getValue());
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BAumentarSubtituloActionPerformed

    private void BRestarSubtituloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRestarSubtituloActionPerformed
        try {
            Videos.adelantarSubtitulosSinMarca(f, (int) SPSubtitulo.getValue());
        } catch (Exception ex) {
            responerException(ex);
        }
    }//GEN-LAST:event_BRestarSubtituloActionPerformed
//    CatalogoDeSeries Catalogos = new CatalogoDeSeries();

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana_Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana_Principal().setVisible(true);
            }
        });
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

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BAumentarSubtitulo;
    private javax.swing.JButton BCopiarSubtitulos;
    private javax.swing.JButton BRectificarSrt;
    private javax.swing.JButton BRestarSubtitulo;
    private javax.swing.JButton BTamañoContenido;
    private javax.swing.JButton BUltimosCapitulos;
    private javax.swing.JCheckBox CBOrdenado;
    private javax.swing.JButton CopiarContenido;
    private javax.swing.JLabel LLeyen;
    private javax.swing.JPanel PTodo;
    private javax.swing.JSpinner SPSubtitulo;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
