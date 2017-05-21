package br.ufg.inf.horus.implementation;

/**
 * 
 * @author Vinicius
 * Classe ConnectionBsus que implementa os serviços da interface Connection.
 * Constrói a requisição e retorna a resposta.
 */
public class ConnectionBsus implements Connection{

    public static final String REQUESTADRESS = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
    
    /**
     * Método para consular estoque pelo número Cnes.
     * @param username Usuário do sistema Horus.
     * @param password Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @return response Resposta retornada da requisição.
     */
    @Override
    public String consultarPosicaoEstoquePorCNES(String username, String password, int cnes){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml(username,password));
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n </est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");        
        String response = new CircuitBreaker(soap.toString(), ConnectionBsus.REQUESTADRESS).execute();
     
        return response;
    }

    /**
     * Método para consultar o estoque pelo Cnes e Princípio Ativo.
     * @param username Usuário do sistema Horus.
     * @param password Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @param principio Princípio Ativo do medicamento.
     * @return response Resposta retornada da requisição.
     */
    @Override
    public String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml(username,password));
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n");
        soap.append(" <est:principioAtivo>").append(principio).append("</est:principioAtivo>\n </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(), ConnectionBsus.REQUESTADRESS).execute();
            
        return response;
    }
    
   /**
    * Método para consultar o estoque pelo Cnes e Princípio Ativo, com paginação.
    * @param username Usuário do sistema Horus.
    * @param password Senha do usuário.
    * @param cnes Número do CNES para busca, 7 posições.
    * @param principio Princípio Ativo do medicamento.
    * @param posicaoInicio Posição de início da listagem.
    * @param qtdRegistrosPagina Quantidade de registros por pagina.
    * @param qtdRegistros Quantidade de registros que serão retornados.
    * @return response Resposta retornada da requisição.
    */
    @Override
    public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(String username, String password, int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXmlPaginado(username,password));
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n");
        soap.append(" <est:principioAtivo>").append(principio).append("</est:principioAtivo>\n");
        soap.append(" <est:paginacao>\n <pag:posicaoRegistroInicio>").append(posicaoInicio).append("</pag:posicaoRegistroInicio>\n");
        soap.append(" <pag:quantidadeRegistrosPorPagina>").append(qtdRegistrosPagina).append("</pag:quantidadeRegistrosPorPagina>\n");
        soap.append(" <pag:quantidadeRegistros>").append(qtdRegistros).append("</pag:quantidadeRegistros>\n");
        soap.append(" </est:paginacao>\n");
        soap.append(" </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(), ConnectionBsus.REQUESTADRESS).execute();
              
        return response;
    }

    /**
     * Método para consular dados e estoque pelo número Cnes.
     * @param username Usuário do sistema Horus.
     * @param password Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @return response Resposta retornada da requisição.
     */
    @Override
    public String consultarProdutoPorCNESDispensacao(String username, String password, int cnes){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml(username,password));
        soap.append(" <est:requestConsultarProdutoPorCNESDispensacao>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n");
        soap.append(" </est:requestConsultarProdutoPorCNESDispensacao>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(), ConnectionBsus.REQUESTADRESS).execute();
                
        return response;
    }

    /**
     * Método para consultar o estoque pelo Cnes e Princípio Ativo, com paginação.
     * @param username Usuário do sistema Horus.
     * @param password Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @param posicaoInicio Posição de início da listagem.
     * @param qtdRegistrosPagina Quantidade de registros por pagina.
     * @param qtdRegistros Quantidade de registros que serão retornados.
     * @return response Resposta retornada da requisição.
     */
    @Override
    public String consultarProdutoPorCNESDispensacaoPaginado(String username, String password, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXmlPaginado(username,password));
        soap.append(" <est:requestConsultarProdutoPorCNESDispensacaoPaginado>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n");
        soap.append("<est:paginacao>\n");
        soap.append(" <pag:posicaoRegistroInicio>").append(posicaoInicio).append("</pag:posicaoRegistroInicio>\n");
        soap.append(" <pag:quantidadeRegistrosPorPagina>").append(qtdRegistrosPagina).append("</pag:quantidadeRegistrosPorPagina>\n");
        soap.append("<pag:quantidadeRegistros>").append(qtdRegistros).append("</pag:quantidadeRegistros>\n");
        soap.append(" </est:paginacao>\n");
        soap.append(" </est:requestConsultarProdutoPorCNESDispensacaoPaginado>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String response = new CircuitBreaker(soap.toString(), ConnectionBsus.REQUESTADRESS).execute();
           
        return response;
    }

    /**
     * Método que constrói o header padrão.
     * @param username Usuário do sistema Horus.
     * @param password Senha do usuário.
     * @return str Header da requisição para serviços não paginados.
     */
    private String buildHeaderXml(String username, String password){
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");
        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>").append(username).append("</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">").append(password).append("</wsse:Password>\n");
        str.append( " </wsse:UsernameToken>\n </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }
    
    /**
     * Método que constrói o header padrão.
     * @param username Usuário do sistema Horus.
     * @param password Senha do usuário.
     * @return str Header da requisição para serviços paginados.
     */
    private String buildHeaderXmlPaginado(String username, String password){
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\" xmlns:pag=\"http://servicos.saude.gov.br/wsdl/mensageria/v1r0/paginacao\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>").append(username).append("</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">").append(password).append("</wsse:Password>\n");
        str.append( " </wsse:UsernameToken>\n </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }
}
