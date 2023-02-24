/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utiles.Exepciones;

/**
 *
 * @author Rene
 */
public class ErrorInternoException extends Exception{

    public ErrorInternoException() {
    }

    public ErrorInternoException(String message) {
        super(message);
    }
    
}