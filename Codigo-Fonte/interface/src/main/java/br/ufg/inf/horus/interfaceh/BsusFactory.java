/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.interfaceh;

import br.ufg.inf.horus.implementation.Connection;

/**
 *
 * @author aluno
 */
public class BsusFactory{
    
    public static Connection createConnection(String className) throws Exception {
        Class clazz = Class.forName(className);
        return (Connection) clazz.newInstance();        
    }
}
