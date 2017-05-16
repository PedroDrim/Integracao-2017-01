/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

/**
 *
 * @author aluno
 */
public interface Connection {

    String consultarPosicaoEstoquePorCNES(String username, String password, int cnes);
    String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio);
    String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(String username, String password, int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);
    String consultarProdutoPorCNESDispensacao(String username, String password, int cnes);
    String consultarProdutoPorCNESDispensacaoPaginado(String username, String password, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);
    //String fazUmaRequisicao(String argumento);

    
}
