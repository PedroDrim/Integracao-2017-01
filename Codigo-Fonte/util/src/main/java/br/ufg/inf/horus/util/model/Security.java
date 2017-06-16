/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.util.model;

/**
 * Interface para obtenção das credênciais da aplicação-usuário.
 * @author Pedro
 */
public interface Security {
    
    /**
     * Obtem o identificador do usuário.
     * @return O identificador do usuário.
     */
    String getUser();
    
    /**
     * Obtem a senha do usuário.
     * @return A senha do usuário.
     */
    String getPassword();
}
