/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.horus;

public class BSUS {


    public static void main(String[] args) throws Exception {

       consultarProdutoPorCNESDispensacao();

    }

    public static String consultarPosicaoEstoquePorCNES(){

        HttpRequest request = new HttpRequest();
        String soapBody = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n" +
                " <soap:Header>\n" +
                " <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n" +
                " <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
                " <wsse:Username>HORUS</wsse:Username>\n" +
                " <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">SENHA</wsse:Password>\n" +
                " </wsse:UsernameToken>\n" +
                " </wsse:Security>\n" +
                " </soap:Header>\n" +
                " <soap:Body><est:requestConsultarPosicaoEstoquePorCNES>\n" +
                " <est:cnes>7604041</est:cnes>\n" +
                " </est:requestConsultarPosicaoEstoquePorCNES>\n" +
                " </soap:Body>\n" +
                "</soap:Envelope>\n";

        StringBuilder soap = new StringBuilder(soapBody);

        return request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());
    }


    public static String consultarPosicaoEstoquePorCNESPrincipioAtivo(){
        HttpRequest request = new HttpRequest();
        String soapBody = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n" +
                " <soap:Header>\n" +
                " <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n" +
                " <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
                " <wsse:Username>HORUS</wsse:Username>\n" +
                " <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">SENHA</wsse:Password>\n" +
                " </wsse:UsernameToken>\n" +
                " </wsse:Security>\n" +
                " </soap:Header>\n" +
                " <soap:Body>\n" +
                " <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n" +
                " <est:cnes>7604041</est:cnes>\n" +
                " <est:principioAtivo>Principio Ativo</est:principioAtivo>\n" +
                " </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivo>\n" +
                " </soap:Body>\n" +
                "</soap:Envelope>\n";

        StringBuilder soap = new StringBuilder(soapBody);

        return request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());
    }

    public static String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(){
        HttpRequest request = new HttpRequest();
        String soapBody = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n" +
                " <soap:Header>\n" +
                " <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n" +
                " <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
                " <wsse:Username>HORUS</wsse:Username>\n" +
                " <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">SENHA</wsse:Password>\n" +
                " </wsse:UsernameToken>\n" +
                " </wsse:Security>\n" +
                " </soap:Header>\n" +
                "<soap:Body>\n" +
                " <est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n" +
                " <est:cnes>[Código CNES]</est:cnes>\n" +
                " <est:principioAtivo>[Descrição Princípio Ativo]</est:principioAtivo>\n" +
                " <!--Optional:-->\n" +
                " <est:paginacao>\n" +
                " <pag:posicaoRegistroInicio>[Posição Inicio]</pag:posicaoRegistroInicio>\n" +
                " <pag:quantidadeRegistrosPorPagina>[Quantidade de Registros por\n" +
                "Página]</pag:quantidadeRegistrosPorPagina>\n" +
                " <!--Optional:-->\n" +
                " <pag:quantidadeRegistros>[Quantidade de Registros]</pag:quantidadeRegistros>\n" +
                " </est:paginacao>\n" +
                " </est:requestConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado>\n" +
                " </soap:Body>\n"+
                "</soap:Envelope>\n";

        StringBuilder soap = new StringBuilder(soapBody);

        return request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());
    }

    public static String consultarProdutoPorCNESDispensacao(){
        HttpRequest request = new HttpRequest();
        String soapBody = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n" +
                " <soap:Header>\n" +
                " <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n" +
                " <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
                " <wsse:Username>HORUS</wsse:Username>\n" +
                " <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">SENHA</wsse:Password>\n" +
                " </wsse:UsernameToken>\n" +
                " </wsse:Security>\n" +
                " </soap:Header>\n" +
                "<soap:Body>\n" +
                " <est:requestConsultarProdutoPorCNESDispensacao>\n" +
                " <est:cnes>[Código CNES]</est:cnes>\n" +
                " </est:requestConsultarProdutoPorCNESDispensacao>\n" +
                " </soap:Body>\n"+
                "</soap:Envelope>\n";

        StringBuilder soap = new StringBuilder(soapBody);

        return request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());
    }

    public static String consultarProdutoPorCNESDispensacaoPaginado(){

        HttpRequest request = new HttpRequest();
        String soapBody = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n" +
                " <soap:Header>\n" +
                " <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n" +
                " <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n" +
                " <wsse:Username>HORUS</wsse:Username>\n" +
                " <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">SENHA</wsse:Password>\n" +
                " </wsse:UsernameToken>\n" +
                " </wsse:Security>\n" +
                " </soap:Header>\n" +
                "<soap:Body>\n" +
                " <est:requestConsultarProdutoPorCNESDispensacaoPaginado>\n" +
                " <est:cnes>[CNES]</est:cnes>\n" +
                " <!--Optional:-->\n" +
                " <est:paginacao>\n" +
                " <pag:posicaoRegistroInicio>[Posição Inicio]</pag:posicaoRegistroInicio>\n" +
                " <pag:quantidadeRegistrosPorPagina>[Quantidade de Registros por\n" +
                "Página]</pag:quantidadeRegistrosPorPagina>\n" +
                " <!--Optional:-->\n" +
                " <pag:quantidadeRegistros>[Quantidade de Registros]</pag:quantidadeRegistros>\n" +
                " </est:paginacao>\n" +
                " </est:requestConsultarProdutoPorCNESDispensacaoPaginado>\n" +
                " </soap:Body>\n"+
                "</soap:Envelope>\n";

        StringBuilder soap = new StringBuilder(soapBody);

        return request.request("https://servicos.saude.gov.br/horus/v1r0/EstoqueService", soap.toString());
    }
}
