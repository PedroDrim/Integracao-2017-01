/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.service;

import br.ufg.inf.horus.util.validations.BsusException;
import br.ufg.inf.horus.implementation.model.HttpInterface;
import br.ufg.inf.horus.util.model.Log;
import br.ufg.inf.horus.util.validations.BsusValidator;
import java.io.IOException;
import java.nio.charset.UnsupportedCharsetException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

/**
 * Classe que faz a conexão e requisição.
 *
 * @see HttpInterface
 * @author Vinicius
 */
public class HttpRequest implements HttpInterface {

    /**
     * Método que executa o POST.
     *
     * @see HttpInterface
     * @param url Url para a requisição.
     * @param body Mensagem da requisição.
     * @param log Objeto para exibição de mensagens Log (opcional).
     * @return Resposta da requisição.
     */
    @Override
    public String request(String url, String body, Log log) 
            throws BsusException {

        BsusValidator.verifyNull(body," body", log);
        BsusValidator.verifyNull(url," url", log);

        String resposta = "";
        
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            StringEntity strEntity = new StringEntity(body, "UTF-8");
            strEntity.setContentType("text/xml");
            HttpPost post = new HttpPost(url);
            post.setEntity(strEntity);

            HttpResponse response = httpclient.execute(post);
            HttpEntity respEntity = response.getEntity();
            resposta = EntityUtils.toString(respEntity);

        } catch (IOException e) {
            String message = "Houve um erro ao abrir o documento .xml";
            BsusValidator.catchException(e, message, log);
        } catch (UnsupportedCharsetException e) {
            String message = 
                    "O charset 'UTF-8' da mensagem não esta sendo suportado.";
            BsusValidator.catchException(e, message, log);
        } catch (ParseException e) {
            String message = "Houve um erro ao buscar as informações.";
            BsusValidator.catchException(e, message, log);
        }
        
        return resposta;
    }
}
