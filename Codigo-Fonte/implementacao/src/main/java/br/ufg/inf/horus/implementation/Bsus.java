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
    /**
     * Método que executa remotamente o serviço e trata a resposta.
     * @param username Usuário do sistema Horus.
     * @param senha Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterEstoquePorCNES(String username, String senha, int cnes) {
        String xml = connection.consultarPosicaoEstoquePorCNES(username, senha, cnes);

        //Requisita a execucao remota do servico
        //Recebe a resposta XML e converte a resposta XML na saída conforme o negocio
        //Parser do xml
        return xml;
    }

    /**
     * Método que executa remotamente o serviço e trata a resposta.
     * @param username Usuário do sistema Horus.
     * @param senha Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @param principio Princípio Ativo do medicamento.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterEstoquePorCNESEPrincipio(String username, String senha, int cnes, String principio) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método que executa remotamente o serviço e trata a resposta. 
     * @param username Usuário do sistema Horus.
     * @param senha Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @param principio Princípio Ativo do medicamento.
     * @param posicaoInicio Posição de início da listagem.
     * @param qtdRegistrosPagina Quantidade de registros por pagina.
     * @param qtdRegistros Quantidade de registros que serão retornados.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterEstoquePorCNESEPrincipioPaginado(String username, String senha, int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método que executa remotamente o serviço e trata a resposta. 
     * @param username Usuário do sistema Horus.
     * @param senha Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterDadosEEstoquePorCNES(String username, String senha, int cnes) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Método que executa remotamente o serviço e trata a resposta. 
     * @param username Usuário do sistema Horus.
     * @param senha Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @param posicaoInicio Posição de início da listagem.
     * @param qtdRegistrosPagina Quantidade de registros por pagina.
     * @param qtdRegistros Quantidade de registros que serão retornados.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterDadosEEstoquePorCNESPaginado(String username, String senha, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
