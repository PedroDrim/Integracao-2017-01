/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.ufg.inf.horus.interfaceh;

/**
 * Interface para geração de mensagens de controle (Log's)
 * @author Pedro.
 */
public interface Log {
    
    /**
     * Exibição de uma mensagem informativa.
     * @param message Mensagem a ser exibida.
     */
    void info(String message);
    
    /**
     * Exibição de uma mensagem de erro.
     * @param message Message a ser exibida.
     */
    void erro(String message);
}
