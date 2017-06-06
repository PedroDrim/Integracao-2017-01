package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.interfaceh.Log;
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
 * 
 * @author Vinicius
 * Classe ConnectionBsus que implementa os serviços da interface Connection.
 * Constrói a requisição e retorna a resposta.
 */
public class ConnectionBsus implements Connection{
    
    private Log log;
    private String usuario;
    private String senha;
    private String url;
    
    /**
     * Método para definir a classe de exibição de mensagens (Log).
     * @param log Objeto que implemente a interface Log.
     */
    @Override
    public void setLog(Log log) {
        this.log = log;
    }
    
    /**
     * Método para definir o conjunto de credênciais para acesso aos serviços.
     * @param usuario Identificador de acesso.
     * @param senha Senha de acesso.
     */
    @Override
    public void setCredential(String usuario, String senha) {
        this.usuario = usuario;
        this.senha = senha;
    }

    /**
     * Método para definir a url de acesso aos serviços.
     * @param url Url referente aos serviços.
     */
    @Override
    public void setURL(String url) {
        this.url = url;
    }

    /**
     * Método para consular estoque pelo número Cnes.
     * @param cnes Número do CNES para busca, 7 posições.
     * @return response Resposta retornada da requisição.
     */
    @Override
    public String consultarPosicaoEstoquePorCNES(int cnes){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml());
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" <est:cnes>");
        soap.append(cnes);
        soap.append("</est:cnes>\n </est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");        
        String response = new CircuitBreaker(soap.toString(), url, log).execute();
        
        return getError(response);
    }

    /**
     * Método para consultar o estoque pelo Cnes e Princípio Ativo.
     * @param cnes Número do CNES para busca, 7 posições.
     * @param principio Princípio Ativo do medicamento.
     * @return response Resposta retornada da requisição.
     */
    @Override
    public String consultarPosicaoEstoquePorCNESPrincipioAtivo(int cnes,
            String principio){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml());
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" <est:cnes>");
        soap.append(cnes);
        soap.append("</est:cnes>\n");
        soap.append(" <est:principioAtivo>");
        soap.append(principio);
        soap.append("</est:principioAtivo>\n </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(), url, log)
                .execute();
            
        return getError(response);
    }
    
   /**
    * Método para consultar o estoque pelo Cnes e Princípio Ativo, com paginação.
    * @param cnes Número do CNES para busca, 7 posições.
    * @param principio Princípio Ativo do medicamento.
    * @param posicaoInicio Posição de início da listagem.
    * @param qtdRegistrosPagina Quantidade de registros por pagina.
    * @param qtdRegistros Quantidade de registros que serão retornados.
    * @return response Resposta retornada da requisição.
    */
    @Override
    public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXmlPaginado());
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n");
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
        soap.append(" </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(), url, log)
                .execute();
              
        return getError(response);
    }

    /**
     * Método para consular dados e estoque pelo número Cnes.
     * @param cnes Número do CNES para busca, 7 posições.
     * @return response Resposta retornada da requisição.
     */
    @Override
    public String consultarProdutoPorCNESDispensacao(int cnes){
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
     * Método para consultar o estoque pelo Cnes e Princípio Ativo,
     * com paginação.
     * @param cnes Número do CNES para busca, 7 posições.
     * @param posicaoInicio Posição de início da listagem.
     * @param qtdRegistrosPagina Quantidade de registros por pagina.
     * @param qtdRegistros Quantidade de registros que serão retornados.
     * @return response Resposta retornada da requisição.
     */
    @Override
    public String consultarProdutoPorCNESDispensacaoPaginado(int cnes,
            int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXmlPaginado());
        soap.append(" <est:requestConsultarProdutoPorCNESDispensacaoPaginado>\n");
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
     * @return str Header da requisição para serviços não paginados.
     */
    private String buildHeaderXml(){
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");
        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>");
        str.append(usuario);
        str.append("</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">").append(senha).append("</wsse:Password>\n");
        str.append( " </wsse:UsernameToken>\n </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }
    
    /**
     * Método que constrói o header padrão.
     * @return str Header da requisição para serviços paginados.
     */
    private String buildHeaderXmlPaginado(){
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\" xmlns:pag=\"http://servicos.saude.gov.br/wsdl/mensageria/v1r0/paginacao\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");
        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>");
        str.append(usuario);
        str.append("</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">").append(senha).append("</wsse:Password>\n");
        str.append( " </wsse:UsernameToken>\n </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }
    
    /**
     * Método que verifica se o xml retornou com erro e o trata de acordo com o negócio.
     * @param xml
     * @return response String corrigida ou não
     */
    private String getError(String xml){
         String message = "";
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
            message = node1.getTextContent()+"\n"+node2.getTextContent()+
                    "\n"+node3.getTextContent();
           
        } catch (FileNotFoundException e) {
        } catch (ParserConfigurationException | SAXException | IOException e) {
        }
        if(message==""){
            return xml;
        }
        else{
        return message;
        }
    }
}
