package br.ufg.inf.horus.implementation;

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
 *@author Vinicius
 *Classe que faz a conexão e requisição.
 */
public class HttpRequest implements HttpInterface{

    /**
     * Método que executa o POST.
     * @param url Url para a requisição.
     * @param body Mensagem da requisição.
     * @return resp Resposta da requisição.
     */
    @Override
    public String request(String url,String body){
        String resp="";
        
        try {
            CloseableHttpClient httpclient = HttpClientBuilder.create().build();
            StringEntity strEntity = new StringEntity(body,"UTF-8");
            strEntity.setContentType("text/xml");
            HttpPost post = new HttpPost(url);
            post.setEntity(strEntity);

            HttpResponse response = httpclient.execute(post);
            HttpEntity respEntity = response.getEntity();
            resp = EntityUtils.toString(respEntity);
        }
        catch (IOException | UnsupportedCharsetException | ParseException e) {
        }

        return resp;
    }
}
