/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.controller;

import br.ufg.inf.horus.util.model.Log;
import br.ufg.inf.horus.util.validations.BsusValidator;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Classe responsável para retornar os ,xml necessários.
 *
 * @author Pedro
 */
public class FileResources {

    private Log log;

    public FileResources(Log log) {
        this.log = log;
    }

    /**
     * Método interno para consultar as posições nos estoques do produto com
     * base no cnes.
     *
     * @see ConnectionBsus
     * @param cnes Número de cnes.
     * @return A posição no estoque do referido produto.
     */
    public String consultarPosicaoEstoquePorCNES(String usuario, String senha,
            int cnes) {
        String resposta = getXml("consultarPosicaoEstoquePorCNES");
        String output = String.format(resposta, usuario, senha,cnes);
        return output;
    }

    /**
     * Método interno para consultar as posições nos estoques pelo número de
     * cnes e princípio ativo.
     *
     * @see ConnectionBsus
     * @param cnes Número de cnes.
     * @param principio Tipo de princípio ativo.
     * @return A posição do estoque do referido produto.
     */
    public String consultarPosicaoEstoquePorCNESPrincipioAtivo(String usuario,
            String senha,int cnes, String principio) {
        String arquivo = "consultarPosicaoEstoquePorCNESPrincipioAtivo";
        String resposta = getXml(arquivo);
        String output = String.format(resposta, usuario, senha, cnes,
                principio);
        return output;
    }

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
    public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(
            String usuario, String senha,int cnes,
            String principio, int posicaoInicio, int qtdRegistrosPagina,
            int qtdRegistros) {
        String arquivo = "consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado";
        String resposta = getXml(arquivo);
        String output = String.format(resposta, usuario, senha, cnes, principio,
                posicaoInicio, qtdRegistrosPagina, qtdRegistros);
        return output;
    }

    /**
     * Método interno para consultar as posições nos estoques, bem como os dados
     * do produto referentes ao número de cnes.
     *
     * @see ConnectionBsus
     * @param cnes Número de cnes.
     * @return A posição no estoque e os dados do referido produto.
     */
    public String consultarProdutoPorCNESDispensacao(String usuario,
            String senha, int cnes) {
        String resposta = getXml("consultarProdutoPorCNESDispensacao");
        String output = String.format(resposta, usuario, senha,cnes);
        return output;
    }

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
    public String consultarProdutoPorCNESDispensacaoPaginado(String usuario, 
            String senha, int cnes,
            int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
        String resposta
                = getXml("consultarProdutoPorCNESDispensacaoPaginado");
        String output = String.format(resposta, usuario, senha, cnes,
                posicaoInicio, qtdRegistrosPagina, qtdRegistros);
        return output;
    }

    public String resposta() {
        String resposta = getXml("resposta");

        return resposta;
    }

    private String getXml(String arquivo) {

        BsusValidator.verifyNull(arquivo, "arquivo", log);
        StringBuilder builder = new StringBuilder();

        BufferedReader br;
        String PACKAGE = "./src/main/resources/";

        try {
            br = new BufferedReader(
                    new FileReader(PACKAGE + arquivo));
            String linha;

            while ((linha = br.readLine()) != null) {
                builder.append(linha);
            }
        } catch (IOException ex) {
            String message = "Não foi possivel abrir o arquivo '"
                    + arquivo + "'";
            BsusValidator.catchException(ex, message, log);
        }

        return builder.toString();
    }
}
