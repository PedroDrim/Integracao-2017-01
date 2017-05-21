package br.ufg.inf.horus.implementation;

/**
 *
 * @author Vinicius
 * Classe Bsus que implementa e requisita a execução remota, recebe a resposta XML e converte-a.
 */
public class Bsus implements Barramento {

    private Connection connection;
    /**
     * Construtor.
     * @param connection Interface com os serviços.
     */
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
