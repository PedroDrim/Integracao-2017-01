/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author vinicius
 */
public class HttpRequestTest {
    
    private String url;
    private String body;
    private String wrongBody;
    private String resp;
    private String wrongResp;

    
    @Before
    public void setUp() {
        this.url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        this.body = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n" +
" <soap:Header>\n" +
" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n" +
" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
" <wsse:Username>HORUS</wsse:Username>\n" +
" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">SENHA</wsse:Password>\n" +
" </wsse:UsernameToken>\n" +
" </wsse:Security>\n" +
" </soap:Header>\n" +
" <soap:Body><est:requestConsultarPosicaoEstoquePorCNES>\n" +
" <est:cnes>7604041</est:cnes>\n" +
" </est:requestConsultarPosicaoEstoquePorCNES>\n" +
" </soap:Body>\n" +
"</soap:Envelope>";
     
    this.resp= "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"><soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/><soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"><soap:Fault><soap:Code><env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value></soap:Code><soap:Reason><soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text></soap:Reason><soap:Detail><msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\"><msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\"><men:codigo>OSB_SEM_AUTENTICACAO</men:codigo><men:descricao>As credenciais informadas não são válidas</men:descricao></msf:Mensagem></msf:MSFalha></soap:Detail></soap:Fault></soap:Body></soap:Envelope>";
    }
    
    @After
    public void tearDown() {
        this.url ="";
        this.body="";
        this.resp = "";
    }

    /**
     * Test of request method, of class HttpRequest.
     */
    @Test
    public void testRequest() {
        HttpRequest req = new HttpRequest();
        //System.out.println(req.request(url, body));
        assertEquals(req.request(url,body),resp);
    }

    
}
