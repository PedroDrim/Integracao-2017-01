/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.objects.LogTester;
import br.ufg.inf.horus.util.validations.BsusException;
import br.ufg.inf.horus.implementation.controller.XmlParser;
import br.ufg.inf.horus.implementation.controller.FileResources;
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
     * Objeto responsável por retornar o conteudo dos arquivos internos .xml.
     */
    private FileResources fileResources;
    
    /**
     * Construtor publico do teste.
     */
    public XmlParserTest(){
        String usuario = "usuario";
        String senha = "senha";
        this.fileResources = new FileResources(usuario, senha, new LogTester());
    }
    
    /**
     * Teste unitário funcional do método 'getMessage'.
     *
     * @see XmlParser
     */
    @Test
    public void functionalGetMessageTest() {

        XmlParser instance = new XmlParser(new LogTester());

        String response = "Uma ou mais regras negociais foram violadas, "
                + "verifique a lista de erros.\n";

        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">"
                + "<soap:Header xmlns:est=\"http://servicos.saude.gov.br/"
                + "horus/v1r0/EstoqueService\"/><soap:Body xmlns:est=\"http://"
                + "servicos.saude.gov.br/horus/v1r0/EstoqueService\">"
                + "<soap:Fault><soap:Code><env:Value xmlns:"
                + "env=\"http://www.w3.org/2003/05/soap-envelope\">env:"
                + "Sender</env:Value></soap:Code><soap:Reason><soap:Text"
                + " xml:lang=\"pt-BR\">Uma ou mais regras negociais foram"
                + " violadas, verifique a lista de erros.</soap:Text></soap:"
                + "Reason><soap:Detail><msf:MSFalha xmlns:msf=\"http://"
                + "servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\">"
                + "<msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl"
                + "/mensageria/falha/v5r0/mensagem\"><men:codigo>"
                + "OSB_SEM_AUTENTICACAO</men:codigo><men:descricao>"
                + "As credenciais informadas não são válidas</men:descricao>"
                + "</msf:Mensagem></msf:MSFalha></soap:Detail></soap:Fault>"
                + "</soap:Body></soap:Envelope>";
        String request = instance.getMessage(xml);
        assertEquals(request, response);
    }

    /**
     * Teste unitário do construtor quando o parametro 'xml' é vazio.
     *
     * @see XmlParser
     */
    @Test(expected = BsusException.class)
    public void xmlIsNullTest() {

        XmlParser instance = new XmlParser(new LogTester());

        String response = "Uma ou mais regras negociais foram violadas,"
                + "verifique a lista de erros.\n";

        String request = instance.getMessage(null);
        assertEquals(request, response);
    }

    /**
     * Teste unitário do método 'getMessage' visando capturar uma excessão SAX.
     *
     * @see XmlParser
     */
    @Test(expected = BsusException.class)
    public void captureSAXExceptionGetMessageTest() {

        XmlParser instance = new XmlParser(new LogTester());

        String response = "Uma ou mais regras negociais foram violadas,"
                + "verifique a lista de erros.\n";

        String request = instance.getMessage("batata");
        assertEquals(request, response);
    }
}
