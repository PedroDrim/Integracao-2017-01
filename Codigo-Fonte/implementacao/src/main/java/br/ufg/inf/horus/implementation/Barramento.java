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
    
    String obterEstoquePorCNES(String username,String senha,int cnes);
    String obterEstoquePorCNESEPrincipio(String username,String senha,int cnes,String principio);
    String obterEstoquePorCNESEPrincipioPaginado(String username,String senha,int cnes,String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);
    String obterDadosEEstoquePorCNES(String username,String senha,int cnes);
    String obterDadosEEstoquePorCNESPaginado(String username,String senha,int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);
}
