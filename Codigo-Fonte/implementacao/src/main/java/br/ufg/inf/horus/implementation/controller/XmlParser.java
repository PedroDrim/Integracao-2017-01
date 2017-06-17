/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.controller;

import br.ufg.inf.horus.util.model.Log;
import br.ufg.inf.horus.util.validations.BsusException;
import br.ufg.inf.horus.util.validations.BsusValidator;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Classe que faz a conversão da resposta Xml em resposta para o cliente.
 *
 * @author Vinicius/Pedro
 */
public class XmlParser {

    /**
     * Objeto resposável por exibir Log's.
     *
     * @see Log
     */
    private Log log;

    /**
     * Construtor publico da classe.
     *
     * @see Log
     * @param log Estrutura de log a ser definida.
     */
    public XmlParser(Log log) {
        this.log = log;
    }

    /**
     * Método que busca as informações necessárias na resposta.
     *
     * @param xml String xml para tratamento.
     * @return Mensagem com as informações tratadas.
     */
    public String getMessage(String xml) throws BsusException {

        BsusValidator.verifyNull(xml, "xml", log);
        String message = "";
        
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                    .newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            InputSource source = new InputSource(new StringReader(xml));
            Document document = builder.parse(source);
            NodeList n1 = document.getElementsByTagName("soap:Text");

            Node node1 = n1.item(0);
            message = node1.getTextContent() + "\n";
            
        } catch (ParserConfigurationException e) {
            String logMessage = "Houve um erro ao buscar as informações.";
            BsusValidator.catchException(e, logMessage, log);
        } catch (SAXException e) {
            String logMessage = "Houve um erro ao utilizar o SAX.";
            BsusValidator.catchException(e, logMessage, log);
        } catch (IOException e) {
            String logMessage = "Houve um erro ao abrir o documento .xml";
            BsusValidator.catchException(e, logMessage, log);
        }

        return message;
    }
}
