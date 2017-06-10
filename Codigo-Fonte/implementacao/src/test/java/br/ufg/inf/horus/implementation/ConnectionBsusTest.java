/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

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

    public ConnectionBsusTest() {
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
        this.result = "Uma ou mais regras negociais foram violadas, "
                + "verifique a lista de erros.\n" 
                + "OSB_SEM_AUTENTICACAO\n"
                + "As credenciais informadas não são válidas";
        
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
        assertEquals(instance.consultarPosicaoEstoquePorCNESPrincipioAtivo(cnes, principio), result);

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

        String response = instance.consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(cnes, principio, posicaoInicio, qtdRegistrosPagina, qtdRegistros);
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

        String response = instance.consultarProdutoPorCNESDispensacaoPaginado(cnes, posicaoInicio, qtdRegistrosPagina, qtdRegistros);
        assertEquals(response, result);
    }

}
