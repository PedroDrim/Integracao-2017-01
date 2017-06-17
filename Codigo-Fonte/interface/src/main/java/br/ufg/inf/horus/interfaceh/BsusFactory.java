/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.interfaceh;

import br.ufg.inf.horus.util.validations.BsusException;
import br.ufg.inf.horus.util.validations.BsusValidator;
import br.ufg.inf.horus.implementation.model.Barramento;
import br.ufg.inf.horus.implementation.model.Connection;
import br.ufg.inf.horus.util.model.Log;
import br.ufg.inf.horus.util.model.Security;

/**
 * Instancia os serviços disponíveis.
 *
 * @author Pedro
 */
public class BsusFactory {

    /**
     * Gera um Barramento responsável pelo acesso aos serviços.
     *
     * @param classNameConnection Nome da classe que implementa a interface
     * Connection.
     * @param classNameBarramento Nome da classe que implementa a interface
     * Barramento.
     * @param url URL referente ao serviço.
     * @param usuario Identificador de acesso.
     * @param senha Senha de acesso.
     * @param log Objeto que implementa a interface Log (Opcional).
     * @return Barramento responsável pelo acesso aos serviços.
     * @throws BsusException
     */
    public static Barramento createBarramento(String classNameConnection,
            String classNameBarramento, String url,
            String usuario, String senha, Log log) throws BsusException {

        BsusValidator.verifyNull(classNameBarramento, "classNameBarramento",
                log);
        BsusValidator.verifyNull(classNameConnection, "classNameConnection",
                log);
        BsusValidator.verifyNull(url, "url", log);
        BsusValidator.verifyNull(usuario, "usuario", log);
        BsusValidator.verifyNull(senha, "senha", log);

        Connection c = (Connection) criaInstancia(classNameConnection, log);
        c.setLog(log);
        c.setCredential(usuario, senha);
        c.setURL(url);

        return buildBarramento(classNameBarramento, log, c);
    }

    /**
     * Gera um Barramento responsável pelo acesso aos serviços.
     *
     * @param classNameConnection Nome da classe que implementa a interface
     * Connection.
     * @param classNameBarramento Nome da classe que implementa a interface
     * Barramento.
     * @param url URL referente ao serviço.
     * @param security Objeto responsável pelas credênciais do usuário.
     * @param log Objeto que implementa a interface Log (Opcional).
     * @return Barramento responsável pelo acesso aos serviços.
     * @throws BsusException
     */
    public static Barramento createBarramento(String classNameConnection,
            String classNameBarramento, String url,
            Security security, Log log) throws BsusException {

        BsusValidator.verifyNull(classNameBarramento, "classNameBarramento",
                log);
        BsusValidator.verifyNull(classNameConnection, "classNameConnection",
                log);
        BsusValidator.verifyNull(url, "url", log);
        BsusValidator.verifyNull(security, "security", log);

        Connection c = (Connection) criaInstancia(classNameConnection, log);
        c.setLog(log);
        c.setCredential(security);
        c.setURL(url);

        return buildBarramento(classNameBarramento, log, c);
    }

    /**
     * Método intermediário para criação de um barramento.
     *
     * @param classNameBarramento Nome da classe que implementa a interface
     * Barramento.
     * @param log Objeto que implementa a interface Log (Opcional).
     * @param c Objeto que implementa a interface Connecion.
     * @return O objeto que implementa a interface barramento.
     * @throws BsusException
     */
    private static Barramento buildBarramento(String classNameBarramento,
            Log log, Connection c) throws BsusException {
        
        BsusValidator.verifyNull(classNameBarramento, "classNameBarramento",
                log);
        BsusValidator.verifyNull(c, "c", log);
        
        Barramento b = (Barramento) criaInstancia(classNameBarramento, log);
        b.setConnection(c);
        b.setLog(log);
        return b;
    }

    /**
     * Gera, se existir, o objeto conforme o nome definido pelo usuário.
     *
     * @param className Nome da classe a ser gerada.
     * @param log Interface para exibição de Log's.
     * @return O objeto do tipo definido pelo usuário.
     * @throws BsusException Excessão padrão do Bsus.
     */
    private static Object criaInstancia(String className, Log log)
            throws BsusException {

        BsusValidator.verifyNull(className, "className", log);
        Object object = null;

        try {
            Class clazz = Class.forName(className);
            object = clazz.newInstance();

        } catch (ClassNotFoundException ex) {
            final String CLASSNAMEERROR = "A classe " + className
                    + " não foi encontrada.";
            BsusValidator.catchException(ex, CLASSNAMEERROR, log);
        } catch (InstantiationException ex) {
            final String INSTANTIATIONERROR
                    = "Não foi possível instanciar a classe " + className + ".";
            BsusValidator.catchException(ex, INSTANTIATIONERROR, log);
        } catch (IllegalAccessException ex) {
            final String ILLEGALACCESSERROR
                    = "Não foi possível inicializar o construtor da classe "
                    + className + ".";
            BsusValidator.catchException(ex, ILLEGALACCESSERROR, log);
        }

        return object;
    }
}
