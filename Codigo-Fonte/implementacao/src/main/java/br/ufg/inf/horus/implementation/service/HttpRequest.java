/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufg.inf.horus.implementation.service;

import br.ufg.inf.horus.implementation.controller.BsusException;
import br.ufg.inf.horus.implementation.model.HttpInterface;
import br.ufg.inf.horus.implementation.model.Log;
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
     * @param log Objeto para exibição de mensagens (Log).
     * @return Resposta da requisição.
     */
    @Override
    public String request(String url, String body, Log log) 
            throws BsusException {

        if (url == null) {
            String logMessage = "O parametro 'url' não possui valor.";
            log.erro(logMessage);
            throw new BsusException(logMessage);
        }

        if (body == null) {
            String logMessage = "O parametro 'body' não possui valor.";
            log.erro(logMessage);
            throw new BsusException(logMessage);
        }

        if (log == null) {
            String logMessage = "O parametro 'log' não possui valor.";
            throw new BsusException(logMessage);
        }

        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            StringEntity strEntity = new StringEntity(body, "UTF-8");
            strEntity.setContentType("text/xml");
            HttpPost post = new HttpPost(url);
            post.setEntity(strEntity);

            HttpResponse response = httpclient.execute(post);
            HttpEntity respEntity = response.getEntity();
            return (EntityUtils.toString(respEntity));

        } catch (IOException e) {
            String message = "Houve um erro ao abrir o documento .xml";
            log.erro(message);
            throw new BsusException(message);
        } catch (UnsupportedCharsetException e) {
            String message = 
                    "O charset 'UTF-8' da mensagem não esta sendo suportado.";
            log.erro(message);
            throw new BsusException(message);
        } catch (ParseException e) {
            String message = "Houve um erro ao buscar as informações.";
            log.erro(message);
            throw new BsusException(message);
        }
    }
}
