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
 * Classe responsável para retornar os .xml necessários.
 *
 * @author Pedro
 */
public class FileResources {

    /**
     * Objeto resposável por exibir Log's.
     *
     * @see Log
     */
    private Log log;
    
    /**
     * Identificador de acesso da aplicação-usuário.
     */
    private String usuario;
    
    /**
     * Senha de acesso da aplicação-usuário.
     */
    private String senha;

    /**
     * Construtor publico da classe.
     * @param usuario Identificador de acesso.
     * @param senha Senha de acesso.
     * @param log Estrutura de log a ser definida (pode ser 'null').
     */
    public FileResources(String usuario, String senha, Log log) {
        BsusValidator.verifyNull(usuario, "usuario", log);
        BsusValidator.verifyNull(senha, "senha", log);
        
        this.log = log;
        this.usuario = usuario;
        this.senha = senha;
    }

    /**
     * Método interno para retornar o conteudo do arquivo
     * 'consultarPosicaoEstoquePorCNES'.
     *
     * @param cnes Número de cnes.
     * @return A posição no estoque do referido produto.
     */
    public String consultarPosicaoEstoquePorCNES(
            int cnes) {
        String resposta = getXml("consultarPosicaoEstoquePorCNES");
        String output = String.format(resposta, usuario, senha, cnes);
        return output;
    }

    /**
     * Método interno para retornar o conteudo do arquivo
     * 'consultarPosicaoEstoquePorCNESPrincipioAtivo'.
     *
     * @param cnes Número de cnes.
     * @param principio Tipo de princípio ativo.
     * @return A posição do estoque do referido produto.
     */
    public String consultarPosicaoEstoquePorCNESPrincipioAtivo(
            int cnes, String principio) {
        String arquivo = "consultarPosicaoEstoquePorCNESPrincipioAtivo";
        String resposta = getXml(arquivo);
        String output = String.format(resposta, usuario, senha, cnes,
                principio);
        return output;
    }

    /**
     * Método interno para retornar o conteudo do arquivo
     * 'consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado'.
     *
     * @param cnes Número de cnes.
     * @param principio Tipo de princípio ativo.
     * @param posicaoInicio Posição inicial.
     * @param qtdRegistrosPagina Quantidade de registros por página.
     * @param qtdRegistros Quantidade de registros ao todo.
     * @return A posição no estoque do referido produto.
     */
    public String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(
            int cnes,
            String principio, int posicaoInicio, int qtdRegistrosPagina,
            int qtdRegistros) {
        String arquivo = "consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado";
        String resposta = getXml(arquivo);
        String output = String.format(resposta, usuario, senha, cnes, principio,
                posicaoInicio, qtdRegistrosPagina, qtdRegistros);
        return output;
    }

    /**
     * Método interno para retornar o conteudo do arquivo
     * 'consultarProdutoPorCNESDispensacao'.
     *
     * @param cnes Número de cnes.
     * @return A posição no estoque e os dados do referido produto.
     */
    public String consultarProdutoPorCNESDispensacao(int cnes) {
        String resposta = getXml("consultarProdutoPorCNESDispensacao");
        String output = String.format(resposta, usuario, senha, cnes);
        return output;
    }

    /**
     * Método interno para retornar o conteudo do arquivo
     * 'consultarProdutoPorCNESDispensacaoPaginado'.
     *
     * @param cnes Número de cnes.
     * @param posicaoInicio Posição inicial.
     * @param qtdRegistrosPagina Quantidade de registros por página.
     * @param qtdRegistros Quantidade de registros ao todo.
     * @return A posição no estoque e os dados do referido produto.
     */
    public String consultarProdutoPorCNESDispensacaoPaginado(int cnes,
            int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
        String resposta
                = getXml("consultarProdutoPorCNESDispensacaoPaginado");
        String output = String.format(resposta, usuario, senha, cnes,
                posicaoInicio, qtdRegistrosPagina, qtdRegistros);
        return output;
    }

    /**
     * Método interno para retornar o conteudo do arquivo 'resposta'.
     * @return O conteúdo do arquivo 'resposta'.
     */
    public String resposta() {
        String resposta = getXml("resposta");
        return resposta;
    }

    /**
     * Método interno responsável por buscar e ler um arquivo definido.
     * @param arquivo nome do arquivo a ser lido.
     * @return Conteúdo do arquivo.
     */
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
