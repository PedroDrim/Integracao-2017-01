/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.objects.LogTester;
import br.ufg.inf.horus.implementation.controller.BsusException;
import br.ufg.inf.horus.implementation.service.ConnectionBsus;
import br.ufg.inf.horus.implementation.model.Log;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Vinicius
 */
public class ConnectionBsusTest {

    private String result;
    private int cnes;
    private ConnectionBsus instance;
    private String xml;
    
    public ConnectionBsusTest() {
        instance = new ConnectionBsus();
        instance.setCredential("user", "pass");
        instance
            .setURL("https://servicos.saude.gov.br/horus/v1r0/EstoqueService");
        instance.setLog(new LogTester());
    }

    @Before
    public void setUp() {
        
        this.result = "Uma ou mais regras negociais foram violadas, "
                + "verifique a lista de erros.\n" 
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
        
        this.cnes = 7604041;
        this.xml = "<soap:Envelope"
             + " xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
"   <soap:Header"
                + " xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/"
                + "EstoqueService\"/>\n" +
"   <soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/"
                + "EstoqueService\">\n" +
"      <soap:Fault>\n" +
"         <soap:Code>\n" +
"            <env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">"
                + "env:Sender</env:Value>\n" +
"         </soap:Code>\n" +
"         <soap:Reason>\n" +
"            <soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram"
                + " violadas, verifique a lista de erros.</soap:Text>\n" +
"         </soap:Reason>\n" +
"         <soap:Detail>\n" +
"            <msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/"
                + "mensageria/falha/v5r0/msfalha\">\n" +
"               <msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/"
                + "mensageria/falha/v5r0/mensagem\">\n" +
"                  <men:descricao>As credenciais informadas não são válidas"
                + "</men:descricao>\n" +
"               </msf:Mensagem>\n" +
"            </msf:MSFalha>\n" +
"         </soap:Detail>\n" +
"      </soap:Fault>\n" +
"   </soap:Body>\n" +
"</soap:Envelope>";
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
        String request = instance.consultarPosicaoEstoquePorCNES(cnes);
        assertEquals(request, result);
    }

    /**
     * Test of consultarPosicaoEstoquePorCNESPrincipioAtivo method, of class
     * ConnectionBsus.
     */
    @Test
    public void testConsultarPosicaoEstoquePorCNESPrincipioAtivo() {
        String principio = "Principio Ativo";
        assertEquals(instance.consultarPosicaoEstoquePorCNESPrincipioAtivo(
                cnes, principio), result);

    }

    /**
     * Test of consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado method, of
     * class ConnectionBsus.
     */
    @Test
    public void testConsultarPosicaoEstoquePorCNESPrincipioAtivoPaginado() {
        String principio = "Principio Ativo";
        int posicaoInicio = 0;
        int qtdRegistrosPagina = 10;
        int qtdRegistros = 10;

        String response = 
                instance.consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(
                        cnes, principio, posicaoInicio, qtdRegistrosPagina, 
                        qtdRegistros);
        assertEquals(response, result);
    }

    /**
     * Test of consultarProdutoPorCNESDispensacao method, of class
     * ConnectionBsus.
     */
    @Test
    public void testConsultarProdutoPorCNESDispensacao() {
        String response = instance.consultarProdutoPorCNESDispensacao(cnes);
        assertEquals(response, result);
    }

    /**
     * Test of consultarProdutoPorCNESDispensacaoPaginado method, of class
     * ConnectionBsus.
     */
    @Test
    public void testConsultarProdutoPorCNESDispensacaoPaginado() {

        int posicaoInicio = 0;
        int qtdRegistrosPagina = 10;
        int qtdRegistros = 10;

        String response = instance.consultarProdutoPorCNESDispensacaoPaginado(
                cnes, posicaoInicio, qtdRegistrosPagina, qtdRegistros);
        assertEquals(response, result);
    }

    @Test(expected=BsusException.class)
    public void getErrorSaxTest(){
        instance.getError("");
    }
    
    @Test(expected=BsusException.class)
    public void getErrorParserTest(){
        instance.getError(result);
    }
    
    @Test(expected=java.lang.NullPointerException.class)
    public void getErrorXmlTest(){
        instance.getError(xml);
    }
}
