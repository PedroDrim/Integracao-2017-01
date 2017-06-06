package br.ufg.inf.horus.implementation;

import br.ufg.inf.horus.interfaceh.BsusException;
import br.ufg.inf.horus.interfaceh.Log;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;

/**
 *
 * @author Pedro Classe que implemente o CircuitBreaker do sistema com o
 * Hystrix.
 */
public class CircuitBreaker extends HystrixCommand<String> {

    private String soapRequest;
    private String destination;
    private Log log;
    private HttpRequest request = new HttpRequest();

    /**
     * Construtor do CircuitBreaker.
     *
     * @param soapRequest String com a requisição.
     * @param destination Url de destino.
     * @param log Objeto para exibição de mensagens (Log).
     */
    public CircuitBreaker(String soapRequest, String destination, Log log) {

        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("HorusTolerance"))
                .andCommandPropertiesDefaults(
                        HystrixCommandProperties.Setter()
                                .withExecutionTimeoutEnabled(false)
                ));

        if (soapRequest == null) {
            String errorMessage = "O parametro 'soapRequest' não possui valor.";
            this.catchError(errorMessage);
        }

        if (destination == null) {
            String errorMessage = "O parametro 'destination' não possui valor.";
            this.catchError(errorMessage);
        }

        if (log == null) {
            String errorMessage = "O parametro 'log' não possui valor.";
            this.catchError(errorMessage);
        }

        this.log = log;
        this.soapRequest = soapRequest;
        this.destination = destination;
    }

    /**
     * Método run para threads.
     *
     * @return resposta Resposta da requisição
     * @throws Exception
     */
    @Override
    protected String run() throws Exception {
        String resposta = this.request.request(this.destination, this.soapRequest);
        return resposta;
    }

    /**
     * Método para captura e exibição de erros.
     *
     * @param message Mensagem do erro referente.
     */
    private void catchError(String message) throws BsusException {
        log.erro(message);
        throw new BsusException(message);
    }
}
