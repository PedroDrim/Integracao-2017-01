/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.service;

import br.ufg.inf.horus.implementation.controller.BsusException;
import br.ufg.inf.horus.implementation.model.HttpInterface;
import br.ufg.inf.horus.implementation.model.Log;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 * Classe que implementa o CircuitBreaker do sistema em conjunto com o Hystrix.
 *
 * @author Pedro
 */
public class CircuitBreaker extends HystrixCommand<String> {

    /**
     * Cabeçalho da requisisão SOAP.
     */
    private String soapRequest;

    /**
     * URL do serviço.
     */
    private String destination;

    /**
     * Objeto resposável por exibir Log's.
     *
     * @see Log
     */
    private Log log;

    /**
     * Objeto responsável por realizar as requisições com os serviços
     * disponíveis.
     *
     * @see HttpInterface
     */
    private HttpInterface request = new HttpRequest();

    /**
     * Construtor do CircuitBreaker.
     *
     * @see Log
     * @param soapRequest String com a requisição.
     * @param destination Url de destino.
     * @param log Objeto para exibição de mensagens (Log).
     */
    public CircuitBreaker(String soapRequest, String destination, Log log)
            throws BsusException {

        super(Setter.withGroupKey(
                HystrixCommandGroupKey.Factory.asKey("HorusTolerance")
        ).andCommandPropertiesDefaults(
                HystrixCommandProperties.Setter()
                        .withExecutionTimeoutEnabled(false)
        ));

        if (soapRequest == null) {
            String message = "O parametro 'soapRequest' não possui valor.";
            log.erro(message);
            throw new BsusException(message);
        }

        if (destination == null) {
            String message = "O parametro 'destination' não possui valor.";
            log.erro(message);
            throw new BsusException(message);
        }

        if (log == null) {
            String message = "O parametro 'log' não possui valor.";
            throw new BsusException(message);
        }

        this.log = log;
        this.soapRequest = soapRequest;
        this.destination = destination;
    }

    /**
     * Método run para threads.
     *
     * @return resposta Resposta da requisição
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        String resposta = this.request.request(this.destination,
                this.soapRequest, this.log);
        return resposta;
    }
}
