/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.objects.ConnectionTester;
import br.ufg.inf.horus.implementation.objects.LogTester;
import br.ufg.inf.horus.util.validations.BsusException;
import br.ufg.inf.horus.implementation.service.Bsus;
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
        bsus.setLog(new LogTester());
    }

    /**
     * Caso de teste do método obterEstoquePorCNES.
     */
    @Test
    public void obterEstoquePorCNESTest() {

        bsus.setConnection(new ConnectionTester());

        String response = "Uma ou mais regras negociais foram violadas, "
                + "verifique a lista de erros.\n";
        assertEquals(bsus.obterEstoquePorCNES(7604041), response);
    }
    
    @Test
    public void cnesValidoTest(){
        bsus.setConnection(new ConnectionTester());
        assertEquals(bsus.verificaCnes(7604041),7604041);
    }
    
    @Test(expected=BsusException.class)
    public void cnesErradoMenorTest(){
        bsus.setConnection(new ConnectionTester());
        bsus.verificaCnes(01);
    }
    
     @Test(expected=BsusException.class)
    public void cnesErradoMaiorTest(){
        bsus.setConnection(new ConnectionTester());
        bsus.verificaCnes(87654321);
    }

    /**
     * Caso de teste do método obterEstoquePorCNESEPrincipio.
     */
    @Test
    public void obterEstoquePorCNESEPrincipioTest() {

        bsus.setConnection(new ConnectionTester());

        String response = "Uma ou mais regras negociais foram violadas, "
                + "verifique a lista de erros.\n";

        assertEquals(bsus.obterEstoquePorCNESEPrincipio(7604041, "PRINCIPIO"),
                response);
    }

     @Test(expected=BsusException.class)
    public void principioAtivoTest(){
        bsus.setConnection(new ConnectionTester());
        bsus.verificaPrincipio("");
    }
    
    @Test
    public void principioAtivoCorretoTest(){
        bsus.setConnection(new ConnectionTester());
        assertEquals(bsus.verificaPrincipio("Principio Ativo"),
                "Principio Ativo");
    }
    
    @Test(expected=BsusException.class)
    public void paginadoErradoTest(){
        bsus.setConnection(new ConnectionTester());
        bsus.verificaPaginado(0,-1,0);
    }
    
        @Test
    public void paginadoCorretoTest(){
        bsus.setConnection(new ConnectionTester());
        assertEquals(bsus.verificaPaginado(1,15,30),true);
    }
    
    /**
     * Caso de teste do método obterEstoquePorCNESEPrincipioPaginado
     */
    @Test
    public void obterEstoquePorCNESEPrincipioPaginadoTest() {

        bsus.setConnection(new ConnectionTester());

        String response = "Uma ou mais regras negociais foram violadas, "
                + "verifique a lista de erros.\n";
        
        assertEquals(bsus.obterEstoquePorCNESEPrincipioPaginado(7604041, 
                "PRINCIPIO", 1111, 2222, 3333), response);
    }

    /**
     * Caso de teste do método obterDadosEEstoquePorCNES
     */
    @Test
    public void obterDadosEEstoquePorCNESTest() {

        bsus.setConnection(new ConnectionTester());

        String response = "Uma ou mais regras negociais foram violadas, "
                + "verifique a lista de erros.\n";

        assertEquals(bsus.obterDadosEEstoquePorCNES(7604041), response);
    }

    /**
     * Caso de teste do método obterDadosEEstoquePorCNESPaginado
     */
    @Test
    public void obterDadosEEstoquePorCNESPaginadoTest() {

        bsus.setConnection(new ConnectionTester());

        String response = "Uma ou mais regras negociais foram violadas, "
                + "verifique a lista de erros.\n";
        
        assertEquals(bsus.obterDadosEEstoquePorCNESPaginado(
                7604041, 1111, 2222, 3333), response);
    }
}