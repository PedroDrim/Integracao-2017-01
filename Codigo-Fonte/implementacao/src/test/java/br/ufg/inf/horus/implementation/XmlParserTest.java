/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.controller.BsusException;
import br.ufg.inf.horus.implementation.controller.XmlParser;
import br.ufg.inf.horus.implementation.model.Log;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Teste da classe XmlParser.
 *
 * @see XmlParser
 * @author Vinicius
 */
public class XmlParserTest {

    /**
     * Teste unitário funcional do método 'getMessage'.
     *
     * @see XmlParser
     */
    @Test
    public void functionalGetMessageTest() {

        StringBuilder xml = new StringBuilder();
        xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n");
        xml.append("<soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/>\n");
        xml.append("<soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        xml.append("<soap:Fault>\n");
        xml.append("<soap:Code>\n");
        xml.append("<env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value>\n");
        xml.append("</soap:Code>\n");
        xml.append("<soap:Reason>\n");
        xml.append("<soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text>\n");
        xml.append("</soap:Reason>\n");
        xml.append("<soap:Detail>\n");
        xml.append("<msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\">\n");
        xml.append("<msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\">\n");
        xml.append("<men:codigo>OSB_SEM_AUTENTICACAO</men:codigo>\n");
        xml.append("<men:descricao>As credenciais informadas não são válidas</men:descricao>\n");
        xml.append("</msf:Mensagem>\n");
        xml.append("</msf:MSFalha>\n");
        xml.append("</soap:Detail>\n");
        xml.append("</soap:Fault>\n");
        xml.append("</soap:Body>\n");
        xml.append("</soap:Envelope>");

        XmlParser instance = new XmlParser(new Log() {

            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        });

        String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n";
        /*
                String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
         */

        String request = instance.getMessage(xml.toString());
        assertEquals(request, response);
    }

    /**
     * Teste unitário do construtor quando o parametro 'xml' é vazio.
     *
     * @see XmlParser
     */
    @Test(expected = BsusException.class)
    public void xmlIsNullTest() {

        XmlParser instance = new XmlParser(new Log() {

            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        });

        String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n";
        /*
                String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
         */

        String request = instance.getMessage(null);
        assertEquals(request, response);
    }

    /**
     * Teste unitário do método 'getMessage' parametro xml é vazio.
     *
     * @see XmlParser
     */
    @Test(expected = BsusException.class)
    public void logIsNullGetMessageTest() {

        StringBuilder xml = new StringBuilder();
        xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n");
        xml.append("<soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/>\n");
        xml.append("<soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        xml.append("<soap:Fault>\n");
        xml.append("<soap:Code>\n");
        xml.append("<env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value>\n");
        xml.append("</soap:Code>\n");
        xml.append("<soap:Reason>\n");
        xml.append("<soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text>\n");
        xml.append("</soap:Reason>\n");
        xml.append("<soap:Detail>\n");
        xml.append("<msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\">\n");
        xml.append("<msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\">\n");
        xml.append("<men:codigo>OSB_SEM_AUTENTICACAO</men:codigo>\n");
        xml.append("<men:descricao>As credenciais informadas não são válidas</men:descricao>\n");
        xml.append("</msf:Mensagem>\n");
        xml.append("</msf:MSFalha>\n");
        xml.append("</soap:Detail>\n");
        xml.append("</soap:Fault>\n");
        xml.append("</soap:Body>\n");
        xml.append("</soap:Envelope>");

        XmlParser instance = new XmlParser(null);

        String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n";
        /*
                String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
         */

        String request = instance.getMessage(xml.toString());
        assertEquals(request, response);
    }

    /**
     * Teste unitário do método 'getMessage' visando capturar uma excessão SAX.
     *
     * @see XmlParser
     */
    @Test(expected = BsusException.class)
    public void captureSAXExceptionGetMessageTest() {

        XmlParser instance = new XmlParser(new Log() {

            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        });

        String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n";
        /*
                String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
         */

        String request = instance.getMessage("batata");
        assertEquals(request, response);
    }

    /**
     * Teste unitário do método 'getMessage' visando capturar uma excessão SAX.
     *
     * @see XmlParser
     */
    @Test(expected = BsusException.class)
    public void captureNullPointerExceptionGetMessageTest() {

        StringBuilder xml = new StringBuilder();
        xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n");
        xml.append("<soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/>\n");
        xml.append("<soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        xml.append("<soap:Fault>\n");
        xml.append("<soap:Code>\n");
        xml.append("<env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value>\n");
        xml.append("</soap:Code>\n");
        xml.append("<soap:Reason>\n");
        xml.append("<soap:BATATA xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:BATATA>\n");
        xml.append("</soap:Reason>\n");
        xml.append("<soap:Detail>\n");
        xml.append("<msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\">\n");
        xml.append("<msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\">\n");
        xml.append("<men:codigo>OSB_SEM_AUTENTICACAO</men:codigo>\n");
        xml.append("<men:descricao>As credenciais informadas não são válidas</men:descricao>\n");
        xml.append("</msf:Mensagem>\n");
        xml.append("</msf:MSFalha>\n");
        xml.append("</soap:Detail>\n");
        xml.append("</soap:Fault>\n");
        xml.append("</soap:Body>\n");
        xml.append("</soap:Envelope>");
        
        XmlParser instance = new XmlParser(new Log() {

            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        });

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
