/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.service;

import br.ufg.inf.horus.implementation.controller.BsusException;
import br.ufg.inf.horus.implementation.model.Connection;
import br.ufg.inf.horus.implementation.model.Log;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * Classe que implementa os serviços da interface Connection, constrói a
 * requisição e retorna a resposta.
 *
 * @see Connection
 * @author Vinicius
 */
public class ConnectionBsus implements Connection {

    /**
     * Objeto resposável por exibir Log's.
     *
     * @see Log
     */
    private Log log;

    /**
     * A credêncial de acesso do usuário ao serviço.
     */
    private String usuario;

    /**
     * A senha de acesso do usuário ao serviço.
     */
    private String senha;

    /**
     * A URL do serviço.
     */
    private String url;

    /**
     * Define uma estrutura para exibição de Log's.
     *
     * @see Connection
     * @param log Estrutura de log a ser definida.
     */
    @Override
    public void setLog(Log log) {
        this.log = log;
    }

    /**
     * Define as credenciais de acesso da aplicação-usuário ao serviço.
     *
     * @see Connection
     * @param usuario Identificador de acesso.
     * @param senha Senha de acesso.
     */
    @Override
    public void setCredential(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    /**
     * Define a url do serviço a ser utilizado.
     *
     * @see Connection
     * @param url URL do serviço.
     */
    @Override
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Método interno para consultar as posições nos estoques do produto com
     * base no cnes.
     *
     * @see Connection
     * @param cnes Número de cnes.
     * @return A posição no estoque do referido produto.
     */
    @Override
    public String consultarPosicaoEstoquePorCNES(int cnes) {
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml());
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" <est:cnes>");
        soap.append(cnes);
        soap.append("</est:cnes>\n "
                + "</est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(),
                url, log).execute();

