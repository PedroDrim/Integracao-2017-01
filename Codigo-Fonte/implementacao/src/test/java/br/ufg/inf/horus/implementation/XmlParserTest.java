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

        String xml = fileResources.resposta();
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
