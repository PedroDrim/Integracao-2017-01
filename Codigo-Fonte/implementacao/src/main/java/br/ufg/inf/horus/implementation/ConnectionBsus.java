/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

public class ConnectionBsus implements Connection{

    //Método
    public String consultarPosicaoEstoquePorCNES(String username, String password, int cnes){
        String result;
        HttpRequest request = new HttpRequest();
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml(username,password));
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" <est:cnes>"+cnes+"</est:cnes>\n </est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());

        return result;
    }


    public String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio){
        HttpRequest request = new HttpRequest();
        String result;
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml(username,password));
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" <est:cnes>"+cnes+"</est:cnes>\n");
        soap.append(" <est:principioAtivo>"+principio+"</est:principioAtivo>\n </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());

        return result;
    }

    public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(String username, String password, int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros){
        HttpRequest request = new HttpRequest();
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXmlPaginado(username,password));
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n");
        soap.append(" <est:cnes>"+cnes+"</est:cnes>\n");
        soap.append(" <est:principioAtivo>"+principio+"</est:principioAtivo>\n");
        soap.append(" <est:paginacao>\n <pag:posicaoRegistroInicio>"+posicaoInicio+"</pag:posicaoRegistroInicio>\n");
        soap.append(" <pag:quantidadeRegistrosPorPagina>"+qtdRegistrosPagina+"</pag:quantidadeRegistrosPorPagina>\n");
        soap.append(" <pag:quantidadeRegistros>"+qtdRegistros+"</pag:quantidadeRegistros>\n");
        soap.append(" </est:paginacao>\n");
        soap.append(" </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());

        return result;
    }

    public String consultarProdutoPorCNESDispensacao(String username, String password, int cnes){
        HttpRequest request = new HttpRequest();
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXml(username,password));
        soap.append(" <est:requestConsultarProdutoPorCNESDispensacao>\n");
        soap.append(" <est:cnes>"+cnes+"</est:cnes>\n");
        soap.append(" </est:requestConsultarProdutoPorCNESDispensacao>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        String result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());

        return result;
    }

    public String consultarProdutoPorCNESDispensacaoPaginado(String username, String password, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros){
        String result;
        HttpRequest request = new HttpRequest();
        StringBuilder soap = new StringBuilder();

        soap.append(buildHeaderXmlPaginado(username,password));
        soap.append(" <est:requestConsultarProdutoPorCNESDispensacaoPaginado>\n");
        soap.append(" <est:cnes>"+cnes+"</est:cnes>\n");
        soap.append("<est:paginacao>\n");
        soap.append(" <pag:posicaoRegistroInicio>"+posicaoInicio+"</pag:posicaoRegistroInicio>\n");
        soap.append(" <pag:quantidadeRegistrosPorPagina>"+qtdRegistrosPagina+"</pag:quantidadeRegistrosPorPagina>\n");
        soap.append("<pag:quantidadeRegistros>"+qtdRegistros+"</pag:quantidadeRegistros>\n");
        soap.append(" </est:paginacao>\n");
        soap.append(" </est:requestConsultarProdutoPorCNESDispensacaoPaginado>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());

        return result;
    }

    public String buildHeaderXml(String username, String password){
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");
        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>"+username+"</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">"+password+"</wsse:Password>\n");
        str.append( " </wsse:UsernameToken>\n </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }

    public static String buildHeaderXmlPaginado(String username, String password){
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\" xmlns:pag=\"http://servicos.saude.gov.br/wsdl/mensageria/v1r0/paginacao\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>"+username+"</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">"+password+"</wsse:Password>\n");
        str.append( " </wsse:UsernameToken>\n </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }
}
