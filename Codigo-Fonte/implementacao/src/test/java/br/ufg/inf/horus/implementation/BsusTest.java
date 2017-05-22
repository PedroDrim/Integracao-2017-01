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
    public void obterEstoquePorCNESTest() {

        Bsus bsus = new Bsus(new Connection() {
            @Override
            public String consultarPosicaoEstoquePorCNES(String username, String password, int cnes) {
                return "OK1";
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

        assertEquals(bsus.obterEstoquePorCNES("HORUS","SENHA",7604041),"OK1");
    }
    
    @Test
    public void obterEstoquePorCNESEPrincipioText() {

        Bsus bsus = new Bsus(new Connection() {
            @Override
            public String consultarPosicaoEstoquePorCNES(String username, String password, int cnes) {
                return null;
            }

            @Override
            public String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio) {
                return "OK2";
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

        assertEquals(bsus.obterEstoquePorCNESEPrincipio("HORUS","SENHA",7604041,"PRINCIPIO"),"OK2");
    }
    
    @Test
    public void obterEstoquePorCNESEPrincipioPaginadoText() {

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
                return "OK3";
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

        assertEquals(bsus.obterEstoquePorCNESEPrincipioPaginado("HORUS","SENHA",7604041,"PRINCIPIO",1111,2222,3333),"OK3");
    }
    
    @Test
    public void obterDadosEEstoquePorCNESText() {

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
                return "OK4";
            }

            @Override
            public String consultarProdutoPorCNESDispensacaoPaginado(String username, String password, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
                return null;
            }
        });

        assertEquals(bsus.obterDadosEEstoquePorCNES("HORUS","SENHA",7604041),"OK4");
    }
    
    @Test
    public void obterDadosEEstoquePorCNESPaginadoText() {

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
                return "OK5";
            }
        });

        assertEquals(bsus.obterDadosEEstoquePorCNESPaginado("HORUS","SENHA",7604041, 1111, 2222, 3333),"OK5");
    }
}

