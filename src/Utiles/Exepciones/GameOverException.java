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
public class GameOverException extends Exception{

    public GameOverException() {
    }

    public GameOverException(String message) {
        super(message);
    }
    
}
