/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.controller.BsusException;
import br.ufg.inf.horus.implementation.model.Log;
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
    private StringBuilder body;

    /**
     * Resposta esperada.
     */
    private String resp;

    /**
     * Construtor publico da classe.
     */
    public HttpRequestTest() {

        this.url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";

        this.body = new StringBuilder();
        this.body.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        this.body.append("<soap:Header>\n");
        this.body.append("<wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");
        this.body.append("<wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        this.body.append("<wsse:Username>HORUS</wsse:Username>\n");
        this.body.append("<wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">SENHA</wsse:Password>\n");
        this.body.append("</wsse:UsernameToken>\n");
        this.body.append("</wsse:Security>\n");
        this.body.append("</soap:Header>\n");
        this.body.append("<soap:Body><est:requestConsultarPosicaoEstoquePorCNES>\n");
        this.body.append("<est:cnes>7604041</est:cnes>\n");
        this.body.append("</est:requestConsultarPosicaoEstoquePorCNES>\n");
        this.body.append("</soap:Body>\n");
        this.body.append("</soap:Envelope>");

        this.resp = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
                + "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"><soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/><soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"><soap:Fault><soap:Code><env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value></soap:Code><soap:Reason><soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text></soap:Reason><soap:Detail><msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\"><msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\"><men:codigo>OSB_SEM_AUTENTICACAO</men:codigo><men:descricao>As credenciais informadas não são válidas</men:descricao></msf:Mensagem></msf:MSFalha></soap:Detail></soap:Fault></soap:Body></soap:Envelope>";
    }

    /**
     * Teste funcional do método 'request'.
     * @see HttpRequest
     */
    @Test
    public void functionalRequestTest() {
        
        Log log = new Log() {

            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        };
                
        HttpRequest req = new HttpRequest();
        assertEquals(req.request(url, body.toString(), log), resp);
    }

    @Test(expected=BsusException.class)
    public void erroUrlTest(){
        Log log = new Log() {

            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        };
        HttpRequest req = new HttpRequest();
        req.request(null, body.toString(), log);
    }
    
    @Test(expected=BsusException.class)
    public void erroBodyTest(){
        Log log = new Log() {

            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        };
        HttpRequest req = new HttpRequest();
        req.request(url, null, log);
    }
    
    @Test(expected=BsusException.class)
    public void erroLogTest(){
        Log log = new Log() {

            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        };
        HttpRequest req = new HttpRequest();
        req.request(url, body.toString(), null);
    }
}

