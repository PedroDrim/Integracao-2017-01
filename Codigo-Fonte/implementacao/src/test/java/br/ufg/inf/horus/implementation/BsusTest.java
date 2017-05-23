/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author aluno
 */
public class BsusTest {
    
    private String xml;
    
    @Before
    public void setUp() {
       this.xml = "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\">\n" +
"   <soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/>\n" +
"   <soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n" +
"      <soap:Fault>\n" +
"         <soap:Code>\n" +
"            <env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value>\n" +
"         </soap:Code>\n" +
"         <soap:Reason>\n" +
"            <soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text>\n" +
"         </soap:Reason>\n" +
"         <soap:Detail>\n" +
"            <msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\">\n" +
"               <msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\">\n" +
"                  <men:codigo>OSB_SEM_AUTENTICACAO</men:codigo>\n" +
"                  <men:descricao>As credenciais informadas não são válidas</men:descricao>\n" +
"               </msf:Mensagem>\n" +
"            </msf:MSFalha>\n" +
"         </soap:Detail>\n" +
"      </soap:Fault>\n" +
"   </soap:Body>\n" +
"</soap:Envelope>";
                }
    
    @After
    public void tearDown() {
        this.xml = "";
    }

    /**
     * Caso de teste do método obterEstoquePorCNES
     */
    @Test
    public void obterEstoquePorCNESTest() {
        
        Bsus bsus = new Bsus(new Connection() {
            @Override
            public String consultarPosicaoEstoquePorCNES(String username, String password, int cnes) {
                
                return xml;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio) {
                return null;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(String username, String password, int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                return null;
            }

            @Override
            public String consultarProdutoPorCNESDispensacao(String username, String password, int cnes) {
                return null;
            }

            @Override
            public String consultarProdutoPorCNESDispensacaoPaginado(String username, String password, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                return null;
            }
        });

        assertEquals(bsus.obterEstoquePorCNES("HORUS","SENHA",7604041),"Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas");
    }
    
    /**
     * Caso de teste do método obterEstoquePorCNESEPrincipio
     */
    @Test
    public void obterEstoquePorCNESEPrincipioTest() {

        Bsus bsus = new Bsus(new Connection() {
            @Override
            public String consultarPosicaoEstoquePorCNES(String username, String password, int cnes) {    
                return null;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio) {
                
                return xml;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(String username, String password, int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                return null;
            }

            @Override
            public String consultarProdutoPorCNESDispensacao(String username, String password, int cnes) {
                return null;
            }

            @Override
            public String consultarProdutoPorCNESDispensacaoPaginado(String username, String password, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                return null;
            }
        });

        assertEquals(bsus.obterEstoquePorCNESEPrincipio("HORUS","SENHA",7604041,"PRINCIPIO"),"Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas");
    }
    
    /**
     * Caso de teste do método obterEstoquePorCNESEPrincipioPaginado
     */
    @Test
    public void obterEstoquePorCNESEPrincipioPaginadoTest() {

        Bsus bsus = new Bsus(new Connection() {
            @Override
            public String consultarPosicaoEstoquePorCNES(String username, String password, int cnes) {
                return null;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio) {
                return null;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(String username, String password, int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                
                return xml;
            }

            @Override
            public String consultarProdutoPorCNESDispensacao(String username, String password, int cnes) {
                return null;
            }

            @Override
            public String consultarProdutoPorCNESDispensacaoPaginado(String username, String password, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                return null;
            }
        });

        assertEquals(bsus.obterEstoquePorCNESEPrincipioPaginado("HORUS","SENHA",7604041,"PRINCIPIO",1111,2222,3333),"Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas");
    }
    
    /**
     * Caso de teste do método obterDadosEEstoquePorCNES
     */
    @Test
    public void obterDadosEEstoquePorCNESTest() {

        Bsus bsus = new Bsus(new Connection() {
            @Override
            public String consultarPosicaoEstoquePorCNES(String username, String password, int cnes) {
                return null;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio) {
                return null;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(String username, String password, int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                return null;
            }

            @Override
            public String consultarProdutoPorCNESDispensacao(String username, String password, int cnes) {
                
                return xml;
            }

            @Override
            public String consultarProdutoPorCNESDispensacaoPaginado(String username, String password, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                return null;
            }
        });

        assertEquals(bsus.obterDadosEEstoquePorCNES("HORUS","SENHA",7604041),"Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas");
    }
    
    /**
     * Caso de teste do método obterDadosEEstoquePorCNESPaginado
     */
    @Test
    public void obterDadosEEstoquePorCNESPaginadoTest() {

        Bsus bsus = new Bsus(new Connection() {
            @Override
            public String consultarPosicaoEstoquePorCNES(String username, String password, int cnes) {
                return null;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio) {
                return null;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(String username, String password, int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                return null;
            }

            @Override
            public String consultarProdutoPorCNESDispensacao(String username, String password, int cnes) {
                return null;
            }

            @Override
            public String consultarProdutoPorCNESDispensacaoPaginado(String username, String password, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                
                return xml;
            }
        });

        assertEquals(bsus.obterDadosEEstoquePorCNESPaginado("HORUS","SENHA",7604041, 1111, 2222, 3333),"Uma ou mais regras negociais foram violadas, verifique a lista de erros.\n"
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas");
    }
}

