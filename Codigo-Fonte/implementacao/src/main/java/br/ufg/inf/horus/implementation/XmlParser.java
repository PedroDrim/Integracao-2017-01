package br.ufg.inf.horus.implementation;

import java.io.*;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

/**
 *
 * @author Vinicius/Pedro
 * Classe que faz a conversão da resposta Xml em resposta para o cliente.
 */
public class XmlParser {
    /**
     * Método que busca as informações necessárias na resposta.
     * @param xml String xml para tratamento.
     * @return message Mensagem com as informações tratadas.
     */
    public String getMessage(String xml) {

        String message = "";
        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document xmlDocument = builder.parse(xml);

            

            NodeList n1 = xmlDocument.getElementsByTagName("soap:Text");
            NodeList n2 = xmlDocument.getElementsByTagName("men:codigo");
            NodeList n3 = xmlDocument.getElementsByTagName("men:descricao");
            Node node1;
            Node node2;
            Node node3;
            node1 = n1.item(0);
            node2 = n2.item(0);
            node3 = n3.item(0);
            message = node1.getTextContent()+"\n"+node2.getTextContent()+"\n"+node3.getTextContent();
           
        } catch (FileNotFoundException e) {
        } catch (ParserConfigurationException | SAXException | IOException e) {
        }
        
        return message;
    }
}
