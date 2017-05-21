package br.ufg.inf.horus.implementation;
/**
 *
 * @author Vinicius
 * Interface Connection com os serviços do Horus.
 */
public interface Connection {

    String consultarPosicaoEstoquePorCNES(String username, String password, int cnes);
    String consultarPosicaoEstoquePorCNESPrincipioAtivo(String username, String password, int cnes, String principio);
    String consultarPosicaoEstoquePorCNESPrincipioAtivoPaginado(String username, String password, int cnes, String principio, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);
    String consultarProdutoPorCNESDispensacao(String username, String password, int cnes);
    String consultarProdutoPorCNESDispensacaoPaginado(String username, String password, int cnes, int posicaoInicio, int qtdRegistrosPagina, int qtdRegistros);
  
}
