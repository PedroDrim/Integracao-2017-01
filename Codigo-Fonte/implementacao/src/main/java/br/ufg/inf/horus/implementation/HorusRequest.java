/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

/**
 *
 * @author pedro
 */
public class HorusRequest {
 
    private String soapRequest;
    private String destination;

    public HorusRequest(String soapRequest, String destination) {
        this.soapRequest = soapRequest;
        this.destination = destination;
    }
    
    public String getResponse(){
        HttpRequest request = new HttpRequest();        
        return request.request(this.destination, soapRequest);
    }
}
