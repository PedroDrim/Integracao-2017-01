/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.controller.FileResources;
import br.ufg.inf.horus.implementation.objects.LogTester;
import br.ufg.inf.horus.util.validations.BsusException;
import br.ufg.inf.horus.util.model.Log;
import br.ufg.inf.horus.implementation.service.HttpRequest;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Classe responsável por realizar os testes de unidade da classe 'HttpRequest'.
 *
 * @see HttpRequest
 * @author Vinicius
 */
public class HttpRequestTest {

    /**
     * URL do serviço.
     */
    private String url;

    /**
     * Xml da requisisão SOAP.
     */
    private String body;

    /**
     * Resposta esperada.
     */
    private String resp;

    /**
     * Construtor publico da classe.
     */
    public HttpRequestTest() {

        this.url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";

        this.body = FileResources.getXml("consultarPosicaoEstoquePorCNES",
                new LogTester());
        this.resp = FileResources.getXml("resposta", new LogTester());
    }

    /**
     * Teste funcional do método 'request'.
     *
     * @see HttpRequest
     */
    @Test
    public void functionalRequestTest() {
        Log log = new LogTester();
        HttpRequest req = new HttpRequest();
        assertEquals(req.request(url, body, log), resp);
    }

    @Test(expected = BsusException.class)
    public void erroUrlTest() {
        Log log = new LogTester();
        HttpRequest req = new HttpRequest();
        req.request(null, body, log);
        assertEquals(req.request(url, body, log), resp);
    }

    @Test(expected = BsusException.class)
    public void erroBodyTest() {
        Log log = new LogTester();
        HttpRequest req = new HttpRequest();
        req.request(url, null, log);
        assertEquals(req.request(url, body, log), resp);
    }

    @Test(expected = BsusException.class)
    public void erroLogTest() {
        HttpRequest req = new HttpRequest();
        req.request(url, body, null);
        assertEquals(req.request(url, body, null), resp);
    }
}
