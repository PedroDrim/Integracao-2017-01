package br.ufg.inf.horus.implementation;

/**
 *
 * @author Vinicius
 * Interface Connection com os serviços do Horus.
 */
public interface Connection {

    void setCredential(String usuario, String senha);
    void setURL(String url);
    void setLog(Log log);
    String consultarPosicaoEstoquePorCNES(int cnes);
    String consultarPosicaoEstoquePorCNESPrincipioAtivo(int cnes,
            String principio);
    String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(int cnes,
            String principio, int posicaoInicio, int qtdRegistrosPagina,
            int qtdRegistros);
    String consultarProdutoPorCNESDispensacao(int cnes);
    String consultarProdutoPorCNESDispensacaoPaginado(int cnes,
            int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);
  
}
