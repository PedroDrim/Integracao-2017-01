package br.ufg.inf.horus.implementation;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 *
 * @author Pedro
 * Classe que implemente o CircuitBreaker do sistema com o Hystrix.
 */
public class CircuitBreaker extends HystrixCommand<String>{

    private String soapRequest;
    private String destination;
    private HttpRequest request = new HttpRequest();

    /**
     * Construtor.
     * @param soapRequest String com a requisição.
     * @param destination Url de destino.
     */
    public CircuitBreaker(String soapRequest, String destination) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HorusTolerance")));
        
        this.soapRequest = soapRequest;
        this.destination = destination;
    }
    
    /**
     * Método run para threads.
     * @return resposta Resposta da requisição
     * @throws Exception 
     */
    @Override
    protected String run() throws Exception {
        String resposta = this.request.request(this.destination, this.soapRequest);
        return resposta;
    }
}
