/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.interfaceh;

import br.ufg.inf.horus.implementation.controller.BsusException;
import br.ufg.inf.horus.implementation.model.Log;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 * Classe de Teste unitário da classe BsusFactory.
 *
 * @see BsusFactory
 * @author Pedro
 */
public class BsusFactorytest {

    /**
     * Caminho das classes externas.
     */
    private final String PACKAGE;

    /**
     * Objeto resposável por exibir Log's.
     *
     * @see Log
     */
    private Log log;

    public BsusFactorytest() {
        this.PACKAGE = "br.ufg.inf.horus.implementation.service";
        this.log = new Log() {
            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        };
    }

    @Test
    public void createBarramentoCorretoTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".Bsus";
        String classNameConnection = this.PACKAGE + ".ConnectionBsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        String usuario = "HORUS";
        String senha = "HORUS";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, classNameBarramento,
                this.log, url, usuario, senha
        ));
    }

    @Test(expected = BsusException.class)
    public void createBarramentoExceptionTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".FalsoBsus";
        String classNameConnection = this.PACKAGE + ".FalsoConnectionBsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        String usuario = "HORUS";
        String senha = "HORUS";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, classNameBarramento,
                this.log, url, usuario, senha
        ));
    }

}
