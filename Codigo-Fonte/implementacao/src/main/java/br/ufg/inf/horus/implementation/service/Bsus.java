/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.service;

import br.ufg.inf.horus.implementation.controller.XmlParser;
import br.ufg.inf.horus.implementation.model.Barramento;
import br.ufg.inf.horus.implementation.model.Connection;
import br.ufg.inf.horus.implementation.model.Log;

/**
 * Classe responsável por implementar e requerir uma execução remota além de
 * receber uma resposta XML, convertendo-a.
 *
 * @see Barramento
 * @author Vinicius
 */
public class Bsus implements Barramento {

    /**
     * Objeto responsável por realizar a conexão com os serviços.
     *
     * @see Connection
     */
    private Connection connection;

    /**
     * Objeto resposável por exibir Log's.
     *
     * @see Log
     */
    private Log log;

    /**
     * Define uma Connection.
     *
     * @see Bsus
     * @param connection Connection a ser definida.
     */
    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Define uma estrutura para exibição de Log's.
     *
     * @see Bsus
     * @param log Estrutura de log a ser definida.
     */
    @Override
    public void setLog(Log log) {
        this.log = log;
    }

    /**
     * Obtem as posições nos estoques pelo número de cnes.
     *
     * @see Bsus
     * @param cnes Número de cnes.
     * @return A posição no estoque do referido produto.
     */
    @Override
    public String obterEstoquePorCNES(int cnes) {
        String xml = connection.consultarPosicaoEstoquePorCNES(cnes
        );
        XmlParser parser = new XmlParser(log);
        String retorno = parser.getMessage(xml);
        return retorno;
    }

    /**
     * Obtem as posições nos estoques pelo número de cnes e princípio ativo.
     *
     * @see Bsus
     * @param cnes Número de cnes.
     * @param principio Tipo de princípio ativo.
     * @return A posição do estoque do referido produto.
     */
    @Override
    public String obterEstoquePorCNESEPrincipio(int cnes, String principio) {
        String xml = connection.consultarPosicaoEstoquePorCNESPrincipioAtivo(
                cnes, principio
        );
        XmlParser parser = new XmlParser(log);
        String retorno = parser.getMessage(xml);
        return retorno;
    }

    /**
     * Obtem as posições nos estoques pelo número de cnes, princípio ativo e
     * dados de paginação.
     *
     * @see Bsus
     * @param cnes Número de cnes.
     * @param principio Tipo de princípio ativo.
     * @param posicaoInicio Posição inicial.
     * @param qtdRegistrosPagina Quantidade de registros por página.
     * @param qtdRegistros Quantidade de registros ao todo.
     * @return A posição no estoque do referido produto.
     */
    @Override
    public String obterEstoquePorCNESEPrincipioPaginado(int cnes,
            String principio, int posicaoInicio, int qtdRegistrosPagina,
            int qtdRegistros) {
        String xml = connection.consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(
                cnes, principio, posicaoInicio, qtdRegistrosPagina, qtdRegistros
        );
        XmlParser parser = new XmlParser(log);
        String retorno = parser.getMessage(xml);
        return retorno;
    }

    /**
     * Obtem as posições nos estoques, bem como os dados do produto referentes
     * ao número de cnes.
     *
     * @see Bsus
     * @param cnes Número de cnes.
     * @return A posição no estoque e os dados do referido produto.
     */
    @Override
    public String obterDadosEEstoquePorCNES(int cnes) {
        String xml = connection.consultarProdutoPorCNESDispensacao(cnes
        );
        XmlParser parser = new XmlParser(log);
        String retorno = parser.getMessage(xml);
        return retorno;
    }

    /**
     * Obtem as posições nos estoques, bem como os dados do produto referentes
     * ao número de cnes e dados de paginação.
     *
     * @see Bsus
     * @param cnes Número de cnes.
     * @param posicaoInicio Posição inicial.
     * @param qtdRegistrosPagina Quantidade de registros por página.
     * @param qtdRegistros Quantidade de registros ao todo.
     * @return A posição no estoque e os dados do referido produto.
     */
    @Override
    public String obterDadosEEstoquePorCNESPaginado(int cnes, int posicaoInicio,
            int qtdRegistrosPagina, int qtdRegistros) {
        String xml = connection.consultarProdutoPorCNESDispensacaoPaginado(
                cnes, posicaoInicio, qtdRegistrosPagina, qtdRegistros
        );
        XmlParser parser = new XmlParser(log);
        String retorno = parser.getMessage(xml);
        return retorno;
    }

}
