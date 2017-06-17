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
     * Objeto responsável por retornar o conteudo dos arquivos internos .xml.
     */
    private FileResources fileResources;

    /**
     * Construtor publico da classe.
     */
    public HttpRequestTest() {

        this.fileResources = new FileResources("usuario", "senha",
                new LogTester());
        
        this.url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        this.body = fileResources.consultarPosicaoEstoquePorCNES(1234567);
        this.resp = fileResources.resposta();
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
        assertEquals(req.request(null, body, log), resp);
    }

    @Test(expected = BsusException.class)
    public void erroBodyTest() {
        Log log = new LogTester();
        HttpRequest req = new HttpRequest();
        assertEquals(req.request(url, null, log), resp);
    }

    @Test
    public void erroLogTest() {
        HttpRequest req = new HttpRequest();
        String get = req.request(url, body, null);
        assertEquals(get, resp);
    }
}
