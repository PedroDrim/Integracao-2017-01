/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.util.validations;

import br.ufg.inf.horus.util.model.Log;

/**
 * Classe responsável por validar entrada de parametros.
 *
 * @author Pedro
 */
public class BsusValidator {

    /**
     * Verifica se o parametro possui valor diferente de 'null'.
     *
     * @param objeto Valor do parametro a ser verificado.
     * @param nome Nome do parametro.
     * @param log Objeto de exibição de Log's (Opcional).
     * @throws BsusException
     */
    public static void verifyNull(Object objeto, String nome, Log log)
            throws BsusException {
        if (objeto == null) {
            String message = "O parametro '" + nome + "' não possui valor.";
            if (log != null) {
                log.erro(message);
            }
            throw new BsusException(message);
        }
    }

    /**
     * Captura um exception.
     *
     * @param e Exceção a ser capturada.
     * @param mensagem Mensagem de erro a ser exibida.
     * @param log Objeto referente aos Log's (Opcional).
     * @throws BsusException
     */
    public static void catchException(Exception e, String mensagem, Log log)
            throws BsusException {

        BsusException bsusException;
        if (log != null) {
            log.erro(mensagem);
        }

        if (e == null) {
            bsusException = new BsusException(mensagem);
        } else {
            bsusException = new BsusException(mensagem, e);
        }

        throw bsusException;
    }
}
