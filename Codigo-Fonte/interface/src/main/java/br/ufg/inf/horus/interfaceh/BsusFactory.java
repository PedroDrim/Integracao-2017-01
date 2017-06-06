/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.interfaceh;

import br.ufg.inf.horus.implementation.Barramento;
import br.ufg.inf.horus.implementation.Connection;

/**
 *
 * @author aluno
 */
public class BsusFactory {

    private static final String PREFIXO = "[ERRO]";
    
    public static Barramento createBarramento(String classNameConnection,
            String classNameBarramento, Log log, String url,
            String usuario, String senha) throws BsusException {

        Connection c = (Connection) criaInstancia(classNameConnection, log);
        Barramento b = (Barramento) criaInstancia(classNameBarramento, log);
        b.setConnection(c);
        b.setLog(log);
        b.setCredenntial(usuario, senha);
        b.setURL(url);
        
        return b;
    }

    private static Object criaInstancia(String className, Log log) throws BsusException {

        try {
            Class clazz = Class.forName(className);
            return (Barramento) clazz.newInstance();
            
        } catch (ClassNotFoundException ex) {
            final String CLASSNAMEERROR = PREFIXO + "A classe " + className + " não foi encontrada.";
            log.erro(CLASSNAMEERROR);
            throw new BsusException(CLASSNAMEERROR, ex);
            
        } catch (InstantiationException ex) {
            final String INSTANTIATIONERROR = PREFIXO + "A classe " + className + " não foi encontrada.";
            log.erro(INSTANTIATIONERROR);
            throw new BsusException(INSTANTIATIONERROR, ex);
            
        } catch (IllegalAccessException ex) {
            log.erro(ex.getMessage());
            throw new BsusException(className, ex);
        }
    }
}
