/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.controller;

import br.ufg.inf.horus.implementation.model.Log;
import java.io.FileNotFoundException;
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
     * @return message Mensagem com as informações tratadas.
     */
    public String getMessage(String xml) {

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                    .newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            InputSource source = new InputSource(new StringReader(xml));
            Document xmlDocument = builder.parse(source);

            NodeList n1 = xmlDocument.getElementsByTagName("soap:Text");

            Node node1;

            node1 = n1.item(0);

            String message = node1.getTextContent() + "\n";
            return (message);

        } catch (FileNotFoundException e) {
            String logMessage = "O documento .xml não foi encontrado.";
            log.erro(logMessage);
            throw new BsusException(logMessage, e);
        } catch (ParserConfigurationException e) {
            String logMessage = "Houve um erro ao buscar as informações.";
            log.erro(logMessage);
            throw new BsusException(logMessage, e);
        } catch (SAXException e) {
            String logMessage = "Houve um erro ao utilizar o SAX.";
            log.erro(logMessage);
            throw new BsusException(logMessage, e);
        } catch (IOException e) {
            String logMessage = "Houve um erro ao abrir o documento .xml";
            log.erro(logMessage);
            throw new BsusException(logMessage, e);
        }
    }
}
