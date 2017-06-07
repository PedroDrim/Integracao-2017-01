/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.controller.XmlParser;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author viniciuscmac
 */
public class XmlParserTest {
    
    private String xml;

    @Before
    public void setUp() {
        this.xml = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n"
                + "   <soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/>\n"
                + "   <soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n"
                + "      <soap:Fault>\n"
                + "         <soap:Code>\n"
                + "            <env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value>\n"
                + "         </soap:Code>\n"
                + "         <soap:Reason>\n"
                + "            <soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text>\n"
                + "         </soap:Reason>\n"
                + "         <soap:Detail>\n"
                + "            <msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\">\n"
                + "               <msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\">\n"
                + "                  <men:codigo>OSB_SEM_AUTENTICACAO</men:codigo>\n"
                + "                  <men:descricao>As credenciais informadas não são válidas</men:descricao>\n"
                + "               </msf:Mensagem>\n"
                + "            </msf:MSFalha>\n"
                + "         </soap:Detail>\n"
                + "      </soap:Fault>\n"
                + "   </soap:Body>\n"
                + "</soap:Envelope>";
    }
    
    @After
    public void tearDown() {
        this.xml ="";
    }

    /**
     * Test of getMessage method, of class XmlParser.
     */
    @Test
    public void testGetMessage() {
        XmlParser instance = new XmlParser();
        assertEquals(instance.getMessage(xml),"Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas");
        
    }
    
}
