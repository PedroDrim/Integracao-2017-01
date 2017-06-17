/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.interfaceh.objects;

import br.ufg.inf.horus.util.model.Security;

/**
 * Classe que implementa a interface para obtenção das credênciais da
 * aplicação-usuário.
 *
 * @see Security
 * @author Pedro
 */
public class SecurityTester implements Security {

    /**
     * Obtem o identificador do usuário.
     *
     * @see Security
     * @return O identificador do usuário.
     */
    @Override
    public String getUser() {
        return "user";
    }

    /**
     * Obtem a senha do usuário.
     *
     * @see Security
     * @return A senha do usuário.
     */
    @Override
    public String getPassword() {
        return "senha";
    }

}
