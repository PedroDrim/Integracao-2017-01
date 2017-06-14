/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.interfaceh;

import br.ufg.inf.horus.interfaceh.objects.LogTester;
import br.ufg.inf.horus.implementation.controller.BsusException;
import br.ufg.inf.horus.implementation.model.Log;
import br.ufg.inf.horus.interfaceh.objects.SecurityTester;
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

    /**
     * Construtor publico da classe de testes de unidade
     *
     * @see BsusFactory
     */
    public BsusFactorytest() {
        this.PACKAGE = "br.ufg.inf.horus.implementation.service";
        this.log = new LogTester();
    }

    /**
     * Teste de unidade funcional do barramento.
     *
     * @see BsusFactory
     */
    @Test
    public void createBarramentoCorretoTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".Bsus";
        String classNameConnection = this.PACKAGE + ".ConnectionBsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        String usuario = "HORUS";
        String senha = "HORUS";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, classNameBarramento,
                url, usuario, senha, this.log
        ));
    }

    /**
     * Teste de unidade do barramento quando nome das classes estão errados.
     *
     * @see BsusFactory
     */
    @Test(expected = BsusException.class)
    public void wrongClassNAmeTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".FalsoBsus";
        String classNameConnection = this.PACKAGE + ".FalsoConnectionBsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        String usuario = "HORUS";
        String senha = "HORUS";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, classNameBarramento,
                url, usuario, senha, this.log
        ));
    }

    /**
     * Teste de unidade do barramento quando log é 'null' (deverá passar).
     *
     * @see BsusFactory
     */
    @Test
    public void logIsNullTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".Bsus";
        String classNameConnection = this.PACKAGE + ".ConnectionBsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        String usuario = "HORUS";
        String senha = "HORUS";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, classNameBarramento,
                url, usuario, senha, null
        ));
    }

    /**
     * Teste de unidade do barramento quando o nome do barramento é null.
     *
     * @see BsusFactory
     */
    @Test(expected = BsusException.class)
    public void nameBarramentoIsNullTest() throws BsusException {
        String classNameConnection = this.PACKAGE + ".ConnectionBsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        String usuario = "HORUS";
        String senha = "HORUS";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, null,
                url, usuario, senha, log
        ));
    }

    /**
     * Teste de unidade do barramento quando o nome da connection é null.
     *
     * @see BsusFactory
     */
    @Test(expected = BsusException.class)
    public void nameConnectionIsNullTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".Bsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        String usuario = "HORUS";
        String senha = "HORUS";

        assertNotNull(BsusFactory.createBarramento(
                null, classNameBarramento,
                url, usuario, senha, log
        ));
    }

    /**
     * Teste de unidade do barramento quando a url é null.
     *
     * @see BsusFactory
     */
    @Test(expected = BsusException.class)
    public void urlIsNullTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".Bsus";
        String classNameConnection = this.PACKAGE + ".ConnectionBsus";
        String usuario = "HORUS";
        String senha = "HORUS";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, classNameBarramento,
                null, usuario, senha, this.log
        ));
    }

    /**
     * Teste de unidade do barramento quando o usuário é null.
     *
     * @see BsusFactory
     */
    @Test(expected = BsusException.class)
    public void usuarioIsNullTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".Bsus";
        String classNameConnection = this.PACKAGE + ".ConnectionBsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        String senha = "HORUS";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, classNameBarramento,
                url, null, senha, this.log
        ));
    }

    /**
     * Teste de unidade do barramento quando a senha é null.
     *
     * @see BsusFactory
     */
    @Test(expected = BsusException.class)
    public void senhaIsNullTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".Bsus";
        String classNameConnection = this.PACKAGE + ".ConnectionBsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        String usuario = "HORUS";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, classNameBarramento,
                url, usuario, null, this.log
        ));
    }

    /**
     * Teste de unidade do barramento quando existe uma interface de segurança
     * implementada corretamente.
     *
     * @see BsusFactory
     */
    @Test
    public void corretoSecurityTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".Bsus";
        String classNameConnection = this.PACKAGE + ".ConnectionBsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, classNameBarramento,
                url, new SecurityTester(), this.log
        ));
    }

    /**
     * Teste de unidade do barramento quando a interface de segurança é null.
     *
     * @see BsusFactory
     */
    @Test(expected = BsusException.class)
    public void securityIsNullTest() throws BsusException {
        String classNameBarramento = this.PACKAGE + ".Bsus";
        String classNameConnection = this.PACKAGE + ".ConnectionBsus";
        String url = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";

        assertNotNull(BsusFactory.createBarramento(
                classNameConnection, classNameBarramento,
                url, null, this.log
        ));
    }
}
