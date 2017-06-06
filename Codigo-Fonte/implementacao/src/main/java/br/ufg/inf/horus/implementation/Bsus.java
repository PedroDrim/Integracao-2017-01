package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.interfaceh.Log;

/**
 *
 * @author Vinicius
 * Classe Bsus que implementa e requisita a execução remota, recebe a resposta
 * XML e converte-a.
 */
public class Bsus implements Barramento {
    
    private Connection connection;
    private Log log;
    
    @Override
    public void setConnection(Connection connection) {
        this.connection = connection;
    }
    
    @Override
    public void setLog(Log log) {
        this.log = log;
    }
        
    /**
     * Método que executa remotamente o serviço e trata a resposta.
     * @param cnes Número do CNES para busca, 7 posições.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterEstoquePorCNES(int cnes) {
        String xml = connection.consultarPosicaoEstoquePorCNES(cnes
        );
        XmlParser parser = new XmlParser();
        String retorno = parser.getMessage(xml);
        return retorno;
    }

    /**
     * Método que executa remotamente o serviço e trata a resposta.
     * @param cnes Número do CNES para busca, 7 posições.
     * @param principio Princípio Ativo do medicamento.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterEstoquePorCNESEPrincipio(int cnes, String principio) {
        String xml = connection.consultarPosicaoEstoquePorCNESPrincipioAtivo(
                cnes, principio
        );
        XmlParser parser = new XmlParser();
        String retorno = parser.getMessage(xml);
        return retorno;
    }

    /**
     * Método que executa remotamente o serviço e trata a resposta. 
     * @param cnes Número do CNES para busca, 7 posições.
     * @param principio Princípio Ativo do medicamento.
     * @param posicaoInicio Posição de início da listagem.
     * @param qtdRegistrosPagina Quantidade de registros por pagina.
     * @param qtdRegistros Quantidade de registros que serão retornados.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterEstoquePorCNESEPrincipioPaginado(int cnes,
            String principio, int posicaoInicio, int qtdRegistrosPagina,
            int qtdRegistros) {
        String xml = connection.consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(
                cnes, principio, posicaoInicio, qtdRegistrosPagina, qtdRegistros
        );
        XmlParser parser = new XmlParser();
        String retorno = parser.getMessage(xml);
        return retorno;
    }

    /**
     * Método que executa remotamente o serviço e trata a resposta. 
     * @param cnes Número do CNES para busca, 7 posições.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterDadosEEstoquePorCNES(int cnes) {
        String xml = connection.consultarProdutoPorCNESDispensacao(cnes
        );
        XmlParser parser = new XmlParser();
        String retorno = parser.getMessage(xml);
        return retorno;
    }

    /**
     * Método que executa remotamente o serviço e trata a resposta. 
     * @param cnes Número do CNES para busca, 7 posições.
     * @param posicaoInicio Posição de início da listagem.
     * @param qtdRegistrosPagina Quantidade de registros por pagina.
     * @param qtdRegistros Quantidade de registros que serão retornados.
     * @return xml Resposta tratada conforme o negócio.
     */
    @Override
    public String obterDadosEEstoquePorCNESPaginado(int cnes, int posicaoInicio,
            int qtdRegistrosPagina, int qtdRegistros) {
        String xml = connection.consultarProdutoPorCNESDispensacaoPaginado(
                cnes, posicaoInicio, qtdRegistrosPagina, qtdRegistros
        );
        XmlParser parser = new XmlParser();
        String retorno = parser.getMessage(xml);
        return retorno;
    }
    
}
