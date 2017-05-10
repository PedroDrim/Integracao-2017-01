/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.horus;

import java.io.*;
import java.io.StringReader;
import java.util.Set;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 *
 * @author Vinicius
 */
public class XmlParser {

    public String getMessage() {

        String message = null;
        try {

            DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();

            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            Document xmlDocument = builder.parse(new FileInputStream("./teste.xml"));

            XPath xPath = XPathFactory.newInstance().newXPath();

            NodeList n1 = xmlDocument.getElementsByTagName("soap:Text");
            NodeList n2 = xmlDocument.getElementsByTagName("men:codigo");
            NodeList n3 = xmlDocument.getElementsByTagName("men:descricao");
            Node node1;
            Node node2;
            Node node3;
            node1 = n1.item(0);
            node2 = n2.item(0);
            node3 = n3.item(0);
            System.out.println(node1.getTextContent());
            System.out.println(node2.getTextContent());
            System.out.println(node3.getTextContent());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

        return message;
    }
}
