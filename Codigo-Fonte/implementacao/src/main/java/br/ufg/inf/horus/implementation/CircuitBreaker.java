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

    private final HorusRequest horusRequest;

    public CircuitBreaker(HorusRequest horusRequest) {
        super(HystrixCommandGroupKey.Factory.asKey("HorusTolerance"));
        this.horusRequest = horusRequest;
    }
    
    @Override
    protected String run() throws Exception {
        return this.horusRequest.getResponse();
    }
 
    
}
