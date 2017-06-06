package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.interfaceh.Log;
import java.util.concurrent.Future;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author Pedro
 */
public class CircuitBreakerTest {

    private String destination;
    private String header;
    private Log log;
    
    public CircuitBreakerTest() {
        this.destination = "https://servicos.saude.gov.br/horus/v1r0/EstoqueService";
        this.log = new Log() {
            @Override
            public void info(String message) {
                System.out.println(message);
            }

            @Override
            public void erro(String message) {
                System.err.println(message);
            }
        };
    }

    /**
     * Método executado antes de cada teste, responsável por inicializar as variaveis comuns.
     */
    @Before
    public void setUp() {
        HeaderGenerator headerGenerator = new HeaderGenerator();
        this.header = headerGenerator.getHeader("user", "123", 0);
    }

    /**
     * Método executado antes de cada teste, responsável por limpar as variaveis comuns.
     */
    @After
    public void tearDown() {
        this.header = "";
    }

    /**
     * Caso de teste resonsável por testar a classe CicruitBreaker de modo assincrono.
     * @throws Exception 
     */
    @Test
    public void singleAsynchronousTest() throws Exception {
  
        CircuitBreaker circuitBreaker = new CircuitBreaker(header, destination, log);
        
        Future<String> asynchrnous = circuitBreaker.queue();
        String response = asynchrnous.get();
        
        String esperado = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
        "<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\"><soap:Header xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"/><soap:Body xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\"><soap:Fault><soap:Code><env:Value xmlns:env=\"http://www.w3.org/2003/05/soap-envelope\">env:Sender</env:Value></soap:Code><soap:Reason><soap:Text xml:lang=\"pt-BR\">Uma ou mais regras negociais foram violadas, verifique a lista de erros.</soap:Text></soap:Reason><soap:Detail><msf:MSFalha xmlns:msf=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/msfalha\"><msf:Mensagem xmlns:men=\"http://servicos.saude.gov.br/wsdl/mensageria/falha/v5r0/mensagem\"><men:codigo>OSB_SEM_AUTENTICACAO</men:codigo><men:descricao>As credenciais informadas não são válidas</men:descricao></msf:Mensagem></msf:MSFalha></soap:Detail></soap:Fault></soap:Body></soap:Envelope>";
        
        assertEquals(response, esperado);
    }
}

/**
 * Classe responsável pela geração do cabeçalho utilizado para os testes da classe CircuitBreaker
 * @author Pedro
 */
class HeaderGenerator {

    /**
     * Gera o cabeçalho da requisição com base em um username, password e número cnes.
     * @param username Usuário do sistema Horus.
     * @param senha Senha do usuário.
     * @param cnes Número do CNES para busca, 7 posições.
     * @return Retorna o cabeçalho da requisição.
     */
    public String getHeader(String username, String password, int cnes) {

        StringBuilder soap = new StringBuilder();
        soap.append(buildHeaderXml(username, password));
        soap.append(" <est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" <est:cnes>").append(cnes).append("</est:cnes>\n </est:requestConsultarPosicaoEstoquePorCNES>\n");
        soap.append(" </soap:Body>\n </soap:Envelope>");
        
        return soap.toString();
    }

    /**
     * Gera o conteúdo do cabeçalho da requisição com base em um username e password.
     * @param username Usuário do sistema Horus.
     * @param senha Senha do usuário.
     * @return Retorna o conteúdo do cabeçalho da requisição.
     */
    public String buildHeaderXml(String username, String password) {
        StringBuilder str = new StringBuilder();
        str.append("<soap:Envelope xmlns:soap=\"http://www.w3.org/2003/05/soap-envelope\" xmlns:est=\"http://servicos.saude.gov.br/horus/v1r0/EstoqueService\">\n");
        str.append(" <soap:Header>\n");
        str.append(" <wsse:Security xmlns:wsse=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wsswssecurity-secext-1.0.xsd\">\n");
        str.append(" <wsse:UsernameToken wsu:Id=\"Id-0001334008436683-000000002c4a1908-1\" xmlns:wsu=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-utility-1.0.xsd\">\n");
        str.append(" <wsse:Username>").append(username).append("</wsse:Username>\n");
        str.append(" <wsse:Password Type=\"http://docs.oasis-open.org/wss/2004/01/oasis-200401-wssusername-token-profile-1.0#PasswordText\">").append(password).append("</wsse:Password>\n");
        str.append(" </wsse:UsernameToken>\n </wsse:Security>\n </soap:Header>\n <soap:Body>\n");
        return str.toString();
    }
}
