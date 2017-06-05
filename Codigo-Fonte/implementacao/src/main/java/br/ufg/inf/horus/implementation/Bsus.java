package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.interfaceh.Log;
import br.ufg.inf.horus.interfaceh.Security;

/**
 *
 * @author Vinicius
 * Classe Bsus que implementa e requisita a execução remota, recebe a resposta XML e converte-a.
 */
public class Bsus implements Barramento {
    
    private Connection connection;
    private Security security;
    private Log log;
    
    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void setSecurity(Security security) {
        this.security = security;
    }
    
    @Override
    public void setLog(Log log) {
        this.log = log;
    }
    
    /**
     * Método que executa remotamente o serviço e trata a resposta.
     * @param username Usuário do sistema Horus.
     * @param senha Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterEstoquePorCNES(int cnes) {
        String xml = connection.consultarPosicaoEstoquePorCNES(
                this.security.getUser(), this.security.getPassword(), cnes
        );
        XmlParser parser = new XmlParser();
        String retorno = parser.getMessage(xml);
        return retorno;
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
    public String obterEstoquePorCNESEPrincipio(int cnes, String principio) {
        String xml = connection.consultarPosicaoEstoquePorCNESPrincipioAtivo(
                this.security.getUser(), this.security.getPassword(), cnes, principio
        );
        XmlParser parser = new XmlParser();
        String retorno = parser.getMessage(xml);
        return retorno;
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
    public String obterEstoquePorCNESEPrincipioPaginado(int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
        String xml = connection.consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(
                this.security.getUser(), this.security.getPassword(), cnes, principio, posicaoInicio, qtdRegistrosPagina, qtdRegistros
        );
        XmlParser parser = new XmlParser();
        String retorno = parser.getMessage(xml);
        return retorno;
    }

    /**
     * Método que executa remotamente o serviço e trata a resposta. 
     * @param username Usuário do sistema Horus.
     * @param senha Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterDadosEEstoquePorCNES(int cnes) {
        String xml = connection.consultarProdutoPorCNESDispensacao(
                this.security.getUser(), this.security.getPassword(), cnes
        );
        XmlParser parser = new XmlParser();
        String retorno = parser.getMessage(xml);
        return retorno;
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
    public String obterDadosEEstoquePorCNESPaginado(int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros) {
        String xml = connection.consultarProdutoPorCNESDispensacaoPaginado(
                this.security.getUser(), this.security.getPassword(), cnes, posicaoInicio, qtdRegistrosPagina, qtdRegistros
        );
        XmlParser parser = new XmlParser();
        String retorno = parser.getMessage(xml);
        return retorno;
    }
    
}
