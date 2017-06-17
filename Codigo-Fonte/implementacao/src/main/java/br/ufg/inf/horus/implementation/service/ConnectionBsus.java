/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.service;

import br.ufg.inf.horus.implementation.model.Connection;
import br.ufg.inf.horus.util.model.Log;
import br.ufg.inf.horus.util.model.Security;
import br.ufg.inf.horus.util.validations.BsusException;
import br.ufg.inf.horus.util.validations.BsusValidator;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
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
        BsusValidator.verifyNull(usuario, "usuario", log);
        BsusValidator.verifyNull(senha, "senha", log);
        this.usuario = usuario;
        this.senha = senha;
    }

    /**
     * Define as credenciais de acesso da aplicação-usuário ao serviço
     * com base em uma interface.
     *
     * @see Connection
     * @param security interface para obtenção das credenciais.
     */
    @Override
    public void setCredential(Security security){
        BsusValidator.verifyNull(security, "security", log);
        this.usuario = security.getUser();
        this.senha = security.getPassword();
    }
    
    /**
     * Define a url do serviço a ser utilizado.
     *
     * @see Connection
     * @param url URL do serviço.
     */
    @Override
    public void setURL(String url) {
        BsusValidator.verifyNull(url, "url", log);
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
       String teste ="";
       try{
        BufferedReader br = new BufferedReader(new FileReader(
                "./src/main/resources/consultarPosicaoEstoquePorCNES") );
        String a;
        while((a = br.readLine())!=null){
        teste += a;
        }
        }catch (IOException ex){
           log.erro("Arquivo não encontrado");
        }
        String output = String.format(teste, usuario, senha,cnes);
        soap.append(output);
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
        String teste ="";
        try{
        BufferedReader br = new BufferedReader(new FileReader(
                "./src/main/resources/consultarPosicaoEstoquePorCNES"
                        + "PrincipioAtivo") );
        String a;
        while((a = br.readLine())!=null){
        teste += a;
        }
        }catch (IOException ex){
           log.erro("Arquivo não encontrado");
        }
        String output = String.format(teste, usuario, senha, cnes, principio);
        soap.append(output);
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
        String teste ="";
        try{
        BufferedReader br = new BufferedReader(new FileReader(
                "./src/main/resources/consultarPosicaoEstoquePorCNES"
                        + "PrincipioAtivoPaginado") );
        String a;
        while((a = br.readLine())!=null){
        teste += a;
        }
        }catch (IOException ex){
           log.erro("Arquivo não encontrado");
        }
        String output = String.format(teste, usuario, senha, cnes, principio,
                posicaoInicio,qtdRegistrosPagina,qtdRegistros);
        soap.append(output);
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
       String teste ="";
       try{
        BufferedReader br = new BufferedReader(new FileReader(
                "./src/main/resources/consultarProdutoPor"
                        + "CNESDispensacao") );
        String a;
        while((a = br.readLine())!=null){
        teste += a;
        }
        }catch (IOException ex){
           log.erro("Arquivo não encontrado");
        }
        String output = String.format(teste, usuario, senha,cnes);
        soap.append(output);
        String response = new CircuitBreaker(soap.toString(),
                url, log)
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
        String teste ="";
        try{
        BufferedReader br = new BufferedReader(new FileReader(
                "./src/main/resources/consultarProdutoPor"
                        + "CNESDispensacaoPaginado") );
        String a;
        while((a = br.readLine())!=null){
        teste += a;
        }
        }catch (IOException ex){
           log.erro("Arquivo não encontrado");
        }
        String output = String.format(teste, usuario, senha,cnes,posicaoInicio,
                qtdRegistrosPagina,qtdRegistros);
        soap.append(output);
        String response = new CircuitBreaker(soap.toString(), url, log)
                .execute();

        return getError(response);
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