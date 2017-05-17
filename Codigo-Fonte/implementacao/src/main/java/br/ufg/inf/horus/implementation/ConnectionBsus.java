/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

public class ConnectionBsus implements Connection{

    public static final String REQUESTADRESS = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
    
    //Método
    @Override
    public String consultarPosicaoEstoquePorCNES(String username, String password, int cnes){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml(username,password));
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n </est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        
        String response = new CircuitBreaker(soap.toString(), ConnectionBsus.REQUESTADRESS).execute();
        //result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());        
        return response;
    }


    @Override
    public String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml(username,password));
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n");
        soap.append(" <est:principioAtivo>").append(principio).append("</est:principioAtivo>\n </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
 
        String response = new CircuitBreaker(soap.toString(), ConnectionBsus.REQUESTADRESS).execute();
        //result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());        
        return response;
    }

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
        //result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());        
        return response;
    }

    @Override
    public String consultarProdutoPorCNESDispensacao(String username, String password, int cnes){
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml(username,password));
        soap.append(" <est:requestConsultarProdutoPorCNESDispensacao>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n");
        soap.append(" </est:requestConsultarProdutoPorCNESDispensacao>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");

        String response = new CircuitBreaker(soap.toString(), ConnectionBsus.REQUESTADRESS).execute();
        //result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());        
        return response;
    }

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
        //result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());        
        return response;
    }

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
