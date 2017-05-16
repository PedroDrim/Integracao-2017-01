/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;

/**
 *
 * @author pedro
 */
public class CircuitBreaker extends HystrixCommand<String>{

    private String soapRequest;
    private String destination;
    private HttpRequest request = new HttpRequest();

    public CircuitBreaker(String soapRequest, String destination) {
        super(HystrixCommandGroupKey.Factory.asKey("HorusTolerance"));
        this.soapRequest = soapRequest;
        this.destination = destination;
    }
    
    @Override
    protected String run() throws Exception {
        return this.request.request(this.destination, this.soapRequest);
    }
 
    
}
