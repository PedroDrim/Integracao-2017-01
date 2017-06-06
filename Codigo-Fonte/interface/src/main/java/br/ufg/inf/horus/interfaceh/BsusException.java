/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.interfaceh;

/**
 *
 * @author aluno
 */
public class BsusException extends RuntimeException{

    public BsusException(String string) {
        super(string);
    }

    public BsusException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    public BsusException(Throwable thrwbl) {
        super(thrwbl);
    }
    
}
