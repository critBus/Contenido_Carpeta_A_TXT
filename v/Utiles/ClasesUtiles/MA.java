/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.ClasesUtiles;

import java.awt.Component;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author Rene
 */
public class MA implements ItemListener {

    Component C[];
    JComboBox<String> c;

    public MA(JComboBox<String> c, Component... C) {
        this.C = C;
        this.c = c;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            UIManager.setLookAndFeel(UIManager.getInstalledLookAndFeels()[c.getSelectedIndex()].getClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(MA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(MA.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(MA.class.getName()).log(Level.SEVERE, null, ex);
        }
        for (int i = 0; i < C.length; i++) {
            SwingUtilities.updateComponentTreeUI(C[i]);
        }
    }

}
