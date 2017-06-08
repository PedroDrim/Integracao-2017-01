/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.controller.XmlParser;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 *
 * @author Vinicius
 */
public class XmlParserTest {
    
    private StringBuilder xml;

    public XmlParserTest() {
        this.xml = new StringBuilder();
        this.xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n");
        this.xml.append("<soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/>\n");
        this.xml.append("<soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        this.xml.append("<soap:Fault>\n");
        this.xml.append("<soap:Code>\n");
        this.xml.append("<env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value>\n");
        this.xml.append("</soap:Code>\n");
        this.xml.append("<soap:Reason>\n");
        this.xml.append("<soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text>\n");
        this.xml.append("</soap:Reason>\n");
        this.xml.append("<soap:Detail>\n");
        this.xml.append("<msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\">\n");
        this.xml.append("<msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\">\n");
        this.xml.append("<men:codigo>OSB_SEM_AUTENTICACAO</men:codigo>\n");
        this.xml.append("<men:descricao>As credenciais informadas não são válidas</men:descricao>\n");
        this.xml.append("</msf:Mensagem>\n");
        this.xml.append("</msf:MSFalha>\n");
        this.xml.append("</soap:Detail>\n");
        this.xml.append("</soap:Fault>\n");
        this.xml.append("</soap:Body>\n");
        this.xml.append("</soap:Envelope>");
    }

    /**
     * Test of getMessage method, of class XmlParser.
     */
    @Test
    public void testGetMessage() {
        XmlParser instance = new XmlParser();
        
        String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n";
        /*
                String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
        */
                
        String request = instance.getMessage(xml.toString());
        assertEquals(request, response);
        
    }
    
}
