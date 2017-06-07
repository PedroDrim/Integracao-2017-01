/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.model;

import br.ufg.inf.horus.implementation.service.ConnectionBsus;

/**
 * Interface para conexão com os serviços do Horus.
 *
 * @see ConnectionBsus
 * @author Vinicius
 */
public interface Connection {

    /**
     * Define as credenciais de acesso da aplicação-usuário ao serviço.
     *
     * @see ConnectionBsus
     * @param usuario Identificador de acesso.
     * @param senha Senha de acesso.
     */
    void setCredential(String usuario, String senha);

    /**
     * Define a url do serviço a ser utilizado.
     *
     * @see ConnectionBsus
     * @param url URL do serviço.
     */
    void setURL(String url);

    /**
     * Define uma estrutura para exibição de Log's.
     *
     * @see ConnectionBsus
     * @param log Estrutura de log a ser definida.
     */
    void setLog(Log log);

    /**
     * Método interno para consultar as posições nos estoques do produto com
     * base no cnes.
     *
     * @see ConnectionBsus
     * @param cnes Número de cnes.
     * @return A posição no estoque do referido produto.
     */
    String consultarPosicaoEstoquePorCNES(int cnes);

    /**
     * Método interno para consultar as posições nos estoques pelo número de
     * cnes e princípio ativo.
     *
     * @see ConnectionBsus
     * @param cnes Número de cnes.
     * @param principio Tipo de princípio ativo.
     * @return A posição do estoque do referido produto.
     */
    String consultarPosicaoEstoquePorCNESPrincipioAtivo(int cnes,
            String principio);

    /**
     * Método interno para consultar as posições nos estoques pelo número de
     * cnes, princípio ativo e dados de paginação.
     *
     * @see ConnectionBsus
     * @param cnes Número de cnes.
     * @param principio Tipo de princípio ativo.
     * @param posicaoInicio Posição inicial.
     * @param qtdRegistrosPagina Quantidade de registros por página.
     * @param qtdRegistros Quantidade de registros ao todo.
     * @return A posição no estoque do referido produto.
     */
    String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(int cnes,
            String principio, int posicaoInicio, int qtdRegistrosPagina,
            int qtdRegistros);

    /**
     * Método interno para consultar as posições nos estoques, bem como os dados
     * do produto referentes ao número de cnes.
     *
     * @see ConnectionBsus
     * @param cnes Número de cnes.
     * @return A posição no estoque e os dados do referido produto.
     */
    String consultarProdutoPorCNESDispensacao(int cnes);

    /**
     * Método interno para consultar as posições nos estoques, bem como os dados
     * do produto referentes ao número de cnes e dados de paginação.
     *
     * @see ConnectionBsus
     * @param cnes Número de cnes.
     * @param posicaoInicio Posição inicial.
     * @param qtdRegistrosPagina Quantidade de registros por página.
     * @param qtdRegistros Quantidade de registros ao todo.
     * @return A posição no estoque e os dados do referido produto.
     */
    String consultarProdutoPorCNESDispensacaoPaginado(int cnes,
            int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);
}
