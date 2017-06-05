/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation;

/**
 *
 * @author Pedro
 */
public interface Barramento {
    
    String obterEstoquePorCNES(int cnes);
    String obterEstoquePorCNESEPrincipio(int cnes,String principio);
    String obterEstoquePorCNESEPrincipioPaginado(int cnes,String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);
    String obterDadosEEstoquePorCNES(int cnes);
    String obterDadosEEstoquePorCNESPaginado(int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);
}
