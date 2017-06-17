/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.model;

import br.ufg.inf.horus.implementation.service.Bsus;
import br.ufg.inf.horus.util.model.Log;

/**
 * Interface responsável por definir os métodos do serviço a serem utilizados
 * pela aplicação-usuário.
 *
 * @see Bsus
 * @author Pedro
 */
public interface Barramento {

    /**
     * Define uma Connection.
     *
     * @see Bsus
     * @param connection Connection a ser definida.
     */
    void setConnection(Connection connection);

    /**
     * Define uma estrutura para exibição de Log's.
     *
     * @see Bsus
     * @param log Estrutura de log a ser definida.
     */
    void setLog(Log log);

    /**
     * Obtem as posições nos estoques pelo número de cnes.
     *
     * @see Bsus
     * @param cnes Número de cnes.
     * @return A posição no estoque do referido produto.
     */
    String obterEstoquePorCNES(int cnes);

    /**
     * Obtem as posições nos estoques pelo número de cnes e princípio ativo.
     *
     * @see Bsus
     * @param cnes Número de cnes.
     * @param principio Tipo de princípio ativo.
     * @return A posição do estoque do referido produto.
     */
    String obterEstoquePorCNESEPrincipio(int cnes, String principio);

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
    String obterEstoquePorCNESEPrincipioPaginado(int cnes, String principio,
            int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);

    /**
     * Obtem as posições nos estoques, bem como os dados do produto referentes
     * ao número de cnes.
     *
     * @see Bsus
     * @param cnes Número de cnes.
     * @return A posição no estoque e os dados do referido produto.
     */
    String obterDadosEEstoquePorCNES(int cnes);

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
    String obterDadosEEstoquePorCNESPaginado(int cnes, int posicaoInicio,
            int qtdRegistrosPagina, int qtdRegistros);
}
