package br.ufg.inf.horus.interfaceh;

import br.ufg.inf.horus.implementation.Barramento;
import br.ufg.inf.horus.implementation.Connection;

/**
 * Instancia os serviços disponíveis.
 * @author Pedro
 */
public class BsusFactory {

    private static final String PREFIXO = "[ERRO]";

    /**
     * Gera um Barramento responsável pelo acesso aos serviços.
     * @param classNameConnection Nome da classe que implementa a interface Connection.
     * @param classNameBarramento Nome da classe que implementa a interface Barramento.
     * @param log Objeto que implementa a interface Log.
     * @param url URL referente ao serviço.
     * @param usuario Identificador de acesso.
     * @param senha Senha de acesso.
     * @return Barramento responsável pelo acesso aos serviços.
     * @throws BsusException
     */
    public static Barramento createBarramento(String classNameConnection,
            String classNameBarramento, Log log, String url,
            String usuario, String senha) throws BsusException {

        Connection c = (Connection) criaInstancia(classNameConnection, log);
        c.setLog(log);
        c.setCredential(usuario, senha);
        c.setURL(url);

        Barramento b = (Barramento) criaInstancia(classNameBarramento, log);
        b.setConnection(c);
        b.setLog(log);

        return b;
    }

    /**
     * Gera, se existir, o objeto conforme o nome definido pelo usuário.
     * @param className Nome da classe a ser gerada.
     * @param log Interface para exibição de Log's.
     * @return O objeto do tipo definido pelo usuário.
     * @throws BsusException 
     */
    private static Object criaInstancia(String className, Log log)
            throws BsusException {

        try {
            Class clazz = Class.forName(className);
            return clazz.newInstance();

        } catch (ClassNotFoundException ex) {
            final String CLASSNAMEERROR = PREFIXO
                    + "A classe " + className + " não foi encontrada.";
            log.erro(CLASSNAMEERROR);
            throw new BsusException(CLASSNAMEERROR, ex);

        } catch (InstantiationException ex) {
            final String INSTANTIATIONERROR = PREFIXO
                    + "Não foi possível instanciar a classe " + className + ".";
            log.erro(INSTANTIATIONERROR);
            throw new BsusException(INSTANTIATIONERROR, ex);

        } catch (IllegalAccessException ex) {
            final String ILLEGALACCESSERROR = PREFIXO
                    + "Não foi possível inicializar o construtor da classe "
                    + className + ".";
            log.erro(ILLEGALACCESSERROR);
            throw new BsusException(ILLEGALACCESSERROR, ex);
        }
    }
}
