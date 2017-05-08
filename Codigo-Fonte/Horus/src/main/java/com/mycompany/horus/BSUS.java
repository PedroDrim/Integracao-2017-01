/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.horus;

public class BSUS {


    public static void main(String[] args) throws Exception {

       //consultarPosicaoEstoquePorCNES();
        XmlParser parser = new XmlParser();
        parser.getMessage();
    }

    public static String consultarPosicaoEstoquePorCNES(){
        String result;
        HttpRequest request = new HttpRequest();
        XmlParser parser = new XmlParser();
        StringBuilder soap = new StringBuilder();
        soap.append(buildHeaderXml());
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" <est:cnes>7604041</est:cnes>\n </est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        result = request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());
        parser.getMessage();
        return result;
    }


    public static String consultarPosicaoEstoquePorCNESPrincipioAtivo(){
        HttpRequest request = new HttpRequest();

        StringBuilder soap = new StringBuilder();
        soap.append(buildHeaderXml());
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" <est:cnes>7604041</est:cnes>\n");
        soap.append(" <est:principioAtivo>Principio Ativo</est:principioAtivo>\n </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");

        return request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());
    }

    public static String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(){
        HttpRequest request = new HttpRequest();

        StringBuilder soap = new StringBuilder();
        soap.append(buildHeaderXmlPaginado());
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n");
        soap.append(" <est:cnes>7604041</est:cnes>\n");
        soap.append(" <est:principioAtivo>Descricao</est:principioAtivo>\n");
        soap.append(" <est:paginacao>\n <pag:posicaoRegistroInicio>1</pag:posicaoRegistroInicio>\n");
        soap.append(" <pag:quantidadeRegistrosPorPagina>15</pag:quantidadeRegistrosPorPagina>\n");
        soap.append(" <pag:quantidadeRegistros>40</pag:quantidadeRegistros>\n");
        soap.append(" </est:paginacao>\n");
        soap.append(" </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        return request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());
    }

    public static String consultarProdutoPorCNESDispensacao(){
        HttpRequest request = new HttpRequest();

        StringBuilder soap = new StringBuilder();
        soap.append(buildHeaderXml());
        soap.append(" <est:requestConsultarProdutoPorCNESDispensacao>\n");
        soap.append(" <est:cnes>7604041</est:cnes>\n");
        soap.append(" </est:requestConsultarProdutoPorCNESDispensacao>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");

        return request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());
    }

    public static String consultarProdutoPorCNESDispensacaoPaginado(){

        HttpRequest request = new HttpRequest();

        StringBuilder soap = new StringBuilder();
        soap.append(buildHeaderXmlPaginado());
        soap.append(" <est:requestConsultarProdutoPorCNESDispensacaoPaginado>\n");
        soap.append(" <est:cnes>7604041</est:cnes>\n");
        soap.append(" <!--Optional:-->\n");
        soap.append("<est:paginacao>\n");
        soap.append(" <pag:posicaoRegistroInicio>1</pag:posicaoRegistroInicio>\n");
        soap.append(" <pag:quantidadeRegistrosPorPagina>5</pag:quantidadeRegistrosPorPagina>\n");
        soap.append(" <!--Optional:-->\n");
        soap.append("<pag:quantidadeRegistros>15</pag:quantidadeRegistros>\n");
        soap.append(" </est:paginacao>\n");
        soap.append(" </est:requestConsultarProdutoPorCNESDispensacaoPaginado>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");

        return request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());
    }

    public static String buildHeaderXml(){
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");
        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>HORUS</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">SENHA</wsse:Password>\n");
        str.append( " </wsse:UsernameToken>\n </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }

    public static String buildHeaderXmlPaginado(){
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\" xmlns:pag=\"http://servicos.saude.gov.br/wsdl/mensageria/v1r0/paginacao\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>HORUS</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">SENHA</wsse:Password>\n");
        str.append( " </wsse:UsernameToken>\n </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }
}
