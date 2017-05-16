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
public class Bsus implements Barramento {

    private Connection connection;
    
    public Bsus(Connection connection) {
        this.connection = connection;
    }

    @Override
    public String obterEstoquePorCNES(int cnes) {
        String xml = connection.consultarPosicaoEstoquePorCNES("HORUS","SENHA",cnes);
        //Requisita a execucao remota do servico
        //Recebe a resposta XML e converte a resposta XML na saída conforme o negocio
        //Parser do xml
        return xml;
    }
    
}
