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
    
    public BsusTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void hello() {

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
                return null;
            }
        });

        assertEquals(bsus.obterEstoquePorCNES(7604041),"OK");
    }
}

