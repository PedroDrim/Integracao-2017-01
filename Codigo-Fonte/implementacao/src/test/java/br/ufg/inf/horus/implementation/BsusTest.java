/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.service.Bsus;
import br.ufg.inf.horus.implementation.model.Connection;
import br.ufg.inf.horus.implementation.model.Log;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 *
 * @author Vinicius
 */
public class BsusTest {

    private Bsus bsus;

    public BsusTest() {
        bsus = new Bsus();
        bsus.setLog(new TestLog());
    }

    /**
     * Caso de teste do método obterEstoquePorCNES.
     */
    @Test
    public void obterEstoquePorCNESTest() {

        bsus.setConnection(new TestConnection());

        String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n";
        /*
                String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
        */
        
        assertEquals(bsus.obterEstoquePorCNES(7604041), response);
    }

    /**
     * Caso de teste do método obterEstoquePorCNESEPrincipio.
     */
    @Test
    public void obterEstoquePorCNESEPrincipioTest() {

        bsus.setConnection(new TestConnection());

        String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n";
        /*
                String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
        */
        
        assertEquals(bsus.obterEstoquePorCNESEPrincipio(7604041, "PRINCIPIO"), response);
    }

    /**
     * Caso de teste do método obterEstoquePorCNESEPrincipioPaginado
     */
    @Test
    public void obterEstoquePorCNESEPrincipioPaginadoTest() {

        bsus.setConnection(new TestConnection());

        String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n";
        /*
                String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
        */
        
        assertEquals(bsus.obterEstoquePorCNESEPrincipioPaginado(7604041, "PRINCIPIO", 1111, 2222, 3333), response);
    }

    /**
     * Caso de teste do método obterDadosEEstoquePorCNES
     */
    @Test
    public void obterDadosEEstoquePorCNESTest() {

        bsus.setConnection(new TestConnection());

        String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n";
        /*
                String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
        */
        
        assertEquals(bsus.obterDadosEEstoquePorCNES(7604041), response);
    }

    /**
     * Caso de teste do método obterDadosEEstoquePorCNESPaginado
     */
    @Test
    public void obterDadosEEstoquePorCNESPaginadoTest() {

        bsus.setConnection(new TestConnection());

        String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n";
        /*
                String response = "Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
        */
        
        assertEquals(bsus.obterDadosEEstoquePorCNESPaginado(7604041, 1111, 2222, 3333), response);
    }
}

class TestLog implements Log {

    @Override
    public void info(String message) {
        System.out.println(message);
    }

    @Override
    public void erro(String message) {
        System.err.println(message);
    }
}

class TestConnection implements Connection {

    private StringBuilder xml;

    public TestConnection() {
        this.xml = new StringBuilder();
        this.xml.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n");
        this.xml.append("<soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/>\n");
        this.xml.append("<soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        this.xml.append("<soap:Fault>\n");
        this.xml.append("<soap:Code>\n");
        this.xml.append("<env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value>\n");
        this.xml.append("</soap:Code>\n");
        this.xml.append("<soap:Reason>\n");
        this.xml.append("<soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text>\n");
        this.xml.append("</soap:Reason>\n");
        this.xml.append("<soap:Detail>\n");
        this.xml.append("<msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\">\n");
        this.xml.append("<msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\">\n");
        this.xml.append("<men:codigo>OSB_SEM_AUTENTICACAO</men:codigo>\n");
        this.xml.append("<men:descricao>As credenciais informadas não são válidas</men:descricao>\n");
        this.xml.append("</msf:Mensagem>\n");
        this.xml.append("</msf:MSFalha>\n");
        this.xml.append("</soap:Detail>\n");
        this.xml.append("</soap:Fault>\n");
        this.xml.append("</soap:Body>\n");
        this.xml.append("</soap:Envelope>");
    }

    @Override
    public String consultarPosicaoEstoquePorCNES(int cnes) {
        return xml.toString();
    }

    @Override
    public String consultarPosicaoEstoquePorCNESPrincipioAtivo(
            int cnes, String principio) {
        return xml.toString();
    }

    @Override
    public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(
            int cnes, String principio, int posicaoInicio,
            int qtdRegistrosPagina, int qtdRegistros) {
        return xml.toString();
    }

    @Override
    public String consultarProdutoPorCNESDispensacao(int cnes) {
        return xml.toString();
    }

    @Override
    public String consultarProdutoPorCNESDispensacaoPaginado(int cnes,
            int posicaoInicio, int qtdRegistrosPagina,
            int qtdRegistros) {
        return xml.toString();
    }

    @Override
    public void setCredential(String usuario, String senha) {
        throw new UnsupportedOperationException(
                "Metétodo não utilizado.");
    }

    @Override
    public void setURL(String url) {
        throw new UnsupportedOperationException(
                "Metétodo não utilizado.");
    }

    @Override
    public void setLog(Log log) {
        throw new UnsupportedOperationException(
                "Método não utilizado.");
    }

}
