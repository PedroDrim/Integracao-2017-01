/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.model;

/**
 * Interface para relizar as requisições com o serviço.
 *
 * @see HttpRequest
 * @author Vinicius
 */
public interface HttpInterface {

    /**
     * Realiza as requisições com o serviço.
     *
     * @see HttpRequest
     * @param url URL do serviço.
     * @param body Corpo da requisição.
     * @param log Objeto para exibição de mensagens (Log).
     * @return A resposta da requisição referida.
     */
    String request(String url, String body, Log log);
}
