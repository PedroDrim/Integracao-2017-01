/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import java.util.concurrent.Future;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aluno
 */
public class CircuitBreakerTest {

    public CircuitBreakerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void singleAsynchronousTest() throws Exception {

        String destination = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        HeaderGenerator headerGenerator = new HeaderGenerator();
        String header = headerGenerator.getHeader("user", "123", 0);
        
        CircuitBreaker circuitBreaker = new CircuitBreaker(header, destination);
        
        Future<String> asynchrnous = circuitBreaker.queue();
        String response = asynchrnous.get();
        
        String esperado = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"><soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/><soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"><soap:Fault><soap:Code><env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value></soap:Code><soap:Reason><soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text></soap:Reason><soap:Detail><msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\"><msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\"><men:codigo>OSB_SEM_AUTENTICACAO</men:codigo><men:descricao>As credenciais informadas não são válidas</men:descricao></msf:Mensagem></msf:MSFalha></soap:Detail></soap:Fault></soap:Body></soap:Envelope>";
        
        assertEquals(response, esperado);
    }
}

class HeaderGenerator {

    public String getHeader(String username, String password, int cnes) {

        StringBuilder soap = new StringBuilder();
        soap.append(buildHeaderXml(username, password));
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n </est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        
        return soap.toString();
    }

    public String buildHeaderXml(String username, String password) {
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");
        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>").append(username).append("</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">").append(password).append("</wsse:Password>\n");
        str.append(" </wsse:UsernameToken>\n </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }
}