        return getError(response);
    }

    /**
     * Método interno para consultar as posições nos estoques pelo número de
     * cnes e princípio ativo.
     *
     * @see Connection
     * @param cnes Número de cnes.
     * @param principio Tipo de princípio ativo.
     * @return A posição do estoque do referido produto.
     */
    @Override
    public String consultarPosicaoEstoquePorCNESPrincipioAtivo(int cnes,
            String principio) {
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml());
        soap.append(" "
            + "<est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" <est:cnes>");
        soap.append(cnes);
        soap.append("</est:cnes>\n");
        soap.append(" <est:principioAtivo>");
        soap.append(principio);
        soap.append("</est:principioAtivo>\n "
            + "</est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(), url, log)
                .execute();

        return getError(response);
    }

    /**
     * Método interno para consultar as posições nos estoques pelo número de
     * cnes, princípio ativo e dados de paginação.
     *
     * @see Connection
     * @param cnes Número de cnes.
     * @param principio Tipo de princípio ativo.
     * @param posicaoInicio Posição inicial.
     * @param qtdRegistrosPagina Quantidade de registros por página.
     * @param qtdRegistros Quantidade de registros ao todo.
     * @return A posição no estoque do referido produto.
     */
    @Override
    public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(int cnes,
            String principio, int posicaoInicio, int qtdRegistrosPagina,
            int qtdRegistros) {
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXmlPaginado());
        soap.append(
        " <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n");
        soap.append(" <est:cnes>");
        soap.append(cnes);
        soap.append("</est:cnes>\n");
        soap.append(" <est:principioAtivo>");
        soap.append(principio);
        soap.append("</est:principioAtivo>\n");
        soap.append(" <est:paginacao>\n <pag:posicaoRegistroInicio>");
        soap.append(posicaoInicio);
        soap.append("</pag:posicaoRegistroInicio>\n");
        soap.append(" <pag:quantidadeRegistrosPorPagina>");
        soap.append(qtdRegistrosPagina);
        soap.append("</pag:quantidadeRegistrosPorPagina>\n");
        soap.append(" <pag:quantidadeRegistros>");
        soap.append(qtdRegistros);
        soap.append("</pag:quantidadeRegistros>\n");
        soap.append(" </est:paginacao>\n");
        soap.append(" </est:requestConsultarPosicaoEstoquePor"
                + "CNESPrincipioAtivoPaginado>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(), url, log)
                .execute();

        return getError(response);
    }

    /**
     * Método interno para consultar as posições nos estoques, bem como os dados
     * do produto referentes ao número de cnes.
     *
     * @see Connection
     * @param cnes Número de cnes.
     * @return A posição no estoque e os dados do referido produto.
     */
    @Override
    public String consultarProdutoPorCNESDispensacao(int cnes) {
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml());
        soap.append(" <est:requestConsultarProdutoPorCNESDispensacao>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n");
        soap.append(" </est:requestConsultarProdutoPorCNESDispensacao>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(), url, log)
                .execute();

        return getError(response);
    }

    /**
     * Método interno para consultar as posições nos estoques, bem como os dados
     * do produto referentes ao número de cnes e dados de paginação.
     *
     * @see Connection
     * @param cnes Número de cnes.
     * @param posicaoInicio Posição inicial.
     * @param qtdRegistrosPagina Quantidade de registros por página.
     * @param qtdRegistros Quantidade de registros ao todo.
     * @return A posição no estoque e os dados do referido produto.
     */
    @Override
    public String consultarProdutoPorCNESDispensacaoPaginado(int cnes,
            int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXmlPaginado());
        soap.append(" <est:requestConsultarProdutoPor"
                + "CNESDispensacaoPaginado>\n");
        soap.append(" <est:cnes>");
        soap.append(cnes);
        soap.append("</est:cnes>\n");
        soap.append("<est:paginacao>\n");
        soap.append(" <pag:posicaoRegistroInicio>");
        soap.append(posicaoInicio);
        soap.append("</pag:posicaoRegistroInicio>\n");
        soap.append(" <pag:quantidadeRegistrosPorPagina>");
        soap.append(qtdRegistrosPagina);
        soap.append("</pag:quantidadeRegistrosPorPagina>\n");
        soap.append("<pag:quantidadeRegistros>");
        soap.append(qtdRegistros);
        soap.append("</pag:quantidadeRegistros>\n");
        soap.append(" </est:paginacao>\n");
        soap.append(" </est:requestConsultarProdutoPorCNESDispensacaoPaginado>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(), url, log)
                .execute();

        return getError(response);
    }

    /**
     * Método que constrói o header padrão.
     *
     * @return str Header da requisição para serviços não paginados.
     */
    private String buildHeaderXml() {
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope "
                + "xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\""
                + " xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/"
                + "EstoqueService\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:"
                + "wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-"
                + "wsswssecurity-secext-1.0.xsd\">\n");
        str.append(" <wsse:UsernameToken"
                + " wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\""
                + " xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/"
                + "oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>");
        str.append(usuario);
        str.append("</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004"
                + "/01/oasis-200401-wssusername-token-profile-1.0#PasswordText"
                + "\">").append(senha).append("</wsse:Password>\n");
        str.append(" </wsse:UsernameToken>\n </wsse:Security>\n"
                + " </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }

    /**
     * Método que constrói o header padrão.
     *
     * @return str Header da requisição para serviços paginados.
     */
    private String buildHeaderXmlPaginado() {
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope"
                + " xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\""
                + " xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/"
                + "EstoqueService\" xmlns:pag=\"http://servicos.saude.gov.br/"
                + "wsdl/mensageria/v1r0/paginacao\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security"
                + " xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/"
                + "oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");
        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683"
                + "-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org"
                + "/wss/2004/01/oasis-200401-wss-wssecurity-utility"
                + "-1.0.xsd\">\n");
        str.append(" <wsse:Username>");
        str.append(usuario);
        str.append("</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/"
                + "2004/01/oasis-200401-wssusername-token-profile"
                + "-1.0#PasswordText\">").append(senha)
                .append("</wsse:Password>\n");
        str.append(" </wsse:UsernameToken>\n"
                + " </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }

    /**
     * Método que verifica se o xml retornou com erro e o trata de acordo com o
     * negócio.
     *
     * @param xml
     * @return response String corrigida ou não
     */
    public String getError(String xml) {

        try {
            DocumentBuilderFactory builderFactory = DocumentBuilderFactory
                    .newInstance();
            builderFactory.setNamespaceAware(true);
            DocumentBuilder builder = builderFactory.newDocumentBuilder();

            InputSource source = new InputSource(new StringReader(xml));
            Document xmlDocument = builder.parse(source);

            NodeList n1 = xmlDocument.getElementsByTagName("soap:Text");
            NodeList n2 = xmlDocument.getElementsByTagName("men:codigo");
            NodeList n3 = xmlDocument.getElementsByTagName("men:descricao");
            
            Node node1;
            Node node2;
            Node node3;
            
            node1 = n1.item(0);
            node2 = n2.item(0);
            node3 = n3.item(0);
            
            StringBuilder message =  new StringBuilder();
            
            message.append(node1.getTextContent());
            message.append("\n");
            message.append(node2.getTextContent());
            message.append("\n");
            message.append(node3.getTextContent());
            
            if(node1.getTextContent().isEmpty() || 
                    node2.getTextContent().isEmpty() ||
                    node3.getTextContent().isEmpty()){
                log.info("Não foi encontrado mensagem de erro.");
                return xml;
            }
            else{
                return(message.toString());
            }

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
