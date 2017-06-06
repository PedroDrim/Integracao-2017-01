/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.interfaceh.Log;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author viniciuscmac
 */
public class ConnectionBsusTest {
    
    private String result;
    private String resultPaginado; 
    private int cnes;
    private ConnectionBsus instance;
    
    public ConnectionBsusTest(){
        instance = new ConnectionBsus();
        instance.setCredential("user", "pass");
        instance.setURL("https://servicos.saude.gov.br/horus/v1r0/EstoqueService");
        instance.setLog(new Log() {
            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        });
    }
    
    @Before
    public void setUp() {
        this.result =  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"><soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/><soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"><soap:Fault><soap:Code><env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value></soap:Code><soap:Reason><soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text></soap:Reason><soap:Detail><msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\"><msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\"><men:codigo>OSB_SEM_AUTENTICACAO</men:codigo><men:descricao>As credenciais informadas não são válidas</men:descricao></msf:Mensagem></msf:MSFalha></soap:Detail></soap:Fault></soap:Body></soap:Envelope>";
 
        this.resultPaginado = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"><soap:Header xmlns:pag=\"http://servicos.saude.gov.br/wsdl/mensageria/v1r0/paginacao\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/><soap:Body xmlns:pag=\"http://servicos.saude.gov.br/wsdl/mensageria/v1r0/paginacao\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"><soap:Fault><soap:Code><env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value></soap:Code><soap:Reason><soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text></soap:Reason><soap:Detail><msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\"><msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\"><men:codigo>OSB_SEM_AUTENTICACAO</men:codigo><men:descricao>As credenciais informadas não são válidas</men:descricao></msf:Mensagem></msf:MSFalha></soap:Detail></soap:Fault></soap:Body></soap:Envelope>";
        
        this.cnes = 7604041;
    }
    
    @After
    public void tearDown() {
        this.cnes = 0;
    }

    /**
     * Test of consultarPosicaoEstoquePorCNES method, of class ConnectionBsus.
     */
    @Test
    public void testConsultarPosicaoEstoquePorCNES() {
        assertEquals(instance.consultarPosicaoEstoquePorCNES(cnes), result);
    }

    /**
     * Test of consultarPosicaoEstoquePorCNESPrincipioAtivo method, of class ConnectionBsus.
     */
    @Test
    public void testConsultarPosicaoEstoquePorCNESPrincipioAtivo() {
        String principio = "Principio Ativo";
        assertEquals(instance.consultarPosicaoEstoquePorCNESPrincipioAtivo(cnes, principio), result);
        
    }

    /**
     * Test of consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado method, of class ConnectionBsus.
     */
    @Test
    public void testConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado() {   
        String principio = "Principio Ativo";
        int posicaoInicio = 0;
        int qtdRegistrosPagina = 10;
        int qtdRegistros = 10;

        String response = instance.consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(cnes, principio, posicaoInicio, qtdRegistrosPagina, qtdRegistros);
        assertEquals(response, resultPaginado);    
    }

    /**
     * Test of consultarProdutoPorCNESDispensacao method, of class ConnectionBsus.
     */
    @Test
    public void testConsultarProdutoPorCNESDispensacao() {
        assertEquals(instance.consultarProdutoPorCNESDispensacao(cnes), result);    
    }

    /**
     * Test of consultarProdutoPorCNESDispensacaoPaginado method, of class ConnectionBsus.
     */
    @Test
    public void testConsultarProdutoPorCNESDispensacaoPaginado() {
       
        int posicaoInicio = 0;
        int qtdRegistrosPagina = 10;
        int qtdRegistros = 10;

        String response = instance.consultarProdutoPorCNESDispensacaoPaginado(cnes, posicaoInicio, qtdRegistrosPagina, qtdRegistros);
        assertEquals(response, resultPaginado);            
    }
    
}
