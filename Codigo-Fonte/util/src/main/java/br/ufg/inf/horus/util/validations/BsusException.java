/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.util.validations;

/**
 * Encapsula as excessões do projeto.
 *
 * @author Pedro
 */
public class BsusException extends RuntimeException {

    /**
     * Construtor que captura a mensagem de erro.
     *
     * @param string Mensagem de erro a ser exibida.
     */
    public BsusException(String string) {
        super(string);
    }

    /**
     * Construtor que captura a mensagem de erro junto com o tipo de erro
     * gerado.
     *
     * @param string Mensagem de erro a ser exibida.
     * @param thrwbl Tipo de erro gerado.
     */
    public BsusException(String string, Throwable thrwbl) {
        super(string, thrwbl);
    }

    /**
     * Construtor que captura o tipo de erro gerado.
     *
     * @param thrwbl Tipo de erro gerado.
     */
    public BsusException(Throwable thrwbl) {
        super(thrwbl);
    }

}
