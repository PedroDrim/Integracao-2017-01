/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.implementation.controller.FileResources;
import br.ufg.inf.horus.implementation.objects.LogTester;
import br.ufg.inf.horus.util.validations.BsusException;
import br.ufg.inf.horus.implementation.service.CircuitBreaker;
import br.ufg.inf.horus.util.model.Log;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

/**
 * Teste unitário do Circuit Breaker.
 *
 * @see CircuitBreaker
 * @author Pedro
 */
public class CircuitBreakerTest {

    /**
     * Objeto responsável por retornar o conteudo dos arquivos internos .xml.
     */
    private FileResources fileResources;

    /**
     * Cabeçalho da requisição.
     */
    private String header;

    /**
     * Objeto resposável por exibir Log's.
     *
     * @see Log
     */
    private Log log;

    /**
     * Construtor da classe de testes.
     */
    public CircuitBreakerTest() {
        this.log = new LogTester();
        this.fileResources = new FileResources("Usuario", "Senha", log);
        this.header = this.fileResources
                .consultarPosicaoEstoquePorCNES(1234567);
    }

    /**
     * Caso de teste responsável por testar a classe CicruitBreaker de modo
     * assincrono.
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void singleAsynchronousTest() throws InterruptedException,
            ExecutionException {

        String destination
                = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";

        CircuitBreaker circuitBreaker
                = new CircuitBreaker(header, destination, log);

        Future<String> asynchrnous = circuitBreaker.queue();
        String response = asynchrnous.get();
        String esperado = fileResources.resposta();
        assertEquals(response, esperado);
    }

    /**
     * Caso de teste responsável por testar a classe CicruitBreaker de modo
     * sincrono.
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test
    public void singleSynchronousTest() throws InterruptedException,
            ExecutionException {

        String destination
                = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";

        CircuitBreaker circuitBreaker
                = new CircuitBreaker(header, destination, log);

        String response = circuitBreaker.execute();

        String esperado = fileResources.resposta();
        assertEquals(response, esperado);
    }

    /**
     * Caso de teste responsável por testar a classe CicruitBreaker quando o
     * parametro 'header' é vazio.
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test(expected = BsusException.class)
    public void headerIsNullTest() throws InterruptedException,
            ExecutionException {

        String destination
                = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";

        CircuitBreaker circuitBreaker
                = new CircuitBreaker(null, destination, log);

        Future<String> asynchrnous = circuitBreaker.queue();
        String response = asynchrnous.get();

        String esperado = fileResources.resposta();
        assertEquals(response, esperado);
    }

    /**
     * Caso de teste responsável por testar a classe CicruitBreaker quando o
     * parametro 'destination' é vazio.
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    @Test(expected = BsusException.class)
    public void destinationIsNullTest() throws InterruptedException, ExecutionException {

        CircuitBreaker circuitBreaker
                = new CircuitBreaker(header, null, log);

        Future<String> asynchrnous = circuitBreaker.queue();
        String response = asynchrnous.get();

        String esperado = fileResources.resposta();
        assertEquals(response, esperado);
    }

    /**
     * Caso de teste responsável por testar a classe CicruitBreaker quando o
     * parametro 'log' é vazio (deverá permitir).
     *
     * @throws InterruptedException
     * @throws ExecutionException
     */
    public void logIsNullTest() throws InterruptedException,
            ExecutionException {

        String destination
                = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";

        CircuitBreaker circuitBreaker
                = new CircuitBreaker(header, destination, null);

        Future<String> asynchrnous = circuitBreaker.queue();
        String response = asynchrnous.get();

        String esperado = fileResources.resposta();
        assertEquals(response, esperado);
    }
}
