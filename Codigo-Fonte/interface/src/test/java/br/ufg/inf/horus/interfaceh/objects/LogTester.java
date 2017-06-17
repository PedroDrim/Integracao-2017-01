/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.interfaceh.objects;

import br.ufg.inf.horus.util.model.Log;
import java.util.ArrayList;
import java.util.List;

/**
 * Objeto que implementa a interface para geração de mensagens de controle
 * (Log's).
 *
 * @see Log
 * @author Pedro
 */
public class LogTester implements Log {

    /**
     * Lista que armazenará as mensagens de erros gerados.
     */
    private List message;

    /**
     * Construtor de LogTester.
     */
    public LogTester() {
        this.message = new ArrayList<>();
    }

    /**
     * Método para obter a lista de mensagens geradas.
     * @return A lista com as mensagens geradas.
     */
    public List<String> getMessages() {
        return this.message;
    }

    /**
     * Exibição de uma mensagem informativa.
     *
     * @see Log
     * @param message Mensagem a ser exibida.
     */
    @Override
    public void info(String message) {
        this.message.add(message);
    }

    /**
     * Exibição de uma mensagem de erro.
     *
     * @see Log
     * @param message Message a ser exibida.
     */
    @Override
    public void erro(String message) {
        this.message.add(message);
    }

}
